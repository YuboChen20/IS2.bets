package domain;



import java.util.ArrayList;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Usuario{
	
	/**
	 * 
	 */
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	private String userName;
	private String password;
	private String cardCode;
	private String correo;
	private boolean admin;
	private double dinero;
	private Vector<Bet> apuestas = new Vector<Bet>();
	
    public Usuario(String userName, String passwor,String cardCode, boolean admi , String correo) {
    	this.userName= userName;
    	this.password=passwor;
    	this.cardCode=cardCode;
    	this.admin=admi;
    	this.correo=correo;
    	this.dinero=50;
    	
    }
    public Usuario() {}
    
	public boolean isAdmin() {
		return admin;
	}


	public void setAdmin(boolean admin) {
		this.admin = admin;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Bet añadirApuesta(Bet a) {
	
		apuestas.add(a);
		
		return a;
		
	}

	public Vector<Bet> getApuestas() {
		
		return apuestas;
	}

	public void setApuestas(Vector<Bet> apuestas) {
		this.apuestas = apuestas;
	}

	public double getDinero() {
		return dinero;
	}

	public void setDinero(double dinero) {
		this.dinero = dinero;
	}
	
	public void addDinero(double cant){
		this.dinero=dinero + cant;
		
	}
	
	
	
	

}