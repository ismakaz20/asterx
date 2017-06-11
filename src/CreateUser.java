import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JProgressBar;
import javax.swing.JOptionPane;

//import java.beans.PropertyChangeListener;
//import java.beans.PropertyChangeEvent;
public class CreateUser {

	//static JProgressBar progressBar = new JProgressBar();
	static JFrame frame;
	static String uname,password;

	/**
	 * Launch the application.
	 */
	public static void mainCreateUser() {//String uname, String pass) {
		//uname = uname;
		//this.password = pass;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//CreateUser window = 
					new CreateUser();
					CreateUser.frame.setVisible(false);
					//window.initialize();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	CreateUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Connection con;
		Statement st;
		frame = new JFrame();
		//frame.pack();
		frame.setBounds(100, 100, 450, 100);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Starting");
		lblNewLabel.setBounds(50, 15, 175, 20);
		frame.getContentPane().add(lblNewLabel);
		//frame.dispose();
		
		try
		{
			//System.out.println("username: "+uname);
			//System.out.println("password: "+password);
			//changeProgressBar(progressBar,lblNewLabel);
			//JProgressBar progressBar = new JProgressBar();
			
			//System.out.println("Before");
			//ProgressBarClass pbc = new ProgressBarClass();
			//pbc.run();
			//System.out.println("After");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:system/manager@localhost","system","manager");
			st = con.createStatement();
			lblNewLabel.setText("Connecting");
			
			String createUserCmd = new String("create user "+uname+" identified by "+password);
			st.execute(createUserCmd);
			lblNewLabel.setText("Creating");
			
			String grantCmd = new String("grant connect,resource to "+uname);
			st.execute(grantCmd);
			lblNewLabel.setText("Granting");
			try{
				con.close();
				st.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				JOptionPane jOptionPane = new JOptionPane();
				JOptionPane.showMessageDialog(jOptionPane, "Oops! Something went wrong!", "Error!", JOptionPane.ERROR_MESSAGE);
			}
			
			con = DriverManager.getConnection("jdbc:oracle:thin:"+uname+"/"+password+"@localhost",uname,password);
			st = con.createStatement();
			lblNewLabel.setText("Connecting...");
			
			//System.out.println("Connected to deepu");
			String createTableCmd = new String("create table result (WebsiteOrCard varchar2(50), UserName varchar2(50), Password varchar2(50), ExpiryDate varchar2(30), CVV varchar2(30), CardName varchar2(30), TypeOfCard varchar2(30))");
			st.executeUpdate(createTableCmd);
			
			st.close();
			con.close();
			//System.out.println("Created result");
			//frame.dispose();
			//frame.setVisible(false);
			//System.out.println("statement: "+st.SUCCESS_NO_INFO);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			JOptionPane jOptionPane = new JOptionPane();
			JOptionPane.showMessageDialog(jOptionPane, "Oops! Something went wrong!", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
			JOptionPane jOptionPane = new JOptionPane();
			JOptionPane.showMessageDialog(jOptionPane, "Oops! Something went wrong!", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		finally
		{
			frame.dispose();
		}
	}
}

/*class ProgressBarClass extends Thread
{
	
	synchronized public void run()
	{
		System.out.println("in thread");
		//JProgressBar progressBar = new JProgressBar();
		CreateUser.progressBar.setStringPainted(true);
		CreateUser.progressBar.setBounds(50, 36, 330, 15);
		CreateUser.frame.getContentPane().add(CreateUser.progressBar);
		CreateUser.progressBar.setValue(1);
		CreateUser.progressBar.setVisible(true);
		System.out.println("Before for loop");
		//progressBar.setString("Connecting");
		for(int i = 0; i <= 100; i+=10)
		{
			//progressBar.setString("Connecting"+i);
			CreateUser.progressBar.setValue(i);
			try {
			sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("At the end of thread");
		
	}
}*/