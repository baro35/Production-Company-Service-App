package oopdeneme;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class SearchPage extends JFrame {

	
	private JRadioButton producerButton;
	private JRadioButton actorButton;
	private JRadioButton managerButton;
	private JRadioButton movieButton;
	private JRadioButton studioButton;
	private JRadioButton employeeButton;
	private String selectedRadioButton;
	
	private JPanel contentPane;
	private JTextField id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchPage frame = new SearchPage();
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
	public SearchPage() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 215);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 128), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		actorButton = new JRadioButton("ACTOR");
		actorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRadioButton = "Actor";

			}
		});
		actorButton.setOpaque(false);
		actorButton.setBounds(160, 66, 103, 21);
		contentPane.add(actorButton);

		managerButton = new JRadioButton("MANAGER");
		managerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRadioButton = "Manager";

			}
		});
		managerButton.setOpaque(false);
		managerButton.setBounds(370, 66, 103, 21);
		contentPane.add(managerButton);

		producerButton = new JRadioButton("PRODUCER");
		producerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRadioButton = "Producer";

			}
		});
		producerButton.setOpaque(false);
		producerButton.setBounds(55, 66, 103, 21);
		contentPane.add(producerButton);

		movieButton = new JRadioButton("MOVIE");
		movieButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRadioButton = "Movie";

			}
		});
		movieButton.setOpaque(false);
		movieButton.setBounds(475, 66, 103, 21);
		contentPane.add(movieButton);

		studioButton = new JRadioButton("STUDIO");
		studioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRadioButton = "STUDIO";

			}
		});
		studioButton.setOpaque(false);
		studioButton.setBounds(580, 66, 103, 21);
		contentPane.add(studioButton);

		employeeButton = new JRadioButton("EMPLOYEE");
		employeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRadioButton = "EMPLOYEE";

			}
		});
		employeeButton.setOpaque(false);
		employeeButton.setBounds(685, 66, 103, 21);
		contentPane.add(employeeButton);
		


		ButtonGroup group = new ButtonGroup();
		group.add(actorButton);
		group.add(managerButton);
		group.add(producerButton);
		group.add(movieButton);
		group.add(studioButton);
		group.add(employeeButton);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(59, 119, 56, 21);
		contentPane.add(lblNewLabel);
		
		id = new JTextField();
		id.setFont(new Font("Arial", Font.BOLD, 16));
		id.setBounds(141, 122, 96, 19);
		contentPane.add(id);
		id.setColumns(10);
		
		JButton btnShow = new JButton("SHOW");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(selectedRadioButton.equalsIgnoreCase("Actor")) {
						ActorInfo.openActorInfo(application.DB.getActors().get(Integer.parseInt(id.getText())).getActorID());
					}
					if(selectedRadioButton.equalsIgnoreCase("Employee")) {
						EmployeeInfo.openEmployeeInfo(application.DB.getEmployees().get(Integer.parseInt(id.getText())).getEmployeeID());
					}
					if(selectedRadioButton.equalsIgnoreCase("Studio")) {
						StudioInfo.openStudioInfo(application.DB.getStudios().get(Integer.parseInt(id.getText())).getStudioID());
					}
					if(selectedRadioButton.equalsIgnoreCase("Producer")) {
						AccountInfo.openAccountInfo(application.DB.getProducers().get(Integer.parseInt(id.getText())).getProducerID());
					}
					if(selectedRadioButton.equalsIgnoreCase("Manager")) {
						ManagerInfo.openManagerInfo(application.DB.getManagers().get(Integer.parseInt(id.getText())).getManagerID());
					}
					if(selectedRadioButton.equalsIgnoreCase("Movie")) {
						MovieAddedInfo.openMovieAddedInfo(application.DB.getMovies().get(Integer.parseInt(id.getText())).getMovieID());
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(contentPane, "Something happened!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnShow.setFont(new Font("Arial", Font.BOLD, 16));
		btnShow.setBounds(388, 121, 85, 21);
		contentPane.add(btnShow);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setFont(new Font("Arial", Font.BOLD, 16));
		btnBack.setBounds(507, 121, 85, 21);
		contentPane.add(btnBack);
	}
}
