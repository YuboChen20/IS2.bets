package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;

import domain.Event;
import domain.Liga;
import domain.Pronostico;
import domain.Question;
import domain.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;


public class FQuestion2 extends JFrame {
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
	
	
	
	
	private String[] columnNamesPronostico = new String[] {
			"",
			"", 
            "1",
            "X",
            "2",
            "",
            ""
	};
	

	

	private final JButton btnLogout = new JButton("Cerrar Sesi�n"); //$NON-NLS-1$ //$NON-NLS-2$
	private JTextField textField;
	
	private JButton btnApostar = new JButton("Apostar");
	private final JLabel lblErrorAlApostar = new JLabel(""); //$NON-NLS-1$ //$NON-NLS-2$
    
	
	private Date firstDay;
	
	private final JTable tableLigas = new JTable();
	private DefaultTableModel tableModelLigas;
	private String[] columnNamesLiga = new String[] {
			"Liga",
			"ObjetoLiga"
	};
	private final JButton btnNoticia = new JButton(ResourceBundle.getBundle("Etiquetas").getString("VerNoticia"));
	
	private JLabel lblNombreLiga = new JLabel("Liga Santander");
	
	/**
	 * @wbp.parser.constructor
	 */
	public FQuestion2(Usuario u)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
			try {
				jbInit();
				this.user=u;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	
	public FQuestion2(Date date1, int i1, Usuario u)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
			try {
				jbInit();
				this.user=u;
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
		this.setSize(new Dimension(932, 430));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		getContentPane().setLayout(null);
		jLabelEventDate.setBounds(33, 34, 140, 25);

		this.getContentPane().add(jLabelEventDate);
		jLabelEvents.setBounds(351, 38, 343, 16);
		this.getContentPane().add(jLabelEvents);

		BLFacade facade = MainGUI.getBusinessLogic();
		datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
		CreateAndQueryGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
		jCalendar1.setBounds(33, 111, 273, 167);

		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MainGUI a =new MainGUI();
				a.setVisible(true);
			}
		});
		
		
		
		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{
				
				lblErrorAlApostar.setText("");

				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
					firstDay=UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));

					 
					
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

					
					tableModelPronostico.setDataVector(null, columnNamesPronostico);
					tableModelPronostico.setColumnCount(7);
					
					Vector<domain.Event> events=facade.getEvents(firstDay);
					if (events.isEmpty() ) {
						jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarAct.getTime()));
						lblNombreLiga.setText("");
					}else {
						jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarAct.getTime()));
						lblNombreLiga.setText("Liga Santander");
					}
					for (domain.Event ev:events){
						
						if(ev.getEquipos().get(0).getLiga().getNombre().equals("Liga Santander")) {
							Vector<Object> row = new Vector<Object>();
	
							System.out.println("Events "+ev);
							
							row.add(ev.getEventNumber());
							
							String[] equipos =ev.getDescription().split("-");
							row.add("<html>"+equipos[0]+"<br>"+equipos[1]+"</html>"); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							Vector <Pronostico> list= ev.getQuest(0).getListPronosticos();
							System.out.println(ev.getQuest(0).getQuestion());
							row.add("    "+list.get(0).getCuota());
							row.add("    "+list.get(1).getCuota());
							row.add("    "+list.get(2).getCuota());
							
							JButton btn1 =new JButton("<html>Otras<br>apuestas</html>");
							row.add(btn1);
							row.add(ev);
							tableModelPronostico.addRow(row);
						
						}
					
					}
										
					tablePronosticos.setRowHeight(50);
					
					tablePronosticos.getColumnModel().getColumn(0).setPreferredWidth(25);
					tablePronosticos.getColumnModel().getColumn(1).setPreferredWidth(100);
					tablePronosticos.getColumnModel().getColumn(2).setPreferredWidth(50);
					tablePronosticos.getColumnModel().getColumn(3).setPreferredWidth(50);
					tablePronosticos.getColumnModel().getColumn(4).setPreferredWidth(50);
					tablePronosticos.getColumnModel().getColumn(5).setPreferredWidth(80);
					
					
					tablePronosticos.getColumnModel().removeColumn(tablePronosticos.getColumnModel().getColumn(6)); // not shown in JTable
					
					btnApostar.setEnabled(false);
					
					
				} 
				
			} 
		});


		
		
		this.getContentPane().add(jCalendar1);
		
		
		tablePronosticos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tablePronosticos.getSelectedRow();
				int j= tablePronosticos.getSelectedColumn();
				
				lblErrorAlApostar.setText("");
				
				if(1<j && j<5) btnApostar.setEnabled(true);
				else btnApostar.setEnabled(false);
				
				if(j==5) {
					domain.Event ev=(domain.Event)tableModelPronostico.getValueAt(i,6); // obtain ev object 
					
					Date date=jCalendar1.getDate();
					OtrasApuestas2GUI a=new OtrasApuestas2GUI(ev,date,i,user);
					a.setVisible(true);
					setVisible(false);
					
				}
				
			}
		});
		
		scrollPanePronostico.setBounds(351, 70, 379, 305);
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
		btnLogout.setBounds(795, 0, 123, 23);
		
		getContentPane().add(btnLogout);
		
		JLabel lblApuesta = new JLabel("Apuesta"); //$NON-NLS-1$ //$NON-NLS-2$
		lblApuesta.setBounds(25, 304, 62, 14);
		getContentPane().add(lblApuesta);
		
		textField = new JTextField();
		textField.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		textField.setBounds(81, 301, 112, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		
		btnApostar.setBounds(203, 300, 89, 23);
		btnApostar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					
					int i=tablePronosticos.getSelectedRow();
					int j=tablePronosticos.getSelectedColumn();
					lblErrorAlApostar.setForeground(Color.RED);
					Double pr = Double.parseDouble (textField.getText());
					
	
					Vector<domain.Event> events=facade.getEvents(calendarAct.getTime());
					
					Event ev=events.get(i);
					
					Question q = ev.getQuest(0);
					Pronostico p =q.getPron(j-2);
					if (pr.floatValue()>=q.getBetMinimum()) {

							BLFacade facade = MainGUI.getBusinessLogic();
                            int k= facade.crearApuesta(user,pr,p);
                    
                            
                            if(k==0) {
                            	lblErrorAlApostar.setForeground(Color.BLACK);
                           
                            	
                            	lblErrorAlApostar.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateApuesta"));

                            } 
                            else if(k==1) {
                            	lblErrorAlApostar.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorBet"));
                            }
                            else {
                            	lblErrorAlApostar.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorBetDinero"));
                            }
                            
					}
                }catch(Exception e1) {
               
                	lblErrorAlApostar.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorApuesta"));
        	
                }
				
			}
		});
		btnApostar.setEnabled(false);
		getContentPane().add(btnApostar);
		
		JButton btnVerPerfil = new JButton("Ver perfil"); //$NON-NLS-1$ //$NON-NLS-2$
		btnVerPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new SeeUserInfoGUI(user);
				a.setVisible(true);	
			}
		});
		
		
		
		btnVerPerfil.setBounds(662, 0, 123, 23);
		getContentPane().add(btnVerPerfil);
		
		JLabel lblApuestaMinima = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("FQuestion2.lblApuestaMinima.text")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-2$
		lblApuestaMinima.setBounds(29, 333, 156, 14);
		getContentPane().add(lblApuestaMinima);
		
		JButton btnVerRanking = new JButton(ResourceBundle.getBundle("Etiquetas").getString("FQuestion2.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnVerRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVerRanking.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RankingGUI a= new RankingGUI();
				a.setVisible(true);
			}
		});
		btnVerRanking.setBounds(526, 0, 129, 23);
		getContentPane().add(btnVerRanking);
		lblErrorAlApostar.setBounds(40, 357, 252, 14);
		
		getContentPane().add(lblErrorAlApostar);
		
		tablePronosticos.setDefaultRenderer(Object.class, new Render());
		
		
		lblNombreLiga.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNombreLiga.setBounds(33, 57, 222, 44);
		getContentPane().add(lblNombreLiga);
		
    	tableLigas.setDefaultRenderer(Object.class, new Render());
    	
    	tableLigas.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
    			DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
    			
    			int j=tableLigas.getSelectedRow();
    			Liga l = (Liga)tableModelLigas.getValueAt(j, 1);
    			lblNombreLiga.setText(l.getNombre());
    			while(tableModelPronostico.getRowCount()>0)tableModelPronostico.removeRow(0);
    			
    			Vector<domain.Event> events=facade.getEvents(firstDay);
				if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarAct.getTime()));
				else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarAct.getTime()));
				for (domain.Event ev:events){
					
					System.out.println(ev.getEquipos().get(0).getLiga().getNombre()+ "  "+ l.getNombre());
					if(ev.getEquipos().get(0).getLiga().getNombre().equals(l.getNombre())) {
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
						row.add(ev);
						tableModelPronostico.addRow(row);
					
					}
				
				}
				
				tableLigas.setRowSelectionInterval(0, 0);
    			
    			
    			
    		}
    	});
    	
    	DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
    	Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date());
		jCalendar1.setCalendar(calendar);
		List<Event> events=facade.getEvents(new Date());
		if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendar.getTime()));
    	
    	
    	JScrollPane scrollPaneLigas = new JScrollPane();
		scrollPaneLigas.setBounds(749, 70, 144, 305);
		getContentPane().add(scrollPaneLigas);
		
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
		btnNoticia.setBounds(0, 0, 129, 23);
		
		getContentPane().add(btnNoticia);
		btnNoticia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNoticia_actionPerformed(e);
			}
		});	
		
		
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
    	int pos=-1;
		List<Liga> ligas1=facade.getAllLigas();
		for(int i=0; i<ligas1.size();i++)
			if(ligas1.get(i).getNombre().equals("Liga Santander")) pos=i;
		if(pos!=-1 || pos<ligas1.size())
			tableLigas.setRowSelectionInterval(pos, pos);
	
    	
	
	}
	private void btnNoticia_actionPerformed(ActionEvent e) {
		Integer num=2;
		JFrame a = new NoticiasGUI(num);
		a.setVisible(true);
	}
}