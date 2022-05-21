package gui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Equipo;
import domain.Liga;
import exceptions.LeagueAlreadyExist;
import exceptions.LessThanMinimumTeamException;
import exceptions.MaximumNumberOfTeamsReached;
import exceptions.TeamAlreadyExistsException;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

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
	
	private JLabel lblErrorCrearEquipo = new JLabel("");

	
	private JLabel lblErrorCrearLiga = new JLabel("");
	
	JButton btnEliminarEquipo = new JButton("Eliminar Equipo");
	
	
   
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
    			
    			lblErrorCrearEquipo.setText("");
				lblErrorCrearLiga.setText("");
    			
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
    			
    			if(tableEquiposLiga.getRowCount()==0) btnEliminarEquipo.setEnabled(false);
    			else btnEliminarEquipo.setEnabled(true);
    			tableLigas.setRowSelectionInterval(j, j);
    			if(tableEquiposLiga.getRowCount()!=0) tableLigas.setRowSelectionInterval(0, 0);
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
		

		tableLigas.setModel(tableModelLigas);	
		tableLigas.getColumnModel().getColumn(0).setPreferredWidth(268);
		scrollPaneLigas.setViewportView(tableLigas);
		
		JButton btnCrearLiga = new JButton("Crear Liga");
		btnCrearLiga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblErrorCrearLiga.setText("");
				lblErrorCrearEquipo.setText("");
				String nombre= textFieldNombreLiga.getText();
				String numMaxString= textFieldNumMaxEquipos.getText();
				
				System.out.println("nombre: "+nombre+" num: "+numMaxString);
				if(nombre!=null && numMaxString!=null && !nombre.equals("")) {
					
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
				    
						
						while(tableModelEquipos.getRowCount()>0) tableModelEquipos.removeRow(0);
						
						
						CreateAndQueryGUI.getInstance().updateLeagues();
						
						List<Liga> ligas2= facade.getAllLigas();
						int pos=-1;
						for(int i=0; i<ligas2.size();i++) 
							if(ligas2.get(i).getNombre().equals(nombre)) pos=i;
						tableLigas.setRowSelectionInterval(pos, pos);
						lblNombreLiga.setText(nombre);
						
						List<Equipo> teams=facade.getEquiposPorLiga(2, nombre);
			
						int n=teams.size();
						
		    	        for(int i=0;i<n;i++) {
		    	        	Vector<Object> row = new Vector<Object>();
		    	        	row.add(i+1);
		    	        	row.add(teams.get(i).getNombre());
		    	    		tableModelEquipos.addRow(row);	
		    	        }
						
						btnEliminarEquipo.setEnabled(false);
						
						
					}catch(NumberFormatException eNFE) {
						lblErrorCrearLiga.setText("No se ha insertado un número");
					} catch(LessThanMinimumTeamException eLTMTE) {
						lblErrorCrearLiga.setText("Una liga debe tener al menos dos participantes");
					}catch(LeagueAlreadyExist eLAE) {
						lblErrorCrearLiga.setText("La liga ya existe");
					}
					
					
					
					
				}else lblErrorCrearLiga.setText("Nombre no insertado");
				
				
			}
		});
		
		btnCrearLiga.setBounds(430, 564, 199, 23);
		contentPane.add(btnCrearLiga);
		
		JLabel lblNombreLigaIns = new JLabel("Nombre Liga:");
		lblNombreLigaIns.setBounds(430, 477, 116, 23);
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
		lblNombreEquipo.setBounds(33, 512, 116, 14);
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
				lblErrorCrearEquipo.setText("");
				lblErrorCrearLiga.setText("");
				String nombreEquipo=textFieldNombreEquipo.getText();
				if(nombreEquipo!=null && !nombreEquipo.equals("")) {
					Liga l=(Liga) tableModelLigas.getValueAt(tableLigas.getSelectedRow(), 1);
					try {
						facade.anadirEquipoALiga(nombreEquipo, l);
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
		    			
		    			int pos=-1;
		    			for(int i=0;i<n;i++) {
		    				if(equipos.get(i).getNombre().equals(nombreEquipo)) pos=i;
		    	        }
		    			
		    			if(tableEquiposLiga.getRowCount()==1) tableEquiposLiga.setRowSelectionInterval(0, 0);
		    			else if(pos<tableEquiposLiga.getRowCount() && pos!=-1) tableEquiposLiga.setRowSelectionInterval(pos,pos);
		    			
		    			Liga otherLiga=CreateAndQueryGUI.getInstance().getSelectedLiga();
		    			if(otherLiga.getNombre().equals(l.getNombre()))
		    				CreateAndQueryGUI.getInstance().updateTeams();
		    			CreateAndQueryGUI.getInstance().setLigaSelection(otherLiga.getNombre());
		    			btnEliminarEquipo.setEnabled(true);
		 
					} catch (TeamAlreadyExistsException e1) {
						System.out.println("TeamAlreadyExistsException");
						lblErrorCrearEquipo.setText("No puede haber dos equipos con el mismo nombre");
					} catch (MaximumNumberOfTeamsReached e1) {
						lblErrorCrearEquipo.setText("Se ha alcanzado el número máximo de Equipos");
					} catch(Exception e1) {
						lblErrorCrearEquipo.setText("No puede haber dos equipos con el mismo nombre");
						System.out.println(e1.getMessage());
					}
					
					
				}else lblErrorCrearEquipo.setText("Nombre no insertado");
				
				
			}
		});
		btnCrearEquipo.setBounds(33, 564, 155, 23);
		contentPane.add(btnCrearEquipo);
		
		
		btnEliminarEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminarEquipo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(btnEliminarEquipo.isEnabled()) {
					lblErrorCrearEquipo.setText("");
					lblErrorCrearLiga.setText("");
					
					int j=tableEquiposLiga.getSelectedRow();
				if(j>=0) {
					String nombreEquipo=(String) tableModelEquipos.getValueAt(j, 1);
					List<Equipo> teams =facade.getAllEquipos(2);
					int pos=-1;
					for(int i=0;i<teams.size();i++)
						if(nombreEquipo.equals(teams.get(i).getNombre())) pos=i;
					
					Equipo eq=teams.get(pos);
					
					
					Liga l=eq.getLiga();
					facade.eliminarEquipoDeLiga(nombreEquipo, l);
					
					l.eliminarEquipo(eq);
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
	    	        
	    	        if(tableEquiposLiga.getRowCount()>0) {
	    	        	if(j>=tableEquiposLiga.getRowCount()) tableEquiposLiga.setRowSelectionInterval(tableEquiposLiga.getRowCount()-1, tableEquiposLiga.getRowCount()-1);
	        	        else tableEquiposLiga.setRowSelectionInterval(j, j);
	    	        }
	    	        
	    			
	    			if(tableEquiposLiga.getRowCount()==0) btnEliminarEquipo.setEnabled(false); 
	    			
	    			CreateAndQueryGUI.getInstance().updateTeams();
				
				}else {
					lblErrorCrearEquipo.setText("No se ha seleccionado un equipo");
				}
				}
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
		
		
		lblErrorCrearEquipo.setBounds(78, 540, 290, 14);
		contentPane.add(lblErrorCrearEquipo);
		
		
		lblErrorCrearLiga.setBounds(440, 588, 189, 14);
		contentPane.add(lblErrorCrearLiga);
				
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
    	
    	
    	
    	if(tableLigas.getRowCount()>0) {
    		
    		List<Liga> leagues=facade.getAllLigas();
    		int pos=-1;
    		for(int i=0;i<leagues.size();i++)
    			if(leagues.get(i).getNombre().equals("Liga Santander")) pos=i;
    		if(pos!=-1)
    			tableLigas.setRowSelectionInterval(pos, pos);
    		
    		
    	}
    		
    	
    	lblErrorCrearEquipo.setForeground(Color.red);
    	lblErrorCrearLiga.setForeground(Color.red);
  
	}
	private void jButtonAtras_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		CreateAndQueryGUI a =CreateAndQueryGUI.getInstance();
		a.setVisible(true);
	}
}

