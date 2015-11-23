package de.htwg.se.minesweeper.observer;



import java.util.ArrayList;
import java.util.List;

public class MSObservable  {

	/**
	 * Liste enthaelt alle angemeldeten Observer.
	 */
	private List<IMSObserver> subscribers = new ArrayList<IMSObserver>();

	/**
	 * Fuegt einen Observer zur Liste der Observer hinzu.
	 * @param s Der Observer, der hinzugefuegt werden soll.
	 */
	public void addObserver(IMSObserver s) {
		subscribers.add(s);
	}

	/**
	 * Entfernt einen Observer von der Observer-Liste.
	 * @param s Der Observer, der entfernt werden soll.
	 */
	public void removeObserver(IMSObserver s) {
		subscribers.remove(s);
	}

	/**
	 * Entfernt alle Observer von der Observer-Liste.
	 */
	public void removeAllObservers() {
		subscribers.clear();
	}

	/**
	 * Ruft auf allen Observern die in den Observer-implementierenden Klassen
	 * implementierte update()-Methode auf.
	 */
	public void notifyObservers() {
		for ( IMSObserver observer : subscribers ) {
			observer.update();
		}
	}
	
	/**
	 * Gibt die Liste mit allen Observern zurueck.
	 * @return Liste aller Observer.
	 */
	public List<IMSObserver> getSubscribers(){
		return subscribers;
	}
}