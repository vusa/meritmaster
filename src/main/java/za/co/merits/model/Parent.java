package za.co.merits.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vusa
 * Date: 2012/11/27
 * Time: 10:17 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Parent extends Person {

    @OneToMany(mappedBy = "parent")
    private List<Student> students;

    public Parent(){
    }

    public Parent(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
