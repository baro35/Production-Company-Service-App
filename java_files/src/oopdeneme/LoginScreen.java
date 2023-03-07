package oopdeneme;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginScreen extends JFrame {
	
	private Image img1 = new ImageIcon(LoginScreen.class.getResource("/res/lock2.png")).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
	private Image img2 = new ImageIcon(LoginScreen.class.getResource("/res/businessman.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image img3 = new ImageIcon(LoginScreen.class.getResource("/res/key.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

	
	
	

	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen frame = new LoginScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public LoginScreen() throws IOException {
		application.DB db = application.DB.getDataObject();
		db.deleteAllArrayList();
		db.readAllFiles();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.BLUE));
		contentPane.setBackground(new Color(0, 139, 139));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JPanel panel = new JPanel();
		panel.setBounds(344, 222, 300, 50);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtUserName = new JTextField();
		txtUserName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtUserName.getText().equals("Username")) {
					txtUserName.setText("");
				}
				else txtUserName.selectAll();
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtUserName.getText().equals("")) {
					txtUserName.setText("Username");
				}
			}
		});
		txtUserName.setBorder(null);
		txtUserName.setFont(new Font("Arial", Font.PLAIN, 16));
		txtUserName.setText("Username");
		txtUserName.setBounds(10, 10, 200, 30);
		panel.add(txtUserName);
		txtUserName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(245, 10, 30, 30);
		lblNewLabel_2.setIcon(new ImageIcon(img2));
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(344, 282, 300, 50);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key=e.getKeyCode();
				if(key == KeyEvent.VK_ENTER) {
		        	application.DB.checkAccount(txtUserName.getText(), txtPassword.getText());
					if(application.DB.getProducerID()!= -1) {
						AccountPage.openAccountPage();
						dispose();
					} else if(application.DB.getAdminID()!= -1) {
						AdminPanel.openAdminPanel();
						dispose();
					}else {
						JOptionPane.showMessageDialog(contentPane, "Wrong username or password!", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		txtPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtPassword.getText().equals("Password")) {
					txtPassword.setEchoChar('*');
					txtPassword.setText("");
				}
				else txtPassword.selectAll();
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtPassword.getText().equals("")) {
					txtPassword.setEchoChar((char)0);
					txtPassword.setText("Password");
				}
			}
		});
		txtPassword.setBorder(null);
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		txtPassword.setEchoChar((char)0);
		txtPassword.setText("Password");
		txtPassword.setBounds(10, 10, 200, 30);
		panel_1.add(txtPassword);
		
		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setBounds(245, 10, 30, 30);
		lblNewLabel_2_1.setIcon(new ImageIcon(img3));
		panel_1.add(lblNewLabel_2_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
	        	application.DB.checkAccount(txtUserName.getText(), txtPassword.getText());
				if(application.DB.getProducerID()!= -1) {
					AccountPage.openAccountPage();
					dispose();
				} else if(application.DB.getAdminID()!= -1) {
					AdminPanel.openAdminPanel();
					dispose();
				}else {
					JOptionPane.showMessageDialog(contentPane, "Wrong username or password!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel_2.setBackground(new Color(47, 79, 79));
		panel_2.setBounds(344, 380, 120, 70);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel loginLabel = new JLabel("LOG IN");
		loginLabel.setForeground(new Color(255, 255, 255));
		loginLabel.setBackground(new Color(255, 255, 255));
		loginLabel.setFont(new Font("Arial", Font.BOLD, 16));
		loginLabel.setBounds(33, 25, 80, 20);
		panel_2.add(loginLabel);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegisterPage.openRegisterPage();
				dispose();
			}
		});
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(new Color(47, 79, 79));
		panel_2_1.setBounds(512, 380, 120, 70);
		contentPane.add(panel_2_1);
		
		JLabel registerLabel = new JLabel("REGISTER");
		registerLabel.setForeground(Color.WHITE);
		registerLabel.setFont(new Font("Arial", Font.BOLD, 16));
		registerLabel.setBackground(Color.WHITE);
		registerLabel.setBounds(21, 25, 120, 20);
		panel_2_1.add(registerLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(434, 50, 133, 122);
		lblNewLabel_1.setIcon(new ImageIcon(img1));
		contentPane.add(lblNewLabel_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 222, 173));
		panel_3.setBounds(0, 0, 300, 500);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("PRODUCER`S");
		lblNewLabel_3.setBackground(new Color(255, 255, 255));
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblNewLabel_3.setBounds(0, 119, 290, 72);
		panel_3.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("HANDBOOK");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setForeground(Color.BLACK);
		lblNewLabel_3_1.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblNewLabel_3_1.setBackground(Color.WHITE);
		lblNewLabel_3_1.setBounds(0, 201, 290, 72);
		panel_3.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("APPLICATION");
		lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_2.setForeground(Color.BLACK);
		lblNewLabel_3_2.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblNewLabel_3_2.setBackground(Color.WHITE);
		lblNewLabel_3_2.setBounds(0, 283, 290, 72);
		panel_3.add(lblNewLabel_3_2);
	}
}