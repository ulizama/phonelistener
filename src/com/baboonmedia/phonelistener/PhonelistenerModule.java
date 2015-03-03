package com.baboonmedia.phonelistener;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiApplication;

import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.common.TiConfig;

import android.content.IntentFilter;
import android.content.Intent;
import android.telephony.TelephonyManager;


@Kroll.module(name="Phonelistener", id="com.baboonmedia.phonelistener")
public class PhonelistenerModule extends KrollModule
{

	// Standard Debugging variables
	private static final String LCAT = "PhonelistenerModule";
	private static final boolean DBG = TiConfig.LOGD;

	// You can define constants with @Kroll.constant, for example:
	// @Kroll.constant public static final String EXTERNAL_NAME = value;

	public PhonelistenerModule()
	{
		super();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app)
	{
		Log.d(LCAT, "inside onAppCreate");
	}

	// Methods
	@Kroll.method
	public void init() {
		Log.d(LCAT, "inside init()");
		CallReceiver receiver =  new CallReceiver(this);
		CallOutgoing outgoing = new CallOutgoing(this);
		TiApplication.getInstance().getCurrentActivity().registerReceiver(receiver, new IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED));
		TiApplication.getInstance().getCurrentActivity().registerReceiver(outgoing, new IntentFilter(Intent.ACTION_NEW_OUTGOING_CALL));
	}

	public void firePhoneStatusChanged(String status, String phone) {
		fireEvent("phoneChange", createEventObject(status, phone));
	}
	
	private KrollDict createEventObject (String status, String phone) 
	{
		KrollDict event = new KrollDict();
		event.put("status", status);
		event.put("phone", phone);
		return event;
	}

}

