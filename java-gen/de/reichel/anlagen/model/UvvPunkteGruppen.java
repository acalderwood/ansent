package de.reichel.anlagen.model;
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
 * UvvPunkteGruppen generated by hbm2java
 */
@Entity
@Table(name="UVV_PUNKTE_GRUPPEN"
    ,catalog="ansent"
)
public class UvvPunkteGruppen  implements java.io.Serializable {


     private Integer idUvvPunkteGruppen;
     private Date timestamp;
     private int idUvvArt;
     private int gruppe;
     private String text;

    public UvvPunkteGruppen() {
    }

	
    public UvvPunkteGruppen(int idUvvArt, int gruppe) {
        this.idUvvArt = idUvvArt;
        this.gruppe = gruppe;
    }
    public UvvPunkteGruppen(int idUvvArt, int gruppe, String text) {
       this.idUvvArt = idUvvArt;
       this.gruppe = gruppe;
       this.text = text;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="ID_UVV_PUNKTE_GRUPPEN", unique=true, nullable=false)
    public Integer getIdUvvPunkteGruppen() {
        return this.idUvvPunkteGruppen;
    }
    
    public void setIdUvvPunkteGruppen(Integer idUvvPunkteGruppen) {
        this.idUvvPunkteGruppen = idUvvPunkteGruppen;
    }
    @Version@Temporal(TemporalType.TIMESTAMP)
    @Column(name="TIMESTAMP", nullable=false, length=19)
    public Date getTimestamp() {
        return this.timestamp;
    }
    
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
    @Column(name="ID_UVV_ART", nullable=false)
    public int getIdUvvArt() {
        return this.idUvvArt;
    }
    
    public void setIdUvvArt(int idUvvArt) {
        this.idUvvArt = idUvvArt;
    }
    
    @Column(name="GRUPPE", nullable=false)
    public int getGruppe() {
        return this.gruppe;
    }
    
    public void setGruppe(int gruppe) {
        this.gruppe = gruppe;
    }
    
    @Column(name="TEXT")
    public String getText() {
        return this.text;
    }
    
    public void setText(String text) {
        this.text = text;
    }




}


