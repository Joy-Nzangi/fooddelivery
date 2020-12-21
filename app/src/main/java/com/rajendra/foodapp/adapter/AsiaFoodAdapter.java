package com.rajendra.foodapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rajendra.foodapp.R;
import com.rajendra.foodapp.model.KenyanFood;

import java.util.List;


public class AsiaFoodAdapter extends RecyclerView.Adapter<AsiaFoodAdapter.AsiaFoodViewHolder> {

    Context context;
    List<KenyanFood> kenyanFoodList;



    public AsiaFoodAdapter(Context context, List<KenyanFood> kenyanFoodList) {
        this.context = context;
        this.kenyanFoodList = kenyanFoodList;
    }

    @NonNull
    @Override
    public AsiaFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.asia_food_row_item, parent, false);
        return new AsiaFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder( AsiaFoodViewHolder holder, int position) {

        holder.foodImage.setImageResource(kenyanFoodList.get(position).getImageUrl());
        holder.name.setText(kenyanFoodList.get(position).getName());
        holder.price.setText(kenyanFoodList.get(position).getPrice());
        holder.rating.setText(kenyanFoodList.get(position).getRating());
        holder.restorantName.setText(kenyanFoodList.get(position).getRestorantname());

    }

    @Override
    public int getItemCount() {
        return kenyanFoodList.size();
    }


    public static final class AsiaFoodViewHolder extends RecyclerView.ViewHolder{


        ImageView foodImage;
        TextView price, name, rating, restorantName;

        public AsiaFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.food_image);
            price = itemView.findViewById(R.id.price);
            name = itemView.findViewById(R.id.name);
            rating = itemView.findViewById(R.id.rating);
            restorantName = itemView.findViewById(R.id.restorant_name);



        }
    }

}
