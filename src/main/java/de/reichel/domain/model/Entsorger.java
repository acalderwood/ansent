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
 * Entsorger generated by hbm2java
 */
@Entity
@Table(name="ENTSORGER"
    ,catalog="ansent"
)
public class Entsorger  implements java.io.Serializable {


     private Integer idEntsorger;
     private Date timestamp;
     private String entsorgerName;
     private String strasseNr;
     private String plz;
     private String ort;
     private String bemerkung;
     private String ansprechpartner;
     private String fax;
     private String faxGsm;
     private String telefon;
     private String email;
     private String emailGsm;

    public Entsorger() {
    }

	
    public Entsorger(String entsorgerName) {
        this.entsorgerName = entsorgerName;
    }
    public Entsorger(String entsorgerName, String strasseNr, String plz, String ort, String bemerkung, String ansprechpartner, String fax, String faxGsm, String telefon, String email, String emailGsm) {
       this.entsorgerName = entsorgerName;
       this.strasseNr = strasseNr;
       this.plz = plz;
       this.ort = ort;
       this.bemerkung = bemerkung;
       this.ansprechpartner = ansprechpartner;
       this.fax = fax;
       this.faxGsm = faxGsm;
       this.telefon = telefon;
       this.email = email;
       this.emailGsm = emailGsm;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="ID_ENTSORGER", unique=true, nullable=false)
    public Integer getIdEntsorger() {
        return this.idEntsorger;
    }
    
    public void setIdEntsorger(Integer idEntsorger) {
        this.idEntsorger = idEntsorger;
    }
    @Version@Temporal(TemporalType.TIMESTAMP)
    @Column(name="TIMESTAMP", nullable=false, length=19)
    public Date getTimestamp() {
        return this.timestamp;
    }
    
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
    @Column(name="ENTSORGER_NAME", nullable=false, length=200)
    public String getEntsorgerName() {
        return this.entsorgerName;
    }
    
    public void setEntsorgerName(String entsorgerName) {
        this.entsorgerName = entsorgerName;
    }
    
    @Column(name="STRASSE_NR", length=200)
    public String getStrasseNr() {
        return this.strasseNr;
    }
    
    public void setStrasseNr(String strasseNr) {
        this.strasseNr = strasseNr;
    }
    
    @Column(name="PLZ", length=10)
    public String getPlz() {
        return this.plz;
    }
    
    public void setPlz(String plz) {
        this.plz = plz;
    }
    
    @Column(name="ORT", length=200)
    public String getOrt() {
        return this.ort;
    }
    
    public void setOrt(String ort) {
        this.ort = ort;
    }
    
    @Column(name="BEMERKUNG", length=200)
    public String getBemerkung() {
        return this.bemerkung;
    }
    
    public void setBemerkung(String bemerkung) {
        this.bemerkung = bemerkung;
    }
    
    @Column(name="ANSPRECHPARTNER", length=200)
    public String getAnsprechpartner() {
        return this.ansprechpartner;
    }
    
    public void setAnsprechpartner(String ansprechpartner) {
        this.ansprechpartner = ansprechpartner;
    }
    
    @Column(name="FAX", length=200)
    public String getFax() {
        return this.fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
    }
    
    @Column(name="FAX_GSM", length=200)
    public String getFaxGsm() {
        return this.faxGsm;
    }
    
    public void setFaxGsm(String faxGsm) {
        this.faxGsm = faxGsm;
    }
    
    @Column(name="TELEFON", length=200)
    public String getTelefon() {
        return this.telefon;
    }
    
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    
    @Column(name="EMAIL", length=200)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name="EMAIL_GSM", length=200)
    public String getEmailGsm() {
        return this.emailGsm;
    }
    
    public void setEmailGsm(String emailGsm) {
        this.emailGsm = emailGsm;
    }




}

