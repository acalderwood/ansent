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
 * Betreiber generated by hbm2java
 */
@Entity
@Table(name="BETREIBER"
    ,catalog="ansent"
)
public class Betreiber  implements java.io.Serializable {


     private Integer idBetreiber;
     private Date timestamp;
     private int idKunden;
     private String betreibername;
     private String ansprechpartner;
     private String strasseNr;
     private String plz;
     private String ort;
     private String bemerkung;
     private String interneNotiz;
     private String land;
     private String telefon;
     private String fax;
     private String email;

    public Betreiber() {
    }

	
    public Betreiber(int idKunden) {
        this.idKunden = idKunden;
    }
    public Betreiber(int idKunden, String betreibername, String ansprechpartner, String strasseNr, String plz, String ort, String bemerkung, String interneNotiz, String land, String telefon, String fax, String email) {
       this.idKunden = idKunden;
       this.betreibername = betreibername;
       this.ansprechpartner = ansprechpartner;
       this.strasseNr = strasseNr;
       this.plz = plz;
       this.ort = ort;
       this.bemerkung = bemerkung;
       this.interneNotiz = interneNotiz;
       this.land = land;
       this.telefon = telefon;
       this.fax = fax;
       this.email = email;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="ID_BETREIBER", unique=true, nullable=false)
    public Integer getIdBetreiber() {
        return this.idBetreiber;
    }
    
    public void setIdBetreiber(Integer idBetreiber) {
        this.idBetreiber = idBetreiber;
    }
    @Version@Temporal(TemporalType.TIMESTAMP)
    @Column(name="TIMESTAMP", nullable=false, length=19)
    public Date getTimestamp() {
        return this.timestamp;
    }
    
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
    @Column(name="ID_KUNDEN", nullable=false)
    public int getIdKunden() {
        return this.idKunden;
    }
    
    public void setIdKunden(int idKunden) {
        this.idKunden = idKunden;
    }
    
    @Column(name="BETREIBERNAME", length=200)
    public String getBetreibername() {
        return this.betreibername;
    }
    
    public void setBetreibername(String betreibername) {
        this.betreibername = betreibername;
    }
    
    @Column(name="ANSPRECHPARTNER", length=200)
    public String getAnsprechpartner() {
        return this.ansprechpartner;
    }
    
    public void setAnsprechpartner(String ansprechpartner) {
        this.ansprechpartner = ansprechpartner;
    }
    
    @Column(name="STRASSE_NR", length=200)
    public String getStrasseNr() {
        return this.strasseNr;
    }
    
    public void setStrasseNr(String strasseNr) {
        this.strasseNr = strasseNr;
    }
    
    @Column(name="PLZ", length=200)
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
    
    @Column(name="INTERNE_NOTIZ", length=200)
    public String getInterneNotiz() {
        return this.interneNotiz;
    }
    
    public void setInterneNotiz(String interneNotiz) {
        this.interneNotiz = interneNotiz;
    }
    
    @Column(name="LAND", length=200)
    public String getLand() {
        return this.land;
    }
    
    public void setLand(String land) {
        this.land = land;
    }
    
    @Column(name="TELEFON", length=200)
    public String getTelefon() {
        return this.telefon;
    }
    
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    
    @Column(name="FAX", length=200)
    public String getFax() {
        return this.fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
    }
    
    @Column(name="EMAIL", length=200)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }




}


