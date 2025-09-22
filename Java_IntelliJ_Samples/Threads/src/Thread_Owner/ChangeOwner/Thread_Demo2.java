package Thread_Owner.ChangeOwner;

/**
 * 
 * This demo is just the main line, and uses another class to 
 * define the thread.  This sample makes it easier to see
 * what part is the thread and what part is the demo structure.
 * 
 * @author Russ
 *
 */
public class Thread_Demo2 {

    public static void main(String args[]) {
        Thread_Class2 a = new Thread_Class2("Hello, ", 2);
        a.start();

        Thread_Class2 b = new Thread_Class2("So ", 3);
        b.start();

        Thread_Class2 c = new Thread_Class2("You ", 4);
        c.start();

        Thread_Class2 d = new Thread_Class2("Want ", 1);
        d.start();

        Thread_Class2 e = new Thread_Class2("To ", 3);
        e.start();

        Thread_Class2 f = new Thread_Class2("Say Goodbye? ", 2);
        f.start();

        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println(
                    "            OWNER IS = " +
                    Thread_Class2.getOwner());
        }
    }
}
