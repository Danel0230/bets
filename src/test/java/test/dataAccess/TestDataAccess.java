package test.dataAccess;
import java.util.Calendar;
import dataAccess.DataAccess;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.Sport;
import domain.Team;
import domain.Transaction;

public class TestDataAccess {
	protected  EntityManager  db;
	protected  EntityManagerFactory emf;

	ConfigXML  c=ConfigXML.getInstance();


	public TestDataAccess()  {
		
		System.out.println("Creating TestDataAccess instance");

		open();
		
	}

	
	public void open(){
		
		System.out.println("Opening TestDataAccess instance ");

		String fileName=c.getDbFilename();
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		
	}
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	public boolean removeEvent(Event ev) {
		System.out.println(">> DataAccessTest: removeEvent");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e!=null) {
			db.getTransaction().begin();
			db.remove(e);
			db.getTransaction().commit();
			return true;
		} else 
		return false;
    }
		
		public Event addEventWithQuestion(String desc, Date d, String question, float qty) {
			System.out.println(">> DataAccessTest: addEvent");
			Event ev=null;
				db.getTransaction().begin();

				try {
				    ev=new Event(desc,d,null, null);
				    ev.addQuestion(question, qty);
					db.persist(ev);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return ev;
	    }
	public Event addEventWithSport(String desc, Date d, String sport) {
			System.out.println(">> DataAccessTest: addEvent");
			Event ev= null;
			Sport spo= null;
				db.getTransaction().begin();
				
				try {
					ev=new Event(desc,d,null, null);
					spo= new Sport(sport);
					ev.setSport(spo);
					db.persist(ev);
					db.getTransaction().commit();
				}catch (Exception e){
					e.printStackTrace();
				}
				return ev;
		}
		
		public Quote test12hasieraketa(String desc, Date d, String question, float qty,double balio,String forecast) {
			System.out.println(">> DataAccessTest: addEvent");
			Event ev=null;
			Question q=null;
			Quote qu= null;
				db.getTransaction().begin();

				try {
				    ev=new Event(desc,d,null, null);
				    q=new Question(question, qty,ev);
				    Vector<Question> galderak= ev.getQuestions();
				    galderak.add(q);
				    ev.setQuestions(galderak);
				    qu=new Quote(balio, forecast, q);
				    q.listaraGehitu(qu);
				    
				    
					Registered reg1 =new Registered();
					Registered reg2 =new Registered();
					reg1.setUsername("test1");
					reg2.setUsername("test2");
					ApustuAnitza apA1 = new ApustuAnitza(reg1, 5.0);
					ApustuAnitza apA2 = new ApustuAnitza(reg2, 5.0);
					apA1.setUser(reg1);
					apA2.setUser(reg2);
					Apustua ap1 = new Apustua(apA1, qu);
					Apustua ap2 = new Apustua(apA2, qu);
					apA2.addApustua(ap2);
					apA1.addApustua(ap1);
					ap1.setApustuAnitza(apA1);
					ap2.setApustuAnitza(apA2);
					qu.addApustua(ap1);
					qu.addApustua(ap2);
					
					Transaction t1 = new Transaction(reg1, apA1.getBalioa(), new Date(), "ApustuaEgin");
					Transaction t3 = new Transaction(reg2, apA2.getBalioa(), new Date(), "ApustuaEgin");
					reg1.setTransakzioak(new Vector<Transaction>());
					reg2.setTransakzioak(new Vector<Transaction>());
					reg1.addTransaction(t1);
					reg2.addTransaction(t3);
					reg1.setDirukop(200.5);
					reg2.setDirukop(200.5);
				    

					db.persist(apA1);
					db.persist(apA2);
					//db.persist(reg1);
					//db.persist(reg2);
					db.persist(ap1);
					db.persist(ap2);
					db.persist(ev);
					db.persist(q);
					db.persist(qu);
					db.persist(t3);
					db.persist(t1);
					
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return qu;
	    }
		
		public Quote test3hasieraketa(String desc, Date d, String question, float qty,double balio,String forecast,String forecast1) {
			System.out.println(">> DataAccessTest: addEvent");
			Event ev=null;
			Question q=null;
			Quote qu= null;
			Quote qu1= null;
			Apustua ap1 =null;
			Apustua ap2 =null;
				db.getTransaction().begin();

				try {
				    ev=new Event(desc,d,null, null);
				    q=new Question(question, qty,ev);
				    Vector<Question> galderak= ev.getQuestions();
				    galderak.add(q);
				    ev.setQuestions(galderak);
				    qu=new Quote(balio, forecast, q);
				    qu1=new Quote(balio, forecast1,q);

				    
				    
					Registered reg1 =new Registered();
					Registered reg2 =new Registered();
					reg1.setUsername("test1");
					reg2.setUsername("test2");
					ApustuAnitza apA1 = new ApustuAnitza(reg1, 5.0);
					ApustuAnitza apA2 = new ApustuAnitza(reg2, 5.0);
					apA1.setUser(reg1);
					apA2.setUser(reg2);
					ap1 = new Apustua(apA1, qu1);
					ap2 = new Apustua(apA2, qu1);
					apA2.addApustua(ap2);
					apA1.addApustua(ap1);
					ap1.setApustuAnitza(apA1);
					ap2.setApustuAnitza(apA2);
					qu1.addApustua(ap1);
					qu1.addApustua(ap2);
					ap1.setKuota(qu1);
					ap2.setKuota(qu1);
					
					Transaction t1 = new Transaction(reg1, apA1.getBalioa(), new Date(), "ApustuaEgin");
					Transaction t3 = new Transaction(reg2, apA2.getBalioa(), new Date(), "ApustuaEgin");
					reg1.setTransakzioak(new Vector<Transaction>());
					reg2.setTransakzioak(new Vector<Transaction>());
					reg1.addTransaction(t1);
					reg2.addTransaction(t3);
					reg1.setDirukop(200.5);
					reg2.setDirukop(200.5);
				    
				    q.listaraGehitu(qu1);
				    q.listaraGehitu(qu);


					db.persist(apA1);
					db.persist(apA2);
					//db.persist(reg1);
					//db.persist(reg2);
					db.persist(ap1);
					db.persist(ap2);
					db.persist(ev);
					db.persist(q);
					db.persist(qu1);
					db.persist(qu);
					db.persist(t3);
					db.persist(t1);
					
					db.getTransaction().commit();
					

					ap1.setKuota(qu1);
					ap2.setKuota(qu1);
				    System.out.println(ap1.getKuota().getQuoteNumber()+"hasi");
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return qu;
	    }

		public Quote test4hasieraketa(String desc, Date d, String question, float qty,double balio,String forecast,String forecast1) {
			System.out.println(">> DataAccessTest: addEvent");
			Event ev=null;
			Question q=null;
			Quote qu= null;
			Quote qu1= null;
			Apustua ap1 =null;
			Apustua ap2 =null;
				db.getTransaction().begin();

				try {
				    ev=new Event(desc,d,null, null);
				    q=new Question(question, qty,ev);
				    Vector<Question> galderak= ev.getQuestions();
				    galderak.add(q);
				    ev.setQuestions(galderak);
				    qu=new Quote(balio, forecast, q);
				    qu1=new Quote(balio, forecast1,q);

				    
				    
					Registered reg1 =new Registered();
					Registered reg2 =new Registered();
					reg1.setUsername("test1");
					reg2.setUsername("test2");
					ApustuAnitza apA1 = new ApustuAnitza(reg1, 5.0);
					ApustuAnitza apA2 = new ApustuAnitza(reg2, 5.0);
					apA1.setUser(reg1);
					apA2.setUser(reg2);
					ap1 = new Apustua(apA1, qu);
					ap2 = new Apustua(apA2, qu1);
					apA2.addApustua(ap2);
					apA1.addApustua(ap1);
					ap1.setApustuAnitza(apA1);
					ap2.setApustuAnitza(apA2);
					qu.addApustua(ap1);
					qu1.addApustua(ap2);
					ap1.setKuota(qu);
					ap2.setKuota(qu1);
					
					Transaction t1 = new Transaction(reg1, apA1.getBalioa(), new Date(), "ApustuaEgin");
					Transaction t3 = new Transaction(reg2, apA2.getBalioa(), new Date(), "ApustuaEgin");
					reg1.setTransakzioak(new Vector<Transaction>());
					reg2.setTransakzioak(new Vector<Transaction>());
					reg1.addTransaction(t1);
					reg2.addTransaction(t3);
					reg1.setDirukop(200.5);
					reg2.setDirukop(200.5);
				    
				    q.listaraGehitu(qu);
				    q.listaraGehitu(qu1);



					db.persist(apA1);
					db.persist(apA2);
					//db.persist(reg1);
					//db.persist(reg2);
					db.persist(ap1);
					db.persist(ap2);
					db.persist(ev);
					db.persist(q);
					db.persist(qu1);
					db.persist(qu);
					db.persist(t3);
					db.persist(t1);
					
					db.getTransaction().commit();
					

					ap1.setKuota(qu);
					ap2.setKuota(qu1);
				    System.out.println(ap1.getKuota().getQuoteNumber()+"hasi");
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return qu;
	    }
		
		
		public Quote addQuoteAndBets(double balio,String forecast, Question que) {
			System.out.println(">> DataAccessTest: addQuote");

			Quote q=null;
			db.getTransaction().begin();
			try {
			Question quest= db.find(Question.class, que);
			q= new Quote(balio,forecast,quest);
			quest.listaraGehitu(q);
			
			Registered reg1 =new Registered();
			Registered reg2 =new Registered();
			reg1.setUsername("test1");
			reg2.setUsername("test2");
			ApustuAnitza apA1 = new ApustuAnitza(reg1, 5.0);
			ApustuAnitza apA2 = new ApustuAnitza(reg2, 5.0);
			apA1.setUser(reg1);
			apA2.setUser(reg2);
			Apustua ap1 = new Apustua(apA1, q);
			Apustua ap2 = new Apustua(apA2, q);
			apA2.addApustua(ap2);
			apA1.addApustua(ap1);
			ap1.setApustuAnitza(apA1);
			ap2.setApustuAnitza(apA2);
			q.addApustua(ap1);
			q.addApustua(ap2);
			
			Transaction t1 = new Transaction(reg1, apA1.getBalioa(), new Date(), "ApustuaEgin");
			Transaction t3 = new Transaction(reg2, apA2.getBalioa(), new Date(), "ApustuaEgin");
			reg1.setTransakzioak(new Vector<Transaction>());
			reg2.setTransakzioak(new Vector<Transaction>());
			reg1.addTransaction(t1);
			reg2.addTransaction(t3);
			reg1.setDirukop(200.5);
			reg2.setDirukop(200.5);
		    

			db.persist(apA1);
			db.persist(apA2);
			//db.persist(reg1);
			//db.persist(reg2);
			db.persist(ap1);
			db.persist(ap2);
			db.persist(q);
			db.persist(quest);
			db.persist(t3);
			db.persist(t1);
			db.getTransaction().commit();
		}catch (Exception e){
			e.printStackTrace();
		}
			return q;
		}
		
		public Quote addEventWithQuestionAndQuote(String desc, Date d, String question, float qty,double balio,String forecast) {
			System.out.println(">> DataAccessTest: addEvent");
			Event ev=null;
			Question q=null;
			Quote qu= null;
				db.getTransaction().begin();

				try {
				    ev=new Event(desc,d,null, null);
				    q=new Question(question, qty,ev);
				    Vector<Question> galderak= ev.getQuestions();
				    galderak.add(q);
				    ev.setQuestions(galderak);
				    qu=new Quote(balio, forecast, q);
				    q.listaraGehitu(qu);
					db.persist(ev);
					db.persist(q);
					db.persist(qu);
					db.getTransaction().commit();
				}catch (Exception e){
					e.printStackTrace();
				}
					return qu;
				}
		
		
		
		public boolean existQuestion(Event ev,Question q) {
			System.out.println(">> DataAccessTest: existQuestion");
			Event e = db.find(Event.class, ev.getEventNumber());
			if (e!=null) {
				return e.DoesQuestionExists(q.getQuestion());
			} else 
			return false;
			
		}
		
		public void createUser(String username, String password) {
			db.getTransaction().begin();
			Registered reg1 = new Registered(username, password, 1111);	
			db.getTransaction().commit();
			db.close();
		}
		
		public void removeUser(String pId) throws Exception {
			try {
				db.getTransaction().begin();
				db.remove(db.find(Registered.class, pId));				
			}
			catch(Exception e) {
				throw new Exception(e);
			}
			finally {
				db.getTransaction().commit();
				db.close();
			}
		}
}

