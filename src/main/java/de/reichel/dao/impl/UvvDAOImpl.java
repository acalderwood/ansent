/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.dao.impl;

import de.reichel.dao.UvvDAO;
import de.reichel.domain.model.Uvv;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alastair
 */
@Repository
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
}
