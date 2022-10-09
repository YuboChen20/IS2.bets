package test.businessLogic;


import java.util.Date;

import configuration.ConfigXML;
import domain.Equipo;
import domain.Event;
import domain.Pronostico;
import domain.Usuario;
import test.dataAccess.TestDataAccess;

public class TestFacadeImplementation {
	TestDataAccess dbManagerTest;
 	
    
	   public TestFacadeImplementation()  {
			
			System.out.println("Creating TestFacadeImplementation instance");
			ConfigXML c=ConfigXML.getInstance();
			dbManagerTest=new TestDataAccess(); 
			dbManagerTest.close();
		}
		
		 
		public boolean removeEvent(Event ev) {
			dbManagerTest.open();
			boolean b=dbManagerTest.removeEvent(ev);
			dbManagerTest.close();
			return b;

		}
		
		public Event addEventWithQuestion(String desc, Date d, String q, float qty) {
			dbManagerTest.open();
			Event o=dbManagerTest.addEventWithQuestion(desc,d,q, qty);
			dbManagerTest.close();
			return o;

		}
		
		public boolean existEvent(Event ev) {
			dbManagerTest.open();
			boolean b=dbManagerTest.existEvent(ev);
			dbManagerTest.close();
			return b;

		}
	    public Equipo getEquipoById(String name) {
	    	dbManagerTest.open();
	    	Equipo e =dbManagerTest.getEquipoById(name);
	    	dbManagerTest.close();
			return e;
	      	
	    }
	    
		public Usuario addUsuario(String userName, String passwor,String cardCode, boolean admi , String correo, boolean bloqueado) {
			dbManagerTest.open();
			Usuario u=dbManagerTest.addUsuario(userName,passwor,cardCode,admi,correo,bloqueado);
			dbManagerTest.close();
			return u;

	    }
		public boolean removeUsuario(Usuario u) {
			dbManagerTest.open();
			boolean b=dbManagerTest.removeUsuario(u);
			dbManagerTest.close();
			return b;

		}
		
		public Usuario addUser(Usuario u) {
			dbManagerTest.open();
			Usuario us=dbManagerTest.addUser(u);
			dbManagerTest.close();
			return us;

		}
		public Pronostico addPronostico(Pronostico pro) {
			dbManagerTest.open();
			Pronostico p=dbManagerTest.addPronos(pro);
			dbManagerTest.close();
			return p;

	    }
		public boolean removePronostico(Pronostico pro) {
			dbManagerTest.open();
			boolean b=dbManagerTest.removePronostico(pro);
			dbManagerTest.close();
			return b;

		}
		
		public Event addEvent(Event ev) {
			dbManagerTest.open();
			Event e=dbManagerTest.addEvent(ev);
			dbManagerTest.close();
			return e;

	    }
		

}
