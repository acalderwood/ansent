/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.dao;

import de.reichel.bean.EntsorgerEdit;
import de.reichel.bean.EntsorgerNew;
import de.reichel.domain.model.Betreiber;
import de.reichel.domain.model.Entsorger;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Alastair Calderwood
 */
public interface EntsorgerDAO extends Serializable  {
    
    public void updateEntsorger(EntsorgerEdit backingBean);

    public void addEntsorger(EntsorgerNew backingBean);

    public List<Entsorger> getAllEntsorger();

    public Entsorger getEntsorger(Integer idEntsorger);

    public void loadEntsorger(EntsorgerEdit backingBean);
}
