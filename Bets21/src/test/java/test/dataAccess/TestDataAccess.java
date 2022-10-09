package test.dataAccess;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import configuration.ConfigXML;
import domain.Bet;
import domain.Equipo;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import domain.Usuario;

public class TestDataAccess {
	protected  EntityManager  db;
	protected  EntityManagerFactory emf;

	ConfigXML  c=ConfigXML.getInstance();


	public TestDataAccess()  {
		
		System.out.println("Creating TestDataAccess instance");

		open();
		
	}

	
	public void open(){
		
		System.out.println("Opening TestDataAccess instance ");

		String fileName=c.getDbFilename();
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		
	}
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	public boolean removeEvent(Event ev) {
		System.out.println(">> DataAccessTest: removeEvent");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e!=null) {
			db.getTransaction().begin();
			db.remove(e);
			db.getTransaction().commit();
			return true;
		} else 
		return false;
    }
		
		public Event addEventWithQuestion(String desc, Date d, String question, float qty) {
			System.out.println(">> DataAccessTest: addEvent");
			Event ev=null;
				db.getTransaction().begin();
				try {
				    ev=new Event(desc,d);
				    ev.addQuestion(question, qty);
					db.persist(ev);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return ev;
	    }
		public boolean existQuestion(Event ev,Question q) {
			System.out.println(">> DataAccessTest: existQuestion");
			Event e = db.find(Event.class, ev.getEventNumber());
			if (e!=null) {
				return e.DoesQuestionExists(q.getQuestion());
			} else 
			return false;
			
		}
		
		public Event addEvent(Event ev) {
			System.out.println(">> DataAccessTest: addEvent");

				db.getTransaction().begin();
				try {
					db.persist(ev);
					db.getTransaction().commit();
					
				}
				catch (Exception e){
					e.printStackTrace();
				}
			Event ev1= db.find(Event.class, ev.getEventNumber());
			return ev1;
	    }
		
		public Usuario addUser(Usuario user) {
			System.out.println(">> DataAccessTest: addUser");

				db.getTransaction().begin();
				try {
					db.persist(user);
					db.getTransaction().commit();
					
				}
				catch (Exception e){
					e.printStackTrace();
				}
			Usuario u= db.find(Usuario.class,user.getUserName());
			return u;
	    }
		
		public boolean removeUser(Usuario user) {
			System.out.println(">> DataAccessTest: removeUser");
			Usuario u= db.find(Usuario.class,user.getUserName());
			if (u!=null) {
				db.getTransaction().begin();
				db.remove(u);
				db.getTransaction().commit();
				return true;
			} else 
			return false;
	    }
		public Pronostico addPronos(Pronostico pronos) {
			System.out.println(">> DataAccessTest: addProno");

				db.getTransaction().begin();
				try {
					db.persist(pronos);
					db.getTransaction().commit();
					
				}
				catch (Exception e){
					e.printStackTrace();
				}
			Pronostico p= db.find(Pronostico.class,pronos.getPronosNumber());
			return p;
	    }
		public boolean removePronostico(Pronostico pronos) {
			System.out.println(">> DataAccessTest: removeProno");
			Pronostico p= db.find(Pronostico.class,pronos.getPronosNumber());
			if (p!=null) {
				db.getTransaction().begin();
				db.remove(p);
				db.getTransaction().commit();
				return true;
			} else 
			return false;
	    }
		
		public Bet addBets(Bet bet) {
			System.out.println(">> DataAccessTest: addBet");

				db.getTransaction().begin();
				try {
					db.persist(bet);
					db.getTransaction().commit();
					
				}
				catch (Exception e){
					e.printStackTrace();
				}
			Bet b= db.find(Bet.class,bet.getBetNumber());
			return b;
	    }
		public boolean removeBet(Bet bet) {
			System.out.println(">> DataAccessTest: removeBet");
			Bet b= db.find(Bet.class,bet.getBetNumber());
			if (b!=null) {
				db.getTransaction().begin();
				db.remove(b);
				db.getTransaction().commit();
				return true;
			} else 
			return false;
	    }
		
	    public Equipo getEquipoById(String name) {
	    	System.out.println(">> DataAccessTest: getEquipoById");
	    	return db.find(Equipo.class, name);
	    	
	    }
	    
		public boolean existEvent(Event ev) {
			System.out.println(">> DataAccessTest: existEvent");
			Event e = db.find(Event.class, ev.getEventNumber());
			if(e==null) return false;
			else return true;
			
		}
		
		public Usuario addUsuario(String userName, String passwor,String cardCode, boolean admi , String correo, boolean bloqueado) {
			System.out.println(">> DataAccessTest: addUsuario");
			Usuario u=null;
				db.getTransaction().begin();
				try {
				    u=new Usuario(userName,passwor,cardCode,admi,correo);
				    u.setBloqueado(bloqueado);
					db.persist(u);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				} 
				return u;
	    }
		public boolean removeUsuario(Usuario u) {
			System.out.println(">> DataAccessTest: removeUsuario");
			Usuario us = db.find(Usuario.class, u.getUserName());
			if (us!=null) {
				db.getTransaction().begin();
				db.remove(us);
				db.getTransaction().commit();
				return true;
			} else 
			return false;
	    }
		
}

