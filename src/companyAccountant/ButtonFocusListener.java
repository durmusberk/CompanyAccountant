package companyAccountant;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;

public class ButtonFocusListener extends FocusAdapter {
	
	private String text;
	
	public ButtonFocusListener(String text) {
		this.text = text;
		
	}

	@Override
    public void focusGained(FocusEvent e){
		JTextField clickedField = (JTextField) e.getSource();
    	if (clickedField.getText().equals(text)) {
    		clickedField.setText("");
    		clickedField.setForeground(Color.BLACK);
		}
    }

	@Override
	public void focusLost(FocusEvent e) {
		JTextField clickedField = (JTextField) e.getSource();
		if (clickedField.getText().equals("")) {
			clickedField.setText(text);
			clickedField.setForeground(Color.GRAY);
		}
		
	}
}
