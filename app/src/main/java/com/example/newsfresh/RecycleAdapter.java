package com.example.newsfresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder>
{
    String[] data;
    Context context;

    public RecycleAdapter(String[] data, Context context) {
        this.data = data;
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

        holder.text.setText(data[position]);
        holder.text.setOnClickListener(v -> Toast.makeText(context,"Clicked on "+ data[position],Toast.LENGTH_SHORT).show());

    }

    @Override
    // for number of views
    public int getItemCount() {

        return data.length;
    }
    // Fetch the custom view created
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.TextDisplayed);
        }
    }
}