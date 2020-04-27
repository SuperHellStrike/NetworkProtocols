package hellstrike21291.scene;

import java.io.FileOutputStream;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.math.Matrix4;
import com.jogamp.opengl.util.texture.Texture;

import hellstrike21291.utils.Shaders;
import hellstrike21291.utils.VAO;

/**
 * Абстрактный класс графического объекта, который содержит общую
 * информацию для всех объектов сцены. Используется для наследования и реализации
 * более конкретного объекта.
 * 
 * @author Igor Zhigulin
 * @version 1
 */
public abstract class SpaceObject {

	/** Глобальная координата X */
	protected float x = 0;
	
	/** Глобальная координата Y */
	protected float y = 0;
	
	/** Угол поворота вокруг своей оси */
	protected float angle = 0;
	
	/** Объект текстуры для графического объекта */
	protected Texture texture;
	
	/** Объект вершинного массива */
	protected VAO vao;
	
	/** Объект шейдеров, используемый для отрисовки */
	protected Shaders shaders;
	
	/** Модельная матрица */
	protected Matrix4 modelMatrix = new Matrix4();
	
	/** Объект OpenGL 4 */
	protected GL4 gl;
	
	/**
	 * Вращает объект вокруг своей оси
	 * @param dAngle - дельта угла
	 */
	public void rotate(float dAngle) {
		angle += dAngle;
	}

	/**
	 * Перемещает объект в глобальном пространстве
	 * @param dx - дельта X
	 * @param dy - дельта Y
	 */
	public void translate(float dx, float dy) {
		x += dx;
		y += dy;
	}
	
	/**
	 * Абстрактный метод отрисовки объекта
	 */
	public abstract void draw();
	
	/**
	 * Абстрактный метод для определённых действий
	 */
	protected abstract void doAction();
	
	/**
	 * Абстракный метод сохранения данных в файл
	 * @param file - файл для сохранения
	 */
	public abstract void save(FileOutputStream file);
}
