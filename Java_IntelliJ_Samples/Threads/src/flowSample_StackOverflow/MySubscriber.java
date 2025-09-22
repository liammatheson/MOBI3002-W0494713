package flowSample_StackOverflow;

import java.util.concurrent.Flow.Subscription;

public class MySubscriber implements java.util.concurrent.Flow.Subscriber<String> {

	private Subscription subscription;

	private int counter = 0;

	@Override
	public void onSubscribe(Subscription subscription) {
		System.out.println("...onSubscribe:");
		this.subscription = subscription;
		subscription.request(100);
	}

	@Override
    public void onNext(String item) {
		// Also print thread name to identify thread decoupling from Publisher
        System.out.println(this.getClass().getSimpleName()+" item "+item+ " Thread: "+ Thread.currentThread().getName());
        //subscription.request(1);
        counter++;
    }

	@Override
	public void onError(Throwable throwable) {
		System.out.println(this.getClass().getName() + " an error occured " + throwable);

	}

	@Override
	public void onComplete() {
		System.out.println("activity completed");

	}

	public int getCounter() {
		return counter;
	}

}