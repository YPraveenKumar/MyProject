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

        holder.tvAssName.setText("\n"+"Name:"+assessmentList.get(position).getsName());
        holder.tvAssRoll.setText("\n"+"Roll NO:"+assessmentList.get(position).getsRollNo());
        holder.tvAssTerm.setText("\n"+"Term:"+assessmentList.get(position).getsTerm());
        holder.tvASsSub1.setText("\n"+"Subject 1:"+assessmentList.get(position).getsSub1());
        holder.tvASsSub2.setText("\n"+"Subject 2:"+assessmentList.get(position).getsSub2());
        holder.tvASsSub3.setText("\n"+"Subject 3:"+assessmentList.get(position).getsSub3());
        holder.tvAssTotal.setText("\n"+"TOTAL:"+assessmentList.get(position).getsTotal());
        holder.tvAssPEr.setText("\n"+"PERCENTAGE:"+assessmentList.get(position).getsPercentage());
        holder.tvAssGra.setText("\n"+"GRADE:"+assessmentList.get(position).getsGrade());
        holder.tvAssRec.setText("\n"+"Recommendation:"+assessmentList.get(position).getsRecommendation());
        holder.tvASsAtten.setText("\n"+"Attendance:"+assessmentList.get(position).getsAttendance());
        holder.tvAssAct.setText("\n"+"Perfomance:"+assessmentList.get(position).getsStuActivity());
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
            tvASsSub2 = itemView.findViewById(R.id.tvAssessmentSub2);
            tvASsSub3 = itemView.findViewById(R.id.tvAssessmentSub3);
            tvAssTotal = itemView.findViewById(R.id.tvAssessmentTotal);
            tvAssPEr = itemView.findViewById(R.id.tvAssessmentPer);
            tvAssGra = itemView.findViewById(R.id.tvAssessmentGra);
            tvAssRec = itemView.findViewById(R.id.tvAssessmentRec);
            tvASsAtten = itemView.findViewById(R.id.tvAssessmentAtten);
            tvAssAct = itemView.findViewById(R.id.tvAssessmentActiv);
        }
    }
}
