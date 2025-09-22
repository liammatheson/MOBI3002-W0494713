package NotifyWait;

public class the_monitor extends Thread {

    @Override
    public void run() {
        try {
            the_monitor.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (int i = 0; i < 4; i++) {
            System.out.println(
                    "                Monitor...activeCount=" + Thread_object.activeCount());
            System.out.println(
                    "                Monitor...currentThread=" + Thread_object.currentThread());
            try {
                the_monitor.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
