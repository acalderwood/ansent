/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.dao.EntsorgerDAO;
import de.reichel.domain.model.Betreiber;
import java.util.List;
import javax.inject.Inject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Alastair Calderwood
 */
public class EntsorgerSearch {
      
    private static final Log log = LogFactory.getLog(EntsorgerSearch.class);
    @Inject
    private EntsorgerDAO betreiberDAO;
    
    public List<Betreiber> getAllEntsorger(){
        return betreiberDAO.getAllEntsorger();
    }
      
}
