package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Usuario;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField usuario;
	private JPasswordField password;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(29, 34, 99, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(29, 89, 106, 13);
		contentPane.add(lblNewLabel_1);
		
		usuario = new JTextField();
		usuario.setBounds(162, 37, 229, 25);
		contentPane.add(usuario);
		usuario.setColumns(10);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(29, 198, 362, 43);
		contentPane.add(editorPane);
		
		JButton btnNewButton = new JButton("Acceder a la cuenta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				   String tipoError=null;
				   String us= usuario.getText();
				   if(us.equals("")) { tipoError= "u";}
				   
				   String ps= new String(password.getPassword());
				   if(ps.equals("") & tipoError==null ) tipoError="p";
		
				   BLFacade facade = MainGUI.getBusinessLogic();
				   Usuario user = facade.login(us, ps);
				   if(user==null && tipoError==null) tipoError="n";
				   if(tipoError==null){
					   if(user.isAdmin()) {
						   if(user.isBloqueado())editorPane.setText("Administrador bloqueado");
						   else if(user.getPassword().equals(ps)) btnLoginAdmi_actionPerformed(e);
						   else  editorPane.setText("Contrase�a incorrecta intento "+user.getNumIntento()+"/5 ."); 
					   }
					   else {
						   if(user.isBloqueado())editorPane.setText("Usuario bloqueado");
						   else if(user.getPassword().equals(ps)) btnLoginUser_actionPerformed(e,user);	
						   else editorPane.setText("Contrase�a incorrecta intento "+user.getNumIntento()+"/5 ."); 
					   }						   
				   }else {
					    
					   if(tipoError.equals("u")) editorPane.setText("Introduzca el nombre de usuario.");
					   if(tipoError.equals("p")) editorPane.setText("Introduzca la contrase�a.");
					   if(tipoError.equals("n")) editorPane.setText("ERRIR:No existe el usuario introducido."); 
				   }
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(103, 134, 229, 38);
		contentPane.add(btnNewButton);
		

		
		password = new JPasswordField();
		password.setBounds(162, 81, 229, 26);
		contentPane.add(password);
		
		JButton btnNewMenu = new JButton("Men\u00FA");
		btnNewMenu.setBounds(357, 0, 81, 18);
		contentPane.add(btnNewMenu);
		
		btnNewMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonMenu_actionPerformed(e);
			}
		});
		
		
	}
	private void btnLoginUser_actionPerformed(ActionEvent e,Usuario u) {
		
		 JFrame b= new FQuestion2(u);
		 b.setVisible(true);	
		 this.setVisible(false);;
	}
	private void btnLoginAdmi_actionPerformed(ActionEvent e) {
		JFrame b= CreateAndQueryGUI.getInstance();
		   b.setVisible(true);	
		
		 this.setVisible(false);;
	}
	private void jButtonMenu_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		MainGUI a=new MainGUI();
		a.setVisible(true);
	}
}
