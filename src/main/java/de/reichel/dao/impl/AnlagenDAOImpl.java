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

        Anlagen anlagen = (Anlagen)query.getResultList().get(0);

        anlagen.setBemerkung(backingBean.getBemerkung());
        log.debug("Bemerkung is set: " + backingBean.getBemerkung());

        try {
            anlagen.setBaujahr(backingBean.getBaujahr());
        } catch (Exception e) {
            log.debug("Date was empty for Baujahr " + e.getMessage());
        }


        anlagen.setFabrikationsnummer(backingBean.getFabrikationsnr());
        anlagen.setIdAnlagenArt(backingBean.getIdArt());
        anlagen.setIdAnlagenHersteller(backingBean.getIdHersteller());
        anlagen.setInterneNotiz(backingBean.getInterneNotiz());
        anlagen.setInterneNr(backingBean.getInterneNr());

        //@TODO: validate and accept different formats
        //DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            anlagen.setNUvv(backingBean.getNaechsteUVV());
        } catch (Exception e) {
            log.debug("Date was empty for NUvv " + e.getMessage());
        }

        try {
            anlagen.setNWart(backingBean.getNaechsteWartung());
        } catch (Exception e) {
            log.debug("Date was empty for NUvv " + e.getMessage());
        }


        anlagen.setTyp(backingBean.getTyp());
        log.debug("Typ is set");
        
        this.entityManager.merge(anlagen);
        
        log.debug("Persisted Anlagen with ID " + anlagen.getIdAnlagen());

        AnlagenStandorte anlagenStandort = new AnlagenStandorte();
        anlagenStandort.setIdAnlagen(anlagen.getIdAnlagen());
        anlagenStandort.setIdStandorte(backingBean.getIdStandort());
        anlagenStandort.setIdBetreiber(backingBean.getIdBetreiber());
        anlagenStandort.setIdKunden(backingBean.getIdKunden());

        this.entityManager.merge(anlagenStandort);

        log.debug("Persisted AnlagenStandorte with ID " + anlagenStandort.getIdAnlagenStandorte());        

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
            anlagen.setBaujahr(backingBean.getBaujahr());
        } catch (Exception e) {
            log.debug("Date was empty for Baujahr " + e.getMessage());
        }


        anlagen.setFabrikationsnummer(backingBean.getFabrikationsnr());
        anlagen.setIdAnlagenArt(backingBean.getIdArt());
        anlagen.setIdAnlagenHersteller(backingBean.getIdHersteller());
        anlagen.setInterneNotiz(backingBean.getInterneNotiz());
        anlagen.setInterneNr(backingBean.getInterneNr());

        //@TODO: validate and accept different formats
        //DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            anlagen.setNUvv(backingBean.getNaechsteUVV());
        } catch (Exception e) {
            log.debug("Date was empty for NUvv " + e.getMessage());
        }

        try {
            anlagen.setNWart(backingBean.getNaechsteWartung());
        } catch (Exception e) {
            log.debug("Date was empty for NUvv " + e.getMessage());
        }

        anlagen.setTyp(backingBean.getTyp());

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
        Query query = entityManager.createQuery("select anlagen.idAnlagen, anlagen.interneNr from Anlagen anlagen where anlagen.interneNr is not null order by anlagen.interneNr");
        query.setHint("org.hibernate.cacheable", true);
        List<Object[]> queryResult = query.getResultList();
        List<Anlagen> operationResult = new ArrayList<Anlagen>();
        for (Object[] obj: queryResult) {
            Anlagen anlagen = new Anlagen();
            anlagen.setIdAnlagen((Integer)obj[0]);
            anlagen.setInterneNr((String)obj[1]);
            operationResult.add(anlagen);
        }
        return operationResult;
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
    
    @Transactional(readOnly = true)
    public void searchAnlagen(AnlageSearch anlageSearch) {

        String searchQuery = "from Anlagen anlagen, AnlagenArt anlagenArt, AnlagenHersteller anlagenHersteller, AnlagenStandorte anlagenStandorte, Kunden kunden, Standorte standorte, Betreiber betreiber where anlagen.interneNr like '%" + anlageSearch.getSearchAnlagenInterneNr() 
                + "%' and anlagen.fabrikationsnummer like '%" + anlageSearch.getSearchAnlagenFabrikationsNr() 
                + "%' and anlagen.typ like '%" + anlageSearch.getSearchAnlagenTyp()
                + "%' and anlagen.bemerkung like '%" + anlageSearch.getSearchAnlagenBemerkung()
                + "%' and anlagen.baujahr like '%" + anlageSearch.getSearchAnlagenBaujahr()
                + "%' and anlagenArt.art like '%" + anlageSearch.getSearchAnlagenArt()
                + "%' and anlagenHersteller.hersteller like '%" + anlageSearch.getSearchAnlagenHersteller()         
                + "%' and kunden.firmenname like '%" + anlageSearch.getSearchKundenFirmenName()
                + "%' and kunden.ansprechpartner like '%" + anlageSearch.getSearchKundenAnsprechPartner()
                + "%' and kunden.strasseNr like '%" + anlageSearch.getSearchKundenStrasseNr()
                + "%' and kunden.plz like '%" + anlageSearch.getSearchKundenPlz()
                + "%' and kunden.ort like '%" + anlageSearch.getSearchKundenOrt()
                + "%' and kunden.bemerkung like '%" + anlageSearch.getSearchKundenBemerkung()
                + "%' and standorte.ansprechpartner like '%" + anlageSearch.getSearchStandortAnsprechPartner()
                + "%' and standorte.strasseNr like '%" + anlageSearch.getSearchStandortStrasseNr()
                + "%' and standorte.standortname like '%" + anlageSearch.getSearchStandortName()
                + "%' and standorte.plz like '%" + anlageSearch.getSearchStandortPlz()
                + "%' and standorte.ort like '%" + anlageSearch.getSearchStandortOrt()
                + "%' and standorte.bemerkung like '%" + anlageSearch.getSearchStandortBemerkung()
                + "%' and betreiber.ansprechpartner like '%" + anlageSearch.getSearchBetreiberAnsprechPartner()
                + "%' and betreiber.strasseNr like '%" + anlageSearch.getSearchBetreiberStrasseNr()
                + "%' and betreiber.betreibername like '%" + anlageSearch.getSearchBetreiberName()
                + "%' and betreiber.plz like '%" + anlageSearch.getSearchBetreiberPlz()
                + "%' and betreiber.ort like '%" + anlageSearch.getSearchBetreiberOrt()
                + "%' and betreiber.bemerkung like '%" + anlageSearch.getSearchBetreiberBemerkung() + "%'"
                + " and anlagenStandorte.idAnlagen = anlagen.idAnlagen"
                + " and betreiber.idBetreiber = anlagenStandorte.idBetreiber"
                + " and standorte.idStandorte = anlagenStandorte.idStandorte"
                + " and kunden.idKunden = anlagenStandorte.idKunden"
                + " and anlagenArt.idAnlagenArt = anlagen.idAnlagenArt"
                + " and anlagenHersteller.idAnlagenHersteller = anlagen.idAnlagenHersteller";
        
        log.debug("query = " + searchQuery);
        
        Query query = entityManager.createQuery(searchQuery);
        
        List<Object[]> results = query.getResultList();
        List<Anlagen> anlagenList = new ArrayList<Anlagen>();
        for (Object[] result: results) {
            Anlagen anlagen = (Anlagen)result[0];
            anlagenList.add(anlagen);
        }
        anlageSearch.setAnlagenResultList(anlagenList);
    } 
	
    public void loadAnlagen(AnlageSearch backingBean) {
        log.debug("Inside load Anlagen for Standort");
        //log.debug("Standort id: "+backingBean.getIdStandort());
        Query query = entityManager.createQuery("from AnlagenStandorte anlagenStandorte where anlagenStandorte.idStandorte = :idStandort");
        log.debug("Query: "+query.toString());
        query.setParameter("idStandort", backingBean.getIdStandort());
        log.debug("Standort id: "+backingBean.getIdStandort());
        }	
    
    public List<Anlagen> getAnlagebyStandort(AnlageSearch backingBean) {
        log.debug("Inside GET Anlagen for Standort");
        List<Anlagen> anlagenList = new ArrayList<Anlagen>();
        if (backingBean.getIdStandort()==null){
            log.debug("Empty ID");
            Query query = entityManager.createQuery("from Anlagen anlagen");
           anlagenList = query.getResultList();
        }
        else{
            log.debug("Non Empty ID");
            anlagenList.clear();
            //Query query = entityManager.createQuery("SELECT anlagen.idAnlagen, anlagen.interneNr FROM Anlagen anlagen,AnlagenStandorte anlagenStandorte where anlagenStandorte.idAnlagen=anlagenStandorte.idAnlagen and anlagenStandorte.idStandorte = :idStandort");
            Query query = entityManager.createQuery("from Anlagen anlagen,AnlagenStandorte anlagenStandorte where anlagenStandorte.idAnlagen=anlagenStandorte.idAnlagen and anlagenStandorte.idStandort = :idStandort");
            // Query query = entityManager.createQuery();
            query.setParameter("idStandort", backingBean.getIdStandort());
            log.debug("Query Executed for id: "+backingBean.getIdStandort());
            List<Object[]> results = query.getResultList();
            //anlagenList = new ArrayList<Anlagen>();
            for (Object[] result: results) {
                Anlagen anlagen = (Anlagen)result[0];
                anlagenList.add(anlagen);
            }
        }
        
        
        
        
        return anlagenList;
        
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}