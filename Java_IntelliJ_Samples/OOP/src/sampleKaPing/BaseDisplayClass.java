package sampleKaPing;

public class BaseDisplayClass {

	private int startPos;
	private int length;
	private char ch = 'X';

	public BaseDisplayClass(char ch, int startPos, int length) {
		this.ch = ch;
		this.startPos = startPos;
		this.length = length;
	}

	/**
	 * Return true if display objects overlap.
	 * 
	 * @param otherDisplayObject - display object to check against this.
	 * @return true if these objects collide
	 */
	public boolean doesItCollide(BaseDisplayClass otherDisplayObject) {

		if (this == otherDisplayObject) {
			System.out.println("Same as me!!!");  // same object, return false
		} 

		return ((this.startPos < (otherDisplayObject.startPos + otherDisplayObject.length + 20))
				&& (this.startPos + this.length > otherDisplayObject.startPos));
	}

	/**
	 * Print the display object in it's place
	 */
	public void printit() {
		for (int i = 0; i < this.startPos; i++) {
			System.out.print(" ");
		}

		for (int i = 0; i < this.length; i++) {
			System.out.print(ch);
		}
		System.out.println();
	}

	public String toString() {
		return "Pos=>" + this.startPos + "  Length=>" + this.length;
	}

}
