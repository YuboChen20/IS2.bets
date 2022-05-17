package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Noticia {
	private static final long serialVersionUID = 1L;
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer numNoticia;
	private String titulo;
	private String subTitulo;
	private String texto;
	private String nomAutor;
	private String nomMedio;
	private Date fechaPubli;
	
	public Noticia(String titulo, String subTitulo, String texto, String nomAutor, String nomMedio, Date fechaPubli) {
		this.titulo = titulo;
		this.subTitulo = subTitulo;
		this.texto = texto;
		this.nomAutor = nomAutor;
		this.nomMedio = nomMedio;
		this.fechaPubli = fechaPubli;
	}

	
	
	public Integer getNumNoticia() {
		return numNoticia;
	}



	public void setNumNoticia(Integer numNoticia) {
		this.numNoticia = numNoticia;
	}



	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubTitulo() {
		return subTitulo;
	}

	public void setSubTitulo(String subTitulo) {
		this.subTitulo = subTitulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getNomAutor() {
		return nomAutor;
	}

	public void setNomAutor(String nomAutor) {
		this.nomAutor = nomAutor;
	}

	public String getNomMedio() {
		return nomMedio;
	}

	public void setNomMedio(String nomMedio) {
		this.nomMedio = nomMedio;
	}

	public Date getFechaPubli() {
		return fechaPubli;
	}

	public void setFechaPubli(Date fechaPubli) {
		this.fechaPubli = fechaPubli;
	}
	
	@Override
	public String toString() {
		return this.fechaPubli.getDate()+"/"+(this.fechaPubli.getMonth())+"/"+(this.fechaPubli.getYear()+1900)+" - "+titulo +"\n";
		
	}
	
	

}