/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.dao;

import de.reichel.bean.TeileBean;
import de.reichel.bean.TeileEdit;
import de.reichel.bean.TeileNew;
import de.reichel.domain.model.RepairTeile;
import de.reichel.domain.model.Teile;
import java.util.List;

/**
 *
 * @author Alastair Calderwood
 */
public interface TeileDAO {
    
    public void updateRepairTeile(TeileEdit backingBean);

    public void addRepairTeile(TeileNew backingBean);

    public void removeRepairTeile(TeileEdit backingBean);

    public List<Teile> getAllTeile();

    public Teile getTeile(Integer idTeile);

    public List<RepairTeile> getAllRepairTeile();

    public RepairTeile getRepairTeile(Integer idRepairTeile);
    

}
