package synchMethods.synch;

/**
 * @author Ezra, then Russ
 * @description Try's to change data of a person
 */
public class thread extends Thread {

    /**
     * This copy of data to use to overwrite
     */
    public String name;
    public long phone_number;
    public long cell_number;
    public String email;

    /**
     * thread receives person information
     * @param s Name
     * @param p Phone
     * @param c Cell
     * @param e Email
     */
    public thread(String name, long phone, long cell, String email) {
        this.name = name;
        this.phone_number = phone;
        this.cell_number = cell;
        this.email = email;
    }

    /**
     * Loop setting our name, phone, etc.. into static area.
     */
    @Override
    public void run() {
        System.out.println("Start name = " + this.name);
        try {
            for (int i = 0; i < 10; i++) {
                Data.setStaticThreadperson(this.name, this.phone_number, this.cell_number, this.email);
                //System.out.println("Loop thread " + this.name);
                Thread.sleep(75);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
