package ObserverSample;

import java.util.Observable;

public class myObservable extends Observable {

	eventObject eventToSend = new eventObject();

	// Better to set up a method that packs and sends events for you
	public void triggerEvent(int intEvent) {
		// Set event object accordingly
		eventToSend.ActionInt = intEvent;
		eventToSend.ActionCount = eventToSend.ActionCount + 1;
		// Set flag and notify event handler
		setChanged();
		notifyObservers(eventToSend);
	}

	// a method which notifies observer when a condition is set
	// This method will use our handy triggerEvent method
	public void checkDivisable(int checkInt) {
		System.err.println(); // white space in output
		// Default event object
		eventToSend.Action = " is divisible by " + checkInt;
		eventToSend.ActionInt = 0;
		eventToSend.ActionCount = 0;
		// Check the division
		for (int i = 1; i < 100; i++) {
			if (i % checkInt == 0) {
				triggerEvent(i); // Invoke event handler with this int
			}
		}
	}

	// a method which notifies observer when a condition is set
	// This method sends the event directly (bulkier code)
	public void checkDivisibleAndEven(int checkInt) {
		eventObject eventToSend = new eventObject();
		System.err.println(); // white space in output
		// Default event object
		eventToSend.Action = " is divisible by " + checkInt + " and even ";
		eventToSend.ActionInt = 0;
		eventToSend.ActionCount = 0;
		// Check the division...and even (divides by 2 evenly)
		for (int i = 1; i < 100; i++) {
			if ((i % checkInt == 0) && (i % 2 == 0)) {
				// Set event object accordingly
				eventToSend.ActionInt = i;
				eventToSend.ActionCount = eventToSend.ActionCount + 1;
				// Set flag and notify event handler
				setChanged();
				notifyObservers(eventToSend);
			}
		}
	}
}
