package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;

import domain.Bet;
import domain.Comentarios;
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


public class OtrasApuestas2GUI extends JFrame {
	private Usuario user;
	private static final long serialVersionUID = 1L;
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	private JScrollPane scrollPaneQueries = new JScrollPane();
	private final JScrollPane scrollPanePronostico = new JScrollPane();
	
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();
	private JTable tableQueries = new JTable();
	private final JTable tablePronosticos = new JTable();

	private DefaultTableModel tableModelEvents;
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
	private final JButton btnLogout = new JButton("Cerrar Sesión"); //$NON-NLS-1$ //$NON-NLS-2$
	private JTextField textApuesta;
	private final JButton btnApostar = new JButton("Apostar"); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel lblMinima = new JLabel("Apuesta mínima"); //$NON-NLS-1$ //$NON-NLS-2$
	
	
	//////////////////////////////
	private JTextArea textArea;
	private JScrollPane scrollpane1;
	private JTextField textField;
    private int pos;
    private final JLabel lblErrorApostar = new JLabel(""); //$NON-NLS-1$ //$NON-NLS-2$
    ///////////////////////////////

	public OtrasApuestas2GUI(Event event1, Date date1, int i1, Usuario u)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
			try {
				jbInit(event1,date1,i1,u);
				this.user=u;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	
	private void jbInit(Event event1, Date date1, int i1, Usuario user) throws Exception
	{
		this.setSize(new Dimension(800, 534));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		getContentPane().setLayout(null);
		jLabelQueries.setBounds(20, 68, 249, 14);
		this.getContentPane().add(jLabelQueries);

		BLFacade facade = MainGUI.getBusinessLogic();
		datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
		CreateAndQueryGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
		jCalendar1.setBounds(40, 65, 273, 167);

		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLogin_actionPerformed(e);
			}
		});
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents) {
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
		btnLogout.setBounds(665, 0, 123, 23);
		
		getContentPane().add(btnLogout);
		
		JButton btnAtras = new JButton("Atrás");
		
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FQuestion2 a = new FQuestion2(date1,i1, user);
				a.setVisible(true);
				setVisible(false);
			}
		});
		btnAtras.setBounds(0, 0, 89, 23);
		getContentPane().add(btnAtras);
		
		
		JLabel lblEvento = new JLabel(event1.getDescription()); 
		lblEvento.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblEvento.setBounds(20, 11, 497, 46);
		getContentPane().add(lblEvento);
		
		
		
		
		JButton btnPrueba = new JButton("Prueba");
		
		btnPrueba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnPrueba.setBounds(500, 100, 89, 23);
		btnPrueba.setVisible(true);
		getContentPane().add(btnPrueba);
		
		JLabel lblApuesta = new JLabel("Apuesta"); //$NON-NLS-1$ //$NON-NLS-2$
		lblApuesta.setBounds(20, 259, 50, 14);
		getContentPane().add(lblApuesta);
		
		textApuesta = new JTextField();
		textApuesta.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		textApuesta.setBounds(90, 255, 134, 23);
		getContentPane().add(textApuesta);
		textApuesta.setColumns(10);
		btnApostar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					lblErrorApostar.setForeground(Color.RED);
					Double pr = Double.parseDouble (textApuesta.getText());
					int numQ= tableQueries.getSelectedRow()+1;
					int numP= tablePronosticos.getSelectedRow();
	
				
					
					Question q = event1.getQuest(numQ);
					Pronostico p =q.getPron(numP);
					if (pr.floatValue()>=q.getBetMinimum()) {

							BLFacade facade = MainGUI.getBusinessLogic();
                            int i= facade.crearApuesta(user,pr,p);
                    
                            
                            if(i==0) {
                            	lblErrorApostar.setForeground(Color.BLACK);
                            	lblErrorApostar.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateApuesta"));
                            	
                            ////Actualizar tabla de pronósticos//
            					
            					if(numQ>=1) {
            						
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
            		                	if(tablePronosticos.getRowCount()>0)tablePronosticos.setRowSelectionInterval(numP, numP);
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
                            	lblErrorApostar.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorBet"));
                            }
                            else {
                            	lblErrorApostar.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorBetDinero"));
                            }
                            
					}
                }catch(Exception e1) {
               
                	lblErrorApostar.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorApuesta"));
        	
                }
				
			}
		});
		btnApostar.setBounds(249, 254, 89, 24);
		
		getContentPane().add(btnApostar);
		lblMinima.setBounds(20, 283, 152, 14);
		
		getContentPane().add(lblMinima);
		
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////////
		
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
        	
        	if(tableQueries.getRowCount()>0) {
        		tableQueries.setRowSelectionInterval(0,0);
        		
        		
        		
        		
				
				Question q = event1.getQuest(1);
				
				lblMinima.setText("Apuesta Mínima: "+q.getBetMinimum());
				
				
                try {
                	List<Pronostico> pronosticos=facade.findPronosticos(q);
                	
                	tableModelPronostico.setDataVector(null, columnNamesPronostico);
                	
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
                	tablePronosticos.setRowSelectionInterval(0, 0);
            		//tablePronosticos.getColumnModel().getColumn(3).setPreferredWidth(25);
                	
                }catch(PronosticAlreadyExist e1) {
                	//lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorPronosAlreadyEx"));
                }
        	}
       
        }catch(Exception e1) {
        	//lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorPronosAlreadyEx"));
        }
		
		
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////////
		
		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
				int j=tableQueries.getSelectedRow()+1;
				
				System.out.println(j);
				
				Question q = event1.getQuest(j);
			//	domain.Question ev=(domain.Question)tableModelPronostico.getValueAt(i,2); // obtain ev object
				
				lblMinima.setText("Apuesta Mínima: "+q.getBetMinimum());
				
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
                	tablePronosticos.setRowSelectionInterval(0, 0);
            		//tablePronosticos.getColumnModel().getColumn(3).setPreferredWidth(25);
                	
                }catch(PronosticAlreadyExist e1) {
                	//lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorPronosAlreadyEx"));
                }
			}
		});
		
		////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		scrollpane1=new JScrollPane(textArea);
		scrollpane1.setBounds(139,321,584,20);
		scrollpane1.setBounds(new Rectangle(45, 325, 500, 100));
		getContentPane().add(scrollpane1);
		pos=0;
		Event eventoactualizado = facade.getEventoactualizado(event1);
		for(Comentarios c : eventoactualizado.getComentarios()) {
			textArea.insert(c.toString(), pos);
			pos=textArea.getCaretPosition();
		}
		textField = new JTextField();
		textField.setText("");
		textField.setBounds(85, 449, 461, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnComentar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Comentar")); //$NON-NLS-1$ //$NON-NLS-2$
		btnComentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Comentarios coment = facade.createComent(textField.getText(), event1, user);
				textArea.insert(coment.toString(), pos);
				pos=textArea.getCaretPosition();
				textField.setText("");
			}
		});
		btnComentar.setBounds(561, 449, 134, 21);
		getContentPane().add(btnComentar);
		
		JLabel lblUserComentarios = new JLabel(user.getUserName() + " :");
		lblUserComentarios.setBounds(30, 453, 45, 13);
		getContentPane().add(lblUserComentarios);
		lblErrorApostar.setBounds(137, 288, 131, 14);
		
		getContentPane().add(lblErrorApostar);
	
	
	}
	private void btnLogin_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		MainGUI a =new MainGUI();
		a.setVisible(true);
	}
}

