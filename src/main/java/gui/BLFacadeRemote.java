package gui;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import businessLogic.BLFacade;
import configuration.ConfigXML;

public class BLFacadeRemote implements BLFacadeInterface {
	public void negozioLogikaKargatu()  {
		try {
		ConfigXML c=ConfigXML.getInstance();
		BLFacade appFacadeInterface;
		String serviceName= "http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl";
		 
		//URL url = new URL("http://localhost:9999/ws/ruralHouses?wsdl");
		URL url = new URL(serviceName);

 
        //1st argument refers to wsdl document above
		//2nd argument is service name, refer to wsdl document above
//        QName qname = new QName("http://businessLogic/", "FacadeImplementationWSService");
        QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");
 
        Service service = Service.create(url, qname);

         appFacadeInterface = service.getPort(BLFacade.class);
		}catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
