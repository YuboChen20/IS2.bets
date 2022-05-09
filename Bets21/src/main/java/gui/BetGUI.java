package gui;
import javax.swing.*;

import businessLogic.BLFacade;
import domain.*;
import domain.Event;

import java.awt.*;

import java.util.ResourceBundle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BetGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	private JScrollPane scrollpane1;
	private JTextField textField;
    private int pos;
	public BetGUI(Usuario user, Event ev)	{		
	try
		{
			
			jbInit(user,ev);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void jbInit(Usuario user,Event ev) throws Exception{
		BLFacade facade = MainGUI.getBusinessLogic(); 
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		this.getContentPane().setLayout(null);
		
		this.setSize(new Dimension(873, 613));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Bet"));
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		scrollpane1=new JScrollPane(textArea);  
		scrollpane1.setBounds(59,227,741,239);
		getContentPane().add(scrollpane1);
		pos=0;
		Event eventoactualizado = facade.getEventoactualizado(ev);
		for(Comentarios c : eventoactualizado.getComentarios()) {
			textArea.insert(c.toString(), pos);
			pos=textArea.getCaretPosition();
		}
		textField = new JTextField();
		textField.setText("");
		textField.setBounds(124, 498, 676, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnComentar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Comentar")); //$NON-NLS-1$ //$NON-NLS-2$
		btnComentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Comentarios coment = facade.createComent(textField.getText(), ev, user);
				textArea.insert(coment.toString(), pos);
				pos=textArea.getCaretPosition();
				textField.setText("");
			}
		});
		btnComentar.setBounds(121, 527, 134, 21);
		getContentPane().add(btnComentar);
		
		JLabel lblNewLabel = new JLabel(user.getUserName() + " :");
		lblNewLabel.setBounds(55, 501, 45, 13);
		getContentPane().add(lblNewLabel);
	
	}
	
}
