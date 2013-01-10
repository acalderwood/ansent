/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.dao.AnlagenDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionListener;
import javax.inject.Inject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

/**
 *
 * @author alastair
 */
@ManagedBean(name = "anlageEdit")
@RequestScoped
@Controller
public class AnlageEdit extends AnlageBean {
    
    private static final Log log = LogFactory.getLog(AnlageEdit.class);

    public String update() {
        log.debug("Update Angale called");
        anlagenDAO.updateAnlagen(this);
        return "index";
    }
    
    public String load() {
        
        log.debug("Load Anlagen is called");
        anlagenDAO.loadAnlagen(this);
        return "index";
    }
}
