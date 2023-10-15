import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Registered;

@RunWith(MockitoJUnitRunner.class)
public class RankingLortuBLBMTest {

	@Mock
     DataAccess dataAccess;

	@InjectMocks
	 BLFacade sut=new BLFacadeImplementation(dataAccess);
	
	@Test
	public void test1() {
		try {
			//Ranking normala itzultzen du:
			ArrayList<Registered> expectedEma = new ArrayList<Registered>();
			expectedEma.add(new Registered("arantxa", "1234", 1111));
			expectedEma.add(new Registered("markel", "pass123", 1111));
			expectedEma.add(new Registered("andoni", "qwerty", 1111));
			
			ArrayList<Registered> expectedMocked = new ArrayList<Registered>();
			expectedMocked.add(new Registered("arantxa", "1234", 1111));
			expectedMocked.add(new Registered("markel", "pass123", 1111));
			expectedMocked.add(new Registered("andoni", "qwerty", 1111));
			
			Mockito.doReturn(expectedMocked).when(sut).rankingLortu();
			
			assertEquals(expectedEma, sut.rankingLortu());
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
