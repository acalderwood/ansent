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
        log.debug("Anlagen successfully restored");
        anlagen.setInterneNr(backingBean.getInterneNr());
        log.debug("Getting Internal Number");
        anlagen.setFabrikationsnummer(backingBean.getFabrikationsnr());
        log.debug("Getting Fabrikation Number");
        anlagen.setIdAnlagenHersteller(backingBean.getIdHersteller());
        log.debug("Getting Hersteller ID");
        
        this.entityManager.merge(anlagen);
//        this.entityManager.persist(anlagen);
        //@TODO: finish fields
    }

    @Transactional(readOnly = true)
    public long getNumberAnlagen(AnlageSearch anlageSearch) {
        Query query = entityManager.createQuery("select count(*) from Anlagen anlagen where anlagen.idAnlagen = :idAnlagen");
        query.setParameter("idAnlagen", anlageSearch.getIdAnlagen());
        return (Long) query.getSingleResult();
    }

    @Transactional(readOnly = false)
    public void addAnlagen(AnlageNew backingBean) {
        Anlagen anlagen = new Anlagen();
        log.debug("New Anlagen is created");

        anlagen.setBemerkung(backingBean.getBemerkung());
        log.debug("Bemerkung is set: " + backingBean.getBemerkung());

        try {
            Date cal;
            if (backingBean.getBaujahr() == null) {
                Calendar calen = new GregorianCalendar(0, 0, 0, 0, 0, 0);
                cal = calen.getTime();
            } else {
                cal = backingBean.getBaujahr();
            }
            log.debug("Baujahr is set");
            anlagen.setBaujahr(cal);
        } catch (Exception e) {
            log.debug("Date was empty for Baujahr " + e.getMessage());
        }


        anlagen.setFabrikationsnummer(backingBean.getFabrikationsnr());
        log.debug("Fabriknum is set: " + backingBean.getFabrikationsnr());
        anlagen.setIdAnlagenArt(backingBean.getIdArt());
        log.debug("ArtId is set");
        anlagen.setIdAnlagenHersteller(backingBean.getIdHersteller());
        log.debug("Hersteller is set");
        anlagen.setInterneNotiz(backingBean.getInterneNotiz());
        log.debug("InterneNotiz is set");
        anlagen.setInterneNr(backingBean.getInterneNr());
        log.debug("InterneNum is set");

        //@TODO: validate and accept different formats
        //DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date date;
            if (backingBean.getNaechsteUVV() == null) {
                Calendar calen = new GregorianCalendar(0, 0, 0, 0, 0, 0);
                date = calen.getTime();
            } else {
                date = backingBean.getNaechsteUVV();
            }
            log.debug("NUvv is set");
            anlagen.setNUvv(date);
        } catch (Exception e) {
            log.debug("Date was empty for NUvv " + e.getMessage());
        }

        try {
            Date date;
            if (backingBean.getNaechsteWartung() == null) {
                Calendar calen = new GregorianCalendar(0, 0, 0, 0, 0, 0);
                date = calen.getTime();
            } else {
                date = backingBean.getNaechsteWartung();
            }
            log.debug("NUvv is set");
            anlagen.setNWart(date);
        } catch (Exception e) {
            log.debug("Date was empty for NUvv " + e.getMessage());
        }


        anlagen.setTyp(backingBean.getTyp());
        log.debug("Typ is set");

        this.entityManager.persist(anlagen);
        backingBean.setIdAnlagen(anlagen.getIdAnlagen());

        log.debug("Persisted Anlagen with ID " + anlagen.getIdAnlagen());

        AnlagenStandorte anlagenStandort = new AnlagenStandorte();
        anlagenStandort.setIdAnlagen(anlagen.getIdAnlagen());
        anlagenStandort.setIdStandorte(backingBean.getIdStandort());
        anlagenStandort.setIdBetreiber(backingBean.getIdBetreiber());
        anlagenStandort.setIdKunden(backingBean.getIdKunden());

        this.entityManager.persist(anlagenStandort);

        log.debug("Persisted AnlagenStandorte with ID " + anlagenStandort.getIdAnlagenStandorte());
    }

    @Transactional(readOnly = true)
    public List<Anlagen> getAllAnlagen() {
        log.debug("Inside get All Anlagen");
        //Changing the query below to reflect all the Anlage
        //Query query = entityManager.createQuery("from Anlagen anlagen, AnlagenArt anlagenArt, AnlagenHersteller anlagenHersteller where anlagen.idAnlagenArt = anlagenArt.idAnlagenArt and anlagen.idAnlagenHersteller = anlagenHersteller.idAnlagenHersteller order by anlagen.interneNr");
        Query query = entityManager.createQuery("from Anlagen anlagen where anlagen.interneNr is not null order by anlagen.interneNr");
        query.setHint("org.hibernate.cacheable", true);
        //List<Object[]> result = query.getResultList();
        return query.getResultList();
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
        Query query = entityManager.createQuery("from Anlagen anlagen, AnlagenArt anlagenArt, AnlagenHersteller anlagenHersteller where anlagen.idAnlagenArt = anlagenArt.idAnlagenArt and anlagen.idAnlagenHersteller = anlagenHersteller.idAnlagenHersteller and anlagen.idAnlagen = :idAnlagen order by anlagen.interneNr");
        query.setParameter("idAnlagen", idAnlagen);
        return (Object[]) query.getResultList().get(0);
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

    @Transactional(readOnly = true)
    public int getIdBetreiber(int idAnlagen) {
        Query query = entityManager.createQuery("from Betreiber betreiber, AnlagenStandorte anlagenStandorte where betreiber.idBetreiber = anlagenStandorte.idBetreiber and anlagenStandorte.idAnlagen = :idAnlagen");
        query.setParameter("idAnlagen", idAnlagen);
        List<Object[]> betreiber = query.getResultList();
        if (betreiber.isEmpty()) {
            return 203;
        } else {
            return ((Betreiber) betreiber.get(0)[0]).getIdBetreiber();
        }
    }

    @Transactional(readOnly = true)
    public int getIdKunden(int idAnlagen) {
        Query query = entityManager.createQuery("from Kunden kunden, AnlagenStandorte anlagenStandorte where kunden.idKunden = anlagenStandorte.idKunden and anlagenStandorte.idAnlagen = :idAnlagen");
        query.setParameter("idAnlagen", idAnlagen);
        List<Object[]> kunden = query.getResultList();
        if (kunden.isEmpty()) {
            return -1;
        } else {
            return ((Kunden) kunden.get(0)[0]).getIdKunden();
        }
    }

    @Transactional(readOnly = true)
    public void loadAnlagen(AnlageEdit backingBean) {
        Query query = entityManager.createQuery("from Anlagen anlagen where anlagen.idAnlagen = :idAnlagen");
        query.setParameter("idAnlagen", backingBean.getIdAnlagen());
        log.debug("Query to run: " + query.toString());
        Anlagen anlagen = (Anlagen) query.getResultList().get(0);
        log.debug("Anlage Id :"+ backingBean.getIdAnlagen());
        backingBean.setInterneNr(anlagen.getInterneNr());
        log.debug("Anlage Int Num :"+ anlagen.getInterneNr());
        backingBean.setIdAnlagen(anlagen.getIdAnlagen());
        backingBean.setIdArt(anlagen.getIdAnlagenArt());
        log.debug("Art id :"+anlagen.getIdAnlagenHersteller());
        backingBean.setIdHersteller(anlagen.getIdAnlagenHersteller());
        log.debug("Hersteller id :"+anlagen.getIdAnlagenHersteller());
        backingBean.setBemerkung(anlagen.getBemerkung());
        log.debug("Bemerkung :"+ anlagen.getBemerkung());
        backingBean.setTyp(anlagen.getTyp());
        backingBean.setFabrikationsnr(anlagen.getFabrikationsnummer());
        backingBean.setBaujahr(anlagen.getBaujahr());
        backingBean.setInterneNotiz(anlagen.getInterneNotiz());
        backingBean.setNaechsteUVV(anlagen.getNUvv());
        backingBean.setNaechsteWartung(anlagen.getNWart());

        backingBean.setIdKunden(getIdKunden(backingBean.getIdAnlagen()));
        backingBean.setIdBetreiber(getIdBetreiber(backingBean.getIdAnlagen()));
        backingBean.setIdStandort(getIdStandort(backingBean.getIdAnlagen()));
    }

    public void loadAnlagen(AnlageSearch backingBean) {
        log.debug("Inside load Anlagen for Standort");
        log.debug("Standort id: "+backingBean.getIdStandort());
        
        Query query = entityManager.createQuery("from AnlagenStandorte anlagenStandorte where anlagenStandorte.idStandorte = :idStandort");
        log.debug("Query: "+query.toString());
        query.setParameter("idStandort", backingBean.getIdStandort());
        
        log.debug("Standort id: "+backingBean.getIdStandort());
        
        throw new UnsupportedOperationException("Not supported yet.");
    }
}