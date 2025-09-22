package OO_Sample_PKG;

/**
 * The implemented methods can be hidden 
 * from the customer.  The Customer doesn't see this source 
 * (they get the .class file instead).
 *  
 * @author Russell Shanahan
 * 
 */
public class Sample_Derived_Class extends Sample_Abstract_Class {

	/**
	 * The parameter tags are inserted automatically when the 
	 * JavaDoc comment is started. 
	 * @param X  The change in x location.
	 * @param Y  The change in y location.
	 * @return true if the move was successful.
	 */public boolean move(int X, int Y){
		return false;
	
	}
         
    public Sample_Derived_Class() {
    }

	@Override
	public boolean moveLeft() {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	public boolean moveRight() {
//		// TODO Auto-generated method stub
//		return false;
//	}

    //@Override
        /**
         *  Friday 13th comment This methods moves something a 
         * little bit to the right, and then does the Java Shuffle.
         * @return a flag indicating if the user, in fact, can do the Java shuffle.
         */
        public boolean moveRight() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
