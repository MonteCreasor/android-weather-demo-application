package org.openweathermap.fragment;

import org.openweather.R;

import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Displays a dialog with an option to include a message or 
 * an "OK" button if the EXTRA_BUTTON_TYPE and EXTRA_DIALOG_TEXT 
 * extras are provided. The class could be extend to include more button types.
 * @author samkirton
 */
public class ModalDialogFragment extends DialogFragment implements OnClickListener, OnKeyListener {
	private Button uiOkButton;
	private TextView uiActivityDialogTextView;
	
	private int mButtonType;
	private boolean mIsBlocking;
	
	public static final int BUTTON_TYPE_OK = 1000;
	public static final int BUTTON_TYPE_BLOCKING = 1001;
	public static final String EXTRA_BUTTON_TYPE = "EXTRA_BUTTON_TYPE";
	public static final String EXTRA_DIALOG_TEXT = "EXTRA_DIALOG_TEXT";
	public static final String EXTRA_CANCEL_OPERATION = "EXTRA_CANCEL_OPERATION";
	
	public boolean isBlocking() {
		return mIsBlocking;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(STYLE_NORMAL, R.style.Dialog);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_modal_dialog, container, false);
		uiOkButton = (Button)view.findViewById(R.id.fragment_dialog_ok_button);
		uiActivityDialogTextView = (TextView)view.findViewById(R.id.activity_dialog_textview);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Bundle intent = getArguments();
		
		String dialogText = intent.getString(EXTRA_DIALOG_TEXT);
		mButtonType = intent.getInt(EXTRA_BUTTON_TYPE, BUTTON_TYPE_OK);
		uiActivityDialogTextView.setText(dialogText);
		
		// show the button type that has been requested
		if (mButtonType == BUTTON_TYPE_OK) {
			uiOkButton.setVisibility(View.VISIBLE);
			uiOkButton.setOnClickListener(this);
		}  else if (mButtonType == BUTTON_TYPE_BLOCKING) {
			mIsBlocking = true;
		}
		
		// setup the dialog key listener for blocking dialogs
		getDialog().setOnKeyListener(this);
	}
	
	/**
	 * Close the dialog and send a cancel result back to the host activity
	 */
	private void button_Click(int buttonType) {
		dismiss();
	}

	
	@Override
	public void onClick(View v) {
		button_Click(mButtonType);
	}

	@Override
	public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
		// When the dialog is open and it is in blocking mode, the back button
		// should be disabled
		if (keyCode == KeyEvent.KEYCODE_BACK && mIsBlocking)
			return true;
	     
		return false;
	}
}