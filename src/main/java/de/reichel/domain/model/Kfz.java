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
 * Kfz generated by hbm2java
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="KFZ"
    ,catalog="ansent"
)
public class Kfz  implements java.io.Serializable {


     private int kfzId;
     private Date timestamp;
     private String kfzKennzeichen;
     private String hersteller;
     private String typ;
     private String variante;
     private String schluesselnr;

    public Kfz() {
    }

	
    public Kfz(int kfzId, String typ, String variante, String schluesselnr) {
        this.kfzId = kfzId;
        this.typ = typ;
        this.variante = variante;
        this.schluesselnr = schluesselnr;
    }
    public Kfz(int kfzId, String kfzKennzeichen, String hersteller, String typ, String variante, String schluesselnr) {
       this.kfzId = kfzId;
       this.kfzKennzeichen = kfzKennzeichen;
       this.hersteller = hersteller;
       this.typ = typ;
       this.variante = variante;
       this.schluesselnr = schluesselnr;
    }
   
     @Id 
    
    @Column(name="KFZ_ID", unique=true, nullable=false)
    public int getKfzId() {
        return this.kfzId;
    }
    
    public void setKfzId(int kfzId) {
        this.kfzId = kfzId;
    }
    @Version@Temporal(TemporalType.TIMESTAMP)
    @Column(name="TIMESTAMP", nullable=false, length=19)
    public Date getTimestamp() {
        return this.timestamp;
    }
    
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
    @Column(name="KFZ_KENNZEICHEN", length=8)
    public String getKfzKennzeichen() {
        return this.kfzKennzeichen;
    }
    
    public void setKfzKennzeichen(String kfzKennzeichen) {
        this.kfzKennzeichen = kfzKennzeichen;
    }
    
    @Column(name="HERSTELLER", length=20)
    public String getHersteller() {
        return this.hersteller;
    }
    
    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }
    
    @Column(name="TYP", nullable=false, length=20)
    public String getTyp() {
        return this.typ;
    }
    
    public void setTyp(String typ) {
        this.typ = typ;
    }
    
    @Column(name="VARIANTE", nullable=false, length=20)
    public String getVariante() {
        return this.variante;
    }
    
    public void setVariante(String variante) {
        this.variante = variante;
    }
    
    @Column(name="SCHLUESSELNR", nullable=false, length=20)
    public String getSchluesselnr() {
        return this.schluesselnr;
    }
    
    public void setSchluesselnr(String schluesselnr) {
        this.schluesselnr = schluesselnr;
    }




}


