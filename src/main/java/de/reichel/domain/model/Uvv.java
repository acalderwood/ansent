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

/**
 * Uvv generated by hbm2java
 */
@Entity
@Table(name="UVV"
    ,catalog="ansent"
)
public class Uvv  implements java.io.Serializable {


     private Integer idUvv;
     private Date timestamp;
     private Integer idAnlagen;
     private Date pruefdatum;
     private String pruefer;
     private String bemerkungen;
     private Short maengel;
     private String maengelErledigtDurch;
     private Date maengelErledigtDatum;
     private Short erstpruefungIo;
     private Short nachpruefungIo;
     private Short pruefplakette;
     private Date druckdatum;
     private Date nextUvv;
     private Short isNew;

    public Uvv() {
    }

    public Uvv(Integer idAnlagen, Date pruefdatum, String pruefer, String bemerkungen, Short maengel, String maengelErledigtDurch, Date maengelErledigtDatum, Short erstpruefungIo, Short nachpruefungIo, Short pruefplakette, Date druckdatum, Date nextUvv, Short isNew) {
       this.idAnlagen = idAnlagen;
       this.pruefdatum = pruefdatum;
       this.pruefer = pruefer;
       this.bemerkungen = bemerkungen;
       this.maengel = maengel;
       this.maengelErledigtDurch = maengelErledigtDurch;
       this.maengelErledigtDatum = maengelErledigtDatum;
       this.erstpruefungIo = erstpruefungIo;
       this.nachpruefungIo = nachpruefungIo;
       this.pruefplakette = pruefplakette;
       this.druckdatum = druckdatum;
       this.nextUvv = nextUvv;
       this.isNew = isNew;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="ID_UVV", unique=true, nullable=false)
    public Integer getIdUvv() {
        return this.idUvv;
    }
    
    public void setIdUvv(Integer idUvv) {
        this.idUvv = idUvv;
    }
    @Version@Temporal(TemporalType.TIMESTAMP)
    @Column(name="TIMESTAMP", nullable=false, length=19)
    public Date getTimestamp() {
        return this.timestamp;
    }
    
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
    @Column(name="ID_ANLAGEN")
    public Integer getIdAnlagen() {
        return this.idAnlagen;
    }
    
    public void setIdAnlagen(Integer idAnlagen) {
        this.idAnlagen = idAnlagen;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="PRUEFDATUM", length=19)
    public Date getPruefdatum() {
        return this.pruefdatum;
    }
    
    public void setPruefdatum(Date pruefdatum) {
        this.pruefdatum = pruefdatum;
    }
    
    @Column(name="PRUEFER", length=100)
    public String getPruefer() {
        return this.pruefer;
    }
    
    public void setPruefer(String pruefer) {
        this.pruefer = pruefer;
    }
    
    @Column(name="BEMERKUNGEN", length=65535)
    public String getBemerkungen() {
        return this.bemerkungen;
    }
    
    public void setBemerkungen(String bemerkungen) {
        this.bemerkungen = bemerkungen;
    }
    
    @Column(name="MAENGEL")
    public Short getMaengel() {
        return this.maengel;
    }
    
    public void setMaengel(Short maengel) {
        this.maengel = maengel;
    }
    
    @Column(name="MAENGEL_ERLEDIGT_DURCH", length=100)
    public String getMaengelErledigtDurch() {
        return this.maengelErledigtDurch;
    }
    
    public void setMaengelErledigtDurch(String maengelErledigtDurch) {
        this.maengelErledigtDurch = maengelErledigtDurch;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="MAENGEL_ERLEDIGT_DATUM", length=19)
    public Date getMaengelErledigtDatum() {
        return this.maengelErledigtDatum;
    }
    
    public void setMaengelErledigtDatum(Date maengelErledigtDatum) {
        this.maengelErledigtDatum = maengelErledigtDatum;
    }
    
    @Column(name="ERSTPRUEFUNG_IO")
    public Short getErstpruefungIo() {
        return this.erstpruefungIo;
    }
    
    public void setErstpruefungIo(Short erstpruefungIo) {
        this.erstpruefungIo = erstpruefungIo;
    }
    
    @Column(name="NACHPRUEFUNG_IO")
    public Short getNachpruefungIo() {
        return this.nachpruefungIo;
    }
    
    public void setNachpruefungIo(Short nachpruefungIo) {
        this.nachpruefungIo = nachpruefungIo;
    }
    
    @Column(name="PRUEFPLAKETTE")
    public Short getPruefplakette() {
        return this.pruefplakette;
    }
    
    public void setPruefplakette(Short pruefplakette) {
        this.pruefplakette = pruefplakette;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DRUCKDATUM", length=19)
    public Date getDruckdatum() {
        return this.druckdatum;
    }
    
    public void setDruckdatum(Date druckdatum) {
        this.druckdatum = druckdatum;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="NEXT_UVV", length=19)
    public Date getNextUvv() {
        return this.nextUvv;
    }
    
    public void setNextUvv(Date nextUvv) {
        this.nextUvv = nextUvv;
    }
    
    @Column(name="IS_NEW")
    public Short getIsNew() {
        return this.isNew;
    }
    
    public void setIsNew(Short isNew) {
        this.isNew = isNew;
    }




}

