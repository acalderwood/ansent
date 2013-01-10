/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.dao.impl;

import de.reichel.bean.KundenEdit;
import de.reichel.bean.KundenNew;
import de.reichel.dao.KundenDAO;
import de.reichel.domain.model.Anlagen;
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
    }

    @Transactional(readOnly = false)
    public void updateKunden(KundenEdit backingBean) {
        Query query = entityManager.createQuery("from Kunden kunden where kunden.idKunden = :idKunden");
        query.setParameter("idKunden", backingBean.getIdKunden());
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
        this.entityManager.merge(kunden);
        this.entityManager.persist(kunden);
    }

    @Transactional(readOnly = true)
    public List<Kunden> getSelectableKunden() {
        Query query = entityManager.createQuery("from Kunden k order by k.firmenname");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public Kunden getKunden(Integer kundenId) {
        Query query = entityManager.createQuery("from Kunden k where k.idKunden = :idKunden");
        query.setParameter("idKunden", kundenId);
        log.debug("getKunden for id " + kundenId);
        return (Kunden) query.getResultList().get(0);
    }

    @Transactional(readOnly = true)
    public void loadKunden(KundenEdit backingBean) {
        Query query = entityManager.createQuery("from Kunden kunden where kunden.firmenname = :firmenname");
        query.setParameter("firmenname", backingBean.getFirmenname());
        log.debug("Query to run " + query.toString());
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
//        kunden.setTimestamp(Calendar.getInstance().getTime());
        log.debug("Kunden Loaded!");
    }
}
