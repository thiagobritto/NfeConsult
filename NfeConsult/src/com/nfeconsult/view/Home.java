package com.nfeconsult.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.nfeconsult.controller.NfeController;
import com.nfeconsult.model.NfeModel;
import com.nfeconsult.service.TableService;

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
import java.util.ArrayList;
import javax.swing.SwingConstants;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPathDir;
	private JTextField txtAddFilter;
	private JTable tabNfeList;
	private JLabel lblResults;

	private DefaultListModel<String> listModelProduct;
	private Integer listIndexSelected;
	private ArrayList<NfeModel> listNfeModel;
	private DefaultTableModel tableModel = new DefaultTableModel();
	
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
		txtPathDir.setBounds(10, 10, 244, 23);
		txtPathDir.setFont(new Font("Arial", Font.PLAIN, 11));
		panel.add(txtPathDir);
		txtPathDir.setColumns(10);

		JButton btnSelectPathDir = new JButton("Selecione uma pasta");
		btnSelectPathDir.setBounds(264, 10, 180, 23);
		btnSelectPathDir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("Selecione uma pasta");
				chooser.setApproveButtonText("Selecionar pasta");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				if (chooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {
					txtPathDir.setText(chooser.getSelectedFile().getPath());
				} else {
					JOptionPane.showMessageDialog(panel, "Nenhum diretorio foi selecionado!", 
							"Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnSelectPathDir.setFont(new Font("Arial", Font.BOLD, 11));
		panel.add(btnSelectPathDir);

		JLabel lblNewLabel = new JLabel("Busca por produto:");
		lblNewLabel.setBounds(11, 48, 108, 14);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 11));
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
		btnAddFilter.setBounds(264, 43, 90, 23);
		btnAddFilter.addActionListener(addProductEvent);
		btnAddFilter.setFont(new Font("Arial", Font.PLAIN, 11));
		panel.add(btnAddFilter);

		JButton btnRemoveFilter = new JButton("Excluir");
		btnRemoveFilter.setBounds(364, 43, 80, 23);
		btnRemoveFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listIndexSelected != null) {
					listModelProduct.removeElementAt(listIndexSelected);
					listIndexSelected = null;
				}
			}
		});
		btnRemoveFilter.setFont(new Font("Arial", Font.PLAIN, 11));
		panel.add(btnRemoveFilter);

		txtAddFilter = new JTextField();
		txtAddFilter.setBounds(120, 43, 134, 23);
		txtAddFilter.addActionListener(addProductEvent);
		txtAddFilter.setFont(new Font("Arial", Font.PLAIN, 11));
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
		btnFind.setBounds(369, 231, 75, 23);
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listNfeModel = NfeController.searchNfes(txtPathDir.getText());
					listNfeModel = NfeController.filterByProducts(listModelProduct);
					TableService.preencherTabela(tableModel, listNfeModel);
					lblResults.setText(Integer.toString(listNfeModel.size()));
				} catch (DialogException err) {
					err.dialogMessage("Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnFind.setFont(new Font("Arial", Font.PLAIN, 11));
		panel.add(btnFind);

		JButton btnGenerateCSV = new JButton("Gerar CSV");
		btnGenerateCSV.setBounds(10, 231, 89, 23);
		btnGenerateCSV.setFont(new Font("Arial", Font.PLAIN, 11));
		btnGenerateCSV.setEnabled(false);
		panel.add(btnGenerateCSV);

		JScrollPane scrollPaneNfe = new JScrollPane();
		scrollPaneNfe.setBounds(10, 146, 434, 75);
		panel.add(scrollPaneNfe);
		
		tableModel.addColumn("Nota");
		tableModel.addColumn("Cliente");
		tableModel.addColumn("CPF/CNPJ");
		
		tabNfeList = new JTable(tableModel);
		tabNfeList.getColumnModel().getColumn(0).setPreferredWidth(50);
		tabNfeList.getColumnModel().getColumn(1).setPreferredWidth(200);
		tabNfeList.getColumnModel().getColumn(2).setPreferredWidth(80);
		scrollPaneNfe.setViewportView(tabNfeList);
		
		JLabel lblNewLabel_1 = new JLabel("resultados");
		lblNewLabel_1.setBounds(230, 234, 75, 14);
		panel.add(lblNewLabel_1);
		
		lblResults = new JLabel("0");
		lblResults.setHorizontalAlignment(SwingConstants.RIGHT);
		lblResults.setBounds(180, 235, 46, 14);
		panel.add(lblResults);
	}
}
