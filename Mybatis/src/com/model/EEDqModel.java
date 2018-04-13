package com.model;

import java.util.Date;

public class EEDqModel {
    private String id;

    private String farmId;

    private Integer dataType;

    private String preDate;

    private String dataTime;

    private Float prePower;

    private Float truePower;

    private Float setPower;

    private Date sysDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId == null ? null : farmId.trim();
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getPreDate() {
        return preDate;
    }

    public void setPreDate(String preDate) {
        this.preDate = preDate == null ? null : preDate.trim();
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime == null ? null : dataTime.trim();
    }

    public Float getPrePower() {
        return prePower;
    }

    public void setPrePower(Float prePower) {
        this.prePower = prePower;
    }

    public Float getTruePower() {
        return truePower;
    }

    public void setTruePower(Float truePower) {
        this.truePower = truePower;
    }

    public Float getSetPower() {
        return setPower;
    }

    public void setSetPower(Float setPower) {
        this.setPower = setPower;
    }

    public Date getSysDate() {
        return sysDate;
    }

    public void setSysDate(Date sysDate) {
        this.sysDate = sysDate;
    }
}