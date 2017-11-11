package assignment2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

//class designed by Haoran Zhou 
public class Data implements Initializable{
	//Ath[][]ID    Name   Age  State  Point Type
	private String[][] Ath = new String[32][6];
	@FXML
	private ListView athlist;
	@FXML
	private Button btback;
	

	//draw option 5 GUI
	public void loadGUI5() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Menu.class.getResource("fxml/showathlete.fxml"));
		Pane pane = loader.load();
		Scene scene = new Scene(pane,600,450);	
		Stage showall = new Stage();
		showall.setTitle("All athletes information");
		showall.initModality(Modality.WINDOW_MODAL);
		showall.initOwner(Menu.getPrimaryStage());
		showall.setScene(scene);	
		showall.show();
	}
	
	//load athletes from txt file
	public void load() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("Athletes.txt"));
		String readline;
		readline=br.readLine();
		ObservableList<String> list = FXCollections.observableArrayList();
		list.add("ID     Name    Age  State  Point Type\n");
		int j=0;
		while(readline!=null){
			StringTokenizer st = new StringTokenizer(readline," ");
			for(int i=0;i<6;i++)
			{
				Ath[j][i]=st.nextToken();
					
			}
			readline=br.readLine();	
			if(readline!=null)
				list.add(readline);
			j++;
		}	
		br.close();
		//set athletes on the ListView
		athlist.setItems(list);
	}
	//back button
	public void back(){
		btback.getScene().getWindow().hide();
	}
	
	public String[][] getATH(){
		return Ath;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
