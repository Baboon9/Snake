import Ticker.*;


public class Logic {
	
	private Score score;
	private Hero hero;
	private int growXPos, growYPos;
	private Snake snake;
	
	private TickerAdapter listener = new TickerAdapter(){
		public void nextTick(TickerEvent e){
			if(hero.length - 2 > Math.pow(snake.SQUARE_WIDTH, 2)/2-(snake.LEVEL-1)*5) snake.nextLevel();
			hero.move();		
			if(hero.body.get(hero.tailPointer).intersects(score)){
				growXPos = score.getXIncrementalPostion();
				growYPos = score.getYIncrementalPostion();
				hero.grow(growXPos, growYPos);
				snake.highScore++;
				score.reposition(hero);
				

			}
			
			for(int i = 0; i<hero.body.size(); i++){
				if(hero.body.get(hero.tailPointer).intersects(hero.body.get(i)) && i != hero.body.size()-1 && i != hero.tailPointer){
					snake.gameOver();
				}
			}
			hero.tailPointer--;
			
		}


	};
	
	public Logic(Score score, Hero hero, Snake snake) {
		this.score = score;
		this.hero = hero;
		this.snake = snake;
		
	}


	public TickerListener getListener(){
		return listener;
	}
}


