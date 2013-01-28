/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.report;

import de.reichel.dao.impl.StandorteDAOImpl;
import java.io.Serializable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Alastair Calderwood
 */
public class RepairReportBean implements Serializable {

    private Integer repair_ID_REPAIR = null;
    private String user_VORNAME = null;
    private String user_NACHNAME = null;
    private String repair_ERSTELLT = null;
    private String repair_GESENDET_DATUM = null;
    private String anlagen_INTERNE_NR = null;
    private String standorte_ID_STANDORTE = null;
    private String standorte_STANDORTNAME = null;
    private String standorte_STRASSE_NR = null;
    private String standorte_PLZ = null;
    private String standorte_ORT = null;
    private String anlagen_art_ART = null;
    private String anlagen_hersteller_HERSTELLER = null;
    private String anlagen_TYP = null;
    private String anlagen_FABRIKATIONSNUMMER = null;
    private String anlagen_BAUJAHR = null;
    private String betreiber_ID_BETREIBER = null;
    private String betreiber_BETREIBERNAME = null;
    private String betreiber_STRASSE_NR = null;
    private String betreiber_PLZ = null;
    private String betreiber_ORT = null;
    private String kunden_ID_KUNDEN = null;
    private String kunden_FIRMENNAME = null;
    private String kunden_STRASSE_NR = null;
    private String kunden_PLZ = null;
    private String kunden_ORT = null;
    private String repair_FAX_TEXT = null;
    private String repair_INTERNE_BEMERKUNG = null;
    private String repair_REPARATUR_DATUM = null;
    private String techniker_FIRMA_TECHNIKER = null;
    private String techniker_NAME_TECHNIKER = null;
    private String rechnungen_RECHNUNGSNUMMERINTERN = null;
    private String rechnungen_RECHNUNGSDATUM = null;
    private String rechnungen_LIEFERDATUM = null;
    private String rechnungen_REPAIR_ID = null;
    private String rechnungen_ANGEBOTSNUMMER = null;
    private String rechnungen_BESTELLNUMMER = null;
    private String rechnungen_INVOICEDUE = null;
    private String rechnungen_INVOICEQTY = null;
    private String rechnungen_ME = null;
    private String rechnungen_DESCRIPTION = null;
    private String rechnungen_UNITPRICE = null;
    private String rechnungen_TOTAL = null;
    private String rechnungen_AMT = null;
    private String rechnungen_TAX = null;
    private String rechnungen_SUM = null;
    
    private static final Log log = LogFactory.getLog(RepairReportBean.class);
    /**
     * @return the repair_ID_REPAIR
     */
    public Integer getRepair_ID_REPAIR() {
        return 10;
    }

    /**
     * @param repair_ID_REPAIR the repair_ID_REPAIR to set
     */
    public void setRepair_ID_REPAIR(Integer repair_ID_REPAIR) {
        this.repair_ID_REPAIR = repair_ID_REPAIR;
    }

    /**
     * @return the user_VORNAME
     */
    public String getUser_VORNAME() {
        return user_VORNAME;
    }

    /**
     * @param user_VORNAME the user_VORNAME to set
     */
    public void setUser_VORNAME(String user_VORNAME) {
        this.user_VORNAME = user_VORNAME;
    }

    /**
     * @return the user_NACHNAME
     */
    public String getUser_NACHNAME() {
        return user_NACHNAME;
    }

    /**
     * @param user_NACHNAME the user_NACHNAME to set
     */
    public void setUser_NACHNAME(String user_NACHNAME) {
        this.user_NACHNAME = user_NACHNAME;
    }

    /**
     * @return the repair_ERSTELLT
     */
    public String getRepair_ERSTELLT() {
        return repair_ERSTELLT;
    }

    /**
     * @param repair_ERSTELLT the repair_ERSTELLT to set
     */
    public void setRepair_ERSTELLT(String repair_ERSTELLT) {
        this.repair_ERSTELLT = repair_ERSTELLT;
    }

    /**
     * @return the repair_GESENDET_DATUM
     */
    public String getRepair_GESENDET_DATUM() {
        return repair_GESENDET_DATUM;
    }

    /**
     * @param repair_GESENDET_DATUM the repair_GESENDET_DATUM to set
     */
    public void setRepair_GESENDET_DATUM(String repair_GESENDET_DATUM) {
        this.repair_GESENDET_DATUM = repair_GESENDET_DATUM;
    }

    /**
     * @return the anlagen_INTERNE_NR
     */
    public String getAnlagen_INTERNE_NR() {
        return anlagen_INTERNE_NR;
    }

