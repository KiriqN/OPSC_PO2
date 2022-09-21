package com.example.opsc_poe;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class landmark_adapter extends RecyclerView.Adapter<landmark_adapter.MyViewHolder> {

    Context context;
    ArrayList<landmark_model> data;
    LandmarksPage page;


    public landmark_adapter(Context context, ArrayList<landmark_model> data, LandmarksPage page) {

        this.context = context;
        this.data = data;
        this.page = page;


    }

    @NonNull
    @Override
    public landmark_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_row, parent, false);

        return new landmark_adapter.MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull landmark_adapter.MyViewHolder holder, int position) {



        int pos = holder.getAdapterPosition();



        holder.landmark_name.setText(data.get(position).landmark_name);



        holder.open_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                page.LoadItem(data.get(pos));

            }
        });

    }




    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView landmark_name;
        Button open_button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            landmark_name = itemView.findViewById(R.id.landmark_row_name);
            open_button = itemView.findViewById(R.id.open_landmark_button);

            



        }
    }


}


