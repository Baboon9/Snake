public class Score extends Square{

	public Score(double x, double y) {
		super(x, y);
		this.setRGB(1, 0.1f , 0);
	}
	
	public void reposition(Hero hero){
		x = Snake.SQUARE_SIZE[Snake.LEVEL] * (int)(Math.random() * Snake.SQUARE_WIDTH);
		y = Snake.SQUARE_SIZE[Snake.LEVEL] * (int)(Math.random() * Snake.SQUARE_WIDTH);
		for(int i = 0; i < hero.body.size(); i++){
			if(intersects(hero.body.get(i))){
				reposition(hero);
			}
		}
	}
	
	

}
