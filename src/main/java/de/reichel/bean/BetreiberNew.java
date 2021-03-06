/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.dao.BetreiberDAO;
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
@ManagedBean(name = "betreiberNew")
@ViewScoped
@Scope("view")
@Controller
public class BetreiberNew extends BetreiberBean {

    @Inject
    private BetreiberDAO betreiberDAO;

    public String add() {
        betreiberDAO.addBetreiber(this);
        return "index";
    }
}
