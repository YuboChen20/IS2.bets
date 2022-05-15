package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Event;
import domain.Noticia;
import domain.Pronostico;
import domain.Question;
import exceptions.PronosticAlreadyExist;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;

public class NoticiasGUI extends JFrame {

	private JPanel contentPane;
	private JComboBox JComboBoxNoticias = new JComboBox();
	DefaultComboBoxModel<Noticia> modelNoticia = new DefaultComboBoxModel<Noticia>();
	private JComboBox JComboBoxAutores = new JComboBox();
	DefaultComboBoxModel<String> modelAutores = new DefaultComboBoxModel<String>();
	private JTextArea textTexto = new JTextArea();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTextArea textTitulo = new JTextArea();
	private final JTextArea textSubTitulo = new JTextArea();
	private final JCalendar jCalendar = new JCalendar();
	private Calendar calendarAct = null;
	private Calendar calendarAnt = null;
	private final JButton jButtonFiltrarAutor = new JButton("Filtrar por Autor");
	private final JButton jButtonFiltrarMes = new JButton("Filtrar por mes");
	private final JButton jButtonFiltrarDia = new JButton("Filtrar por dia");
	private final JButton jButtonCerrar = new JButton("Cerrar");
	private final JComboBox JComboBoxMedios = new JComboBox();
	DefaultComboBoxModel<String> modelMedios = new DefaultComboBoxModel<String>();
	private final JButton jButtonFiltrarMedios = new JButton("Filtrar por Medio");
	private Vector<Date> datesWithNoticiasCurrentMonth = new Vector<Date>();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NoticiasGUI frame = new NoticiasGUI();
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
	public NoticiasGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 986, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Font fontTitulo = new Font("Verdana", Font.BOLD, 15);
		Font fontSubTitulo = new Font("Verdana", Font.ITALIC, 12);
		Font fontTexto = new Font("Verdana", Font.PLAIN, 10);
		

		JComboBoxNoticias.setBounds(430, 24, 353, 32);
		JComboBoxNoticias.setModel(modelNoticia);
		JComboBoxAutores.setModel(modelAutores);
		contentPane.add(JComboBoxNoticias);
		textTitulo.setBounds(430, 85, 353, 22);
		
		BLFacade facade = MainGUI.getBusinessLogic();

