import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;

@SuppressWarnings("rawtypes")
public class applyButtonListener implements ActionListener {
	
	private profileFrame proFrame;
	private JComboBox<String> cb1;
	private JComboBox<String> cb2;
	private JComboBox<String> cb3;
	private JSpinner enteringDate;
	private JSpinner extraShift;
	private JSpinner nightShift;
	private JSpinner bonus;
	private JComboBox jobLocationCb;
	private JComboBox agi;
	private JSpinner fuel;
	private JSpinner riskCompensation;
	private JSpinner familyAssistance;
	private JSpinner childAssistance;
	private JSpinner otherPayments;
	private JSpinner unionDues;
	private JSpinner otherCuts;
	private DButton sumValue;
	private DButton sumValue1;
	private JButton sumValue2;
	private DButton grossSalary;
	private DButton netSalary;
	private JButton percentage;
	private JButton[] buttonArray1;
	private JButton[] buttonArray3;
	private JButton[] buttonArray4;
	private JButton[] buttonArray5;
	private boolean closeOperation = false;
	
	
	public applyButtonListener(profileFrame proFrame,JComboBox<String> cb1,JComboBox<String> cb2,JComboBox<String> cb3,JSpinner enteringDate ,JSpinner extraShift, JSpinner nightShift,JSpinner bonus,JComboBox jobLocationCb,
			JComboBox agi,JSpinner fuel,JSpinner riskCompensation,JSpinner familyAssistance,JSpinner childAssistance,JSpinner otherPayments,JSpinner unionDues,JSpinner otherCuts,JButton[] buttonArray1,DButton sumValue, JButton[] buttonArray3,DButton sumValue1,JButton[] buttonArray4,
			JButton sumValue2,JButton[] buttonArray5,DButton grossSalary,DButton netSalary,JButton percentage) {
		this.proFrame = proFrame;
		this.cb1 = cb1;
		this.cb2 = cb2;
		this.cb3 = cb3;
		this.enteringDate = enteringDate;
		this.extraShift = extraShift;
		this.nightShift = nightShift;
		this.bonus = bonus;
		this.jobLocationCb = jobLocationCb;
		this.agi = agi;
		this.fuel = fuel;
		this.riskCompensation = riskCompensation;
		this.familyAssistance = familyAssistance;
		this.childAssistance = childAssistance;
		this.otherPayments = otherPayments;
		this.unionDues = unionDues;
		this.otherCuts = otherCuts;
		this.sumValue2 = sumValue2;
		this.sumValue = sumValue;
		this.sumValue1 = sumValue1;
		this.grossSalary = grossSalary;
		this.netSalary = netSalary;
		this.percentage = percentage;
		this.buttonArray1 = buttonArray1;
		this.buttonArray3 = buttonArray3;
		this.buttonArray4 = buttonArray4;
		this.buttonArray5 = buttonArray5;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (closeOperation) {
			proFrame.setVisible(false);
			proFrame.getCalFrame().getMenu().setEnabled(true);
		}
       
        String[] newProfileSettings = new String[15];
		newProfileSettings[0] = ""+(cb2.getSelectedIndex()+1);//job
		newProfileSettings[1] = ""+(cb3.getSelectedIndex()+1);//graduation
		
		//Get date in MM.yyyy formats
		Date date = (Date)enteringDate.getValue();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        String time = format.format(date);
        
		newProfileSettings[2] = time + "-01";
		
		newProfileSettings[3] = ""+ extraShift.getValue();
		newProfileSettings[4] = ""+ nightShift.getValue();
		newProfileSettings[5] = ""+ bonus.getValue();
		newProfileSettings[6] = ""+ jobLocationCb.getSelectedIndex();
		newProfileSettings[7] = ""+ agi.getSelectedIndex();
		newProfileSettings[8] = ""+ fuel.getValue();
		newProfileSettings[9] = ""+ riskCompensation.getValue();
		newProfileSettings[10] = ""+ familyAssistance.getValue();
		newProfileSettings[11] = ""+ childAssistance.getValue();
		newProfileSettings[12] = ""+ otherPayments.getValue();
		newProfileSettings[13] = ""+ unionDues.getValue();
		newProfileSettings[14] = ""+ otherCuts.getValue();
		
		if (Arrays.equals(proFrame.getCalFrame().getLogin().getProfileSettings(), newProfileSettings) && cb1.getSelectedIndex()+1 == proFrame.getCalFrame().getLogin().getSelectedMonth() ) {

		}
		else if (cb1.getSelectedIndex()+1 != proFrame.getCalFrame().getLogin().getSelectedMonth()) {
			setProfileSettings(newProfileSettings,cb1.getSelectedIndex()+1);
			proFrame.getCalFrame().getLogin().loginChecker(proFrame.getCalFrame().getLogin().getCurrentUser(), proFrame.getCalFrame().getLogin().getCurrentPass());
			proFrame.getCalFrame().updateFrame(buttonArray1, sumValue, buttonArray3, sumValue1, buttonArray4, sumValue2, buttonArray5, grossSalary, netSalary, percentage);
			
		}
		else {
			setProfileSettings(newProfileSettings,cb1.getSelectedIndex()+1);
			proFrame.getCalFrame().getLogin().loginChecker(proFrame.getCalFrame().getLogin().getCurrentUser(), proFrame.getCalFrame().getLogin().getCurrentPass());
			proFrame.getCalFrame().updateFrame(buttonArray1, sumValue, buttonArray3, sumValue1, buttonArray4, sumValue2, buttonArray5, grossSalary, netSalary,percentage);
			
		}
		
		
	}
	
