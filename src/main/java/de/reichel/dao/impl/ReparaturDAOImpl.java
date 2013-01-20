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
import de.reichel.domain.model.Techniker;
import de.reichel.domain.model.Teile;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alastair Calderwood
 */
@Repository
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
    @Transactional(readOnly = true)
    public List<Repair> getExistingRepairs(int idAnlagen) {
        Query query = entityManager.createQuery("from Repair repair where repair.idAnlagen = :idAnlagen order by repair.reparaturDatum");
        query.setParameter("idAnlagen", idAnlagen);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Saetze> getAllSaetze() {
        Query query = entityManager.createQuery("from Saetze saetze order by saetze.satzBezeichnung");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Techniker> getAllTechniker() {
        Query query = entityManager.createQuery("from Techniker techniker order by techniker.nameTechniker");
        return query.getResultList();
    }

    /**
     * Gets the times and distance for a machine repair, used in generating the
     * invoice
     *
     * @param idSaetze
     * @return Saetze
     */
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
     * erstellt         REPAIR.ERLEDIGT - 3      REPAIR.ABGERECHNET - 0 
     * in Bearbeitung	REPAIR.ERLEDIGT - 0	REPAIR.ABGERECHNET - 0 
     * bearbeitet       REPAIR.ERLEDIGT - 1	REPAIR.ABGERECHNET - 0 - no invoice, 1 - invoice printed? 
     * abgerechnet	REPAIR.ERLEDIGT - 2	REPAIR.ABGERECHNET - 2 - invoice paid? 
     * 
     * NB only REPAIR.ERLEDIGT updated in this method. REPAIR.ABGERECHNET
     * updated when invoice printed - need to check
     *
     * @param idFirma - the 3 companies in FIRMA table
     * @param idRates - this is a key to SAETZE table used for working out rates
     * on invoice pdf
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
     * @param travelTimeMultiple - set either travel time or distance, not both
     * (or time will be preferred)
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
    @Transactional(readOnly = false)
    public void addRepair(RepairNew backingBean) {
        log.debug("Add new repair");
        Repair repair = new Repair();
        repair.setIdAnlagen(backingBean.getIdAnlagen());
        repair.setIdFirma(backingBean.getIdFirma());
        repair.setErledigt(backingBean.getState());
        repair.setIdSatz(backingBean.getIdRates());
        repair.setFaxText(backingBean.getWorkDescription());
        repair.setInterneBemerkung(backingBean.getInternalRemarks());
        repair.setBetriebsstunden(backingBean.getHoursOperation());
        repair.setReparaturDatum(backingBean.getRepairDate());
        repair.setIdTechniker(backingBean.getIdTechnician());
        repair.setArbeitszeitStunden(getHours(backingBean.getTimeWorked()));
        repair.setArbeitszeitMinuten(getMins(backingBean.getTimeWorked()));
        repair.setArbzeitHelferStunden(getHours(backingBean.getHelperTimeWorked()));
        repair.setArbzeitHelferMinuten(getMins(backingBean.getHelperTimeWorked()));
        //set either travel time or travel distance but not both - should already have been handled in the view
        if ((backingBean.getTravelTime() != null && backingBean.getTravelTime().getTime() > 0)
                || (backingBean.getTravelTimeHelper() != null && backingBean.getTravelTimeHelper().getTime() > 0)) {
            log.debug("Add time");
            repair.setFahrzeitStunden(getHours(backingBean.getTravelTime()));
            repair.setFahrzeitMinuten(getMins(backingBean.getTravelTime()));
            repair.setFahrzeitHelferStunden(getHours(backingBean.getTravelTimeHelper()));
            repair.setFahrzeitHelferMinuten(getMins(backingBean.getTravelTimeHelper()));
            repair.setFahrzeitPauschaleBetrag(backingBean.getTravelRatePerHr());
        } else {
            log.debug("Add distance");
            repair.setKilometer(backingBean.getTravelDistanceKm());
            repair.setKilometerPauschaleBetrag(backingBean.getTravelRatePerKm());
        }

        repair.setAusloeseStunden(getHours(backingBean.getAccommodationTime()));
        repair.setAusloeseMinuten(getMins(backingBean.getAccommodationTime()));
        repair.setUeberzeitStunden(getHours(backingBean.getOvertimeTime()));
        repair.setUeberzeitMinuten(getMins(backingBean.getOvertimeTime()));
        repair.setZuschlagVmStunden(getHours(backingBean.getDirtyTime()));
        repair.setZuschlagVmMinuten(getMins(backingBean.getDirtyTime()));
        
        Query anlagenStandorteQuery = entityManager.createQuery("from AnlagenStandorte anlagenStandorte where anlagenStandorte.idAnlagen = :idAnlagen");
        anlagenStandorteQuery.setParameter("idAnlagen", backingBean.getIdAnlagen());

        log.debug("Anlagen " + backingBean.getIdAnlagen() + " has " + anlagenStandorteQuery.getResultList().size() + " results");

        repair.setIdAnlagen(backingBean.getIdAnlagen());
        
        AnlagenStandorte anlagenStandorte = null;
        try {
            anlagenStandorte = (AnlagenStandorte) anlagenStandorteQuery.getSingleResult();
            repair.setIdBetreiber(anlagenStandorte.getIdBetreiber());
            repair.setIdKunden(anlagenStandorte.getIdKunden());
            repair.setIdStandorte(anlagenStandorte.getIdAnlagenStandorte());
            log.debug("Set foreign keys on repair");
        } catch (Exception e) {
            log.debug("Could not set ID Betreiber, ID Kunden or ID Standorte for Repair");
        }

        try {
            log.debug("In query");
            Query standorteQuery = entityManager.createQuery("from Standorte s where s.idStandorte = :idStandorte");
            log.debug(standorteQuery);
            standorteQuery.setParameter("idStandorte", anlagenStandorte.getIdStandorte());
            log.debug(anlagenStandorte.getIdAnlagenStandorte());
            log.debug(standorteQuery.getResultList().size());

            Standorte standorte = (Standorte) standorteQuery.getSingleResult();
            log.debug(standorte);
            repair.setFahrzeitPauschaleBetrag(standorte.getAnfahrtspauschaleBetrag());
            if (standorte.getAnfahrtspauschaleBetrag() > 0) {
                repair.setFahrzeitPauschale(1D);
            }

            log.debug("Set FahrzeitPauschaleBetrag");

            repair.setKilometerPauschaleBetrag(standorte.getKilometerpauschaleBetrag());
            if (standorte.getKilometerpauschaleBetrag() > 0) {
                repair.setKilometerPauschale(1D);
            }

            log.debug("Set KilometerpauschaleBetrag");
        } catch (Exception e) {
            log.debug("Could not set FahrzeitPauschaleBetrag/KilometerpauschaleBetrag for Repair");
        }

        log.debug("Retrieved Standorte");

        entityManager.persist(repair);
        backingBean.setIdRepair(repair.getIdRepair());
        log.debug("Added basics");


    }

    @Transactional(readOnly = false)
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
        repair.setIdTechniker(backingBean.getIdTechnician());
        repair.setArbeitszeitStunden(getHours(backingBean.getTimeWorked()));
        repair.setArbeitszeitMinuten(getMins(backingBean.getTimeWorked()));
        repair.setArbzeitHelferStunden(getHours(backingBean.getHelperTimeWorked()));
        repair.setArbzeitHelferMinuten(getMins(backingBean.getHelperTimeWorked()));
        //set either travel time or travel distance but not both - should already have been handled in the view
        if ((backingBean.getTravelTime() != null && backingBean.getTravelTime().getTime() > 0)
                || (backingBean.getTravelTimeHelper() != null && backingBean.getTravelTimeHelper().getTime() > 0)) {
            repair.setFahrzeitStunden(getHours(backingBean.getTravelTime()));
            repair.setFahrzeitMinuten(getMins(backingBean.getTravelTime()));
            repair.setFahrzeitHelferStunden(getHours(backingBean.getTravelTimeHelper()));
            repair.setFahrzeitHelferMinuten(getMins(backingBean.getTravelTimeHelper()));
        } else {
            repair.setKilometer(backingBean.getTravelDistanceKm());
            repair.setKilometerPauschaleBetrag(backingBean.getTravelRatePerKm());
        }

        repair.setAusloeseStunden(getHours(backingBean.getAccommodationTime()));
        repair.setAusloeseMinuten(getMins(backingBean.getAccommodationTime()));
        repair.setUeberzeitStunden(getHours(backingBean.getOvertimeTime()));
        repair.setUeberzeitMinuten(getMins(backingBean.getOvertimeTime()));
        repair.setZuschlagVmStunden(getHours(backingBean.getDirtyTime()));
        repair.setZuschlagVmMinuten(getMins(backingBean.getDirtyTime()));
        addDetailsToRepair(backingBean.getIdAnlagen(), repair);
        entityManager.merge(repair);
    }

    /**
     * Adds a machine part to an existing repair
     *
     * @param idRepair
     * @param idMachinePart
     * @param number
     * @param unit
     * @param price
     * @param rebatePercent
     * @param ek
     * @param idSubContractor
     */
    @Transactional(readOnly = false)
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
        Teile teile = (Teile) query.getSingleResult();
        repairTeile.setTeileName(teile.getAngebotstext());
        repairTeile.setTeilePreis(price);
        repairTeile.setTeileRabatt(price);
        repairTeile.setTimestamp(Calendar.getInstance().getTime());

        entityManager.persist(repairTeile);
    }

    /**
     * Gets the machine parts used in this repair
     *
     * @param idRepair
     * @return
     */
    @Transactional(readOnly = true)
    public List<RepairTeile> getMachinePartsForRepair(int idRepair) {
        Query query = entityManager.createQuery("from RepairTeile repairTeile where repairTeile.idRepair = :idRepair");
        query.setParameter("idRepair", idRepair);
        return query.getResultList();
    }

    /**
     * Gets the details for a particular machine part
     *
     * @param idMachinePart
     * @return
     */
    @Transactional(readOnly = true)
    public Teile getMachinePartDetails(int idMachinePart) {
        Query query = entityManager.createQuery("from Teile teile where teile.idTeile = :idTeile");
        query.setParameter("idTeile", idMachinePart);
        return (Teile) query.getSingleResult();
    }

    @Transactional(readOnly = true)
    public void loadRepair(RepairEdit backingBean) {
        Query query = entityManager.createQuery("from Repair repair where repair.idRepair = :idRepair");
        query.setParameter("idRepair", backingBean.getIdRepair());
        log.debug("Query to run " + query.toString());
        Repair repair = (Repair) query.getSingleResult();
        backingBean.setIdAnlagen(backingBean.getIdAnlagen());
        repair.setIdBetreiber(repair.getIdBetreiber());
        backingBean.setIdKunden(repair.getIdKunden());
        backingBean.setIdStandorte(repair.getIdStandorte());
        //pauschale?
        backingBean.setTravelRatePerHr(repair.getFahrzeitPauschaleBetrag());
        backingBean.setTravelRatePerKm(repair.getKilometerPauschaleBetrag());
        backingBean.setIdFirma(repair.getIdFirma());
        backingBean.setState(repair.getErledigt());
        backingBean.setIdRates(repair.getIdSatz());
        backingBean.setWorkDescription(repair.getFaxText());
        backingBean.setInternalRemarks(repair.getInterneBemerkung());
        backingBean.setHoursOperation(repair.getBetriebsstunden());
        backingBean.setRepairDate(repair.getReparaturDatum());
        backingBean.setIdTechnician(repair.getIdTechniker());
        backingBean.setTimeWorked(getDate(repair.getArbeitszeitStunden(), repair.getArbeitszeitMinuten()));
        backingBean.setHelperTimeWorked(getDate(repair.getArbzeitHelferStunden(), repair.getArbzeitHelferMinuten()));
        backingBean.setTravelTime(getDate(repair.getFahrzeitStunden(), repair.getFahrzeitMinuten()));
        backingBean.setTravelTimeHelper(getDate(repair.getFahrzeitHelferStunden(), repair.getFahrzeitHelferMinuten()));
        backingBean.setTravelRatePerHr(repair.getFahrzeitPauschaleBetrag());
        backingBean.setTravelDistanceKm(repair.getKilometer());
        backingBean.setTravelRatePerKm(repair.getKilometerPauschaleBetrag());
        backingBean.setAccommodationTime(getDate(repair.getAusloeseStunden(), repair.getAusloeseMinuten()));
        backingBean.setOvertimeTime(getDate(repair.getUeberzeitStunden(), repair.getUeberzeitMinuten()));
        backingBean.setDirtyTime(getDate(repair.getZuschlagVmStunden(), repair.getZuschlagVmMinuten()));
//        addDetailsToRepair(backingBean.getIdAnlagen(), repair);

    }

    /**
     * Adds details for a new or updated repair but does not persist.
     */
    @Transactional(readOnly = true)
    public void addDetailsToRepair(int idAnlagen, Repair repair) {

    }

    private Integer getHours(Date date) {
        if (date != null) {
            return date.getHours();
        } else {
            return 0;
        }
    }

    private Integer getMins(Date date) {
        if (date != null) {
            return date.getMinutes();
        } else {
            return 0;
        }
    }

    private Date getDate(Integer hrs, Integer mins) {
        Date date = new Date();
        if (hrs != null && mins != null) {
            date.setTime((hrs.intValue() * 3600 + mins.intValue() * 60) * 1000);
        }
        return date;
    }
}
