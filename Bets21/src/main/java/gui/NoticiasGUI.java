package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Noticia;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class NoticiasGUI extends JFrame {

	private JPanel contentPane;
	private JComboBox<Noticia> JComboBoxNoticias = new JComboBox<Noticia>();
	DefaultComboBoxModel<Noticia> modelNoticia = new DefaultComboBoxModel<Noticia>();
	private JComboBox<String> JComboBoxAutores = new JComboBox<String>();
	DefaultComboBoxModel<String> modelAutores = new DefaultComboBoxModel<String>();
	private JTextArea textTexto = new JTextArea();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTextArea textTitulo = new JTextArea();
	private final JTextArea textSubTitulo = new JTextArea();
	private final JButton jButtonFiltrarAutor = new JButton(ResourceBundle.getBundle("Etiquetas").getString("FiltroAutor"));
	private final JButton jButtonAtras = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));
	private final JComboBox<String> JComboBoxMedios = new JComboBox<String>();
	DefaultComboBoxModel<String> modelMedios = new DefaultComboBoxModel<String>();
	private final JButton jButtonFiltrarMedios = new JButton(ResourceBundle.getBundle("Etiquetas").getString("FiltroMedio"));
	private final JLabel jTitleListaNoticias = DefaultComponentFactory.getInstance().createTitle(ResourceBundle.getBundle("Etiquetas").getString("ListaNoticia"));
	private final JLabel JLabelNoticia = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Noticia")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JButton jButtonFiltro = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Filtro")); //$NON-NLS-1$ //$NON-NLS-2$






	/**
	 * Create the frame.
	 */
	public NoticiasGUI(Integer backNum) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Font fontTitulo = new Font("Verdana", Font.BOLD, 15);
		Font fontSubTitulo = new Font("Verdana", Font.ITALIC, 12);
		Font fontTexto = new Font("Verdana", Font.PLAIN, 10);
		

		JComboBoxNoticias.setBounds(298, 24, 477, 45);
		JComboBoxNoticias.setModel(modelNoticia);
		JComboBoxAutores.setModel(modelAutores);
		contentPane.add(JComboBoxNoticias);
		textTitulo.setBounds(298, 80, 477, 39);
		
		BLFacade facade = MainGUI.getBusinessLogic();

		List<Noticia> noticias=facade.getAllNoticias();
		for(Noticia no: noticias) {
			modelNoticia.addElement(no);
		}
		JComboBoxNoticias.repaint();
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
						JComboBoxMedios.setSelectedItem(no.getNomMedio());
						JComboBoxAutores.setSelectedItem(no.getNomAutor());
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
		textSubTitulo.setBounds(298, 130, 477, 54);
		textSubTitulo.setEditable(false);
		textSubTitulo.setFont(fontSubTitulo);
		jTitleListaNoticias.setBounds(193, 35, 95, 23);
		contentPane.add(jTitleListaNoticias);
		textTitulo.setLineWrap(true);
		textTitulo.setWrapStyleWord(true);
		
		contentPane.add(textSubTitulo);
		scrollPane.setBounds(298, 195, 477, 203);
		textSubTitulo.setLineWrap(true);
		textSubTitulo.setWrapStyleWord(true);
		
		contentPane.add(scrollPane);
		scrollPane.setViewportView(textTexto);
		textTexto.setEditable(false);
		textTexto.setFont(fontTexto);
		textTexto.setLineWrap(true);
		textTexto.setWrapStyleWord(true);
		jButtonFiltrarAutor.setBounds(26, 232, 225, 32);
		
		
		contentPane.add(jButtonFiltrarAutor);
		
		
		JComboBoxAutores.setBounds(26, 189, 225, 32);
		contentPane.add(JComboBoxAutores);
		

		jButtonAtras.setBounds(10, 11, 112, 32);
		contentPane.add(jButtonAtras);
		JComboBoxMedios.setBounds(26, 275, 225, 32);
		JComboBoxMedios.setModel(modelMedios);
		
		contentPane.add(JComboBoxMedios);
		jButtonFiltrarMedios.setBounds(26, 318, 225, 32);
		
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
		JLabelNoticia.setHorizontalAlignment(SwingConstants.CENTER);
		JLabelNoticia.setFont(new Font("Tahoma", Font.BOLD, 30));
		JLabelNoticia.setBounds(10, 87, 260, 76);
		
		contentPane.add(JLabelNoticia);

		jButtonAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(backNum==1) {
					jButtonAtras_actionPerformed1(e);
				}else if(backNum==2) {
					jButtonAtras_actionPerformed2(e);
				}else {
					System.out.println("ERROR NOTICIAGUI");
				}
			}
		});
		jButtonFiltro.setBounds(26, 361, 225, 37);
		contentPane.add(jButtonFiltro);
		
		jButtonFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
				modelNoticia.removeAllElements();
				List<Noticia> noticias=facade.getAllNoticias();
				for(Noticia no: noticias) {
					modelNoticia.addElement(no);
				}
				JComboBoxNoticias.repaint();
			}
		});


	}
	
	private void jButtonAtras_actionPerformed1(ActionEvent e) {
		this.setVisible(false);
		FQuestionInvitado2 a = new FQuestionInvitado2();
		a.setVisible(true);
	}
	private void jButtonAtras_actionPerformed2(ActionEvent e) {
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
