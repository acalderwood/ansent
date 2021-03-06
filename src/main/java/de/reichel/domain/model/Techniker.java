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
 * Techniker generated by hbm2java
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="TECHNIKER"
    ,catalog="ansent"
)
public class Techniker  implements java.io.Serializable {


     private int idTechniker;
     private Date timestamp;
     private String firmaTechniker;
     private String nameTechniker;

    public Techniker() {
    }

	
    public Techniker(int idTechniker) {
        this.idTechniker = idTechniker;
    }
    public Techniker(int idTechniker, String firmaTechniker, String nameTechniker) {
       this.idTechniker = idTechniker;
       this.firmaTechniker = firmaTechniker;
       this.nameTechniker = nameTechniker;
    }
   
     @Id 
    
    @Column(name="ID_TECHNIKER", unique=true, nullable=false)
    public int getIdTechniker() {
        return this.idTechniker;
    }
    
    public void setIdTechniker(int idTechniker) {
        this.idTechniker = idTechniker;
    }
    @Version@Temporal(TemporalType.TIMESTAMP)
    @Column(name="TIMESTAMP", nullable=false, length=19)
    public Date getTimestamp() {
        return this.timestamp;
    }
    
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
    @Column(name="FIRMA_TECHNIKER", length=40)
    public String getFirmaTechniker() {
        return this.firmaTechniker;
    }
    
    public void setFirmaTechniker(String firmaTechniker) {
        this.firmaTechniker = firmaTechniker;
    }
    
    @Column(name="NAME_TECHNIKER", length=40)
    public String getNameTechniker() {
        return this.nameTechniker;
    }
    
    public void setNameTechniker(String nameTechniker) {
        this.nameTechniker = nameTechniker;
    }




}


