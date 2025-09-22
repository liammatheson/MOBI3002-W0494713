package NotifyWait;

/**
 * Start up a few threads and make them interact with either busy/wait
 * or wait/notify.  Six running threads are needed to release all the threads.
 * So, the first 5 threads wait for the sixth.
 *
 * Change wait method at line 34 of Thread_object.java.
 * 
 * Reference...http://java.sun.com/docs/books/tutorial/essential/index.html
 * 
 * @author Russ
 */
public class Thread_test {

    public static void main(String[] args) throws Exception {

        // Make sure you have one object to lock around, not many
        lockMe lockmeObject = new lockMe();
        lockMe lockmeObject2 = new lockMe();

        the_monitor m = new the_monitor();
        new Thread(m).start();

        // Give the monitor a chance to give status
        Thread.sleep(100);

        Thread_object t1 = new Thread_object(1, lockmeObject);
        Thread_object t2 = new Thread_object(2, lockmeObject);
        Thread_object t3 = new Thread_object(3, lockmeObject);
        Thread_object t4 = new Thread_object(4, lockmeObject);
        Thread_object t5 = new Thread_object(5, lockmeObject2);
        Thread_object t6 = new Thread_object(6, lockmeObject2);
        Thread_object t7 = new Thread_object(7, lockmeObject2);
        Thread_object t8 = new Thread_object(8, lockmeObject2);
        Thread_object t9 = new Thread_object(9, lockmeObject2);
        Thread_object t10 = new Thread_object(10, lockmeObject2);
        Thread_object t11 = new Thread_object(11, lockmeObject2);

        Thread_object t12 = new Thread_object(12, lockmeObject);

        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        t11.start();
        t12.start();

        // Give some time for 5 threads to wait around awhile.
        Thread.sleep(7000);

        Thread_object t100 = new Thread_object(6, lockmeObject);
        t100.start();
        System.out.println("6th Thread Started");

        Thread.sleep(1000);

    }
}
