package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import domain.Noticia;
import java.awt.TextField;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;

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
	private final JCalendar jCalendar = new JCalendar();
	private Calendar calendarAct = null;
	private Calendar calendarAnt = null;
	private final JButton jButtonCerrar = new JButton("Cerrar");
	private final JButton jButtonCrearNoticia = new JButton("Crear Noticia");
	private final JRadioButton jRadioButtonExisteAutor = new JRadioButton("Existe Autor");
	private final JRadioButton jRadioButtonNuevoAutor = new JRadioButton("Nuevo Autor");
	private final ButtonGroup buttonGroupAutor = new ButtonGroup();
	private final JComboBox JComboBoxMedios = new JComboBox();
	DefaultComboBoxModel<String> modelMedios = new DefaultComboBoxModel<String>();
	private final TextField textFieldMedios = new TextField();
	private final JRadioButton jRadioButtonExisteMedio = new JRadioButton("Existe Medio");
	private final JRadioButton jRadioButtonNuevoMedio = new JRadioButton("Nuevo Medio");
	private final ButtonGroup buttonGroupMedio = new ButtonGroup();
	


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
	public CreateNoticiaGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 986, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Font fontTitulo = new Font("Verdana", Font.BOLD, 15);
		Font fontSubTitulo = new Font("Verdana", Font.ITALIC, 12);
		Font fontTexto = new Font("Verdana", Font.PLAIN, 10);
		

		JComboBoxNoticias.setBounds(566, 39, 353, 32);
		JComboBoxNoticias.setModel(modelNoticia);
		JComboBoxAutores.setModel(modelAutores);
		contentPane.add(JComboBoxNoticias);
		textTitulo.setBounds(565, 98, 353, 22);
		
		BLFacade facade = MainGUI.getBusinessLogic();
		List<Noticia> noticias=facade.getAllNoticias();
		for(Noticia no:noticias) {
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
					textTitulo.setText(no.getTitulo());
					textSubTitulo.setText(no.getSubTitulo());
					textTexto.setText(no.getTexto());
					
					
				}catch(Exception el) {
					el.printStackTrace();
				}
					
					}
			
		});
		
		
		
		contentPane.add(textTitulo);
		textTitulo.setEditable(true);
		textTitulo.setFont(fontTitulo);
		textSubTitulo.setBounds(565, 142, 354, 32);
		textSubTitulo.setEditable(true);
		textSubTitulo.setFont(fontSubTitulo);
		
		contentPane.add(textSubTitulo);
		scrollPane.setBounds(566, 216, 353, 148);
		
		contentPane.add(scrollPane);
		scrollPane.setViewportView(textTexto);
		textTexto.setLineWrap(true);
		textTexto.setEditable(true);
		textTexto.setFont(fontTexto);
		textTexto.setWrapStyleWord(true);
		jCalendar.setBounds(54, 24, 225, 150);
		jCalendar.setTodayButtonVisible(false);
		
		contentPane.add(jCalendar);
		
		
		JComboBoxAutores.setBounds(127, 388, 225, 32);
		contentPane.add(JComboBoxAutores);
		

		jButtonCerrar.setBounds(771, 408, 148, 40);
		contentPane.add(jButtonCerrar);
		jButtonCrearNoticia.setBounds(608, 405, 114, 46);
		
		contentPane.add(jButtonCrearNoticia);
		
		JLabel jTitleListaNoticias = DefaultComponentFactory.getInstance().createTitle("Lista Noticias:");
		jTitleListaNoticias.setBounds(427, 44, 129, 23);
		contentPane.add(jTitleListaNoticias);
		
		JLabel jTitleTitulo = DefaultComponentFactory.getInstance().createTitle("Titulo:");
		jTitleTitulo.setBounds(500, 105, 92, 14);
		contentPane.add(jTitleTitulo);
		
		JLabel jTitleSubTitle = DefaultComponentFactory.getInstance().createTitle("Subtitulo:");
		jTitleSubTitle.setBounds(487, 146, 92, 14);
		contentPane.add(jTitleSubTitle);
		
		JLabel jTitleText = DefaultComponentFactory.getInstance().createTitle("Texto");
		jTitleText.setBounds(464, 229, 92, 14);
		contentPane.add(jTitleText);
		
		TextField textFieldAutor = new TextField();
		textFieldAutor.setBounds(127, 442, 227, 32);
		contentPane.add(textFieldAutor);
		

		buttonGroupAutor.add(jRadioButtonExisteAutor);
		jRadioButtonExisteAutor.setBounds(10, 393, 111, 23);
		contentPane.add(jRadioButtonExisteAutor);
		

		buttonGroupAutor.add(jRadioButtonNuevoAutor);
		jRadioButtonNuevoAutor.setBounds(10, 451, 111, 23);
		contentPane.add(jRadioButtonNuevoAutor);
		JComboBoxMedios.setBounds(127, 290, 225, 32);
		JComboBoxMedios.setModel(modelMedios);
		List<String> medios=facade.getAllNoticiasMedio();
		for(String me: medios) {
			modelMedios.addElement(me);
		}
		JComboBoxAutores.repaint();
		
		contentPane.add(JComboBoxMedios);
		textFieldMedios.setBounds(127, 328, 225, 32);
		
		contentPane.add(textFieldMedios);
		buttonGroupMedio.add(jRadioButtonExisteMedio);
		jRadioButtonExisteMedio.setBounds(20, 299, 111, 23);
		
		contentPane.add(jRadioButtonExisteMedio);
		buttonGroupMedio.add(jRadioButtonNuevoMedio);
		jRadioButtonNuevoMedio.setBounds(23, 341, 111, 23);
		
		contentPane.add(jRadioButtonNuevoMedio);
		jButtonCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCerrar_actionPerformed(e);
			}
		});
		
		jButtonCrearNoticia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titulo = textTitulo.getText();
				String subtitulo = textSubTitulo.getText();
				String texto = textTexto.getText();
				String nomAutor = "Anonimo";
				String nomMedio = "Desconocido";
				if(jRadioButtonExisteAutor.isSelected()) {
					nomAutor = (String) JComboBoxAutores.getSelectedItem();
				} else if(jRadioButtonNuevoAutor.isSelected()) {
					nomAutor = textFieldAutor.getText();
				}
				if(jRadioButtonExisteMedio.isSelected()) {
					nomMedio = (String) JComboBoxMedios.getSelectedItem();
				} else if(jRadioButtonNuevoAutor.isSelected()) {
					nomMedio= textFieldMedios.getText();
				}
				Date dat = jCalendar.getDate();
				BLFacade facade = MainGUI.getBusinessLogic();
				try {
					
					facade.createNoticia(titulo, subtitulo, texto, nomAutor, nomMedio, dat);
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});


		
		//http://javapiola.blogspot.com/2009/11/tutorial-de-jtextarea-en-java.html
		

	}
	
	private void btnCerrar_actionPerformed(ActionEvent e) {
		JFrame a = new MainGUI();
		a.setVisible(true);
		this.setVisible(false);
	}
}
