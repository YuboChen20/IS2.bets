package domain;



import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Usuario{
	
	/**
	 * 
	 */
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	private String userName;
	private String password;
	private String cardCode;
	private String correo;
	private boolean admin;
	
    public Usuario(String userName, String passwor,String cardCode, boolean admi ) {
    	this.userName= userName;
    	this.password=passwor;
    	this.cardCode=cardCode;
    	this.admin=admi;
    	
    }

	public boolean isAdmin() {
		return admin;
	}


	public void setAdmin(boolean admin) {
		this.admin = admin;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	
	
	
	

}