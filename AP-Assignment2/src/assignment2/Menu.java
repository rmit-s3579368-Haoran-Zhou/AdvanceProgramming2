package assignment2;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
//class designed by Haoran Zhou 
public class Menu extends Application{

	private static Stage primaryStage;
	//unify the access of object through class Menu
	private Data data = new Data();
	private Game game = new Game();
	private Initial ini = new Initial();
	private Prediction pre = new Prediction();
	
	public Data getData(){
		return this.data;
	}
	public Game getGame(){
		return this.game;
	}
	public Initial getIni(){
		return this.ini;
	}
	public Prediction getPre(){
		return this.pre;
	}
	
	
	public static void main(String args[]){		
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Menu.setPrimaryStage(primaryStage);
		ShowMenu();
		
	}
	//load the menu fxml
	private void ShowMenu() throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("fxml/menu.fxml"));
		Scene menu = new Scene(root,600,600);
		getPrimaryStage().setScene(menu);
		getPrimaryStage().setTitle("Ozlympic Games");
		getPrimaryStage().show();
		
	}


	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void setPrimaryStage(Stage primaryStage) {
		Menu.primaryStage = primaryStage;
	}
	
	public static void closePrimaryStage() {
		Menu.primaryStage.close();
	}
	
}