    /**
     * @param anlagen_INTERNE_NR the anlagen_INTERNE_NR to set
     */
    public void setAnlagen_INTERNE_NR(String anlagen_INTERNE_NR) {
        this.anlagen_INTERNE_NR = anlagen_INTERNE_NR;
    }

    /**
     * @return the standorte_ID_STANDORTE
     */
    public String getStandorte_ID_STANDORTE() {
        return standorte_ID_STANDORTE;
    }

    /**
     * @param standorte_ID_STANDORTE the standorte_ID_STANDORTE to set
     */
    public void setStandorte_ID_STANDORTE(String standorte_ID_STANDORTE) {
        this.standorte_ID_STANDORTE = standorte_ID_STANDORTE;
    }

    /**
     * @return the standorte_STANDORTNAME
     */
    public String getStandorte_STANDORTNAME() {
        log.debug("getStandorte_STANDORTNAME():" + standorte_STANDORTNAME);
        return standorte_STANDORTNAME;
    }

    /**
     * @param standorte_STANDORTNAME the standorte_STANDORTNAME to set
     */
    public void setStandorte_STANDORTNAME(String standorte_STANDORTNAME) {
        this.standorte_STANDORTNAME = standorte_STANDORTNAME;
    }

    /**
     * @return the standorte_STRASSE_NR
     */
    public String getStandorte_STRASSE_NR() {
        return standorte_STRASSE_NR;
    }

    /**
     * @param standorte_STRASSE_NR the standorte_STRASSE_NR to set
     */
    public void setStandorte_STRASSE_NR(String standorte_STRASSE_NR) {
        this.standorte_STRASSE_NR = standorte_STRASSE_NR;
    }

    /**
     * @return the standorte_PLZ
     */
    public String getStandorte_PLZ() {
        return standorte_PLZ;
    }

    /**
     * @param standorte_PLZ the standorte_PLZ to set
     */
    public void setStandorte_PLZ(String standorte_PLZ) {
        this.standorte_PLZ = standorte_PLZ;
    }

    /**
     * @return the standorte_ORT
     */
    public String getStandorte_ORT() {
        return standorte_ORT;
    }

    /**
     * @param standorte_ORT the standorte_ORT to set
     */
    public void setStandorte_ORT(String standorte_ORT) {
        this.standorte_ORT = standorte_ORT;
    }

    /**
     * @return the anlagen_art_ART
     */
    public String getAnlagen_art_ART() {
        return anlagen_art_ART;
    }

    /**
     * @param anlagen_art_ART the anlagen_art_ART to set
     */
    public void setAnlagen_art_ART(String anlagen_art_ART) {
        this.anlagen_art_ART = anlagen_art_ART;
    }

    /**
     * @return the anlagen_hersteller_HERSTELLER
     */
    public String getAnlagen_hersteller_HERSTELLER() {
        return anlagen_hersteller_HERSTELLER;
    }

    /**
     * @param anlagen_hersteller_HERSTELLER the anlagen_hersteller_HERSTELLER to set
     */
    public void setAnlagen_hersteller_HERSTELLER(String anlagen_hersteller_HERSTELLER) {
        this.anlagen_hersteller_HERSTELLER = anlagen_hersteller_HERSTELLER;
    }

    /**
     * @return the anlagen_TYP
     */
    public String getAnlagen_TYP() {
        return anlagen_TYP;
    }

    /**
     * @param anlagen_TYP the anlagen_TYP to set
     */
    public void setAnlagen_TYP(String anlagen_TYP) {
        this.anlagen_TYP = anlagen_TYP;
    }

    /**
     * @return the anlagen_FABRIKATIONSNUMMER
     */
    public String getAnlagen_FABRIKATIONSNUMMER() {
        return anlagen_FABRIKATIONSNUMMER;
    }

    /**
     * @param anlagen_FABRIKATIONSNUMMER the anlagen_FABRIKATIONSNUMMER to set
     */
    public void setAnlagen_FABRIKATIONSNUMMER(String anlagen_FABRIKATIONSNUMMER) {
        this.anlagen_FABRIKATIONSNUMMER = anlagen_FABRIKATIONSNUMMER;
    }

    /**
     * @return the anlagen_BAUJAHR
     */
    public String getAnlagen_BAUJAHR() {
        return anlagen_BAUJAHR;
    }

    /**
     * @param anlagen_BAUJAHR the anlagen_BAUJAHR to set
     */
    public void setAnlagen_BAUJAHR(String anlagen_BAUJAHR) {
        this.anlagen_BAUJAHR = anlagen_BAUJAHR;
    }

