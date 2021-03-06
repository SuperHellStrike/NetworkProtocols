package hellstrike21291.scene;

import java.io.FileOutputStream;
import java.io.IOException;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.util.texture.Texture;

import hellstrike21291.utils.Shaders;
import hellstrike21291.utils.VAO;

/**
 * Класс наследующий от абстрактного графического объекта.
 * Реализует планету, которая вращается вокруг солнца
 * 
 * @author Igor Zhigulin
 * @version 1
 */
public class Planet extends SpaceObject {
	
	/** Угол вокруг своего солнца */
	private float globalAngle;
	
	/** Координата X солнца */
	private float sunX;
	
	/** Координата Y солнца */
	private float sunY;
	
	/** Скорость вращение вокруг солнца */
	private float rotSpeed;
	
	/**
	 * Конструктор для создания новой планеты
	 * @param x - координата X
	 * @param y - координата Y
	 * @param sunX - координата X солнца
	 * @param sunY - координата Y солнца
	 * @param gl - объект OpenGL 4
	 * @param shaders - объект шейдеров
	 * @param vao - объект вершинного массива
	 * @param texture - объект текстуры
	 */
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
	
	/**
	 * Конструктор для загрузки объектов из файла
	 */
	public Planet(float x, float y, float sunX, float sunY, float angle, float globalAngle, GL4 gl, Shaders shaders, VAO vao, Texture texture) {
		this.vao = vao;
		this.shaders = shaders;
		this.gl = gl;
		this.texture = texture;
		
		this.sunX = sunX;
		this.sunY = sunY;
		this.x = x;
		this.y = y;
		
		this.angle = angle;
		this.globalAngle = globalAngle;
		
		double distance = Math.pow(x - sunX, 2) + Math.pow(y - sunY, 2);
		this.rotSpeed = (float) Math.sqrt(1/(distance));
	}

	/**
	 * Определение метода отрисовки
	 */
	@Override
	public void draw() {
		texture.bind(gl);
		shaders.use();
		doAction();
		vao.bind();
		gl.glDrawArrays(GL4.GL_QUADS, 0, 4);
		vao.unbind();
		
		
	}
	
	/**
	 * Определение метода действий
	 */
	@Override
	protected void doAction() {
		modelMatrix.loadIdentity();
		modelMatrix.translate(sunX, sunY, 0);
		modelMatrix.rotate(globalAngle, 0, 0, 1);
		modelMatrix.translate(x - sunX, y - sunY, 0);
		modelMatrix.rotate(angle, 0, 0, 1);
		modelMatrix.scale(0.5f, 0.5f, 1);
		shaders.loadUniformMatrix4(shaders.getUniformLocation("model"), modelMatrix);
		
		globalAngle += rotSpeed;
		if(globalAngle >= Math.PI * 2)
			globalAngle = 0;
	}

	/**
	 * Определение метода сохранения
	 */
	@Override
	public void save(FileOutputStream file) {
		try {
			file.write((Float.toString(x) + " ").getBytes());
			file.write((Float.toString(y) + " ").getBytes());
			file.write((Float.toString(angle) + " ").getBytes());
			file.write((Float.toString(globalAngle) + " ").getBytes());
		} catch (IOException e) {
			System.out.println("Не удалось записать планету в файл");
		}
	}
}
