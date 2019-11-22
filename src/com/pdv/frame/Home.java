package com.pdv.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.pdv.dao.ClienteDao;
import com.pdv.dao.LocalidadeDao;
import com.pdv.dao.ProdutoDao;
import com.pdv.dao.VendaDao;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Home {

	private JFrame frmPdv;
	private JTextField txtDescricaoProduto;
	private JTextField txtTotalCompra;
	private JComboBox<Integer> cbCliente = new JComboBox<Integer>();
	private JComboBox<Integer> cbLocalidade = new JComboBox<Integer>();
	private JComboBox<Integer> cbProduto = new JComboBox<Integer>();
	private JSpinner spinnerQuantidade = new JSpinner();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frmPdv.setVisible(true);
					window.frmPdv.setLocationRelativeTo(null);
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
		frmPdv = new JFrame();
		frmPdv.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				if(table.getRowCount() > 0) {
					JOptionPane.showMessageDialog(null, "Antes de sair, exclua os produtos!");
					frmPdv.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					
				}else {
					frmPdv.dispose();
				}
			}
		});

		frmPdv.setResizable(false);
		frmPdv.setTitle("PDV");
		frmPdv.setBounds(100, 100, 438, 576);
		frmPdv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPdv.getContentPane().setLayout(null);

		JLabel lblCaixaLivre = new JLabel("CAIXA LIVRE");
		lblCaixaLivre.setBounds(10, 11, 402, 14);
		lblCaixaLivre.setFont(new Font("Arial", Font.BOLD, 16));
		lblCaixaLivre.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaixaLivre.setForeground(Color.BLUE);
		frmPdv.getContentPane().add(lblCaixaLivre);

		JLabel lblCliente = new JLabel("Cliente Selecionado:");
		lblCliente.setBounds(22, 70, 176, 14);
		lblCliente.setFont(new Font("Arial", Font.BOLD, 12));
		lblCliente.setForeground(new Color(0, 0, 128));
		frmPdv.getContentPane().add(lblCliente);
		cbCliente.setBounds(22, 95, 176, 20);
		frmPdv.getContentPane().add(cbCliente);

		JLabel lblLocalVenda = new JLabel("Local de Venda:");
		lblLocalVenda.setBounds(225, 70, 176, 14);
		lblLocalVenda.setForeground(new Color(0, 0, 128));
		lblLocalVenda.setFont(new Font("Arial", Font.BOLD, 12));
		frmPdv.getContentPane().add(lblLocalVenda);
		cbLocalidade.setBounds(225, 95, 176, 20);
		frmPdv.getContentPane().add(cbLocalidade);

		JLabel lblCodigoDoProduto = new JLabel("Codigo do Produto:");
		lblCodigoDoProduto.setBounds(22, 126, 176, 14);
		lblCodigoDoProduto.setForeground(new Color(0, 0, 128));
		lblCodigoDoProduto.setFont(new Font("Arial", Font.BOLD, 12));
		frmPdv.getContentPane().add(lblCodigoDoProduto);
		cbProduto.setBounds(22, 151, 176, 20);
		cbProduto.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				setTextDescricaoProduto();
			}
		});
		frmPdv.getContentPane().add(cbProduto);

		JLabel lblQuantidadeProduto = new JLabel("Quantidade:");
		lblQuantidadeProduto.setBounds(225, 126, 176, 14);
		lblQuantidadeProduto.setForeground(new Color(0, 0, 128));
		lblQuantidadeProduto.setFont(new Font("Arial", Font.BOLD, 12));
		frmPdv.getContentPane().add(lblQuantidadeProduto);

		JLabel lblDescricaoProduto = new JLabel("Descricao do Produto:");
		lblDescricaoProduto.setBounds(22, 182, 135, 14);
		lblDescricaoProduto.setForeground(new Color(0, 0, 128));
		lblDescricaoProduto.setFont(new Font("Arial", Font.BOLD, 12));
		frmPdv.getContentPane().add(lblDescricaoProduto);

		txtDescricaoProduto = new JTextField();
		txtDescricaoProduto.setBounds(22, 207, 379, 20);
		txtDescricaoProduto.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtDescricaoProduto.setEditable(false);
		txtDescricaoProduto.setHorizontalAlignment(SwingConstants.CENTER);
		frmPdv.getContentPane().add(txtDescricaoProduto);
		txtDescricaoProduto.setColumns(10);

		JButton btnVender = new JButton("Vender");
		btnVender.setBounds(151, 243, 138, 23);

		/**
		 * Evento para adicionar um item na tabela do form
		 */
		btnVender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ProdutoDao dao = new ProdutoDao();

				Integer id = (Integer) cbProduto.getSelectedItem();
				Integer quantidadeComprada = (Integer) spinnerQuantidade.getValue();

				try {
					String aprovada = dao.vender(id, quantidadeComprada);

					switch (aprovada) {
					case "Produto adicionado!":
						JOptionPane.showMessageDialog(null, "Produto adicionado!");

						DefaultTableModel model = (DefaultTableModel) table.getModel();

						model.addRow(new Object[] { id, dao.getDescPrecoById(id)[0], quantidadeComprada,
								dao.getDescPrecoById(id)[1],
								(Double.parseDouble(dao.getDescPrecoById(id)[1]) * quantidadeComprada) });

						double valorTotal = 0;
						for (int i = 0; i < table.getRowCount(); i++) {
							double valor = (double) table.getValueAt(i, 4);
							valorTotal = +valor;
						}

						if (txtTotalCompra.getText().isEmpty()) {
							txtTotalCompra.setText(String.valueOf(valorTotal));
						} else {
							double valor = Double.parseDouble(txtTotalCompra.getText());
							txtTotalCompra.setText(String.valueOf(valor + valorTotal));

						}

						break;

					case "Produto sem estoque!":
						JOptionPane.showMessageDialog(null, "Produto sem estoque!");
						break;
					default:
						JOptionPane.showMessageDialog(null, "SQL Errado");
						break;
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnVender.setFont(new Font("Arial", Font.PLAIN, 11));
		frmPdv.getContentPane().add(btnVender);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Integer id = 0;
				int quantidadeItem = 0;
				double valorTotalItem = 0;
				double valorTotalCompra = 0;

				ProdutoDao dao = new ProdutoDao();

				DefaultTableModel model = (DefaultTableModel) table.getModel();

				try {
					id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
					quantidadeItem = (int) table.getValueAt(table.getSelectedRow(), 2);
					valorTotalItem = (double) table.getValueAt(table.getSelectedRow(), 4);
					valorTotalCompra = Double.parseDouble(txtTotalCompra.getText());

					dao.excluir(id, quantidadeItem);

					model.removeRow(table.getSelectedRow());
					txtTotalCompra.setText(String.valueOf(valorTotalCompra - valorTotalItem));
				} catch (ArrayIndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null, "Selecione um produto primeiro!");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnExcluir.setBounds(151, 414, 138, 23);
		btnExcluir.setFont(new Font("Arial", Font.PLAIN, 11));
		frmPdv.getContentPane().add(btnExcluir);

		JLabel lblNewLabel = new JLabel("Produto");
		lblNewLabel.setBounds(10, 276, 71, 14);
		frmPdv.getContentPane().add(lblNewLabel);

		JLabel lblTotalCompra = new JLabel("Total da Compra:");
		lblTotalCompra.setBounds(165, 469, 101, 14);
		lblTotalCompra.setForeground(new Color(0, 0, 128));
		lblTotalCompra.setFont(new Font("Arial", Font.BOLD, 12));
		frmPdv.getContentPane().add(lblTotalCompra);

		txtTotalCompra = new JTextField();
		txtTotalCompra.setBounds(303, 467, 109, 20);
		txtTotalCompra.setEditable(false);
		txtTotalCompra.setFont(new Font("Arial", Font.PLAIN, 12));
		frmPdv.getContentPane().add(txtTotalCompra);
		txtTotalCompra.setColumns(10);

		JButton btnFecharCompra = new JButton("Fechar Compra");
		btnFecharCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int clienteSelecionado = 0;
				int codigoProduto = 0;
				int localVenda = 0;
				int quantidade = 0;
				double valorTotalCompra = 0;

				Date data = new Date();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dataVenda = sdf.format(data);

				if (!txtTotalCompra.getText().isEmpty() && txtTotalCompra.getText() != "0.0") {

					try {
						clienteSelecionado = (Integer) (cbCliente.getSelectedItem());
						codigoProduto = (Integer) (cbProduto.getSelectedItem());
						localVenda = (Integer) (cbLocalidade.getSelectedItem());
						quantidade = (Integer) (spinnerQuantidade.getValue());
						valorTotalCompra = Double.parseDouble(txtTotalCompra.getText());

						VendaDao dao = new VendaDao();
						dao.addVenda(clienteSelecionado, codigoProduto, localVenda, quantidade, valorTotalCompra,
								dataVenda);

						JOptionPane.showMessageDialog(null, "Venda efetuada e salva com sucesso!");
						limparCampos();

					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {

				}
			}
		});
		btnFecharCompra.setBounds(10, 503, 402, 23);
		frmPdv.getContentPane().add(btnFecharCompra);

		spinnerQuantidade.setBounds(225, 151, 176, 20);
		spinnerQuantidade.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		frmPdv.getContentPane().add(spinnerQuantidade);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(10, 327, 402, 71);
		frmPdv.getContentPane().add(scrollPane);

		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Produto", "Quantidade", "Pre\u00E7o Unit\u00E1rio", "Total" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(39);
		table.getColumnModel().getColumn(1).setPreferredWidth(178);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(134);

		scrollPane.setViewportView(table);
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
			rs.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void limparCampos() {
		cbCliente.setSelectedIndex(0);
		cbLocalidade.setSelectedIndex(0);
		cbProduto.setSelectedIndex(0);
		spinnerQuantidade.setValue(1);
		txtTotalCompra.setText("0.0");
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		for (int i = 0; i < table.getRowCount(); i++) {
			model.removeRow(i);
		}
		setTextDescricaoProduto();

	}
}
