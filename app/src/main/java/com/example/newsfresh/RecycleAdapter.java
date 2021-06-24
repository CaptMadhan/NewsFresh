package com.example.newsfresh;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

class  RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder>
{
    ArrayList<News> items = new ArrayList<>();
    Context context;

    RecycleAdapter(Context context){
        this.context = context;
    }
    @NonNull
    @NotNull
    @Override
    // create new view when the recycler view is opened
    public RecycleAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.item_news,parent,false);
        return new ViewHolder(view);
    }

    @Override
    //Bind the data to the views i.e serve the data to the views
    public void onBindViewHolder(@NonNull @NotNull RecycleAdapter.ViewHolder holder, int position) {

        News currentItem = items.get(position);
        holder.title.setText(currentItem.title);
        holder.content.setText(currentItem.content);
        holder.source.setText(currentItem.source);
        Glide.with(holder.itemView.getContext()).load(currentItem.imageUrl).into(holder.newsImg);
        holder.title.setOnClickListener(v -> NewsItemClicked(currentItem));

    }
    @Override
    // for number of views
    public int getItemCount() {

        return items.size();
    }
    void updateNews(ArrayList<News> updatedNews){
        items.clear();
        items.addAll(updatedNews);
        notifyDataSetChanged(); // This will call all the 3 functions again with the updated items
    }

    private void NewsItemClicked( News item) {
        int colorInt = Color.parseColor("#FF0000"); //red
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(colorInt);
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(context, Uri.parse(item.url));
    }


    // Fetch the custom view created
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView content,source;
        ImageView newsImg;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            newsImg = itemView.findViewById(R.id.image);
            content = itemView.findViewById(R.id.content);
            source = itemView.findViewById(R.id.source);

        }
    }
}