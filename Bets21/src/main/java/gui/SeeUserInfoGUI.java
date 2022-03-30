package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JTable;

public class SeeUserInfoGUI extends JFrame {

	private JPanel contentPane;
	private final JScrollPane scrollPanePronostico = new JScrollPane();
	private final JTable tablePronosticos = new JTable();
	private DefaultTableModel tableModelPronostico;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeeUserInfoGUI frame = new SeeUserInfoGUI();
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
	public SeeUserInfoGUI() {
		setTitle("Informacion del usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 477, 609);
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
		
		JScrollPane scrollPanePronostico = new JScrollPane();
		scrollPanePronostico.setBounds(new Rectangle(138, 274, 406, 116));
		scrollPanePronostico.setBounds(42, 185, 378, 146);
		contentPane.add(scrollPanePronostico);
		tablePronosticos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00BA", "Pronostico"
			}
		));
		tablePronosticos.getColumnModel().getColumn(0).setPreferredWidth(41);
		
		scrollPanePronostico.setViewportView(tablePronosticos);
		
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
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_9.setBounds(115, 34, 286, 21);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_10.setBounds(115, 73, 286, 21);
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel_10_1 = new JLabel("");
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
		lblNewLabel_10_4.setBounds(123, 448, 97, 21);
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
		lblNewLabel_10_4_3.setBounds(329, 477, 110, 21);
		contentPane.add(lblNewLabel_10_4_3);
	}
}
