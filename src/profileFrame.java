import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

@SuppressWarnings({ "unused", "serial" })
public class profileFrame extends JFrame{
	
	private calculationFrame calFrame;
	private JButton sumValue2;
	private DButton sumValue;
	private DButton sumValue1;
	private DButton grossSalary;
	private DButton netSalary;
	private JButton[] buttonArray1;
	private JButton[] buttonArray3;
	private JButton[] buttonArray4;
	private JButton[] buttonArray5;
	
	
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public profileFrame(calculationFrame calFrame,JButton[] buttonArray1,DButton sumValue, JButton[] buttonArray3,DButton sumValue1,JButton[] buttonArray4,
			JButton sumValue2,JButton[] buttonArray5,DButton grossSalary,DButton netSalary,JButton percentage) {
		
		this.calFrame = calFrame;
		this.sumValue2 = sumValue2;
		this.sumValue = sumValue;
		this.sumValue1 = sumValue1;
		this.grossSalary = grossSalary;
		this.netSalary = netSalary;
		this.buttonArray1 = buttonArray1;
		this.buttonArray3 = buttonArray3;
		this.buttonArray4 = buttonArray4;
		this.buttonArray5 = buttonArray5;
		
		this.setTitle("Profile Settings");
		this.setPreferredSize(new Dimension(550,720));
		this.setLayout(null);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		
		this.addWindowListener(new WindowAdapter() {
			
			 @Override
			    public void windowClosing(WindowEvent e) {
			        calFrame.getMenu().setEnabled(true);
			    }
		});
		
		String months[]={"Ocak","Şubat","Mart","Nisan","Mayıs","Haziran","Temmuz","Ağustos","Eylül","Ekim","Kasım","Aralık"};    
		String jobs[] = {"H. Personeli G.1","H. Personeli G.2","H. Personeli G.3","İtfaiyeci G.1" , "İtfaiyeci G.2" ,"İtfaiyeci G.3" , "Teknisyen G.1" , 
				"Teknisyen G.2" , "Teknisyen G.3", "Operatör G.1","Operatör G.2","Operatör G.3","Memur G.1","Memur G.2","Memur G.3","Uzman G.1","Uzman G.2","Uzman G.3"
				,"Mühendis G.1","Mühendis G.2","Mühendis G.3"};
	    String graduations[] = {"İlkokul","Ortaokul","Lise","2 ve 3 Yıllık Y.O.","4 ve 5 Yıllık Y.O. ve Fakülte","Yüksek Lisans","Bilim Doktorası Yapan"};
		
	    String jobLocation[] = {"CMT","PT2, PT4, IPT1","PT1, PT3, IPT2"};
		
	    String agiTable[] = {"Bekâr", "Evli, Eşi Çalışmayan Çocuksuz","Evli, Eşi Çalışmayan, 1 Çocuklu","Evli, Eşi Çalışmayan, 2 Çocuklu","Evli, Eşi Çalışmayan, 3+ Çocuklu",
	    		"Evli, Eşi Çalışan, Çocuksuz","Evli, Eşi Çalışan, 1 Çocuklu","Evli, Eşi Çalışan, 2 Çocuklu","Evli, Eşi Çalışan, 3 Çocuklu"," Evli, Eşi Çalışan, 4 Çocuklu","Evli, Eşi Çalışan, 5+ Çocuklu"}; 
		
		
		
		
		JLabel l1 = new JLabel("Ay");
		JLabel l2 = new JLabel("Meslek ve Grup");
		JLabel l3 = new JLabel("Mezuniyet");
		JLabel l4 = new JLabel("İşe Giriş Tarihi");
		JLabel l5 = new JLabel("Fazla Mesai (Saat)");
		JLabel l6 = new JLabel("Gece Mesaisi (Saat)");
		JLabel l7 = new JLabel("İkramiye ve Tediye (Gün)");
		JLabel l8 = new JLabel("Çalışma Yeri");
		JLabel l9 = new JLabel("AGİ");
		JLabel l10 = new JLabel("Yakacak");
		JLabel l11 = new JLabel("Risk Tazminatı");
		JLabel l12 = new JLabel("Aile Yardımı");
		JLabel l13 = new JLabel("Çocuk Yardımı");
		JLabel l14 = new JLabel("Diğer Ödemeler");
		JLabel l15 = new JLabel("Sendika Ücreti");
		JLabel l16 = new JLabel("Diğer Kesintiler");
		
		
		l1.setBounds(47,25,150,20);
		l2.setBounds(47,100,150,20);
		l3.setBounds(47,175,150,20);
		l4.setBounds(47,250,150,20);
		l5.setBounds(47,325,150,20);
		l6.setBounds(47,400,150,20);
		l7.setBounds(47,475,150,20);
		l8.setBounds(47,550,150,20);
		
		l9.setBounds(297,25,150,20);
		l10.setBounds(297,100,150,20);
		l11.setBounds(297,175,150,20);
		l12.setBounds(297,250,150,20);
		l13.setBounds(297,325,150,20);
		l14.setBounds(297,400,150,20);
		l15.setBounds(297,475,150,20);
		l16.setBounds(297,550,150,20);
		
		
		JButton applyButton = new JButton("Apply");
		JButton revertButton = new JButton("Revert");
		JButton applyAndCloseButton = new JButton("Apply and Close");
		
		applyButton.setBounds(100,630,80,30);
		revertButton.setBounds(350,630,80,30);
		applyAndCloseButton.setBounds(200,630,130,30);
		
		
		
		
		SpinnerModel sm1,sm2,sm3,sm4,sm5,sm6,sm7,sm8,sm9,sm10,sm11,sm12;
		sm1 = new SpinnerNumberModel(0, 0, 999999, 1);
		sm2 = new SpinnerNumberModel(0, 0, 999999, 0.5);
		sm3 = new SpinnerNumberModel(0, 0, 999999, 1);
		sm5 = new SpinnerNumberModel(0, 0, 999999, 0.01);
		sm6 = new SpinnerNumberModel(0, 0, 999999, 0.01);
		sm7 = new SpinnerNumberModel(0, 0, 999999, 0.01);
		sm8 = new SpinnerNumberModel(0, 0, 999999, 0.01);
		sm9 = new SpinnerNumberModel(0, 0, 999999, 0.01);
		sm10 = new SpinnerNumberModel(0, 0, 999999, 0.01);
		sm12 = new SpinnerNumberModel(0, 0, 999999, 0.01);
		
		JComboBox monthCb = new JComboBox(months); 
		JComboBox jobsCb = new JComboBox(jobs);
		JComboBox graduationsCb = new JComboBox(graduations);
		JComboBox jobLocationCb = new JComboBox(jobLocation);
		JComboBox agiTableCb = new JComboBox(agiTable);
		
		SpinnerDateModel spinMod=new SpinnerDateModel();
	    JSpinner enteringDate=new JSpinner(spinMod);
		enteringDate.setEditor(new JSpinner.DateEditor(enteringDate,"MM.yyyy"));
		
		
		JSpinner extraShift,nightShift,bonus,fuel,riskCompensation,familyAssistance,childAssistance,otherPayments,unionDues,otherCuts;
		extraShift = new JSpinner(sm1);
		nightShift = new JSpinner(sm2);
		bonus = new JSpinner(sm3);
		fuel = new JSpinner(sm5);
		riskCompensation = new JSpinner(sm6);
		familyAssistance = new JSpinner(sm7);
		childAssistance = new JSpinner(sm8);
		otherPayments = new JSpinner(sm9);
		unionDues = new JSpinner(sm10);
		otherCuts = new JSpinner(sm12);
		
		
		monthCb.setBounds(47, 45, 190,30);
		jobsCb.setBounds(47, 120, 190,30);
		graduationsCb.setBounds(47, 195, 190,30);
		enteringDate.setBounds(47, 270, 190,30);
		extraShift.setBounds(47, 345, 190,30);
		nightShift.setBounds(47, 420, 190,30);
		bonus.setBounds(47, 495, 190,30);
		jobLocationCb.setBounds(47, 570, 190,30);
		
		agiTableCb.setBounds(295, 45, 190,30);
		fuel.setBounds(295, 120, 190,30);
		riskCompensation.setBounds(295, 195, 190,30);
		familyAssistance.setBounds(295, 270, 190,30);
		childAssistance.setBounds(295, 345, 190,30);
		otherPayments.setBounds(295, 420, 190,30);
		unionDues.setBounds(295, 495, 190,30);
		otherCuts.setBounds(295, 570, 190,30);
		
		
		
		
		monthCb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (calFrame.getLogin().getSelectedMonth() != monthCb.getSelectedIndex()+1) {
					updateFrame(monthCb, jobsCb,graduationsCb, enteringDate, extraShift, nightShift, bonus,jobLocationCb, agiTableCb, fuel, riskCompensation, familyAssistance, childAssistance, otherPayments, unionDues,otherCuts,updateSettings(monthCb.getSelectedIndex()+1),false);
				}
				
				
			}
		});
		
		
		updateFrame(monthCb, jobsCb,graduationsCb, enteringDate, extraShift, nightShift, bonus, jobLocationCb, agiTableCb, fuel, riskCompensation, familyAssistance, childAssistance, otherPayments, unionDues,otherCuts,calFrame.getLogin().getProfileSettings(),true);
		
		this.add(nightShift);
		this.add(extraShift);
		this.add(enteringDate);
		this.add(monthCb);
		this.add(jobsCb);
		this.add(graduationsCb);
		this.add(bonus);
		this.add(jobLocationCb);
		this.add(agiTableCb);
		this.add(fuel);
		this.add(riskCompensation);
		this.add(familyAssistance);
		this.add(childAssistance);
		this.add(otherPayments);
		this.add(unionDues);
		this.add(otherCuts);
		
		
		this.add(l1);
		this.add(l2);
		this.add(l3);
		this.add(l4);
		this.add(l5);
		this.add(l6);
		this.add(l7);
		this.add(l8);
		this.add(l9);
		this.add(l10);
		this.add(l11);
		this.add(l12);
		this.add(l13);
		this.add(l14);
		this.add(l15);
		this.add(l16);
		
		this.add(applyButton);
		this.add(revertButton);
		this.add(applyAndCloseButton);
		
		this.getRootPane().setDefaultButton(applyButton);
		
		
		
		applyButton.addActionListener(new applyButtonListener(this, monthCb, jobsCb,graduationsCb, enteringDate, extraShift, nightShift, bonus,jobLocationCb ,agiTableCb, fuel, riskCompensation, 
				familyAssistance, childAssistance, otherPayments, unionDues,otherCuts,buttonArray1, sumValue, buttonArray3, sumValue1, buttonArray4, sumValue2, buttonArray5, grossSalary, netSalary,percentage));
		
		applyButtonListener listener = new applyButtonListener(this, monthCb, jobsCb,graduationsCb, enteringDate, extraShift, nightShift, bonus,jobLocationCb ,agiTableCb, fuel, riskCompensation, 
				familyAssistance, childAssistance, otherPayments, unionDues,otherCuts,buttonArray1, sumValue, buttonArray3, sumValue1, buttonArray4, sumValue2, buttonArray5, grossSalary, netSalary,percentage);
		listener.setCloseOperation(true);
		
		applyAndCloseButton.addActionListener(listener);
		
		revertButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateFrame(monthCb, jobsCb,graduationsCb, enteringDate, extraShift, nightShift, bonus,jobLocationCb , agiTableCb, fuel, riskCompensation, familyAssistance, childAssistance, otherPayments, unionDues,otherCuts,calFrame.getLogin().getProfileSettings(),true);
				
			}
		});
		
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack ();
		this.setLocationRelativeTo ( null );
		this.setVisible ( true );
		
		
		
	}
	@SuppressWarnings("rawtypes")
	public void updateFrame(JComboBox<String> cb1,JComboBox<String> cb2,JComboBox<String> cb3,JSpinner enteringDate ,JSpinner extraShift, JSpinner nightShift,JSpinner bonus,JComboBox jobLocationCb,
			JComboBox agiTableCb,JSpinner fuel,JSpinner riskCompensation,JSpinner familyAssistance,JSpinner childAssistance,JSpinner otherPayments,JSpinner unionDues,JSpinner otherCuts,String[] profileSettings,boolean monthBoolean) {
		
		
		
		if (monthBoolean) {
			cb1.setSelectedIndex(calFrame.getLogin().getSelectedMonth() - 1);
		}
		cb2.setSelectedIndex(Integer.parseInt(profileSettings[0])-1);
		cb3.setSelectedIndex(Integer.parseInt(profileSettings[1])-1);
		
		int year = Integer.parseInt(profileSettings[2].substring(0,4));
		int month = Integer.parseInt(profileSettings[2].substring(5,7));
		
		Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.YEAR, year);
        Date date = calendar.getTime();
        enteringDate.setValue(date);;
        
        extraShift.setValue(Double.parseDouble(profileSettings[3]));
        nightShift.setValue(Double.parseDouble(profileSettings[4]));
        bonus.setValue(Double.parseDouble(profileSettings[5]));
        jobLocationCb.setSelectedIndex(Integer.parseInt(profileSettings[6]));
        agiTableCb.setSelectedIndex(Integer.parseInt(profileSettings[7]));
        fuel.setValue(Double.parseDouble(profileSettings[8]));
        riskCompensation.setValue(Double.parseDouble(profileSettings[9]));
        familyAssistance.setValue(Double.parseDouble(profileSettings[10]));
        childAssistance.setValue(Double.parseDouble(profileSettings[11]));
        otherPayments.setValue(Double.parseDouble(profileSettings[12]));
        unionDues.setValue(Double.parseDouble(profileSettings[13]));
        otherCuts.setValue(Double.parseDouble(profileSettings[14]));
        
        
	}
	public String[] updateSettings(int selectedMonth) {
		String url = "jdbc:mysql:// /*Your Ip*/    /companyAcc";
		String uname = calFrame.getLogin().getCurrentUser();
		String password = calFrame.getLogin().getCurrentPass();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection(url,uname,password);
			Statement statementProfile = con.createStatement();
			
			
			String queryProfile = "select * from user_"+ uname +" where month LIKE "+selectedMonth ;
			
			ResultSet resultProfile = statementProfile.executeQuery(queryProfile);
			System.out.println("connected");
			
			String[] profileSettings = new String[15];
				while (resultProfile.next()) {
					for (int i = 2; i <= 16 ; i++) {
						profileSettings[i-2] = resultProfile.getString(i);
					}
				}
			return profileSettings;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		

	}
	

	public calculationFrame getCalFrame() {
		return calFrame;
	}
	
	
}
