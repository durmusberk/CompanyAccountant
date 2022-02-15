import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class changePasswordFrame extends JFrame {
	
	private calculationFrame calFrame;
	
	public changePasswordFrame(calculationFrame calFrame) {
		
		this.calFrame = calFrame;
		
		this.setTitle("Change Password");
		
		this.setPreferredSize(new Dimension(400,400));
		this.setLayout(null);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		
		
		JPasswordField oldPass = new JPasswordField();
		JPasswordField newPass1 = new JPasswordField();
		JPasswordField newPass2 = new JPasswordField();
		
		oldPass.setBounds(107,75,175,25);
		newPass1.setBounds(107,135,175,25);
		newPass2.setBounds(107,195,175,25);
		
		JLabel l1 = new JLabel("Current Password");
		JLabel l2 = new JLabel("New Password");
		JLabel l3 = new JLabel("New Password Again");
		
		l1.setBounds(110,55,200,20);
		l2.setBounds(110,115,200,20);
		l3.setBounds(110,175,200,20);
		
		JButton applyButton = new JButton("Apply");
		JButton cancelButton = new JButton("Cancel");
		
		applyButton.setBounds(115,245,75,25);
		cancelButton.setBounds(195,245,75,25);
		
		JLabel notEqualLabel = new JLabel();
		notEqualLabel.setForeground(Color.RED);
		notEqualLabel.setBounds(110,218,150,20);
		
		
		
		this.add(oldPass);
		this.add(newPass1);
		this.add(newPass2);
		
		this.add(l1);
		this.add(l2);
		this.add(l3);
		
		this.add(applyButton);
		this.add(cancelButton);
		
		this.add(notEqualLabel);
		
		this.addWindowListener(new WindowAdapter() {
			
			 @Override
			    public void windowClosing(WindowEvent e) {
			        calFrame.getMenu().setEnabled(true);
			    }
		});
		
		applyButton.addActionListener(new passwordApplyButtonListener(this, oldPass, newPass1, newPass2, notEqualLabel));
		
		cancelButton.addActionListener(new cancelButtonListener(this));
		
		this.getRootPane().setDefaultButton(applyButton);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack ();
		this.setLocationRelativeTo ( null );
		this.setVisible ( true );
	}
	public  String arrayToPass(char[] array) {
		String pass = "";
		
		for (int i = 0; i < array.length; i++) {
			pass += array[i];
		}
		return pass;
	}
	public calculationFrame getCalFrame() {
		return calFrame;
	}
	
}
