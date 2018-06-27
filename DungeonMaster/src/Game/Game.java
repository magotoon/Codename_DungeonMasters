package Game;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import Leaderboards.DBConnection;

public class Game {
	
	private ArrayList<Player> players = null;
	private static Game game = null;
	private static Connection c = null;
	
	private Player player1 = null;
	private Player player2 = null;
	private Boss boss = new Boss();
	private Dice dice = new Dice(1,6);
	private BoardGame boardGame = null;
	private BossFight bossFight = null;
	private boolean wait = true;
	
	private Game() {
		try {
			c = DBConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBConnection.generateTable();
		players = DBConnection.retrieveAll();
	}
	
	public static Game getGame() {
		if (game == null){
			game = new Game();
		}
		
		return game;
	}
	
	public void resetGame () {
		game = null;
		player1 = null;
		player2 = null;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}
	
	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	
	public Boss getBoss (){
		return boss;
	}
	
	public void setBoardGame (BoardGame boardGame){
		this.boardGame = boardGame;
		this.boardGame.setDaemon(true);
	}
	
	public BoardGame getBoardGame (){
		return boardGame;
	}
	
	synchronized public void makeBoardGameWait(){
		while (wait == true){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		wait = true;
		notifyAll();
	}
	
	synchronized public void makeBoardGameResume(){
		if (wait == false){
			notifyAll();
		}
	}
	
	public void setBossFight (BossFight bossFight){
		this.bossFight = bossFight;
		this.bossFight.setDaemon(true);
	}
	
	public BossFight getBossFight () {
		return bossFight;
	}
	
	public Dice getDice (){
		return dice;
	}
	
	public void setWait (boolean wait){
		this.wait = wait;
	}
	
	public void addToTable () {
		DBConnection.addToTable(player1);
		players.add(player1);
	}
	
	public void deleteFromTable(String name, String hp){
		DBConnection.deleteFormTable(name, Integer.parseInt(hp));
	}
	
	public ArrayList<Player> getPlayerList() {
		return players;
	}
	

}
