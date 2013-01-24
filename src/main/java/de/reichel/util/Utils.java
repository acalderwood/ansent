/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.util;

/**
 *
 * @author Alastair Calderwood
 */
public class Utils {

    public static double roundDP(double num, int dp) {
        double result = num * (Math.pow(10, (double)dp));
        result = Math.round(result);
        result = result / (Math.pow(10, (double)dp));
        return result;
    }
    
    public static String doubleToCurrency(double num) {
        String result = new Double(num).toString() + "00";
        result = result.replace(".", ",");
        int point = result.indexOf(",");
        if (point != -1) {
            return result.substring(0, point + 3);
        } else {
            return result;
        }
    }
    
    public static String doubleNoDP(double num) {
        String result = new Double(num).toString();
        result = result.replace(".", ",");
        int point = result.indexOf(",");
        if (point != -1) {
            return result.substring(0, point);
        } else {
            return result;
        }
    }
    
    public static void main(String[] args) {
        double d = 234234.2342876;
        System.out.println(roundDP(d, 3));
        System.out.println(doubleToCurrency(d));
        System.out.println(doubleNoDP(d));
        
    }
}
