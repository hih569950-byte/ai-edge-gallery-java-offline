package com.example.aiedgegallery;

public class FeatureItem {
    private final String title;
    private final String subtitle;

    public FeatureItem(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }
}