package view;

public class UserForm extends Form {
	
	private static UserForm USERFORM = new UserForm();
	public static UserForm getUserForm() { return USERFORM; }

	private UserForm() {
		setLayout()
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

}
