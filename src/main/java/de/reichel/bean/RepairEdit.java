/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.domain.model.Repair;
import de.reichel.domain.model.Teile;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
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
        return "bearbeitenreparatur_ret";
    }

    public String loadRepair() {
        log.debug("Load Repair for Rechnung is called");
        repairDAO.loadRepair(this);
        log.debug("ID Repair: " + this.getIdRepair());
        return null;
    }

    public String loadRepairs() {
        log.debug("Load Repairs for Rechnung is called");
        return null;
    }

    public List<Repair> getSelectableRepairsByID() {
        return repairDAO.getExistingRepairsByID(this);
    }

    public void removePart(ActionEvent event) {
        TeileBean partToRemove = (TeileBean) event.getComponent().getAttributes().get("partToRemove");
        this.getParts().remove(partToRemove);

    }

    public void addPart(ActionEvent event) {
        TeileBean partToAdd = (TeileBean) event.getComponent().getAttributes().get("partToAdd");
        TeileBean clonePartToAdd = new TeileBean();
        clonePartToAdd.setAnzahl(partToAdd.getAnzahl());
        clonePartToAdd.setIdRepair(partToAdd.getIdRepair());
        clonePartToAdd.setIdSub(partToAdd.getIdSub());
        clonePartToAdd.setIdTeile(partToAdd.getIdTeile());
        clonePartToAdd.setTeileEk(partToAdd.getTeileEk());
        clonePartToAdd.setTeileEinheit(partToAdd.getTeileEinheit());
        clonePartToAdd.setTeilePreis(partToAdd.getTeilePreis());
        clonePartToAdd.setTeileRabatt(partToAdd.getTeileRabatt());
        clonePartToAdd.setTimestamp(Calendar.getInstance().getTime());
        log.debug("Part Id: " + partToAdd.getIdTeile());
        clonePartToAdd.setTeileName(repairDAO.getPartDescription(partToAdd.getIdTeile()));
        log.debug("Added part");
        this.getParts().add(clonePartToAdd);
        for (TeileBean teile : this.getParts()) {
            log.debug(teile.getIdTeile());
        }
    }
}