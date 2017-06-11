import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

import java.util.Vector;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class DisplayTable {

	//private Connection con;// = TableData.con;
	//private Statement st; //= TableData.st;
	/*private*/ static JFrame frame;
	/*static*/ Vector<String> colHeads = new Vector<String>();
	//static Vector<Object> row = new Vector<Object>();
	static Vector<Vector<Object>> data = new  Vector<Vector<Object>>();
	//static String uname,password;
	//private JTable table;

	/**
	 * Launch the application.
	 */
	public static void mainDisplayTable() {//(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//DisplayTable window = 
					new DisplayTable();
					DisplayTable.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DisplayTable() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		//frame.setResizable(false);
		//frame.pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		frame.setSize(width/2, height/2);
		frame.setLocationRelativeTo(null);
		//frame.setBounds(100, 100, 630, 430);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//colHeads.add("Sno");
		colHeads.add("Website Or Card#");
		colHeads.add("User Name");
		colHeads.add("Password");
		colHeads.add("Expiry Date");
		colHeads.add("CVV");
		colHeads.add("Card Name");
		colHeads.add("Type Of Card");
		
		//@SuppressWarnings("serial")
		JTable table = new JTable(data, colHeads){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) { //int row, int column
		        return false;
		    }
		};
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				logOut();
			}
		});
		
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(20);
		//table.setEnabled(false);
		//table.setCellSelectionEnabled(true);
		//table.isCellEditable(0, 0);
		//table.getColumnModel().getColumn(0).setMaxWidth(16);
		//table.setColumnSelectionAllowed(true);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(table);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 0, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -122, SpringLayout.EAST, frame.getContentPane());
		//scrollPane.setEnabled(false);
		
		frame.getContentPane().add(scrollPane);
		
		springLayout.putConstraint(SpringLayout.SOUTH, table, -28, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, table, 419, SpringLayout.WEST, frame.getContentPane());
		
		JButton btnInsert = new JButton("Add Record");
		springLayout.putConstraint(SpringLayout.WEST, btnInsert, 11, SpringLayout.EAST, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, btnInsert, -10, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, btnInsert, 24, SpringLayout.NORTH, frame.getContentPane());
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddRecord.mainAddRecord();
			}
		});
		frame.getContentPane().add(btnInsert);
		
		JButton btnDelete = new JButton("Delete Record");
		springLayout.putConstraint(SpringLayout.NORTH, btnDelete, 42, SpringLayout.SOUTH, btnInsert);
		springLayout.putConstraint(SpringLayout.WEST, btnDelete, 0, SpringLayout.WEST, btnInsert);
		springLayout.putConstraint(SpringLayout.SOUTH, btnDelete, 65, SpringLayout.SOUTH, btnInsert);
		springLayout.putConstraint(SpringLayout.EAST, btnDelete, 0, SpringLayout.EAST, btnInsert);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow() == -1)
				{
					JOptionPane jOptionPane = new JOptionPane();
					JOptionPane.showMessageDialog(jOptionPane, "Select the record to be deleted!", "No row selected", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					int row = table.getSelectedRow();
					int noOfCols = table.getColumnCount();
					String[] rowDetails = new String[noOfCols];
					for(int i = 0; i < noOfCols; i++)
					{
						rowDetails[i]=table.getValueAt(row, i).toString();
						//System.out.println(rowDetails[i]);
					}
					/*String expiryDate = rowDetails[3];
					int indexOfSlash = expiryDate.indexOf('/');
					String month = expiryDate.substring(0, indexOfSlash);
					String year = expiryDate.substring(indexOfSlash+1, expiryDate.length());*/
					UpdateDataBase delete = new UpdateDataBase();
					delete.deleteFromDataBase(rowDetails);
					DisplayTable.frame.dispose();
					TableData td = new TableData();
					td.compute();
				}
			}
		});
		frame.getContentPane().add(btnDelete);
		
		JButton btnDeactivate = new JButton("Deactivate");
		btnDeactivate.setToolTipText("Deactivate your AsterX account");
		btnDeactivate.setForeground(Color.RED);
		springLayout.putConstraint(SpringLayout.WEST, btnDeactivate, 0, SpringLayout.WEST, btnInsert);
		springLayout.putConstraint(SpringLayout.EAST, btnDeactivate, 0, SpringLayout.EAST, btnInsert);
		btnDeactivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane confirmationPane = new JOptionPane();
				if(JOptionPane.showConfirmDialog(confirmationPane, "Are you sure you want to delete your AsterX account? This cannot be undone!", "Confirmation", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
				{
					try
					{
						TableData.con.close();
						TableData.st.close();
						TableData.rs.close();
					}
					catch(Exception excep)
					{
						excep.printStackTrace();
					}
					try
					{
						TableData.con = DriverManager.getConnection("jdbc:oracle:thin:system/manager@localhost","system","manager");
						TableData.st = TableData.con.createStatement();
						
						String deleteUserCmd = new String("drop user "+TableData.uname+" cascade");
						TableData.st.executeUpdate(deleteUserCmd);
						
						frame.dispose();
						AsterX.frame.setVisible(true);
						JOptionPane jOptionPane = new JOptionPane();
						JOptionPane.showMessageDialog(jOptionPane, "Your account has been deactivated!", "Account deleted", JOptionPane.INFORMATION_MESSAGE);
					}	
					catch(Exception e)
					{
						//e.printStackTrace();
						JOptionPane jOptionPane = new JOptionPane();
						JOptionPane.showMessageDialog(jOptionPane, "Could not deactivate your account! Try again!", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		frame.getContentPane().add(btnDeactivate);
		
		JButton btnLogout = new JButton("Logout");
		springLayout.putConstraint(SpringLayout.NORTH, btnDeactivate, 43, SpringLayout.SOUTH, btnLogout);
		springLayout.putConstraint(SpringLayout.WEST, btnLogout, 0, SpringLayout.WEST, btnInsert);
		springLayout.putConstraint(SpringLayout.EAST, btnLogout, 0, SpringLayout.EAST, btnInsert);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logOut();
			}
		});
		frame.getContentPane().add(btnLogout);
		
		JButton btnUpdateRecord = new JButton("Update Record");
		springLayout.putConstraint(SpringLayout.NORTH, btnLogout, 41, SpringLayout.SOUTH, btnUpdateRecord);
		springLayout.putConstraint(SpringLayout.NORTH, btnUpdateRecord, 40, SpringLayout.SOUTH, btnDelete);
		springLayout.putConstraint(SpringLayout.SOUTH, btnUpdateRecord, 63, SpringLayout.SOUTH, btnDelete);
		btnUpdateRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() == -1)
				{
					JOptionPane jOptionPane = new JOptionPane();
					JOptionPane.showMessageDialog(jOptionPane, "Select the record to be updated!", "No row selected", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					AddRecord.update = true;
					int row = table.getSelectedRow();
					int noOfCols = table.getColumnCount();
					String[] rowDetails = new String[noOfCols];
					for(int i = 0; i < noOfCols; i++)
					{
						rowDetails[i]=table.getValueAt(row, i).toString();
						//System.out.println(rowDetails[i]);
					}
					AddRecord.rowDetails = rowDetails.clone();
					AddRecord.mainAddRecord();
				}
			}
		});
		springLayout.putConstraint(SpringLayout.WEST, btnUpdateRecord, 0, SpringLayout.WEST, btnInsert);
		springLayout.putConstraint(SpringLayout.EAST, btnUpdateRecord, 0, SpringLayout.EAST, btnInsert);
		btnUpdateRecord.setFont(new Font("Tahoma", Font.PLAIN, 10));
		frame.getContentPane().add(btnUpdateRecord);
	}
	void logOut()
	{
		JOptionPane confirmationPane = new JOptionPane();
		if (JOptionPane.showConfirmDialog(confirmationPane, "Are you sure you want to logout?", "Confirmation", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
		{
			try
			{
				TableData.con.close();
				TableData.st.close();
				TableData.rs.close();
			}
			catch(Exception excep)
			{
				excep.printStackTrace();
			}
			frame.dispose();
			AsterX.frame.setVisible(true);
			if(Welcome.frame.isActive())
				Welcome.frame.dispose();
		}
	}
}
