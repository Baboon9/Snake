import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import Text.SimpleText;
import Ticker.Ticker;

public class Snake{

	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	public static int LEVEL = 1;
	public static int[] SQUARE_SIZE = {200, 150, 120, 100, 60, 40, 20, 10, 5};
	public static int SQUARE_WIDTH = WIDTH/SQUARE_SIZE[LEVEL];
	public int highScore = 0;
	private boolean gameOver = false;
	private boolean isRunning = true;
	private Controls controls;
	private Hero hero;
	private Score score;
	private Ticker ticker;
	private Logic logic;
	
	
	@SuppressWarnings("deprecation")
	public Snake(){
		
		ticker = new Ticker();
		hero = new Hero();
		controls = new Controls(hero, this);
		score = new Score(0,0);
		logic = new Logic(score, hero, this);
		
		ticker.addTickerListener(logic.getListener());
		score.reposition(hero);
		
		setUpDisplay();
		setUpOpenGL();
		while(isRunning){
			render();
			controls.check();
			Display.update();
			Display.sync(60);
			if(Display.isCloseRequested()){
				isRunning = false;
				ticker.life.stop();
			}
			
		}
		Display.destroy();
	}

	private void setUpDisplay() {
		try{
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle("Snake");
			Display.create();
		} catch(LWJGLException e) {
			e.printStackTrace();
		}

	}
	
	private void setUpOpenGL() {
		//initialization code OpenGL
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}

	
	private void render(){
		glClear(GL_COLOR_BUFFER_BIT);
		glLoadIdentity();
		score.draw();
		hero.draw();
		if(gameOver){
			glLoadIdentity();
			glColor3f(1, 0.1f, 0);
			SimpleText.drawString("Game Over",70, 80);
			glLoadIdentity();
			glColor3f(1, 0.1f, 0);
			SimpleText.drawString("Press Return",70, 90);


		}
		int shift = 40;
		glLoadIdentity();
		glColor3f(0, 0.5f , 1);
		SimpleText.drawString("Score: ",10, 145 + shift);
		glLoadIdentity();
		SimpleText.drawString(Integer.toString(highScore),50, 145 + shift);
		glLoadIdentity();
		SimpleText.drawString("Level: ",10, 155 + shift);
		glLoadIdentity();
		SimpleText.drawString(Integer.toString(LEVEL),50, 155 + shift);
		
	}
	
	public static void main(String[] args){

		new Snake();
				
	}

	public void gameOver() {
		gameOver = true;
		ticker.runs = false;
	}
	
	public void restart() {
		highScore = 0;
		ticker.runs = true;
		gameOver = false;
		hero.restart();
	}

	public void nextLevel(){
		LEVEL++;
		SQUARE_WIDTH = WIDTH/SQUARE_SIZE[LEVEL];
		SQUARE_WIDTH = HEIGHT/SQUARE_SIZE[LEVEL];
		hero.resize();
		score.resize();
		score.reposition(hero);
		ticker.speed /= 1.5;
	}


}
