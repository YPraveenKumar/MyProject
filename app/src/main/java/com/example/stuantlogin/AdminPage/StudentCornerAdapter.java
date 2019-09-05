package com.example.stuantlogin.AdminPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stuantlogin.Dashboard.TransportModel;
import com.example.stuantlogin.R;

import java.util.ArrayList;

public class StudentCornerAdapter extends RecyclerView.Adapter<StudentCornerAdapter.StuCorViewHolder> {

    private Context context;
    private ArrayList<TransportModel> list;

    public StudentCornerAdapter() {
    }

    public StudentCornerAdapter(Context context, ArrayList<TransportModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public StuCorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_corner_layout, null, false);

        return new StuCorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StuCorViewHolder holder, int position) {

        holder.tvListName.setText("Name:"+list.get(position).getName());
        holder.tvListRoll.setText("RollNo:"+list.get(position).getRollno());
        holder.tvListEmail.setText("Standard:"+list.get(position).getStd());
        holder.tvListStd.setText("Email:"+list.get(position).getEmail());
        holder.tvListSub.setText("Subject:"+list.get(position).getsSub());
        holder.tvListMsg.setText("Messeage::"+list.get(position).getsMsg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class StuCorViewHolder extends RecyclerView.ViewHolder {
        TextView tvListName, tvListRoll, tvListEmail, tvListStd, tvListSub, tvListMsg ;
        public StuCorViewHolder(@NonNull View itemView) {
            super(itemView);

            tvListName = itemView.findViewById(R.id.tvStuCorName);
            tvListRoll = itemView.findViewById(R.id.tvStuCorRollNo);
            tvListEmail = itemView.findViewById(R.id.tvStuCorEmail);
            tvListStd = itemView.findViewById(R.id.tvStuCorStd);
            tvListSub = itemView.findViewById(R.id.tvStuCorSub);
            tvListMsg = itemView.findViewById(R.id.tvStuCorMsg);
        }
    }
}
