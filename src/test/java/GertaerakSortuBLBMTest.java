import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GertaerakBLBMTest {
     DataAccess dataAccess=Mockito.mock(DataAccess.class);
     Sport mockedSport=Mockito.mock(Sport.class);
	
	@InjectMocks
	 BLFacade sut=new BLFacadeImplementation(dataAccess);
	
	//sut.createQuestion:  The event has one question with a queryText. 

	
	@Test
	public void test1() {
			try {
				//define paramaters
				String spo="proba galdera";
				String desc= "Alaves-Deportivo";
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date oneDate=null;;
				try {
					oneDate = sdf.parse("05/10/2022");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
				//configure Mock
				Mockito.doReturn(false).when(dataAccess).gertaeraksortu(Mockito.any(String.class),Mockito.any(Date.class), Mockito.any(Sport.class));

				

				//invoke System Under Test (sut) 
				Question q=sut.gertaeraksortu(desc,oneDate,mockedSport);
				
				//verify the results			
				ArgumentCaptor<String> descCaptor = ArgumentCaptor.forClass(String.class);
				ArgumentCaptor<Date> dateCaptor = ArgumentCaptor.forClass(Date.class);
				ArgumentCaptor<Sport> sportCaptor = ArgumentCaptor.forClass(Sport.class);
				
				Mockito.verify(dataAccess,Mockito.times(1)).gertaerakSortu(descCaptor.capture(),dateCaptor.capture(), sportCaptor.capture());

				
				assertEquals(StringCaptor.getValue(),desc);
				assertEquals(dateCaptor.getValue(),oneDate);
				assertEquals(sport.getValue(),mockedSport);

				} catch (EventFinished e) {
				    fail();
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }
	@Test
	public void test3() {
		try {
			//define paramaters
			String desc= "Alaves-Deportivo"
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			//configure Mock
			Mockito.doReturn(null).when(dataAccess).gertaeraksortu(Mockito.any(String.class),Mockito.any(Date.class), Mockito.any(Sport.class));
			

			//invoke System Under Test (sut) 
			boolean b =sut.gertaeraksortu(desc,oneDate,null);
			
			//verify the results
			Mockito.verify(dataAccess,Mockito.times(1)).gertaeraksortu(Mockito.any(String.class),Mockito.any(Date.class), Mockito.any(Sport.class));
			
			assertTrue(b==null);
			
			fail();
			} catch (EventFinished e) {
			    fail();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
	@Test
	public void test7() {
		try {
			//define paramaters
			String desc= "Alaves-Deportivo"
			
			//configure Mock
			Mockito.doReturn(null).when(dataAccess).gertaeraksortu(Mockito.any(String.class),Mockito.any(Date.class), Mockito.any(Sport.class));			

			//invoke System Under Test (sut) 
			
			sut.gertaeraksortu(desc,null,mockedSport);
			
			//if the program continues fail
		    fail();
			} catch (EventFinished e) {
				// if the program goes to this point fail
			    fail();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
	
	
	
	
		
}
