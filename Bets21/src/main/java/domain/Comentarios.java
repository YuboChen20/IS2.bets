package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Comentarios {
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
    private Integer comtNumber;
    private String text;
	@XmlIDREF
    private Event evento;
	@XmlIDREF
	private Usuario user;
	private String date;
	
	public Comentarios (String text , Event evento, Usuario user, String date) {
		super();
		this.comtNumber=comtNumber;
		this.text=text;
		this.evento=evento;
		this.user=user;
		this.date=date;
	}

	public Integer getComtNumber() {
		return comtNumber;
	}

	public void setComtNumber(Integer comtNumber) {
		this.comtNumber = comtNumber;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Event getEvento() {
		return evento;
	}

	public void setEvento(Event evento) {
		this.evento = evento;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String toString() {
		return user.getUserName()+ " ["+ date +"]: " + text + "\n"  ;
	}
}
