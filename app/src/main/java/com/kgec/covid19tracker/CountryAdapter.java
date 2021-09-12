package com.kgec.covid19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private Context context;
    private List<CountryModel>list;

    public CountryAdapter(Context context, List<CountryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view= LayoutInflater.from(context).inflate(R.layout.custom_item_layout,parent,false);
        return new CountryAdapter.ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CountryModel model=list.get(position);
        holder.country_name.setText(model.getCountry());
        Glide.with(context).load(model.getFlag()).into(holder.country_image);


    }






    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView country_image;
        TextView country_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            country_image=itemView.findViewById(R.id.country_image);
            country_name=itemView.findViewById(R.id.country_name);
        }
    }
}


