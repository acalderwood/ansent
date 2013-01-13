/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.junit.Test;


/**
 *
 * @author Alastair Calderwood
 */
public class CustomerInvoiceTest {

    @Test
    public void testGenerateReport() throws Exception {

        List<TestBean> list = new ArrayList<TestBean>();
        JRBeanCollectionDataSource src = new JRBeanCollectionDataSource(list);
        Map parameters = new HashMap();
        parameters.put("ReportTitle", "Address Report");
        parameters.put("DataFile", "TestBean.java");
        JasperFillManager.fillReportToFile("reports/reparatur_2.jasper",
                parameters, src);
    }
}
