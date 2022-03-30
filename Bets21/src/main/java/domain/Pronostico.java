package domain;

import java.io.*;
import java.util.ArrayList;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Pronostico implements Serializable {
	
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer pronosNumber;
	private String pronostico;
	private Float cuota;
	private float porcentajeApuesta; 
	@XmlIDREF
	private Question question;
	private ArrayList<Usuario> userList;

	public Pronostico(){
		super();
	}
	
	public Pronostico(Integer pronNumber, String prono, Question quest, float cuota) {
		super();
		this.pronosNumber = pronNumber;
		this.pronostico = prono;
		this.porcentajeApuesta= 0;
		this.question = quest;
		this.cuota=cuota;
		this.userList= new ArrayList<Usuario>();
		
	}
	
	public Pronostico(String prono, Question quest, float cuota) {
		super();
		this.pronostico = prono;
		this.porcentajeApuesta= 0;
		this.question = quest;
		this.cuota = cuota;
		this.userList= new ArrayList<Usuario>();
		
	}
	






	public Integer getPronosNumber() {
		return pronosNumber;
	}

	public void setPronosNumber(Integer pronosNumber) {
		this.pronosNumber = pronosNumber;
	}

	public String getPronostico() {
		return pronostico;
	}

	public void setPronostico(String pronostico) {
		this.pronostico = pronostico;
	}





	public float getPorcentajeApuesta() {
		return porcentajeApuesta;
	}

	public void setPorcentajeApuesta(float porcentajeApuesta) {
		this.porcentajeApuesta = porcentajeApuesta;
	}

	public ArrayList<Usuario> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<Usuario> userList) {
		this.userList = userList;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}



	public String toString(){
		return pronosNumber+";"+pronostico+";"+Float.toString(porcentajeApuesta);
	}

	public Float getCuota() {
		return cuota;
	}

	public void setCuota(Float cuota) {
		this.cuota = cuota;
	}



	
	




	
}
