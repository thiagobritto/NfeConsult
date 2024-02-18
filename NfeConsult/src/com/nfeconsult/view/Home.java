package com.nfeconsult.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.nfeconsult.controller.NfeController;
import com.nfeconsult.model.NfeModel;

import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPathDir;
	private JTextField txtAddFilter;
	private JTable tabNfeList;

	private DefaultListModel<String> listModelProduct;
	private Integer listIndexSelected;
	private ArrayList<NfeModel> nfeList;

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
		setBounds(100, 100, 470, 305);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 454, 261);
		contentPane.add(panel);
		panel.setLayout(null);

		txtPathDir = new JTextField();
		txtPathDir.setFont(new Font("Arial", Font.PLAIN, 11));
		txtPathDir.setBounds(10, 10, 244, 23);
		panel.add(txtPathDir);
		txtPathDir.setColumns(10);

		JButton btnSelectPathDir = new JButton("Selecione uma pasta");
		btnSelectPathDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("Selecione uma pasta");
				chooser.setApproveButtonText("Selecionar pasta");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				if (chooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {
					txtPathDir.setText(chooser.getSelectedFile().getPath());
				} else {
					JOptionPane.showMessageDialog(panel, "Nenhum diretorio foi selecionado", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnSelectPathDir.setFont(new Font("Arial", Font.BOLD, 11));
		btnSelectPathDir.setBounds(264, 10, 180, 23);
		panel.add(btnSelectPathDir);

		JLabel lblNewLabel = new JLabel("Busca por produto:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel.setBounds(11, 48, 108, 14);
		panel.add(lblNewLabel);

		ActionListener addProductEvent = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String prodName = txtAddFilter.getText().trim().toUpperCase();
				if (!listModelProduct.contains(prodName) && !prodName.isEmpty())
					listModelProduct.addElement(prodName);
				txtAddFilter.setText("");
				txtAddFilter.requestFocus();
			}
		};

		JButton btnAddFilter = new JButton("Adicionar");
		btnAddFilter.addActionListener(addProductEvent);
		btnAddFilter.setFont(new Font("Arial", Font.PLAIN, 11));
		btnAddFilter.setBounds(264, 43, 90, 23);
		panel.add(btnAddFilter);

		JButton btnRemoveFilter = new JButton("Excluir");
		btnRemoveFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listIndexSelected != null) {
					listModelProduct.removeElementAt(listIndexSelected);
					listIndexSelected = null;
				}
			}
		});
		btnRemoveFilter.setFont(new Font("Arial", Font.PLAIN, 11));
		btnRemoveFilter.setBounds(364, 43, 80, 23);
		panel.add(btnRemoveFilter);

		txtAddFilter = new JTextField();
		txtAddFilter.addActionListener(addProductEvent);
		txtAddFilter.setFont(new Font("Arial", Font.PLAIN, 11));
		txtAddFilter.setBounds(120, 43, 134, 23);
		panel.add(txtAddFilter);
		txtAddFilter.setColumns(10);

		JScrollPane scrollPaneFilters = new JScrollPane();
		scrollPaneFilters.setBounds(10, 76, 434, 60);
		panel.add(scrollPaneFilters);

		listModelProduct = new DefaultListModel<String>();

		JList<String> listFilters = new JList<String>(listModelProduct);
		listFilters.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listIndexSelected = listFilters.getSelectedIndex();
			}
		});
		listFilters.setFont(new Font("Arial", Font.PLAIN, 11));
		scrollPaneFilters.setViewportView(listFilters);

		JButton btnFind = new JButton("Buscar");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String path = txtPathDir.getText();
					nfeList = NfeController.getList(path);
					if (nfeList!=null) {
						System.out.println(nfeList.size());
					}
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
		});
		btnFind.setFont(new Font("Arial", Font.PLAIN, 11));
		btnFind.setBounds(369, 231, 75, 23);
		panel.add(btnFind);

		JButton btnGenerateCSV = new JButton("Gerar CSV");
		btnGenerateCSV.setFont(new Font("Arial", Font.PLAIN, 11));
		btnGenerateCSV.setEnabled(false);
		btnGenerateCSV.setBounds(10, 231, 89, 23);
		panel.add(btnGenerateCSV);

		JScrollPane scrollPaneNfe = new JScrollPane();
		scrollPaneNfe.setBounds(10, 146, 434, 75);
		panel.add(scrollPaneNfe);

		tabNfeList = new JTable();
		scrollPaneNfe.setViewportView(tabNfeList);
	}
}
