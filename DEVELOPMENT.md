# Development Guide - AI Edge Gallery Java + XML

## Local Development Setup

### Prerequisites
1. Install Android Studio (latest version)
2. Install Java 17 SDK
3. Install Android SDK:
   - SDK Platform 26 (minimum)
   - SDK Platform 34 (target)
   - Android SDK Build-tools

### Initial Setup

```bash
# Clone repo
git clone https://github.com/hih569950-byte/ai-edge-gallery-java-offline.git
cd ai-edge-gallery-java-offline

# Open in Android Studio
studio .
```

## Project Structure

### Java Classes

**MainActivity.java**
- Main activity - handles UI and user interaction
- Manages chat, voice input, image picker
- Contains AI reply logic
- Initializes TextToSpeech

**ChatMessage.java**
- Data class for chat messages
- Stores sender, text, and user/AI flag

**ChatAdapter.java**
- RecyclerView adapter for chat messages
- Displays user messages (right) and AI responses (left)
- Two ViewHolder types: UserViewHolder, AiViewHolder

**FeatureItem.java**
- Data class for feature cards
- Stores title and subtitle

**FeatureAdapter.java**
- RecyclerView adapter for feature cards
- Horizontal scrolling feature showcase
- Click listener for feature selection

### XML Layouts

**activity_main.xml**
- Main screen layout
- Contains: Title, Features RecyclerView, Chat RecyclerView, Input bar
- Dark theme colors (#0F172A background)

**item_feature.xml**
- Individual feature card layout
- Shows title and subtitle
- Clickable card background (#1E293B)

**item_chat_left.xml**
- AI message bubble layout
- Left-aligned with dark background (#1E293B)

**item_chat_right.xml**
- User message bubble layout
- Right-aligned with blue background (#2563EB)

### Resources

**colors.xml** - App color palette
**strings.xml** - App text strings
**themes.xml** - Material Design 3 dark theme

## Build Process

### Debug Build

```bash
# Using Gradle wrapper
./gradlew assembleDebug

# Output: app/build/outputs/apk/debug/app-debug.apk
```

### Release Build

```bash
# Create unsigned APK
./gradlew assembleRelease

# Output: app/build/outputs/apk/release/app-release-unsigned.apk
```

### Sign APK (for Play Store)

```bash
# Create keystore (one time)
jarsigner -genkey -v -keystore my-release-key.keystore \
  -keyalg RSA -keysize 2048 -validity 10000 \
  -alias my-key-alias

# Sign APK
jarsigner -verbose -sigalg SHA256withRSA -digestalg SHA-256 \
  -keystore my-release-key.keystore \
  app/build/outputs/apk/release/app-release-unsigned.apk \
  my-key-alias

# Verify signature
jarsigner -verify -verbose -certs \
  app/build/outputs/apk/release/app-release-unsigned.apk
```

## Testing

### Unit Tests
- Location: `app/src/test/java/`
- Add tests for ChatMessage, FeatureItem, etc.

### Instrumentation Tests (UI Tests)
- Location: `app/src/androidTest/java/`
- Test MainActivity, Adapters, etc.
- Requires emulator or device

### Manual Testing Checklist

- [ ] Chat messages appear correctly
- [ ] Voice input works (needs device with mic)
- [ ] Text-to-Speech responds
- [ ] Image picker opens gallery
- [ ] Feature cards scroll horizontally
- [ ] Dark theme displays correctly
- [ ] App handles long messages
- [ ] Orientation changes preserve chat history

## Common Development Tasks

### Add New Feature

1. Create feature item in `setupFeatures()`:
   ```java
   features.add(new FeatureItem("New Feature", "Description"));
   ```

2. Add reply logic in `generateAssistantReply()`:
   ```java
   if (lower.contains("keyword")) {
       return "Your response";
   }
   ```

3. Update AI logic to handle new keywords

### Integrate External API

1. Add HTTP library to `build.gradle`:
   ```gradle
   implementation 'com.squareup.okhttp3:okhttp:4.11.0'
   ```

2. Replace `generateAssistantReply()` with API call:
   ```java
   private String callGeminiAPI(String prompt) {
       // Make HTTP request to API
       // Return response
   }
   ```

3. Handle async responses:
   ```java
   new Thread(() -> {
       String reply = callGeminiAPI(text);
       runOnUiThread(() -> addMessage("AI", reply, false));
   }).start();
   ```

### Add Local ML Model

1. Add TensorFlow Lite to `build.gradle`:
   ```gradle
   implementation 'org.tensorflow:tensorflow-lite:2.14.0'
   ```

2. Place `.tflite` model in `app/src/main/assets/`

3. Load and run model in `MainActivity`:
   ```java
   private Interpreter tflite;
   
   private void loadModel() {
       try {
           tflite = new Interpreter(loadModelFile());
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   ```

## Code Style Guidelines

- Follow Java naming conventions
- Use camelCase for variables/methods
- Use UPPER_CASE for constants
- Keep methods small (< 50 lines ideally)
- Use meaningful variable names
- Add comments for complex logic
- Handle null checks and exceptions

## Performance Tips

1. **Avoid Main Thread Blocking**
   ```java
   new Thread(() -> {
       // Heavy work here
       runOnUiThread(() -> {
           // Update UI
       });
   }).start();
   ```

2. **Reuse RecyclerView Adapters**
   - Don't recreate adapters on every update
   - Use `notifyItemInserted()` instead of `notifyDataSetChanged()`

3. **Manage Memory**
   - Release TextToSpeech in `onDestroy()`
   - Close file streams properly
   - Avoid memory leaks with listeners

## Debugging

### Logcat Viewing
```bash
./gradlew logcat
# Or use Android Studio Logcat tab
```

### Add Debug Logs
```java
import android.util.Log;

Log.d("TAG", "Debug message: " + variable);
Log.e("TAG", "Error: ", exception);
```

### Debugger
- Set breakpoints by clicking line numbers
- Run in Debug mode (Shift + F9)
- Step through code with F10/F11

## Publishing to Play Store

1. Sign release APK (see "Sign APK" section above)
2. Test on multiple devices
3. Create Play Store listing
4. Upload signed APK
5. Fill in description, screenshots, etc.
6. Submit for review

## Troubleshooting Development Issues

### Gradle Build Fails
```bash
# Clean and rebuild
./gradlew clean
./gradlew build
```

### App Crashes on Launch
- Check Logcat for stack trace
- Verify all imports are correct
- Check AndroidManifest.xml for typos
- Ensure Activity name matches class name

### RecyclerView Not Showing
- Verify LayoutManager is set
- Check adapter has items
- Verify layout XML has proper dimensions

### Voice Input Not Working
- Check RECORD_AUDIO permission granted
- Verify device has mic
- Test on emulator with audio support

## Resources

- [Android Developer Docs](https://developer.android.com/docs)
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture)
- [Material Design](https://material.io/design)
- [TensorFlow Lite Android](https://www.tensorflow.org/lite/guide/android)

---

**Happy coding! 🚀**
