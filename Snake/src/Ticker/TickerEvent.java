package Ticker;

public class TickerEvent {
	private Ticker source;

	public TickerEvent(Ticker source) {
		this.source = source;
	}
	
	public Ticker getSource(){
		return source;
	}
}
