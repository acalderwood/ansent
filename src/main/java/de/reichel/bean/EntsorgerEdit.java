/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Alastair Calderwood
 */
@ManagedBean(name = "entsorgerEdit")
@RequestScoped
@Controller
public class EntsorgerEdit extends EntsorgerBean {

    private static final Log log = LogFactory.getLog(EntsorgerEdit.class);

    public String update() {
        entsorgerDAO.updateEntsorger(this);
        return "index";
    }
    
    public String load() {
        
        log.debug("Load Entsorger is called");
        entsorgerDAO.loadEntsorger(this);
        return "index";
    }
}
