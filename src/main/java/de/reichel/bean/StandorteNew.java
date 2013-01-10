/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.dao.StandorteDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Alastair Calderwood
 */
@ManagedBean(name = "standorteNew")
@RequestScoped
@Controller
public class StandorteNew extends StandorteBean {

    @Inject
    private StandorteDAO standorteDAO;

    public String add() {
        standorteDAO.addStandorte(this);
        return "index";
    }
}
