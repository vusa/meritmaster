package za.co.merits.view;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import za.co.merits.bean.MeritsFacade;
import za.co.merits.model.Course;
import za.co.merits.model.Teacher;

@Named
@ConversationScoped
public class TeacherHome extends EntityHome<Teacher> implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private MeritsFacade meritsFacade;

	/*
	 * (non-Javadoc)
	 * 
	 * @see za.co.merits.view.EntityHome#doInit()
	 */
	@Override
	protected boolean doInit() {
		if (getId() == null) {
			ViewUtil.redirect("home.jsf");
			return false;
		}
		return true;
	}

	@Override
	protected Teacher doCreateEntity() {
			return new Teacher();
	}

	@Override
	protected Teacher doLoadEntity() {
        return meritsFacade.getTeacher(getId());
	}

	public List<Course> getCourses() {
		return meritsFacade.getCoursesForTeacher(getEntity());
	}

	/**
	 * Save the person and if it is a teacher, it notifies the student of any
	 * course changes via email using the CDI event mechanism.
	 * 
	 * Also ends the current conversation and redirects to the person view page.
	 */
	public void save() {

		meritsFacade.save(getEntity());
		getConversation().end();
		ViewUtil.redirect("teacherView.jsf?personId=" + getEntity().getId());
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
			ViewUtil.redirect("teacherView.jsf?personId=" + getEntity().getId());
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
	public Teacher getTeacher() {
		return getEntity();
	}

}
