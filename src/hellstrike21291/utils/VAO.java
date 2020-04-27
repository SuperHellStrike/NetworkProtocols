package hellstrike21291.utils;

import java.nio.FloatBuffer;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL4;

/**
 * Класс-обёртка для вершинного массива
 * 
 * @author Igor Zhigulin
 * @version 1
 */
public class VAO {
	private int[] vao = new int[1];
	
	private int[] vbos = new int[2];
	
	private GL4 gl;
	
	public VAO(GL4 gl, float[] positionData, int posLoc, float[] textureData, int texLoc, int usage) {
		this.gl = gl;
		
		gl.glGenVertexArrays(1, vao, 0);
		gl.glBindVertexArray(vao[0]);
		
		gl.glGenBuffers(2, vbos, 0);
		
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vbos[0]);
		gl.glBufferData(GL.GL_ARRAY_BUFFER, positionData.length * Float.BYTES, FloatBuffer.wrap(positionData), usage);
		gl.glVertexAttribPointer(posLoc, 2, GL4.GL_FLOAT, false, 0, 0);
		gl.glEnableVertexAttribArray(posLoc);
		
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vbos[1]);
		gl.glBufferData(GL.GL_ARRAY_BUFFER, textureData.length * Float.BYTES, FloatBuffer.wrap(textureData), usage);
		gl.glVertexAttribPointer(texLoc, 2, GL4.GL_FLOAT, false, 0, 0);
		gl.glEnableVertexAttribArray(texLoc);
		
		gl.glBindVertexArray(0);
	}
	
	public void bind() {
		gl.glBindVertexArray(vao[0]);
	}
	
	public void unbind() {
		gl.glBindVertexArray(0);
	}
}
