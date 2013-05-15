package za.co.merits.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: vusa
 * Date: 2012/11/27
 * Time: 6:49 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table
public class Assignment extends BaseEntity {

    private String title;
    private String details;
    private Date issueDate;
    private Date dueDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Course is required")
    private Course course;
    private Integer possibleMark;
    private Boolean open;

    public Assignment() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getPossibleMark() {
        return possibleMark;
    }

    public void setPossibleMark(Integer possibleMark) {
        this.possibleMark = possibleMark;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    @Override
    public String getDisplayText() {
        return null;
    }
}
