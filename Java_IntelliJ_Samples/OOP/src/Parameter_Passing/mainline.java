package Parameter_Passing;

public class mainline {

    public static void main(String[] args) {
        // Look at public, private, and protected

        System.out.println("====== Start =======");
        Person p1 = new Person("Russ", "NSCC", "Oak Street", "NSCC");

        System.out.println(p1.first_Name); // Direct access to class field
        System.out.println(p1.Street); // in this package this works

        System.out.println(p1.getFirst_Name()); // Use getter is better
        System.out.println(p1.getStreet()); // getter is public
        System.out.println(p1.getEmployment()); // getter is public

        {
            // Nested scope
            System.out.println("====== Nested scope =======");
            Person p2 = new Person("Russ2", "NSCC2", "Oak Street2", "NSCC2");

            System.out.println(p2.getStreet()); // getter is public
            System.out.println(p2.getEmployment()); // getter is public

            // Still access p1
            System.out.println(p1.getFirst_Name()); // Use getter is better

            // set the pointer p1 to point to the same object that p2 points to
            p1 = p2;

        }

        System.out.println("====== After Nested scope =======");

        // Still access p1
        System.out.println(p1.getLast_Name()); // Use getter is better


        //  Try some parameter passing...what comes back from the called method?
        HandleParameter hp = new HandleParameter();

        // give integer, change integer
        int i = 5;
        hp.give_integer_change_integer(i);
        System.out.println("====== After Int: " + i);

        // give p1, change part of object p1 points to
        hp.change_part_of_object(p1);
        System.out.println("====== After Int: " + p1.first_Name);

        // give p1, change p1
        hp.give_p1_change_p1(p1);
        System.out.println("====== After Int: " + p1.first_Name);


    }
}
