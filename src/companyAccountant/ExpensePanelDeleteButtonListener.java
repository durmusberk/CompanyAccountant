package companyAccountant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ExpensePanelDeleteButtonListener implements ActionListener {
	
	private JTable table;
	private JPanel expensePanel;
	private calculationFrame calFrame;
	
	public ExpensePanelDeleteButtonListener(JPanel expensePanel,calculationFrame calFrame,JTable table) {
		this.table = table;
		this.expensePanel = expensePanel;
		this.calFrame = calFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		if (table.getSelectedRowCount() == 1) {
			String expenseText = table.getValueAt(table.getSelectedRow(), 0).toString();
			String priceText = table.getValueAt(table.getSelectedRow(), 1).toString();
			String noteText;
			try {
				noteText = table.getValueAt(table.getSelectedRow(), 2).toString();
			} catch (Exception e2) {
				noteText = null;
			}
			deleteRowFromDb(expenseText, priceText, noteText);
			model.removeRow(table.getSelectedRow());
			calFrame.updateRemainingSalary();
		}else {
			if (table.getRowCount() == 0) {
				JOptionPane.showMessageDialog(expensePanel, "Tablo boş...");
			}
			else {
				JOptionPane.showMessageDialog(expensePanel, "Bir satır seçin...");
			}
		}
		
	}
	public void deleteRowFromDb(String expenseText, String priceText, String noteText) {
		String uname = calFrame.getLogin().getCurrentUser();
		String upassword = calFrame.getLogin().getCurrentPass();
		String url = "jdbc:mysql:///*  YOUR IP  *//db_"+uname;
		String queryUpdate = "delete from expenseMonth" + calFrame.getLogin().getSelectedMonth() + " where expenseName = '"+expenseText+"' AND expensePrice = '" +priceText +"' AND expenseNote = '"+noteText+"'";
		 
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
