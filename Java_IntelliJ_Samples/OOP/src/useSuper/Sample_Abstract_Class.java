package useSuper;

/**
 * The description.
 *
 * @author Russell Shanahan
 *
 */
public abstract class Sample_Abstract_Class {

    public Sample_Abstract_Class() {

        System.out.println("Constructor: Sample_Abstract_Class");
    }

    public Sample_Abstract_Class(int x, int y) {

        System.out.println("Constructor: Sample_Abstract_Class + x,y => " + x + ", " + y);
    }

    /**
     *
     * The description.
     *
     * @return the boolean indicates success/failure
     */
    public abstract boolean moveLeft();

    /*
     * to add a JavaDoc comment, type the /** just before the method or class
     * and hit "Enter". Eclipse will automatically create the comment template.
     *
     * Try it.
     */
    public boolean moveRight() {
        System.out.println("Abstract moveRight");
        return true;
    }
}
