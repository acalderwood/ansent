/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.report;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alastair Calderwood
 */
public class CustomerInvoiceBeanCollection {

    public static List<CustomerInvoice> getBeanCollection() {
        CustomerInvoice invoice = new CustomerInvoice();
        
        invoice.setRechnungen_INVOICE_TEXT("Anz\tME\tBezeichnung\t\t\t\t\tEinzelpreis\t\t\tGesamt\n"
                +                 "1\tX\tWartung und BGR 186 Pauschale\t\t55,00\t\t55,00\n"
                +                 "1\tX\tAnfahrtspauschale Wartung  Kaufland Los B (4700103528)\t55,00\t\t55,00\n");
        List<CustomerInvoice> invoices = new ArrayList<CustomerInvoice>();
        invoices.add(invoice);
        return invoices;
    }
}
