package de.htwg.se.minesweeper.observer;

import static org.junit.Assert.*;

import org.junit.Test;

public class ObservableTest {

	MSObservable obs = new MSObservable();
	IMSObserver observer1 = new IMSObserver() {

		@Override
		public void update() {

		}
	};
	IMSObserver observer2 = new IMSObserver() {

		@Override
		public void update() {

		}
	};

	@Test
	public void testAddObserver() {

		obs.addObserver(observer1);
		assertEquals(observer1, obs.getSubscribers().get(0));
	}

	@Test
	public void testRemoveObservers() {

		obs.removeObserver(observer1);
		assertEquals(0, obs.getSubscribers().size());
	}

	@Test
	public void testRemoveAllObservers() {
		obs.addObserver(observer1);
		obs.addObserver(observer2);
		obs.removeAllObservers();
		assertEquals(0, obs.getSubscribers().size());
	}

	@Test
	public void testNotifyObservers() {
		obs.addObserver(observer1);
		obs.addObserver(observer2);
		for (IMSObserver observer : obs.getSubscribers()) {
			observer.update();
		}

		obs.notifyObservers();

	}
}
