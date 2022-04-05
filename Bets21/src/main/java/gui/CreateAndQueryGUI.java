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
import exceptions.EventFinished;
import exceptions.PronosticAlreadyExist;
import exceptions.QuestionAlreadyExist;
import javax.swing.table.DefaultTableModel;

public class CreateAndQueryGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private JComboBox<Event> jComboBoxEvents = new JComboBox<Event>();
	DefaultComboBoxModel<Event> modelEvents = new DefaultComboBoxModel<Event>();

	//DefaultComboBoxModel<Question> modelQuestion = new DefaultComboBoxModel<Question>();
	private JLabel jLabelListOfEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ListEvents"));
	private JLabel jLabelQuery = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Query"));
	private JLabel jLabelMinBet = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MinimumBetPrice"));
	private JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));

	private JTextField jTextFieldQuery = new JTextField();
	private JTextField jTextFieldPrice = new JTextField();
	private JCalendar jCalendar = new JCalendar();
	private Calendar calendarAct = null;
	private Calendar calendarAnt = null;

	private JButton jButtonCreate = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
	private JButton jButtonLogout = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Logout"));
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();
	private JLabel  lblNewLabel= new JLabel();

	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();
	private final JScrollPane scrollPaneQueries = new JScrollPane();
	
	private JTable tableQueries = new JTable();
	private DefaultTableModel tableModelQueries;
	
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	private String[] columnNamesPronostico = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("PronosticoN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Pronostico"),
            ResourceBundle.getBundle("Etiquetas").getString("Cuota"),
           
	};
	private JTextField textFieldDescripcionEvento;
	private final JLabel jLabelMsg2 = new JLabel();
	private final JScrollPane scrollPanePronostico = new JScrollPane();
	private final JTable tablePronosticos = new JTable();
	private DefaultTableModel tableModelPronostico;
	private final JLabel jLabelPronostico = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateAndQueryGUI.jLabelPronostico.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JTextField textFieldPronostico = new JTextField();
	private final JButton jButtonPronostico = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateAndQueryGUI.jButtonPronostico.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private JTextField textFieldCuota;
	private final JButton jButtonListaEventosFinalizados = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateAndQueryGUI.btnNewButton_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JButton jButtonCerrarEvento = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateAndQueryGUI.btnNewButton_2.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateAndQueryGUI.lblNewLabel_2.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel lblNewLabel_3 = new JLabel(); 
	private final JLabel lblNewLabel_4 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateAndQueryGUI.lblNewLabel_4.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel lblNewLabel_3_1 = new JLabel();
	private final JButton jButtonCerrarConsulta = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateAndQueryGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateAndQueryGUI.lblNewLabel_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
	
	public CreateAndQueryGUI(Vector<domain.Event> v) {
		try {
			jbInit(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit(Vector<domain.Event> v) throws Exception {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(915, 450));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
		jComboBoxEvents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				jButtonPronostico.setEnabled(false);
					domain.Event ev=(domain.Event)jComboBoxEvents.getSelectedItem(); // obtain ev object
				
					if(ev!=null) {
						if(ev.isClosed()) {
							lblNewLabel_3_1.setText("Evento  cerrado");
						}else {
							lblNewLabel_3_1.setText("Evento sin cerrado");
						}
						Vector<Question> queries=ev.getQuestions();
						
						
						tableModelQueries.setDataVector(null, columnNamesQueries);

						//if (queries.isEmpty())
							//jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
						//else 
							//jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+ev.getDescription());

						for (domain.Question q:queries){
							Vector<Object> row = new Vector<Object>();

							row.add(q.getQuestionNumber());
							row.add(q.getQuestion());
							tableModelQueries.addRow(row);	
						}
						tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
						
						
					}
					
			}
				
			
		});
		

		jComboBoxEvents.setModel(modelEvents);
		jComboBoxEvents.setBounds(new Rectangle(299, 50, 250, 20));
		jLabelListOfEvents.setBounds(new Rectangle(303, 18, 277, 20));
		jLabelQuery.setBounds(new Rectangle(40, 318, 91, 20));
		jTextFieldQuery.setBounds(new Rectangle(120, 319, 425, 18));
		jLabelMinBet.setBounds(new Rectangle(40, 348, 91, 20));
		jTextFieldPrice.setBounds(new Rectangle(130, 348, 84, 20));

		jCalendar.setBounds(new Rectangle(40, 50, 225, 150));

		jButtonCreate.setBounds(new Rectangle(419, 347, 130, 30));
		jButtonCreate.setEnabled(false);

		jButtonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});

		jLabelMsg.setBounds(new Rectangle(130, 256, 250, 20));
		jLabelMsg.setForeground(Color.red);
		// jLabelMsg.setSize(new Dimension(305, 20));

		jLabelError.setBounds(new Rectangle(224, 347, 191, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);
		this.getContentPane().add(jButtonCreate, null);
		this.getContentPane().add(jTextFieldQuery, null);
		this.getContentPane().add(jLabelQuery, null);
		this.getContentPane().add(jTextFieldPrice, null);
		scrollPaneQueries.setBounds(new Rectangle(138, 274, 406, 116));
		scrollPaneQueries.setBounds(600, 50, 250, 97);
		
		getContentPane().add(scrollPaneQueries);

		this.getContentPane().add(jLabelMinBet, null);
		this.getContentPane().add(jLabelListOfEvents, null);
		this.getContentPane().add(jComboBoxEvents, null);

		this.getContentPane().add(jCalendar, null);
		
		
		BLFacade facade = MainGUI.getBusinessLogic();
		datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar.getDate());
		paintDaysWithEvents(jCalendar,datesWithEventsCurrentMonth);
		
		

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelEventDate.setBounds(40, 16, 140, 25);
		getContentPane().add(jLabelEventDate);

		
		// Code for JCalendar
		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
				
				jButtonPronostico.setEnabled(false);
				jLabelError.setText("");
				jLabelMsg.setText("");
				jLabelMsg2.setText("");
				
