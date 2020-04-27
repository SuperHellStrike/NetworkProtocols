package hellstrike21291.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;


/**
 * Класс базового слушателя событий OpenGL, клавиатуры и мыши.
 * Нужен лишь для наследование, но не является абстрактным, чтобы 
 * была возможность создать его экземпляр для тестирования в самом
 * начале разработки.
 * 
 * @author Igor Zhigulin
 * @version 1
 */
public class BasicListener implements GLEventListener, MouseListener, KeyListener{

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void init(GLAutoDrawable drawable) {}

	@Override
	public void dispose(GLAutoDrawable drawable) {}

	@Override
	public void display(GLAutoDrawable drawable) {}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}
}
