package hellstrike21291.utils;

import com.jogamp.opengl.GLAutoDrawable;

/**
 * Класс для обновления сцены (для анимации)
 * 
 * @author Valeriya Chekalova
 * @version 1
 */
public class Updater extends Thread {

	/** Холст OpenGL для вызова перерисовки */
	private GLAutoDrawable canvas;
	
	/** Состояние активности перерисовки */
	private boolean isActive;
	
	/**
	 * Конструктор аниматора
	 * @param canvas - холст OpenGL
	 */
	public Updater(GLAutoDrawable canvas) {
		this.canvas = canvas;
		isActive = true;
	}
	
	/**
	 * Переопределение метода run() из класса Thread.
	 * FPS фиксированный и равен 50
	 */
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				System.out.println("Updater was iterrupted");
			}
			
			if(isActive)
				canvas.display();
		}
	}

	/**
	 * Переключение состояния активности перерисовки
	 */
	public void switchActive() {
		isActive = !isActive;
	}
}
