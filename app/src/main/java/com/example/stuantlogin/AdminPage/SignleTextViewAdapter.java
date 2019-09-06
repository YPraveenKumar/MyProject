package com.example.stuantlogin.AdminPage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stuantlogin.Dashboard.ProgRegModel;
import com.example.stuantlogin.R;

import java.util.ArrayList;

public class SignleTextViewAdapter extends RecyclerView.Adapter<SignleTextViewAdapter.STViewHolder> {

    private Context context;
    private ArrayList<ProgRegModel> pgmList;

    public SignleTextViewAdapter(Context context, ArrayList<ProgRegModel> pgmList) {
        this.context = context;
        this.pgmList = pgmList;
    }

    @NonNull
    @Override
    public STViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_textview_layout, null, false);
        return new STViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull STViewHolder holder, int position) {
        holder.tvPgmDet.setText(pgmList.get(position).getpID()+"\n"+
                                pgmList.get(position).getpName()+"\n"+
                                pgmList.get(position).getpCode()+"\n"+
                                pgmList.get(position).getpDet()+"\n"+
                                pgmList.get(position).getpDate()+"\n"+
                                pgmList.get(position).getpOrg()+"\n"+
                                pgmList.get(position).getpType()+"\n"+
                                pgmList.get(position).getcID()+"\n"+
                                pgmList.get(position).getcName()+"\n"+
                                pgmList.get(position).getcDate()+"\n"+
                                pgmList.get(position).getcTime()+"\n"+
                                pgmList.get(position).getcFee()+"\n"+
                                pgmList.get(position).getsID()+"\n"+
                                pgmList.get(position).getsCode()+"\n"+
                                pgmList.get(position).getsName()+"\n"+
                                pgmList.get(position).getaName()+"\n"+
                                pgmList.get(position).getaNo()+"\n"+
                                pgmList.get(position).getaTeamName()+"\n"
        );

//
//        holder.tvPgmDet.setText(pgmList.get(position).getpName()+"\n");
//        holder.tvPgmDet.setText(pgmList.get(position).getpCode()+"\n");
//        holder.tvPgmDet.setText(pgmList.get(position).getpDet()+"\n");
//        holder.tvPgmDet.setText(pgmList.get(position).getpDate()+"\n");
//        holder.tvPgmDet.setText(pgmList.get(position).getpOrg()+"\n");
//        holder.tvPgmDet.setText(pgmList.get(position).getpType()+"\n");
//        holder.tvPgmDet.setText(pgmList.get(position).getcID()+"\n");
//        holder.tvPgmDet.setText(pgmList.get(position).getcName()+"\n");
//        holder.tvPgmDet.setText(pgmList.get(position).getcDate()+"\n");
//        holder.tvPgmDet.setText(pgmList.get(position).getcTime()+"\n");
//        holder.tvPgmDet.setText(pgmList.get(position).getcFee()+"\n");
//        holder.tvPgmDet.setText(pgmList.get(position).getsID()+"\n");
//        holder.tvPgmDet.setText(pgmList.get(position).getsCode()+"\n");
//        holder.tvPgmDet.setText(pgmList.get(position).getsName()+"\n");
//        holder.tvPgmDet.setText(pgmList.get(position).getaName()+"\n");
//        holder.tvPgmDet.setText(pgmList.get(position).getaNo()+"\n");
//        holder.tvPgmDet.setText(pgmList.get(position).getaTeamName()+"\n");
    }

    @Override
    public int getItemCount() {
        return pgmList.size();
    }

    public class STViewHolder extends RecyclerView.ViewHolder {

        TextView tvPgmDet;
        public STViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPgmDet = itemView.findViewById(R.id.tvPgmDet);
        }
    }
}
