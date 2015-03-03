// open a single window
var win = Ti.UI.createWindow({
	backgroundColor:'white'
});
win.open();

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
