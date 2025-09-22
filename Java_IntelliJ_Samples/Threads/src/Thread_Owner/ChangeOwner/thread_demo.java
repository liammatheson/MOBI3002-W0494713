package Thread_Owner.ChangeOwner;

/**
 * @author Russell
 *
 */
public class thread_demo extends Thread {

    private String inner_string;
    private int delay;
    static private String owner;

    public thread_demo(String s, int d) {
        inner_string = s;
        delay = d;
    }

    public synchronized void Be_Owner(String s) {
        // thread that gets here is owner
        owner = s;
    }

    @Override
    public void run() {

        System.err.print(inner_string);
        try {
            for (int i = 0; i < 100; i++) {
                System.out.println(inner_string);
                Be_Owner(inner_string);
                Thread.sleep(delay);
            }
        } catch (Exception e) {
            return;
        }

    }

    public static void main(String args[]) {
        thread_demo a = new thread_demo("Hello, ", 2);
        a.start();
        thread_demo b = new thread_demo("So ", 2);
        b.start();
        thread_demo c = new thread_demo("You ", 3);
        c.start();
        thread_demo d = new thread_demo("Want ", 3);
        d.start();
        thread_demo e = new thread_demo("To ", 1);
        e.start();
        thread_demo f = new thread_demo("Say Goodbye? ", 1);
        f.start();

        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            //String o = owner;
            System.out.println("            OWNER IS = " + owner);
        }
    }
}
