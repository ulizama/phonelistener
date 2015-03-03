# Phonelistener Android Module [![Titanium](http://www-static.appcelerator.com/badges/titanium-git-badge-sq.png)](http://www.appcelerator.com/titanium/) 

This module allows your app to register listeners to the device phone. With this listeners you'll be able to know when the phone is receiving a call, making a call, etc.

## Usage

Require the module on your tiapp.xml:

```xml
    <modules>
        <module platform="android">com.baboonmedia.phonelistener</module>
    </modules>
```

You will also need to request some permissions to be able to listen to phone changes:

```xml
        <manifest>
            <uses-permission android:name="android.permission.PROCESS_OUTGOING_CALLS"/>
            <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
        </manifest>
```

Then on your app, you'll need to require the module, set the listener and initialize the module:

```javascript
var PhoneManager = require('com.baboonmedia.phonelistener');

PhoneManager.addEventListener('phoneChange', function(e){
	if( e.status == 'incoming' ){
		Ti.API.info('Ringing Icnoming from ' + e.phone);
	}
	else if( e.status == 'outgoing' ){
		Ti.API.info('Outgoing ringing to ' + e.phone);
	}
	else if( e.status == 'offhook' ){
		Ti.API.info('Call answered');
	}
	else if( e.status == 'hanged' ){
		Ti.API.info('Call hanged up');
	}
});

PhoneManager.init();
```

You listen to the `phoneChange`event which will return an event with two properties: `status`which is the current status of the phone, and `phone` which is the phone number used.

** NOTE: Only the incoming and outgoing events return the 'phone' number so you need to make sure to save it on those events. **

## Phone Status

| Status | Description | Phone |
| -------- | ------- | ----------- |
| incoming | The phone is ringing with an incoming call. | Yes, with the number that is calling. |
| outgoing | A number has been dialed and the phone is waiting for recipient to pickup. |  Yes, with the dialled number. |
| offhook | Phone is off the hook. A call is in place. | No |
| hanged | The phone has been hanged. | No |


Usually the order of an actual call will be:

(incoming|outgoing) -> offhook -> hanged

And the order of a call with no answer will be:

(incoming|outgoing) -> hanged

## Author
Uriel Lizama
uriel@baboonmedia.com

## Licenses

This work is released under the MIT license.

Appcelerator, Appcelerator Titanium and associated marks and logos are trademarks of Appcelerator, Inc.

Titanium is Copyright (c) 2008-2012 by Appcelerator, Inc. All Rights Reserved.