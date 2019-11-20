package com.pdv.frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.pdv.dao.ClienteDao;
import com.pdv.dao.LocalidadeDao;
import com.pdv.dao.ProdutoDao;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Home {

	private JFrame frame;
	private JTextField txtDescricaoProduto;
	private JTable table;
	private JTextField txtTotalCompra;
	private JComboBox<Integer> cbCliente = new JComboBox<Integer>();
	private JComboBox<Integer> cbLocalidade = new JComboBox<Integer>();
	private JComboBox<Integer> cbProduto = new JComboBox<Integer>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
		try {
			populateClienteBox();
			populateLocalidadeBox();
			populateProdutoBox();
			setTextDescricaoProduto();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 438, 576);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblCaixaLivre = new JLabel("CAIXA LIVRE");
		lblCaixaLivre.setFont(new Font("Arial", Font.BOLD, 16));
		lblCaixaLivre.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaixaLivre.setForeground(Color.BLUE);
		lblCaixaLivre.setBounds(10, 11, 402, 14);
		frame.getContentPane().add(lblCaixaLivre);

		JLabel lblCliente = new JLabel("Cliente Selecionado:");
		lblCliente.setFont(new Font("Arial", Font.BOLD, 12));
		lblCliente.setForeground(new Color(0, 0, 128));
		lblCliente.setBounds(22, 70, 176, 14);
		frame.getContentPane().add(lblCliente);

		cbCliente.setBounds(22, 95, 176, 20);
		frame.getContentPane().add(cbCliente);

		JLabel lblLocalVenda = new JLabel("Local de Venda:");
		lblLocalVenda.setForeground(new Color(0, 0, 128));
		lblLocalVenda.setFont(new Font("Arial", Font.BOLD, 12));
		lblLocalVenda.setBounds(225, 70, 176, 14);
		frame.getContentPane().add(lblLocalVenda);

		cbLocalidade.setBounds(225, 95, 176, 20);
		frame.getContentPane().add(cbLocalidade);

		JLabel lblCodigoDoProduto = new JLabel("Codigo do Produto:");
		lblCodigoDoProduto.setForeground(new Color(0, 0, 128));
		lblCodigoDoProduto.setFont(new Font("Arial", Font.BOLD, 12));
		lblCodigoDoProduto.setBounds(22, 126, 176, 14);
		frame.getContentPane().add(lblCodigoDoProduto);

		/**
		 * Evento para mudar a descricao sempre que selecionar outro produto
		 */
		cbProduto.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				setTextDescricaoProduto();
			}
		});

		cbProduto.setBounds(22, 151, 176, 20);
		frame.getContentPane().add(cbProduto);

		JLabel lblQuantidadeProduto = new JLabel("Quantidade:");
		lblQuantidadeProduto.setForeground(new Color(0, 0, 128));
		lblQuantidadeProduto.setFont(new Font("Arial", Font.BOLD, 12));
		lblQuantidadeProduto.setBounds(225, 126, 176, 14);
		frame.getContentPane().add(lblQuantidadeProduto);

		JLabel lblDescricaoProduto = new JLabel("Descricao do Produto:");
		lblDescricaoProduto.setForeground(new Color(0, 0, 128));
		lblDescricaoProduto.setFont(new Font("Arial", Font.BOLD, 12));
		lblDescricaoProduto.setBounds(22, 182, 135, 14);
		frame.getContentPane().add(lblDescricaoProduto);

		txtDescricaoProduto = new JTextField();
		txtDescricaoProduto.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtDescricaoProduto.setEditable(false);
		txtDescricaoProduto.setHorizontalAlignment(SwingConstants.CENTER);
		txtDescricaoProduto.setBounds(22, 207, 379, 20);
		frame.getContentPane().add(txtDescricaoProduto);
		txtDescricaoProduto.setColumns(10);

		JButton btnVender = new JButton("Vender");
		
		/**
		 * Evento para adicionar um item na tabela do form
		 */
		btnVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		btnVender.setFont(new Font("Arial", Font.PLAIN, 11));
		btnVender.setBounds(151, 243, 138, 23);
		frame.getContentPane().add(btnVender);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("Arial", Font.PLAIN, 11));
		btnExcluir.setBounds(151, 414, 138, 23);
		frame.getContentPane().add(btnExcluir);

		table = new JTable();
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		table.setBounds(10, 291, 402, 106);
		frame.getContentPane().add(table);

		JLabel lblTotalCompra = new JLabel("Total da Compra:");
		lblTotalCompra.setForeground(new Color(0, 0, 128));
		lblTotalCompra.setFont(new Font("Arial", Font.BOLD, 12));
		lblTotalCompra.setBounds(165, 469, 101, 14);
		frame.getContentPane().add(lblTotalCompra);

		txtTotalCompra = new JTextField();
		txtTotalCompra.setEditable(false);
		txtTotalCompra.setFont(new Font("Arial", Font.PLAIN, 12));
		txtTotalCompra.setBounds(303, 467, 109, 20);
		frame.getContentPane().add(txtTotalCompra);
		txtTotalCompra.setColumns(10);

		JButton btnFecharCompra = new JButton("Fechar Compra");
		btnFecharCompra.setBounds(10, 503, 402, 23);
		frame.getContentPane().add(btnFecharCompra);
		
		JSpinner spinnerQuantidade = new JSpinner();
		spinnerQuantidade.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinnerQuantidade.setBounds(225, 151, 176, 20);
		frame.getContentPane().add(spinnerQuantidade);
	}

	private void populateClienteBox() throws SQLException, ClassNotFoundException {
		ClienteDao dao = new ClienteDao();
		ResultSet rs = dao.getClientes();

		while (rs.next()) {
			cbCliente.addItem(rs.getRow());
		}

		rs.close();
	}

	private void populateLocalidadeBox() throws SQLException, ClassNotFoundException {
		LocalidadeDao dao = new LocalidadeDao();
		ResultSet rs = dao.getLocalidades();

		while (rs.next()) {
			cbLocalidade.addItem(rs.getRow());
		}

		rs.close();

	}

	private void populateProdutoBox() throws SQLException, ClassNotFoundException {
		ProdutoDao dao = new ProdutoDao();
		ResultSet rs = dao.getProdutos();

		while (rs.next()) {
			cbProduto.addItem(rs.getRow());
		}

		rs.close();

	}

	private void setTextDescricaoProduto() {
		int produtoId = (Integer) cbProduto.getSelectedItem();
		ProdutoDao dao = new ProdutoDao();

		try {
			ResultSet rs = dao.getDescricaoById(produtoId);

			while (rs.next()) {
				txtDescricaoProduto.setText(rs.getString("descricao"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
