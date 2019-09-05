package com.example.stuantlogin.Dashboard.RoomDatabase;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Notes.class, version = 1, exportSchema = false)
public abstract class AppDatabse extends RoomDatabase {

    private static AppDatabse INSTANCE;

    public static AppDatabse getDatabase (final Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabse.class, "notes.db")
//                    .allowMainThreadQueries() //SHOULD NOT USE IN PRODUCTION
                    .build();
        }
        return INSTANCE;
    }
    public abstract NotesDAO getNotesDAO();

}
