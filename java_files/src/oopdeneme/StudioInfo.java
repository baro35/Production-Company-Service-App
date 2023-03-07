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

public class StudioInfo extends JFrame {
	private Image img = new ImageIcon(LoginScreen.class.getResource("/res/studio.png")).getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH);

	private JPanel contentPane;
	private JTable elementOfStudioInfo;
	private JTable studioInfos;
	private String lastSelectedComboBox = "";
	/**
	 * Launch the application.
	 */
	public static void openStudioInfo(int studioID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudioInfo frame = new StudioInfo(studioID);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private int findMovie(String title,int studioID) {
		int size = application.DB.getStudios().get(studioID).getMovieIDs().size();
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
	public StudioInfo(int studioID) {		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 128), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(37, 10, 213, 177);
		lblNewLabel.setIcon(new ImageIcon(img));
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 220, 200, 200);
		contentPane.add(scrollPane);
		

		

		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(277, 10, 387, 410);
		contentPane.add(scrollPane_1);
		


		
		
		String[] movies = new String[application.DB.getStudios().get(studioID).getMovieIDs().size()];
		int movieSize = application.DB.getStudios().get(studioID).getMovieIDs().size();
		for (int i = 0; i < movieSize; i++) {
			int movieID = application.DB.getStudios().get(studioID).getMovieIDs().get(i);
			movies[i] = application.DB.getMovies().get(movieID).getTitle();
		}
		
		String[][] data = new String[application.Studio.getHeaders().length][2];
		String[] columns = {"Property", "Value"};
		for (int i = 0; i < application.Studio.getHeaders().length; i++) {
			String[] rowData = {application.Studio.getHeaders()[i],application.DB.getStudios().get(studioID).tableRow()[i]};
			data[i] = rowData;
		}
        DefaultTableModel dtmStudioInfos = new DefaultTableModel(data, columns);
        JTable studioInfos = new JTable(dtmStudioInfos)
        {
            // determine which editor to use by JTable
            public TableCellEditor getCellEditor(int row, int column)
            {
                int col = convertColumnIndexToModel(column);
                if (col == 1 && row == 5)
                {
                    JComboBox<String> cb = new JComboBox<String>(movies);
                    return new DefaultCellEditor(cb);
                }
                else
                    return super.getCellEditor(row, column);
            }
        };
		scrollPane_1.setViewportView(studioInfos);
		
		elementOfStudioInfo = new JTable();		
		elementOfStudioInfo.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		elementOfStudioInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//
			}
		});
		elementOfStudioInfo.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
			},
			new String[] {
				"New column"
			}
		));
		scrollPane.setViewportView(elementOfStudioInfo);
		DefaultTableModel dtmElementOfStudioInfo = (DefaultTableModel) elementOfStudioInfo.getModel();
		dtmElementOfStudioInfo.setColumnCount(0);
		dtmElementOfStudioInfo.setRowCount(0);
		dtmElementOfStudioInfo.addColumn("");
		dtmElementOfStudioInfo.addColumn("");
		
		JButton getMovieInfo = new JButton("Info");
		getMovieInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(studioInfos.getSelectedRow() == 5) {
					String title = dtmStudioInfos.getValueAt(5, 1).toString();
					int movieID = findMovie(title, studioID);
					dtmElementOfStudioInfo.setColumnCount(0);
					dtmElementOfStudioInfo.setRowCount(0);
					dtmElementOfStudioInfo.addColumn("");
					dtmElementOfStudioInfo.addColumn("");
					for (int i = 0; i < application.Movie.getHeaders().length; i++) {
						String[] rowData = {application.Movie.getHeaders()[i],application.DB.getMovies().get(movieID).tableRow()[i]};
						dtmElementOfStudioInfo.addRow(rowData);
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
		btnBack.setBounds(484, 433, 85, 21);
		contentPane.add(btnBack);
		
		

		
		
	}
}