    /**
     * @return the betreiber_ID_BETREIBER
     */
    public String getBetreiber_ID_BETREIBER() {
        return betreiber_ID_BETREIBER;
    }

    /**
     * @param betreiber_ID_BETREIBER the betreiber_ID_BETREIBER to set
     */
    public void setBetreiber_ID_BETREIBER(String betreiber_ID_BETREIBER) {
        this.betreiber_ID_BETREIBER = betreiber_ID_BETREIBER;
    }

    /**
     * @return the betreiber_BETREIBERNAME
     */
    public String getBetreiber_BETREIBERNAME() {
        return betreiber_BETREIBERNAME;
    }

    /**
     * @param betreiber_BETREIBERNAME the betreiber_BETREIBERNAME to set
     */
    public void setBetreiber_BETREIBERNAME(String betreiber_BETREIBERNAME) {
        this.betreiber_BETREIBERNAME = betreiber_BETREIBERNAME;
    }

    /**
     * @return the betreiber_STRASSE_NR
     */
    public String getBetreiber_STRASSE_NR() {
        return betreiber_STRASSE_NR;
    }

    /**
     * @param betreiber_STRASSE_NR the betreiber_STRASSE_NR to set
     */
    public void setBetreiber_STRASSE_NR(String betreiber_STRASSE_NR) {
        this.betreiber_STRASSE_NR = betreiber_STRASSE_NR;
    }

    /**
     * @return the betreiber_PLZ
     */
    public String getBetreiber_PLZ() {
        return betreiber_PLZ;
    }

    /**
     * @param betreiber_PLZ the betreiber_PLZ to set
     */
    public void setBetreiber_PLZ(String betreiber_PLZ) {
        this.betreiber_PLZ = betreiber_PLZ;
    }

    /**
     * @return the betreiber_ORT
     */
    public String getBetreiber_ORT() {
        return betreiber_ORT;
    }

    /**
     * @param betreiber_ORT the betreiber_ORT to set
     */
    public void setBetreiber_ORT(String betreiber_ORT) {
        this.betreiber_ORT = betreiber_ORT;
    }

    /**
     * @return the kunden_ID_KUNDEN
     */
    public String getKunden_ID_KUNDEN() {
        return kunden_ID_KUNDEN;
    }

    /**
     * @param kunden_ID_KUNDEN the kunden_ID_KUNDEN to set
     */
    public void setKunden_ID_KUNDEN(String kunden_ID_KUNDEN) {
        this.kunden_ID_KUNDEN = kunden_ID_KUNDEN;
    }

    /**
     * @return the kunden_FIRMENNAME
     */
    public String getKunden_FIRMENNAME() {
        return kunden_FIRMENNAME;
    }

    /**
     * @param kunden_FIRMENNAME the kunden_FIRMENNAME to set
     */
    public void setKunden_FIRMENNAME(String kunden_FIRMENNAME) {
        this.kunden_FIRMENNAME = kunden_FIRMENNAME;
    }

    /**
     * @return the kunden_STRASSE_NR
     */
    public String getKunden_STRASSE_NR() {
        return kunden_STRASSE_NR;
    }

    /**
     * @param kunden_STRASSE_NR the kunden_STRASSE_NR to set
     */
    public void setKunden_STRASSE_NR(String kunden_STRASSE_NR) {
        this.kunden_STRASSE_NR = kunden_STRASSE_NR;
    }

    /**
     * @return the kunden_PLZ
     */
    public String getKunden_PLZ() {
        return kunden_PLZ;
    }

    /**
     * @param kunden_PLZ the kunden_PLZ to set
     */
    public void setKunden_PLZ(String kunden_PLZ) {
        this.kunden_PLZ = kunden_PLZ;
    }

    /**
     * @return the kunden_ORT
     */
    public String getKunden_ORT() {
        return kunden_ORT;
    }

    /**
     * @param kunden_ORT the kunden_ORT to set
     */
    public void setKunden_ORT(String kunden_ORT) {
        this.kunden_ORT = kunden_ORT;
    }

    /**
     * @return the repair_FAX_TEXT
     */
    public String getRepair_FAX_TEXT() {
        return repair_FAX_TEXT;
    }

    /**
     * @param repair_FAX_TEXT the repair_FAX_TEXT to set
     */
    public void setRepair_FAX_TEXT(String repair_FAX_TEXT) {
        this.repair_FAX_TEXT = repair_FAX_TEXT;
    }

