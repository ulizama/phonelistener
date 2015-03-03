package com.baboonmedia.phonelistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.appcelerator.kroll.common.Log;

public class CallOutgoing extends BroadcastReceiver {
	
	private static final String LCAT = "PhonelistenerModule";
	
	PhonelistenerModule _module = null;
	
	public CallOutgoing(PhonelistenerModule module) {
		_module = module;
	}

    @Override
    public void onReceive(Context context, Intent intent) {
    	
    	if (Intent.ACTION_NEW_OUTGOING_CALL.equals(intent.getAction())) {
    		

        	String outgoingNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        	
            // This code will execute when the call is answered or disconnected
        	Log.d(LCAT, ">>> PHONE LISTENER Outgoing Ringing");
        	Log.d(LCAT, outgoingNumber);
        	
        	_module.firePhoneStatusChanged("outgoing", outgoingNumber);
        	
        }

    }
    
}