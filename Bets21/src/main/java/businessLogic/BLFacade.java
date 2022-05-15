package businessLogic;

import java.util.Vector;
import java.util.Date;
import java.util.List;

//import domain.Booking;
import domain.*;
import exceptions.EventAlreadyExistsException;
import exceptions.EventFinishedException;
import exceptions.PronosticAlreadyExist;
import exceptions.QuestionAlreadyExist;
import exceptions.UnknownTeamException;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	  

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
	@WebMethod Question createQuestion(Event event, String question, float betMinimum) throws EventFinishedException, QuestionAlreadyExist;
	
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();

	@WebMethod public boolean createUser(String us, String pass,String ccode, String correo);
	
	@WebMethod public Usuario login(String user, String pass);
	
	@WebMethod public Event createEvent(String inputDescription, Date firstDay) throws EventFinishedException, UnknownTeamException;
	
	@WebMethod public Event createEvent(Equipo local, Equipo visitante, Date firstDay) throws EventFinishedException, UnknownTeamException, EventAlreadyExistsException;
	
	@WebMethod public Question createPronostic(String pr,Event event,int i, double cuota) throws PronosticAlreadyExist;


	@WebMethod public List<Pronostico> findPronosticos(Question q) throws PronosticAlreadyExist;
	@WebMethod public List<Bet> getBet(Usuario user);
	@WebMethod public int crearApuesta(Usuario user, double pr, Pronostico p);
	@WebMethod public void aumentarDinero(Usuario user,double cant);
	@WebMethod public Vector<Event> getEventosAc(Date date);
	@WebMethod public boolean isEventoCerrar(Date date,Event evento);
	@WebMethod public void cerrarApuesta(Pronostico prono);
	@WebMethod public Question getQuestion(Event e,int i);
	@WebMethod public Vector<Question> getQuestionList(Event e);
	@WebMethod public void cerrarEvento(Event e);
	@WebMethod public Question getQuestion(Question q);
	@WebMethod public Usuario getUser(Usuario user);	
	@WebMethod public Comentarios createComent(String text,Event ev, Usuario us);
	@WebMethod public Event getEventoactualizado(Event ev);
	@WebMethod public Vector<Event> getEventosFinalizadosNoCerrados(Date date);	
	@WebMethod public List<Equipo> obtenerEquipos();
	@WebMethod public List<Noticia> getNoticiasMonth(Date date);
	@WebMethod public List<Noticia> getNoticias(Date date);
	@WebMethod public List<Noticia> getAllNoticias();
	@WebMethod public void eliminarNoticia(Noticia no);
	@WebMethod public Noticia createNoticia(String titulo, String subTitulo, String texto, String nomAutor, String nomMedio, Date fechaPubli);
	@WebMethod public List<String> getAllNoticiasAuthor();
	@WebMethod public List<Noticia> getNoticiasAuthor(String aut);
	@WebMethod public List<String> getAllNoticiasMedio();
	@WebMethod public List<Noticia> getNoticiasMedio(String med);
	@WebMethod public List<Equipo> getAllEquipos(int mode);
	@WebMethod public List<Usuario> getUsuarios(String s, String s2);
	@WebMethod public Usuario getUsuario(String s, String s2,int i);
	@WebMethod public void desBloquear(String s,Usuario u);
	@WebMethod public Vector<Date> getNoticiasDateMonth(Date date);
}
