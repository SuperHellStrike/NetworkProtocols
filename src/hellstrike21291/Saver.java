package hellstrike21291;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import hellstrike21291.scene.Sun;
/**
 * Класс окна сохранения планет на сцене
 * 
 * @author Valeriya Chekalova
 * @version 1
 */
public class Saver {

	/** Окно сохранения */
	private JFrame frame;
	
	/** Поле для ввода имени файла */
	private JTextPane text;
	
	/** Кнопка для сохранения файла */
	private JButton btn;
	
	/** Метка для поля с именем файла */
	private JLabel label;
	
	/**
	 * Конструктор класса Saver
	 * 
	 * @param sun - Солнце, планеты которого сохранить
	 */
	public Saver(Sun sun) {
		frame = new JFrame("Сохранить сцену");
		text = new JTextPane();
		btn = new JButton("Сохранить");
		label = new JLabel("Имя файла сцены:");
		
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(400, 200);
		
		text.setBounds(10, 30, 200, 20);
		frame.add(text);
		
		btn.setBounds(220, 30, 100, 20);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nameOfFile = text.getText();
				if(nameOfFile != null && !nameOfFile.isEmpty()) {
					FileOutputStream file;
					
					try {
						file = new FileOutputStream(nameOfFile);
						sun.save(file);
						file.close();

					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					catch (IOException e1) {
						e1.printStackTrace();
					}
					frame.dispose();
				}
			}
		});
		frame.add(btn);
		
		label.setBounds(10, 10, 200, 20);
		frame.add(label);
		
		frame.setVisible(true);
	}
}
