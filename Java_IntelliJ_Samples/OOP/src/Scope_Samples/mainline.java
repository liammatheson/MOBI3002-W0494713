package Scope_Samples;

import Parameter_Passing.Person;

public class mainline {

   
    public static void main(String[] args) {
        // Look at public, private, and protected

        Person p1 = new Person("Russ", "Shanahan", "Oak Street", "NSCC");

        System.out.println(p1.first_Name); // Direct access to class field
        //System.out.println(p1.Street); // in this package this works

        System.out.println(p1.getFirst_Name()); // Use getter is better
        System.out.println(p1.getStreet()); // getter is public
        System.out.println(p1.getEmployment()); // getter is public

        {
            // Nested scope
            Person p2 = new Person("Russ2", "NSCC2", "Oak Street2", "NSCC2");

            System.out.println(p2.getStreet()); // getter is public
            System.out.println(p2.getEmployment()); // getter is public

            // Still access p1
            System.out.println(p1.getFirst_Name()); // Use getter is better
            //System.out.println(p1.); // Use getter is better

//            p1.Street= "hello";
//            System.out.println(p1.Street); // Use getter is better
        }

        // Still access p1
        System.out.println(p1.getLast_Name()); // Use getter is better


        // Can't access p2...why?
        //System.out.println(p2.getEmployment()); // getter is public


    }
}
