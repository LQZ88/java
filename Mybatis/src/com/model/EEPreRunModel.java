package com.model;

import java.math.BigDecimal;

public class EEPreRunModel {
    private BigDecimal id;

    private String farmId;

    private String runDate;

    private String runTime;

    private Float theoryPower;

    private Float cap;

    private Float avgcap;

    private Float fdl;

    private Float swdl;

    private String dataType;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId == null ? null : farmId.trim();
    }

    public String getRunDate() {
        return runDate;
    }

    public void setRunDate(String runDate) {
        this.runDate = runDate == null ? null : runDate.trim();
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime == null ? null : runTime.trim();
    }

    public Float getTheoryPower() {
        return theoryPower;
    }

    public void setTheoryPower(Float theoryPower) {
        this.theoryPower = theoryPower;
    }

    public Float getCap() {
        return cap;
    }

    public void setCap(Float cap) {
        this.cap = cap;
    }

    public Float getAvgcap() {
        return avgcap;
    }

    public void setAvgcap(Float avgcap) {
        this.avgcap = avgcap;
    }

    public Float getFdl() {
        return fdl;
    }

    public void setFdl(Float fdl) {
        this.fdl = fdl;
    }

    public Float getSwdl() {
        return swdl;
    }

    public void setSwdl(Float swdl) {
        this.swdl = swdl;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }
}