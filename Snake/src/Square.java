


import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glRectd;

import entities.AbstractEntity;
import entities.Entity;

public class Square extends AbstractEntity implements Entity{

	float r,g,b;
	
	public Square(double x, double y) {
		super(x * Snake.SQUARE_SIZE[Snake.LEVEL], y * Snake.SQUARE_SIZE[Snake.LEVEL], Snake.SQUARE_SIZE[Snake.LEVEL], Snake.SQUARE_SIZE[Snake.LEVEL]);
		r = 1;
		g = 1;
		b = 1;
	}

	@Override
	public void draw() {
		glColor3f(r, g, b);
		glRectd(x + 3, y + 3,x + width - 3, y + height - 3);
		
	}
	
	public void setIncrementalPosition(int x, int y){
		this.x = x*Snake.SQUARE_SIZE[Snake.LEVEL];
		this.y = y*Snake.SQUARE_SIZE[Snake.LEVEL];
		this.x = this.x % Snake.WIDTH;
		this.y = this.y % Snake.HEIGHT;
		if(this.x < 0) this.x += Snake.WIDTH;
		if(this.y < 0) this.y += Snake.HEIGHT;

	}

	public int getXIncrementalPostion(){
		return (int) (this.x / Snake.SQUARE_SIZE[Snake.LEVEL]);
	}
	
	public int getYIncrementalPostion(){
		return (int) (this.y / Snake.SQUARE_SIZE[Snake.LEVEL]);
	}
	
	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub
		
	}
	public void setRGB(float r, float g, float b){
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public void resize(){
		this.width = Snake.SQUARE_SIZE[Snake.LEVEL];
		this.height = Snake.SQUARE_SIZE[Snake.LEVEL];
		this.x /= 2;
		this.y /= 2;

	}

}
