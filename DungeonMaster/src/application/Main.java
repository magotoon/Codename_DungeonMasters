package application;
	
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Game.*;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class Main extends Application implements Initializable {
	
	private int positionp1 = 1;
	private boolean flag1 = false;
	private int positionp2 = 1;
	private boolean flag2 = false;
	private int answer;
	private int previousStage = 0;
	private int newStage;
	private boolean flagLead = false;
	private boolean flagColCreation = false;
	private ObservableList<Person> data = FXCollections.observableArrayList();
	private TableColumn hp = null;
	private TableColumn name = null;
	//index for deleting items
	private IntegerProperty index = new SimpleIntegerProperty();
	
	@FXML
	BorderPane mainMenu;
	@FXML
	BorderPane gameWindow;
	@FXML
	BorderPane bossFight;
	@FXML
	BorderPane infoWindow;
	@FXML
	BorderPane infoWindowBB;
	@FXML
	BorderPane leaderboard;
	@FXML
	Pane bossInstructions;
	@FXML
	Pane victoryWindow;
	@FXML
	Pane stage0;
	@FXML
	Pane stage1;
	@FXML
	Pane stage2;
	@FXML
	Pane stage3;
	@FXML
	ImageView space1p1;
	@FXML
	ImageView space2p1;
	@FXML
	ImageView space3p1;
	@FXML
	ImageView space4p1;
	@FXML
	ImageView space5p1;
	@FXML
	ImageView space6p1;
	@FXML
	ImageView space7p1;
	@FXML
	ImageView space8p1;
	@FXML
	ImageView space9p1;
	@FXML
	ImageView space10p1;
	@FXML
	ImageView space11p1;
	@FXML
	ImageView space12p1;
	@FXML
	ImageView space13p1;
	@FXML
	ImageView space14p1;
	@FXML
	ImageView space15p1;
	@FXML
	ImageView space1p2;
	@FXML
	ImageView space2p2;
	@FXML
	ImageView space3p2;
	@FXML
	ImageView space4p2;
	@FXML
	ImageView space5p2;
	@FXML
	ImageView space6p2;
	@FXML
	ImageView space7p2;
	@FXML
	ImageView space8p2;
	@FXML
	ImageView space9p2;
	@FXML
	ImageView space10p2;
	@FXML
	ImageView space11p2;
	@FXML
	ImageView space12p2;
	@FXML
	ImageView space13p2;
	@FXML
	ImageView space14p2;
	@FXML
	ImageView space15p2;
	@FXML
	Label player1;
	@FXML
	Label player2;
	@FXML
	Label posLabel1;
	@FXML
	Label posLabel2;
	@FXML
	Label diceLabel;
	@FXML
	Label winner;
	@FXML
	Label result;
	@FXML
	TextField player1Name;
	@FXML
	TextField player1NameBB;
	@FXML
	TextField player2Name;
	@FXML
	VBox diceBox;
	@FXML
	VBox toTheBoss;
	@FXML
	TableView<Person> playerList;
//	@FXML
//	TableColumn name;
//	@FXML
//	TableColumn hp;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			StackPane root = (StackPane) FXMLLoader.load(Main.class.getResource("DungeonMasterGUI.fxml"));
			Scene scene = new Scene(root,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Dungeon Masters");

			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize (URL location, ResourceBundle resources){
			index.set(-1);
			
			playerList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>(){
				@Override
				public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
					index.set(data.indexOf(newValue));
					System.out.println("Index is: " + data.indexOf(newValue));
				}
			});
	}
	
	@FXML
	private void handlePlayButtonAction (ActionEvent e){
		System.out.println("PLay button working");
		mainMenu.setVisible(false);
		infoWindow.setVisible(true);
	}
	
	@FXML
	private void handleLeadBoardButtonAction (ActionEvent e){
		System.out.println("LeaderBoards Button Working");
		Game.getGame();
		//update leaderboard
		if (flagColCreation == false) {
			name = new TableColumn("Name");
			name.setMinWidth(200);
			name.setCellValueFactory(new PropertyValueFactory<Person, String>("pName"));

			hp = new TableColumn("HP");
			hp.setMinWidth(200);
			hp.setCellValueFactory(new PropertyValueFactory<Person, String>("pHP"));
			
		}
		
		if (flagLead == false) {
			for (Player player : Game.getGame().getPlayerList()) {
				data.add(new Person(player.getName(), Integer.toString(player.getHp())));
			}
			playerList.setItems(data);
			playerList.getColumns().addAll(name, hp);
			flagLead = true;
		}
		//end
		mainMenu.setVisible(false);
		leaderboard.setVisible(true);
	}
	
	@FXML
	private void handleOKButton (ActionEvent e){
		System.out.println("OK button working");
		if(player1Name.getText().equals("") || player2Name.getText().equals("")){
			player1Name.setPromptText("Enter player name");
			player2Name.setPromptText("Enter player name");
		}
		else {
			infoWindow.setVisible(false);
			space1p1.setVisible(true);
			space2p1.setVisible(false);
			space3p1.setVisible(false);
			space4p1.setVisible(false);
			space5p1.setVisible(false);
			space6p1.setVisible(false);
			space7p1.setVisible(false);
			space8p1.setVisible(false);
			space9p1.setVisible(false);
			space10p1.setVisible(false);
			space11p1.setVisible(false);
			space12p1.setVisible(false);
			space13p1.setVisible(false);
			space14p1.setVisible(false);
			space15p1.setVisible(false);
			space1p2.setVisible(true);
			space2p2.setVisible(false);
			space3p2.setVisible(false);
			space4p2.setVisible(false);
			space5p2.setVisible(false);
			space6p2.setVisible(false);
			space7p2.setVisible(false);
			space8p2.setVisible(false);
			space9p2.setVisible(false);
			space10p2.setVisible(false);
			space11p2.setVisible(false);
			space12p2.setVisible(false);
			space13p2.setVisible(false);
			space14p2.setVisible(false);
			space15p2.setVisible(false);
			toTheBoss.setVisible(false);
			diceBox.setVisible(true);

			// Save the names of the players
			Game.getGame().setPlayer1(new Player(player1Name.getText()));
			Game.getGame().setPlayer2(new Player(player2Name.getText()));
			// end
			// change the names of the labels
			player1.setText(Game.getGame().getPlayer1().getName());
			player2.setText(Game.getGame().getPlayer2().getName());
			// end
			// clear text field
			player1Name.setText("");
			player2Name.setText("");
			player1Name.setPromptText("");
			player2Name.setPromptText("");
			// end
			// Create BoardGame
			Game.getGame().setBoardGame(new BoardGame(Game.getGame().getPlayer1(), Game.getGame().getPlayer2()));
			// end
			gameWindow.setVisible(true);
			// Start Board Game
			Game.getGame().getBoardGame().start();
			// end
		}
	}
	
	public void handleOKBBButton(ActionEvent e){
		System.out.println("OK BB button working");
		if(player1NameBB.getText().equals("")){
			player1NameBB.setPromptText("Enter player name");
		}
		else {
			Game.getGame().setPlayer1(new Player(player1NameBB.getText()));
			player1NameBB.setText("");
			player1NameBB.setPromptText("");
			infoWindowBB.setVisible(false);
			bossInstructions.setVisible(true);
		}
	}
	
	@FXML
	public void handleCancelButton (ActionEvent e){
		System.out.println("Cancel button working");
		infoWindow.setVisible(false);
		infoWindowBB.setVisible(false);
		//clear text field
		player1Name.setText("");
		player1Name.setPromptText("");
		player2Name.setText("");
		player2Name.setPromptText("");
		player1NameBB.setText("");
		player1NameBB.setPromptText("");
		//end
		mainMenu.setVisible(true);
	}
	
	@FXML
	public void handleMainMenuButton (ActionEvent e){
		System.out.println("Main Menu button working");
		gameWindow.setVisible(false);
		//reset game data
		Game.getGame().resetGame();
		positionp1 = 1;
		positionp2 = 1;
		
		//end
		mainMenu.setVisible(true);
	}
	
	@FXML
	public void handleReturnButton (ActionEvent e){
		System.out.println("Return button working");
		leaderboard.setVisible(false);
		mainMenu.setVisible(true);
	}
	
	@FXML
	synchronized public void handleStartButton (ActionEvent e){
		System.out.println("Start button working");
		ArrayList<Pane> bossStages = new ArrayList<Pane>();
		bossStages.add(stage0);
		bossStages.add(stage1);
		bossStages.add(stage2);
		bossStages.add(stage3);
		bossInstructions.setVisible(false);
		bossFight.setVisible(true);
		//Start boss fight
		Game.getGame().setBossFight(new BossFight(Game.getGame().getPlayer1(),Game.getGame().getBoss()));
		Game.getGame().getBossFight().start();
		newStage = Game.getGame().getBossFight().setNewStage();
		bossStages.get(previousStage).setVisible(false);
		bossStages.get(Game.getGame().getBossFight().getStage()).setVisible(true);
		previousStage = newStage;
		//end
	}
	
	@FXML
	synchronized public void handleAttckButton(ActionEvent e){
		System.out.println("Attack button working");
		ArrayList<Pane> bossStages = new ArrayList<Pane>();
		bossStages.add(stage0);
		bossStages.add(stage1);
		bossStages.add(stage2);
		bossStages.add(stage3);
		//pass in marker and change to next stage
		answer = 1;
		Game.getGame().getBossFight().tryToDamage(answer);
		if(Game.getGame().getBossFight().getChallenger().isDead() == true){
			bossFight.setVisible(false);
			result.setText("You Lost");
			victoryWindow.setVisible(true);
		}
		else if(Game.getGame().getBossFight().getChallenger().isWinner() == true){
			bossFight.setVisible(false);
			result.setText("You Won");
			victoryWindow.setVisible(true);
		}
		newStage = Game.getGame().getBossFight().setNewStage();
		bossStages.get(previousStage).setVisible(false);
		bossStages.get(Game.getGame().getBossFight().getStage()).setVisible(true);
		previousStage = newStage;
		//end
	}
	
	@FXML
	synchronized public void handleBlockButton(ActionEvent e){
		System.out.println("Block button working");
		ArrayList<Pane> bossStages = new ArrayList<Pane>();
		bossStages.add(stage0);
		bossStages.add(stage1);
		bossStages.add(stage2);
		bossStages.add(stage3);
		//pass in marker and change to next stage
		answer = 2;
		Game.getGame().getBossFight().tryToDamage(answer);
		if(Game.getGame().getBossFight().getChallenger().isDead() == true){
			bossFight.setVisible(false);
			result.setText("You Lost");
			victoryWindow.setVisible(true);
		}
		else if(Game.getGame().getBossFight().getChallenger().isWinner() == true){
			bossFight.setVisible(false);
			result.setText("You Won");
			victoryWindow.setVisible(true);
		}
		newStage = Game.getGame().getBossFight().setNewStage();
		bossStages.get(previousStage).setVisible(false);
		bossStages.get(Game.getGame().getBossFight().getStage()).setVisible(true);
		previousStage = newStage;
		//end
	}
	
	@FXML
	synchronized public void handleEvadeButton(ActionEvent e){
		System.out.println("Evade button working");
		ArrayList<Pane> bossStages = new ArrayList<Pane>();
		bossStages.add(stage0);
		bossStages.add(stage1);
		bossStages.add(stage2);
		bossStages.add(stage3);
		//pass in marker and change to next stage
		answer = 3;
		Game.getGame().getBossFight().tryToDamage(answer);
		if(Game.getGame().getBossFight().getChallenger().isDead() == true){
			bossFight.setVisible(false);
			result.setText("You Lost");
			victoryWindow.setVisible(true);
		}
		else if(Game.getGame().getBossFight().getChallenger().isWinner() == true){
			bossFight.setVisible(false);
			result.setText("You Won");
			victoryWindow.setVisible(true);
		}
		newStage = Game.getGame().getBossFight().setNewStage();
		bossStages.get(previousStage).setVisible(false);
		bossStages.get(Game.getGame().getBossFight().getStage()).setVisible(true);
		previousStage = newStage;
		//end
	}
	
	synchronized public void handleRollButton (ActionEvent e) throws InterruptedException{
		ArrayList<ImageView> spacesP1 = new ArrayList<ImageView>();
		spacesP1.add(space1p1);
		spacesP1.add(space2p1);
		spacesP1.add(space3p1);
		spacesP1.add(space4p1);
		spacesP1.add(space5p1);
		spacesP1.add(space6p1);
		spacesP1.add(space7p1);
		spacesP1.add(space8p1);
		spacesP1.add(space9p1);
		spacesP1.add(space10p1);
		spacesP1.add(space11p1);
		spacesP1.add(space12p1);
		spacesP1.add(space13p1);
		spacesP1.add(space14p1);
		spacesP1.add(space15p1);
		
		ArrayList<ImageView> spacesP2 = new ArrayList<ImageView>();
		spacesP2.add(space1p2);
		spacesP2.add(space2p2);
		spacesP2.add(space3p2);
		spacesP2.add(space4p2);
		spacesP2.add(space5p2);
		spacesP2.add(space6p2);
		spacesP2.add(space7p2);
		spacesP2.add(space8p2);
		spacesP2.add(space9p2);
		spacesP2.add(space10p2);
		spacesP2.add(space11p2);
		spacesP2.add(space12p2);
		spacesP2.add(space13p2);
		spacesP2.add(space14p2);
		spacesP2.add(space15p2);
		
		System.out.println("Roll button working");
		int roll = Game.getGame().getDice().roll1Dice();
		//notify game to continue and sleep for a few miliseconds;
		Game.getGame().setWait(false);
		Game.getGame().makeBoardGameResume();
		Thread.sleep(100);
		//end
		//change player position on board
		if (positionp2 == Game.getGame().getPlayer2().getPosition()) {
			
			switch (Game.getGame().getPlayer1().getPosition()) {
			case 1:
				// set the previous space occupied to false
				spacesP1.get(positionp1 - 1).setVisible(false);
				// end
				space1p1.setVisible(true);
				positionp1 = Game.getGame().getPlayer1().getPosition();
				break;
			case 2:
				// set the previous space occupied to false
				spacesP1.get(positionp1 - 1).setVisible(false);
				// end
				space2p1.setVisible(true);
				positionp1 = Game.getGame().getPlayer1().getPosition();
				break;
			case 3:
				// set the previous space occupied to false
				spacesP1.get(positionp1 - 1).setVisible(false);
				// end
				space3p1.setVisible(true);
				positionp1 = Game.getGame().getPlayer1().getPosition();
				break;
			case 4:
				// set the previous space occupied to false
				spacesP1.get(positionp1 - 1).setVisible(false);
				// end
				space4p1.setVisible(true);
				positionp1 = Game.getGame().getPlayer1().getPosition();
				break;
			case 5:
				// set the previous space occupied to false
				spacesP1.get(positionp1 - 1).setVisible(false);
				// end
				space5p1.setVisible(true);
				positionp1 = Game.getGame().getPlayer1().getPosition();
				break;
			case 6:
				// set the previous space occupied to false
				spacesP1.get(positionp1 - 1).setVisible(false);
				// end
				space6p1.setVisible(true);
				positionp1 = Game.getGame().getPlayer1().getPosition();
				break;
			case 7:
				// set the previous space occupied to false
				spacesP1.get(positionp1 - 1).setVisible(false);
				// end
				space7p1.setVisible(true);
				positionp1 = Game.getGame().getPlayer1().getPosition();
				break;
			case 8:
				// set the previous space occupied to false
				spacesP1.get(positionp1 - 1).setVisible(false);
				// end
				space8p1.setVisible(true);
				positionp1 = Game.getGame().getPlayer1().getPosition();
				break;
			case 9:
				// set the previous space occupied to false
				spacesP1.get(positionp1 - 1).setVisible(false);
				// end
				space9p1.setVisible(true);
				positionp1 = Game.getGame().getPlayer1().getPosition();
				break;
			case 10:
				// set the previous space occupied to false
				spacesP1.get(positionp1 - 1).setVisible(false);
				// end
				space10p1.setVisible(true);
				positionp1 = Game.getGame().getPlayer1().getPosition();
				break;
			case 11:
				// set the previous space occupied to false
				spacesP1.get(positionp1 - 1).setVisible(false);
				// end
				space11p1.setVisible(true);
				positionp1 = Game.getGame().getPlayer1().getPosition();
				break;
			case 12:
				// set the previous space occupied to false
				spacesP1.get(positionp1 - 1).setVisible(false);
				// end
				space12p1.setVisible(true);
				positionp1 = Game.getGame().getPlayer1().getPosition();
				break;
			case 13:
				// set the previous space occupied to false
				spacesP1.get(positionp1 - 1).setVisible(false);
				// end
				space13p1.setVisible(true);
				positionp1 = Game.getGame().getPlayer1().getPosition();
				break;
			case 14:
				// set the previous space occupied to false
				spacesP1.get(positionp1 - 1).setVisible(false);
				// end
				space14p1.setVisible(true);
				positionp1 = Game.getGame().getPlayer1().getPosition();
				break;
			case 15:
				// set the previous space occupied to false
				spacesP1.get(positionp1 - 1).setVisible(false);
				// end
				space15p1.setVisible(true);
				positionp1 = Game.getGame().getPlayer1().getPosition();
				break;
			}
		}
		else if (positionp1 == Game.getGame().getPlayer1().getPosition()) {

			switch (Game.getGame().getPlayer2().getPosition()) {
			case 1:
				// set the previous space occupied to false
				spacesP2.get(positionp2 - 1).setVisible(false);
				// end
				space1p2.setVisible(true);
				positionp2 = Game.getGame().getPlayer2().getPosition();
				break;
			case 2:
				// set the previous space occupied to false
				spacesP2.get(positionp2 - 1).setVisible(false);
				// end
				space2p2.setVisible(true);
				positionp2 = Game.getGame().getPlayer2().getPosition();
				break;
			case 3:
				// set the previous space occupied to false
				spacesP2.get(positionp2 - 1).setVisible(false);
				// end
				space3p2.setVisible(true);
				positionp2 = Game.getGame().getPlayer2().getPosition();
				break;
			case 4:
				// set the previous space occupied to false
				spacesP2.get(positionp2 - 1).setVisible(false);
				// end
				space4p2.setVisible(true);
				positionp2 = Game.getGame().getPlayer2().getPosition();
				break;
			case 5:
				// set the previous space occupied to false
				spacesP2.get(positionp2 - 1).setVisible(false);
				// end
				space5p2.setVisible(true);
				positionp2 = Game.getGame().getPlayer2().getPosition();
				break;
			case 6:
				// set the previous space occupied to false
				spacesP2.get(positionp2 - 1).setVisible(false);
				// end
				space6p2.setVisible(true);
				positionp2 = Game.getGame().getPlayer2().getPosition();
				break;
			case 7:
				// set the previous space occupied to false
				spacesP2.get(positionp2 - 1).setVisible(false);
				// end
				space7p2.setVisible(true);
				positionp2 = Game.getGame().getPlayer2().getPosition();
				break;
			case 8:
				// set the previous space occupied to false
				spacesP2.get(positionp2 - 1).setVisible(false);
				// end
				space8p2.setVisible(true);
				positionp2 = Game.getGame().getPlayer2().getPosition();
				break;
			case 9:
				// set the previous space occupied to false
				spacesP2.get(positionp2 - 1).setVisible(false);
				// end
				space9p2.setVisible(true);
				positionp2 = Game.getGame().getPlayer2().getPosition();
				break;
			case 10:
				// set the previous space occupied to false
				spacesP2.get(positionp2 - 1).setVisible(false);
				// end
				space10p2.setVisible(true);
				positionp2 = Game.getGame().getPlayer2().getPosition();
				break;
			case 11:
				// set the previous space occupied to false
				spacesP2.get(positionp2 - 1).setVisible(false);
				// end
				space11p2.setVisible(true);
				positionp2 = Game.getGame().getPlayer2().getPosition();
				break;
			case 12:
				// set the previous space occupied to false
				spacesP2.get(positionp2 - 1).setVisible(false);
				// end
				space12p2.setVisible(true);
				positionp2 = Game.getGame().getPlayer2().getPosition();
				break;
			case 13:
				// set the previous space occupied to false
				spacesP2.get(positionp2 - 1).setVisible(false);
				// end
				space13p2.setVisible(true);
				positionp2 = Game.getGame().getPlayer2().getPosition();
				break;
			case 14:
				// set the previous space occupied to false
				spacesP2.get(positionp2 - 1).setVisible(false);
				// end
				space14p2.setVisible(true);
				positionp2 = Game.getGame().getPlayer2().getPosition();
				break;
			case 15:
				// set the previous space occupied to false
				spacesP2.get(positionp2 - 1).setVisible(false);
				// end
				space15p2.setVisible(true);
				positionp2 = Game.getGame().getPlayer2().getPosition();
				break;
			}
		}
		
		if(Game.getGame().getPlayer1().isWinner() == true){
			flag1 = true;
		}
		else if(Game.getGame().getPlayer2().isWinner() == true){
			flag2 = true;
		}
		//end
		if(flag1 == true){
			winner.setText("Player 1");
			diceBox.setVisible(false);
			toTheBoss.setVisible(true);
			Game.getGame().getPlayer1().setWinner(false);
			flag1 = false;
			Game.getGame().getPlayer1().setPositionTo(1);
			positionp1 = 1;
			positionp2 = 1;
		}
		else if (flag2){
			winner.setText("player 2");
			diceBox.setVisible(false);
			Game.getGame().getPlayer2().setWinner(false);
			flag2 = false;
			Game.getGame().getPlayer2().setPositionTo(1);
			Game.getGame().setPlayer1(Game.getGame().getPlayer2());
			toTheBoss.setVisible(true);
			positionp1 = 1;
			positionp2 = 1;
		}
		String s = Integer.toString(roll);
		diceLabel.setText(s);
	}
	
	public void handleContinueButton (ActionEvent e){
		gameWindow.setVisible(false);
		bossInstructions.setVisible(true);
	}
	
	public void handleBossFightButton (ActionEvent e){
		mainMenu.setVisible(false);
		infoWindowBB.setVisible(true);
	}
	
	public void handleFinishButton (ActionEvent e){
		victoryWindow.setVisible(false);
		//update list in leaderboard from DB
		if (flagColCreation == false) {
			name = new TableColumn("Name");
			name.setMinWidth(200);
			name.setCellValueFactory(new PropertyValueFactory<Person, String>("pName"));

			hp = new TableColumn("HP");
			hp.setMinWidth(200);
			hp.setCellValueFactory(new PropertyValueFactory<Person, String>("pHP"));
			
		}
		
		if (flagLead == false) {
			for (Player player : Game.getGame().getPlayerList()) {
				data.add(new Person(player.getName(), Integer.toString(player.getHp())));
			}
			playerList.setItems(data);
			playerList.getColumns().addAll(name, hp);
			flagLead = true;
		}
		//end
		//register player in DB and add it to the leaderboard
		if (Game.getGame().getBossFight().getChallenger().isWinner()){
			Game.getGame().addToTable();
			data.add(new Person (Game.getGame().getBossFight().getChallenger().getName(), Integer.toString(Game.getGame().getBossFight().getChallenger().getHp())));
		}
		//end
		leaderboard.setVisible(true);
		Game.getGame().getBoss().setHp(15);
		Game.getGame().resetGame();
	}
	
	public void hableDeleteSelectedButton (ActionEvent e) {
		System.out.println("Delete button working");
		int i = index.get();
		// delete selected item from table, AL, and DB
		if (i > -1) {
			Game.getGame().deleteFromTable(data.get(i).getPName(), data.get(i).getPHP());
			data.remove(i);
			playerList.getSelectionModel().clearSelection();
		}
		//
	}
	
	public static class Person {
		 
        private final SimpleStringProperty pName;
        private final SimpleStringProperty pHP;
 
        private Person(String pName, String pHP) {
            this.pName = new SimpleStringProperty(pName);
            this.pHP = new SimpleStringProperty(pHP);
        }
 
        public String getPName() {
            return pName.get();
        }
 
        public void setpName(String fName) {
            pName.set(fName);
        }
 
        public String getPHP() {
            return pHP.get();
        }
 
        public void setpHP(String fName) {
            pHP.set(fName);
        }
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
