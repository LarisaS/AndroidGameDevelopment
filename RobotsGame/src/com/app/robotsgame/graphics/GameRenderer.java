package com.app.robotsgame.graphics;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUseProgram;
import static android.opengl.GLES20.glVertexAttribPointer;
import static android.opengl.GLES20.glViewport;
import static com.app.robotsgame.util.GraphicsHelper.compileFragmentShader;
import static com.app.robotsgame.util.GraphicsHelper.compileVertexShader;
import static com.app.robotsgame.util.GraphicsHelper.linkProgram;
import static com.app.robotsgame.util.GraphicsHelper.validateProgram;
import static com.app.robotsgame.util.LoggerController.ON;
import static com.app.robotsgame.util.ResourcesUtil.readTextFileFromResource;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;

import com.app.robotsgame.R;

public class GameRenderer implements Renderer {

	private Context context;
	private static final int POSITION_COMPONENT_COUNT = 2;
	private static final int BYTES_PER_FLOAT = 4;
	private final FloatBuffer vertexData;
	private int program;
	
	private static final String U_COLOR = "u_Color";
	private int uColorLocation;
	
	private static final String A_POSITION = "a_Position";
	private int aPositionLocation;
	
	private float[] tableVertices = {
			0f, 0f,
			9f, 14f,
			0f, 14f,
			// Triangle 2
			0f, 0f,
			9f, 0f,
			9f, 14f,
			
			//Line
			0f, 7f,
			9f, 7f,
			// Mallets
			4.5f, 2f,
			4.5f, 12f
	};
	
	public  GameRenderer(Context context) {
		this.context=context;
		
		vertexData = ByteBuffer
				.allocateDirect(tableVertices.length * BYTES_PER_FLOAT)
				.order(ByteOrder.nativeOrder())
				.asFloatBuffer();
				vertexData.put(tableVertices);
	}
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
		String vertexShaderSource = readTextFileFromResource(context, R.raw.simple_vertex_shader);
		String fragmentShaderSource = readTextFileFromResource(context, R.raw.simple_fragment_shader);
	
		program = linkProgram(compileVertexShader(vertexShaderSource), compileFragmentShader(fragmentShaderSource));
	
		if(ON){
			validateProgram(program);
		}
		
		glUseProgram(program);
		
		uColorLocation = glGetUniformLocation(program, U_COLOR);
		
		aPositionLocation = glGetAttribLocation(program, A_POSITION);
		
		vertexData.position(0);
		glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT, GL_FLOAT, false, 0, vertexData);
		glEnableVertexAttribArray(aPositionLocation);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		glViewport(0, 0, width, height);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		glClear(GL_COLOR_BUFFER_BIT);
	}

}
