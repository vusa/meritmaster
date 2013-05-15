package za.co.merits.view;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import za.co.merits.bean.MeritsFacade;
import za.co.merits.bean.TeacherCourseCount;
import za.co.merits.model.Course;
import za.co.merits.model.Teacher;

/**
 * This class is used to hold producer methods for view related items in the
 * application. This bean is only used when an item is requested that is
 * produced by one of these methods.
 * 
 * @author Andy Gibson
 * 
 */
public class ViewProducers {

	@EJB
	private MeritsFacade meritsFacade;

	/**
	 * Returns a list of JSF {@link SelectItem} objects for the teachers in the
	 * application based on ID and Name.
	 * 
	 * @return List of JSF select items for teachers
	 */
	@Produces
	@Named("teacherItems")
	@RequestScoped
	public List<SelectItem> generateTeacherItems() {
		List<Teacher> teachers = meritsFacade.getTeachers();
		return ViewUtil.wrapInSelectItems(teachers, "Select Teacher");
	}

	/**
	 * Returns a list of JSF {@link SelectItem} objects for the courses in the
	 * application based on ID and Name.
	 * 
	 * @return List of JSF select items for teachers
	 */
	@Produces
	@Named("courseItems")
	@RequestScoped
	public List<SelectItem> generateCourseItems() {
		List<Course> courses = meritsFacade.getCourses();
		return ViewUtil.wrapInSelectItems(courses, "Select Teacher");
	}
	
	@Produces
	@Named("teacherCourseCounts")
	@RequestScoped
	public List<TeacherCourseCount> generateTeacherCountList() {
		return meritsFacade.getTeachCourseCounts();
	}
	
}
