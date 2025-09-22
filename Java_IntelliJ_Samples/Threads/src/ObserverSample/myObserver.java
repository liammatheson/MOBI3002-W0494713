package ObserverSample;

import java.util.Observable;
import java.util.Observer;

public class myObserver implements Observer {

	@Override
    public void update(Observable arg0, Object event) {

    	// Print the data sent to the event handler
    	if (event instanceof eventObject) {
        eventObject e = (eventObject) event;
        
        System.err.println(
                "Event Handler: "
                + e.ActionInt
                + e.Action
                + "; event count... "
                + e.ActionCount);
    	}
    }
}
