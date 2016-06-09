/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Alastair Calderwood
 */
public class CustomerInvoiceTest {

    private static final Log log = LogFactory.getLog(CustomerInvoiceTest.class);

    @Test @Ignore
    public void testGenerateReport() throws Exception {

        RepairReportBean invoice = new RepairReportBean();
        invoice.setStandorte_STANDORTNAME("standorte");
        invoice.setStandorte_STRASSE_NR("strasseNr");
        invoice.setStandorte_ORT("ort");
        invoice.setStandorte_PLZ("plz");
        List<RepairReportBean> invoices = new ArrayList<RepairReportBean>();
        invoices.add(invoice);

            log.debug("Generating invoice: PLZ: " + invoices.get(0).getStandorte_PLZ());
            InputStream is = new FileInputStream("C:\\Users\\Alastair Calderwood\\Documents\\NetBeansProjects\\reichel\\target\\reichel-1.0-SNAPSHOT\\WEB-INF\\classes\\reports\\reparatur_4.jasper");
            byte[] bytes = JasperRunManager.runReportToPdf(is, new HashMap(), new JRBeanCollectionDataSource(invoices));

            log.debug("JasperPrint object created");
            File pdf = File.createTempFile("output.", ".pdf");
            log.debug("Temp file created at: " + pdf.getAbsolutePath());

            OutputStream os = new FileOutputStream(pdf);
            os.write(bytes);
            log.debug("Exported to PDF");
        
        log.debug("Temp file created at: " + pdf.getAbsolutePath());
    }
}
