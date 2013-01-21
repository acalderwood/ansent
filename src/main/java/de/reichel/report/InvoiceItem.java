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
    
    private int quantity;
    private boolean me;
    private String description;
    private double unitPrice;
    private double totalPrice;

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the me
     */
    public boolean isMe() {
        return me;
    }

    /**
     * @param me the me to set
     */
    public void setMe(boolean me) {
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
