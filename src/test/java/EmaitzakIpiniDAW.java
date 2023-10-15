import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
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
import domain.Team;
import domain.Transaction;
import exceptions.EventNotFinished;
import test.dataAccess.TestDataAccess;

public class EmaitzakIpiniDAW {
	 //sut:system under test
	 static DataAccess sut=new DataAccess();
	 
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
	//irabaziak bakarrik
	public void test2() {
		Quote q= null;
		Vector<ApustuAnitza> apustuak;
		testDA.open();
		q= testDA.test12hasieraketa("betis vs osasuna", UtilDate.newDate(1002, 1,2), "quien ganara?", 1,2.0,"1");
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
			

			Question que= q.getQuestion();
			Question quest= sut.findQuestionFromQuote(q);
			for(Quote qberri: sut.findQuote(quest) ) {
				for(Apustua apu: qberri.getApustuak())  {
						if(apu.getEgoera()!="irabazita") {
							fail();
						}	
					}
				}
			assertTrue(true);
			
		} catch (EventNotFinished e) {
			// TODO Auto-generated catch block
			fail();
		}

	}

	@Test
	//galerak bakarrik, errorea du galduta markatu funtzioak apustua quota bati dagokien bakarrik konprobatzen du emaitza kontuan hartu gabe
	public void test3() {
		Quote q= null;
		Quote q1= null;
		Question que= null;
		Vector<ApustuAnitza> apustuak;
		testDA.open();
		q= testDA.test3hasieraketa("betis vs osasuna", UtilDate.newDate(1002, 1,2), "quien ganara?", 1,2.0,"1","2");
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
				for(Apustua apu: qberri.getApustuak())  {
						if(apu.getEgoera()!="galduta") {
							fail();
						}	
					}
				}
			assertTrue(true);
			
		} catch (EventNotFinished e) {
			// TODO Auto-generated catch block
			fail();
		}

	}
	
	@Test
	//irabaziak eta galerak
	public void test4() {
		Quote q= null;
		Quote q1= null;
		Question que= null;
		Vector<ApustuAnitza> apustuak;
		testDA.open();
		q= testDA.test4hasieraketa("betis vs osasuna", UtilDate.newDate(1002, 1,2), "quien ganara?", 1,2.0,"1","2");
		testDA.close();
		System.out.println(q.getQuoteNumber());

		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date oneDate=null;;
		try {
			oneDate = sdf.parse("05/10/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(q.getQuoteNumber()+"real");
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
	
}