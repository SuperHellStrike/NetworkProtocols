package hellstrike21291.scene;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.util.texture.Texture;

import hellstrike21291.utils.Shaders;
import hellstrike21291.utils.VAO;

/**
 * Класс реализующий солнце и наследующий от абстрактного
 * класса графического объекта
 * @author Igor Zhigulin
 * @version 1
 */
public class Sun extends SpaceObject{
	
	/** Список планет принадлежащих солнцу */
	private PlanetList list;
	
	/**
	 * Конструктор солнца
	 * @param x - координата X
	 * @param y - координата Y
	 * @param gl - объект OpenGL 4
	 * @param shaders - объект используемы шейдеров
	 * @param vao - вершинный массив
	 * @param texture - объект текстуры
	 */
	public Sun(float x, float y, GL4 gl, Shaders shaders, VAO vao, Texture texture) {
		this.x = x;
		this.y = y;
		this.gl = gl;
		this.shaders = shaders;
		this.vao = vao;
		this.texture = texture;
		this.list = new PlanetList();
	}

	/**
	 * Определение метода отрисовки
	 */
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

	/**
	 * Определение метода действий
	 */
	@Override
	protected void doAction() {
		modelMatrix.loadIdentity();
		
		modelMatrix.translate(x, y, 0);
		
		shaders.loadUniformMatrix4(shaders.getUniformLocation("model"), modelMatrix);
	}
	
	/**
	 * Добавляет планету по указанным её глобальным координатам с указанной текстурой
	 * @param x - координата X планеты
	 * @param y - координата Y планеты
	 * @param t - текстура планеты
	 */
	public void addPlanet(int x, int y, Texture t) {
		list.push(new Planet(x, y, this.x, this.y, gl, shaders, vao, t));
	}

	/**
	 * Очищает список планет
	 */
	public void clear() {
		while(list.getSize() != 0)
			list.pop();
	}
	
	/**
	 * Загружает планеты из файла
	 * @param file - файл с планетами
	 * @param tex - текстура для планет
	 */
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
	
	
	/**
	 * Сохраняет планеты в файл
	 */
	@Override
	public void save(FileOutputStream file) {
		list.save(file);
	}
}
