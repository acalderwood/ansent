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
 * RepairTeile generated by hbm2java
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="REPAIR_TEILE"
    ,catalog="ansent"
)
public class RepairTeile  implements java.io.Serializable {


     private Integer idRepairTeile;
     private Date timestamp;
     private Integer idTeile;
     private Integer idRepair;
     private Double anzahl;
     private String teileEinheit;
     private String teileName;
     private Double teilePreis;
     private Double teileRabatt;
     private Double teileEk;
     private Integer idSub;

    public RepairTeile() {
    }

    public RepairTeile(Integer idTeile, Integer idRepair, Double anzahl, String teileEinheit, String teileName, Double teilePreis, Double teileRabatt, Double teileEk, Integer idSub) {
       this.idTeile = idTeile;
       this.idRepair = idRepair;
       this.anzahl = anzahl;
       this.teileEinheit = teileEinheit;
       this.teileName = teileName;
       this.teilePreis = teilePreis;
       this.teileRabatt = teileRabatt;
       this.teileEk = teileEk;
       this.idSub = idSub;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="ID_REPAIR_TEILE", unique=true, nullable=false)
    public Integer getIdRepairTeile() {
        return this.idRepairTeile;
    }
    
    public void setIdRepairTeile(Integer idRepairTeile) {
        this.idRepairTeile = idRepairTeile;
    }
    @Version@Temporal(TemporalType.TIMESTAMP)
    @Column(name="TIMESTAMP", nullable=false, length=19)
    public Date getTimestamp() {
        return this.timestamp;
    }
    
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
    @Column(name="ID_TEILE")
    public Integer getIdTeile() {
        return this.idTeile;
    }
    
    public void setIdTeile(Integer idTeile) {
        this.idTeile = idTeile;
    }
    
    @Column(name="ID_REPAIR")
    public Integer getIdRepair() {
        return this.idRepair;
    }
    
    public void setIdRepair(Integer idRepair) {
        this.idRepair = idRepair;
    }
    
    @Column(name="ANZAHL", precision=22, scale=0)
    public Double getAnzahl() {
        return this.anzahl;
    }
    
    public void setAnzahl(Double anzahl) {
        this.anzahl = anzahl;
    }
    
    @Column(name="TEILE_EINHEIT", length=50)
    public String getTeileEinheit() {
        return this.teileEinheit;
    }
    
    public void setTeileEinheit(String teileEinheit) {
        this.teileEinheit = teileEinheit;
    }
    
    @Column(name="TEILE_NAME")
    public String getTeileName() {
        return this.teileName;
    }
    
    public void setTeileName(String teileName) {
        this.teileName = teileName;
    }
    
    @Column(name="TEILE_PREIS", precision=22, scale=0)
    public Double getTeilePreis() {
        return this.teilePreis;
    }
    
    public void setTeilePreis(Double teilePreis) {
        this.teilePreis = teilePreis;
    }
    
    @Column(name="TEILE_RABATT", precision=22, scale=0)
    public Double getTeileRabatt() {
        return this.teileRabatt;
    }
    
    public void setTeileRabatt(Double teileRabatt) {
        this.teileRabatt = teileRabatt;
    }
    
    @Column(name="TEILE_EK", precision=22, scale=0)
    public Double getTeileEk() {
        return this.teileEk;
    }
    
    public void setTeileEk(Double teileEk) {
        this.teileEk = teileEk;
    }
    
    @Column(name="ID_SUB")
    public Integer getIdSub() {
        return this.idSub;
    }
    
    public void setIdSub(Integer idSub) {
        this.idSub = idSub;
    }




}


