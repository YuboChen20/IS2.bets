import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Equipo;
import domain.Event;
import domain.Liga;
import domain.Pronostico;
import domain.Question;
import domain.Usuario;
import exceptions.EventAlreadyExistsException;
import exceptions.EventFinishedException;
import exceptions.QuestionAlreadyExist;
import exceptions.TeamAlreadyPlaysInDayException;
import exceptions.UnknownTeamException;
import test.businessLogic.TestFacadeImplementation;

import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CrearApuestaMockito {
	@Mock
    DataAccess dataAccess=Mockito.mock(DataAccess.class);
    Event mockedEvent=Mockito.mock(Event.class);
		
	@InjectMocks
	 BLFacade sut=new BLFacadeImplementation(dataAccess);

	
	
	@Test
	//sut.createQuestion:  The event has NOT a question with a queryText.
	public void test1() {
			try {
				//define paramaters
				Liga ligaSantander=new Liga("Liga Santander",20);
				Equipo atleticoDeMadrid= new Equipo("Atlético de Madrid", ligaSantander);
				Equipo atlheticDeBilbao= new Equipo("Athletic de Bilbao", ligaSantander);
				Event ev1=new Event(1, "Atlético de Madrid-Athletic de Bilbao", UtilDate.newDate(1,1,17), atleticoDeMadrid, atlheticDeBilbao);
				Usuario user= new Usuario("Pepe","dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");
				
				//configure Mock
				Mockito.doReturn(0).when(dataAccess).crearApuesta(user, 10, ev1.getQuest(0).getPron(0));

				

				//invoke System Under Test (sut) 
				int num = sut.crearApuesta(user, 10, ev1.getQuest(0).getPron(0));
				
				//verify the results
				//Mockito.verify(dataAccess,Mockito.times(1)).createQuestion(Mockito.any(Event.class),Mockito.any(String.class), Mockito.any(Integer.class));

				
				Mockito.verify(dataAccess,Mockito.times(1)).crearApuesta(user, 10, ev1.getQuest(0).getPron(0));


				assertEquals(0,num);
			   }catch (Exception e) {
				    fail();
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }
	
	@Test
	//sut.createQuestion:  The event has NOT a question with a queryText.
	public void test2() {
			try {
				//define paramaters
				Liga ligaSantander=new Liga("Liga Santander",20);
				Equipo atleticoDeMadrid= new Equipo("Atlético de Madrid", ligaSantander);
				Equipo atlheticDeBilbao= new Equipo("Athletic de Bilbao", ligaSantander);
				Event ev1=new Event(1, "Atlético de Madrid-Athletic de Bilbao", UtilDate.newDate(1,1,17), atleticoDeMadrid, atlheticDeBilbao);
				Usuario user= new Usuario("Pepe","dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");
				
				//configure Mock
				Mockito.doReturn(0).when(dataAccess).crearApuesta(user, 10, ev1.getQuest(0).getPron(1));

				

				//invoke System Under Test (sut) 
				int num = sut.crearApuesta(user, 10, ev1.getQuest(0).getPron(1));
				
				//verify the results
				//Mockito.verify(dataAccess,Mockito.times(1)).createQuestion(Mockito.any(Event.class),Mockito.any(String.class), Mockito.any(Integer.class));

				
				Mockito.verify(dataAccess,Mockito.times(1)).crearApuesta(user, 10, ev1.getQuest(0).getPron(1));


				assertEquals(0,num);
			   }catch (Exception e) {
				    fail();
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }
	
	@Test
	//sut.createQuestion:  The event has NOT a question with a queryText.
	public void test3() {
			try {
				//define paramaters
				Liga ligaSantander=new Liga("Liga Santander",20);
				Equipo atleticoDeMadrid= new Equipo("Atlético de Madrid", ligaSantander);
				Equipo atlheticDeBilbao= new Equipo("Athletic de Bilbao", ligaSantander);
				Event ev1=new Event(1, "Atlético de Madrid-Athletic de Bilbao", UtilDate.newDate(1,1,17), atleticoDeMadrid, atlheticDeBilbao);
				Usuario user= new Usuario("Pepe","dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");
				
				//configure Mock
				Mockito.doReturn(0).when(dataAccess).crearApuesta(user, 10, ev1.getQuest(0).getPron(2));

				

				//invoke System Under Test (sut) 
				int num = sut.crearApuesta(user, 10, ev1.getQuest(0).getPron(2));
				
				//verify the results
				//Mockito.verify(dataAccess,Mockito.times(1)).createQuestion(Mockito.any(Event.class),Mockito.any(String.class), Mockito.any(Integer.class));

				
				Mockito.verify(dataAccess,Mockito.times(1)).crearApuesta(user, 10, ev1.getQuest(0).getPron(2));


				assertEquals(0,num);
			   }catch (Exception e) {
				    fail();
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }
	
	@Test
	//sut.createQuestion:  The event has NOT a question with a queryText.
	public void test4() {
			try {
				//define paramaters
				Liga ligaSantander=new Liga("Liga Santander",20);
				Equipo atleticoDeMadrid= new Equipo("Atlético de Madrid", ligaSantander);
				Equipo atlheticDeBilbao= new Equipo("Athletic de Bilbao", ligaSantander);
				Event ev1=new Event(1, "Atlético de Madrid-Athletic de Bilbao", UtilDate.newDate(1,1,17), atleticoDeMadrid, atlheticDeBilbao);
				Question q1=ev1.addQuestion("dfddgd",1);
				Pronostico p1=new Pronostico("fg",q1,1.2);
				q1.addPronostico(p1);
				Usuario user= new Usuario("Pepe","dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");
				
				//configure Mock
				Mockito.doReturn(0).when(dataAccess).crearApuesta(user, 10, p1);

				

				//invoke System Under Test (sut) 
				int num = sut.crearApuesta(user, 10, p1);
				
				//verify the results
				//Mockito.verify(dataAccess,Mockito.times(1)).createQuestion(Mockito.any(Event.class),Mockito.any(String.class), Mockito.any(Integer.class));

				
				Mockito.verify(dataAccess,Mockito.times(1)).crearApuesta(user, 10, p1);


				assertEquals(0,num);
			   }catch (Exception e) {
				    fail();
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }
	
	@Test
	//sut.createQuestion:  The event has NOT a question with a queryText.
	public void test5() {
			try {
				//define paramaters
				Liga ligaSantander=new Liga("Liga Santander",20);
				Equipo atleticoDeMadrid= new Equipo("Atlético de Madrid", ligaSantander);
				Equipo atlheticDeBilbao= new Equipo("Athletic de Bilbao", ligaSantander);
				Event ev1=new Event(1, "Atlético de Madrid-Athletic de Bilbao", UtilDate.newDate(1,1,17), atleticoDeMadrid, atlheticDeBilbao);
				Question q1=ev1.addQuestion("dfddgd",1);
				Pronostico p1=new Pronostico("fg",q1,1.2);
				q1.addPronostico(p1);
				Usuario user= new Usuario(null,"dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");
				
				//configure Mock
				Mockito.when(dataAccess.crearApuesta(user,10,p1)).thenThrow(new Exception());
				


				//invoke System Under Test (sut) 
				int num = sut.crearApuesta(user, 10, p1);
				
				//verify the results
				//Mockito.verify(dataAccess,Mockito.times(1)).createQuestion(Mockito.any(Event.class),Mockito.any(String.class), Mockito.any(Integer.class));

				
				Mockito.verify(dataAccess,Mockito.times(1)).crearApuesta(user, 10, p1);


				fail();
			   }catch (Exception e) {
				    assertTrue(true);
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }
	@Test
	//sut.createQuestion:  The event has NOT a question with a queryText.
	public void test6() {
			try {
				//define paramaters
				Liga ligaSantander=new Liga("Liga Santander",20);
				Equipo atleticoDeMadrid= new Equipo("Atlético de Madrid", ligaSantander);
				Equipo atlheticDeBilbao= new Equipo("Athletic de Bilbao", ligaSantander);
				Event ev1=new Event(1, "Atlético de Madrid-Athletic de Bilbao", UtilDate.newDate(1,1,17), atleticoDeMadrid, atlheticDeBilbao);
				Usuario user= new Usuario(null,"dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");
				
				//configure Mock
				Mockito.when(dataAccess.crearApuesta(user,10,null)).thenThrow(new Exception());


				//invoke System Under Test (sut) 
				int num = sut.crearApuesta(user, 10, null);
				
				//verify the results
				//Mockito.verify(dataAccess,Mockito.times(1)).createQuestion(Mockito.any(Event.class),Mockito.any(String.class), Mockito.any(Integer.class));

				
				Mockito.verify(dataAccess,Mockito.times(1)).crearApuesta(user, 10, null);


				fail();
			   }catch (Exception e) {
				   assertTrue(true);
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }
	
	@Test
	//sut.createQuestion:  The event has NOT a question with a queryText.
	public void test7() {
			try {
				//define paramaters
				Liga ligaSantander=new Liga("Liga Santander",20);
				Equipo atleticoDeMadrid= new Equipo("Atlético de Madrid", ligaSantander);
				Equipo atlheticDeBilbao= new Equipo("Athletic de Bilbao", ligaSantander);
				Event ev1=new Event(1, "Atlético de Madrid-Athletic de Bilbao", UtilDate.newDate(1,1,17), atleticoDeMadrid, atlheticDeBilbao);
				Usuario user= new Usuario(null,"dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");
				user.setDinero(-1);
				
				//configure Mock
				Mockito.when(dataAccess.crearApuesta(user, 10, ev1.getQuest(0).getPron(0))).thenThrow(new Exception());


				//invoke System Under Test (sut) 
				int num = sut.crearApuesta(user, 10, ev1.getQuest(0).getPron(0));
				
				//verify the results
				//Mockito.verify(dataAccess,Mockito.times(1)).createQuestion(Mockito.any(Event.class),Mockito.any(String.class), Mockito.any(Integer.class));

				
				Mockito.verify(dataAccess,Mockito.times(1)).crearApuesta(user, 10, ev1.getQuest(0).getPron(0));


				fail();
			   }catch (Exception e) {
				   assertTrue(true);
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }
	
	@Test
	//sut.createQuestion:  The event has NOT a question with a queryText.
	public void test8() {
			try {
				//define paramaters
				Liga ligaSantander=new Liga("Liga Santander",20);
				Equipo atleticoDeMadrid= new Equipo("Atlético de Madrid", ligaSantander);
				Equipo atlheticDeBilbao= new Equipo("Athletic de Bilbao", ligaSantander);
				Event ev1=new Event(1, "Atlético de Madrid-Athletic de Bilbao", UtilDate.newDate(1,1,17), atleticoDeMadrid, atlheticDeBilbao);
				Usuario user= new Usuario(null,"dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");
				double dinero=user.getDinero()+30;
				//configure Mock
				Mockito.when(dataAccess.crearApuesta(user,dinero,ev1.getQuest(0).getPron(0))).thenThrow(new Exception());


				//invoke System Under Test (sut) 
				int num = sut.crearApuesta(user, dinero, ev1.getQuest(0).getPron(0));
				
				//verify the results
				//Mockito.verify(dataAccess,Mockito.times(1)).createQuestion(Mockito.any(Event.class),Mockito.any(String.class), Mockito.any(Integer.class));

				
				Mockito.verify(dataAccess,Mockito.times(1)).crearApuesta(user, 10, ev1.getQuest(0).getPron(0));


				fail();
			   }catch (Exception e) {
				   assertTrue(true);
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }
	
	@Test
	//sut.createQuestion:  The event has NOT a question with a queryText.
	public void test9() {
			try {
				//define paramaters
				Liga ligaSantander=new Liga("Liga Santander",20);
				Equipo atleticoDeMadrid= new Equipo("Atlético de Madrid", ligaSantander);
				Equipo atlheticDeBilbao= new Equipo("Athletic de Bilbao", ligaSantander);
				Event ev1=new Event(1, "Atlético de Madrid-Athletic de Bilbao", UtilDate.newDate(1,1,17), atleticoDeMadrid, atlheticDeBilbao);
				Usuario user= new Usuario(null,"dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");
				
				//configure Mock
				Mockito.when(dataAccess.crearApuesta(user,10,ev1.getQuest(0).getPron(0))).thenThrow(new Exception());


				//invoke System Under Test (sut) 
				int num = sut.crearApuesta(user, 10, ev1.getQuest(0).getPron(0));
				
				//verify the results
				//Mockito.verify(dataAccess,Mockito.times(1)).createQuestion(Mockito.any(Event.class),Mockito.any(String.class), Mockito.any(Integer.class));

				
				Mockito.verify(dataAccess,Mockito.times(1)).crearApuesta(user, 10, ev1.getQuest(0).getPron(2));


				fail();
			   }catch (Exception e) {
				   assertTrue(true);
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }
	
	
	
	

}
