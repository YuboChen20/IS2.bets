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
import domain.Equipo;
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

public class RankingGUI extends JFrame {
	

	private JPanel contentPane;
	private final JScrollPane scrollPanePronostico = new JScrollPane();
	private final JTable tablePronosticos = new JTable();
	private DefaultTableModel tableModelPronostico;
	private String[] columnNamesPronostico = new String[] {
			"#", 
			"Equipo",
			"Usuarios"

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
					RankingGUI frame = new RankingGUI();
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

	
	public RankingGUI() {
		BLFacade facade = MainGUI.getBusinessLogic(); 
		
		
		setTitle("Informacion del usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 477, 652);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JScrollPane scrollPanePronostico = new JScrollPane();
		scrollPanePronostico.setBounds(new Rectangle(138, 274, 406, 116));
		scrollPanePronostico.setBounds(33, 28, 378, 390);
		contentPane.add(scrollPanePronostico);
		tableModelPronostico = new DefaultTableModel(null, columnNamesPronostico);
		tablePronosticos.setModel(tableModelPronostico);	
		tablePronosticos.getColumnModel().getColumn(0).setPreferredWidth(25);
		tablePronosticos.getColumnModel().getColumn(1).setPreferredWidth(268);
		scrollPanePronostico.setViewportView(tablePronosticos);
		
		
		
		
		
		
		

		


		tableModelPronostico.setDataVector(null, columnNamesPronostico);
		
		List<Equipo> equipos=facade.getAllEquiposPorUsuarios();
		int n=equipos.size();
		
        for(int i=0;i<n;i++) {
        	Vector<Object> row = new Vector<Object>();
        	row.add(i+1);
        	row.add(equipos.get(i).getNombre());
    		row.add((int)equipos.get(i).getNumUsuariosApuestan());
    		
    		tableModelPronostico.addRow(row);	
        }
        
		
		
        
    	tablePronosticos.getColumnModel().getColumn(0).setPreferredWidth(25);
    	tablePronosticos.getColumnModel().getColumn(1).setPreferredWidth(268);
    	tablePronosticos.getColumnModel().getColumn(2).setPreferredWidth(25);
		
	}
}
