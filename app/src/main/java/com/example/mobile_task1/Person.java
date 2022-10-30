package com.example.mobile_task1;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {

    private int ID;
    private String fname;
    private String lname;

    public Person(int ID, String fname, String lname) {
        this.ID = ID;
        this.fname = fname;
        this.lname = lname;

    }


    protected Person(Parcel in) {
        ID = in.readInt();
        fname = in.readString();
        lname = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ID);
        parcel.writeString(fname);
        parcel.writeString(lname);
    }

    public int getID() {
        return ID;
    }
    public String getFname(){
        return fname;
    }

    public String getLname()
    {
        return lname;
    }
}
