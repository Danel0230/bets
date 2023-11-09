package gui;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccess;

public class BLFAcadeLocal implements BLFacadeInterface {

	public void negozioLogikaKargatu() {
		//In this option the DataAccess is created by FacadeImplementationWS
		//appFacadeInterface=new BLFacadeImplementation();

		//In this option, you can parameterize the DataAccess (e.g. a Mock DataAccess object)
		ConfigXML c=ConfigXML.getInstance();
		BLFacade appFacadeInterface;
		DataAccess da= new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
		appFacadeInterface=new BLFacadeImplementation(da);

	}
}
