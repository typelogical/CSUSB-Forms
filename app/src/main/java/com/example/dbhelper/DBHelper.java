package com.example.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "STUDENT.db"; // STUDENT DATABASE CREATED FOR COMPLETE APPLICATION.
    public static final String STUDENT_TABLE = "student_table"; // STUDENT TABLE CREATED FOR 1ST PART OF THIS ASSIGNMENT
    public static final String FORMS_TABLE = "forms_table"; // FORM TABLE CREATED FOR 2ND PART OF THIS ASSIGNMENT

    /**
     * FIELDS OF THE STUDENT TABLE // COLUMN FIELDS
     */
    //public static final String STUDENT_COLUMN_ID = "id";
    public static final String STUDENT_COLUMN_SID = "sid";
    public static final String STUDENT_COLUMN_PHONE = "phone";
    public static final String STUDENT_COLUMN_EMAIL = "email";
    public static final String STUDENT_COLUMN_NAME = "name";

    /**
     * FIELDS OF THE FORM TABLE // COLUMN FIELDS
     */
    //public static final String FORMS_COLUMN_ID = "id";
    public static final String FORMS_COLUMN_NAME = "name";
    public static final String FORMS_COLUMN_SID = "sid";
    public static final String FORMS_COLUMN_FORM_PHONE = "phone";
    public static final String FORMS_COLUMN_FORM_QUARTER = "quarter";
    public static final String FORMS_COLUMN_FORM_YEAR = "year";
    public static final String FORMS_COLUMN_FORM_MAJOR = "major";

    /**
     * @param context
     */
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        //CREATE TABLE QUERY FOR STUDENT TABLE
        db.execSQL(  // execute NAME, SID # , Phone # , Email
                "create table " + STUDENT_TABLE +
                        "(id integer primary key, name varchar,sid varchar,phone varchar, email varchar)"
        );
        //CREATE TABLE QUERY FOR FORM TABLE
        db.execSQL( // execute NAME , SID # , Phone # , Quarter , Year , Major
                "create table " + FORMS_TABLE +
                        "(id integer primary key, name varchar,sid varchar,phone varchar, quarter varchar, year varchar, major varchar)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //calls onUpgrade when the data has been upgraded

        //DROP TABLE QUERY FOR THE TABLES CREATE WHEN DATABSE UPGRADATION OCCUR.
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + FORMS_TABLE);
        onCreate(db);
    }

    /**
     * @param name
     * @param sid
     * @param phone
     * @param email
     * @return
     */
    //TABLE FOR STUDENT INFORMATION
    public boolean insertRecord(String name, String sid, String phone, String email) {
        SQLiteDatabase db = this.getWritableDatabase();     // allows writing and reading from database
        ContentValues contentValues = new ContentValues();  //new object for creating new empty set of values.

        contentValues.put(STUDENT_COLUMN_NAME, name);       // NAME
        contentValues.put(STUDENT_COLUMN_SID, sid);        //SID #
        contentValues.put(STUDENT_COLUMN_PHONE, phone);        //Phone #
        contentValues.put(STUDENT_COLUMN_EMAIL, email);     //EMAIL

        db.insert(STUDENT_TABLE, null, contentValues);      //INSERT INTO STUDENT_TABLE
        return true;
    }

    /**
     * @param name
     * @param sid
     * @param phone
     * @param quarter
     * @param year
     * @param major
     * @return
     */

    //TABLE FOR CLASS SECTION CHANGE
    public boolean insertRecordFormTable(String name, String sid, String phone, String quarter, String year, String major) {
        SQLiteDatabase db = this.getWritableDatabase();                  //allows writing and reading from database  (creating db)
        ContentValues contentValues = new ContentValues();               //new object for creating new empty set of variables

        contentValues.put(FORMS_COLUMN_NAME, name);                      //NAME
        contentValues.put(FORMS_COLUMN_SID, sid);                       //SID #
        contentValues.put(FORMS_COLUMN_FORM_PHONE, phone);                 //Phone #
        contentValues.put(FORMS_COLUMN_FORM_QUARTER, quarter);           //EMAIL
        contentValues.put(FORMS_COLUMN_FORM_YEAR, year);                 //YEAR
        contentValues.put(FORMS_COLUMN_FORM_MAJOR, major);               //MAJOR


        db.insert(FORMS_TABLE, null, contentValues);                    //INSERT INTO FORMS_TABLE
        return true;
    }

    public Cursor getData() {                                                //CURSOR FOR STUDENT_TABLE (STUDENT INFORMATION APPLICATION)
        SQLiteDatabase db = this.getReadableDatabase();                    //READ-ONLY FROM DATABASE  (db)
        Cursor res = db.rawQuery("select * from " + STUDENT_TABLE, null);  //Runs the SQL (STUDENT_TABLE)
        return res;                                                         //returns the cursor (result set)
    }

    public Cursor getDataFormTable() {                                       //CURSOR FOR FORMS_TABLE (CLASS SECTION CHANGE APPLICATION)
        SQLiteDatabase db = this.getReadableDatabase();                     //READ-ONLY FROM DATABASE (db)
        Cursor res = db.rawQuery("select * from " + FORMS_TABLE, null);    //Runs the SQL (FORMS_TABLE)
        return res;                                                         //returns the cursor (result set)
    }

}

    /*

    IGNORE THE FOLLOWING FUNCTIONS. ONLY TO TRY STUFF

    ---------------------------------
    ---------------------------------

	public int numberOfRows(){
		SQLiteDatabase db = this.getReadableDatabase();
		int numRows = (int) DatabaseUtils.queryNumEntries(db, STUDENT_TABLE);
		return numRows;
	}

	public Integer deleteRecord(String id){
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete(STUDENT_TABLE,
				"id = ? ",
				new String[] { id });
	}

}

    ---------------------------------
    ---------------------------------

*/
