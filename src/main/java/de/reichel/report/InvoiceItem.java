/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.report;

/**
 *
 * @author Alastair Calderwood
 */
public class InvoiceItem {
    
    private double quantity;
    private String me;
    private String description;
    private double unitPrice;
    private double totalPrice;

    /**
     * @return the quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the me
     */
    public String getMe() {
        return me;
    }

    /**
     * @param me the me to set
     */
    public void setMe(String me) {
        this.me = me;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the unitPrice
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return the totalPrice
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
}
