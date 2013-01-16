/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.dao.TeileDAO;
import java.io.Serializable;
import java.util.Date;
import javax.inject.Inject;

/**
 *
 * @author Alastair Calderwood
 */
public class TeileBean implements Serializable {

    @Inject
    protected TeileDAO teileDAO;
    protected Integer idRepairTeile;
    protected Date timestamp;
    protected Integer idTeile;
    protected Integer idRepair;
    protected Double anzahl;
    protected String teileEinheit;
    protected String teileName;
    protected Double teilePreis;
    protected Double teileRabatt;
    protected Double teileEk;
    protected Integer idSub;

    /**
     * @return the idRepairTeile
     */
    public Integer getIdRepairTeile() {
        return idRepairTeile;
    }

    /**
     * @param idRepairTeile the idRepairTeile to set
     */
    public void setIdRepairTeile(Integer idRepairTeile) {
        this.idRepairTeile = idRepairTeile;
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

    /**
     * @return the idTeile
     */
    public Integer getIdTeile() {
        return idTeile;
    }

    /**
     * @param idTeile the idTeile to set
     */
    public void setIdTeile(Integer idTeile) {
        this.idTeile = idTeile;
    }

    /**
     * @return the idRepair
     */
    public Integer getIdRepair() {
        return idRepair;
    }

    /**
     * @param idRepair the idRepair to set
     */
    public void setIdRepair(Integer idRepair) {
        this.idRepair = idRepair;
    }

    /**
     * @return the anzahl
     */
    public Double getAnzahl() {
        return anzahl;
    }

    /**
     * @param anzahl the anzahl to set
     */
    public void setAnzahl(Double anzahl) {
        this.anzahl = anzahl;
    }

    /**
     * @return the teileEinheit
     */
    public String getTeileEinheit() {
        return teileEinheit;
    }

    /**
     * @param teileEinheit the teileEinheit to set
     */
    public void setTeileEinheit(String teileEinheit) {
        this.teileEinheit = teileEinheit;
    }

    /**
     * @return the teileName
     */
    public String getTeileName() {
        return teileName;
    }

    /**
     * @param teileName the teileName to set
     */
    public void setTeileName(String teileName) {
        this.teileName = teileName;
    }

    /**
     * @return the teilePreis
     */
    public Double getTeilePreis() {
        return teilePreis;
    }

    /**
     * @param teilePreis the teilePreis to set
     */
    public void setTeilePreis(Double teilePreis) {
        this.teilePreis = teilePreis;
    }

    /**
     * @return the teileRabatt
     */
    public Double getTeileRabatt() {
        return teileRabatt;
    }

    /**
     * @param teileRabatt the teileRabatt to set
     */
    public void setTeileRabatt(Double teileRabatt) {
        this.teileRabatt = teileRabatt;
    }

    /**
     * @return the teileEk
     */
    public Double getTeileEk() {
        return teileEk;
    }

    /**
     * @param teileEk the teileEk to set
     */
    public void setTeileEk(Double teileEk) {
        this.teileEk = teileEk;
    }

    /**
     * @return the idSub
     */
    public Integer getIdSub() {
        return idSub;
    }

    /**
     * @param idSub the idSub to set
     */
    public void setIdSub(Integer idSub) {
        this.idSub = idSub;
    }
}
