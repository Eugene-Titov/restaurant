package view;

import java.awt.GridLayout;

public class UserForm extends Form {
	
	private static UserForm USERFORM = new UserForm();
	public static UserForm getUserForm() { return USERFORM; }

	private UserForm() {
		setLayout(new GridLayout(1, 1));
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

}
