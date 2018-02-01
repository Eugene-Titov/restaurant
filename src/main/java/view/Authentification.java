package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import db.DB;

public class Authentification extends Form {
	
	private static Authentification AUTHENTIFICATION = new Authentification();
	public static Authentification getAuthentification() { return AUTHENTIFICATION; }	
	
	private JTextField user = null;
	private JPasswordField pass = null;
		
	private Authentification() {
		
		setLayout(new GridLayout(3, 2));
		
		JLabel userLabel = new JLabel("user");
		user = new JTextField(50);
		user.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				if(e.getKeyCode() == KeyEvent.VK_ENTER) pass.requestFocus();
			}
		});
		add(userLabel);
		add(user);
		
		JLabel password = new JLabel("password");
		pass = new JPasswordField();
		pass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {						
						int id_user = DB.getIdUser(user.getText(), new String(pass.getPassword()));
						if(user.getText().equals("admin") && id_user > -1) MainClass.getMainClass().setCurrentForm(AdminForm.getAdminForm());
						else if(id_user > -1) MainClass.getMainClass().setCurrentForm(UserForm.getUserForm());
						else clear();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		add(password);
		add(pass);
		
		JButton reg = new JButton("Registration");
		reg.addActionListener(event -> {
			MainClass.getMainClass().setCurrentForm(Registration.getRegistration());
		});
		add(new JLabel());
		add(reg);		
	}
		
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		user.setText("");
		pass.setText("");
	}
	
}
