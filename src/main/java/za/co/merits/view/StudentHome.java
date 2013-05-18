package za.co.merits.view;

import za.co.merits.bean.MeritsFacade;
import za.co.merits.model.Course;
import za.co.merits.model.Student;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ConversationScoped
public class StudentHome extends EntityHome<Student> implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private MeritsFacade meritsFacade;

	private List<Course> courses;

   private boolean addNew;

	// snapshot of the students enrolled courses is captured at load time so we
	// can determine any changes later on.
	private List<Course> initialEnrollment = new ArrayList<Course>();

	/*
	 * (non-Javadoc)
	 *
	 * @see za.co.merits.view.EntityHome#doInit()
	 */
	@Override
	protected boolean doInit() {
		if (getId() == null && !addNew) {
			ViewUtil.redirect("home.jsf");
			return false;
		}
		return true;
	}

	@Override
	protected Student doCreateEntity() {
			return new Student();
	}

	@Override
	protected Student doLoadEntity() {
		Student student = meritsFacade.getStudent(getId());
		// grab a snapshot of courses taken by the student so we know what
		// changed when we save it so we can notify the student.
			initialEnrollment.addAll(student.getCourses());
		return student;
	}

	/**
	 * Returns a list of courses for this person. If the person is a
	 * {@link za.co.merits.model.Student}, it returns the courses enrolled.
	 *
	 * @return List of {@link za.co.merits.model.Course} objects
	 */
	public List<Course> getCourses() {
		if (courses == null) {
				courses = meritsFacade.getCoursesForStudent(getEntity());
		}
		return courses;
	}

	public void save() {
		meritsFacade.save(getEntity());
		meritsFacade.notifyCourseChanges(getEntity(), initialEnrollment);
		getConversation().end();
		ViewUtil.redirect("studentView.jsf?personId=" + getEntity().getId());
	}

	/**
	 * Called when the user cancel changes to the person. Ends the conversation
	 * and redirects the user to another page. If this entity is a new one (and
	 * therefore doesn't exist since we cancelled changes) then the user is
	 * redirected home. Otherwise they are redirected to the view page for the
	 * person.
	 */
	public void cancel() {
		getConversation().end();
		if (isManaged()) {
			ViewUtil.redirect("studentView.jsf?personId=" + getEntity().getId());
		} else {
			ViewUtil.redirect("home.jsf");
		}
	}

	/**
	 * This method just makes things more readable so we can use
	 * <code>getPerson()</code> instead of <code>getEntity</code>.
	 *
	 * @return The entity as a person
	 */
	public Student getStudent() {
		return getEntity();
	}

    public boolean isAddNew() {
        return addNew;
    }

    public void setAddNew(boolean addNew) {
        this.addNew = addNew;
    }
}
