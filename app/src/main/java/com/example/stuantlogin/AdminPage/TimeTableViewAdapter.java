package com.example.stuantlogin.AdminPage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stuantlogin.R;

import java.util.ArrayList;

public class TimeTableViewAdapter extends RecyclerView.Adapter<TimeTableViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<TimeTableModule> moduleArrayList;

    public TimeTableViewAdapter(Context context, ArrayList<TimeTableModule> moduleArrayList) {
        this.context = context;
        this.moduleArrayList = moduleArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_timetable_layout, null, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvVHr12.setText(moduleArrayList.get(position).getsHr1());
        holder.tvVHr22.setText(moduleArrayList.get(position).getsHr2());
        holder.tvVHr32.setText(moduleArrayList.get(position).getsHr3());
        holder.tvVHr42.setText(moduleArrayList.get(position).getsHr4());
        holder.tvVHr52.setText(moduleArrayList.get(position).getsHr5());
        holder.tvVHr62.setText(moduleArrayList.get(position).getsHr6());
        holder.tvVHr72.setText(moduleArrayList.get(position).getsHr7());
        holder.tvVHr82.setText(moduleArrayList.get(position).getsHr8());
    }

    @Override
    public int getItemCount() {
        return moduleArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvVHr12, tvVHr22, tvVHr32, tvVHr42, tvVHr52, tvVHr62, tvVHr72, tvVHr82;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvVHr12 = itemView.findViewById(R.id.tvVHr1);
            tvVHr22 = itemView.findViewById(R.id.tvVHr2);
            tvVHr32 = itemView.findViewById(R.id.tvVHr3);
            tvVHr42 = itemView.findViewById(R.id.tvVHr4);
            tvVHr52 = itemView.findViewById(R.id.tvVHr5);
            tvVHr62 = itemView.findViewById(R.id.tvVHr6);
            tvVHr72 = itemView.findViewById(R.id.tvVHr7);
            tvVHr82 = itemView.findViewById(R.id.tvVHr8);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String hr1 = tvVHr12.getText().toString();
                    String hr2 = tvVHr22.getText().toString();
                    String hr3 = tvVHr32.getText().toString();
                    String hr4 = tvVHr42.getText().toString();
                    String hr5 = tvVHr52.getText().toString();
                    String hr6 = tvVHr62.getText().toString();
                    String hr7 = tvVHr72.getText().toString();
                    String hr8 = tvVHr82.getText().toString();

                    Intent tTableIntent = new Intent(context, TimeTableAdmin.class);

                    tTableIntent.putExtra("Hr1", hr1);
                    tTableIntent.putExtra("Hr2", hr2);
                    tTableIntent.putExtra("Hr3", hr3);
                    tTableIntent.putExtra("Hr4", hr4);
                    tTableIntent.putExtra("Hr5", hr5);
                    tTableIntent.putExtra("Hr6", hr6);
                    tTableIntent.putExtra("Hr7", hr7);
                    tTableIntent.putExtra("Hr8", hr8);

                    context.startActivity(tTableIntent);
                }
            });
        }
    }
}
