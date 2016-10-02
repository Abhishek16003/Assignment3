package com.code.abhishek.assignment3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ABHISHEK GUPTA on 9/28/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String TYPE = " TEXT";
    private static final String COMMA_SEPARATOR = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Contract.dbentry.TABLE_NAME+ " (" +
                    Contract.dbentry.DATA_ROLL + " TEXT," +
                    Contract.dbentry.DATA_NAME+ TYPE + COMMA_SEPARATOR +
                    Contract.dbentry.DATA_PHONE+ TYPE + COMMA_SEPARATOR+
                    Contract.dbentry.DATA_SEM+ TYPE + " )";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + Contract.dbentry.TABLE_NAME;
   // private static final String SQL_SELECT= "SELECT * FROM " + Contract.dbentry.TABLE_NAME+ "where "+ Contract.dbentry.DATA_ROLL
    public DBHelper(Context context) {
        super(context, Contract.dbentry.DATABASE_NAME, null, 1);
         SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public boolean insert(String rollno,String name,String phone,String sem){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Contract.dbentry.DATA_ROLL,rollno);
        values.put(Contract.dbentry.DATA_NAME,name);
        values.put(Contract.dbentry.DATA_PHONE,phone);
        values.put(Contract.dbentry.DATA_SEM,sem);
        long r=db.insert(Contract.dbentry.TABLE_NAME,null,values);
        if(r==-1)
            return false;
        else
            return true;
    }
    public Integer delete(String rollno){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(Contract.dbentry.TABLE_NAME,"rollno=?",new String[]{rollno});
    }
    public boolean update(String rollno,String name,String phone,String sem){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Contract.dbentry.DATA_ROLL,rollno);
        values.put(Contract.dbentry.DATA_NAME,name);
        values.put(Contract.dbentry.DATA_PHONE,phone);
        values.put(Contract.dbentry.DATA_SEM,sem);
        db.update(Contract.dbentry.TABLE_NAME,values,"rollno=?",new String[]{rollno});
        return true;
    }
    public Cursor disp(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor result=db.rawQuery("select * from "+Contract.dbentry.TABLE_NAME,null);
        return result;
    }
    public Cursor disp1(String s){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor result=db.rawQuery("select * from "+Contract.dbentry.TABLE_NAME+" where rollno=?",new String[]{s});
        return result;
    }
    public Cursor disp2(String rollno,String name,String phone,String sem){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor result=db.rawQuery("select * from "+Contract.dbentry.TABLE_NAME+" where rollno=? and name=? and sem=? and phone=?",new String[]{rollno,name,sem,phone});
        return result;
    }
}
