/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import org.springframework.stereotype.Controller;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author Alastair Calderwood
 */
@ManagedBean(name = "kundenEdit")
@ViewScoped
@Scope("view")
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
