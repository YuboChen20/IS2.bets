package domain;

import java.io.*;

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
	private Integer numbApostados; 
	@XmlIDREF
	private Question question;
	private Usuario user;

	public Pronostico(){
		super();
	}
	
	public Pronostico(Integer pronNumber, String prono, Question quest, Usuario usuario) {
		super();
		this.pronosNumber = pronNumber;
		this.pronostico = prono;
		this.numbApostados= 0;
		this.question = quest;
		this.user = usuario;
	}
	
	public Pronostico(String prono, Question quest, Usuario usuario) {
		super();
		this.pronostico = prono;
		this.numbApostados= 0;
		this.question = quest;
		this.user = usuario;
		
	}
	
	public Pronostico(String prono, Question quest) {
		super();
		this.pronostico = prono;
		this.numbApostados= 0;
		this.question = quest;
		
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

	public Integer getNumbApostados() {
		return numbApostados;
	}

	public void setNumbApostados(Integer numbApostados) {
		this.numbApostados = numbApostados;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String toString(){
		return pronosNumber+";"+pronostico+";"+Float.toString(numbApostados);
	}

	public Float getCuota() {
		return cuota;
	}

	public void setCuota(Float cuota) {
		this.cuota = cuota;
	}



	
	




	
}
