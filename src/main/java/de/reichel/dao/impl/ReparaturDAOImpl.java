/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.dao.impl;

import de.reichel.bean.RepairEdit;
import de.reichel.bean.RepairNew;
import de.reichel.dao.ReparaturDAO;
import de.reichel.domain.model.AnlagenStandorte;
import de.reichel.domain.model.Firmen;
import de.reichel.domain.model.Repair;
import de.reichel.domain.model.RepairTeile;
import de.reichel.domain.model.Saetze;
import de.reichel.domain.model.Standorte;
import de.reichel.domain.model.Teile;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Alastair Calderwood
 */
public class ReparaturDAOImpl implements ReparaturDAO {

    private static final Log log = LogFactory.getLog(ReparaturDAOImpl.class);
    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    /**
     * Gets a list of all repairs
     *
     * @param idAnlagen
     * @return List of Repairs where - Rep. ID = idRepair (ID_REPAIR) -
     * Auszuführende Arbeiten = faxText (FAX_TEXT) - - r
     */
    public List<Repair> getExistingRepairs(int idAnlagen) {
        Query query = entityManager.createQuery("from Repair repair where repair.idAnlagen = :idAnlagen order by repair.reparaturDatum");
        query.setParameter("idAnlagen", idAnlagen);
        return query.getResultList();
    }

    /**
     * Gets the times and distance for a machine repair, used in generating the invoice
     *
     * @param idSaetze
     * @return Saetze
     */
    public Saetze getSaetze(int idSaetze) {
        Query query = entityManager.createQuery("from Saetze saetze where saetze.idSaetze = :idSaetze");
        query.setParameter("idSaetze", idSaetze);
        return (Saetze) query.getSingleResult();
    }

    /**
     * Gets the owner of a machine
     *
     * @param idFirma
     * @return Firmen, or null if none exists. Note: need to check for null in
     * backing bean
     */
    public Firmen getFirmen(int idFirma) {
        Query query = entityManager.createQuery("from Firmen firmen where firmen.idFirma = :idFirma");
        query.setParameter("idFirma", idFirma);
        return (Firmen) query.getSingleResult();
    }

    /**
     * Gets all the owners of machines (only 3)
     *
     * @return List of Firmen
     */
    public List<Firmen> getAllFirmen() {
        Query query = entityManager.createQuery("from Firmen firmen order by firmen.firmenname");
        return query.getResultList();
    }

    /**
     * Adds or updates details for an existing repair. This would probably be
     * done along with saveNewRepair
     *
     * @param idRepair
     * @param state - 
     * erstellt         REPAIR.ERLEDIGT - 3	REPAIR.ABGERECHNET - 0
     * in Bearbeitung	REPAIR.ERLEDIGT - 0	REPAIR.ABGERECHNET - 0 
     * bearbeitet       REPAIR.ERLEDIGT - 1	REPAIR.ABGERECHNET - 0 - no invoice, 1 - invoice printed?
     * abgerechnet	REPAIR.ERLEDIGT - 2	REPAIR.ABGERECHNET - 2 - invoice paid?
     * NB only REPAIR.ERLEDIGT updated in this method. REPAIR.ABGERECHNET updated when invoice printed - need to check
     *
     * @param idFirma - the 3 companies in FIRMA table
     * @param idRates - this is a key to SAETZE table used for working out rates on invoice pdf
     * @param workDescription
     * @param internalRemarks
     * @param idRepair
     * @param hoursOperation
     * @param repairDate
     * @param technician
     * @param hoursWorked
     * @param minsWorked
     * @param helperHoursWorked
     * @param helperMinsWorked
     * @param travelTimeMultiple - set either travel time or distance, not both (or time will be preferred)
     * @param travelTimeHrs
     * @param travelTimeMins
     * @param travelTimeHelperHrs
     * @param travelTimeHelperMins
     * @param travelRatePerHr
     * @param travelDistanceMultiple
     * @param travelDistanceKm
     * @param travelRatePerKm
     * @param accommodationHrs
     * @param accommodationMins
     * @param overtimeHrs
     * @param overtimeMins
     * @param dirtyHrs
     * @param dirtyMins
     */
    
