package com.example.aiedgegallery;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private RecyclerView featureRecyclerView;
    private RecyclerView chatRecyclerView;
    private EditText messageInput;
    private Button sendButton;
    private Button voiceButton;
    private Button imageButton;
    private TextToSpeech textToSpeech;

    private final List<FeatureItem> features = new ArrayList<>();
    private final List<ChatMessage> chatMessages = new ArrayList<>();
    private ChatAdapter chatAdapter;

    private final ActivityResultLauncher<Intent> speechLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    List<String> matches = result.getData().getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (matches != null && !matches.isEmpty()) {
                        String spoken = matches.get(0);
                        messageInput.setText(spoken);
                        messageInput.setSelection(spoken.length());
                    }
                }
            });

    private final ActivityResultLauncher<Intent> imageLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri imageUri = result.getData().getData();
                    if (imageUri != null) {
                        Toast.makeText(this, "Image selected: " + imageUri, Toast.LENGTH_SHORT).show();
                        addMessage("AI", "Image selected. We can add vision analysis next.", false);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        featureRecyclerView = findViewById(R.id.featureRecyclerView);
        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        messageInput = findViewById(R.id.messageInput);
        sendButton = findViewById(R.id.sendButton);
        voiceButton = findViewById(R.id.voiceButton);
        imageButton = findViewById(R.id.imageButton);

        textToSpeech = new TextToSpeech(this, this);

        setupFeatures();
        setupChat();

        sendButton.setOnClickListener(v -> handleSend());
        voiceButton.setOnClickListener(v -> startVoiceInput());
        imageButton.setOnClickListener(v -> openImagePicker());
    }

    private void setupFeatures() {
        features.clear();
        features.add(new FeatureItem("AI Chat", "Local assistant"));
        features.add(new FeatureItem("Voice", "Speech support"));
        features.add(new FeatureItem("Ask Image", "Camera + gallery"));
        features.add(new FeatureItem("Video", "Media analysis"));
        features.add(new FeatureItem("Models", "LLM integration"));
        features.add(new FeatureItem("Fast AI", "Low latency"));

        FeatureAdapter adapter = new FeatureAdapter(features, position -> {
            String selected = features.get(position).getTitle();
            Toast.makeText(this, selected + " selected", Toast.LENGTH_SHORT).show();
        });

        featureRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        featureRecyclerView.setAdapter(adapter);
    }

    private void setupChat() {
        chatAdapter = new ChatAdapter(chatMessages);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatRecyclerView.setAdapter(chatAdapter);

        addMessage("AI", "Hello! I am your AI assistant. Ask me anything.", false);
    }

    private void handleSend() {
        String text = messageInput.getText().toString().trim();
        if (TextUtils.isEmpty(text)) {
            return;
        }

        addMessage("You", text, true);
        messageInput.setText("");

        String reply = generateAssistantReply(text);
        addMessage("AI", reply, false);
        speakReply(reply);
    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak your message");
        speechLauncher.launch(intent);
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        imageLauncher.launch(intent);
    }

    private String generateAssistantReply(String input) {
        String lower = input.toLowerCase();

        if (lower.contains("hello") || lower.contains("hi")) {
            return "Hello! I can help with chat, voice, image, and AI features.";
        }
        if (lower.contains("voice")) {
            return "Voice input is enabled. We can add better speech recognition and conversation flows.";
        }
        if (lower.contains("image")) {
            return "Image support is ready. Next we can add camera preview and ML features.";
        }
        if (lower.contains("video")) {
            return "Video tools can be connected with a media player and smart analysis.";
        }
        if (lower.contains("model")) {
            return "On-device LLM support can be added for offline AI execution.";
        }
        if (lower.contains("fast")) {
            return "Fast responses are possible with less prompt size and optimized local models.";
        }
        if (lower.contains("search")) {
            return "Search features can be integrated with local indexing and vector databases.";
        }
        if (lower.contains("camera")) {
            return "Camera support can be added for real-time image capture and analysis.";
        }
        if (lower.contains("offline")) {
            return "This app is fully offline-ready. No internet connection required for core AI features.";
        }

        return "I understand: " + input + ". This app is inspired by Google AI Edge Gallery and can expand with AI tools.";
    }

    private void speakReply(String message) {
        if (textToSpeech != null) {
            textToSpeech.speak(message, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    private void addMessage(String sender, String text, boolean isUser) {
        chatMessages.add(new ChatMessage(sender, text, isUser));
        chatAdapter.notifyItemInserted(chatMessages.size() - 1);
        chatRecyclerView.scrollToPosition(chatMessages.size() - 1);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            textToSpeech.setLanguage(Locale.US);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}