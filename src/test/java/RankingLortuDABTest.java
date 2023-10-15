import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.Vector;

import org.junit.Test;

import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.Transaction;
import test.dataAccess.TestDataAccess;

public class RankingLortuDABTest {

	
	 //sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();
	
	 @Test
	 public void test01() {
		 try {
			 //Test honetarako jatorrizko datu-basea erabili da eta initializeDB()
			 //metodoak uzten duen informazioarekin egin da lan.
			 //Metodo horren arabera horrela izan beharko litzateke rankinga
			 //[registered (1), mikel (2), markel (3), andrea (4), admin (5)]
			 assertEquals(sut.rankingLortu().get(0).getUsername(),"registered");
			 assertEquals(sut.rankingLortu().get(1).getUsername(), "mikel");
			 assertEquals(sut.rankingLortu().get(2).getUsername(), "markel");
			 assertEquals(sut.rankingLortu().get(3).getUsername(), "andrea");
			 assertEquals(sut.rankingLortu().get(4).getUsername(), "admin");
		 }
		 catch (Exception e) {
			 System.out.println("Test01");
			 e.printStackTrace();
			 
		 }
	 }
	 
	 @Test
	 public void test02() {
		 try {
			 testDA.removeUser("registered");
			 testDA.removeUser("mikel");
			 testDA.removeUser("markel");
			 testDA.removeUser("andrea");
			 testDA.removeUser("admin");
			 sut.rankingLortu().get(0).getUsername();
			 fail();
		 }
		 catch(Exception e) {
			 System.out.println("Test02");
			 e.printStackTrace();
			 assertTrue(true);
		 }
	 }
	
}
