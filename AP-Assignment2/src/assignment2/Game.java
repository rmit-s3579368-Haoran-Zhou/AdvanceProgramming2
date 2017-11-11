package assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
//class designed by Haoran Zhou 
public class Game {
	@FXML
	private Button gback;
	@FXML
	private TextArea gtxt;
	private Initial ini = new Initial();
	private String gameRecord="\n";
	private static String winID;
	
	private static double[] GameTime = new double[8];
	private static String[][] Gameinfo = new String[8][4];//0ID 1Name 2Time 3Rank
	//load option 4's fxml
	public void loadGUI4() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Menu.class.getResource("fxml/showgame.fxml"));
		Pane pane = loader.load();
		Scene scene = new Scene(pane,600,450);	
		Stage showgame = new Stage();
		showgame.setTitle("All Games information");
		showgame.initModality(Modality.WINDOW_MODAL);
		showgame.initOwner(Menu.getPrimaryStage());
		showgame.setScene(scene);	
		showgame.show();
		
	}
	
	public void back() throws IOException{
		gback.getScene().getWindow().hide();
		
	}
	//record game information with time and game type as game index
	public void setGameinfo() throws IOException{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		gameRecord+="Official: "+ini.getOf()[0][1]+"\r\n";
		gameRecord+="Game Index: "+ini.getType()+" competition"+"\r\n"+df.format(new Date())+"\r\n";
		gameRecord+="-------------------------------\r\n\n";
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Games.txt",true)));
		pw.println(gameRecord);
		pw.close();
	}
	//load game record from text file
	public void loadGames() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("Games.txt"));
		String readline;
		String all;
		readline=br.readLine();
		all=readline+"\n";
		while(readline!=null){
			readline=br.readLine();	
			if(readline!=null)
			all+=readline+"\n";
		}	
		br.close();
			gtxt.setText(all);
	}
	//generate runner's time
	public double GenRunnerTime(){
		Random ran = new Random();
		double time= ran.nextDouble()*10+10;
		String temp = String.format("%.2f", time);
		time = Double.valueOf(temp);
		return time;
	}
	//generate cyclist's time
	public double GenCyclistTime(){
		Random ran = new Random();
		double time= ran.nextDouble()*100+100;	 
		String temp = String.format("%.2f", time);
		time = Double.valueOf(temp);
		return time;
	}
	//generate swimmer's time
	public double GenSwimmerTime(){
		Random ran = new Random();
		double time= ran.nextDouble()*30+500;	
		String temp = String.format("%.2f", time);
		time = Double.valueOf(temp);
		return time;
	}
	
	//generate 8 participants' time accroding to game type
	public void GenGame(){
		if(ini.getType().equals("runner"))
		{
			for(int i=0;i<8;i++)
				GameTime[i]=GenRunnerTime();
		}
		else if(ini.getType().equals("cyclist"))
		{
			for(int i=0;i<8;i++)
				GameTime[i]=GenCyclistTime();
		}
		else
		{
			for(int i=0;i<8;i++)
				GameTime[i]=GenSwimmerTime();
		}
		
		
	}
	
	public void loadGUI3() throws IOException{
		GenGame();
		assignTime();
		rank();
		assignPoint();
		setGameinfo();
		ini.updateAth();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Menu.class.getResource("fxml/animation.fxml"));
		Pane pane = loader.load();
		Scene scene = new Scene(pane,600,400);		
		Stage showgame = new Stage();
		showgame.setTitle("Game Start");
		showgame.initModality(Modality.WINDOW_MODAL);
		showgame.initOwner(Menu.getPrimaryStage());
		showgame.setScene(scene);	
		showgame.show();
	}
	
	public double[] getGameTime(){
		return this.GameTime;
	}
	
	public void assignTime(){
		for(int i=0;i<8;i++){
			Gameinfo[i][0]=ini.getParticipant()[i][0];
			Gameinfo[i][1]=ini.getParticipant()[i][1];
			Gameinfo[i][2]=String.valueOf(GameTime[i]);
		}
	}
	//set the rank for participant by their time and record in String[][] Gameinfo
	public void rank(){
		int[] rank = new int[]{1,1,1,1,1,1,1,1}; //Every athlete's default place is 1st
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				if(Double.valueOf(Gameinfo[i][2])>Double.valueOf(Gameinfo[j][2]))
				{
	              rank[i]++;//If the time this athlete spends more than other,its rank add 1
				}
			}		
		}
		
		for(int i=0;i<8;i++)
		{
			Gameinfo[i][3]= Integer.toString(rank[i]);
		}
		
	}
	//update the point of athletes
	public void assignPoint(){
		for(int i=0;i<8;i++)
		{
			if(Gameinfo[i][3].equals("1"))// rank 1 athlete get 5 points
			{
				for(int j=0;j<32;j++)
				{
					if(ini.getAth()[j][0].equals(Gameinfo[i][0]))
						ini.setAthPoint(j,5);		
				}
				gameRecord+=" ID: "+Gameinfo[i][1]+" Name: "+Gameinfo[i][2]+" Rank: "+Gameinfo[i][3]+"\r\n";
				winID=Gameinfo[i][0];
			}
			else if(Gameinfo[i][3].equals("2"))//rank 2 athlete get 2 point
			{
				for(int j=0;j<32;j++)
				{
					if(ini.getAth()[j][0].equals(Gameinfo[i][0]))
						ini.setAthPoint(j,2);
				}
				gameRecord+=" ID: "+Gameinfo[i][1]+" Name: "+Gameinfo[i][2]+" Rank: "+Gameinfo[i][3]+"\r\n";
			}
			else if(Gameinfo[i][3].equals("3"))
			{
				for(int j=0;j<32;j++)
				{
					if(ini.getAth()[j][0].equals(Gameinfo[i][0]))//rank 3 athlete get 1 point
						ini.setAthPoint(j,1);
				}
				gameRecord+=" ID: "+Gameinfo[i][1]+" Name: "+Gameinfo[i][2]+" Rank: "+Gameinfo[i][3]+"\r\n";
			}	
		}	
	}
	
	
	public String[][] getGameinfo(){
		return this.Gameinfo;
	}
	
	public String getWinID(){
		return this.winID;
	}
	

	
}
