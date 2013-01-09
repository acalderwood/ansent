/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.dao.impl;

import de.reichel.bean.AnlageEdit;
import de.reichel.bean.AnlageNew;
import de.reichel.bean.AnlageSearch;
import de.reichel.dao.AnlagenDAO;
import de.reichel.domain.model.Anlagen;
import de.reichel.domain.model.AnlagenArt;
import de.reichel.domain.model.AnlagenHersteller;
import de.reichel.domain.model.AnlagenStandorte;
import de.reichel.domain.model.Betreiber;
import de.reichel.domain.model.Kunden;
import de.reichel.domain.model.Standorte;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class AnlagenDAOImpl implements AnlagenDAO {

    private static final Log log = LogFactory.getLog(AnlagenDAOImpl.class);
    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = false)
    public void updateAnlagen(AnlageEdit backingBean) {
        Query query = entityManager.createQuery("from Anlagen anlagen where anlagen.idAnlagen = :idAnlagen");
        query.setParameter("idAnlagen", backingBean.getIdAnlagen());
        Anlagen anlagen = (Anlagen) query.getResultList().get(0);
        anlagen.setInterneNr(backingBean.getInterneNr());
        anlagen.setFabrikationsnummer(backingBean.getFabrikationsnr());
        anlagen.setIdAnlagenHersteller(backingBean.getIdHersteller());
        this.entityManager.merge(anlagen);
//        this.entityManager.persist(anlagen);
        //@TODO: finish fields
    }
    
    @Transactional(readOnly = true)
    public long getNumberAnlagen(AnlageSearch anlageSearch) {
        Query query = entityManager.createQuery("select count(*) from Anlagen anlagen where anlagen.idAnlagen = :idAnlagen");
        query.setParameter("idAnlagen", anlageSearch.getIdAnlagen());
        return (Long)query.getSingleResult();
    }

    @Transactional(readOnly = false)
    public void addAnlagen(AnlageNew backingBean) {
        Anlagen anlagen = new Anlagen();
        log.debug("New Anlagen is created");
        
        anlagen.setBemerkung(backingBean.getBemerkung());
        log.debug("Bemerkung is set: "+backingBean.getBemerkung());
        
        try{
            Calendar cal = new GregorianCalendar(Integer.parseInt(backingBean.getBaujahr()), 1, 6);
            anlagen.setBaujahr(cal.getTime());
            log.debug("Baujahr is set");
        }catch (Exception e) {
            log.debug("Date was empty for Baujahr "+e.getMessage());
        }
        
        
        anlagen.setFabrikationsnummer(backingBean.getFabrikationsnr());
        log.debug("Fabriknum is set");
        anlagen.setIdAnlagenArt(backingBean.getIdArt());
        log.debug("ArtId is set");
        anlagen.setIdAnlagenHersteller(backingBean.getIdHersteller());
        log.debug("Hersteller is set");
        anlagen.setInterneNotiz(backingBean.getInterneNotiz());
        log.debug("InterneNotiz is set");
        anlagen.setInterneNr(backingBean.getInterneNr());
        log.debug("InterneNum is set");

        //@TODO: validate and accept different formats
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        if (backingBean.getNaechsteUVV() != null && !backingBean.getNaechsteUVV().equals("")) {
            try {
                Date nUvv = format.parse(backingBean.getNaechsteUVV());
                anlagen.setNUvv(nUvv);
                log.debug("NUvv is set");
            } catch (Exception e) {
                //already caught by validator?
            }
        }

        if (backingBean.getNaechsteWartung() != null && !backingBean.getNaechsteWartung().equals("")) {
            try {
                Date nWart = format.parse(backingBean.getNaechsteWartung());
                anlagen.setNWart(nWart);
                log.debug("NWart is set");
            } catch (ParseException e) {
                //already caught by validator?
            }
        }

        anlagen.setTyp(backingBean.getTyp());
        log.debug("Typ is set");

        this.entityManager.persist(anlagen);

        System.out.println(anlagen.getIdAnlagen());

        AnlagenStandorte anlagenStandort = new AnlagenStandorte();
        anlagenStandort.setIdAnlagen(anlagen.getIdAnlagen());
        anlagenStandort.setIdStandorte(backingBean.getIdStandort());
        anlagenStandort.setIdBetreiber(backingBean.getIdBetreiber());
        anlagenStandort.setIdKunden(backingBean.getIdKunden());

        this.entityManager.persist(anlagenStandort);
    }

    @Transactional(readOnly = true)
    public List<Object[]> getAllAnlagen() {
        Query query = entityManager.createQuery("from Anlagen anlagen, AnlagenArt anlagenArt, AnlagenHersteller anlagenHersteller where anlagen.idAnlagenArt = anlagenArt.idAnlagenArt and anlagen.idAnlagenHersteller = anlagenHersteller.idAnlagenHersteller");
        List<Object[]> result = query.getResultList();
        return result;
    }

    @Transactional(readOnly = true)
    public Object[] getAnlagen(String interneNr) {
        Query query = entityManager.createQuery("from Anlagen anlagen, AnlagenArt anlagenArt, AnlagenHersteller anlagenHersteller where anlagen.idAnlagenArt = anlagenArt.idAnlagenArt and anlagen.idAnlagenHersteller = anlagenHersteller.idAnlagenHersteller and anlagen.interneNr = :interneNr");
        query.setParameter("interneNr", interneNr);
        List<Object[]> result = query.getResultList();
        return result.get(0);
    }

    @Transactional(readOnly = true)
    public List<Object[]> getAnlagen(AnlageSearch backingBean) {

        int stmtNo = 0;

        String queryStr = "from Anlagen anlagen, AnlagenArt anlagenArt, AnlagenHersteller anlagenHersteller where anlagen.idAnlagenArt = anlagenArt.idAnlagenArt and anlagen.idAnlagenHersteller = anlagenHersteller.idAnlagenHersteller";

        List<String> tableVars = new ArrayList<String>();
        List<String> paramVars = new ArrayList<String>();
        List<Object> paramVals = new ArrayList<Object>();
        List<String> junctionVals = new ArrayList<String>();

        if (backingBean.getInterneNr() != null && !backingBean.getInterneNr().equals("")) {
            tableVars.add("anlagen");
            paramVars.add("interneNr");
            paramVals.add("%" + backingBean.getInterneNr() + "%");
            junctionVals.add(")");
        }

        stmtNo = 0;
        if (backingBean.getSelectedArt() != null) {
            for (Integer idAnlagenArt : backingBean.getSelectedArt()) {
                tableVars.add("anlagenArt");
                paramVars.add("idAnlagenArt");
                paramVals.add(idAnlagenArt);
                if (stmtNo != backingBean.getSelectedArt().size() - 1) {
                    junctionVals.add("or ");
                } else {
                    junctionVals.add(")");
                }
                stmtNo++;
            }
        }

        stmtNo = 0;
        if (backingBean.getSelectedHersteller() != null) {
            for (Integer idAnlagenHersteller : backingBean.getSelectedHersteller()) {
                tableVars.add("anlagenHersteller");
                paramVars.add("idAnlagenHersteller");
                paramVals.add(idAnlagenHersteller);
                if (stmtNo != backingBean.getSelectedHersteller().size() - 1) {
                    junctionVals.add("or ");
                } else {
                    junctionVals.add(")");
                }
                stmtNo++;
            }
        }

        if (tableVars.size() > 0) {
            queryStr += " and ( ";
        }

        for (int i = 0; i < tableVars.size(); i++) {
            String clause = "";

            if (i > 0 && junctionVals.get(i - 1).equals(")")) {
                clause += " and ( ";
            }

            clause += tableVars.get(i) + "." + paramVars.get(i) + " like :" + paramVars.get(i) + i + " " + junctionVals.get(i);
            queryStr += clause;
        }

        //log.debug("queryStr = " + queryStr);
        System.out.println("queryStr = " + queryStr);

        Query query = entityManager.createQuery(queryStr);

        for (int i = 0; i < paramVals.size(); i++) {
            query.setParameter(paramVars.get(i) + i, paramVals.get(i));
        }

        log.debug("query = " + query.toString());
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<AnlagenArt> getSelectableArt() {
        Query query = entityManager.createQuery("from AnlagenArt a order by a.art");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<AnlagenHersteller> getSelectableHersteller() {
        Query query = entityManager.createQuery("from AnlagenHersteller a order by a.hersteller");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Standorte> getSelectableStandort() {
        Query query = entityManager.createQuery("from Standorte s order by s.standortname");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Betreiber> getSelectableBetreiber() {
        Query query = entityManager.createQuery("from Betreiber b order by b.betreibername");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Kunden> getSelectableKunden() {
        Query query = entityManager.createQuery("from Kunden k order by k.firmenname");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public Object[] getAnlagen(int idAnlagen) {
        Query query = entityManager.createQuery("from Anlagen anlagen, AnlagenArt anlagenArt, AnlagenHersteller anlagenHersteller where anlagen.idAnlagenArt = anlagenArt.idAnlagenArt and anlagen.idAnlagenHersteller = anlagenHersteller.idAnlagenHersteller and anlagen.idAnlagen = :idAnlagen");
        query.setParameter("idAnlagen", idAnlagen);
        return (Object[]) query.getResultList().get(0);
    }

    @Transactional(readOnly = true)
    public Standorte getStandort(int idAnlagen) {
        Query query = entityManager.createQuery("from Standorte standorte, AnlagenStandorte anlagenStandorte where standorte.idStandorte = anlagenStandorte.idStandorte and anlagenStandorte.idAnlagen = :idAnlagen");
        query.setParameter("idAnlagen", idAnlagen);
        return (Standorte) ((Object[]) query.getResultList().get(0))[0];
    }

    @Transactional(readOnly = true)
    public Betreiber getBetreiber(int idAnlagen) {
        Query query = entityManager.createQuery("from Betreiber betreiber, AnlagenStandorte anlagenStandorte where betreiber.idBetreiber = anlagenStandorte.idBetreiber and anlagenStandorte.idAnlagen = :idAnlagen");
        query.setParameter("idAnlagen", idAnlagen);
        return (Betreiber) ((Object[]) query.getResultList().get(0))[0];
    }

    @Transactional(readOnly = true)
    public Kunden getKunden(int idAnlagen) {
        Query query = entityManager.createQuery("from Kunden kunden, AnlagenStandorte anlagenStandorte where kunden.idKunden = anlagenStandorte.idKunden and anlagenStandorte.idAnlagen = :idAnlagen");
        query.setParameter("idAnlagen", idAnlagen);
        return (Kunden) ((Object[]) query.getResultList().get(0))[0];
    }
}