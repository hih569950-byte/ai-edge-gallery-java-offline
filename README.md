# AI Edge Gallery - Java + XML Android App

An offline-ready AI assistant Android app inspired by Google AI Edge Gallery. Built with **Java** and **XML**, designed for easy offline APK builds.

## Features

✅ **AI Chat** - Local assistant with keyword-based replies  
✅ **Voice Input** - Speech-to-text using Android SpeechRecognizer  
✅ **Text-to-Speech** - AI responses spoken aloud  
✅ **Image Picker** - Select images from gallery  
✅ **Feature Cards** - Horizontal scrollable AI tools showcase  
✅ **Dark Theme UI** - Modern Material Design  
✅ **100% Offline** - No internet required for core features  
✅ **Java + XML** - Easy to build and customize  

## Tech Stack

- **Language**: Java
- **UI Framework**: Android XML Layouts
- **Architecture**: RecyclerView, Adapters
- **Libraries**: 
  - AndroidX AppCompat
  - RecyclerView
  - Material Components
  - TextToSpeech (Android native)
  - SpeechRecognizer (Android native)

## Project Structure

```
ai-edge-gallery-java-offline/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/aiedgegallery/
│   │   │   ├── MainActivity.java
│   │   │   ├── ChatMessage.java
│   │   │   ├── ChatAdapter.java
│   │   │   ├── FeatureItem.java
│   │   │   └── FeatureAdapter.java
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml
│   │   │   │   ├── item_chat_left.xml
│   │   │   │   ├── item_chat_right.xml
│   │   │   │   └── item_feature.xml
│   │   │   ├── values/
│   │   │   │   ├── colors.xml
│   │   │   │   ├── strings.xml
│   │   │   │   └── themes.xml
│   │   └── AndroidManifest.xml
│   ├── build.gradle
│   └── proguard-rules.pro
├── settings.gradle
├── build.gradle
└── README.md
```

## Quick Start

### Prerequisites
- Android Studio (latest version)
- Java 17 or higher
- Android SDK 26+ (minimum)
- Android SDK 34 (target)

### Build Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/hih569950-byte/ai-edge-gallery-java-offline.git
   cd ai-edge-gallery-java-offline
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an existing Android Studio project"
   - Navigate to the project folder
   - Click "Open"

3. **Sync Gradle**
   - Wait for Gradle to sync (might take 2-5 minutes)
   - If prompted, update Gradle and Android plugins

4. **Build the APK**
   - Go to **Build** > **Build Bundle(s)/APK(s)** > **Build APK(s)**
   - Wait for the build to complete

5. **Locate your APK**
   ```
   app/build/outputs/apk/debug/app-debug.apk
   ```

6. **Install on Device/Emulator**
   - Connect your Android device (or start emulator)
   - Run: `adb install app/build/outputs/apk/debug/app-debug.apk`
   - Or use Android Studio's "Run" button

## Release APK (for Google Play)

```bash
./gradlew assembleRelease
# Output: app/build/outputs/apk/release/app-release-unsigned.apk
```

You'll need to sign the APK before uploading to Google Play Store.

## Features Explained

### AI Chat
- Keyword-based intelligent replies
- Extensible reply logic in `MainActivity.generateAssistantReply()`

### Voice Input
- Press 🎤 button to start speaking
- Android SpeechRecognizer converts speech to text
- Text appears in input field

### Text-to-Speech
- AI replies are automatically spoken
- Uses Android TextToSpeech engine
- Supports multiple languages

### Image Picker
- Press 📷 button to select images from gallery
- Intent-based image selection
- Ready for ML Kit / vision API integration

### Dark Theme
- Modern Material Design 3 dark theme
- Easy to customize colors in `colors.xml`

## Customization

### Change App Name
- Edit `app/src/main/res/values/strings.xml`

### Change Colors
- Edit `app/src/main/res/values/colors.xml`

### Add AI Logic
- Modify `MainActivity.generateAssistantReply()` method
- Add your own API calls or ML models

### Add New Features
- Create new Activity/Fragment classes
- Add layouts to `res/layout/`
- Update `AndroidManifest.xml`

## Future Enhancements

- [ ] Integration with TensorFlow Lite models
- [ ] On-device LLM support (Gemma, Mistral)
- [ ] Camera capture + image analysis
- [ ] Video playback and analysis
- [ ] Local model downloading and caching
- [ ] Multi-language support
- [ ] Cloud API fallback (optional)
- [ ] Advanced conversation memory

## Offline vs Online

**Offline (Current)**
- ✅ No internet required
- ✅ Fast local processing
- ✅ Complete privacy
- ❌ Limited AI capabilities (keyword-based)

**Online (Easy to add)**
- Add Gemini API, OpenAI API, or Hugging Face
- Replace `generateAssistantReply()` with API calls
- Requires internet but more powerful AI

## Troubleshooting

### Gradle Sync Fails
- Update Android Studio to latest version
- Delete `.gradle/` folder and re-sync
- Check Java version: `java -version` (should be 17+)

### Build Fails
- Clean project: `Build` > `Clean Project`
- Rebuild: `Build` > `Rebuild Project`
- Check SDK versions in `build.gradle`

### App Crashes on Startup
- Check `logcat` for errors: `Logcat` tab in Android Studio
- Ensure all permissions are granted on device
- Verify Android version is 8.0+ (API 26+)

## Permissions

App requires these Android permissions:
- `INTERNET` - For future API integrations
- `RECORD_AUDIO` - For voice input
- `READ_EXTERNAL_STORAGE` - For image picker

## License

Apache License 2.0 - See LICENSE file

## Support

For issues, feature requests, or questions:
- Open an issue on GitHub
- Check existing issues first

## Credits

Inspired by [Google AI Edge Gallery](https://github.com/google-ai-edge/gallery)

---

**Made with ❤️ for offline AI on Android**
