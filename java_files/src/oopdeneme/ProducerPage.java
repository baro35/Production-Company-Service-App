package oopdeneme;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JTextPane;

public class ProducerPage extends JFrame {

	private JPanel contentPane;
	private JTable listCrew;
	private JTable selectedCrew;
	private String lastSelectedComboBox = "";
	private int producerID;
	private ArrayList<application.Employee> selectedEmployees = new ArrayList<>();
	private ArrayList<application.Actor> selectedActors = new ArrayList<>();
	private int selectedStudio = -1;
	/**
	 * Launch the application.
	 */
	public static void openProducerPage(int producerID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProducerPage frame = new ProducerPage(producerID);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private int getMovieStar() {
        int returnValue = 0;

        for (int i = 0; i < selectedActors.size(); i++) {
            returnValue += application.DB.getActors().get(i).getStar();
        }
        for (int i = 0; i < selectedEmployees.size(); i++) {
            returnValue += application.DB.getEmployees().get(i).getStar();
        }
        returnValue = returnValue / (selectedActors.size() + selectedEmployees.size());
        return returnValue;
    }
	
	private boolean actorIsAvailable(int actorID,String day,String month,String year) {
		int contractSize = application.DB.getContracts().size();
		boolean flag = true;
		for (int i = 0; i < contractSize; i++) {
			if(application.DB.getContracts().get(i).getContractActorID() == actorID){
				if(Integer.parseInt(application.DB.getContracts().get(i).getValidUntil().getYear()) > Integer.parseInt(year))
					flag = false;
				else if(Integer.parseInt(application.DB.getContracts().get(i).getValidUntil().getYear()) == Integer.parseInt(year) && Integer.parseInt(application.DB.getContracts().get(i).getValidUntil().getMonth()) > Integer.parseInt(month))
					flag = false;
				else if(Integer.parseInt(application.DB.getContracts().get(i).getValidUntil().getYear()) == Integer.parseInt(year) && Integer.parseInt(application.DB.getContracts().get(i).getValidUntil().getMonth()) == Integer.parseInt(month) && Integer.parseInt(application.DB.getContracts().get(i).getValidUntil().getDay()) >= Integer.parseInt(day))
					flag = false;
			}
		}
		return flag;
	}
	
	private void addMovieToCrew(int movieID) {
        for (int i = 0; i <selectedEmployees.size() ; i++) {
            application.DB.getEmployees().get(selectedEmployees.get(i).getEmployeeID()).getMovieIDs().add(movieID);
        }
        for (int i = 0; i <selectedActors.size() ; i++) {
            application.DB.getActors().get(selectedActors.get(i).getActorID()).getMovieIDs().add(movieID);
        }
        application.DB.getStudios().get(selectedStudio).getMovieIDs().add(movieID);
        application.DB.getProducers().get(producerID).getMovieIDs().add(movieID);
    }
	
	private String objListToTextForWriteFile(String type) {
		String IDsText = null;
		if(type.equalsIgnoreCase("Actor")) {
			boolean flag = true;
			for (int i = 0; i < selectedActors.size(); i++) {
				flag = false;
				if(IDsText != null)
					IDsText = IDsText + selectedActors.get(i).getActorID();
				else
					IDsText = Integer.toString(selectedActors.get(i).getActorID());
				if(i+1<selectedActors.size())
					IDsText = IDsText + " ";
			}
			if(flag)
				IDsText = "-";
		
		} else if(type.equalsIgnoreCase("Employee")) {
			boolean flag = true;
			for (int i = 0; i < selectedEmployees.size(); i++) {
				flag = false;
				if(IDsText != null)
					IDsText = IDsText + selectedEmployees.get(i).getEmployeeID();
				else
					IDsText = Integer.toString(selectedEmployees.get(i).getEmployeeID());
				if(i+1<selectedEmployees.size())
					IDsText = IDsText + " ";
			}
			if(flag)
				IDsText = "-";
		}
		return IDsText;
	}
	
	private void deleteSelectedElement(String type, String name, String surname) {
		if(type.equalsIgnoreCase("Actor")) {
			for (int i = 0; i < selectedActors.size(); i++) {
				if(selectedActors.get(i).getName().equalsIgnoreCase(name) && selectedActors.get(i).getSurname().equalsIgnoreCase(surname))
					selectedActors.remove(i);
			}
		}else if(type.equalsIgnoreCase("Studio")) {
			selectedStudio = -1;
		}else if(type.equalsIgnoreCase("Employee")) {
			for (int i = 0; i < selectedEmployees.size(); i++) {
				if(selectedEmployees.get(i).getName().equalsIgnoreCase(name) && selectedEmployees.get(i).getSurname().equalsIgnoreCase(surname))
					selectedEmployees.remove(i);
			}
		}
	}
	
	private String[] allObjectName(String type) {
		String[] st;
		if(type.equalsIgnoreCase("Studio")) {
			st = new String[application.DB.getStudios().size()];
			for (int i = 0; i < application.DB.getStudios().size(); i++) {
				st[i] = (i+1) + "." + application.DB.getStudios().get(i).getTradeName();
			}
		}else if(type.equalsIgnoreCase("Actor")) {
			st = new String[application.DB.getActors().size()];
			for (int i = 0; i < application.DB.getActors().size(); i++) {
				st[i] = (i+1) + "." + application.DB.getActors().get(i).getName() + " " + application.DB.getActors().get(i).getSurname();
			}
		}else if(type.equalsIgnoreCase("Employee")) {
			st = new String[application.DB.getEmployees().size()];
			for (int i = 0; i < application.DB.getEmployees().size(); i++) {
				st[i] = (i+1) + "." +application.DB.getEmployees().get(i).getWorkArea()+":"+ application.DB.getEmployees().get(i).getName() + " " + application.DB.getEmployees().get(i).getSurname();
			}
		}else {
			st = new String[1];
			st[0] = "Invalid";
		}
		return st;
	}

	/**
	 * Create the frame.
	 */
	public ProducerPage(int producerID) {
		
		
		this.producerID = producerID;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 128), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(315, 75, 303, 426);
		contentPane.add(scrollPane);
		
		listCrew = new JTable();
		listCrew.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
			},
			new String[] {
				"New column"
			}
		));
		scrollPane.setViewportView(listCrew);
		

		

		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(642, 75, 303, 426);
		contentPane.add(scrollPane_1);
		
		selectedCrew = new JTable();
		selectedCrew.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column"
			}
		));
		selectedCrew.getColumnModel().getColumn(0).setPreferredWidth(45);
		selectedCrew.getColumnModel().getColumn(1).setPreferredWidth(175);
		scrollPane_1.setViewportView(selectedCrew);
		DefaultTableModel dtmListCrew = (DefaultTableModel) listCrew.getModel();
		dtmListCrew.setColumnCount(0);
		dtmListCrew.setRowCount(0);
		
		
		DefaultTableModel dtmSelectedCrew = (DefaultTableModel) selectedCrew.getModel();
		dtmSelectedCrew.setColumnCount(0);
		dtmSelectedCrew.setRowCount(0);
    	dtmSelectedCrew.addColumn("");
    	dtmSelectedCrew.addColumn("");
    	

		JComboBox startDateDay = new JComboBox();
		String[] dates = new String[31];
		for (int i = 1; i <= 31; i++) {
			dates[i-1] = String.valueOf(i);
		}
		startDateDay.setModel(new DefaultComboBoxModel(dates));
		startDateDay.setBounds(129, 120, 40, 21);
		contentPane.add(startDateDay);
		
		JComboBox endDateDay = new JComboBox();
		endDateDay.setBounds(129, 159, 40, 21);
		endDateDay.setModel(new DefaultComboBoxModel(dates));
		contentPane.add(endDateDay);
		
		JComboBox startDateMonth = new JComboBox();
		dates = new String[12];
		for (int i = 1; i <= 12; i++) {
			dates[i-1] = String.valueOf(i);
		}
		startDateMonth.setModel(new DefaultComboBoxModel(dates));
		startDateMonth.setBounds(179, 120, 40, 21);
		contentPane.add(startDateMonth);
		
		JComboBox endDateMonth = new JComboBox();
		endDateMonth.setBounds(179, 159, 40, 21);
		endDateMonth.setModel(new DefaultComboBoxModel(dates));
		contentPane.add(endDateMonth);
		
		JComboBox startDateYear = new JComboBox();
		startDateYear.setModel(new DefaultComboBoxModel(new String[] {"2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"}));
		startDateYear.setBounds(224, 120, 55, 21);
		contentPane.add(startDateYear);
		
		JLabel lblStartDate_1_1 = new JLabel("END DATE");
		lblStartDate_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblStartDate_1_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblStartDate_1_1.setBounds(10, 151, 120, 35);
		contentPane.add(lblStartDate_1_1);
		
		JComboBox endDateYear = new JComboBox();
		endDateYear.setModel(new DefaultComboBoxModel(new String[] {"2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"}));
		endDateYear.setBounds(224, 159, 55, 21);
		contentPane.add(endDateYear);
		
		JComboBox studioBox = new JComboBox();
		studioBox.setFont(new Font("Arial", Font.PLAIN, 12));
		studioBox.setModel(new DefaultComboBoxModel(allObjectName("Studio")));
		studioBox.setBounds(159, 370, 120, 21);
		contentPane.add(studioBox);
		studioBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	dtmListCrew.setColumnCount(0);
		    	dtmListCrew.setRowCount(0);
				int dotIndex = studioBox.getSelectedItem().toString().indexOf(".");
		    	String index = (String) studioBox.getSelectedItem().toString().subSequence(0,dotIndex);
		    	dtmListCrew.addColumn("");
		    	dtmListCrew.addColumn("");
		    	lastSelectedComboBox = "Studio";
		    	for (int i = 0; i < application.Studio.getHeaders().length; i++) {
		    		if(application.Studio.getHeaders()[i].equalsIgnoreCase("Movie IDs")) {
		    			for (int j = 0; j < application.DB.getStudios().get(Integer.parseInt(index) - 1).getMovieIDs().size(); j++) {
		    				String[] rowData = {"Movie Name",application.DB.getMovies().get(application.DB.getStudios().get(Integer.parseInt(index) - 1).getMovieIDs().get(j)).getTitle()};
			    			dtmListCrew.addRow(rowData);
						}
		    		}else {
		    			String[] rowData = {application.Studio.getHeaders()[i],application.DB.getStudios().get(Integer.parseInt(index) - 1).tableRow()[i]};
		    			dtmListCrew.addRow(rowData);
		    		}
				}
		    }
		});
		
		
		JComboBox actorBox = new JComboBox();
		actorBox.setFont(new Font("Arial", Font.PLAIN, 12));
		actorBox.setBounds(159, 422, 120, 21);
		actorBox.setModel(new DefaultComboBoxModel(allObjectName("Actor")));
		contentPane.add(actorBox);
		
		actorBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	dtmListCrew.setColumnCount(0);
		    	dtmListCrew.setRowCount(0);
				int dotIndex = actorBox.getSelectedItem().toString().indexOf(".");
		    	String index = (String) actorBox.getSelectedItem().toString().subSequence(0,dotIndex);
		    	dtmListCrew.addColumn("");
		    	dtmListCrew.addColumn("");
		    	lastSelectedComboBox = "Actor";
		    	if(actorIsAvailable(application.DB.getActors().get(Integer.parseInt(index) - 1).getActorID(),startDateDay.getSelectedItem().toString() , startDateMonth.getSelectedItem().toString(), startDateYear.getSelectedItem().toString()))
		    		application.DB.getActors().get(Integer.parseInt(index) - 1).setAvailable(true);
		    	else
		    		application.DB.getActors().get(Integer.parseInt(index) - 1).setAvailable(false);
		    	for (int i = 0; i < application.Actor.getHeaders().length; i++) {
		    		if(application.Actor.getHeaders()[i].equalsIgnoreCase("Movie IDs")) {
		    			for (int j = 0; j < application.DB.getActors().get(Integer.parseInt(index) - 1).getMovieIDs().size(); j++) {
		    				String[] rowData = {"Movie Name",application.DB.getMovies().get(application.DB.getActors().get(Integer.parseInt(index) - 1).getMovieIDs().get(j)).getTitle()};
			    			dtmListCrew.addRow(rowData);
						}
		    		}else if(application.Actor.getHeaders()[i].equalsIgnoreCase("Manager ID")){
		    			String[] rowData = {"Manager Name",application.DB.getManagers().get(application.DB.getActors().get(Integer.parseInt(index) - 1).getManagerID()).getName() + " " + application.DB.getManagers().get(application.DB.getActors().get(Integer.parseInt(index) - 1).getManagerID()).getSurname()};
		    			dtmListCrew.addRow(rowData);
		    		}else {
		    			String[] rowData = {application.Actor.getHeaders()[i],application.DB.getActors().get(Integer.parseInt(index) - 1).tableRow()[i]};
			    		dtmListCrew.addRow(rowData);
		    		}
		  	
				}
		    }
		});
		
		JComboBox employeeBox = new JComboBox();
		employeeBox.setFont(new Font("Arial", Font.PLAIN, 12));
		employeeBox.setBounds(159, 480, 120, 21);
		employeeBox.setModel(new DefaultComboBoxModel(allObjectName("Employee")));
		contentPane.add(employeeBox);

		employeeBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
				dtmListCrew.setColumnCount(0);
				dtmListCrew.setRowCount(0);
				int dotIndex = employeeBox.getSelectedItem().toString().indexOf(".");
		    	String index = (String) employeeBox.getSelectedItem().toString().subSequence(0,dotIndex);
		    	dtmListCrew.addColumn("");
		    	dtmListCrew.addColumn("");
		    	lastSelectedComboBox = "Employee";
		    	for (int i = 0; i < application.Employee.getHeaders().length; i++) {
		    		if(application.Employee.getHeaders()[i].equalsIgnoreCase("Movie IDs")) {
		    			for (int j = 0; j < application.DB.getEmployees().get(Integer.parseInt(index) - 1).getMovieIDs().size(); j++) {
		    				String[] rowData = {"Movie Name",application.DB.getMovies().get(application.DB.getEmployees().get(Integer.parseInt(index) - 1).getMovieIDs().get(j)).getTitle()};
			    			dtmListCrew.addRow(rowData);
						}
		    		}else {
		    			String[] rowData = {application.Employee.getHeaders()[i],application.DB.getEmployees().get(Integer.parseInt(index) - 1).tableRow()[i]};
			    		dtmListCrew.addRow(rowData);
		    		}
		    		
				}
		    }
		});
		

		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lastSelectedComboBox.equalsIgnoreCase("Studio")) {
					int dotIndex = studioBox.getSelectedItem().toString().indexOf(".");
			    	String index = (String) studioBox.getSelectedItem().toString().subSequence(0,dotIndex);		    	
			    	if(selectedStudio == -1) {
				    	String[] rowData = {"Studio",application.DB.getStudios().get(Integer.parseInt(index) - 1).getTradeName()};
				    	dtmSelectedCrew.addRow(rowData);
				    	selectedStudio = application.DB.getStudios().get(Integer.parseInt(index) - 1).getStudioID();
			    	}
				}else if(lastSelectedComboBox.equalsIgnoreCase("Employee")) {
					int dotIndex = employeeBox.getSelectedItem().toString().indexOf(".");
			    	String index = (String) employeeBox.getSelectedItem().toString().subSequence(0,dotIndex);
			    	boolean flag = false;
			    	for (int i = 0; i < selectedEmployees.size(); i++) {
			    		if(selectedEmployees.get(i).getEmployeeID() == application.DB.getEmployees().get(Integer.parseInt(index) - 1).getEmployeeID()) {
							flag = true;
						}
					}
			    	if(!flag) {
			    		String[] rowData = {"Employee",application.DB.getEmployees().get(Integer.parseInt(index) - 1).getName() + " " + application.DB.getEmployees().get(Integer.parseInt(index) - 1).getSurname()};
				    	dtmSelectedCrew.addRow(rowData);
				    	selectedEmployees.add(application.DB.getEmployees().get(Integer.parseInt(index) - 1));
			    	}
				}else if(lastSelectedComboBox.equalsIgnoreCase("Actor")) {
					int dotIndex = actorBox.getSelectedItem().toString().indexOf(".");
			    	String index = (String) actorBox.getSelectedItem().toString().subSequence(0,dotIndex);
			    	boolean flag = false;
			    	for (int i = 0; i < selectedActors.size(); i++) {
						if(selectedActors.get(i).getActorID() == application.DB.getActors().get(Integer.parseInt(index) - 1).getActorID()) {
							flag = true;
						}
					}
			    	
			    	if(!flag && actorIsAvailable(application.DB.getActors().get(Integer.parseInt(index) - 1).getActorID(),startDateDay.getSelectedItem().toString() , startDateMonth.getSelectedItem().toString(), startDateYear.getSelectedItem().toString())) {
			    		String[] rowData = {"Actor",application.DB.getActors().get(Integer.parseInt(index) - 1).getName() + " " + application.DB.getActors().get(Integer.parseInt(index) - 1).getSurname()};
				    	dtmSelectedCrew.addRow(rowData);
				    	selectedActors.add(application.DB.getActors().get(Integer.parseInt(index) - 1));
			    	}
			    	else JOptionPane.showMessageDialog(contentPane, "Actor is not available!", "Error!", JOptionPane.ERROR_MESSAGE);

				}
				
			}
		});
		btnAdd.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAdd.setBounds(501, 503, 120, 30);
		contentPane.add(btnAdd);
		
		JLabel lblNewLabel = new JLabel("STUDIO");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(23, 356, 120, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblActor = new JLabel("ACTOR");
		lblActor.setHorizontalAlignment(SwingConstants.CENTER);
		lblActor.setFont(new Font("Arial", Font.BOLD, 14));
		lblActor.setBounds(23, 407, 120, 35);
		contentPane.add(lblActor);
		
		JLabel lblEmloyee = new JLabel("EMPLOYEE");
		lblEmloyee.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmloyee.setFont(new Font("Arial", Font.BOLD, 14));
		lblEmloyee.setBounds(23, 466, 120, 35);
		contentPane.add(lblEmloyee);
		
		JLabel lblNewLabel_1 = new JLabel("SELECTED ELEMENT INFORMATION");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblNewLabel_1.setBounds(313, 10, 295, 55);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("SUMMARY");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(642, 9, 303, 55);
		contentPane.add(lblNewLabel_2);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dtmSelectedCrew.getValueAt(selectedCrew.getSelectedRow(), 0).toString().equalsIgnoreCase("Actor")) {
					String[] nameSurname = dtmSelectedCrew.getValueAt(selectedCrew.getSelectedRow(), 1).toString().split(" ");
					deleteSelectedElement("Actor", nameSurname[0], nameSurname[1]);
				}else if(dtmSelectedCrew.getValueAt(selectedCrew.getSelectedRow(), 0).toString().equalsIgnoreCase("Studio")) {
					String[] nameSurname = dtmSelectedCrew.getValueAt(selectedCrew.getSelectedRow(), 1).toString().split(" ");
					deleteSelectedElement("Studio", nameSurname[0], "");
				}else if(dtmSelectedCrew.getValueAt(selectedCrew.getSelectedRow(), 0).toString().equalsIgnoreCase("Employee")) {
					String[] nameSurname = dtmSelectedCrew.getValueAt(selectedCrew.getSelectedRow(), 1).toString().split(" ");
					deleteSelectedElement("Employee", nameSurname[0], nameSurname[1]);
				}
				dtmSelectedCrew.removeRow(selectedCrew.getSelectedRow());
			}
		});
		btnDelete.setFont(new Font("Arial", Font.BOLD, 12));
		btnDelete.setBounds(642, 503, 120, 30);
		contentPane.add(btnDelete);
		
		JTextPane title = new JTextPane();
		title.setBounds(159, 75, 120, 21);
		contentPane.add(title);
		
		JLabel lblTitle = new JLabel("TITLE");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 14));
		lblTitle.setBounds(23, 61, 120, 35);
		contentPane.add(lblTitle);
		
		JLabel lblGenre = new JLabel("GENRE");
		lblGenre.setHorizontalAlignment(SwingConstants.CENTER);
		lblGenre.setFont(new Font("Arial", Font.BOLD, 14));
		lblGenre.setBounds(23, 196, 120, 35);
		contentPane.add(lblGenre);
		
		JLabel lblStartDate_1 = new JLabel("START DATE");
		lblStartDate_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblStartDate_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblStartDate_1.setBounds(10, 112, 120, 35);
		contentPane.add(lblStartDate_1);
		
		JTextPane genre = new JTextPane();
		genre.setBounds(159, 210, 120, 21);
		contentPane.add(genre);
		

		
		JLabel lblDuration = new JLabel("DURATION");
		lblDuration.setHorizontalAlignment(SwingConstants.CENTER);
		lblDuration.setFont(new Font("Arial", Font.BOLD, 14));
		lblDuration.setBounds(23, 251, 120, 35);
		contentPane.add(lblDuration);
		
		JTextPane duration = new JTextPane();
		duration.setBounds(159, 265, 120, 21);
		contentPane.add(duration);
		
		JLabel lblBudget = new JLabel("BUDGET");
		lblBudget.setHorizontalAlignment(SwingConstants.CENTER);
		lblBudget.setFont(new Font("Arial", Font.BOLD, 14));
		lblBudget.setBounds(23, 307, 120, 35);
		contentPane.add(lblBudget);
		
		JTextPane budget = new JTextPane();
		budget.setBounds(159, 321, 120, 21);
		contentPane.add(budget);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setFont(new Font("Arial", Font.BOLD, 16));
		btnBack.setBounds(10, 523, 103, 30);
		contentPane.add(btnBack);
		

		
		JButton createMovie = new JButton("PRODUCE MOVIE");
		createMovie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(title.getText().equalsIgnoreCase("") || genre.getText().equalsIgnoreCase("") || duration.getText().equalsIgnoreCase("") || selectedActors.size() ==0 || selectedEmployees.size() == 0 || selectedStudio == -1) {
					JOptionPane.showMessageDialog(contentPane, "Incomplete Information!", "Error!", JOptionPane.ERROR_MESSAGE);
				}else {
					String[] str = {title.getText(),startDateDay.getSelectedItem().toString()+":"+startDateMonth.getSelectedItem().toString()+":"+startDateYear.getSelectedItem().toString()
							,endDateDay.getSelectedItem().toString()+":"+endDateMonth.getSelectedItem().toString()+":"+endDateYear.getSelectedItem().toString()
							,genre.getText(),duration.getText(),budget.getText(),"-1","-1",Integer.toString(producerID)
							,Integer.toString(selectedStudio),objListToTextForWriteFile("Actor"),objListToTextForWriteFile("Employee")};
					application.Movie movie = new application.Movie(str);
					movie.setExpectedStar(getMovieStar());
					movie.setBoxOffice(getMovieStar() * movie.getBudget());
					application.DB.getMovies().add(movie);
					MovieAddedInfo.openMovieAddedInfo(movie.getMovieID());
					addMovieToCrew(movie.getMovieID());
					
					for (int i = 0; i < selectedActors.size(); i++) {
						application.DB.getActors().get(selectedActors.get(i).getActorID()).setAvailable(false);
						String[] contractInfo = {startDateDay.getSelectedItem().toString()+":"+startDateMonth.getSelectedItem().toString()+":"+startDateYear.getSelectedItem().toString()	
								,endDateDay.getSelectedItem().toString()+":"+endDateMonth.getSelectedItem().toString()+":"+endDateYear.getSelectedItem().toString()
								,Integer.toString(selectedActors.get(i).getActorID())
								,Integer.toString(producerID)};
						application.Contract newContract = new application.Contract(contractInfo);
						application.DB.getProducers().get(producerID).getContractIDs().add(newContract.getContractID());
						application.DB.getManagers().get(application.DB.getActors().get(selectedActors.get(i).getActorID()).getManagerID()).getContractIDs().add(newContract.getContractID());
						application.DB.getContracts().add(newContract);
						
					}
					try {
						application.DB.writeAllFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					dispose();
				}

			}
		});
		createMovie.setFont(new Font("Arial", Font.BOLD, 14));
		createMovie.setBounds(780, 503, 165, 30);
		contentPane.add(createMovie);
		
	}
}