    /**
     * @return the repair_INTERNE_BEMERKUNG
     */
    public String getRepair_INTERNE_BEMERKUNG() {
        return repair_INTERNE_BEMERKUNG;
    }

    /**
     * @param repair_INTERNE_BEMERKUNG the repair_INTERNE_BEMERKUNG to set
     */
    public void setRepair_INTERNE_BEMERKUNG(String repair_INTERNE_BEMERKUNG) {
        this.repair_INTERNE_BEMERKUNG = repair_INTERNE_BEMERKUNG;
    }

    /**
     * @return the repair_REPARATUR_DATUM
     */
    public String getRepair_REPARATUR_DATUM() {
        return repair_REPARATUR_DATUM;
    }

    /**
     * @param repair_REPARATUR_DATUM the repair_REPARATUR_DATUM to set
     */
    public void setRepair_REPARATUR_DATUM(String repair_REPARATUR_DATUM) {
        this.repair_REPARATUR_DATUM = repair_REPARATUR_DATUM;
    }

    /**
     * @return the techniker_FIRMA_TECHNIKER
     */
    public String getTechniker_FIRMA_TECHNIKER() {
        return techniker_FIRMA_TECHNIKER;
    }

    /**
     * @param techniker_FIRMA_TECHNIKER the techniker_FIRMA_TECHNIKER to set
     */
    public void setTechniker_FIRMA_TECHNIKER(String techniker_FIRMA_TECHNIKER) {
        this.techniker_FIRMA_TECHNIKER = techniker_FIRMA_TECHNIKER;
    }

    /**
     * @return the techniker_NAME_TECHNIKER
     */
    public String getTechniker_NAME_TECHNIKER() {
        return techniker_NAME_TECHNIKER;
    }

    /**
     * @param techniker_NAME_TECHNIKER the techniker_NAME_TECHNIKER to set
     */
    public void setTechniker_NAME_TECHNIKER(String techniker_NAME_TECHNIKER) {
        this.techniker_NAME_TECHNIKER = techniker_NAME_TECHNIKER;
    }

    /**
     * @return the rechnungen_RECHNUNGSNUMMERINTERN
     */
    public String getRechnungen_RECHNUNGSNUMMERINTERN() {
        return rechnungen_RECHNUNGSNUMMERINTERN;
    }

    /**
     * @param rechnungen_RECHNUNGSNUMMERINTERN the rechnungen_RECHNUNGSNUMMERINTERN to set
     */
    public void setRechnungen_RECHNUNGSNUMMERINTERN(String rechnungen_RECHNUNGSNUMMERINTERN) {
        this.rechnungen_RECHNUNGSNUMMERINTERN = rechnungen_RECHNUNGSNUMMERINTERN;
    }

    /**
     * @return the rechnungen_RECHNUNGSDATUM
     */
    public String getRechnungen_RECHNUNGSDATUM() {
        return rechnungen_RECHNUNGSDATUM;
    }

    /**
     * @param rechnungen_RECHNUNGSDATUM the rechnungen_RECHNUNGSDATUM to set
     */
    public void setRechnungen_RECHNUNGSDATUM(String rechnungen_RECHNUNGSDATUM) {
        this.rechnungen_RECHNUNGSDATUM = rechnungen_RECHNUNGSDATUM;
    }

    /**
     * @return the rechnungen_LIEFERDATUM
     */
    public String getRechnungen_LIEFERDATUM() {
        return rechnungen_LIEFERDATUM;
    }

    /**
     * @param rechnungen_LIEFERDATUM the rechnungen_LIEFERDATUM to set
     */
    public void setRechnungen_LIEFERDATUM(String rechnungen_LIEFERDATUM) {
        this.rechnungen_LIEFERDATUM = rechnungen_LIEFERDATUM;
    }

    /**
     * @return the rechnungen_REPAIR_ID
     */
    public String getRechnungen_REPAIR_ID() {
        return rechnungen_REPAIR_ID;
    }

    /**
     * @param rechnungen_REPAIR_ID the rechnungen_REPAIR_ID to set
     */
    public void setRechnungen_REPAIR_ID(String rechnungen_REPAIR_ID) {
        this.rechnungen_REPAIR_ID = rechnungen_REPAIR_ID;
    }

    /**
     * @return the rechnungen_ANGEBOTSNUMMER
     */
    public String getRechnungen_ANGEBOTSNUMMER() {
        return rechnungen_ANGEBOTSNUMMER;
    }

