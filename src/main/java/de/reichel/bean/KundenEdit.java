/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.dao.KundenDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Alastair Calderwood
 */
@ManagedBean(name = "kundenEdit")
@RequestScoped
@Controller
public class KundenEdit extends KundenBean {
    private static final Log log = LogFactory.getLog(KundenEdit.class);

    public String update() {
        log.debug("Update Kunden is called");
        kundenDAO.updateKunden(this);
        return "index";
    }
    
    public String load() {
        
        log.debug("Load Kunden is called");
        kundenDAO.loadKunden(this);
        return "index";
    }
}
