package oopdeneme;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AccountPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void openAccountPage() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountPage frame = new AccountPage();
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
	public AccountPage() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 128), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnMovies = new JButton("My Account");
		btnMovies.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AccountInfo.openAccountInfo(application.DB.getProducerID());
			}
		});
		btnMovies.setFont(new Font("Arial", Font.BOLD, 16));
		btnMovies.setBounds(250, 163, 150, 75);
		contentPane.add(btnMovies);
		
		JButton btnProduce = new JButton("Produce Movie");
		btnProduce.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProducerPage.openProducerPage(application.DB.getProducerID());
			}
		});
		btnProduce.setFont(new Font("Arial", Font.BOLD, 16));
		btnProduce.setBounds(250, 267, 150, 75);
		contentPane.add(btnProduce);
		
		JLabel lblNewLabel = new JLabel("Welcome "+application.DB.getProducers().get(application.DB.getProducerID()).getName() + " " + application.DB.getProducers().get(application.DB.getProducerID()).getSurname());
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel.setBounds(162, 60, 338, 75);
		contentPane.add(lblNewLabel);
		
		JButton btnLogOut = new JButton("Logout");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				application.DB.setProducerID(-1);
				LoginScreen.main(null);
				dispose();
			}
		});
		btnLogOut.setFont(new Font("Arial", Font.BOLD, 16));
		btnLogOut.setBounds(576, 10, 100, 27);
		contentPane.add(btnLogOut);
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchPage.main(null);
			}
		});
		btnSearch.setFont(new Font("Arial", Font.BOLD, 16));
		btnSearch.setBounds(250, 365, 150, 75);
		contentPane.add(btnSearch);
		


	
	}
}
