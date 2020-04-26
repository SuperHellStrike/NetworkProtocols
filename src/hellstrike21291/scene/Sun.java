package hellstrike21291.scene;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

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

	public void clear() {
		while(list.getSize() != 0)
			list.pop();
	}
	
	public void load(FileInputStream file, Texture tex) {
		Scanner s = new Scanner(file);
		
		while(s.hasNext()) {
			float x, y, angle, globalAngle;
			
			String str = s.next();
			x = Float.parseFloat(str);
			
			str = s.next();
			y = Float.parseFloat(str);
			
			str = s.next();
			angle = Float.parseFloat(str);
			
			str = s.next();
			globalAngle = Float.parseFloat(str);
			
			list.push(new Planet(x, y, this.x, this.y, angle, globalAngle, gl, shaders, vao, tex));
		}
		
		s.close();
		
	}
	
	
	
	@Override
	public void save(FileOutputStream file) {
		list.save(file);
	}
}
