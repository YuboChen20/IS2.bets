package gui;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

import javax.swing.*;

import com.toedter.calendar.JCalendar;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import exceptions.EventFinishedException;
import exceptions.PronosticAlreadyExist;
import exceptions.QuestionAlreadyExist;
import javax.swing.table.DefaultTableModel;

public class CloseBetGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	String etiqueta = "Etiquetas";
	

	private JComboBox<Event> jComboBoxEvents = new JComboBox<Event>();
	DefaultComboBoxModel<Event> modelEvents = new DefaultComboBoxModel<Event>();

	private JLabel jLabelListOfEvents = new JLabel(ResourceBundle.getBundle(etiqueta).getString("ListEvents"));
	private Calendar calendarAct = null;
	private Calendar calendarAnt = null;

	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();
	private final JScrollPane scrollPaneQueries = new JScrollPane();
	
	private JTable tableQueries = new JTable();
	private DefaultTableModel tableModelQueries;
	
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle(etiqueta).getString("QueryN"), 
			ResourceBundle.getBundle(etiqueta).getString("Query")

	};
	private String[] columnNamesPronostico = new String[] {
			ResourceBundle.getBundle(etiqueta).getString("PronosticoN"), 
			ResourceBundle.getBundle(etiqueta).getString("Pronostico"),
            ResourceBundle.getBundle(etiqueta).getString("Cuota"),
           
	};
	private final JLabel jLabelMsg2 = new JLabel();
	private final JScrollPane scrollPanePronostico = new JScrollPane();
	private final JTable tablePronosticos = new JTable();
	private DefaultTableModel tableModelPronostico;
	private final JButton jButtonCerrarEvento = new JButton(ResourceBundle.getBundle(etiqueta).getString("CreateAndQueryGUI.btnNewButton_2.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle(etiqueta).getString("CreateAndQueryGUI.lblNewLabel_2.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel lblNewLabel_3 = new JLabel(); 
	private final JLabel lblNewLabel_3_1 = new JLabel();
	private final JButton jButtonCerrarConsulta = new JButton("Cerrar Consulta"); //$NON-NLS-1$ //$NON-NLS-2$
	private final JButton btnButtonCerrarApuesta = new JButton(ResourceBundle.getBundle(etiqueta).getString("CreateAndQueryGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JButton btnAtras = new JButton(ResourceBundle.getBundle(etiqueta).getString("CloseBetGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel lblFecha = new JLabel(ResourceBundle.getBundle(etiqueta).getString("CloseBetGUI.lblNewLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
	
	
	public CloseBetGUI(Vector<domain.Event> v) {
		try {
			jbInit(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit(Vector<domain.Event> v) throws Exception {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(379, 450));
		
		jComboBoxEvents.setBounds(54, 43, 250, 20);
		jComboBoxEvents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					domain.Event ev=(domain.Event)jComboBoxEvents.getSelectedItem(); // obtain ev object
					if(ev!=null) {
						lblFecha.setText(""+ev.getEventDate());
						Vector<Question> questions=ev.getQuestions();					
						tableModelQueries.setDataVector(null, columnNamesQueries);
						for (domain.Question q:questions){
							Vector<Object> row = new Vector<Object>();
						
							row.add(q.getQuestionNumber());
							row.add(q.getQuestion());
							tableModelQueries.addRow(row);	
							
						}
					
						tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);		
						try {
							BLFacade facade = MainGUI.getBusinessLogic();
							List<Pronostico> pronosticos=facade.findPronosticos(questions.get(0));
						

			            	tableModelPronostico.setDataVector(null, columnNamesPronostico);


			            	for (domain.Pronostico p:pronosticos){
			            		Vector<Object> row = new Vector<Object>();

			            		row.add(p.getPronosNumber());
			            		row.add(p.getPronostico());
			            		row.add(p.getCuota());
			            		System.out.println(p.getCuota());
			            		tableModelPronostico.addRow(row);	
			            	}
			            	tablePronosticos.getColumnModel().getColumn(0).setPreferredWidth(25);
			            	tablePronosticos.getColumnModel().getColumn(1).setPreferredWidth(268);
						}catch(Exception e1) {
							e1.printStackTrace();
							lblNewLabel_3.setText(ResourceBundle.getBundle(etiqueta).getString("ErrorPronosNoSelect"));
						}
						
					}
					else {
						lblFecha.setText("No hay eventos finalizados sin cerrar");
						
					}
	
			}		
			
		});
		

		jComboBoxEvents.setModel(modelEvents);
		getContentPane().setLayout(null);
		scrollPaneQueries.setBounds(54, 87, 250, 97);
		
		getContentPane().add(scrollPaneQueries);
		jLabelListOfEvents.setBounds(54, 22, 333, 20);
		this.getContentPane().add(jLabelListOfEvents);
		this.getContentPane().add(jComboBoxEvents);
		
		
		BLFacade facade = MainGUI.getBusinessLogic();
		
		
		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries){
				boolean[] columnEditables = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
				
			};

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
		this.getContentPane().add(scrollPaneQueries, null);
		jLabelMsg2.setBounds(54, 352, 250, 20);
		jLabelMsg2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		jLabelMsg2.setForeground(Color.RED);
		
		getContentPane().add(jLabelMsg2);
		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jLabelMsg2.setText("");
				int i=tableQueries.getSelectedRow();
				Event ev= (Event) jComboBoxEvents.getSelectedItem();
				Question q = facade.getQuestion(ev, i);
				boolean isclosedQue=q.isIsclosed();
				LocalDateTime time=LocalDateTime.now();
				Date date= UtilDate.newDate(time.getYear(),time.getMonthValue()-1,time.getDayOfMonth());
				BLFacade facade = MainGUI.getBusinessLogic();
				boolean isCerrado= facade.isEventoCerrar(date, q.getEvent());
				if(!isclosedQue) {
					
					lblNewLabel_3.setText("Consulta sin cerrar");
				}
				else{
					
					lblNewLabel_3.setText("Consulta Cerrada");
				}
			
			//	domain.Question ev=(domain.Question)tableModelPronostico.getValueAt(i,2); // obtain ev object
				
				
                try {
                	List<Pronostico> pronosticos=facade.findPronosticos(q);
        

                	tableModelPronostico.setDataVector(null, columnNamesPronostico);

   
                	for (domain.Pronostico p:pronosticos){
                		Vector<Object> row = new Vector<Object>();

                		row.add(p.getPronosNumber());
                		row.add(p.getPronostico());
                		row.add(p.getCuota());
                		System.out.println(p.getCuota());
                		tableModelPronostico.addRow(row);	
                	}
                	tablePronosticos.getColumnModel().getColumn(0).setPreferredWidth(25);
                	tablePronosticos.getColumnModel().getColumn(1).setPreferredWidth(268);
                	Vector<Question> que= q.getEvent().getQuestions();
              
                }catch(PronosticAlreadyExist e1) {
                	jLabelMsg2.setText(ResourceBundle.getBundle(etiqueta).getString("ErrorPronosAlreadyEx"));
                }
			}
		});
		scrollPanePronostico.setBounds(54, 209, 250, 111);
		
		getContentPane().add(scrollPanePronostico);
		tableModelPronostico = new DefaultTableModel(null, columnNamesPronostico){
				boolean[] columnEditables = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
				
			};
		tablePronosticos.setModel(tableModelPronostico);

		
		tablePronosticos.getColumnModel().getColumn(0).setPreferredWidth(25);
		tablePronosticos.getColumnModel().getColumn(1).setPreferredWidth(268);
		scrollPanePronostico.setViewportView(tablePronosticos);
		
		JLabel jLabelCuota = new JLabel();
		jLabelCuota.setBounds(602, 363, 238, -22);
		getContentPane().add(jLabelCuota);
		jButtonCerrarConsulta.setBounds(54, 319, 250, 20);
		

		jButtonCerrarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				jLabelMsg2.setText("");
				try {
					Event ev= (Event) jComboBoxEvents.getSelectedItem();
					if(ev!=null) {
						int i= tableQueries.getSelectedRow();	
						
						if(i!=-1) {
							Question q = facade.getQuestion(ev,i);
							if(!q.isIsclosed()) {
								int ind= tablePronosticos.getSelectedRow();
								if(ind!=-1) {
									if(ind<= q.getListPronosticos().size()) {
										Pronostico pro= q.getPronosticos().elementAt(ind);
										facade.cerrarApuesta(pro);
										lblNewLabel_3.setText("Consulta Finalizada");
										Vector<Question> questions= ev.getQuestions();
									}
								}
								else jLabelMsg2.setText("No se ha seleccionado un pronóstico");
							}else {
								lblNewLabel_3.setText("La consulta ya esta finalizada");
							}
						}
						else jLabelMsg2.setText("No se ha seleccionado una consulta");
					
					}
				}catch(Exception e1) {
					e1.printStackTrace();
					lblNewLabel_3.setText(ResourceBundle.getBundle(etiqueta).getString("ErrorPronosNoSelect"));
				}
			}
		});
		getContentPane().add(jButtonCerrarConsulta);
	
		jButtonCerrarEvento.setBounds(54, 375, 250, 21);
	
		jButtonCerrarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				jLabelMsg2.setText("");
				Event ev= (Event) jComboBoxEvents.getSelectedItem();
				boolean comp=true;
				for(Question q: facade.getQuestionList(ev)) {
					if(!q.isIsclosed()) comp=false;
				}
				if(comp) {
					facade.cerrarEvento(ev);
					//lblNewLabel_3_1.setText("Evento cerrado");
					modelEvents.removeElement(jComboBoxEvents.getSelectedItem());
				}else jLabelMsg2.setText("Hay consulta sin finalizar");
			}
		});
		
		getContentPane().add(jButtonCerrarEvento);
		lblNewLabel_2.setBounds(54, 195, 112, 13);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		getContentPane().add(lblNewLabel_2);
		lblNewLabel_3.setBounds(155, 195, 149, 13);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		getContentPane().add(lblNewLabel_3);
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnAtras.setBounds(0, 0, 68, 20);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonAtras_actionPerformed(e);
			}
		});
		getContentPane().add(btnAtras);
		
		
		lblFecha.setBounds(64, 69, 229, 14);
		getContentPane().add(lblFecha);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_3_1.setBounds(404, 80, 145, 13);
		
		actualizarTabla();
		

	}
	
	
	private void actualizarTabla(){
			jLabelMsg2.setText("");
			BLFacade facade = MainGUI.getBusinessLogic();
			LocalDateTime time=LocalDateTime.now();
			Date date= UtilDate.newDate(time.getYear(),time.getMonthValue()-1,time.getDayOfMonth());
			Vector<Event> events= facade.getEventosFinalizadosNoCerrados(date);
	System.out.println(events);
			modelEvents.removeAllElements();
			System.out.println("Eventos sin finalizar: "+events.size());
			if(events.size()!=0) {
				for(Event ev: events) {
					modelEvents.addElement(ev);
				}
				lblFecha.setText(""+events.get(0).getEventDate());
				Vector<Question> questions= events.get(0).getQuestions();
				tableModelQueries.setDataVector(null, columnNamesQueries);
				int i1=0;
				for (domain.Question q:questions){
					Vector<Object> row = new Vector<Object>();
					if(q.isIsclosed()==true)i1++;
					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					tableModelQueries.addRow(row);	
					
				}
				
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
			try {
				List<Pronostico> pronosticos=facade.findPronosticos(questions.get(0));
			

            	tableModelPronostico.setDataVector(null, columnNamesPronostico);


            	for (domain.Pronostico p:pronosticos){
            		Vector<Object> row = new Vector<Object>();

            		row.add(p.getPronosNumber());
            		row.add(p.getPronostico());
            		row.add(p.getCuota());
            		System.out.println(p.getCuota());
            		tableModelPronostico.addRow(row);	
            	}
            	tablePronosticos.getColumnModel().getColumn(0).setPreferredWidth(25);
            	tablePronosticos.getColumnModel().getColumn(1).setPreferredWidth(268);
			}catch(Exception e1) {
				e1.printStackTrace();
				lblNewLabel_3.setText(ResourceBundle.getBundle(etiqueta).getString("ErrorPronosNoSelect"));
			}
           
			}
			else {
				lblFecha.setText("No hay eventos finalizados sin cerrar");
			}

	}

	private void jButtonAtras_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		CreateAndQueryGUI a =CreateAndQueryGUI.getInstance();
		a.setVisible(true);
	}
}