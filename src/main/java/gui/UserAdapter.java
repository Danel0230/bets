package gui;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Event;
import domain.Question;
import domain.Registered;

public class UserAdapter extends AbstractTableModel{

	private Vector<String> colnames;
	private Vector<Vector<String>> taula;
	
	private void hasieratu() {
		colnames = new Vector<String>();
		colnames.add("Event");
		colnames.add("Question");
		colnames.add("Event Date");
		colnames.add("Bet (€)");
		taula= new Vector<Vector<String>>();
		for (int i=0;i<4;i++) {
			taula.add(new Vector<String>());
		}
	}

	public int getRowCount() {
		return taula.size();
		}

	public int getColumnCount() {
		return taula.get(0).size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return taula.get(rowIndex).get(columnIndex);
	}
	
	public void addBet(String ev,String que, String date, String bal) {
		taula.get(0).add(ev);
		taula.get(1).add(que);
		taula.get(2).add(date);
		taula.get(3).add(bal);
	}

	public String getColumnName(int columnIndeex) {
		return colnames.get(columnIndeex);
	}
		
	public UserAdapter(Registered user) {
		this.hasieratu();
		for(ApustuAnitza apuan: user.getApustuAnitzak()) {
			Vector<Apustua> apu=apuan.getApustuak();
			for (Apustua ap: apu) {
				Question que=ap.getKuota().getQuestion();
				Event ev= que.getEvent();
				this.addBet(ev.getDescription(),que.getQuestion(),apuan.getData().toString(),apuan.getBalioa().toString());
			}
		}
	}

}