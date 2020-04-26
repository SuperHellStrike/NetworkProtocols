package hellstrike21291.utils;

import com.jogamp.opengl.GLAutoDrawable;

public class Updater extends Thread {

	private GLAutoDrawable canvas;
	private boolean isActive;
	
	public Updater(GLAutoDrawable canvas) {
		this.canvas = canvas;
		isActive = true;
	}
	
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

	public void switchActive() {
		isActive = !isActive;
	}
}
