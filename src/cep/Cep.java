package cep;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;

public class Cep extends JFrame {

	private JPanel contentPane;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtBairro;
	private JTextField txtCidade;
	@SuppressWarnings("rawtypes")
	private JComboBox cboUf;
	private JLabel lblStatus;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cep frame = new Cep();
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
	public Cep() {
		setResizable(false);
		setTitle("Buscar CEP");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cep.class.getResource("/img/home.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("Sobre");
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CEP");
		lblNewLabel.setBounds(27, 46, 46, 14);
		contentPane.add(lblNewLabel);
		
		txtCep = new JTextField();
		txtCep.setBounds(63, 43, 86, 20);
		contentPane.add(txtCep);
		txtCep.setColumns(10);
		
		JLabel txt = new JLabel("Endereço");
		txt.setBounds(10, 79, 63, 14);
		contentPane.add(txt);
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(10, 122, 39, 14);
		contentPane.add(lblBairro);
		
		JLabel lblNewLabel_2 = new JLabel("Cidade");
		lblNewLabel_2.setBounds(10, 168, 39, 14);
		contentPane.add(lblNewLabel_2);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(66, 76, 331, 20);
		contentPane.add(txtEndereco);
		txtEndereco.setColumns(10);
		
		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(52, 119, 331, 20);
		contentPane.add(txtBairro);
		
		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(52, 165, 218, 20);
		contentPane.add(txtCidade);
		
		JLabel lblNewLabel_3 = new JLabel("UF");
		lblNewLabel_3.setBounds(290, 168, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		 cboUf = new JComboBox();
		cboUf.setModel(new DefaultComboBoxModel(new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", "DF"}));
		cboUf.setBounds(312, 164, 68, 22);
		contentPane.add(cboUf);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(10, 227, 89, 23);
		contentPane.add(btnLimpar);
		
		JButton btnCep = new JButton("Buscar");
		btnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtCep.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Informe o CEP");
					txt.requestFocus();
				}else {
					buscarCep();
				}
				
				
			}
		});
		btnCep.setBounds(181, 42, 89, 23);
		contentPane.add(btnCep);
		
		JButton btnSobre = new JButton((String) null);
		btnSobre.setToolTipText("Sobre");
		btnSobre.setIcon(new ImageIcon(Cep.class.getResource("/img/about.png")));
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);
			}
		});
		btnSobre.setBounds(312, 27, 35, 35);
		contentPane.add(btnSobre);
		
		
		// uso da bilbioteca Atxy2k para validçação do campo txtCep
		RestrictedTextField validar = new RestrictedTextField(txtCep);
		validar.setOnlyNums(true);
		validar.setLimit(8);
	}
	//Buscar Cep
	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCep.getText();
		
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			
			 for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
			        Element element = it.next();
			       if(element.getQualifiedName().equals("cidade")) {
			    	   txtCidade.setText(element.getText());
			       }
			       
			       if(element.getQualifiedName().equals("bairro")) {
			    	   txtBairro.setText(element.getText());
			       }
			       
			       if(element.getQualifiedName().equals("uf")) {
			    	   cboUf.setSelectedItem(element.getText());
			       }
			       
			       if(element.getQualifiedName().equals("tipo_logradouro")) {
			    	   tipoLogradouro = (element.getText());
			       }
			       if(element.getQualifiedName().equals("logradouro")) {
			    	   logradouro = (element.getText());
			       }
			       
			       }
			       
			       
			       
			    
			 
			 txtEndereco.setText(tipoLogradouro + " " + logradouro);
			 
			 
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
