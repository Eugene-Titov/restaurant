package view;

import java.awt.GridLayout;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListDataListener;

import db.DB;


public class AdminForm extends Form {
	
	private static AdminForm ADMINFORM = new AdminForm();
	public static AdminForm getAdminForm() { return ADMINFORM; }

	private AdminForm() {
		
		setLayout(new GridLayout(4, 1));
		{
			JPanel rest = new JPanel();
			rest.setLayout(new GridLayout(1, 3));
			JLabel rl = new JLabel("name restourant");
			JTextField re = new JTextField(50);
			JButton br = new JButton("rest");
			br.addActionListener(event -> {
				try {
					DB.insertSQL("insert into tb_restaurant(name) values('" + re.getText() + "')");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			});
			
			rest.add(rl); rest.add(re); rest.add(br);
			add(rest);
		}
		{
			JPanel dishes = new JPanel();
			dishes.setLayout(new GridLayout(1, 3));
			JLabel rl = new JLabel("name dish");
			JTextField re = new JTextField(50);
			JTextField dc = new JTextField(50);
			JButton br = new JButton("dish");
			br.addActionListener(event -> {
				try {
					DB.insertSQL("insert into tb_dish(name,cost) values('" + re.getText() + "'," + dc.getText() + ")");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			});
			
			dishes.add(rl); dishes.add(re); dishes.add(dc); dishes.add(br);
			add(dishes);
		}
		{
			JPanel menu = new JPanel();
			menu.setLayout(new GridLayout(1, 4));
			JComboBox<String> rest = new JComboBox<>();
			
			DefaultListModel<String> model = new DefaultListModel<>();
			JList<String> l = new JList<>(model);				
			JScrollPane sc = new JScrollPane(l);
			l.setVisibleRowCount(4);
			l.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			
			JButton update = new JButton("update");			
			update.addActionListener(event -> {
				rest.removeAll();
				try {
					List<String> lr = DB.getListStrings("select name from tb_restaurant");
					for(String r : lr) rest.addItem(r);
					
					model.removeAllElements();
					List<String> ld = DB.getListStrings("select name from tb_dish");
					for(String d : ld) model.addElement(d);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});	
			
			JButton save = new JButton("save");
			save.addActionListener(event -> {
				List<String> sel = l.getSelectedValuesList();
				try {
					int id_rest = DB.getId("select id from tb_restaurant where name='" + rest.getItemAt(rest.getSelectedIndex()) + "'");
					for(String d : sel) {					
						try {
							int id_dish = DB.getId("select id from tb_dish where name='" + d + "'");
							if(id_rest > 0 && id_dish > 0) {
								DB.insertSQL("insert into tb_menu(tb_restaurant_id,tb_dish_id) values(" + String.valueOf(id_rest) + "," + String.valueOf(id_dish)+ ")");
							}
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			});
			
			menu.add(update); menu.add(rest); menu.add(l); menu.add(save);
			add(menu);
		}
		{
			JPanel rest = new JPanel();
			rest.setLayout(new GridLayout(1, 2));
			DefaultListModel<String> model = new DefaultListModel<>();
			JList<String> l = new JList<>(model);
			JScrollPane sc = new JScrollPane(l);
			l.setVisibleRowCount(4);
			
			try {
				Map<Integer,String> m = DB.getIdName("select id, name from tb_restaurant");
				for(Map.Entry<Integer, String> e : m.entrySet()) {
					String s = e.getValue() + "->" + DB.getCount("select id from tb_vote where tb_restaurant_id=" + String.valueOf(e.getKey()));
					model.addElement(s);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JButton exit = new JButton("exit");
			exit.addActionListener(event -> {
				MainClass.getMainClass().setCurrentForm(Authentification.getAuthentification());
			});
			
			rest.add(l); rest.add(exit);
			add(rest);
		}
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

}
