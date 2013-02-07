package de.reichel.domain.model;
// Generated 25-Dec-2012 19:51:17 by Hibernate Tools 3.2.2.GA


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Teile generated by hbm2java
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="TEILE"
    ,catalog="ansent"
)
public class Teile  implements java.io.Serializable {


     private int id;
     private Date timestamp;
     private String intern;
     private Short idGruppe;
     private String bezeichnung;
     private String einheit;
     private Double vk;
     private Double ek;
     private Double hk;
     private Short idLieferant;
     private String bestellnummer;
     private Short lagerbestand;
     private String bemerkung;
     private String angebotstext;
     private Integer rabatt;

    public Teile() {
    }

	
    public Teile(int id) {
        this.id = id;
    }
    public Teile(int id, String intern, Short idGruppe, String bezeichnung, String einheit, Double vk, Double ek, Double hk, Short idLieferant, String bestellnummer, Short lagerbestand, String bemerkung, String angebotstext, Integer rabatt) {
       this.id = id;
       this.intern = intern;
       this.idGruppe = idGruppe;
       this.bezeichnung = bezeichnung;
       this.einheit = einheit;
       this.vk = vk;
       this.ek = ek;
       this.hk = hk;
       this.idLieferant = idLieferant;
       this.bestellnummer = bestellnummer;
       this.lagerbestand = lagerbestand;
       this.bemerkung = bemerkung;
       this.angebotstext = angebotstext;
       this.rabatt = rabatt;
    }
   
     @Id 
    
    @Column(name="ID", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    @Version@Temporal(TemporalType.TIMESTAMP)
    @Column(name="TIMESTAMP", nullable=false, length=19)
    public Date getTimestamp() {
        return this.timestamp;
    }
    
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
    @Column(name="INTERN", length=50)
    public String getIntern() {
        return this.intern;
    }
    
    public void setIntern(String intern) {
        this.intern = intern;
    }
    
    @Column(name="ID_GRUPPE")
    public Short getIdGruppe() {
        return this.idGruppe;
    }
    
    public void setIdGruppe(Short idGruppe) {
        this.idGruppe = idGruppe;
    }
    
    @Column(name="BEZEICHNUNG")
    public String getBezeichnung() {
        return this.bezeichnung;
    }
    
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
    
    @Column(name="EINHEIT", length=50)
    public String getEinheit() {
        return this.einheit;
    }
    
    public void setEinheit(String einheit) {
        this.einheit = einheit;
    }
    
    @Column(name="VK", precision=22, scale=0)
    public Double getVk() {
        return this.vk;
    }
    
    public void setVk(Double vk) {
        this.vk = vk;
    }
    
    @Column(name="EK", precision=22, scale=0)
    public Double getEk() {
        return this.ek;
    }
    
    public void setEk(Double ek) {
        this.ek = ek;
    }
    
    @Column(name="HK", precision=22, scale=0)
    public Double getHk() {
        return this.hk;
    }
    
    public void setHk(Double hk) {
        this.hk = hk;
    }
    
    @Column(name="ID_LIEFERANT")
    public Short getIdLieferant() {
        return this.idLieferant;
    }
    
    public void setIdLieferant(Short idLieferant) {
        this.idLieferant = idLieferant;
    }
    
    @Column(name="BESTELLNUMMER", length=50)
    public String getBestellnummer() {
        return this.bestellnummer;
    }
    
    public void setBestellnummer(String bestellnummer) {
        this.bestellnummer = bestellnummer;
    }
    
    @Column(name="LAGERBESTAND")
    public Short getLagerbestand() {
        return this.lagerbestand;
    }
    
    public void setLagerbestand(Short lagerbestand) {
        this.lagerbestand = lagerbestand;
    }
    
    @Column(name="BEMERKUNG", length=65535)
    public String getBemerkung() {
        return this.bemerkung;
    }
    
    public void setBemerkung(String bemerkung) {
        this.bemerkung = bemerkung;
    }
    
    @Column(name="ANGEBOTSTEXT", length=65535)
    public String getAngebotstext() {
        return this.angebotstext;
    }
    
    public void setAngebotstext(String angebotstext) {
        this.angebotstext = angebotstext;
    }
    
    @Column(name="RABATT")
    public Integer getRabatt() {
        return this.rabatt;
    }
    
    public void setRabatt(Integer rabatt) {
        this.rabatt = rabatt;
    }




}


