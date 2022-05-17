package domain;

import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;


@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Liga {
	
	@XmlID
	@Id 
	private String nombre;
	private int numEquipos;

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Equipo> equipos =new Vector<Equipo>();
	

	public Liga(String nombre, int numEquipos) {
		this.nombre=nombre;
		this.setNumEquipos(numEquipos);
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Vector<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(Vector<Equipo> equipos) {
		this.equipos = equipos;
	}

	public boolean anadirEquipo(Equipo equipo) {
		if(equipos.size()>=numEquipos) return false;
		equipos.add(equipo);
		return true;
	}
	
	public boolean eliminarEquipo(Equipo equipo) {
		return equipos.remove(equipo);
	}
	
	public int getNumEquipos() {
		return numEquipos;
	}


	public void setNumEquipos(int numEquipos) {
		this.numEquipos = numEquipos;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Liga other = (Liga) obj;
		if (nombre != other.nombre)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return nombre;
	}


	
	
}

