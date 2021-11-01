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
        holder.RVname.setText(model.getSname());
        holder.RVdname.setText(model.getDname());
        holder.RVtotal.setText(""+Math.toIntExact((Long)model.getTotal()));
        holder.RVdeath.setText(""+Math.toIntExact((Long)model.getDeath()));
        holder.RVcured.setText(""+Math.toIntExact((Long)model.getCured()));
        holder.RVactive.setText(""+Math.toIntExact((Long)model.getActive()));
        holder.RVincactive.setText(""+Math.toIntExact((Long)model.getIncAct()));
        holder.RVinccured.setText(""+Math.toIntExact((Long)model.getIncRec()));
        holder.RVincdeath.setText(""+Math.toIntExact((Long)model.getIncDea()));
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
        private  TextView  RVname;
        private  TextView  RVdname;
        private TextView RVtotal;
        private TextView RVdeath;
        private TextView RVcured;
        private TextView RVactive;
        private TextView RVincactive;
        private TextView RVinccured;
        private TextView RVincdeath;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            RVname=itemView.findViewById(R.id.state);
            RVdname=itemView.findViewById(R.id.district);
            RVactive =itemView.findViewById(R.id.active);
            RVcured=itemView.findViewById(R.id.cured);
            RVdeath=itemView.findViewById(R.id.death);
            RVtotal=itemView.findViewById(R.id.total);
            RVincactive=itemView.findViewById(R.id.incactive);
            RVinccured=itemView.findViewById(R.id.inccured);
            RVincdeath=itemView.findViewById(R.id.incdeath);
        }


    }
}
