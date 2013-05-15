/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.merits.bean.ass;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.co.merits.model.Assignment;

/**
 *
 * @author vusa
 */
@Stateless
public class AssignmentFacade extends AbstractFacade<Assignment> {
    @PersistenceContext(unitName = "SimpleEE6PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AssignmentFacade() {
        super(Assignment.class);
    }
    
}
