package com.example.stuantlogin.ProgramRegistration;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stuantlogin.R;

import java.util.ArrayList;

public class ProgramRegistrationAdapter extends RecyclerView.Adapter<ProgramRegistrationAdapter.ProgRegHolder> {

    private Context context;
    private ArrayList<String> nameList;
    private ArrayList<Integer> iconList;

    public ProgramRegistrationAdapter(Context context, ArrayList<String> nameList, ArrayList<Integer> iconList) {
        this.context = context;
        this.nameList = nameList;
        this.iconList = iconList;
    }

    @NonNull
    @Override
    public ProgRegHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.program_registration_layout, null, false);
        return new ProgRegHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgRegHolder progRegHolder, int i) {
        progRegHolder.icon.setImageResource(iconList.get(i));
        progRegHolder.name.setText(nameList.get(i));
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    class ProgRegHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView name;
        ProgRegHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.cvIcon);
            name = itemView.findViewById(R.id.tvName);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    getAdapterPosition();

                    switch (getAdapterPosition()){
                        case 0:
                            context.startActivity(new Intent(context, ProgramMaster.class));
                            break;

                        case 1:
                            context.startActivity(new Intent(context, ClassMaster.class));
                            break;

                        case 2:
                            context.startActivity(new Intent(context, SubjectMaster.class));
                            break;

                        case 3:
                            context.startActivity(new Intent(context, ClassSubject.class));
                            break;

                        case 4:
                            context.startActivity(new Intent(context, Preview.class));
                            break;

                        case 5:
                            context.startActivity(new Intent(context, Feedback.class));
                            break;
                    }

                }
            });
        }
    }
}
