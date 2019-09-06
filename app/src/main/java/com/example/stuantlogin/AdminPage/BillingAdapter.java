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

public class BillingAdapter extends RecyclerView.Adapter<BillingAdapter.BillViewHolder> {

private ArrayList<BillingModel> billingList;
private Context context;

    BillingAdapter(ArrayList<BillingModel> billingList, Context context) {
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

        holder.tvName.setText(billingList.get(position).getsName());
        holder.tvRollno.setText(billingList.get(position).getsRollno());
        holder.tvClassName.setText(billingList.get(position).getsClassName());
        holder.tvTotalFee.setText(billingList.get(position).getsTotalFee());
        holder.tvPaidFee.setText(billingList.get(position).getsPaidFee());
        holder.tvBalanceFee.setText(billingList.get(position).getsBalanceFee());
        holder.tvDueDate.setText(billingList.get(position).getsDueDate());

        }

@Override
public int getItemCount() {
        return billingList.size();
        }

class BillViewHolder extends RecyclerView.ViewHolder {

    TextView tvName, tvRollno, tvClassName, tvTotalFee, tvPaidFee, tvBalanceFee, tvDueDate;

    BillViewHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tvBillingName);
        tvRollno = itemView.findViewById(R.id.tvBillingRoll);
        tvClassName = itemView.findViewById(R.id.tvBillingClass);
        tvTotalFee = itemView.findViewById(R.id.tvBillingTotalFee);
        tvPaidFee = itemView.findViewById(R.id.tvBillingPaid);
        tvBalanceFee = itemView.findViewById(R.id.tvBillingDue);
        tvDueDate = itemView.findViewById(R.id.tvBillingDate);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sName = tvName.getText().toString();
                String sRollNo = tvRollno.getText().toString();
                String sClassNAme = tvClassName.getText().toString();
                String sTotalFee = tvTotalFee.getText().toString();
                String sPaidFee = tvPaidFee.getText().toString();
                String sBalanceFee = tvBalanceFee.getText().toString();
                String sDUeData = tvDueDate.getText().toString();

                Intent billIntent = new Intent(context, BillingDeatils.class);
                billIntent.putExtra("Name", sName);
                billIntent.putExtra("RollNo", sRollNo);
                billIntent.putExtra("Class", sClassNAme);
                billIntent.putExtra("Total", sTotalFee);
                billIntent.putExtra("Paid", sPaidFee);
                billIntent.putExtra("Balance", sBalanceFee);
                billIntent.putExtra("Date", sDUeData);

                context.startActivity(billIntent);

            }
        });

    }
}
}
