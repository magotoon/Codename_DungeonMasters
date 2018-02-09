package Game;

import javafx.beans.property.SimpleStringProperty;

public class Player implements Comparable<Player>{
	private int position = 1;
	private int hp = 10;
	private String name;
	private boolean winner = false;
	private boolean dead = false;
	
	public Player (String name){
		this.name = name;
	}
	public Player (String name, int hp){
		this.name = name;
		this.hp = hp;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position) {
		this.position += position;
		if (this.position > 15){
			position = this.position - 15;
			this.position -= (position * 2);
		}
	}
	
	public void setPositionTo (int position){
		this.position = position;
	}
	
	public int getHp() {
		return hp;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public void takeDmg (int dmg){
		this.hp -= dmg;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setWinner(boolean win) {
		winner = win;
	}
	
	public boolean isWinner() {
		return winner;
	}
	
	public void checkIfWinner (){
		if (position == 15){
			winner = true;
		}
	}
	
	public void setDead (Boolean dead){
		this.dead = dead;
	}
	
	public boolean isDead (){
		return dead;
	}

	@Override
	public int compareTo(Player obj) {
		return this.hp - obj.getHp();
	}
	
	@Override
	public String toString (){
		return "" + name + "     " + hp;
	}

}
