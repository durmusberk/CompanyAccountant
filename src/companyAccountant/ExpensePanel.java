package companyAccountant;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")
public class ExpensePanel extends JPanel {
	
	private DefaultTableModel model;

	public ExpensePanel(calculationFrame calFrame) {
		
		this.setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridBagLayout());
		
		this.add(topPanel,BorderLayout.NORTH);
		
		
		Object[] columnNames = {"Gider","Tutar","Not"};
		
		
		model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
			       return false;
			    }
		};
		
		model.setColumnIdentifiers(columnNames);
		
		JButton addButton = new JButton("Ekle");
		JButton deleteButton = new JButton("Sil");
		
		JTextField expenseField = new JTextField("Gider...");
		expenseField.addFocusListener(new ButtonFocusListener("Gider..."));
		JTextField priceField = new JTextField("Tutar...");
		priceField.addFocusListener(new ButtonFocusListener("Tutar..."));
		JTextField noteField = new JTextField("Not...");
		noteField.addFocusListener(new ButtonFocusListener("Not..."));
		expenseField.setForeground(Color.GRAY);
		priceField.setForeground(Color.GRAY);
		noteField.setForeground(Color.GRAY);
		
		
		JScrollPane jsp = new JScrollPane();
		
		
		
		expenseField.setPreferredSize(new Dimension(130,30));
		priceField.setPreferredSize(new Dimension(130,30));
		noteField.setPreferredSize(new Dimension(130,30));
		addButton.setPreferredSize(new Dimension(90,30));
		deleteButton.setPreferredSize(new Dimension(90,30));
		
		JTable expenseTable = new JTable();
		expenseTable.setModel(model);
		expenseTable.setRowHeight(25);
		expenseTable.getTableHeader().setReorderingAllowed(false);
		
		expenseTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
		{
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		    {
		        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        c.setBackground(row % 2 == 0 ? new Color(154, 208, 236) : new Color(202, 240, 248));
		        return c;
		    }
		});
		
		jsp.setViewportView(expenseTable);
		
		this.add(jsp,BorderLayout.CENTER);
		
		addButton.addActionListener(new ExpensePanelAddButtonListener(this,calFrame,expenseTable, expenseField, priceField, noteField));
		deleteButton.addActionListener(new ExpensePanelDeleteButtonListener(this,calFrame, expenseTable));
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 0, 0, 10);
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
	  	gbc.gridx = 1;
		gbc.gridy = 0;
		topPanel.add(expenseField,gbc);
		gbc.insets = new Insets(10, 0, 0, 10);
		gbc.gridx = 2;
		gbc.gridy = 0;
		topPanel.add(priceField,gbc);
		gbc.insets = new Insets(10, 0, 0, 0);
		gbc.gridx = 3;
		gbc.gridy = 0;
		topPanel.add(noteField,gbc);
		gbc.insets = new Insets(10, 20, 10, 0);
		gbc.gridx = 1;
		gbc.gridy = 1;
		topPanel.add(addButton,gbc);
		gbc.insets = new Insets(10, 0, 10, 20);
		gbc.gridx = 3;
		gbc.gridy = 1;
		topPanel.add(deleteButton,gbc);
	
		
		
	}
	public DefaultTableModel getModel() {
		return model;
	}
	
	
	
	
	
}
