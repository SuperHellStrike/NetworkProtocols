package hellstrike21291.listeners;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.math.Matrix4;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import hellstrike21291.scene.Sun;
import hellstrike21291.utils.Shaders;
import hellstrike21291.utils.VAO;

public class GameListener extends BasicListener{
	
	private GL4 gl;
	private Shaders shaders;
	private VAO vao;
	private Texture sunTexture;
	private Texture planetTexture;
	
	private Sun sun;
	
	private float[] posData = {
		20.0f, 20.0f,
		-20.0f, 20.0f,
		-20.0f, -20.0f,
		20.0f, -20.0f
	};
		
	private float[] texData = {
		1.0f, 0.0f,
		0.0f, 0.0f,
		0.0f, 1.0f,
		1.0f, 1.0f
	};

	@Override
	public void init(GLAutoDrawable drawable) {
		gl = drawable.getGL().getGL4();
		
		shaders = new Shaders(gl, "shaders/vs.vert", "shaders/fs.frag");
		vao = new VAO(gl, 
				posData, shaders.getAttribLocation("position"),
				texData, shaders.getAttribLocation("texPos"),
				GL4.GL_STATIC_DRAW);
		
		try {
			sunTexture = TextureIO.newTexture(new File("textures/sun (2).png"), true);
			planetTexture = TextureIO.newTexture(new File("textures/planet (2).png"), true);
		} catch (GLException | IOException e) {
			System.out.println(e.getMessage());
		}
		
		
		planetTexture.setTexParameteri(gl, GL4.GL_TEXTURE_MAG_FILTER, GL4.GL_NEAREST);
		planetTexture.setTexParameteri(gl, GL4.GL_TEXTURE_MIN_FILTER, GL4.GL_NEAREST);
		
		sunTexture.setTexParameteri(gl, GL4.GL_TEXTURE_MAG_FILTER, GL4.GL_NEAREST);
		sunTexture.setTexParameteri(gl, GL4.GL_TEXTURE_MIN_FILTER, GL4.GL_NEAREST);
		
		sun = new Sun(250, 250, gl, shaders, vao, sunTexture);
	}
	
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		Matrix4 projection = new Matrix4();
		
		projection.makeOrtho(0, width - 1, height - 1, 0, -1, 1);
		shaders.loadUniformMatrix4(shaders.getUniformLocation("projection"), projection);
		
	}
	
	@Override
	public void display(GLAutoDrawable drawable) {
		gl.glClear(GL4.GL_COLOR_BUFFER_BIT);
		
		sun.draw();
		
		gl.glFlush();		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int button = e.getButton();
		
		if(button == MouseEvent.BUTTON1) {
			sun.addPlanet(x, y, planetTexture);
		}
	}
}
