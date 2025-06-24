package view.cliente;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import classes.CadastroCliente;
import view.AdicionarCliente;
import view.Menu;

public class ClientePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientePage frame = new ClientePage();
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
	public ClientePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{192, 49, 0, 0};
		gbl_contentPane.rowHeights = new int[]{22, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JMenuBar menuBar = new JMenuBar();
		GridBagConstraints gbc_menuBar = new GridBagConstraints();
		gbc_menuBar.insets = new Insets(0, 0, 5, 5);
		gbc_menuBar.anchor = GridBagConstraints.NORTHWEST;
		gbc_menuBar.gridx = 1;
		gbc_menuBar.gridy = 0;
		contentPane.add(menuBar, gbc_menuBar);
		
		JMenu mnNewMenu = new JMenu("Cliente");
		menuBar.add(mnNewMenu);
		
		JMenuItem adicionarItem = new JMenuItem("Adicionar Cliente");
		mnNewMenu.add(adicionarItem);
		
		adicionarItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AdicionarCliente telaAdicionar = new AdicionarCliente(ClientePage.this);
				telaAdicionar.setVisible(true); 
				setVisible(false);
			}
			
		});
		
		JMenuItem listarItem = new JMenuItem("Listar Clientes");
		mnNewMenu.add(listarItem);
		
		listarItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					CadastroCliente.ListarCliente();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
		JMenuItem buscarItem = new JMenuItem("Buscar Cliente");
		mnNewMenu.add(buscarItem);
		
		buscarItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					CadastroCliente.ProcurarCliente();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
		JMenuItem removerItem = new JMenuItem("Remover Cliente");
		mnNewMenu.add(removerItem);
		
		removerItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					CadastroCliente.EliminarCliente();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
		JButton voltarBtn = new JButton("Voltar");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 10;
		contentPane.add(voltarBtn, gbc_btnNewButton);
		
		voltarBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Menu();
				setVisible(false);
			}
			
		});
		
		JButton sairBtn = new JButton("Sair");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 10;
		contentPane.add(sairBtn, gbc_btnNewButton_1);
		
		sairBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
			
		});
		
		setVisible(true);
	}

}
