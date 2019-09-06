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

import de.hdodenhof.circleimageview.CircleImageView;

public class Admin_Adapter extends RecyclerView.Adapter<Admin_Adapter.AdminViewHolder> {

    private Context context;
    private ArrayList<String> nameList;
    private ArrayList<Integer> imageList;

    public Admin_Adapter(Context context, ArrayList<String> nameList, ArrayList<Integer> imageList) {
        this.context = context;
        this.nameList = nameList;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public AdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.admin_layout, null, false);
        return new AdminViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminViewHolder holder, int position) {
        holder.tvName.setText(nameList.get(position));
        holder.cvICon.setImageResource(imageList.get(position));
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public class AdminViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        CircleImageView cvICon;
        public AdminViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            cvICon = itemView.findViewById(R.id.cvIcon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getAdapterPosition();
                    switch (getAdapterPosition()){
                        case 0:
                            context.startActivity(new Intent(context, StudentReg.class));
                            break;

                        case 1:
                            context.startActivity(new Intent(context, AssessmentAdmin.class));
                            break;

                        case 2:
                            context.startActivity(new Intent(context, AssessmentList.class));
                            break;

                        case 3:
                            context.startActivity(new Intent(context, StudentList.class));
                            break;

                        case 4:
                            context.startActivity(new Intent(context, BillingDeatils.class));
                            break;

                        case 5:
                            context.startActivity(new Intent(context, BillHistory.class));
                            break;

                        case 6:
                            context.startActivity(new Intent(context, AdminAnnouncement.class));
                            break;

                        case 7:
                            context.startActivity(new Intent(context, TimeTableAdmin.class));
                            break;

                        case 8:
                            context.startActivity(new Intent(context, StuCornerAdmin.class));
                            break;

                        case 9:
                            context.startActivity(new Intent(context, ProgramDetails.class));
                            break;
                    }
                }
            });
        }
    }
}
