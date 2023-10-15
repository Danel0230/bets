import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.Transaction;
import exceptions.EventFinished;
import exceptions.EventNotFinished;
import exceptions.QuestionAlreadyExist;

@RunWith(MockitoJUnitRunner.class)
public class EmaitzakIpiniBLBMTest {

	@Mock
     DataAccess dataAccess=Mockito.mock(DataAccess.class);

	
	@InjectMocks
	 BLFacade sut=new BLFacadeImplementation(dataAccess);
	
	@Test
	//EventNotFinished altxatu behar du
	public void test1() {
			try {
				//define paramaters
				Quote q= new Quote(2.0,"1",new Question("proba",2.0,null));
				EventNotFinished enf= new EventNotFinished();
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date oneDate=null;;
				try {
					oneDate = sdf.parse("05/10/2022");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
				//configure Mock
				Mockito.doThrow(enf).when(dataAccess).EmaitzakIpini(q);
				
				

				//invoke System Under Test (sut)
				try {
					sut.EmaitzakIpini(q);
					fail();
				}catch(EventNotFinished e){
					assertTrue(true);
				}
			}catch (Exception e) {
			    fail();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				
			   }

	@Test
	//sut.createQuestion:  The event is null.
	public void test3() {

		try {
			//define paramaters
		    Question que=new Question("proba", 2.0, null);
			Quote qu= new Quote(2.0,"1",que);
			Quote qu1 =new Quote(2.0,"2",que);
			
			ApustuAnitza apA1 = new ApustuAnitza(null, 5.0);
			ApustuAnitza apA2 = new ApustuAnitza(null, 5.0);
			Apustua ap1 = new Apustua(apA1, qu);
			Apustua ap2 = new Apustua(apA2, qu1);
			apA2.addApustua(ap2);
			apA1.addApustua(ap1);
			ap1.setApustuAnitza(apA1);
			ap2.setApustuAnitza(apA2);
			qu.addApustua(ap1);
			qu1.addApustua(ap2);
		    que.listaraGehitu(qu);
		    que.listaraGehitu(qu1);
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			//configure Mock
			Mockito.doReturn(que).when(dataAccess).EmaitzakIpini(qu);
			for(Quote qberri: que.getQuotes() ) {
				if(qberri.getQuoteNumber()==qu.getQuoteNumber()) {
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

			//invoke System Under Test (sut)
			sut.EmaitzakIpini(qu);
			try {
				sut.EmaitzakIpini(qu);
				fail();
			}catch(EventNotFinished e){
				fail();
			}
		}catch (Exception e) {
		    fail();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
		   }
	@Test
	public void test7() {
		try {
			//define paramaters
			Quote q=null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			//configure Mock
			Mockito.doThrow(new IllegalArgumentException()).when(dataAccess).EmaitzakIpini(q);			

			//invoke System Under Test (sut) 
				sut.EmaitzakIpini(q);
				fail();
			} catch (EventNotFinished e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail();
			} catch(IllegalArgumentException e) {
				assertTrue(true);
			}

	}
	
	
	
		
}


