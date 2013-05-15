package za.co.merits.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import za.co.merits.model.Parent;
import za.co.merits.qualifier.DataRepository;
import za.co.merits.model.Course;
import za.co.merits.model.Student;
import za.co.merits.model.Teacher;

/**
 * This class initializes the data in the database for the demo applications. It
 * is invoked when the JSF page calls for the value of the
 * <code>#{dataStatus}</code> expression. This bean produces that value using
 * the CDI {@link Produces} annotation. When invoked, it checks to see if the
 * data already exists and if not, it generates the data before returning the
 * value. 
 * 
 * @author Andy Gibson
 * 
 */
public class DataFactory {

	private static Random random;

	@Resource
	private UserTransaction utx;

	@Inject
	@DataRepository
	private EntityManager entityManager;

	private List<Student> students = new ArrayList<Student>();
	private List<Course> courses = new ArrayList<Course>();
	private List<Teacher> teachers = new ArrayList<Teacher>();

	private void hookUp() {
		random = new Random(102405);
		for (Course c : courses) {
			int count = 0;
			while ((count < 20 || random.nextInt(100) < 70) && count < 40) {
				Student s = getRandomItem(students);
				if (!s.getCourses().contains(c)) {
					s.getCourses().add(c);
					count++;
				}
			}
		}
		for (Student s : students) {
			entityManager.merge(s);
		}

	}

	public void createData() throws NotSupportedException, SystemException {
		if (!isDataCreated()) {
			utx.begin();
			try {
				random = new Random(102405);

				buildStudents();
				buildTeachers();
				buildCourses();

				hookUp();
				doCustomData();
				utx.commit();
			} catch (Exception e) {
				utx.rollback();
				e.printStackTrace();
			}
		}
	}

	private Teacher createTeacher(String firstName, String lastName) {
		Teacher item = new Teacher(firstName, lastName);
		item.setCreatedOn(createDate(200));
		entityManager.persist(item);
		return item;
	}

	private void buildTeachers() {
		teachers.add(createTeacher("NICHOLAS", "MCINTYRE"));
		teachers.add(createTeacher("MARCUS", "PUGH"));
		teachers.add(createTeacher("NORMA", "TYLER"));
		teachers.add(createTeacher("EMILY", "MCINTYRE"));
		teachers.add(createTeacher("JEAN", "EWING"));
		teachers.add(createTeacher("WYATT", "TYLER"));
		teachers.add(createTeacher("ZACHARY", "FOSTER"));
		teachers.add(createTeacher("DIANE", "ENGLISH"));
		teachers.add(createTeacher("GARY", "HAYES"));
		teachers.add(createTeacher("SABRINA", "LOWE"));
		teachers.add(createTeacher("RANDI", "BARRON"));
		teachers.add(createTeacher("JEFFREY", "BARNES"));
		teachers.add(createTeacher("LEWIS", "HOPPER"));
		teachers.add(createTeacher("JANET", "MACIAS"));
		teachers.add(createTeacher("DEAN", "CASTRO"));
		teachers.add(createTeacher("TRENT", "COPELAND"));
		teachers.add(createTeacher("CHRISTINE", "EDWARDS"));
		teachers.add(createTeacher("DORIS", "CASTILLO"));
		teachers.add(createTeacher("HUNTER", "ROGERS"));
		teachers.add(createTeacher("VINCENT", "PHELPS"));
		teachers.add(createTeacher("WESLEY", "SHIELDS"));
		teachers.add(createTeacher("GREGORY", "HUGHES"));
	}

	public Student createStudent(String firstName, String lastName) {
		Student item = new Student(firstName, lastName);
		item.setCreatedOn(createDate(200));
        item.setParent(createParent(lastName));
		item.setMerits(1 + random.nextInt(30) / 10f);
		entityManager.persist(item);
		return item;

	}

    private Parent createParent(String lastName){
        Parent parent = new Parent(random("Lerato", "Themba","Forbes","Mzwakhe", "Mgcini","Katlego","Mphokuhle", "James","Judith","Sipho","Tebogo","Lebo","Thandi","Palesa","Karabo", "Bongi","Thabo"),lastName);
        parent.setContactNumber(createContactNumber());
        entityManager.persist(parent);
        return parent;
    }

    private String createContactNumber(){
        StringBuilder sb = new StringBuilder("+27 ");
        sb.append(random("71","72","73","74","76","78","79","81","82","83","84"));
        sb.append(" ");
        for(int i = 0; i<7; i++){
            sb.append(new Double(Math.random()*10).intValue());
        }

        return sb.toString();
    }

