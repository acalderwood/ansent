/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.report;

import java.util.List;

/**
 *
 * @author Alastair Calderwood
 */
public class CustomerInvoiceUtil {
    
    public static String createInvoiceDetail(List<InvoiceItem> invoiceItems) {
        String rows = "";
        for (InvoiceItem item: invoiceItems) {
            String row = item.getQuantity() + "\t";
            rows += row;
        }
        return rows;
    }
}
