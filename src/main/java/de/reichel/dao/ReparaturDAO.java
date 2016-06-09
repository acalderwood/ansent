/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.dao;

import de.reichel.bean.RepairBean;
import de.reichel.bean.RepairEdit;
import de.reichel.domain.model.Firmen;
import de.reichel.domain.model.Repair;
import de.reichel.domain.model.Saetze;
import de.reichel.domain.model.Techniker;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Alastair Calderwood
 */
public interface ReparaturDAO extends Serializable {

    public void addRepair(RepairBean backingBean);

    public void updateRepair(RepairEdit backingBean);

    public void loadRepair(RepairEdit backingBean);

    public List<Repair> getExistingRepairs(int idAnlagen);

    public Saetze getSaetze(int idSaetze);

    public Firmen getFirmen(int idFirma);

    public List<Firmen> getAllFirmen();

    public List<Saetze> getAllSaetze();
    
    public List<Techniker> getAllTechniker();

    public List<Repair> getExistingRepairsByID(RepairEdit backingBean);

    public String getPartDescription(int idTeile);
    
    public byte[] generateInvoice(RepairEdit backingBean);
    
    public byte[] generateAuftrag(RepairEdit backingBean);
}
