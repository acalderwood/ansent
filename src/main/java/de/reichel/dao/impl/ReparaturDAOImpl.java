/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.dao.impl;

import de.reichel.domain.model.AnlagenStandorte;
import de.reichel.domain.model.Betreiber;
import de.reichel.domain.model.Firmen;
import de.reichel.domain.model.Kunden;
import de.reichel.domain.model.Repair;
import de.reichel.domain.model.RepairTeile;
import de.reichel.domain.model.Saetze;
import de.reichel.domain.model.Standorte;
import de.reichel.domain.model.Teile;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Alastair Calderwood
 */
public class ReparaturDAOImpl {

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
     * Gets the location of a machine
     *
     * @param idStandorte
     * @return Standorte, or null if none exists. Note: need to check for null
     * in backing bean
     */
    public Standorte getStandorte(int idStandorte) {
        Query query = entityManager.createQuery("from Standorte standorte where standorte.idStandorte = :idStandorte");
        query.setParameter("idStandorte", idStandorte);
        if (query.getResultList().size() > 0) {
            return (Standorte) query.getResultList().get(0);
        } else {
            return null;
        }
    }

    /**
     * Gets the manager of a machine
     *
     * @param idBetreiber
     * @return Betreiber (managing company), or null if none exists. Note: need
     * to check for null in backing bean
     */
    public Betreiber getBetreiber(int idBetreiber) {
        Query query = entityManager.createQuery("from Betreiber betreiber where betreiber.idBetreiber = :idBetreiber");
        query.setParameter("idBetreiber", idBetreiber);
        if (query.getResultList().size() > 0) {
            return (Betreiber) query.getResultList().get(0);
        } else {
            return null;
        }
    }

    /**
     * Gets the customer of a machine
     *
     * @param idKunden
     * @return Kunden (customer), or null if none exists. Note: need to check
     * for null in backing bean
     */
    public Kunden getKunden(int idKunden) {
        Query query = entityManager.createQuery("from Kunden kunden where kunden.idKunden = :idKunden");
        query.setParameter("idKunden", idKunden);
        if (query.getResultList().size() > 0) {
            return (Kunden) query.getResultList().get(0);
        } else {
            return null;
        }
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
     * Saves a new repair. Once added these details cannot be changed. See addOrUpdateRepairDetails to add other details
     *
     * @param idFirma
     * @param workCarriedOut - Auszufuhrende Arbeiten - maps to REPAIR.FAX_TEXT
     * @param internalRemarks - Interne Bemerkungen
     */
    public void addRepairBasics(int idAnlagen, int idFirma) {
        Query anlagenStandorteQuery = entityManager.createQuery("from AnlagenStandorte as where as.idAnlagen = :idAnlagen");
        anlagenStandorteQuery.setParameter("idAnlagen", idAnlagen);
        AnlagenStandorte anlagenStandorte = (AnlagenStandorte) anlagenStandorteQuery.getSingleResult();

        Repair repair = new Repair();

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
        
        anlagenStandorte.getIdAnlagenStandorte();

        entityManager.persist(repair);
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
    public void addOrUpdateRepairDetails(int idRepair, short state, int idFirma, int idRates, String workDescription, String internalRemarks,  
            int hoursOperation, Date repairDate, String technician, int hoursWorked, int minsWorked, int helperHoursWorked, int helperMinsWorked, 
            double travelTimeMultiple, int travelTimeHrs, int travelTimeMins, int travelTimeHelperHrs, int travelTimeHelperMins, double travelRatePerHr, 
            double travelDistanceMultiple, int travelDistanceKm, double travelRatePerKm, int accommodationHrs, int accommodationMins, 
            int overtimeHrs, int overtimeMins, int dirtyHrs, int dirtyMins) {
        Query query = entityManager.createQuery("from Repair repair where repair.idRepair = :idRepair");
        query.setParameter("idRepair", idRepair);

        Repair repair = (Repair) query.getSingleResult();
        repair.setIdFirma(idFirma);
        repair.setIdSatz(idRates);
        repair.setErledigt(state);
        repair.setFaxText(workDescription);
        repair.setInterneBemerkung(internalRemarks);
        repair.setBetriebsstunden(hoursOperation);
        repair.setReparaturDatum(repairDate);
        repair.setArbeitszeitStunden(hoursWorked);
        repair.setArbeitszeitMinuten(minsWorked);
        repair.setArbzeitHelferStunden(helperHoursWorked);
        repair.setArbzeitHelferMinuten(helperMinsWorked);
        
        repair.setFahrzeitPauschale(travelTimeMultiple);
        repair.setFahrzeitPauschaleBetrag(travelRatePerHr);
        repair.setKilometerPauschale(travelDistanceMultiple);
        repair.setKilometerPauschaleBetrag(travelRatePerKm);

        //set either travel time or travel distance but not both - should already have been handled in the view
        if ((travelTimeHrs > 0 || travelTimeMins > 0 || travelTimeHelperHrs > 0 || travelTimeHelperMins > 0)) {

            repair.setFahrzeitStunden(travelTimeHrs);
            repair.setFahrzeitMinuten(travelTimeMins);
            repair.setFahrzeitHelferStunden(travelTimeHelperHrs);
            repair.setFahrzeitHelferMinuten(travelTimeHelperMins);
            repair.setKilometer(0);
        } else {
            repair.setFahrzeitStunden(0);
            repair.setFahrzeitMinuten(0);
            repair.setFahrzeitHelferStunden(0);
            repair.setFahrzeitHelferMinuten(0);
            repair.setKilometer(travelDistanceKm);
        }
        repair.setAusloeseStunden(accommodationHrs);
        repair.setAusloeseMinuten(accommodationMins);
        repair.setUeberzeitStunden(overtimeHrs);
        repair.setUeberzeitMinuten(overtimeMins);
        repair.setZuschlagVmStunden(dirtyHrs);
        repair.setZuschlagVmMinuten(dirtyMins);

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
}
