package gui;

import java.awt.Color;
import java.net.URL;
import java.util.Locale;

import javax.swing.UIManager;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccess;

public class ApplicationLauncher { 
	
	
	
	public static void main(String[] args) {

		ConfigXML c=ConfigXML.getInstance();
		//isgsiu
	
		System.out.println(c.getLocale());
		
		Locale.setDefault(new Locale(c.getLocale()));
		
		System.out.println("Locale: "+Locale.getDefault());
		
		BLFacadeInterface BLF= BLFacadeFactory.createBLfacade(c.isBusinessLogicLocal());
		
		MainGUI a=new MainGUI();
		a.setVisible(false);
		MainGUI.setBussinessLogic(BLF);
		
		MainUserGUI b = new MainUserGUI(); 
		b.setBussinessLogic(BLF);
		b.setVisible(true);
		

		
		//a.pack();


	}

}
