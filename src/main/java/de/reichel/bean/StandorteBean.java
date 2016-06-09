/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.dao.StandorteDAO;
import de.reichel.domain.model.Entsorger;
import de.reichel.domain.model.Standorte;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Alastair Calderwood
 */
public class StandorteBean implements Serializable {

    @Inject
    protected StandorteDAO standorteDAO;
    private Integer idStandorte;
    protected Integer idEntsorger;
    protected Integer idBetreiber;
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
    protected String besonderheiten;
    protected Double anfahrtspauschaleBetrag;
    protected Double kilometerpauschaleBetrag;
    protected String standortname;

    public void hydrate(Standorte standorte) {
        this.setAnsprechpartner(standorte.getAnsprechpartner());
        this.setIdEntsorger(standorte.getIdEntsorger());
        this.setBemerkung(standorte.getBemerkung());
        this.setBesonderheiten(standorte.getBesonderheiten());
        this.setEmail(standorte.getEmail());
        this.setFax(standorte.getFax());
        this.setIdBetreiber(standorte.getIdBetreiber());
        this.setInterneNotiz(standorte.getInterneNotiz());
        this.setAnfahrtspauschaleBetrag(standorte.getAnfahrtspauschaleBetrag());
        this.setKilometerpauschaleBetrag(standorte.getKilometerpauschaleBetrag());
        this.setLand(standorte.getLand());
        this.setOrt(standorte.getOrt());
        this.setPlz(standorte.getPlz());
        this.setStandortname(standorte.getStandortname());
        this.setStrasseNr(standorte.getStrasseNr());
        this.setTelefon(standorte.getTelefon());
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
     * @return the besonderheiten
     */
    public String getBesonderheiten() {
        return besonderheiten;
    }

    /**
     * @param besonderheiten the besonderheiten to set
     */
    public void setBesonderheiten(String besonderheiten) {
        this.besonderheiten = besonderheiten;
    }

    /**
     * @return the anfahrtspauschaleBetrag
     */
    public Double getAnfahrtspauschaleBetrag() {
        return anfahrtspauschaleBetrag;
    }

    /**
     * @param anfahrtspauschaleBetrag the anfahrtspauschaleBetrag to set
     */
    public void setAnfahrtspauschaleBetrag(Double anfahrtspauschaleBetrag) {
        this.anfahrtspauschaleBetrag = anfahrtspauschaleBetrag;
    }

    /**
     * @return the kilometerpauschaleBetrag
     */
    public Double getKilometerpauschaleBetrag() {
        return kilometerpauschaleBetrag;
    }

    /**
     * @param kilometerpauschaleBetrag the kilometerpauschaleBetrag to set
     */
    public void setKilometerpauschaleBetrag(Double kilometerpauschaleBetrag) {
        this.kilometerpauschaleBetrag = kilometerpauschaleBetrag;
    }

    /**
     * @return the standortname
     */
    public String getStandortname() {
        return standortname;
    }

    /**
     * @param standortname the standortname to set
     */
    public void setStandortname(String standortname) {
        this.standortname = standortname;
    }

    /**
     * @return the idStandorte
     */
    public Integer getIdStandorte() {
        return idStandorte;
    }

    /**
     * @param idStandorte the idStandorte to set
     */
    public void setIdStandorte(Integer idStandorte) {
        this.idStandorte = idStandorte;
    }
    
    public List<Entsorger> getSelectableEntsorger() {
        return standorteDAO.getSelectableEntsorger();
    }
}
