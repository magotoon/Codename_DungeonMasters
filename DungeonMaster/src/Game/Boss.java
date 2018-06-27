package Game;

public class Boss {
	
	private int hp = 15;

	public Boss () {
		
	}
	
	public int getHp() {
		return hp;
	}
	
	public void setHp(int hp){
		this.hp = hp;
	}
	
	public void recieveDmg (int dmg){
		hp -= dmg;
	}
}
