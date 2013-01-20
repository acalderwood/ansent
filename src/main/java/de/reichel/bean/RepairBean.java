/*
 * To change this template; choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.dao.AnlagenDAO;
import de.reichel.dao.ReparaturDAO;
import de.reichel.dao.TeileDAO;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    public static DateFormat yearFormat = new SimpleDateFormat("yyyy");
    protected Integer idRepairTeile;
    protected Integer idRepair;
    protected Integer idAnlagen;
    protected Integer idFirma;
    protected Integer idStandorte;
    protected Integer idKunden;
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
    protected double travelRatePerHr;
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
     * @return the travelRatePerHr
     */
    public double getTravelRatePerHr() {
        return travelRatePerHr;
    }

    /**
     * @param travelRatePerHr the travelRatePerHr to set
     */
    public void setTravelRatePerHr(double travelRatePerHr) {
        this.travelRatePerHr = travelRatePerHr;
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
}
