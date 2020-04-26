package hellstrike21291;

import javax.swing.JFrame;

import com.jogamp.opengl.awt.GLCanvas;

import hellstrike21291.listeners.BasicListener;
import hellstrike21291.listeners.GameListener;
import hellstrike21291.utils.Updater;

public class MainWindow {

	private JFrame frame;
	private GLCanvas canvas;
	private BasicListener listener;
	private Updater upd;
	
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
	
	public static void main(String[] args) {
		new MainWindow();
	}

}
