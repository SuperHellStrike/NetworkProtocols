package hellstrike21291.scene;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.util.texture.Texture;

import hellstrike21291.utils.Shaders;
import hellstrike21291.utils.VAO;

public class Planet extends SpaceObject {
	
	private float globalAngle;
	private float sunX;
	private float sunY;
	private float rotSpeed;
	
	public Planet(float x, float y, float sunX, float sunY, GL4 gl, Shaders shaders, VAO vao, Texture texture) {
		this.vao = vao;
		this.shaders = shaders;
		this.gl = gl;
		this.texture = texture;
		
		this.sunX = sunX;
		this.sunY = sunY;
		this.x = x;
		this.y = y;
		
		double distance = Math.pow(x - sunX, 2) + Math.pow(y - sunY, 2);
		rotSpeed = (float) Math.sqrt(1/(distance));
		globalAngle = 0;
	}

	@Override
	public void draw() {
		texture.bind(gl);
		shaders.use();
		doAction();
		vao.bind();
		gl.glDrawArrays(GL4.GL_QUADS, 0, 4);
		vao.unbind();
		globalAngle += rotSpeed;
		if(globalAngle >= Math.PI * 2)
			globalAngle = 0;
	}
	
	@Override
	protected void doAction() {
		modelMatrix.loadIdentity();
		modelMatrix.translate(sunX, sunY, 0);
		modelMatrix.rotate(globalAngle, 0, 0, 1);
		modelMatrix.translate(x - sunX, y - sunY, 0);
		modelMatrix.rotate(angle, 0, 0, 1);
		modelMatrix.scale(0.5f, 0.5f, 1);
		shaders.loadUniformMatrix4(shaders.getUniformLocation("model"), modelMatrix);
	}

	@Override
	public void save() {}

	@Override
	public void load() {}

	@Override
	public void xSave() {}

	@Override
	public void xLoad() {}
	
}