/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.dao.AnlagenDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;

/**
 *
 * @author alastair
 */
@ManagedBean(name = "anlageEdit")
@RequestScoped
@Controller
public class AnlageEdit extends AnlageBean {

    @Inject
    private AnlagenDAO anlagenDAO;

    public String update() {
        anlagenDAO.updateAnlagen(this);
        return "index";
    }
}
