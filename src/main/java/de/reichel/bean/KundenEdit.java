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

/**
 *
 * @author Alastair Calderwood
 */
@ManagedBean(name = "kundenEdit")
@RequestScoped
@Controller
public class KundenEdit extends KundenBean {

    @Inject
    private KundenDAO kundenDAO;

    public String update() {
        kundenDAO.udpateKunden(this);
        return "index";
    }
}
