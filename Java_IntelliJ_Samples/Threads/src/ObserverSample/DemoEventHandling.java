package ObserverSample;

public class DemoEventHandling {

    public static void main(String[] args) {

        // Create instance of objects that get observed
        myObservable doesSomething = new myObservable();

        //... and does the observing
        myObserver listens_1 = new myObserver();
        myObserver listens_2 = new myObserver();
        myObserver listens_3 = new myObserver();

        // register listens_1 as an observer for doesSomething
        doesSomething.addObserver(listens_1);
        doesSomething.addObserver(listens_2);
        //doesSomething.addObserver(listens_3);

        // Do some work which will be observed
        doesSomething.checkDivisable(23);
        doesSomething.checkDivisibleAndEven(13);
    }
}
