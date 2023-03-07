package oopdeneme;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmployeeInfo extends JFrame {
	private Image img = new ImageIcon(LoginScreen.class.getResource("/res/empl.png")).getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH);

	private JPanel contentPane;
	private JTable elementOfEmployeeInfo;
	private JTable employeeInfos;

	/**
	 * Launch the application.
	 */
	public static void openEmployeeInfo(int employeeID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeInfo frame = new EmployeeInfo(employeeID);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private int findMovie(String title,int employeeID) {
		int size = application.DB.getActors().get(employeeID).getMovieIDs().size();
		int id = 0;
		for (int i = 0; i < size; i++) {
			if(application.DB.getMovies().get(i).getTitle().equalsIgnoreCase(title)) {
				id = application.DB.getMovies().get(i).getMovieID();
				break;
			}
		}
		return id;
	}
	
	/**
	 * Create the frame.
	 */
	public EmployeeInfo(int employeeID) {		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 128), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(37, 10, 200, 170);
		lblNewLabel.setIcon(new ImageIcon(img));
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 220, 200, 200);
		contentPane.add(scrollPane);
		

		

		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(277, 10, 387, 410);
		contentPane.add(scrollPane_1);
		


		
		
	
		int movieSize = application.DB.getEmployees().get(employeeID).getMovieIDs().size();
		if(movieSize>application.DB.getMovies().size())
			movieSize = application.DB.getMovies().size();
		String[] movies = new String[movieSize];
		for (int i = 0; i < movieSize; i++) {
			int movieID = application.DB.getEmployees().get(employeeID).getMovieIDs().get(i);
			movies[i] = application.DB.getMovies().get(movieID).getTitle();
		}
		
		String[][] data = new String[application.Employee.getHeaders().length][2];
		String[] columns = {"Property", "Value"};
		for (int i = 0; i < application.Employee.getHeaders().length; i++) {
			String[] rowData = {application.Employee.getHeaders()[i],application.DB.getEmployees().get(employeeID).tableRow()[i]};
			data[i] = rowData;
		}
        DefaultTableModel dtmEmployeeInfos = new DefaultTableModel(data, columns);
        JTable employeeInfos = new JTable(dtmEmployeeInfos)
        {
            // determine which editor to use by JTable
            public TableCellEditor getCellEditor(int row, int column)
            {
                int col = convertColumnIndexToModel(column);
                if (col == 1 && row == 11)
                {
                    JComboBox<String> cb = new JComboBox<String>(movies);
                    return new DefaultCellEditor(cb);
                }
                else
                    return super.getCellEditor(row, column);
            }
        };
		scrollPane_1.setViewportView(employeeInfos);
		
		elementOfEmployeeInfo = new JTable();		
		elementOfEmployeeInfo.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		elementOfEmployeeInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//
			}
		});
		elementOfEmployeeInfo.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
			},
			new String[] {
				"New column"
			}
		));
		scrollPane.setViewportView(elementOfEmployeeInfo);
		DefaultTableModel dtmelementOfEmployeeInfo = (DefaultTableModel) elementOfEmployeeInfo.getModel();
		dtmelementOfEmployeeInfo.setColumnCount(0);
		dtmelementOfEmployeeInfo.setRowCount(0);
		dtmelementOfEmployeeInfo.addColumn("");
		dtmelementOfEmployeeInfo.addColumn("");
		
		JButton getMovieInfo = new JButton("Info");
		getMovieInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(employeeInfos.getSelectedRow() == 11) {
					String title = dtmEmployeeInfos.getValueAt(11, 1).toString(); 
					int movieID = findMovie(title, employeeID);
					dtmelementOfEmployeeInfo.setColumnCount(0);
					dtmelementOfEmployeeInfo.setRowCount(0);
					dtmelementOfEmployeeInfo.addColumn("");
					dtmelementOfEmployeeInfo.addColumn("");
					for (int i = 0; i < application.Movie.getHeaders().length; i++) {
						String[] rowData = {application.Movie.getHeaders()[i],application.DB.getMovies().get(movieID).tableRow()[i]};
						dtmelementOfEmployeeInfo.addRow(rowData);
					}
				}
				
			}
		});
		getMovieInfo.setFont(new Font("Arial", Font.PLAIN, 14));
		getMovieInfo.setBounds(579, 432, 85, 21);
		contentPane.add(getMovieInfo);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setFont(new Font("Arial", Font.PLAIN, 14));
		btnBack.setBounds(487, 433, 85, 21);
		contentPane.add(btnBack);
		
		

		
		
	}
}
