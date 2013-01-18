/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.dao;

import de.reichel.bean.RepairEdit;
import de.reichel.bean.RepairNew;
import de.reichel.domain.model.Firmen;
import de.reichel.domain.model.Repair;
import de.reichel.domain.model.Saetze;
import de.reichel.domain.model.Techniker;
import java.util.List;

/**
 *
 * @author Alastair Calderwood
 */
public interface ReparaturDAO {

    public void addRepair(RepairNew backingBean);

    public void updateRepair(RepairEdit backingBean);

    public void loadRepair(RepairEdit backingBean);

    public List<Repair> getExistingRepairs(int idAnlagen);

    public Saetze getSaetze(int idSaetze);

    public Firmen getFirmen(int idFirma);

    public List<Firmen> getAllFirmen();

    public List<Saetze> getAllSaetze();
    
    public List<Techniker> getAllTechniker();

}
