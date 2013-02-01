/*
 * To change this template; choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.dao.AnlagenDAO;
import de.reichel.dao.ReparaturDAO;
import de.reichel.dao.TeileDAO;
import de.reichel.util.Utils;
import java.io.File;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Alastair Calderwood
 */
public class RepairBean implements Serializable {

    private static final Log log = LogFactory.getLog(RepairBean.class);
    @Inject
    protected AnlagenDAO anlagenDAO;
    @Inject
    protected ReparaturDAO repairDAO;
    @Inject
    protected TeileDAO teileDAO;
    protected Integer idRepairTeile;
    protected Integer idRepair;
    protected Integer idAnlagen;
    protected Integer idFirma;
    protected Integer idStandorte;
    protected Integer idKunden;
    private Integer idBetreiber;
    protected Integer idRates;
    protected short state;
    protected String workDescription;
    protected String internalRemarks;
    protected Integer hoursOperation;
    protected Date repairDate;
    protected Integer idTechnician;
//    protected Integer hoursWorked;
//    protected Integer minsWorked;
    protected Date timeWorked;
//    protected Integer helperHoursWorked;
//    protected Integer helperMinsWorked;
    protected Date helperTimeWorked;
//    protected Integer travelTimeHrs;
//    protected Integer travelTimeMins;
    protected Date travelTime;
//    protected Integer travelTimeHelperHrs;
//    protected Integer travelTimeHelperMins;
    protected Date travelTimeHelper;
    protected double fixedTravelCost;
    protected Integer travelDistanceKm;
    protected double travelRatePerKm;
//    protected Integer accommodationHrs;
//    protected Integer accommodationMins;
    protected Date accommodationTime;
//    protected Integer overtimeHrs;
//    protected Integer overtimeMins;
    protected Date overtimeTime;
//    protected Integer dirtyHrs;
//    protected Integer dirtyMins;
    protected Date dirtyTime;
    protected boolean travelTypeTime = true;
    protected boolean travelTypeKm = true;
    protected List<TeileBean> parts = new ArrayList<TeileBean>();
    protected List<String> invoiceFileNames = new ArrayList<String>();
    protected String newTechnicianName;
    
    private String rechnungsnummer;
    private String auftraggeber;
    private String lieferantenNr;
    private String bestellNr;
    private String wartungzeitraum;
    private String extraKey1;
    private String extraValue1;
    private String extraKey2;
    private String extraValue2;
    private String extraKey3;
    private String extraValue3;
    private String extraKey4;
    private String extraValue4;

    /**
     * @return the idRepair
     */
    public Integer getIdRepair() {
        return idRepair;
    }

    /**
     * @param idRepair the idRepair to set
     */
    public void setIdRepair(Integer idRepair) {
        this.idRepair = idRepair;
    }

    /**
     * @return the state
     */
    public short getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(short state) {
        this.state = state;
    }

    /**
     * @return the idFirma
     */
    public Integer getIdFirma() {
        return idFirma;
    }

    /**
     * @param idFirma the idFirma to set
     */
    public void setIdFirma(Integer idFirma) {
        this.idFirma = idFirma;
    }

    /**
     * @return the idRates
     */
    public Integer getIdRates() {
        return idRates;
    }

    /**
     * @param idRates the idRates to set
     */
    public void setIdRates(Integer idRates) {
        this.idRates = idRates;
    }

    /**
     * @return the workDescription
     */
    public String getWorkDescription() {
        return workDescription;
    }

    /**
     * @param workDescription the workDescription to set
     */
    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    /**
     * @return the internalRemarks
     */
    public String getInternalRemarks() {
        return internalRemarks;
    }

    /**
     * @param internalRemarks the internalRemarks to set
     */
    public void setInternalRemarks(String internalRemarks) {
        this.internalRemarks = internalRemarks;
    }

    /**
     * @return the hoursOperation
     */
    public Integer getHoursOperation() {
        return hoursOperation;
    }

    /**
     * @param hoursOperation the hoursOperation to set
     */
    public void setHoursOperation(Integer hoursOperation) {
        this.hoursOperation = hoursOperation;
    }

    /**
     * @return the repairDate
     */
    public Date getRepairDate() {
        return repairDate;
    }

    /**
     * @param repairDate the repairDate to set
     */
    public void setRepairDate(Date repairDate) {
        this.repairDate = repairDate;
    }

    /**
     * @return the technician
     */
    public Integer getIdTechnician() {
        return idTechnician;
    }

    /**
     * @param technician the technician to set
     */
    public void setIdTechnician(Integer idTechnician) {
        this.idTechnician = idTechnician;
    }

    public Date getTimeWorked() {
        return timeWorked;
    }

    public void setTimeWorked(Date date) {
        this.timeWorked = date;
    }

