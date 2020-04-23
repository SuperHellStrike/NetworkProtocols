package hellstrike21291.scene;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.util.texture.Texture;

import hellstrike21291.utils.Shaders;
import hellstrike21291.utils.VAO;

public class Sun extends SpaceObject{
	
	private PlanetList list;
	
	public Sun(float x, float y, GL4 gl, Shaders shaders, VAO vao, Texture texture) {
		this.x = x;
		this.y = y;
		this.gl = gl;
		this.shaders = shaders;
		this.vao = vao;
		this.texture = texture;
		this.list = new PlanetList();
	}

	@Override
	public void draw() {
		shaders.use();
		texture.bind(gl);
		doAction();
		vao.bind();
		gl.glDrawArrays(GL4.GL_QUADS, 0, 4);
		vao.unbind();
		
		list.rotate(-0.1f);
		list.draw();
	}

	@Override
	protected void doAction() {
		modelMatrix.loadIdentity();
		
		modelMatrix.translate(x, y, 0);
		
		shaders.loadUniformMatrix4(shaders.getUniformLocation("model"), modelMatrix);
	}
	
	public void addPlanet(int x, int y, Texture t) {
		list.push(new Planet(x, y, this.x, this.y, gl, shaders, vao, t));
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
