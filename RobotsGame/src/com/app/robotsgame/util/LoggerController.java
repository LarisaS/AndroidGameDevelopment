package com.app.robotsgame.util;

import android.util.Log;

public class LoggerController {

	public static final boolean ON = true;
	
	public static void warn(String TAG, String message){
		if(ON){
			Log.w(TAG, message);
		}
	}
	
	public static void info(String TAG, String message){
		if(ON){
			Log.i(TAG, message);
		}
	}
	
	public static void error(String TAG, String message){
		if(ON){
			Log.e(TAG, message);
		}
	}
	
	public static void debug(String TAG, String message){
		if(ON){
			Log.d(TAG, message);
		}
	}
}
