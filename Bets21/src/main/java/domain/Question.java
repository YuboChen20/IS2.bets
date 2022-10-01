package domain;

import java.io.*;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Question implements Serializable {
	
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer questionNumber;
	private String quest; 
	private float betMinimum;
	private String result;  
	private Integer N�Apuesta;
	private boolean Isclosed;
	@XmlIDREF
	private Event event;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Pronostico> pronosticos=new Vector<Pronostico>();
	

	public Question(){
		super();
	}
	
	public Question(Integer queryNumber, String query, float betMinimum, Event event) {
		super();
		this.questionNumber = queryNumber;
		this.quest = query;
		this.betMinimum=betMinimum;
		this.event = event;
		this.N�Apuesta=0;
		this.Isclosed=false;
	}
	
	public Question(String query, float betMinimum,  Event event) {
		super();
		this.quest = query;
		this.betMinimum=betMinimum;
		this.N�Apuesta=0;
		this.event = event;
		this.Isclosed=false;
	}
	
	public Vector<Pronostico> getListPronosticos() {
		return pronosticos;
	}

	/**
	 * Get the  number of the question
	 * 
	 * @return the question number
	 */
	public Integer getQuestionNumber() {
		return questionNumber;
	}

	/**
	 * Set the bet number to a question
	 * 
	 * @param questionNumber to be setted
	 */
	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}


	/**
	 * Get the question description of the bet
	 * 
	 * @return the bet question
	 */

	public String getQuestion() {
		return quest;
	}


	/**
	 * Set the question description of the bet
	 * 
	 * @param question to be setted
	 */	
	public void setQuestion(String question) {
		this.quest = question;
	}



	/**
	 * Get the minimun ammount of the bet
	 * 
	 * @return the minimum bet ammount
	 */
	
	public float getBetMinimum() {
		return betMinimum;
	}


	/**
	 * Get the minimun ammount of the bet
	 * 
	 * @param  betMinimum minimum bet ammount to be setted
	 */

	public void setBetMinimum(float betMinimum) {
		this.betMinimum = betMinimum;
	}



	/**
	 * Get the result of the  query
	 * 
	 * @return the the query result
	 */
	public String getResult() {
		return result;
	}



	/**
	 * Get the result of the  query
	 * 
	 * @param result of the query to be setted
	 */
	
	public void setResult(String result) {
		this.result = result;
	}



	/**
	 * Get the event associated to the bet
	 * 
	 * @return the associated event
	 */
	public Event getEvent() {
		return event;
	}



	/**
	 * Set the event associated to the bet
	 * 
	 * @param event to associate to the bet
	 */
	public void setEvent(Event event) {
		this.event = event;
	}
	
	
	/**
	 * This method creates a pronostico
	 * 
	 * @param pronostico
	 * @return Pronostico
	 */
	public Pronostico addPronostico(Pronostico p)  {
        pronosticos.add(p);
        return p;
	}
	
	
	public Pronostico addPronosticoNoHecho(String description,double cuota)  {
		Pronostico p = new Pronostico(description, this,cuota);
        pronosticos.add(p);
        return p;
	}
	

	
	public Integer getN�Apuesta() {
		return N�Apuesta;
	}

	public void addN�Apuesta() {
		this.N�Apuesta ++;
	}
	public void setN�Apuesta(Integer n�Apuesta) {
		N�Apuesta = n�Apuesta;
	}
	public boolean isIsclosed() {
		return Isclosed;
	}

	public void setIsclosed(boolean isclosed) {
		Isclosed = isclosed;
	}

	public Vector<Pronostico> getPronosticos() {
		return pronosticos;
	}

	public void setPronosticos(Vector<Pronostico> pronosticos) {
		this.pronosticos = pronosticos;
	}

 



	public String toString(){
		return questionNumber+";"+quest+";"+Float.toString(betMinimum);
	}


	public Pronostico getPron(int i) {
		return pronosticos.get(i);
	}

	
}