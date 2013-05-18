package za.co.merits.view;

import za.co.merits.bean.PersonFacade;
import za.co.merits.model.Person;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author vusa
 */
@ManagedBean
@RequestScoped
public class PersonHome {
    @EJB
    private PersonFacade personFacade;
    private Person person;

    /**
     * Creates a new instance of UsersView
     */
    public PersonHome() {
        this.person = new Person();
    }

    public Person getPerson() {
        return person;
    }

    public void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public int getNumberOfUsers(){
        return personFacade.count();
    }
    
    public String createUser(){
        person.setFirstName(person.getDisplayName());
        person.setLastName(person.getDisplayName());
        personFacade.create(person);
        return "person-created";
    }
    
    public String login(){
        Person authPerson = personFacade.authenticate(person.getEmail(), person.getPassword());
        if(authPerson == null){
            return  "login-failed";
        }
        person = authPerson;
        return "login-success";
    }
}
