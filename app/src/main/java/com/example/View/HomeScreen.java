package com.example.dbtask2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.dbhelper.DBHelper;
import com.example.dbtask.R;

/**
 * FROM THIS SCREEN USER WILL BE DIRECTED TO EITHER "VIEW" OR "SUBMIT" RECORDS TO THE DATABASE
 * 
 */
public class HomeScreen extends Activity {

	private Button mViewButton=null;            //set view button to null
	private Button mSubmitButton=null;          //set submit button to null
	private DBHelper mDBhelper;                 //set DBHelper to mDBhelper


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homescreen);	

		//CREATING DATABASE OBJECT
		mDBhelper = new DBHelper(this);  //new database object

		/**
		 * TO SEE ALL THE FORM RECORDS IN THE TABLE form_table
		 */
		mViewButton = (Button)findViewById(R.id.view_button);    // Set mViewButton to View Submitted Forms button
		mViewButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				/**
				 * FETCHING RECORD FROM DATABASE TABLE[ form_table]
				 */
				Cursor cursor = mDBhelper.getDataFormTable(); // first cursor get data from FORM_TABLE

				Cursor cursor2 = mDBhelper.getData();  // second cursor get data from STUDENT_TABLE

				// IF WE HAVE NO RECORDS IN THE TABLE form_table THEN SHOW TOAST TO USER TO ENTER FORMS FIRST.
				int len = cursor.getCount();         //returns the number of rows in the cursor
				int len2 = cursor2.getCount();       //returns the number of rows in the cursor
				if(len>0||len2>0){                   // if either are not empty then
					Intent intent = new Intent(HomeScreen.this, ListOfFormsScreen.class); // go to new activity ListOfFormsScreen
					intent.putExtra("flag", false);                //transferring the flag data as false
					startActivity(intent);                         // start the activity
				}else{  // if both tables were empty then
					Toast.makeText(HomeScreen.this, "No records available.", Toast.LENGTH_SHORT).show(); // show message for short period of time
				}
			}
		})	;

		/**
		 * TO ENTER RECORDS IN THE TABLE form_table
		 */
		mSubmitButton = (Button)findViewById(R.id.new_button);                              //set mSubmitButton to submit a new form button
		mSubmitButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {                                           // when click on button, do the following
				Intent intent = new Intent(HomeScreen.this, ListOfFormsScreen.class);       //go to ListOfFormsScreen
				intent.putExtra("flag", true);                                              // transferring the data from one activity to another (true value)
				startActivity(intent);
			}
		})	;

	}
}
