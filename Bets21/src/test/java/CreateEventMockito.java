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
import domain.Question;
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
public class CreateEventMockito {
    DataAccess dataAccess=Mockito.mock(DataAccess.class);
   
	
	@InjectMocks
	 BLFacade sut=new BLFacadeImplementation(dataAccess);
	//sut.createQuestion:  The event has one question with a queryText. 

	private Event ev;
	private Event ev2;
	private Equipo e1;
	private	Equipo e2;
	private	Equipo e3;
	private	Equipo e4;

	
	@Test
	//sut.CreateEvent:  No hay ningun evento en ese diá. 
	public void test1() {
		try {
			
			//define paramaters
			String nE1 = "Barcelona";
			String nE2 = "Atlético de Madrid";
			Liga liga1 = new Liga("Lg1",4);
			Equipo e1 = new Equipo(nE1 ,liga1);
			Equipo e2 = new Equipo(nE2 ,liga1);
			Date dt =UtilDate.newDate(2024,4,20);
		
			//configure the state of the system (create object in the dabatase)
			

				
			
			//invoke System Under Test (sut)  
			 Mockito.doReturn(new Event(nE1+"-"+nE2,dt))
			 .when(dataAccess)
			 .createEvent(e1, e2, dt);
			 
			 ev=sut.createEvent(e1, e2, dt);
			 
	
			
			assertTrue(ev!=null);
			assertTrue(ev.getDescription().compareTo(nE1+"-"+nE2)==0);
		
			
			//q datubasean dago
		
		   } catch (Exception e) {
		
			fail("No tiene que fallar");
			} finally {
				  //Remove the created objects in the database (cascade removing)   
		          System.out.println("Finally1 ");          
		        }
		    
		   }
	@Test
	//sut.createEvent: El evento ya existe
		public void test2() throws EventFinishedException, TeamAlreadyPlaysInDayException, UnknownTeamException {
			try {
				
				//define paramaters
				String nE1 = "Barcelona";
				String nE2 = "Atlético de Madrid";
				Liga liga1 = new Liga("Lg1",4);
				Equipo e1 = new Equipo(nE1 ,liga1);
				Equipo e2 = new Equipo(nE2 ,liga1);
				Date dt =UtilDate.newDate(2024,4,20);
				//configure the state of the system (create object in the dabatase)
				

						
				
				//invoke System Under Test (sut)   
				Mockito.when(dataAccess.createEvent(e1, e2, dt)).thenThrow(new EventAlreadyExistsException());

				ev=sut.createEvent(e1, e2, dt);
			
			   } catch (EventAlreadyExistsException e) {
				//Si llega aqui esque esta bien;
				   assertTrue(true);
				} finally {
					  //Remove the created objects in the database (cascade removing)   

			          System.out.println("Finally2 ");          
			        }
			   }
	
	@Test
	//sut.createEvent: El equipo ya es local de otro evento
		public void test3() throws EventFinishedException, EventAlreadyExistsException, UnknownTeamException {
			try {
				
				//define paramaters
				String nE1 = "Barcelona";
				String nE2 = "Atlético de Madrid";
				Liga liga1 = new Liga("Lg1",4);
				Equipo e1 = new Equipo(nE1 ,liga1);
				Equipo e2 = new Equipo(nE2 ,liga1);
				Date dt =UtilDate.newDate(2024,4,20);
				//configure the state of the system (create object in the dabatase)
				
	 
						
				
				//invoke System Under Test (sut)   
	
				ev=sut.createEvent(e1, e2, dt); 
			
			   } catch (TeamAlreadyPlaysInDayException e) {
				//Si llega aqui esque esta bien;
				   assertTrue(true);
				} finally {
					  //Remove the created objects in the database (cascade removing)   
	
			          System.out.println("Finally3 ");          
			        }
			   }
	@Test
	//sut.createEvent: El equipo local null
		public void test4() throws EventFinishedException,  EventAlreadyExistsException {
			try {
				
				//define paramaters
				String nE1 = "Barcelona";
				String nE2 = "Atlético de Madrid";
				Liga liga1 = new Liga("Lg1",4);
				Equipo e1 = new Equipo(nE1 ,liga1);
				Equipo e2 = new Equipo(nE2 ,liga1);
				Date dt =UtilDate.newDate(2024,4,20);
				//configure the state of the system (create object in the dabatase)
				
	
						
				
				//invoke System Under Test (sut)   
				Mockito.when(dataAccess.createEvent(null, e2, dt)).thenThrow(new Exception());
	
				ev=sut.createEvent(e1, e2, dt);
			
			   } catch (Exception e) {
				//Si llega aqui esque esta bien;
				   fail("Esta bien, si sale fail");
				} finally {
					  //Remove the created objects in the database (cascade removing)   
	
			          System.out.println("Finally4 ");          
			        }
			 }
	
