package src.observer;

import java.util.ArrayList;
import java.util.List;

public class MSObservableWithArguments extends MSObservable {
    
    private List<IMSObserverWithArguments> subscribersWithArguments = new ArrayList<IMSObserverWithArguments>(
                                            2);
    
    public void addObserver(IMSObserverWithArguments s) {
        subscribersWithArguments.add(s);
    }
    
    public void removeObserver(IMSObserverWithArguments s) {
        subscribersWithArguments.remove(s);
    }
    
    public void notifyObservers(Object arg) {
        for (IMSObserverWithArguments observer : subscribersWithArguments) {
            observer.update(arg);
        }
    }
}