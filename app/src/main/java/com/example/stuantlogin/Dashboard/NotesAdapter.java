package com.example.stuantlogin.Dashboard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stuantlogin.Dashboard.RoomDatabase.Notes;
import com.example.stuantlogin.R;

import java.util.List;

class NotesAdapterb extends RecyclerView.Adapter<NotesAdapterb.NotesViewHolder> {

    private Context context;
    private List<Notes> mData;

    public NotesAdapterb(Context context, List<Notes> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notes_layout, null, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.title.setText(mData.get(position).getTitle());
        holder.subject.setText(mData.get(position).getNotes());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView title, subject;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvNotesHead);
            subject = itemView.findViewById(R.id.tvNotesSubject);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Notes notes = mData.get(getAdapterPosition());
                    Intent intent = new Intent(context, AddNotes.class);
                    intent.putExtra("DATA", notes);
                    context.startActivity(intent);
                }
            });
        }
    }
}
