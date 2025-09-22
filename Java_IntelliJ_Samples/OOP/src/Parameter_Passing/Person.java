package Parameter_Passing;

public class Person {

    public String first_Name;
    public String Last_Name;
    protected String Street;
    private String Employment;

    public Person(String first_Name, String last_Name, String street,
            String employment) {
        super();
        Employment = employment;
        Last_Name = last_Name;
        Street = street;
        this.first_Name = first_Name;
    }

    public synchronized String getFirst_Name() {
        return first_Name;
    }

    public synchronized void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public synchronized String getLast_Name() {
        return Last_Name;
    }

    public synchronized void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public synchronized String getStreet() {
        return Street;
    }

    public synchronized void setStreet(String street) {
        Street = street;
    }

    public synchronized String getEmployment() {
        return Employment;
    }

    public synchronized void setEmployment(String employment) {
        Employment = employment;
    }
}
