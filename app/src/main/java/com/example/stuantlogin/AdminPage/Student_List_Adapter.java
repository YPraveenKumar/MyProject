package com.example.stuantlogin.AdminPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stuantlogin.R;

import java.util.ArrayList;

public class Student_List_Adapter extends RecyclerView.Adapter<Student_List_Adapter.Stu_ViewHolder> {

    private Context context;
    private ArrayList<StudentListModel> list;

    public Student_List_Adapter(Context context, ArrayList<StudentListModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Stu_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_list_layout, null, false);
        return new Stu_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Stu_ViewHolder holder, int position) {
        holder.tvname.setText   ("Name:             "+list.get(position).getsName());
        holder.tvrollno.setText ("\n"+"Roll No:          "+list.get(position).getsRollno());
        holder.tvGender.setText ("\n"+"Gender:           "+list.get(position).getsGender());
        holder.tvDate.setText   ("\n"+"Date of Joining:  "+list.get(position).getsDate());
        holder.tvClass.setText  ("\n"+"Class Name:       "+list.get(position).getsClassName());
        holder.tvPhone.setText  ("\n"+"Phone Number:     "+list.get(position).getsPhone());
        holder.tvEmail.setText  ("\n"+"Email Address:    "+list.get(position).getsEmail());
        holder.tvAddress.setText("\n"+"Address:          "+list.get(position).getsAddress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Stu_ViewHolder extends RecyclerView.ViewHolder {
        TextView tvname, tvrollno, tvGender, tvClass, tvDate, tvPhone, tvEmail, tvAddress;
        public Stu_ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvname = itemView.findViewById(R.id.tvStuListName);
            tvrollno = itemView.findViewById(R.id.tvStuListRollno);
            tvGender = itemView.findViewById(R.id.tvStuListGender);
            tvClass = itemView.findViewById(R.id.tvStuListClassName);
            tvDate = itemView.findViewById(R.id.tvStuListDate);
            tvPhone = itemView.findViewById(R.id.tvStuListPhone);
            tvEmail = itemView.findViewById(R.id.tvStuListEmail);
            tvAddress = itemView.findViewById(R.id.tvStuListAddress);

        }
    }
}
