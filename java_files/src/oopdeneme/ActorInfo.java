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

public class ActorInfo extends JFrame {
	private int actorID;
	private JPanel contentPane;
	private JTable elementOfActorInfo;
	private JTable actorInfos;
	private String lastSelectedComboBox = "";

	/**
	 * Launch the application.
	 */
	public static void openActorInfo(int actorID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				
					ActorInfo frame = new ActorInfo(actorID);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private int findMovie(String title,int producerID) {
        int size = application.DB.getMovies().size();
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
	public ActorInfo(int actorID) {		
		this.actorID = actorID;
		Image img = null;
		if(!application.DB.getActors().get(actorID).getImageFile().equalsIgnoreCase("-"))
			img = new ImageIcon(LoginScreen.class.getResource("/res/"+application.DB.getActors().get(actorID).getImageFile())).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 128), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		if(img != null) {
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setBounds(37, 10, 200, 150);
			lblNewLabel.setIcon(new ImageIcon(img));
			contentPane.add(lblNewLabel);
		}

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 220, 200, 200);
		contentPane.add(scrollPane);
		

		

		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(277, 10, 387, 410);
		contentPane.add(scrollPane_1);
		


		
		
		
		int movieSize = application.DB.getActors().get(actorID).getMovieIDs().size();
		if(movieSize>application.DB.getMovies().size())
			movieSize = application.DB.getMovies().size();
		String[] movies = new String[movieSize];
		for (int i = 0; i < movieSize; i++) {
			int movieID = application.DB.getActors().get(actorID).getMovieIDs().get(i);
			movies[i] = application.DB.getMovies().get(movieID).getTitle();
		}
		
		String[][] data = new String[application.Actor.getHeaders().length][2];
		String[] columns = {"Property", "Value"};
		for (int i = 0; i < application.Actor.getHeaders().length; i++) {
			String[] rowData = {application.Actor.getHeaders()[i],application.DB.getActors().get(actorID).tableRow()[i]};
			data[i] = rowData;
		}
        DefaultTableModel dtmActorInfos = new DefaultTableModel(data, columns);
        JTable actorInfos = new JTable(dtmActorInfos)
        {
            // determine which editor to use by JTable
            public TableCellEditor getCellEditor(int row, int column)
            {
                int col = convertColumnIndexToModel(column);
                if (col == 1 && row == 12)
                {
                    JComboBox<String> cb = new JComboBox<String>(movies);
                    return new DefaultCellEditor(cb);
                }
                else
                    return super.getCellEditor(row, column);
            }
        };
		scrollPane_1.setViewportView(actorInfos);
		
		elementOfActorInfo = new JTable();		
		elementOfActorInfo.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		elementOfActorInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//
			}
		});
		elementOfActorInfo.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
			},
			new String[] {
				"New column"
			}
		));
		scrollPane.setViewportView(elementOfActorInfo);
		DefaultTableModel dtmElementOfActorInfo = (DefaultTableModel) elementOfActorInfo.getModel();
		dtmElementOfActorInfo.setColumnCount(0);
		dtmElementOfActorInfo.setRowCount(0);
		dtmElementOfActorInfo.addColumn("");
		dtmElementOfActorInfo.addColumn("");
		
		JButton getMovieInfo = new JButton("Info");
		getMovieInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(actorInfos.getSelectedRow() == 10) {
					int managerID = application.DB.getActors().get(actorID).getManagerID();
					dtmElementOfActorInfo.setColumnCount(0);
					dtmElementOfActorInfo.setRowCount(0);
					dtmElementOfActorInfo.addColumn("");
					dtmElementOfActorInfo.addColumn("");
					for (int i = 0; i < application.Manager.getHeaders().length; i++) {
						String[] rowData = {application.Manager.getHeaders()[i],application.DB.getManagers().get(managerID).tableRow()[i]};
						dtmElementOfActorInfo.addRow(rowData);
					}
				}else if(actorInfos.getSelectedRow() == 12) {
					String title = dtmActorInfos.getValueAt(12, 1).toString();
					int movieID = findMovie(title, actorID);
					dtmElementOfActorInfo.setColumnCount(0);
					dtmElementOfActorInfo.setRowCount(0);
					dtmElementOfActorInfo.addColumn("");
					dtmElementOfActorInfo.addColumn("");
					for (int i = 0; i < application.Movie.getHeaders().length; i++) {
						String[] rowData = {application.Movie.getHeaders()[i],application.DB.getMovies().get(movieID).tableRow()[i]};
						dtmElementOfActorInfo.addRow(rowData);
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
		btnBack.setBounds(491, 432, 85, 21);
		contentPane.add(btnBack);
		
		

		
		
	}
}
