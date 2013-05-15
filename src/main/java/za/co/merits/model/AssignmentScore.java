package za.co.merits.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: vusa
 * Date: 2012/11/27
 * Time: 9:48 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "assignment_score")
public class AssignmentScore extends BaseEntity {
    @ManyToOne
    private Student student;
    @ManyToOne
    private Assignment assignment;
    private Date submitDate;
    private String details;
    private String teacherComment;
    private Integer score;
    private Date assessedDate;

    public AssignmentScore() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTeacherComment() {
        return teacherComment;
    }

    public void setTeacherComment(String teacherComment) {
        this.teacherComment = teacherComment;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer mark) {
        this.score = mark;
    }

    public Date getAssessedDate() {
        return assessedDate;
    }

    public void setAssessedDate(Date assessed) {
        this.assessedDate = assessed;
    }

    @Override
    public String getDisplayText() {
        return null;
    }
}
