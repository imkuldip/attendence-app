package com.example.kuldip.attendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

/**
 * Created by Kuldip on 1/6/2018.
 */

public  class DatabaseHelper extends SQLiteOpenHelper{

    static  String name="Database";
    static  int version =1;

    String createusertable = "CREATE TABLE if not exists `User` (\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`username`\tTEXT,\n" +
            "\t`passcode`\tTEXT,\n" +
            "\t`usertype`\tTEXT,\n" +
            ")";
    String insertUser1= " INSERT INTO User (username,usertype,passcode)\n" +
            "VALUES ( 'Kuldip Bhochhibhoya', 'Student', ' 1234' );";
    String insertUser2= " INSERT INTO User (username,usertype,passcode)\n" +
            "VALUES ( 'Harendra Subedi', 'Teacher', ' 12345' );";
    String insertUser3= " INSERT INTO User (username,usertype,passcode)\n" +
            "VALUES ( 'Bana Mam', 'Adnim', ' 123456' );";


    public DatabaseHelper(Context context) {
        super(context, name, null, version);
        //getWritableDatabase().execSQL(createusertable);
//        getWritableDatabase().execSQL(insertUser1);
//        getWritableDatabase().execSQL(insertUser2);
//        getWritableDatabase().execSQL(insertUs der3);
    }

    public void insertUser(ContentValues cv){
        getWritableDatabase().insert("User","",cv);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createusertable);
        sqLiteDatabase.execSQL(insertUser1);
        sqLiteDatabase.execSQL(insertUser2);
        sqLiteDatabase.execSQL(insertUser3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean isLoginValid(String username, String  passcode){
        String sql = "select count(*) from User where username='"+username+"' and passcode='"+passcode+"'";
        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);
        long l = statement.simpleQueryForLong();
        if(l==1){
            return true;
        }
        else{
            return false;
        }
    }
  /*  String username;
    public  String getUserList(){
        String sql = "select username from User where id=?";
        Cursor c=getReadableDatabase().rawQuery(sql,new String[]{"1"});

       // ArrayList<UserInfo> list = new ArrayList<UserInfo>();
        while(c.moveToNext()){
            username  =c.getString(c.getColumnIndex("username"));
        }
        c.close();
        return username;
    }*/


}