//				this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
//					public void propertyChange(PropertyChangeEvent propertychangeevent) {
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					System.out.println("calendarAnt: "+calendarAnt.getTime());
					System.out.println("calendarAct: "+calendarAct.getTime());
					
					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);
					if (monthAct!=monthAnt) {
						if (monthAct==monthAnt+2) { 
							// Si en JCalendar estÃ¡ 30 de enero y se avanza al mes siguiente, devolverÃ­a 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este cÃ³digo se dejarÃ¡ como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt+1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}
						
						jCalendar.setCalendar(calendarAct);
						
						BLFacade facade = MainGUI.getBusinessLogic();

						datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar.getDate());
					}

					paintDaysWithEvents(jCalendar,datesWithEventsCurrentMonth);

					actualizarTabla();
					
					
					////Actualizar tabla de pron�sticos//
					int i=tableQueries.getSelectedRow();
					Event ev= (Event) jComboBoxEvents.getSelectedItem();
					if(ev!=null && i!=-1) {
						Question q = ev.getQuest(i);
					//	domain.Question ev=(domain.Question)tableModelPronostico.getValueAt(i,2); // obtain ev object
						
						BLFacade facade = MainGUI.getBusinessLogic();
		                try {
		                	List<Pronostico> pronosticos=facade.findPronosticos(q);
		                
						
	
		                	tableModelPronostico.setDataVector(null, columnNamesPronostico);
	
		   
		                	for (domain.Pronostico p:pronosticos){
		                		Vector<Object> row = new Vector<Object>();
	
		                		row.add(p.getPronosNumber());
		                		row.add(p.getPronostico());
		                		tableModelPronostico.addRow(row);	
		                	}
		                	tablePronosticos.getColumnModel().getColumn(0).setPreferredWidth(25);
		                	tablePronosticos.getColumnModel().getColumn(1).setPreferredWidth(268);
		                }catch(PronosticAlreadyExist e1) {
		                	lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorPronosAlreadyEx"));
		                }
					/////////////////////////////////////
					
					} else { // if ev =null
						
						
							//jButtonPronostico.setEnabled(false);
							int n=tableModelPronostico.getRowCount();
							for(int i1=0;i1<n;i1++) {
								tableModelPronostico.removeRow(0);
							}
								
						
							//jButtonPronostico.setEnabled(true);
		                    ////////////////////////////////////////////// 
						
						
					}
				}
			}
		});
		
		
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
		
		JButton jButtonEvent = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateAndQueryGUI.jButtonEvent.text")); //$NON-NLS-1$ //$NON-NLS-2$
		jButtonEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Date firstDay = UtilDate.trim(calendarAct.getTime());
					
					jLabelError.setText("");
					jLabelMsg.setText("");
					jLabelMsg2.setText("");

					// Displays an exception if the query field is empty
					String inputDescription=textFieldDescripcionEvento.getText();

					if (inputDescription.length() > 0 && firstDay!=null) {
                            boolean NoErrorDiaPasado=true;
                            boolean NoErrorYaCreado=true;
							// Obtain the business logic from a StartWindow class (local or remote)
							BLFacade facade = MainGUI.getBusinessLogic();
							
							try {
								Event event1=facade.createEvent(inputDescription,firstDay);
								if(event1==null)NoErrorYaCreado=false;
							}catch(Exception e1) {
								jLabelMsg2.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
								NoErrorDiaPasado=false;
							}
							
                            if(NoErrorDiaPasado) {
                            	if(NoErrorYaCreado) {
                           		jLabelMsg2.setText(ResourceBundle.getBundle("Etiquetas").getString("EventCreated"));     
        							jCalendar.setCalendar(calendarAct);
        							facade = MainGUI.getBusinessLogic();
        							datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar.getDate());
        							paintDaysWithEvents(jCalendar,datesWithEventsCurrentMonth);
                            		actualizarTabla();	
                            	}else jLabelMsg2.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventAlreadyExisted"));
                            }
							
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
	
				
			}
		});
		jButtonEvent.setBounds(new Rectangle(399, 275, 130, 30));
		jButtonEvent.setBounds(419, 256, 130, 30);
		getContentPane().add(jButtonEvent);
		
		JLabel jLabelEventDescription = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateAndQueryGUI.jLabelEventDescription.text")); //$NON-NLS-1$ //$NON-NLS-2$
		jLabelEventDescription.setBounds(new Rectangle(25, 211, 75, 20));
		jLabelEventDescription.setBounds(40, 225, 91, 20);
		getContentPane().add(jLabelEventDescription);
		
		textFieldDescripcionEvento = new JTextField();
		textFieldDescripcionEvento.setBounds(new Rectangle(100, 211, 429, 20));
		textFieldDescripcionEvento.setBounds(120, 226, 429, 20);
		getContentPane().add(textFieldDescripcionEvento);
		jLabelMsg2.setForeground(Color.RED);
		jLabelMsg2.setBounds(new Rectangle(275, 191, 305, 20));
		jLabelMsg2.setBounds(299, 196, 225, 20);
		
		getContentPane().add(jLabelMsg2);
		
		scrollPanePronostico.setBounds(new Rectangle(138, 274, 406, 116));
		scrollPanePronostico.setBounds(600, 158, 250, 111);
		jButtonPronostico.setEnabled(false);
		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableQueries.getSelectedRow();
				Event ev= (Event) jComboBoxEvents.getSelectedItem();
				Question q = ev.getQuest(i);
				boolean isclosedQue=q.isIsclosed();
				LocalDateTime time=LocalDateTime.now();
				Date date= UtilDate.newDate(time.getYear(),time.getMonthValue()-1,time.getDayOfMonth());
				BLFacade facade = MainGUI.getBusinessLogic();
				boolean isCerrado= facade.isEventoCerrar(date, q.getEvent());
				if(isCerrado & !isclosedQue) {
					jButtonCerrarConsulta.setEnabled(true);
				}else {
					jButtonPronostico.setEnabled(true);
				}
				if(isclosedQue) {
					lblNewLabel_3.setText("Consulta Cerrada");
				}else {lblNewLabel_3.setText("Consulta sin cerrar");}
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
               
                }catch(PronosticAlreadyExist e1) {
                	lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorPronosAlreadyEx"));
                }
			}
		});
		
		lblNewLabel.setBounds(new Rectangle(738, 309, 112, 20));
		lblNewLabel.setForeground(Color.red);
		
		getContentPane().add(lblNewLabel); 
		
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
		jLabelPronostico.setBounds(new Rectangle(63, 210, 75, 20));
		jLabelPronostico.setBounds(600, 279, 75, 20);
		
		getContentPane().add(jLabelPronostico);
		textFieldPronostico.setBounds(new Rectangle(138, 240, 60, 20));
		textFieldPronostico.setBounds(670, 279, 180, 20);
		this.getContentPane().add(textFieldPronostico, null);
		
		getContentPane().add(textFieldPronostico);
		jButtonPronostico.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				
				try {
					jLabelError.setText("");
					jLabelMsg.setText("");
					jLabelMsg2.setText("");
					//textFieldCuota.setText(""); 

					// Displays an exception if the query field is empty
					String pr=textFieldPronostico.getText();
					Event ev= (Event) jComboBoxEvents.getSelectedItem();
					int i= tableQueries.getSelectedRow();
					double cuota= Double.parseDouble(textFieldCuota.getText());
					
					if (pr.length() > 0 & i>=0) {

							// Obtain the business logic from a StartWindow class (local or remote)
							BLFacade facade = MainGUI.getBusinessLogic();
                            try {
                            	Question q= facade.createPronostic(pr,ev,i,cuota);
                            }catch(PronosticAlreadyExist e1) {
                            	lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorPronosAlreadyEx"));
                            	
                            }
							
      
                            lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("PronosticCreated"));
		
							actualizarTabla();
							
							tableQueries.setRowSelectionInterval(i, i);
							
							
							
							
							
							//////////////////////////////////////////////////
														
							ev= (Event) jComboBoxEvents.getSelectedItem();
							if(ev!=null) {
							Question q = ev.getQuest(i);
							//	domain.Question ev=(domain.Question)tableModelPronostico.getValueAt(i,2); // obtain ev object
							
							
							facade = MainGUI.getBusinessLogic();
							try {
								List<Pronostico> pronosticos=facade.findPronosticos(q);
								
								
								
								tableModelPronostico.setDataVector(null, columnNamesPronostico);
								
								
								for (domain.Pronostico p:pronosticos){
									Vector<Object> row = new Vector<Object>();
								
									row.add(p.getPronosNumber());
									row.add(p.getPronostico());
									row.add(p.getCuota());
									tableModelPronostico.addRow(row);	
								}
								tablePronosticos.getColumnModel().getColumn(0).setPreferredWidth(25);
								tablePronosticos.getColumnModel().getColumn(1).setPreferredWidth(268);
							}catch(PronosticAlreadyExist e1) {
								lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorPronosAlreadyEx"));
							}
							
							
							}
			
					}
					else lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorPronosAlreadyEx"));
				} catch (Exception e1) {
					e1.printStackTrace();
					lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorPronosAlreadyEx"));
				}

		
			}
		});
	
		jButtonPronostico.setBounds(new Rectangle(399, 275, 130, 30));
		jButtonPronostico.setBounds(600, 347, 160, 30);
		this.getContentPane().add(jButtonPronostico, null);
		
		getContentPane().add(jButtonPronostico);
		
		
		jButtonLogout.setBounds(780, 11, 111, 23);
		jButtonLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});
		getContentPane().add(jButtonLogout);
		
		JLabel jLabelCuota = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
		jLabelCuota.setBounds(602, 363, 238, -22);
		getContentPane().add(jLabelCuota);
		
		textFieldCuota = new JTextField();
		textFieldCuota.setBounds(670, 309, 58, 20);
		getContentPane().add(textFieldCuota);
		textFieldCuota.setColumns(10);
		
		
		lblNewLabel_1.setBounds(604, 309, 45, 18);
		getContentPane().add(lblNewLabel_1);
		

		jButtonCerrarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCerrarEvento.setEnabled(true);
				jButtonCerrarConsulta.setEnabled(false);
				try {
					Event ev= (Event) jComboBoxEvents.getSelectedItem();
					if(ev!=null) {
						int i= tableQueries.getSelectedRow();	
						Question q = facade.getQuestion(ev,i);
						if(!q.isIsclosed()) {
							int ind= tablePronosticos.getSelectedRow();
							Pronostico pro= q.getPronosticos().elementAt(ind);
							facade.cerrarApuesta(pro);
							lblNewLabel_3.setText("Consulta Finalizado");
						}else {
							lblNewLabel_3.setText("La consulta ya esta finalizada");
						}
					
					}
				}catch(Exception e1) {
					e1.printStackTrace();
					lblNewLabel_3.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorPronosNoSelect"));
				}
			}
		});
		
		jButtonCerrarConsulta.setBounds(299, 145, 250, 20);
		getContentPane().add(jButtonCerrarConsulta);
		jButtonCerrarConsulta.setEnabled(false);
		
		JLabel closebetLabelError = new JLabel(); 
		closebetLabelError.setBounds(600, 387, 250, 16);
		getContentPane().add(closebetLabelError);
		jButtonListaEventosFinalizados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						jButtonCreate.setEnabled(false);
						LocalDateTime time=LocalDateTime.now();
						Date date= UtilDate.newDate(time.getYear(),time.getMonthValue()-1,time.getDayOfMonth());
						Vector<Event> eventos= facade.getEventosAc(date);
						modelEvents.removeAllElements();
						for(Event ev: eventos) {
							if(!ev.isClosed()) {
								modelEvents.addElement(ev);
							}
			
						}
						if(modelEvents.getSize()!=0) {
							jButtonCerrarConsulta.setEnabled(true);
						}
			}
		});
		jButtonListaEventosFinalizados.setBounds(298, 114, 251, 21);
		
		jButtonCerrarEvento.setEnabled(false);
		getContentPane().add(jButtonListaEventosFinalizados);
		jButtonCerrarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCerrarEvento.setEnabled(false);
				Event ev= (Event) jComboBoxEvents.getSelectedItem();
				boolean comp=true;
				for(Question q: facade.getQuestionList(ev)) {
					if(!q.isIsclosed()) comp=false;
				}
				if(comp) {
					facade.cerrarEvento(ev);
					lblNewLabel_3_1.setText("Evento cerrado");
					modelEvents.removeElement(jComboBoxEvents.getSelectedItem());
				}else lblNewLabel_3_1.setText("Hay consulta sin finalizar");
			}
		});
		jButtonCerrarEvento.setBounds(299, 175, 250, 21);
		
		getContentPane().add(jButtonCerrarEvento);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_2.setBounds(303, 91, 112, 13);
		
		getContentPane().add(lblNewLabel_2);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_3.setBounds(404, 91, 120, 13);
		
		getContentPane().add(lblNewLabel_3);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_4.setBounds(303, 80, 91, 13);
		
		getContentPane().add(lblNewLabel_4);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_3_1.setBounds(404, 80, 145, 13);
		
		getContentPane().add(lblNewLabel_3_1);
		

		

		//////

		
		
		
		
		
        
	}

	
