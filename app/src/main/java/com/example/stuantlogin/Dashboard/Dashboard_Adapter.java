package com.example.stuantlogin.Dashboard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stuantlogin.R;
import com.example.stuantlogin.TimeTableFragments.TimeTable;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Dashboard_Adapter extends RecyclerView.Adapter<Dashboard_Adapter.DashBoard_ViewHolder> {

    private Context context;
    private DashBoardHome activity = (DashBoardHome) context;
    private ArrayList<String> nameList;
    private ArrayList<Integer> imageList;
    Intent intent;

    public Dashboard_Adapter(Context context, ArrayList<String> nameList, ArrayList<Integer> imageList) {
        this.context = context;
        this.nameList = nameList;
        this.imageList = imageList;
    }

//    private String uid = Objects.requireNonNull(co).getIntent().getStringExtra("UID");

    @NonNull
    @Override
    public DashBoard_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.dash_layout, null, false);

        return new DashBoard_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashBoard_ViewHolder dashBoard_viewHolder, int i) {
        dashBoard_viewHolder.icon.setImageResource(imageList.get(i));
        dashBoard_viewHolder.name.setText(nameList.get(i));
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public class DashBoard_ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView icon;
        TextView name;
        public DashBoard_ViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.cvIcon);
            name = itemView.findViewById(R.id.tvTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getAdapterPosition();
                    switch (getAdapterPosition()){
                        case 0:
                            intent = new Intent(context, ProgramReg.class)/*.putExtra("UID", uid)*/;
                            break;

                        case 1:
                            intent = new Intent(context, StudentRegistration.class)/*.putExtra("UID", uid)*/;
                            break;

                        case 2:
                            intent = new Intent(context, StudentDetails.class)/*.putExtra("UID", uid)*/;
                            break;

                        case 3:
                            intent = new Intent(context, Billing.class)/*.putExtra("UID", uid)*/;
                            break;

                        case 4:
                            intent = new Intent(context, TimeTable.class)/*.putExtra("UID", uid)*/;
                            break;

                        case 5:
                            intent = new Intent(context, Assessment.class)/*.putExtra("UID", uid)*/;
                            break;

                        case 6:
                            intent = new Intent(context, Announcements.class)/*.putExtra("UID", uid)*/;
                            break;

                        case 7:
                            intent = new Intent(context, Transport.class)/*.putExtra("UID", uid)*/;
                            break;


                    }
                    context.startActivity(intent);
                }
            });

        }

    }
}
