import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.sql.*;
import java.awt.event.ActionListener;
//import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Welcome {

	static JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	Connection con = null;
	/**
	 * Launch the application.
	 */
	public static void mainWelcome() {//String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Welcome window = 
					new Welcome();
					Welcome.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane jOptionPane = new JOptionPane();
					JOptionPane.showMessageDialog(jOptionPane, "Oops! Something went wrong!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Welcome() throws Exception{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws Exception{
		//String username,password;
		//frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		//frame.dispose();
		frame = new JFrame("LogIn");
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				AsterX.frame.setVisible(true);
			}
		});
		
		//frame.pack();
		frame.setBounds(100, 100, 350, 250);
		/*Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		frame.setSize(width/2, height/2);*/
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(159, 67, 89, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(64, 70, 86, 14);
		frame.getContentPane().add(lblUsername);
		
		JButton btnLogin = new JButton("LogIn");
		btnLogin.setBounds(159, 162, 89, 23);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//try{
					logInClicked();
				//}
				//catch(Exception e)
				//{
				//	e.printStackTrace();
				//}
			}
		});
		frame.getContentPane().add(btnLogin);
		
		JLabel lblMasterPassword = new JLabel("Master Password");
		lblMasterPassword.setBounds(35, 121, 112, 14);
		frame.getContentPane().add(lblMasterPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(159, 118, 89, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(27, 21, 71, 23);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AsterX.frame.setVisible(true);
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnBack);
	}
	
	public void logInClicked() //throws Exception
	{
		if((textField.getText().equals("system")) && (new String(passwordField.getPassword())).equals("manager"))
		{
			JOptionPane accessDeniedFrame = new JOptionPane();
			JOptionPane.showMessageDialog(accessDeniedFrame, "Access Denied!", "Error", JOptionPane.ERROR_MESSAGE);
		}		
		else if(logInValidate(textField.getText(), new String(passwordField.getPassword())))
		{
			//establishConnection();
			try
			{
				con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			HelloUser(textField.getText(), new String(passwordField.getPassword()));
		}
		else
		{
			JOptionPane passwordWrongFrame = new JOptionPane();
			JOptionPane.showMessageDialog(passwordWrongFrame, "Invalid Username or Password!", "Invalid Credentials", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public boolean logInValidate(String username, String password)// throws ClassNotFoundException,SQLException
	{
		
		//Statement st = null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//st = con.createStatement();
			//ResultSet rs= st.executeQuery("");
		}
		catch(ClassNotFoundException e)
		{
			//Do not write anything here!
		}
		try
		{
			con = DriverManager.getConnection("jdbc:oracle:thin:"+username+"/"+password+"@localhost",username,password);
		}
		catch(SQLException e)
		{
			//Do not write anything here!
		}
		if(con != null)
			return true;
		else
			return false;
	}
	public void HelloUser(String username, String password)
	{
		TableData.uname = username;
		TableData.password = password;
		
		TableData td = new TableData();
		
		try
		{
			con = DriverManager.getConnection("jdbc:oracle:thin:"+username+"/"+password+"@localhost",username,password);
			TableData.con = con;
			//con.close();
			//TableData.st.close();
			//TableData.rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane jOptionPane = new JOptionPane();
			JOptionPane.showMessageDialog(jOptionPane, "Oops! Something went wrong!", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		frame.dispose();
		td.compute();
		//DisplayTable.mainUser();
	}
}