package za.co.merits.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Teacher entity that extends the {@link Person} class and adds a list of
 * classes they are teaching.
 * 
 * @author Andy Gibson
 * 
 */
@Entity
@Table(name="teacher")
public class Teacher extends Person {

	public Teacher() {
	}

	public Teacher(String firstName, String lastName) {
		super(firstName, lastName);
	}

	@OneToMany(mappedBy = "teacher")
	private List<Course> coursesTaught;

	public List<Course> getCoursesTaught() {
		return coursesTaught;
	}

	public void setCoursesTaught(List<Course> coursesTaught) {
		this.coursesTaught = coursesTaught;
	}

}
