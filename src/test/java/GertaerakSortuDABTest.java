import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import configuration.ConfigXML;
import dataAccess.DataAccessInterface;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import test.businessLogic.TestFacadeImplementation;
import test.dataAccess.TestDataAccess;

public class GertaerakSortuDABTest {
 
	 //sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();

	private Event ev;
	
	
	@test
	public void test1(){// Kirola datu basean, data horretarako gertaerak datu basean
		try {
			String description= "Alaves-Deportivo";
			String spo= "futbol";
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			testDA.open();
			ev = testDA.addEventWithSport(description,oneDate,spo);
			testDA.close();
			
			boolean emaitza= sut.gertaeraksortu(description, oneDate,spo);
			assertEquals(false, emaitza);
			
		}finally{
			testDA.open();
	         boolean b=testDA.removeEvent(ev);
	          testDA.close();
	         System.out.println("Finally "+b);          
	        }
		}
	@test
	public void test2() {//data null da
		try {
		String description= "Alaves-Deportivo";
		String spo= "futbol";
		
		boolean emaitza= sut.gertaeraksortu(description, null,spo);
		
		assertTrue(q==null);
		
		
	   } catch  {
		// if the program goes to this point fail  
		fail();
		} 
	}
	@test
	public void test3() {//sport null da
		try {
		String description= "Alaves-Deportivo";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate=null;;
		try {
			oneDate = sdf.parse("05/10/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		boolean emaitza= sut.gertaeraksortu(description, oneDate,null);
		
		assertTrue(q==null);
		
		
	   } catch  {
		// if the program goes to this point fail  
		fail();
		} 
	}
	
	
		
	
	
}