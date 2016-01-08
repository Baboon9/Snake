package entities;


import org.lwjgl.util.Rectangle;

public interface Entity {

	public void draw();
	public void update(int delta);
	public void setLocation(double x, double y);
	public void setX(double x);
	public void setY(double y);
	public void setWidth(double width);
	public void setHeight(double width);
	public double getX();
	public double getY();
	public double getWidth();
	public double getHeight();
	public Rectangle getRectangle();
	public boolean intersects(Entity other);
	
}
