package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Bet;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import domain.Usuario;
import exceptions.PronosticAlreadyExist;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JTable;

public class SeeUserInfoGUI extends JFrame {
	

	private JPanel contentPane;
	private final JScrollPane scrollPanePronostico = new JScrollPane();
	private final JTable tablePronosticos = new JTable();
	private DefaultTableModel tableModelPronostico;
	private String[] columnNamesPronostico = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("NApuesta"), 
			ResourceBundle.getBundle("Etiquetas").getString("Pronostico")

	};
	private JTextField textField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario userPrueba= new Usuario("P","R","1",false,"a");
					SeeUserInfoGUI frame = new SeeUserInfoGUI(userPrueba);
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

	
	public SeeUserInfoGUI(Usuario user) {
		BLFacade facade = MainGUI.getBusinessLogic(); 
		List<Bet>apuestas = facade.getBet(user);
		
		
		setTitle("Informacion del usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 477, 652);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(29, 34, 110, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email  :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(29, 79, 91, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("N\u00BA Tarjeta : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(29, 121, 172, 16);
		contentPane.add(lblNewLabel_2);
		
		
		JLabel lblNewLabel_3 = new JLabel("Evento :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(29, 373, 84, 21);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Pregunta :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(29, 404, 84, 28);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Ganancia :");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(29, 448, 84, 17);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("% De Apuesta :");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(221, 446, 128, 21);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Inversi\u00F3n :");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(29, 484, 120, 21);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Cuota Final :");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_8.setBounds(231, 484, 110, 21);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel(user.getUserName());
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_9.setBounds(101, 32, 286, 21);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel(user.getCorreo());
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_10.setBounds(90, 75, 286, 21);
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel_10_1 = new JLabel(user.getCardCode());
		lblNewLabel_10_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_10_1.setBounds(115, 121, 286, 21);
		contentPane.add(lblNewLabel_10_1);
		
		JLabel lblNewLabel_10_2 = new JLabel("");
		lblNewLabel_10_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_10_2.setBounds(102, 373, 299, 21);
		contentPane.add(lblNewLabel_10_2);
		
		JLabel lblNewLabel_10_3 = new JLabel("");
		lblNewLabel_10_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_10_3.setBounds(115, 411, 286, 21);
		contentPane.add(lblNewLabel_10_3);
		
		JLabel lblNewLabel_10_4 = new JLabel("");
		lblNewLabel_10_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_10_4.setBounds(114, 446, 97, 21);
		contentPane.add(lblNewLabel_10_4);
		
		JLabel lblNewLabel_10_4_1 = new JLabel("");
		lblNewLabel_10_4_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_10_4_1.setBounds(115, 484, 106, 21);
		contentPane.add(lblNewLabel_10_4_1);
		
		JLabel lblNewLabel_10_4_2 = new JLabel("");
		lblNewLabel_10_4_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_10_4_2.setBounds(342, 448, 97, 21);
		contentPane.add(lblNewLabel_10_4_2);
		
		JLabel lblNewLabel_10_4_3 = new JLabel("");
		lblNewLabel_10_4_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_10_4_3.setBounds(329, 484, 110, 21);
		contentPane.add(lblNewLabel_10_4_3);
		
		JScrollPane scrollPanePronostico = new JScrollPane();
		scrollPanePronostico.setBounds(new Rectangle(138, 274, 406, 116));
		scrollPanePronostico.setBounds(42, 185, 378, 146);
		contentPane.add(scrollPanePronostico);
		tableModelPronostico = new DefaultTableModel(null, columnNamesPronostico);
		tablePronosticos.setModel(tableModelPronostico);	
		tablePronosticos.getColumnModel().getColumn(0).setPreferredWidth(25);
		tablePronosticos.getColumnModel().getColumn(1).setPreferredWidth(268);
		scrollPanePronostico.setViewportView(tablePronosticos);
		
		JLabel lblNewLabel_11 = new JLabel("Saldo :");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_11.setBounds(29, 154, 84, 21);
		contentPane.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_12.setBounds(90, 154, 128, 21);
		contentPane.add(lblNewLabel_12);
		
		
		
		JLabel errorLabel = new JLabel("");
		errorLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		errorLabel.setBounds(115, 564, 216, 21);
		contentPane.add(errorLabel);
		
		textField = new JTextField();
		textField.setForeground(Color.BLACK);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(204, 532, 216, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		lblNewLabel_12.setText(user.getDinero()+"");
		
		JButton btnNewButton = new JButton("Rercargar Saldo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					facade.aumentarDinero(user, Double.parseDouble(textField.getText()));
					errorLabel.setForeground(Color.BLACK);
					errorLabel.setText("Se ha recargado correctamente");
					lblNewLabel_12.setText(user.getDinero()+"");
				}catch(Exception exc){
					errorLabel.setForeground(Color.RED);
					errorLabel.setText("Error: No es un numero");
					textField.setText("");
					
				}
				
				
			}
		});

		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(29, 533, 165, 21);
		contentPane.add(btnNewButton);


		tableModelPronostico.setDataVector(null, columnNamesPronostico);
        for(Bet a: apuestas) {
        	Vector<Object> row = new Vector<Object>();
        	
    		row.add(a.getBetNumber());
    		row.add(a.getPronostico());
    		tableModelPronostico.addRow(row);	
        }
		
        tablePronosticos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DecimalFormat formato1 = new DecimalFormat("#.00");
				int i=tablePronosticos.getSelectedRow();
			    Bet ap=apuestas.get(i);
			    Pronostico pro=ap.getPronostico();
			    Question qu=pro.getQuestion();
			    Event ev= qu.getEvent();
			    
			    lblNewLabel_10_2.setText(ev.getDescription());
			    lblNewLabel_10_3.setText(qu.getQuestion());
			    lblNewLabel_10_4_2.setText(pro.getPorcentajeApuesta()+"%");
			    lblNewLabel_10_4.setText(pro.getCuota()+"");
			    lblNewLabel_10_4_1.setText(ap.getBet()+"");
			    lblNewLabel_10_4_3.setText(formato1.format(ap.getGanancia()));
			    lblNewLabel_12.setText(user.getDinero()+"");
				
			}
		});

    	tablePronosticos.getColumnModel().getColumn(0).setPreferredWidth(25);
    	tablePronosticos.getColumnModel().getColumn(1).setPreferredWidth(268);
		
	}
}
