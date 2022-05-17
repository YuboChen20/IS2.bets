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
import domain.Liga;
import domain.Pronostico;
import domain.Question;
import domain.Usuario;
import exceptions.MaximumNumberOfTeamsReached;
import exceptions.PronosticAlreadyExist;
import exceptions.TeamAlreadyExistsException;

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

public class CrearLigaGUI extends JFrame {
	

	private JPanel contentPane;
	private final JTable tableEquiposLiga = new JTable();
	private DefaultTableModel tableModelEquipos;
	private String[] columnNamesEquipos = new String[] {
			"#", 
			"Equipo",
	};
	
	private final JTable tableLigas = new JTable();
	private DefaultTableModel tableModelLigas;
	private String[] columnNamesLiga = new String[] {
			"Liga",
			"ObjetoLiga"
	};
	private JTextField textFieldNombreLiga;
	private JTextField textFieldNumMaxEquipos;
	private JTextField textFieldNombreEquipo;
	
	
   
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

	
	public CrearLigaGUI() {
		BLFacade facade = MainGUI.getBusinessLogic(); 
		
		
		setTitle("Crear Liga");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 685, 652);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JScrollPane scrollPaneEquipos = new JScrollPane();
		scrollPaneEquipos.setBounds(new Rectangle(138, 274, 406, 116));
		scrollPaneEquipos.setBounds(33, 77, 335, 390);
		contentPane.add(scrollPaneEquipos);
		tableModelEquipos = new DefaultTableModel(null, columnNamesEquipos);
		tableEquiposLiga.setModel(tableModelEquipos);	
		tableEquiposLiga.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEquiposLiga.getColumnModel().getColumn(1).setPreferredWidth(268);
		scrollPaneEquipos.setViewportView(tableEquiposLiga);
		
		JLabel lblNombreLiga = new JLabel("Liga Santander");
		lblNombreLiga.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombreLiga.setBounds(33, 22, 378, 44);
		contentPane.add(lblNombreLiga);
		
		
	

		tableModelEquipos.setDataVector(null, columnNamesEquipos);
		
		List<Equipo> equipos=facade.getEquiposPorLiga(2, "Liga Santander");
		int n=equipos.size();
		
        for(int i=0;i<n;i++) {
        	Vector<Object> row = new Vector<Object>();
        	row.add(i+1);
        	row.add(equipos.get(i).getNombre());
    		row.add((int)equipos.get(i).getDineroApostado());
    		
    		tableModelEquipos.addRow(row);	
        }
        
    	tableEquiposLiga.getColumnModel().getColumn(0).setPreferredWidth(25);
    	tableEquiposLiga.getColumnModel().getColumn(1).setPreferredWidth(268);
    	

    	tableLigas.setDefaultRenderer(Object.class, new Render());
    	
    	
    	tableLigas.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
    			while(tableModelEquipos.getRowCount()>0) tableModelEquipos.removeRow(0);
    			
    			int j=tableLigas.getSelectedRow();
    			Liga l = (Liga)tableModelLigas.getValueAt(j, 1);
    			List <Equipo> equipos= facade.getEquiposPorLiga(2, l);
    			lblNombreLiga.setText(l.getNombre());
    			
    			int n=equipos.size();
    			
