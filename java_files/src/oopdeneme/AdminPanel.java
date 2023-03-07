package oopdeneme;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButtonMenuItem;

public class AdminPanel extends JFrame {

	private JPanel contentPane;
	private JTable Tablee;
	private JRadioButton producerButton;
	private JRadioButton actorButton;
	private JRadioButton contractButton;
	private JRadioButton managerButton;
	private JRadioButton movieButton;
	private JRadioButton studioButton;
	private JRadioButton employeeButton;
	private JRadioButton adminButton;
	private DefaultTableModel dtm;
	private String selectedRadioButton;
	private JButton btnLogout;

	/**
	 * Launch the application.
	 */
	public static void openAdminPanel() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPanel frame = new AdminPanel();
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
	
	private static void copyFileUsingStream(File source, File dest) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    }
	}

	private void tableContent(String type) {
		
		if (type.equalsIgnoreCase("producer")) {
			dtm = (DefaultTableModel) Tablee.getModel();
			dtm.setColumnCount(0);
			dtm.setRowCount(0);
			for (int i = 0; i < application.Producer.getHeaders().length; i++) {
				dtm.addColumn(application.Producer.getHeaders()[i]);
			}
			for (int i = 0; i < application.DB.getProducers().size(); i++) {
				dtm.addRow(application.DB.getProducers().get(i).tableRow());
			}
		} else if (type.equalsIgnoreCase("actor")) {
			dtm = (DefaultTableModel) Tablee.getModel();
			dtm.setColumnCount(0);
			dtm.setRowCount(0);
			for (int i = 0; i < application.Actor.getHeaders().length; i++) {
				dtm.addColumn(application.Actor.getHeaders()[i]);
			}
			for (int i = 0; i < application.DB.getActors().size(); i++) {
				dtm.addRow(application.DB.getActors().get(i).tableRow());
			}
		} else if (type.equalsIgnoreCase("manager")) {
			dtm = (DefaultTableModel) Tablee.getModel();
			dtm.setColumnCount(0);
			dtm.setRowCount(0);
			for (int i = 0; i < application.Manager.getHeaders().length; i++) {
				dtm.addColumn(application.Manager.getHeaders()[i]);
			}
			for (int i = 0; i < application.DB.getManagers().size(); i++) {
				dtm.addRow(application.DB.getManagers().get(i).tableRow());
			}
		} else if (type.equalsIgnoreCase("studio")) {
			dtm = (DefaultTableModel) Tablee.getModel();
			dtm.setColumnCount(0);
			dtm.setRowCount(0);
			for (int i = 0; i < application.Studio.getHeaders().length; i++) {
				dtm.addColumn(application.Studio.getHeaders()[i]);
			}
			for (int i = 0; i < application.DB.getStudios().size(); i++) {
				dtm.addRow(application.DB.getStudios().get(i).tableRow());
			}
		} else if (type.equalsIgnoreCase("movie")) {
			dtm = (DefaultTableModel) Tablee.getModel();
			dtm.setColumnCount(0);
			dtm.setRowCount(0);
			for (int i = 0; i < application.Movie.getHeaders().length; i++) {
				dtm.addColumn(application.Movie.getHeaders()[i]);
			}
			for (int i = 0; i < application.DB.getMovies().size(); i++) {
				dtm.addRow(application.DB.getMovies().get(i).tableRow());
			}
		} else if (type.equalsIgnoreCase("admin")) {
			dtm = (DefaultTableModel) Tablee.getModel();
			dtm.setColumnCount(0);
			dtm.setRowCount(0);
			for (int i = 0; i < application.Admin.getHeaders().length; i++) {
				dtm.addColumn(application.Admin.getHeaders()[i]);
			}
			for (int i = 0; i < application.DB.getAdmins().size(); i++) {
				dtm.addRow(application.DB.getAdmins().get(i).tableRow());
			}
		} else if (type.equalsIgnoreCase("employee")) {
			dtm = (DefaultTableModel) Tablee.getModel();
			dtm.setColumnCount(0);
			dtm.setRowCount(0);
			for (int i = 0; i < application.Employee.getHeaders().length; i++) {
				dtm.addColumn(application.Employee.getHeaders()[i]);
			}
			for (int i = 0; i < application.DB.getEmployees().size(); i++) {
				dtm.addRow(application.DB.getEmployees().get(i).tableRow());
			}
		} else if (type.equalsIgnoreCase("Contract")) {
			dtm = (DefaultTableModel) Tablee.getModel();
			dtm.setColumnCount(0);
			dtm.setRowCount(0);
			for (int i = 0; i < application.Contract.getHeaders().length; i++) {
				dtm.addColumn(application.Contract.getHeaders()[i]);
			}
			for (int i = 0; i < application.DB.getContracts().size(); i++) {
				dtm.addRow(application.DB.getContracts().get(i).tableRow());
			}
		}
	}

	public AdminPanel() {

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 128), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(55, 93, 877, 426);
		contentPane.add(scrollPane);

		Tablee = new JTable() {
            public TableCellEditor getCellEditor(int row, int column)
            {
                int col = convertColumnIndexToModel(column);
                if (col == 14 && selectedRadioButton.equals("Actor"))
                {
                	JFileChooser fileChooser = new JFileChooser();
                    fileChooser.addChoosableFileFilter(new ImageFilter());
                    fileChooser.setAcceptAllFileFilterUsed(false);
                    JFrame frame = new JFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   

                    int option = fileChooser.showOpenDialog(frame);
                    if(option == JFileChooser.APPROVE_OPTION){
                       
                       Tablee.setValueAt(fileChooser.getSelectedFile().getName(), row, column);
                       
					try {
						URL imageURL = new URL("file:"+fileChooser.getSelectedFile().getPath().toString());
						 BufferedImage bi = ImageIO.read(imageURL);
	                        
	                       ImageIO.write(bi, "png", new File(System.getProperty("user.dir") +"\\src\\res\\"+ fileChooser.getSelectedFile().getName()));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    }else{
                    	  Tablee.setValueAt("-", row, column);
                    }
                    return super.getCellEditor(row, column);
                }
                else
                    return super.getCellEditor(row, column);
            }
		};
		Tablee.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		Tablee.setEditingRow(1);
		Tablee.setEditingColumn(1);

		Tablee.setModel(new DefaultTableModel(new Object[][] { { null }, }, new String[] { "New column" }));

		Tablee.getColumnModel().getColumn(0).setPreferredWidth(175);
		scrollPane.setViewportView(Tablee);

		dtm = (DefaultTableModel) Tablee.getModel();

		JButton insertSelectedItem = new JButton("ADD");
		insertSelectedItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtm = (DefaultTableModel) Tablee.getModel();
				String[] str = new String[dtm.getColumnCount()];
				dtm.addRow(str);
			}
		});
		insertSelectedItem.setAlignmentX(12.0f);
		insertSelectedItem.setForeground(new Color(0, 0, 0));
		insertSelectedItem.setBackground(new Color(255, 255, 255));
		insertSelectedItem.setFont(new Font("Arial", Font.PLAIN, 14));
		insertSelectedItem.setBounds(340, 516, 126, 35);
		contentPane.add(insertSelectedItem);

		JLabel lblNewLabel = new JLabel("ADMIN PANEL");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel.setBounds(20, 10, 232, 50);
		contentPane.add(lblNewLabel);

		adminButton = new JRadioButton("ADMIN");
		adminButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRadioButton = "Admin";
				tableContent(selectedRadioButton);
			}
		});
		adminButton.setOpaque(false);
		adminButton.setBackground(new Color(0, 139, 139));
		adminButton.setBounds(265, 66, 103, 21);
		contentPane.add(adminButton);

		actorButton = new JRadioButton("ACTOR");
		actorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRadioButton = "Actor";
				tableContent(selectedRadioButton);
			}
		});
		actorButton.setOpaque(false);
		actorButton.setBounds(160, 66, 103, 21);
		contentPane.add(actorButton);

		managerButton = new JRadioButton("MANAGER");
		managerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRadioButton = "Manager";
				tableContent(selectedRadioButton);
			}
		});
		managerButton.setOpaque(false);
		managerButton.setBounds(370, 66, 103, 21);
		contentPane.add(managerButton);

		producerButton = new JRadioButton("PRODUCER");
		producerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRadioButton = "Producer";
				tableContent(selectedRadioButton);
			}
		});
		producerButton.setOpaque(false);
		producerButton.setBounds(55, 66, 103, 21);
		contentPane.add(producerButton);

		movieButton = new JRadioButton("MOVIE");
		movieButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRadioButton = "Movie";
				tableContent(selectedRadioButton);
			}
		});
		movieButton.setOpaque(false);
		movieButton.setBounds(475, 66, 103, 21);
		contentPane.add(movieButton);

		studioButton = new JRadioButton("STUDIO");
		studioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRadioButton = "STUDIO";
				tableContent(selectedRadioButton);
			}
		});
		studioButton.setOpaque(false);
		studioButton.setBounds(580, 66, 103, 21);
		contentPane.add(studioButton);

		employeeButton = new JRadioButton("EMPLOYEE");
		employeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRadioButton = "EMPLOYEE";
				tableContent(selectedRadioButton);
			}
		});
		employeeButton.setOpaque(false);
		employeeButton.setBounds(685, 66, 103, 21);
		contentPane.add(employeeButton);
		
		contractButton = new JRadioButton("CONTRACT");
		contractButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRadioButton = "CONTRACT";
				tableContent(selectedRadioButton);
			}
		});
		contractButton.setOpaque(false);
		contractButton.setBounds(790, 66, 103, 21);
		contentPane.add(contractButton);

		ButtonGroup group = new ButtonGroup();
		group.add(contractButton);
		group.add(actorButton);
		group.add(managerButton);
		group.add(producerButton);
		group.add(movieButton);
		group.add(studioButton);
		group.add(employeeButton);
		group.add(contractButton);
		group.add(adminButton);
		
		JButton updateSelectedItem = new JButton("UPDATE");
		updateSelectedItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean flag = true;
				for (int i = 0; i < dtm.getRowCount(); i++) {
					for (int j = 0; j < dtm.getColumnCount(); j++) {
						dtm.fireTableCellUpdated(i, j);
						if (dtm.getValueAt(i, j) == null) {
							flag = false;
							break;
						}
					}
				}
				if (flag) {
					application.DB.deleteDB(selectedRadioButton);
					for (int i = 0; i < dtm.getRowCount(); i++) {
						String[] str = new String[dtm.getColumnCount()];
						for (int j = 0; j < str.length; j++) {
							dtm.fireTableCellUpdated(i, j);
							if(dtm.getValueAt(i, j).toString().equals("")) {
								str[j] = "-";
							}
							else {
								str[j] = dtm.getValueAt(i, j).toString();
							}
						}
						if (selectedRadioButton.equalsIgnoreCase("producer")) {
							application.Producer obj = new application.Producer(str);
							application.DB.getProducers().add(obj);
						} else if (selectedRadioButton.equalsIgnoreCase("actor")) {
							application.Actor obj = new application.Actor(str);
							application.DB.getActors().add(obj);
						} else if (selectedRadioButton.equalsIgnoreCase("manager")) {
							application.Manager obj = new application.Manager(str);
							application.DB.getManagers().add(obj);
						} else if (selectedRadioButton.equalsIgnoreCase("studio")) {
							application.Studio obj = new application.Studio(str);
							application.DB.getStudios().add(obj);
						} else if (selectedRadioButton.equalsIgnoreCase("movie")) {
							application.Movie obj = new application.Movie(str);
							application.DB.getMovies().add(obj);
						} else if (selectedRadioButton.equalsIgnoreCase("admin")) {
							application.Admin obj = new application.Admin(str);
							application.DB.getAdmins().add(obj);
						} else if (selectedRadioButton.equalsIgnoreCase("employee")) {
							application.Employee obj = new application.Employee(str);
							application.DB.getEmployees().add(obj);
						} else if (selectedRadioButton.equalsIgnoreCase("contract")) {
							application.Contract obj = new application.Contract(str);
							application.DB.getContracts().add(obj);
						} 
					}
					try {
						if (selectedRadioButton.equalsIgnoreCase("producer")) {
							application.DB.writeDBFile("producers.txt", "producer");
						} else if (selectedRadioButton.equalsIgnoreCase("actor")) {
							application.DB.writeDBFile("actors.txt", "actor");
						} else if (selectedRadioButton.equalsIgnoreCase("manager")) {
							application.DB.writeDBFile("managers.txt", "manager");
						} else if (selectedRadioButton.equalsIgnoreCase("studio")) {
							application.DB.writeDBFile("studios.txt", "studio");
						} else if (selectedRadioButton.equalsIgnoreCase("movie")) {
							application.DB.writeDBFile("movies.txt", "movie");
						} else if (selectedRadioButton.equalsIgnoreCase("admin")) {
							application.DB.writeDBFile("admins.txt", "admin");
						} else if (selectedRadioButton.equalsIgnoreCase("employee")) {
							application.DB.writeDBFile("employees.txt", "employee");
						} else if (selectedRadioButton.equalsIgnoreCase("contract")) {
							application.DB.writeDBFile("contract.txt", "contract");
						}
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			

			}
		});
		updateSelectedItem.setForeground(Color.BLACK);
		updateSelectedItem.setFont(new Font("Arial", Font.PLAIN, 14));
		updateSelectedItem.setBackground(Color.WHITE);
		updateSelectedItem.setAlignmentX(12.0f);
		updateSelectedItem.setBounds(498, 516, 126, 35);
		contentPane.add(updateSelectedItem);
		
		JButton deleteSelectedItem = new JButton("DELETE");
		deleteSelectedItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedRadioButton.equalsIgnoreCase("producer")) {
					application.DB.getProducers().remove(Tablee.getSelectedRow());
					try {
						application.DB.writeDBFile("producers.txt", "Producer");
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (selectedRadioButton.equalsIgnoreCase("actor")) {
					application.DB.getActors().remove(Tablee.getSelectedRow());
					try {
						application.DB.writeDBFile("actors.txt", "Actor");
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (selectedRadioButton.equalsIgnoreCase("manager")) {
					application.DB.getManagers().remove(Tablee.getSelectedRow());
					try {
						application.DB.writeDBFile("managers.txt", "Manager");
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (selectedRadioButton.equalsIgnoreCase("studio")) {
					application.DB.getManagers().remove(Tablee.getSelectedRow());
					try {
						application.DB.writeDBFile("studios.txt", "Studio");
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (selectedRadioButton.equalsIgnoreCase("movie")) {
					application.DB.getMovies().remove(Tablee.getSelectedRow());
					try {
						application.DB.writeDBFile("movies.txt", "Movie");
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (selectedRadioButton.equalsIgnoreCase("admin")) {
					application.DB.getAdmins().remove(Tablee.getSelectedRow());
					try {
						application.DB.writeDBFile("admins.txt", "Admin");
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (selectedRadioButton.equalsIgnoreCase("employee")) {
					application.DB.getEmployees().remove(Tablee.getSelectedRow());
					try {
						application.DB.writeDBFile("employees.txt", "Employee");
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (selectedRadioButton.equalsIgnoreCase("contract")) {
					application.DB.getEmployees().remove(Tablee.getSelectedRow());
					try {
						application.DB.writeDBFile("contract.txt", "Contract");
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} 
				dtm.removeRow(Tablee.getSelectedRow());
			}
		});
		deleteSelectedItem.setForeground(Color.BLACK);
		deleteSelectedItem.setFont(new Font("Arial", Font.PLAIN, 14));
		deleteSelectedItem.setBackground(Color.WHITE);
		deleteSelectedItem.setAlignmentX(12.0f);
		deleteSelectedItem.setBounds(651, 516, 126, 35);
		contentPane.add(deleteSelectedItem);

		JButton showSelectedItem = new JButton("SHOW");
		showSelectedItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedRadioButton.equalsIgnoreCase("Actor")) {
					ActorInfo.openActorInfo(Tablee.getSelectedRow());
				}
				if(selectedRadioButton.equalsIgnoreCase("Employee")) {
					EmployeeInfo.openEmployeeInfo(Tablee.getSelectedRow());
				}
				if(selectedRadioButton.equalsIgnoreCase("Studio")) {
					StudioInfo.openStudioInfo(Tablee.getSelectedRow());
				}
				if(selectedRadioButton.equalsIgnoreCase("Producer")) {
					AccountInfo.openAccountInfo(Tablee.getSelectedRow());
				}
				if(selectedRadioButton.equalsIgnoreCase("Manager")) {
					ManagerInfo.openManagerInfo(Tablee.getSelectedRow());
				}
				if(selectedRadioButton.equalsIgnoreCase("Movie")) {
					MovieAddedInfo.openMovieAddedInfo(Tablee.getSelectedRow());
				}
		
			}
		});
		showSelectedItem.setForeground(Color.BLACK);
		showSelectedItem.setFont(new Font("Arial", Font.PLAIN, 14));
		showSelectedItem.setBackground(Color.WHITE);
		showSelectedItem.setAlignmentX(12.0f);
		showSelectedItem.setBounds(806, 516, 126, 35);
		contentPane.add(showSelectedItem);
		
		btnLogout = new JButton("Logout");
		btnLogout.setFont(new Font("Arial", Font.BOLD, 16));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginScreen.main(null);
				application.DB.setAdminID(-1);
				dispose();
			}
		});
		btnLogout.setBounds(822, 619, 110, 27);
		contentPane.add(btnLogout);
		

	}
}