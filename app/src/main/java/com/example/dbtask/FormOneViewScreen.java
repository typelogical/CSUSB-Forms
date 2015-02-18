package com.example.dbtask;

import com.example.dbhelper.DBHelper;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FormOneViewScreen extends Activity {

    private TextView mNameEditText=null;                // set nNameEditText to null
    private TextView mSIDEditText=null;                 // set mSIDEditText to null
    private TextView mPhoneEditText=null;               // set mPhoneEditText to null
	private TextView mEMailEditText=null;               // set mEMailEditText to null

	private Button mReturnButton=null;                  // set mReturnButton to null

	private DBHelper mDBhelper=null;                    // set mDBhelper to null
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_info_view_screen);

		mDBhelper = new DBHelper(FormOneViewScreen.this);

		Cursor cursor = mDBhelper.getData();            // get data from STUDENT_TABLE and place it in cursor
		
		cursor.moveToLast();                            // move cursor to the last row

        //take name from database and place it in cursor, display text under string name
		String name= cursor.getString(cursor.getColumnIndex(DBHelper.STUDENT_COLUMN_NAME));
		String sid= cursor.getString(cursor.getColumnIndex(DBHelper.STUDENT_COLUMN_SID));
		String phone= cursor.getString(cursor.getColumnIndex(DBHelper.STUDENT_COLUMN_PHONE));
		String email= cursor.getString(cursor.getColumnIndex(DBHelper.STUDENT_COLUMN_EMAIL));

		mNameEditText = (TextView)findViewById(R.id.name_tv); //set mNameEditText to id name_tv
		mNameEditText.setText("Name: "+name);                 //display the text that has been taken from database
		
		mSIDEditText = (TextView)findViewById(R.id.sid_tv);
		mSIDEditText.setText("SID #:"+sid);
		
		mPhoneEditText = (TextView)findViewById(R.id.phone_tv);
		mPhoneEditText.setText("Phone #:"+ phone);
		
		mEMailEditText = (TextView)findViewById(R.id.email_tv);
		mEMailEditText.setText("Email Id: "+ email);

		mReturnButton = (Button)findViewById(R.id.return_button);
		mReturnButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				FormOneViewScreen.this.finish();
			}
		});
	}
}
