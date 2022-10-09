import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Equipo;
import domain.Event;
import domain.Question;
import exceptions.EventAlreadyExistsException;
import exceptions.EventFinishedException;
import exceptions.QuestionAlreadyExist;
import exceptions.TeamAlreadyPlaysInDayException;
import exceptions.UnknownTeamException;
import test.businessLogic.TestFacadeImplementation;

public class CreateEventInt {
	 static BLFacadeImplementation sut;
	 static TestFacadeImplementation testBL;


	private Event ev;
	private Event ev2;
	private Equipo e1;
	private	Equipo e2;
	private	Equipo e3;
	private	Equipo e4;
	@BeforeClass
	
	public static void setUpClass() {
		//sut= new BLFacadeImplementation();
		
		// you can parametrize the DataAccess used by BLFacadeImplementation
		//DataAccess da= new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));
		DataAccess da= new DataAccess();

		sut=new BLFacadeImplementation(da);
		
		testBL= new TestFacadeImplementation();
	}
	
	@Test
	//sut.CreateEvent:  No hay ningun evento en ese diá. 
	public void test1() throws EventFinishedException, TeamAlreadyPlaysInDayException, UnknownTeamException {
		try {
			
			//define paramaters
			String nE1 = "Barcelona";
			String nE2 = "Atlético de Madrid";
			Date dt =UtilDate.newDate(2024,4,20);
		
			//configure the state of the system (create object in the dabatase)
			
			e1 = testBL.getEquipoById(nE1);
			e2 = testBL.getEquipoById(nE2);
				
			
			//invoke System Under Test (sut)  
			 ev=sut.createEvent(e1, e2, dt);
		
	
			
			assertTrue(ev!=null);
			assertTrue(ev.getDescription().compareTo(nE1+"-"+nE2)==0);
		
			
			//q datubasean dago
			
			boolean exist = testBL.existEvent(ev);
				
			assertTrue(exist);
			
			
		   } catch (EventAlreadyExistsException e) {
		
			fail("No tiene que fallar");
			} finally {
				  //Remove the created objects in the database (cascade removing)   
				  
				
		          boolean b=testBL.removeEvent(ev);
		          
		          System.out.println("Finally "+b);          
		        }
		    
		   }
	@Test
	//sut.createEvent: El evento ya existe
		public void test2() throws EventFinishedException, TeamAlreadyPlaysInDayException, UnknownTeamException {
			try {
				
				//define paramaters
				String nE1 = "Barcelona";
				String nE2 = "Atlético de Madrid";
				Date dt =UtilDate.newDate(2024,4,20);
			
				//configure the state of the system (create object in the dabatase)
				
				e1 = testBL.getEquipoById(nE1);
				e2 = testBL.getEquipoById(nE2);
						
				
				//invoke System Under Test (sut)        
				ev=sut.createEvent(e1, e2, dt);
	
				Event ev3=sut.createEvent(e1, e2, dt);
				
				
			   } catch (EventAlreadyExistsException e) {
				//Si llega aqui esque esta bien;
				   assertTrue(true);
				} finally {
					  //Remove the created objects in the database (cascade removing)   
					
			          boolean b=testBL.removeEvent(ev);
			          ;
			          System.out.println("Finally2 "+b);          
			        }
			   }
	@Test 
	
	//sut.createEvent: El equipo ya es local de otro evento
		public void test3() throws EventFinishedException, EventAlreadyExistsException, UnknownTeamException {
			try {
				
				//define paramaters
				String nE1 = "Barcelona";
				String nE2 = "Atlético de Madrid";
				String nE3 = "Cádiz";
				Date dt =UtilDate.newDate(2024,4,20);
			
				//configure the state of the system (create object in the dabatase)
				
				e1 = testBL.getEquipoById(nE1);
				e2 = testBL.getEquipoById(nE2);
				e3 = testBL.getEquipoById(nE3);
						
				
				//invoke System Under Test (sut)        
				ev=sut.createEvent(e1, e2, dt);
	            ev2= sut.createEvent(e3,e1, dt);
				fail();
				
			   } catch (TeamAlreadyPlaysInDayException e) {
				 
				   assertTrue(true);
				} finally {
					  //Remove the created objects in the database (cascade removing)   
					
			          boolean b=testBL.removeEvent(ev);
			          
			          System.out.println("Finally3 "+b);          
			        }
			   }
	
	@Test
	//sut.createEvent: El equipo local null
		public void test4() {
			try {
				 
				//define paramaters
				String nE1 = "Barcelona";
				String nE2 = "Atlético de Madrid";

				Date dt =UtilDate.newDate(2024,4,20);
			
				//configure the state of the system (create object in the dabatase)
				
				e1 = testBL.getEquipoById(nE1);
				e2 = testBL.getEquipoById(nE2);
	
				//invoke System Under Test (sut)        
				ev=sut.createEvent(null, e2, dt);
	           fail();
				
				
			   } catch (Exception e) {
				   //assertTrue(true);
				   fail("Tiene que fallar");
				} finally {
					  //Remove the created objects in the database (cascade removing)      
			        }
			   }
	
	@Test
	//sut.createEvent: Equipo visitante null
		public void test5() {
			try {
				
				//define paramaters
				String nE1 = "Barcelona";
				String nE2 = "Atlético de Madrid";
	
				Date dt =UtilDate.newDate(2024,4,20);
			
				//configure the state of the system (create object in the dabatase)
			
				e1 = testBL.getEquipoById(nE1);
				e2 = testBL.getEquipoById(nE2);
		
					
				
				//invoke System Under Test (sut)        
				ev=sut.createEvent(e1,null, dt);
	            fail();

			   } catch (Exception e) {
				 //assertTrue(true);
				fail("Tiene que fallar");
				} finally {
					  //Remove the created objects in the database (cascade removing)   
			        }
			   }
	@Test
	//sut.createEvent: fecha null
		public void test6() {
			try {
				
				//define paramaters
				String nE1 = "Barcelona";
				String nE2 = "Atlético de Madrid";
	
				Date dt =UtilDate.newDate(2024,4,20);
			
				//configure the state of the system (create object in the dabatase)
				
				e1 = testBL.getEquipoById(nE1);
				e2 = testBL.getEquipoById(nE2);
		
						
				
				//invoke System Under Test (sut)        
				ev=sut.createEvent(e1,e2, null);
	            fail();    
				
			   } catch (Exception e) {
				 //assertTrue(true);
				fail("Tiene que fallar");
				} finally {
					  //Remove the created objects in the database (cascade removing)   
			        }
			   }
	
	@Test
	//sut.createEvent: fecha anterior
		public void test7() throws EventAlreadyExistsException, TeamAlreadyPlaysInDayException, UnknownTeamException {
			try {
				
				//define paramaters
				String nE1 = "Barcelona";
				String nE2 = "Atlético de Madrid";
	
				Date dt =UtilDate.newDate(2022,4,20);
			
				//configure the state of the system (create object in the dabatase)
				
				e1 = testBL.getEquipoById(nE1);
				e2 = testBL.getEquipoById(nE2);
		
			
				//invoke System Under Test (sut)        
				ev=sut.createEvent(e1,e2, dt);
	            fail();    
				
			   } catch (EventFinishedException e) {
				   assertTrue(true);
				
				} finally {
					  //Remove the created objects in the database (cascade removing)   
			        }
			   }
	

}
