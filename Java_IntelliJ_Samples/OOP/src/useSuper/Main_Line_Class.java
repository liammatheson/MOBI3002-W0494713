package useSuper;

/**
 * Step through using the debugger to see when lines are run.
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

        System.out.println("Make a Derived1");
        Derived1 s1 = new Derived1();
        System.out.println();

        System.out.println("Make a Derived2");
        Derived2 s2 = new Derived2(2, 3);
        System.out.println();

        System.out.println("Make a Derived1");
        Derived1 s3 = new Derived1();
        System.out.println();

        System.out.println("Make a Derived2");
        Derived2 s4 = new Derived2(5, 7);
        System.out.println();

        System.out.println("Make a Derived3");
        Derived3 s5 = new Derived3(5, 7);
        System.out.println("==========================");


        System.out.println("before call Derived1 moveRight");
        boolean b1 = s1.moveRight();
        System.out.println();


        System.out.println("before call Derived2 moveRight");
        boolean b2 = s2.moveRight();
        System.out.println();


        System.out.println("before call Derived3 moveRight");
        boolean b3 = s5.moveRight();
        System.out.println();

    }
}
