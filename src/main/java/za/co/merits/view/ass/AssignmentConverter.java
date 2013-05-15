/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.merits.view.ass;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import za.co.merits.model.Assignment;

/**
 *
 * @author vusa
 */
public class AssignmentConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Long id = new Long(string);
        AssignmentController controller = (AssignmentController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "assignment");
        return controller.getJpaController().find(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Assignment) {
            Assignment o = (Assignment) object;
            return o.getId() == null ? "" : o.getId().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: za.co.merits.model.Assignment");
        }
    }
    
}
