import org.lwjgl.input.Keyboard;

public class Controls {

	private Hero hero;
	private Snake snake;
	
	public Controls(Hero hero, Snake snake){
		this.snake = snake;
		this.hero = hero;
	
	}
	
	public void check(){
		if(Keyboard.isKeyDown(Keyboard.KEY_UP) && hero.lastDirection != 2){
			hero.direction = 0;
			
		} else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN) && hero.lastDirection != 0){
			hero.direction = 2;
			
		} else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT ) && hero.lastDirection != 3){
			hero.direction = 1;
			
		} else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT) && hero.lastDirection != 1){
			hero.direction = 3;
		
		} else if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)){
			snake.restart();
		}
	}
}
