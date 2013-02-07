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
 * AnlagenArt generated by hbm2java
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="ANLAGEN_ART"
    ,catalog="ansent"
)
public class AnlagenArt  implements java.io.Serializable {


     private Integer idAnlagenArt;
     private Date timestamp;
     private String art;
     private Integer repBetrag;

    public AnlagenArt() {
    }

    public AnlagenArt(String art, Integer repBetrag) {
       this.art = art;
       this.repBetrag = repBetrag;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="ID_ANLAGEN_ART", unique=true, nullable=false)
    public Integer getIdAnlagenArt() {
        return this.idAnlagenArt;
    }
    
    public void setIdAnlagenArt(Integer idAnlagenArt) {
        this.idAnlagenArt = idAnlagenArt;
    }
    @Version@Temporal(TemporalType.TIMESTAMP)
    @Column(name="TIMESTAMP", nullable=false, length=19)
    public Date getTimestamp() {
        return this.timestamp;
    }
    
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
    @Column(name="ART", length=200)
    public String getArt() {
        return this.art;
    }
    
    public void setArt(String art) {
        this.art = art;
    }
    
    @Column(name="REP_BETRAG")
    public Integer getRepBetrag() {
        return this.repBetrag;
    }
    
    public void setRepBetrag(Integer repBetrag) {
        this.repBetrag = repBetrag;
    }




}


