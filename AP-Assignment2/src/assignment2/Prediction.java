package assignment2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
//class designed by Haoran Zhou 
public class Prediction implements Initializable{

	private static String content;
	
	@FXML
	private TextArea vptext;
	
	@FXML
	private Button vpback;
	
	//load option 2's fxml to check prediction
	public void loadGUI2() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Menu.class.getResource("fxml/viewprediction.fxml"));
		Pane P = loader.load();
		Scene scene = new Scene(P,400,300);	
		Stage SelGame = new Stage();
		SelGame.setTitle("View Prediction");
		SelGame.initModality(Modality.WINDOW_MODAL);
		SelGame.initOwner(Menu.getPrimaryStage());
		SelGame.setScene(scene);	
		SelGame.show();
	}
	
	//back button on the option2 page
	public void viewPback(){
		vpback.getScene().getWindow().hide();	
	}
	
	//preset the value of TextArea to check whether prediction exists
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if(content!=null)
			vptext.setText(content);
		else
			vptext.setText("Please select game and predict first!");
	}
	

	public void presettext(String checkPredict) {
		// TODO Auto-generated method stub
		content=checkPredict;
	}
}
