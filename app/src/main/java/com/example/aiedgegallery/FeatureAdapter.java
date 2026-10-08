package com.example.aiedgegallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.FeatureViewHolder> {

    private final List<FeatureItem> items;
    private final OnFeatureClickListener listener;

    public interface OnFeatureClickListener {
        void onFeatureClick(int position);
    }

    public FeatureAdapter(List<FeatureItem> items, OnFeatureClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FeatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_feature, parent, false);
        return new FeatureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeatureViewHolder holder, int position) {
        FeatureItem item = items.get(position);
        holder.title.setText(item.getTitle());
        holder.subtitle.setText(item.getSubtitle());

        holder.itemView.setOnClickListener(v -> listener.onFeatureClick(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class FeatureViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView subtitle;

        FeatureViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.featureTitle);
            subtitle = itemView.findViewById(R.id.featureSubtitle);
        }
    }
}