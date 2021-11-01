package com.example.covidtracker;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    @NonNull
    private ArrayList<Model>modelList;
    private Context context;

    public Adapter(@NonNull ArrayList<Model> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.testing,parent,false);
        return new Adapter.ViewHolder(view);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model model= modelList.get(position);
        holder.name.setText(model.getSname());
        holder.dname.setText(model.getDname());
        holder.total.setText(""+Math.toIntExact((Long)model.getTotal()));
        holder.death.setText(""+Math.toIntExact((Long)model.getDeath()));
        holder.cured.setText(""+Math.toIntExact((Long)model.getCured()));
        holder.active.setText(""+Math.toIntExact((Long)model.getActive()));
        holder.incactive.setText(""+Math.toIntExact((Long)model.getIncAct()));
        holder.inccured.setText(""+Math.toIntExact((Long)model.getIncRec()));
        holder.incdeath.setText(""+Math.toIntExact((Long)model.getIncDea()));
    }
    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public void filterList(ArrayList<Model> filteredlist) {
        modelList=filteredlist;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private  TextView  name;
        private  TextView  dname;
        private TextView total;
        private TextView death;
        private TextView cured;
        private TextView active;
        private TextView incactive;
        private TextView inccured;
        private TextView incdeath;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            name=itemView.findViewById(R.id.state);
            dname=itemView.findViewById(R.id.district);
            active =itemView.findViewById(R.id.active);
            cured=itemView.findViewById(R.id.cured);
            death=itemView.findViewById(R.id.death);
            total=itemView.findViewById(R.id.total);
            incactive=itemView.findViewById(R.id.incactive);
            inccured=itemView.findViewById(R.id.inccured);
            incdeath=itemView.findViewById(R.id.incdeath);
        }


    }
}
