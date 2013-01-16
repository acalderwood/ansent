/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.dao.TeileDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Alastair Calderwood
 */
@ManagedBean(name = "teileNew")
@RequestScoped
@Controller
public class TeileNew extends TeileBean {

    @Inject
    private TeileDAO teileDAO;

    public String add() {
        teileDAO.addRepairTeile(this);
        return "index";
    }
}
