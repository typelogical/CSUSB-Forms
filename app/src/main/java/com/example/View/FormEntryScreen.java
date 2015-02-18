package com.example.dbtask2;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dbhelper.DBHelper;
import com.example.dbtask.R;
/**
 * FROM THIS SCREEN USER WILL ENTER ALL THE FORMS RECORDS ONE BY ONE IN THE DATABASE
 * 
 * @author Android
 *
 */
public class FormRecordsEntryScreen extends Activity implements OnItemSelectedListener {

	private EditText mNameEditText=null;            //set Name EditText to null
	private EditText mSIDEditText=null;             //set Student ID EditText to null
	private EditText mPhoneEditText=null;           //set Phone EditText to null
	private EditText mYearEditText=null;            //set Year EditText to null
	private EditText mMajorEditText=null;           //set Major EditText to null

	private Button mSubmitButton=null;              //set Submit Button to null

	private DBHelper mDBhelper=null;                //set Database to null

	private Spinner spinner=null;                   //set spinner to null

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forms_entry_screen);
		/**
		 * CREATING OBJECT OF DBHelper CLASS
		 */
		mDBhelper = new DBHelper(FormRecordsEntryScreen.this);


		mNameEditText = (EditText)findViewById(R.id.form_name_edittext);    //setting the edit text to mNameEditText
		mSIDEditText = (EditText)findViewById(R.id.sid_edittext);           //setting the edit text to mSIDEditText
		mPhoneEditText = (EditText)findViewById(R.id.phone_edittext);       //setting the edit text to mPhoneEditText
		mYearEditText  = (EditText)findViewById(R.id.year_edittext);        //setting the edit text to mYearEditText
		mMajorEditText  = (EditText)findViewById(R.id.major_edittext);      //setting the edit text to mMajorEditText


		spinner = (Spinner) findViewById(R.id.spinner);                     //setting the spinner ID to spinner

		// Spinner click listener
		spinner.setOnItemSelectedListener(this);

		List categories = new ArrayList();                                  //constructing the arraylist
		categories.add("Fall");
		categories.add("Winter");
		categories.add("Spring");

		// Creating adapter for spinner
		ArrayAdapter dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);

		// Drop down layout style - list view with radio button
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		spinner.setAdapter(dataAdapter);

		/**
		 * IF ALL THE FIELDS ARE FIELD FROM USER THEN ON CLICK OF SUBMIT BUTTON ENTERING THE VALUES IN DB.
		 * 
		 */
		mSubmitButton = (Button)findViewById(R.id.Submit_button);
		mSubmitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				/**
				 * VALIDATION FOR ALL THE EDIT FIELDS
				 */
				if(mNameEditText.getText().toString().length()>0){

					if(mSIDEditText.getText().toString().length()>0){

						if(mPhoneEditText.getText().toString().length()>0){

							if(mYearEditText.getText().toString().length()>0){


								if(mMajorEditText.getText().toString().length()>0){

									String spinnerValue = spinner.getSelectedItem().toString();

									if(spinnerValue!=null){

										//INSERTING FORM RECORDS IN THE DB TABLE form_TABLE

										mDBhelper.insertRecordFormTable(mNameEditText.getText().toString(), mSIDEditText.getText().toString(),
												mPhoneEditText.getText().toString(),spinnerValue, mYearEditText.getText().toString(),mMajorEditText.getText().toString());


										//Toast.makeText(FormRecordsEntryScreen.this, "Record successfully inserted.", Toast.LENGTH_SHORT).show();

										/**WHEN RECORD IS SUCCESSFULLY INSERTED 
										 * 
										 * DIRECTING THE APPLICATION FROM StudentInfoEditScreen TO SuccessfullyInsertedRecordScreen
										 */
										
										Intent intent = new Intent(FormRecordsEntryScreen.this, SuccessfullyInsertedRecordScreen.class);
										startActivity(intent);
										FormRecordsEntryScreen.this.finish();

									}else{
										Toast.makeText(FormRecordsEntryScreen.this, "Please select some value of quarter.", Toast.LENGTH_SHORT).show();

									}
								}else{
									Toast.makeText(FormRecordsEntryScreen.this, "Please fill major info.", Toast.LENGTH_SHORT).show();
								}
							}else {
								Toast.makeText(FormRecordsEntryScreen.this, "Please fill year.", Toast.LENGTH_SHORT).show();
							}
						}else {
							Toast.makeText(FormRecordsEntryScreen.this, "Please fill phone.", Toast.LENGTH_SHORT).show();
						}
					}else {
						Toast.makeText(FormRecordsEntryScreen.this, "Please fill Student-id.", Toast.LENGTH_SHORT).show();
					}
				}else{
					Toast.makeText(FormRecordsEntryScreen.this, "Please fill name.", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}
}
