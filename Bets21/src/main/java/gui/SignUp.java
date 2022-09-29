package gui;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nombreUsuario;
	private JPasswordField passwordField;
	private JTextField numTarjeta;
	private JTextField correoField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
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
	public SignUp() {
		setTitle("Sign Up");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(62, 32, 97, 14);
		contentPane.add(lblNewLabel_1);
		
		nombreUsuario = new JTextField();
		nombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nombreUsuario.setBounds(169, 29, 187, 25);
		contentPane.add(nombreUsuario);
		nombreUsuario.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Contrase\u00F1a:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(60, 70, 112, 14);
		contentPane.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBounds(169, 65, 187, 25);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_3 = new JLabel("N\u00BA tarjeta:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(62, 110, 97, 14);
		contentPane.add(lblNewLabel_3);
		
		numTarjeta = new JTextField();
		numTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		numTarjeta.setBounds(169, 105, 187, 25);
		contentPane.add(numTarjeta);
		numTarjeta.setColumns(10);
		
		JTextArea textA = new JTextArea();
		textA.setBounds(71, 228, 294, 25);
		contentPane.add(textA);
		
		
		
	
		
		JLabel lblNewLabel = new JLabel("Correo :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(62, 144, 62, 21);
		contentPane.add(lblNewLabel);
		
		correoField = new JTextField();
		correoField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		correoField.setBounds(169, 140, 187, 25);
		contentPane.add(correoField);
		correoField.setColumns(10);

	
	
		JButton registrarse = new JButton("Registrase");
		registrarse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		registrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			   String tipoError=null;
			   String us= nombreUsuario.getText();
			   if(us.equals("")) { tipoError= "u";}
		   
			   String ps= new String(passwordField.getPassword());
			   if(ps.equals("") && tipoError==null ) tipoError="p";
			   
			   String code= numTarjeta.getText();
			   	   
			   if (code.length()!=12 && tipoError==null) tipoError="n";
			   String correo = correoField.getText();
		
			   	Pattern pattern = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
			   
		        Matcher mather = pattern.matcher(correo);
		        if(!mather.find()) tipoError= "c";
				 
			   if(tipoError==null) {
				   BLFacade facade = MainGUI.getBusinessLogic();
				   boolean b = facade.createUser(us, ps, code,correo);
				   if(!b) textA.setText("Error: Este nombre de usuario ya existe, escriba otra.");
				   else{
					   textA.setText("Usuario " + us+ " registrado correctamente");
					   jButtonSignUp_actionPerformed(e);
				   }
			   }else {
				    
				   if(tipoError.equals("u")) textA.setText("ERROR:Completa el campo del usuario");
				   if(tipoError.equals("p"))textA.setText("ERROR:Completa el campo de la contraseña");
				   if(tipoError.equals("n"))textA.setText("ERROR:El numero de tarjeta debe de tener 12 digitos/letras.");
				   if(tipoError.equals("c"))textA.setText("ERROR:El correo no es valido.");
			   }
			}
		});
			
		registrarse.setBounds(150, 185, 135, 25);
		contentPane.add(registrarse);
		
		JButton btnNewMenu = new JButton("Men\u00FA");
		btnNewMenu.setBounds(357, 0, 81, 18);
		contentPane.add(btnNewMenu);
		
		btnNewMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonMenu_actionPerformed(e);
			}
		});

	}
	
	private void jButtonSignUp_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		JFrame a=new Login();
		a.setVisible(true);
	}
	private void jButtonMenu_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		MainGUI a=new MainGUI();
		a.setVisible(true);
	}
}
