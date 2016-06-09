/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.dao.BetreiberDAO;
import de.reichel.domain.model.Betreiber;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author Alastair Calderwood
 */
public class BetreiberBean implements Serializable {

    @Inject
    BetreiberDAO betreiberDAO;
    protected Integer idBetreiber;
    protected Integer idKunden;
    protected String betreibername;
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

    public void hydrate(Betreiber betreiber) {
        this.setAnsprechpartner(betreiber.getAnsprechpartner());
        this.setBemerkung(betreiber.getBemerkung());
        this.setBetreibername(betreiber.getBetreibername());
        this.setEmail(betreiber.getEmail());
        this.setFax(betreiber.getFax());
        this.setIdBetreiber(betreiber.getIdBetreiber());
        this.setInterneNotiz(betreiber.getInterneNotiz());
        this.setLand(betreiber.getLand());
        this.setOrt(betreiber.getOrt());
        this.setPlz(betreiber.getPlz());
        this.setStrasseNr(betreiber.getStrasseNr());
        this.setTelefon(betreiber.getTelefon());
    }

    /**
     * @return the idBetreiber
     */
    public Integer getIdBetreiber() {
        return idBetreiber;
    }

    /**
     * @param idBetreiber the idBetreiber to set
     */
    public void setIdBetreiber(Integer idBetreiber) {
        this.idBetreiber = idBetreiber;
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

    /**
     * @return the betreibername
     */
    public String getBetreibername() {
        return betreibername;
    }

    /**
     * @param betreibername the betreibername to set
     */
    public void setBetreibername(String betreibername) {
        this.betreibername = betreibername;
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
}
