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
	private String[] columnNamesPronosticoMode1 = new String[] {
			"#", 
			"Equipo",
			"#Apuestas"

	};
	
	private String[] columnNamesPronosticoMode0 = new String[] {
			"#", 
			"Equipo",
			"Dinero"

	};
	
   
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
		
		
		setTitle("Ranking");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 477, 652);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JScrollPane scrollPanePronostico = new JScrollPane();
		scrollPanePronostico.setBounds(new Rectangle(138, 274, 406, 116));
		scrollPanePronostico.setBounds(33, 77, 378, 390);
		contentPane.add(scrollPanePronostico);
		tableModelPronostico = new DefaultTableModel(null, columnNamesPronosticoMode0);
		tablePronosticos.setModel(tableModelPronostico);	
		tablePronosticos.getColumnModel().getColumn(0).setPreferredWidth(25);
		tablePronosticos.getColumnModel().getColumn(1).setPreferredWidth(268);
		scrollPanePronostico.setViewportView(tablePronosticos);
		
		JLabel lblTipoDeRanking = new JLabel("Equipo por el que m�s dinero se apuesta");
		lblTipoDeRanking.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTipoDeRanking.setBounds(33, 22, 401, 44);
		contentPane.add(lblTipoDeRanking);
		
		JButton btnPorUsuarios = new JButton("Por apuestas");
		btnPorUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblTipoDeRanking.setText("Equipo por el que m�s veces se ha apostado");
				tableModelPronostico.setDataVector(null, columnNamesPronosticoMode1);
				while(tableModelPronostico.getRowCount()>0) tableModelPronostico.removeRow(0);
				List<Equipo> equipos=facade.getAllEquipos(1);
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
		});
		btnPorUsuarios.setBounds(33, 490, 125, 23);
		contentPane.add(btnPorUsuarios);
		
		JButton btnPorDinero = new JButton("Por dinero");
		btnPorDinero.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblTipoDeRanking.setText("Equipo por el que m�s dinero se apuesta");
				tableModelPronostico.setDataVector(null, columnNamesPronosticoMode0);
				
				while(tableModelPronostico.getRowCount()>0) tableModelPronostico.removeRow(0);
				List<Equipo> equipos=facade.getAllEquipos(0);
				int n=equipos.size();
				
		        for(int i=0;i<n;i++) {
		        	Vector<Object> row = new Vector<Object>();
		        	row.add(i+1);
		        	row.add(equipos.get(i).getNombre());
		    		row.add((int)equipos.get(i).getDineroApostado());
		    		
		    		tableModelPronostico.addRow(row);	
		        }
		        
		        tablePronosticos.getColumnModel().getColumn(0).setPreferredWidth(25);
		    	tablePronosticos.getColumnModel().getColumn(1).setPreferredWidth(268);
		    	tablePronosticos.getColumnModel().getColumn(2).setPreferredWidth(25);
			}
		});
		btnPorDinero.setBounds(156, 490, 113, 23);
		contentPane.add(btnPorDinero);
		
		
		
		
		
		
		
		lblTipoDeRanking.setText("Equipo por el que m�s dinero se apuesta");
		

		tableModelPronostico.setDataVector(null, columnNamesPronosticoMode0);
		
		List<Equipo> equipos=facade.getAllEquipos(0);
		int n=equipos.size();
		
        for(int i=0;i<n;i++) {
        	Vector<Object> row = new Vector<Object>();
        	row.add(i+1);
        	row.add(equipos.get(i).getNombre());
    		row.add((int)equipos.get(i).getDineroApostado());
    		
    		tableModelPronostico.addRow(row);	
        }
        
		
		
        
    	tablePronosticos.getColumnModel().getColumn(0).setPreferredWidth(25);
    	tablePronosticos.getColumnModel().getColumn(1).setPreferredWidth(268);
    	tablePronosticos.getColumnModel().getColumn(2).setPreferredWidth(25);
		
	}
}
