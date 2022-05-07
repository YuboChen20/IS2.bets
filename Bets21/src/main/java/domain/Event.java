package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Event implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer eventNumber;
	private String description; 
	private Date eventDate;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Question> questions=new Vector<Question>();
	
	@OneToMany(fetch=FetchType.EAGER)
	private Equipo[] equipos= new Equipo[2];
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Comentarios> comentarios= new Vector<Comentarios>();
	private boolean isFinished;
	private boolean isClosed;
	public Vector<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Vector<Question> questions) {
		this.questions = questions;
	}

	public Event() {
		super();
	}

	public Event(Integer eventNumber, String description,Date eventDate) {
		this.eventNumber = eventNumber;
		this.description = description;
		this.eventDate=eventDate;
		this.isFinished=false;
	}
	
	public Event( String description,Date eventDate) {
		this.description = description;
		this.eventDate=eventDate;
		this.isFinished=false;
	}

	public Integer getEventNumber() {
		return eventNumber;
	}

	public void setEventNumber(Integer eventNumber) {
		this.eventNumber = eventNumber;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description=description;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	
	
	public String toString(){
		return eventNumber+";"+description;
	}
	
	/**
	 * This method creates a bet with a question, minimum bet ammount and percentual profit
	 * 
	 * @param question to be added to the event
	 * @param betMinimum of that question
	 * @return Bet
	 */
	public Question addQuestion(String question, float betMinimum)  {
        Question q=new Question(question,betMinimum, this);
        questions.add(q);
        return q;
	}

	
	/**
	 * This method checks if the question already exists for that event
	 * 
	 * @param question that needs to be checked if there exists
	 * @return true if the question exists and false in other case
	 */
	public boolean DoesQuestionExists(String question)  {	
		for (Question q:this.getQuestions()){
			if (q.getQuestion().compareTo(question)==0)
				return true;
		}
		return false;
	}
		
	public Comentarios addCom(Comentarios c) {
		comentarios.add(c);
		return c;
	}
	
	
	public Vector<Comentarios> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Vector<Comentarios> comentarios) {
		this.comentarios = comentarios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eventNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (eventNumber != other.eventNumber)
			return false;
		return true;
	}
	
	public Question getQuest(int i) {
		return questions.get(i);
	}
	
	public boolean getisFinished() {
		return this.isFinished;
	}

	public void setisFinished(boolean estado) {
		this.isFinished=estado;
	}
	public boolean isClosed() {
		return isClosed;
	}

	public void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}

	public Equipo[] getEquipos() {
		return equipos;
	}

	public void setEquipos(Equipo local, Equipo visitante) {
		this.equipos[0] = local;
		this.equipos[1] = visitante;
	}
	
	
}
