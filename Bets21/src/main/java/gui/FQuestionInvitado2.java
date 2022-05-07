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

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Label;


public class FQuestionInvitado2 extends JFrame {
	private Usuario user;
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	private final JScrollPane scrollPanePronostico = new JScrollPane();
	
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();
	private final JTable tablePronosticos = new JTable();


	private DefaultTableModel tableModelPronostico;
	
	
	private final JLabel jLabelApuesta = new JLabel(); 
	
	private String[] columnNamesPronostico = new String[] {
			"",
			"", 
            "1",
            "X",
            "2",
            "",
            ""
	};
	

	

	private final JButton btnLogin = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Login")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SignUp")); //$NON-NLS-1$ //$NON-NLS-2$

	public FQuestionInvitado2()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
			try {
				jbInit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public FQuestionInvitado2(Date date1, int i1)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
			try {
				jbInit();
				Calendar calendar=Calendar.getInstance();
				calendar.setTime(date1);
				jCalendar1.setCalendar(calendar);
				

				BLFacade facade = MainGUI.getBusinessLogic();

				datesWithEventsCurrentMonth=facade.getEventsMonth(date1);
			

				CreateAndQueryGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
				
			
				
				
				
				tablePronosticos.setRowSelectionInterval(i1, i1);
				
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
													
					
                    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					tableModelPronostico.setDataVector(null, columnNamesPronostico);
					tableModelPronostico.setColumnCount(7);
					
					Vector<domain.Event> events=facade.getEvents(firstDay);
					if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarAct.getTime()));
					else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarAct.getTime()));
					for (domain.Event ev:events){
						Vector<Object> row = new Vector<Object>();

						System.out.println("Events "+ev);
						
						row.add(ev.getEventNumber());
						
						
						
						
						
						String[] equipos =ev.getDescription().split("-");
						row.add("<html>"+equipos[0]+"<br>"+equipos[1]+"</html>"); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
						row.add("    "+"0.22");
						row.add("    "+"1.48");
						row.add("    "+ "1.88");
						
						JButton btn1 =new JButton("<html>Otras<br>apuestas</html>");
					
						row.add(btn1);
						//row.add("<html>Otras<br>apuestas</html>");
						row.add(ev);
						
						tableModelPronostico.addRow(row);		
					}
					
					//tablePronosticos.getColumnModel().removeColumn(tablePronosticos.getColumnModel().getColumn(2)); // not shown in JTable
					
					
					tablePronosticos.setRowHeight(50);
					
					//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					
					
					tablePronosticos.getColumnModel().getColumn(0).setPreferredWidth(25);
					tablePronosticos.getColumnModel().getColumn(1).setPreferredWidth(100);
					tablePronosticos.getColumnModel().getColumn(2).setPreferredWidth(50);
					tablePronosticos.getColumnModel().getColumn(3).setPreferredWidth(50);
					tablePronosticos.getColumnModel().getColumn(4).setPreferredWidth(50);
					tablePronosticos.getColumnModel().getColumn(5).setPreferredWidth(80);
					
					
					tablePronosticos.getColumnModel().removeColumn(tablePronosticos.getColumnModel().getColumn(6)); // not shown in JTable
					
					
				} 
				
			} 
		});


		
		
		this.getContentPane().add(jCalendar1);
		
		
		tablePronosticos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tablePronosticos.getSelectedRow();
				int j= tablePronosticos.getSelectedColumn();
				
				if(j==5) {
					domain.Event ev=(domain.Event)tableModelPronostico.getValueAt(i,6); // obtain ev object 
					
					Date date=jCalendar1.getDate();
					OtrasApuestasGUI a=new OtrasApuestasGUI(ev,date,i);
					a.setVisible(true);
					setVisible(false);
					
				}
				
			}
		});
		
		scrollPanePronostico.setBounds(351, 73, 379, 270);
		scrollPanePronostico.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		getContentPane().add(scrollPanePronostico);
		tableModelPronostico = new DefaultTableModel(null, columnNamesPronostico){
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
				
			};
		tablePronosticos.setModel(tableModelPronostico);

		
		tablePronosticos.getColumnModel().getColumn(0).setPreferredWidth(25);
		tablePronosticos.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablePronosticos.getColumnModel().getColumn(2).setPreferredWidth(50);
		tablePronosticos.getColumnModel().getColumn(3).setPreferredWidth(50);
		tablePronosticos.getColumnModel().getColumn(4).setPreferredWidth(50);
		tablePronosticos.getColumnModel().getColumn(5).setPreferredWidth(80);
		tablePronosticos.getColumnModel().removeColumn(tablePronosticos.getColumnModel().getColumn(6)); // not shown in JTable
		scrollPanePronostico.setViewportView(tablePronosticos);
		btnLogin.setBounds(665, 0, 123, 23);
		
		getContentPane().add(btnLogin);
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setBounds(556, 0, 107, 23);
		
		getContentPane().add(btnNewButton);
		
		tablePronosticos.setDefaultRenderer(Object.class, new Render());
		
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