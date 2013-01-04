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
 * RechnungenTe generated by hbm2java
 */
@Entity
@Table(name="Rechnungen_TE"
    ,catalog="ansent"
)
public class RechnungenTe  implements java.io.Serializable {


     private Integer id;
     private Date timestamp;
     private Integer idKunden;
     private String rechnungsnummerintern;
     private String rechnungsempfaenger;
     private Date angebotsdatum;
     private String angebotsnummer;
     private Date bestelldatum;
     private String bestellnummer;
     private Double netto;
     private Double nettoTeile;
     private Date rechnungsdatum;
     private Date lieferdatum;
     private Date faelligDat;
     private Integer skonto;
     private Date skontobis;
     private Date bezahlDat;
     private Integer repairId;
     private Double nettoRepair;
     private String rechnungsKennzeichen;
     private Integer mwst;
     private Date storno;

    public RechnungenTe() {
    }

    public RechnungenTe(Integer idKunden, String rechnungsnummerintern, String rechnungsempfaenger, Date angebotsdatum, String angebotsnummer, Date bestelldatum, String bestellnummer, Double netto, Double nettoTeile, Date rechnungsdatum, Date lieferdatum, Date faelligDat, Integer skonto, Date skontobis, Date bezahlDat, Integer repairId, Double nettoRepair, String rechnungsKennzeichen, Integer mwst, Date storno) {
       this.idKunden = idKunden;
       this.rechnungsnummerintern = rechnungsnummerintern;
       this.rechnungsempfaenger = rechnungsempfaenger;
       this.angebotsdatum = angebotsdatum;
       this.angebotsnummer = angebotsnummer;
       this.bestelldatum = bestelldatum;
       this.bestellnummer = bestellnummer;
       this.netto = netto;
       this.nettoTeile = nettoTeile;
       this.rechnungsdatum = rechnungsdatum;
       this.lieferdatum = lieferdatum;
       this.faelligDat = faelligDat;
       this.skonto = skonto;
       this.skontobis = skontobis;
       this.bezahlDat = bezahlDat;
       this.repairId = repairId;
       this.nettoRepair = nettoRepair;
       this.rechnungsKennzeichen = rechnungsKennzeichen;
       this.mwst = mwst;
       this.storno = storno;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="ID", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
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
    
    @Column(name="ID_KUNDEN")
    public Integer getIdKunden() {
        return this.idKunden;
    }
    
    public void setIdKunden(Integer idKunden) {
        this.idKunden = idKunden;
    }
    
    @Column(name="RECHNUNGSNUMMERINTERN", length=50)
    public String getRechnungsnummerintern() {
        return this.rechnungsnummerintern;
    }
    
    public void setRechnungsnummerintern(String rechnungsnummerintern) {
        this.rechnungsnummerintern = rechnungsnummerintern;
    }
    
    @Column(name="RECHNUNGSEMPFAENGER")
    public String getRechnungsempfaenger() {
        return this.rechnungsempfaenger;
    }
    
    public void setRechnungsempfaenger(String rechnungsempfaenger) {
        this.rechnungsempfaenger = rechnungsempfaenger;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ANGEBOTSDATUM", length=19)
    public Date getAngebotsdatum() {
        return this.angebotsdatum;
    }
    
    public void setAngebotsdatum(Date angebotsdatum) {
        this.angebotsdatum = angebotsdatum;
    }
    
    @Column(name="ANGEBOTSNUMMER")
    public String getAngebotsnummer() {
        return this.angebotsnummer;
    }
    
    public void setAngebotsnummer(String angebotsnummer) {
        this.angebotsnummer = angebotsnummer;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="BESTELLDATUM", length=19)
    public Date getBestelldatum() {
        return this.bestelldatum;
    }
    
    public void setBestelldatum(Date bestelldatum) {
        this.bestelldatum = bestelldatum;
    }
    
    @Column(name="BESTELLNUMMER")
    public String getBestellnummer() {
        return this.bestellnummer;
    }
    
    public void setBestellnummer(String bestellnummer) {
        this.bestellnummer = bestellnummer;
    }
    
    @Column(name="NETTO", precision=22, scale=0)
    public Double getNetto() {
        return this.netto;
    }
    
    public void setNetto(Double netto) {
        this.netto = netto;
    }
    
    @Column(name="NETTO_TEILE", precision=22, scale=0)
    public Double getNettoTeile() {
        return this.nettoTeile;
    }
    
    public void setNettoTeile(Double nettoTeile) {
        this.nettoTeile = nettoTeile;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="RECHNUNGSDATUM", length=19)
    public Date getRechnungsdatum() {
        return this.rechnungsdatum;
    }
    
    public void setRechnungsdatum(Date rechnungsdatum) {
        this.rechnungsdatum = rechnungsdatum;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LIEFERDATUM", length=19)
    public Date getLieferdatum() {
        return this.lieferdatum;
    }
    
    public void setLieferdatum(Date lieferdatum) {
        this.lieferdatum = lieferdatum;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FAELLIG_DAT", length=19)
    public Date getFaelligDat() {
        return this.faelligDat;
    }
    
    public void setFaelligDat(Date faelligDat) {
        this.faelligDat = faelligDat;
    }
    
    @Column(name="SKONTO")
    public Integer getSkonto() {
        return this.skonto;
    }
    
    public void setSkonto(Integer skonto) {
        this.skonto = skonto;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="SKONTOBIS", length=19)
    public Date getSkontobis() {
        return this.skontobis;
    }
    
    public void setSkontobis(Date skontobis) {
        this.skontobis = skontobis;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="BEZAHL_DAT", length=19)
    public Date getBezahlDat() {
        return this.bezahlDat;
    }
    
    public void setBezahlDat(Date bezahlDat) {
        this.bezahlDat = bezahlDat;
    }
    
    @Column(name="REPAIR_ID")
    public Integer getRepairId() {
        return this.repairId;
    }
    
    public void setRepairId(Integer repairId) {
        this.repairId = repairId;
    }
    
    @Column(name="NETTO_REPAIR", precision=22, scale=0)
    public Double getNettoRepair() {
        return this.nettoRepair;
    }
    
    public void setNettoRepair(Double nettoRepair) {
        this.nettoRepair = nettoRepair;
    }
    
    @Column(name="RECHNUNGS_KENNZEICHEN", length=15)
    public String getRechnungsKennzeichen() {
        return this.rechnungsKennzeichen;
    }
    
    public void setRechnungsKennzeichen(String rechnungsKennzeichen) {
        this.rechnungsKennzeichen = rechnungsKennzeichen;
    }
    
    @Column(name="MWST")
    public Integer getMwst() {
        return this.mwst;
    }
    
    public void setMwst(Integer mwst) {
        this.mwst = mwst;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="Storno", length=19)
    public Date getStorno() {
        return this.storno;
    }
    
    public void setStorno(Date storno) {
        this.storno = storno;
    }




}


