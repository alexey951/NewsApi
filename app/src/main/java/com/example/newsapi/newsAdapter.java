package com.example.newsapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class newsAdapter extends RecyclerView.Adapter<newsAdapter.newsHolder> {
    MainActivity mainActivity;
    List<newsModel> allNewsList;
    public newsAdapter(MainActivity mainActivity, List<newsModel> allNewsList) {
        this.mainActivity = mainActivity;
        this.allNewsList = allNewsList;
    }

    @NonNull
    @Override
    public newsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new newsHolder(LayoutInflater.from(mainActivity).inflate(R.layout.items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull newsHolder holder, int position) {
        final newsModel a = allNewsList.get(position);
        holder.tvTitle.setText(a.getTitle());
        holder.tvSource.setText(a.getName());
        holder.tvDate.setText(dateTime(a.getPublishedAt()));
        holder.tvDate.setText(dateTime(a.getPublishedAt()));

        String imageUrl = a.getUrlToImage();
        String url = a.getUrl();

        Picasso.with(mainActivity).load(imageUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return allNewsList.size();
    }

    class newsHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvSource, tvDate;
        ImageView imageView;
        CardView cardView;
        public newsHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSource = itemView.findViewById(R.id.tvSource);
            tvDate = itemView.findViewById(R.id.tvDate);
            imageView = itemView.findViewById(R.id.image);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

    public String dateTime(String t) {
        PrettyTime prettyTime = new PrettyTime(new Locale(getCountry()));
        String time = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:",Locale.ENGLISH);
            Date date = simpleDateFormat.parse(t);
            time = prettyTime.format(date);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public String getCountry() {
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }
}
