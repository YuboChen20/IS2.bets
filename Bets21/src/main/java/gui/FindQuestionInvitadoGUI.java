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


public class FindQuestionInvitadoGUI extends JFrame {
	private Usuario user;
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 

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
	private final JButton btnLogin = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Login")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SignUp")); //$NON-NLS-1$ //$NON-NLS-2$

	public FindQuestionInvitadoGUI()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
			try {
				jbInit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	
	private void jbInit() throws Exception
	{
		this.setSize(new Dimension(800, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		getContentPane().setLayout(null);
		jLabelEventDate.setBounds(45, 34, 140, 25);

		this.getContentPane().add(jLabelEventDate);
		jLabelQueries.setBounds(50, 243, 249, 14);
		this.getContentPane().add(jLabelQueries);
		jLabelEvents.setBounds(365, 38, 343, 16);
		this.getContentPane().add(jLabelEvents);

		BLFacade facade = MainGUI.getBusinessLogic();
		datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
		CreateAndQueryGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
		jCalendar1.setBounds(40, 65, 273, 167);

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLogin_actionPerformed(e);
			}
		});
			
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSignUp_actionPerformed(e);
			}
		});	
		
		
		
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

/*
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLogin_actionPerformed(e);
			}
		});
		private void btnLogin_actionPerformed(ActionEvent e) {
			
		}
	*/	
		
		
		this.getContentPane().add(jCalendar1);

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
		scrollPaneEvents.setBounds(355, 65, 379, 167);

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
		scrollPaneQueries.setBounds(40, 268, 273, 135);


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

		this.getContentPane().add(scrollPaneEvents);
		this.getContentPane().add(scrollPaneQueries);
		scrollPanePronostico.setBounds(355, 257, 379, 146);
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
		btnLogin.setBounds(665, 0, 123, 23);
		
		getContentPane().add(btnLogin);
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setBounds(556, 0, 107, 23);
		
		getContentPane().add(btnNewButton);
		
		JLabel lblParaPoderApostar = new JLabel();
		lblParaPoderApostar.setText(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsInvitadoGUI.lblParaPoderApostar.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblParaPoderApostar.setFont(new Font("Source Serif Pro Black", Font.BOLD | Font.ITALIC, 14));
		lblParaPoderApostar.setForeground(Color.RED);
		lblParaPoderApostar.setBounds(new Rectangle(208, 239, 305, 20));
		lblParaPoderApostar.setBounds(50, 421, 711, 20);
		getContentPane().add(lblParaPoderApostar);
		
		
		
		
		
		
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
	private void btnLogin_actionPerformed(ActionEvent e) {
		JFrame a = new Login();
		a.setVisible(true);
		this.setVisible(false);
	}
	private void btnSignUp_actionPerformed(ActionEvent e) {
		JFrame a = new SignUp();
		a.setVisible(true);
		this.setVisible(false);
	}
}