    public void addRepair(RepairNew backingBean) {
        Repair repair = new Repair();
        repair.setIdAnlagen(backingBean.getIdAnlagen());
        repair.setIdFirma(backingBean.getIdFirma());
        repair.setErledigt(backingBean.getState());
        repair.setIdSatz(backingBean.getIdRates());
        repair.setFaxText(backingBean.getWorkDescription());
        repair.setInterneBemerkung(backingBean.getInternalRemarks());
        repair.setBetriebsstunden(backingBean.getHoursOperation());
        repair.setReparaturDatum(backingBean.getRepairDate());
        repair.setIdTechniker(backingBean.getTechnician());
        repair.setArbeitszeitStunden(backingBean.getHoursWorked());
        repair.setArbeitszeitMinuten(backingBean.getMinsWorked());
        repair.setArbzeitHelferStunden(backingBean.getHelperHoursWorked());
        repair.setArbzeitHelferMinuten(backingBean.getHelperMinsWorked());
        repair.setFahrzeitStunden(backingBean.getTravelTimeHrs());
        //set either travel time or travel distance but not both - should already have been handled in the view
        if ((backingBean.getTravelTimeHrs() > 0 || backingBean.getTravelTimeMins() > 0 
                || backingBean.getTravelTimeHelperHrs() > 0 || backingBean.getTravelTimeHelperMins() > 0)) {

            repair.setFahrzeitMinuten(backingBean.getTravelTimeMins());
            repair.setFahrzeitHelferStunden(backingBean.getTravelTimeHelperHrs());
            repair.setFahrzeitHelferMinuten(backingBean.getTravelTimeHelperMins());
            repair.setFahrzeitPauschaleBetrag(backingBean.getTravelRatePerHr());
        } else {
            repair.setKilometer(backingBean.getTravelDistanceKm());
            repair.setKilometerPauschaleBetrag(backingBean.getTravelRatePerKm()); 
        }
        
        repair.setAusloeseStunden(backingBean.getAccommodationHrs());
        repair.setAusloeseMinuten(backingBean.getAccommodationMins());
        repair.setUeberzeitStunden(backingBean.getOvertimeHrs());
        repair.setUeberzeitMinuten(backingBean.getOvertimeMins());
        repair.setZuschlagVmStunden(backingBean.getDirtyHrs());
        repair.setZuschlagVmMinuten(backingBean.getDirtyMins());
        addDetailsToRepair(backingBean.getIdAnlagen(), repair);
        entityManager.persist(repair);
    }    
    
    public void updateRepair(RepairEdit backingBean) {
        Query query = entityManager.createQuery("from Repair repair where repair.idRepair = :idRepair");
        query.setParameter("idRepair", backingBean.getIdRepair());

        Repair repair = (Repair) query.getSingleResult();
        repair.setIdAnlagen(backingBean.getIdAnlagen());
        repair.setIdFirma(backingBean.getIdFirma());
        repair.setErledigt(backingBean.getState());
        repair.setIdSatz(backingBean.getIdRates());
        repair.setFaxText(backingBean.getWorkDescription());
        repair.setInterneBemerkung(backingBean.getInternalRemarks());
        repair.setBetriebsstunden(backingBean.getHoursOperation());
        repair.setReparaturDatum(backingBean.getRepairDate());
        repair.setIdTechniker(backingBean.getTechnician());
        repair.setArbeitszeitStunden(backingBean.getHoursWorked());
        repair.setArbeitszeitMinuten(backingBean.getMinsWorked());
        repair.setArbzeitHelferStunden(backingBean.getHelperHoursWorked());
        repair.setArbzeitHelferMinuten(backingBean.getHelperMinsWorked());
        repair.setFahrzeitStunden(backingBean.getTravelTimeHrs());
        //set either travel time or travel distance but not both - should already have been handled in the view
        if ((backingBean.getTravelTimeHrs() > 0 || backingBean.getTravelTimeMins() > 0 
                || backingBean.getTravelTimeHelperHrs() > 0 || backingBean.getTravelTimeHelperMins() > 0)) {

            repair.setFahrzeitMinuten(backingBean.getTravelTimeMins());
            repair.setFahrzeitHelferStunden(backingBean.getTravelTimeHelperHrs());
            repair.setFahrzeitHelferMinuten(backingBean.getTravelTimeHelperMins());
            repair.setFahrzeitPauschaleBetrag(backingBean.getTravelRatePerHr());
        } else {
            repair.setKilometer(backingBean.getTravelDistanceKm());
            repair.setKilometerPauschaleBetrag(backingBean.getTravelRatePerKm()); 
        }
        
        repair.setAusloeseStunden(backingBean.getAccommodationHrs());
        repair.setAusloeseMinuten(backingBean.getAccommodationMins());
        repair.setUeberzeitStunden(backingBean.getOvertimeHrs());
        repair.setUeberzeitMinuten(backingBean.getOvertimeMins());
        repair.setZuschlagVmStunden(backingBean.getDirtyHrs());
        repair.setZuschlagVmMinuten(backingBean.getDirtyMins());
        addDetailsToRepair(backingBean.getIdAnlagen(), repair);
        entityManager.merge(repair);
    }
    
    /**
     * Adds a machine part to an existing repair
     * @param idRepair
     * @param idMachinePart
     * @param number
     * @param unit
     * @param price
     * @param rebatePercent
     * @param ek
     * @param idSubContractor 
     */
    public void addMachinePartToRepair(int idRepair, int idMachinePart, int number, String unit, double price, double rebatePercent, double ek, int idSubContractor) {
        RepairTeile repairTeile = new RepairTeile();
        repairTeile.setAnzahl(price);
        repairTeile.setIdRepair(idRepair);
        repairTeile.setIdSub(idSubContractor);
        repairTeile.setIdTeile(idMachinePart);
        repairTeile.setTeileEk(ek);
        repairTeile.setTeileEinheit(unit);

        Query query = entityManager.createQuery("from Teile teile where teile.idTeile = :idTeile");
        query.setParameter("idTeile", idMachinePart);
        Teile teile = (Teile)query.getSingleResult();
        repairTeile.setTeileName(teile.getAngebotstext());
        repairTeile.setTeilePreis(price);
        repairTeile.setTeileRabatt(price);
        repairTeile.setTimestamp(Calendar.getInstance().getTime());
        
        entityManager.persist(repairTeile);
    }
    
