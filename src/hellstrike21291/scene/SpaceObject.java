package hellstrike21291.scene;

import com.jogamp.opengl.math.Matrix4;
import com.jogamp.opengl.util.texture.Texture;

import hellstrike21291.utils.Shaders;
import hellstrike21291.utils.VAO;

public abstract class SpaceObject {

	protected float x;
	protected float y;
	
	protected float angle;
	
	protected Texture texture;
	protected VAO vao;
	protected Shaders shaders;
	
	protected Matrix4 modelMatrix;
	
	public SpaceObject(float x, float y, Texture texture, VAO vao, Shaders shaders) {
		this.x = x;
		this.y = y;
		this.texture = texture;
		this.vao = vao;
		this.shaders = shaders;
		this.modelMatrix = new Matrix4();
		this.angle = 0;
		this.updateModelMatrix();
	}
	
	public abstract void draw();
	
	public void rotate(float dangle) {
		angle += dangle;
	}
	
	public void translate(float dx, float dy) {
		x += dx;
		y += dy;
	}
	
	protected void updateModelMatrix() {
		modelMatrix.loadIdentity();
		modelMatrix.translate(x, y, 0);
		modelMatrix.rotate(angle, 0, 0, 1);
		shaders.loadUniformMatrix4(shaders.getUniformLocation("model"), modelMatrix);
	}
	
	public void save() {
		
	}
	
	public void load() {
		
	}
	
	public void xSave() {
		
	}
	
	public void xLoad() {
		
	}
}
