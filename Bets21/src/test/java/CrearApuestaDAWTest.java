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

public class CrearApuestaDAWTest {

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
			Question q1=ev1.addQuestion("dfddgd",1);
			Pronostico p1=new Pronostico("fg",q1,1.2);
			q1.addPronostico(p1);
			Usuario user= new Usuario("User1","dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");
			Bet b1 = new Bet(p1,user,10);
			Bet b2 = new Bet(ev1.getQuest(0).getPron(0),user,10);
			Vector<Bet> bets=new Vector<Bet>();
			bets.add(b2);
			

			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEvent(ev1);
			u = testDA.addUser(user);	
			b = testDA.addBets(b1);
			p = testDA.addPronos(p1);
			testDA.close();
		
			//invoke System Under Test (sut)  
			sut.añadirApuesta(u, b);
			int num = sut.crearApuesta(u, 10, p);
			assertEquals(0,num);
		}catch(Exception e) {
			fail("Not yet implemented");
		}finally {
			  //Remove the created objects in the database (cascade removing)   
			testDA.open();
	          boolean a=testDA.removeEvent(ev);
	          boolean c= testDA.removeUser(u);
	          boolean x= testDA.removeBet(b);
	          boolean z = testDA.removePronostico(p);
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
			Question q1=ev1.addQuestion("dfddgd",1);
			Pronostico p1=new Pronostico("fg",q1,1.2);
			q1.addPronostico(p1);
			Usuario user= new Usuario("User2","dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");
			Bet b1 = new Bet(p1,user,10);
			Bet b2 = new Bet(ev1.getQuest(0).getPron(0),user,10);
			Vector<Bet> bets=new Vector<Bet>();
			bets.add(b2);
			

			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEvent(ev1);
			u = testDA.addUser(user);	
			b = testDA.addBets(b1);
			p = testDA.addPronos(p1);
			testDA.close();
		
			//invoke System Under Test (sut)  
			sut.añadirApuesta(u, b);
			int num = sut.crearApuesta(u, 10, p);
			assertEquals(0,num);
		}catch(Exception e) {
			fail("Not yet implemented");
		}finally {
			  //Remove the created objects in the database (cascade removing)   
			testDA.open();
	          boolean a=testDA.removeEvent(ev);
	          boolean c= testDA.removeUser(u);
	          boolean x= testDA.removeBet(b);
	          boolean z = testDA.removePronostico(p);
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
			Question q1=ev1.addQuestion("dfddgd",1);
			Pronostico p1=new Pronostico("fg",q1,1.2);
			q1.addPronostico(p1);
			Usuario user= new Usuario("User3","dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");
			Bet b1 = new Bet(p1,user,10);
			Vector<Bet> bets=new Vector<Bet>();
			bets.add(b1);
			
			

		
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEvent(ev1);
			u = testDA.addUser(user);	
			b = testDA.addBets(b1);
			p = testDA.addPronos(p1);
			testDA.close();
		
			//invoke System Under Test (sut)  
			sut.crearApuesta(u, 10, p);
			int num = sut.crearApuesta(u, 10, p);
			assertEquals(num,1);
		}catch(Exception e) {
			fail("Not yet implemented");
		}finally {
			  //Remove the created objects in the database (cascade removing)   
			testDA.open();
	          boolean a=testDA.removeEvent(ev);
	          boolean c= testDA.removeUser(u);
	          boolean x= testDA.removeBet(b);
	          boolean z = testDA.removePronostico(p);
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
			Question q2=ev1.addQuestion("¿En cuantoterminara el partido?",2);
			Pronostico p1=new Pronostico("4-0",q2,1.2);
			q2.addPronostico(p1);
			Usuario user= new Usuario("User4","dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");
		
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEvent(ev1);
			u = testDA.addUser(user);
			testDA.close();
		
			//invoke System Under Test (sut)  
			int num = sut.crearApuesta(u, 500, p1);
			System.out.println(p);
			assertEquals(2,num);
		}catch(Exception e) {
			fail("Not yet implemented");
		}finally {
			  //Remove the created objects in the database (cascade removing)   
			testDA.open();
	          boolean b=testDA.removeEvent(ev);
	          boolean c= testDA.removeUser(u);
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
			Question q2=ev1.addQuestion("1X2",2);
			Pronostico p1=new Pronostico("4",ev1.getQuest(0),1.2);
			ev1.getQuest(0).addPronostico(p1);
			Usuario user= new Usuario("User5","dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");
		
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEvent(ev1);
			u = testDA.addUser(user);
			testDA.close();
		
			//invoke System Under Test (sut)  
			int num = sut.crearApuesta(u, 20, p1);
			System.out.println(p);
			assertEquals(0,num);
		}catch(Exception e) {
			fail("Not yet implemented");
		}finally {
			  //Remove the created objects in the database (cascade removing)   
			testDA.open();
	          boolean b=testDA.removeEvent(ev);
	          boolean c= testDA.removeUser(u);
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
			Question q2=ev1.addQuestion("1X2",2);
			Pronostico p1=new Pronostico("2",q2,1.2);
			q2.addPronostico(p1);
			Usuario user= new Usuario("User6","dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");
		
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEvent(ev1);
			u = testDA.addUser(user);
			p = testDA.addPronos(p1);
			testDA.close();
		
			//invoke System Under Test (sut)  
			int num = sut.crearApuesta(u, 20, ev.getQuest(0).getPron(0));
			System.out.println(p);
			assertEquals(0,num);
		}catch(Exception e) {
			fail("Not yet implemented");
		}finally {
			  //Remove the created objects in the database (cascade removing)   
			testDA.open();
	          boolean b=testDA.removeEvent(ev);
	          boolean c= testDA.removeUser(u);
	          boolean x= testDA.removePronostico(p);
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
			Question q2=ev1.addQuestion("1X2",2);
			Pronostico p1=new Pronostico("2",q2,1.2);
			q2.addPronostico(p1);
			Usuario user= new Usuario("User7","dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");
		
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEvent(ev1);
			u = testDA.addUser(user);
			p = testDA.addPronos(p1);
			testDA.close();
		
			//invoke System Under Test (sut)  
			int num = sut.crearApuesta(u, 20, ev.getQuest(0).getPron(1));
			System.out.println(p);
			assertEquals(0,num);
		}catch(Exception e) {
			fail("Not yet implemented");
		}finally {
			  //Remove the created objects in the database (cascade removing)   
			testDA.open();
	          boolean b=testDA.removeEvent(ev);
	          boolean c= testDA.removeUser(u);
	          boolean x= testDA.removePronostico(p);
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
			Question q2=ev1.addQuestion("1X2",2);
			Pronostico p1=new Pronostico("2",q2,1.2);
			q2.addPronostico(p1);
			Usuario user= new Usuario("User8","dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");
		
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEvent(ev1);
			u = testDA.addUser(user);
			p = testDA.addPronos(p1);
			testDA.close();
		
			//invoke System Under Test (sut)  
			int num = sut.crearApuesta(u, 20, ev.getQuest(0).getPron(2));
			System.out.println(p);
			assertEquals(0,num);
		}catch(Exception e) {
			fail("Not yet implemented");
		}finally {
			  //Remove the created objects in the database (cascade removing)   
			testDA.open();
	          boolean b=testDA.removeEvent(ev);
	          boolean c= testDA.removeUser(u);
	          boolean x= testDA.removePronostico(p);
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
			Equipo atlheticDeBilbao= new Equipo(null, ligaSantander);
			Event ev1=new Event();
			Question q2=ev1.addQuestion("¿En cuantoterminara el partido?",2);
			Pronostico p1=new Pronostico("4-0",q2,1.2);
			q2.addPronostico(p1);
			Usuario user= new Usuario("User9","dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");
		
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEvent(ev1);
			u = testDA.addUser(user);
			testDA.close();
		
			//invoke System Under Test (sut)  
			int num = sut.crearApuesta(u, 20, p1);
			System.out.println(p);
			assertEquals(0,num);
		}catch(Exception e) {
			fail("Not yet implemented");
		}finally {
			  //Remove the created objects in the database (cascade removing)   
			testDA.open();
	          boolean b=testDA.removeEvent(ev);
	          boolean c= testDA.removeUser(u);
	          testDA.close();
	      //     System.out.println("Finally "+b);          
	        }
	}
	 
	 
	 @Test
	public void test10() {
		try {
			//define paramaters
			Liga ligaSantander=new Liga("Liga Santander",20);
			Equipo atleticoDeMadrid= new Equipo("Atlético de Madrid", ligaSantander);
			Equipo atlheticDeBilbao= new Equipo("Athletic de Bilbao", ligaSantander);
			Event ev1=new Event(1, "Atlético de Madrid-Athletic de Bilbao", UtilDate.newDate(1,1,17), atleticoDeMadrid, atlheticDeBilbao);
			Question q2=ev1.addQuestion("¿En cuantoterminara el partido?",2);
			Pronostico p1=new Pronostico("4-0",q2,1.2);
			q2.addPronostico(p1);
			Usuario user= new Usuario("User10","dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");
		
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEvent(ev1);
			u = testDA.addUser(user);
			testDA.close();
		
			//invoke System Under Test (sut)  
			int num = sut.crearApuesta(u, 20, p1);
			System.out.println(p);
			assertEquals(0,num);
		}catch(Exception e) {
			fail("Not yet implemented");
		}finally {
			  //Remove the created objects in the database (cascade removing)   
			testDA.open();
	          boolean b=testDA.removeEvent(ev);
	          boolean c= testDA.removeUser(u);
	          testDA.close();
	      //     System.out.println("Finally "+b);          
	        }
	}
	 


	 
	 
}
