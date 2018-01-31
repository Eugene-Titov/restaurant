package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainClass extends JFrame {
	
	private GridLayout grid = new GridLayout(1, 1);
	private static MainClass MAINCLASS = new MainClass();
	public static MainClass getMainClass() { return MAINCLASS; }
	
	private Form currentForm = null;	
	
	private MainClass() {		
		setSize(300,200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);						
		setLayout(grid);		
		setLocationByPlatform(true);
		setCurrentForm(Authentification.getAuthentification());
		setVisible(true);		
	}
	
	public void setCurrentForm(Form f) {
		if(currentForm != null) {
			currentForm.setVisible(false);
			remove(currentForm);
			invalidate();
		}
		currentForm = f;
		currentForm.clear();
		add(currentForm);
		currentForm.setVisible(true);
		validate();
	}
}
