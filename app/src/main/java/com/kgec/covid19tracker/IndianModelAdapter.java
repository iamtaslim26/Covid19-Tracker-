package com.kgec.covid19tracker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.content.ContentValues.TAG;

public class IndianModelAdapter extends RecyclerView.Adapter<IndianModelAdapter.ItemModelHolder> {

    private Context context;
    private List<IndiaModel>list;

    public IndianModelAdapter(Context context, List<IndiaModel> list) {

        Log.w("Oncons", String.valueOf(list.size()));
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ItemModelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.w("Oncreate", String.valueOf(list.size()));
        View view= LayoutInflater.from(context).inflate(R.layout.state_item_layout,parent,false);
        return new IndianModelAdapter.ItemModelHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemModelHolder holder, int position) {

        Log.w("Onbind", String.valueOf(list.size()));

        final  IndiaModel model=list.get(position);
        //final String model=list.get(position);

        holder.cases.setText(model.getTotalConfirmed());
        holder.state_name.setText(model.getLoc());
        holder.recovered.setText(model.getDischarged());
        holder.deaths.setText(model.getDeaths());

        //holder.cases.setText(model);
//        Toast.makeText(context, list.size(), Toast.LENGTH_SHORT).show();

    }



    @Override
    public int getItemCount() {
        return list.size();
    }

      class ItemModelHolder extends RecyclerView.ViewHolder {

        TextView state_name,cases,recovered,deaths;
        public ItemModelHolder(@NonNull View itemView) {
            super(itemView);

            state_name=itemView.findViewById(R.id.state_name);
            cases=itemView.findViewById(R.id.no_of_cases1);
            recovered=itemView.findViewById(R.id.no_of_recovered1);
            deaths=itemView.findViewById(R.id.no_of_deaths1);
        }
    }
}
