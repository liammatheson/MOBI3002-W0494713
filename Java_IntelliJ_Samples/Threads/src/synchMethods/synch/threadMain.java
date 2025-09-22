package synchMethods.synch;

//import synchMethods.notsynched.thread;

/**
 * Make a few threads compete for a static set of fields.
 * 
 * @author Ezra, then Russ
 * 
 */
public class threadMain {

    /**
     * @param args
     */
    public static void main(String[] args) {

        thread a = new thread("James22", 992297822, 777734722, "ahh22@email.com");
        a.start();

        thread b = new thread("Craig33", 956537833, 546456433, "boo33@email.com");
        b.start();
//
//        thread c = new thread("Joe44", 955543844, 546565444, "cic44@email.com");
//        c.start();

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(50);
                System.out.println(Data.getStaticThreadperson());
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

        //System.out.println(thread.personChange.name + " " + thread.personChange.cell_number + " " + thread.personChange.phone_number + " " + thread.personChange.email);
        }

    }
}
