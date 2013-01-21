/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.domain.model.Repair;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Alastair Calderwood
 */
@ManagedBean(name = "repairEdit")
@RequestScoped
@Controller
public class RepairEdit extends RepairBean {
    private static final Log log = LogFactory.getLog(RepairEdit.class);

    public String update() {
        log.debug("Update Repair is called");
        repairDAO.updateRepair(this);
        return "index";
    }
    
    public String load() {
        
        log.debug("Load Repair is called");
        repairDAO.loadRepair(this);
        return "index";
    }
    
    public List<Repair> getSelectableRepairsByID() {
        return repairDAO.getExistingRepairsByID(this);
    }
}