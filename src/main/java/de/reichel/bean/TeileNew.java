/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Alastair Calderwood
 */
@ManagedBean(name = "teileNew")
@RequestScoped
@Controller
public class TeileNew extends TeileBean {

    private static final Log log = LogFactory.getLog(TeileNew.class);    
    
    private static List<TeileNew> currentTeile = new ArrayList<TeileNew>();

    private TeileBean teileToDelete;

    public void addAction() {
        TeileNew teileNew = new TeileNew();
        teileNew.setTeileName(this.getTeileName());
        teileNew.setTeileEinheit(this.getTeileEinheit());
        teileNew.setTeileEk(this.getTeileEk());
        teileNew.setTeilePreis(this.getTeilePreis());
        teileNew.setTeileRabatt(this.getTeileRabatt());
        teileNew.setAnzahl(this.getAnzahl());

        currentTeile.add(teileNew);
    }
    
    public void removeAction(ActionEvent event) {
        teileToDelete = (TeileNew)event.getComponent().getAttributes().get("partToRemove");
        currentTeile.remove(teileToDelete);
        teileToDelete = null;

    }

    public List<TeileNew> getCurrentTeile() {
        return currentTeile;
    }
}