public static void paintDaysWithEvents(JCalendar jCalendar,Vector<Date> datesWithEventsCurrentMonth) {
		// For each day with events in current month, the background color for that day is changed.

		
		Calendar calendar = jCalendar.getCalendar();
		
		int month = calendar.get(Calendar.MONTH);
		int today=calendar.get(Calendar.DAY_OF_MONTH);
		int year=calendar.get(Calendar.YEAR);
		
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int offset = calendar.get(Calendar.DAY_OF_WEEK);

		if (Locale.getDefault().equals(new Locale("es")))
			offset += 5;
		else
			offset += 5;
		
		
	 	for (Date d:datesWithEventsCurrentMonth){

	 		calendar.setTime(d);
	 		System.out.println(d);
	 		

			
			// Obtain the component of the day in the panel of the DayChooser of the
			// JCalendar.
			// The component is located after the decorator buttons of "Sun", "Mon",... or
			// "Lun", "Mar"...,
			// the empty days before day 1 of month, and all the days previous to each day.
			// That number of components is calculated with "offset" and is different in
			// English and Spanish
//			    		  Component o=(Component) jCalendar.getDayChooser().getDayPanel().getComponent(i+offset);; 
			Component o = (Component) jCalendar.getDayChooser().getDayPanel()
					.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
			o.setBackground(Color.CYAN);
	 	}
	 	
 			calendar.set(Calendar.DAY_OF_MONTH, today);
	 		calendar.set(Calendar.MONTH, month);
	 		calendar.set(Calendar.YEAR, year);

	 	
	}
	
	 
	private void jButtonCreate_actionPerformed(ActionEvent e) {
		domain.Event event = ((domain.Event) jComboBoxEvents.getSelectedItem());

		try {
			jLabelError.setText("");
			jLabelMsg.setText("");
			jLabelMsg2.setText("");

			// Displays an exception if the query field is empty
			String inputQuery = jTextFieldQuery.getText();

			if (inputQuery.length() > 0) {

				// It could be to trigger an exception if the introduced string is not a number
				float inputPrice = Float.parseFloat(jTextFieldPrice.getText());

				if (inputPrice <= 0)
					jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
				else {

					// Obtain the business logic from a StartWindow class (local or remote)
					BLFacade facade = MainGUI.getBusinessLogic();

					facade.createQuestion(event, inputQuery, inputPrice);

					jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryCreated"));
					
				
				
					actualizarTabla();
					jComboBoxEvents.setSelectedItem(event);
					
					
				}
			} else
				jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorQuery"));
		} catch (EventFinished e1) {
			jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished") + ": "
					+ event.getDescription());
		} catch (QuestionAlreadyExist e1) {
			jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
		} catch (java.lang.NumberFormatException e1) {
			jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
		} catch (Exception e1) {

			e1.printStackTrace();

		}
	}

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		MainGUI a =new MainGUI();
		a.setVisible(true);;
	}
	

	
	
	
	
	
	
	
	
	private void actualizarTabla(){
		
		DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());
		
		Date firstDay = UtilDate.trim(calendarAct.getTime());

		try {
			BLFacade facade = MainGUI.getBusinessLogic();

			Vector<domain.Event> events = facade.getEvents(firstDay);

			if (events.isEmpty())
				jLabelListOfEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")
						+ ": " + dateformat1.format(calendarAct.getTime()));
			else
				jLabelListOfEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events") + ": "
					+ dateformat1.format(calendarAct.getTime()));
				jComboBoxEvents.removeAllItems();
				System.out.println("Events " + events);

				
				for (domain.Event ev : events)
					modelEvents.addElement(ev);
					jComboBoxEvents.repaint();
				
				if (events.size() == 0) {
					jButtonCreate.setEnabled(false);
					int n=tableModelQueries.getRowCount();
					for(int i=0;i<n;i++) {
						tableModelQueries.removeRow(0);
					}
						
				}else {
					jButtonCreate.setEnabled(true);
                    ////////////////////////////////////////////// 
				}
					

				} catch (Exception e1) {

					jLabelError.setText(e1.getMessage());
				}
		
		
	}
}