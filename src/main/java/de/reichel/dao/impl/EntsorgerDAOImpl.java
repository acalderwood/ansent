/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.dao.impl;

import de.reichel.bean.EntsorgerEdit;
import de.reichel.bean.EntsorgerNew;
import de.reichel.dao.EntsorgerDAO;
import de.reichel.domain.model.Betreiber;
import de.reichel.domain.model.Entsorger;
import java.util.Calendar;
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
 * @author Alastair Calderwood
 */
@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "prototype")
public class EntsorgerDAOImpl implements EntsorgerDAO {

    private static final Log log = LogFactory.getLog(EntsorgerDAOImpl.class);
    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = false)
    public void updateEntsorger(EntsorgerEdit backingBean) {
        Query query = entityManager.createQuery("from Entsorger entsorger where entsorger.idEntsorger = :idEntsorger");
        query.setParameter("idEntsorger", backingBean.getIdEntsorger());
        Entsorger entsorger = (Entsorger) query.getResultList().get(0);
        entsorger.setAnsprechpartner(backingBean.getAnsprechPartner());
        entsorger.setBemerkung(backingBean.getBemerkung());
        entsorger.setEmail(backingBean.getEmail());
        entsorger.setEmailGsm(backingBean.getEmailGsm());
        entsorger.setEntsorgerName(backingBean.getEntsorgerName());
        entsorger.setFax(backingBean.getFax());
        entsorger.setFaxGsm(backingBean.getFaxGsm());
        entsorger.setIdEntsorger(backingBean.getIdEntsorger());
        entsorger.setOrt(backingBean.getOrt());
        entsorger.setPlz(backingBean.getPlz());
        entsorger.setStrasseNr(backingBean.getStrasseNr());
        entsorger.setTelefon(backingBean.getTelefon());
        entsorger.setTimestamp(Calendar.getInstance().getTime());
        entityManager.merge(entsorger);
    }

    @Transactional(readOnly = false)
    public void addEntsorger(EntsorgerNew backingBean) {
        Entsorger entsorger = new Entsorger();
        entsorger.setAnsprechpartner(backingBean.getAnsprechPartner());
        entsorger.setBemerkung(backingBean.getBemerkung());
        entsorger.setEmail(backingBean.getEmail());
        entsorger.setEmailGsm(backingBean.getEmailGsm());
        entsorger.setEntsorgerName(backingBean.getEntsorgerName());
        entsorger.setFax(backingBean.getFax());
        entsorger.setFaxGsm(backingBean.getFaxGsm());
        entsorger.setIdEntsorger(backingBean.getIdEntsorger());
        entsorger.setOrt(backingBean.getOrt());
        entsorger.setPlz(backingBean.getPlz());
        entsorger.setStrasseNr(backingBean.getStrasseNr());
        entsorger.setTelefon(backingBean.getTelefon());
        entsorger.setTimestamp(Calendar.getInstance().getTime());
        entityManager.persist(entsorger);
        backingBean.setIdEntsorger(entsorger.getIdEntsorger());
    }

    @Transactional(readOnly = true)
    public List<Entsorger> getAllEntsorger() {
        Query query = entityManager.createQuery("from Entsorger entsorger order by entsorger.entsorgerName");
        List<Entsorger> result = query.getResultList();
        return result;
    }

    @Transactional(readOnly = true)
    public Entsorger getEntsorger(Integer idEntsorger) {
        Query query = entityManager.createQuery("from Entsorger entsorger where entsorger.idEntsorger = :idEntsorger");
        query.setParameter("idEntsorger", idEntsorger);
        return (Entsorger) query.getResultList().get(0);
    }

    public void loadEntsorger(EntsorgerEdit backingBean) {
        log.debug("Inside loadEntsorger method");
        Query query = entityManager.createQuery("from Entsorger entsorger where entsorger.idEntsorger = :idEntsorger");
        query.setParameter("idEntsorger", backingBean.getIdEntsorger());
        log.debug("Query to run " + query.toString());
        Entsorger entsorger = (Entsorger) query.getResultList().get(0);
        backingBean.setAnsprechPartner(entsorger.getAnsprechpartner());
        backingBean.setBemerkung(entsorger.getBemerkung());
        backingBean.setEmail(entsorger.getEmail());
        backingBean.setEmailGsm(entsorger.getEmailGsm());
        backingBean.setEntsorgerName(entsorger.getEntsorgerName());
        log.debug("Entsorger name loaded: "+backingBean.getEntsorgerName());
        backingBean.setFax(entsorger.getFax());
        backingBean.setFaxGsm(entsorger.getFaxGsm());
        backingBean.setIdEntsorger(entsorger.getIdEntsorger());
        backingBean.setOrt(entsorger.getOrt());
        backingBean.setPlz(entsorger.getPlz());
        backingBean.setStrasseNr(entsorger.getStrasseNr());
        backingBean.setTelefon(entsorger.getTelefon());
        backingBean.setTimestamp(Calendar.getInstance().getTime());
    }
}