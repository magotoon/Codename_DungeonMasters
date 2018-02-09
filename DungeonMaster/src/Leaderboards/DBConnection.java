package Leaderboards;

import java.sql.*;
import java.util.ArrayList;

import Game.Player;

public class DBConnection {
	
	private static Connection c = null;
	private static Statement stmt = null;
	
	public static Connection getConnection() throws SQLException{
		
		if (c == null || c.isClosed()) {
			c = magicallyCreateNewConnection();
		}
		
		return c;
	}
	
	private static Connection magicallyCreateNewConnection(){
		Connection conn = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:test.db");
			System.out.println("Opened database successfully");
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return conn;
	}
	public static void generateTable () {
		try {
			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS LEADERBOARD " +
						 "(NAME             TEXT        NOT NULL," +
					     "HP                INT         NOT NULL)";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void addToTable (Player player){
		try {
			stmt = c.createStatement();
			String sql = "INSERT INTO LEADERBOARD (NAME, HP)" +
						 "VALUES ('" + player.getName() + "', " + player.getHp() + ");";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public static void deleteFormTable (String name, int hp){
		try {
			stmt = c.createStatement();
			//make it delete just 1 item
			String sql = "DELETE from LEADERBOARD where NAME='" + name + "' and HP=" + hp + ";";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Player> retrieveAll() {
		ArrayList<Player> players = new ArrayList<Player>();
		
		try {
			stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM LEADERBOARD;");
			
			while (rs.next()) {
				String name = rs.getString("NAME");
				int hp = rs.getInt("HP");
				
				players.add(new Player(name, hp));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return players;
	}

}
