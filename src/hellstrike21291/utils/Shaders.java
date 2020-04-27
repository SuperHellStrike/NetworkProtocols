package hellstrike21291.utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import com.jogamp.common.net.Uri;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.math.Matrix4;
import com.jogamp.opengl.util.glsl.ShaderCode;
import com.jogamp.opengl.util.glsl.ShaderProgram;

/**
 * Класс-обёртка для шейдеров
 * 
 * @author Igor Zhigulin
 * @version 1
 */
public class Shaders {
	private GL4 gl;
	private ShaderProgram program = new ShaderProgram();
	
	private ShaderCode vertexShader;
	private ShaderCode fragmentShader;
	
	private String[][] vertexShaderSource = new String[1][1];
	private String[][] fragmentShaderSource = new String[1][1];
	
	public Shaders(GL4 gl, String pathToVS, String pathToFS) {
		this.gl = gl;
		
		File fileVS = new File(pathToVS);
		File fileFS = new File(pathToFS);
		
		try {
			vertexShaderSource[0][0] = ShaderCode.readShaderSource(Uri.valueOf(fileVS), false).toString();
			fragmentShaderSource[0][0] = ShaderCode.readShaderSource(Uri.valueOf(fileFS), false).toString();
		} catch (IOException | URISyntaxException e) {
			System.out.println(e.getMessage());
		}
		
		vertexShader = new ShaderCode(GL4.GL_VERTEX_SHADER, 1, vertexShaderSource);
		fragmentShader = new ShaderCode(GL4.GL_FRAGMENT_SHADER, 1, fragmentShaderSource);
		
		vertexShader.compile(gl, System.out);
		fragmentShader.compile(gl, System.out);
		
		program.add(vertexShader);
		program.add(fragmentShader);
		program.link(gl, System.out);
		gl.glUseProgram(program.program());
		
		vertexShader.destroy(gl);
		fragmentShader.destroy(gl);
	}
	
	public int getUniformLocation(String uniformName) {
		return gl.glGetUniformLocation(program.program(), uniformName);
	}
	
	public int getAttribLocation(String attribName) {
		return gl.glGetAttribLocation(program.program(), attribName);
	}
	
	public void loadUniformMatrix4(int location, Matrix4 matrix) {
		gl.glUniformMatrix4fv(location, 1, false, matrix.getMatrix(), 0);
	}
	
	public void loadAttrib(int location, int size, int type) {
		gl.glVertexAttribPointer(location, size, type, false, 0, 0);
	}
	
	public void use() {
		gl.glUseProgram(program.program());
	}
}
