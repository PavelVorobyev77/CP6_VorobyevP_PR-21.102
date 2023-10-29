package com.example.cp6_vorobyevp_pr_21102;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Weather> weather;

    public WeatherAdapter(Context context, List<Weather> weather) {
        this.inflater = LayoutInflater.from(context);
        this.weather = weather;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Weather w = weather.get(position);
        Glide.with(holder.itemView.getContext())
                .load(w.getUrl())
                .fitCenter()
                .into(holder.flagView);
        holder.nameView.setText(w.getDate());
        holder.capitalView.setText(w.getTemp());
    }

    @Override
    public int getItemCount() {
        return weather.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView flagView;
        final TextView nameView, capitalView;

        ViewHolder(View view) {
            super(view);
            flagView = view.findViewById(R.id.imageView);
            nameView = view.findViewById(R.id.textView4);
            capitalView = view.findViewById(R.id.textView5);
        }
    }
}

