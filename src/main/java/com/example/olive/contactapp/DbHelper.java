package com.example.olive.contactapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context) {
        super(context,"contacts",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table contacts(name text,number text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insert(Contact contact){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",contact.getName());
        values.put("number",contact.getNumber());
        db.insert("contacts",null,values);
    }
    public List<Contact> getAllData(){
        List<Contact> list=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from contacts",null);
        c.moveToFirst();
        int nameIndex=c.getColumnIndex("name");
        int numberIndex=c.getColumnIndex("number");
        while(!c.isAfterLast()){
            String name=c.getString(nameIndex);
            String number=c.getString(numberIndex);
            Contact contact=new Contact(name,number);
            list.add(contact);
            c.moveToNext();
        }
        return list;
    }
    public void delete(String name){
        SQLiteDatabase db=getWritableDatabase();
        db.delete("contacts","name=?",new String[]{name});
    }
}

