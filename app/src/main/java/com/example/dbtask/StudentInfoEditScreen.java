package com.example.dbtask;


import com.example.dbhelper.DBHelper;
import com.example.dbtask2.FormRecordsEntryScreen;
import com.example.dbtask2.SuccessfullyInsertedRecordScreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 *  THIS ACTIVITY IS FOR INSERTING STUDENT INFORMATION IN THE STUDENT TABLE.
 *  
 * @author Android
 *
 */
public class StudentInfoEditScreen extends Activity {

	private EditText mNameEditText=null;            //set Name to null
	private EditText mSIDEditText=null;             //set S ID# to null
	private EditText mPhoneEditText=null;           //set Phone to null
	private EditText mEmailIdEditText=null;         //set Email to null

	private Button mSubmitButton=null;              //set submit button to null

	private DBHelper mDBhelper=null;                //set database to null

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_info_edit_screen);

		// CREATING OBJECT FOR DATABSE HELPER
		mDBhelper = new DBHelper(StudentInfoEditScreen.this);


		mNameEditText = (EditText)findViewById(R.id.name_edittext);     //name edit text to mNameEditText
		mSIDEditText = (EditText)findViewById(R.id.sid_edittext);       //student id edit text to variable
		mPhoneEditText = (EditText)findViewById(R.id.phone_edittext);   //phone edit text to variable
		mEmailIdEditText = (EditText)findViewById(R.id.emailedittext);  //email edit text to mEmailIdEditText

		// ONCLICK OF SUBMIT BUTTON ALL THE VALUES ENTERED IN THE EDIT FIELD FROM USER IS INSERTED IN THE DATABASE
		
		mSubmitButton = (Button)findViewById(R.id.Submit_button);
		mSubmitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				if(mNameEditText.getText().toString().length()>0){  // checks if the name entered is greater than 0 (can't be empty)

					if(mSIDEditText.getText().toString().length()>0){   // checks if the student id entered is greater than 0 (can't be empty)

						if(mPhoneEditText.getText().toString().length()>0){ // checks if the phone number entered is greater than 0 (can't be empty)

							if(mEmailIdEditText.getText().toString().length()>0){   // checks if the email entered is greater than 0 (can't be empty)

								if(Constant.isBasicallyValidEmailAddress(mEmailIdEditText.getText().toString())){   // checks that the email is valid

									//INSERTING RECORD IN THE DB
									mDBhelper.insertRecord(mNameEditText.getText().toString(), mSIDEditText.getText().toString(),
											mPhoneEditText.getText().toString(), mEmailIdEditText.getText().toString());

									/**WHEN RECORD IS SUCCESSFULLY INSERTED 
									 * 
									 * DIRECTING THE APPLICATION FROM StudentInfoEditScreen TO SuccessfullyInsertedRecordScreen
									 */
									Intent intent = new Intent(StudentInfoEditScreen.this, SuccessfullyInsertedRecordScreen.class);
									startActivity(intent);
									StudentInfoEditScreen.this.finish();

								}else {
									Toast.makeText(StudentInfoEditScreen.this, "Please fill email id.", Toast.LENGTH_SHORT).show();
								}
							}else {
								Toast.makeText(StudentInfoEditScreen.this, "Please fill phone number.", Toast.LENGTH_SHORT).show();
							}
						}else {
							Toast.makeText(StudentInfoEditScreen.this, "Please fill student id.", Toast.LENGTH_SHORT).show();
						}
					}else {
						Toast.makeText(StudentInfoEditScreen.this, "Please fill name.", Toast.LENGTH_SHORT).show();
					}
				}else{
					Toast.makeText(StudentInfoEditScreen.this, "Please fill user id.", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
