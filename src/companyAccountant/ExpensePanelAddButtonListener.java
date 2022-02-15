package companyAccountant;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ExpensePanelAddButtonListener implements ActionListener {
	
	private JTable table;
	private JTextField expense;
	private JTextField price;
	private JTextField note;
	private JPanel expensePanel;
	private calculationFrame calFrame;
	private DefaultTableModel model;
	
	public ExpensePanelAddButtonListener(JPanel expensePanel,calculationFrame calFrame,JTable table,JTextField expense, JTextField price,JTextField note){
		this.table = table;
		this.expense = expense;
		this.price = price;
		this.note = note;
		this.expensePanel = expensePanel;
		this.calFrame = calFrame;
		model = (DefaultTableModel) table.getModel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (checkDuplicate()) {
			JOptionPane.showMessageDialog(expensePanel,"Aynı satırdan tabloda bulunuyor...");
		}else {
			DecimalFormat df = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.US));
			String expenseText = expense.getText();
			boolean isAccepted = false;
			double priceValue = 0;
			if (price.getText().equals("Tutar...")) {
				 priceValue =0;
			}
			else {
				try {
					priceValue =  Double.parseDouble(price.getText());;
				} catch (Exception e2) {
					isAccepted = true;
				}
				
			}
			
			String noteText = note.getText();
			if (noteText.equals("Not...")) {
				noteText = null;
			}
			
			if (expenseText.equals("Gider...") || price.getText().equals("Tutar...") || isAccepted) {
				
				JOptionPane.showMessageDialog(expensePanel,"Geçerli Bir Değer Girin...");
			}
			else {
				model.insertRow(0, new Object[]{expenseText, df.format(priceValue), noteText});
				addRowToDb(expenseText,df.format(priceValue),noteText);
				calFrame.updateRemainingSalary();
				expense.setText("Gider...");
				price.setText("Tutar...");
				note.setText("Not...");
				expense.setForeground(Color.gray);
				price.setForeground(Color.gray);
				note.setForeground(Color.gray);
			}
			
		}
	}
	
	public boolean checkDuplicate() {
		String tempExpense = "";
		String tempPrice = "";
		String tempNote = "";
		for (int i = 0; i < table.getRowCount() ; i++) {
			tempExpense = table.getValueAt(i, 0).toString();
			tempPrice = (table.getValueAt(i, 1).toString());
			try {
				tempNote = table.getValueAt(i, 2).toString();
			} catch (Exception e) {
				tempNote = null;
			}
			if (tempExpense.equals(expense.getText()) && tempPrice.equals(price.getText().trim()) && tempNote.equals(note.getText())) {
				return true;
			}
		}
		return false;
	}
	
	public void addRowToDb(String expenseText, String priceText, String noteText) {
		String uname = calFrame.getLogin().getCurrentUser();
		String upassword = calFrame.getLogin().getCurrentPass();
		String url = "jdbc:mysql:///*  YOUR IP  *//db_"+uname;
		String queryUpdate = "insert into expenseMonth" + calFrame.getLogin().getSelectedMonth() + " values('"+expenseText+"',"+priceText+",'"+noteText+"')";
		 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection(url,uname,upassword);
			Statement statement = con.createStatement();
			statement.executeUpdate(queryUpdate);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	

}
