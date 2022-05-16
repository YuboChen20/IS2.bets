package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Entrada {
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
    private Integer num;
	private boolean exito;
	private Date fecha;
	@XmlIDREF
	private Usuario us;
	private boolean bloqueado;
	private boolean bloqueoAdmin;
	
	public Entrada() {
		super();
	}
	public Entrada(Date f, boolean e,Usuario u,boolean b,boolean ba) {
		this.exito=e;
		this.fecha=f;
		this.us=u;
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