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
 * Kunden generated by hbm2java
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="KUNDEN"
    ,catalog="ansent"
)
public class Kunden  implements java.io.Serializable {


     private Integer idKunden;
     private Date timestamp;
     private String idRw;
     private String firmenname;
     private String ansprechpartner;
     private String strasseNr;
     private String plz;
     private String ort;
     private String land;
     private String telefon;
     private String fax;
     private String email;
     private String bemerkung;
     private String interneNotiz;
     private String buchungskreis;
     private String steuernummer;

    public Kunden() {
    }

    public Kunden(String idRw, String firmenname, String ansprechpartner, String strasseNr, String plz, String ort, String land, String telefon, String fax, String email, String bemerkung, String interneNotiz, String buchungskreis, String steuernummer) {
       this.idRw = idRw;
       this.firmenname = firmenname;
       this.ansprechpartner = ansprechpartner;
       this.strasseNr = strasseNr;
       this.plz = plz;
       this.ort = ort;
       this.land = land;
       this.telefon = telefon;
       this.fax = fax;
       this.email = email;
       this.bemerkung = bemerkung;
       this.interneNotiz = interneNotiz;
       this.buchungskreis = buchungskreis;
       this.steuernummer = steuernummer;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="ID_KUNDEN", unique=true, nullable=false)
    public Integer getIdKunden() {
        return this.idKunden;
    }
    
    public void setIdKunden(Integer idKunden) {
        this.idKunden = idKunden;
    }
    @Version@Temporal(TemporalType.TIMESTAMP)
    @Column(name="TIMESTAMP", nullable=false, length=19)
    public Date getTimestamp() {
        return this.timestamp;
    }
    
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
    @Column(name="ID_RW", length=5)
    public String getIdRw() {
        return this.idRw;
    }
    
    public void setIdRw(String idRw) {
        this.idRw = idRw;
    }
    
    @Column(name="FIRMENNAME", length=200)
    public String getFirmenname() {
        return this.firmenname;
    }
    
    public void setFirmenname(String firmenname) {
        this.firmenname = firmenname;
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
    
    @Column(name="BUCHUNGSKREIS", length=10)
    public String getBuchungskreis() {
        return this.buchungskreis;
    }
    
    public void setBuchungskreis(String buchungskreis) {
        this.buchungskreis = buchungskreis;
    }
    
    @Column(name="STEUERNUMMER", length=20)
    public String getSteuernummer() {
        return this.steuernummer;
    }
    
    public void setSteuernummer(String steuernummer) {
        this.steuernummer = steuernummer;
    }




}


