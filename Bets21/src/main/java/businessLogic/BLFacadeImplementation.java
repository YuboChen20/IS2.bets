package businessLogic;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.*;
import exceptions.EventFinished;
import exceptions.PronosticAlreadyExist;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
		    dbManager=new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
		    dbManager.initializeDB();
		    } else
		     dbManager=new DataAccess();
		dbManager.close();

		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();

		}
		dbManager=da;		
	}
	

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
   @WebMethod
   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
		dbManager.open(false);
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dbManager.createQuestion(event,question,betMinimum);		

		dbManager.close();
		
		return qry;
   };
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
    @WebMethod	
	public Vector<Event> getEvents(Date date)  {
		dbManager.open(false);
		Vector<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}
	
	
	public void close() {
		DataAccess dB4oManager=new DataAccess(false);

		dB4oManager.close();

	}

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}
    
    @WebMethod
    public boolean createUser(String us, String pass,String ccode) {
    	dbManager.open(false);
    	boolean b = dbManager.createUser(us, pass,ccode);
    	this.dbManager.close();
    	return b;
    }
    
    public Usuario login(String user, String pass) {
    	this.dbManager.open(false);
    	Usuario u= dbManager.login(user, pass);
    	this.dbManager.close();
    	return u;
    }
    
    @WebMethod
	public Event createEvent(String inputDescription, Date firstDay) throws EventFinished {
		
		dbManager.open(false);
		Event ev=null;
		
	    
		if(new Date().compareTo(firstDay)>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 //qry=dbManager.createQuestion(event,question,betMinimum);	
		 ev=dbManager.createEvent(inputDescription,firstDay);
		dbManager.close();
		
		return ev;
		
	}
    
    @WebMethod 
    public Question createPronostic(String pr,Event event,int i) throws PronosticAlreadyExist {
    	dbManager.open(false);
    	Question b= dbManager.createPronostic(pr,event,i);
    	if(b==null) throw new PronosticAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorPronosAlreadyEx"));
    	this.dbManager.close();
    	return b;
    }

    @WebMethod public List<Pronostico> findPronosticos(Question q) throws PronosticAlreadyExist{
    	dbManager.open(false);
    	List<Pronostico> p = dbManager.findPronosticos(q);
    	if(p==null) throw new PronosticAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorPronosAlreadyEx"));
    	this.dbManager.close();
    	return p;
    }
    
    
}

