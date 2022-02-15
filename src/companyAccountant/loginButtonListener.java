package companyAccountant;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class loginButtonListener implements ActionListener {
	
	private loginFrame logFrame;
	private JTextField userField;
	private JPasswordField passField;
	
	public loginButtonListener(loginFrame logFrame, JTextField userField,JPasswordField passField) {
		this.logFrame = logFrame;
		this.userField = userField;
		this.passField = passField;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String usernameCheck = userField.getText();
		
		char[] passArray = passField.getPassword();
		
		String passCheck = logFrame.arrayToPass(passArray);
		
		passArray = null;
		
		if (logFrame.loginChecker(usernameCheck, passCheck)) {
			logFrame.setCurrentUser(usernameCheck);
			logFrame.setVisible(false);
			 new calculationFrame(logFrame);
			userField.setText("Username...");
			userField.setForeground(Color.GRAY);
			passField.setText("**********");
			passField.setForeground(Color.GRAY);
			
			

		}
		else {
			new wrongPasswordThread(logFrame.getWrongPassword(),"⚠️ Wrong Username or Password!");
		}
		
	}
	
	
	

}
