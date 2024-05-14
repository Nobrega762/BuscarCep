package cep;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Sobre extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Sobre dialog = new Sobre();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Sobre() {
		setModal(true);
		setResizable(false);
		setTitle("Sobre");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/home.png")));
		setBounds(150, 150, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Buscar CEP - Ver 1.0");
			lblNewLabel.setBounds(26, 38, 117, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("@Author Renato Nobrega - O VIKING");
			lblNewLabel_1.setBounds(26, 63, 194, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("WEB Service");
			lblNewLabel_1.setBounds(26, 119, 65, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblWebService = new JLabel("republicavirtual.com.br");
			lblWebService.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					link("https://www.republicavirtual.com.br/");
				}
			});
			lblWebService.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblWebService.setForeground(SystemColor.textHighlight);
			lblWebService.setBounds(113, 119, 129, 14);
			contentPanel.add(lblWebService);
		}
		{
			JButton btnGithub = new JButton("");
			btnGithub.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					link("https://github.com/Nobrega762");
				}
			});
			btnGithub.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnGithub.setIcon(new ImageIcon(Sobre.class.getResource("/img/github.png")));
			btnGithub.setToolTipText("Github");
			btnGithub.setBounds(286, 63, 32, 32);
			contentPanel.add(btnGithub);
		}
	}
	
	private void link(String site){
		Desktop desktop = Desktop.getDesktop();
		try {
			URI uri = new URI(site);
			desktop.browse(uri);
		}catch (Exception e) {
			System.out.println(e);
		}
	}
}
