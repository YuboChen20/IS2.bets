

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;

import domain.Usuario;



@RunWith(MockitoJUnitRunner.class)
public class GetUsuariosMockIntTest {
	
	DataAccess data=Mockito.mock(DataAccess.class);
	
	BLFacadeImplementation sut =new BLFacadeImplementation(data);
	
	 
	 
	@Test
	public void test1() {
	try
	{	
		
		Usuario u = new Usuario("1","12345","123456789013",true,"u1correo@gmail.com");
		u.setBloqueado(true);
		
		List <Usuario> list = new ArrayList<>();
		list.add(u); 
		
		Mockito.doReturn(list)      //configurar mockito
		 .when(data)
		 .getUsuarios("Bloqueados","Admin");
		
		 
		 
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
		
		Mockito.doReturn(list)      //configurar mockito
		 .when(data)
		 .getUsuarios("No Bloqueados","Usuarios");
		
		List <Usuario> list1= sut.getUsuarios("No Bloqueados", "Usuarios");
		
		//verify parameter values as usual using JUnit asserts
		assertEquals(u.getUserName(), list1.get(0).getUserName());
	}
	catch(Exception e) {
	  fail();
	}
	}
	
	@Test
	public void test3() {
	try
	{	

		Mockito.when(data.getUsuarios(null,"Usuarios"))
		 .thenThrow(Exception.class);
		
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

		Mockito.when(data.getUsuarios("Bloqueo", "Admin"))      //configurar mockito
		 .thenThrow(Exception.class);
		
		 
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

		Mockito.when(data.getUsuarios("Bloqueados", null))      //configurar mockito
		 .thenThrow(Exception.class);
		
		 
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

		Mockito.when(data.getUsuarios("Bloqueados","User"))      //configurar mockito
		 .thenThrow(Exception.class);
		
		 
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