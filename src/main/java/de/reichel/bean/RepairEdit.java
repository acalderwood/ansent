/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.domain.model.Repair;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
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

    public String generateInvoice() {
        log.debug("generate invoice is called");
        repairDAO.updateRepair(this);

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        try {
            byte[] invoiceBytes = repairDAO.generateInvoice(this);
            
            log.debug("JasperPrint object created");
            File pdf = new File("C:\\temp\\rechnung.pdf");
            log.debug("Temp file created at: " + pdf.getAbsolutePath());

            OutputStream os = new FileOutputStream(pdf);
            os.write(invoiceBytes);
            os.flush();
            os.close();
            
            ServletOutputStream servletOutputStream = response.getOutputStream();
            servletOutputStream.write(invoiceBytes, 0, invoiceBytes.length);
            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (Exception e) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            response.setContentType("text/plain");
            try {
                response.getOutputStream().print(stringWriter.toString());
            } catch (IOException ioe) {
                log.error("Exception could not be displayed on screen. See above in log");
            }
        } finally {
            FacesContext.getCurrentInstance().responseComplete();
        }
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