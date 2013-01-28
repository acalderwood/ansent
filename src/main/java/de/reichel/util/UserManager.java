/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.util;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Alastair Calderwood
 */
@ManagedBean(name="userManager")
@SessionScoped
public class UserManager {

    private User current;

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "logout.jsf?faces-redirect=true";
    }   
    
  
    public String getUsername() {
        return FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    }

    public boolean isCRUD() {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("crud");
    }
    
    public boolean isCRU() {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("cru");
    }
    
    public boolean isBergmann() {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("bergmann");
    }
    
    public boolean isKaufland() {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("kaufland");
    }    
}
