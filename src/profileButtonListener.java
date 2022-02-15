import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class profileButtonListener implements ActionListener {
	
	private calculationFrame calFrame;
	private profileFrame profileFrame;
	private DButton sumValue;
	private DButton sumValue1;
	private JButton sumValue2;
	private DButton grossSalary;
	private DButton netSalary;
	private JButton percentage;
	private JButton[] buttonArray1;
	private JButton[] buttonArray3;
	private JButton[] buttonArray4;
	private JButton[] buttonArray5;

	public profileButtonListener(calculationFrame calFrame,JButton[] buttonArray1,DButton sumValue, JButton[] buttonArray3,DButton sumValue1,JButton[] buttonArray4,
			JButton sumValue2,JButton[] buttonArray5,DButton grossSalary,DButton netSalary,JButton percentage) {
		this.calFrame = calFrame;
		this.sumValue2 = sumValue2;
		this.sumValue = sumValue;
		this.sumValue1 = sumValue1;
		this.grossSalary = grossSalary;
		this.netSalary = netSalary;
		this.percentage = percentage;
		this.buttonArray1 = buttonArray1;
		this.buttonArray3 = buttonArray3;
		this.buttonArray4 = buttonArray4;
		this.buttonArray5 = buttonArray5;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!calFrame.isProfileScreenOpened()) {
			profileFrame = new profileFrame(calFrame,buttonArray1, sumValue, buttonArray3, sumValue1, buttonArray4, sumValue2, buttonArray5, grossSalary, netSalary,percentage);
			calFrame.setProfileScreenOpened(true);
			
		}
		else {
			profileFrame.setVisible(true);
		}
		
		
		calFrame.getMenu().setEnabled(false);
		
		profileFrame.setAlwaysOnTop(true);
		
	}
	
}
