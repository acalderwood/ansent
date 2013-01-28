/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.dao.EntsorgerDAO;
import de.reichel.domain.model.Betreiber;
import de.reichel.domain.model.Entsorger;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Alastair Calderwood
 */
@ManagedBean(name = "entsorgerSearch")
@ViewScoped
@Scope("view")
@Controller
public class EntsorgerSearch {
      
    private static final Log log = LogFactory.getLog(EntsorgerSearch.class);
    @Inject
    private EntsorgerDAO entsorgerDAO;
    
    public List<Entsorger> getAllEntsorger(){
        return entsorgerDAO.getAllEntsorger();
    }
      
}
