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
		AirPort airport = Controller.background.searchAirPort();
		if (airport==null) return;
		airport.generatePlane(anchor);
		airport.print();
//		System.out.println("Plane Succesfully Created");
		return;
	}
	
}