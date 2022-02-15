import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class DButton extends JButton {
	
	public DButton(String title) {
		this.setText(title);
	    this.setOpaque(false);
	    this.setContentAreaFilled(false);
	    this.setFocusable(false);
	    this.setFont(new Font("Arial", Font.PLAIN, 20));
	    this.setBorder(new LineBorder(Color.BLACK));
	    this.setPreferredSize(new Dimension(150,35));
	}
	public DButton() {
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setFont(new Font("Arial", Font.PLAIN, 20));
		this.setFocusable(false);
		this.setBorder(new LineBorder(Color.BLACK));
    	 this.setPreferredSize(new Dimension(150,35));
	}
	
	public DButton(String title,int r,int b,int g,boolean bold) {
		this.setContentAreaFilled(false);
		this.setText(title);
		this.setFocusable(false);
		this.setBorder(new LineBorder(Color.BLACK));
    	this.setPreferredSize(new Dimension(150,35));
    	if (bold) {
    		this.setFont(new Font("Arial", Font.BOLD, 20));
		}else {
			this.setFont(new Font("Arial", Font.PLAIN, 20));
		}
    	
    	this.setOpaque(true);
    	this.setBackground(new Color(r,g,b));
    	this.setForeground(Color.BLACK);
    	
	}
}
