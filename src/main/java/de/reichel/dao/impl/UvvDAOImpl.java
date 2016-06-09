/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.dao.impl;

import de.reichel.bean.UvvBean;
import de.reichel.dao.UvvDAO;
import de.reichel.domain.model.Anlagen;
import de.reichel.domain.model.Standorte;
import de.reichel.domain.model.Uvv;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alastair
 */
@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "prototype")
public class UvvDAOImpl implements UvvDAO {

    private static final Log log = LogFactory.getLog(UvvDAOImpl.class);
    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Uvv> getSelectableUvv(int idAnlagen) {
        Query query = entityManager.createQuery("from Uvv uvv where uvv.idAnlagen = :idAnlagen order by uvv.pruefdatum");
        query.setParameter("idAnlagen", idAnlagen);
        return query.getResultList();
    }

    /**
     * 
     * @param idUvv
     * @return [0]=UVV, [1]=UVV_ART
     */
    @Transactional(readOnly = true)
    public Object[] getUvv(int idUvv) {
        Query query = entityManager.createQuery("from Uvv uvv, UvvArt uvvArt, Anlagen anlagen, AnlagenArt anlagenArt "
                + "where uvv.idUvv = :idUvv and uvv.idAnlagen = anlagen.idAnlagen and anlagen.idAnlagenArt = anlagenArt.idAnlagenArt "
                + "and uvvArt.idAnlagenArt = anlagenArt.idAnlagenArt");
        query.setParameter("idUvv", idUvv);
        List<Object[]> result = query.getResultList();
        return (Object[]) result.get(0);
    }
    
    @Transactional(readOnly = true)
    public List<Object[]> getUvvPunkte(int idUvv) {
        Query query = entityManager.createQuery("from UvvPunkte uvvPunkte, UvvPunkteGruppen uvvPunkteGruppen, Uvv uvv, UvvArt uvvArt, Anlagen anlagen, AnlagenArt anlagenArt "
                + "where uvv.idUvv=:idUvv and uvvPunkte.idUvvArt = uvvArt.idUvvArt and uvvPunkte.idUvvPunkteGruppen = uvvPunkteGruppen.idUvvPunkteGruppen "
                + "and uvv.idAnlagen=anlagen.idAnlagen and anlagen.idAnlagenArt=anlagenArt.idAnlagenArt "
                + "and uvvArt.idAnlagenArt=anlagenArt.idAnlagenArt");
        query.setParameter("idUvv", idUvv);
        return query.getResultList();
    }
    
    @Transactional(readOnly = true)
    public List<Object[]> getUvvMangel(int idUvv) {
        Query query = entityManager.createQuery("from UvvMangel uvvMangel where uvvMangel.idUvv = :idUvv");
        query.setParameter("idUvv", idUvv);
        return query.getResultList();
    }
    
    //get UVV for a month
    public List<Uvv> getUvvForMonthAndPlz(Date month, String plz) {
        Date startMonth = new Date();
        startMonth.setMonth(month.getMonth());
        startMonth.setDate(1);
        startMonth.setYear(month.getYear());
        Date endMonth = new Date();
        if (startMonth.getMonth() == 11) {
            endMonth.setMonth(0);
            endMonth.setYear(startMonth.getYear() + 1);
        } else {
            endMonth.setMonth(month.getMonth() + 1);
            endMonth.setYear(month.getYear());
        }
        Query query = entityManager.createQuery("from Anlagen anlagen where anlagen.start >= :start "
                + "and anlagen.end < :end and anlagen.plz = :plz");
        query.setParameter("start", startMonth);
        query.setParameter("end", endMonth);
        query.setParameter("plz", plz);
        return query.getResultList();
    }
    
    //get UVV for a period    
    @Transactional(readOnly = true) 
    public void loadUvvs(UvvBean uvvBean) {
        System.out.println("getUvvDue");
        Query query = entityManager.createQuery("from Anlagen anlagen, AnlagenStandorte anlagen_standorte, Standorte standorte "
                + " where anlagen.NUvv >= :start "
                + "and anlagen.NUvv <= :end and anlagen_standorte.idAnlagen = anlagen.idAnlagen "
                + "and anlagen_standorte.idStandorte = standorte.idStandorte and standorte.plz like '%" + uvvBean.getPlz() + "%'");
        query.setParameter("start", uvvBean.getUvvVon());
        query.setParameter("end", uvvBean.getUvvBis());
        List<Object[]> objs = query.getResultList();
        
        //Object = [Anlagen, AnlagenStandorte, 
        
        List<Anlagen> uvvAnlagen = new ArrayList<Anlagen>();
        for (Object[] obj: objs) {
            Anlagen anlagen = (Anlagen)obj[0];
            uvvAnlagen.add(anlagen);
            uvvBean.setIdStandort(getIdStandort(anlagen.getIdAnlagen()));
        }
        System.out.println("uvvs = " + uvvAnlagen.size());
        uvvBean.setUvvList(uvvAnlagen);
    }
    
    @Transactional(readOnly = true)
    public int getIdStandort(int idAnlagen) {
        Query query = entityManager.createQuery("from Standorte standorte, AnlagenStandorte anlagenStandorte where standorte.idStandorte = anlagenStandorte.idStandorte and anlagenStandorte.idAnlagen = :idAnlagen");
        query.setParameter("idAnlagen", idAnlagen);
        List<Object[]> standorte = query.getResultList();
        if (standorte.isEmpty()) {
            return 203108;
        } else {
            return ((Standorte) standorte.get(0)[0]).getIdStandorte();
        }
    }    
}
