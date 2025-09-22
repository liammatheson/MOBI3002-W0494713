package threadBasics;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Show two methods of threads waiting for signal.
 * 
 * @author Russ
 */
public class Thread_object extends Thread {

    /**
     * Append "Russ-" to this thread's name...just to show we can.
     *  
     * @param thread_number Thread name will be "Russ-" and the thread number.
     */
    public Thread_object(int thread_number) {
        this.setName("Russ-" + thread_number);
        //this.setPriority(thread_number);
    }

    @Override
    public void run() {
        {
            try //for (int i = Thread.MIN_PRIORITY; i <= Thread.MAX_PRIORITY; i++)
            {
                this.setPriority(Thread.MIN_PRIORITY);

                System.out.println("============================= ");
                System.out.println("this.activeCount() = " + Thread_object.activeCount());
                Thread.sleep(20);
                System.out.println("this.getName() = " + this.getName());
                Thread.sleep(20);
                System.out.println("currentThread = " + Thread_object.currentThread());
                Thread.sleep(20);
                System.out.println("this.getPriority() = " + this.getPriority());
                Thread.sleep(20);
                System.out.println("this.isAlive() = " + this.isAlive());

                Thread.sleep(2);
                System.err.println("this.toString() = " + this.toString());
            } catch (InterruptedException ex) {
                Logger.getLogger(Thread_object.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

