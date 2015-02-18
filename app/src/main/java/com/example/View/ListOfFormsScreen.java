package com.example.dbtask2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.dbhelper.DBHelper;
import com.example.dbtask.FormOneViewScreen;
import com.example.dbtask.R;
import com.example.dbtask.StudentInfoEditScreen;
/**
 * THIS SCREEN WILL PROVIDE THE LIST OF FORMS WHICH ARE CREATED AND STORED IN THE DATABSE IN OUR APPLICATION
 * 
 * @author Android
 *
 */
public class ListOfFormsScreen extends Activity {

	private Button mClassSectionButton=null;        //set Class Section Button to null
	private Button mStudentInfoButton=null;         //set Student Information Button to null

	private DBHelper mDBhelper=null;                //Set database variable to null
	private TextView mTextView=null;                // List of forms TextView to null

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listofforms);

		final boolean flag = this.getIntent().getBooleanExtra("flag", false);

		mDBhelper = new DBHelper(this);         //new database object

		mTextView = (TextView)findViewById(R.id.text_tv);   //view the "List of Forms" text

		//FORM OF CLASS SECTION FORM CENSUS
		mClassSectionButton = (Button)findViewById(R.id.button1);   //When clicking on Census Class Section Button, do the following
		mClassSectionButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(flag){
					Intent intent = new Intent(ListOfFormsScreen.this, FormRecordsEntryScreen.class);
					startActivity(intent);
				}else{
					Intent intent = new Intent(ListOfFormsScreen.this, FormRecordsViewScreen.class);
					startActivity(intent);
				}
			}
		});

		// STUDENT INFORMATION BUTTON
		mStudentInfoButton = (Button)findViewById(R.id.button2);
		mStudentInfoButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(flag){
					Intent intent = new Intent(ListOfFormsScreen.this, StudentInfoEditScreen.class);
					startActivity(intent);
				}else{
					Intent intent = new Intent(ListOfFormsScreen.this, FormOneViewScreen.class);
					startActivity(intent);
				}
			}
		});

		
		Cursor cursor2 = mDBhelper.getDataFormTable();
		Cursor cursor = mDBhelper.getData();

		if(!flag){
			if(!(cursor2.getCount()>0))
				mClassSectionButton.setVisibility(View.INVISIBLE);

			if(!(cursor.getCount()>0))
				mStudentInfoButton.setVisibility(View.INVISIBLE);

			if(!(cursor2.getCount()>0) && !(cursor.getCount()>0)){
				mTextView.setText("No forms submitted");
			}
		}
	}

}

