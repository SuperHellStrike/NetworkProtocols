package hellstrike21291.scene;

import java.io.FileOutputStream;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.math.Matrix4;
import com.jogamp.opengl.util.texture.Texture;

import hellstrike21291.utils.Shaders;
import hellstrike21291.utils.VAO;

public abstract class SpaceObject {

	protected float x = 0;
	protected float y = 0;
	
	protected float angle = 0;
	
	protected Texture texture;
	protected VAO vao;
	protected Shaders shaders;
	
	protected Matrix4 modelMatrix = new Matrix4();
	
	protected GL4 gl;
	
	public void rotate(float dAngle) {
		angle += dAngle;
	}

	public void translate(float dx, float dy) {
		x += dx;
		y += dy;
	}
	
	public abstract void draw();
		
	protected abstract void doAction();
	
	public abstract void save(FileOutputStream file);
}
