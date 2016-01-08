package Ticker;

import java.util.HashSet;
import java.util.Set;

public class Ticker {
	
	public Thread life = new Thread(){
		public void run(){
			while(true){	
			if(runs)fireNextTickEvent();
				try {
					sleep((long) (500l * speed));
				} catch (Exception e){
					
				}
			}
		}
	};
	
	
	public boolean runs = true;
	private Set<TickerListener> listeners;
	public double speed = 1;
	
	public Ticker(){
		listeners = new HashSet<TickerListener>();
		life.start();
	}

	
	public synchronized void addTickerListener(TickerListener listener){
		listeners.add(listener);
	}
	
	public synchronized void removeTickerListener(TickerListener listener){
		listeners.remove(listener);
	}
	
	protected synchronized void fireNextTickEvent(){
		TickerEvent e = new TickerEvent(this);
		for (TickerListener listener : listeners){
			listener.nextTick(e);
		}
	}
}
