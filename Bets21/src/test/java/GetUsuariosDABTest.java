import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.List;
import org.junit.Test;
import dataAccess.DataAccess;
import domain.Usuario;
import test.businessLogic.TestFacadeImplementation;
import test.dataAccess.TestDataAccess;

public class GetUsuariosDABTest {
	
	//sut:system under test
	static DataAccess sut=new DataAccess();
		 
	//additional operations needed to execute the test 
	static TestDataAccess testDA=new TestDataAccess();
	private Usuario u;
	
	
	
	
	
	
	@Test
	//sut.getUsuarios 
	public void test1() {
		try {
			//define paramaters
			String userName="Si";
			String passwor= "12345";
			String cardCode="123456789012";
			boolean admi= true;
			String correo= "sicorreo@gmail.com";
			boolean bloqueado=true;
			
			
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			u = testDA.addUsuario(userName,passwor,cardCode,admi,correo,bloqueado);
			testDA.close();
			
			
			//invoke System Under Test (sut)  
			List<Usuario> list=sut.getUsuarios("Bloqueados","Admin");
			int i=0;
			while(i<list.size()) {
				if(list.get(i).getUserName().equals(u.getUserName())) assertTrue(true);
				i++;
			}
			
		   } catch (Exception e) {
			 //if the program continues fail
			    fail();
			} finally {
				 //Remove the created objects in the database (cascade removing)   
				testDA.open();
		        boolean b=testDA.removeUsuario(u);
		        testDA.close();
		        System.out.println("Finally "+b); 
		   }
	}
	





	@Test
	//sut.getUsuarios 
	public void test2() {
		try {
			//define paramaters
			String userName="Si";
			String passwor= "12345";
			String cardCode="123456789012";
			boolean admi= false;
			String correo= "sicorreo@gmail.com";
			boolean bloqueado=false;
			
			
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			u = testDA.addUsuario(userName,passwor,cardCode,admi,correo,bloqueado);
			testDA.close();
			
			//invoke System Under Test (sut)  
			List<Usuario> list=sut.getUsuarios("No Bloqueados","Usuarios");
			
			
			//verify the results
			int i=0;
			while(i<list.size()) {
				if(list.get(i).getUserName().equals(u.getUserName())) assertTrue(true);
				i++;
			}
			
		   } catch (Exception e) {
			 //if the program continues fail
			    fail();
			} finally {
				 //Remove the created objects in the database (cascade removing)   
				testDA.open();
		        boolean b=testDA.removeUsuario(u);
		        testDA.close();
		        System.out.println("Finally "+b); 
		   }
	}
	 

		
	
	@Test
	//sut.getUsuarios 
	public void test3() {
		try {
			
			
			//invoke System Under Test (sut)  
			sut.getUsuarios(null,"Usuarios");
			
			//if the program continues fail
		    fail();
			
		 } catch (NullPointerException e) {
			 assertTrue(true);
		 }
	}
	
	
	@Test
	//sut.getUsuarios 
	public void test5() {
		try {
			
			
			//invoke System Under Test (sut)  
			sut.getUsuarios("Bloqueados",null);
			
			//if the program continues fail
		    fail();
			
		 } catch (Exception e) {
			 assertTrue(true);
		 }
	}
	
	@Test
	//sut.getUsuarios 
	public void test4() {
		try {
			
			
			//invoke System Under Test (sut)  
			sut.getUsuarios("bloqueo","Admin");
			
			//if the program continues fail
		    fail();
			
		 } catch (Exception e) {
			 assertTrue(true);
		 }
	}
	
	@Test
	//sut.getUsuarios 
	public void test6() {
		try {
			
			
			//invoke System Under Test (sut)  
			sut.getUsuarios("Bloqueados","User");
			
			//if the program continues fail
		    fail();
			
		 } catch (Exception e) {
			 assertTrue(true);
		 }
	}
	
	
	
		
	
	
}