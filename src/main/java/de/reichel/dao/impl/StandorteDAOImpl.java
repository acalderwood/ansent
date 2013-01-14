/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.dao.impl;

import de.reichel.bean.StandorteEdit;
import de.reichel.bean.StandorteNew;
import de.reichel.dao.StandorteDAO;
import de.reichel.domain.model.Standorte;
import java.util.Calendar;
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
 * @author Alastair Calderwood
 */
@Repository
public class StandorteDAOImpl implements StandorteDAO {

    private static final Log log = LogFactory.getLog(AnlagenDAOImpl.class);
    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = false)
    public void updateStandorte(StandorteEdit backingBean) {
        Query query = entityManager.createQuery("from Standorte betreiber where betreiber.idStandorte = :idStandorte");
        query.setParameter("idStandorte", backingBean.getIdStandorte());
        Standorte standorte = (Standorte) query.getResultList().get(0);
        standorte.setAnsprechpartner(backingBean.getAnsprechpartner());
        standorte.setIdEntsorger(backingBean.getIdEntsorger());
        standorte.setBemerkung(backingBean.getBemerkung());
        standorte.setBesonderheiten(backingBean.getBesonderheiten());
        standorte.setEmail(backingBean.getEmail());
        standorte.setFax(backingBean.getFax());
        standorte.setIdBetreiber(backingBean.getIdBetreiber());
        standorte.setInterneNotiz(backingBean.getInterneNotiz());
        standorte.setAnfahrtspauschaleBetrag(backingBean.getAnfahrtspauschaleBetrag());
        standorte.setKilometerpauschaleBetrag(backingBean.getKilometerpauschaleBetrag());
        standorte.setLand(backingBean.getLand());
        standorte.setOrt(backingBean.getOrt());
        standorte.setPlz(backingBean.getPlz());
        standorte.setStandortname(backingBean.getStandortname());
        standorte.setStrasseNr(backingBean.getStrasseNr());
        standorte.setTelefon(backingBean.getTelefon());
        standorte.setTimestamp(Calendar.getInstance().getTime());
        entityManager.merge(standorte);
    }

    @Transactional(readOnly = false)
    public void addStandorte(StandorteNew backingBean) {
        Standorte standorte = new Standorte();
        standorte.setAnsprechpartner(backingBean.getAnsprechpartner());
        standorte.setIdEntsorger(backingBean.getIdEntsorger());
        standorte.setBemerkung(backingBean.getBemerkung());
        standorte.setBesonderheiten(backingBean.getBesonderheiten());
        standorte.setEmail(backingBean.getEmail());
        standorte.setFax(backingBean.getFax());
        standorte.setIdBetreiber(backingBean.getIdBetreiber());
        standorte.setInterneNotiz(backingBean.getInterneNotiz());
        standorte.setAnfahrtspauschaleBetrag(backingBean.getAnfahrtspauschaleBetrag());
        standorte.setKilometerpauschaleBetrag(backingBean.getKilometerpauschaleBetrag());
        standorte.setLand(backingBean.getLand());
        standorte.setOrt(backingBean.getOrt());
        standorte.setPlz(backingBean.getPlz());
        standorte.setStandortname(backingBean.getStandortname());
        standorte.setStrasseNr(backingBean.getStrasseNr());
        standorte.setTelefon(backingBean.getTelefon());
        standorte.setTimestamp(Calendar.getInstance().getTime());
        entityManager.persist(standorte);
    }

    @Transactional(readOnly = true)
    public List<Standorte> getAllStandorte() {
        Query query = entityManager.createQuery("from Standorte standorte");
        List<Standorte> result = query.getResultList();
        return result;
    }

    @Transactional(readOnly = true)
    public Standorte getStandorte(Integer idStandorte) {
        Query query = entityManager.createQuery("from Standorte standorte where standorte.idStandorte = :idStandorte");
        query.setParameter("idStandorte", idStandorte);
        return (Standorte) query.getResultList().get(0);
    }

    public void loadStandorte(StandorteEdit backingBean) {
        Query query = entityManager.createQuery("from Standorte standorte where standorte.idStandorte = :idStandorte");
        query.setParameter("idStandorte", backingBean.getIdStandorte());
        log.debug("Query to run " + query.toString());
        Standorte standorte = (Standorte) query.getResultList().get(0);
        backingBean.setStandortname(standorte.getStandortname());
        log.debug("Standorte Name: "+backingBean.getStandortname());
        backingBean.setStrasseNr(standorte.getStrasseNr());
        backingBean.setPlz(standorte.getPlz());
        backingBean.setOrt(standorte.getOrt());
        backingBean.setLand(standorte.getLand());
        backingBean.setTelefon(standorte.getTelefon());
        backingBean.setFax(standorte.getFax());
        backingBean.setBesonderheiten(standorte.getBesonderheiten());
        backingBean.setBemerkung(standorte.getBemerkung());
        backingBean.setInterneNotiz(standorte.getInterneNotiz());
        backingBean.setAnfahrtspauschaleBetrag(standorte.getAnfahrtspauschaleBetrag());
        backingBean.setKilometerpauschaleBetrag(standorte.getKilometerpauschaleBetrag());
    }
}
