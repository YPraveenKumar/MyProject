package com.example.stuantlogin.Dashboard.RoomDatabase;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDAO {

    @Insert
    void insert (Notes notes);

    @Update
    void update(Notes notes);

    @Delete
    void delete (Notes notes);

    @Query("Select * from notes")
    List<Notes> getAllNotes();
}