    public Date getHelperTimeWorked() {
        return helperTimeWorked;
    }

    public void setHelperTimeWorked(Date date) {
        this.helperTimeWorked = date;
    }

    public Date getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Date date) {
        this.travelTime = date;
    }

    public Date getTravelTimeHelper() {
        return travelTimeHelper;
    }

    public void setTravelTimeHelper(Date date) {
        this.travelTimeHelper = date;
    }

    public Date getAccommodationTime() {
        return accommodationTime;
    }

    public void setAccommodationTime(Date date) {
        this.accommodationTime = date;
    }

    public Date getOvertimeTime() {
        return overtimeTime;
    }

    public void setOvertimeTime(Date date) {
        this.overtimeTime = date;
    }

    public Date getDirtyTime() {
        return dirtyTime;
    }

    public void setDirtyTime(Date date) {
        this.dirtyTime = date;
    }

//    /**
//     * @return the hoursWorked
//     */
//    public Integer getHoursWorked() {
//        return hoursWorked;
//    }
//
//    /**
//     * @param hoursWorked the hoursWorked to set
//     */
//    public void setHoursWorked(Integer hoursWorked) {
//        this.hoursWorked = hoursWorked;
//    }
//
//    /**
//     * @return the minsWorked
//     */
//    public Integer getMinsWorked() {
//        return minsWorked;
//    }
//
//    /**
//     * @param minsWorked the minsWorked to set
//     */
//    public void setMinsWorked(Integer minsWorked) {
//        this.minsWorked = minsWorked;
//    }
//
//    /**
//     * @return the helperHoursWorked
//     */
//    public Integer getHelperHoursWorked() {
//        return helperHoursWorked;
//    }
//
//    /**
//     * @param helperHoursWorked the helperHoursWorked to set
//     */
//    public void setHelperHoursWorked(Integer helperHoursWorked) {
//        this.helperHoursWorked = helperHoursWorked;
//    }
//
//    /**
//     * @return the helperMinsWorked
//     */
//    public Integer getHelperMinsWorked() {
//        return helperMinsWorked;
//    }
//
//    /**
//     * @param helperMinsWorked the helperMinsWorked to set
//     */
//    public void setHelperMinsWorked(Integer helperMinsWorked) {
//        this.helperMinsWorked = helperMinsWorked;
//    }
//
//    /**
//     * @return the travelTimeHrs
//     */
//    public Integer getTravelTimeHrs() {
//        return travelTimeHrs;
//    }
//
//    /**
//     * @param travelTimeHrs the travelTimeHrs to set
//     */
//    public void setTravelTimeHrs(Integer travelTimeHrs) {
//        this.travelTimeHrs = travelTimeHrs;
//    }
//
//    /**
//     * @return the travelTimeMins
//     */
//    public Integer getTravelTimeMins() {
//        return travelTimeMins;
//    }
//
//    /**
//     * @param travelTimeMins the travelTimeMins to set
//     */
//    public void setTravelTimeMins(Integer travelTimeMins) {
//        this.travelTimeMins = travelTimeMins;
//    }
//
//    /**
//     * @return the travelTimeHelperHrs
//     */
//    public Integer getTravelTimeHelperHrs() {
//        return travelTimeHelperHrs;
//    }
//
//    /**
//     * @param travelTimeHelperHrs the travelTimeHelperHrs to set
//     */
//    public void setTravelTimeHelperHrs(Integer travelTimeHelperHrs) {
//        this.travelTimeHelperHrs = travelTimeHelperHrs;
//    }
//
//    /**
//     * @return the travelTimeHelperMins
//     */
//    public Integer getTravelTimeHelperMins() {
//        return travelTimeHelperMins;
//    }
//
//    /**
//     * @param travelTimeHelperMins the travelTimeHelperMins to set
//     */
//    public void setTravelTimeHelperMins(Integer travelTimeHelperMins) {
//        this.travelTimeHelperMins = travelTimeHelperMins;
//    }
    /**
     * @return the fixedTravelCost
     */
    public double getFixedTravelCost() {
        return fixedTravelCost;
    }

    /**
     * @param fixedTravelCost the fixedTravelCost to set
     */
    public void setFixedTravelCost(double fixedTravelCost) {
        this.fixedTravelCost = fixedTravelCost;
    }

    /**
     * @return the travelDistanceKm
     */
    public Integer getTravelDistanceKm() {
        return travelDistanceKm;
    }

    /**
     * @param travelDistanceKm the travelDistanceKm to set
     */
    public void setTravelDistanceKm(Integer travelDistanceKm) {
        this.travelDistanceKm = travelDistanceKm;
    }

    /**
     * @return the travelRatePerKm
     */
    public double getTravelRatePerKm() {
        return travelRatePerKm;
    }

    /**
     * @param travelRatePerKm the travelRatePerKm to set
     */
    public void setTravelRatePerKm(double travelRatePerKm) {
        this.travelRatePerKm = travelRatePerKm;
    }
