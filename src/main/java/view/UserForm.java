package view;

public class UserForm extends Form {
	
	private static UserForm USERFORM = new UserForm();
	public static UserForm getAdminForm() { return USERFORM; }

	private UserForm() {
		
	}
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

}
