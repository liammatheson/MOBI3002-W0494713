package flowSample_StackOverflow;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author StackOverflow
 *         https://stackoverflow.com/questions/53632600/java-9-how-publisher-and-subscriber-works
 *
 */
public class MyReactiveApp {

	public static void main(String args[]) throws InterruptedException {

		SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

		MySubscriber subs = new MySubscriber();
		publisher.subscribe(subs);

		List<String> strs = getStrs();

		System.out.println("Publishing Items to Subscriber: Thread: "+Thread.currentThread().getName() );
		// ...as a lambda expression...
		// https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
		// strs.stream().forEach(i -> publisher.submit(i));
		for (String i : strs) {
			publisher.submit(i);
			System.out.print(".");
			Thread.sleep(100);
		}

		while (strs.size() != subs.getCounter()) {
			Thread.sleep(10);
		}

		publisher.close();

		System.out.println("Exiting the app");

	}

	private static List<String> getStrs() {

		return Stream.generate(new Supplier<String>() {
			int i = 1;

			@Override
			public String get() {
				return "name " + (i++);
			}
		}).limit(100).collect(Collectors.toList());
	}

}