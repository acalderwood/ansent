/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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

    public String add() {
        log.debug("Adding new repair");
        repairDAO.addRepair(this);
        return "index";
    }

    public void timeKmListener(AjaxBehaviorEvent event) {
        log.debug("timeKmListener called");
    }
}
