import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cancelButtonListener implements ActionListener {

	private changePasswordFrame passFrame;
	
	public cancelButtonListener(changePasswordFrame passFrame) {
		this.passFrame = passFrame;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		passFrame.dispose();
		passFrame.getCalFrame().getMenu().setEnabled(true);
		
		
	}

}
