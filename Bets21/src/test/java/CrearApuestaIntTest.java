import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import businessLogic.BLFacadeImplementation;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Bet;
import domain.Equipo;
import domain.Event;
import domain.Liga;
import domain.Pronostico;
import domain.Question;
import domain.Usuario;
import test.businessLogic.TestFacadeImplementation;

public class CrearApuestaIntTest {

	 static BLFacadeImplementation sut;
	 static TestFacadeImplementation testBL;

	 private Event ev;
	 private Usuario u;
	 private Pronostico p;
	 private Bet b;
	 
	@BeforeClass
	public static void setUpClass() {
		DataAccess data= new DataAccess();
		sut=new BLFacadeImplementation(data);
		testBL= new TestFacadeImplementation();
	}
	
	
	@Test
	public void test1() {
			try {
				//define paramaters
				Liga ligaSantander=new Liga("Liga Santander",20);
				Equipo atleticoDeMadrid= new Equipo("Atlético de Madrid", ligaSantander);
				Equipo atlheticDeBilbao= new Equipo("Athletic de Bilbao", ligaSantander);
				Event ev1=new Event(1, "Atlético de Madrid-Athletic de Bilbao", UtilDate.newDate(1,1,17), atleticoDeMadrid, atlheticDeBilbao);
				Usuario user= new Usuario("Pepe1","dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");
				
		
				ev = testBL.addEvent(ev1);
				u = testBL.addUser(user);

				//invoke System Under Test (sut) 
				int num = sut.crearApuesta(u, 10, ev.getQuest(0).getPron(0));

				assertEquals(0,num);
			   }catch (Exception e) {
				    fail();
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					boolean b=testBL.removeEvent(ev);
					boolean z=testBL.removeUsuario(u);
			         System.out.println("Finally: "+b+", "+z);
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
				
				ev = testBL.addEvent(ev1);
				u = testBL.addUser(user);
				
				//invoke System Under Test (sut) 
				int num = sut.crearApuesta(u, 10, ev1.getQuest(0).getPron(1));

				assertEquals(0,num);
			   }catch (Exception e) {
				    fail();
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					boolean b=testBL.removeEvent(ev);
					boolean z=testBL.removeUsuario(u);
			         System.out.println("Finally: "+b+", "+z);
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
				
				ev = testBL.addEvent(ev1);
				u = testBL.addUser(user);

				//invoke System Under Test (sut) 
				int num = sut.crearApuesta(u, 10, ev1.getQuest(0).getPron(2));
				
				assertEquals(0,num);
			   }catch (Exception e) {
				    fail();
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					boolean b=testBL.removeEvent(ev);
					boolean z=testBL.removeUsuario(u);
			         System.out.println("Finally: "+b+", "+z);
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
				
				ev = testBL.addEvent(ev1);
				u = testBL.addUser(user);
				p = testBL.addPronostico(p1);

				//invoke System Under Test (sut) 
				int num = sut.crearApuesta(u, 10, p);
				
				assertEquals(0,num);
			   }catch (Exception e) {
				    fail();
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					boolean b=testBL.removeEvent(ev);
					boolean z=testBL.removeUsuario(u);
					boolean t=testBL.removePronostico(p);
			         System.out.println("Finally: "+b+", "+z+", "+t);
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
				Question q1=ev1.addQuestion("dfddgd",1);
				Pronostico p1=new Pronostico("fg",q1,1.2);
				q1.addPronostico(p1);
				Usuario user= new Usuario(null,"dsfdsf","1010293833",false,"usuariomasguapo@gmail.com");
				
				ev = testBL.addEvent(ev1);
				u = testBL.addUser(user);
				p = testBL.addPronostico(p1);


				//invoke System Under Test (sut) 
				int num = sut.crearApuesta(u, 10, p);
				
				fail();
			   }catch (Exception e) {
				    assertTrue(true);
					// TODO Auto-generated catch block
					e.printStackTrace();
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
				
				ev = testBL.addEvent(ev1);
				u = testBL.addUser(user);


				//invoke System Under Test (sut) 
				int num = sut.crearApuesta(u, 10, null);
				

				fail();
			   }catch (Exception e) {
				   assertTrue(true);
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					boolean b=testBL.removeEvent(ev);
					boolean z=testBL.removeUsuario(u);
			         System.out.println("Finally: "+b+", "+z);
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
				

				ev = testBL.addEvent(ev1);
				u = testBL.addUser(user);
				u.setDinero(-1);


				//invoke System Under Test (sut) 
				int num = sut.crearApuesta(u, 10, ev.getQuest(0).getPron(0));

				assertEquals(2,num);
			   }catch (Exception e) {
					fail();
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					boolean b=testBL.removeEvent(ev);
					boolean z=testBL.removeUsuario(u);
			         System.out.println("Finally: "+b+", "+z);
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
				double dinero=user.getDinero()+30;

				ev = testBL.addEvent(ev1);
				u = testBL.addUser(user);

				//invoke System Under Test (sut) 
				int num = sut.crearApuesta(u, dinero, ev.getQuest(0).getPron(0));

				assertEquals(2,num);
			   }catch (Exception e) {
					fail();
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					boolean b=testBL.removeEvent(ev);
					boolean z=testBL.removeUsuario(u);
			         System.out.println("Finally: "+b+", "+z);
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
				Bet b1 = new Bet(ev1.getQuest(0).getPron(0),user,10);
				user.añadirApuesta(b1);
				
				ev = testBL.addEvent(ev1);
				u = testBL.addUser(user);


				//invoke System Under Test (sut) 
				int num = sut.crearApuesta(u, 10, ev.getQuest(0).getPron(1));
				
				fail();
			   }catch (Exception e) {
				   assertTrue(true);
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					boolean b=testBL.removeEvent(ev);
			         System.out.println("Finally: "+b);
				}
			   }
}
