package OO_Sample_PKG;


/**
 * The description.
 * 
 * @author Russell Shanahan
 *  
 */
public class Main_Line_Class {

	/**
	 * @param args The input arguments from the command line.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Abstract Class");
		
		Sample_Derived_Class s1 = new Sample_Derived_Class();
		System.out.println("Sample Derived Class: " + s1.moveLeft());
		
		Sample_Class_Uses_Interface s2 = new Sample_Class_Uses_Interface();
		System.out.println("Sample Class With Interface: " + s2.moveDown());


	}

}
