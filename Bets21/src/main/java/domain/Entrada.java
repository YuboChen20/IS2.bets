package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Entrada {
	
	private boolean exito;
	private Date fecha;
	private Historial h;
	private boolean bloqueado;
	private boolean bloqueoAdmin;
	
	public Entrada() {}
	public Entrada(Date f, boolean e,Historial h,boolean b,boolean ba) {
		this.exito=e;
		this.fecha=f;
		this.h=h;
		this.bloqueado=b;
		this.bloqueoAdmin=ba;
	}
	public boolean isExito() {
		return exito;
	}
	public Date getFecha() {
		return fecha;
	}
	public String getS() {
		if(exito) {
			return fecha+" : con éxito";
		}
		if(!exito) {
			if(bloqueoAdmin)return fecha+" :  admin";		
			return fecha+" :  sin éxito";
		}
		return null;
		
	}
	public boolean isBloqueado() {
		return bloqueado;
	}
	public boolean isBloqueoAdmin() {
		return bloqueoAdmin;
	}
	
	
	

}