    /**
     * Gets the machine parts used in this repair
     * @param idRepair
     * @return 
     */
    public List<RepairTeile> getMachinePartsForRepair(int idRepair) {
        Query query = entityManager.createQuery("from RepairTeile repairTeile where repairTeile.idRepair = :idRepair");
        query.setParameter("idRepair", idRepair);
        return query.getResultList();
    }
    
    /**
     * Gets the details for a particular machine part
     * @param idMachinePart
     * @return 
     */
    public Teile getMachinePartDetails(int idMachinePart) {
        Query query = entityManager.createQuery("from Teile teile where teile.idTeile = :idTeile");
        query.setParameter("idTeile", idMachinePart);
        return (Teile)query.getSingleResult();       
    }
    
    public void loadRepair(RepairEdit backingBean) {
        Query query = entityManager.createQuery("from Repair repair where repair.idRepair = :idRepair");
        query.setParameter("idRepair", backingBean.getIdRepair());
        log.debug("Query to run " + query.toString());
        Repair repair = (Repair) query.getSingleResult();
        backingBean.setIdAnlagen(backingBean.getIdAnlagen());
        backingBean.setIdFirma(backingBean.getIdFirma());
        backingBean.setState(repair.getErledigt());
        backingBean.setIdRates(repair.getIdSatz());
        backingBean.setWorkDescription(repair.getFaxText());
        backingBean.setInternalRemarks(repair.getInterneBemerkung());
        backingBean.setHoursOperation(repair.getBetriebsstunden());
        backingBean.setRepairDate(repair.getReparaturDatum());
        backingBean.setTechnician(repair.getIdTechniker());
        backingBean.setHoursWorked(repair.getArbeitszeitStunden());
        backingBean.setMinsWorked(repair.getArbeitszeitMinuten());
        backingBean.setHelperHoursWorked(repair.getArbzeitHelferStunden());
        backingBean.setHelperMinsWorked(repair.getArbzeitHelferMinuten());
        backingBean.setTravelTimeHrs(repair.getFahrzeitStunden());
        backingBean.setTravelTimeMins(repair.getFahrzeitMinuten());
        backingBean.setTravelTimeHelperHrs(repair.getFahrzeitHelferStunden());
        backingBean.setTravelTimeHelperMins(repair.getFahrzeitHelferMinuten());
        backingBean.setTravelRatePerHr(repair.getFahrzeitPauschaleBetrag());
        backingBean.setTravelDistanceKm(repair.getKilometer());
        backingBean.setTravelRatePerKm(repair.getKilometerPauschaleBetrag()); 
        backingBean.setAccommodationHrs(repair.getAusloeseStunden());
        backingBean.setAccommodationMins(repair.getAusloeseMinuten());
        backingBean.setOvertimeHrs(repair.getUeberzeitStunden());
        backingBean.setOvertimeMins(repair.getUeberzeitMinuten());
        backingBean.setDirtyHrs(repair.getZuschlagVmStunden());
        backingBean.setDirtyMins(repair.getZuschlagVmMinuten());
        addDetailsToRepair(backingBean.getIdAnlagen(), repair);
        
    }
    
    /**
     * Adds details for a new or updated repair but does not persist.
     */
    private void addDetailsToRepair(int idAnlagen, Repair repair) {
        Query anlagenStandorteQuery = entityManager.createQuery("from AnlagenStandorte as where as.idAnlagen = :idAnlagen");
        anlagenStandorteQuery.setParameter("idAnlagen", idAnlagen);
        AnlagenStandorte anlagenStandorte = (AnlagenStandorte) anlagenStandorteQuery.getSingleResult();

        repair.setIdAnlagen(idAnlagen);
        repair.setIdBetreiber(anlagenStandorte.getIdBetreiber());
        repair.setIdKunden(anlagenStandorte.getIdKunden());
        repair.setIdStandorte(anlagenStandorte.getIdAnlagenStandorte());
        
        Query standorteQuery = entityManager.createQuery("from Standorte s where s.idStandorte = :idStandorte");
        standorteQuery.setParameter("idStandorte", anlagenStandorte.getIdAnlagenStandorte());
        Standorte standorte = (Standorte)standorteQuery.getSingleResult();
        
        repair.setFahrzeitPauschaleBetrag(standorte.getAnfahrtspauschaleBetrag());
        if (standorte.getAnfahrtspauschaleBetrag() > 0) {
            repair.setFahrzeitPauschale(1D);
        }
        
        repair.setKilometerPauschaleBetrag(standorte.getKilometerpauschaleBetrag());
        if (standorte.getKilometerpauschaleBetrag() > 0) {
            repair.setKilometerPauschale(1D);
        }
    }

}
