import java.awt.Dimension;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.*;

import java.awt.Font;

import javax.swing.text.MaskFormatter;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.awt.event.ItemEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class AddRecord {

	private JFrame frmAddNewRecord;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	static boolean update = false;
	static String[] rowDetails;
	
	/**
	 * Launch the application.
	 */
	public static void mainAddRecord() {//String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddRecord window = new AddRecord();
					window.frmAddNewRecord.setVisible(true);
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
	public AddRecord() {
		try{
			initialize();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane jOptionPane = new JOptionPane();
			JOptionPane.showMessageDialog(jOptionPane, "Oops! Something went wrong!", "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ParseException 
	 */
	private void initialize() throws ParseException 
	{
		frmAddNewRecord = new JFrame();
		frmAddNewRecord.setTitle("Add New Record");
		frmAddNewRecord.setBounds(100, 100, 450, 300);
		frmAddNewRecord.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAddNewRecord.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				if(update == true)
					update = false;
				DisplayTable.frame.setEnabled(true);
				frmAddNewRecord.dispose();
			}
		});
		frmAddNewRecord.setResizable(false);
		DisplayTable.frame.setEnabled(false);
		//frmAddNewRecord.setAlwaysOnTop(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = screenSize.width;
		int height = screenSize.height;
		frmAddNewRecord.setSize(width/3, height/3);
		frmAddNewRecord.setLocationRelativeTo(null);
		frmAddNewRecord.getContentPane().setLayout(null);
		
		JLabel lblSelectTypeOf = new JLabel("Select the type of Record");
		lblSelectTypeOf.setBounds(62, 10, 150, 20);
		lblSelectTypeOf.setFont(new Font("Tahoma", Font.PLAIN, 13));
		frmAddNewRecord.getContentPane().add(lblSelectTypeOf);
		
		JLabel Identifier = new JLabel("Credit Card Number");
		Identifier.setBounds(62, 41, 150, 20);
		Identifier.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		class JTextFieldLimit extends PlainDocument 
		{
			private static final long serialVersionUID = 1L;
			private int limit;
			  JTextFieldLimit(int limit) 
			  {
			    super();
			    this.limit = limit;
			  }

			  public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException 
			  {
			    if (str == null)
			      return;

			    if ((getLength() + str.length()) <= limit) {
			      super.insertString(offset, str, attr);
			    }
			  }
		}
		
		textField = new JTextField();
		textField.setBounds(225, 42, 122, 20);
		textField.setColumns(5);
		textField.setDocument(new JTextFieldLimit(30));
		
		JLabel lblCardname = new JLabel("");
		lblCardname.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCardname.setBounds(357, 41, 82, 20);
		
		JLabel lblNameOnCard = new JLabel("Name on Card");
		lblNameOnCard.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNameOnCard.setBounds(62, 72, 93, 20);
		
		textField_1 = new JTextField();
		textField_1.setBounds(225, 73, 122, 20);
		textField_1.setColumns(10);
		textField_1.setDocument(new JTextFieldLimit(30));
		
		JLabel lblExpiryDate = new JLabel("Expiry Date");
		lblExpiryDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblExpiryDate.setBounds(62, 103, 70, 20);
		
		textField_2 = new JTextField();
		textField_2.setBounds(225, 104, 122, 20);
		textField_2.setColumns(10);
		textField_2.setDocument(new JTextFieldLimit(30));
		
		String[] months = new String[13];
		months[0] = "-";
		for(int i = 1;i <= 12;i++)
		{
			months[i] = Integer.toString(i);
		}
		JComboBox<?> comboBox_1 = new JComboBox<Object>(months);
		comboBox_1.setBounds(225, 104, 50, 20);
		
		String[] years = new String [102];
		years[0] = "-";
		for(int i = 1950;i <= 2050;i++)
		{
			years[i-1949] = Integer.toString(i);
		}
		JComboBox<?> comboBox_2 = new JComboBox<Object>(years);
		comboBox_2.setBounds(285, 104, 62, 20);
		
		MaskFormatter formatter;
		formatter = new MaskFormatter("###");
		JFormattedTextField formattedTextField = new JFormattedTextField(formatter);
		formattedTextField.setBounds(225, 135, 50, 20);
		
		JLabel lblCvv = new JLabel("CVV");
		lblCvv.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCvv.setBounds(62, 134, 46, 20);
		
		JLabel lblPin = new JLabel("PIN");
		lblPin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPin.setBounds(62, 165, 46, 14);
		
		
		MaskFormatter pinFormatter;
		pinFormatter = new MaskFormatter("####");
		JFormattedTextField formattedTextField_1 = new JFormattedTextField(pinFormatter);
		formattedTextField_1.setBounds(225, 163, 50, 20);
		
		String[] typeOfRecords = {"Credit Card","Debit Card","Website Password"};
		JComboBox<?> comboBox = new JComboBox<Object>(typeOfRecords);
		comboBox.setBounds(225, 11, 122, 20);
		
		frmAddNewRecord.getContentPane().add(comboBox);
		
		frmAddNewRecord.getContentPane().add(Identifier);
		frmAddNewRecord.getContentPane().add(textField);
		frmAddNewRecord.getContentPane().add(lblCardname);
		
		frmAddNewRecord.getContentPane().add(lblNameOnCard);
		frmAddNewRecord.getContentPane().add(textField_1);
		
		
		frmAddNewRecord.getContentPane().add(lblExpiryDate);
		frmAddNewRecord.getContentPane().add(comboBox_1);
		frmAddNewRecord.getContentPane().add(comboBox_2);
		
		frmAddNewRecord.getContentPane().add(lblCvv);
		frmAddNewRecord.getContentPane().add(formattedTextField);
		
		frmAddNewRecord.getContentPane().add(lblPin);
		frmAddNewRecord.getContentPane().add(formattedTextField_1);
		
		comboBox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{
				if(comboBox.getSelectedItem().toString().equals(typeOfRecords[0]) || comboBox.getSelectedItem().toString().equals(typeOfRecords[1]))
				{
					if(comboBox.getSelectedItem().toString().equals(typeOfRecords[0]))
						Identifier.setText("Credit Card Number");
					else if(comboBox.getSelectedItem().toString().equals(typeOfRecords[1]))
						Identifier.setText("Debit Card Number");
					frmAddNewRecord.getContentPane().add(lblNameOnCard);
					lblNameOnCard.setText("Name on Card");
					frmAddNewRecord.remove(textField_2);
					frmAddNewRecord.getContentPane().add(lblExpiryDate);
					lblExpiryDate.setText("Expiry Date");
					frmAddNewRecord.getContentPane().add(comboBox_1);
					frmAddNewRecord.getContentPane().add(comboBox_2);
					frmAddNewRecord.getContentPane().add(lblCvv);
					frmAddNewRecord.getContentPane().add(formattedTextField);
					frmAddNewRecord.getContentPane().add(lblCardname);
					frmAddNewRecord.getContentPane().add(lblPin);
					frmAddNewRecord.getContentPane().add(formattedTextField);
					frmAddNewRecord.getContentPane().add(formattedTextField_1);
					frmAddNewRecord.repaint();
				}
				if(comboBox.getSelectedItem().toString().equals(typeOfRecords[2]))
				{
					Identifier.setText("Website Name");
					lblNameOnCard.setText("User Name");
					lblExpiryDate.setText("Password");
					frmAddNewRecord.getContentPane().add(textField_2);
					frmAddNewRecord.remove(comboBox_1);
					frmAddNewRecord.remove(comboBox_2);
					frmAddNewRecord.remove(lblCvv);
					frmAddNewRecord.remove(formattedTextField);
					frmAddNewRecord.remove(lblPin);
					frmAddNewRecord.remove(formattedTextField_1);
					frmAddNewRecord.remove(lblCardname);
					frmAddNewRecord.repaint();
				}
			}
		});
		textField.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent fe)
			{
				//Just because we need to implement all the methods of the Listener. Albeit we could use Adapter class.
			}
			public void focusLost(FocusEvent fe)
			{
				String cardNumber = textField.getText();
				int length = cardNumber.length();
				int i,k,sum=0,temp;
				if((length%2)==0)
				    k=0;
				else
				    k=1;
				for(i=0;i<length;i++)
				{
				    if((i%2)==k)
				    {
				        temp=(cardNumber.charAt(i)-48)*2;
				        if(temp<10)
				            sum=sum+temp;
				        else
				            sum=sum+(1+(temp-10));
				    }
				    else
				        sum=sum+(cardNumber.charAt(i)-48);
				}
				if((sum%10)==0 && length==15 && cardNumber.charAt(0)=='3' && (cardNumber.charAt(1)=='4'||cardNumber.charAt(1)=='7'))
					lblCardname.setText("AMEX");
				else if((sum%10)==0 && length==16 && cardNumber.charAt(0)=='5' && (cardNumber.charAt(1)=='1'||cardNumber.charAt(1)=='2'||cardNumber.charAt(1)=='3'||cardNumber.charAt(1)=='4'||cardNumber.charAt(1)=='5'))
					lblCardname.setText("MASTERCARD");
				else if((sum%10)==0 && (length==13 || length==16) && cardNumber.charAt(0)=='4')
					lblCardname.setText("VISA");
				else
				    lblCardname.setText("UNKNOWN");
			}
		});

		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getSelectedItem().equals(typeOfRecords[0]) || comboBox.getSelectedItem().equals(typeOfRecords[1]))
				{
					if(textField.getText().isEmpty() && textField_1.getText().isEmpty() && formattedTextField.getText().trim().length() == 0 && formattedTextField_1.getText().trim().length() == 0)
					{
						JOptionPane jOptionPane = new JOptionPane();
						JOptionPane.showMessageDialog(jOptionPane, "Minimum 1 field required!", "Insufficient Arguments", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						if(update == true)
						{
							//System.out.println("Deleting");
							UpdateDataBase delete = new UpdateDataBase();
							delete.deleteFromDataBase(rowDetails);
							DisplayTable.frame.dispose();
							//TableData td1 = new TableData();
							//td1.compute();
							//System.out.println("Deleted");
							update = false;
						}
						UpdateDataBase insert = new UpdateDataBase();
						insert.insertIntoDataBase(comboBox.getSelectedItem().toString(), textField.getText(), lblCardname.getText(), textField_1.getText(), comboBox_1.getSelectedItem().toString(), comboBox_2.getSelectedItem().toString(), formattedTextField.getText(), formattedTextField_1.getText());
						DisplayTable.frame.dispose();
						TableData td = new TableData();
						
						frmAddNewRecord.dispose();
						td.compute();
					}
				}
				else if(comboBox.getSelectedItem().equals(typeOfRecords[2]))
				{
					if(textField.getText().isEmpty() && textField_1.getText().isEmpty() && textField_2.getText().isEmpty())
					{
						JOptionPane jOptionPane = new JOptionPane();
						JOptionPane.showMessageDialog(jOptionPane, "Minimum 1 field required!", "Insufficient Arguments", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						if(update == true)
						{
							//System.out.println("Deleting");
							UpdateDataBase delete = new UpdateDataBase();
							delete.deleteFromDataBase(rowDetails);
							DisplayTable.frame.dispose();
							//TableData td1 = new TableData();
							//td1.compute();
							//System.out.println("Deleted");
							update = false;
						}
						UpdateDataBase insert = new UpdateDataBase();
						insert.insertIntoDataBase(textField.getText(), textField_1.getText(), textField_2.getText());
						DisplayTable.frame.dispose();
						TableData td = new TableData();
						
						frmAddNewRecord.dispose();
						td.compute();
					}
				}
			}
		});
		btnDone.setBounds(123, 193, 89, 23);
		frmAddNewRecord.getContentPane().add(btnDone);
		
		JButton btnNeverMind = new JButton("Cancel");
		btnNeverMind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(update == true)
					update = false;
				DisplayTable.frame.setEnabled(true);
				frmAddNewRecord.dispose();
			}
		});
		btnNeverMind.setBounds(225, 193, 89, 23);
		frmAddNewRecord.getContentPane().add(btnNeverMind);
		
		//frmAddNewRecord.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblSelectTypeOf, comboBox}));
		if(update == true)
		{
			if((rowDetails[6].equals(typeOfRecords[0])) || (rowDetails[6].equals(typeOfRecords[1])))
			{
				comboBox.setSelectedItem(rowDetails[6]);
				textField.setText(rowDetails[0]);
				lblCardname.setText(rowDetails[5]);
				textField_1.setText(rowDetails[1]);
				String expiryDate = rowDetails[3];
				int indexOfSlash = expiryDate.indexOf('/');
				String month = expiryDate.substring(0, indexOfSlash);
				String year = expiryDate.substring(indexOfSlash+1, expiryDate.length());
				comboBox_1.setSelectedItem(month);
				comboBox_2.setSelectedItem(year);
				formattedTextField.setText(rowDetails[4]);
				formattedTextField_1.setText(rowDetails[2]);
			}
			else if(rowDetails[6].equals(" "))
			{
				comboBox.setSelectedItem(typeOfRecords[2]);
				textField.setText(rowDetails[0]);
				textField_1.setText(rowDetails[1]);
				textField_2.setText(rowDetails[2]);
			}
			//update = false;
		}
	}
}