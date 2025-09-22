package threadBasics;

/**
 * Start up a few threads and make them interact with either busy/wait or
 * wait/notify.
 *
 * Reference...http://java.sun.com/docs/books/tutorial/essential/index.html
 *
 * @author Russ
 */
public class Thread_test {

    public static void main(String[] args) throws Exception {


    	// Get number of cores
    	int cores = Runtime.getRuntime().availableProcessors();
    	System.out.println("  # of cores = " + cores);

        Thread_object t1 = new Thread_object(1);
        Thread_object t2 = new Thread_object(2);
        Thread_object t3 = new Thread_object(3);
        Thread_object t4 = new Thread_object(4);

        // Check isAlive before starting threads
        System.out.println("  ## t1 is alive..." + t1.isAlive());
        
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);
        t3.setPriority(Thread.MIN_PRIORITY);
        t4.setPriority(Thread.MIN_PRIORITY);

        
        t1.start();

        t2.start();
        t3.start();
        t4.start();
        
        // Check isAlive after starting threads
        System.out.println("  ## t1 is alive..." + t1.isAlive());

        Thread.sleep(20);
        Thread.sleep(5000);

        // Check isAlive after starting threads
        System.out.println("  ## t1 is alive..." + t1.isAlive());

    }
}
