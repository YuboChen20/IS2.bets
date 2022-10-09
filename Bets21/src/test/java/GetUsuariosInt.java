

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;

import domain.Usuario;
import test.businessLogic.TestFacadeImplementation;




public class GetUsuariosInt {
	static BLFacadeImplementation sut;
	static TestFacadeImplementation testBL;
	private Usuario u;
	 
	@BeforeClass
	public static void setUpClass() {

		DataAccess data= new DataAccess();
		sut=new BLFacadeImplementation(data);
		testBL= new TestFacadeImplementation();
	}
	
	@Test
	public void test1() {
	try
	{	
		
		Usuario u = new Usuario("1","12345","123456789013",true,"u1correo@gmail.com");
		u.setBloqueado(true);
		
		List <Usuario> list = new ArrayList<>();
		list.add(u); 
		
	
		 
		List <Usuario> list1= sut.getUsuarios("Bloqueados", "Admin");
		
		//verify parameter values as usual using JUnit asserts
		assertEquals(u.getUserName(), list1.get(0).getUserName());
	}
	catch
	(Exception e) {
	
	e.printStackTrace();
	}
	} 

 
	@Test
	public void test2() {
	try
	{	 
		
		Usuario u = new Usuario("4","12345","123456789013",false,"u1correo@gmail.com");
		List <Usuario> list= new ArrayList<>();
		list.add(u);
		
		List <Usuario> list1= sut.getUsuarios("No Bloqueados", "Usuarios");
		
		//verify parameter values as usual using JUnit asserts
		int i=0;
		while(i<list1.size()) {
			if(list.get(i).getUserName()==u.getUserName()) assertTrue(true);
			i++;
		}
	}
	catch(Exception e) {
	  fail();
	}
	}
	
	@Test
	public void test3() {
	try
	{	
		sut.getUsuarios(null, "Usuarios");
		
		//verify parameter values as usual using JUnit asserts
		fail();
	}
	catch
	(Exception e) {
	assertTrue(true);
	}
	}
	


	@Test
	public void test4() {
	try
	{	
		sut.getUsuarios("Bloqueo", "Admin");
		
		//verify parameter values as usual using JUnit asserts
		fail();
	}
	catch
	(Exception e) {
	assertTrue(true);
	}
	}
	
	@Test
	public void test5() {
	try
	{	

		sut.getUsuarios("Bloqueados", null);
		
		//verify parameter values as usual using JUnit asserts
		fail();
	}
	catch
	(Exception e) {
	assertTrue(true);
	}
	}
	
	@Test
	public void test6() {
	try
	{	

		sut.getUsuarios("Bloqueados", "User");
		
		//verify parameter values as usual using JUnit asserts
		fail();
	}
	catch
	(Exception e) {
	assertTrue(true);
	}
	}
	
}