package com.kgec.covid19tracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<CountryModel> {

    private Context context;
    private List<CountryModel> list;
    private List<CountryModel> list_filtered;

    public CustomAdapter(@NonNull Context context, List<CountryModel>list) {
        super(context, R.layout.custom_item_layout,list);

        this.context=context;
        this.list=list;
        this.list_filtered=list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_layout,null,true);

       ImageView country_image=view.findViewById(R.id.country_image);
       TextView country_name=view.findViewById(R.id.country_name);

       country_name.setText(list_filtered.get(position).getCountry());
       Glide.with(context).load(list_filtered.get(position).getFlag()).into(country_image);




        return view;


    }

    @Override
    public int getCount() {
        return list_filtered.size();
    }

    @Nullable
    @Override
    public CountryModel getItem(int position) {
        return list_filtered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = list.size();
                    filterResults.values = list;

                }else{
                    List<CountryModel> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(CountryModel itemsModel:list){
                        if(itemsModel.getCountry().toLowerCase().contains(searchStr)){
                            resultsModel.add(itemsModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }


                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                 list_filtered= (List<CountryModel>) results.values;
                AffectedCountryActivity.list_affected = (List<CountryModel>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}
