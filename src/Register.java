import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//import javax.swing.JOptionPane;

public class Register {

	private JFrame frmRegister;
	/*private*/ JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void mainRegister() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frmRegister.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	Register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	void initialize() {
		frmRegister = new JFrame();
		frmRegister.setResizable(false);
		frmRegister.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				AsterX.frame.setVisible(true);
			}
		});
		frmRegister.setTitle("Register");
		//frmRegister.pack();
		frmRegister.setBounds(100, 100, 390, 300);
		frmRegister.setLocationRelativeTo(null);
		/*Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width = screenSize.width;
		frmRegister.setSize(width/2, height/2);*/
		//frmRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmRegister.getContentPane().setLayout(springLayout);
		
		JButton btnBack = new JButton("Back");
		springLayout.putConstraint(SpringLayout.EAST, btnBack, 98, SpringLayout.WEST, frmRegister.getContentPane());
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AsterX.frame.setVisible(true);
				frmRegister.dispose();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnBack, 21, SpringLayout.NORTH, frmRegister.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnBack, 27, SpringLayout.WEST, frmRegister.getContentPane());
		frmRegister.getContentPane().add(btnBack);
		
		JLabel lblUsername = new JLabel("Username");
		springLayout.putConstraint(SpringLayout.EAST, lblUsername, -226, SpringLayout.EAST, frmRegister.getContentPane());
		frmRegister.getContentPane().add(lblUsername);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 62, SpringLayout.NORTH, frmRegister.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, textField, 21, SpringLayout.EAST, lblUsername);
		springLayout.putConstraint(SpringLayout.EAST, textField, -98, SpringLayout.EAST, frmRegister.getContentPane());
		frmRegister.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		springLayout.putConstraint(SpringLayout.NORTH, lblPassword, 111, SpringLayout.NORTH, frmRegister.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblPassword, -226, SpringLayout.EAST, frmRegister.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblUsername, -29, SpringLayout.NORTH, lblPassword);
		frmRegister.getContentPane().add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		springLayout.putConstraint(SpringLayout.NORTH, lblConfirmPassword, 28, SpringLayout.SOUTH, lblPassword);
		springLayout.putConstraint(SpringLayout.EAST, lblConfirmPassword, 0, SpringLayout.EAST, lblUsername);
		frmRegister.getContentPane().add(lblConfirmPassword);
		
		passwordField = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, passwordField, 23, SpringLayout.SOUTH, textField);
		springLayout.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.EAST, passwordField, 0, SpringLayout.EAST, textField);
		frmRegister.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		springLayout.putConstraint(SpringLayout.NORTH, passwordField_1, 22, SpringLayout.SOUTH, passwordField);
		springLayout.putConstraint(SpringLayout.WEST, passwordField_1, 21, SpringLayout.EAST, lblConfirmPassword);
		springLayout.putConstraint(SpringLayout.EAST, passwordField_1, 0, SpringLayout.EAST, textField);
		frmRegister.getContentPane().add(passwordField_1);
		
		JButton btnCreateAccount = new JButton("Create User");
		springLayout.putConstraint(SpringLayout.NORTH, btnCreateAccount, 23, SpringLayout.SOUTH, passwordField_1);
		springLayout.putConstraint(SpringLayout.WEST, btnCreateAccount, 0, SpringLayout.WEST, textField);
		springLayout.putConstraint(SpringLayout.SOUTH, btnCreateAccount, -57, SpringLayout.SOUTH, frmRegister.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnCreateAccount, 0, SpringLayout.EAST, textField);
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createUserClicked();
			}
		});
		frmRegister.getContentPane().add(btnCreateAccount);
	}
	void createUserClicked()
	{
		String username = new String(textField.getText());
		if(checkFormDetails(username))
		checkUser(username);
	}
	boolean checkFormDetails(String uname)
	{
		String password = new String(passwordField.getPassword());
		String confirmPassword = new String(passwordField_1.getPassword());
		
		if(uname.isEmpty())
		{
			JOptionPane passwordNotMatchFrame = new JOptionPane();
			JOptionPane.showMessageDialog(passwordNotMatchFrame, "Username cannot not be empty!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if(uname.length()<5)
		{
			JOptionPane passwordNotMatchFrame = new JOptionPane();
			JOptionPane.showMessageDialog(passwordNotMatchFrame, "Username must be more than 4 characters!", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else if(password.isEmpty())
		{
			JOptionPane passwordNotMatchFrame = new JOptionPane();
			JOptionPane.showMessageDialog(passwordNotMatchFrame, "Password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if(!password.equals(confirmPassword))
		{
			JOptionPane passwordNotMatchFrame = new JOptionPane();
			JOptionPane.showMessageDialog(passwordNotMatchFrame, "Passwords didn't match!", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}
	void checkUser(String uname)
	{
		Connection con;
		Statement st;
		ResultSet rs;
		String username;
		boolean exists = false;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:system/manager@localhost","system","manager");
			st = con.createStatement();
			rs = st.executeQuery("select username from dba_users");
			while(rs.next())
			{
				//name= rs.getString("sname");
				username = rs.getString("username");
				if(uname.equalsIgnoreCase(username))
				{
					exists = true;
				}
			}
			if(exists)
			{
				JOptionPane passwordNotMatchFrame = new JOptionPane();
				JOptionPane.showMessageDialog(passwordNotMatchFrame, "User already exists!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				con.close();
				st.close();
				rs.close();
				//CreateUser createUser = new CreateUser();
				CreateUser.uname = uname;
				CreateUser.password = new String(passwordField.getPassword());
				/*CreateUser createUser = */CreateUser.mainCreateUser();//CreateUser();
				JOptionPane passwordNotMatchFrame = new JOptionPane();
				JOptionPane.showMessageDialog(passwordNotMatchFrame, "User Created!", "Done", JOptionPane.INFORMATION_MESSAGE);
				AsterX.frame.setVisible(true);
				frmRegister.dispose();
				//DisplayTable.uname = uname;
				//DisplayTable.password = new String(passwordField.getPassword());
				//DisplayTable.mainUser();
			}
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
			JOptionPane jOptionPane = new JOptionPane();
			JOptionPane.showMessageDialog(jOptionPane, "Oops! Something went wrong!", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			JOptionPane jOptionPane = new JOptionPane();
			JOptionPane.showMessageDialog(jOptionPane, "Oops! Something went wrong!", "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
}
