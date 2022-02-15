package companyAccountant;

import javax.swing.JLabel;

public class wrongPasswordThread extends Thread {
	
	private JLabel label;
	
	
	public wrongPasswordThread(JLabel label,String text) {
		this.label = label;
		label.setText(text);
		this.start();
	}
	
	public void run() {
		try {
			Thread.sleep(4000);
			label.setText("");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
