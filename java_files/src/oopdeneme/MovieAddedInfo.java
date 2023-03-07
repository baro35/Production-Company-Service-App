package oopdeneme;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import application.DB;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MovieAddedInfo extends JFrame {
	private Image img = new ImageIcon(LoginScreen.class.getResource("/res/movie.png")).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);


	private JPanel contentPane;
	private JTable table;
	private int movieID;
	/**
	 * Launch the application.
	 */
	public static void openMovieAddedInfo(int movieID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovieAddedInfo frame = new MovieAddedInfo(movieID);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private int findActorId(String name,String surname,String type) {
		int index = 0;
		if(type.equalsIgnoreCase("Actor")) {
			int size = application.DB.getActors().size();
			for (int i = 0; i < size; i++) {
				if(application.DB.getActors().get(i).getName().equalsIgnoreCase(name) && application.DB.getActors().get(i).getSurname().equalsIgnoreCase(surname)) {
					index = i;
					break;
				}
			}
		} else if(type.equalsIgnoreCase("Employee")) {
			int size = application.DB.getEmployees().size();
			for (int i = 0; i < size; i++) {
				if(application.DB.getEmployees().get(i).getName().equalsIgnoreCase(name) && application.DB.getEmployees().get(i).getSurname().equalsIgnoreCase(surname)) {
					index = i;
					break;
				}
			}
		} else if(type.equalsIgnoreCase("Studio")) {
			int size = application.DB.getStudios().size();
			for (int i = 0; i < size; i++) {
				if(application.DB.getStudios().get(i).getTradeName().equalsIgnoreCase(name)) {
					index = i;
					break;
				}
			}
		}else if(type.equalsIgnoreCase("Producer")) {
			index = application.DB.getProducerID();
		}
		
		return index;
	}

	/**
	 * Create the frame.
	 */
	public MovieAddedInfo(int movieID) {
		this.movieID = movieID;

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 128), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(47, 79, 79));
		panel.setForeground(new Color(255, 255, 255));
		panel.setBounds(42, 32, 257, 66);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel movieTitle = new JLabel(application.DB.getMovies().get(movieID).tableRow()[0]);
		movieTitle.setHorizontalAlignment(SwingConstants.CENTER);
		movieTitle.setForeground(new Color(255, 255, 255));
		movieTitle.setFont(new Font("Arial", Font.BOLD, 16));
		movieTitle.setBounds(10, 10, 237, 46);
		panel.add(movieTitle);
		
		JButton btnNewButton = new JButton("CLOSE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(579, 430, 85, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(465, 100, 199, 307);
		lblNewLabel_1.setIcon(new ImageIcon(img));
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setForeground(new Color(0, 0, 0));
		scrollPane.setBounds(42, 155, 391, 296);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setName("movieInfoTable");
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFont(new Font("Arial", Font.PLAIN, 16));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"New column", "New column"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(65);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		scrollPane.setViewportView(table);

        int actorSize = application.DB.getMovies().get(movieID).getActorIDs().size();
		String[] actors = new String[actorSize];
        for (int i = 0; i < actorSize; i++) {
            int actorID = application.DB.getMovies().get(movieID).getActorIDs().get(i);
            actors[i] = application.DB.getActors().get(actorID).getName() + " " + application.DB.getActors().get(actorID).getSurname();
        }
        

        int employeeSize = application.DB.getMovies().get(movieID).getEmployeeIDs().size();
        String[] employees = new String[employeeSize];
        for (int i = 0; i < employeeSize; i++) {
            int employeeID = application.DB.getMovies().get(movieID).getEmployeeIDs().get(i);
            employees[i] = application.DB.getEmployees().get(employeeID).getWorkArea() + ":" + application.DB.getEmployees().get(employeeID).getName() + " " + application.DB.getEmployees().get(employeeID).getSurname();
        }
        
        

        String[][] data = new String[application.Movie.getHeaders().length + application.DB.getMovies().get(movieID).getEmployeeIDs().size() - 2 + application.DB.getMovies().get(movieID).getActorIDs().size()][2];
        String[] columns = {"Property", "Value"};
        int row = 0;
        for (int i = 1; i < application.Movie.getHeaders().length; i++) {
        	if(application.Movie.getHeaders()[i].equalsIgnoreCase("Producer ID")) {
        		String[] rowData = {"Producer",application.DB.getProducers().get(application.DB.getMovies().get(movieID).getProducerID()).getName() + " " + application.DB.getProducers().get(application.DB.getMovies().get(movieID).getProducerID()).getSurname() };
        		data[row] = rowData;
                row++;
    		}else if(application.Movie.getHeaders()[i].equalsIgnoreCase("Studio ID")) {
    			String[] rowData = {"Studio",application.DB.getStudios().get(application.DB.getMovies().get(movieID).getStudioID()).getTradeName()};
    			data[row] = rowData;
                row++;
    		}else if(application.Movie.getHeaders()[i].equalsIgnoreCase("Employee IDs")) {
    			for (int j = 0; j < application.DB.getMovies().get(movieID).getEmployeeIDs().size(); j++) {
    				String[] rowData = {application.DB.getEmployees().get(application.DB.getMovies().get(movieID).getEmployeeIDs().get(j)).getWorkArea(),application.DB.getEmployees().get(application.DB.getMovies().get(movieID).getEmployeeIDs().get(j)).getName() + " " + application.DB.getEmployees().get(application.DB.getMovies().get(movieID).getEmployeeIDs().get(j)).getSurname()};
    				data[row] = rowData;
    				row++;
				}
    		}else if(application.Movie.getHeaders()[i].equalsIgnoreCase("Actor IDs")) {
    			for (int j = 0; j < application.DB.getMovies().get(movieID).getActorIDs().size(); j++) {
    				String[] rowData = {"Actor",application.DB.getActors().get(application.DB.getMovies().get(movieID).getActorIDs().get(j)).getName() + " " + application.DB.getActors().get(application.DB.getMovies().get(movieID).getActorIDs().get(j)).getSurname()};
    				data[row] = rowData;
    				row++;
				}
    		}else {
    			String[] rowData = {application.Movie.getHeaders()[i],application.DB.getMovies().get(movieID).tableRow()[i]};
                data[row] = rowData;
                row++;
    		}
        }
        
        
        DefaultTableModel dtmMovieInfos = new DefaultTableModel(data, columns);
        JTable movieInfos = new JTable(dtmMovieInfos)
        {
            // determine which editor to use by JTable
            public TableCellEditor getCellEditor(int row, int column)
            {
                int col = convertColumnIndexToModel(column);
                if (col == 1 && row == 10)
                {
                    JComboBox<String> cb = new JComboBox<String>(actors);
                    return new DefaultCellEditor(cb);
                }
                else if ( col == 1 && row == 11) {
                	JComboBox<String> cb = new JComboBox<String>(employees);
                    return new DefaultCellEditor(cb);
                }
                else
                    return super.getCellEditor(row, column);
            }
        };
        scrollPane.setViewportView(movieInfos);

        JButton getMovieInfo = new JButton("Info");
        getMovieInfo.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(movieInfos.getSelectedRow() >= 9 &&movieInfos.getSelectedRow()<9+DB.getMovies().get(movieID).getActorIDs().size() ) {
                	String[] nameSurname = dtmMovieInfos.getValueAt(movieInfos.getSelectedRow(), 1).toString().split(" ");
                	ActorInfo.openActorInfo(findActorId(nameSurname[0],nameSurname[1],"Actor"));
                	
                }else if(movieInfos.getSelectedRow() >= 9 +9+DB.getMovies().get(movieID).getActorIDs().size() && movieInfos.getSelectedRow() < 9 +9+DB.getMovies().get(movieID).getActorIDs().size() +DB.getMovies().get(movieID).getEmployeeIDs().size() ) {
                	String[] nameSurname = dtmMovieInfos.getValueAt(movieInfos.getSelectedRow(), 1).toString().split(":")[1].split(" ");
                	EmployeeInfo.openEmployeeInfo(findActorId(nameSurname[0],nameSurname[1],"Employee"));
                }else if(movieInfos.getSelectedRow() == 8) {
                	String nameSurname = dtmMovieInfos.getValueAt(movieInfos.getSelectedRow(), 1).toString();
                	StudioInfo.openStudioInfo(findActorId(nameSurname,"","Studio"));
                }else if(movieInfos.getSelectedRow() == 7) {
                	String[] nameSurname = dtmMovieInfos.getValueAt(movieInfos.getSelectedRow(), 1).toString().split(" ");
                	AccountInfo.openAccountInfo(findActorId(nameSurname[0],nameSurname[1],"Producer"));
                }
        	}
        });

        getMovieInfo.setFont(new Font("Arial", Font.PLAIN, 14));
        getMovieInfo.setBounds(484, 430, 85, 21);
        contentPane.add(getMovieInfo);

	}
}