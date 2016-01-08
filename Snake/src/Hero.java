import java.util.ArrayList;
import java.util.List;

public class Hero {

	public int direction;
	public List<Square> body;
	public int length;
	public int xPos;
	public int yPos;
	public int tailPointer;
	public int lastDirection;
	
	public void move() {
		int xDir = 0, yDir = 0;
		switch(direction){
			case 0: xDir = 0; yDir = -1; break;
			case 1: xDir = 1; yDir = 0; break;
			case 2: xDir = 0; yDir = 1; break;
			case 3: xDir = -1; yDir = 0;
		}
		
		xPos += xDir;
		yPos += yDir;
		xPos = xPos % Snake.SQUARE_WIDTH;
		yPos = yPos % Snake.SQUARE_WIDTH;
		if(xPos < 0) xPos += Snake.SQUARE_WIDTH;
		if(yPos < 0) yPos += Snake.SQUARE_WIDTH;		
		
		updatePosition(xDir, yDir);
	}
	
	public void updatePosition(int xDir, int yDir) {
		if(tailPointer < 0) tailPointer = length - 1;
		body.get(tailPointer).setIncrementalPosition(xPos, yPos);
		lastDirection = direction;
	}

	public Hero(){
		lastDirection = direction;
		direction = 0;
		tailPointer = 1;
		length = 2;
		body = new ArrayList <Square>();
		xPos = Snake.SQUARE_WIDTH/2;
		yPos = Snake.SQUARE_WIDTH/2;
		body.add(new Square(xPos, yPos));
		body.add(new Square(xPos, yPos+1));
	}
	
	public void draw(){
		for(Square each : body){
			each.draw();
		}
	}
	
	public void grow(int xPos, int yPos){
		body.add(new Square(xPos, yPos));
		length++;

	}
	
	public void resize(){
		for (Square each : body){
			each.resize();
		}
		xPos /=2;
		yPos /=2;
		body.clear();
		body.add(new Square(xPos, yPos));
		body.add(new Square(xPos, yPos+1));
		tailPointer = 1;
		length = 2;
	}
	
	public void restart(){
		body.clear();
		direction = 0;
		tailPointer = 1;
		length = 2;
		xPos = Snake.SQUARE_WIDTH/2;
		yPos = Snake.SQUARE_WIDTH/2;
		body.add(new Square(xPos, yPos));
		body.add(new Square(xPos, yPos+1));
	}
	
}
