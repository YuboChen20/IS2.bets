import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import configuration.ConfigXML;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Event;
import domain.Liga;
import domain.Equipo;
import exceptions.EventAlreadyExistsException;
import exceptions.EventFinishedException;
import exceptions.QuestionAlreadyExist;
import test.businessLogic.TestFacadeImplementation;
import test.dataAccess.TestDataAccess;

public class CreateEventDAB {

	 //sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();

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
			Date dt =UtilDate.newDate(2024,4,20);
		
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			e1 = testDA.getEquipoById(nE1);
			e2 = testDA.getEquipoById(nE2);
			testDA.close();			
			
			//invoke System Under Test (sut)  
			 ev=sut.createEvent(e1, e2, dt);
		
	
			
			assertTrue(ev!=null);
			assertTrue(ev.getDescription().compareTo(nE1+"-"+nE2)==0);
		
			
			//q datubasean dago
			testDA.open();
			boolean exist = testDA.existEvent(ev);
				
			assertTrue(exist);
			testDA.close();
			
		   } catch (EventAlreadyExistsException e) {
		
			fail("No tiene que fallar");
			} finally {
				  //Remove the created objects in the database (cascade removing)   
				  
				testDA.open();
		          boolean b=testDA.removeEvent(ev);
		          testDA.close();
		          System.out.println("Finally "+b);          
		        }
		    
		   }
	@Test
	//sut.createEvent: El evento ya existe
		public void test2() {
			try {
				
				//define paramaters
				String nE1 = "Barcelona";
				String nE2 = "Atlético de Madrid";
				Date dt =UtilDate.newDate(2024,4,20);
			
				//configure the state of the system (create object in the dabatase)
				testDA.open();
				e1 = testDA.getEquipoById(nE1);
				e2 = testDA.getEquipoById(nE2);
				testDA.close();			
				
				//invoke System Under Test (sut)        
				ev=sut.createEvent(e1, e2, dt);
	
				Event ev3=sut.createEvent(e1, e2, dt);
				
				
			   } catch (EventAlreadyExistsException e) {
				//Si llega aqui esque esta bien;
				   assertTrue(true);
				} finally {
					  //Remove the created objects in the database (cascade removing)   
					testDA.open();
			          boolean b=testDA.removeEvent(ev);
			          testDA.close();
			          System.out.println("Finally2 "+b);          
			        }
			   }
	@Test 
	
	//sut.createEvent: El equipo ya es local de otro evento
		public void test3() {
			try {
				
				//define paramaters
				String nE1 = "Barcelona";
				String nE2 = "Atlético de Madrid";
				String nE3 = "Cádiz";
				Date dt =UtilDate.newDate(2024,4,20);
			
				//configure the state of the system (create object in the dabatase)
				testDA.open();
				e1 = testDA.getEquipoById(nE1);
				e2 = testDA.getEquipoById(nE2);
				e3 = testDA.getEquipoById(nE3);
				testDA.close();			
				
				//invoke System Under Test (sut)        
				ev=sut.createEvent(e1, e2, dt);
	            ev2= sut.createEvent(e3,e1, dt);
				assertTrue(ev2==null);
				
			   } catch (Exception e) {
				 
				fail();
				} finally {
					  //Remove the created objects in the database (cascade removing)   
					testDA.open();
			          boolean b=testDA.removeEvent(ev);
			          testDA.close();
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
				testDA.open();
				e1 = testDA.getEquipoById(nE1);
				e2 = testDA.getEquipoById(nE2);

				testDA.close();			
				
				//invoke System Under Test (sut)        
				ev=sut.createEvent(null, e2, dt);
	           fail();
				
				
			   } catch (Exception e) {
				   fail("Tiene que fallar");
				fail();
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
				testDA.open();
				e1 = testDA.getEquipoById(nE1);
				e2 = testDA.getEquipoById(nE2);
		
				testDA.close();			
				
				//invoke System Under Test (sut)        
				ev=sut.createEvent(e1,null, dt);
	            fail();

			   } catch (Exception e) {
				
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
				testDA.open();
				e1 = testDA.getEquipoById(nE1);
				e2 = testDA.getEquipoById(nE2);
		
				testDA.close();			
				
				//invoke System Under Test (sut)        
				ev=sut.createEvent(e1,e2, null);
	            fail();    
				
			   } catch (Exception e) {
				
				fail("Tiene que fallar");
				} finally {
					  //Remove the created objects in the database (cascade removing)   
			        }
			   }
	
	@Test
	//sut.createEvent: fecha anterior
		public void test7() {
			try {
				Calendar today = Calendar.getInstance();
				    
				//define paramaters
				String nE1 = "Barcelona";
				String nE2 = "Atlético de Madrid";
	
				Date dt =UtilDate.newDate(2022,4,20);
			
				//configure the state of the system (create object in the dabatase)
				testDA.open();
				e1 = testDA.getEquipoById(nE1);
				e2 = testDA.getEquipoById(nE2);
		
				testDA.close();			 
				
				//invoke System Under Test (sut)        
				ev=sut.createEvent(e1,e2, dt);
	            fail();    
				
			   } catch (Exception e) {
				
				fail("Tiene que fallar");
				} finally {
					  //Remove the created objects in the database (cascade removing)   
			        }
			   }
	

}



