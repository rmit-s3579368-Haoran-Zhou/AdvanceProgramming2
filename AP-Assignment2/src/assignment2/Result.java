package assignment2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
//class designed by Haoran Zhou 
public class Result implements Initializable{

	@FXML
	private Text txtresult;
	@FXML
	private ListView resultlist;
	@FXML
	private Button btback;
	//preset the information for the game result
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Initial ini=new Initial();
		Game g = new Game();	
		if(ini.getpredict().equals(g.getWinID()))
			txtresult.setText("Congratulations! You Win");
		else
			txtresult.setText("You lose! Try again");
		
		ObservableList<String> list = FXCollections.observableArrayList();
		list.add("ID  Name  Time  Rank");
				for(int i=0;i<8;i++)
					list.add(g.getGameinfo()[i][0]+" "+g.getGameinfo()[i][1]+" "+g.getGameinfo()[i][2]+" "+g.getGameinfo()[i][3]);
				resultlist.setItems(list);
	}
	//back button on the show result page
	public void back(){
		btback.getScene().getWindow().hide();
	}
}