//
//    /**
//     * @return the accommodationHrs
//     */
//    public Integer getAccommodationHrs() {
//        return accommodationHrs;
//    }
//
//    /**
//     * @param accommodationHrs the accommodationHrs to set
//     */
//    public void setAccommodationHrs(Integer accommodationHrs) {
//        this.accommodationHrs = accommodationHrs;
//    }
//
//    /**
//     * @return the accommodationMins
//     */
//    public Integer getAccommodationMins() {
//        return accommodationMins;
//    }
//
//    /**
//     * @param accommodationMins the accommodationMins to set
//     */
//    public void setAccommodationMins(Integer accommodationMins) {
//        this.accommodationMins = accommodationMins;
//    }
//
//    /**
//     * @return the overtimeHrs
//     */
//    public Integer getOvertimeHrs() {
//        return overtimeHrs;
//    }
//
//    /**
//     * @param overtimeHrs the overtimeHrs to set
//     */
//    public void setOvertimeHrs(Integer overtimeHrs) {
//        this.overtimeHrs = overtimeHrs;
//    }
//
//    /**
//     * @return the overtimeMins
//     */
//    public Integer getOvertimeMins() {
//        return overtimeMins;
//    }
//
//    /**
//     * @param overtimeMins the overtimeMins to set
//     */
//    public void setOvertimeMins(Integer overtimeMins) {
//        this.overtimeMins = overtimeMins;
//    }
//
//    /**
//     * @return the dirtyHrs
//     */
//    public Integer getDirtyHrs() {
//        return dirtyHrs;
//    }
//
//    /**
//     * @param dirtyHrs the dirtyHrs to set
//     */
//    public void setDirtyHrs(Integer dirtyHrs) {
//        this.dirtyHrs = dirtyHrs;
//    }
//
//    /**
//     * @return the dirtyMins
//     */
//    public Integer getDirtyMins() {
//        return dirtyMins;
//    }
//
//    /**
//     * @param dirtyMins the dirtyMins to set
//     */
//    public void setDirtyMins(Integer dirtyMins) {
//        this.dirtyMins = dirtyMins;
//    }
//

    /**
     * @return the idAnlagen
     */
    public Integer getIdAnlagen() {
        return idAnlagen;
    }

    /**
     * @param idAnlagen the idAnlagen to set
     */
    public void setIdAnlagen(Integer idAnlagen) {
        this.idAnlagen = idAnlagen;
    }

    /**
     * @return the idRepairTeile
     */
    public Integer getIdRepairTeile() {
        return idRepairTeile;
    }

    /**
     * @param idRepairTeile the idRepairTeile to set
     */
    public void setIdRepairTeile(Integer idRepairTeile) {
        this.idRepairTeile = idRepairTeile;
    }

    /**
     * @return the travelTypeTime
     */
    public boolean isTravelTypeTime() {
        return travelTypeTime;
    }

    /**
     * @param travelTypeTime the travelTypeTime to set
     */
    public void setTravelTypeTime(boolean travelTypeTime) {
        this.travelTypeTime = travelTypeTime;
        this.travelTypeKm = !travelTypeTime;
    }

    /**
     * @return the travelTypeKm
     */
    public boolean isTravelTypeKm() {
        return travelTypeKm;
    }

    /**
     * @param travelTypeKm the travelTypeKm to set
     */
    public void setTravelTypeKm(boolean travelTypeKm) {
        log.debug("repairBean.travelTypeKm");
        this.travelTypeKm = travelTypeKm;
        this.travelTypeTime = !travelTypeKm;
    }

    /**
     * @return the idStandorte
     */
    public Integer getIdStandorte() {
        return idStandorte;
    }

    /**
     * @param idStandorte the idStandorte to set
     */
    public void setIdStandorte(Integer idStandorte) {
        this.idStandorte = idStandorte;
    }

    /**
     * @return the idKunden
     */
    public Integer getIdKunden() {
        return idKunden;
    }

    /**
     * @param idKunden the idKunden to set
     */
    public void setIdKunden(Integer idKunden) {
        this.idKunden = idKunden;
    }

    /**
     * @return the parts
     */
    public List<TeileBean> getParts() {
        return parts;
    }

    /**
     * @param parts the parts to set
     */
    public void setParts(List<TeileBean> parts) {
        this.parts = parts;
    }

    /**
     * @return the idBetreiber
     */
    public Integer getIdBetreiber() {
        return idBetreiber;
    }

    /**
     * @param idBetreiber the idBetreiber to set
     */
    public void setIdBetreiber(Integer idBetreiber) {
        this.idBetreiber = idBetreiber;
    }

    public List<String> getInvoiceFileNames() {
        File invoiceDir = new File(Utils.getInvoiceDirPath());
        File[] invoiceFileList = invoiceDir.listFiles();
        List<String> fileNames = new ArrayList<String>();
        for (int i = 0; i < invoiceFileList.length; i++) {
            if (invoiceFileList[i].isFile()) {
                String fileName = invoiceFileList[i].getName();
                fileNames.add(fileName);
            }
        }
        return invoiceFileNames;
    }

    public void setInvoiceFileNames(List<String> invoiceFileNames) {
        this.invoiceFileNames = invoiceFileNames;
    }

    /**
     * @return the newTechnicianName
     */
    public String getNewTechnicianName() {
        return newTechnicianName;
    }

    /**
     * @param newTechnicianName the newTechnicianName to set
     */
    public void setNewTechnicianName(String newTechnicianName) {
        this.newTechnicianName = newTechnicianName;
    }

    /**
     * @return the rechnungsnummer
     */
    public String getRechnungsnummer() {
        return rechnungsnummer;
    }

    /**
     * @param rechnungsnummer the rechnungsnummer to set
     */
    public void setRechnungsnummer(String rechnungsnummer) {
        this.rechnungsnummer = rechnungsnummer;
    }

    /**
     * @return the auftraggeber
     */
    public String getAuftraggeber() {
        return auftraggeber;
    }

    /**
     * @param auftraggeber the auftraggeber to set
     */
    public void setAuftraggeber(String auftraggeber) {
        this.auftraggeber = auftraggeber;
    }

    /**
     * @return the lieferantenNr
     */
    public String getLieferantenNr() {
        return lieferantenNr;
    }

    /**
     * @param lieferantenNr the lieferantenNr to set
     */
    public void setLieferantenNr(String lieferantenNr) {
        this.lieferantenNr = lieferantenNr;
    }

    /**
     * @return the bestellNr
     */
    public String getBestellNr() {
        return bestellNr;
    }

    /**
     * @param bestellNr the bestellNr to set
     */
    public void setBestellNr(String bestellNr) {
        this.bestellNr = bestellNr;
    }

    /**
     * @return the wartungzeitraum
     */
    public String getWartungzeitraum() {
        return wartungzeitraum;
    }

    /**
     * @param wartungzeitraum the wartungzeitraum to set
     */
    public void setWartungzeitraum(String wartungzeitraum) {
        this.wartungzeitraum = wartungzeitraum;
    }

    /**
     * @return the extraKey1
     */
    public String getExtraKey1() {
        return extraKey1;
    }

    /**
     * @param extraKey1 the extraKey1 to set
     */
    public void setExtraKey1(String extraKey1) {
        this.extraKey1 = extraKey1;
    }

    /**
     * @return the extraValue1
     */
    public String getExtraValue1() {
        return extraValue1;
    }

    /**
     * @param extraValue1 the extraValue1 to set
     */
    public void setExtraValue1(String extraValue1) {
        this.extraValue1 = extraValue1;
    }

    /**
     * @return the extraKey2
     */
    public String getExtraKey2() {
        return extraKey2;
    }

    /**
     * @param extraKey2 the extraKey2 to set
     */
    public void setExtraKey2(String extraKey2) {
        this.extraKey2 = extraKey2;
    }

    /**
     * @return the extraValue2
     */
    public String getExtraValue2() {
        return extraValue2;
    }

    /**
     * @param extraValue2 the extraValue2 to set
     */
    public void setExtraValue2(String extraValue2) {
        this.extraValue2 = extraValue2;
    }

    /**
     * @return the extraKey3
     */
    public String getExtraKey3() {
        return extraKey3;
    }

    /**
     * @param extraKey3 the extraKey3 to set
     */
    public void setExtraKey3(String extraKey3) {
        this.extraKey3 = extraKey3;
    }

    /**
     * @return the extraValue3
     */
    public String getExtraValue3() {
        return extraValue3;
    }

    /**
     * @param extraValue3 the extraValue3 to set
     */
    public void setExtraValue3(String extraValue3) {
        this.extraValue3 = extraValue3;
    }

    /**
     * @return the extraKey4
     */
    public String getExtraKey4() {
        return extraKey4;
    }

    /**
     * @param extraKey4 the extraKey4 to set
     */
    public void setExtraKey4(String extraKey4) {
        this.extraKey4 = extraKey4;
    }

    /**
     * @return the extraValue4
     */
    public String getExtraValue4() {
        return extraValue4;
    }

    /**
     * @param extraValue4 the extraValue4 to set
     */
    public void setExtraValue4(String extraValue4) {
        this.extraValue4 = extraValue4;
    }
}
