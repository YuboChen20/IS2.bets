import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Bet;
import domain.Equipo;
import domain.Event;
import domain.Liga;
import domain.Pronostico;
import domain.Question;
import domain.Usuario;
import test.dataAccess.TestDataAccess;

public class CrearApuestaDABTest {

	
	 //sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();
	 
	 private Event ev;
	 private Usuario u;
	 private Pronostico p;
	 private Bet b;

	 
	@Test
	public void test1() {
		try {
			//define paramaters
			Liga ligaSantander=new Liga("Liga Santander",20);
			Equipo atleticoDeMadrid= new Equipo("Atlético de Madrid", ligaSantander);
			Equipo atlheticDeBilbao= new Equipo("Athletic de Bilbao", ligaSantander);
			Event ev1=new Event(1, "Atlético de Madrid-Athletic de Bilbao", UtilDate.newDate(1,1,17), atleticoDeMadrid, atlheticDeBilbao);
			Usuario user= new Usuario("Pepe","dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");

			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEvent(ev1);
			u = testDA.addUser(user);	
			testDA.close();
		
			//invoke System Under Test (sut)  
			int num = sut.crearApuesta(u, 10, ev.getQuest(0).getPron(0));
			assertEquals(0,num);
		}catch(Exception e) {
			fail("Not yet implemented");
		}finally {
			  //Remove the created objects in the database (cascade removing)   
			testDA.open();
	          boolean a=testDA.removeEvent(ev);
	          boolean c= testDA.removeUser(u);
	          testDA.close();
	      //     System.out.println("Finally "+b);          
	        }
	}
	@Test
	public void test2() {
		try {
			//define paramaters
			Liga ligaSantander=new Liga("Liga Santander",20);
			Equipo atleticoDeMadrid= new Equipo("Atlético de Madrid", ligaSantander);
			Equipo atlheticDeBilbao= new Equipo("Athletic de Bilbao", ligaSantander);
			Event ev1=new Event(1, "Atlético de Madrid-Athletic de Bilbao", UtilDate.newDate(1,1,17), atleticoDeMadrid, atlheticDeBilbao);
			Usuario user= new Usuario("Pepe2","dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");

			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEvent(ev1);
			u = testDA.addUser(user);	
			testDA.close();
		
			//invoke System Under Test (sut)  
			int num = sut.crearApuesta(u, 10, ev.getQuest(0).getPron(1));
			assertEquals(0,num);
		}catch(Exception e) {
			fail("Not yet implemented");
		}finally {
			  //Remove the created objects in the database (cascade removing)   
			testDA.open();
	          boolean a=testDA.removeEvent(ev);
	          boolean c= testDA.removeUser(u);
	          testDA.close();
	      //     System.out.println("Finally "+b);          
	        }
	}
	@Test
	public void test3() {
		try {
			//define paramaters
			Liga ligaSantander=new Liga("Liga Santander",20);
			Equipo atleticoDeMadrid= new Equipo("Atlético de Madrid", ligaSantander);
			Equipo atlheticDeBilbao= new Equipo("Athletic de Bilbao", ligaSantander);
			Event ev1=new Event(1, "Atlético de Madrid-Athletic de Bilbao", UtilDate.newDate(1,1,17), atleticoDeMadrid, atlheticDeBilbao);
			Usuario user= new Usuario("Pepe3","dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");

			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEvent(ev1);
			u = testDA.addUser(user);	
			testDA.close();
		
			//invoke System Under Test (sut)  
			int num = sut.crearApuesta(u, 10, ev.getQuest(0).getPron(2));
			assertEquals(0,num);
		}catch(Exception e) {
			fail("Not yet implemented");
		}finally {
			  //Remove the created objects in the database (cascade removing)   
			testDA.open();
	          boolean a=testDA.removeEvent(ev);
	          boolean c= testDA.removeUser(u);
	          testDA.close();
	      //     System.out.println("Finally "+b);          
	        }
	}
	@Test
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
			Usuario user= new Usuario("Pepe4","dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");

			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEvent(ev1);
			u = testDA.addUser(user);	
			p = testDA.addPronos(p1);
			testDA.close();
		
