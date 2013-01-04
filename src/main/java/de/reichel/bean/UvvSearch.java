/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.dao.AnlagenDAO;
import de.reichel.dao.UvvDAO;
import de.reichel.domain.model.Betreiber;
import de.reichel.domain.model.Kunden;
import de.reichel.domain.model.Standorte;
import de.reichel.domain.model.Uvv;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

/**
 *
 * @author alastair
 */
@ManagedBean(name = "uvvSearch")
@RequestScoped
@Controller
public class UvvSearch implements Serializable {

    private static final Log log = LogFactory.getLog(UvvSearch.class);
    @Inject
    private UvvDAO uvvDAO;  
    @Inject
    private AnlagenDAO anlagenDAO;
    private Object[] anlage;
    private Standorte standorte;
    private Kunden kunden;
    private Betreiber betreiber;
    private Integer idAnlagenForUvv;
    private Integer idUvv;
    private Object[] uvv;
    private List<Object[]> uvvPunkte;
    private List<Object[]> uvvMangel;

    public List<Uvv> getSelectableUvv() {
        if (this.idAnlagenForUvv != null) {
            return uvvDAO.getSelectableUvv(this.idAnlagenForUvv);
        } else {
            return new ArrayList<Uvv>();
        }
    }

    public void processSelectedUvv(AjaxBehaviorEvent event) {
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
    public Integer getIdAnlagenForUvv() {
        return idAnlagenForUvv;
    }

    /**
     * @param idAnlagen the idAnlagen to set
     */
    public void setIdAnlagenForUvv(Integer idAnlagenForUvv) {
        System.out.println("setting idAnlagenForUvv to:" + idAnlagenForUvv);
        this.idAnlagenForUvv = idAnlagenForUvv;
    }
//    public void setAnlageSearch(AnlageSearch anlageSearch) {
//        this.anlageSearch = anlageSearch;
//    }

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

    public List<Object[]> getAllAnlagen() {
        return anlagenDAO.getAllAnlagen();
    }

    public Object[] getAnlage() {
        return anlage;
    }

    public void setAnlage(Object[] anlage) {
        this.anlage = anlage;
    }

    public void processSelectedAnlagen(AjaxBehaviorEvent event) {
        int id = this.getIdAnlagenForUvv();
        System.out.println("id=" + id);

        this.anlage = anlagenDAO.getAnlagen(id);
        this.setStandorte(anlagenDAO.getStandort(id));
        this.setBetreiber(anlagenDAO.getBetreiber(id));
        this.setKunden(anlagenDAO.getKunden(id));
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
}