	public  void setProfileSettings(String[] array,int selectedMonth) {
		String url = "jdbc:mysql://  /*Your Ip*/    /companyAcc";
		String uname = proFrame.getCalFrame().getLogin().getCurrentUser();
		String password = proFrame.getCalFrame().getLogin().getCurrentPass();
		
		
		String queryUpdate = "UPDATE user_" + uname + " SET "+
				" jobAndGroup='" + array[0]+"',"+
				" graduation='" + array[1]+"',"+
				" enteringDate='" + array[2]+"',"+
				" extraShift='" + array[3]+"',"+
				" nightShift='" + array[4]+"',"+
				" bonusAndDisbursement='" + array[5]+"',"+
				" jobLocation='" + array[6]+"',"+
				" agi='" + array[7]+"',"+
				" fuel='" + array[8]+"',"+
				" riskCompensation='" + array[9]+"',"+
				" familyAssistance='" + array[10]+"',"+
				" childAssistance='" + array[11]+"',"+
				" otherPayments='" + array[12]+"',"+
				" unionDues='" + array[13]+"',"+
				" otherCuts='" + array[14]+"' WHERE month = "+ selectedMonth + "";
		proFrame.getCalFrame().getLogin().setSelectedMonth(cb1.getSelectedIndex()+1);;
		String months[]={"Ocak","Şubat","Mart","Nisan","Mayıs","Haziran","Temmuz","Ağustos","Eylül","Ekim","Kasım","Aralık"}; 
		proFrame.getCalFrame().getTp().setTitleAt(0, months[cb1.getSelectedIndex()]);
			
			String queryMonth = "update user_"+uname+" set jobAndGroup = '" + proFrame.getCalFrame().getLogin().getSelectedMonth() +  "' where month LIKE 13";
		
		
		proFrame.getCalFrame().getLogin().setProfileSettings(array);
		proFrame.getCalFrame().getLogin().setFieldCompPercentage(Integer.parseInt(array[6]));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection(url,uname,password);
			Statement statement = con.createStatement();
			Statement statementMonth = con.createStatement();
			
			System.out.println("connected");
			
			statement.executeUpdate(queryUpdate);
			
			statementMonth.executeUpdate(queryMonth);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void setCloseOperation(boolean closeOperation) {
		this.closeOperation = closeOperation;
	}
	
	
}
