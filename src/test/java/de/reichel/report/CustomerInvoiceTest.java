/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.report;

import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Alastair Calderwood
 */
public class CustomerInvoiceTest {

    @Test @Ignore
    public void testGenerateReport() throws Exception {
        Map parameters = new HashMap();
        parameters.put("SUBREPORT_DIR", "C:\\Users\\Alastair Calderwood\\Documents\\NetBeansProjects\\reichel\\src\\main\\resources\\reports");
        JasperFillManager.fillReportToFile("C:\\Users\\Alastair Calderwood\\Documents\\NetBeansProjects\\reichel\\src\\main\\resources\\reports\\reparatur_4.jasper",
                "C:\\temp\\jasperreports\\invoice.jrprint", parameters, new JRBeanCollectionDataSource(CustomerInvoiceBeanCollection.getBeanCollection()));
        JasperExportManager.exportReportToPdfFile("C:\\temp\\jasperreports\\invoice.jrprint");
    }
}
