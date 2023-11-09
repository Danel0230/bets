package gui;

public class BLFacadeFactory {
 public static BLFacadeInterface createBLfacade( boolean isLocal) {
	 if(isLocal) {return new BLFAcadeLocal();}
	 if(!isLocal) {return new BLFacadeRemote();}
	 return null;
		
 }
}
