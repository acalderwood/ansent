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
public class Main {
    
    public static void main(String[] args) {
        CustomerInvoice ci = new CustomerInvoice();
        ci.setAnlagen_BAUJAHR("2012");
        List<CustomerInvoice> invoices = new ArrayList<CustomerInvoice>();
        invoices.add(ci);
        System.out.println(invoices.get(0).getAnlagen_BAUJAHR());
    }
}
