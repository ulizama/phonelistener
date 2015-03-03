package com.baboonmedia.phonelistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

import org.appcelerator.kroll.common.Log;

public class CallReceiver extends BroadcastReceiver {
	
	private static final String LCAT = "PhonelistenerModule";
	
	PhonelistenerModule _module = null;
	
	public CallReceiver(PhonelistenerModule module) {
		_module = module;
	}

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(
                TelephonyManager.EXTRA_STATE_RINGING)) {

                // Phone number 
                String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

                // Ringing state
                // This code will execute when the phone has an incoming call
                Log.d(LCAT, ">>> PHONE LISTENER Phone Incoming Ringing");
                Log.d(LCAT, incomingNumber);
                _module.firePhoneStatusChanged("incoming", incomingNumber);
                
                
        } else if ( intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(
                        TelephonyManager.EXTRA_STATE_OFFHOOK)) {

            // This code will execute when the call is answered or disconnected
        	Log.d(LCAT, ">>> PHONE LISTENER Phone OffHook");
        	_module.firePhoneStatusChanged("offhook","");
        	
        	
        } else if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(
                TelephonyManager.EXTRA_STATE_IDLE)) {
        	
            // This code will execute when the call is answered or disconnected
        	Log.d(LCAT, ">>> PHONE LISTENER Phone Idle");
        	
        	_module.firePhoneStatusChanged("hanged", "");
        	
        }

    }
}