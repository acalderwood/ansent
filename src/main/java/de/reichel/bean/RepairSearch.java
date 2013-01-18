/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.domain.model.Anlagen;
import de.reichel.domain.model.Firmen;
import de.reichel.domain.model.Saetze;
import de.reichel.domain.model.Techniker;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Alastair Calderwood
 */
@ManagedBean(name = "repairSearch")
@RequestScoped
@Controller
public class RepairSearch extends RepairBean {
    
    public List<Anlagen> getSelectableAnlagen() {
        return anlagenDAO.getAllAnlagen();
    }    
    
    public List<Firmen> getSelectableFirmen() {
        return repairDAO.getAllFirmen();
    }
    
    public List<Saetze> getSelectableSaetze() {
        return repairDAO.getAllSaetze();
    }    
    
    public List<Techniker> getSelectableTechniker() {
        return repairDAO.getAllTechniker();
    }
}
