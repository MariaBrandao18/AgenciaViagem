package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Cliente;
import classes.ClienteEstrangeiro;
import classes.ClienteNacional;
import conexao_db.ClienteDAO;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import net.miginfocom.swing.MigLayout;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdicionarCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField email;
	private JTextField telefone;
	private JTextField nome;
	private JTextField documento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdicionarCliente frame = new AdicionarCliente();
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
	public AdicionarCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][grow][][]", "[][][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Adicionar Cliente");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblNewLabel, "cell 4 0");
		
		JLabel lblNome = new JLabel("Nome:");
		contentPane.add(lblNome, "cell 3 1,alignx trailing");
		
		nome = new JTextField();
		nome.setColumns(10);
		contentPane.add(nome, "cell 4 1,growx");
		
		JLabel lblTelefone = new JLabel("Telefone:");
		contentPane.add(lblTelefone, "cell 3 2,alignx trailing");
		
		telefone = new JTextField();
		telefone.setColumns(10);
		contentPane.add(telefone, "cell 4 2,growx");
		
		JLabel lblEmail = new JLabel("Email:");
		contentPane.add(lblEmail, "cell 3 3,alignx trailing");
		
		email = new JTextField();
		contentPane.add(email, "cell 4 3,growx");
		email.setColumns(10);
		JLabel lblDocumento = new JLabel("Documento:");
		JRadioButton rdbtnNacional = new JRadioButton("Nacional");
		JRadioButton rdbtnEstrangeiro = new JRadioButton("Estrangeiro");

		
		rdbtnNacional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnNacional.isSelected()) {
					rdbtnEstrangeiro.setSelected(false);
					lblDocumento.setText("CPF:");
						}
					}
				});
				contentPane.add(rdbtnNacional, "cell 3 4");
		
		rdbtnEstrangeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnEstrangeiro.isSelected()) {
					rdbtnNacional.setSelected(false);
					lblDocumento.setText("Passaporte:");

				}
			}
		});
		contentPane.add(rdbtnEstrangeiro, "cell 4 4");
		
		contentPane.add(lblDocumento, "cell 3 5,alignx trailing");
		
		documento = new JTextField();
		contentPane.add(documento, "cell 4 5,growx");
		documento.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				// telaAnterior.setVisible(true);
			}
		});
		contentPane.add(btnCancelar, "cell 3 7");
		
		JButton btnNewButton = new JButton("Adicionar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String nome1 = nome.getText();
				String email1 = email.getText();
				String telefone1 = telefone.getText();
				String documento1 = documento.getText();
				Cliente cliente;

				
				if (rdbtnNacional.isSelected()) {
	                cliente = new ClienteNacional();
	                ((ClienteNacional) cliente).setCpf(documento1);
	            } else if (rdbtnEstrangeiro.isSelected()) {
	                cliente = new ClienteEstrangeiro();
	                ((ClienteEstrangeiro) cliente).setPassaporte(documento1);
	            } else {
	            	JOptionPane.showMessageDialog(null, "Selecione o tipo de cliente!");
	            	return;
	            }
				
				cliente.setNome(nome1);
				cliente.setEmail(email1);
				cliente.setTelefone(telefone1);
				
				ClienteDAO.inserirCliente(cliente);
				
				JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso!");
	            dispose();
		    // telaAnterior.setVisible(true);

	            
				} catch (Exception ex){
					ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Erro ao adicionar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		contentPane.add(btnNewButton, "cell 5 7");

	}

}
