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

public class BillingAdapter extends RecyclerView.Adapter<BillingAdapter.BillViewHolder> {

private ArrayList<BillingModel> billingList;
private Context context;

    public BillingAdapter(ArrayList<BillingModel> billingList, Context context) {
        this.billingList = billingList;
        this.context = context;
    }

    @NonNull
@Override
public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.billing_list, null, false);
        return new BillViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull BillViewHolder holder, int position) {

        holder.tvName.setText("\n"+"Name:"+billingList.get(position).getsName());
        holder.tvRollno.setText("\n"+"Roll NO:"+billingList.get(position).getsRollno());
        holder.tvClassName.setText("\n"+"Class Name:"+billingList.get(position).getsClassName());
        holder.tvTotalFee.setText("\n"+"Total Fee:"+billingList.get(position).getsTotalFee());
        holder.tvPaidFee.setText("\n"+"Paid Fee:"+billingList.get(position).getsPaidFee());
        holder.tvBalanceFee.setText("\n"+"Due Fee:"+billingList.get(position).getsBalanceFee());
        holder.tvDueDate.setText("\n"+"Due Date:"+billingList.get(position).getsDueDate());

        }

@Override
public int getItemCount() {
        return billingList.size();
        }

public class BillViewHolder extends RecyclerView.ViewHolder {

    TextView tvName, tvRollno, tvClassName, tvTotalFee, tvPaidFee, tvBalanceFee, tvDueDate;

    public BillViewHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tvBillingName);
        tvRollno = itemView.findViewById(R.id.tvBillingRoll);
        tvClassName = itemView.findViewById(R.id.tvBillingClass);
        tvTotalFee = itemView.findViewById(R.id.tvBillingTotalFee);
        tvPaidFee = itemView.findViewById(R.id.tvBillingPaid);
        tvBalanceFee = itemView.findViewById(R.id.tvBillingDue);
        tvDueDate = itemView.findViewById(R.id.tvBillingDate);

    }
}
}
