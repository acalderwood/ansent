/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.dao.AnlagenDAO;
import de.reichel.dao.UvvDAO;
import de.reichel.domain.model.Anlagen;
import de.reichel.domain.model.Betreiber;
import de.reichel.domain.model.Kunden;
import de.reichel.domain.model.Standorte;
import de.reichel.domain.model.Uvv;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author alastair
 */
@ManagedBean(name = "uvvBean")
@ViewScoped
@Scope("view")
@Controller
public class UvvBean implements Serializable {

    private static final Log log = LogFactory.getLog(UvvBean.class);
    @Inject
    private UvvDAO uvvDAO;
    @Inject
    private AnlagenDAO anlagenDAO;
    private Object[] anlage;
    private Standorte standorte;
    private Kunden kunden;
    private Betreiber betreiber;
    private Integer idAnlagen;
    protected Integer idStandort;
    private Integer idUvv;
    private Object[] uvv;
    private List<Object[]> uvvPunkte;
    private List<Object[]> uvvMangel;
    private String pruefer;
    private Date pruefDatum;
    private Date naechsteUvv;
    private boolean erstPruefung;
    private boolean nachPruefung;
    private boolean plakette;
    private boolean maengel;
    private String erledigtDurch;
    private String erledigtAm;
    private String bemerkung;
    protected String plz;
    protected Date uvvVon;
    protected Date uvvBis;
    protected List<Anlagen> uvvList;
    
    
    public List<Uvv> getUvvForMonthAndPlz() {
        if (this.naechsteUvv != null) {
            return uvvDAO.getUvvForMonthAndPlz(this.naechsteUvv, this.plz);
        } else {
            return new ArrayList<Uvv>();
        }
    }

    public List<Uvv> getSelectableUvv() {
        if (this.idAnlagen != null) {
            return uvvDAO.getSelectableUvv(this.idAnlagen);
        } else {
            return new ArrayList<Uvv>();
        }
    }
    
    public void loadUvvs() {
        uvvDAO.loadUvvs(this);
    }

    public void processSelectedUvv() {
        this.uvv = uvvDAO.getUvv(this.getIdUvv());
        this.setUvvPunkte(uvvDAO.getUvvPunkte(this.getIdUvv()));
        this.setUvvMangel(uvvDAO.getUvvMangel(this.getIdUvv()));
    }

    public Object[] getUvv() {
        return uvv;
    }

    /**
     * @return the idAnlagen
     */
    public Integer getIdAnlagen() {
        return idAnlagen;
    }

    /**
     * @param idAnlagen the idAnlagen to set
     */
    public void setIdAnlagen(Integer idAnlagen) {
        System.out.println("setting idAnlagen for Uvv to:" + idAnlagen);
        this.idAnlagen = idAnlagen;
    }

    /**
     * @return the idUvv
     */
    public Integer getIdUvv() {
        return idUvv;
    }

    /**
     * @param idUvv the idUvv to set
     */
    public void setIdUvv(Integer idUvv) {
        this.idUvv = idUvv;
    }

    public List<Anlagen> getAllAnlagen() {
        return anlagenDAO.getAllAnlagen();
    }

    public Object[] getAnlage() {
        return anlage;
    }

    public void setAnlage(Object[] anlage) {
        this.anlage = anlage;
    }

    public void load() {
        int id = this.getIdAnlagen();
        System.out.println("id=" + id);

        this.anlage = anlagenDAO.getAnlagen(id);
//        this.setIdStandorte(anlagenDAO.getIdStandort(id));
//        this.setIdBetreiber(anlagenDAO.getIdBetreiber(id));
//        this.setIdKunden(anlagenDAO.getIdKunden(id));
    }

    /**
     * @return the standorte
     */
    public Standorte getStandorte() {
        return standorte;
    }

    /**
     * @param standorte the standorte to set
     */
    public void setStandorte(Standorte standorte) {
        this.standorte = standorte;
    }

    /**
     * @return the kunden
     */
    public Kunden getKunden() {
        return kunden;
    }

    /**
     * @param kunden the kunden to set
     */
    public void setKunden(Kunden kunden) {
        this.kunden = kunden;
    }

    /**
     * @return the betreiber
     */
    public Betreiber getBetreiber() {
        return betreiber;
    }

    /**
     * @param betreiber the betreiber to set
     */
    public void setBetreiber(Betreiber betreiber) {
        this.betreiber = betreiber;
    }

    /**
     * @return the uvvPunkte
     */
    public List<Object[]> getUvvPunkte() {
        return uvvPunkte;
    }

    /**
     * @param uvvPunkte the uvvPunkte to set
     */
    public void setUvvPunkte(List<Object[]> uvvPunkte) {
        this.uvvPunkte = uvvPunkte;
    }

    /**
     * @return the uvvMangel
     */
    public List<Object[]> getUvvMangel() {
        return uvvMangel;
    }

    /**
     * @param uvvMangel the uvvMangel to set
     */
    public void setUvvMangel(List<Object[]> uvvMangel) {
        this.uvvMangel = uvvMangel;
    }

    /**
     * @return the pruefer
     */
    public String getPruefer() {
        return pruefer;
    }

    /**
     * @param pruefer the pruefer to set
     */
    public void setPruefer(String pruefer) {
        this.pruefer = pruefer;
    }

    /**
     * @return the pruefDatum
     */
    public Date getPruefDatum() {
        return pruefDatum;
    }

    /**
     * @param pruefDatum the pruefDatum to set
     */
    public void setPruefDatum(Date pruefDatum) {
        this.pruefDatum = pruefDatum;
    }

    /**
     * @return the naechsteUvv
     */
    public Date getNaechsteUvv() {
        return naechsteUvv;
    }

    /**
     * @param naechsteUvv the naechsteUvv to set
     */
    public void setNaechsteUvv(Date naechsteUvv) {
        this.naechsteUvv = naechsteUvv;
    }

    /**
     * @return the plz
     */
    public String getPlz() {
        return plz;
    }

    /**
     * @param plz the plz to set
     */
    public void setPlz(String plz) {
        this.plz = plz;
    }

    /**
     * @return the uvvVon
     */
    public Date getUvvVon() {
        return uvvVon;
    }

    /**
     * @param uvvVon the uvvVon to set
     */
    public void setUvvVon(Date uvvVon) {
        this.uvvVon = uvvVon;
    }

    /**
     * @return the uvvBis
     */
    public Date getUvvBis() {
        return uvvBis;
    }

    /**
     * @param uvvBis the uvvBis to set
     */
    public void setUvvBis(Date uvvBis) {
        this.uvvBis = uvvBis;
    }

    /**
     * @return the uvvList
     */
    public List<Anlagen> getUvvList() {
        return uvvList;
    }

    /**
     * @param uvvList the uvvList to set
     */
    public void setUvvList(List<Anlagen> uvvList) {
        this.uvvList = uvvList;
    }

    /**
     * @return the idStandort
     */
    public Integer getIdStandort() {
        return idStandort;
    }

    /**
     * @param idStandort the idStandort to set
     */
    public void setIdStandort(Integer idStandort) {
        this.idStandort = idStandort;
    }
}
