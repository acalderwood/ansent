/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.dao.impl;

import de.reichel.bean.BetreiberEdit;
import de.reichel.bean.BetreiberNew;
import de.reichel.dao.BetreiberDAO;
import de.reichel.domain.model.Betreiber;
import de.reichel.domain.model.Kunden;
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
public class BetreiberDAOImpl implements BetreiberDAO {

    private static final Log log = LogFactory.getLog(AnlagenDAOImpl.class);
    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = false)
    public void updateBetreiber(BetreiberEdit backingBean) {
        Query query = entityManager.createQuery("from Betreiber betreiber where betreiber.idBetreiber = :idBetreiber");
        query.setParameter("idBetreiber", backingBean.getIdBetreiber());
        Betreiber betreiber = (Betreiber) query.getResultList().get(0);
        betreiber.setBetreibername(backingBean.getBetreibername());
        betreiber.setAnsprechpartner(backingBean.getAnsprechpartner());
        betreiber.setStrasseNr(backingBean.getStrasseNr());
        betreiber.setPlz(backingBean.getPlz());
        betreiber.setOrt(backingBean.getOrt());
        betreiber.setLand(backingBean.getLand());
        betreiber.setTelefon(backingBean.getTelefon());
        betreiber.setFax(backingBean.getFax());
        betreiber.setEmail(backingBean.getEmail());
        betreiber.setBemerkung(backingBean.getBemerkung());
        betreiber.setInterneNotiz(backingBean.getInterneNotiz());
        betreiber.setTimestamp(Calendar.getInstance().getTime());
        entityManager.merge(betreiber);
    }

    @Transactional(readOnly = false)
    public void addBetreiber(BetreiberNew backingBean) {
        Betreiber betreiber = new Betreiber();
        betreiber.setBetreibername(backingBean.getBetreibername());
        betreiber.setAnsprechpartner(backingBean.getAnsprechpartner());
        betreiber.setStrasseNr(backingBean.getStrasseNr());
        betreiber.setPlz(backingBean.getPlz());
        betreiber.setOrt(backingBean.getOrt());
        betreiber.setLand(backingBean.getLand());
        betreiber.setTelefon(backingBean.getTelefon());
        betreiber.setFax(backingBean.getFax());
        betreiber.setEmail(backingBean.getEmail());
        betreiber.setBemerkung(backingBean.getBemerkung());
        betreiber.setInterneNotiz(backingBean.getInterneNotiz());
        betreiber.setTimestamp(Calendar.getInstance().getTime());
        entityManager.persist(betreiber);
    }

    @Transactional(readOnly = true)
    public List<Object[]> getAllBetreiber() {
        Query query = entityManager.createQuery("from Betreiber betreiber");
        List<Object[]> result = query.getResultList();
        return result;
    }

    @Transactional(readOnly = true)
    public Betreiber getBetreiber(Integer idBetreiber) {
        Query query = entityManager.createQuery("from Betreiber betreiber where betreiber.idBetreiber = :idBetreiber");
        query.setParameter("idBetreiber", idBetreiber);
        return (Betreiber) query.getResultList().get(0);
    }
}
