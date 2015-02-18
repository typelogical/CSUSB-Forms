package com.example.dbtask2;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.dbhelper.DBHelper;
import com.example.dbtask.FormOneViewScreen;
import com.example.dbtask.R;


/**
 * CLASS PROVIDE THE DETAILS RECORD OF A PARTICULAR FORM FETCHED FORM DB
 * 
 * @author Android
 *
 */
public class FormRecordsViewScreen extends Activity {

	private TextView mNameTextView=null;
	private TextView mSIDTextView=null;
	private TextView mPhoneTextView=null;
	private TextView mQuarterTextView=null;
	private TextView mMajorTextView=null;
	private TextView myearTextView=null;

	private Button mReturnButton=null;
	private DBHelper mDBhelper=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forms_record_view_screen);

		//CREATING DATABASE OBJECT 
		mDBhelper = new DBHelper(FormRecordsViewScreen.this);

		//CREATING CURSOR FOR FETCING OUT THE RECORDS FROM SERVER
		Cursor cursor = mDBhelper.getDataFormTable();
		cursor.moveToLast();


		/**
		 * FETCHING DATA FROM INTENT TRASFERED FROM FormListAdapter ONCLICK OF A PARTICULAR RECORD 
		 */
		String name= cursor.getString(cursor.getColumnIndex(DBHelper.FORMS_COLUMN_NAME));
		String sid= cursor.getString(cursor.getColumnIndex(DBHelper.FORMS_COLUMN_SID));
		String phone= cursor.getString(cursor.getColumnIndex(DBHelper.FORMS_COLUMN_FORM_PHONE));
		String quarter= cursor.getString(cursor.getColumnIndex(DBHelper.FORMS_COLUMN_FORM_QUARTER));
		String year= cursor.getString(cursor.getColumnIndex(DBHelper.FORMS_COLUMN_FORM_YEAR));
		String major= cursor.getString(cursor.getColumnIndex(DBHelper.FORMS_COLUMN_FORM_MAJOR));


		//SET TEXT TO THE TEXT FIELDS SHOWN ON THE SCREENS.
		mNameTextView = (TextView)findViewById(R.id.name_tv);
		mNameTextView.setText("Name: "+name);

		mSIDTextView = (TextView)findViewById(R.id.sid_tv);
		mSIDTextView.setText("SID#: "+sid);

		mPhoneTextView = (TextView)findViewById(R.id.phone_tv);
		mPhoneTextView.setText("Phone #: "+phone);

		mQuarterTextView = (TextView)findViewById(R.id.quarter_tv);
		mQuarterTextView.setText("Quarter: "+quarter);

		myearTextView = (TextView)findViewById(R.id.year_tv);
		myearTextView.setText("Year :"+year);

		mMajorTextView = (TextView)findViewById(R.id.major_tv);
		mMajorTextView.setText("Major :"+major);

		//ONCLICK OF RETURN BUTTON THE USER IS DIRECTED TO THE BACK SCREEN.
		mReturnButton = (Button)findViewById(R.id.return_button);
		mReturnButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FormRecordsViewScreen.this.finish();
			}
		});
	}
}
