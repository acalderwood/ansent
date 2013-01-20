/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Alastair Calderwood
 */
@ManagedBean(name = "repairNew")
@RequestScoped
@Controller
public class RepairNew extends RepairBean {

    private static final Log log = LogFactory.getLog(RepairNew.class);
    private List<TeileNew> parts = null;

    public String add() {
        log.debug("Adding new repair");
        repairDAO.addRepair(this);
        Integer idRepair = this.getIdRepair();
        for (TeileNew teileNew : parts) {
            teileNew.setIdRepair(idRepair);
            teileDAO.addRepairTeile(teileNew);
        }
        return "index";
    }

    public void addParts(ActionEvent event) {
        parts = (List<TeileNew>) event.getComponent().getAttributes().get("parts");
    }

    public void timeKmListener(AjaxBehaviorEvent event) {
        log.debug("timeKmListener called");
    }
}
