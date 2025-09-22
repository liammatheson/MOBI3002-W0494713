package OO_Sample_PKG;

/**
 * The description. This description covers the use of the full class.
 * 
 * @author Russell Shanahan
 * 
 */
public abstract class Sample_Abstract_Class {

    /**
     * This method is declared in the base class only.
     * @return  returns a 57 just as a sample number.
     */
    public int thisMeathodisBase(){
        return 57;
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
	public abstract boolean moveRight();

}
