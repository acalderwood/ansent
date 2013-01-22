/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.dao.KundenDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Alastair Calderwood
 */
@ManagedBean(name = "kundenNew")
@RequestScoped
@Controller
public class KundenNew extends KundenBean {

    private static final Log log = LogFactory.getLog(KundenNew.class);
    @Inject
    private KundenDAO kundenDAO;

    public String add() {
        kundenDAO.addKunden(this);
        return "index";
    }
    
    public String init(){
        log.debug("init() has been called");
        kundenDAO.newKundenInit(this);
        return null;
    }
}
