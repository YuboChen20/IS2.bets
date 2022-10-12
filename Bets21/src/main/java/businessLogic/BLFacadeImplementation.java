package businessLogic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.*;
import exceptions.EventAlreadyExistsException;
import exceptions.EventFinishedException;
import exceptions.LeagueAlreadyExist;
import exceptions.LessThanMinimumTeamException;
import exceptions.MaximumNumberOfTeamsReached;
import exceptions.PronosticAlreadyExist;
import exceptions.QuestionAlreadyExist;
import exceptions.TeamAlreadyExistsException;
import exceptions.TeamAlreadyPlaysInDayException;
import exceptions.UnknownTeamException;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	static final String INI="initialize";
	DataAccess dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals(INI)) {
		    dbManager=new DataAccess(c.getDataBaseOpenMode().equals(INI));
		    dbManager.initializeDB();
		    } else
		     dbManager=new DataAccess();
		dbManager.close();

		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals(INI)) {
			da.open(true);
			da.initializeDB();
			da.close();

		}
		dbManager=da;	
		dbManager.open(false);
		
		LocalDateTime time=LocalDateTime.now();
		Date date= UtilDate.newDate(time.getYear(),time.getMonthValue()-1,time.getDayOfMonth());
		
		dbManager.finalizarApuesta(date);
		
		dbManager.close();
	}
	

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinishedException if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
   @WebMethod
   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinishedException, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
		dbManager.open(false);
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinishedException(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dbManager.createQuestion(event,question,betMinimum);		

		dbManager.close();
		
		return qry;
   }
	
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
    public boolean createUser(String us, String pass,String ccode, String correo) {
    	dbManager.open(false);
    	boolean b = dbManager.createUser(us, pass,ccode,correo);
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
	public Event createEvent(String inputDescription, Date firstDay) throws EventFinishedException, UnknownTeamException {
		
		dbManager.open(false);
		Event ev=null;
		
	    
		if(new Date().compareTo(firstDay)>0)
			throw new EventFinishedException(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 ev=dbManager.createEvent(inputDescription,firstDay);
		dbManager.close();
		
		return ev;
		
	}
    
    @WebMethod
	public Event createEvent(Equipo local, Equipo visitante, Date firstDay) throws EventFinishedException, UnknownTeamException, EventAlreadyExistsException, TeamAlreadyPlaysInDayException {
		
		dbManager.open(false);
		Event ev=null;
		
	    
		if(new Date().compareTo(firstDay)>0)
			throw new EventFinishedException(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
		
		
		 //qry=dbManager.createQuestion(event,question,betMinimum);	
		 ev=dbManager.createEvent(local, visitante, firstDay);
		dbManager.close();
		
		if(ev==null)
			throw new TeamAlreadyPlaysInDayException();
		
		return ev;
		
	}
    
    @WebMethod 
    public Question createPronostic(String pr,Event event,int i, double cuota) throws PronosticAlreadyExist {
    	dbManager.open(false);
    	Question b= dbManager.createPronostic(pr,event,i, cuota);
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

    @WebMethod public List<Bet> getBet(Usuario user){
        this.dbManager.open(false);
        List<Bet> b =this.dbManager.getBets(user);
        this.dbManager.close();
        return b;
    }
    @WebMethod public int crearApuesta(Usuario user, double pr, Pronostico p) {
    	  this.dbManager.open(false);
          int i=this.dbManager.crearApuesta(user,pr,p);
          this.dbManager.close();
          return i;
    }
    @WebMethod public void aumentarDinero(Usuario user,double cant) {
    	this.dbManager.open(false);
    	this.dbManager.aumentarDinero(user, cant);
    	this.dbManager.close();
    }
    
    @WebMethod public Vector<Event> getEventosAc(Date date) {
    	this.dbManager.open(false);
    	Vector<Event> vec= this.dbManager.getEventstoClose(date);
    	this.dbManager.close();
    	return vec;
    }
    @WebMethod public boolean isEventoCerrar(Date date,Event evento) {
    	Vector<Event> vec= this.getEventosAc(date);
    	return vec.contains(evento);
    }
    
    @WebMethod public void cerrarApuesta(Pronostico prono) {
    	this.dbManager.open(false);
    	this.dbManager.cerrarApuesta(prono);
    	this.dbManager.close();    }
    @WebMethod public Question getQuestion(Event e,int i) {
    	this.dbManager.open(false);
    	Question q= this.dbManager.getQuestion(e,i);
    	this.dbManager.close();
    	return q;
    }
    @WebMethod public Vector<Question> getQuestionList(Event e) {
    	this.dbManager.open(false);
    	Vector<Question> q= this.dbManager.getQuestion(e);
    	this.dbManager.close();
    	return q;
    }
    @WebMethod public void cerrarEvento(Event e) {
    	this.dbManager.open(false);
    	this.dbManager.cerrarEvento(e);
    	this.dbManager.close();    	this.dbManager.open(false);
 
    }
    @WebMethod public Question getQuestion(Question q) {
    	this.dbManager.open(false);
    	Question qu= this.dbManager.getQuestion(q);
    	this.dbManager.close();
    	return qu;
    	
    }
  
    @WebMethod public Usuario getUser(Usuario user) {
    	this.dbManager.open(false);
    	Usuario u= this.dbManager.getUser(user);
    	this.dbManager.close();
    	return u;
    }
    
    @WebMethod 
    public Comentarios createComent(String text,Event ev, Usuario us) {
	    LocalDateTime now = LocalDateTime.now();     
	    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
	    String formatDateTime = now.format(format);  
		this.dbManager.open(false);
		Comentarios com= dbManager.createComent(new Comentarios(text,ev,us,formatDateTime));
		this.dbManager.close();
		return com;
    }
    
    @WebMethod 
    public Event getEventoactualizado(Event ev) {
    	this.dbManager.open(false);
    	Event eve = this.dbManager.getEventoActualizado(ev);
    	this.dbManager.close();
    	return eve;
    }
    @WebMethod 
    public Vector<Event> getEventosFinalizadosNoCerrados(Date date){
    	dbManager.open(false);
		Vector<Event>  events=dbManager.getEventosFinalizadosNoCerrados(date);
		dbManager.close();
		return events;
    }
    
    
    @WebMethod 
    public List<Equipo> obtenerEquipos(){
    	dbManager.open(false);
		List<Equipo>  equipos=dbManager.obtenerEquipos();
		dbManager.close();
		return equipos;
    }
    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public List<Noticia> getNoticiasMonth(Date date) {
		dbManager.open(false);
		List<Noticia> noticias=dbManager.getNoticiasMonth(date);
		dbManager.close();
		return noticias;
	}
	
	@WebMethod public List<Noticia> getNoticias(Date date){
		dbManager.open(false);
		List<Noticia> noticias = dbManager.getNoticias(date);
		dbManager.close();
		return noticias;
	}
	
	@WebMethod public List<Noticia> getAllNoticias(){
		dbManager.open(false);
		List<Noticia> noticias = dbManager.getAllNoticias();
		dbManager.close();
		return noticias;
	}
	
	
	@WebMethod public void eliminarNoticia(Noticia no) {
		dbManager.open(false);
		dbManager.eliminarNoticia(no);
		dbManager.close();
		
	}
	@WebMethod public Noticia createNoticia(String titulo, String subTitulo, String texto, String nomAutor, String nomMedio) {
		dbManager.open(false);
		Noticia noticia = dbManager.createNoticia(new Noticia(titulo,subTitulo,texto,nomAutor,nomMedio,null));
		dbManager.close();
		return noticia;
		
	}
	
	@WebMethod public List<String> getAllNoticiasAuthor(){
		dbManager.open(false);
		List<String> autores = dbManager.getAllNoticiasAuthor();
		dbManager.close();
		return autores;
	}
	
	@WebMethod public List<Noticia> getNoticiasAuthor(String aut){
		dbManager.open(false);
		List<Noticia> noticias = dbManager.getNoticiasAuthor(aut);
		dbManager.close();
		return noticias;
	}
	
	@WebMethod public List<String> getAllNoticiasMedio(){
		dbManager.open(false);
		List<String> medios = dbManager.getAllNoticiasMedio();
		dbManager.close();
		return medios;
	}
	
	@WebMethod public List<Noticia> getNoticiasMedio(String med){
		dbManager.open(false);
		List<Noticia> noticias = dbManager.getNoticiasMedio(med);
		dbManager.close();
		return noticias;
	}
	
	@WebMethod public List<Equipo> getAllEquipos(int mode) {
		dbManager.open(false);
		List<Equipo> equipos = dbManager.getAllEquipos(mode);
		dbManager.close();
	 	return equipos;
	}
	   @WebMethod 
	    public List<Usuario> getUsuarios(String s, String s2){
	    	dbManager.open(false);
			List<Usuario>  usuarios=dbManager.getUsuarios(s,s2);
			dbManager.close();
			return usuarios;
	    }
	    @WebMethod 
	    public Usuario getUsuario(String s, String s2, int i) {
	    	dbManager.open(false);
			Usuario  u=dbManager.getUsuario(s,s2,i);
			dbManager.close();
			return u;
	    }
		@WebMethod 
		public void desBloquear(String s, Usuario u) {
			dbManager.open(false);
			dbManager.desBloquear(s,u);
			dbManager.close();
		}
		
		@WebMethod public Vector<Date> getNoticiasDateMonth(Date date){
			dbManager.open(false);
			Vector<Date>  dates=dbManager.getNoticiasDateMonth(date);
			dbManager.close();
			return dates;
		}

		@Override
		public void crearLiga(String nombre, int numEquipos) throws LeagueAlreadyExist, LessThanMinimumTeamException {
			dbManager.open(false);
			dbManager.crearLiga(nombre, numEquipos);
			dbManager.close();
		}

		@Override
		public void anadirEquipoALiga(String nombreEquipo, Liga liga) throws TeamAlreadyExistsException, MaximumNumberOfTeamsReached {
			dbManager.open(false);
			dbManager.anadirEquipoALiga(nombreEquipo, liga);
			dbManager.close();
		}

		@Override
		public void eliminarEquipoDeLiga(String nombreEquipo, Liga liga) {
			dbManager.open(false);
			dbManager.eliminarEquipoDeLiga(nombreEquipo, liga);
			dbManager.close();
		}
		
		@WebMethod public List<Equipo> getEquiposPorLiga(int mode, Liga liga) {
			dbManager.open(false);
			List<Equipo> equipos = dbManager.getEquiposPorLiga(mode,liga);
			dbManager.close();
		 	return equipos;
		}

		@Override
		public List<Liga> getAllLigas() {
			dbManager.open(false);
			List<Liga> ligas = dbManager.getAllLigas();
			dbManager.close();
		 	return ligas;
		}

		@Override
		public List<Equipo> getEquiposPorLiga(int mode, String nombreLiga) {
			dbManager.open(false);
			List<Equipo> equipos = dbManager.getEquiposPorLiga(mode,nombreLiga);
			dbManager.close();
		 	return equipos;
		}
}

