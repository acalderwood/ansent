/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alastair Calderwood
 */
public class Utils {

    public static DateFormat fileDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.GERMAN);
    public static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    public static DateFormat yearFormat = new SimpleDateFormat("yyyy");
    
    private static String OS = System.getProperty("os.name").toLowerCase();

    public static double roundDP(double num, int dp) {
        double result = num * (Math.pow(10, (double) dp));
        result = Math.round(result);
        result = result / (Math.pow(10, (double) dp));
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

    public static String getInvoiceDirPath() {
        if (isUnix() || isMac() || isSolaris()) {
//            return "/share/MD0_DATA/Public/MTB-Eneutec/Claudia/ENEUTEC-MTB/Rechnungen/2013-Rechnungen";
            return "/var/ansent/rechnungen";
        }
        if (isWindows()) {
            return "C:\\reichel\\rechnungen";
        }
        return "/tmp";
    }
    
    public static String getAuftragDirPath() {
        if (isUnix() || isMac() || isSolaris()) {
return "/var/ansent/auftraege";
            //            return "/share/MD0_DATA/Public/MTB-Eneutec/Claudia/ENEUTEC-MTB/Auftragsbestätigungen/2013-Aufträge";
        }
        if (isWindows()) {
            return "C:\\reichel\\auftraege";
        }
        return "/tmp";
    }    

    public static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    public static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    public static boolean isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
    }

    public static boolean isSolaris() {
        return (OS.indexOf("sunos") >= 0);
    }
}