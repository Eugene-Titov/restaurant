package view;

import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import db.DB;

public class Registration extends Form {
	
	private static Registration REGISTRATION = new Registration();
	public static Registration getRegistration() { return REGISTRATION; }
	
	private Registration() {				
		setLayout(new GridLayout(3, 2));
		
		JLabel ul = new JLabel("user");
		JTextField u = new JTextField(50);
		
		JLabel pl = new JLabel("password");
		JTextField p = new JTextField(50);
		
		JButton a = new JButton("apply");
		a.addActionListener(event -> {
			try {
				DB.saveUser(u.getText(), p.getText());
				MainClass.getMainClass().setCurrentForm(Authentification.getAuthentification());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		});
		
		add(ul);
		add(u);
		
		add(pl);
		add(p);
		
		add(new JLabel());
		add(a);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
	}

}
