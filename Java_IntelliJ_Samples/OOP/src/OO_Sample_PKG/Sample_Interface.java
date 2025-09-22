package OO_Sample_PKG;

/**
 * @author Russell Shanahan
 * 
 */
public interface Sample_Interface {

	/**
	 * Sample comment for moveup.
	 * @return Just return anything, this is a sample.
	 */
	public boolean moveUp();
	
	/**
	 * Test
	 * @param x comment x
	 * @param y comment y
	 * @param z comment z
	 * @return
	 */public boolean moveDown(int x, int y, int z);

	/**
	 * Added with eclipse refactoring support
	 * @return a boolean
	 */
	 boolean moveDown();
	
	
}
