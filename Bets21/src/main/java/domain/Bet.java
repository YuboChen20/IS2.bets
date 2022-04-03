package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Bet {

	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer betNumber;
	@XmlIDREF
	private Pronostico pronos;
	@XmlIDREF
	private Usuario us;
	private double bet;
	private double ganancia;
	

	
	public Bet(){
		super();
	}
	
	public Bet(Integer betNumber, Pronostico pronos, Usuario us, double bet) {
		super();
		this.betNumber = betNumber;
		this.pronos = pronos;
		this.us= us;
		this.bet = bet;
		ganancia=pronos.getCuota()*bet;
	}
	
	public Bet( Pronostico pronos, Usuario us, double bet) {
		super();
		this.pronos = pronos;
		this.us= us;
		this.bet = bet;
		ganancia=pronos.getCuota()*bet;
	}
	
	
	public Integer getBetNumber() {
		return betNumber;
	}

	public void setBetNumber(Integer betNumber) {
		this.betNumber = betNumber;
	}

	public Pronostico getPronostico() {
		return pronos;
	}

	public void setPronostico(Pronostico pronos) {
		this.pronos = pronos;
	}

	public Usuario getUsuario() {
		return us;
	}

	public void setUsuario(Usuario us) {
		this.us =us;
	}
	
	public Double getBet() {
		return bet;
	}

	public void setBet(double bet) {
		this.bet = bet;
	}

	
	
}


