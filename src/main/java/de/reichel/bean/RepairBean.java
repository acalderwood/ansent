/*
 * To change this template; choose Tools | Templates
 * and open the template in the editor.
 */
package de.reichel.bean;

import de.reichel.dao.ReparaturDAO;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.inject.Inject;

/**
 *
 * @author Alastair Calderwood
 */
public class RepairBean implements Serializable {

    @Inject
    protected ReparaturDAO repairDAO;
    public static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    public static DateFormat yearFormat = new SimpleDateFormat("yyyy");
    protected int idRepairTeile;
    protected int idRepair;
    protected int idAnlagen;
    protected int idFirma;
    protected int idRates;
    protected short state;
    protected String workDescription;
    protected String internalRemarks;
    protected int hoursOperation;
    protected Date repairDate;
    protected int idTechnician;
    protected int hoursWorked;
    protected int minsWorked;
    protected int helperHoursWorked;
    protected int helperMinsWorked;
    protected int travelTimeHrs;
    protected int travelTimeMins;
    protected int travelTimeHelperHrs;
    protected int travelTimeHelperMins;
    protected double travelRatePerHr;
    protected int travelDistanceKm;
    protected double travelRatePerKm;
    protected int accommodationHrs;
    protected int accommodationMins;
    protected int overtimeHrs;
    protected int overtimeMins;
    protected int dirtyHrs;
    protected int dirtyMins;

    /**
     * @return the idRepair
     */
    public int getIdRepair() {
        return idRepair;
    }

    /**
     * @param idRepair the idRepair to set
     */
    public void setIdRepair(int idRepair) {
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
    public int getIdFirma() {
        return idFirma;
    }

    /**
     * @param idFirma the idFirma to set
     */
    public void setIdFirma(int idFirma) {
        this.idFirma = idFirma;
    }

    /**
     * @return the idRates
     */
    public int getIdRates() {
        return idRates;
    }

    /**
     * @param idRates the idRates to set
     */
    public void setIdRates(int idRates) {
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
    public int getHoursOperation() {
        return hoursOperation;
    }

    /**
     * @param hoursOperation the hoursOperation to set
     */
    public void setHoursOperation(int hoursOperation) {
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
    public int getTechnician() {
        return idTechnician;
    }

    /**
     * @param technician the technician to set
     */
    public void setTechnician(int idTechnician) {
        this.idTechnician = idTechnician;
    }

    /**
     * @return the hoursWorked
     */
    public int getHoursWorked() {
        return hoursWorked;
    }

    /**
     * @param hoursWorked the hoursWorked to set
     */
    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    /**
     * @return the minsWorked
     */
    public int getMinsWorked() {
        return minsWorked;
    }

    /**
     * @param minsWorked the minsWorked to set
     */
    public void setMinsWorked(int minsWorked) {
        this.minsWorked = minsWorked;
    }

    /**
     * @return the helperHoursWorked
     */
    public int getHelperHoursWorked() {
        return helperHoursWorked;
    }

    /**
     * @param helperHoursWorked the helperHoursWorked to set
     */
    public void setHelperHoursWorked(int helperHoursWorked) {
        this.helperHoursWorked = helperHoursWorked;
    }

    /**
     * @return the helperMinsWorked
     */
    public int getHelperMinsWorked() {
        return helperMinsWorked;
    }

    /**
     * @param helperMinsWorked the helperMinsWorked to set
     */
    public void setHelperMinsWorked(int helperMinsWorked) {
        this.helperMinsWorked = helperMinsWorked;
    }

    /**
     * @return the travelTimeHrs
     */
    public int getTravelTimeHrs() {
        return travelTimeHrs;
    }

    /**
     * @param travelTimeHrs the travelTimeHrs to set
     */
    public void setTravelTimeHrs(int travelTimeHrs) {
        this.travelTimeHrs = travelTimeHrs;
    }

    /**
     * @return the travelTimeMins
     */
    public int getTravelTimeMins() {
        return travelTimeMins;
    }

    /**
     * @param travelTimeMins the travelTimeMins to set
     */
    public void setTravelTimeMins(int travelTimeMins) {
        this.travelTimeMins = travelTimeMins;
    }

    /**
     * @return the travelTimeHelperHrs
     */
    public int getTravelTimeHelperHrs() {
        return travelTimeHelperHrs;
    }

    /**
     * @param travelTimeHelperHrs the travelTimeHelperHrs to set
     */
    public void setTravelTimeHelperHrs(int travelTimeHelperHrs) {
        this.travelTimeHelperHrs = travelTimeHelperHrs;
    }

    /**
     * @return the travelTimeHelperMins
     */
    public int getTravelTimeHelperMins() {
        return travelTimeHelperMins;
    }

    /**
     * @param travelTimeHelperMins the travelTimeHelperMins to set
     */
    public void setTravelTimeHelperMins(int travelTimeHelperMins) {
        this.travelTimeHelperMins = travelTimeHelperMins;
    }

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
    public int getTravelDistanceKm() {
        return travelDistanceKm;
    }

    /**
     * @param travelDistanceKm the travelDistanceKm to set
     */
    public void setTravelDistanceKm(int travelDistanceKm) {
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

    /**
     * @return the accommodationHrs
     */
    public int getAccommodationHrs() {
        return accommodationHrs;
    }

    /**
     * @param accommodationHrs the accommodationHrs to set
     */
    public void setAccommodationHrs(int accommodationHrs) {
        this.accommodationHrs = accommodationHrs;
    }

    /**
     * @return the accommodationMins
     */
    public int getAccommodationMins() {
        return accommodationMins;
    }

    /**
     * @param accommodationMins the accommodationMins to set
     */
    public void setAccommodationMins(int accommodationMins) {
        this.accommodationMins = accommodationMins;
    }

    /**
     * @return the overtimeHrs
     */
    public int getOvertimeHrs() {
        return overtimeHrs;
    }

    /**
     * @param overtimeHrs the overtimeHrs to set
     */
    public void setOvertimeHrs(int overtimeHrs) {
        this.overtimeHrs = overtimeHrs;
    }

    /**
     * @return the overtimeMins
     */
    public int getOvertimeMins() {
        return overtimeMins;
    }

    /**
     * @param overtimeMins the overtimeMins to set
     */
    public void setOvertimeMins(int overtimeMins) {
        this.overtimeMins = overtimeMins;
    }

    /**
     * @return the dirtyHrs
     */
    public int getDirtyHrs() {
        return dirtyHrs;
    }

    /**
     * @param dirtyHrs the dirtyHrs to set
     */
    public void setDirtyHrs(int dirtyHrs) {
        this.dirtyHrs = dirtyHrs;
    }

    /**
     * @return the dirtyMins
     */
    public int getDirtyMins() {
        return dirtyMins;
    }

    /**
     * @param dirtyMins the dirtyMins to set
     */
    public void setDirtyMins(int dirtyMins) {
        this.dirtyMins = dirtyMins;
    }

    /**
     * @return the idAnlagen
     */
    public int getIdAnlagen() {
        return idAnlagen;
    }

    /**
     * @param idAnlagen the idAnlagen to set
     */
    public void setIdAnlagen(int idAnlagen) {
        this.idAnlagen = idAnlagen;
    }

    /**
     * @return the idRepairTeile
     */
    public int getIdRepairTeile() {
        return idRepairTeile;
    }

    /**
     * @param idRepairTeile the idRepairTeile to set
     */
    public void setIdRepairTeile(int idRepairTeile) {
        this.idRepairTeile = idRepairTeile;
    }
}
