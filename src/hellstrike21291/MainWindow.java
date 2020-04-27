package hellstrike21291;

import javax.swing.JFrame;

import com.jogamp.opengl.awt.GLCanvas;

import hellstrike21291.listeners.BasicListener;
import hellstrike21291.listeners.GameListener;
import hellstrike21291.utils.Updater;

/**
 * Класс главного окна приложения
 * 
 * @author Igor Zhigulin
 * @version 1
 */
public class MainWindow {

	/** Фрейм окна */
	private JFrame frame;

	/** Холст OpenGL */
	private GLCanvas canvas;
	
	/** Слушатель событий OpenGL, клавиатуры и мыши */
	private BasicListener listener;
	
	/** Аниматор холста */
	private Updater upd;
	
	/**
	 * Конструктор класса главного окна без параметров.
	 * Инициализирует все поля, настраивает их и открывает окно
	 */
	public MainWindow() {
		frame = new JFrame("AVT-818 Жигулин Чекалова");
		canvas = new GLCanvas();
		upd = new Updater(canvas);
		listener = new GameListener(upd);
		
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		canvas.addGLEventListener(listener);
		canvas.addMouseListener(listener);
		canvas.addKeyListener(listener);
		frame.add(canvas);
		
		frame.setVisible(true);
	}
	
	/**
	 * Статический метод main() с которого стартует приложение
	 * @param args - аргументы командной строки
	 */
	public static void main(String[] args) {
		new MainWindow();
	}

}
