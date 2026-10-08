# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# signingConfigs.signing config. 'proguard-android.txt' is included in the
# Android SDK (usually at tools/proguard/etc/proguard-android.txt), and that file
# is included in the 'proguard-android.txt' configuration. Activities that use
# AppBarActivity from androidX or NEED the android.support classes END up resolving
# some androix classes from support library instead of androidX
# Add options that fit your needs.
# See https://developer.android.com/guide/topics/security/security

# Keep project default rules
-keep class com.example.aiedgegallery.** { *; }
-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Service
-keepclassmembers class * {
    public <init>(...);
}