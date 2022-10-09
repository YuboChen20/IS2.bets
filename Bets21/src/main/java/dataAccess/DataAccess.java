package dataAccess;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
//hello
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.*;
import exceptions.EventAlreadyExistsException;
import exceptions.LeagueAlreadyExist;
import exceptions.LessThanMinimumTeamException;
import exceptions.MaximumNumberOfTeamsReached;
import exceptions.QuestionAlreadyExist;
import exceptions.TeamAlreadyExistsException;
import exceptions.UnknownTeamException;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();

     public DataAccess(boolean initializeMode)  {
		
		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		open(initializeMode);
		
	}

	public DataAccess()  {	
		 this(false);
	}
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();
		try {
			Liga ligaSantander=new Liga("Liga Santander",20);
			Liga premierLeague=new Liga("Premier League",20);
			
			Equipo atleticoDeMadrid= new Equipo("Atlético de Madrid", ligaSantander);
			Equipo atlheticDeBilbao= new Equipo("Athletic de Bilbao", ligaSantander);
			Equipo barcelona= new Equipo("Barcelona", ligaSantander);
			Equipo cadiz= new Equipo("Cádiz", ligaSantander);
			Equipo alaves= new Equipo("Alavés", ligaSantander);
			Equipo celtaDeVigo= new Equipo("Celta de Vigo", ligaSantander);
			Equipo elche= new Equipo("Elche", ligaSantander);
			Equipo espanyol= new Equipo("Espanyol", ligaSantander);
			Equipo getafe= new Equipo("Getafe", ligaSantander);
			Equipo granada= new Equipo("Granada", ligaSantander);
			Equipo levante= new Equipo("Levante", ligaSantander);  
			Equipo mallorca= new Equipo("Mallorca", ligaSantander);
			Equipo osasuna= new Equipo("Osasuna", ligaSantander);
			Equipo rayoVallecano= new Equipo("RayoVallecano", ligaSantander);
			Equipo realBetis= new Equipo("Real Betis", ligaSantander);
			Equipo realMadrid= new Equipo("Real Madrid", ligaSantander);
			Equipo realSociedad= new Equipo("Real Sociedad", ligaSantander);
			Equipo sevilla= new Equipo("Sevilla", ligaSantander);
			Equipo valencia= new Equipo("Valencia", ligaSantander);
			Equipo villareal= new Equipo("Villarreal", ligaSantander);
			
		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
		   
		   
		   	Noticia no1 = new Noticia("TITULO1", "SUBTITULOS", "ESCRIBIR TEXTO AQUI.	PLANTILLA", "YUBO CHEN", "MARCA", UtilDate.newDate(year,month,17));
		   	Noticia no2 = new Noticia("El fin del futbol", "Debido a unos sucesos, el futbol se acaba", "ME HAN DESPEDIDO.", "YUBO CHEN", "MARCA", UtilDate.newDate(year,month,17));
		   	db.persist(no1);
		   	db.persist(no2);
		   	String titulo3="Seis jugadores del Sevilla, nominados al once de LaLiga";
		   	String subTitulo3="Bono, Jesús Navas, Koundé, Diego Carlos, Acuña y Joan Jordán optan al reconocimiento entre más de 45 futbolistas";
		   	String texto3="Seis jugadores del Sevilla han sido nominados por LaLiga para la elección del mejor once de la temporada 21-22, premios que se entregarán en una gala que será presidida por Javier Tebas. A través de la aplicación Player App, los aficionados al fútbol podrán votar a los mejores jugadores de la Liga entre un total de 46 futbolistas de todos los equipos que conforman la Liga Santander. Además, un comité de expertos formado por periodistas también participará en la votación.\n"
		   			+ "\n"
		   			+ "Bono, Jesús Navas, Koundé, Diego Carlos, Acuña, Joan Jordán optan a formar parte de este once de estrellas a los que acompañarán los cuatro siguientes jugadores más votados.\n"
		   			+ "\n"
		   			+ "La lista completa de nominados la forman los siguientes futbolistas:\n"
		   			+ "\n"
		   			+ "-Porteros: Yassine Bounou (Sevilla FC); Thibaut Courtois (Real Madrid); Gero Rulli (Villarreal CF); Diego López (RCD Espanyol); Jan Oblak (Atlético de Madrid) y Matías Dituro (Celta de Vigo).\n"
		   			+ "\n"
		   			+ "-Defensas: Jordi Alba (FC Barcelona); Militao (Real Madrid); Marcos Acuña (Sevilla); Ronald Araújo (Barcelona); Le Normand (Real Sociedad); David García (CA Osasuna); Alaba (Real Madrid); Álex Moreno (Real Betis); Íñigo Martínez (Athletic Club); Diego Carlos (Sevilla); Kounde (Sevilla) y Jesús Navas (Sevilla).\n"
		   			+ "\n"
		   			+ "-Centrocampistas: Kroos (Real Madrid); Casemiro (Real Madrid); Joan Jordán (Sevilla); Fekir (Betis); Muniain (Athletic Club); Modric (Real Madrid); Juanmi (Betis); Mikel Merino (Real Sociedad); Carlos Soler (Valencia CF); Canales (Betis); Lemar (Atlético de Madrid); Pedri (Barcelona); Trejo (Rayo Vallecano); Brais Méndez (Celta); Capoué (Villareal); Yeremi Pino (Villareal) y Frenkie De Jong (Barcelona).\n"
		   			+ "\n"
		   			+ "-Delanteros: Benzema (Real Madrid); Vini Jr (Real Madrid); Aspas (Celta); Joao Félix (Atlético de Madrid); Mikel Oyarzabal (Real Sociedad); Joselu (Deportivo Alavés); Raul de Tomás (Espanyol); Enes Ünal (Getafe CF); Ángel Correa (Atlético de Madrid); Guedes (Valencia) y Borja Iglesias (Betis).";
		   	Noticia no3 =new Noticia(titulo3, subTitulo3, texto3, "JUAN PELEGRÍN", "MUNDODEPORTIVO", UtilDate.newDate(year,month,18));
		   	db.persist(no3);
		   	
		   	String titulo4="Alberto De la Bella anuncia su retirada del fútbol profesional";
		   	String subTitulo4="El lateral zurdo, ex de la Real Sociedad y ahora en el Cartagena, cuelga las botas a los 36 años";
		   	String texto4="Una de las muchas leyendas que ha tenido la Real Sociedad,Alberto de la Bella, da un paso al lado y deja el fútbol profesional, tal y como ha anunciado el mismo jugador a través de sus redes sociales. El jugador catalán, quién militaba en las filas del FC Cartagena, cuelga las botas con un emotivo reportaje en el que se muestran algunos de sus mejores momentos que ha vivido en sus diferentes equipos.\n"
		   			+ "\n"
		   			+ "En el vídeo, De la Bella, agradece no sólo a todos los aficionados, sino también, a todos y cada uno de los entrenadores y compañeros que ha tenido a lo largo de su vida deportiva. El jugador ha disputado, aproximadamente, la cifra de 300 partidos en el fútbol profesional, con un total de 22.155 minutos, aportando un total de 9 goles. Donde más partidos ha jugado, y donde más temporadas ha militado, ha sido en la Real Sociedad, club en el que más veces ha lucido la camiseta. En total han sido 6 equipos en los que ha defendido los colores, en las últimas dos temporadas, en el FC Cartagena."
		   			+ "\n"
		   			+ "Con los albinegros ha jugado dos temporadas, disputando un total de 53 partidos, sumando un total de 2.937 minutos, aportando un gol fundamental para la salvación del cuadro albinegro. Su tanto ante el Málaga en la temporada 2020/2021, le permitió al Efesé sumar los tres puntos en su pelea por la permanencia.\n"
		   			+ "\n"
		   			+ "Finalmente, y después de algunas muchas temporadas, Alberto de la Bella, decide dejar a un lado la élite del fútbol profesional.";
	    
		   	Noticia no4 =new Noticia(titulo4, subTitulo4, texto4, "ÁLVARO PRIAN", "MARCA", UtilDate.newDate(year,month+1,3));
		   	db.persist(no4);
		   	
		    Event ev1=new Event(1, "Atlético de Madrid-Athletic de Bilbao", UtilDate.newDate(year,month,17), atleticoDeMadrid, atlheticDeBilbao);
			Event ev2=new Event(2, "Elche-Barcelona", UtilDate.newDate(year,month,17), elche, barcelona);
			Event ev3=new Event(3, "Getafe-Celta de Vigo", UtilDate.newDate(year,month,17), getafe, celtaDeVigo);
			Event ev4=new Event(4, "Alavés-Osasuna", UtilDate.newDate(year,month,17), alaves, osasuna);
			Event ev5=new Event(5, "Espanyol-Villareal", UtilDate.newDate(year,month,17), espanyol, villareal);
			Event ev6=new Event(6, "Granada-Sevilla", UtilDate.newDate(year,month,17), granada, sevilla);
			Event ev7=new Event(7, "Mallorca-Valencia", UtilDate.newDate(year,month,17), mallorca, valencia);
			Event ev8=new Event(8, "Cádiz-Rayo Vallecano", UtilDate.newDate(year,month,17), cadiz, rayoVallecano);
			Event ev9=new Event(9, "Real Betis-Levante", UtilDate.newDate(year,month,17), realBetis, levante);
			Event ev10=new Event(10, "Real Sociedad-Real Madrid", UtilDate.newDate(year,month,17), realSociedad, realMadrid);

			Event ev11=new Event(11, "Atlético de Madrid-Athletic de Bilbao", UtilDate.newDate(year,month,1), atleticoDeMadrid, atlheticDeBilbao);
			Event ev12=new Event(12, "Elche-Barcelona", UtilDate.newDate(year,month,1), elche, barcelona);
			Event ev13=new Event(13, "Getafe-Celta de Vigo", UtilDate.newDate(year,month,1), getafe, celtaDeVigo);
			Event ev14=new Event(14, "Alavés-Osasuna", UtilDate.newDate(year,month,1), alaves, osasuna);
			Event ev15=new Event(15, "Espanyol-Villareal", UtilDate.newDate(year,month,1), espanyol, villareal);
			Event ev16=new Event(16, "Granada-Sevilla", UtilDate.newDate(year,month,1), granada, sevilla);
			

			Event ev17=new Event(17, "Mallorca-Valencia", UtilDate.newDate(year,month+1,28), mallorca, valencia);
			Event ev18=new Event(18, "Cádiz-Rayo Vallecano", UtilDate.newDate(year,month+1,28), cadiz, rayoVallecano);
			Event ev19=new Event(19, "Real Betis-Levante", UtilDate.newDate(year,month+1,28), realBetis, levante);
			Event ev20=new Event(20, "Real Sociedad-Real Madrid", UtilDate.newDate(year,month+1,28), realSociedad, realMadrid);
			Event ev21=new Event(21, "Betis-Real Madrid", UtilDate.newDate(year,month-2,28),realBetis, realMadrid);
			Event ev22=new Event(22, "Barcelona-Real Madrid", UtilDate.newDate(year,month-2,28), barcelona, realMadrid);
			
			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
			Question q7;
			Question q8;
			Question q9;
			
			
			q7=ev21.addQuestion("¿Habrá goles en la primera parte?",2);
			q8=ev21.addQuestion("¿Habrá goles en la segunda parte?",2);
			q9=ev22.addQuestion("¿Habrá goles en la segunda parte?",2);
			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion("¿Quién ganará el partido?",1);
				q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
				q3=ev11.addQuestion("¿Quién ganará el partido?",1);
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
				q5=ev17.addQuestion("¿Quién ganará el partido?",1);
				q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion("Who will win the match?",1);
				q2=ev1.addQuestion("Who will score first?",2);
				q3=ev11.addQuestion("Who will win the match?",1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				q6=ev17.addQuestion("Will there be goals in the first half?",2);
			}			
			else {
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);
				
			}
		

			Pronostico p1=new Pronostico("0-1",q4,1.2);
			Pronostico p2=new Pronostico("0-2",q4,1.4);
			Pronostico p3=new Pronostico("0-3",q4,1.2);
			Pronostico p4=new Pronostico("0-4",q4,1.2);
			
			q4.addPronostico(p1);
			q4.addPronostico(p2);
			q4.addPronostico(p3);
			q4.addPronostico(p4);
			
			Pronostico p5=new Pronostico("0-1",q7,1.2);
			Pronostico p9=new Pronostico("1-0",q7,1.4);
			Pronostico p10=new Pronostico("0-4",q8,1.2);
			Pronostico p11=new Pronostico("0-4",q9,1.2);
		
			Pronostico p6=new Pronostico("0-2",q3,1.4);
			Pronostico p7=new Pronostico("0-3",q3,1.2);
			Pronostico p8=new Pronostico("0-4",q3,1.2);
			
			q3.addPronostico(p6);
			q3.addPronostico(p7);
			q3.addPronostico(p8);
			q7.addPronostico(p5);
			q7.addPronostico(p9);
			q8.addPronostico(p10);
			q9.addPronostico(p11);
			final String CONTRASEÑA = "12345";
			Usuario admin= new Usuario("Alfredo",CONTRASEÑA,null,true,null);
			Usuario user= new Usuario("User1",CONTRASEÑA,"1010293833",false,"usuariomasguapo@gmail.com");
			Usuario user2= new Usuario("User2",CONTRASEÑA,"1010294833",false,"usuariamasguapa@gmail.com");
			Usuario admi1= new Usuario("Silvia",CONTRASEÑA,null,true,null);
			Usuario admi2= new Usuario("Yubo",CONTRASEÑA,null,true,null);
			Usuario admi3= new Usuario("Carlos",CONTRASEÑA,null,true,null);
			Usuario admi4= new Usuario("Jaime",CONTRASEÑA,null,true,null);
	
			
			db.persist(ligaSantander);
			db.persist(premierLeague);
			db.persist(atleticoDeMadrid);
			db.persist(atlheticDeBilbao);
			db.persist(barcelona);
			db.persist(cadiz);
			db.persist(alaves);
			db.persist(celtaDeVigo);
			db.persist(elche);
			db.persist(espanyol);
			db.persist(getafe);
			db.persist(granada);
			db.persist(levante);
			db.persist(mallorca);
			db.persist(osasuna);
			db.persist(rayoVallecano);
			db.persist(realBetis);
			db.persist(realMadrid);
			db.persist(realSociedad);
			db.persist(sevilla);
			db.persist(valencia);
			db.persist(villareal);
			
						
			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);
			db.persist(q7);
			db.persist(q8);
			db.persist(q9);
			
			
			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);		
			db.persist(ev21);		
			db.persist(ev22);	
			


				

			db.persist(admin);
			db.persist(user);
			db.persist(user2);
			db.persist(admi1);
			db.persist(admi2);
			db.persist(admi3);
			db.persist(admi4);
			db.persist(p1);
			db.persist(p2);
			db.persist(p3);
			db.persist(p4);
			db.persist(p5);
			db.persist(p6);
			db.persist(p7);
			db.persist(p8);
			db.persist(p9);
			db.persist(p10);
			db.persist(p11);
	
		    db.getTransaction().commit();	
		    
			this.crearApuesta(user,10,p1);
			this.crearApuesta(user,12,p5);
		    LocalDateTime now = LocalDateTime.now();     
		    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
		    String formatDateTime = now.format(format);  
			
			this.createComent("Hola", ev11, user, formatDateTime);
			this.createComent("Va a ganar Atletico", ev11, user, formatDateTime);
			this.createComent("......", ev11, user2, formatDateTime);
		    System.out.println("Db initialized");

		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		
			Event ev = db.find(Event.class, event.getEventNumber());
			
			if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
			
			db.getTransaction().begin();
			Question q = ev.addQuestion(question, betMinimum);
			//db.persist(q);
			db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			db.getTransaction().commit();
			return q;
		
	}
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
	 	 for (Event ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
	 	return res;
	}
	
	
	public void open(boolean initializeMode){
			
		System.out.println("Opening DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());
	
		String fileName=c.getDbFilename();
		if (initializeMode) {
			fileName=fileName+";drop";
			System.out.println("Deleting the DataBase");
		}
			
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
	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
		Event ev = db.find(Event.class, event.getEventNumber());
		return ev.DoesQuestionExists(question);
		
	
	}
	public void close(){
			db.close();
			System.out.println("DataBase closed");
	}
		
	public boolean createUser(String userName, String pass,String cCode, String correo){
		Usuario u= db.find(Usuario.class,userName);
		if(u!=null) return false;
		u = new Usuario(userName,pass,cCode,false,correo);
		db.getTransaction().begin();
		db.persist(u);
		db.getTransaction().commit();
		System.out.println(">> DataAccess: createUser=> User: "+ userName +" registered");
		return true;
	}
	
	
	
	
	public Usuario login(String uName, String pass) {
		Usuario u = db.find(Usuario.class,uName);
		if(u==null)return null;
		Calendar calendar = Calendar.getInstance();
	    Date d = calendar.getTime();
		if(!u.getPassword().equals(pass) | u.isBloqueado()==true) {
			db.getTransaction().begin();
			int i=u.getNumIntento();
			u.setNumIntento(i+1);
			Entrada f=null;
			if(i+1==5) {
				u.setBloqueado(true);     //se acaban de bloquear
				f = new Entrada(d,false,u,true,false);
				u.setFecha(d);
			}
			else if(i<5) f = new Entrada(d,false,u,false,false);   //no están bloqueados
			else f=new Entrada(d,false,u,true,false); //estaban bloqueados de antes
			db.persist(f);
			u.addFechas(f);
			db.persist(u);
			db.getTransaction().commit();
			return u;
		}
		db.getTransaction().begin();
		u.setNumIntento(0);
		Entrada f = new Entrada(d,true,u,false,false);
		db.persist(f);
	    u.addFechas(f);
	    u.setFecha(d);
	    db.getTransaction().commit();
		return u;
	}
	
	
	

	public Event createEvent(String inputDescription, Date firstDay) throws UnknownTeamException {
		
		System.out.println(">> DataAccess: createEvent=> description= "+inputDescription+" date="+firstDay.toString());
		TypedQuery<Event>  query = db.createQuery("SELECT e FROM Event e WHERE e.eventDate=?1",Event.class);
		query.setParameter(1, firstDay);
		List<Event> eventos = query.getResultList();
		if(eventos!=null) 
			for(Event e: eventos)if(e.getDescription().equals(inputDescription))return null;
		db.getTransaction().begin();
		Event ev=new Event(inputDescription,firstDay);
		String[] equipos=inputDescription.split("-");
		Equipo local= db.find(Equipo.class,equipos[0]);
		Equipo visitante=db.find(Equipo.class,equipos[1]);
		if(local==null || visitante==null) throw new UnknownTeamException();
		ev.setEquipos(local, visitante);
		
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
		// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	
		for(Pronostico p: ev.getQuestions().get(0).getPronosticos())System.out.println(p.getCuota());
		
		db.getTransaction().commit();
		return ev;
		
	}
	
	public Event createEvent(Equipo local, Equipo visitante, Date firstDay) throws  EventAlreadyExistsException {
		String inputDescription=local.getNombre()+"-"+visitante.getNombre();
		
		System.out.println(">> DataAccess: createEvent=> description= "+inputDescription+" date="+firstDay.toString());
		TypedQuery<Event>  query = db.createQuery("SELECT e FROM Event e WHERE e.eventDate=?1",Event.class);
		query.setParameter(1, firstDay);
		List<Event> eventos = query.getResultList();	
		for(Event e: eventos) {
				if(e.getDescription().equals(inputDescription))throw new EventAlreadyExistsException();
				String [] equipos=e.getDescription().split("-");
				
				
				if(local.getNombre().equals(equipos[0])||local.getNombre().equals(equipos[1])) return null;
				if(visitante.getNombre().equals(equipos[0])||visitante.getNombre().equals(equipos[1])) return null;
		}
		
		db.getTransaction().begin();
		Event ev=new Event(inputDescription,firstDay);
		ev.setEquipos(local, visitante);
		
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
		// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		

		db.getTransaction().commit();
		return ev;
	}

	public List<Pronostico> findPronosticos(Question q) {
		
		TypedQuery<Pronostico>  query = db.createQuery("SELECT FROM Pronostico q WHERE q.getQuestion()=?1",Pronostico.class);
		query.setParameter(1, q);
		List<Pronostico> pronosticos = query.getResultList();
		
		return pronosticos;
}


	public Question createPronostic(String description, Event event,int i,double cuota) {
		Question q = db.find(Question.class, event.getQuest(i).getQuestionNumber());
		List<Pronostico> list=findPronosticos(q);
		for(Pronostico p1:list) {
			if(p1.getPronostico().equals(description))return q=null;
		}
		
			db.getTransaction().begin();
			Pronostico p = q.addPronosticoNoHecho(description, cuota);

			db.persist(p); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			db.getTransaction().commit();
			return q;
			
			
	}
	

	/**
	 * Calcula el procentaje de cada pronostico en una apuesta y la asigna a cada una
	 * @param La apuesta en cuestion
	 */
	public void calcularPorcentajePronostico(Question que) {
		Question q= db.find(Question.class,que);
		Vector<Pronostico> pronos = q.getListPronosticos();
		int numTotal= q.getNºApuesta();
		if(numTotal>0) {
			float div;
			for(Pronostico p: pronos){
				div = ((float) p.getNumUser()/numTotal)*100;
				db.getTransaction().begin();
				p.setPorcentajeApuesta(div);
				db.persist(p);
				db.getTransaction().commit();
			}
		}
				
	}
	
	
	public void añadirApuesta(Usuario user,Bet ap) {
		db.getTransaction().begin();
		Usuario us=db.find(Usuario.class, user.getUserName());
		us.añadirApuesta(ap);
		Bet b=db.find(Bet.class,ap.getBetNumber());
		b.getPronostico().addUser();
		
		db.getTransaction().commit();
		System.out.println(">> DataAccess: createBet=> Usuario= "+user.getUserName() +" Ha apostado " + ap.getBet() +" en " + ap.getPronostico().getPronostico()+" en el evento "+ ap.getPronostico().getQuestion().getEvent().getDescription() );
	}
	
	public List<Bet> getBets (Usuario user){
		
		TypedQuery<Bet>  query = db.createQuery("SELECT FROM Bet b WHERE b.getUsuario()=?1",Bet.class);
		query.setParameter(1, user);
		List<Bet> b  =  query.getResultList();
		return b;
		
	}
 
	public int crearApuesta(Usuario user,double apuesta,Pronostico pos ) {
		Usuario u= db.find(Usuario.class,user.getUserName());
		Pronostico p= db.find(Pronostico.class,pos.getPronosNumber());
		
		Question q=p.getQuestion();
		List<Pronostico> pronosticos = this.findPronosticos(q);
		Vector<Bet> apuestas= u.getApuestas();
	
		for(Bet be: apuestas) {
			for(Pronostico pr: pronosticos) {
				if(be.getPronostico().equals(pr))return 1;
			}
		}
		double d= u.getDinero()-apuesta;
		if(d<0)return 2;
		
		db.getTransaction().begin();
		
		q. addNºApuesta();
		u.setDinero(d);
		user.setDinero(d);
		Bet b=new Bet(p, u, apuesta);
		p.addApuesta(b);
		db.persist(q);
		db.persist(b);

		if(q.getQuestion().equals("1X2")) {
			
			Equipo local=q.getEvent().getEquipos().get(0);
			Equipo visitante=q.getEvent().getEquipos().get(1);
			
			if(pos.getPronostico().equals("1")) {
				local.incrNumUsuariosApuestan();
				local.setDineroApostado(local.getDineroApostado()+apuesta);
			} else if(pos.getPronostico().equals("X")) {
				local.halfIncrNumUsuariosApuestan();
				visitante.halfIncrNumUsuariosApuestan();
				local.setDineroApostado(local.getDineroApostado()+apuesta/2);
				visitante.setDineroApostado(visitante.getDineroApostado()+apuesta/2);
			} else if(pos.getPronostico().equals("2")) {
				visitante.incrNumUsuariosApuestan();
				visitante.setDineroApostado(visitante.getDineroApostado()+apuesta);
			}
			
			if(local != null && visitante !=null) {
				db.persist(local);
				db.persist(visitante);
			}
			
			System.out.println("Local: "+local + " Visitante"+visitante);
			
		}
		
		db.getTransaction().commit();
		
		System.out.println("APUESTA   "+ b);
		
		
		this.añadirApuesta(u,b);
		this.calcularPorcentajePronostico(q);
		return 0;	
	}
	
	public void aumentarDinero(Usuario user, double cant) {
		Usuario us=db.find(Usuario.class, user.getUserName());
		db.getTransaction().begin();
		us.addDinero(cant);
		user.addDinero(cant);
		db.persist(us);
		db.getTransaction().commit();
		System.out.println(">> DataAccess: addDinero=> Usuario= "+user.getUserName() +" Ha aportado " + cant +" se le queda comp " + us.getDinero() );
	}
    


	public Vector<Event> getEventstoClose(Date date) {
	
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate<=?1",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
	 	 for (Event ev:events){
	 	   if(!ev.getisFinished()) {
	 		   System.out.println("Eventos ya terminados :"+ev.toString() + "del dia: " +ev.getEventDate().toString());		 
	 		   res.add(ev);
		   }
		  }
	 	return res;
	}
	public void cerrarApuesta(Pronostico prono) {
		Pronostico pr= db.find(Pronostico.class, prono.getPronosNumber());
		 Question quest = db.find(Question.class, prono.getQuestion().getQuestionNumber());
		db.getTransaction().begin();
		 for(Bet b: pr.getApuestas()) {
			Usuario user= db.find(Usuario.class, b.getUsuario().getUserName());
			user.addDinero(b.getGanancia());
		 }		
		 quest.setIsclosed(true);
		db.getTransaction().commit();
	}
	public Question getQuestion(Event ev,int i) {
		Event e=db.find(Event.class, ev.getEventNumber());
		return e.getQuest(i);
	}
	public Vector<Question> getQuestion(Event ev) {
		Event e=db.find(Event.class, ev.getEventNumber());
		return e.getQuestions();
	}
	public void cerrarEvento(Event ev) {
		Event e=db.find(Event.class, ev.getEventNumber());
		db.getTransaction().begin();
		e.setClosed(true);
		db.persist(e);
		db.getTransaction().commit();
	}

	public void finalizarApuesta(Date date) {
		db.getTransaction().begin();
		for(Event ev: this.getEventstoClose(date)) {
			if(!ev.getisFinished()) {
				ev.setisFinished(false);
				}
		}
		db.getTransaction().commit();
	}
	
	public Question getQuestion(Question q) {
		Question qu=db.find(Question.class, q.getQuestionNumber());
		return qu;
	}
	
	public Usuario getUser(Usuario user) {
		Usuario u= db.find(Usuario.class, user.getUserName());
		return u;
	}
	
	public Comentarios createComent (String text, Event ev, Usuario us, String Date) {
		Usuario user= db.find(Usuario.class, us.getUserName());
		Event eve=db.find(Event.class, ev.getEventNumber());
		db.getTransaction().begin();
		Comentarios cm= new Comentarios(text, eve,  user, Date);
		db.persist(cm);
		eve.addCom(cm);
		user.addCom(cm);
		db.getTransaction().commit();
		return cm;
	}
	
	public Event getEventoActualizado(Event ev) {
		Event eve=db.find(Event.class,ev.getEventNumber() );
		return eve;
	}
	public Vector<Event> getEventosFinalizadosNoCerrados(Date date){
		Vector<Event> eventos = getEventstoClose(date);
		Vector<Event> even = new Vector<Event>();
 		for(Event ev: eventos) {
			if(!ev.isClosed()) {
				even.add(ev);
			}
		}
 		return even;
		
	}
	
	public List<Equipo> obtenerEquipos() {
			TypedQuery<Equipo>  query = db.createQuery("SELECT e FROM Equipo e ",Equipo.class);
			List<Equipo> equipos = query.getResultList();
			for(Equipo eq:equipos)System.out.println(eq);
			return equipos;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public List<Noticia> getNoticiasMonth(Date date) {
		System.out.println(">> DataAccess: getNoticiasMonth");
			
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Noticia> query = db.createQuery("SELECT DISTINCT no FROM Noticia no WHERE no.fechaPubli BETWEEN ?1 and ?2",Noticia.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Noticia> noticias = query.getResultList();
		for(Noticia no: noticias)System.out.println(no);
	 	return noticias;
	}
	
	public Vector<Date> getNoticiasDateMonth(Date date) {
		System.out.println(">> DataAccess: getNoticiasDateMonth");
		Vector<Date> res = new Vector<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT no.fechaPubli FROM Noticia no WHERE no.fechaPubli BETWEEN ?1 and ?2",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
	 	return res;
	}
	
	
	public List<Noticia> getNoticias(Date date) {
		System.out.println(">> DataAccess: getNoticia");
		System.out.println(date);
		TypedQuery<Noticia> query = db.createQuery("SELECT no FROM Noticia no WHERE no.fechaPubli=?1",Noticia.class);   
		query.setParameter(1, date);
		List<Noticia> noticias = query.getResultList();
		for(Noticia no: noticias)System.out.println(no);
	 	return noticias;
	}
	
	public List<Noticia> getAllNoticias() {
		System.out.println(">> DataAccess: getAllNoticias");
		TypedQuery<Noticia> query = db.createQuery("SELECT no FROM Noticia no ",Noticia.class);   
		List<Noticia> noticias = query.getResultList();
		Collections.reverse(noticias);
	 	for(Noticia no: noticias)System.out.println(no);
	 	return noticias;
	}
	
	public void eliminarNoticia(Noticia no) {
		Noticia not=db.find(Noticia.class, no.getNumNoticia());
		db.getTransaction().begin();
		
		db.persist(not);
		db.getTransaction().commit();
	}
	
	public Noticia createNoticia(String titulo, String subTitulo, String texto, String nomAutor, String nomMedio) {
		Calendar today = Calendar.getInstance();
		int day=today.get(Calendar.DAY_OF_MONTH);
		int month=today.get(Calendar.MONTH);
		month+=1;
		int year=today.get(Calendar.YEAR);
		Date data = UtilDate.newDate(year, month, day);
		TypedQuery<Noticia>  query = db.createQuery("SELECT no FROM Noticia no WHERE no.fechaPubli=?1",Noticia.class);
		query.setParameter(1, data);
		List<Noticia> noticias = query.getResultList();
		if(noticias!=null) 
			for(Noticia no: noticias)if(no.getTexto().equals(texto))return null;
		db.getTransaction().begin();
		Noticia not= new Noticia(titulo, subTitulo, texto, nomAutor, nomMedio, data);
		db.persist(not);
		db.getTransaction().commit();
		return not;
	}
	
	public List<String> getAllNoticiasAuthor() {
		System.out.println(">> DataAccess: getAllNoticiasAuthor");
		TypedQuery<String> query = db.createQuery("SELECT DISTINCT no.nomAutor FROM Noticia no ",String.class);   
		List<String> autores = query.getResultList();
	 	for(String au: autores)System.out.println(au);
	 	return autores;
	}
	
	public List<Noticia> getNoticiasAuthor(String aut) {
		System.out.println(">> DataAccess: getNoticiasAuthor");
		TypedQuery<Noticia> query = db.createQuery("SELECT DISTINCT no FROM Noticia no WHERE no.getNomAutor()=?1 ",Noticia.class);
		query.setParameter(1, aut);
		List<Noticia> noticias = query.getResultList();
		Collections.reverse(noticias);
	 	for(Noticia no: noticias)System.out.println(no);
	 	return noticias;
	}
	
	public List<String> getAllNoticiasMedio() {
		System.out.println(">> DataAccess: getAllNoticiasMedio");
		TypedQuery<String> query = db.createQuery("SELECT DISTINCT no.nomMedio FROM Noticia no ",String.class);   
		List<String> medios = query.getResultList();
	 	for(String me: medios)System.out.println(me);
	 	return medios;
	}
	
	public List<Noticia> getNoticiasMedio(String med) {
		System.out.println(">> DataAccess: getNoticiasMedios");
		TypedQuery<Noticia> query = db.createQuery("SELECT DISTINCT no FROM Noticia no WHERE no.getNomMedio()=?1 ",Noticia.class);
		query.setParameter(1, med);
		List<Noticia> noticias = query.getResultList();
		Collections.reverse(noticias);
	 	for(Noticia no: noticias)System.out.println(no);
	 	return noticias;
	}
	
	public List<Equipo> getAllEquipos(int mode) {
		System.out.println(">> DataAccess: getAllEquiposPorUsuarios");
		String atributo;
		switch(mode) {
		case 0:
			atributo="dineroApostado";
			break;
		case 1:
			atributo="numUsuariosApuestan";
			break;
		default:
			atributo="nombre";
			break;
					
		}
		
		TypedQuery<Equipo> query = db.createQuery("select eq from Equipo eq order by "+ atributo +" desc",Equipo.class);
		
		List<Equipo> equipos = query.getResultList();
	 	for(Equipo eq: equipos)System.out.println(eq);
	 	return equipos;
	}
	public List<Usuario> getUsuarios(String s, String s2) {
		TypedQuery<Usuario> query= db.createQuery("SELECT us FROM Usuario us WHERE us.isBloqueado()=?1 and us.isAdmin()=?2",Usuario.class); 
		if(s.equals("Bloqueados")) query.setParameter(1, true); 
		if(s.equals("No Bloqueados")) query.setParameter(1,false);
		if(s2.equals("Admin"))query.setParameter(2, true); 
		if(s2.equals("Usuarios"))  query.setParameter(2, false);  
		List<Usuario> u  =  query.getResultList();
		return u;
		
	}

	public Usuario getUsuario(String s, String s2, int i) {
		TypedQuery<Usuario> query= db.createQuery("SELECT us FROM Usuario us WHERE us.isBloqueado()=?1 and us.isAdmin()=?2",Usuario.class); 
		if(s.equals("Bloqueados")) query.setParameter(1, true); 
		if(s.equals("No Bloqueados")) query.setParameter(1,false);
		if(s2.equals("Admin"))query.setParameter(2, true); 
		if(s2.equals("Usuarios"))  query.setParameter(2, false);  
		List<Usuario> u  =  query.getResultList();

		return u.get(i);

	}

	public void desBloquear(String s,Usuario u) {
		Usuario us=db.find(Usuario.class,u);
		Calendar calendar = Calendar.getInstance();
	    Date d = calendar.getTime();
	    Entrada f =null;
		db.getTransaction().begin();
		if(us.isBloqueado()) {
			us.setBloqueado(false);
			Vector<Entrada> e=us.getFechas();
			for(int i=e.size()-1; i>=0; i--) {
				Entrada en = e.get(i);
				if(!en.isBloqueado() & en.isExito()) {
					us.setFecha(en.getFecha());
					break;
				}
			}
		f = new Entrada(d,false,us,false,true); 
		}
		else {
			us.setBloqueado(true);
			f = new Entrada(d,false,us,true,true); 
			us.setFecha(d);
		}
		us.addFechas(f);
		db.persist(f);
		db.persist(us);
		db.getTransaction().commit();
		
	}
	
	
	public void crearLiga(String nombre, int numEquipos) throws LeagueAlreadyExist, LessThanMinimumTeamException {
		
		if(numEquipos<2) throw new LessThanMinimumTeamException();
		List<Liga> ligas=this.getAllLigas();
		for(Liga lig:ligas)
			if(lig.getNombre().equals(nombre)) throw new LeagueAlreadyExist();
		db.getTransaction().begin();
		Liga liga= new Liga(nombre, numEquipos);
		db.persist(liga);
		db.getTransaction().commit();
		System.out.println(">> DataAccess: crearLiga=> Nombre= "+nombre +" Numero máximo de equipos " + numEquipos);
	}
	
	public void anadirEquipoALiga(String nombreEquipo, Liga liga) throws TeamAlreadyExistsException, MaximumNumberOfTeamsReached {
		Liga l=db.find(Liga.class,liga.getNombre());
		if(l.getEquipos().size()>=l.getNumEquipos()) throw new MaximumNumberOfTeamsReached();
		for(Equipo eq: l.getEquipos())
			if(nombreEquipo.equals(eq.getNombre())) throw new TeamAlreadyExistsException();
		Equipo equipo=new Equipo(nombreEquipo, l);
		db.getTransaction().begin();
		db.persist(equipo);
		db.persist(l);
		db.getTransaction().commit();
		System.out.println(">> DataAccess: anadirEquipoALiga=> Equipo: "+equipo.getNombre() +" a liga: " + liga.getNombre());
	}
	
	public void eliminarEquipoDeLiga(String nombreEquipo, Liga liga) {
		Liga l=db.find(Liga.class,liga.getNombre());
		Equipo equipo=db.find(Equipo.class,nombreEquipo);

		db.getTransaction().begin();
		l.eliminarEquipo(equipo);
		db.createQuery("DELETE FROM Equipo eq WHERE Eq.nombre='"+equipo.getNombre()+"'").executeUpdate();
		db.persist(l);
		db.getTransaction().commit();
		System.out.println(">> DataAccess: eliminarEquipoALiga=> Equipo: "+equipo.getNombre() +" de liga: " + liga.getNombre());
		
		
	}

	public List<Equipo> getEquiposPorLiga(int mode, Liga liga) {
		System.out.println(">> DataAccess: getEquiposPorLiga");
		String atributo;
		String nombreLiga=liga.getNombre();
		String orden=" desc";
		switch(mode) {
		case 0:
			atributo="dineroApostado";
			break;
		case 1:
			atributo="numUsuariosApuestan";
			break;
		default:
			atributo="nombre";
			orden=" asc";
			break;
					
		}
		
		TypedQuery<Equipo> query = db.createQuery("select eq from Equipo eq where eq.liga.nombre='"+nombreLiga+"' order by "+ atributo + orden,Equipo.class);
		
		List<Equipo> equipos = query.getResultList();
	 	for(Equipo eq: equipos)System.out.println(eq);
	 	return equipos;
	}
	
	public List<Liga> getAllLigas() {
		System.out.println(">> DataAccess: getAllLigas");
		TypedQuery<Liga> query = db.createQuery("select l from Liga l  order by l.nombre asc",Liga.class);
		List<Liga> ligas = query.getResultList();
	 	for(Liga lig: ligas)System.out.println(lig);
	 	return ligas;
	}
	
	
	public List<Equipo> getEquiposPorLiga(int mode, String nombreLiga) {
		System.out.println(">> DataAccess: getEquiposPorLiga");
		String atributo;
		String orden=" desc";
		switch(mode) {
		case 0:
			atributo="dineroApostado";
			break;
		case 1:
			atributo="numUsuariosApuestan";
			break;
		default:
			atributo="nombre";
			orden=" asc";
			break;
					
		}
		
		TypedQuery<Equipo> query = db.createQuery("select eq from Equipo eq where eq.liga.nombre='"+nombreLiga+"' order by "+ atributo + orden,Equipo.class);
		
		List<Equipo> equipos = query.getResultList();
	 	for(Equipo eq: equipos)System.out.println(eq);
	 	return equipos;
	}
	
	
	
}
