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
 * Fraktionen generated by hbm2java
 */
@Entity
@Table(name="FRAKTIONEN"
    ,catalog="ansent"
)
public class Fraktionen  implements java.io.Serializable {


     private Integer idFraktion;
     private Date timestamp;
     private String beschreibung;
     private String beschreibungLang;

    public Fraktionen() {
    }

	
    public Fraktionen(String beschreibung) {
        this.beschreibung = beschreibung;
    }
    public Fraktionen(String beschreibung, String beschreibungLang) {
       this.beschreibung = beschreibung;
       this.beschreibungLang = beschreibungLang;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="ID_FRAKTION", unique=true, nullable=false)
    public Integer getIdFraktion() {
        return this.idFraktion;
    }
    
    public void setIdFraktion(Integer idFraktion) {
        this.idFraktion = idFraktion;
    }
    @Version@Temporal(TemporalType.TIMESTAMP)
    @Column(name="TIMESTAMP", nullable=false, length=19)
    public Date getTimestamp() {
        return this.timestamp;
    }
    
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
    @Column(name="BESCHREIBUNG", nullable=false, length=20)
    public String getBeschreibung() {
        return this.beschreibung;
    }
    
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
    
    @Column(name="BESCHREIBUNG_LANG", length=200)
    public String getBeschreibungLang() {
        return this.beschreibungLang;
    }
    
    public void setBeschreibungLang(String beschreibungLang) {
        this.beschreibungLang = beschreibungLang;
    }




}


