package com.example.stuantlogin.Dashboard;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stuantlogin.Dashboard.RoomDatabase.AppDatabse;
import com.example.stuantlogin.Dashboard.RoomDatabase.Notes;
import com.example.stuantlogin.R;

public class AddNotes extends AppCompatActivity {
    private EditText etTitle, etSub;
    private Button btnAddUpdate, btnDelete;
    private Notes mNotes;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        etTitle = findViewById(R.id.etAddNotesTitle);
        etSub = findViewById(R.id.etNotesAddSubject);
        btnAddUpdate = findViewById(R.id.btnAddNotesAdd);
        btnDelete = findViewById(R.id.btnAddNotesDelete);

        btnAddUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sTitle = etTitle.getText().toString().trim();
                String sSub = etSub.getText().toString().trim();

                Notes notes = new Notes();
                notes.setTitle(sTitle);
                notes.setNotes(sSub);

                UpdateTask updateTask = new UpdateTask(notes);
                updateTask.execute();
            }
        });

        if(getIntent() != null && getIntent().getExtras() != null){
            mNotes = getIntent().getParcelableExtra("DATA");
            btnAddUpdate.setText("UPDATE");
            btnDelete.setVisibility(View.VISIBLE);
            etTitle.setText(mNotes.getTitle());
            etSub.setText(mNotes.getNotes());
        }

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateTask updateTask = new UpdateTask(null);
                updateTask.execute();
            }
        });
    }

    private class UpdateTask extends AsyncTask<String, Void, String> {

        private Notes notes;

        public UpdateTask(Notes notes) {
            this.notes = notes;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String message;
            if(this.notes == null){
                AppDatabse.getDatabase(AddNotes.this).getNotesDAO().delete(mNotes);
                message = "Note Deleted";
            }else if(mNotes !=null ){
                mNotes.setId((mNotes.getId()));
                AppDatabse.getDatabase(AddNotes.this).getNotesDAO().update(notes);
                message = "Updated SuccessFully!";
            }else {
                AppDatabse.getDatabase(AddNotes.this).getNotesDAO().insert(notes);
                message = "Note Added";
            }
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(AddNotes.this, s, Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
