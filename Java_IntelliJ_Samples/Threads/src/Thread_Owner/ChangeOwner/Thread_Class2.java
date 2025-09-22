package Thread_Owner.ChangeOwner;

/**
 * 
 * This is a small class that contains a string field.
 * and prints this string fields after a given delay.
 * Several of these classes running together would 
 * output these string in some sort of interleaved pattern. 
 * 
 *  Items to note:
 *  	1) extends Thread
 *  	2) keyword synchronized
 *  	3) keyword static
 *  	4) sleep
 * 
 * @author Russ
 *
 */
public class Thread_Class2 extends Thread {

    private String inner_string;
    private int delay;
    private static String owner;

    public Thread_Class2(String s, int d) {
        inner_string = s;
        delay = d;
        this.setPriority(d);
    }

    public synchronized void setOwner(String s) {
        // thread that gets here is owner
        owner = s;
    }

    public synchronized static String getOwner() {
        // thread that gets here is owner
        return owner;
    }

    public void run() {

        System.err.print(inner_string);
        try {
            for (int i = 0; i < 100; i++) {
                System.out.println(inner_string);
                setOwner(inner_string);
                Thread.sleep(delay);
            }
        } catch (Exception e) {
            return;
        }

    }
}
