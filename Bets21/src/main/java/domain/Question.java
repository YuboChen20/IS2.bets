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
	private String question; 
	private float betMinimum;
	private String result;  
	private Integer NºApuesta;
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
		this.question = query;
		this.betMinimum=betMinimum;
		this.event = event;
		this.NºApuesta=0;
	}
	
	public Question(String query, float betMinimum,  Event event) {
		super();
		this.question = query;
		this.betMinimum=betMinimum;
		this.NºApuesta=0;
		this.event = event;
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
		return question;
	}


	/**
	 * Set the question description of the bet
	 * 
	 * @param question to be setted
	 */	
	public void setQuestion(String question) {
		this.question = question;
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
	
	

	
	public Integer getNºApuesta() {
		return NºApuesta;
	}

	public void setNºApuesta(Integer nºApuesta) {
		NºApuesta = nºApuesta;
	}

	public Vector<Pronostico> getPronosticos() {
		return pronosticos;
	}

	public void setPronosticos(Vector<Pronostico> pronosticos) {
		this.pronosticos = pronosticos;
	}

	/**
	 * This method checks if the pronostico already exists for that question
	 * 
	 * @param pronostico that needs to be checked if there exists
	 * @return true if the pronostico exists and false in other case
	 */
	public boolean DoesPrnosExists(String pronostico)  {	
         return pronostico.contains(pronostico);
	}
		
    



	public String toString(){
		return questionNumber+";"+question+";"+Float.toString(betMinimum);
	}


	public Pronostico getPron(int i) {
		return pronosticos.get(i);
	}

	
}