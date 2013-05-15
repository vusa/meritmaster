package za.co.merits.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Student entity that extends the {@link Person} class and adds a
 * <code>gpa</code> attribute as well as a list of classes they are enrolled in.
 * 
 * @author Andy Gibson
 * 
 */
@Entity
@Table(name = "student")
public class Student extends Person {

	public Student() {
	}

	public Student(String firstName, String lastName) {
		super(firstName, lastName);
	}

	@Min(0)
	@Max(4)
	private Float merits;

    @ManyToOne
    private Parent parent;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="student_course",
            joinColumns=
            @JoinColumn(name="student_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="course_id", referencedColumnName="id")
    )
	private List<Course> courses = new ArrayList<Course>();

    @OneToMany(mappedBy = "student")
    private List<AssignmentScore> assignmentScores = new ArrayList<AssignmentScore>();

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> enrolled) {
		this.courses = enrolled;
	}

	public String getCoursesCodes() {
		String result = "";
		for (Course course : getCourses()) {
			if (result.length() != 0) {
				result = result + ", ";
			}
			result += course.getCode();
		}
		return result;
	}

	public Float getMerits() {
		return merits;
	}

	public void setMerits(Float gpa) {
		this.merits = gpa;
	}

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public List<AssignmentScore> getAssignmentScores() {
        return assignmentScores;
    }

    public void setAssignmentScores(List<AssignmentScore> assignmentScores) {
        this.assignmentScores = assignmentScores;
    }
}
