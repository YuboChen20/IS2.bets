package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Entrada;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import domain.Usuario;
import exceptions.PronosticAlreadyExist;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;

public class HistorialGUI extends JFrame {

	private JPanel contentPane;
	private final JTable tableUser = new JTable();
	private final JTable tableUser1 = new JTable();
	private DefaultTableModel tableModelUserBlo;
	private DefaultTableModel tableModelUserBlo1;
	JComboBox<String> jComboBoxTipoUsers = new JComboBox<String>();
	DefaultComboBoxModel<String> modelEvents = new DefaultComboBoxModel<String>();
	JComboBox<String> jcbUserAdmin = new JComboBox<String>();
	DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
	private JButton btnBloquear = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Bloquear"));
	private JButton btnDesbloquear = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Desbloquear"));
	private String[] columnNamesUser = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("Usuario"), 
			ResourceBundle.getBundle("Etiquetas").getString("Fecha"), 
	};
	
	private String[] columnNamesUser1 = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("Fecha1"), 
			ResourceBundle.getBundle("Etiquetas").getString("Estado"), 
			
	};
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistorialGUI frame = new HistorialGUI();
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
	public HistorialGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 701, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		JLabel lblErrorButton = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ErrorButton")); //$NON-NLS-1$ //$NON-NLS-2$
		lblErrorButton.setForeground(Color.RED);
		lblErrorButton.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorButton.setBounds(32, 333, 202, 14);
		contentPane.add(lblErrorButton);

		
		jComboBoxTipoUsers.setBounds(14, 28, 194, 22);
		contentPane.add(jComboBoxTipoUsers);
		jComboBoxTipoUsers.setModel(modelEvents);
		modelEvents.addElement("Bloqueados");
		jComboBoxTipoUsers.repaint();
		modelEvents.addElement("No Bloqueados");
		jComboBoxTipoUsers.repaint();
		
		jComboBoxTipoUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblErrorButton.setText(" ");
				String s= (String) jComboBoxTipoUsers.getSelectedItem(); // obtain object
				actualizarTabla1(s);
				tableUser.getSelectionModel().setSelectionInterval(0,0);
			}
		});
		
		
		
		jcbUserAdmin.setBounds(218, 28, 124, 22);
		contentPane.add(jcbUserAdmin);
		jcbUserAdmin.setModel(model);
		model.addElement("Usuarios");
		jcbUserAdmin.repaint();
		model.addElement("Admin");
		jcbUserAdmin.repaint();
		
		jcbUserAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblErrorButton.setText(" ");
				String s= (String) jComboBoxTipoUsers.getSelectedItem(); 
				actualizarTabla1(s);
				tableUser.getSelectionModel().setSelectionInterval(0,0);
			}
		});
		
		
		JScrollPane scrollPaneUserBlo = new JScrollPane();
		scrollPaneUserBlo.setBounds(14, 51, 328, 246);
		contentPane.add(scrollPaneUserBlo);
		getContentPane().add(scrollPaneUserBlo);
		tableModelUserBlo = new DefaultTableModel(null, columnNamesUser){
				boolean[] columnEditables = new boolean[] {
					false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}	
		};	
		tableUser.setModel(tableModelUserBlo);
		tableUser.getColumnModel().getColumn(0).setPreferredWidth(80);
		tableUser.getColumnModel().getColumn(1).setPreferredWidth(213);
		scrollPaneUserBlo.setViewportView(tableUser);
		
		
		JScrollPane scrollPaneUserBlo_1 = new JScrollPane();
		scrollPaneUserBlo_1.setBounds(352, 51, 327, 246);
		contentPane.add(scrollPaneUserBlo_1);
		getContentPane().add(scrollPaneUserBlo_1);
		tableModelUserBlo1 = new DefaultTableModel(null, columnNamesUser1){
				boolean[] columnEditables = new boolean[] {
					false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}	
		};
		tableUser1.setModel(tableModelUserBlo1);
		tableUser1.getColumnModel().getColumn(0).setPreferredWidth(223);
		tableUser1.getColumnModel().getColumn(0).setPreferredWidth(50);
		scrollPaneUserBlo_1.setViewportView(tableUser1);
		
		
		
		
		tableUser.addMouseListener(new MouseAdapter() {  //seleccionar una fila de la tabla1 => poner el historial del usuario seleccionado en la tabla2
			@Override
			public void mouseClicked(MouseEvent e) {
				lblErrorButton.setText(" ");
				actualizarTabla2(null);
			}
		});
		
		JButton btnAtras = new JButton("Atr\u00E1s");
		btnAtras.setBounds(0, 0, 90, 22);
		contentPane.add(btnAtras);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonAtras_actionPerformed(e);
			}
		});
		getContentPane().add(btnAtras);
		
		
		btnBloquear.setBounds(22, 308, 103, 23);
		contentPane.add(btnBloquear);
		btnBloquear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=tableUser.getSelectedRow();
				if(i>=0) {
					String s= (String) jComboBoxTipoUsers.getSelectedItem();
					String s2= (String) jcbUserAdmin.getSelectedItem(); 
					BLFacade facade = MainGUI.getBusinessLogic();
					Usuario u=facade.getUsuario(s,s2,i);
					facade.desBloquear(s,u);
					actualizarTabla1(s);
					tableUser.getSelectionModel().setSelectionInterval(0,0);
				}else {
					lblErrorButton.setText("No se ha seleccionado un usuario");
				}
			}
		});
		getContentPane().add(btnBloquear);
		
		
		btnDesbloquear.setBounds(142, 308, 121, 23);
		contentPane.add(btnDesbloquear);
		btnDesbloquear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=tableUser.getSelectedRow();
				if(i>=0) {
					String s= (String) jComboBoxTipoUsers.getSelectedItem();
					String s2= (String) jcbUserAdmin.getSelectedItem(); 
					BLFacade facade = MainGUI.getBusinessLogic();
					Usuario u=facade.getUsuario(s,s2,i);
					facade.desBloquear(s,u);
					actualizarTabla1(s);
					tableUser.getSelectionModel().setSelectionInterval(0,0);
				}else {
					lblErrorButton.setText("No se ha seleccionado un usuario");
				}
			}
		});
		
	}
	
	private void actualizarTabla1(String s) {
		try {
			
			if(s.equals("Bloqueados")) {
				btnDesbloquear.setEnabled(true);
				btnBloquear.setEnabled(false);
			}else {
				btnDesbloquear.setEnabled(false);
				btnBloquear.setEnabled(true);
			}
				
				
			int n= tableModelUserBlo.getRowCount();     //Se borra lo que había en la tabla 1 
			for(int i1=0;i1<n;i1++) {
				tableModelUserBlo.removeRow(0);
			}
			String s2= (String) jcbUserAdmin.getSelectedItem(); 
			System.out.println("Seleccion: "+ s+" y "+s2);
			BLFacade facade = MainGUI.getBusinessLogic();
			List<Usuario> usuarios=facade.getUsuarios(s,s2);  //todos los usuarios q estenbloqueado/noestenbloqueados
			for (domain.Usuario u:usuarios){
				Vector<Object> row = new Vector<Object>();
	
				row.add(u.getUserName());
				row.add(u.getFecha());

				tableModelUserBlo.addRow(row);	    //se añaden a la tabla1
			}
			tableUser.getColumnModel().getColumn(0).setPreferredWidth(80);
			tableUser.getColumnModel().getColumn(1).setPreferredWidth(213);
			
			int m= tableModelUserBlo1.getRowCount();     //se elimina lo que había en la tabla 2
			for(int i1=0;i1<m;i1++) {
				tableModelUserBlo1.removeRow(0);
			}
			actualizarTabla2(usuarios.get(0));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void actualizarTabla2(Usuario u) {
		try {
			int m= tableModelUserBlo1.getRowCount();     //se elimina lo que había en la tabla 2
			for(int i1=0;i1<m;i1++) {
				tableModelUserBlo1.removeRow(0);
			}
		if(u==null) {
			String s= (String) jComboBoxTipoUsers.getSelectedItem();
			String s2= (String) jcbUserAdmin.getSelectedItem(); 
			int i=tableUser.getSelectedRow();            //indice de la tabla1 selecionado
			BLFacade facade = MainGUI.getBusinessLogic();
			Usuario us=facade.getUsuario(s,s2,i);           //obtener el usuario seleccionado
			u=us;
			if(s.equals("Bloqueados"))btnDesbloquear.setEnabled(true);
			else btnBloquear.setEnabled(true);
		}else if(u.isBloqueado())btnDesbloquear.setEnabled(true);
		else if(!u.isBloqueado())btnBloquear.setEnabled(true);
		
		
		Vector<Entrada> historial= u.getFechas();
		
			for (int j=historial.size()-1; j>=0; j--){   //añadir su historial a la tabla2
				Vector<Object> row = new Vector<Object>();

				row.add(historial.get(j).getS());
				row.add(historial.get(j).isBloqueado());
				tableModelUserBlo1.addRow(row);	
			}
			tableUser1.getColumnModel().getColumn(0).setPreferredWidth(223);
			tableUser1.getColumnModel().getColumn(1).setPreferredWidth(50);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	private void jButtonAtras_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		CreateAndQueryGUI a =CreateAndQueryGUI.getInstance();
		a.setVisible(true);
	}
}