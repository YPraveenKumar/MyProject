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

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssViewHolder> {

   private ArrayList<AssessmentModel> assessmentList;
   private Context context;

    public AssessmentAdapter(ArrayList<AssessmentModel> assessmentList, Context context) {
        this.assessmentList = assessmentList;
        this.context = context;
    }

    @NonNull
    @Override
    public AssViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.assessment_list, null, false);
        return new AssViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssViewHolder holder, int position) {

        holder.tvAssName.setText(assessmentList.get(position).getsName());
        holder.tvAssRoll.setText(assessmentList.get(position).getsRollNo());
        holder.tvAssTerm.setText(assessmentList.get(position).getsTerm());
        holder.tvASsSub1.setText(assessmentList.get(position).getsSub1());
        holder.tvASsSub2.setText(assessmentList.get(position).getsSub2());
        holder.tvASsSub3.setText(assessmentList.get(position).getsSub3());
        holder.tvAssTotal.setText(assessmentList.get(position).getsTotal());
        holder.tvAssPEr.setText(assessmentList.get(position).getsPercentage());
        holder.tvAssGra.setText(assessmentList.get(position).getsGrade());
        holder.tvAssRec.setText(assessmentList.get(position).getsRecommendation());
        holder.tvASsAtten.setText(assessmentList.get(position).getsAttendance());
        holder.tvAssAct.setText(assessmentList.get(position).getsStuActivity());
    }

    @Override
    public int getItemCount() {
        return assessmentList.size();
    }

    public class AssViewHolder extends RecyclerView.ViewHolder {

        TextView tvAssName, tvAssRoll, tvAssTerm, tvASsSub1, tvASsSub2, tvASsSub3, tvAssTotal, tvAssPEr, tvAssGra, tvAssRec, tvASsAtten, tvAssAct;

        public AssViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAssName = itemView.findViewById(R.id.tvAssessmentName);
            tvAssRoll = itemView.findViewById(R.id.tvAssessmentRoll);
            tvAssTerm = itemView.findViewById(R.id.tvAssessmentTerm);
            tvASsSub1 = itemView.findViewById(R.id.tvAssessmentSub1);
            tvAssGra = itemView.findViewById(R.id.tvAssessmentGra);
            tvASsSub2 = itemView.findViewById(R.id.tvAssessmentSub2);
            tvASsSub3 = itemView.findViewById(R.id.tvAssessmentSub3);
            tvAssTotal = itemView.findViewById(R.id.tvAssessmentTotal);
            tvAssPEr = itemView.findViewById(R.id.tvAssessmentPer);
            tvAssRec = itemView.findViewById(R.id.tvAssessmentRec);
            tvASsAtten = itemView.findViewById(R.id.tvAssessmentAtten);
            tvAssAct = itemView.findViewById(R.id.tvAssessmentActiv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = tvAssName.getText().toString();
                    String rollNo = tvAssRoll.getText().toString();
                    String sub1 = tvASsSub1.getText().toString();
                    String sub2 = tvASsSub2.getText().toString();
                    String sub3 = tvASsSub3.getText().toString();
                    String total = tvAssTotal.getText().toString();
                    String percentage = tvAssPEr.getText().toString();
                    String recommendation = tvAssRec.getText().toString();
                    String attendance = tvASsAtten.getText().toString();
                    String activity = tvAssAct.getText().toString();

                    Intent assementIntent = new Intent(context, AssessmentAdmin.class);
                    assementIntent.putExtra("Name", name);
                    assementIntent.putExtra("RollNo", rollNo);
                    assementIntent.putExtra("Sub1", sub1);
                    assementIntent.putExtra("Sub2", sub2);
                    assementIntent.putExtra("Sub3", sub3);
                    assementIntent.putExtra("Total", total);
                    assementIntent.putExtra("Per", percentage);
                    assementIntent.putExtra("Rec", recommendation);
                    assementIntent.putExtra("Atten", attendance);
                    assementIntent.putExtra("Act", activity);

                    context.startActivity(assementIntent);
                }
            });
        }
    }
}
