package FlowSample;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.function.Consumer;

class SampleSubscriber<T> implements Subscriber<T> {
	   final Consumer<? super T> consumer;
	   Subscription subscription;
	   final long bufferSize;
	   long count;
	   SampleSubscriber(long bufferSize, Consumer<? super T> consumer) {
	     this.bufferSize = bufferSize;
	     this.consumer = consumer;
	   }
	   public void onSubscribe(Subscription subscription) {
	     long initialRequestSize = bufferSize;
	     count = bufferSize - bufferSize / 2; // re-request when half consumed
	     (this.subscription = subscription).request(initialRequestSize);
	   }
	   public void onNext(T item) {
	     if (--count <= 0)
	       subscription.request(count = bufferSize - bufferSize / 2);
	     consumer.accept(item);
	   }
	   public void onError(Throwable ex) { ex.printStackTrace(); }
	   public void onComplete() {}
	 }