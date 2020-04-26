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

public class Saver {

	private JFrame frame;
	private JTextPane text;
	private JButton btn;
	private JLabel label;
	
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
