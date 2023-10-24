import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.junit.Test;

import configuration.UtilDate;
import dataAccess.DataAccess;
import dataAccess.DataAccessEmaitzakIpini;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Question;
import domain.Quote;
import exceptions.EventNotFinished;
import test.dataAccess.TestDataAccess;

public class EmaitzakIpini2DAB {
	 //sut:system under test
	 static DataAccessEmaitzakIpini sut=new DataAccessEmaitzakIpini();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();

	
	@Test
	//EventNotFinished excepzioa altxatu behar du.
	public void test1() {
		Quote q= null;
		testDA.open();
		q= testDA.test12hasieraketa("betis vs osasuna", UtilDate.newDate(3002, 1,2), "quien ganara?", 1,2.0,"1");
		testDA.close();

		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate=null;;
		try {
			oneDate = sdf.parse("05/10/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		try{
		
		sut.EmaitzakIpini(q);
		
		fail();
		}catch(EventNotFinished e){
			assertTrue(true);
		}
	}
	
	@Test
	//irabaziak eta galerak batera
	public void test2() {
		Quote q= null;
		Quote q1= null;
		Question que= null;
		Vector<ApustuAnitza> apustuak;
		testDA.open();
		q= testDA.test4hasieraketa("betis vs osasuna", UtilDate.newDate(1002, 1,2), "quien ganara?", 1,2.0,"1","2");
		testDA.close();

		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate=null;;
		try {
			oneDate = sdf.parse("05/10/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			sut.EmaitzakIpini(q);
			

			que= q.getQuestion();
			Question quest= sut.findQuestionFromQuote(q);
			for(Quote qberri: sut.findQuote(quest) ) {
				if(qberri.getQuoteNumber()==q.getQuoteNumber()) {
					for(Apustua apu: qberri.getApustuak())  {
							if(apu.getEgoera()!="irabazita") {
								fail();
						}
					}
				}else {
					for(Apustua apu: qberri.getApustuak())  {
						if(apu.getEgoera()!="galduta") {
							fail();
						}
					}
				}
			}
			assertTrue(true);
			
		} catch (EventNotFinished e) {
			// TODO Auto-generated catch block
			fail();
		}
}
/*	@Test
	//null bidalita errorea jaso
	public void test3() {
		Quote q= null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate=null;;
		try {
			oneDate = sdf.parse("05/10/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			sut.EmaitzakIpini(q);
			fail();
		} catch (EventNotFinished e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		} catch(IllegalArgumentException e) {
			assertTrue(true);
		}

}*/
}


