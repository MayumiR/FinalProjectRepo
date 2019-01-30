package com.bit.sfa.Settings;

/**
 * Created by Sathiyaraja on 6/19/2018.
 */

public enum TaskType {

    DATABASENAME(1),
    FMSALREP(2),
    FCONTROL(3),
    FITEMLOC(4),
    FITENRDET(5),
    FITENRHED(6),
    FITEDEBDET(7),
    FITEMPRI(8),
    FITEMS(9),
    FCOMPANYSETTING(10),
    FREPDALO(11),
    FSUPPLIER(12),
    FAREA(13),
    //FLOCATIONS(13),
    FCOMPANYBRANCH(14),
    FREASON(15),
    FROUTE(16),
    FEXPENSE(17),
    FTOWN(18),
    FROUTEDET(19),
    FTYPE(20),
    FGROUP(21),
    FBRAND(22),
    FINVDETL3(23),
    FCOST(24),
    FREPLOC(25),
    FMITEMS(26),
    FMITEMS_DET(27),
    FCOUNTRYMGR(28),
    FMAREA(29),
    FMAREASUB(30),
    FMDEBTOR(31),
    FMDEBTORDET(32),
    FMEXP_GRP(33),
    FITNDEBDET(36),
    FMISS_SUBDET(37),
    FMISS_HED(38),
    FMISS_DET(39),
    FORDSTAT(40),
    UPLOAD_NEW_CUSTOMER(41),
    UPLOAD_PROMO_ORDER(42),
    UPLOAD_ISSUE_NOTE(43);

    int value;

    private TaskType(int value) {
        this.value = value;
    }

}
