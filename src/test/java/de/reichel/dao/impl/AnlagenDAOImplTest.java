/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.dao.impl;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import de.reichel.bean.AnlageNew;
import de.reichel.bean.AnlageSearch;
import de.reichel.bean.BetreiberNew;
import de.reichel.bean.KundenNew;
import de.reichel.dao.AnlagenDAO;
import de.reichel.dao.BetreiberDAO;
import de.reichel.dao.KundenDAO;
import de.reichel.domain.model.Anlagen;
import de.reichel.domain.model.AnlagenArt;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author alastair
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/WEB-INF/applicationContext.xml"})
public class AnlagenDAOImplTest {

    private AnlagenDAO anlagenDAO;
    private BetreiberDAO betreiberDAO;
    private KundenDAO kundenDAO;

    static {
        try {
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
            System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");

            InitialContext ic = new InitialContext();
            ic.createSubcontext("jdbc");

            MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
            ds.setURL("jdbc:mysql://localhost:3306/ansent");
            ds.setZeroDateTimeBehavior("convertToNull");
            ds.setUser("root");
            ds.setPassword("root");

            ic.bind("jdbc/ReichelDS", ds);

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    @Autowired
    ApplicationContext context;

    @Before
    public void setUp() {
        anlagenDAO = context.getBean(AnlagenDAOImpl.class);
        betreiberDAO = context.getBean(BetreiberDAOImpl.class);
        kundenDAO = context.getBean(KundenDAOImpl.class);
    }

    @Test @Ignore
    public void testGetAnlageArt() {
        List<Integer> selectedArt = new ArrayList<Integer>();
        selectedArt.add(1);
        selectedArt.add(2);
        selectedArt.add(3);
        AnlageSearch backingBean = new AnlageSearch();
        backingBean.setSelectedArt(selectedArt);
        Assert.assertNotEquals(0, anlagenDAO.getAnlagen(backingBean).size());
    }

    @Test @Ignore
    public void testGetAnlageId() {
        String interneNr = "389-X-PPK";
        AnlageSearch backingBean = new AnlageSearch();
        backingBean.setInterneNr(interneNr);
        backingBean.setSelectedArt(null);
        Assert.assertEquals(1, anlagenDAO.getAnlagen(backingBean).size());
        
        backingBean.setSelectedArt(new ArrayList<Integer>());
        Assert.assertEquals(1, anlagenDAO.getAnlagen(backingBean).size());
    }

    @Test @Ignore
    public void testGetAnlageIdAndArt() {
        List<Integer> selectedArt = new ArrayList<Integer>();
        selectedArt.add(1);
        selectedArt.add(2);
        String interneNr = "389-X-PPK";
        
        AnlageSearch backingBean = new AnlageSearch();
        backingBean.setInterneNr(interneNr);
        backingBean.setSelectedArt(selectedArt);
        Assert.assertEquals(1, anlagenDAO.getAnlagen(backingBean).size());
    }

    @Test @Ignore
    public void testGetAnlagen() {
        String interneNr = "389-X-PPK";
        Assert.assertEquals(Anlagen.class, (anlagenDAO.getAnlagen(interneNr))[0].getClass());
        Assert.assertEquals(AnlagenArt.class, (anlagenDAO.getAnlagen(interneNr))[1].getClass());
    }
    
    @Test
    public void testAddAnlagen() {
        AnlageNew backingBean = new AnlageNew();
        backingBean.setInterneNr("test" + Calendar.getInstance().getTimeInMillis());
        backingBean.setIdArt(1);
        backingBean.setBaujahr(new Date(1,1,20));
        backingBean.setBemerkung("test test");
        backingBean.setFabrikationsnr("123");
        backingBean.setIdHersteller(1);
        backingBean.setInterneNotiz("test");
        backingBean.setNaechsteUVV(new Date(1,1,43));
        backingBean.setNaechsteWartung(new Date(1,1,43));
        backingBean.setTyp("FP 25 ");
        
        int sizeBefore = anlagenDAO.getAllAnlagen().size();
        anlagenDAO.addAnlagen(backingBean);
        int sizeAfter = anlagenDAO.getAllAnlagen().size();
        Assert.assertEquals(sizeBefore + 1, sizeAfter);
    }

    @Test
    public void testAddBetreiber() {
        BetreiberNew backingBean = new BetreiberNew();
        backingBean.setAnsprechpartner("test" + Calendar.getInstance().getTimeInMillis());
        backingBean.setBemerkung("hohoho");
        backingBean.setBetreibername("10000000");
        backingBean.setEmail("a@a.com");
        backingBean.setIdKunden(1);
        backingBean.setInterneNotiz("test");
        backingBean.setLand("AAAA");
        backingBean.setOrt("AAAA");
        backingBean.setPlz("AAAA");
        backingBean.setStrasseNr("123 AAA");
        backingBean.setTelefon("098098098");
        
        int sizeBefore = betreiberDAO.getAllBetreiber().size();
        betreiberDAO.addBetreiber(backingBean);
        int sizeAfter = betreiberDAO.getAllBetreiber().size();
        Assert.assertEquals(sizeBefore + 1, sizeAfter);
    } 
    
    @Test
    public void testAddKunden() {
        KundenNew backingBean = new KundenNew();
        backingBean.setAnsprechpartner("AAAAAA");
        backingBean.setBemerkung("AAAAAA");
        backingBean.setBuchungskreis("AAAAAA");
        backingBean.setEmail("AAAAAA");
        backingBean.setFax("AAAAAA");
        backingBean.setFirmenname("AAAAAA");
        backingBean.setInterneNotiz("AAAAAA");
        backingBean.setLand("AAAAAA");
        backingBean.setOrt("AAAAAA");
        backingBean.setPlz("AAAAAA");
        backingBean.setSteuerNr("AAAAAA");
        backingBean.setStrasseNr("AAAAAA");
        backingBean.setTelefon("AAAAAA");

        
        int sizeBefore = kundenDAO.getSelectableKunden().size();
        kundenDAO.addKunden(backingBean);
        int sizeAfter = kundenDAO.getSelectableKunden().size();
        Assert.assertEquals(sizeBefore + 1, sizeAfter);
    }     
    
    @Test @Ignore
    public void testGetTypes() {
        String interneNr = "389-X-PPK";
        AnlageSearch backingBean = new AnlageSearch();
        backingBean.setInterneNr(interneNr);
        Assert.assertTrue((anlagenDAO.getAnlagen(backingBean)).get(0)[0] instanceof Anlagen);
        Assert.assertTrue((anlagenDAO.getAnlagen(backingBean)).get(0)[1] instanceof AnlagenArt);
    }

    @Test @Ignore
    public void testGetSelectableArt() {
        List<AnlagenArt> arten = anlagenDAO.getSelectableArt();
        Assert.assertTrue(arten.size() > 0);
    }
    
    @Test @Ignore
    public void testSearchAnlagen() {
        AnlageSearch anlagen = new AnlageSearch();
        anlagen.setSearchAnlagenArt("container");
        anlagenDAO.searchAnlagen(anlagen);
    }
}
