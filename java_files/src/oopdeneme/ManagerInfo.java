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

public class ManagerInfo extends JFrame {
	private Image img = new ImageIcon(LoginScreen.class.getResource("/res/manager.png")).getImage().getScaledInstance(170, 170, Image.SCALE_SMOOTH);

	private JPanel contentPane;
	private JTable elementofManagerInfo;
	private JTable managerInfos;
	private String lastSelectedComboBox = "";
	/**
	 * Launch the application.
	 */
	public static void openManagerInfo(int managerID) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerInfo frame = new ManagerInfo(managerID);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	private int findContract(String actorToProducer,int studioID) {
		int size = application.DB.getManagers().get(studioID).getContractIDs().size();
		int id = 0;
		for (int i = 0; i < size; i++) {
			if((application.DB.getContracts().get(i).getContractActorID() + " " + application.DB.getContracts().get(i).getContractProducerID()).equalsIgnoreCase(actorToProducer)) {
				id = application.DB.getContracts().get(i).getContractID();
				break;
			}
		}
		return id;
	}
	
	/**
	 * Create the frame.
	 */
	public ManagerInfo(int managerID) {		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 128), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(37, 10, 200, 182);
		lblNewLabel.setIcon(new ImageIcon(img));
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 220, 200, 200);
		contentPane.add(scrollPane);
		

		

		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(277, 10, 387, 410);
		contentPane.add(scrollPane_1);
		


		
		
		String[] contracts = new String[application.DB.getManagers().get(managerID).getContractIDs().size()];
		int contractSize = application.DB.getManagers().get(managerID).getContractIDs().size();
		for (int i = 0; i < contractSize; i++) {
			int contractID = application.DB.getManagers().get(managerID).getContractIDs().get(i);
			contracts[i] = application.DB.getContracts().get(contractID).getContractActorID() + " " + application.DB.getContracts().get(contractID).getContractProducerID();
		}
		
		String[][] data = new String[application.Manager.getHeaders().length][2];
		String[] columns = {"Property", "Value"};
		for (int i = 0; i < application.Manager.getHeaders().length; i++) {
			String[] rowData = {application.Manager.getHeaders()[i],application.DB.getManagers().get(managerID).tableRow()[i]};
			data[i] = rowData;
		}
        DefaultTableModel dtmManagerInfos = new DefaultTableModel(data, columns);
        JTable managerInfos = new JTable(dtmManagerInfos)
        {
            // determine which editor to use by JTable
            public TableCellEditor getCellEditor(int row, int column)
            {
                int col = convertColumnIndexToModel(column);
                if (col == 1 && row == 10)
                {
                    JComboBox<String> cb = new JComboBox<String>(contracts);
                    return new DefaultCellEditor(cb);
                }
                else
                    return super.getCellEditor(row, column);
            }
        };
		scrollPane_1.setViewportView(managerInfos);
		
		elementofManagerInfo = new JTable();		
		elementofManagerInfo.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		elementofManagerInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//
			}
		});
		elementofManagerInfo.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
			},
			new String[] {
				"New column"
			}
		));
		scrollPane.setViewportView(elementofManagerInfo);
		DefaultTableModel dtmElementOfManagerInfo = (DefaultTableModel) elementofManagerInfo.getModel();
		dtmElementOfManagerInfo.setColumnCount(0);
		dtmElementOfManagerInfo.setRowCount(0);
		dtmElementOfManagerInfo.addColumn("");
		dtmElementOfManagerInfo.addColumn("");
		
		JButton getContractInfo = new JButton("Info");
		getContractInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(managerInfos.getSelectedRow() == 10) {
					String title = dtmManagerInfos.getValueAt(10, 1).toString();
					int contractID = findContract(title, managerID);
					dtmElementOfManagerInfo.setColumnCount(0);
					dtmElementOfManagerInfo.setRowCount(0);
					dtmElementOfManagerInfo.addColumn("");
					dtmElementOfManagerInfo.addColumn("");
					for (int i = 0; i < application.Contract.getHeaders().length; i++) {
						String[] rowData = {application.Contract.getHeaders()[i],application.DB.getContracts().get(contractID).tableRow()[i]};
						dtmElementOfManagerInfo.addRow(rowData);
					}
				}
				
			}
		});
		getContractInfo.setFont(new Font("Arial", Font.PLAIN, 14));
		getContractInfo.setBounds(579, 432, 85, 21);
		contentPane.add(getContractInfo);
		
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