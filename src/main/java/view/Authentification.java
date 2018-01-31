package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

class Registration extends JDialog {
	
	public Registration(JFrame owner) {
		super(owner, "", true);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2));
		
		JLabel ul = new JLabel("user");
		JTextField u = new JTextField(50);
		
		JLabel pl = new JLabel("password");
		JTextField p = new JTextField(50);
		
		JButton a = new JButton("apply");
		a.addActionListener(event -> {
			try {
				DB.saveUser(u.getText(), p.getText());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dispose();
		});
		
		panel.add(ul);
		panel.add(u);
		
		panel.add(pl);
		panel.add(p);
		
		panel.add(new JLabel());
		panel.add(a);
		
		add(panel, BorderLayout.CENTER);
		
		setSize(300,200);
		setLocationByPlatform(true);
		pack();
		setVisible(true);
	}
	
}

public class Authentification extends JFrame {
	
	private JTextField user = null;
	private JPasswordField pass = null;
	
	private JPanel createForm() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2));
		
		JLabel userLabel = new JLabel("user");
		user = new JTextField(50);
		user.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) pass.requestFocus();
			}
		});
		panel.add(userLabel);
		panel.add(user);
		
		JLabel password = new JLabel("password");
		pass = new JPasswordField();
		pass.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) System.exit(0);
			}
		});
		panel.add(password);
		panel.add(pass);
		
		JButton reg = new JButton("Registration");
		reg.addActionListener(event -> {
			new Registration(this);
		});
		panel.add(new JLabel());
		panel.add(reg);
		
		return panel;
	}
	
	public Authentification() {	
		setSize(300,200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(createForm(), BorderLayout.CENTER);
		setLocationByPlatform(true);
		setVisible(true);
	}
	
}
