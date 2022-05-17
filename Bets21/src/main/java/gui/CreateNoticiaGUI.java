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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import domain.Noticia;
import java.awt.TextField;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.SwingConstants;

public class CreateNoticiaGUI extends JFrame {


	private JPanel contentPane;
	private JComboBox JComboBoxNoticias = new JComboBox();
	DefaultComboBoxModel<Noticia> modelNoticia = new DefaultComboBoxModel<Noticia>();
	private JComboBox JComboBoxAutores = new JComboBox();
	DefaultComboBoxModel<String> modelAutores = new DefaultComboBoxModel<String>();
	private JTextArea textTexto = new JTextArea();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTextArea textTitulo = new JTextArea();
	private final JTextArea textSubTitulo = new JTextArea();
	private Calendar calendarAct = null;
	private Calendar calendarAnt = null;
	private final JButton jButtonAtras = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));
	private final JButton jButtonCrearNoticia = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateNoticia"));
	private final JComboBox JComboBoxMedios = new JComboBox();
	DefaultComboBoxModel<String> modelMedios = new DefaultComboBoxModel<String>();
	private final TextField textFieldMedios = new TextField();
	private Vector<Date> datesWithNoticiasCurrentMonth = new Vector<Date>();
	private final JLabel jLabelErrorTitulo = new JLabel("");
	private final JLabel jLabelErrorSubTitulo = new JLabel("");
	private final JLabel jLabelErrorTexto = new JLabel("");
	private final JLabel jLabelAutor = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AutorAviso"));
	private final JLabel jLabelMedio = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MedioAviso"));
	private final JLabel jTitleListaNoticias = DefaultComponentFactory.getInstance().createTitle(ResourceBundle.getBundle("Etiquetas").getString("ListaNoticia"));
	private final JLabel jTitleTitulo = DefaultComponentFactory.getInstance().createTitle(ResourceBundle.getBundle("Etiquetas").getString("Titulo"));
	private final JLabel jTitleSubTitle = DefaultComponentFactory.getInstance().createTitle(ResourceBundle.getBundle("Etiquetas").getString("SubTitulo"));
	private final JLabel jTitleText = DefaultComponentFactory.getInstance().createTitle(ResourceBundle.getBundle("Etiquetas").getString("Texto"));
	private final TextField textFieldAutor = new TextField();
	private final JButton jButtonNew = new JButton(ResourceBundle.getBundle("Etiquetas").getString("New"));
	private final JLabel jLabelMedios = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Medios")); 
	private final JLabel jLabelAutores = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Autores")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel jLabelCrear = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateNoticiasLetrero")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel jLabelNoticias = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Noticia"));
	private final JLabel jLabelCreada = new JLabel(""); //$NON-NLS-1$ //$NON-NLS-2$
	



	/**
	 * Create the frame.
	 */
	public CreateNoticiaGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 787, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Font fontTitulo = new Font("Verdana", Font.BOLD, 15);
		Font fontSubTitulo = new Font("Verdana", Font.ITALIC, 12);
		Font fontTexto = new Font("Verdana", Font.PLAIN, 10);
		

		JComboBoxNoticias.setBounds(271, 11, 478, 32);
		JComboBoxNoticias.setModel(modelNoticia);
		JComboBoxAutores.setModel(modelAutores);
		contentPane.add(JComboBoxNoticias);
		textTitulo.setBounds(272, 97, 477, 39);
		
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
		
		
		contentPane.add(textTitulo);
		textTitulo.setEditable(true);
		textTitulo.setFont(fontTitulo);
		textSubTitulo.setBounds(272, 174, 477, 39);
		textSubTitulo.setEditable(true);
		textSubTitulo.setFont(fontSubTitulo);
		textTitulo.setLineWrap(true);
		textTitulo.setWrapStyleWord(true);
		
		contentPane.add(textSubTitulo);
		scrollPane.setBounds(272, 247, 477, 150);
		textSubTitulo.setLineWrap(true);
		textSubTitulo.setWrapStyleWord(true);
		
		contentPane.add(scrollPane);
		scrollPane.setViewportView(textTexto);
		textTexto.setLineWrap(true);
		textTexto.setEditable(true);
		textTexto.setFont(fontTexto);
		textTexto.setWrapStyleWord(true);
		
		
		JComboBoxAutores.setBounds(12, 341, 225, 32);
		contentPane.add(JComboBoxAutores);
		

		jButtonAtras.setBounds(12, 7, 100, 40);
		contentPane.add(jButtonAtras);
		jButtonCrearNoticia.setBounds(399, 408, 120, 40);
		
		contentPane.add(jButtonCrearNoticia);
		

		jTitleListaNoticias.setBounds(171, 16, 87, 23);
		contentPane.add(jTitleListaNoticias);
		

		jTitleTitulo.setBounds(268, 74, 92, 14);
		contentPane.add(jTitleTitulo);
		
		
		jTitleSubTitle.setBounds(271, 149, 92, 14);
		contentPane.add(jTitleSubTitle);
		
		
		jTitleText.setBounds(271, 228, 54, 14);
		contentPane.add(jTitleText);
		
		
		textFieldAutor.setBounds(10, 379, 227, 32);
		contentPane.add(textFieldAutor);
		JComboBoxMedios.setBounds(12, 219, 225, 32);
		JComboBoxMedios.setModel(modelMedios);
		List<String> medios=facade.getAllNoticiasMedio();
		for(String me: medios) {
			modelMedios.addElement(me);
		}
		JComboBoxAutores.repaint();
		
		contentPane.add(JComboBoxMedios);
		textFieldMedios.setBounds(12, 257, 225, 32);
		
		contentPane.add(textFieldMedios);
		jLabelErrorTitulo.setBounds(338, 64, 411, 32);
		jLabelErrorTitulo.setForeground(Color.red);
		
		contentPane.add(jLabelErrorTitulo);
		jLabelErrorSubTitulo.setBounds(338, 147, 411, 22);
		jLabelErrorSubTitulo.setForeground(Color.red);
		
		contentPane.add(jLabelErrorSubTitulo);
		jLabelErrorTexto.setBounds(338, 220, 411, 22);
		jLabelErrorTexto.setForeground(Color.red);
		
		contentPane.add(jLabelErrorTexto);
		jLabelAutor.setBounds(12, 421, 225, 14);
		
		contentPane.add(jLabelAutor);
		jLabelMedio.setBounds(10, 295, 225, 14);
		
		contentPane.add(jLabelMedio);
		

		jButtonNew.setBounds(529, 408, 114, 40);
		contentPane.add(jButtonNew);
		

		jLabelMedios.setBounds(12, 199, 68, 14);
		contentPane.add(jLabelMedios);
		
		
		jLabelAutores.setBounds(10, 321, 72, 14);
		contentPane.add(jLabelAutores);
		jLabelCrear.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelCrear.setFont(new Font("Tahoma", Font.BOLD, 30));
		jLabelCrear.setBounds(12, 82, 225, 54);
		
		contentPane.add(jLabelCrear);
		jLabelNoticias.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelNoticias.setFont(new Font("Tahoma", Font.BOLD, 30));
		jLabelNoticias.setBounds(12, 121, 225, 54);
		
		contentPane.add(jLabelNoticias);
		

		jLabelCreada.setBounds(293, 421, 96, 23);
		contentPane.add(jLabelCreada);
		jLabelCreada.setForeground(Color.green);
		jButtonNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textTitulo.setText("");
				textSubTitulo.setText("");
				textTexto.setText("");
				textFieldMedios.setText("");
				textFieldAutor.setText("");
				jLabelErrorTitulo.setText("");
				jLabelErrorSubTitulo.setText("");
				jLabelErrorTexto.setText("");
				jLabelCreada.setText("");
				
			}
		});
		
		
		
		jButtonAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonAtras_actionPerformed(e);
			}
		});
		
		jButtonCrearNoticia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jLabelErrorTitulo.setText("");
				jLabelErrorSubTitulo.setText("");
				jLabelErrorTexto.setText("");
				jLabelErrorTexto.setForeground(Color.red);
				jLabelCreada.setText("");
				String titulo = textTitulo.getText();
				String subtitulo = textSubTitulo.getText();
				String texto = textTexto.getText();
				String nomAutor = "Anonimo";
				String nomMedio = "Desconocido";
				if(titulo.isEmpty()) {
					jLabelErrorTitulo.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorTitulo"));
					return;
				}
				if(subtitulo.isEmpty()) {
					jLabelErrorSubTitulo.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorSubTitulo"));
					return;
				}
				if(texto.isEmpty()) {
					jLabelErrorTexto.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorTexto"));
					return;
				}
				if((textFieldAutor.getText()).isEmpty()) {
					nomAutor = (String) JComboBoxAutores.getSelectedItem();
				} else {
					nomAutor = textFieldAutor.getText();
				}
				if((textFieldMedios.getText()).isEmpty()) {
					nomMedio = (String) JComboBoxMedios.getSelectedItem();
				} else{
					nomMedio= textFieldMedios.getText();
				}
				BLFacade facade = MainGUI.getBusinessLogic();
				try {
					
					facade.createNoticia(titulo, subtitulo, texto, nomAutor, nomMedio);
					
					jLabelCreada.setText(ResourceBundle.getBundle("Etiquetas").getString("CreatedNoticia"));
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});


		
		//http://javapiola.blogspot.com/2009/11/tutorial-de-jtextarea-en-java.html
		

	}
	
	private void jButtonAtras_actionPerformed(ActionEvent e) {
		this.setVisible(false);
		CreateAndQueryGUI a = CreateAndQueryGUI.getInstance();
		a.setVisible(true);
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
