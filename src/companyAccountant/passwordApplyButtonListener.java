package companyAccountant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class passwordApplyButtonListener implements ActionListener {
	
	private changePasswordFrame passFrame;
	private JPasswordField oldPass;
	private JPasswordField newPass1;
	private JPasswordField newPass2;
	private JLabel notEqualLabel;
	
	
	
	public passwordApplyButtonListener(changePasswordFrame passFrame,JPasswordField oldPass, JPasswordField newPass1, JPasswordField newPass2,JLabel notEqualLabel) {
		
		this.passFrame = passFrame;
		this.oldPass = oldPass;
		this.newPass1 = newPass1;
		this.newPass2 = newPass2;
		this.notEqualLabel = notEqualLabel;
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		char[] currentPassArr = oldPass.getPassword();
		char[] newPassArr1 = newPass1.getPassword();
		char[] newPassArr2 = newPass2.getPassword();
		
		if (!Arrays.equals(newPassArr1, newPassArr2)) {
			new wrongPasswordThread(notEqualLabel,"Passwords did not match!");
		}
		else if (Arrays.equals(newPassArr1, newPassArr2)) {
			notEqualLabel.setText("");
			int result = changePassword(arrayToPass(currentPassArr), arrayToPass(newPassArr1));
			if (result == 1) {
				JOptionPane.showMessageDialog(passFrame, "Password Changing Succesful!");
				passFrame.setVisible(false);
				passFrame.getCalFrame().getMenu().setEnabled(true);
				oldPass.setText("");
				newPass1.setText("");
				newPass2.setText("");
			}
			else if (result == 2) {
				new wrongPasswordThread(notEqualLabel,"Passwords Same!");
			}
			
			else {
				new wrongPasswordThread(notEqualLabel,"Current Password Wrong. Try Again!"); 
			}
		}
		
	}
	
	public  String arrayToPass(char[] array) {
		String pass = "";
		
		for (int i = 0; i < array.length; i++) {
			pass += array[i];
		}
		return pass;
	}
	public  int changePassword(String oldPass,String newPass) {
		String url = "jdbc:mysql:///*  YOUR IP  *//companyAcc";
		String uname = passFrame.getCalFrame().getLogin().getCurrentUser();
		String password = passFrame.getCalFrame().getLogin().getCurrentPass();
		String queryUpdatePass = "SET PASSWORD = '"+newPass+"';";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection(url,uname,password);
			Statement statementPass = con.createStatement();
			
			
				if (oldPass.equals(password) && oldPass.equals(newPass)) {
					return 2;
				}
				else if (oldPass.equals(password)) {
					statementPass.executeUpdate(queryUpdatePass);
					passFrame.getCalFrame().getLogin().setCurrentPass(newPass);
					return 1;
				}
				
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
