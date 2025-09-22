package NotifyWait;

/**
 * Show two methods of threads waiting for signal.
 * 
 * @author Russ
 */
public class Thread_object extends Thread {

    // The object to lock onto
    lockMe lockmeObject;
    int thread_number;

    /**
     * Append "Russ-" to this thread's name...just to show we can.
     *  
     * @param thread_number Thread name will be "Russ-" and the thread number.
     */
    public Thread_object(int thread_number, lockMe lockmeObject) {
        this.thread_number = thread_number;
        this.lockmeObject = lockmeObject;
        this.setName("Russ-" + thread_number);
    }

    @Override
    public void run() {

        // increment lock
        lockmeObject.increment_waiting_count();
        Thread.yield();

        // wait until all threads arrive
        //lockmeObject.Notify_Wait_here();  // Notify-Wait sleeps until time to act
        lockmeObject.busy_wait_here();  // Busy-wait wastes CPU cycles

        Thread.yield();

        // decrement count and end
        lockmeObject.decrement_waiting_count();

    }
}
