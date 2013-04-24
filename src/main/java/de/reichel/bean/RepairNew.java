/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.util.Utils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Alastair Calderwood
 */
@ManagedBean(name = "repairNew")
@ViewScoped
@Scope("view")
@Controller
public class RepairNew extends RepairBean {

    private static final Log log = LogFactory.getLog(RepairNew.class);
    private List<TeileNew> parts = null;
    private Integer additionalNo = null;

    public String add() {
        log.debug("Adding new repair");
        repairDAO.addRepair(this);
        return "index";
    }
    
//    public String generateAuftrag() {
//        log.debug("Adding new repair");
////        repairDAO.addRepair(this);
//        try {
//            byte[] invoiceBytes = repairDAO.generateAuftrag(this);
//            log.debug("JasperPrint object created");
//            File pdf = new File(Utils.getAuftragDirPath() + File.separator + "reparatur-auftrag-" + Utils.fileDateFormat.format(this.getRepairDate()) + "-" + this.getIdRepair() + ".pdf");
//            log.debug("Jasper file created at: " + pdf.getAbsolutePath());
//            OutputStream os = new FileOutputStream(pdf);
//            os.write(invoiceBytes);
//            os.flush();
//            os.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return "index";
//    }    

    public void addParts(ActionEvent event) {
        parts = (List<TeileNew>) event.getComponent().getAttributes().get("parts");
    }

    public void timeKmListener(AjaxBehaviorEvent event) {
        log.debug("timeKmListener called");
    }
    
    public void setAdditionalNo(Integer additionalNo) {
        this.additionalNo = additionalNo;
    }
    
    public Integer getAdditionalNo() {
        return additionalNo;
    }
    
    public void incrementAdditionalNo() {
        if (additionalNo != null) {
            additionalNo = additionalNo++;
        }
    }
}
