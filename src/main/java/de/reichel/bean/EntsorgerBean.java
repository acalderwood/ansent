/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.dao.EntsorgerDAO;
import de.reichel.domain.model.Entsorger;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.inject.Inject;

/**
 *
 * @author Alastair Calderwood
 */
public class EntsorgerBean implements Serializable {

    @Inject
    protected EntsorgerDAO entsorgerDAO;
    protected Integer idEntsorger;
    protected String entsorgerName;
    protected String strasseNr;
    protected String plz;
    protected String ort;
    protected String bemerkung;
    protected String ansprechPartner;
    protected String fax;
    protected String faxGsm;
    protected String telefon;
    protected String email;
    protected String emailGsm;
    protected Date timestamp;

    public void hydrate(Entsorger entsorger) {
        this.setAnsprechPartner(entsorger.getAnsprechpartner());
        this.setBemerkung(entsorger.getBemerkung());
        this.setEmail(entsorger.getEmail());
        this.setEmailGsm(entsorger.getEmailGsm());
        this.setEntsorgerName(entsorger.getEntsorgerName());
        this.setFax(entsorger.getFax());
        this.setFaxGsm(entsorger.getFaxGsm());
        this.setIdEntsorger(entsorger.getIdEntsorger());
        this.setOrt(entsorger.getOrt());
        this.setPlz(entsorger.getPlz());
        this.setStrasseNr(entsorger.getStrasseNr());
        this.setTelefon(entsorger.getTelefon());
    }

    /**
     * @return the idEntsorger
     */
    public Integer getIdEntsorger() {
        return idEntsorger;
    }

    /**
     * @param idEntsorger the idEntsorger to set
     */
    public void setIdEntsorger(Integer idEntsorger) {
        this.idEntsorger = idEntsorger;
    }

    /**
     * @return the entsorgerName
     */
    public String getEntsorgerName() {
        return entsorgerName;
    }

    /**
     * @param entsorgerName the entsorgerName to set
     */
    public void setEntsorgerName(String entsorgerName) {
        this.entsorgerName = entsorgerName;
    }

    /**
     * @return the strasseNr
     */
    public String getStrasseNr() {
        return strasseNr;
    }

    /**
     * @param strasseNr the strasseNr to set
     */
    public void setStrasseNr(String strasseNr) {
        this.strasseNr = strasseNr;
    }

    /**
     * @return the plz
     */
    public String getPlz() {
        return plz;
    }

    /**
     * @param plz the plz to set
     */
    public void setPlz(String plz) {
        this.plz = plz;
    }

    /**
     * @return the ort
     */
    public String getOrt() {
        return ort;
    }

    /**
     * @param ort the ort to set
     */
    public void setOrt(String ort) {
        this.ort = ort;
    }

    /**
     * @return the bemerkung
     */
    public String getBemerkung() {
        return bemerkung;
    }

    /**
     * @param bemerkung the bemerkung to set
     */
    public void setBemerkung(String bemerkung) {
        this.bemerkung = bemerkung;
    }

    /**
     * @return the ansprechPartner
     */
    public String getAnsprechPartner() {
        return ansprechPartner;
    }

    /**
     * @param ansprechPartner the ansprechPartner to set
     */
    public void setAnsprechPartner(String ansprechPartner) {
        this.ansprechPartner = ansprechPartner;
    }

    /**
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return the faxGsm
     */
    public String getFaxGsm() {
        return faxGsm;
    }

    /**
     * @param faxGsm the faxGsm to set
     */
    public void setFaxGsm(String faxGsm) {
        this.faxGsm = faxGsm;
    }

    /**
     * @return the telefon
     */
    public String getTelefon() {
        return telefon;
    }

    /**
     * @param telefon the telefon to set
     */
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the emailGsm
     */
    public String getEmailGsm() {
        return emailGsm;
    }

    /**
     * @param emailGsm the emailGsm to set
     */
    public void setEmailGsm(String emailGsm) {
        this.emailGsm = emailGsm;
    }

    /**
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}
