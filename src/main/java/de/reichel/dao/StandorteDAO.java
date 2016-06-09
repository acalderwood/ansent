/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.dao;

import de.reichel.bean.StandorteEdit;
import de.reichel.bean.StandorteNew;
import de.reichel.domain.model.Entsorger;
import de.reichel.domain.model.Standorte;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Alastair Calderwood
 */
public interface StandorteDAO extends Serializable {

    public void addStandorte(StandorteNew backingBean);

    public List<Standorte> getAllStandorte();

    public Standorte getStandorte(Integer kundenId);

    public void updateStandorte(StandorteEdit backingBean);

    public void loadStandorte(StandorteEdit backingBean);
    
    public List<Entsorger> getSelectableEntsorger();
    
}
