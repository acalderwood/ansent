/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Alastair Calderwood
 */
@ManagedBean(name = "betreiberEdit")
@ViewScoped
@Scope("view")
@Controller
public class BetreiberEdit extends BetreiberBean {

    private static final Log log = LogFactory.getLog(BetreiberEdit.class);

    public String update() {
        betreiberDAO.updateBetreiber(this);
        return "index";
    }
    
    public String load() {
        
        log.debug("Load Betreiber is called");
        betreiberDAO.loadBetreiber(this);
        return "index";
    }
}
