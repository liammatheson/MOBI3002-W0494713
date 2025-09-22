package NotifyWait;

public class lockMe extends Object {

    // Create one static copy of this object for locking
    public lockMe lockmeObject;
    private  volatile int count = 0;
    private  volatile boolean bunching = false;

    public synchronized void increment_waiting_count() {
       this.count =this.count + 1;
        System.out.println("##### inc count = " +this.count);
        this.bunching = this.bunching ||this.count > 5;
        if (this.bunching) {
        	System.out.println(" # Bunching is now set # ");
            this.notifyAll();
        }
    }

    public synchronized void decrement_waiting_count() {
        if (!this.bunching) {
            System.out.println("@@@@@@ Bunching and decrementing");
        }
       this.count =this.count - 1;
        System.out.println("##### dec count = " +this.count);
        this.notifyAll();
    }

    public synchronized int getcount() {
        return this.count;
    }

    /**
     * Block until all threads are ready to proceed.
     * Seems the Wait() wants a value as in wait(10).
     * Wait() without a param waits forever.
     */
    public synchronized void Notify_Wait_here() {
        while (!this.bunching) {
            try {
                this.wait();     // waits for notify() call from Producer

            } catch (InterruptedException e) {
            }
        }
        this.notifyAll();

    }

    public void busy_wait_here() {
        System.out.print(":");
        while (!this.bunching) {
            //try {
                System.out.print(".");
              //  Thread.sleep(5);   // wake up often wastes CPU, Wait long causes delay
           // } catch (InterruptedException e) {
                // TODO Auto-generated catch block
             //  e.printStackTrace();
           // }
        }
    }
} 