	@Test
	//sut.createEvent: El equipo visitante null
		public void test5() throws EventFinishedException, EventAlreadyExistsException {
			try {
				
				//define paramaters
				String nE1 = "Barcelona";
				String nE2 = "Atlético de Madrid";
				Liga liga1 = new Liga("Lg1",4);
				Equipo e1 = new Equipo(nE1 ,liga1);
				Equipo e2 = new Equipo(nE2 ,liga1);
				Date dt =UtilDate.newDate(2024,4,20);
				//configure the state of the system (create object in the dabatase)
				
	
						
				
				//invoke System Under Test (sut)   
				Mockito.when(dataAccess.createEvent(e1, null, dt)).thenThrow(new Exception());
	
				ev=sut.createEvent(e1, null, dt);
			
			   } catch (Exception e) {
				//Si llega aqui esque esta bien;
				   fail("Esta bien, si sale fail");
				} finally {
					  //Remove the created objects in the database (cascade removing)   
	
			          System.out.println("Finally3 ");          
			        }
			   }
	
	@Test
	//sut.createEvent: La fecha null
		public void test6() throws EventFinishedException, EventAlreadyExistsException {
			try {
				
				//define paramaters
				String nE1 = "Barcelona";
				String nE2 = "Atlético de Madrid";
				Liga liga1 = new Liga("Lg1",4);
				Equipo e1 = new Equipo(nE1 ,liga1);
				Equipo e2 = new Equipo(nE2 ,liga1);
				Date dt =UtilDate.newDate(2024,4,20);
				//configure the state of the system (create object in the dabatase)
				
	
						
				
				//invoke System Under Test (sut)   
				Mockito.when(dataAccess.createEvent(e1, e2, dt)).thenThrow(new Exception());
	
				ev=sut.createEvent(e1, e2, null);
			
			   } catch (Exception e) {
				//Si llega aqui esque esta bien;
				   fail("Esta bien, si sale fail");
				} finally {
					  //Remove the created objects in the database (cascade removing)   
	
			          System.out.println("Finally4 ");          
			        }
			   }
	@Test
	//sut.createEvent:La fecha anterior
		public void test7() throws EventFinishedException, EventAlreadyExistsException, TeamAlreadyPlaysInDayException, UnknownTeamException {
			try {
				
				//define paramaters
				String nE1 = "Barcelona";
				String nE2 = "Atlético de Madrid";
				Liga liga1 = new Liga("Lg1",4);
				Equipo e1 = new Equipo(nE1 ,liga1);
				Equipo e2 = new Equipo(nE2 ,liga1);
				Date dt =UtilDate.newDate(2022,2,20);
				//configure the state of the system (create object in the dabatase)
				
	
						
				
				//invoke System Under Test (sut)   
				 Mockito.doReturn(new Event(nE1+"-"+nE2,dt))
				 .when(dataAccess)
				 .createEvent(e1, e2, dt);
	
				ev=sut.createEvent(e1, e2, dt);
			    fail(); 
			   } catch (EventFinishedException e) {
				//Si llega aqui esque esta bien;
				   assertTrue(true);
				} finally {
					  //Remove the created objects in the database (cascade removing)   
	
			          System.out.println("Finally7 ");          
			        }
			   }
}
