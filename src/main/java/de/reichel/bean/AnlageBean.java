/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.dao.AnlagenDAO;
import de.reichel.domain.model.Anlagen;
import de.reichel.domain.model.AnlagenArt;
import de.reichel.domain.model.AnlagenHersteller;
import de.reichel.domain.model.Betreiber;
import de.reichel.domain.model.Kunden;
import de.reichel.domain.model.Standorte;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Alastair Calderwood
 */
public class AnlageBean implements Serializable {

    @Inject
    protected AnlagenDAO anlagenDAO;
    private Integer idAnlagen;
    protected Integer idArt;
    private String art;
    protected Integer idHersteller;
    private String hersteller;
    protected Integer idKunden;
    protected Integer idBetreiber;
    protected Integer idStandort;
    protected String interneNr;
    protected String typ;
    protected String fabrikationsnr;
    protected Date baujahr;
    protected String bemerkung;
    protected String interneNotiz;
    protected Date naechsteUVV;
    protected Date naechsteWartung;
    protected Standorte standorte;
    protected Kunden kunden;
    protected Betreiber betreiber;

    public void hydrate(Object[] anlagenGraph) {
        Anlagen anlagen = (Anlagen) anlagenGraph[0];
        //String year = yearFormat.format(anlagen.getBaujahr());
        this.setBaujahr(anlagen.getBaujahr());
        this.setBemerkung(anlagen.getBemerkung());
        this.setFabrikationsnr(anlagen.getFabrikationsnummer());
        this.setIdAnlagen(anlagen.getIdAnlagen());
        this.setIdArt(anlagen.getIdAnlagenArt());
        this.setIdHersteller(anlagen.getIdAnlagenHersteller());
        this.setInterneNotiz(anlagen.getInterneNotiz());
        this.setInterneNr(anlagen.getInterneNr());
        if (anlagen.getNUvv() != null) {
            this.setNaechsteUVV(anlagen.getNUvv());
        }
        if (anlagen.getNWart() != null) {
            this.setNaechsteWartung(anlagen.getNWart());
        }
        this.setTyp(anlagen.getTyp());

        AnlagenArt anlagenArt = (AnlagenArt) anlagenGraph[1];
        this.setArt(anlagenArt.getArt());

        AnlagenHersteller anlagenHersteller = (AnlagenHersteller) anlagenGraph[2];
        this.setHersteller(anlagenHersteller.getHersteller());
    }

    public void setInterneNr(String interneNr) {
        this.interneNr = interneNr;
    }

    public String getInterneNr() {
        return interneNr;
    }

    public Integer getIdHersteller() {
        return idHersteller;
    }

    public void setIdHersteller(Integer hersteller) {
        this.idHersteller = hersteller;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getFabrikationsnr() {
        return fabrikationsnr;
    }

    public void setFabrikationsnr(String fabrikationsnr) {
        this.fabrikationsnr = fabrikationsnr;
    }

    public Date getBaujahr() {
        return baujahr;
    }

    public void setBaujahr(Date baujahr) {
        this.baujahr = baujahr;
    }

    public String getBemerkung() {
        return bemerkung;
    }

    public void setBemerkung(String bemerkung) {
        this.bemerkung = bemerkung;
    }

    public Integer getIdArt() {
        return idArt;
    }

    public void setIdArt(Integer art) {
        this.idArt = art;
    }

    public String getInterneNotiz() {
        return interneNotiz;
    }

    public void setInterneNotiz(String interneNotiz) {
        this.interneNotiz = interneNotiz;
    }

    public Date getNaechsteUVV() {
        return naechsteUVV;
    }

    public void setNaechsteUVV(Date naechsteUVV) {
        this.naechsteUVV = naechsteUVV;
    }

    public Date getNaechsteWartung() {
        return naechsteWartung;
    }

    public void setNaechsteWartung(Date naechsteWartung) {
        this.naechsteWartung = naechsteWartung;
    }

    /**
     * @return the idAnlagen
     */
    public Integer getIdAnlagen() {
        return idAnlagen;
    }

    /**
     * @param idAnlagen the idAnlagen to set
     */
    public void setIdAnlagen(Integer idAnlagen) {
        this.idAnlagen = idAnlagen;
    }

    public Integer getIdStandort() {
        return idStandort;
    }

    public void setIdStandort(Integer standort) {
        this.idStandort = standort;
    }

    public Integer getIdKunden() {
        return idKunden;
    }

    public void setIdKunden(Integer kunden) {
        this.idKunden = kunden;
    }

    public Integer getIdBetreiber() {
        return idBetreiber;
    }

    public void setIdBetreiber(Integer betreiber) {
        this.idBetreiber = betreiber;
    }

    /**
     * @return the hersteller
     */
    public String getHersteller() {
        return hersteller;
    }

    /**
     * @param hersteller the hersteller to set
     */
    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    /**
     * @return the art
     */
    public String getArt() {
        return art;
    }

    /**
     * @param art the art to set
     */
    public void setArt(String art) {
        this.art = art;
    }

    public Standorte getStandorte() {
        return standorte;
    }

    public void setStandorte(Standorte standorte) {
        this.standorte = standorte;
    }

    public Kunden getKunden() {
        return kunden;
    }

    public void setKunden(Kunden kunden) {
        this.kunden = kunden;
    }

    public Betreiber getBetreiber() {
        return betreiber;
    }

    public void setBetreiber(Betreiber betreiber) {
        this.betreiber = betreiber;
    }
}
