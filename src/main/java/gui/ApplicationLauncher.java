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
		System.out.println(c.getLocale());
		Locale.setDefault(new Locale(c.getLocale()));
		System.out.println("Locale: "+Locale.getDefault());
		MainGUI a=new MainGUI();
		a.setVisible(false);
		try {
			BLFacade BLFacadeInterface;
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			if (c.isBusinessLogicLocal()) {
					DataAccess da= new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
					BLFacadeInterface=new BLFacadeImplementation(da);
			}
			else { //If remote
				String serviceName= "http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+ "/ws/"+c.getBusinessLogicName()+"?wsdl";

				URL url = new URL(serviceName);
				QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");
				Service service = Service.create(url, qname);
				BLFacadeInterface = service.getPort(BLFacade.class);
			}
			
			MainGUI.setBussinessLogic(BLFacadeInterface);
			MainUserGUI b= new MainUserGUI();
			b.setVisible(true);
			MainUserGUI.setBussinessLogic(BLFacadeInterface);
			
		}catch (Exception e) {
			a.jLabelSelectOption.setText("Error: "+e.toString());
			a.jLabelSelectOption.setForeground(Color.RED);
			System.out.println("Error in ApplicationLauncher: "+e.toString());
		}
	}

}
