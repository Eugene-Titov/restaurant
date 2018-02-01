package view;

import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import db.DB;

public class UserForm extends Form {
	
	private static UserForm USERFORM = new UserForm();
	public static UserForm getUserForm() { return USERFORM; }
	
	private DefaultListModel<String> def = new DefaultListModel<>();
	private List<Integer> ids = new ArrayList<>();

	private UserForm() {
		setLayout(new GridLayout(1, 1));
		{
			JPanel panel = new JPanel();			
			JList<String> l = new JList<>(def);
			JScrollPane sc = new JScrollPane(l);
			l.setVisibleRowCount(4);
			
			JButton save = new JButton("save");
			save.addActionListener(event -> {
				int id_rest = ids.get(l.getSelectedIndex());
				try {
					DB.insertSQL("insert into tb_vote(tb_user_id,tb_restaurant_id) values(" + String.valueOf(Authentification.getCurrentUser()) + "," + String.valueOf(id_rest) + ")");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				MainClass.getMainClass().setCurrentForm(Authentification.getAuthentification());
			});
			
			panel.setLayout(new GridLayout(1, 2));
			panel.add(l); panel.add(save);
			add(panel);
		}		
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		def.removeAllElements();
		ids.clear();
		try {
			Map<Integer,String> m = DB.getIdName("select id, name from tb_restaurant");
			for(Map.Entry<Integer, String> e : m.entrySet()) {
				ids.add(e.getKey());
				String r = e.getValue() + "-> ";
				List<String> l = DB.getListStrings("select name from tb_dish where id in (select tb_dish_id from tb_menu where tb_restaurant_id=" + String.valueOf(e.getKey()) + ")");
				for(String s : l) r += s + ", ";
				def.addElement(r);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
