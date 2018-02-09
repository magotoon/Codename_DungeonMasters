package Game;

import java.util.Random;

public class BossFight extends Thread{
	
	private Player challenger;
	private Boss boss;
	private int stage;
	Random r = new Random();
	
	public BossFight(Player challenger, Boss boss){
		this.challenger = challenger;
		this.boss = boss;
	}
	
	@Override
	public void run (){
		while (challenger.getHp() > 0 && boss.getHp() > 0){
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			challenger.takeDmg(1);
			System.out.println(challenger.getHp());
		}
		if (challenger.getHp() <= 0){
			challenger.setDead(true);
		}
		else if (boss.getHp() <= 0){
			challenger.setWinner(true);
		}
	}
	
	public void tryToDamage(int answer){
		if(answer == stage){
			boss.recieveDmg(1);
		}
	}
	
	public int setNewStage (){
		stage = Math.abs(r.nextInt((3 - 1) + 1) + 1);
		
		return stage;
	}
	
	public int getStage(){
		return stage;
	}
	
	public Player getChallenger(){
		return challenger;
	}

}
