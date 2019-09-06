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
        holder.tvname.setText   (list.get(position).getsName());
        holder.tvrollno.setText (list.get(position).getsRollno());
        holder.tvGender.setText (list.get(position).getsGender());
        holder.tvDate.setText   (list.get(position).getsDate());
        holder.tvClass.setText  (list.get(position).getsClassName());
        holder.tvPhone.setText  (list.get(position).getsPhone());
        holder.tvEmail.setText  (list.get(position).getsEmail());
        holder.tvAddress.setText(list.get(position).getsAddress());
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = tvname.getText().toString();
                    String rollNo = tvrollno.getText().toString();
                    String gender = tvGender.getText().toString();
                    String className = tvClass.getText().toString();
                    String date = tvDate.getText().toString();
                    String hone = tvPhone.getText().toString();
                    String email = tvEmail.getText().toString();
                    String address = tvAddress.getText().toString();

                    Intent stuListIntent = new Intent(context, StudentReg.class);

                    stuListIntent.putExtra("Name", name);
                    stuListIntent.putExtra("RollNo", rollNo);
                    stuListIntent.putExtra("Gender", gender);
                    stuListIntent.putExtra("Class", className);
                    stuListIntent.putExtra("Date", date);
                    stuListIntent.putExtra("Phone", hone);
                    stuListIntent.putExtra("Email", email);
                    stuListIntent.putExtra("Address", address);

                    context.startActivity(stuListIntent);
                }
            });

        }
    }
}
