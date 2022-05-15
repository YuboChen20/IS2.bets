package domain;


import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;


@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Equipo {
	
	@XmlID
	@Id 
	private String nombre;

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Event> events=new Vector<Event>();
	
	private double numUsuariosApuestan;
	private double dineroApostado;
	
	public Equipo(String nombre) {
		this.nombre=nombre;
		this.setNumUsuariosApuestan(0);
		this.setDineroApostado(0);
	}
	public String getNombre() {
		return nombre;
	}

	public Vector<Event> getEvents() {
		return events;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEvents(Vector<Event> questions) {
		this.events = questions;
	}
	
	public double getDineroApostado() {
		return dineroApostado;
	}
	public void setDineroApostado(double dineroApostado) {
		this.dineroApostado = dineroApostado;
	}
	public void anadirEvento(Event ev) {
		this.events.add(ev);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipo other = (Equipo) obj;
		if (nombre != other.nombre)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
	public double getNumUsuariosApuestan() {
		return numUsuariosApuestan;
	}
	public void setNumUsuariosApuestan(double numUsuariosApuestan) {
		this.numUsuariosApuestan = numUsuariosApuestan;
	}
	public void incrNumUsuariosApuestan() {
		this.numUsuariosApuestan = numUsuariosApuestan+1;
	}
	public void halfIncrNumUsuariosApuestan() {
		this.numUsuariosApuestan = numUsuariosApuestan+0.5;
	}
	
	
	
	
	

	
}