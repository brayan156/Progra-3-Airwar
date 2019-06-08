package Loops;


import java.util.TimerTask;

import GameElements.AirPort;
import Interface.Controller;
import Listas.NodoList;
import javafx.scene.layout.AnchorPane;


public class TimerGenerate  extends  TimerTask{
	private AnchorPane anchor = new AnchorPane();
    private NodoList<AirPort> lista = Controller.background.getAirports();

    
	public TimerGenerate(AnchorPane mapAnchorPane) {
		anchor = mapAnchorPane;	
		run();
	}


	@Override
	public void run(){ 
		toDo();
	}
	
	
	private void toDo(){
		int i = 0;
		while(i<lista.getLargo()) {
//			System.out.println("\ni :"+i);
			AirPort airport = lista.get(i);
//			System.out.println("AirPort : "+airport.getPosx()+" "+airport.getPosy());
			boolean check = airport.generatePlane(anchor);
			if (!check) {i++; continue;}
			System.out.println("Check : "+check);
		    System.out.println("Plane Succesfully Created");
			return;
		}
	}
	
}