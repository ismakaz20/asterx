import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AsterX {

	/*private*/ static JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                } catch (InstantiationException ex) {
                } catch (IllegalAccessException ex) {
                } catch (UnsupportedLookAndFeelException ex) {
                }
				try {
					/*AsterX window = */new AsterX();
					AsterX.frame.setVisible(true);
				} catch (Exception e) {
					//e.printStackTrace();
					JOptionPane jOptionPane = new JOptionPane();
					JOptionPane.showMessageDialog(jOptionPane, "Oops! Something went wrong!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AsterX() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("AsterX");
		frame.setBounds(100, 100, 450, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLogin = new JButton("LogIn ?");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					//Welcome welcome = new Welcome();
					frame.setVisible(false);
					//frame.dispose();
					Welcome.mainWelcome();
				}
				catch(Exception e)
				{
					//e.printStackTrace();
					JOptionPane jOptionPane = new JOptionPane();
					JOptionPane.showMessageDialog(jOptionPane, "Oops! Something went wrong!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnLogin.setBounds(75, 84, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		JButton btnSignin = new JButton("SignUp ?");
		btnSignin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					//Register register = new Register();
					frame.setVisible(false);
					Register.mainRegister();
				}
				catch(Exception e)
				{
					e.printStackTrace();
					JOptionPane jOptionPane = new JOptionPane();
					JOptionPane.showMessageDialog(jOptionPane, "Oops! Something went wrong!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSignin.setBounds(257, 84, 89, 23);
		frame.getContentPane().add(btnSignin);
	}
}
