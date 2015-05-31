package com.app.robotsgame;

import com.app.robotsgame.graphics.GameRenderer;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.widget.Toast;

public class MainScreen extends Activity {

	private GLSurfaceView gameSurface;
	private boolean isRendererSet=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		gameSurface=new GLSurfaceView(this);
		if(isOpenGLES2Supported()){
			gameSurface.setEGLContextClientVersion(2);
			gameSurface.setRenderer(new GameRenderer(this));
			setContentView(gameSurface);
		} else {
			Toast.makeText(this, "OpenGL ES 2 is not supported!!!", Toast.LENGTH_LONG).show();
			return;
		}
		
		
	}
	
	protected void onPause(){
		super.onPause();
		if(isRendererSet){
			gameSurface.onPause();
		}
	}
	
	protected void onResume(){
		super.onResume();
		if(isRendererSet){
			gameSurface.onResume();
		}
	}

	private boolean isOpenGLES2Supported() {
		ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
		return configurationInfo.reqGlEsVersion >= 0x20000;
	}

}
