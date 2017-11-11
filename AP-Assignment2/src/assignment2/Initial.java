package assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
//class designed by Haoran Zhou 
public class Initial  implements Initializable{
	//Ath[][]0ID    1Name   2Age  3State  4Point 5Type
	private static String[][] Ath = new String[32][6];
	//Of ID Name Age Nation
	private static String[][] Of = new String[4][4];
	//participant[][]ID    Name   Age  State  Point Type
	private static String[][] participant = new String[8][6];
	//Of 0ID 1Name 2Age 3Nation
	private static String[][] selectOf = new String[1][4];
	private String genath;
	private static String Type="runner";
	private static String predict;
	private static boolean checkSeclect=false;
	@FXML
	private TextArea Rathlete;

	@FXML
	private TextArea Rofficial;
	
	@FXML
	private TextArea Cathlete;

	@FXML
	private TextArea Cofficial;
	
	@FXML
	private TextArea Sathlete;

	@FXML
	private TextArea Sofficial;
	
	@FXML
	private TextField PID;
	
	@FXML
	private TextField PID2;
	
	@FXML
	private TextField PID3;
	
	@FXML
	private Button Rsubmit;
	
	@FXML
	private Button Csubmit;
	
	@FXML
	private Button Ssubmit;
	//load Opion1's fxml
	public void loadGUI1() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Menu.class.getResource("fxml/select.fxml"));
		TabPane TP = loader.load();
		Scene scene = new Scene(TP,600,600);	
		Stage SelGame = new Stage();
		SelGame.setTitle("Game Select");
		SelGame.initModality(Modality.WINDOW_MODAL);
		SelGame.initOwner(Menu.getPrimaryStage());
		SelGame.setScene(scene);	
		SelGame.show();
	}

	//generate 8 participants from athletes list
	public void GenAth() throws IOException{
		String type=this.Type;
		BufferedReader br = new BufferedReader(new FileReader("Athletes.txt"));
		String readline;
		readline=br.readLine();
		int j=0;
		while(readline!=null){
			StringTokenizer st = new StringTokenizer(readline," ");
			for(int i=0;i<6;i++)
			{
				Ath[j][i]=st.nextToken();
					
			}
			readline=br.readLine();	
			j++;
		}	
		br.close();
			random(type);
			genath="ID    Name       Age State Point Type\n";
			genath+=participant[0][0]+" "+participant[0][1]+"   "+participant[0][2]+"  "+participant[0][3]+" "+participant[0][4]+" "+participant[0][5]+"\n";
			for(int i=1;i<8;i++)
			{
				genath+=participant[i][0]+" "+participant[i][1]+"   "+participant[i][2]+"  "+participant[i][3]+" "+participant[i][4]+" "+participant[i][5]+"\n";
			}
			//set the text by the game type
			if(this.Type.equals("runner"))
				Rathlete.setText(genath);
			else if(this.Type.equals("cyclist"))
				Cathlete.setText(genath);
			else
				Sathlete.setText(genath);
	}
	
	//generate 8 athlete randomly according to the game type
	public void random(String type){	
		int amount=0;
		int index = genATHnum(type);
		while(amount<8)
		{
			if(amount>0)
			{
				for(int i=0;i<amount;i++)
				{
					while(Ath[index][0].equals(participant[i][0]))
					{
						index=genATHnum(type);
					}
				}
				if(amount<8){
					participant[amount]=Ath[index];
						amount++;
						index= genATHnum(type);
					}
			}
			else
			{
				participant[amount]=Ath[index];
				amount++;
				index= genATHnum(type);
			}
		}	
	}
	//generate 1 official randomly
	public void randomof(){
		int index = (int)(Math.random()*Of.length);
		selectOf[0]=Of[index];
	}
	
	//serve for random() to generate the number of athlete by the type of game
	public int genATHnum(String type){
		int index = (int)(Math.random()*Ath.length);
		while(!(Ath[index][5].equals("super")||Ath[index][5].equals(type)))
		{index = (int)(Math.random()*Ath.length);}
		return index;
	}
	//load official information from text file and generate 1 official
	public void GenOf() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("Officials.txt"));
		String readline;
		readline=br.readLine();
		int j=0;
		while(readline!=null){
			StringTokenizer st = new StringTokenizer(readline," ");
			for(int i=0;i<4;i++)
			{
				Of[j][i]=st.nextToken();
			}
			readline=br.readLine();	
			j++;
		}	
		br.close();
		randomof();
		String sof = "ID     Name      Age  Nation \n";
		sof+=selectOf[0][0]+" "+selectOf[0][1]+" "+selectOf[0][2]+" "+selectOf[0][3];
		if(this.Type.equals("runner"))
			Rofficial.setText(sof);
		else if(this.Type.equals("cyclist"))
			Cofficial.setText(sof);
		else
			Sofficial.setText(sof);
		
	}
	//get the user input for prediction by different game type
	public void setPredict(){		
		if(this.Type.equals("runner"))
		{
			Initial.predict=PID.getText();
			if(PID.getText().equals(""))
				Rsubmit.setText("Please input prediction");
			else
				Rsubmit.getScene().getWindow().hide();
		}
		else if(this.Type.equals("cyclist"))
		{
			Initial.predict=PID2.getText();
			if(PID2.getText().equals(""))
				Csubmit.setText("Please input prediction");
			else
				Csubmit.getScene().getWindow().hide();
		}
		else
		{
			Initial.predict=PID3.getText();
			if(PID3.getText().equals(""))
				Ssubmit.setText("Please input prediction");
			else
				Ssubmit.getScene().getWindow().hide();
		}
		checkSeclect=true;
		
		
	}
	
	//change the tab and change game type
	public void changeTypeC() throws IOException{
		this.Type="cyclist";
		GenAth();
		GenOf();
	}
	//change the tab and change game type
	public void changeTypeS() throws IOException{
		this.Type="swimmer";
		GenAth();
		GenOf();
	}
	//change the tab and change game type
	public void changeTypeR() throws IOException{
		this.Type="runner";
		GenAth();
		GenOf();
	}
	public String getpredict(){
		return Initial.predict;
	}

	public String getType(){
		return this.Type;
	}
	
	public String[][] getParticipant(){
		return this.participant;
	}
	
	public String[][] getAth(){
		return this.Ath;
	}
	
	public void setAthPoint(int j,int mark){
		int score = Integer.valueOf(Ath[j][4]);
		score+=mark;
		Ath[j][4]=String.valueOf(score);	
	}

	public String[][] getOf(){
		return this.selectOf;
	}
	//serve for option2 to check whether the prediction exist
	public String checkPredict(){
		String content="Can not find the athlete you choose!";
		for(int i=0;i<8;i++)
		{
			if(predict.equals(participant[i][0]))
				content="You have choosed:\n"+participant[i][0]+" "+participant[i][1]+" "+participant[i][2]+" "+participant[i][3];
		}
		return content;
	}
	
	public boolean getCheckSelect(){
		return this.checkSeclect;
	}
	//write the athlete information to text file to update
	public void updateAth() throws IOException{
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Athletes.txt")));
		for(int i=0;i<Ath.length;i++)
		{
			System.out.println(Ath[i][0]+" "+Ath[i][1]+" "+Ath[i][2]+" "+Ath[i][3]+" "+Ath[i][4]+" "+Ath[i][5]);
			pw.println(Ath[i][0]+" "+Ath[i][1]+" "+Ath[i][2]+" "+Ath[i][3]+" "+Ath[i][4]+" "+Ath[i][5]);	
		}
		pw.close();
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			GenAth();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			GenOf();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
