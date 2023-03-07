package oopdeneme;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class RegisterPage extends JFrame {
	private Image img1 = new ImageIcon(LoginScreen.class.getResource("/res/register.png")).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);


	private JPanel contentPane;
	private JTextField registerName;
	private JTextField registerSurname;
	private JTextField registerSalary;
	private JTextField userName;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void openRegisterPage() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterPage frame = new RegisterPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterPage() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 683, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 128), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JPanel panel = new JPanel();
		panel.setBounds(196, 179, 300, 50);
		contentPane.add(panel);
		panel.setLayout(null);
		
		registerName = new JTextField();
		registerName.setFont(new Font("Arial", Font.PLAIN, 16));
		registerName.setBounds(100, 10, 170, 30);
		panel.add(registerName);
		registerName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 13, 70, 23);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(196, 254, 300, 50);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		registerSurname = new JTextField();
		registerSurname.setFont(new Font("Arial", Font.PLAIN, 16));
		registerSurname.setBounds(100, 10, 170, 30);
		panel_1.add(registerSurname);
		registerSurname.setColumns(10);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setFont(new Font("Arial", Font.BOLD, 16));
		lblSurname.setBounds(10, 10, 80, 23);
		panel_1.add(lblSurname);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(196, 331, 300, 50);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		registerSalary = new JTextField();
		registerSalary.setFont(new Font("Arial", Font.PLAIN, 16));
		registerSalary.setBounds(100, 10, 170, 30);
		panel_2.add(registerSalary);
		registerSalary.setColumns(10);
		
		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setFont(new Font("Arial", Font.BOLD, 16));
		lblSalary.setBounds(10, 13, 66, 23);
		panel_2.add(lblSalary);
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginScreen.main(null);
				dispose();
			}
		});

		btnNewButton.setFont(new Font("Arial", Font.BOLD, 12));
		btnNewButton.setBounds(10, 425, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("SIGN UP");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				application.Producer pr = new application.Producer(registerName.getText(), registerSurname.getText(),
						Double.parseDouble(registerSalary.getText()), userName.getText(), password.getText());
				application.DB.getProducers().add(pr);
				try {
					application.DB.writeDBFile("producers.txt", "producer");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ProducerPage.openProducerPage(application.DB.getProducerID());
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_1.setBounds(286, 412, 126, 41);
		contentPane.add(btnNewButton_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(196, 103, 300, 50);
		contentPane.add(panel_3);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Arial", Font.BOLD, 16));
		lblPassword.setBounds(10, 13, 80, 23);
		panel_3.add(lblPassword);
		
		password = new JPasswordField();
		password.setFont(new Font("Arial", Font.PLAIN, 16));
		password.setBounds(100, 10, 170, 30);
		panel_3.add(password);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(196, 27, 300, 50);
		contentPane.add(panel_4);
		
		userName = new JTextField();
		userName.setFont(new Font("Arial", Font.PLAIN, 16));
		userName.setColumns(10);
		userName.setBounds(100, 10, 170, 30);
		panel_4.add(userName);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Arial", Font.BOLD, 16));
		lblUsername.setBounds(10, 13, 80, 23);
		panel_4.add(lblUsername);
	}
}
