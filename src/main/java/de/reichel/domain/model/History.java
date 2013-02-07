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
 * History generated by hbm2java
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="HISTORY"
    ,catalog="ansent"
)
public class History  implements java.io.Serializable {


     private Integer idHistory;
     private Date timestamp;
     private String beschreibung;
     private Integer idUser;
     private Integer idAnlagenStandorte;
     private Integer idAnlagen;
     private Integer idStandorte;
     private Integer idBetreiber;
     private Integer idKunde;
     private Integer idRepair;
     private Integer idUvv;
     private Integer idEntsorger;
     private Date datum;
     private Date von;
     private Date bis;

    public History() {
    }

    public History(String beschreibung, Integer idUser, Integer idAnlagenStandorte, Integer idAnlagen, Integer idStandorte, Integer idBetreiber, Integer idKunde, Integer idRepair, Integer idUvv, Integer idEntsorger, Date datum, Date von, Date bis) {
       this.beschreibung = beschreibung;
       this.idUser = idUser;
       this.idAnlagenStandorte = idAnlagenStandorte;
       this.idAnlagen = idAnlagen;
       this.idStandorte = idStandorte;
       this.idBetreiber = idBetreiber;
       this.idKunde = idKunde;
       this.idRepair = idRepair;
       this.idUvv = idUvv;
       this.idEntsorger = idEntsorger;
       this.datum = datum;
       this.von = von;
       this.bis = bis;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="ID_HISTORY", unique=true, nullable=false)
    public Integer getIdHistory() {
        return this.idHistory;
    }
    
    public void setIdHistory(Integer idHistory) {
        this.idHistory = idHistory;
    }
    @Version@Temporal(TemporalType.TIMESTAMP)
    @Column(name="TIMESTAMP", nullable=false, length=19)
    public Date getTimestamp() {
        return this.timestamp;
    }
    
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
    @Column(name="BESCHREIBUNG", length=200)
    public String getBeschreibung() {
        return this.beschreibung;
    }
    
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
    
    @Column(name="ID_USER")
    public Integer getIdUser() {
        return this.idUser;
    }
    
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
    
    @Column(name="ID_ANLAGEN_STANDORTE")
    public Integer getIdAnlagenStandorte() {
        return this.idAnlagenStandorte;
    }
    
    public void setIdAnlagenStandorte(Integer idAnlagenStandorte) {
        this.idAnlagenStandorte = idAnlagenStandorte;
    }
    
    @Column(name="ID_ANLAGEN")
    public Integer getIdAnlagen() {
        return this.idAnlagen;
    }
    
    public void setIdAnlagen(Integer idAnlagen) {
        this.idAnlagen = idAnlagen;
    }
    
    @Column(name="ID_STANDORTE")
    public Integer getIdStandorte() {
        return this.idStandorte;
    }
    
    public void setIdStandorte(Integer idStandorte) {
        this.idStandorte = idStandorte;
    }
    
    @Column(name="ID_BETREIBER")
    public Integer getIdBetreiber() {
        return this.idBetreiber;
    }
    
    public void setIdBetreiber(Integer idBetreiber) {
        this.idBetreiber = idBetreiber;
    }
    
    @Column(name="ID_KUNDE")
    public Integer getIdKunde() {
        return this.idKunde;
    }
    
    public void setIdKunde(Integer idKunde) {
        this.idKunde = idKunde;
    }
    
    @Column(name="ID_REPAIR")
    public Integer getIdRepair() {
        return this.idRepair;
    }
    
    public void setIdRepair(Integer idRepair) {
        this.idRepair = idRepair;
    }
    
    @Column(name="ID_UVV")
    public Integer getIdUvv() {
        return this.idUvv;
    }
    
    public void setIdUvv(Integer idUvv) {
        this.idUvv = idUvv;
    }
    
    @Column(name="ID_ENTSORGER")
    public Integer getIdEntsorger() {
        return this.idEntsorger;
    }
    
    public void setIdEntsorger(Integer idEntsorger) {
        this.idEntsorger = idEntsorger;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATUM", length=19)
    public Date getDatum() {
        return this.datum;
    }
    
    public void setDatum(Date datum) {
        this.datum = datum;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="VON", length=19)
    public Date getVon() {
        return this.von;
    }
    
    public void setVon(Date von) {
        this.von = von;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="BIS", length=19)
    public Date getBis() {
        return this.bis;
    }
    
    public void setBis(Date bis) {
        this.bis = bis;
    }




}


