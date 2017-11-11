package assignment2;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;


//class designed by Haoran Zhou 
public class Controller {

    private Menu m = new Menu();
	//open select menu stage and wait for operation
    @FXML
    private Button bt3;
    @FXML 
    private Button bt2;
    //option 1 to select a game
	public void Cselect() throws IOException{
		m.getIni().loadGUI1();	
		//reset the prediction check
		bt2.setText("2.View Prediction");
		bt3.setText("3.Start the Game");
	}
	//option 2 to load what have been chose
	public void Cviewp() throws IOException{
		//check whether the game and prediction exist
		if(m.getIni().getCheckSelect()==false)
		{
			bt2.setText("Select game first");
		}
		else
		{
		m.getPre().presettext(m.getIni().checkPredict());
		m.getPre().loadGUI2();
		}
	}
	//option 5 to load all athletes
	public void showATH() throws IOException{
		m.getData().loadGUI5();
	}
	//option 3 to start game
	public void start() throws IOException{
		if(m.getIni().getCheckSelect()==false)
		{
			bt3.setText("Select game first");
		}
		else 
		m.getGame().loadGUI3();
		
	}
	
	public void Cexit(){
		Menu.closePrimaryStage();;		
	}
	
	public void showGame() throws IOException{
		m.getGame().loadGUI4();
	}
	
	
}
