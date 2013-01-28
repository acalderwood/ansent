/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.dao.AnlagenDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Alastair Calderwood
 */
@ManagedBean(name = "anlageNew")
@ViewScoped
@Scope("view")
@Controller
public class AnlageNew extends AnlageBean {

    @Inject
    private AnlagenDAO anlagenDAO;

    public String add() {
        anlagenDAO.addAnlagen(this);
        return "index";
    }
}
