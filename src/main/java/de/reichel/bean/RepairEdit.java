/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.domain.model.Repair;
import de.reichel.util.Utils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ActionEvent;
import org.springframework.context.annotation.Scope;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Alastair Calderwood
 */
@ManagedBean(name = "repairEdit")
@ViewScoped
@Scope("view")
@Controller
public class RepairEdit extends RepairBean {

    private static final Log log = LogFactory.getLog(RepairEdit.class);

    @PostConstruct
    public void refreshData() {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        RepairEdit repairEdit = (RepairEdit) flash.get("repairEdit");
        if (repairEdit != null) {
            log.debug("FLASH=" + repairEdit.getWorkDescription());
            this.setAccommodationTime(repairEdit.getAccommodationTime());
            this.setAuftraggeber(repairEdit.getAuftraggeber());
            this.setBestellNr(repairEdit.getBestellNr());
            this.setDirtyTime(repairEdit.getDirtyTime());
            this.setFixedTravelCost(repairEdit.getFixedTravelCost());
            this.setHelperTimeWorked(repairEdit.getHelperTimeWorked());
            this.setHoursOperation(repairEdit.getHoursOperation());
            this.setIdAnlagen(repairEdit.getIdAnlagen());
            this.setIdBetreiber(repairEdit.getIdBetreiber());
            this.setIdFirma(repairEdit.getIdFirma());
            this.setIdKunden(repairEdit.getIdKunden());
            this.setIdRates(repairEdit.getIdRates());
            this.setIdRepair(repairEdit.getIdRepair());
            this.setIdRepairTeile(repairEdit.getIdRepairTeile());
            this.setIdStandorte(repairEdit.getIdStandorte());
            this.setIdTechnician(repairEdit.getIdTechnician());
            this.setInternalRemarks(repairEdit.getInternalRemarks());
            this.setLieferantenNr(repairEdit.getLieferantenNr());
            this.setNewTechnicianName(repairEdit.getNewTechnicianName());
            this.setOvertimeTime(repairEdit.getOvertimeTime());
            this.setParts(repairEdit.getParts());
            this.setRechnungsnummer(repairEdit.getRechnungsnummer());
            this.setRepairDate(repairEdit.getRepairDate());
            this.setState(repairEdit.getState());
            this.setTimeWorked(repairEdit.getTimeWorked());
            this.setTravelDistanceKm(repairEdit.getTravelDistanceKm());
            this.setTravelRatePerKm(repairEdit.getTravelRatePerKm());
            this.setTravelTime(repairEdit.getTravelTime());
            this.setTravelTimeHelper(repairEdit.getTravelTimeHelper());
            this.setWartungzeitraum(repairEdit.getWartungzeitraum());
            this.setWorkDescription(repairEdit.getWorkDescription());
        }
    }

    public String update() {
        log.debug("Update Repair is called");
        try {
            repairDAO.updateRepair(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    public String generateInvoice() {
        log.debug("generate invoice is called");
        repairDAO.updateRepair(this);
        try {
            byte[] invoiceBytes = repairDAO.generateInvoice(this);
            log.debug("JasperPrint object created");
            File pdf = new File(Utils.getInvoiceDirPath() + File.separator + "reparatur-rechnung-" + Utils.fileDateFormat.format(this.getRepairDate()) + "-" + this.getIdRepair() + ".pdf");
            log.debug("Jasper file created at: " + pdf.getAbsolutePath());
            OutputStream os = new FileOutputStream(pdf);
            os.write(invoiceBytes);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "index";
    }
    
    public String generateAuftrag() {
        log.debug("Adding new repair");
        if (this.getIdRepair() == null) {
            repairDAO.addRepair(this);        
        } else {
            repairDAO.updateRepair(this);
        }
        try {
            byte[] invoiceBytes = repairDAO.generateAuftrag(this);
            log.debug("JasperPrint object created");
            File pdf = new File(Utils.getAuftragDirPath() + File.separator + "reparatur-auftrag-" + Utils.fileDateFormat.format(this.getRepairDate()) + "-" + this.getIdRepair() + ".pdf");
            log.debug("Jasper file created at: " + pdf.getAbsolutePath());
            OutputStream os = new FileOutputStream(pdf);
            os.write(invoiceBytes);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "index";
    }

    public String loadRepairRedirect() {
        log.debug("Load Repair is called");
        repairDAO.loadRepair(this);
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("repairEdit", this);
        return "bearbeitenreparatur_ret?faces-redirect=true";
    }

    public String loadRepairRedirectRechnung() {
        log.debug("Load Repair is called");
        repairDAO.loadRepair(this);
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("repairEdit", this);
        return "neuerechnung_geschichte?faces-redirect=true";
    }

    public String loadRepairRedirectAuftrag() {
        log.debug("Load Repair is called");
        repairDAO.loadRepair(this);
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("repairEdit", this);
        return "neuerauftrag_geschichte?faces-redirect=true";
    }

    public String loadRepair() {
        log.debug("Load Repair for Rechnung is called");
        repairDAO.loadRepair(this);
        log.debug("ID Repair: " + this.getIdRepair());
        return null;
    }

    public String loadRepairs() {
        log.debug("Load Repairs for Rechnung - method required for actionListener");
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
