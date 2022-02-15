package companyAccountant;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class calculationFrame extends JFrame {
	
	private loginFrame login;
	private JMenu menu;
	private boolean profileScreenOpened = false;
	private boolean changePasswordScreenOpened = false;
	private JTabbedPane tp;
	private ExpensePanel panel2;
	private DButton remainingSalary;
	
	public calculationFrame(loginFrame login) {
		
		this.login = login;
		
		this.setTitle("Calculation");
		this.setLayout(null);
		
		JMenuBar mb = new JMenuBar();  
		
		menu = new JMenu("Settings");
		
		JMenuItem i1 = new JMenuItem("Profile Settings");
		JMenuItem i2 = new JMenuItem("Change Password");
		JMenuItem i3 = new JMenuItem("Information");
		JMenuItem i4 = new JMenuItem("Logout");
		JMenuItem i5 = new JMenuItem("Exit");
		
		JSeparator sp = new JSeparator();
		
		menu.add(i1);
		menu.add(i2);
		menu.add(i3);
		menu.add(i4);
		menu.add(sp);
		menu.add(i5);
		
		
		
		i2.addActionListener(new changePasswordListener(this));
		i4.addActionListener(new logoutActionListener(this, login));
		i5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1,1));
		this.setContentPane(mainPanel);
		
		
	 
	    String months[]={"Ocak","Şubat","Mart","Nisan","Mayıs","Haziran","Temmuz","Ağustos","Eylül","Ekim","Kasım","Aralık"}; 
	   
	    
	    tp=new JTabbedPane();
	    
	    JPanel p1=new JPanel();  
	    JPanel p2=new JPanel();  
	    p1.setLayout(new BorderLayout());
	    
	    
	    
	    
	    tp.add(months[login.getSelectedMonth()-1],p1);
	    tp.add("Giderler",p2);
	    
	    tp.setBounds(10,0, 500,500);
	    tp.setLocation(0, 0);
	    this.add(tp);  
	    
	    
	    JPanel panel1 = new JPanel();
	    
	    panel2 =  new ExpensePanel(this);
	    
	   
	    
	    
	    
	    panel1.setLayout(new GridBagLayout());
	    GridBagConstraints gbcPanel1 = new GridBagConstraints();
	    gbcPanel1.fill = GridBagConstraints.HORIZONTAL;
	    DButton panel1Title = new DButton("Mesai Kazançları",153,204,255,true);
	    gbcPanel1.gridwidth = 4;
	    gbcPanel1.gridx = 0;
	    gbcPanel1.gridy = 0;
	    panel1.add(panel1Title,gbcPanel1);
	    gbcPanel1.gridwidth = 1;
	    
	    
	    JButton[] buttonArray1 = new JButton[16];
	    int arrayCounter1 = 0;
	    
	    for (int i = 0; i < 4; i++) {
	    	for (int j = 0; j < 4; j++) {
				DButton button = new DButton();
				gbcPanel1.gridx = j; 
				gbcPanel1.gridy = i+1; 
				panel1.add(button,gbcPanel1);
				buttonArray1[arrayCounter1] = button;
				arrayCounter1 += 1;
			}
		}
	    
	    DButton sum = new DButton("        Toplam");
	    gbcPanel1.gridwidth = 3;
	    gbcPanel1.gridx = 0;
	    gbcPanel1.gridy = 5;
	    panel1.add(sum,gbcPanel1);
	    
	    DButton sumValue = new DButton();
	    gbcPanel1.gridwidth = 1;
	    gbcPanel1.gridx = 3;
	    gbcPanel1.gridy = 5;
	    panel1.add(sumValue,gbcPanel1);
	    
	    sum.setForeground(new Color(0,255,17));
	    sumValue.setForeground(new Color(0,255,17));
	    sum.setOpaque(true);sumValue.setOpaque(true);
	    sum.setBackground(new Color(160,160,160));sumValue.setBackground(new Color(160,160,160));
    	sum.setFont(new Font("Arial", Font.BOLD, 20));sumValue.setFont(new Font("Arial", Font.BOLD, 20));
	    
	    buttonArray1[0].setText("Mesai Türü");
	    buttonArray1[1].setText("Gün");
	    buttonArray1[2].setText("Saat");
	    buttonArray1[3].setText("Tutar");
	    buttonArray1[4].setText("Normal");
	    buttonArray1[8].setText("Fazla Çalışma");
	    buttonArray1[12].setText("Gece Vardiyası");
	    
	    DButton panel1Title1 = new DButton("Ek Ödemeler",153,204,255,true);
	    panel1Title1.setFont(new Font("Arial", Font.BOLD, 20));
	    gbcPanel1.gridwidth = 4;
	    gbcPanel1.gridx = 0;
	    gbcPanel1.gridy = 6;
	    panel1.add(panel1Title1,gbcPanel1);
	    gbcPanel1.gridwidth = 1;
	    
	    JButton[] buttonArray2 = new JButton[9];//text cells
	    
	    	for (int j = 0; j < 9; j++) {
				DButton button = new DButton();
				gbcPanel1.gridwidth = 2;
				gbcPanel1.gridx = 0; 
				gbcPanel1.gridy = j+7; 
				panel1.add(button,gbcPanel1);
				buttonArray2[j]=button;
			}
    	JButton[] buttonArray3 = new JButton[9];//value cells
    	
	    
    	for (int j = 0; j < 9; j++) {
			DButton button = new DButton();
			gbcPanel1.gridwidth = 2;
			gbcPanel1.gridx = 2; 
			gbcPanel1.gridy = j+7; 
			panel1.add(button,gbcPanel1);
			buttonArray3[j]=button;
		}
	    
    	buttonArray2[0].setText("Saha Tazminatı");
    	buttonArray2[1].setText("Vardiya Tazminatı %10");
    	buttonArray2[2].setText("İş Riski Tazminatı");
    	buttonArray2[3].setText("İkramiye ve Tediye Ödemesi");
    	buttonArray2[4].setText("AGİ");
    	buttonArray2[5].setText("Aile Yardımı");
    	buttonArray2[6].setText("Çocuk Yardımı");
    	buttonArray2[7].setText("Yakacak Yardımı");
    	buttonArray2[8].setText("Diğer Ödemeler");
    	
		
	    DButton sum1 = new DButton("Toplam");
	    gbcPanel1.gridwidth = 2;
	    gbcPanel1.gridx = 0;
	    gbcPanel1.gridy = 16;
	    panel1.add(sum1,gbcPanel1);
	    
	    DButton sumValue1 = new DButton();
	    gbcPanel1.gridwidth = 2;
	    gbcPanel1.gridx = 2;
	    gbcPanel1.gridy = 16;
	    panel1.add(sumValue1,gbcPanel1);
	    
	    sum1.setForeground(new Color(0,255,17));
	    sumValue1.setForeground(new Color(0,255,17));
	    sum1.setOpaque(true);sumValue1.setOpaque(true);
	    sum1.setBackground(new Color(160,160,160));sumValue1.setBackground(new Color(160,160,160));
    	sum1.setFont(new Font("Arial", Font.BOLD, 20));sumValue1.setFont(new Font("Arial", Font.BOLD, 20));
    	
	    
	    DButton panel1Title2 = new DButton("Kesintiler",153,204,255,true);
	    panel1Title2.setFont(new Font("Arial", Font.BOLD, 20));
	    gbcPanel1.gridwidth = 4;
	    gbcPanel1.gridx = 6;
	    gbcPanel1.gridy = 0;
	    gbcPanel1.insets = new Insets(0, 25, 0, 0);
	    panel1.add(panel1Title2,gbcPanel1);
	    gbcPanel1.gridwidth = 1;
	    gbcPanel1.insets = new Insets(0, 0, 0, 0);
	    
	    JButton[] buttonArray4 = new JButton[6];
	    int arrayCounter4 = 0;
	    
	    for (int i = 0; i < 3; i++) {
	    	for (int j = 0; j < 2; j++) {
	    		if (j==0) {
	    			gbcPanel1.insets = new Insets(0, 25, 0, 0);
				}
	    		DButton button = new DButton();
	    		gbcPanel1.gridwidth = 2;
	    		gbcPanel1.gridx = 5+2*j; 
	    		gbcPanel1.gridy = i+1; 
	    		panel1.add(button,gbcPanel1);
	    		buttonArray4[arrayCounter4]=button;
	    		arrayCounter4++;
	    		if (j==0) {
	    			gbcPanel1.insets = new Insets(0, 0, 0, 0);
				}
		}}
	    
    	buttonArray4[0].setText("Sendika Ücreti");
    	buttonArray4[2].setText("Diğer Kesintiler");
    	buttonArray4[4].setText("Toplam");
    	
    	buttonArray4[4].setForeground(Color.red);
    	buttonArray4[5].setForeground(Color.red);
    	buttonArray4[4].setOpaque(true);buttonArray4[5].setOpaque(true);
    	buttonArray4[4].setBackground(new Color(160,160,160));
    	buttonArray4[5].setBackground(new Color(160,160,160));
    	buttonArray4[5].setFont(new Font("Arial", Font.BOLD, 20));buttonArray4[4].setFont(new Font("Arial", Font.BOLD, 20));
    	
    	
    	
    	DButton panel1Title3 = new DButton("Maaş",153,204,255,true);
    	panel1Title3.setFont(new Font("Arial", Font.BOLD, 20));
    	gbcPanel1.insets = new Insets(0, 25, 0, 0);
	    gbcPanel1.gridwidth = 4;
	    gbcPanel1.gridx = 6;
	    gbcPanel1.gridy = 5;
	    
	    panel1.add(panel1Title3,gbcPanel1);
	    gbcPanel1.gridwidth = 1;
	    gbcPanel1.insets = new Insets(0, 0, 0, 0);
	    
	    JButton[] buttonArray5 = new JButton[4];
	    int arrayCounter5 = 0;
	    
	    for (int i = 0; i < 2; i++) {
	    	for (int j = 0; j < 2; j++) {
	    		
	    		if (j==0) {
	    			gbcPanel1.insets = new Insets(0, 25, 0, 0);
				}
	    		DButton button = new DButton();
	    		gbcPanel1.gridwidth = 2;
	    		gbcPanel1.gridx = 5+2*j; 
	    		gbcPanel1.gridy = i+6; 
	    		panel1.add(button,gbcPanel1);
	    		buttonArray5[arrayCounter5]=button;
	    		arrayCounter5++;
	    		
	    		if (j==0) {
	    			gbcPanel1.insets = new Insets(0, 0, 0, 0);
				}
		}}
	    
	    buttonArray5[0].setText("İş Gücü");
	    buttonArray5[2].setText("Kıdem");
	    
	    gbcPanel1.insets = new Insets(0, 25, 0, 0);
	    DButton grossText = new DButton("Net Maaş");
	    gbcPanel1.gridwidth = 2;
	    gbcPanel1.gridx = 5;
	    gbcPanel1.gridy = 8;
	    panel1.add(grossText,gbcPanel1);
	    
	    DButton netText = new DButton("Yatan Maaş");
	    gbcPanel1.gridwidth = 2;
	    gbcPanel1.gridx = 5;
	    gbcPanel1.gridy = 9;
	    panel1.add(netText,gbcPanel1);
	    
	    DButton remainingTest = new DButton("Giderler");
	    gbcPanel1.gridwidth = 2;
	    gbcPanel1.gridx = 5;
	    gbcPanel1.gridy = 10;
	    panel1.add(remainingTest,gbcPanel1);
	    gbcPanel1.insets = new Insets(0, 0, 0, 0);
	    
	    
	    DButton grossSalary = new DButton();
	    gbcPanel1.gridwidth = 2;
	    gbcPanel1.gridx = 7;
	    gbcPanel1.gridy = 8;
	    panel1.add(grossSalary,gbcPanel1);
	    
	    DButton netSalary = new DButton();
	    gbcPanel1.gridwidth = 2;
	    gbcPanel1.gridx = 7;
	    gbcPanel1.gridy = 9;
	    panel1.add(netSalary,gbcPanel1);
	    
	    remainingSalary = new DButton();
	    gbcPanel1.gridwidth = 2;
	    gbcPanel1.gridx = 7;
	    gbcPanel1.gridy = 10;
	    panel1.add(remainingSalary,gbcPanel1);

	    
	  
	    
	    JScrollPane scrollableArea = new JScrollPane(panel1);  
	    
        scrollableArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        scrollableArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    
        JScrollPane scrollableArea2 = new JScrollPane(panel2);
        scrollableArea2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        scrollableArea2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        
	    p1.setLayout(new GridLayout(1,1));
	    
	    p1.add(scrollableArea);
	    
	    p2.setLayout(new GridLayout(1,1));
	    
	    p2.add(scrollableArea2);
	    
	    
	    
	    updateFrame(buttonArray1, sumValue, buttonArray3, sumValue1, buttonArray4, buttonArray4[5], buttonArray5, grossSalary, netSalary,buttonArray2[0]);
		updateExpensePanel();
		updateRemainingSalary();
	    i1.addActionListener(new profileButtonListener(this,buttonArray1, sumValue, buttonArray3, sumValue1, buttonArray4, buttonArray4[5], buttonArray5, grossSalary, netSalary,buttonArray2[0]));
		
		mb.add(menu);
		this.setJMenuBar(mb);  
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.pack();
		this.setVisible ( true );
		 
		 
		
	}
	
	public void updateFrame(JButton[] buttonArray1,DButton sumValue, JButton[] buttonArray3,DButton sumValue1,JButton[] buttonArray4,
			JButton sumValue2,JButton[] buttonArray5,DButton grossSalary,DButton netSalary,JButton percentage) {
		DecimalFormat df = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.US));
		
		double jobPrice =getLogin().getJobPrice();
		double seniorityPrice =getLogin().getSeniorityPrice();
		double grossSalaryValue = jobPrice+seniorityPrice;
		String[] profileSetting = getLogin().getProfileSettings();
		
		
		percentage.setText("Saha Tazminatı %"+ df.format(getLogin().getFieldCompPercentage()*100));
		
		buttonArray5[1].setText(""+df.format(jobPrice));
		buttonArray5[3].setText(""+df.format(seniorityPrice));
		grossSalary.setText(""+(df.format(grossSalaryValue)));
		
		
		buttonArray1[5].setText("30");
		buttonArray1[6].setText("225");
		buttonArray1[7].setText(grossSalary.getText());
		
		buttonArray1[10].setText(df.format(Double.parseDouble(profileSetting[3])));
		buttonArray1[11].setText(""+df.format(grossSalaryValue/225*2*Double.parseDouble(profileSetting[3])));
		
		buttonArray1[14].setText(df.format(Double.parseDouble(profileSetting[4])));
		buttonArray1[15].setText(""+df.format(grossSalaryValue/225*0.5*Double.parseDouble(profileSetting[4])));
		
		double sum1= Double.parseDouble(buttonArray1[7].getText()) + Double.parseDouble(buttonArray1[11].getText()) + Double.parseDouble(buttonArray1[15].getText());
		
		sumValue.setText(""+df.format(sum1));
		
		buttonArray3[0].setText(""+df.format(jobPrice*getLogin().getFieldCompPercentage()));
		buttonArray3[1].setText(""+df.format(jobPrice*0.1));
		buttonArray3[2].setText(""+df.format(Double.parseDouble(profileSetting[9])));
		buttonArray3[3].setText(""+df.format(grossSalaryValue/30*Double.parseDouble(profileSetting[5])));
		buttonArray3[4].setText(""+df.format(getLogin().getAgiTable()[Integer.parseInt(profileSetting[7])]));
		buttonArray3[5].setText(""+df.format(Double.parseDouble(profileSetting[10])));
		buttonArray3[6].setText(""+df.format(Double.parseDouble(profileSetting[11])));
		buttonArray3[7].setText(""+df.format(Double.parseDouble(profileSetting[8])));
		buttonArray3[8].setText(""+df.format(Double.parseDouble(profileSetting[12])));
		
		double sum2 = 0;
		
		for (int i = 0; i < 9; i++) {
			sum2 += Double.parseDouble(buttonArray3[i].getText());
		}
		
		sumValue1.setText(""+df.format(sum2));
		
		buttonArray4[1].setText(""+df.format(Double.parseDouble(profileSetting[13])));
		buttonArray4[3].setText(""+df.format(Double.parseDouble(profileSetting[14])));
		
		double sum3 = Double.parseDouble(buttonArray4[1].getText()) + Double.parseDouble(buttonArray4[3].getText());
		
		sumValue2.setText(""+df.format(sum3));
		
		double netSalaryValue =  sum1 + sum2 - sum3;
		
		netSalary.setText(""+df.format(netSalaryValue));
		
	}
	
	public void updateExpensePanel() {
		for (int i = 0; i < panel2.getModel().getRowCount(); i++) {
			panel2.getModel().removeRow(i);
		}
		
		String uname = getLogin().getCurrentUser();
		String upassword = getLogin().getCurrentPass();
		String url = "jdbc:mysql:///*  YOUR IP  *//db_"+uname;
		String query = "select * from expenseMonth"+ getLogin().getSelectedMonth();
		String countTable = "select count(*) from expenseMonth"+ getLogin().getSelectedMonth();
		 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection(url,uname,upassword);
			Statement statement =  con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
			Statement statementCount = con.createStatement();
			ResultSet resultQuery = statement.executeQuery(query);
			
			ResultSet rs = statementCount.executeQuery(countTable);
			int rowCount = 0;
			while (rs.next()) {
				rowCount = Integer.parseInt(rs.getString(1));
			}
			while (resultQuery.next()) {
				for (int i = 1; i <= rowCount; i++) {
					resultQuery.absolute(i);
					panel2.getModel().addRow(new Object[]{resultQuery.getString(1), resultQuery.getString(2), resultQuery.getString(3)});
			}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void updateRemainingSalary() {
		DecimalFormat df = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.US));
		double sum = 0;
		for (int i = 0; i < panel2.getModel().getRowCount(); i++) {
			sum += Double.parseDouble(panel2.getModel().getValueAt(i, 1).toString());
		}
		
		remainingSalary.setText(""+df.format(sum));
		
		
	}
	
	public JMenu getMenu() {
		return menu;
	}


	public loginFrame getLogin() {
		return login;
	}

	public boolean isProfileScreenOpened() {
		return profileScreenOpened;
	}

	public void setProfileScreenOpened(boolean profileScreenOpened) {
		this.profileScreenOpened = profileScreenOpened;
	}

	public boolean isChangePasswordScreenOpened() {
		return changePasswordScreenOpened;
	}

	public void setChangePasswordScreenOpened(boolean changePasswordScreenOpened) {
		this.changePasswordScreenOpened = changePasswordScreenOpened;
	}
	public int getWidth1() {
		return this.getWidth();
	}
	public int getHeight1() {
		return this.getHeight();
	}

	public JTabbedPane getTp() {
		return tp;
	}
	
	


	
}
