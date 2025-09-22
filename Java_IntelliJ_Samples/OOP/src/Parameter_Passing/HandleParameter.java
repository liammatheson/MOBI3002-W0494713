package Parameter_Passing;

public class HandleParameter {

    public void give_integer_change_integer(int i) {
        i = i + 1;
    }


    public void change_part_of_object(Person p){
        p.first_Name = "Changed in parameter passing class";
        p.Street = "xxxxxx";
    }

    void give_p1_change_p1(Person p1) {
        Person p3 = new Person("first","last","street","employment");
        p1 = p3;
    }

}
