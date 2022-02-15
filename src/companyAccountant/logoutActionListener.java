package companyAccountant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class logoutActionListener implements ActionListener {

	private calculationFrame calFrame;
	private loginFrame logFrame;
	
	public logoutActionListener(calculationFrame calFrame,loginFrame logFrame) {
		this.calFrame = calFrame;
		this.logFrame = logFrame;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		calFrame.setVisible(false);
		logFrame.setVisible(true);
		logFrame.setSelectedMonth(0);
		logFrame.setProfileSettings(null);
		logFrame.setCurrentUser(null);
		logFrame.setSeniorityPrice(null);
		logFrame.setJobPrice(null);
		logFrame.setCurrentPass(null);
		
	}

}
