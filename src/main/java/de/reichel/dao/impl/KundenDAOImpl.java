/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.dao.impl;

import de.reichel.bean.KundenEdit;
import de.reichel.bean.KundenNew;
import de.reichel.dao.KundenDAO;
import de.reichel.domain.model.Kunden;
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
public class KundenDAOImpl implements KundenDAO {

    private static final Log log = LogFactory.getLog(KundenDAOImpl.class);
    
    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = false)
    public void addKunden(KundenNew backingBean) {
        Kunden kunden = new Kunden();
        kunden.setAnsprechpartner(backingBean.getAnsprechpartner());
        kunden.setBemerkung(backingBean.getBemerkung());
        kunden.setBuchungskreis(backingBean.getBuchungskreis());
        kunden.setEmail(backingBean.getEmail());
        kunden.setFax(backingBean.getFax());
        kunden.setFirmenname(backingBean.getFirmenname());
        kunden.setInterneNotiz(backingBean.getInterneNotiz());
        kunden.setLand(backingBean.getLand());
        kunden.setOrt(backingBean.getOrt());
        kunden.setPlz(backingBean.getPlz());
        kunden.setSteuernummer(backingBean.getSteuerNr());
        kunden.setStrasseNr(backingBean.getStrasseNr());
        kunden.setTelefon(backingBean.getTelefon());
        kunden.setTimestamp(Calendar.getInstance().getTime());
        entityManager.persist(kunden);
        backingBean.setIdKunden(kunden.getIdKunden());
    }

    @Transactional(readOnly = false)
    public void updateKunden(KundenEdit backingBean) {
        Query query = entityManager.createQuery("from Kunden kunden where kunden.idKunden = :idKunden");
        query.setParameter("idKunden", backingBean.getIdKunden());
        log.debug("Inside UpdateKunden method, starting to set up the attributes");
        Kunden kunden = (Kunden) query.getResultList().get(0);
        kunden.setAnsprechpartner(backingBean.getAnsprechpartner());
        kunden.setBemerkung(backingBean.getBemerkung());
        kunden.setBuchungskreis(backingBean.getBuchungskreis());
        kunden.setEmail(backingBean.getEmail());
        kunden.setFax(backingBean.getFax());
        kunden.setFirmenname(backingBean.getFirmenname());
        kunden.setInterneNotiz(backingBean.getInterneNotiz());
        kunden.setLand(backingBean.getLand());
        kunden.setOrt(backingBean.getOrt());
        kunden.setPlz(backingBean.getPlz());
        kunden.setSteuernummer(backingBean.getSteuerNr());
        kunden.setStrasseNr(backingBean.getStrasseNr());
        kunden.setTelefon(backingBean.getTelefon());
        kunden.setTimestamp(Calendar.getInstance().getTime());
        entityManager.merge(kunden);
        log.debug("Kunden successfully updated");
        //return ("index");
    }

    @Transactional(readOnly = true)
    public List<Kunden> getSelectableKunden() {
        Query query = entityManager.createQuery("from Kunden kunden order by kunden.firmenname");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public Kunden getKunden(Integer kundenId) {
        Query query = entityManager.createQuery("from Kunden kunden where kunden.idKunden = :idKunden");
        query.setParameter("idKunden", kundenId);
        log.debug("getKunden for id " + kundenId);
        return (Kunden) query.getResultList().get(0);
    }

    @Transactional(readOnly = true)
    public void loadKunden(KundenEdit backingBean) {
        Query query = entityManager.createQuery("from Kunden kunden where kunden.idKunden = :idKunden");
        query.setParameter("idKunden", backingBean.getIdKunden());
        log.debug("Query to run " + query.toString());
        Kunden kunden = (Kunden) query.getResultList().get(0);
        backingBean.setFirmenname(kunden.getFirmenname());
        backingBean.setAnsprechpartner(kunden.getAnsprechpartner());
        backingBean.setBemerkung(kunden.getBemerkung());
        backingBean.setBuchungskreis(kunden.getBuchungskreis());
        backingBean.setEmail(kunden.getEmail());
        backingBean.setFax(kunden.getFax());
        backingBean.setInterneNotiz(kunden.getInterneNotiz());
        backingBean.setLand(kunden.getLand());
        backingBean.setOrt(kunden.getOrt());
        backingBean.setPlz(kunden.getPlz());
        backingBean.setStrasseNr(kunden.getStrasseNr());
        backingBean.setTelefon(kunden.getTelefon());
        backingBean.setSteuerNr(kunden.getSteuernummer());
        kunden.setTimestamp(Calendar.getInstance().getTime());
        log.debug("Kunden Loaded!");
    }

    public void newKundenInit(KundenNew backingBean) {
        log.debug("Initializing new Kunden Bean");
        backingBean.setEmail(null);
        backingBean.setFirmenname(null);
        backingBean.setInterneNotiz(null);
        backingBean.setAnsprechpartner(null);
        backingBean.setStrasseNr(null);
        backingBean.setBemerkung(null);
        backingBean.setBuchungskreis(null);
        backingBean.setFax(null);
        backingBean.setOrt(null);
        backingBean.setTelefon(null);
        backingBean.setSteuerNr(null);
        backingBean.setPlz(null);
        backingBean.setLand(null);
        backingBean.setIdKunden(null);
        log.debug("New Kunden Bean is now empty!");
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