			//invoke System Under Test (sut)  
			int num = sut.crearApuesta(u, 10, p);
			assertEquals(0,num);
		}catch(Exception e) {
			fail("Not yet implemented");
		}finally {
			  //Remove the created objects in the database (cascade removing)   
			testDA.open();
	          boolean a=testDA.removeEvent(ev);
	          boolean c= testDA.removeUser(u);
	          boolean z = testDA.removePronostico(p);
	          testDA.close();
	      //     System.out.println("Finally "+b);          
	        }
	}
	
	@Test
	public void test5() {
		try {
			//define paramaters
			Liga ligaSantander=new Liga("Liga Santander",20);
			Equipo atleticoDeMadrid= new Equipo("Atlético de Madrid", ligaSantander);
			Equipo atlheticDeBilbao= new Equipo("Athletic de Bilbao", ligaSantander);
			Event ev1=new Event(1, "Atlético de Madrid-Athletic de Bilbao", UtilDate.newDate(1,1,17), atleticoDeMadrid, atlheticDeBilbao);
			Usuario user= new Usuario("Pepe5","dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");

			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEvent(ev1);
			testDA.close();
		
			//invoke System Under Test (sut)  
			int num = sut.crearApuesta(user, 10, ev.getQuest(0).getPron(0));
			assertEquals(0,num);
		}catch(Exception e) {
			String mensaje = "Cannot invoke "+(char)34+"domain.Usuario.getApuestas()"+(char)34+" because "+(char)34+"u"+(char)34+" is null";
			System.out.print(e.getMessage());
			assertEquals(mensaje,e.getMessage());
		}finally {
			  //Remove the created objects in the database (cascade removing)   
			testDA.open();
	          boolean a=testDA.removeEvent(ev);
	          testDA.close();
	      //     System.out.println("Finally "+b);          
	        }
	}
	
	@Test
	public void test6() {
		try {
			//define paramaters
			Liga ligaSantander=new Liga("Liga Santander",20);
			Equipo atleticoDeMadrid= new Equipo("Atlético de Madrid", ligaSantander);
			Equipo atlheticDeBilbao= new Equipo("Athletic de Bilbao", ligaSantander);
			Event ev1=new Event(1, "Atlético de Madrid-Athletic de Bilbao", UtilDate.newDate(1,1,17), atleticoDeMadrid, atlheticDeBilbao);
			Usuario user= new Usuario("Pepe6","dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");
			Pronostico p1=new Pronostico("fg",ev1.getQuest(0),1.2);

			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEvent(ev1);
			u = testDA.addUser(user);	
			testDA.close();
		
			//invoke System Under Test (sut)  
			int num = sut.crearApuesta(u, 10, p1);
			assertEquals(0,num);
		}catch(Exception e) {
			String mensaje = "Unexpected null argument";
			System.out.print(e.getMessage());
			assertEquals(mensaje,e.getMessage());
		}finally {
			  //Remove the created objects in the database (cascade removing)   
			testDA.open();
	          boolean a=testDA.removeEvent(ev);
	          boolean c= testDA.removeUser(u);
	          testDA.close();
	      //     System.out.println("Finally "+b);          
	        }
	}
	
	@Test
	public void test7() {
		try {
			//define paramaters
			Liga ligaSantander=new Liga("Liga Santander",20);
			Equipo atleticoDeMadrid= new Equipo("Atlético de Madrid", ligaSantander);
			Equipo atlheticDeBilbao= new Equipo("Athletic de Bilbao", ligaSantander);
			Event ev1=new Event(1, "Atlético de Madrid-Athletic de Bilbao", UtilDate.newDate(1,1,17), atleticoDeMadrid, atlheticDeBilbao);
			Usuario user= new Usuario("Pepe7","dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");
			user.setDinero(-1);

			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEvent(ev1);
			u = testDA.addUser(user);	
			testDA.close();
		
			//invoke System Under Test (sut)  
			int num = sut.crearApuesta(u, 10, ev.getQuest(0).getPron(0));
			assertEquals(2,num);
		}catch(Exception e) {
			fail("Not yet implemented");
		}finally {
			  //Remove the created objects in the database (cascade removing)   
			testDA.open();
	          boolean a=testDA.removeEvent(ev);
	          boolean c= testDA.removeUser(u);
	          testDA.close();
	      //     System.out.println("Finally "+b);          
	        }
	}
	
	@Test
	public void test8() {
		try {
			//define paramaters
			Liga ligaSantander=new Liga("Liga Santander",20);
			Equipo atleticoDeMadrid= new Equipo("Atlético de Madrid", ligaSantander);
			Equipo atlheticDeBilbao= new Equipo("Athletic de Bilbao", ligaSantander);
			Event ev1=new Event(1, "Atlético de Madrid-Athletic de Bilbao", UtilDate.newDate(1,1,17), atleticoDeMadrid, atlheticDeBilbao);
			Usuario user= new Usuario("Pepe8","dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");

			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEvent(ev1);
			u = testDA.addUser(user);	
			testDA.close();
		
			//invoke System Under Test (sut)  
			int num = sut.crearApuesta(u, 500, ev.getQuest(0).getPron(0));
			assertEquals(2,num);
		}catch(Exception e) {
			fail("Not yet implemented");
		}finally {
			  //Remove the created objects in the database (cascade removing)   
			testDA.open();
	          boolean a=testDA.removeEvent(ev);
	          boolean c= testDA.removeUser(u);
	          testDA.close();
	      //     System.out.println("Finally "+b);          
	        }
	}
	
	 @Test
	public void test9() {
		try {
			//define paramaters
			Liga ligaSantander=new Liga("Liga Santander",20);
			Equipo atleticoDeMadrid= new Equipo("Atlético de Madrid", ligaSantander);
			Equipo atlheticDeBilbao= new Equipo("Athletic de Bilbao", ligaSantander);
			Event ev1=new Event(1, "Atlético de Madrid-Athletic de Bilbao", UtilDate.newDate(1,1,17), atleticoDeMadrid, atlheticDeBilbao);
			Usuario user= new Usuario("Pepe9","dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");

			
			

		
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEvent(ev1);
			u = testDA.addUser(user);	
			testDA.close();
		
			//invoke System Under Test (sut)  
			sut.crearApuesta(u, 10, ev.getQuest(0).getPron(2));
			int num = sut.crearApuesta(u, 10, ev.getQuest(0).getPron(1));
			assertEquals(num,1);
		}catch(Exception e) {
			fail("Not yet implemented");
		}finally {
			  //Remove the created objects in the database (cascade removing)   
			testDA.open();
	          boolean a=testDA.removeEvent(ev);
	          boolean c= testDA.removeUser(u);
	          testDA.close();
	      //     System.out.println("Finally "+b);          
	        }
	}
	
	
	


}
