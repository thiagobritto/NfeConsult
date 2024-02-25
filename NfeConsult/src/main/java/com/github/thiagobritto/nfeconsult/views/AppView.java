package com.github.thiagobritto.nfeconsult.views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.github.thiagobritto.nfeconsult.controllers.FilterListController;
import com.github.thiagobritto.nfeconsult.models.FilterListModel;

public class AppView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtAdd;
	
	private FilterListController filterList = new FilterListController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppView frame = new AppView();
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
	public AppView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBounds(0, 0, 434, 105);
		contentPane.add(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		txtAdd = new JTextField();
		txtAdd.setBounds(185, 26, 122, 20);
		panelPrincipal.add(txtAdd);
		txtAdd.setColumns(10);
		
		JButton btnTeste = new JButton("add");
		btnTeste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FilterListModel fl = (FilterListModel) filterList.get(20);
				if (fl!=null)
					JOptionPane.showMessageDialog(null, fl.getDescription());
			}
		});
		btnTeste.setBounds(317, 25, 89, 23);
		panelPrincipal.add(btnTeste);
		
	}
}
