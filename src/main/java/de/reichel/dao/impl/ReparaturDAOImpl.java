/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.dao.impl;

import de.reichel.bean.RepairBean;
import de.reichel.bean.RepairEdit;
import de.reichel.bean.TeileBean;
import de.reichel.dao.ReparaturDAO;
import de.reichel.domain.model.Anlagen;
import de.reichel.domain.model.AnlagenArt;
import de.reichel.domain.model.AnlagenHersteller;
import de.reichel.domain.model.AnlagenStandorte;
import de.reichel.domain.model.Firmen;
import de.reichel.domain.model.Kunden;
import de.reichel.domain.model.Repair;
import de.reichel.domain.model.RepairTeile;
import de.reichel.domain.model.Saetze;
import de.reichel.domain.model.Standorte;
import de.reichel.domain.model.Techniker;
import de.reichel.domain.model.Teile;
import de.reichel.report.RepairReportBean;
import de.reichel.report.InvoiceItem;
import de.reichel.util.Utils;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alastair Calderwood
 */
@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "prototype")
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
        Query query = entityManager.createQuery("from Repair repair where repair.idAnlagen = :idAnlagen and repair.reparaturDatum is not null order by repair.reparaturDatum");
        query.setHint("org.hibernate.cacheable", true);
        query.setParameter("idAnlagen", idAnlagen);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Saetze> getAllSaetze() {
        Query query = entityManager.createQuery("from Saetze saetze order by saetze.satzBezeichnung");
        query.setHint("org.hibernate.cacheable", true);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Techniker> getAllTechniker() {
        Query query = entityManager.createQuery("from Techniker techniker order by techniker.nameTechniker");
        query.setHint("org.hibernate.cacheable", true);
        List<Techniker> list = (List<Techniker>) query.getResultList();
        Techniker techniker = new Techniker();
        techniker.setNameTechniker("");
        techniker.setIdTechniker(-1);
        list.add(0, techniker);
        return list;
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
        Query query = entityManager.createQuery("from Saetze saetze where saetze.idSatz = :idSatz");
        query.setHint("org.hibernate.cacheable", true);
        query.setParameter("idSatz", idSaetze);
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
        query.setHint("org.hibernate.cacheable", true);
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
     * @param state - erstellt REPAIR.ERLEDIGT - 3 REPAIR.ABGERECHNET - 0 in
     * Bearbeitung	REPAIR.ERLEDIGT - 0	REPAIR.ABGERECHNET - 0 bearbeitet
     * REPAIR.ERLEDIGT - 1	REPAIR.ABGERECHNET - 0 - no invoice, 1 - invoice
     * printed? abgerechnet	REPAIR.ERLEDIGT - 2	REPAIR.ABGERECHNET - 2 - invoice
     * paid?
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
     * @param fixedTravelCost
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
    public void addRepair(RepairBean backingBean) {
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
        if (backingBean.getNewTechnicianName() != null && !("".equals(backingBean.getNewTechnicianName()))) {
            Techniker techniker = new Techniker();
            techniker.setNameTechniker(backingBean.getNewTechnicianName());
            entityManager.persist(techniker);
            backingBean.setIdTechnician(techniker.getIdTechniker());
        }
        if (backingBean.getIdTechnician() != null && backingBean.getIdTechnician() != -1) {
            repair.setIdTechniker(backingBean.getIdTechnician());
        }
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
            repair.setFahrzeitPauschaleBetrag(backingBean.getFixedTravelCost());
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
        try {
            Query anlagenQuery = entityManager.createQuery("from Anlagen anlagen where anlagen.idAnlagen = :idAnlagen");
            anlagenQuery.setParameter("idAnlagen", backingBean.getIdAnlagen());
            Anlagen anlagen = (Anlagen) anlagenQuery.getSingleResult();
            if (backingBean.getNextUVV() != null) {
                anlagen.setNUvv(backingBean.getNextUVV());
            }
            entityManager.merge(anlagen);

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
            if (backingBean.getIdTechnician() != -1) {
                repair.setIdTechniker(backingBean.getIdTechnician());
            }
            repair.setArbeitszeitStunden(getHours(backingBean.getTimeWorked()));
            repair.setArbeitszeitMinuten(getMins(backingBean.getTimeWorked()));
            repair.setArbzeitHelferStunden(getHours(backingBean.getHelperTimeWorked()));
            repair.setArbzeitHelferMinuten(getMins(backingBean.getHelperTimeWorked()));
            //set either travel time or travel distance but not both - should already have been handled in the view

            repair.setFahrzeitStunden(getHours(backingBean.getTravelTime()));
            repair.setFahrzeitMinuten(getMins(backingBean.getTravelTime()));
            repair.setFahrzeitHelferStunden(getHours(backingBean.getTravelTimeHelper()));
            repair.setFahrzeitHelferMinuten(getMins(backingBean.getTravelTimeHelper()));
            repair.setKilometer(backingBean.getTravelDistanceKm());
            repair.setKilometerPauschaleBetrag(backingBean.getTravelRatePerKm());

            repair.setAusloeseStunden(getHours(backingBean.getAccommodationTime()));
            repair.setAusloeseMinuten(getMins(backingBean.getAccommodationTime()));
            repair.setUeberzeitStunden(getHours(backingBean.getOvertimeTime()));
            repair.setUeberzeitMinuten(getMins(backingBean.getOvertimeTime()));
            repair.setZuschlagVmStunden(getHours(backingBean.getDirtyTime()));
            repair.setZuschlagVmMinuten(getMins(backingBean.getDirtyTime()));

            entityManager.merge(repair);

            for (TeileBean partBean : backingBean.getParts()) {

                Query removePartsQuery = entityManager.createQuery("from RepairTeile repairTeile where repairTeile.idRepairTeile = :idRepairTeile");
                removePartsQuery.setParameter("idRepairTeile", partBean.getIdRepairTeile());
                RepairTeile repairTeileToRemove = (RepairTeile) removePartsQuery.getSingleResult();
                entityManager.remove(repairTeileToRemove);

                RepairTeile repairTeile = new RepairTeile();
                repairTeile.setAnzahl(partBean.getAnzahl());
                log.debug(partBean.getAnzahl());
                repairTeile.setIdRepair(backingBean.getIdRepair());
                log.debug(backingBean.getIdRepair());
                repairTeile.setIdSub(partBean.getIdSub());
                log.debug(partBean.getIdSub());
                repairTeile.setIdTeile(partBean.getIdTeile());
                log.debug(partBean.getIdTeile());
                repairTeile.setTeileEk(partBean.getTeileEk());
                log.debug(partBean.getTeileEk());
                repairTeile.setTeileEinheit(partBean.getTeileEinheit());
                log.debug(partBean.getTeileEinheit());
                repairTeile.setTeilePreis(partBean.getTeilePreis());
                log.debug(partBean.getTeilePreis());
                repairTeile.setTeileRabatt(partBean.getTeileRabatt());
                log.debug(partBean.getTeileRabatt());
//                String partDesc = getPartDescription(partBean.getIdTeile());
//                if (partDesc != null) {
//                    repairTeile.setTeileName(partDesc);
//                } else {
                repairTeile.setTeileName(partBean.getTeileName());
//                }
                repairTeile.setTimestamp(Calendar.getInstance().getTime());

                entityManager.persist(repairTeile);
                log.debug("Part added");
            }
            log.debug("Repair updated");
        } catch (Throwable t) {
            log.error(t.getMessage());
            t.printStackTrace();
        }
    }

    @Transactional(readOnly = true)
    public byte[] generateAuftrag(RepairEdit backingBean) {

        RepairReportBean auftrag = new RepairReportBean();

        Query queryAnlagen = entityManager.createQuery("from Anlagen anlagen where anlagen.idAnlagen = :idAnlagen");
        queryAnlagen.setParameter("idAnlagen", backingBean.getIdAnlagen());
        log.debug("idAnlagen for invoice: " + backingBean.getIdAnlagen());
        Anlagen anlagen = (Anlagen) queryAnlagen.getSingleResult();
        log.debug("Got Anlagen for invoice");

        Query anlagenStandorteQuery = entityManager.createQuery("from AnlagenStandorte anlagenStandorte where anlagenStandorte.idAnlagen = :idAnlagen");
        anlagenStandorteQuery.setParameter("idAnlagen", backingBean.getIdAnlagen());

        log.debug("Anlagen " + backingBean.getIdAnlagen() + " has " + anlagenStandorteQuery.getResultList().size() + " results");

        AnlagenStandorte anlagenStandorte = null;
        try {
            anlagenStandorte = (AnlagenStandorte) anlagenStandorteQuery.getSingleResult();
        } catch (Exception e) {
            log.debug("Could not set ID Betreiber, ID Kunden or ID Standorte for Repair");
        }

        Query queryStandorte = entityManager.createQuery("from Standorte standorte where standorte.idStandorte = :idStandorte");
        queryStandorte.setParameter("idStandorte", anlagenStandorte.getIdStandorte());
        log.debug("idStandort for invoice: " + anlagenStandorte.getIdStandorte());
        Standorte standorte = (Standorte) queryStandorte.getSingleResult();
        log.debug("Got Standorte for invoice");

        Query queryKunden = entityManager.createQuery("from Kunden kunden where kunden.idKunden = :idKunden");
        queryKunden.setParameter("idKunden", anlagenStandorte.getIdKunden());
        log.debug("idKunden for invoice: " + anlagenStandorte.getIdKunden());
        Kunden kunden = (Kunden) queryKunden.getSingleResult();
        log.debug("Got Kunden for invoice");

        Query queryAnlagenArt = entityManager.createQuery("from AnlagenArt anlagenArt where anlagenArt.idAnlagenArt = :idAnlagenArt");
        queryAnlagenArt.setParameter("idAnlagenArt", anlagen.getIdAnlagenArt());
        log.debug("idAnlagenArt for invoice: " + anlagen.getIdAnlagenArt());
        AnlagenArt anlagenArt = (AnlagenArt) queryAnlagenArt.getSingleResult();
        log.debug("Got AnlagenArt for invoice");

        Query queryAnlagenHersteller = entityManager.createQuery("from AnlagenHersteller anlagenHersteller where anlagenHersteller.idAnlagenHersteller = :idAnlagenHersteller");
        queryAnlagenHersteller.setParameter("idAnlagenHersteller", anlagen.getIdAnlagenHersteller());
        log.debug("idAnlagenHersteller for invoice: " + anlagen.getIdAnlagenHersteller());
        AnlagenHersteller anlagenHersteller = (AnlagenHersteller) queryAnlagenHersteller.getSingleResult();
        log.debug("Got AnlagenHersteller for invoice");

        auftrag.setAnlagen_FABRIKATIONSNUMMER(anlagen.getFabrikationsnummer());
        auftrag.setAnlagen_INTERNE_NR(anlagen.getInterneNr());
        if (anlagen.getBaujahr() != null) {
            auftrag.setAnlagen_BAUJAHR(Utils.yearFormat.format(anlagen.getBaujahr()));
        } else {
            auftrag.setAnlagen_BAUJAHR("Baujahr unbekannt");
        }
        auftrag.setRepair_FAX_TEXT(backingBean.getWorkDescription());
        log.debug("********* " + Utils.dateFormat.format(backingBean.getRepairDate()));
        auftrag.setRechnungen_LIEFERDATUM(Utils.dateFormat.format(backingBean.getRepairDate()));

        auftrag.setRepair_ID_REPAIR(backingBean.getIdRepair() + "");

        Query queryTechniker = entityManager.createQuery("from Techniker techniker where techniker.idTechniker = :idTechniker");
        queryTechniker.setParameter("idTechniker", backingBean.getIdTechnician());
        log.debug("idTechniker for invoice: " + backingBean.getIdTechnician());

        Techniker techniker = null;
        List<Techniker> technikerList = queryTechniker.getResultList();
        if (technikerList.isEmpty()) {
            techniker = new Techniker();
            techniker.setNameTechniker("");
        } else {
            techniker = technikerList.get(0);
        }
        log.debug("Got Techniker for invoice");
        auftrag.setTechniker_NAME_TECHNIKER(techniker.getNameTechniker());

        auftrag.setStandorte_STANDORTNAME(standorte.getStandortname());
        auftrag.setStandorte_STRASSE_NR(standorte.getStrasseNr());
        auftrag.setStandorte_PLZ(standorte.getPlz());
        auftrag.setStandorte_ORT(standorte.getOrt());
        auftrag.setStandorte_ANSPRECHPARTNER(standorte.getAnsprechpartner());
        auftrag.setStandorte_TEL(standorte.getTelefon());
        auftrag.setStandorte_FAX(standorte.getFax());

        auftrag.setAnlagen_art_ART(anlagenArt.getArt());
        auftrag.setAnlagen_hersteller_HERSTELLER(anlagenHersteller.getHersteller());
        auftrag.setAnlagen_TYP(anlagen.getTyp());
        auftrag.setKunden_FIRMENNAME(kunden.getFirmenname());
        auftrag.setKunden_STRASSE_NR(kunden.getStrasseNr());
        auftrag.setKunden_PLZ(kunden.getPlz());
        auftrag.setKunden_ORT(kunden.getOrt());

        List<RepairReportBean> auftraege = new ArrayList<RepairReportBean>();
        auftraege.add(auftrag);

        // keep these for the way home (generating the file name in RepairEdit)
        backingBean.setKundenName(kunden.getFirmenname());
        backingBean.setAnlageInterneNr(anlagen.getInterneNr());

        try {
            log.debug("Generating auftrag: PLZ: " + auftraege.get(0).getStandorte_PLZ());
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("reports/auftrag.jasper");
            byte[] bytes = JasperRunManager.runReportToPdf(is, new HashMap(), new JRBeanCollectionDataSource(auftraege));
            return bytes;
        } catch (Throwable t) {
            t.printStackTrace();
            throw new RuntimeException(t.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public byte[] generateInvoice(RepairEdit backingBean) {

        RepairReportBean invoice = new RepairReportBean();

        Query queryStandorte = entityManager.createQuery("from Standorte standorte where standorte.idStandorte = :idStandorte");
        queryStandorte.setParameter("idStandorte", backingBean.getIdStandorte());
        log.debug("idStandort for invoice: " + backingBean.getIdStandorte());
        Standorte standorte = (Standorte) queryStandorte.getSingleResult();
        log.debug("Got Standorte for invoice");

        Query queryKunden = entityManager.createQuery("from Kunden kunden where kunden.idKunden = :idKunden");
        queryKunden.setParameter("idKunden", backingBean.getIdKunden());
        log.debug("idKunden for invoice: " + backingBean.getIdKunden());
        Kunden kunden = (Kunden) queryKunden.getSingleResult();
        log.debug("Got Kunden for invoice");

        Query queryTechniker = entityManager.createQuery("from Techniker techniker where techniker.idTechniker = :idTechniker");
        queryTechniker.setParameter("idTechniker", backingBean.getIdTechnician());
        log.debug("idTechniker for invoice: " + backingBean.getIdTechnician());

        Techniker techniker = null;
        List<Techniker> technikerList = queryTechniker.getResultList();
        if (technikerList.isEmpty()) {
            techniker = new Techniker();
            techniker.setNameTechniker("");
        } else {
            techniker = technikerList.get(0);
        }
        log.debug("Got Techniker for invoice");

        invoice.setTechniker_NAME_TECHNIKER(techniker.getNameTechniker().equals("") ? "" : "Techniker: " + techniker.getNameTechniker());

        Query queryAnlagen = entityManager.createQuery("from Anlagen anlagen where anlagen.idAnlagen = :idAnlagen");
        queryAnlagen.setParameter("idAnlagen", backingBean.getIdAnlagen());
        log.debug("idAnlagen for invoice: " + backingBean.getIdAnlagen());
        Anlagen anlagen = (Anlagen) queryAnlagen.getSingleResult();
        log.debug("Got Anlagen for invoice");

        Query queryAnlagenArt = entityManager.createQuery("from AnlagenArt anlagenArt where anlagenArt.idAnlagenArt = :idAnlagenArt");
        queryAnlagenArt.setParameter("idAnlagenArt", anlagen.getIdAnlagenArt());
        log.debug("idAnlagenArt for invoice: " + anlagen.getIdAnlagenArt());
        AnlagenArt anlagenArt = (AnlagenArt) queryAnlagenArt.getSingleResult();
        log.debug("Got AnlagenArt for invoice");

        Query queryAnlagenHersteller = entityManager.createQuery("from AnlagenHersteller anlagenHersteller where anlagenHersteller.idAnlagenHersteller = :idAnlagenHersteller");
        queryAnlagenHersteller.setParameter("idAnlagenHersteller", anlagen.getIdAnlagenHersteller());
        log.debug("idAnlagenHersteller for invoice: " + anlagen.getIdAnlagenHersteller());
        AnlagenHersteller anlagenHersteller = (AnlagenHersteller) queryAnlagenHersteller.getSingleResult();
        log.debug("Got AnlagenHersteller for invoice");

        Integer idSaetze = backingBean.getIdRates();
        log.debug("idSaetze for invoice: " + idSaetze);
        Saetze saetze = getSaetze(idSaetze);
        log.debug("got Saetze for invoice");

        Calendar today = Calendar.getInstance();
        Calendar due = Calendar.getInstance();
        due.add(Calendar.DATE, 21);

        invoice.setStandorte_STANDORTNAME(standorte.getStandortname());
        invoice.setStandorte_STRASSE_NR(standorte.getStrasseNr());
        invoice.setStandorte_ORT(standorte.getOrt());
        invoice.setStandorte_PLZ(standorte.getPlz());
        invoice.setRechnungen_REPAIR_ID(backingBean.getIdRepair().toString());
        invoice.setStandorte_STANDORTNAME(standorte.getStandortname());
        invoice.setStandorte_STRASSE_NR(standorte.getStrasseNr());
        invoice.setStandorte_PLZ(standorte.getPlz());
        invoice.setStandorte_ORT(standorte.getOrt());
        invoice.setAnlagen_art_ART(anlagenArt.getArt());
        invoice.setAnlagen_hersteller_HERSTELLER(anlagenHersteller.getHersteller());
        invoice.setAnlagen_INTERNE_NR(anlagen.getInterneNr());
        invoice.setAnlagen_TYP(anlagen.getTyp());
        invoice.setAnlagen_FABRIKATIONSNUMMER(anlagen.getFabrikationsnummer());
        invoice.setRepair_REPARATUR_DATUM(Utils.dateFormat.format(backingBean.getRepairDate()));
        invoice.setRechnungen_WARTUNGSZEITRAUM(backingBean.getWartungzeitraum());
        invoice.setRechnungen_BESTELLNUMMER(backingBean.getBestellNr());
        invoice.setRechnungen_RECHNUNGSNUMMERINTERN(backingBean.getRechnungsnummer());
        invoice.setRechnungen_AUFTRAGGEBER(backingBean.getAuftraggeber());
        invoice.setRechnungen_ANGEBOTSNUMMER(backingBean.getLieferantenNr());

        if (anlagen.getBaujahr() != null) {
            invoice.setAnlagen_BAUJAHR(Utils.yearFormat.format(anlagen.getBaujahr()));
        } else {
            invoice.setAnlagen_BAUJAHR("");
        }


        invoice.setRepair_FAX_TEXT(backingBean.getWorkDescription());

        invoice.setKunden_PLZ(kunden.getPlz());
        invoice.setKunden_ORT(kunden.getOrt());
        invoice.setKunden_STRASSE_NR(kunden.getStrasseNr());
        invoice.setRechnungen_RECHNUNGSDATUM(Utils.dateFormat.format(today.getTime()));
        invoice.setKunden_FIRMENNAME(kunden.getFirmenname());
        invoice.setRechnungen_ANGEBOTSNUMMER("");
        invoice.setRechnungen_RECHNUNGSNUMMERINTERN("");
        invoice.setRechnungen_BESTELLNUMMER("");
        invoice.setRechnungen_LIEFERDATUM("");
        invoice.setRechnungen_INVOICEDUE(Utils.dateFormat.format(due.getTime()));

        invoice.setLine_EXTRA_KEY_1(backingBean.getExtraKey1());
        invoice.setLine_EXTRA_KEY_2(backingBean.getExtraKey2());
        invoice.setLine_EXTRA_KEY_3(backingBean.getExtraKey3());
        invoice.setLine_EXTRA_KEY_4(backingBean.getExtraKey4());

        invoice.setLine_EXTRA_VALUE_1(backingBean.getExtraValue1());
        invoice.setLine_EXTRA_VALUE_2(backingBean.getExtraValue2());
        invoice.setLine_EXTRA_VALUE_3(backingBean.getExtraValue3());
        invoice.setLine_EXTRA_VALUE_4(backingBean.getExtraValue4());

        List<InvoiceItem> invoiceItems = new ArrayList<InvoiceItem>();

        for (TeileBean part : backingBean.getParts()) {
            double qty = part.getAnzahl();
            String me = "Stk.";
            String description = part.getTeileName();
            double unitPrice = part.getTeilePreis();
            double total = Utils.roundDP(qty * unitPrice, 2);

            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setQuantity(qty);
            invoiceItem.setMe(me);
            invoiceItem.setDescription(description);
            invoiceItem.setUnitPrice(unitPrice);
            invoiceItem.setTotalPrice(total);
            invoiceItems.add(invoiceItem);
        }

        if (backingBean.getTimeWorked().getTime() > 0) {
            double qty = backingBean.getTimeWorked().getTime() / 3600 / 1000;
            String me = "Std.";
            String description = "Arbeitszeit";
            double unitPrice = saetze.getArbeitszeit();
            double total = Utils.roundDP(qty * unitPrice, 2);

            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setQuantity(qty);
            invoiceItem.setMe(me);
            invoiceItem.setDescription(description);
            invoiceItem.setUnitPrice(unitPrice);
            invoiceItem.setTotalPrice(total);
            invoiceItems.add(invoiceItem);
        }

        if (backingBean.getHelperTimeWorked().getTime() > 0) {
            double qty = backingBean.getHelperTimeWorked().getTime() / 3600 / 1000;
            String me = "Std.";
            String description = "Arbeitszeit Helfer";
            double unitPrice = saetze.getArbzeitHelfer();
            double total = Utils.roundDP(qty * unitPrice, 2);

            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setQuantity(qty);
            invoiceItem.setMe(me);
            invoiceItem.setDescription(description);
            invoiceItem.setUnitPrice(unitPrice);
            invoiceItem.setTotalPrice(total);
            invoiceItems.add(invoiceItem);
        }

        if (backingBean.getFixedTravelCost() > 0D) {
            double qty = 1;
            String me = "X";
            String description = "Anfahrtspauschale";
            double unitPrice = backingBean.getFixedTravelCost();
            double total = Utils.roundDP(qty * unitPrice, 2);

            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setQuantity(qty);
            invoiceItem.setMe(me);
            invoiceItem.setDescription(description);
            invoiceItem.setUnitPrice(unitPrice);
            invoiceItem.setTotalPrice(total);
            invoiceItems.add(invoiceItem);
        }

        if (backingBean.getTravelTime().getTime() > 0) {
            double qty = backingBean.getTravelTime().getTime() / 3600 / 1000;
            String me = "Std.";
            String description = "Fahrzeit";
            double unitPrice = saetze.getFahrzeit();
            double total = Utils.roundDP(qty * unitPrice, 2);

            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setQuantity(qty);
            invoiceItem.setMe(me);
            invoiceItem.setDescription(description);
            invoiceItem.setUnitPrice(unitPrice);
            invoiceItem.setTotalPrice(total);
            invoiceItems.add(invoiceItem);
        }

        if (backingBean.getTravelTimeHelper().getTime() > 0) {
            double qty = backingBean.getTravelTimeHelper().getTime() / 3600 / 1000;
            String me = "Std.";
            String description = "Fahrzeit";
            double unitPrice = saetze.getFahrzeitHelfer();
            double total = Utils.roundDP(qty * unitPrice, 2);

            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setQuantity(qty);
            invoiceItem.setMe(me);
            invoiceItem.setDescription(description);
            invoiceItem.setUnitPrice(unitPrice);
            invoiceItem.setTotalPrice(total);
            invoiceItems.add(invoiceItem);
        }

        if (backingBean.getOvertimeTime().getTime() > 0) {
            double qty = backingBean.getOvertimeTime().getTime() / 3600 / 1000;
            String me = "Std.";
            String description = "Überzeit";
            double unitPrice = saetze.getUeberzeit();
            double total = Utils.roundDP(qty * unitPrice, 2);

            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setQuantity(qty);
            invoiceItem.setMe(me);
            invoiceItem.setDescription(description);
            invoiceItem.setUnitPrice(unitPrice);
            invoiceItem.setTotalPrice(total);
            invoiceItems.add(invoiceItem);
        }

        if (backingBean.getAccommodationTime().getTime() > 0) {
            double qty = backingBean.getAccommodationTime().getTime() / 3600 / 1000;
            String me = "Std.";
            String description = "Auslöse";
            double unitPrice = saetze.getAusloese();
            double total = Utils.roundDP(qty * unitPrice, 2);

            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setQuantity(qty);
            invoiceItem.setMe(me);
            invoiceItem.setDescription(description);
            invoiceItem.setUnitPrice(unitPrice);
            invoiceItem.setTotalPrice(total);
            invoiceItems.add(invoiceItem);
        }

        if (backingBean.getDirtyTime().getTime() > 0) {
            double qty = backingBean.getDirtyTime().getTime() / 3600 / 1000;
            String me = "Std.";
            String description = "Verschmutzungszuchlag";
            double unitPrice = saetze.getZuschlagVerschmutzung();
            double total = Utils.roundDP(qty * unitPrice, 2);

            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setQuantity(qty);
            invoiceItem.setMe(me);
            invoiceItem.setDescription(description);
            invoiceItem.setUnitPrice(unitPrice);
            invoiceItem.setTotalPrice(total);
            invoiceItems.add(invoiceItem);
        }

        String invoiceQty = "";
        String me = "";
        String description = "";
        String unitPrice = "";
        String total = "";
        double sumBeforeTax = 0;

        for (InvoiceItem item : invoiceItems) {
            if (item.getMe().equals("Stk.")) {
                invoiceQty += Utils.doubleNoDP(item.getQuantity()) + "\n";
            } else {
                invoiceQty += Utils.doubleToCurrency(item.getQuantity()) + "\n";
            }
            me += item.getMe() + "\n";
            description += item.getDescription() + "\n";
            unitPrice += Utils.doubleToCurrency(item.getUnitPrice()) + "\n";
            total += Utils.doubleToCurrency(item.getTotalPrice()) + "\n";
            sumBeforeTax += item.getTotalPrice();
        }

        double TAX_RATE = 0.19;
        double tax = sumBeforeTax * TAX_RATE;
        double sumAfterTax = sumBeforeTax + tax;

        String sumBeforeTaxStr = Utils.doubleToCurrency(sumBeforeTax);
        String taxStr = Utils.doubleToCurrency(tax);
        String sumAfterTaxStr = Utils.doubleToCurrency(sumAfterTax);

        invoice.setRechnungen_INVOICEQTY(invoiceQty);
        invoice.setRechnungen_ME(me);
        invoice.setRechnungen_DESCRIPTION(description);
        invoice.setRechnungen_UNITPRICE(unitPrice);
        invoice.setRechnungen_TOTAL(total);
        invoice.setRechnungen_SUM(sumBeforeTaxStr);
        invoice.setRechnungen_TAX(taxStr);
        invoice.setRechnungen_AMT(sumAfterTaxStr);

        List<RepairReportBean> invoices = new ArrayList<RepairReportBean>();
        invoices.add(invoice);

        try {

            log.debug("Generating invoice: PLZ: " + invoices.get(0).getStandorte_PLZ());
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("reports/reparatur_4.jasper");
            byte[] bytes = JasperRunManager.runReportToPdf(is, new HashMap(), new JRBeanCollectionDataSource(invoices));
            return bytes;
        } catch (Throwable t) {
            throw new RuntimeException(t.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public String getPartDescription(int idTeile) {
        Query descriptionQuery = entityManager.createQuery("from Teile teile where teile.id = :id");
        descriptionQuery.setParameter("id", idTeile);
        List<Teile> teilen = descriptionQuery.getResultList();
        if (teilen.isEmpty()) {
            return null;
        } else {
            return teilen.get(0).getBezeichnung();
        }
    }

    /**
     * Gets the machine parts used in this repair
     *
     * @param idRepair
     * @return
     */
    @Transactional(readOnly = true)
    public List<RepairTeile> getMachinePartsForRepair(int idRepair) {
        long startTime = new Date().getTime();
        Query query = entityManager.createQuery("from RepairTeile repairTeile where repairTeile.idRepair = :idRepair");
        query.setParameter("idRepair", idRepair);
        List<RepairTeile> repairTeile = query.getResultList();
        log.debug("TIME: " + (new Date().getTime() - startTime));
        return repairTeile;
    }

    /**
     * Gets the details for a particular machine part
     *
     * @param idMachinePart
     * @return
     */
    @Transactional(readOnly = true)
    public Teile getMachinePartDetails(int idMachinePart) {
        long startTime = new Date().getTime();
        Query query = entityManager.createQuery("from Teile teile where teile.idTeile = :idTeile");
        query.setParameter("idTeile", idMachinePart);
        Teile teil = (Teile) query.getSingleResult();
        log.debug("TIME: " + (new Date().getTime() - startTime));
        return teil;
    }

    @Transactional(readOnly = true)
    public void loadRepair(RepairEdit backingBean) {
        long startTime = new Date().getTime();

        Query query = entityManager.createQuery("from Repair repair where repair.idRepair = :idRepair");
        log.debug("ID_REPAIR=" + backingBean.getIdRepair());
        query.setParameter("idRepair", backingBean.getIdRepair());
        Repair repair = (Repair) query.getSingleResult();
        backingBean.setIdAnlagen(repair.getIdAnlagen());
        log.debug("ID_ANLAGEN=" + backingBean.getIdAnlagen());
        
        Query anlagenQuery = entityManager.createQuery("from Anlagen anlagen where anlagen.idAnlagen = :idAnlagen");
        anlagenQuery.setParameter("idAnlagen", backingBean.getIdAnlagen());
        Anlagen anlagen = (Anlagen) anlagenQuery.getSingleResult();
        backingBean.setNextUVV(anlagen.getNUvv());
        
        backingBean.setIdBetreiber(repair.getIdBetreiber());
        backingBean.setIdKunden(repair.getIdKunden());
        backingBean.setIdStandorte(repair.getIdStandorte());
        backingBean.setFixedTravelCost(repair.getFahrzeitPauschaleBetrag());
        backingBean.setTravelRatePerKm(0D); //always use FahrzeitPauschale
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
        backingBean.setFixedTravelCost(repair.getFahrzeitPauschaleBetrag());
        backingBean.setTravelDistanceKm(repair.getKilometer());
        backingBean.setTravelRatePerKm(repair.getKilometerPauschaleBetrag());
        backingBean.setAccommodationTime(getDate(repair.getAusloeseStunden(), repair.getAusloeseMinuten()));
        backingBean.setOvertimeTime(getDate(repair.getUeberzeitStunden(), repair.getUeberzeitMinuten()));
        backingBean.setDirtyTime(getDate(repair.getZuschlagVmStunden(), repair.getZuschlagVmMinuten()));
        backingBean.setParts(new ArrayList<TeileBean>());

        Query partsQuery = entityManager.createQuery("from RepairTeile repairTeile where repairTeile.idRepair = :idRepair");
        partsQuery.setParameter("idRepair", backingBean.getIdRepair());
        log.debug("Query to run " + partsQuery.toString());
        List<RepairTeile> repairTeile = partsQuery.getResultList();
        log.debug("No. Parts returned: " + repairTeile.size());
        for (RepairTeile repairTeil : repairTeile) {
            TeileBean teileBean = new TeileBean();
            teileBean.setTeileName(repairTeil.getTeileName());
            teileBean.setIdRepairTeile(repairTeil.getIdRepairTeile());
            teileBean.setTeileEinheit(repairTeil.getTeileEinheit());
            teileBean.setTeileEk(repairTeil.getTeileEk());
            teileBean.setTeilePreis(repairTeil.getTeilePreis());
            teileBean.setTeileRabatt(repairTeil.getTeileRabatt());
            teileBean.setAnzahl(repairTeil.getAnzahl());
            teileBean.setIdTeile(repairTeil.getIdTeile());
            log.debug("Created part");
            backingBean.getParts().add(teileBean);
            log.debug("Added part, idTeile = " + teileBean.getIdTeile());
        }
        log.debug("No. Parts added: " + backingBean.getParts().size());
        log.debug("TIME: " + (new Date().getTime() - startTime));
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

    @Transactional(readOnly = true)
    public List<Repair> getExistingRepairsByID(RepairEdit backingBean) {
        long startTime = new Date().getTime();
        log.debug("Inside Search by ID method");
        log.debug("Anlage ID for repairs " + backingBean.getIdAnlagen());

        if (backingBean.getIdAnlagen() == null) {
            log.debug("TIME: " + (new Date().getTime() - startTime));
            return new ArrayList<Repair>();
        } else {
            log.debug("Anlage ID is not null");
            Query query = entityManager.createQuery("from Repair repair where repair.idAnlagen = :idAnlagen and repair.reparaturDatum is not null order by repair.idRepair desc");
            query.setParameter("idAnlagen", backingBean.getIdAnlagen());
            List<Repair> repairs = query.getResultList();
            log.debug("TIME: " + (new Date().getTime() - startTime));
            return repairs;
        }
    }
}
