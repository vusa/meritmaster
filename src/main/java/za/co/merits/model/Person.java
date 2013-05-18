package za.co.merits.model;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 *
 * @author vusa
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT u FROM Person u"),
    @NamedQuery(name = "Person.findByEmail", query = "SELECT u FROM Person u WHERE u.email = :email"),
    @NamedQuery(name = "Person.login", query = "SELECT u FROM Person u WHERE u.email = :email AND u.password= :password"),
    @NamedQuery(name = "Person.findById", query = "SELECT u FROM Person u WHERE u.id = :id")})
@Inheritance(strategy = InheritanceType.JOINED)
public class Person extends BaseEntity {
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 30)
    private String email;
    private String firstName;
    private String lastName;
    @Size(max = 25)
    private String displayName;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Size(max = 50)
    private String password;
    private String contactNumber;

    public Person() {
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getName(){
        return firstName +" " +lastName;
    }

    public String getOrderedName(){
        return lastName+", "+firstName;
    }

    @Override
    public String toString() {
        String exp = "%s [id = %d firstName = %s ,lastName = %s]";
        return String.format(exp, super.toString(), getId(), getFirstName(),
                getLastName());
    }

        @Override
    public String getDisplayText() {
        return getOrderedName();
    }
}
