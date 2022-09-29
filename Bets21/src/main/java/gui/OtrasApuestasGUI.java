package gui;

import businessLogic.BLFacade;
import com.toedter.calendar.JCalendar;

import domain.Event;
import domain.Pronostico;
import domain.Question;
import exceptions.PronosticAlreadyExist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;


public class OtrasApuestasGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private JScrollPane scrollPaneQueries = new JScrollPane();
	private final JScrollPane scrollPanePronostico = new JScrollPane();
	
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();
	private JTable tableQueries = new JTable();
	private final JTable tablePronosticos = new JTable();

	private DefaultTableModel tableModelQueries;
	private DefaultTableModel tableModelPronostico;
	
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

	public OtrasApuestasGUI(Event event1, Date date1, int i1)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
			try {
				jbInit(event1,date1,i1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}

	
	private void jbInit(Event event1, Date date1, int i1) throws Exception
	{
		this.setSize(new Dimension(800, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		
		getContentPane().setLayout(null);
		jLabelQueries.setBounds(20, 68, 249, 14);
		this.getContentPane().add(jLabelQueries);

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
		new DefaultTableModel(null, columnNamesEvents) {
			boolean[] columnEditables = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
		};
		scrollPaneQueries.setBounds(20, 93, 318, 146);


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
		this.getContentPane().add(scrollPaneQueries);
		scrollPanePronostico.setBounds(382, 93, 379, 146);
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
		
		JButton btnAtras = new JButton("Atrás");
		
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FQuestionInvitado2 a = new FQuestionInvitado2(date1,i1);
				a.setVisible(true);
				setVisible(false);
			}
		});
		btnAtras.setBounds(672, 319, 89, 23);
		getContentPane().add(btnAtras);
		
		
		JLabel lblEvento = new JLabel(event1.getDescription()); 
		lblEvento.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblEvento.setBounds(20, 11, 497, 46);
		getContentPane().add(lblEvento);
		

		
		try {
        	List<Question> cuestiones=event1.getQuestions();
        
        	tableModelQueries.setDataVector(null, columnNamesQueries);

        	for (domain.Question q:cuestiones){
        		Vector<Object> row = new Vector<Object>();

        		row.add(q.getQuestionNumber());
        		row.add(q.getQuestion());
        		
        		tableModelQueries.addRow(row);	
        	}
        	tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
        	tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
        	tableModelQueries.removeRow(0);
        	
       
        }catch(Exception e1) {
        	//lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorPronosAlreadyEx"));
        }
		

		
		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
				int j=tableQueries.getSelectedRow()+1;
				
				System.out.println(j);
				
				Question q = event1.getQuest(j);
				
				System.out.println(q.toString());
				
				BLFacade facade = MainGUI.getBusinessLogic();
                try {
                	List<Pronostico> pronosticos=facade.findPronosticos(q);
                	if(pronosticos.isEmpty())System.out.println("pronos is empty");
                	tableModelPronostico.setDataVector(null, columnNamesPronostico);
                	System.out.println("size of pronosticos"+pronosticos.size());
                	for (domain.Pronostico p:pronosticos){
                		System.out.println(p.toString());
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
                	tablePronosticos.getColumnModel().getColumn(2).setWidth(25);
            		//tablePronosticos.getColumnModel().getColumn(3).setPreferredWidth(25);
                	
                }catch(PronosticAlreadyExist e1) {
                	//lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorPronosAlreadyEx"));
                }
			}
		});
		

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

