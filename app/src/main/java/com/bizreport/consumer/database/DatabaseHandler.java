package com.bizreport.consumer.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bizReportCons";
    private static final String TABLE_NAME = "companies";
    private static final int DATABASE_VERSION = 1;
    private Context ctx;
    private static DatabaseHandler instance;

    public static final String COL_ID = "_id";
    public static final String COL_NAME = "name";
    public static final String COL_RISK = "risk_factors";
    public static final String COL_EXPENSES = "expenses";
    public static final String COL_REV = "revenue";
    public static final String COL_OFFICERS = "officers";
    private DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx = context;
    }

    public static DatabaseHandler getInstance(Context context){
        if(instance == null){
            instance = new DatabaseHandler(context);
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + TABLE_NAME + "("
                + COL_ID + " INTEGER PRIMARY KEY NOT NULL, "
                + COL_NAME + " TEXT, "
                + COL_RISK + " TEXT, "
                + COL_EXPENSES + " TEXT, "
                + COL_REV + " TEXT, "
                + COL_OFFICERS + " TEXT"
                + ")";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addCompanies(Company company){
        ContentValues values = new ContentValues();
        values.put(COL_NAME, company.getName());
        values.put(COL_RISK, company.getRiskFactors());
        values.put(COL_EXPENSES, company.getExpenses());
        values.put(COL_REV, company.getIncome());
        values.put(COL_OFFICERS, company.getExecutiveOfficers());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
        Log.d("Logging data", "data added");
    }

    public int dbSize(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        int count = 0;
        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                ++count;
            }
        }
        return count;
    }

    public ArrayList<Company> getAllCompanies(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Company> companies = new ArrayList<>();
        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                Company company = new Company();
                company.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
                company.setRiskFactors(cursor.getString(cursor.getColumnIndex(COL_RISK)));
                company.setExpenses(cursor.getString(cursor.getColumnIndex(COL_EXPENSES)));
                company.setIncome(cursor.getString(cursor.getColumnIndex(COL_REV)));
                company.setExecutiveOfficers(cursor.getString(cursor.getColumnIndex(COL_OFFICERS)));
                companies.add(company);
            }
        }
        return companies;
    }

    public Company getCompanyByName(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_NAME + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{name});
        Company company = new Company();

        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                Log.d("name","name="+cursor.getString(cursor.getColumnIndex(COL_NAME)));
                company.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
                company.setRiskFactors(cursor.getString(cursor.getColumnIndex(COL_RISK)));
                company.setExpenses(cursor.getString(cursor.getColumnIndex(COL_EXPENSES)));
                company.setIncome(cursor.getString(cursor.getColumnIndex(COL_REV)));
                company.setExecutiveOfficers(cursor.getString(cursor.getColumnIndex(COL_OFFICERS)));
            }
        }
        db.close();
        return company;

    }

    public void clearTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
    }
}
