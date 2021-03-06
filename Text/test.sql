select count(*) from DM6SJ.EEBAS_FARM_INFO;
select count(*) from DM6SJ.EEWPF_CDQ_RESULT_UP;
select count(*) from DM6SJ.EEWPF_DQ_RESULT_UP;
select count(*) from DM6SJ.EEWPF_PRE_RUN_DATA;
select count(*) from DM6SJ.EE_STATISTICS_FARM;

select * from YWXT.XRH_DC_BASE_PARAMS_AUDIT_XTYX where BUSINESS_KEY=368;
select * from YWXT.XRH_TIANBAOREN where BUSINESS_KEY=368;
select * from YWXT.XRH_BASE_OBJ where OBJ_ID =7051;
select * from YWXT.XRH_ONE_FREQUENCY_PARAMETER where ID=368;
select * from YWXT.XRH_DC_BASE_PARAMS_AUDIT where BUSINESS_KEY =368;


insert into YWXT.XRH_DC_BASE_PARAMS_AUDIT_XTYX (ID,BUSINESS_TYPE,BUSINESS_KEY,IS_AUDIT,AUDIT_USER,AUDIT_TIME,AUDIT_INFO,UPDATE_TIME,CREATE_TIME,PHONES) values (562, 10014, 368, 1, '李甘', '2017-12-27', 'OMS导入', null, '2017-12-27 10:07:30.088000', null);
insert into YWXT.XRH_TIANBAOREN (TB_ID,TB_NAME,BUSINESS_TYPE,BUSINESS_KEY,TB_TIME,UPDATE_TIME,CREATE_TIME,PHONES) values (384, '罗小晶', 10014, 368, '2017-07-10', '2017-12-27 10:20:32.761000', '2017-12-27 10:07:29.818000', '13709022791');
insert into YWXT.XRH_BASE_OBJ (OBJ_ID,OBJ_NAME,METE_CODE,OBJ_VALUE,DC_ID,DC_GROUP_ID,OBJ_PARENT_ID,DELETE_FLAG,OMS_ID,DDMM,CREATE_TIME,UPDATE_TIME,DYDJ,MVARATE) values (7051, '四川.长河坝厂/20kV.#2机组', 'MUD_SB_GENERATINGUNIT', '长河坝#2机组', 1444, null, 6237, null, 'FCBFFB2C-4AEA-07E1-C420-BD16D23B8B6A-00001', null, '2016-12-17 03:00:00.674232', '2017-12-27 10:20:31.899000', 20, 650.00);
insert into YWXT.XRH_ONE_FREQUENCY_PARAMETER (ID,DC_ID,CREATE_USER_ID,OBJ_ID,CREATE_TIME,UPDATE_TIME,UNIQUE_ID,STATUS_N_O,CHANGE_FIELD,RMJZCS,EDRL,YCTPDZSQ,YTZCL,ZDTZFHX,FHXYZHSJ,YCTPWDSJ,CDDW,CSSJ,YCTPGNTR,REMARK,EDGL,TSQKPIDCS,DSQGYSQ,YCTQQLSQ,ZSSQHCHL,FHDDMBSJ,YCTQDZDBH,SDDSKZMS,SDEDST,SDDSKZLX,SDRMJZXS,YCTPRGSQ,SUBMITSTATUS,DSQGYSQFZ,YCTQQLSQFZ,YCTPRGSQFZ,ZDFHZXHDW,SDXFFS,SDXFDATE,OMSID,STATEOMS) values (368, 1444, 2, 7051, '2017-12-27 10:07:30.028000', '2017-12-27 10:20:32.781000', null, 1, null, null, null, null, '4', '-65', '0.8', '15.2', '四川省电力工业调整试验所', '', '001', null, '650', 'Kp=5,Ki=7,Kd=0', '+0.002', '+0.042', '+0.004/-0.008', '8.6', '[YCTP]-四川.长河坝厂-170710-2', '001', '200', '', '', '0.04', '1', '-0.004', '-0.04', '-0.044', 'MW', '001', '6%', '3357576C-8676-40B4-8C7D-F3A12E79C537-88665', 1);
insert into YWXT.XRH_DC_BASE_PARAMS_AUDIT (ID,BUSINESS_TYPE,BUSINESS_KEY,IS_AUDIT,AUDIT_USER,AUDIT_TIME,AUDIT_INFO,UPDATE_TIME,CREATE_TIME,PHONES) values (1151, 10014, 368, 1, '李甘', '2017-12-27', 'OMS导入', null, '2017-12-27 10:07:30.068000', null);


