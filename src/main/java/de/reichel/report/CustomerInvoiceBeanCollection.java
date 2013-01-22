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
//        invoice
        List<CustomerInvoice> invoices = new ArrayList<CustomerInvoice>();
        invoices.add(invoice);
        return invoices;
    }
}
