/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.merits.view.ass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;
import za.co.merits.bean.ass.AssignmentFacade;
import za.co.merits.model.Assignment;
import za.co.merits.view.ass.util.JsfUtil;
import za.co.merits.view.ass.util.PagingInfo;

/**
 *
 * @author vusa
 */
public class AssignmentController {

    public AssignmentController() {
        pagingInfo = new PagingInfo();
        converter = new AssignmentConverter();
    }
    private Assignment assignment = null;
    private List<Assignment> assignmentItems = null;
    private AssignmentFacade jpaController = null;
    private AssignmentConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "SimpleEE6PU")
    private EntityManagerFactory emf = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public AssignmentFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (AssignmentFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "assignmentJpa");
        }
        return jpaController;
    }

    public SelectItem[] getAssignmentItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getAssignmentItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Assignment getAssignment() {
        if (assignment == null) {
            assignment = (Assignment) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentAssignment", converter, null);
        }
        if (assignment == null) {
            assignment = new Assignment();
        }
        return assignment;
    }

    public String listSetup() {
        reset(true);
        return "assignment_list";
    }

    public String createSetup() {
        reset(false);
        assignment = new Assignment();
        return "assignment_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(assignment);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Assignment was successfully created.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("assignment_detail");
    }

    public String editSetup() {
        return scalarSetup("assignment_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        assignment = (Assignment) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentAssignment", converter, null);
        if (assignment == null) {
            String requestAssignmentString = JsfUtil.getRequestParameter("jsfcrud.currentAssignment");
            JsfUtil.addErrorMessage("The assignment with id " + requestAssignmentString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String assignmentString = converter.getAsString(FacesContext.getCurrentInstance(), null, assignment);
        String currentAssignmentString = JsfUtil.getRequestParameter("jsfcrud.currentAssignment");
        if (assignmentString == null || assignmentString.length() == 0 || !assignmentString.equals(currentAssignmentString)) {
            String outcome = editSetup();
            if ("assignment_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit assignment. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(assignment);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Assignment was successfully updated.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return detailSetup();
    }

    public String remove() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentAssignment");
        Long id = new Long(idAsString);
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().remove(getJpaController().find(id));
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Assignment was successfully deleted.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return relatedOrListOutcome();
    }

    private String relatedOrListOutcome() {
        String relatedControllerOutcome = relatedControllerOutcome();
        if (relatedControllerOutcome != null) {
            return relatedControllerOutcome;
        }
        return listSetup();
    }

    public List<Assignment> getAssignmentItems() {
        if (assignmentItems == null) {
            getPagingInfo();
            assignmentItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return assignmentItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "assignment_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "assignment_list";
    }

    private String relatedControllerOutcome() {
        String relatedControllerString = JsfUtil.getRequestParameter("jsfcrud.relatedController");
        String relatedControllerTypeString = JsfUtil.getRequestParameter("jsfcrud.relatedControllerType");
        if (relatedControllerString != null && relatedControllerTypeString != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Object relatedController = context.getApplication().getELResolver().getValue(context.getELContext(), null, relatedControllerString);
            try {
                Class<?> relatedControllerType = Class.forName(relatedControllerTypeString);
                Method detailSetupMethod = relatedControllerType.getMethod("detailSetup");
                return (String) detailSetupMethod.invoke(relatedController);
            } catch (ClassNotFoundException e) {
                throw new FacesException(e);
            } catch (NoSuchMethodException e) {
                throw new FacesException(e);
            } catch (IllegalAccessException e) {
                throw new FacesException(e);
            } catch (InvocationTargetException e) {
                throw new FacesException(e);
            }
        }
        return null;
    }

    private void reset(boolean resetFirstItem) {
        assignment = null;
        assignmentItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Assignment newAssignment = new Assignment();
        String newAssignmentString = converter.getAsString(FacesContext.getCurrentInstance(), null, newAssignment);
        String assignmentString = converter.getAsString(FacesContext.getCurrentInstance(), null, assignment);
        if (!newAssignmentString.equals(assignmentString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
