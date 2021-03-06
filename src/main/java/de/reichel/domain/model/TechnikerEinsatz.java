package de.reichel.domain.model;
// Generated 25-Dec-2012 19:51:17 by Hibernate Tools 3.2.2.GA


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * TechnikerEinsatz generated by hbm2java
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="TECHNIKER_EINSATZ"
    ,catalog="ansent"
)
public class TechnikerEinsatz  implements java.io.Serializable {


     private Integer technikerEinsatzId;
     private Date timestamp;
     private Integer repairId;
     private int technikerId;
     private Date einsatzDatum;
     private Short timeFromH;
     private Short timeFromMin;
     private Short timeToH;
     private Short timeToMin;
     private Integer kfzId;
     private Integer kmStart;
     private Integer kmEnde;
     private String beschreibung;
     private String zielStrasseNr;
     private Integer zielPlz;
     private String zielOrt;

    public TechnikerEinsatz() {
    }

	
    public TechnikerEinsatz(int technikerId) {
        this.technikerId = technikerId;
    }
    public TechnikerEinsatz(Integer repairId, int technikerId, Date einsatzDatum, Short timeFromH, Short timeFromMin, Short timeToH, Short timeToMin, Integer kfzId, Integer kmStart, Integer kmEnde, String beschreibung, String zielStrasseNr, Integer zielPlz, String zielOrt) {
       this.repairId = repairId;
       this.technikerId = technikerId;
       this.einsatzDatum = einsatzDatum;
       this.timeFromH = timeFromH;
       this.timeFromMin = timeFromMin;
       this.timeToH = timeToH;
       this.timeToMin = timeToMin;
       this.kfzId = kfzId;
       this.kmStart = kmStart;
       this.kmEnde = kmEnde;
       this.beschreibung = beschreibung;
       this.zielStrasseNr = zielStrasseNr;
       this.zielPlz = zielPlz;
       this.zielOrt = zielOrt;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="TECHNIKER_EINSATZ_ID", unique=true, nullable=false)
    public Integer getTechnikerEinsatzId() {
        return this.technikerEinsatzId;
    }
    
    public void setTechnikerEinsatzId(Integer technikerEinsatzId) {
        this.technikerEinsatzId = technikerEinsatzId;
    }
    @Version@Temporal(TemporalType.TIMESTAMP)
    @Column(name="TIMESTAMP", nullable=false, length=19)
    public Date getTimestamp() {
        return this.timestamp;
    }
    
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
    @Column(name="REPAIR_ID")
    public Integer getRepairId() {
        return this.repairId;
    }
    
    public void setRepairId(Integer repairId) {
        this.repairId = repairId;
    }
    
    @Column(name="TECHNIKER_ID", nullable=false)
    public int getTechnikerId() {
        return this.technikerId;
    }
    
    public void setTechnikerId(int technikerId) {
        this.technikerId = technikerId;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="EINSATZ_DATUM", length=10)
    public Date getEinsatzDatum() {
        return this.einsatzDatum;
    }
    
    public void setEinsatzDatum(Date einsatzDatum) {
        this.einsatzDatum = einsatzDatum;
    }
    
    @Column(name="TIME_FROM_H")
    public Short getTimeFromH() {
        return this.timeFromH;
    }
    
    public void setTimeFromH(Short timeFromH) {
        this.timeFromH = timeFromH;
    }
    
    @Column(name="TIME_FROM_MIN")
    public Short getTimeFromMin() {
        return this.timeFromMin;
    }
    
    public void setTimeFromMin(Short timeFromMin) {
        this.timeFromMin = timeFromMin;
    }
    
    @Column(name="TIME_TO_H")
    public Short getTimeToH() {
        return this.timeToH;
    }
    
    public void setTimeToH(Short timeToH) {
        this.timeToH = timeToH;
    }
    
    @Column(name="TIME_TO_MIN")
    public Short getTimeToMin() {
        return this.timeToMin;
    }
    
    public void setTimeToMin(Short timeToMin) {
        this.timeToMin = timeToMin;
    }
    
    @Column(name="KFZ_ID")
    public Integer getKfzId() {
        return this.kfzId;
    }
    
    public void setKfzId(Integer kfzId) {
        this.kfzId = kfzId;
    }
    
    @Column(name="KM_START")
    public Integer getKmStart() {
        return this.kmStart;
    }
    
    public void setKmStart(Integer kmStart) {
        this.kmStart = kmStart;
    }
    
    @Column(name="KM_ENDE")
    public Integer getKmEnde() {
        return this.kmEnde;
    }
    
    public void setKmEnde(Integer kmEnde) {
        this.kmEnde = kmEnde;
    }
    
    @Column(name="BESCHREIBUNG", length=65535)
    public String getBeschreibung() {
        return this.beschreibung;
    }
    
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
    
    @Column(name="ZIEL_STRASSE_NR", length=200)
    public String getZielStrasseNr() {
        return this.zielStrasseNr;
    }
    
    public void setZielStrasseNr(String zielStrasseNr) {
        this.zielStrasseNr = zielStrasseNr;
    }
    
    @Column(name="ZIEL_PLZ")
    public Integer getZielPlz() {
        return this.zielPlz;
    }
    
    public void setZielPlz(Integer zielPlz) {
        this.zielPlz = zielPlz;
    }
    
    @Column(name="ZIEL_ORT", length=200)
    public String getZielOrt() {
        return this.zielOrt;
    }
    
    public void setZielOrt(String zielOrt) {
        this.zielOrt = zielOrt;
    }




}


