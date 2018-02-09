package Game;

public class BoardGame extends Thread{
	
	private Player player1;
	private Player player2;

	public BoardGame (Player player1, Player player2){
		this.player1 = player1;
		this.player2 = player2;
	}
	
	@Override
	public void run (){
		System.out.println("in game");
		
		while (player1.isWinner() == false && player2.isWinner() == false){
			
			System.out.println("player1 turn");
			Game.getGame().makeBoardGameWait();
			player1.setPosition(Game.getGame().getDice().getNumber());
			player1.checkIfWinner();
			if (player1.isWinner() == true){
				continue;
			}
			
			System.out.println("player2 turn");
			Game.getGame().makeBoardGameWait();
			player2.setPosition(Game.getGame().getDice().getNumber());
			player2.checkIfWinner();
			if (player2.isWinner() == true){
				continue;
			}
		}
	}
}
