package businessLogic;

import java.util.Date;
import java.util.Vector;

import javax.jws.WebMethod;

import com.sun.tools.javac.util.List;

public class getEventsDemo {
	
	public getEventsDemo() {
	}

	@WebMethod	
	public ExtendedIterator<Integer> getEvents()  {
		Vector<Integer>  events= this.getDemoEvents();
		ExtendedIterator<Integer> it = new ExtendedIteratorImpl<Integer>(events);
		return it;
	}
	
	private Vector<Integer> getDemoEvents(){
		Vector<Integer> ret = new Vector<Integer>();
		ret.add(1);
		ret.add(2);
		ret.add(3);
		ret.add(4);
		ret.add(5);
		ret.add(6);
		ret.add(7);
		ret.add(8);
		ret.add(9);		
		return ret;
	}
	
	public static void main(String[] args) {
		getEventsDemo demo = new getEventsDemo();
		ExtendedIterator<Integer> it = demo.getEvents();
		
		//Lehenengotik azkenengora:
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		it.goLast();
		System.out.println("\n\nBREAK\n\n");
		
		//Azkenengotik lehenengora:
		while(it.hasPrevious()) {
			System.out.println(it.previous());
		}
	}
}

