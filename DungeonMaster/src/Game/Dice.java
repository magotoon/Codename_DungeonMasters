package Game;

import java.util.Random;

public class Dice {
	private Random dice = new Random();
	private int min;
	private int max;
	private int number;
	
	public Dice (int min, int max){
		this.min = min;
		this.max = max;
	}
	
	synchronized public int roll1Dice() {
		number = dice.nextInt((max - min) + 1) + min;
		
		return number;
	}
	
	synchronized public int roll2Dice() {
		number = (dice.nextInt((max - min) + 1) + min) +
				(dice.nextInt((max - min) + 1) + min);
		
		return number;
	}
	
	synchronized public int getNumber (){
		return number;
	}
}
