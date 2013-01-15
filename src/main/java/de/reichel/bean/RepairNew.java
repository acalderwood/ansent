/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.dao.ReparaturDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Alastair Calderwood
 */
@ManagedBean(name = "repairNew")
@RequestScoped
@Controller
public class RepairNew extends RepairBean {

    @Inject
    private ReparaturDAO repairDAO;

    public String add() {
        repairDAO.addRepair(this);
        return "index";
    }
}
