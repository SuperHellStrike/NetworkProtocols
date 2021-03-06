package hellstrike21291;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import com.jogamp.opengl.util.texture.Texture;

import hellstrike21291.scene.Sun;

/**
 * Класс загрузчика карты из файла
 * 
 * @author Igor Zhigulin
 * @version 1
 */
public class Loader {
	
	/** Фрейм окна загрузки */
	private JFrame frame;
	
	/** Кнопка для загрузки */
	private JButton btn;
	
	/** Текстовая метка над полем для ввода имени файла */
	private JLabel label;
	
	/** Текстовое поле для ввода имени файла */
	private JTextPane text;
	
	/**
	 * Конструктор окна загрузки сцены
	 * 
	 * @param sun - объект-группа (Солнце), к которому загрузить планеты
	 * @param t - текстура для планеты
	 */
	public Loader(Sun sun, Texture t) {
		frame = new JFrame("Загрузка сцены");
		btn = new JButton("Загрузить");
		label = new JLabel("Имя файла сцены:");
		text = new JTextPane();
		
		frame.setSize(400, 200);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(null);
		
		label.setBounds(10, 10, 200, 20);
		frame.add(label);
		
		text.setBounds(10, 30, 200, 20);
		frame.add(text);
		
		btn.setBounds(220, 30, 100, 20);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nameOfFile = text.getText();
				
				if(nameOfFile != null && !nameOfFile.isEmpty()) {
					FileInputStream file = null;
					
					try {
						file = new FileInputStream(nameOfFile);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					sun.clear();
					sun.load(file, t);
				}
				
				frame.dispose();
			}
		});
		frame.add(btn);
		
		frame.setVisible(true);
	}
}
