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
import java.awt.Color;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class SignUp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nombreUsuario;
	private JPasswordField passwordField;
	private JTextField numTarjeta;
	private final Action action = new SwingAction();


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
		textA.setBounds(71, 203, 294, 50);
		contentPane.add(textA);
		
		JButton registrarse = new JButton("Registrase");
		registrarse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		registrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			   String tipoError=null;
			   String us= nombreUsuario.getText();
			   if(us.equals("")) { tipoError= "u";}
			   
			   String ps= new String(passwordField.getPassword());
			   if(ps.equals("") & tipoError==null ) tipoError="p";
			   
			   String code= numTarjeta.getText();
			   if (code.length()!=12 && tipoError==null) tipoError="n";
			   if(tipoError==null) {
				   BLFacade facade = MainGUI.getBusinessLogic();
				   boolean b = facade.createUser(us, ps, code);
				   if(!b) textA.setText("Error: Este nombre de usuario ya existe, escriba otra.");
				   else textA.setText("Usuario " + us+ " registrado correctamente");
			   }else {
				    
				   if(tipoError.equals("u")) textA.setText("ERROR:Completa el campo del usuario");
				   if(tipoError.equals("p"))textA.setText("ERROR:Completa el campo de la contraseña");
				   if(tipoError.equals("n"))textA.setText("ERROR:El numero de tarjeta debe de tener 12 digitos/letras.");
			   }
			}
		});
		
	 
		registrarse.setBounds(145, 151, 146, 33);
		contentPane.add(registrarse);
		

		}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
