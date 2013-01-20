/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.domain.model.Teile;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Alastair Calderwood
 */
@ManagedBean(name = "teileSearch")
@RequestScoped
@Controller
public class TeileSearch extends TeileBean {
    
    public List<Teile> getSelectableTeile() {
        return teileDAO.getAllTeile();
    }
}
