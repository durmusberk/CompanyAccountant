package companyAccountant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class changePasswordListener implements ActionListener {

	private calculationFrame calFrame;
	private changePasswordFrame passFrame;
	
	public changePasswordListener(calculationFrame calFrame) {
		this.calFrame = calFrame;
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!calFrame.isChangePasswordScreenOpened()) {
			passFrame = new changePasswordFrame(calFrame);
			calFrame.setChangePasswordScreenOpened(true);
		}
		else {
			passFrame.setVisible(true);
		}
		calFrame.getMenu().setEnabled(false);
		passFrame.setAlwaysOnTop(true);
		
	}

}
