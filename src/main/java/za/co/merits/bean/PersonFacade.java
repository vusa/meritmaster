package za.co.merits.bean;

import za.co.merits.model.Person;
import za.co.merits.qualifier.DataRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author vusa
 */
@Stateless
public class PersonFacade extends AbstractFacade<Person> {

    //@PersistenceContext(unitName = "SimpleEE6PU")
    @DataRepository
    @Inject
    private EntityManager em;

    public PersonFacade() {
        super(Person.class);
    }

    public Person authenticate(String email, String password) {
        try {
            return (Person) em.createNamedQuery("Person.login").setParameter("email", email).setParameter("password", password).getSingleResult();
        } catch (NoResultException nre) {
        }
        return null;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Person findByEmail(String owner) {
        try{
            return (Person) em.createNamedQuery("Person.findByEmail").setParameter("email", owner).getSingleResult();
        } catch (NoResultException nre) {

        }
        return null;
    }
}
