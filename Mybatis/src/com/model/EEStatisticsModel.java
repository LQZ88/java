package com.model;

import java.math.BigDecimal;
import java.util.Date;

public class EEStatisticsModel {
    private String id;

    private String farmName;

    private String otherName;

    private BigDecimal cap;

    private String lvrtMark;

    private String activeMark;

    private String reactiveMark;

    private String dataType;

    private String nwpNum;

    private Integer ycId;

    private Integer nwpId;

    private Integer methodId;

    private String farmFlag;

    private Integer seqno;

    private String owner;

    private String city;

    private BigDecimal landmeasure;

    private String powerdispatchname;

    private String dispatchnature;

    private BigDecimal designcap;

    private BigDecimal realcap;

    private String fristgriddate;

    private String fullgriddate;

    private Integer gridcrewnum;

    private BigDecimal utilizationhours;

    private String voltagelevel;

    private BigDecimal reactorcapacity;

    private BigDecimal capacitorcapcity;

    private BigDecimal svccapcity;

    private BigDecimal svgcapcity;

    private BigDecimal othercapcity;

    private String checkMark;

    private Integer monitornum;

    private String factory1;

    private String factory2;

    private String factory3;

    private String factory4;

    private Integer version;

    private String state;

    private String x;

    private String y;

    private String griddate;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName == null ? null : farmName.trim();
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName == null ? null : otherName.trim();
    }

    public BigDecimal getCap() {
        return cap;
    }

    public void setCap(BigDecimal cap) {
        this.cap = cap;
    }

    public String getLvrtMark() {
        return lvrtMark;
    }

    public void setLvrtMark(String lvrtMark) {
        this.lvrtMark = lvrtMark == null ? null : lvrtMark.trim();
    }

    public String getActiveMark() {
        return activeMark;
    }

    public void setActiveMark(String activeMark) {
        this.activeMark = activeMark == null ? null : activeMark.trim();
    }

    public String getReactiveMark() {
        return reactiveMark;
    }

    public void setReactiveMark(String reactiveMark) {
        this.reactiveMark = reactiveMark == null ? null : reactiveMark.trim();
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    public String getNwpNum() {
        return nwpNum;
    }

    public void setNwpNum(String nwpNum) {
        this.nwpNum = nwpNum == null ? null : nwpNum.trim();
    }

    public Integer getYcId() {
        return ycId;
    }

    public void setYcId(Integer ycId) {
        this.ycId = ycId;
    }

    public Integer getNwpId() {
        return nwpId;
    }

    public void setNwpId(Integer nwpId) {
        this.nwpId = nwpId;
    }

    public Integer getMethodId() {
        return methodId;
    }

    public void setMethodId(Integer methodId) {
        this.methodId = methodId;
    }

    public String getFarmFlag() {
        return farmFlag;
    }

    public void setFarmFlag(String farmFlag) {
        this.farmFlag = farmFlag == null ? null : farmFlag.trim();
    }

    public Integer getSeqno() {
        return seqno;
    }

    public void setSeqno(Integer seqno) {
        this.seqno = seqno;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public BigDecimal getLandmeasure() {
        return landmeasure;
    }

    public void setLandmeasure(BigDecimal landmeasure) {
        this.landmeasure = landmeasure;
    }

    public String getPowerdispatchname() {
        return powerdispatchname;
    }

    public void setPowerdispatchname(String powerdispatchname) {
        this.powerdispatchname = powerdispatchname == null ? null : powerdispatchname.trim();
    }

    public String getDispatchnature() {
        return dispatchnature;
    }

    public void setDispatchnature(String dispatchnature) {
        this.dispatchnature = dispatchnature == null ? null : dispatchnature.trim();
    }

    public BigDecimal getDesigncap() {
        return designcap;
    }

    public void setDesigncap(BigDecimal designcap) {
        this.designcap = designcap;
    }

    public BigDecimal getRealcap() {
        return realcap;
    }

    public void setRealcap(BigDecimal realcap) {
        this.realcap = realcap;
    }

    public String getFristgriddate() {
        return fristgriddate;
    }

    public void setFristgriddate(String fristgriddate) {
        this.fristgriddate = fristgriddate == null ? null : fristgriddate.trim();
    }

    public String getFullgriddate() {
        return fullgriddate;
    }

    public void setFullgriddate(String fullgriddate) {
        this.fullgriddate = fullgriddate == null ? null : fullgriddate.trim();
    }

    public Integer getGridcrewnum() {
        return gridcrewnum;
    }

    public void setGridcrewnum(Integer gridcrewnum) {
        this.gridcrewnum = gridcrewnum;
    }

    public BigDecimal getUtilizationhours() {
        return utilizationhours;
    }

    public void setUtilizationhours(BigDecimal utilizationhours) {
        this.utilizationhours = utilizationhours;
    }

    public String getVoltagelevel() {
        return voltagelevel;
    }

    public void setVoltagelevel(String voltagelevel) {
        this.voltagelevel = voltagelevel == null ? null : voltagelevel.trim();
    }

    public BigDecimal getReactorcapacity() {
        return reactorcapacity;
    }

    public void setReactorcapacity(BigDecimal reactorcapacity) {
        this.reactorcapacity = reactorcapacity;
    }

    public BigDecimal getCapacitorcapcity() {
        return capacitorcapcity;
    }

    public void setCapacitorcapcity(BigDecimal capacitorcapcity) {
        this.capacitorcapcity = capacitorcapcity;
    }

    public BigDecimal getSvccapcity() {
        return svccapcity;
    }

    public void setSvccapcity(BigDecimal svccapcity) {
        this.svccapcity = svccapcity;
    }

    public BigDecimal getSvgcapcity() {
        return svgcapcity;
    }

    public void setSvgcapcity(BigDecimal svgcapcity) {
        this.svgcapcity = svgcapcity;
    }

    public BigDecimal getOthercapcity() {
        return othercapcity;
    }

    public void setOthercapcity(BigDecimal othercapcity) {
        this.othercapcity = othercapcity;
    }

    public String getCheckMark() {
        return checkMark;
    }

    public void setCheckMark(String checkMark) {
        this.checkMark = checkMark == null ? null : checkMark.trim();
    }

    public Integer getMonitornum() {
        return monitornum;
    }

    public void setMonitornum(Integer monitornum) {
        this.monitornum = monitornum;
    }

    public String getFactory1() {
        return factory1;
    }

    public void setFactory1(String factory1) {
        this.factory1 = factory1 == null ? null : factory1.trim();
    }

    public String getFactory2() {
        return factory2;
    }

    public void setFactory2(String factory2) {
        this.factory2 = factory2 == null ? null : factory2.trim();
    }

    public String getFactory3() {
        return factory3;
    }

    public void setFactory3(String factory3) {
        this.factory3 = factory3 == null ? null : factory3.trim();
    }

    public String getFactory4() {
        return factory4;
    }

    public void setFactory4(String factory4) {
        this.factory4 = factory4 == null ? null : factory4.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x == null ? null : x.trim();
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y == null ? null : y.trim();
    }

    public String getGriddate() {
        return griddate;
    }

    public void setGriddate(String griddate) {
        this.griddate = griddate == null ? null : griddate.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}