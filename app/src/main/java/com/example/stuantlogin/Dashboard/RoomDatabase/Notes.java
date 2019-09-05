package com.example.stuantlogin.Dashboard.RoomDatabase;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Notes implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String title;
    private String notes;

    public Notes() {
    }

    public Notes(Parcel in) {

        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        title = in.readString();
        notes = in.readString();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if(id == null){
            dest.writeByte((byte) 0);
        }
        else {
            dest.writeByte((byte) 1 );
            dest.writeInt(id);
        }
        dest.writeString(title);
        dest.writeString(notes);
    }
}
