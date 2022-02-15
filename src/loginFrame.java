import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class loginFrame extends JFrame{
	
	private String currentUser;
	private String currentPass;
	private String[] profileSettings;
	private JLabel wrongPassword;
	private int selectedMonth;
	private Double[][] jobTable; 
	private Double[]seniorityTable;
	private Double[] agiTable;
	private Double seniorityPrice;
	private Double jobPrice;
	private Double fieldCompPercentage;
	
	public loginFrame() {
		
		this.setTitle("Login");
		this.setPreferredSize(new Dimension(400,500));
		this.setResizable(false);
		this.setLayout(null);
		
		JLabel text = new JLabel();
		text.setText("Login");
		
		
		JLabel text2 = new JLabel();
		text2.setText("Welcome to the CompanyAccountant");
		
		text.setFont(new Font("Arial", Font.PLAIN, 40));
		text2.setFont(new Font("Arial", Font.PLAIN, 13));
		this.add(text);
		this.add(text2);
		text.setBounds(90,80,150,50);
		text2.setBounds(90,120,250,50);
		
		
		JTextField userField = new JTextField();
		JPasswordField passField = new JPasswordField();
		
		
		userField.setText("Username...");
		passField.setText("**********");
		
		passField.setEchoChar('*');
		
		userField.setForeground(Color.GRAY);
		passField.setForeground(Color.GRAY);
		
	    userField.addFocusListener(new FocusListener(){
	        @Override
	        public void focusGained(FocusEvent e){
	        	if (userField.getText().equals("Username...")) {
	        		userField.setText("");
		        	userField.setForeground(Color.BLACK);
				}
	        }

			@Override
			public void focusLost(FocusEvent e) {
				if (userField.getText().equals("")) {
					userField.setText("Username...");
					userField.setForeground(Color.GRAY);
				}
				
			}
	    });
	    
	    passField.addFocusListener(new FocusListener(){
	        @Override
	        public void focusGained(FocusEvent e){
	        	if (arrayToPass(passField.getPassword()).equals("**********")) {
	        		passField.setText("");
		        	passField.setForeground(Color.BLACK);
				}
	        	
	        }
	        
			@Override
			public void focusLost(FocusEvent e) {
				if (passField.getPassword().length == 0) {
					passField.setText("**********");
					passField.setForeground(Color.GRAY);
				}
				
			}
	    });
		
		userField.setBounds(90,200,200,25);
		passField.setBounds(90,250,200,25);
		
		this.add(userField);
		this.add(passField);
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(90,300,200,25);
		this.getRootPane().setDefaultButton(loginButton);
		
		this.add(loginButton);
		
		JButton showPass = new JButton();
		showPass.setBounds(300,250,22,22);
		
		wrongPassword = new JLabel();
		wrongPassword.setBounds(90,350,300,30);
		wrongPassword.setForeground(Color.RED);
		this.add(wrongPassword);
		
		
		
		
		showPass.addMouseListener(new MouseAdapter() {
				@SuppressWarnings("unused")
				int counter = 0;
				boolean mousePressed;
		        public void mousePressed(MouseEvent e) {
		            mousePressed = true;
		            new Thread() {
		                public void run() {
		                    while (mousePressed) {
		                        counter += 1;
		                        passField.setEchoChar((char)0);
		                    }
		                }

		            }.start();
		        }

		        public void mouseReleased(MouseEvent e) {
		            mousePressed = false;
		            passField.setEchoChar('*');
		        }

		    });
		
		
		this.add(showPass);
		
		
		
		
		
		
		loginButton.addActionListener(new loginButtonListener(this, userField, passField));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack ();
		this.setLocationRelativeTo ( null );
		this.setVisible ( true );
		
		
	}
	
	public void setPrices() {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
		int currentDate = Calendar.getInstance().get(Calendar.DATE);
		int userYear = Integer.parseInt(profileSettings[2].substring(0,4));
		int userMonth = Integer.parseInt(profileSettings[2].substring(5,7));
		int userMonthTemp1 =Integer.parseInt(profileSettings[2].substring(5,7));
		int userMonthTemp2 =Integer.parseInt(profileSettings[2].substring(5,7));
		int lastDate = 30;
		
        
        
        if (userMonth <= 3) {
			userMonthTemp1 = 3;
		}
        else if (userMonth <= 6) {
			userMonthTemp1 = 6;
		}
        else if (userMonth <= 9) {
			userMonthTemp1 = 9;
		}
        else if (userMonth <= 12) {
			userMonthTemp1 = 12;
		}
        
        if (userMonth <= 11) {
			userMonthTemp2 = 11;
		}
        
        
        int workingTimeDecember = calculateAge(LocalDate.of(userYear, userMonthTemp2, lastDate), LocalDate.of(currentYear, currentMonth, currentDate));
        
        int workingTime = calculateAge(LocalDate.of(userYear, userMonthTemp1, lastDate), LocalDate.of(currentYear, currentMonth, currentDate))-1;
        
        
        int graduation = Integer.parseInt(profileSettings[1])-1;
        
        int jobGroup = Integer.parseInt(profileSettings[0])-1;
        
        if (workingTime < 0) {
			workingTime = 0;
		}
        if (workingTimeDecember<0) {
			workingTimeDecember = 0;
		}
        
        int jobRow;
        if (workingTime % 2 == 1) {
        	jobRow = (workingTime - 1)/2;
		}
        else {
        	jobRow = (workingTime)/2;
		}
        
        int graduationPoint;
        
        if (graduation == 0) {
        	graduationPoint = 1;
		}
        else {
        	graduationPoint = 2 * graduation;
		}
        
        
        
        jobPrice = jobTable[jobGroup][jobRow];
        seniorityPrice = seniorityTable[graduationPoint+workingTimeDecember-1];
        
        
        
        
        
	}
	
	public  int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
	private void pullAgiTable(Connection con) throws SQLException{
		
		String queryAgi = "select price from agiTable";
		Statement statementSeniority=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);  
		ResultSet resultAgi = statementSeniority.executeQuery(queryAgi);
		
		agiTable = new Double[11];
		while (resultAgi.next()) {
			for (int i = 0; i <11; i++) {
				resultAgi.absolute(i+1);
				agiTable[i] = resultAgi.getDouble(1);
			}
		}
	}
	
	
	private void pullSeniorityTable(Connection con) throws SQLException{
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
		String table;
		if (currentMonth < 7) {
			table = "seniorityTable1";
		}else {
			table = "seniorityTable2";
		}
		String querySeniority = "select kidemUcret from "+table;
		Statement statementSeniority=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);  
		ResultSet resultSeniority = statementSeniority.executeQuery(querySeniority);
		
		seniorityTable = new Double[35];
		while (resultSeniority.next()) {
			for (int i = 0; i <35; i++) {
				resultSeniority.absolute(i+1);
				seniorityTable[i] = resultSeniority.getDouble(1);
			}
		}
	}
	
	private void pullProfileSettings(Connection con) throws SQLException {
		
		String queryProfile = "select * from user_"+ currentUser;
		
		
		Statement statementProfile = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
		
		ResultSet resultProfile = statementProfile.executeQuery(queryProfile);
		
		
		
		profileSettings = new String[15];
		
		
			resultProfile.absolute(selectedMonth);
			
			for (int i = 2; i <= 16 ; i++) {
				profileSettings[i-2] = resultProfile.getString(i);
			
		}
			
			setFieldCompPercentage(Integer.parseInt(profileSettings[6]));
			
			
		
	}
	
	private void pullJobTable(Connection con) throws SQLException {
		
		String queryJob ="select * from jobTable"+selectedMonth;
		
		Statement statementJob = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
		
		ResultSet resultjob = statementJob.executeQuery(queryJob);
		
		jobTable = new Double[21][13];
		
		while (resultjob.next()) {
			for (int j = 0; j < 13; j++) {
				resultjob.absolute(j+1);
				for (int i = 0; i < 21; i++) {
					jobTable[i][j]=resultjob.getDouble(i+2);
				}
			}
		}
	}
	
	public  boolean loginChecker(String usernameCheck, String passwordCheck) {
		String url = "jdbc:mysql:// /*Your Ip*/    /companyAcc";
		String uname = usernameCheck;
		String password = passwordCheck;
		String queryProfile = "select * from user_"+ usernameCheck;		 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		}
		try {
			Connection con = DriverManager.getConnection(url,uname,password);
			
			
			Statement statementMonth = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE); 
			ResultSet resultMonth = statementMonth.executeQuery(queryProfile);
			
			resultMonth.absolute(13);
			
			selectedMonth = Integer.parseInt(resultMonth.getString(2));
			
			
			System.out.println("connected");
			
			currentUser = usernameCheck;
			currentPass = passwordCheck;
			pullProfileSettings(con);
			pullSeniorityTable(con);
			pullJobTable(con);
			pullAgiTable(con);
			setPrices();
			
			
			return true;
			
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
		}
		return false;
		
	}
	public  String arrayToPass(char[] array) {
		String pass = "";
		
		for (int i = 0; i < array.length; i++) {
			pass += array[i];
		}
		return pass;
	}
	

	public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}

	public String[] getProfileSettings() {
		return profileSettings;
	}

	public void setProfileSettings(String[] profileSettings) {
		this.profileSettings = profileSettings;
	}

	public JLabel getWrongPassword() {
		return wrongPassword;
	}

	public int getSelectedMonth() {
		return selectedMonth;
	}

	public void setSelectedMonth(int selectedMonth) {
		this.selectedMonth = selectedMonth;
	}

	public Double[] getSeniorityTable() {
		return seniorityTable;
	}

	public void setSeniorityTable(Double[] seniorityTable) {
		this.seniorityTable = seniorityTable;
	}
	public Double[][] getJobTable() {
		return jobTable;
	}
	public void setJobTable(Double[][] jobTable) {
		this.jobTable = jobTable;
	}
	public Double getSeniorityPrice() {
		return seniorityPrice;
	}
	public void setSeniorityPrice(Double seniorityPrice) {
		this.seniorityPrice = seniorityPrice;
	}
	public Double getJobPrice() {
		return jobPrice;
	}
	public void setJobPrice(Double jobPrice) {
		this.jobPrice = jobPrice;
	}

	public String getCurrentPass() {
		return currentPass;
	}

	public void setCurrentPass(String currentPass) {
		this.currentPass = currentPass;
	}

	public Double getFieldCompPercentage() {
		return fieldCompPercentage;
	}

	public void setFieldCompPercentage(int a) {
		
		switch (a) {
		case 0:
			fieldCompPercentage = 0.06;
			break;
		case 1:
			fieldCompPercentage = 0.09;
			break;
		case 2:
			fieldCompPercentage = 0.22;
			break;
		}
	}

	public Double[] getAgiTable() {
		return agiTable;
	}
	
	
	
	

}
