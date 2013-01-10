/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.dao.KundenDAO;
import de.reichel.domain.model.Kunden;
import java.io.Serializable;
import javax.inject.Inject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Alastair Calderwood
 */
public class KundenBean implements Serializable {

    private static final Log log = LogFactory.getLog(KundenBean.class);
    @Inject
    protected KundenDAO kundenDAO;
    protected Integer idKunden;
    protected String firmenname;
    protected String ansprechpartner;
    protected String strasseNr;
    protected String plz;
    protected String ort;
    protected String land;
    protected String telefon;
    protected String fax;
    protected String email;
    protected String bemerkung;
    protected String interneNotiz;
    protected String buchungskreis;
    protected String steuerNr;

    public void hydrate(Kunden kunden) {
        log.debug("KundenBean:" + this);
        this.setIdKunden(kunden.getIdKunden());
        this.setAnsprechpartner(kunden.getAnsprechpartner());
        this.setBemerkung(kunden.getBemerkung());
        this.setBuchungskreis(kunden.getBuchungskreis());
        this.setEmail(kunden.getEmail());
        this.setFax(kunden.getFax());
        this.setFirmenname(kunden.getFirmenname());
        this.setInterneNotiz(kunden.getInterneNotiz());
        this.setLand(kunden.getLand());
        this.setOrt(kunden.getOrt());
        this.setPlz(kunden.getPlz());
        this.setSteuerNr(kunden.getSteuernummer());
        this.setStrasseNr(kunden.getStrasseNr());
        this.setTelefon(kunden.getTelefon());
    }

    /**
     * @return the firmenname
     */
    public String getFirmenname() {
        return firmenname;
    }

    /**
     * @param firmenname the firmenname to set
     */
    public void setFirmenname(String firmenname) {
        this.firmenname = firmenname;
    }

    /**
     * @return the ansprechpartner
     */
    public String getAnsprechpartner() {
        return ansprechpartner;
    }

    /**
     * @param ansprechpartner the ansprechpartner to set
     */
    public void setAnsprechpartner(String ansprechpartner) {
        this.ansprechpartner = ansprechpartner;
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
     * @return the land
     */
    public String getLand() {
        return land;
    }

    /**
     * @param land the land to set
     */
    public void setLand(String land) {
        this.land = land;
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
     * @return the interneNotiz
     */
    public String getInterneNotiz() {
        return interneNotiz;
    }

    /**
     * @param interneNotiz the interneNotiz to set
     */
    public void setInterneNotiz(String interneNotiz) {
        this.interneNotiz = interneNotiz;
    }

    /**
     * @return the buchungskreis
     */
    public String getBuchungskreis() {
        return buchungskreis;
    }

    /**
     * @param buchungskreis the buchungskreis to set
     */
    public void setBuchungskreis(String buchungskreis) {
        this.buchungskreis = buchungskreis;
    }

    /**
     * @return the steuerNr
     */
    public String getSteuerNr() {
        return steuerNr;
    }

    /**
     * @param steuerNr the steuerNr to set
     */
    public void setSteuerNr(String steuerNr) {
        this.steuerNr = steuerNr;
    }

    /**
     * @return the idKunden
     */
    public Integer getIdKunden() {
        return idKunden;
    }

    /**
     * @param idKunden the idKunden to set
     */
    public void setIdKunden(Integer idKunden) {
        this.idKunden = idKunden;
    }
}
