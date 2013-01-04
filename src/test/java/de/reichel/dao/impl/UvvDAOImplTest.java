/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.dao.impl;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import de.reichel.dao.UvvDAO;
import de.reichel.domain.model.Uvv;
import de.reichel.domain.model.UvvArt;
import de.reichel.domain.model.UvvMangel;
import de.reichel.domain.model.UvvPunkte;
import de.reichel.domain.model.UvvPunkteGruppen;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.Assert;
import org.junit.Before;
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
public class UvvDAOImplTest {

    private UvvDAO uvvDAO;

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
        uvvDAO = context.getBean(UvvDAOImpl.class);
    }

    @Test
    public void testGetUvv() {
        Object[] uvv = uvvDAO.getUvv(10);
//        Assert.assertEquals(Anlagen.class, uvv[].getClass());
        //Uvv uvv, UvvArt uvvArt, UvvMangel uvvMangel, UvvPunkte uvvPunkte, UvvPunkteGruppen uvvPunkteGruppen, Anlagen anlagen, AnlagenArt anlagenAr
        Assert.assertEquals(Uvv.class, uvv[0].getClass());
        Assert.assertEquals(UvvArt.class, uvv[1].getClass());
    }
}