    	        for(int i=0;i<n;i++) {
    	        	Vector<Object> row = new Vector<Object>();
    	        	row.add(i+1);
    	        	row.add(equipos.get(i).getNombre());
    	    		tableModelEquipos.addRow(row);	
    	        }
    			
    			
    			tableLigas.setRowSelectionInterval(j, j);
    		}
    	});
    	
    	
    	
    	//////////////////////////////////////////////////////////
    	
    	
    	
    	JScrollPane scrollPaneLigas = new JScrollPane();
		scrollPaneLigas.setBounds(430, 77, 199, 390);
		contentPane.add(scrollPaneLigas);
		
		tableModelLigas = new DefaultTableModel(null, columnNamesLiga) {
			boolean[] columnEditables = new boolean[] {
					false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			
		};
		
		/*
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents) {
			boolean[] columnEditables = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
		};
		*/
		tableLigas.setModel(tableModelLigas);	
		tableLigas.getColumnModel().getColumn(0).setPreferredWidth(268);
		scrollPaneLigas.setViewportView(tableLigas);
		
		JButton btnCrearLiga = new JButton("Crear Liga");
		btnCrearLiga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre= textFieldNombreLiga.getText();
				String numMaxString= textFieldNumMaxEquipos.getText();
				
				System.out.println("nombre: "+nombre+" num: "+numMaxString);
				if(nombre!=null && numMaxString!=null) {
					
					try {
						int numMax= Integer.parseInt(numMaxString);
						facade.crearLiga(nombre, numMax);
						
						List<Liga> ligas=facade.getAllLigas();
						
						while(tableModelLigas.getRowCount()>0) tableModelLigas.removeRow(0);
						int nL=ligas.size();
						
				        for(int i=0;i<nL;i++) {
				        	Vector<Object> row = new Vector<Object>();
				        	
				        	JButton l=new JButton(ligas.get(i).getNombre());
				        	row.add(l);
				    		row.add(ligas.get(i));
				    		
				    		tableModelLigas.addRow(row);	
				        }
				        
				    	tableLigas.getColumnModel().getColumn(0).setPreferredWidth(25);
				    	textFieldNombreLiga.setText("");
						textFieldNumMaxEquipos.setText("");
				    
						int k=tableLigas.getRowCount()-1;
						tableLigas.setRowSelectionInterval(k, k);
						while(tableModelEquipos.getRowCount()>0) tableModelEquipos.removeRow(0);
						lblNombreLiga.setText(tableModelLigas.getValueAt(k, 1).toString());
						
						CreateAndQueryGUI.getInstance().updateLeagues();
						
					}catch(NumberFormatException eNFE) {
						System.out.println("nombre: "+nombre+" num: "+numMaxString);
					}
					
					
				}
				
				
			}
		});
		
		btnCrearLiga.setBounds(430, 564, 199, 23);
		contentPane.add(btnCrearLiga);
		
		JLabel lblNombreLigaIns = new JLabel("Nombre Liga:");
		lblNombreLigaIns.setBounds(430, 477, 70, 23);
		contentPane.add(lblNombreLigaIns);
		
		textFieldNombreLiga = new JTextField();
		textFieldNombreLiga.setBounds(430, 510, 199, 20);
		contentPane.add(textFieldNombreLiga);
		textFieldNombreLiga.setColumns(10);
		
		JLabel lblNumeroMaxEquiposIns = new JLabel("Num. max equipos: ");
		lblNumeroMaxEquiposIns.setBounds(428, 540, 132, 14);
		contentPane.add(lblNumeroMaxEquiposIns);
		
		textFieldNumMaxEquipos = new JTextField();
		textFieldNumMaxEquipos.setBounds(537, 540, 92, 20);
		contentPane.add(textFieldNumMaxEquipos);
		textFieldNumMaxEquipos.setColumns(10);
		
		JLabel lblNombreEquipo = new JLabel("Nombre Equipo:");
		lblNombreEquipo.setBounds(33, 512, 89, 14);
		contentPane.add(lblNombreEquipo);
		
		textFieldNombreEquipo = new JTextField();
		textFieldNombreEquipo.setBounds(145, 510, 223, 20);
		contentPane.add(textFieldNombreEquipo);
		textFieldNombreEquipo.setColumns(10);
		
		JButton btnCrearEquipo = new JButton("Crear Equipo");
		btnCrearEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCrearEquipo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nombreEquipo=textFieldNombreEquipo.getText();
				if(nombreEquipo!=null) {
					Liga l=(Liga) tableModelLigas.getValueAt(tableLigas.getSelectedRow(), 1);
					try {
						facade.anadirEquipoALiga(nombreEquipo, l);
					} catch (TeamAlreadyExistsException e1) {
						System.out.println("TeamAlreadyExistsException");
					} catch (MaximumNumberOfTeamsReached e1) {
						System.out.println("MaximumNumberOfTeamsReached");
					}
					
					while(tableModelEquipos.getRowCount()>0) tableModelEquipos.removeRow(0);
	    			
	    			int j=tableLigas.getSelectedRow();
	    			List <Equipo> equipos= facade.getEquiposPorLiga(2, l);
	    			lblNombreLiga.setText(l.getNombre());
	    			
	    			int n=equipos.size();
	    			
	    	        for(int i=0;i<n;i++) {
	    	        	Vector<Object> row = new Vector<Object>();
	    	        	row.add(i+1);
	    	        	row.add(equipos.get(i).getNombre());
	    	    		tableModelEquipos.addRow(row);	
	    	        }
	    			
	    			
	    			tableLigas.setRowSelectionInterval(j, j);
	    			
	    			CreateAndQueryGUI.getInstance().updateTeams();
	    			
	 
				}
				
				
			}
		});
		btnCrearEquipo.setBounds(33, 564, 155, 23);
		contentPane.add(btnCrearEquipo);
		
		JButton btnEliminarEquipo = new JButton("Eliminar Equipo");
		btnEliminarEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminarEquipo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int j=tableEquiposLiga.getSelectedRow();
				String nombreEquipo=(String) tableModelEquipos.getValueAt(j, 1);
				Liga l=(Liga)tableModelLigas.getValueAt(tableLigas.getSelectedRow(), 1);
				facade.eliminarEquipoDeLiga(nombreEquipo, l);
				
				
				while(tableModelEquipos.getRowCount()>0) tableModelEquipos.removeRow(0);
    			
    		
    			List <Equipo> equipos= facade.getEquiposPorLiga(2, l);
    			lblNombreLiga.setText(l.getNombre());
    			
    			int n=equipos.size();
    			
    	        for(int i=0;i<n;i++) {
    	        	Vector<Object> row = new Vector<Object>();
    	        	row.add(i+1);
    	        	row.add(equipos.get(i).getNombre());
    	    		tableModelEquipos.addRow(row);	
    	        }
    			
    			
    			
    			CreateAndQueryGUI.getInstance().updateTeams();
				
				
			}
		});
		btnEliminarEquipo.setBounds(213, 564, 155, 23);
		contentPane.add(btnEliminarEquipo);
		

		JButton btnAtras = new JButton("Atr\u00E1s");
		btnAtras.setBounds(0, 0, 90, 22);
		contentPane.add(btnAtras);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonAtras_actionPerformed(e);
			}
		});
		getContentPane().add(btnAtras);
				
		tableModelLigas.setDataVector(null, columnNamesLiga);
		
		List<Liga> ligas=facade.getAllLigas();
		int nL=ligas.size();
		
        for(int i=0;i<nL;i++) {
        	Vector<Object> row = new Vector<Object>();
        	
        	JButton l=new JButton(ligas.get(i).getNombre());
        	row.add(l);
    		row.add(ligas.get(i));
    		
    		tableModelLigas.addRow(row);	
        }
        
    	tableLigas.getColumnModel().getColumn(0).setPreferredWidth(25);
    	tableLigas.getColumnModel().removeColumn(tableLigas.getColumnModel().getColumn(1));
    	
    	tableLigas.setRowSelectionInterval(0, 0);
    	
  
	}
	private void jButtonAtras_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		CreateAndQueryGUI a =CreateAndQueryGUI.getInstance();
		a.setVisible(true);
	}
}

