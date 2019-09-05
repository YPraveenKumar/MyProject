package com.example.stuantlogin.Dashboard;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stuantlogin.Dashboard.RoomDatabase.AppDatabse;
import com.example.stuantlogin.Dashboard.RoomDatabase.Notes;
import com.example.stuantlogin.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class StudentRegistration extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton actionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        recyclerView = findViewById(R.id.rvNotes);
        actionButton = findViewById(R.id.fbNotesActionButton);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentRegistration.this, AddNotes.class));
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        GetTask task = new GetTask();
        task.execute();
    }

    private class GetTask extends AsyncTask<String, Void, List<Notes>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Notes> doInBackground(String... strings) {
            List<Notes> notes = AppDatabse.getDatabase(StudentRegistration.this).getNotesDAO().getAllNotes();
            return notes;
        }

        @Override
        protected void onPostExecute(List<Notes> notes) {
            super.onPostExecute(notes);
            NotesAdapterb adapterb = new NotesAdapterb(StudentRegistration.this, notes);
            recyclerView.setAdapter(adapterb);
            adapterb.notifyDataSetChanged();
        }
    }
}
