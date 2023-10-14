	@RunWith (MockitoJUnitRunner.class)
	
	
	public class GertaerakSortuDAWTest {
		@Mock
		DataAccessInterface DAO;
		@InjectMocks
		Utils sut;
		
		@Test 
		public void test1() {// Kirola data basean dago, eta data horretarako deskripzio berarekin dago gertaera bat
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			try {
				// Mockito konfiguratu
				Mockito.doReturn(new Event("Alaves-Deportivo",oneDate,new team("Alaves"), new team("Deportivo") )).when(DAO)
				.getEvents(oneDate);
				Mockito.doReturn(true).when(DAO).isInDb(Mockito.any(Sport.class));
				// Sistema probatu
				boolean esperotakoa= false;
				boolean emaitza = sut.GertaerakSortu("Alaves-Deportivo",oneDate,"Futbol"),
				// Emaitza konprobatu
				assertEquals(esperotakoa, emaitza);
			} catch (Exception e) {
				fail();
			}
		}

		@Test
		public void test2() { // Kirola data basean dago data horretarako ez dago deskripzio horrekin gertaerarik
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				//Mockito konfiguratu
				Mockito.doReturn(new Event("Alaves-Deportivo",oneDate,new team("Alaves"), new team("Deportivo"))).when(DAO).getEvents(oneDate);
				Mockito.doReturn(true).when(DAO).isInDb(Mockito.any(Sport.class))
				//sistema Probatu
				boolean esperotakoa= true
				boolean emaitza= sut.GeraterakSortu("Alaves-Alegria",oneDate,"Futbol")
				// Emaitza konprobatu
				assertEquals(esperotakoa, emaitza);
			}catch{
				fail();
			}
		}
		
		@Test
		public void test3() {// Kirola ez dago data basea
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				//Mockito konfiguratu
				Mockito.doReturn(new Event("Alaves-Deportivo",oneDate,new team("Alaves"), new team("Deportivo"))).when(DAO).getEvents(oneDate);
				Mockito.doReturn(false).when(DAO).isInDb(Mockito.any(Sport.class))
				//sistema Probatu
				boolean esperotakoa= false
				boolean emaitza= sut.GeraterakSortu("Gasteiz-Jaca",oneDate,"Hockey")
				// Emaitza konprobatu
				assertEquals(esperotakoa, emaitza);
			}catch{
				fail();
			}
		}
		
		@test
		public void test4() {// Kirola badago datu basean baina data horretarako ez dago gertaerarik
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				//Mockito konfiguratu
				Mockito.doReturn(null).when(DAO).getEvents(oneDate);
				Mockito.doReturn(true).when(DAO).isInDb(Mockito.any(Sport.class))
				//sistema Probatu
				boolean esperotakoa= false
				boolean emaitza= sut.GeraterakSortu("Alaves-Alegria",oneDate,"Futbol")
				// Emaitza konprobatu
				assertEquals(esperotakoa, emaitza);
			}catch{
				fail();
			}
		}
		@test
		public void test5() {// Data basen ez dago ez kirola ezta data
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
			}
			try {
				//Mockito konfiguratu
				Mockito.doReturn(null).when(DAO).getEvents(oneDate);
				Mockito.doReturn(false).when(DAO).isInDb(Mockito.any(Sport.class))
				//sistema Probatu
				boolean esperotakoa= false
				boolean emaitza= sut.GeraterakSortu("Gasteiz-Jaca",oneDate,"Hockey")
				// Emaitza konprobatu
				assertEquals(esperotakoa, emaitza);
			}catch{
				fail();
			}
		}
	}
	