		List<String> autores=facade.getAllNoticiasAuthor();
		for(String au: autores) {
			modelAutores.addElement(au);
		}
		JComboBoxAutores.repaint();
		List<String> medios=facade.getAllNoticiasMedio();
		for(String me: medios) {
			modelMedios.addElement(me);
		}
		JComboBoxAutores.repaint();
		
		
		JComboBoxNoticias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					domain.Noticia no=(domain.Noticia)JComboBoxNoticias.getSelectedItem(); // obtain ev object
					if(no!=null) {
						textTitulo.setText(no.getTitulo());
						textSubTitulo.setText(no.getSubTitulo());
						textTexto.setText(no.getTexto());
					}else {
						System.out.println("No existe una noticia");
					}
					
				}catch(Exception el) {
					el.printStackTrace();
				}
					
					}
			
		});
		
		jButtonFiltrarAutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String aut=(String)JComboBoxAutores.getSelectedItem();
				BLFacade facade = MainGUI.getBusinessLogic();
				List<Noticia> noticias=facade.getNoticiasAuthor(aut);
				modelNoticia.removeAllElements();
				for(Noticia no:noticias) {
					modelNoticia.addElement(no);
				}
				JComboBoxNoticias.repaint();
			}
		});
		
		contentPane.add(textTitulo);
		textTitulo.setEditable(false);
		textTitulo.setFont(fontTitulo);
		textSubTitulo.setBounds(430, 130, 354, 32);
		textSubTitulo.setEditable(false);
		textSubTitulo.setFont(fontSubTitulo);
		
		contentPane.add(textSubTitulo);
		scrollPane.setBounds(430, 195, 353, 148);
		
		contentPane.add(scrollPane);
		scrollPane.setViewportView(textTexto);
		textTexto.setEditable(false);
		textTexto.setFont(fontTexto);
		textTexto.setLineWrap(true);
		textTexto.setWrapStyleWord(true);
		jCalendar.setBounds(54, 24, 225, 150);
		jCalendar.setTodayButtonVisible(false);
		
		contentPane.add(jCalendar);
		jButtonFiltrarAutor.setBounds(54, 364, 225, 32);
		
		
		contentPane.add(jButtonFiltrarAutor);
		jButtonFiltrarMes.setBounds(54, 210, 225, 32);
		jButtonFiltrarMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date dat = jCalendar.getDate();
				BLFacade facade = MainGUI.getBusinessLogic();
				List<Noticia> noticias=facade.getNoticiasMonth(dat);
				modelNoticia.removeAllElements();
				for(Noticia no:noticias) {
					modelNoticia.addElement(no);
				}
				JComboBoxNoticias.repaint();
			}
			
		});
		
		contentPane.add(jButtonFiltrarMes);
		jButtonFiltrarDia.setBounds(54, 260, 225, 32);
		jButtonFiltrarDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date dat = jCalendar.getDate();
				
				BLFacade facade = MainGUI.getBusinessLogic();
				List<Noticia> noticias=facade.getNoticias(dat);
				modelNoticia.removeAllElements();
				for(Noticia no:noticias) {
					modelNoticia.addElement(no);
				}
				JComboBoxNoticias.repaint();
			}
			
		});
		contentPane.add(jButtonFiltrarDia);
		
		
		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
				
				//jLabelError.setText("");
				//jLabelMsg.setText("");
				//jLabelMsg2.setText("");
				//lblConsultaCreada.setText("");
				
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

						datesWithNoticiasCurrentMonth=facade.getNoticiasDateMonth(jCalendar.getDate());
					}

					paintDaysWithNoticias(jCalendar,datesWithNoticiasCurrentMonth);

					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());
					
					Date firstDay = UtilDate.trim(calendarAct.getTime());

					try {
						BLFacade facade = MainGUI.getBusinessLogic();
						JComboBoxNoticias.removeAllItems();
						List<domain.Noticia> noticias = facade.getNoticiasMonth(firstDay);

						if (noticias.isEmpty())
							/*jLabelListOfEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")
									+ ": " + dateformat1.format(calendarAct.getTime()));*/
							System.out.println("No hay noticias en este mes.");
						else
							/*jLabelListOfEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events") + ": "
								+ dateformat1.format(calendarAct.getTime())); */
	
							System.out.println("Noticias " + noticias);

							
							for (domain.Noticia no : noticias)
								modelNoticia.addElement(no);
								JComboBoxNoticias.repaint();
							
							if (noticias.size() == 0) {
								/*jButtonCreateQuery.setEnabled(false);
								int n=tableModelQueries.getRowCount();
								for(int i=0;i<n;i++) {
									tableModelQueries.removeRow(0);
								}*/
									
							}else {
								//jButtonCreateQuery.setEnabled(true);
			                    ////////////////////////////////////////////// 
							}
								

							} catch (Exception e1) {

								//jLabelError.setText(e1.getMessage());
								e1.printStackTrace();
								System.out.println("No se ha podido acceder al las noticias");
							}
					

			}
		}});
		
		
		JComboBoxAutores.setBounds(54, 321, 225, 32);
		contentPane.add(JComboBoxAutores);
		

		jButtonCerrar.setBounds(566, 426, 192, 40);
		contentPane.add(jButtonCerrar);
		JComboBoxMedios.setBounds(54, 419, 225, 32);
		JComboBoxMedios.setModel(modelMedios);
		
		contentPane.add(JComboBoxMedios);
		jButtonFiltrarMedios.setBounds(54, 456, 225, 32);
		
		jButtonFiltrarMedios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String med=(String)JComboBoxMedios.getSelectedItem();
				BLFacade facade = MainGUI.getBusinessLogic();
				List<Noticia> noticias=facade.getNoticiasMedio(med);
				modelNoticia.removeAllElements();
				for(Noticia no:noticias) {
					modelNoticia.addElement(no);
				}
				JComboBoxNoticias.repaint();
			}
		});
		
		contentPane.add(jButtonFiltrarMedios);
		jButtonCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCerrar_actionPerformed(e);
			}
		});
		


		
		//http://javapiola.blogspot.com/2009/11/tutorial-de-jtextarea-en-java.html
		

	}
	
	private void btnCerrar_actionPerformed(ActionEvent e) {
		JFrame a = new MainGUI();
		a.setVisible(true);
		this.setVisible(false);
	}
	
	public static void paintDaysWithNoticias(JCalendar jCalendar,Vector<Date> datesWithNoticiasCurrentMonth) {
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
		
		
	 	for (Date d:datesWithNoticiasCurrentMonth){

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
}
