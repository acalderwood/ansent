/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author alastair
 */
@ManagedBean(name = "anlageEdit")
@ViewScoped
@Scope("view")
@Controller
public class AnlageEdit extends AnlageBean {
    
    private static final Log log = LogFactory.getLog(AnlageEdit.class);

    public String update() {
        log.debug("Update Anlage called");
        anlagenDAO.updateAnlagen(this);
        return "index";
    }
    
    public void load() {
        
        log.debug("Load Anlagen is called");
        anlagenDAO.loadAnlagen(this);
//        return "index";
    }
 
    public String geschichte() {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        RepairEdit repairEdit = new RepairEdit();
        repairEdit.setIdAnlagen(this.getIdAnlagen());
        flash.put("repairEdit", repairEdit);
        return "geschichteanlagen?faces-redirect=true";
    }
    
    public String reparatur() {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        RepairEdit repairEdit = new RepairEdit();
        repairEdit.setIdAnlagen(this.getIdAnlagen());
        flash.put("repairEdit", repairEdit);
        return "geschichteanlagen?faces-redirect=true";
    }  
    
    public String auftrag() {
        log.debug("Load Repair is called");
        RepairEdit repairEdit = new RepairEdit();
        repairEdit.setIdAnlagen(this.getIdAnlagen());
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("repairEdit", repairEdit);
        return "neuereparatur?faces-redirect=true";
    }    
}
