package com.example.oop.studentview;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.example.oop.model.Student;
import com.example.oop.studentcontroller.StudentDAOImp;
import com.example.oop.studentdb.StudentDb;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentInfoApp {

	private JFrame frmStudentInformation;
	private JTextField txtsName;
	private JTextField txtCourse;
	private JTextField txtFee;
	private JTable table;
	private JTable table_1;
	
	int search;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentInfoApp window = new StudentInfoApp();
					window.frmStudentInformation.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StudentInfoApp() {
		initialize();
		Load();
	}
	
	 public void Load()
	    {
	        StudentDAOImp dao = new StudentDAOImp();
	        List<Student> list = dao.list();
	        DefaultTableModel DFT = (DefaultTableModel) table_1.getModel();
	        DFT.setRowCount(0);
	        for(Student st: list)
	        {
	            int sid = st.getId();
	            String stname = st.getFname();
	            String course = st.getCourse();
	            int fee = st.getFee();
	            DFT.addRow(new Object[]{sid,stname,course,fee});
	        }     
	  
	    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStudentInformation = new JFrame();
		frmStudentInformation.setTitle("Student Information");
		frmStudentInformation.setBounds(100, 100, 978, 456);
		frmStudentInformation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStudentInformation.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), null, null, null));
		panel.setBackground(new Color(0, 149, 221));
		panel.setBounds(0, 0, 1021, 83);
		frmStudentInformation.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Information System");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(43, 20, 354, 30);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setBounds(0, 80, 964, 43);
		frmStudentInformation.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Student st = new Student();
		        String sname = txtsName.getText();
		        String course = txtCourse .getText();
		        int fee = Integer.parseInt(txtFee .getText());
		        
		       
		        st.setFname(sname);
		        st.setCourse(course);
		        st.setFee(fee);
		        
		        StudentDAOImp dao = new StudentDAOImp();
		        dao.save(st);
		        Load();
		        txtsName.setText("");
		        txtCourse.setText("");
		        txtFee.setText(""); 
		        txtsName.requestFocus();
		        
			}
		});
		
		btnSave.setBackground(new Color(172, 255, 172));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSave.setBounds(0, 0, 95, 43);
		panel_1.add(btnSave);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				Student st = new Student();

		        String sname = txtsName.getText();
		        String course = txtCourse .getText();
		        int fee = Integer.parseInt(txtFee.getText());
		        
		        st.setFname(sname);
		        st.setCourse(course);
		        st.setFee(fee);
		        st.setId(search);
		        StudentDAOImp dao = new StudentDAOImp();
		        dao.update(st);
		        Load();
		        
		        txtsName.setText("");
		        txtCourse.setText("");
		        txtFee.setText(""); 
		        txtsName.requestFocus();
			}
		});
		
		btnEdit.setBackground(new Color(193, 224, 255));
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEdit.setBounds(94, 0, 95, 43);
		panel_1.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Student st = new Student();
		        st.setId(search);
		        StudentDAOImp dao = new StudentDAOImp();
		        dao.delete(st);
		        Load();
		        txtsName.setText("");
		        txtCourse.setText("");
		        txtFee.setText(""); 
		        txtsName.requestFocus();
			}
		});
		
		btnDelete.setBackground(new Color(217, 138, 146));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDelete.setBounds(188, 0, 95, 43);
		panel_1.add(btnDelete);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				search = Integer.parseInt(JOptionPane.showInputDialog("Enter Student ID"));
			       
				StudentDAOImp dao = new StudentDAOImp();
				Student st  = dao.get(search);
				        
				txtsName.setText(st.getFname());
				txtCourse.setText(st.getCourse());
				txtFee.setText(String.valueOf(st.getFee()));
			}
		});
		
		btnSearch.setBackground(new Color(255, 255, 217));
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSearch.setBounds(282, 0, 95, 43);
		panel_1.add(btnSearch);
		
		table = new JTable();
		table.setBounds(489, 42, 65, 27);
		panel_1.add(table);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_2.setBounds(0, 121, 486, 281);
		frmStudentInformation.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Student Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(25, 52, 147, 37);
		panel_2.add(lblNewLabel_1);
		
		txtsName = new JTextField();
		txtsName.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtsName.setBounds(204, 52, 272, 37);
		panel_2.add(txtsName);
		txtsName.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Course");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(25, 118, 147, 37);
		panel_2.add(lblNewLabel_1_1);
		
		txtCourse = new JTextField();
		txtCourse.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCourse.setColumns(10);
		txtCourse.setBounds(204, 118, 272, 37);
		panel_2.add(txtCourse);
		
		JLabel lblNewLabel_1_2 = new JLabel("Fee");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(25, 189, 147, 37);
		panel_2.add(lblNewLabel_1_2);
		
		txtFee = new JTextField();
		txtFee.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtFee.setColumns(10);
		txtFee.setBounds(204, 189, 272, 37);
		panel_2.add(txtFee);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_3.setBounds(488, 121, 476, 281);
		frmStudentInformation.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 476, 281);
		panel_3.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Student Name", "Course", "Fee"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table_1);
	}
}
