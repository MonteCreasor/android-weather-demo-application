package org.openweathermap.activity;

import org.openweather.R;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * Displays a dialog with an option to include a message or 
 * an "OK" button if the EXTRA_BUTTON_TYPE and EXTRA_DIALOG_TEXT 
 * extras are provided. The class could be extend to include more button types.
 * @author samkirton
 */
public class DialogActivity extends Activity implements OnClickListener {
	private Button uiOkButton;
	private TextView uiActivityDialogTextView;
	
	private int mButtonType;
	
	public static final int BUTTON_TYPE_OK = 1000;
	public static final int BUTTON_TYPE_NONE = 1001;
	public static final String EXTRA_BUTTON_TYPE = "EXTRA_BUTTON_TYPE";
	public static final String EXTRA_DIALOG_TEXT = "EXTRA_DIALOG_TEXT";
	public static final String EXTRA_CANCEL_OPERATION = "EXTRA_CANCEL_OPERATION";
	public static final String BROADCAST_CLOSE_DIALOG = "BROADCAST_CLOSE_DIALOG";
	
	/**
	 * A BroadCastReceiver is used to close the dialog when the host activity 
	 * finishes its processing. The closeDialog() method in the BaseActivity will
	 * send the broadcast.
	 */
	private BroadcastReceiver mBroadcastReciever = new BroadcastReceiver() {
	    @Override
	    public void onReceive(Context context, Intent intent) {
	        String action = intent.getAction();
	        if (action.equals(BROADCAST_CLOSE_DIALOG)) {
	            finish();
	        }
	    }
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog);
		uiOkButton = (Button)findViewById(R.id.activity_dialog_ok_button);
		uiActivityDialogTextView = (TextView)findViewById(R.id.activity_dialog_textview);
		
		// load the text from the intent and display it on the dialog TextView
		Intent intent = getIntent();
		String dialogText = intent.getStringExtra(EXTRA_DIALOG_TEXT);
		mButtonType = intent.getIntExtra(EXTRA_BUTTON_TYPE, BUTTON_TYPE_OK);
		uiActivityDialogTextView.setText(dialogText);
		
		// show the button type that has been requested
		if (mButtonType == BUTTON_TYPE_OK) {
			uiOkButton.setVisibility(View.VISIBLE);
			uiOkButton.setOnClickListener(this);
		} 
		
		// register the broadcast receiver so the host activity can close the dialog
		registerReceiver(mBroadcastReciever, new IntentFilter(BROADCAST_CLOSE_DIALOG));
	}
	
	@Override
	public void finish() {
		super.finish();
		unregisterReceiver(mBroadcastReciever);
		overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
	}
	
	/**
	 * Close the dialog and send a cancel result back to the host activity
	 */
	private void button_Click(int buttonType) {
		Intent intent = new Intent();		
		setResult(buttonType, intent);
		finish();
	}

	@Override
	public void onBackPressed() {
		button_Click(mButtonType);
	}
	
	@Override
	public void onClick(View v) {
		button_Click(mButtonType);
	}
}