    private String random(String... items){
        return items[new Double(Math.random() * items.length).intValue()];
    }

	private Date createDate(int minDaysBack) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -random.nextInt(minDaysBack));
		return cal.getTime();
	}

	private void buildStudents() {
		students.add(createStudent("ROSE", "MDLADLA"));
		students.add(createStudent("ROD", "ZWANE"));
		students.add(createStudent("BOBBY", "MASHILE"));
		students.add(createStudent("CHARLENE", "MOKOENA"));
		students.add(createStudent("BRUCE", "MANGWANE"));
		students.add(createStudent("JACOB", "MUDAU"));
		students.add(createStudent("LARRY", "LEBUSO"));
		students.add(createStudent("BRIANNA", "KHANYILE"));
		students.add(createStudent("ABIGAIL", "MASEKO"));
		students.add(createStudent("MOLLY", "TLAKA"));
		students.add(createStudent("TRENT", "ZUMA"));
		students.add(createStudent("BROOKE", "MDLONGWA"));
		students.add(createStudent("JOSH", "MHLABA"));
		students.add(createStudent("CRAIG", "SHABANGU"));
		students.add(createStudent("JANET", "MASILELA"));
		students.add(createStudent("TYLER", "NLEYA"));
		students.add(createStudent("CLIFFORD", "NDLOVU"));
		students.add(createStudent("MASON", "MLILO"));
		students.add(createStudent("MARILYN", "BALOZWI"));
		students.add(createStudent("JOHN", "MOROKA"));
		students.add(createStudent("JOHN", "MKHIZE"));
		students.add(createStudent("SHAUN", "MAJOLA"));
		students.add(createStudent("PEGGY", "MSIMANGA"));
		students.add(createStudent("LAKEN", "MASUKU"));
		students.add(createStudent("HEIDI", "DUBE"));
		students.add(createStudent("JASMINE", "SEBATI"));
		students.add(createStudent("CHAD", "MALAPELA"));
		students.add(createStudent("FLOYD", "PHETLA"));
		students.add(createStudent("WHITNEY", "HLATSHWAYO"));
		students.add(createStudent("JEREMIAH", "SEEFANE"));
		students.add(createStudent("ROB", "NTINI"));
		students.add(createStudent("RONDA", "TSOTETSI"));
		students.add(createStudent("KATELYN", "SELEMO"));
		students.add(createStudent("CARMEN", "MAZIBUKO"));
		students.add(createStudent("JOANN", "DLAMINI"));
		students.add(createStudent("DERRICK", "SIZIBA"));
		students.add(createStudent("COURTNEY", "BAHLE"));
		students.add(createStudent("AMBER", "THUNGO"));
		students.add(createStudent("MARY ANN", "RAMPHELE"));
		students.add(createStudent("TRACY", "KENTE"));
		students.add(createStudent("ALICE", "RATAU"));
		students.add(createStudent("BECKY", "DINAKE"));
		students.add(createStudent("RUSS", "KHUBU"));
		students.add(createStudent("DENISE", "SITHOLE"));
		students.add(createStudent("ALEXIS", "PHIRI"));
		students.add(createStudent("RANDI", "NHLAPO"));
		students.add(createStudent("VIVIAN", "THEBE"));
		students.add(createStudent("ROBERT", "NCUBE"));
		students.add(createStudent("WILLIAM", "PHALANE"));
		students.add(createStudent("CHRISTINA", "TATANE"));
		students.add(createStudent("DERRICK", "KUNENE"));
		students.add(createStudent("KIMBERLY", "SIBIYA"));
		students.add(createStudent("DEREK", "MASINGA"));
		students.add(createStudent("MARION", "SOKHULU"));
		students.add(createStudent("BILL", "KHUMALO"));
		students.add(createStudent("TARA", "NTLOKO"));
		students.add(createStudent("JEREMY", "NYATHI"));
		students.add(createStudent("JIMMY", "SENTENI"));
		students.add(createStudent("BRIAN", "MBELE"));
		students.add(createStudent("TABITHA", "CATA"));
		students.add(createStudent("MARIE", "GELA"));
		students.add(createStudent("ERICA", "KHESWA"));
		students.add(createStudent("MYRON", "BUKEKA"));
		students.add(createStudent("BRANDON", "SETHOSA"));
		students.add(createStudent("HELEN", "MABHENA"));
		students.add(createStudent("DANIELLE", "NKABINDE"));
		students.add(createStudent("GERALD", "SKHOSANA"));
		students.add(createStudent("ZACHARY", "GATSHENI"));
		students.add(createStudent("WILLIE", "HADEBE"));
		students.add(createStudent("MONICA", "MGUNI"));
		students.add(createStudent("ALLISON", "TSHABALALA"));
		students.add(createStudent("SHELLY", "BUSANG"));
		students.add(createStudent("DAKOTA", "MOYO"));
		students.add(createStudent("SHANNA", "BALOYI"));
		students.add(createStudent("VICTOR", "PITSI"));
		students.add(createStudent("DAWN", "MBEDZI"));
		students.add(createStudent("THOMAS", "MTHUNZI"));
		students.add(createStudent("EARL", "MPHELA"));
		students.add(createStudent("HEATHER", "JAMELA"));
		students.add(createStudent("TASHA", "DLODLO"));
		students.add(createStudent("VERNON", "MTHEMBU"));
		students.add(createStudent("GINGER", "THABETHE"));
		students.add(createStudent("TIMOTHY", "MABASA"));
		students.add(createStudent("ROCHELLE", "MAHLAOLA"));
		students.add(createStudent("DAWN", "MLAZI"));
		students.add(createStudent("STEVEN", "NKOSI"));
		students.add(createStudent("RODNEY", "LANGA"));
		students.add(createStudent("DERRICK", "ZIKHALI"));
		students.add(createStudent("DEBBIE", "RADEBE"));
		students.add(createStudent("DIANE", "MPOFU"));
		students.add(createStudent("SANDRA", "CHAUKE"));
		students.add(createStudent("JEFFREY", "ANDREWS"));
		students.add(createStudent("HEATHER", "LUKELE"));
		students.add(createStudent("KYLIE", "TEKANA"));
		students.add(createStudent("DARLENE", "CLAYTON"));
		students.add(createStudent("SAVANNAH", "MNISI"));
		students.add(createStudent("HOPE", "LEGEU"));
		students.add(createStudent("MANDY", "DANISA"));
		students.add(createStudent("GLEN", "NYONI"));
		students.add(createStudent("REGINA", "MNCINA"));
		createStudent("MIKE", "SEFUTHI");// don't add this to the list

	}

	public Course createCourse(String code, String title) {
		Course course = new Course();
		course.setTitle(title);
		course.setCode(code);
		course.setCreatedOn(createDate(200));
		course.setTeacher(getRandomItem(teachers));
		entityManager.persist(course);
		courses.add(course);
		return course;
	}

	private void buildCourses() {
		createCourse("CS101", "Computing for Beginners");
		createCourse("BZ101", "Business for Beginners");
		createCourse("EC101", "Economics for Beginners");
		createCourse("EX101", "Expertism for Beginners");
		createCourse("HIS101", "History for Beginners");
		createCourse("GEO101", "Geography for Beginners");
		createCourse("LIT101", "Literature for Beginners");
		createCourse("MAT101", "Math for Beginners");

		createCourse("LIT301", "Dickensian Parodies");
		createCourse("ECO324", "Keynsian Economics");
		createCourse("HIS311", "The Middle Ages");
		createCourse("GEO411", "Locating things on Earth");
		createCourse("CS393", "Optical Computing");
		createCourse("CS253", "Networking");
		createCourse("CS253", "Database Administration");
		createCourse("CS325", "3D Graphics");
		createCourse("CS250", "Computing in Business");
		createCourse("HIS243", "Early European History");
		createCourse("MAT253", "Advanced Calculus");
		createCourse("BZ245", "Principles of Profit");
		createCourse("EC382", "Managing Societal Costs");
		createCourse("BZ322", "Advertising for Profit");
		createCourse("BZ895", "Strategies for Winning");
		createCourse("CS134", "Assembly Language");
		createCourse("CS289", "Artificial Intelligence");

	}

	private <T> T getRandomItem(List<T> items) {
		return items.get(random.nextInt(items.size()));
	}

	private boolean isDataCreated() {
		return entityManager.createQuery("select c from Course c").getResultList().size() != 0;
	}

	@Produces
	@Named("dataStatus")
	public String getDataMessage() throws NotSupportedException,
			SystemException {
		createData();
		return "Data Created";
	}

	@SuppressWarnings("unchecked")
	@Produces
	@Named("demoCourseList")
	public List<Course> getCourses() {
		List<Course> courses = entityManager.createQuery(
				"select distinct c from Course c left join fetch c.teacher left join fetch c.students")
				.getResultList();
		return courses;
	}

	private void doCustomData() {
		// do nothing, users can add additional data creation here
	}
}