    /**
     * @param rechnungen_ANGEBOTSNUMMER the rechnungen_ANGEBOTSNUMMER to set
     */
    public void setRechnungen_ANGEBOTSNUMMER(String rechnungen_ANGEBOTSNUMMER) {
        this.rechnungen_ANGEBOTSNUMMER = rechnungen_ANGEBOTSNUMMER;
    }

    /**
     * @return the rechnungen_BESTELLNUMMER
     */
    public String getRechnungen_BESTELLNUMMER() {
        return rechnungen_BESTELLNUMMER;
    }

    /**
     * @param rechnungen_BESTELLNUMMER the rechnungen_BESTELLNUMMER to set
     */
    public void setRechnungen_BESTELLNUMMER(String rechnungen_BESTELLNUMMER) {
        this.rechnungen_BESTELLNUMMER = rechnungen_BESTELLNUMMER;
    }

    /**
     * @return the rechnungen_INVOICEDUE
     */
    public String getRechnungen_INVOICEDUE() {
        return rechnungen_INVOICEDUE;
    }

    /**
     * @param rechnungen_INVOICEDUE the rechnungen_INVOICEDUE to set
     */
    public void setRechnungen_INVOICEDUE(String rechnungen_INVOICEDUE) {
        this.rechnungen_INVOICEDUE = rechnungen_INVOICEDUE;
    }

    /**
     * @return the rechnungen_INVOICEQTY
     */
    public String getRechnungen_INVOICEQTY() {
        return rechnungen_INVOICEQTY;
    }

    /**
     * @param rechnungen_INVOICEQTY the rechnungen_INVOICEQTY to set
     */
    public void setRechnungen_INVOICEQTY(String rechnungen_INVOICEQTY) {
        this.rechnungen_INVOICEQTY = rechnungen_INVOICEQTY;
    }

    /**
     * @return the rechnungen_ME
     */
    public String getRechnungen_ME() {
        return rechnungen_ME;
    }

    /**
     * @param rechnungen_ME the rechnungen_ME to set
     */
    public void setRechnungen_ME(String rechnungen_ME) {
        this.rechnungen_ME = rechnungen_ME;
    }

    /**
     * @return the rechnungen_DESCRIPTION
     */
    public String getRechnungen_DESCRIPTION() {
        return rechnungen_DESCRIPTION;
    }

    /**
     * @param rechnungen_DESCRIPTION the rechnungen_DESCRIPTION to set
     */
    public void setRechnungen_DESCRIPTION(String rechnungen_DESCRIPTION) {
        this.rechnungen_DESCRIPTION = rechnungen_DESCRIPTION;
    }

    /**
     * @return the rechnungen_UNITPRICE
     */
    public String getRechnungen_UNITPRICE() {
        return rechnungen_UNITPRICE;
    }

    /**
     * @param rechnungen_UNITPRICE the rechnungen_UNITPRICE to set
     */
    public void setRechnungen_UNITPRICE(String rechnungen_UNITPRICE) {
        this.rechnungen_UNITPRICE = rechnungen_UNITPRICE;
    }

    /**
     * @return the rechnungen_TOTAL
     */
    public String getRechnungen_TOTAL() {
        return rechnungen_TOTAL;
    }

    /**
     * @param rechnungen_TOTAL the rechnungen_TOTAL to set
     */
    public void setRechnungen_TOTAL(String rechnungen_TOTAL) {
        this.rechnungen_TOTAL = rechnungen_TOTAL;
    }

    /**
     * @return the rechnungen_AMT
     */
    public String getRechnungen_AMT() {
        return rechnungen_AMT;
    }

    /**
     * @param rechnungen_AMT the rechnungen_AMT to set
     */
    public void setRechnungen_AMT(String rechnungen_AMT) {
        this.rechnungen_AMT = rechnungen_AMT;
    }

    /**
     * @return the rechnungen_TAX
     */
    public String getRechnungen_TAX() {
        return rechnungen_TAX;
    }

    /**
     * @param rechnungen_TAX the rechnungen_TAX to set
     */
    public void setRechnungen_TAX(String rechnungen_TAX) {
        this.rechnungen_TAX = rechnungen_TAX;
    }

    /**
     * @return the rechnungen_SUM
     */
    public String getRechnungen_SUM() {
        return rechnungen_SUM;
    }

    /**
     * @param rechnungen_SUM the rechnungen_SUM to set
     */
    public void setRechnungen_SUM(String rechnungen_SUM) {
        this.rechnungen_SUM = rechnungen_SUM;
    }
}