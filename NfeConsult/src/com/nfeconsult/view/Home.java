package com.nfeconsult.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTable;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPathDir;
	private JTextField txtAddFilter;
	private JTable tabNfeList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setResizable(false);
		setTitle("thiagobritto.github.io - NfeConsult - v1.0.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtPathDir = new JTextField();
		txtPathDir.setBounds(10, 11, 244, 20);
		panel.add(txtPathDir);
		txtPathDir.setColumns(10);
		
		JButton btnSelectPathDir = new JButton("Selecione a pasta");
		btnSelectPathDir.setBounds(264, 10, 160, 23);
		panel.add(btnSelectPathDir);
		
		JLabel lblNewLabel = new JLabel("Busca por produtos");
		lblNewLabel.setBounds(10, 43, 108, 14);
		panel.add(lblNewLabel);
		
		JButton btnAddFilter = new JButton("adicionar");
		btnAddFilter.setBounds(264, 39, 75, 23);
		panel.add(btnAddFilter);
		
		JButton btnRemoveFilter = new JButton("Excluir");
		btnRemoveFilter.setBounds(349, 39, 75, 23);
		panel.add(btnRemoveFilter);
		
		txtAddFilter = new JTextField();
		txtAddFilter.setBounds(110, 39, 144, 20);
		panel.add(txtAddFilter);
		txtAddFilter.setColumns(10);
		
		JScrollPane scrollPaneFilters = new JScrollPane();
		scrollPaneFilters.setBounds(10, 71, 414, 59);
		panel.add(scrollPaneFilters);
		
		JList listFilters = new JList();
		scrollPaneFilters.setViewportView(listFilters);
		
		JButton btnFind = new JButton("Buscar");
		btnFind.setBounds(349, 137, 75, 23);
		panel.add(btnFind);
		
		JButton btnGenerateCSV = new JButton("Gerar CSV");
		btnGenerateCSV.setBounds(10, 137, 89, 23);
		panel.add(btnGenerateCSV);
		
		JScrollPane scrollPaneNfe = new JScrollPane();
		scrollPaneNfe.setBounds(10, 168, 414, 80);
		panel.add(scrollPaneNfe);
		
		tabNfeList = new JTable();
		scrollPaneNfe.setViewportView(tabNfeList);
	}
}
