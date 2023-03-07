package oopdeneme;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.JTextPane;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AccountInfo extends JFrame {

	private JPanel contentPane;
	private JTable accountInfo;

	/**
	 * Launch the application.
	 */
	public static void openAccountInfo(int producerID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountInfo frame = new AccountInfo(producerID);
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
	
	public AccountInfo(int producerID) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 128), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(Color.WHITE);
		panel.setBackground(new Color(47, 79, 79));
		panel.setBounds(25, 25, 257, 66);
		contentPane.add(panel);
		
		JLabel lblMovies = new JLabel("MY ACCOUNT");
		lblMovies.setHorizontalAlignment(SwingConstants.CENTER);
		lblMovies.setForeground(Color.WHITE);
		lblMovies.setFont(new Font("Arial", Font.BOLD, 16));
		lblMovies.setBounds(10, 10, 237, 46);
		panel.add(lblMovies);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 101, 338, 351);
		contentPane.add(scrollPane);
		
		
		String[] movies = new String[application.DB.getProducers().get(producerID).getMovieIDs().size()];
        int movieSize = application.DB.getProducers().get(producerID).getMovieIDs().size();
        for (int i = 0; i < movieSize; i++) {
            int movieID = application.DB.getProducers().get(producerID).getMovieIDs().get(i);
            movies[i] = application.DB.getMovies().get(movieID).getTitle();
        }
        System.out.println();

        String[][] data = new String[application.Producer.getHeaders().length][2];
        String[] columns = {"Property", "Value"};
        for (int i = 0; i < application.Producer.getHeaders().length; i++) {
            String[] rowData = {application.Producer.getHeaders()[i],application.DB.getProducers().get(producerID).tableRow()[i]};
            data[i] = rowData;
        }
        DefaultTableModel dtmProducerInfos = new DefaultTableModel(data, columns);
        JTable producerInfos = new JTable(dtmProducerInfos)
        {
            // determine which editor to use by JTable
            public TableCellEditor getCellEditor(int row, int column)
            {
                int col = convertColumnIndexToModel(column);
                if (col == 1 && row == 10)
                {
                    JComboBox<String> cb = new JComboBox<String>(movies);
                    return new DefaultCellEditor(cb);
                }
                else
                    return super.getCellEditor(row, column);
            }
        };
        
        scrollPane.setViewportView(producerInfos);
		
        
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(388, 101, 338, 351);
		contentPane.add(scrollPane_1);
						
		accountInfo = new JTable();
		accountInfo.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Property", "Value"
			}
		));
		accountInfo.getColumnModel().getColumn(1).setPreferredWidth(175);
		
		//
		
		
		scrollPane_1.setViewportView(accountInfo);
		
		JButton btnNewButton = new JButton("Show Movie Info");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) producerInfos.getModel();
				String movieTitle = dtm.getValueAt(10, 1).toString();
				dtm = (DefaultTableModel) accountInfo.getModel();
				String[] headers = application.Movie.getHeaders();
				String[] tableRow = application.DB.getMovies().get(findMovie(movieTitle, producerID)).tableRow();
				
				dtm.setRowCount(0);
				for (int i = 0; i < headers.length; i++) {
					String[] str = {headers[i], tableRow[i]};
					dtm.addRow(str);
				}
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton.setBounds(228, 452, 120, 30);
		contentPane.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(Color.WHITE);
		panel_1.setBackground(new Color(47, 79, 79));
		panel_1.setBounds(418, 25, 257, 66);
		contentPane.add(panel_1);
		
		JLabel lblAccountInformation = new JLabel("MOVIE INFORMATION");
		lblAccountInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccountInformation.setForeground(Color.WHITE);
		lblAccountInformation.setFont(new Font("Arial", Font.BOLD, 16));
		lblAccountInformation.setBounds(10, 10, 237, 46);
		panel_1.add(lblAccountInformation);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 16));
		btnNewButton_1.setBounds(641, 532, 85, 21);
		contentPane.add(btnNewButton_1);
	}
}