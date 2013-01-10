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
@ManagedBean(name = "standorteEdit")
@RequestScoped
@Controller
public class StandorteEdit extends StandorteBean {

    private static final Log log = LogFactory.getLog(KundenEdit.class);

    public String update() {
        standorteDAO.updateStandorte(this);
        return "index";
    }
}
