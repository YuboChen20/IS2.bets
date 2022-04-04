package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;

import domain.Bet;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import domain.Usuario;
import exceptions.PronosticAlreadyExist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;


public class FindQuestionsGUI extends JFrame {
	private Usuario user;
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 
	private JButton jButtonLogout = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Logout"));

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();
	private final JScrollPane scrollPanePronostico = new JScrollPane();
	
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();

	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();
	private final JTable tablePronosticos = new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;
	private DefaultTableModel tableModelPronostico;
	
	private final JLabel jLabelApuesta = new JLabel(); 
	
	private String[] columnNamesPronostico = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("PronosticoN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Pronostico"),
            ResourceBundle.getBundle("Etiquetas").getString("Cuota"),
            ResourceBundle.getBundle("Etiquetas").getString("Usuarios"),
	};
	

	
	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	private JTextField textFieldApostar;

	public FindQuestionsGUI(Usuario user)
	{
		
		try
		{
			this.user= user;
			
			jbInit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	private void jbInit() throws Exception
	{
		
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(800, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(49, 228, 167, 14);
		jLabelEvents.setBounds(295, 19, 109, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);


		jCalendar1.setBounds(new Rectangle(40, 50, 225, 150));

		BLFacade facade = MainGUI.getBusinessLogic();
		datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
		CreateAndQueryGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);

		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{

				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
//					jCalendar1.setCalendar(calendarAct);
					Date firstDay=UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));

					 
					
					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);
					
					if (monthAct!=monthAnt) {
						if (monthAct==monthAnt+2) {
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolvería 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt+1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}						
						
						jCalendar1.setCalendar(calendarAct);

						BLFacade facade = MainGUI.getBusinessLogic();

						datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
					}



					CreateAndQueryGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
													
					

					try {
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

						BLFacade facade=MainGUI.getBusinessLogic();

						Vector<domain.Event> events=facade.getEvents(firstDay);

						if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarAct.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarAct.getTime()));
						for (domain.Event ev:events){
							Vector<Object> row = new Vector<Object>();

							System.out.println("Events "+ev);

							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							tableModelEvents.addRow(row);		
						}
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not shown in JTable
					} catch (Exception e1) {

						jLabelQueries.setText(e1.getMessage());
					}

				}
			} 
		});

		this.getContentPane().add(jCalendar1, null);
		
		scrollPaneEvents.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPaneQueries.setBounds(new Rectangle(40, 253, 258, 121));

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				Vector<Question> queries=ev.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);

				if (queries.isEmpty())
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
				else 
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+ev.getDescription());

				for (domain.Question q:queries){
					Vector<Object> row = new Vector<Object>();

					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					tableModelQueries.addRow(row);	
				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
			}
		});

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents) {
			boolean[] columnEditables = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
		};

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);


		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries) {
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

		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);
		scrollPanePronostico.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
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
		tablePronosticos.getColumnModel().getColumn(2).setPreferredWidth(25);
		tablePronosticos.getColumnModel().getColumn(3).setPreferredWidth(25);
		scrollPanePronostico.setViewportView(tablePronosticos);
		
		jButtonLogout.setBounds(657, 16, 121, 23);
		jButtonLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
			}
			
		});
		getContentPane().add(jButtonLogout);
		
		
		scrollPanePronostico.setBounds(new Rectangle(138, 274, 406, 116));
		scrollPanePronostico.setBounds(395, 228, 336, 146);
		
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFrame a = new SeeUserInfoGUI(user);
				
				
				a.setVisible(true);	
			}
		});
		JLabel jLabelApuesta = new JLabel();
		jLabelApuesta.setForeground(Color.RED);
		jLabelApuesta.setBounds(new Rectangle(208, 239, 305, 20));
		jLabelApuesta.setBounds(395, 418, 363, 20);
		getContentPane().add(jLabelApuesta);
		
		textFieldApostar = new JTextField();
		textFieldApostar.setColumns(10);
		textFieldApostar.setBounds(484, 385, 106, 22);
		getContentPane().add(textFieldApostar);
		
		
		btnNewButton.setBounds(535, 17, 112, 21);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.btnNewButton_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Double pr = Double.parseDouble (textFieldApostar.getText());
					int numQ= tableQueries.getSelectedRow();
					int numP= tablePronosticos.getSelectedRow();
					int numE= tableEvents.getSelectedRow();
				
					domain.Event ev=(domain.Event)tableModelEvents.getValueAt(numE,2);
					Question q = ev.getQuest(numQ);
					Pronostico p =q.getPron(numP);
					if (pr.floatValue()>=q.getBetMinimum()) {

							BLFacade facade = MainGUI.getBusinessLogic();
                            int i= facade.crearApuesta(user,pr,p);
                    
                            
                            if(i==0) {
                            	jLabelApuesta.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateApuesta"));
                            	
                            ////Actualizar tabla de pron�sticos//
            					
            					if(numE>=0 && numQ>=0) {
            						
            		                try {
            		                	
            		                	List<Pronostico> pronosticos=facade.findPronosticos(q);
            		                	tableModelPronostico.setDataVector(null, columnNamesPronostico);

            		                	for (domain.Pronostico p1:pronosticos){
            		                		Vector<Object> row = new Vector<Object>();

            		                		row.add(p1.getPronosNumber());
            		                		row.add(p1.getPronostico());
            		                		row.add(p1.getCuota());
            		                		row.add(p1.getPorcentajeApuesta());
            		                		tableModelPronostico.addRow(row);	
            		                	}
            		                	tablePronosticos.getColumnModel().getColumn(0).setPreferredWidth(25);
            		                	tablePronosticos.getColumnModel().getColumn(1).setPreferredWidth(268);
            		                }catch(PronosticAlreadyExist e1) {
            		                	// lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorPronosAlreadyEx"));
            		                }
  
            					} else { // if ev =null
    
            							int n=tableModelPronostico.getRowCount();
            							for(int i1=0;i1<n;i1++) {
            								tableModelPronostico.removeRow(0);
            							}

            					}

                            } 
                            else if(i==1) {
                            	jLabelApuesta.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorBet"));
                            }
                            else {
                            	jLabelApuesta.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorBetDinero"));
                            }
                            
					}
                }catch(Exception e1) {
               
                	jLabelApuesta.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorBet"));
        	
                }
		
			}
							
						
		});
		btnNewButton_1.setBounds(589, 385, 91, 23);
		getContentPane().add(btnNewButton_1);
		
	
		
		JLabel lblApuesta = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.lblApuesta.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblApuesta.setBounds(new Rectangle(63, 210, 75, 20));
		lblApuesta.setBounds(420, 385, 75, 20);
		getContentPane().add(lblApuesta);
		
		
		
		
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////////
		
		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int i=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2);
				int j=tableQueries.getSelectedRow();
				Question q = ev.getQuest(j);
			//	domain.Question ev=(domain.Question)tableModelPronostico.getValueAt(i,2); // obtain ev object
				
				BLFacade facade = MainGUI.getBusinessLogic();
                try {
                	List<Pronostico> pronosticos=facade.findPronosticos(q);
                
                	tableModelPronostico.setDataVector(null, columnNamesPronostico);

                	for (domain.Pronostico p:pronosticos){
                		Vector<Object> row = new Vector<Object>();

                		row.add(p.getPronosNumber());
                		row.add(p.getPronostico());
                		row.add(p.getCuota());
                		row.add(p.getPorcentajeApuesta());
                		System.out.println(p.getCuota());
                		tableModelPronostico.addRow(row);	
                	}
                	tablePronosticos.getColumnModel().getColumn(0).setPreferredWidth(25);
                	tablePronosticos.getColumnModel().getColumn(1).setPreferredWidth(268);
               
                }catch(PronosticAlreadyExist e1) {
                	//lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorPronosAlreadyEx"));
                }
			}
		});
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		

	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
