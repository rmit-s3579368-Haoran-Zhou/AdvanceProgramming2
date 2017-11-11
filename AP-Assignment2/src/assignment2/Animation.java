package assignment2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
//class designed by Haoran Zhou 
public class Animation implements Initializable{

	@FXML
	private Circle a1;
	@FXML
	private Circle a2;
	@FXML
	private Circle a3;
	@FXML
	private Circle a4;
	@FXML
	private Circle a5;
	@FXML
	private Circle a6;
	@FXML
	private Circle a7;
	@FXML
	private Circle a8;
	@FXML
	private Text GameTypeTxt;
	@FXML
	private Text name1;
	@FXML
	private Text name2;
	@FXML
	private Text name3;
	@FXML
	private Text name4;
	@FXML
	private Text name5;
	@FXML
	private Text name6;
	@FXML
	private Text name7;
	@FXML
	private Text name8;
	
	private Game g = new Game();
	private Initial ini = new Initial();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		TranslateTransition[] t = new TranslateTransition[8];
		//set participants name on the scene
		name1.setText(g.getGameinfo()[0][1]);
		name2.setText(g.getGameinfo()[1][1]);
		name3.setText(g.getGameinfo()[2][1]);
		name4.setText(g.getGameinfo()[3][1]);
		name5.setText(g.getGameinfo()[4][1]);
		name6.setText(g.getGameinfo()[5][1]);
		name7.setText(g.getGameinfo()[6][1]);
		name8.setText(g.getGameinfo()[7][1]);
		for(int i=0;i<8;i++)
		{
		t[i]=new TranslateTransition();	
		t[i].setToX(580);
		//set the game type and show on the scene
		if(ini.getType().equals("runner"))
		{
			t[i].setDuration(Duration.seconds(g.getGameTime()[i]/5));
			GameTypeTxt.setText("Running");
		}
		else if(ini.getType().equals("cyclist")){
			t[i].setDuration(Duration.seconds(g.getGameTime()[i]/50));
			GameTypeTxt.setText("Cycling");
		}
		else {
			t[i].setDuration(Duration.seconds(g.getGameTime()[i]/250));
			GameTypeTxt.setText("Swimming");
		}
		}
		t[0].setNode(a1);
		t[1].setNode(a2);
		t[2].setNode(a3);
		t[3].setNode(a4);
		t[4].setNode(a5);
		t[5].setNode(a6);
		t[6].setNode(a7);
		t[7].setNode(a8);
		for(int i=0;i<8;i++)
		{
			t[i].play();
		}
	}
	//load game result fxml file
	public void result() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Menu.class.getResource("fxml/result.fxml"));
		Pane pane = loader.load();
		Scene scene = new Scene(pane,600,450);	
		Stage showgame = new Stage();
		showgame.setTitle("Result");
		showgame.initModality(Modality.WINDOW_MODAL);
		showgame.initOwner(Menu.getPrimaryStage());
		showgame.setScene(scene);	
		showgame.show();
		
	}
	
}
