package domain;



import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
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
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Comentarios> comentarios= new Vector<Comentarios>();
	private boolean bloqueado;
	private Date fecha;    //fecha de bloque o última entrada
	private int numIntento;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Entrada> fechas;
	
	public Usuario(String userName, String passwor,String cardCode, boolean admi , String correo) {
    	this.userName= userName;
    	this.password=passwor;
    	this.cardCode=cardCode;
    	this.admin=admi;
    	this.correo=correo;
    	this.dinero=50;
    	this.bloqueado=false;
    	this.numIntento=0;
    	this.fechas = new Vector <Entrada>();
    	
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
	public Comentarios addCom(Comentarios c) {
		comentarios.add(c);
		return c;
	}
	public boolean isBloqueado() {
		return bloqueado;
	}
	public void setBloqueado(boolean b) {
		this.bloqueado = b;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setNumIntento(int i) {
		this.numIntento = i;
	}
	public int getNumIntento() {
		return numIntento;
	}
	public Vector<Entrada> getFechas(){
		return this.fechas;
	}
	public void addFechas(Entrada e){
		this.fechas.add(e);
	}
	
	

}