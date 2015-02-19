package com.example.dbtask2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.dbtask.R;

/**
 * THE SCREEN IS USED TO SHOW THAT THE RECORD IS SUCCESSFULLY INSERTED.
 * 
 * @author Android
 *
 */
public class SuccessfullyInsertedRecordScreen extends Activity {

	private Button mMainButton=null;

    //this is the successfully inserted screen and it has a main screen button which will transfer you back to the home screen
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.successfully_inserted_screen);
		mMainButton= (Button)findViewById(R.id.button1);
		mMainButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SuccessfullyInsertedRecordScreen.this, HomeScreen.class);
				startActivity(intent);
				SuccessfullyInsertedRecordScreen.this.finish();
			}
		});
	}
}
