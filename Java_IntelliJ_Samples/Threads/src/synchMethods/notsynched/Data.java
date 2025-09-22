package synchMethods.notsynched;

/**
 * @author Ezra, then Russ description: class to hold a persons data.
 */

public class Data {

	public enum ozcol {
		a, b, c
	}

	public static String name;
	public static long phone_number;
	public static long cell_number;
	public static String email;

	/**
	 * Constructor for static data area. Set a default.
	 * 
	 * @param name
	 * @param phone
	 * @param cell
	 * @param email
	 */
	public Data(String name, long phone, long cell, String email) {
		Data.name = name;
		Data.phone_number = phone;
		// mySleep(20);
		Data.cell_number = cell;
		Data.email = email;
	}

	/**
	 * Get the string version of the data appended. We get all one set of data
	 * this way.
	 * 
	 * @return A string which is the data fields appended.
	 */
	public static String getStaticThreadperson() {
		return "name= " + Data.name + " cell= " + Data.cell_number + " email= "
				+ Data.email + " phone= " + Data.phone_number;
	}


	/**
	 * Set the static fields here.
	 * 
	 * @param name
	 * @param phone
	 * @param cell
	 * @param email
	 */
	public static void setStaticThreadperson(String name, long phone, long cell, String email) {
		//public synchronized static void setStaticThreadperson(String name, long phone, long cell, String email) {
		Data.name = name;
		mySleep(5);
		Data.phone_number = phone;
		mySleep(5);
		Data.cell_number = cell;
		mySleep(5);
		Data.email = email;
		mySleep(5);
	}

	public static void mySleep(int d) {
		try {
			Thread.sleep(d);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
