package com.bit.sfa.Settings;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Sathiyaraja on 6/19/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // database information
    public static final String DATABASE_NAME = "sfa_database.db";
    public static final int DATABASE_VERSION = 2;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * ############################ server table Details
     * #################################
     */
    // table
    public static final String TABLE_SERVER_DB = "serverdb";
    // table attributes
    public static final String SERVER_DB_ID = "server_db_id";
    public static final String SERVER_DB_NAME = "server_db_name";

    private static final String CREATE_SERVER_DB_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_SERVER_DB + " (" + SERVER_DB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SERVER_DB_NAME + " TEXT); ";

	/*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-FOrdDisc table details*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    public static final String TABLE_FORDDISC = "FOrdDisc";
    public static final String FORDDISC_REFNO = "RefNo";
    public static final String FORDDISC_TXNDATE = "TxnDate";
    public static final String FORDDISC_REFNO1 = "RefNo1";
    public static final String FORDDISC_ITEMCODE = "itemcode";
    public static final String FORDDISC_DISAMT = "DisAmt";
    public static final String FORDDISC_DISPER = "DisPer";

    public static final String CREATE_FORDDISC_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_FORDDISC + " (" + FORDDISC_REFNO + " TEXT, " + FORDDISC_TXNDATE + " TEXT, " + FORDDISC_REFNO1 + " TEXT, " + FORDDISC_ITEMCODE + " TEXT, " + FORDDISC_DISAMT + " TEXT, " + FORDDISC_DISPER + " TEXT ); ";

	/*-*-*-*-*-*-*-*-*-*-*-*-*-FOrdFreeIss table info-*-**-**-**-**-**-**-**-*-*-*-*/

    public static final String TABLE_FORDFREEISS = "FOrdFreeIss";
    public static final String FORDFREEISS_REFNO = "RefNo";
    public static final String FORDFREEISS_TXNDATE = "TxnDate";
    public static final String FORDFREEISS_REFNO1 = "RefNo1";
    public static final String FORDFREEISS_ITEMCODE = "ItemCode";
    public static final String FORDFREEISS_QTY = "Qty";

    public static final String CREATE_FORDFREEISS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_FORDFREEISS + " (" + FORDFREEISS_REFNO + " TEXT, " + FORDFREEISS_TXNDATE + " TEXT, " + FORDFREEISS_REFNO1 + " TEXT, " + FORDFREEISS_ITEMCODE + " TEXT, " + FORDFREEISS_QTY + " TEXT ); ";

    //--------------------2018-25-6 Sathya added FDistricts------------------------------------

    // table
    public static final String TABLE_FDISTRICT = "fDistrict";
    // table attributes
    public static final String FDISTRICT_ID = "_id";
    public static final String FDISTRICT_CODE = "DistrCode";
    public static final String FDISTRICT_NAME = "DistrName";
    public static final String FDISTRICT_PROVECODE = "ProvCode";

    private static final String CREATE_FDISTRICT = "CREATE  TABLE IF NOT EXISTS " + TABLE_FDISTRICT + " ("
            + FDISTRICT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FDISTRICT_NAME + " TEXT, "
            + FDISTRICT_CODE + " TEXT, "
            + FDISTRICT_PROVECODE + " TEXT); ";
    private static final String INDEX_FDISTRICT = "CREATE UNIQUE INDEX IF NOT EXISTS fDistrictIN ON " + TABLE_FDISTRICT + " (DistrCode,DistrName);";

	/*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**-**-**-**-**-**-**-*-*-*-*/

    /**
     * ############################ fDebtor table Details
     * ################################
     */

    public static final String TABLE_FDEBTOR = "fDebtor";
    // table attributes
    public static final String FDEBTOR_ID = "_id";
    public static final String FDEBTOR_CODE = "DebCode";
    public static final String FDEBTOR_NAME = "DebName";
    public static final String FDEBTOR_ADD1 = "DebAdd1";
    public static final String FDEBTOR_ADD2 = "DebAdd2";
    public static final String FDEBTOR_ADD3 = "DebAdd3";
    public static final String FDEBTOR_TELE = "DebTele";
    public static final String FDEBTOR_MOB = "DebMob";
    public static final String FDEBTOR_EMAIL = "DebEMail";
    public static final String FDEBTOR_CREATEDATE = "CretDate";
    public static final String FDEBTOR_TOWN_CODE = "TownCode";
    public static final String FDEBTOR_AREA_CODE = "AreaCode";
    public static final String FDEBTOR_DBGR_CODE = "DbGrCode";
    public static final String FDEBTOR_STATUS = "Status";
    public static final String FDEBTOR_ADD_DATE_DEB = "AddDateDEB";
    public static final String FDEBTOR_CRD_PERIOD = "CrdPeriod";
    public static final String FDEBTOR_CHK_CRD_PRD = "ChkCrdPrd";
    public static final String FDEBTOR_CRD_LIMIT = "CrdLimit";
    public static final String FDEBTOR_CHK_CRD_LIMIT = "ChkCrdLmt";
    public static final String FDEBTOR_REP_CODE = "RepCode";
    public static final String FDEBTOR_RANK_CODE = "RankCode";
    public static final String FDEBTOR_TAXREG = "TaxReg";
    public static final String FDEBTOR_PRIL_CODE = "PrilCode";
    public static final String FDEBTOR_ROUTE_CODE = "RouteCode";
    public static final String FDEBTOR_SUMMARY = "DebSumary";
    public static final String FDEBTOR_CHK_MUSTFREE = "ChkMustFree";
    public static final String FDEBTOR_CHK_FREE = "ChkFree";


    // table
    public static final String TABLE_FMDEBTOR = "fmDebtor";
    // table attributes
    public static final String FDEBTOR_CODEM = "DebCodeM";
    public static final String FDEBTOR_NAMEM = "DebNameM";
    public static final String FDEBTOR_REPCODEM = "RepCodem";
    public static final String FDEBTOR_ADD_USER = "AddUser";
    public static final String FDEBTOR_ADD_MACH = "AddMach";
    public static final String FDEBTOR_ADD_DATE = "AddDate";
    public static final String FDEBTOR_MROUTE_CODE = "RouteCode";
    public static final String FDEBTOR_RECORD_ID = "Recordid";

    // create String
    private static final String CREATE_FDEBTOR_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_FMDEBTOR + " (" + FDEBTOR_CODEM + " TEXT, " + FDEBTOR_NAMEM +
            " TEXT, " + FDEBTOR_REPCODEM + " TEXT, " + FDEBTOR_ADD_USER + " TEXT, " + FDEBTOR_ADD_MACH + " TEXT, " + FDEBTOR_ADD_DATE + " TEXT, "+ FDEBTOR_MROUTE_CODE + " TEXT, " + FDEBTOR_RECORD_ID + " TEXT); ";


    // table
    public static final String TABLE_FMISS_SUBDET = "FmissSubDet";
    // table attributes
    public static final String FMISS_SUBDET_REFNO = "Refn";
    public static final String FMISS_SUBDET_ITEMCODE = "ItemCode";
    public static final String FMISS_SUBDET_GITEMCODE = "GItemCode";
    public static final String FMISS_SUBDET_COSTPRICE = "CostPrice";
    public static final String FMISS_SUBDET_QTY = "Qty";
    public static final String FMISS_SUBDET_AMT = "Amt";
    public static final String FMISS_SUBDET_SQNO = "SeqNo";
    public static final String FMISS_SUBDET_RECORDID = "RecordId";


    // create String
    private static final String CREATE_FMISS_SUBDET = "CREATE TABLE IF NOT EXISTS " + TABLE_FMISS_SUBDET + " (" + FMISS_SUBDET_REFNO + " TEXT, "
            + FMISS_SUBDET_ITEMCODE + " TEXT, "
            + FMISS_SUBDET_GITEMCODE + " TEXT, "
            + FMISS_SUBDET_COSTPRICE + " TEXT, "
            + FMISS_SUBDET_QTY + " TEXT, "
            + FMISS_SUBDET_AMT + " TEXT, "
            + FMISS_SUBDET_SQNO + " TEXT, "
            + FMISS_SUBDET_RECORDID + " TEXT); ";


    // table
    public static final String TABLE_FMISS_HED = "Fmisshed";
    // table attributes
    public static final String FMISS_REFNO = "RefNo";
    public static final String FMISS_TXNDATE = "TxnDate";
    public static final String FMISS_MANUREF = "ManuRef";
    public static final String FMISS_COSTCODE = "CostCode";
    public static final String FMISS_LOCCODE = "LocCode";
    public static final String FMISS_DEBCODEM = "DebCodeM";
    public static final String FMISS_REPCODEM = "RepCodeM";
    public static final String FMISS_REMARKS = "Remarks";
    public static final String FMISS_TXNTYPE = "TxnType";
    public static final String FMISS_TOTALAMT = "TotalAmt";
    public static final String FMISS_GITYPE = "GIType";
    public static final String FMISS_GITYPES = "GITypeS";
    public static final String FMISS_PRTCOPY = "Prtcopy";
    public static final String FMISS_GIPOST = "GlPost";
    public static final String FMISS_GIBATCH = "GlBatch";
    public static final String FMISS_ADDUSER = "AddUser";
    public static final String FMISS_ADD_DATE = "AddDate";
    public static final String FMISS_ADD_MACH = "AddMach";
    public static final String FMISS_IS_ISSUE = "IsIssue";
    public static final String FMISS_ORD_REFNO = "OrderRefNo";
    public static final String FMISS_RECORDID = "RecordId";
    public static final String FMISS_IS_SYNC = "IsSync";

    // create String
    private static final String CREATE_FMISS_HED = "CREATE TABLE IF NOT EXISTS " + TABLE_FMISS_HED + " (" + FMISS_REFNO + " TEXT, "
            + FMISS_TXNDATE + " TEXT, "
            + FMISS_MANUREF + " TEXT, "
            + FMISS_COSTCODE + " TEXT, "
            + FMISS_LOCCODE + " TEXT, "
            + FMISS_DEBCODEM + " TEXT, "
            + FMISS_REPCODEM + " TEXT, "
            + FMISS_REMARKS + " TEXT, "
            + FMISS_TXNTYPE + " TEXT, "
            + FMISS_TOTALAMT + " TEXT, "
            + FMISS_GITYPE + " TEXT, "
            + FMISS_GITYPES + " TEXT, "
            + FMISS_PRTCOPY + " TEXT, "
            + FMISS_GIPOST + " TEXT, "
            + FMISS_GIBATCH + " TEXT, "
            + FMISS_ADDUSER + " TEXT, "
            + FMISS_ADD_DATE + " TEXT, "
            + FMISS_ADD_MACH + " TEXT, "
            + FMISS_IS_ISSUE + " TEXT DEFAULT 0, "
            + FMISS_IS_SYNC + " TEXT DEFAULT 0, "
            + FMISS_ORD_REFNO + " TEXT, "
            + FMISS_RECORDID + " TEXT); ";


    public static final String TABLE_FMISS_DET = "Fmissdet";
    // table attributes
    public static final String FMISS_DET_REFNO = "RefNo";
    public static final String FMISS_DET_TXNDATE = "TxnDate";
    public static final String FMISS_DET_TXNTYPE = "TxnType";
    public static final String FMISS_DET_SQNO = "SeqNo";
    public static final String FMISS_DET_GITEMCODE = "GItemCode";
    public static final String FMISS_DET_QTY = "Qty";
    public static final String FMISS_DET_COST_PRICE = "CostPrice";
    public static final String FMISS_DET_AMT = "Amt";

    // create String
    private static final String CREATE_FMISS_DET = "CREATE TABLE IF NOT EXISTS " + TABLE_FMISS_DET + " (" + FMISS_DET_REFNO + " TEXT, "
            + FMISS_DET_TXNDATE + " TEXT, "
            + FMISS_DET_TXNTYPE + " TEXT, "
            + FMISS_DET_SQNO + " TEXT, "
            + FMISS_DET_GITEMCODE + " TEXT, "
            + FMISS_DET_QTY + " TEXT, "
            + FMISS_DET_COST_PRICE + " TEXT, "
            + FMISS_DET_AMT + " TEXT); ";

    /**
     * ############################ fControl table Details
     * ################################
     */
    // table
    public static final String TABLE_FSUPPLIER = "fSupplier";
    // table attributes
    public static final String FSUPPLIER_ID = "FSup_id";
    public static final String FSUPPLIER_CODE = "SupCode";
    public static final String FSUPPLIER_SUPNAME = "SupName";

    // create String
    private static final String CREATE_TABLE_FSUPPLIER = "CREATE  TABLE IF NOT EXISTS " + TABLE_FSUPPLIER + " ("
            + FSUPPLIER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FSUPPLIER_CODE + " TEXT, " + FSUPPLIER_SUPNAME + " TEXT); ";


    // table
    public static final String TABLE_FREPDALO = "FrepDalo";
    // table attributes
    public static final String FREPDALO_ID = "FrepDalo_id";
    public static final String FREPDALO_CODE = "DebCode";
    public static final String FREPDALO_REPcODE = "RepCode";

    // create String
    private static final String CREATE_TABLE_FREPDALO = "CREATE  TABLE IF NOT EXISTS " + TABLE_FREPDALO + " ("
            + FREPDALO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FREPDALO_CODE + " TEXT, " + FREPDALO_REPcODE + " TEXT); ";


    // table
    public static final String TABLE_FCONTROL = "fControl";
    // table attributes
    public static final String FCONTROL_ID = "fcontrol_id";
    public static final String FCONTROL_COM_NAME = "ComName";
    public static final String FCONTROL_COM_ADD1 = "ComAdd1";
    public static final String FCONTROL_COM_ADD2 = "ComAdd2";
    public static final String FCONTROL_COM_ADD3 = "ComAdd3";
    public static final String FCONTROL_COM_TEL1 = "comtel1";
    public static final String FCONTROL_COM_TEL2 = "comtel2";
    public static final String FCONTROL_COM_FAX = "comfax1";
    public static final String FCONTROL_COM_EMAIL = "comemail";
    public static final String FCONTROL_COM_WEB = "comweb";
    public static final String FCONTROL_FYEAR = "confyear";
    public static final String FCONTROL_TYEAR = "contyear";
    public static final String FCONTROL_COM_REGNO = "comRegNo";
    public static final String FCONTROL_FTXN = "ConfTxn";
    public static final String FCONTROL_TTXN = "ContTxn";
    public static final String FCONTROL_CRYSTALPATH = "Crystalpath";
    public static final String FCONTROL_VATCMTAXNO = "VatCmTaxNo";
    public static final String FCONTROL_NBTCMTAXNO = "NbtCmTaxNo";
    public static final String FCONTROL_SYSTYPE = "SysType";
    public static final String FCONTROL_DEALCODE = "DealCode";
    public static final String FCONTROL_BASECUR = "basecur";
    public static final String FCONTROL_BALGCRLM = "BalgCrLm";
    public static final String FCONTROL_CONAGE1 = "conage1";
    public static final String FCONTROL_CONAGE2 = "conage2";
    public static final String FCONTROL_CONAGE3 = "conage3";
    public static final String FCONTROL_CONAGE4 = "conage4";
    public static final String FCONTROL_CONAGE5 = "conage5";
    public static final String FCONTROL_CONAGE6 = "conage6";
    public static final String FCONTROL_CONAGE7 = "conage7";
    public static final String FCONTROL_CONAGE8 = "conage8";
    public static final String FCONTROL_CONAGE9 = "conage9";
    public static final String FCONTROL_CONAGE10 = "conage10";
    public static final String FCONTROL_CONAGE11 = "conage11";
    public static final String FCONTROL_CONAGE12 = "conage12";
    public static final String FCONTROL_CONAGE13 = "conage13";
    public static final String FCONTROL_CONAGE14 = "conage14";
    public static final String FCONTROL_SALESACC = "salesacc";

    // create String
    private static final String CREATE_FCONTROL_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_FCONTROL + " (" + FCONTROL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FCONTROL_COM_NAME + " TEXT, " + FCONTROL_COM_ADD1 + " TEXT, " + FCONTROL_COM_ADD2 + " TEXT, " + FCONTROL_COM_ADD3 + " TEXT, " + FCONTROL_COM_TEL1 + " TEXT, " + FCONTROL_COM_TEL2 + " TEXT, " + FCONTROL_COM_FAX + " TEXT, " + FCONTROL_COM_EMAIL + " TEXT, " + FCONTROL_COM_WEB + " TEXT, " + FCONTROL_FYEAR + " TEXT, " + FCONTROL_TYEAR + " TEXT, " + FCONTROL_COM_REGNO + " TEXT, " + FCONTROL_FTXN + " TEXT, " + FCONTROL_TTXN + " TEXT, " + FCONTROL_CRYSTALPATH + " TEXT, " + FCONTROL_VATCMTAXNO + " TEXT, " + FCONTROL_NBTCMTAXNO + " TEXT, " + FCONTROL_SYSTYPE + " TEXT, " + FCONTROL_DEALCODE + " TEXT, " + FCONTROL_BASECUR + " TEXT, " + FCONTROL_BALGCRLM + " TEXT, " + FCONTROL_CONAGE1 + " TEXT, " + FCONTROL_CONAGE2 + " TEXT, " + FCONTROL_CONAGE3 + " TEXT, " + FCONTROL_CONAGE4 + " TEXT, " + FCONTROL_CONAGE5 + " TEXT, " + FCONTROL_CONAGE6 + " TEXT, " + FCONTROL_CONAGE7 + " TEXT, " + FCONTROL_CONAGE8 + " TEXT, " + FCONTROL_CONAGE9 + " TEXT, " + FCONTROL_CONAGE10 + " TEXT, " + FCONTROL_CONAGE11 + " TEXT, " + FCONTROL_CONAGE12 + " TEXT, " + FCONTROL_CONAGE13 + " TEXT, " + FCONTROL_CONAGE14 + " TEXT, " + FCONTROL_SALESACC + " TEXT); ";

    /**
     * ############################ fCompanySetting table Details
     * ################################
     */

    // table
    public static final String TABLE_FCOMPANYSETTING = "fCompanySetting";
    // table attributes
    public static final String FCOMPANYSETTING_ID = "fcomset_id";// ok
    public static final String FCOMPANYSETTING_SETTINGS_CODE = "cSettingsCode";// ok
    public static final String FCOMPANYSETTING_GRP = "cSettingGrp";// ok
    public static final String FCOMPANYSETTING_LOCATION_CHAR = "cLocationChar";// ok
    public static final String FCOMPANYSETTING_CHAR_VAL = "cCharVal";// ok
    public static final String FCOMPANYSETTING_NUM_VAL = "nNumVal";// ok
    public static final String FCOMPANYSETTING_REMARKS = "cRemarks";// ok
    public static final String FCOMPANYSETTING_TYPE = "nType";// ok
    // public static final String FCOMPANYSETTING_UPDATEFLAG = "bUpdateFlag";
    public static final String FCOMPANYSETTING_COMPANY_CODE = "cCompanyCode";// ok
    // public static final String FCOMPANYSETTING_RECORD_ID = "recordid";
    // public static final String FCOMPANYSETTING_TIMESTAMP_COLUMN =
    // "timestamp_column";

    // create String
    private static final String CREATE_FCOMPANYSETTING_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FCOMPANYSETTING + " (" + FCOMPANYSETTING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FCOMPANYSETTING_SETTINGS_CODE + " TEXT, " + FCOMPANYSETTING_GRP + " TEXT, " + FCOMPANYSETTING_LOCATION_CHAR + " TEXT, " + FCOMPANYSETTING_CHAR_VAL + " TEXT, " + FCOMPANYSETTING_NUM_VAL + " TEXT, " + FCOMPANYSETTING_REMARKS + " TEXT, " + FCOMPANYSETTING_TYPE + " TEXT, "
            // + FCOMPANYSETTING_UPDATEFLAG+ " TEXT, "
            + FCOMPANYSETTING_COMPANY_CODE + " TEXT); ";
    // + FCOMPANYSETTING_RECORD_ID+ " TEXT, "
    // + FCOMPANYSETTING_TIMESTAMP_COLUMN+ " TEXT); ";

    private static final String IDXCOMSETT = "CREATE UNIQUE INDEX IF NOT EXISTS idxcomsett_something ON " + TABLE_FCOMPANYSETTING + " (" + FCOMPANYSETTING_SETTINGS_CODE + ")";

    /**
     * ############################ fRoute table Details
     * ################################
     */

    // table
    public static final String TABLE_FROUTE = "fRoute";
    // table attributes
    public static final String FROUTE_ID = "route_id";
    public static final String FROUTE_REPCODE = "RepCode";
    public static final String FROUTE_ROUTECODE = "RouteCode";
    public static final String FROUTE_ROUTE_NAME = "RouteName";
    public static final String FROUTE_RECORDID = "RecordId";
    public static final String FROUTE_ADDDATE = "AddDate";
    public static final String FROUTE_ADD_MACH = "AddMach";
    public static final String FROUTE_ADD_USER = "AddUser";
    public static final String FROUTE_AREACODE = "AreaCode";
    public static final String FROUTE_DEALCODE = "DealCode";
    public static final String FROUTE_FREQNO = "FreqNo";
    public static final String FROUTE_KM = "Km";
    public static final String FROUTE_MINPROCALL = "MinProcall";
    public static final String FROUTE_RDALORATE = "RDAloRate";
    public static final String FROUTE_RDTARGET = "RDTarget";
    public static final String FROUTE_REMARKS = "Remarks";
    public static final String FROUTE_STATUS = "Status";
    public static final String FROUTE_TONNAGE = "Tonnage";

    // create String
    private static final String CREATE_FROUTE_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FROUTE + " (" + FROUTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FROUTE_REPCODE + " TEXT, " + FROUTE_ROUTECODE + " TEXT, " + FROUTE_ROUTE_NAME + " TEXT, " + FROUTE_RECORDID + " TEXT, " + FROUTE_ADDDATE + " TEXT, " + FROUTE_ADD_MACH + " TEXT, " + FROUTE_ADD_USER + " TEXT, " + FROUTE_AREACODE + " TEXT, " + FROUTE_DEALCODE + " TEXT, " + FROUTE_FREQNO + " TEXT, " + FROUTE_KM + " TEXT, " + FROUTE_MINPROCALL + " TEXT, " + FROUTE_RDALORATE + " TEXT, " + FROUTE_RDTARGET + " TEXT, " + FROUTE_REMARKS + " TEXT, " + FROUTE_STATUS + " TEXT, " + FROUTE_TONNAGE + " TEXT); ";

    /**
     * ############################ fBank table Details
     * ################################
     */

    // table
    public static final String TABLE_FBANK = "fBank";
    // table attributes
    public static final String FBANK_ID = "bankre_id";
    public static final String FBANK_RECORD_ID = "RecordId";
    public static final String FBANK_BANK_CODE = "bankcode";
    public static final String FBANK_BANK_NAME = "bankname";
    public static final String FBANK_BANK_ACC_NO = "bankaccno";
    public static final String FBANK_BRANCH = "Branch";
    public static final String FBANK_ADD1 = "bankadd1";
    public static final String FBANK_ADD2 = "bankadd2";
    public static final String FBANK_ADD_DATE = "AddDate";
    public static final String FBANK_ADD_MACH = "AddMach";
    public static final String FBANK_ADD_USER = "AddUser";

    // create String
    private static final String CREATE_FBANK_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FBANK + " (" + FBANK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FBANK_RECORD_ID + " TEXT, " + FBANK_BANK_CODE + " TEXT, " + FBANK_BANK_NAME + " TEXT, " + FBANK_BANK_ACC_NO + " TEXT, " + FBANK_BRANCH + " TEXT, " + FBANK_ADD1 + " TEXT, " + FBANK_ADD2 + " TEXT, " + FBANK_ADD_MACH + " TEXT, " + FBANK_ADD_USER + " TEXT, " + FBANK_ADD_DATE + " TEXT); ";

    private static final String TESTBANK = "CREATE UNIQUE INDEX IF NOT EXISTS idxbank_something ON " + TABLE_FBANK + " (" + FBANK_BANK_CODE + ")";

    /**
     * ############################ fReason table Details
     * ################################
     */

    public static final String TABLE_FREASON = "fReason";
    // table attributes
    public static final String FREASON_ID = "freason_id";
    public static final String FREASON_ADD_DATE = "AddDate";
    public static final String FREASON_ADD_MACH = "AddMach";
    public static final String FREASON_ADD_USER = "AddUser";
    public static final String FREASON_CODE = "ReaCode";
    public static final String FREASON_NAME = "ReaName";
    public static final String FREASON_REATCODE = "ReaTcode";
    public static final String FREASON_RECORD_ID = "RecordId";
    public static final String FREASON_TYPE = "Type";

    // create String
    private static final String CREATE_FREASON_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FREASON + " (" + FREASON_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FREASON_ADD_DATE + " TEXT, " + FREASON_ADD_MACH + " TEXT, " + FREASON_ADD_USER + " TEXT, " + FREASON_CODE + " TEXT, " + FREASON_NAME + " TEXT, " + FREASON_REATCODE + " TEXT, " + FREASON_RECORD_ID + " TEXT, " + FREASON_TYPE + " TEXT); ";

    /**
     * ############################ fExpense table Details
     * ################################
     */

    /**
     * ############################ fTown table Details
     * ################################
     */

    // table
    public static final String TABLE_FTOWN = "fTown";
    // table attributes
    public static final String FTOWN_ID = "townre_id";
    public static final String FTOWN_RECORDID = "RecordId";
    public static final String FTOWN_CODE = "TownCode";
    public static final String FTOWN_NAME = "TownName";
    public static final String FTOWN_DISTR_CODE = "DistrCode";
    public static final String FTOWN_ADDDATE = "AddDate";
    public static final String FTOWN_ADD_MACH = "AddMach";
    public static final String FTOWN_ADD_USER = "AddUser";

    // create String
    private static final String CREATE_FTOWN_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FTOWN + " (" + FTOWN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FTOWN_RECORDID + " TEXT, " + FTOWN_CODE + " TEXT, " + FTOWN_NAME + " TEXT, " + FTOWN_DISTR_CODE + " TEXT, " + FTOWN_ADDDATE + " TEXT, " + FTOWN_ADD_MACH + " TEXT, " + FTOWN_ADD_USER + " TEXT); ";

    /**
     * ############################ FTrgCapUL table Details
     * ################################
     */

    // table
    public static final String TABLE_FTRGCAPUL = "FTrgCapUL";
    // table attributes
    public static final String FTRGCAPUL_ID = "ftrg_id";
    public static final String FTRGCAPUL_ADD_DATE = "AddDate";
    public static final String FTRGCAPUL_ADD_MACH = "AddMach";
    public static final String FTRGCAPUL_ADD_USER = "AddUser";
    public static final String FTRGCAPUL_DEAL_CODE = "DealCode";
    public static final String FTRGCAPUL_MONTH = "Month";
    public static final String FTRGCAPUL_QTY = "Qty";
    public static final String FTRGCAPUL_RECORDID = "RecordId";
    public static final String FTRGCAPUL_REP_CODE = "RepCode";
    public static final String FTRGCAPUL_SKU_CODE = "SKUCode";
    public static final String FTRGCAPUL_YEAR = "Year";

    // create String
    private static final String CREATE_FTRGCAPUL_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FTRGCAPUL + " (" + FTRGCAPUL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FTRGCAPUL_ADD_DATE + " TEXT, " + FTRGCAPUL_ADD_MACH + " TEXT, " + FTRGCAPUL_ADD_USER + " TEXT, " + FTRGCAPUL_DEAL_CODE + " TEXT, " + FTRGCAPUL_MONTH + " TEXT, " + FTRGCAPUL_QTY + " TEXT, " + FTRGCAPUL_RECORDID + " TEXT, " + FTRGCAPUL_REP_CODE + " TEXT, " + FTRGCAPUL_SKU_CODE + " TEXT, " + FTRGCAPUL_YEAR + " TEXT); ";

    /**
     * ############################ fType table Details
     * ################################
     */

    // table
    public static final String TABLE_FTYPE = "fType";
    // table attributes
    public static final String FTYPE_ID = "ftype_id";
    public static final String FTYPE_ADD_DATE = "AddDate";
    public static final String FTYPE_ADD_MACH = "AddMach";
    public static final String FTYPE_ADD_USER = "AddUser";
    public static final String FTYPE_RECORDID = "RecordId";
    public static final String FTYPE_CODE = "TypeCode";
    public static final String FTYPE_NAME = "TypeName";

    // create String
    private static final String CREATE_FTYPE_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FTYPE + " (" + FTYPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FTYPE_ADD_DATE + " TEXT, " + FTYPE_ADD_MACH + " TEXT, " + FTYPE_ADD_USER + " TEXT, " + FTYPE_RECORDID + " TEXT, " + FTYPE_CODE + " TEXT, " + FTYPE_NAME + " TEXT); ";

    /**
     * ############################ fSubBrand table Details
     * ################################
     */

    // table
    public static final String TABLE_FSUBBRAND = "fSubBrand";
    // table attributes
    public static final String FSUBBRAND_ID = "fsubbrand_id";
    public static final String FSUBBRAND_ADD_DATE = "AddDate";
    public static final String FSUBBRAND_ADD_MACH = "AddMach";
    public static final String FSUBBRAND_ADD_USER = "AddUser";
    public static final String FSUBBRAND_RECORDID = "RecordId";
    public static final String FSUBBRAND_CODE = "SBrandCode";
    public static final String FSUBBRAND_NAME = "SBrandName";

    // create String
    private static final String CREATE_FSUBBRAND_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FSUBBRAND + " (" + FSUBBRAND_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FSUBBRAND_ADD_DATE + " TEXT, " + FSUBBRAND_ADD_MACH + " TEXT, " + FSUBBRAND_ADD_USER + " TEXT, " + FSUBBRAND_RECORDID + " TEXT, " + FSUBBRAND_CODE + " TEXT, " + FSUBBRAND_NAME + " TEXT); ";

    /**
     * ############################ fCost table Details
     * ################################
     */

    // table
    public static final String TABLE_FCOST = "fCost";
    // table attributes
    public static final String FCOST_ID = "fcost_id";
    public static final String FCOST_CODE = "CostCode";
    public static final String FCOST_NAME = "CostName";

    // create String
    private static final String CREATE_FCOST_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FCOST + " (" + FCOST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FCOST_CODE + " TEXT, " + FCOST_NAME + " TEXT); ";

    /**
     * ############################ fGroup table Details
     * ################################
     */

    // table
    public static final String TABLE_FGROUP = "fGroup";
    // table attributes
    public static final String FGROUP_ID = "fgroup_id";
    public static final String FGROUP_ADD_DATE = "AddDate";
    public static final String FGROUP_ADD_MACH = "AddMach";
    public static final String FGROUP_ADD_USER = "AddUser";
    public static final String FGROUP_CODE = "GroupCode";
    public static final String FGROUP_NAME = "GroupName";
    public static final String FGROUP_RECORDID = "RecordId";

    // create String
    private static final String CREATE_FGROUP_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FGROUP + " (" + FGROUP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FGROUP_ADD_DATE + " TEXT, " + FGROUP_ADD_MACH + " TEXT, " + FGROUP_ADD_USER + " TEXT, " + FGROUP_CODE + " TEXT, " + FGROUP_NAME + " TEXT, " + FGROUP_RECORDID + " TEXT); ";

    /**
     * ############################ fSku table Details
     * ################################
     */

    //table fmitems
    public static final String TABLE_FMITEMS = "fmItems";
    public static final String GITEM_CODE = "GItemCode";
    public static final String GITEM_NAME = "GItemName";
    public static final String UOM = "UOM";
    public static final String GITYPE = "GIType";
    public static final String GITYPES = "GITypeS";
    public static final String GITEM_NAMED = "GItemNameD";
    public static final String REMARKS = "Remarks";
    public static final String ADD_USER = "AddUser";
    public static final String ADD_DATE = "AddDate";
    public static final String ADD_MACH = "AddMach";
    public static final String RECORD_ID = "RecordId";

    private static final String CREATE_TABLE_FMITEMS = "CREATE  TABLE IF NOT EXISTS " + TABLE_FMITEMS + " (" + GITEM_CODE + " TEXT, " + GITEM_NAME + " TEXT, " + UOM +
            " TEXT, " + GITYPE + " TEXT, " + GITYPES + " TEXT, " + GITEM_NAMED + " TEXT, " + REMARKS +
            " TEXT, " + ADD_USER + " TEXT, " + ADD_DATE + " TEXT, " + ADD_MACH + " TEXT, " + RECORD_ID +
            " TEXT) ";
    //----------------------------------------------------------------------------------------------
    //table fmitemsDet
    public static final String TABLE_FMITEMS_DET = "fmitemsDet ";
    public static final String GITEMCODE = "GItemCode";
    public static final String ITEM_CODE = "ItemCode";
    public static final String RECORDID = "RecordId";

    private static final String CREATE_FMITEMS_DET = "CREATE  TABLE IF NOT EXISTS " + TABLE_FMITEMS_DET + " (" + GITEMCODE + " TEXT, " + ITEM_CODE + " TEXT, " + RECORDID +
            " TEXT) ";
    //----------------------------------------------------------------------------------------------

    //table fmitemsDet
    public static final String TABLE_FCOUNTRY_MGR = "fCountrymgr";
    public static final String COUNTRY_CODE = "CntryMCode";
    public static final String COUNTRY_NAME = "CntryMName";

    private static final String CREATE_FCOUNTRY_MGR = "CREATE  TABLE IF NOT EXISTS " + TABLE_FCOUNTRY_MGR + " (" + COUNTRY_CODE + " TEXT, " + COUNTRY_NAME + " TEXT, " + ADD_USER +
            " TEXT, " + ADD_DATE + " TEXT, " + ADD_MACH + " TEXT, " + RECORD_ID +
            " TEXT) ";
    //----------------------------------------------------------------------------------------------


    // table
    public static final String TABLE_FSKU = "fSku";
    // table attributes
    public static final String FSKU_ID = "fsku_id";
    public static final String FSKU_ADD_DATE = "AddDate";
    public static final String FSKU_ADD_MACH = "AddMach";
    public static final String FSKU_ADD_USER = "AddUser";
    public static final String FSKU_BRAND_CODE = "BrandCode";
    public static final String FSKU_GROUP_CODE = "GroupCode";
    public static final String FSKU_ITEM_STATUS = "ItemStatus";
    public static final String FSKU_MUST_SALE = "MustSale";
    public static final String FSKU_NOUCASE = "NOUCase";
    public static final String FSKU_ORDSEQ = "OrdSeq";
    public static final String FSKU_RECORDID = "RecordId";
    public static final String FSKU_SUB_BRAND_CODE = "SBrandCode";
    public static final String FSKU_CODE = "SKUCode";
    public static final String FSKU_NAME = "SkuName";
    public static final String FSKU_SIZE_CODE = "SkuSizCode";
    public static final String FSKU_TONNAGE = "Tonnage";
    public static final String FSKU_TYPE_CODE = "TypeCode";
    public static final String FSKU_UNIT = "Unit";

    // create String
    private static final String CREATE_FSKU_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FSKU + " (" + FSKU_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FSKU_ADD_DATE + " TEXT, " + FSKU_ADD_MACH + " TEXT, " + FSKU_ADD_USER + " TEXT, " + FSKU_BRAND_CODE + " TEXT, " + FSKU_GROUP_CODE + " TEXT, " + FSKU_ITEM_STATUS + " TEXT, " + FSKU_MUST_SALE + " TEXT, " + FSKU_NOUCASE + " TEXT, " + FSKU_ORDSEQ + " TEXT, " + FSKU_RECORDID + " TEXT, " + FSKU_SUB_BRAND_CODE + " TEXT, " + FSKU_CODE + " TEXT, " + FSKU_NAME + " TEXT, " + FSKU_SIZE_CODE + " TEXT, " + FSKU_TONNAGE + " TEXT, " + FSKU_TYPE_CODE + " TEXT, " + FSKU_UNIT + " TEXT); ";

    /**
     * ############################ fbrand table Details
     * ################################
     */

    // table
    public static final String TABLE_FBRAND = "fbrand";
    // table attributes
    public static final String FBRAND_ID = "fbrand_id";
    public static final String FBRAND_ADD_MACH = "AddMach";
    public static final String FBRAND_ADD_USER = "AddUser";
    public static final String FBRAND_CODE = "BrandCode";
    public static final String FBRAND_NAME = "BrandName";
    public static final String FBRAND_RECORDID = "RecordId";

    // create String
    private static final String CREATE_FBRAND_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FBRAND + " (" + FBRAND_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FBRAND_ADD_MACH + " TEXT, " + FBRAND_ADD_USER + " TEXT, " + FBRAND_CODE + " TEXT, " + FBRAND_NAME + " TEXT, " + FBRAND_RECORDID + " TEXT); ";

    /**
     * ############################ FOrdHed table Details
     * ################################
     */

    // table
    public static final String TABLE_FORDHED = "FOrdHed";
    // table attributes
    public static final String FORDHED_ID = "FOrdHed_id";
    public static final String FORDHED_REFNO = "RefNo";
    public static final String FORDHED_ADD_DATE = "AddDate";
    public static final String FORDHED_ADD_MACH = "AddMach";
    public static final String FORDHED_ADD_USER = "AddUser";
    public static final String FORDHED_APP_DATE = "Appdate";
    public static final String FORDHED_APPSTS = "Appsts";
    public static final String FORDHED_APP_USER = "AppUser";
    public static final String FORDHED_BP_TOTAL_DIS = "BPTotalDis";
    public static final String FORDHED_B_TOTAL_AMT = "BTotalAmt";
    public static final String FORDHED_B_TOTAL_DIS = "BTotalDis";
    public static final String FORDHED_B_TOTAL_TAX = "BTotalTax";
    public static final String FORDHED_COST_CODE = "CostCode";
    public static final String FORDHED_CUR_CODE = "CurCode";
    public static final String FORDHED_CUR_RATE = "CurRate";
    public static final String FORDHED_DEB_CODE = "DebCode";
    public static final String FORDHED_DIS_PER = "DisPer";
    public static final String FORDHED_START_TIME_SO = "startTimeSO";
    public static final String FORDHED_END_TIME_SO = "endTimeSO";
    public static final String FORDHED_LONGITUDE = "Longitude";
    public static final String FORDHED_LATITUDE = "Latitude";
    public static final String FORDHED_LOC_CODE = "LocCode";
    public static final String FORDHED_MANU_REF = "ManuRef";
    public static final String FORDHED_RECORD_ID = "RecordId";
    public static final String FORDHED_REMARKS = "Remarks";
    public static final String FORDHED_REPCODE = "RepCode";
    public static final String FORDHED_TAX_REG = "TaxReg";
    public static final String FORDHED_TIMESTAMP_COLUMN = "Timestamp_column";
    public static final String FORDHED_TOTAL_AMT = "TotalAmt";
    public static final String FORDHED_TOTALDIS = "TotalDis";
    public static final String FORDHED_TOTAL_TAX = "TotalTax";
    public static final String FORDHED_TXN_TYPE = "TxnType";
    public static final String FORDHED_TXN_DATE = "TxnDate";
    public static final String FORDHED_ADDRESS = "gpsAddress";
    public static final String FORDHED_TOTAL_ITM_DIS = "TotalItemDis";
    public static final String FORDHED_TOT_MKR_AMT = "TotMkrAmt";
    public static final String FORDHED_IS_SYNCED = "isSynced";
    public static final String FORDHED_IS_ACTIVE = "isActive";
    public static final String FORDHED_DELV_DATE = "DelvDate";
    public static final String FORDHED_HED_DIS_VAL = "HedDisVal";
    public static final String FORDHED_HED_DIS_PER_VAL = "HedDisPerVal";
    public static final String FORDHED_ROUTE_CODE = "RouteCode";
    public static final String FORDHED_PAYMENT_TYPE = "PaymentType";
    public static final String FORDHED_UPLOAD_TIME = "UploadTime";

    //------------------ temp table crate for PreSales detail data saved------------------------------------
    public static final String TABLE_FPRODUCT_PRE = "fProducts_pre";
    public static final String FPRODUCT_ID_PRE = "id";
    public static final String FPRODUCT_ITEMCODE_PRE = "itemcode_pre";
    public static final String FPRODUCT_ITEMNAME_PRE = "itemname_pre";
    public static final String FPRODUCT_PRICE_PRE = "price_pre";
    public static final String FPRODUCT_QOH_PRE = "qoh_pre";
    public static final String FPRODUCT_QTY_PRE = "qty_pre";   //------------------ temp table crate for PreSales detail data saved------------------------------------

    // create String
    private static final String CREATE_FORDHED_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_FORDHED + " (" + FORDHED_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FORDHED_ADD_MACH + " TEXT, " + FORDHED_ADD_DATE + " TEXT," + FORDHED_ADD_USER + " TEXT, " + FORDHED_APP_DATE + " TEXT, " + FORDHED_ADDRESS + " TEXT, " + FORDHED_APPSTS + " TEXT, " + FORDHED_APP_USER + " TEXT, " + FORDHED_BP_TOTAL_DIS + " TEXT, " + FORDHED_B_TOTAL_AMT + " TEXT, " + FORDHED_B_TOTAL_DIS + " TEXT, " + FORDHED_B_TOTAL_TAX + " TEXT, " + FORDHED_COST_CODE + " TEXT, " + FORDHED_CUR_CODE + " TEXT, " + FORDHED_CUR_RATE + " TEXT, " + FORDHED_DEB_CODE + " TEXT, " + FORDHED_LOC_CODE + " TEXT, " + FORDHED_MANU_REF + " TEXT, " + FORDHED_DIS_PER + " TEXT, " + FORDHED_RECORD_ID + " TEXT, " + FORDHED_REFNO + " TEXT, " + FORDHED_REMARKS + " TEXT, " + FORDHED_REPCODE + " TEXT, " + FORDHED_TAX_REG + " TEXT, " + FORDHED_TIMESTAMP_COLUMN + " TEXT, " + FORDHED_TOTAL_TAX + " TEXT, " + FORDHED_TOTAL_AMT + " TEXT, " + FORDHED_TOTALDIS + " TEXT, " + FORDHED_TOTAL_ITM_DIS + " TEXT, " + FORDHED_TOT_MKR_AMT + " TEXT, " + FORDHED_TXN_TYPE + " TEXT, " + FORDHED_TXN_DATE + " TEXT, " + FORDHED_LONGITUDE + " TEXT, " + FORDHED_LATITUDE + " TEXT, " + FORDHED_START_TIME_SO + " TEXT, " + FORDHED_IS_SYNCED + " TEXT, " + FORDHED_IS_ACTIVE + " TEXT, " + FORDHED_DELV_DATE + " TEXT, " + FORDHED_ROUTE_CODE + " TEXT, " + FORDHED_HED_DIS_VAL + " TEXT, " + FORDHED_HED_DIS_PER_VAL + " TEXT," + FORDHED_PAYMENT_TYPE + " TEXT," + FORDHED_END_TIME_SO + " TEXT," + FORDHED_UPLOAD_TIME + " TEXT); ";

    /**
     * ############################ FOrddet table Details
     * ################################
     */

    // table
    public static final String TABLE_FORDDET = "FOrddet";
    // table attributes
    public static final String FORDDET_ID = "stodet_id";
    public static final String FORDDET_AMT = "Amt";
    public static final String FORDDET_BAL_QTY = "BalQty";
    public static final String FORDDET_B_AMT = "BAmt";
    public static final String FORDDET_B_DIS_AMT = "BDisAmt";
    public static final String FORDDET_BP_DIS_AMT = "BPDisAmt";
    public static final String FORDDET_B_SELL_PRICE = "BSellPrice";
    public static final String FORDDET_BT_TAX_AMT = "BTaxAmt";
    public static final String FORDDET_BT_SELL_PRICE = "BTSellPrice";
    public static final String FORDDET_CASE = "Cases";
    public static final String FORDDET_CASE_QTY = "CaseQty";
    public static final String FORDDET_DIS_AMT = "DisAmt";
    public static final String FORDDET_DIS_PER = "DisPer";
    public static final String FORDDET_FREE_QTY = "freeqty";
    public static final String FORDDET_ITEM_CODE = "Itemcode";
    public static final String FORDDET_P_DIS_AMT = "PDisAmt";
    public static final String FORDDET_PRIL_CODE = "PrilCode";
    public static final String FORDDET_QTY = "Qty";
    public static final String FORDDET_DIS_VAL_AMT = "DisValAmt";
    public static final String FORDDET_PICE_QTY = "PiceQty";
    public static final String FORDDET_REA_CODE = "ReaCode";
    public static final String FORDDET_TYPE = "Types";
    public static final String FORDDET_RECORD_ID = "RecordId";
    public static final String FORDDET_REFNO = "RefNo";
    public static final String FORDDET_SELL_PRICE = "SellPrice";
    public static final String FORDDET_SEQNO = "SeqNo";
    public static final String FORDDET_TAX_AMT = "TaxAmt";
    public static final String FORDDET_TAX_COM_CODE = "TaxComCode";
    public static final String FORDDET_TIMESTAMP_COLUMN = "timestamp_column";
    public static final String FORDDET_T_SELL_PRICE = "TSellPrice";
    public static final String FORDDET_TXN_DATE = "TxnDate";
    public static final String FORDDET_TXN_TYPE = "TxnType";
    public static final String FORDDET_IS_ACTIVE = "isActive";

    public static final String FORDDET_ITEMNAME = "ItemName";
    public static final String FORDDET_PACKSIZE = "PackSize";
    //----------------------------------------------------------------------------------------------------------------------------
    private static final String CREATE_FPRODUCT_PRE_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FPRODUCT_PRE + " ("
            + FPRODUCT_ID_PRE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FPRODUCT_ITEMCODE_PRE + " TEXT, "
            + FPRODUCT_ITEMNAME_PRE + " TEXT, "
            + FPRODUCT_PRICE_PRE + " TEXT, "
            + FPRODUCT_QOH_PRE + " TEXT, "
            + FPRODUCT_QTY_PRE + " TEXT); ";
    private static final String INDEX_PRODUCTS_PRE = "CREATE UNIQUE INDEX IF NOT EXISTS pre_product ON " + TABLE_FPRODUCT_PRE + " (itemcode_pre,itemname_pre);";

    // create String
    private static final String CREATE_FORDDET_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_FORDDET + " (" + FORDDET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FORDDET_AMT + " TEXT, " + FORDDET_BAL_QTY + " TEXT, " + FORDDET_B_AMT + " TEXT, " + FORDDET_B_DIS_AMT + " TEXT, " + FORDDET_BP_DIS_AMT + " TEXT, " + FORDDET_B_SELL_PRICE + " TEXT, " + FORDDET_BT_TAX_AMT + " TEXT, " + FORDDET_BT_SELL_PRICE + " TEXT, " + FORDDET_CASE + " TEXT, " + FORDDET_CASE_QTY + " TEXT, " + FORDDET_DIS_AMT + " TEXT, " + FORDDET_DIS_PER + " TEXT, " + FORDDET_FREE_QTY + " TEXT, " + FORDDET_ITEM_CODE + " TEXT, " + FORDDET_P_DIS_AMT + " TEXT, " + FORDDET_PRIL_CODE + " TEXT, " + FORDDET_QTY + " TEXT, " + FORDDET_DIS_VAL_AMT + " TEXT, " + FORDDET_PICE_QTY + " TEXT, " + FORDDET_REA_CODE + " TEXT, " + FORDDET_TYPE + " TEXT, " + FORDDET_RECORD_ID + " TEXT, " + FORDDET_REFNO + " TEXT, " + FORDDET_SELL_PRICE + " TEXT, " + FORDDET_SEQNO + " TEXT, " + FORDDET_TAX_AMT + " TEXT, " + FORDDET_TAX_COM_CODE + " TEXT, " + FORDDET_TIMESTAMP_COLUMN + " TEXT, " + FORDDET_T_SELL_PRICE + " TEXT, "

            + FORDDET_ITEMNAME + " TEXT, " + FORDDET_PACKSIZE + " TEXT, "

            + FORDDET_TXN_DATE + " TEXT, " + FORDDET_IS_ACTIVE + " TEXT, " + FORDDET_TXN_TYPE + " TEXT); ";

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Menaka Index for OrdDet ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private static final String ORDDET_IDX = "CREATE UNIQUE INDEX IF NOT EXISTS idxordet_duplicate ON " + TABLE_FORDDET + " (" + FORDDET_REFNO + "," + FORDDET_ITEM_CODE + "," + FORDDET_TYPE + ")";

    /**
     * ############################ finvHed table Details
     * ################################
     */

    // table
    public static final String TABLE_FINVHED = "finvHed";
    // table attributes
    public static final String FINVHED_ID = "finvHed_id";
    public static final String FINVHED_REFNO = "RefNo";
    public static final String FINVHED_ADD_DATE = "AddDate";
    public static final String FINVHED_ADD_MACH = "AddMach";
    public static final String FINVHED_ADD_USER = "AddUser";
    public static final String FINVHED_APP_DATE = "Appdate";
    public static final String FINVHED_APPSTS = "Appsts";
    public static final String FINVHED_APP_USER = "AppUser";
    public static final String FINVHED_BP_TOTAL_DIS = "BPTotalDis";
    public static final String FINVHED_B_TOTAL_AMT = "BTotalAmt";
    public static final String FINVHED_B_TOTAL_DIS = "BTotalDis";
    public static final String FINVHED_B_TOTAL_TAX = "BTotalTax";
    public static final String FINVHED_COST_CODE = "CostCode";
    public static final String FINVHED_CUR_CODE = "CurCode";
    public static final String FINVHED_CUR_RATE = "CurRate";
    public static final String FINVHED_DEB_CODE = "DebCode";
    public static final String FINVHED_DIS_PER = "DisPer";
    public static final String FINVHED_START_TIME_SO = "startTimeSO";
    public static final String FINVHED_END_TIME_SO = "endTimeSO";
    public static final String FINVHED_LONGITUDE = "Longitude";
    public static final String FINVHED_LATITUDE = "Latitude";
    public static final String FINVHED_LOC_CODE = "LocCode";
    public static final String FINVHED_MANU_REF = "ManuRef";
    public static final String FINVHED_RECORD_ID = "RecordId";
    public static final String FINVHED_REMARKS = "Remarks";
    public static final String FINVHED_REPCODE = "RepCode";
    public static final String FINVHED_TAX_REG = "TaxReg";
    public static final String FINVHED_TIMESTAMP_COLUMN = "Timestamp_column";
    public static final String FINVHED_TOTAL_AMT = "TotalAmt";
    public static final String FINVHED_TOTALDIS = "TotalDis";
    public static final String FINVHED_TOTAL_TAX = "TotalTax";
    public static final String FINVHED_TXN_TYPE = "TxnType";
    public static final String FINVHED_TXN_DATE = "TxnDate";
    public static final String FINVHED_ADDRESS = "gpsAddress";
    public static final String FINVHED_TOTAL_ITM_DIS = "TotalItemDis";
    public static final String FINVHED_TOT_MKR_AMT = "TotMkrAmt";
    public static final String FINVHED_IS_SYNCED = "isSynced";
    public static final String FINVHED_IS_ACTIVE = "isActive";
    public static final String FINVHED_ROUTE_CODE = "RouteCode";
    public static final String FINVHED_REFNO1 = "RefNo1";

    public static final String FINVHED_HED_DIS_VAL = "HedDisVal";
    public static final String FINVHED_HED_DIS_PER_VAL = "HedDisPerVal";
    public static final String FINVHED_PAYMENT_TYPE = "PaymentType";

    private static final String CREATE_FINVHED_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_FINVHED + " (" + FINVHED_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FINVHED_ADD_MACH + " TEXT, " + FINVHED_ADD_DATE + " TEXT," + FINVHED_ADD_USER + " TEXT, " + FINVHED_APP_DATE + " TEXT, " + FINVHED_ADDRESS + " TEXT, " + FINVHED_APPSTS + " TEXT, " + FINVHED_APP_USER + " TEXT, " + FINVHED_BP_TOTAL_DIS + " TEXT, " + FINVHED_B_TOTAL_AMT + " TEXT, " + FINVHED_B_TOTAL_DIS + " TEXT, " + FINVHED_B_TOTAL_TAX + " TEXT, " + FINVHED_COST_CODE + " TEXT, " + FINVHED_CUR_CODE + " TEXT, " + FINVHED_CUR_RATE + " TEXT, " + FINVHED_DEB_CODE + " TEXT, " + FINVHED_LOC_CODE + " TEXT, " + FINVHED_MANU_REF + " TEXT, " + FINVHED_DIS_PER + " TEXT, " + FINVHED_RECORD_ID + " TEXT, " + FINVHED_REFNO + " TEXT, " + FINVHED_REMARKS + " TEXT, " + FINVHED_REPCODE + " TEXT, " + FINVHED_TAX_REG + " TEXT, " + FINVHED_TIMESTAMP_COLUMN + " TEXT, " + FINVHED_TOTAL_TAX + " TEXT, " + FINVHED_TOTAL_AMT + " TEXT, " + FINVHED_TOTALDIS + " TEXT, " + FINVHED_TOTAL_ITM_DIS + " TEXT, " + FINVHED_TOT_MKR_AMT + " TEXT, " + FINVHED_TXN_TYPE + " TEXT, " + FINVHED_TXN_DATE + " TEXT, " + FINVHED_LONGITUDE + " TEXT, " + FINVHED_LATITUDE + " TEXT, " + FINVHED_START_TIME_SO + " TEXT, " + FINVHED_IS_SYNCED + " TEXT, " + FINVHED_IS_ACTIVE + " TEXT, " + FINVHED_REFNO1 + " TEXT, " + FINVHED_ROUTE_CODE + " TEXT, " + FINVHED_HED_DIS_VAL + " TEXT, " + FINVHED_HED_DIS_PER_VAL + " TEXT, " + FINVHED_PAYMENT_TYPE + " TEXT, " + FINVHED_END_TIME_SO + " TEXT); ";

    /**
     * ############################ finvDet table Details
     * ################################
     */

    // table
    public static final String TABLE_FINVDET = "finvDet";
    // table attributes
    public static final String FINVDET_ID = "stodet_id";
    public static final String FINVDET_AMT = "Amt";
    public static final String FINVDET_BAL_QTY = "BalQty";
    public static final String FINVDET_B_AMT = "BAmt";
    public static final String FINVDET_B_DIS_AMT = "BDisAmt";
    public static final String FINVDET_BP_DIS_AMT = "BPDisAmt";
    public static final String FINVDET_B_SELL_PRICE = "BSellPrice";
    public static final String FINVDET_BT_TAX_AMT = "BTaxAmt";
    public static final String FINVDET_BT_SELL_PRICE = "BTSellPrice";
    public static final String FINVDET_CASE = "Cases";
    public static final String FINVDET_CASE_QTY = "CaseQty";
    public static final String FINVDET_DIS_AMT = "DisAmt";
    public static final String FINVDET_DIS_PER = "DisPer";
    public static final String FINVDET_FREE_QTY = "freeqty";
    public static final String FINVDET_ITEM_CODE = "Itemcode";
    public static final String FINVDET_P_DIS_AMT = "PDisAmt";
    public static final String FINVDET_PRIL_CODE = "PrilCode";
    public static final String FINVDET_QTY = "Qty";
    public static final String FINVDET_DIS_VAL_AMT = "DisValAmt";
    public static final String FINVDET_PICE_QTY = "PiceQty";
    public static final String FINVDET_REA_CODE = "ReaCode";
    public static final String FINVDET_TYPE = "Types";
    public static final String FINVDET_RECORD_ID = "RecordId";
    public static final String FINVDET_REFNO = "RefNo";
    public static final String FINVDET_SELL_PRICE = "SellPrice";
    public static final String FINVDET_SEQNO = "SeqNo";
    public static final String FINVDET_TAX_AMT = "TaxAmt";
    public static final String FINVDET_TAX_COM_CODE = "TaxComCode";
    public static final String FINVDET_TIMESTAMP_COLUMN = "timestamp_column";
    public static final String FINVDET_T_SELL_PRICE = "TSellPrice";
    public static final String FINVDET_TXN_DATE = "TxnDate";
    public static final String FINVDET_TXN_TYPE = "TxnType";
    public static final String FINVDET_IS_ACTIVE = "isActive";

    private static final String CREATE_FINVDET_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_FINVDET + " (" + FINVDET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FINVDET_AMT + " TEXT, " + FINVDET_BAL_QTY + " TEXT, " + FINVDET_B_AMT + " TEXT, " + FINVDET_B_DIS_AMT + " TEXT, " + FINVDET_BP_DIS_AMT + " TEXT, " + FINVDET_B_SELL_PRICE + " TEXT, " + FINVDET_BT_TAX_AMT + " TEXT, " + FINVDET_BT_SELL_PRICE + " TEXT, " + FINVDET_CASE + " TEXT, " + FINVDET_CASE_QTY + " TEXT, " + FINVDET_DIS_AMT + " TEXT, " + FINVDET_DIS_PER + " TEXT, " + FINVDET_FREE_QTY + " TEXT, " + FINVDET_ITEM_CODE + " TEXT, " + FINVDET_P_DIS_AMT + " TEXT, " + FINVDET_PRIL_CODE + " TEXT, " + FINVDET_QTY + " TEXT, " + FINVDET_DIS_VAL_AMT + " TEXT, " + FINVDET_PICE_QTY + " TEXT, " + FINVDET_REA_CODE + " TEXT, " + FINVDET_TYPE + " TEXT, " + FINVDET_RECORD_ID + " TEXT, " + FINVDET_REFNO + " TEXT, " + FINVDET_SELL_PRICE + " TEXT, " + FINVDET_SEQNO + " TEXT, " + FINVDET_TAX_AMT + " TEXT, " + FINVDET_TAX_COM_CODE + " TEXT, " + FINVDET_TIMESTAMP_COLUMN + " TEXT, " + FINVDET_T_SELL_PRICE + " TEXT, " + FINVDET_TXN_DATE + " TEXT, " + FINVDET_IS_ACTIVE + " TEXT, " + FINVDET_TXN_TYPE + " TEXT); ";
    /**
     * ############################ FCompanyBranch table Details
     * ################################
     */

    // table
    public static final String TABLE_FCOMPANYBRANCH = "FCompanyBranch";
    // table attributes
    public static final String FCOMPANYBRANCH_ID = "fcombra_id";
    public static final String FCOMPANYBRANCH_BRANCH_CODE = "BranchCode";
    public static final String FCOMPANYBRANCH_RECORD_ID = "RecordId";
    public static final String FCOMPANYBRANCH_CSETTINGS_CODE = "cSettingsCode";
    public static final String FCOMPANYBRANCH_NNUM_VAL = "nNumVal";
    public static final String FCOMPANYBRANCH_NYEAR_VAL = "nYear";
    public static final String FCOMPANYBRANCH_NMONTH_VAL = "nMonth";

    // create String
    private static final String CREATE_FCOMPANYBRANCH_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FCOMPANYBRANCH + " (" + FCOMPANYBRANCH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FCOMPANYBRANCH_BRANCH_CODE + " TEXT, " + FCOMPANYBRANCH_RECORD_ID + " TEXT, " + FCOMPANYBRANCH_CSETTINGS_CODE + " TEXT, " + FCOMPANYBRANCH_NNUM_VAL + " TEXT, " + FCOMPANYBRANCH_NYEAR_VAL + " TEXT, " + FCOMPANYBRANCH_NMONTH_VAL + " TEXT); ";

    /**
     * ############################ fSalRep table Details
     * ################################
     */

    // table
    public static final String TABLE_FSALREP = "fSalRep";
    // table attributes
    public static final String FSALREP_ID = "fsalrep_id";
    public static final String FSALREP_ASE_CODE = "ASECode";
    public static final String FSALREP_AREA_CODE = "AreaCode";
    public static final String FSALREP_DEAL_CODE = "DealCode";
    public static final String FSALREP_RECORD_ID = "RecordId";
    public static final String FSALREP_REP_CODE = "RepCode";
    public static final String FSALREP_REP_PREFIX = "RepPrefix";
    public static final String FSALREP_REP_TCODE = "RepTCode";
    public static final String FSALREP_REP_PHONE_NO = "repPhoneNo";
    public static final String FSALREP_REP_NAME = "RepName";
    public static final String FSALREP_REP_EMAIL = "RepEMail";
    public static final String FSALREP_REP_MOB = "RepMob";
    public static final String FSALREP_PASSWORD = "Password";// Password
    public static final String FSALREP_COSTCODE = "CostCode";// Password

    // create String
    private static final String CREATE_FSALREP_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FSALREP + " (" + FSALREP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FSALREP_ASE_CODE + " TEXT, " + FSALREP_AREA_CODE + " TEXT, " + FSALREP_DEAL_CODE + " TEXT, " + FSALREP_RECORD_ID + " TEXT, " + FSALREP_REP_CODE + " TEXT, " + FSALREP_REP_PREFIX + " TEXT, " + FSALREP_REP_TCODE + " TEXT, " + FSALREP_REP_PHONE_NO + " TEXT, " + FSALREP_REP_NAME + " TEXT, " + FSALREP_REP_EMAIL + " TEXT, " + FSALREP_REP_MOB + " TEXT, " + FSALREP_PASSWORD + " TEXT, " + FSALREP_COSTCODE + " TEXT); ";


    // table
    public static final String TABLE_FMSALREP = "fmSalRep";
    // table attributes
    public static final String FMSALREP_ID = "RepCodem";
    public static final String FMSALREP_NAME = "RepNamem";
    public static final String FMSALREP_IDNO = "RepIdNo";
    public static final String FMSALREP_ADD1 = "RepAdd1";
    public static final String FMSALREP_ADD2 = "RepAdd2";
    public static final String FMSALREP_ADD3 = "RepAdd3";
    public static final String FMSALREP_TELE = "RepTele";
    public static final String FMSALREP_MOBILE = "RepMobil";
    public static final String FMSALREP_EMAIL = "RepEMail";
    public static final String FMSALREP_ROUTE_CODE = "RouteCode";
    public static final String FMSALREP_LOCCODE = "LocCode";
    public static final String FMSALREP_AREASCODE = "areascode";
    public static final String FMSALREP_ADD_USER = "AddUser";
    public static final String FMSALREP_ADD_MACH = "AddMach";
    public static final String FMSALREP_ADD_DATE = "AddDate";
    public static final String FMSALREP_RECORD_ID = "RecordId";
    public static final String FMSALREP_REP_PREFIX = "RepPrefix";
    public static final String FMSALREP_REP_MACKID = "Mackid";
    public static final String FMSALREP_REP_PASSWORD = "Password";
    public static final String FMSALREP_REP_STATUS = "Status";

    // create String
    private static final String CREATE_TABLE_FMSALREP = "CREATE  TABLE IF NOT EXISTS " + TABLE_FMSALREP + " (" + FMSALREP_ID + " TEXT, " + FMSALREP_NAME +
            " TEXT, " + FMSALREP_IDNO + " TEXT, " + FMSALREP_ADD1 + " TEXT, " + FMSALREP_ADD2 +
            " TEXT, " + FMSALREP_ADD3 + " TEXT, " + FMSALREP_TELE + " TEXT, " + FMSALREP_MOBILE +
            " TEXT, " + FMSALREP_EMAIL + " TEXT, " + FMSALREP_ROUTE_CODE + " TEXT, " + FMSALREP_LOCCODE +
            " TEXT, " + FMSALREP_REP_PREFIX + " TEXT, " + FMSALREP_REP_MACKID +
            " TEXT, " + FMSALREP_AREASCODE + " TEXT, " + FMSALREP_ADD_USER + " TEXT, " + FMSALREP_ADD_MACH + " TEXT, " + FMSALREP_ADD_DATE +
            " TEXT, " + FMSALREP_REP_PASSWORD +
            " TEXT, " + FMSALREP_REP_STATUS +
            " TEXT, " + FMSALREP_RECORD_ID + " TEXT); ";


    // table fmArea-----------------------------------------------------------------------------------
    public static final String TABLE_FMAREA = "fmArea ";
    // table attributes

    public static final String AREA_CODE = "AreaCode";
    public static final String AREA_NAME = "AreaName";
    public static final String ASM_CODE = "ASMCode";
    public static final String REPCODEM = "RepCodem";

    // create String
    private static final String CREATE_FMAREA_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FMAREA + " (" + AREA_CODE + " TEXT, " + AREA_NAME +
            " TEXT, " + ASM_CODE + " TEXT, " + ADD_USER + " TEXT, " + ADD_DATE +
            " TEXT, " + ADD_MACH + " TEXT, " + RECORD_ID + " TEXT); ";

    // table fmAreaSub-----------------------------------------------------------------------------------
    public static final String TABLE_FAREA_SUB = "fmAreaSub ";
    // table attributes
    public static final String AREA_SCODE = "AreaSCode";
    public static final String AREA_SNAME = "AreaSName";
    public static final String ROUTE_CODE = "RouteCode";

    // create String
    private static final String CREATE_TABLE_FAREA_SUB = "CREATE  TABLE IF NOT EXISTS " + TABLE_FAREA_SUB + " (" + AREA_SCODE + " TEXT, " + AREA_SNAME +
            " TEXT, " + ROUTE_CODE + " TEXT, " + ADD_USER + " TEXT, " + ADD_DATE +
            " TEXT, " + ADD_MACH + " TEXT, " + RECORD_ID + " TEXT); ";

    // table fmDebDet-----------------------------------------------------------------------------------
    public static final String TABLE_FMDEBDET = "fmDebDet ";
    // table attributes
    public static final String DEBCODEM = "DebCodeM";
    public static final String DEBCODE = "DebCode";

    // create String
    private static final String CREATE_TABLE_FMDEBDET = "CREATE  TABLE IF NOT EXISTS " + TABLE_FMDEBDET + " (" + RECORD_ID + " TEXT, " + DEBCODEM +
            " TEXT, " + DEBCODE + " TEXT); ";

    // table fExpense-----------------------------------------------------------------------------------
    public static final String TABLE_FEXPENSE = "fExpense ";
    // table attributes

    public static final String EXPCODE = "ExpCode";
    public static final String EXPNAME = "ExpName";
    public static final String EXP_STATUS = "Status";
    public static final String EXP_ADDUSER = "AddUser";
    public static final String EXP_ADD_DATE = "AddDate";
    public static final String EXP_ADD_MACH = "AddMach";
    public static final String EXP_EXP_GRP_CODE = "Exp_Grp_Code";
    public static final String EXP_RECORDID = "RecordId";

//    public static final String FEXPENSE_ID = "uexp_id";
//    public static final String FEXPENSE_CODE = "ExpCode";
//    public static final String FEXPENSE_GRP_CODE = "ExpGrpCode";
//    public static final String FEXPENSE_NAME = "ExpName";
//    public static final String FEXPENSE_RECORDID = "RecordId";
//    public static final String FEXPENSE_STATUS = "Status";
//    public static final String FEXPENSE_ADD_MACH = "AddMach";
//    public static final String FEXPENSE_ADD_USER = "AddUser";
//    public static final String FEXPENSE_ADD_DATE = "AddDate";

    // create String
    private static final String CREATE_TABLE_FEXPENSE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FEXPENSE + " (" + EXP_RECORDID + " TEXT, " + EXPCODE +
            " TEXT, " + EXPNAME + " TEXT, " + EXP_STATUS + " TEXT, " + EXP_EXP_GRP_CODE +
            " TEXT, " + EXP_ADDUSER + " TEXT, " + EXP_ADD_DATE + " TEXT, " + EXP_ADD_MACH + " TEXT); ";

    // table fExpGrp-----------------------------------------------------------------------------------
    public static final String TABLE_FEXP_GRP = "fExpGrp";
    // table attributes

    public static final String EXP_GRP_CODE = "ExpGrpCode";
    public static final String EXP_GRPNAME = "ExpGrpName";
    public static final String EXP_GRP_ADDUSER = "AddUser";
    public static final String EXP_ADDDATE = "AddDate";
    public static final String EXP_ADDMACH = "AddMach";
    public static final String EXP_GRP_RECORDID = "RecordId";

    // create String
    private static final String CREATE_TABLE_FEXP_GRP = "CREATE  TABLE IF NOT EXISTS " + TABLE_FEXP_GRP + " (" + EXP_GRP_RECORDID + " TEXT, " + EXP_GRP_CODE +
            " TEXT, " + EXP_GRPNAME + " TEXT, " + EXP_GRP_ADDUSER + " TEXT, " + EXP_ADDDATE +
            " TEXT, " + EXP_ADDMACH + " TEXT); ";


    /**
     * ############################ FDDbNote table Details
     * ################################
     */

    // table
    public static final String TABLE_FDDBNOTE = "FDDbNote";
    // table attributes
    public static final String FDDBNOTE_ID = "recinv_id";
    public static final String FDDBNOTE_RECORD_ID = "RecordId";
    public static final String FDDBNOTE_REFNO = "RefNo";
    public static final String FDDBNOTE_REF_INV = "RefInv";
    public static final String FDDBNOTE_SALE_REF_NO = "SaleRefNo";
    public static final String FDDBNOTE_MANU_REF = "ManuRef";
    public static final String FDDBNOTE_TXN_TYPE = "TxnType";
    public static final String FDDBNOTE_TXN_DATE = "TxnDate";
    public static final String FDDBNOTE_CUR_CODE = "CurCode";
    public static final String FDDBNOTE_CUR_RATE = "CurRate";
    public static final String FDDBNOTE_DEB_CODE = "DebCode";
    public static final String FDDBNOTE_REP_CODE = "RepCode";
    public static final String FDDBNOTE_TAX_COM_CODE = "TaxComCode";
    public static final String FDDBNOTE_TAX_AMT = "TaxAmt";
    public static final String FDDBNOTE_B_TAX_AMT = "BTaxAmt";
    public static final String FDDBNOTE_AMT = "Amt";
    public static final String FDDBNOTE_B_AMT = "BAmt";
    public static final String FDDBNOTE_TOT_BAL = "TotBal";
    public static final String FDDBNOTE_TOT_BAL1 = "TotBal1";
    public static final String FDDBNOTE_OV_PAY_AMT = "OvPayAmt";
    public static final String FDDBNOTE_REMARKS = "Remarks";
    public static final String FDDBNOTE_CR_ACC = "CrAcc";
    public static final String FDDBNOTE_PRT_COPY = "PrtCopy";
    public static final String FDDBNOTE_GL_POST = "GlPost";
    public static final String FDDBNOTE_GL_BATCH = "glbatch";
    public static final String FDDBNOTE_ADD_USER = "AddUser";
    public static final String FDDBNOTE_ADD_DATE = "AddDate";
    public static final String FDDBNOTE_ADD_MACH = "AddMach";
    public static final String FDDBNOTE_REFNO1 = "RefNo1";
    public static final String FDDBNOTE_REFNO2 = "RefNo2";
    public static final String FDDBNOTE_REPNAME = "RepName";
    public static final String FDDBNOTE_ENTER_AMT = "EnterAmt";
    public static final String FDDBNOTE_REMARK = "Remark";
    public static final String FDDBNOTE_ENT_REMARK = "EntRemark";
    public static final String FDDBNOTE_PDAAMT = "PdaAmt";
    public static final String FDDBNOTE_RECEIPT_TYPE = "ReceiptType";


    // create String
    private static final String CREATE_FDDBNOTE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_FDDBNOTE + " (" + FDDBNOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FDDBNOTE_RECORD_ID + " TEXT, " +
            FDDBNOTE_REFNO + " TEXT, " + FDDBNOTE_REFNO2 + " TEXT, " + FDDBNOTE_REPNAME + " TEXT, " +
            FDDBNOTE_RECEIPT_TYPE + " TEXT, " + FDDBNOTE_REMARK + " TEXT, " + FDDBNOTE_ENT_REMARK + " TEXT, " + FDDBNOTE_PDAAMT + " TEXT, " + FDDBNOTE_REF_INV + " TEXT, " + FDDBNOTE_ENTER_AMT + " TEXT, " + FDDBNOTE_SALE_REF_NO + " TEXT, " + FDDBNOTE_MANU_REF + " TEXT, " + FDDBNOTE_TXN_TYPE + " TEXT, " + FDDBNOTE_TXN_DATE + " TEXT, " + FDDBNOTE_CUR_CODE + " TEXT, " + FDDBNOTE_CUR_RATE + " TEXT, " + FDDBNOTE_DEB_CODE + " TEXT, " + FDDBNOTE_REP_CODE + " TEXT, " + FDDBNOTE_TAX_COM_CODE + " TEXT, " + FDDBNOTE_TAX_AMT + " TEXT, " + FDDBNOTE_B_TAX_AMT + " TEXT, " + FDDBNOTE_AMT + " TEXT, " + FDDBNOTE_B_AMT + " TEXT, " + FDDBNOTE_TOT_BAL + " TEXT, " + FDDBNOTE_TOT_BAL1 + " TEXT, " + FDDBNOTE_OV_PAY_AMT + " TEXT, " + FDDBNOTE_REMARKS + " TEXT, " + FDDBNOTE_CR_ACC + " TEXT, " + FDDBNOTE_PRT_COPY + " TEXT, " + FDDBNOTE_GL_POST + " TEXT, " + FDDBNOTE_GL_BATCH + " TEXT, " + FDDBNOTE_ADD_USER + " TEXT, " + FDDBNOTE_ADD_DATE + " TEXT, " + FDDBNOTE_ADD_MACH + " TEXT, " + FDDBNOTE_REFNO1 + " TEXT); ";

    private static final String TESTDDBNOTE = "CREATE UNIQUE INDEX IF NOT EXISTS idxddbnote_something ON " + TABLE_FDDBNOTE + " (" + FDDBNOTE_REFNO + ")";

    /**
     * ############################ Ffreedeb table Details
     * ################################
     */

    // table
    public static final String TABLE_FFREEDEB = "Ffreedeb";
    // table attributes
    public static final String FFREEDEB_ID = "Ffreedeb_id";
    public static final String FFREEDEB_REFNO = "Refno";
    public static final String FFREEDEB_DEB_CODE = "Debcode";
    public static final String FFREEDEB_ADD_USER = "AddUser";
    public static final String FFREEDEB_ADD_DATE = "AddDate";
    public static final String FFREEDEB_ADD_MACH = "AddMach";
    public static final String FFREEDEB_RECORD_ID = "RecordId";
    public static final String FFREEDEB_TIMESTAMP_COLUMN = "timestamp_column";

    // create String
    private static final String CREATE_FFREEDEB_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FFREEDEB + " (" + FFREEDEB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FFREEDEB_REFNO + " TEXT, " + FFREEDEB_DEB_CODE + " TEXT, " + FFREEDEB_ADD_USER + " TEXT, " + FFREEDEB_ADD_DATE + " TEXT, " + FFREEDEB_ADD_MACH + " TEXT, " + FFREEDEB_RECORD_ID + " TEXT, " + FFREEDEB_TIMESTAMP_COLUMN + " TEXT); ";

    private static final String TESTFREEDEB = "CREATE UNIQUE INDEX IF NOT EXISTS idxfreedeb_something ON " + TABLE_FFREEDEB + " (" + FFREEDEB_REFNO + "," + FFREEDEB_DEB_CODE + ")";

    /**
     * ############################ Ffreedet table Details
     * ################################
     */

    // table
    public static final String TABLE_FFREEDET = "Ffreedet";
    // table attributes
    public static final String FFREEDET_ID = "Ffreedet_id";
    public static final String FFREEDET_REFNO = "Refno";
    public static final String FFREEDET_ITEM_CODE = "Itemcode";
    public static final String FFREEDET_RECORD_ID = "RecordId";

    // create String
    private static final String CREATE_FFREEDET_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FFREEDET + " (" + FFREEDET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FFREEDET_REFNO + " TEXT, " + FFREEDET_ITEM_CODE + " TEXT, " + FFREEDET_RECORD_ID + " TEXT); ";

    private static final String IDXFREEDET = "CREATE UNIQUE INDEX IF NOT EXISTS idxfreedet_something ON " + TABLE_FFREEDET + " (" + FFREEDET_REFNO + ", " + FFREEDET_ITEM_CODE + ")";

    /**
     * ############################ Ffreehed table Details
     * ################################
     */

    // table
    public static final String TABLE_FFREEHED = "Ffreehed";
    // table attributes
    public static final String FFREEHED_ID = "Ffreehed_id";
    public static final String FFREEHED_REFNO = "Refno";
    public static final String FFREEHED_TXNDATE = "txndate";
    public static final String FFREEHED_DISC_DESC = "DiscDesc";
    public static final String FFREEHED_PRIORITY = "Priority";
    public static final String FFREEHED_VDATEF = "Vdatef";
    public static final String FFREEHED_VDATET = "Vdatet";
    public static final String FFREEHED_REMARKS = "Remarks";
    public static final String FFREEHED_RECORD_ID = "RecordId";
    public static final String FFREEHED_ITEM_QTY = "ItemQty";
    public static final String FFREEHED_FREE_IT_QTY = "FreeItQty";
    public static final String FFREEHED_FTYPE = "Ftype";
    public static final String FFREEHED_MUST_FLAG = "Mustflg";

    // create String
    private static final String CREATE_FFREEHED_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FFREEHED + " (" + FFREEHED_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FFREEHED_REFNO + " TEXT, " + FFREEHED_TXNDATE + " TEXT, " + FFREEHED_DISC_DESC + " TEXT, " + FFREEHED_PRIORITY + " TEXT, " + FFREEHED_VDATEF + " TEXT, " + FFREEHED_VDATET + " TEXT, " + FFREEHED_REMARKS + " TEXT, " + FFREEHED_RECORD_ID + " TEXT, " + FFREEHED_ITEM_QTY + " TEXT, " + FFREEHED_FREE_IT_QTY + " TEXT, " + FFREEHED_FTYPE + " TEXT," + FFREEHED_MUST_FLAG + " TEXT); ";


    private static final String IDXFREEHED = "CREATE UNIQUE INDEX IF NOT EXISTS idxfreehed_something ON " + TABLE_FFREEHED + " (" + FFREEHED_REFNO + ")";
    /**
     * ############################ FfreeSlab table Details
     * ################################
     */

    // table
    public static final String TABLE_FFREESLAB = "Ffreeslab";
    // table attributes
    public static final String FFREESLAB_ID = "Ffreeslab_id";
    public static final String FFREESLAB_REFNO = "Refno";
    public static final String FFREESLAB_QTY_F = "Qtyf";
    public static final String FFREESLAB_QTY_T = "Qtyt";
    public static final String FFREESLAB_FITEM_CODE = "fItemCode";
    public static final String FFREESLAB_FREE_QTY = "freeQty";
    public static final String FFREESLAB_ADD_USER = "AddUser";
    public static final String FFREESLAB_ADD_DATE = "AddDate";
    public static final String FFREESLAB_ADD_MACH = "AddMach";
    public static final String FFREESLAB_RECORD_ID = "RecordId";
    public static final String FFREESLAB_TIMESTAP_COLUMN = "timestamp_column";
    public static final String FFREESLAB_SEQ_NO = "seqno";

    // create String
    private static final String CREATE_FFREESLAB_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FFREESLAB + " (" + FFREESLAB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FFREESLAB_REFNO + " TEXT, " + FFREESLAB_QTY_F + " TEXT, " + FFREESLAB_QTY_T + " TEXT, " + FFREESLAB_FITEM_CODE + " TEXT, " + FFREESLAB_FREE_QTY + " TEXT, " + FFREESLAB_ADD_USER + " TEXT, " + FFREESLAB_ADD_DATE + " TEXT, " + FFREESLAB_ADD_MACH + " TEXT, " + FFREESLAB_RECORD_ID + " TEXT, " + FFREESLAB_TIMESTAP_COLUMN + " TEXT, " + FFREESLAB_SEQ_NO + " TEXT); ";

    private static final String IDXFREESLAB = "CREATE UNIQUE INDEX IF NOT EXISTS idxfreeslab_something ON " + TABLE_FFREESLAB + " (" + FFREESLAB_REFNO + ", " + FFREESLAB_SEQ_NO + ")";

    /**
     * ############################ fFreeItem table Details
     * ################################
     */

    // table
    public static final String TABLE_FFREEITEM = "fFreeItem";
    // table attributes
    public static final String FFREEITEM_ID = "fFreeItem_id";
    public static final String FFREEITEM_REFNO = "Refno";
    public static final String FFREEITEM_ITEMCODE = "Itemcode";
    public static final String FFREEITEM_RECORD_ID = "RecordId";

    // create String
    private static final String CREATE_FFREEITEM_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FFREEITEM + " (" + FFREEITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FFREEITEM_REFNO + " TEXT, " + FFREEITEM_ITEMCODE + " TEXT, " + FFREEITEM_RECORD_ID + " TEXT); ";

    private static final String IDXFREEITEM = "CREATE UNIQUE INDEX IF NOT EXISTS idxfreeitem_something ON " + TABLE_FFREEITEM + " (" + FFREEITEM_REFNO + ", " + FFREEITEM_ITEMCODE + ")";
    /**
     * ############################ fItem table Details
     * ################################
     */

    // table
    public static final String TABLE_FITEM = "fItem";
    // table attributes
    public static final String FITEM_ID = "fItem_id";
    public static final String FITEM_ADD_MATCH = "AddMach";
    public static final String FITEM_ADD_USER = "AddUser";
    public static final String FITEM_AVG_PRICE = "AvgPrice";
    public static final String FITEM_BRAND_CODE = "BrandCode";
    public static final String FITEM_GROUP_CODE = "GroupCode";
    public static final String FITEM_ITEM_CODE = "ItemCode";
    public static final String FITEM_ITEM_NAME = "ItemName";
    public static final String FITEM_ITEM_STATUS = "ItemStatus";
    public static final String FITEM_MUST_SALE = "MustSale";
    public static final String FITEM_NOU_CASE = "NOUCase";
    public static final String FITEM_ORD_SEQ = "OrdSeq";
    public static final String FITEM_PRIL_CODE = "PrilCode";
    public static final String FITEM_RE_ORDER_LVL = "ReOrderLvl";
    public static final String FITEM_RE_ORDER_QTY = "ReOrderQty";
    public static final String FITEM_TAX_COM_CODE = "TaxComCode";
    public static final String FITEM_TYPE_CODE = "TypeCode";
    public static final String FITEM_UNIT_CODE = "UnitCode";
    public static final String FITEM_VEN_P_CODE = "VenPcode";
    public static final String FITEM_CAT_CODE = "CatCode";
    public static final String FITEM_PACK = "Pack";
    public static final String FITEM_PACK_SIZE = "PackSize";
    public static final String FITEM_SUP_CODE = "SupCode";
    public static final String FITEM_MUST_FREE = "ChkMustFre";

    // create String
    private static final String CREATE_FITEM_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FITEM + " (" + FITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FITEM_ADD_MATCH + " TEXT, " + FITEM_ADD_USER + " TEXT, " + FITEM_AVG_PRICE + " TEXT, " + FITEM_BRAND_CODE + " TEXT, " + FITEM_GROUP_CODE + " TEXT, " + FITEM_ITEM_CODE + " TEXT, " + FITEM_ITEM_NAME + " TEXT, " + FITEM_ITEM_STATUS + " TEXT, " + FITEM_MUST_SALE + " TEXT, " + FITEM_NOU_CASE + " TEXT, " + FITEM_ORD_SEQ + " TEXT, " + FITEM_PRIL_CODE + " TEXT, " + FITEM_RE_ORDER_LVL + " TEXT, " + FITEM_RE_ORDER_QTY + " TEXT, " + FITEM_TAX_COM_CODE + " TEXT, " + FITEM_TYPE_CODE + " TEXT, " + FITEM_UNIT_CODE + " TEXT, " + FITEM_CAT_CODE + " TEXT, " + FITEM_PACK + " TEXT, " + FITEM_PACK_SIZE + " TEXT, " + FITEM_SUP_CODE + " TEXT, " + FITEM_VEN_P_CODE + " TEXT, " + FITEM_MUST_FREE + " TEXT); ";

    private static final String TESTITEM = "CREATE UNIQUE INDEX IF NOT EXISTS idxitem_something ON " + TABLE_FITEM + " (" + FITEM_ITEM_CODE + ")";

    /**
     * ############################ fItemLoc table Details
     * ################################
     */

    // table
    public static final String TABLE_FITEMLOC = "fItemLoc";
    // table attributes
    public static final String FITEMLOC_ID = "fItemLoc_id";
    public static final String FITEMLOC_ITEM_CODE = "ItemCode";
    public static final String FITEMLOC_LOC_CODE = "LocCode";
    public static final String FITEMLOC_QOH = "QOH";

    // create String
    private static final String CREATE_FITEMLOC_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FITEMLOC + " (" + FITEMLOC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FITEMLOC_ITEM_CODE + " TEXT, " + FITEMLOC_LOC_CODE + " TEXT, " + FITEMLOC_QOH + " TEXT); ";

    private static final String TESTITEMLOC = "CREATE UNIQUE INDEX IF NOT EXISTS idxitemloc_something ON " + TABLE_FITEMLOC + " (" + FITEMLOC_ITEM_CODE + "," + FITEMLOC_LOC_CODE + ")";

    /**
     * ############################ fItemPri table Details
     */
    // table
    public static final String TABLE_FITEMPRI = "fItemPri";
    // table attributes
    public static final String FITEMPRI_ID = "fItemPri_id";
    public static final String FITEMPRI_ADD_MACH = "AddMach";
    public static final String FITEMPRI_ADD_USER = "AddUser";
    public static final String FITEMPRI_ITEM_CODE = "ItemCode";
    public static final String FITEMPRI_PRICE = "Price";
    public static final String FITEMPRI_PRIL_CODE = "PrilCode";
    public static final String FITEMPRI_TXN_MACH = "TxnMach";
    public static final String FITEMPRI_TXN_USER = "Txnuser";
    public static final String FITEMPRI_COST_CODE = "CostCode";

    // create String
    private static final String CREATE_FITEMPRI_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FITEMPRI + " (" + FITEMPRI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FITEMPRI_ADD_MACH + " TEXT, " + FITEMPRI_ADD_USER + " TEXT, " + FITEMPRI_ITEM_CODE + " TEXT, " + FITEMPRI_PRICE + " TEXT, " + FITEMPRI_PRIL_CODE + " TEXT, " + FITEMPRI_TXN_MACH + " TEXT, " + FITEMPRI_TXN_USER + " TEXT, " + FITEMPRI_COST_CODE + " TEXT); ";

    private static final String TESTITEMPRI = "CREATE UNIQUE INDEX IF NOT EXISTS idxitempri_something ON " + TABLE_FITEMPRI + " (" + FITEMPRI_ITEM_CODE + "," + FITEMPRI_PRIL_CODE + "," + FITEMPRI_COST_CODE + ")";

    /**
     * ############################ fArea table Details
     * ################################
     */
    // table
    public static final String TABLE_FAREA = "fArea";
    // table attributes
    public static final String FAREA_ID = "fArea_id";
    public static final String FAREA_ADD_MACH = "AddMach";
    public static final String FAREA_ADD_USER = "AddUser";
    public static final String FAREA_AREA_CODE = "AreaCode";
    public static final String FAREA_AREA_NAME = "AreaName";
    public static final String FAREA_DEAL_CODE = "DealCode";
    public static final String FAREA_REQ_CODE = "RegCode";

    // create String
    private static final String CREATE_FAREA_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FAREA + " (" + FAREA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FAREA_ADD_MACH + " TEXT, " + FAREA_ADD_USER + " TEXT, " + FAREA_AREA_CODE + " TEXT, " + FAREA_AREA_NAME + " TEXT, " + FAREA_DEAL_CODE + " TEXT, " + FAREA_REQ_CODE + " TEXT); ";
    /**
     * ############################ fLocations table Details
     * ################################
     */
    // table
    public static final String TABLE_FLOCATIONS = "fLocations";
    // table attributes
    public static final String FLOCATIONS_ID = "fLocations_id";
    public static final String FLOCATIONS_ADD_MACH = "AddMach";
    public static final String FLOCATIONS_ADD_USER = "AddUser";
    public static final String FLOCATIONS_LOC_CODE = "LocCode";
    public static final String FLOCATIONS_LOC_NAME = "LocName";
    public static final String FLOCATIONS_LOC_T_CODE = "LoctCode";
    public static final String FLOCATIONS_REP_CODE = "RepCode";
    public static final String FLOCATIONS_COST_CODE = "CostCode";

    // create String
    private static final String CREATE_FLOCATIONS_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FLOCATIONS + " (" + FLOCATIONS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FLOCATIONS_ADD_MACH + " TEXT, " + FLOCATIONS_ADD_USER + " TEXT, " + FLOCATIONS_LOC_CODE + " TEXT, " + FLOCATIONS_LOC_NAME + " TEXT, " + FLOCATIONS_LOC_T_CODE + " TEXT, " + FLOCATIONS_REP_CODE + " TEXT, " + FLOCATIONS_COST_CODE + " TEXT); ";
    /**
     * ############################ fDealer table Details
     * ################################
     */
    // table
    public static final String TABLE_FDEALER = "fDealer";
    // table attributes
    public static final String FDEALER_ID = "fDealer_id";
    public static final String FDEALER_ADD_DATE = "AddDate";
    public static final String FDEALER_ADD_MACH = "AddMach";
    public static final String FDEALER_ADD_USER = "AddUser";
    public static final String FDEALER_CUS_TYP_CODE = "CusTypCode";
    public static final String FDEALER_DEAL_ADD1 = "DealAdd1";
    public static final String FDEALER_DEAL_ADD2 = "DealAdd2";
    public static final String FDEALER_DEAL_ADD3 = "DealAdd3";
    public static final String FDEALER_DEAL_CODE = "DealCode";
    public static final String FDEALER_DEAL_EMAIL = "DealEMail";
    public static final String FDEALER_DEAL_GD_CODE = "DealGdCode";
    public static final String FDEALER_DEAL_MOB = "DealMob";
    public static final String FDEALER_DEAL_NAME = "DealName";
    public static final String FDEALER_DEAL_TELE = "DealTele";
    public static final String FDEALER_DISTANCE = "Distance";
    public static final String FDEALER_STATUS = "Status";
    public static final String FDEALER_PREFIX = "DealPreFix";
    // create String
    private static final String CREATE_FDEALER_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FDEALER + " (" + FDEALER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FDEALER_ADD_DATE + " TEXT, " + FDEALER_ADD_MACH + " TEXT, " + FDEALER_ADD_USER + " TEXT, " + FDEALER_CUS_TYP_CODE + " TEXT, " + FDEALER_DEAL_ADD1 + " TEXT, " + FDEALER_DEAL_ADD2 + " TEXT, " + FDEALER_DEAL_ADD3 + " TEXT, " + FDEALER_DEAL_CODE + " TEXT, " + FDEALER_DEAL_EMAIL + " TEXT, " + FDEALER_DEAL_GD_CODE + " TEXT, " + FDEALER_DEAL_MOB + " TEXT, " + FDEALER_DEAL_NAME + " TEXT, " + FDEALER_DEAL_TELE + " TEXT, " + FDEALER_DISTANCE + " TEXT, " + FDEALER_STATUS + " TEXT," + FDEALER_PREFIX + " TEXT); ";

    /**
     * ############################ fMerch table Details
     * ################################
     */
    // table
    public static final String TABLE_FMERCH = "fMerch";
    // table attributes
    public static final String FMERCH_ID = "fMerch_id";
    public static final String FMERCH_CODE = "MerchCode";
    public static final String FMERCH_NAME = "MerchName";
    public static final String FMERCH_ADD_USER = "AddUser";
    public static final String FMERCH_ADD_DATE = "AddDate";
    public static final String FMERCH_ADD_MACH = "AddMach";
    public static final String FMERCH_RECORD_ID = "RecordId";
    public static final String FMERCH_TIMESTAMP_COLUMN = "timestamp_column";

    // create String
    private static final String CREATE_FMERCH_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FMERCH + " (" + FMERCH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FMERCH_CODE + " TEXT, " + FMERCH_NAME + " TEXT, " + FMERCH_ADD_USER + " TEXT, " + FMERCH_ADD_DATE + " TEXT, " + FMERCH_ADD_MACH + " TEXT, " + FMERCH_RECORD_ID + " TEXT, " + FMERCH_TIMESTAMP_COLUMN + " TEXT); ";

    /**
     * ############################ FfreeMslab table Details
     * ################################
     */
    // table
    public static final String TABLE_FFREEMSLAB = "FfreeMslab";
    // table attributes
    public static final String FFREEMSLAB_ID = "FfreeMslab_id";
    public static final String FFREEMSLAB_REFNO = "Refno";
    public static final String FFREEMSLAB_QTY_F = "Qtyf";
    public static final String FFREEMSLAB_QTY_T = "Qtyt";
    public static final String FFREEMSLAB_ITEM_QTY = "ItemQty";
    public static final String FFREEMSLAB_FREE_IT_QTY = "FreeItQty";
    public static final String FFREEMSLAB_ADD_USER = "AddUser";
    public static final String FFREEMSLAB_ADD_DATE = "AddDate";
    public static final String FFREEMSLAB_ADD_MACH = "AddMach";
    public static final String FFREEMSLAB_RECORD_ID = "RecordId";
    public static final String FFREEMSLAB_TIMESTAMP_COLUMN = "timestamp_column";
    public static final String FFREEMSLAB_SEQ_NO = "seqno";

    // create String
    private static final String CREATE_FFREEMSLAB_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FFREEMSLAB + " (" + FFREEMSLAB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FFREEMSLAB_REFNO + " TEXT, " + FFREEMSLAB_QTY_F + " TEXT, " + FFREEMSLAB_QTY_T + " TEXT, " + FFREEMSLAB_ITEM_QTY + " TEXT, " + FFREEMSLAB_FREE_IT_QTY + " TEXT, " + FFREEMSLAB_ADD_USER + " TEXT, " + FFREEMSLAB_ADD_DATE + " TEXT, " + FFREEMSLAB_ADD_MACH + " TEXT, " + FFREEMSLAB_RECORD_ID + " TEXT, " + FFREEMSLAB_TIMESTAMP_COLUMN + " TEXT, " + FFREEMSLAB_SEQ_NO + " TEXT); ";

    private static final String IDXFREEMSLAB = "CREATE UNIQUE INDEX IF NOT EXISTS idxfreemslab_something ON " + TABLE_FFREEMSLAB + " (" + FFREEMSLAB_REFNO + ", " + FFREEMSLAB_SEQ_NO + ")";
    /**
     * ############################ fRouteDet table Details
     * ################################
     */
    // table
    public static final String TABLE_FROUTEDET = "fRouteDet";
    // table attributes
    public static final String FROUTEDET_ID = "fRouteDet_id";
    public static final String FROUTEDET_DEB_CODE = "DebCode";
    public static final String FROUTEDET_ROUTE_CODE = "RouteCode";

    // create String
    private static final String CREATE_FROUTEDET_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FROUTEDET + " (" + FROUTEDET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FROUTEDET_DEB_CODE + " TEXT, " + FROUTEDET_ROUTE_CODE + " TEXT); ";

    private static final String TESTROUTEDET = "CREATE UNIQUE INDEX IF NOT EXISTS idxrutdet_something ON " + TABLE_FROUTEDET + " (" + FROUTEDET_DEB_CODE + "," + FROUTEDET_ROUTE_CODE + ")";

    /**
     * ############################ FDiscvhed table Details
     * ################################
     */
    // table
    public static final String TABLE_FDISCVHED = "FDiscvhed";
    // table attributes
    public static final String FDISCVHED_ID = "FDiscvhed_id";
    public static final String FDISCVHED_REF_NO = "Refno";
    public static final String FDISCVHED_TXN_DATE = "Txndate";
    public static final String FDISCVHED_DISC_DESC = "DiscDesc";
    public static final String FDISCVHED_PRIORITY = "Priority";
    public static final String FDISCVHED_DIS_TYPE = "DisType";
    public static final String FDISCVHED_V_DATE_F = "Vdatef";
    public static final String FDISCVHED_V_DATE_T = "Vdatet";
    public static final String FDISCVHED_REMARKS = "Remarks";

    // create String
    private static final String CREATE_FDISCVHED_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FDISCVHED + " (" + FDISCVHED_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FDISCVHED_REF_NO + " TEXT, " + FDISCVHED_TXN_DATE + " TEXT, " + FDISCVHED_DISC_DESC + " TEXT, " + FDISCVHED_PRIORITY + " TEXT, " + FDISCVHED_DIS_TYPE + " TEXT, " + FDISCVHED_V_DATE_F + " TEXT, " + FDISCVHED_V_DATE_T + " TEXT, " + FDISCVHED_REMARKS + " TEXT); ";
    /**
     * ############################ Fdiscvdet table Details
     * ################################
     */
    // table
    public static final String TABLE_FDISCVDET = "Fdiscvdet";
    // table attributes
    public static final String FDISCVDET_ID = "FDiscvdet_id";
    public static final String FDISCVDET_REF_NO = "Refno";
    public static final String FDISCVDET_VALUE_F = "Valuef";
    public static final String FDISCVDET_VALUE_T = "Valuet";
    public static final String FDISCVDET_DISPER = "Disper";
    public static final String FDISCVDET_DIS_AMT = "Disamt";

    // create String
    private static final String CREATE_FDISCVDET_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FDISCVDET + " (" + FDISCVDET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FDISCVDET_REF_NO + " TEXT, " + FDISCVDET_VALUE_F + " TEXT, " + FDISCVDET_VALUE_T + " TEXT, " + FDISCVDET_DISPER + " TEXT, " + FDISCVDET_DIS_AMT + " TEXT); ";

    /**
     * ############################ FDiscvdeb table Details
     * ################################
     */
    // table
    public static final String TABLE_FDISCVDEB = "FDiscvdeb";
    // table attributes
    public static final String FDISCVDEB_ID = "FDiscvdet_id";
    public static final String FDISCVDEB_REF_NO = "Refno";
    public static final String FDISCVDED_DEB_CODE = "Debcode";

    // create String
    private static final String CREATE_FDISCVDEB_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FDISCVDEB + " (" + FDISCVDEB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FDISCVDEB_REF_NO + " TEXT, " + FDISCVDED_DEB_CODE + " TEXT); ";

    /**
     * ############################ fdisched table Details
     * ################################
     */
    // table
    public static final String TABLE_FDISCHED = "fdisched";
    // table attributes
    public static final String FDISCHED_ID = "fdisched_id";
    public static final String FDISCHED_REF_NO = "RefNo";
    public static final String FDISCHED_TXN_DATE = "TxnDate";
    public static final String FDISCHED_DISC_DESC = "DiscDesc";
    public static final String FDISCHED_PRIORITY = "Priority";
    public static final String FDISCHED_DIS_TYPE = "DisType";
    public static final String FDISCHED_V_DATE_F = "Vdatef";
    public static final String FDISCHED_V_DATE_T = "Vdatet";
    public static final String FDISCHED_REMARK = "Remarks";
    public static final String FDISCHED_ADD_USER = "AddUser";
    public static final String FDISCHED_ADD_DATE = "AddDate";
    public static final String FDISCHED_ADD_MACH = "AddMach";
    public static final String FDISCHED_RECORD_ID = "RecordId";
    public static final String FDISCHED_TIMESTAMP_COLUMN = "timestamp_column";
    // create String
    private static final String CREATE_FDISCHED_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FDISCHED + " (" + FDISCHED_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FDISCHED_REF_NO + " TEXT, " + FDISCHED_TXN_DATE + " TEXT, " + FDISCHED_DISC_DESC + " TEXT, " + FDISCHED_PRIORITY + " TEXT, " + FDISCHED_DIS_TYPE + " TEXT, " + FDISCHED_V_DATE_F + " TEXT, " + FDISCHED_V_DATE_T + " TEXT, " + FDISCHED_REMARK + " TEXT, " + FDISCHED_ADD_USER + " TEXT, " + FDISCHED_ADD_DATE + " TEXT, " + FDISCHED_ADD_MACH + " TEXT, " + FDISCHED_RECORD_ID + " TEXT, " + FDISCHED_TIMESTAMP_COLUMN + " TEXT); ";

    /**
     * ############################ FDiscdet table Details
     * ################################
     */

    // table
    public static final String TABLE_FDISCDET = "FDiscdet";
    // table attributes
    public static final String FDISCDET_ID = "FDiscdet_id";
    public static final String FDISCDET_REF_NO = "RefNo";
    public static final String FDISCDET_ITEM_CODE = "itemcode";
    public static final String FDISCDET_RECORD_ID = "RecordId";
    public static final String FDISCHED_TIEMSTAMP_COLUMN = "timestamp_column";

    // create String
    private static final String CREATE_FDISCDET_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FDISCDET + " (" + FDISCDET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FDISCDET_REF_NO + " TEXT, " + FDISCDET_ITEM_CODE + " TEXT, " + FDISCDET_RECORD_ID + " TEXT, " + FDISCHED_TIEMSTAMP_COLUMN + " TEXT); ";

    /**
     * ############################ FDiscdeb table Details
     * ################################
     */

    // table
    public static final String TABLE_FDISCDEB = "FDiscdeb";
    // table attributes
    public static final String FDISCDEB_ID = "FDiscdet_id";
    public static final String FDISCDEB_REF_NO = "RefNo";
    public static final String FDISCDEB_DEB_CODE = "debcode";
    public static final String FDISCDEB_RECORD_ID = "RecordId";
    public static final String FDISCDEB_TIEMSTAMP_COLUMN = "timestamp_column";

    // create String
    private static final String CREATE_FDISCDEB_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FDISCDEB + " (" + FDISCDEB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FDISCDEB_REF_NO + " TEXT, " + FDISCDEB_DEB_CODE + " TEXT, " + FDISCDET_RECORD_ID + " TEXT, " + FDISCHED_TIEMSTAMP_COLUMN + " TEXT); ";

    /**
     * ############################ FDiscslab table Details
     * ################################
     */

    // table
    public static final String TABLE_FDISCSLAB = "FDiscslab";
    // table attributes
    public static final String FDISCSLAB_ID = "FDiscdet_id";
    public static final String FDISCSLAB_REF_NO = "RefNo";
    public static final String FDISCSLAB_SEQ_NO = "seqno";
    public static final String FDISCSLAB_QTY_F = "Qtyf";
    public static final String FDISCSLAB_QTY_T = "Qtyt";
    public static final String FDISCSLAB_DIS_PER = "disper";
    public static final String FDISCSLAB_DIS_AMUT = "disamt";
    public static final String FDISCSLAB_RECORD_ID = "RecordId";
    public static final String FDISCSLAB_TIMESTAMP_COLUMN = "timestamp_column";

    // create String
    private static final String CREATE_FDISCSLAB_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FDISCSLAB + " (" + FDISCSLAB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FDISCSLAB_REF_NO + " TEXT, " + FDISCSLAB_SEQ_NO + " TEXT, " + FDISCSLAB_QTY_F + " TEXT, " + FDISCSLAB_QTY_T + " TEXT, " + FDISCSLAB_DIS_PER + " TEXT, " + FDISCSLAB_DIS_AMUT + " TEXT, " + FDISCSLAB_RECORD_ID + " TEXT, " + FDISCSLAB_TIMESTAMP_COLUMN + " TEXT); ";

    /**
     * ############################ FItenrHed table Details
     * ################################
     */

    // table
    public static final String TABLE_FITENRHED = "FItenrHed";
    // table attributes
    public static final String FITENRHED_ID = "FItenrHed_id";
    public static final String FITENRHED_COST_CODE = "CostCode";
    public static final String FITENRHED_DEAL_CODE = "DealCode";
    public static final String FITENRHED_MONTH = "Month";
    public static final String FITENRHED_REF_NO = "RefNo";
    public static final String FITENRHED_REMARKS1 = "Remarks1";
    public static final String FITENRHED_REP_CODE = "RepCode";
    public static final String FITENRHED_YEAR = "Year";


    // create String
    private static final String CREATE_FITENRHED_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FITENRHED + " (" + FITENRHED_ID + " TEXT , " + FITENRHED_COST_CODE + " TEXT, "
            + FITENRHED_DEAL_CODE + " TEXT, " + FITENRHED_MONTH + " TEXT, " + FITENRHED_REF_NO + " TEXT, "
            + FITENRHED_REMARKS1 + " TEXT, " + FITENRHED_REP_CODE + " TEXT, " + FITENRHED_YEAR + " TEXT); ";

    /**
     * ############################ FItenrDet table Details
     * ################################
     */

    // table
    public static final String TABLE_FITENRDET = "FItenrDet";
    // table attributes
    public static final String FITENRDET_REFNO = "RefNo";
    public static final String FITENRDET_TXNDATE = "TxnDate";
    public static final String FITENRDET_ROUTE = "RouteCode";
    public static final String FITENRDET_NO_OUT_LET = "NoOutlet";
    public static final String FITENRDET_NO_SHUCAL = "NoShcuCal";
    public static final String FITENRDET_REMARKS = "Remarks";
    public static final String FITENRDET_RECORDID = "RecordId";

    // create String
    private static final String CREATE_FITENRDET_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FITENRDET + " (" + FITENRDET_REFNO + " TEXT, "
            + FITENRDET_TXNDATE + " TEXT, " + FITENRDET_ROUTE + " TEXT, " + FITENRDET_NO_OUT_LET + " TEXT, "
            + FITENRDET_NO_SHUCAL + " TEXT, " + FITENRDET_REMARKS + " TEXT, " + FITENRDET_RECORDID + " TEXT); ";

    /**
     * ############################ FIteDebDet table Details
     * ################################
     */

    // table
    public static final String TABLE_FITEDEBDET = "FIteDebDet";
    // table attributes
    public static final String FITEDEBDET_REFNO = "RefNo";
    public static final String FITEDEBDET_TXNDATE = "TxnDate";
    public static final String FITEDEBDET_ROUTE_CODE = "RouteCode";
    public static final String FITEDEBDET_DEBCODEM = "DebCodeM";
    public static final String FITEDEBDET_RECORDID = "RecordId";


    // create String
    private static final String CREATE_FITEDEBDET_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FITEDEBDET + " (" + FITEDEBDET_REFNO + " TEXT , " + FITEDEBDET_TXNDATE + " TEXT, "
            + FITEDEBDET_ROUTE_CODE + " TEXT, " + FITEDEBDET_DEBCODEM + " TEXT, " + FITEDEBDET_RECORDID + " TEXT); ";

    /**
     * ############################ FinvHedL3 table Details
     * ################################
     */

    // table
    public static final String TABLE_FINVHEDL3 = "FinvHedL3";
    // table attributes
    public static final String FINVHEDL3_ID = "FinvHedL3_id";
    public static final String FINVHEDL3_DEB_CODE = "DebCode";
    public static final String FINVHEDL3_REF_NO = "RefNo";
    public static final String FINVHEDL3_REF_NO1 = "RefNo1";
    public static final String FINVHEDL3_TOTAL_AMT = "TotalAmt";
    public static final String FINVHEDL3_TOTAL_TAX = "TotalTax";
    public static final String FINVHEDL3_TXN_DATE = "TxnDate";

    // create String
    private static final String CREATE_FINVHEDL3_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FINVHEDL3 + " (" + FINVHEDL3_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FINVHEDL3_DEB_CODE + " TEXT, " + FINVHEDL3_REF_NO + " TEXT, " + FINVHEDL3_REF_NO1 + " TEXT, " + FINVHEDL3_TOTAL_AMT + " TEXT, " + FINVHEDL3_TOTAL_TAX + " TEXT, " + FINVHEDL3_TXN_DATE + " TEXT); ";

    private static final String TESTINVHEDL3 = "CREATE UNIQUE INDEX IF NOT EXISTS idxinvhedl3_something ON " + TABLE_FINVHEDL3 + " (" + FINVHEDL3_REF_NO + ")";

    /**
     * ############################ FinvHedL3 table Details
     * ################################
     */

    // table
    public static final String TABLE_FINVDETL3 = "FinvDetL3";
    // table attributes
    public static final String FINVDETL3_ID = "FinvDetL3_id";
    public static final String FINVDETL3_AMT = "Amt";
    public static final String FINVDETL3_ITEM_CODE = "ItemCode";
    public static final String FINVDETL3_QTY = "Qty";
    public static final String FINVDETL3_REF_NO = "RefNo";
    public static final String FINVDETL3_SEQ_NO = "SeqNo";
    public static final String FINVDETL3_TAX_AMT = "TaxAmt";
    public static final String FINVDETL3_TAX_COM_CODE = "TaxComCode";
    public static final String FINVDETL3_TXN_DATE = "TxnDate";

    // create String
    private static final String CREATE_FINVDETL3_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FINVDETL3 + " (" + FINVDETL3_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FINVDETL3_AMT + " TEXT, " + FINVDETL3_ITEM_CODE + " TEXT, " + FINVDETL3_QTY + " TEXT, " + FINVDETL3_REF_NO + " TEXT, " + FINVDETL3_SEQ_NO + " TEXT, " + FINVDETL3_TAX_AMT + " TEXT, " + FINVDETL3_TAX_COM_CODE + " TEXT, " + FINVDETL3_TXN_DATE + " TEXT); ";

    private static final String TESTINVDETL3 = "CREATE UNIQUE INDEX IF NOT EXISTS idxinvdetl3_something ON " + TABLE_FINVDETL3 + " (" + FINVDETL3_REF_NO + "," + FINVDETL3_ITEM_CODE + ")";

    /**
     * ############################ FTranDet ################################
     */
    public static final String TABLE_FTRANDET = "FTranDet";
    // table attributes
    public static final String FTRANDET_ID = "FTranDet_id";
    public static final String FTRANDET_REFNO = "RefNo";
    public static final String FTRANDET_TXNDATE = "TxnDate";
    public static final String FTRANDET_LOCCODE = "LocCode";
    public static final String FTRANDET_TXNTYPE = "TxnType";
    public static final String FTRANDET_SEQNO = "SeqNo";
    public static final String FTRANDET_ITEMCODE = "ItemCode";
    public static final String FTRANDET_QTY = "Qty";
    public static final String FTRANDET_AMT = "Amt";

    public static final String FTRANDET_CASEQTY = "CaseQty";
    public static final String FTRANDET_PICEQTY = "PiceQty";
    public static final String REMQTY = "remqty";

    // create String
    private static final String CREATE_FTRANDET_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FTRANDET + " (" + FTRANDET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FTRANDET_REFNO + " TEXT, " + FTRANDET_TXNDATE + " TEXT, " + FTRANDET_LOCCODE + " TEXT, " + FTRANDET_TXNTYPE + " TEXT, " + FTRANDET_SEQNO + " TEXT, " + FTRANDET_ITEMCODE + " TEXT, " + FTRANDET_QTY + " TEXT, " + FTRANDET_AMT + " TEXT," + FTRANDET_CASEQTY + " TEXT," + FTRANDET_PICEQTY + " TEXT," + REMQTY + " TEXT); ";

    /**
     * ############################ FTranHed ################################
     */

    public static final String TABLE_FTRANHED = "FTranHed";
    // table attributes
    public static final String FTRANHED_ID = "FTranHed_id";
    public static final String FTRANHED_REFNO = "RefNo";
    public static final String FTRANHED_TXNDATE = "TxnDate";
    public static final String FTRANHED_MANUREF = "ManuRef";
    public static final String FTRANHED_COSTCODE = "CostCode";
    public static final String FTRANHED_REMARKS = "Remarks";
    public static final String FTRANHED_TXNTYPE = "TxnType";
    public static final String FTRANHED_TOTALAMT = "TotalAmt";
    public static final String FTRANHED_DELPERSN = "DelPerson";
    public static final String FTRANHED_DELADD1 = "DelAdd1";
    public static final String FTRANHED_DELADD2 = "DelAdd2";
    public static final String FTRANHED_DELADD3 = "DelAdd3";
    public static final String FTRANHED_VEHICALNO = "VehicalNo";
    public static final String FTRANHED_PRTCOPY = "PrtCopy";
    public static final String FTRANHED_GLPOST = "GlPost";
    public static final String FTRANHED_GLBATCH = "GlBatch";
    public static final String FTRANHED_ADDUSER = "AddUser";
    public static final String FTRANHED_ADDDATE = "AddDate";

    public static final String FTRANHED_ADDMACH = "AddMach";
    public static final String FTRANHED_DEALCODE = "DealCode";

    public static final String FTRANHED_LONGITUDE = "Longitude";
    public static final String FTRANHED_LATITUDE = "Latitude";
    public static final String FTRANHED_LOCFROM = "Locfrom";
    public static final String FTRANHED_LOCTO = "Locto";
    public static final String FTRANHED_IS_SYNCED = "issync";
    public static final String FTRANHED_ACTIVE_STATE = "ActiveState";

    // create String
    private static final String CREATE_FTRANHED_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FTRANHED + " (" + FTRANHED_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FTRANHED_REFNO + " TEXT, " + FTRANHED_TXNDATE + " TEXT, " + FTRANHED_MANUREF + " TEXT, " + FTRANHED_COSTCODE + " TEXT, " + FTRANHED_REMARKS + " TEXT, " + FTRANHED_TXNTYPE + " TEXT, " + FTRANHED_TOTALAMT + " TEXT, " + FTRANHED_DELPERSN + " TEXT," + FTRANHED_DELADD1 + " TEXT," + FTRANHED_DELADD2 + " TEXT, " + FTRANHED_DELADD3 + " TEXT, " + FTRANHED_VEHICALNO + " TEXT, " + FTRANHED_PRTCOPY + " TEXT, " + FTRANHED_GLPOST + " TEXT, " + FTRANHED_GLBATCH + " TEXT, " + FTRANHED_ADDUSER + " TEXT," + FTRANHED_ADDDATE + " TEXT," + FTRANHED_ADDMACH + " TEXT," + FTRANHED_DEALCODE + " TEXT," + FTRANHED_LONGITUDE + " TEXT," + FTRANHED_LATITUDE + " TEXT," + FTRANHED_LOCFROM + " TEXT," + FTRANHED_LOCTO + " TEXT," + FTRANHED_IS_SYNCED + " TEXT," + FTRANHED_ACTIVE_STATE + " TEXT); ";

    /**
     * ############################ FTranIss ################################
     */

    public static final String TABLE_FTRANISS = "FTranIss";
    // table attributes
    public static final String FTRANISS_ID = "FTranHed_id";
    public static final String FTRANISS_REFNO = "RefNo";
    public static final String FTRANISS_TXNDATE = "TxnDate";
    public static final String FTRANISS_STKRECNO = "StkreCno";
    public static final String FTRANISS_STKRECDATE = "StkrecDate";
    public static final String FTRANISS_STKTXNNO = "StkTxnNo";
    public static final String FTRANISS_STKTXNDATE = "StkTxnDate";
    public static final String FTRANISS_STKTXNTYPE = "StkTxnType";
    public static final String FTRANISS_LOCCODE = "LocCode";
    public static final String FTRANISS_ITEMCODE = "ItemCode";
    public static final String FTRANISS_QTY = "Qty";
    public static final String FTRANISS_COSTPRICE = "CostPrice";
    public static final String FTRANISS_AMT = "Amt";
    public static final String FTRANISS_OTHCOST = "OthCost";

    // create String
    private static final String CREATE_FTRANISS_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FTRANISS + " (" + FTRANISS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FTRANISS_REFNO + " TEXT, " + FTRANISS_TXNDATE + " TEXT, " + FTRANISS_STKRECNO + " TEXT, " + FTRANISS_STKRECDATE + " TEXT, " + FTRANISS_STKTXNNO + " TEXT, " + FTRANISS_STKTXNDATE + " TEXT, " + FTRANISS_STKTXNTYPE + " TEXT, " + FTRANISS_LOCCODE + " TEXT," + FTRANISS_ITEMCODE + " TEXT," + FTRANISS_QTY + " TEXT, " + FTRANISS_COSTPRICE + " TEXT, " + FTRANISS_AMT + " TEXT, " + FTRANISS_OTHCOST + " TEXT); ";

    /**
     * ############################ FDaynonprdHed
     * ################################
     */

    public static final String TABLE_NONPRDHED = "FDaynPrdHed";
    // table attributes
    public static final String NONPRDHED_ID = "FNonprdHed_id";
    public static final String NONPRDHED_REFNO = "RefNo";
    public static final String NONPRDHED_REPCODE = "RepCode";
    public static final String NONPRDHED_TXNDAET = "TxnDate";
    public static final String NONPRDHED_DEALCODE = "DealCode";
    public static final String NONPRDHED_REMARK = "Remarks";
    public static final String NONPRDHED_COSTCODE = "CostCode";

    public static final String NONPRDHED_ADDUSER = "AddUser";
    public static final String NONPRDHED_ADDDATE = "AddDate";
    public static final String NONPRDHED_ADDMACH = "AddMach";

    public static final String NONPRDHED_IS_SYNCED = "ISsync";
    public static final String NONPRDHED_ADDRESS = "Address";

    public static final String NONPRDHED_DEBCODE = "DebCode";
    public static final String NONPRDHED_LONGITUDE = "Longitude";
    public static final String NONPRDHED_LATITUDE = "Latitude";
    public static final String NONPRDHED_IS_ACTIVE = "ISActive";
    // create String
    private static final String CREATE_TABLE_NONPRDHED = "CREATE  TABLE IF NOT EXISTS " + TABLE_NONPRDHED + " (" + NONPRDHED_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NONPRDHED_REFNO + " TEXT, " + NONPRDHED_TXNDAET + " TEXT, " + NONPRDHED_DEALCODE + " TEXT, " + NONPRDHED_REPCODE + " TEXT, " + NONPRDHED_REMARK + " TEXT, " + NONPRDHED_COSTCODE + " TEXT, " + NONPRDHED_ADDUSER + " TEXT, " + NONPRDHED_ADDDATE + " TEXT," + NONPRDHED_ADDMACH + " TEXT," + NONPRDHED_IS_SYNCED + " TEXT," + NONPRDHED_DEBCODE + " TEXT," + NONPRDHED_LATITUDE + " TEXT," + NONPRDHED_LONGITUDE + " TEXT," + NONPRDHED_ADDRESS + " TEXT," + NONPRDHED_IS_ACTIVE + " TEXT); ";
    /**
     * ############################ FDaynonprdDet
     * ################################
     */
    public static final String TABLE_NONPRDDET = "FDaynPrdDet";
    // table attributes

    public static final String NONPRDDET_ID = "FNonprdDet_id";
    public static final String NONPRDDET_REFNO = "RefNo";
    public static final String NONPRDDET_TXNDATE = "TxnDate";
    public static final String NONPRDDET_REASON = "Reason";
    public static final String NONPRDDET_REASON_CODE = "ReasonCode";


//


//	public static final String NONPRDDET_REPCODE = "RepCode";
//	public static final String NONPRDDET_IS_SYNCED = "ISsync";

    // create String
    private static final String CREATE_TABLE_NONPRDDET = "CREATE  TABLE IF NOT EXISTS " + TABLE_NONPRDDET + " (" + NONPRDDET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NONPRDDET_REFNO + " TEXT, " + NONPRDDET_TXNDATE + " TEXT, " + NONPRDDET_REASON_CODE + " TEXT, " + NONPRDDET_REASON + " TEXT); ";

    /**
     * ############################ FDamHed ################################
     */

    public static final String TABLE_FDAMHED = "FDamHed";
    // table attributes
    public static final String FDAMHED_ID = "FDamHed_id";
    public static final String FDAMHED_REFNO = "RefNo";
    public static final String FDAMHED_TXNDATE = "TxnDate";
    public static final String FDAMHED_MANUREF = "ManuRef";
    public static final String FDAMHED_DEALCODE = "DealCode";
    public static final String FDAMHED_COSTCODE = "CostCode";
    public static final String FDAMHED_REMARKS = "Remarks";
    public static final String FDAMHED_TXNTYPE = "TxnType";
    public static final String FDAMHED_TOTALAMT = "TotalAmt";
    public static final String FDAMHED_REACODE = "ReaCode";
    public static final String FDAMHED_ITEMTYPE = "ItemType";
    public static final String FDAMHED_GLPOST = "GlPost";
    public static final String FDAMHED_GLBATCH = "GlBatch";
    public static final String FDAMHED_ADDUSER = "AddUser";
    public static final String FDAMHED_ADDDATE = "AddDate";

    public static final String FDAMHED_ADDMACH = "AddMach";

    public static final String FDAMHED_LOCFROM = "Locfrom";
    public static final String FDAMHED_LOCTO = "Locto";
    public static final String FDAMHED_IS_SYNCED = "issync";
    public static final String FDAMHED_ACTIVE_STATE = "ActiveState";

    // create String
    private static final String CREATE_FDAMHED_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FDAMHED + " (" + FDAMHED_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FDAMHED_REFNO + " TEXT, " + FDAMHED_TXNDATE + " TEXT, " + FDAMHED_MANUREF + " TEXT, " + FDAMHED_COSTCODE + " TEXT, " + FDAMHED_REMARKS + " TEXT, " + FDAMHED_TXNTYPE + " TEXT, " + FDAMHED_TOTALAMT + " TEXT, " + FDAMHED_REACODE + " TEXT, " + FDAMHED_ITEMTYPE + " TEXT, " + FDAMHED_GLPOST + " TEXT, " + FDAMHED_GLBATCH + " TEXT, " + FDAMHED_ADDUSER + " TEXT," + FDAMHED_ADDDATE + " TEXT," + FDAMHED_ADDMACH + " TEXT," + FDAMHED_DEALCODE + " TEXT," + FDAMHED_LOCFROM + " TEXT," + FDAMHED_LOCTO + " TEXT," + FDAMHED_IS_SYNCED + " TEXT," + FDAMHED_ACTIVE_STATE + " TEXT); ";

    /**
     * ############################ FDamDet ################################
     */
    public static final String TABLE_FDAMDET = "FDamDet";
    // table attributes
    public static final String FDAMDET_ID = "FTranDet_id";
    public static final String FDAMDET_REFNO = "RefNo";
    public static final String FDAMDET_TXNDATE = "TxnDate";
    public static final String FDAMDET_LOCCODE = "LocCode";
    public static final String FDAMDET_TXNTYPE = "TxnType";
    public static final String FDAMDET_SEQNO = "SeqNo";
    public static final String FDAMDET_ITEMCODE = "ItemCode";
    public static final String FDAMDET_REACODE = "ReaCode";
    public static final String FDAMDET_REANAME = "ReaName";
    public static final String FDAMDET_QTY = "Qty";
    public static final String FDAMDET_AMT = "Amt";
    public static final String FDAMDET_CASEQTY = "CaseQty";
    public static final String FDAMDET_PICEQTY = "PiceQty";
    public static final String REMQTYDAM = "remqty";

    // create String
    private static final String CREATE_FDAMDET_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FDAMDET + " (" + FDAMDET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FDAMDET_REFNO + " TEXT, " + FDAMDET_TXNDATE + " TEXT, " + FDAMDET_LOCCODE + " TEXT, " + FDAMDET_TXNTYPE + " TEXT, " + FDAMDET_SEQNO + " TEXT, " + FDAMDET_ITEMCODE + " TEXT, " + FDAMDET_REACODE + " TEXT, " + FDAMDET_REANAME + " TEXT, " + FDAMDET_QTY + " TEXT, " + FDAMDET_AMT + " TEXT," + FDAMDET_CASEQTY + " TEXT," + FDAMDET_PICEQTY + " TEXT," + REMQTYDAM + " TEXT); ";



	/*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-fTOUR-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    public static final String TABLE_FTOUR = "fTour";
    public static final String FTOUR_ID = "Id";
    public static final String FTOUR_DATE = "tDate";
    public static final String FTOUR_S_TIME = "StartTime";
    public static final String FTOUR_F_TIME = "EndTime";
    public static final String FTOUR_VEHICLE = "Vehicle";
    public static final String FTOUR_S_KM = "StartKm";
    public static final String FTOUR_F_KM = "EndKm";
    public static final String FTOUR_ROUTE = "Route";

    public static final String FTOUR_DRIVER = "Driver";
    public static final String FTOUR_ASSIST = "Assist";

    public static final String FTOUR_DISTANCE = "Distance";
    public static final String FTOUR_IS_SYNCED = "IsSynced";

    public static final String FTOUR_REPCODE = "RepCode";
    public static final String FTOUR_MAC = "MacAdd";

    public static final String CREATE_FTOUR_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_FTOUR + " (" + FTOUR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

            FTOUR_DATE + " TEXT, " + FTOUR_S_TIME + " TEXT, " + FTOUR_F_TIME + " TEXT, " + FTOUR_VEHICLE + " TEXT, " + FTOUR_S_KM + " TEXT, " + FTOUR_F_KM + " TEXT, " + FTOUR_DISTANCE + " TEXT, " + FTOUR_IS_SYNCED + " TEXT, "

            + FTOUR_REPCODE + " TEXT, " + FTOUR_DRIVER + " TEXT, " + FTOUR_ASSIST + " TEXT, " + FTOUR_MAC + " TEXT, " + FTOUR_ROUTE + " TEXT ); ";


    /**
     * ############################ FAdjHed ################################
     */

    public static final String TABLE_FADJHED = "FAdjHed";
    // table attributes
    public static final String FADJHED_ID = "FAdjHed_id";
    public static final String FADJHED_REFNO = "RefNo";
    public static final String FADJHED_TXNDATE = "TxnDate";
    public static final String FADJHED_COSTCODE = "CostCode";
    public static final String FADJHED_MANUREF = "ManuRef";
    public static final String FADJHED_REFNO2 = "RefNo2";
    public static final String FADJHED_TXNTYPE = "TxnType";
    public static final String FADJHED_TOTALAMT = "TotalAmt";
    public static final String FADJHED_REMARKS = "Remarks";
    public static final String FADJHED_LOCCODE = "LocCode";
    public static final String FADJHED_DEALCODE = "DealCode";
    public static final String FADJHED_REACODE = "ReaCode";
    public static final String FADJHED_GLPOST = "GlPost";
    public static final String FADJHED_GLBATCH = "GlBatch";
    public static final String FADJHED_ADDUSER = "AddUser";
    public static final String FADJHED_ADDDATE = "AddDate";
    public static final String FADJHED_ADDMACH = "AddMach";

    public static final String FADJHED_IS_SYNCED = "issync";
    public static final String FADJHED_ACTIVE_STATE = "ActiveState";

    // create String
    private static final String CREATE_FADJHED_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FADJHED + " (" + FADJHED_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FADJHED_REFNO + " TEXT, " + FADJHED_TXNDATE + " TEXT, " + FADJHED_COSTCODE + " TEXT, " + FADJHED_MANUREF + " TEXT, " + FADJHED_REFNO2 + " TEXT, " + FADJHED_TXNTYPE + " TEXT, " + FADJHED_TOTALAMT + " TEXT, " + FADJHED_REMARKS + " TEXT, " + FADJHED_LOCCODE + " TEXT, " + FADJHED_DEALCODE + " TEXT, " + FADJHED_REACODE + " TEXT, " + FADJHED_GLPOST + " TEXT, " + FADJHED_GLBATCH + " TEXT, " + FADJHED_ADDUSER + " TEXT," + FADJHED_ADDDATE + " TEXT," + FADJHED_ADDMACH + " TEXT," + FADJHED_IS_SYNCED + " TEXT," + FADJHED_ACTIVE_STATE + " TEXT); ";

    /**
     * ############################ FAdjDet ################################
     */
    public static final String TABLE_FADJDET = "FAdjDet";
    // table attributes
    public static final String FADJDET_ID = "FTranDet_id";
    public static final String FADJDET_REFNO = "RefNo";
    public static final String FADJDET_TXNDATE = "TxnDate";
    public static final String FADJDET_TXNTYPE = "TxnType";
    public static final String FADJDET_SEQNO = "SeqNo";
    public static final String FADJDET_ITEMCODE = "ItemCode";
    public static final String FADJDET_QTY = "Qty";
    public static final String FADJDET_CASEQTY = "CaseQty";
    public static final String FADJDET_PICEQTY = "PiceQty";
    public static final String FADJDET_COSTPRICE = "CostPrice";
    public static final String FADJDET_AMT = "Amt";
    public static final String FADJDET_REACODE = "ReaCode";
    public static final String FADJDET_REANAME = "ReaName";

    // create String
    private static final String CREATE_FADJDET_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FADJDET + " (" + FADJDET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FADJDET_REFNO + " TEXT, " + FADJDET_TXNDATE + " TEXT, " + FADJDET_TXNTYPE + " TEXT, " + FADJDET_SEQNO + " TEXT, " + FADJDET_ITEMCODE + " TEXT, " + FADJDET_QTY + " TEXT, " + FADJDET_CASEQTY + " TEXT," + FADJDET_PICEQTY + " TEXT," + FADJDET_COSTPRICE + " TEXT, " + FADJDET_AMT + " TEXT, " + FADJDET_REACODE + " TEXT, " + FADJDET_REANAME + " TEXT ); ";

    /**
     * ############################ FDayExpHed ################################
     */

    public static final String TABLE_FDAYEXPHED = "FDayExpHed";
    // table attributes
    public static final String FDAYEXPHED_ID = "FDayExpHed_id";
    public static final String FDAYEXPHED_REFNO = "RefNo";
    public static final String FDAYEXPHED_TXNDATE = "TxnDate";
    public static final String FDAYEXPHED_REPNAME = "RepName";
    public static final String FDAYEXPHED_DEALCODE = "DealCode";
    public static final String FDAYEXPHED_COSTCODE = "CostCode";
    public static final String FDAYEXPHED_REPCODE = "RepCode";
    public static final String FDAYEXPHED_REMARKS = "Remarks";
    public static final String FDAYEXPHED_AREACODE = "AreaCode";
    public static final String FDAYEXPHED_ADDUSER = "AddUser";
    public static final String FDAYEXPHED_ADDDATE = "AddDate";
    public static final String FDAYEXPHED_ADDMATCH = "AddMach";
    public static final String FDAYEXPHED_LONGITUDE = "Longitude";
    public static final String FDAYEXPHED_LATITUDE = "Latitude";
    public static final String FDAYEXPHED_ISSYNC = "issync";
    public static final String FDAYEXPHED_ACTIVESTATE = "ActiveState";
    public static final String FDAYEXPHED_TOTAMT = "TotAmt";
    public static final String FDAYEXPHED_ADDRESS = "Address";

    // create String
    private static final String CREATE_FDAYEXPHED_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FDAYEXPHED + " (" + FDAYEXPHED_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FDAYEXPHED_REFNO + " TEXT, " + FDAYEXPHED_TXNDATE + " TEXT, " + FDAYEXPHED_REPNAME + " TEXT, " + FDAYEXPHED_DEALCODE + " TEXT, " + FDAYEXPHED_COSTCODE + " TEXT, " + FDAYEXPHED_REPCODE + " TEXT, " + FDAYEXPHED_REMARKS + " TEXT, " + FDAYEXPHED_AREACODE + " TEXT, " + FDAYEXPHED_ADDUSER + " TEXT, " + FDAYEXPHED_ADDDATE + " TEXT, " + FDAYEXPHED_ADDMATCH + " TEXT, " + FDAYEXPHED_LONGITUDE + " TEXT," + FDAYEXPHED_LATITUDE + " TEXT," + FDAYEXPHED_ISSYNC + " TEXT," + FDAYEXPHED_ACTIVESTATE + " TEXT," + FDAYEXPHED_TOTAMT + " TEXT," + FDAYEXPHED_ADDRESS + " TEXT); ";

    /**
     * ############################ FDayExpDet ################################
     */
    public static final String TABLE_FDAYEXPDET = "FDayExpDet";
    // table attributes
    public static final String FDAYEXPDET_ID = "FDayExpDet_id";
    public static final String FDAYEXPDET_REFNO = "RefNo";
    public static final String FDAYEXPDET_TXNDATE = "TxnDate";
    ;
    public static final String FDAYEXPDET_SEQNO = "SeqNo";
    public static final String FDAYEXPDET_EXPCODE = "ExpCode";
    public static final String FDAYEXPDET_AMT = "Amt";

    // create String
    private static final String CREATE_FDAYEXPDET_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FDAYEXPDET + " (" + FDAYEXPDET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FDAYEXPDET_REFNO + " TEXT, " + FDAYEXPDET_TXNDATE + " TEXT, " + FDAYEXPDET_SEQNO + " TEXT, " + FDAYEXPDET_EXPCODE + " TEXT, " + FDAYEXPDET_AMT + " TEXT); ";

    /**
     * ############################ FInvRHed ################################
     */

    public static final String TABLE_FINVRHED = "FInvRHed";
    // table attributes
    public static final String FINVRHED_ID = "FInvRHed_id";
    public static final String FINVRHED_REFNO = "RefNo";
    public static final String FINVRHED_TXNDATE = "TxnDate";
    public static final String FINVRHED_INV_REFNO = "InvRefNo";
    public static final String FINVRHED_INV_DATE = "InvDate";
    public static final String FINVRHED_REMARKS = "Remarks";
    public static final String FINVRHED_DEBCODE = "DebCode";
    public static final String FINVRHED_TOTAL_AMT = "TotalAmt";
    public static final String FINVRHED_ADD_DATE = "AddDate";
    public static final String FINVRHED_ADD_MACH = "AddMach";
    public static final String FINVRHED_ADD_USER = "AddUser";
    public static final String FINVRHED_MANUREF = "ManuRef";
    public static final String FINVRHED_IS_ACTIVE = "IsActive";
    public static final String FINVRHED_IS_SYNCED = "IsSync";
    public static final String FINVRHED_REPCODE = "RepCode";
    public static final String FINVRHED_COSTCODE = "CostCode";
    public static final String FINVRHED_LOCCODE = "LOCCode";
    public static final String FINVRHED_UPLOAD_DATE = "UploadTime";

    // create String
    private static final String CREATE_FINVRHED_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FINVRHED + " (" + FINVRHED_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FINVRHED_REFNO + " TEXT, " + FINVRHED_TXNDATE + " TEXT, " + FINVRHED_INV_REFNO + " TEXT, " + FINVRHED_INV_DATE + " TEXT, " + FINVRHED_REMARKS + " TEXT, " + FINVRHED_DEBCODE + " TEXT, " + FINVRHED_TOTAL_AMT + " TEXT, " + FINVRHED_ADD_DATE + " TEXT, " + FINVRHED_ADD_MACH + " TEXT, " + FINVRHED_ADD_USER + " TEXT, " + FINVRHED_MANUREF + " TEXT, " + FINVRHED_IS_ACTIVE + " TEXT, " + FINVRHED_IS_SYNCED + " TEXT, " + FINVRHED_REPCODE + " TEXT, " + FINVRHED_COSTCODE + " TEXT, " + FINVRHED_LOCCODE + " TEXT, " + FINVRHED_UPLOAD_DATE + " TEXT); ";

    /**
     * ############################ FInvRDet ################################
     */
    public static final String TABLE_FINVRDET = "FInvRDet";
    // table attributes
    public static final String FINVRDET_ID = "FInvRDet_id";
    public static final String FINVRDET_REFNO = "RefNo";
    public static final String FINVRDET_ITEMCODE = "ItemCode";
    ;
    public static final String FINVRDET_TXN_DATE = "TxnDate";
    public static final String FINVRDET_COST_PRICE = "CostPrice";
    public static final String FINVRDET_AMT = "Amt";
    public static final String FINVRDET_QTY = "Qty";
    public static final String FINVRDET_FREE_QTY = "FreeQty";
    public static final String FINVRDET_IS_ACTIVE = "IsActive";
    public static final String FINVRDET_PACKSIZE = "PackSize";
    public static final String FINVRDET_EXPR_DATE = "ExprDate";
    public static final String FINVRDET_RETURN_REASON = "ReturnReason";

    // create String
    private static final String CREATE_FINVRDET_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FINVRDET + " (" + FINVRDET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FINVRDET_REFNO + " TEXT, " + FINVRDET_ITEMCODE + " TEXT, " + FINVRDET_TXN_DATE + " TEXT, " + FINVRDET_COST_PRICE + " TEXT, " + FINVRDET_AMT + " TEXT, " + FINVRDET_QTY + " TEXT, " + FINVRDET_FREE_QTY + " TEXT, " + FINVRDET_IS_ACTIVE + " TEXT, " + FINVRDET_PACKSIZE + " TEXT, " + FINVRDET_RETURN_REASON + " TEXT, " + FINVRDET_EXPR_DATE + " TEXT); ";

    /**
     * ############################ FRepLoc ################################
     */
    public static final String TABLE_FREPLOC = "FRepLoc";
    // table attributes
    public static final String FREPLOC_ID = "FRepLoc_id";
    public static final String FREPLOC_REPCODE = "RepCode";
    public static final String FREPLOC_LOCCODE = "LocCode";
    ;
    public static final String FREPLOC_COSTCODE = "CostCose";

    // create String
    private static final String CREATE_FREPLOC_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FREPLOC + " (" + FREPLOC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FREPLOC_REPCODE + " TEXT, " + FREPLOC_LOCCODE + " TEXT, " + FREPLOC_COSTCODE + " TEXT); ";

    // --------------------------------------------------------------------------------------------------------------
    public static final String TABLE_TEMP_FDEBTOR = "FTempDebtor";
    // table attributes


    // create String
    //  private static final String CREATE_TABLE_TEMP_FDEBTOR = "CREATE  TABLE IF NOT EXISTS " + TABLE_TEMP_FDEBTOR + " (" + FDEBTOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FDEBTOR_CODE + " TEXT, " + FDEBTOR_NAME + " TEXT); ";

    // --------------------------------------------------------------------------------------------------------------

    public static final String TABLE_FPRECDET = "fpRecDet";

    public static final String FPRECDET_ID = "Id";
    public static final String FPRECDET_REFNO = "RefNo";
    public static final String FPRECDET_REFNO1 = "RefNo1";
    public static final String FPRECDET_REFNO2 = "RefNo2";
    public static final String FPRECDET_DEBCODE = "DebCode";
    public static final String FPRECDET_SALEREFNO = "SaleRefNo";
    public static final String FPRECDET_MANUREF = "ManuRef";
    public static final String FPRECDET_TXNTYPE = "TxnType";
    public static final String FPRECDET_TXNDATE = "TxnDate";
    public static final String FPRECDET_DTXNDATE = "DtxnDate";
    public static final String FPRECDET_DTXNTYPE = "DtxnType";
    public static final String FPRECDET_DCURCODE = "DCurCode";
    public static final String FPRECDET_DCURRATE = "DCurRate";
    public static final String FPRECDET_OCURRATE = "OCurRate";
    public static final String FPRECDET_REPCODE = "RepCode";
    public static final String FPRECDET_AMT = "Amt";
    public static final String FPRECDET_BAMT = "BAmt";
    public static final String FPRECDET_ALOAMT = "AloAmt";
    public static final String FPRECDET_OVPAYAMT = "OvPayAmt";
    public static final String FPRECDET_OVPAYBAL = "OvPayBal";
    public static final String FPRECDET_RECORDID = "RecordId";
    public static final String FPRECDET_TIMESTAMP = "timestamp_column";
    public static final String FPRECDET_ISDELETE = "IsDelete";
    public static final String FPRECDET_REMARK = "Remark";


    private static final String CREATE_FPRECDET_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FPRECDET + " (" + FPRECDET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FPRECDET_REFNO + " TEXT, " + FPRECDET_REFNO1 + " TEXT, " + FPRECDET_REFNO2 + " TEXT, " + FPRECDET_DEBCODE + " TEXT, " + FPRECDET_SALEREFNO + " TEXT, "

            + FPRECDET_MANUREF + " TEXT, " + FPRECDET_TXNTYPE + " TEXT, " + FPRECDET_TXNDATE + " TEXT, "

            + FPRECDET_DTXNDATE + " TEXT, " + FPRECDET_DTXNTYPE + " TEXT, " + FPRECDET_DCURCODE + " TEXT, " + FPRECDET_DCURRATE + " TEXT, "

            + FPRECDET_OCURRATE + " TEXT, " + FPRECDET_REPCODE + " TEXT, " + FPRECDET_AMT + " TEXT, " + FPRECDET_BAMT + " TEXT, "

            + FPRECDET_ALOAMT + " TEXT, " + FPRECDET_OVPAYAMT + " TEXT, " + FPRECDET_REMARK + " TEXT, " + FPRECDET_OVPAYBAL + " TEXT, " + FPRECDET_RECORDID + " TEXT, " + FPRECDET_ISDELETE + " TEXT, " + FPRECDET_TIMESTAMP + " TEXT );";

	/*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    public static final String TABLE_FPRECHED = "fpRecHed";

    public static final String FPRECHED_ID = "Id";
    public static final String FPRECHED_REFNO = "RefNo";
    public static final String FPRECHED_REFNO1 = "RefNo1";
    public static final String FPRECHED_MANUREF = "ManuRef";
    public static final String FPRECHED_SALEREFNO = "SaleRefNo";
    public static final String FPRECHED_REPCODE = "RepCode";
    public static final String FPRECHED_TXNTYPE = "TxnType";
    public static final String FPRECHED_CHQNO = "Chqno";
    public static final String FPRECHED_CHQDATE = "ChqDate";
    public static final String FPRECHED_TXNDATE = "TxnDate";
    public static final String FPRECHED_CURCODE = "CurCode";
    public static final String FPRECHED_CURRATE1 = "CurRate1";
    public static final String FPRECHED_DEBCODE = "DebCode";
    public static final String FPRECHED_TOTALAMT = "TotalAmt";
    public static final String FPRECHED_BTOTALAMT = "BTotalAmt";
    public static final String FPRECHED_PAYTYPE = "PayType";
    public static final String FPRECHED_PRTCOPY = "PrtCopy";
    public static final String FPRECHED_REMARKS = "Remarks";
    public static final String FPRECHED_ADDUSER = "AddUser";
    public static final String FPRECHED_ADDMACH = "AddMach";
    public static final String FPRECHED_ADDDATE = "AddDate";
    public static final String FPRECHED_RECORDID = "RecordId";
    public static final String FPRECHED_TIMESTAMP = "timestamp_column";
    public static final String FPRECHED_CURRATE = "CurRate";
    public static final String FPRECHED_CUSBANK = "CusBank";
    public static final String FPRECHED_COST_CODE = "CostCode";
    public static final String FPRECHED_LONGITUDE = "Longitude";
    public static final String FPRECHED_LATITUDE = "Latitude";
    public static final String FPRECHED_ADDRESS = "Address";
    public static final String FPRECHED_START_TIME = "StartTime";
    public static final String FPRECHED_END_TIME = "EndTime";
    public static final String FPRECHED_ISACTIVE = "IsActive";
    public static final String FPRECHED_ISSYNCED = "IsSynced";
    public static final String FPRECHED_ISDELETE = "IsDelete";
    public static final String FPRECHED_BANKCODE = "BankCode";
    public static final String FPRECHED_BRANCHCODE = "BranchCode";
    public static final String FPRECHED_ADDUSER_NEW = "AddUserNew";

    private static final String CREATE_FPRECHED_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FPRECHED + " (" + FPRECHED_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

            FPRECHED_REFNO + " TEXT, " + FPRECHED_REFNO1 + " TEXT, " + FPRECHED_MANUREF + " TEXT, " + FPRECHED_SALEREFNO + " TEXT, " +

            FPRECHED_REPCODE + " TEXT, " + FPRECHED_TXNTYPE + " TEXT, " + FPRECHED_CHQNO + " TEXT, " + FPRECHED_CHQDATE + " TEXT, " + FPRECHED_TXNDATE + " TEXT, " + FPRECHED_CURCODE + " TEXT, " +

            FPRECHED_CURRATE1 + " TEXT, " + FPRECHED_DEBCODE + " TEXT, " + FPRECHED_TOTALAMT + " TEXT, " + FPRECHED_BANKCODE + " TEXT, " + FPRECHED_BRANCHCODE + " TEXT, " +

            FPRECHED_BTOTALAMT + " TEXT, " + FPRECHED_PAYTYPE + " TEXT, " + FPRECHED_PRTCOPY + " TEXT, " + FPRECHED_REMARKS + " TEXT, " + FPRECHED_ADDUSER + " TEXT, " + FPRECHED_ADDMACH + " TEXT, " + FPRECHED_ADDDATE + " TEXT, " +

            FPRECHED_RECORDID + " TEXT, " + FPRECHED_TIMESTAMP + " TEXT, " + FPRECHED_ISDELETE + " TEXT, " + FPRECHED_COST_CODE + " TEXT, " +

            FPRECHED_LONGITUDE + " TEXT, " + FPRECHED_LATITUDE + " TEXT, " + FPRECHED_ADDRESS + " TEXT, " + FPRECHED_START_TIME + " TEXT, " + FPRECHED_END_TIME + " TEXT, " + FPRECHED_ISACTIVE + " TEXT, " + FPRECHED_ISSYNCED + " TEXT, " + FPRECHED_CURRATE + " TEXT, " + FPRECHED_CUSBANK + " TEXT);";

	/*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    public static final String TABLE_FPRECHEDS = "fpRecHedS";

    private static final String CREATE_FPRECHEDS_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FPRECHEDS + " (" + FPRECHED_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

            FPRECHED_REFNO + " TEXT, " + FPRECHED_REFNO1 + " TEXT, " + FPRECHED_MANUREF + " TEXT, " + FPRECHED_SALEREFNO + " TEXT, " +

            FPRECHED_REPCODE + " TEXT, " + FPRECHED_TXNTYPE + " TEXT, " + FPRECHED_CHQNO + " TEXT, " + FPRECHED_CHQDATE + " TEXT, " + FPRECHED_TXNDATE + " TEXT, " + FPRECHED_CURCODE + " TEXT, " +

            FPRECHED_CURRATE1 + " TEXT, " + FPRECHED_DEBCODE + " TEXT, " + FPRECHED_TOTALAMT + " TEXT, " + FPRECHED_BANKCODE + " TEXT, " + FPRECHED_BRANCHCODE + " TEXT, " +

            FPRECHED_BTOTALAMT + " TEXT, " + FPRECHED_PAYTYPE + " TEXT, " + FPRECHED_PRTCOPY + " TEXT, " + FPRECHED_REMARKS + " TEXT, " + FPRECHED_ADDUSER + " TEXT, " + FPRECHED_ADDMACH + " TEXT, " + FPRECHED_ADDDATE + " TEXT, " +

            FPRECHED_RECORDID + " TEXT, " + FPRECHED_TIMESTAMP + " TEXT, " + FPRECHED_ISDELETE + " TEXT, " + FPRECHED_COST_CODE + " TEXT, "

            + FPRECHED_LONGITUDE + " TEXT, " + FPRECHED_LATITUDE + " TEXT, " + FPRECHED_ADDUSER_NEW + " TEXT, " + FPRECHED_ADDRESS + " TEXT, " + FPRECHED_START_TIME + " TEXT, " + FPRECHED_END_TIME + " TEXT, " + FPRECHED_ISACTIVE + " TEXT, " + FPRECHED_ISSYNCED + " TEXT, " + FPRECHED_CURRATE + " TEXT, " + FPRECHED_CUSBANK + " TEXT);";

    public static final String TABLE_FPRECDETS = "fpRecDetS";


    private static final String CREATE_FPRECDETS_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FPRECDETS + " (" + FPRECDET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FPRECDET_REFNO + " TEXT, " + FPRECDET_REFNO1 + " TEXT, " + FPRECDET_REFNO2 + " TEXT, " + FPRECDET_DEBCODE + " TEXT, " + FPRECDET_SALEREFNO + " TEXT, "

            + FPRECDET_MANUREF + " TEXT, " + FPRECDET_TXNTYPE + " TEXT, " + FPRECDET_TXNDATE + " TEXT, "

            + FPRECDET_DTXNDATE + " TEXT, " + FPRECDET_DTXNTYPE + " TEXT, " + FPRECDET_DCURCODE + " TEXT, " + FPRECDET_DCURRATE + " TEXT, "

            + FPRECDET_OCURRATE + " TEXT, " + FPRECDET_REPCODE + " TEXT, " + FPRECDET_AMT + " TEXT, " + FPRECDET_BAMT + " TEXT, " + FPRECDET_ISDELETE + " TEXT, "

            + FPRECDET_REMARK + " TEXT, " + FPRECDET_ALOAMT + " TEXT, " + FPRECDET_OVPAYAMT + " TEXT, " + FPRECDET_OVPAYBAL + " TEXT, " + FPRECDET_RECORDID + " TEXT, " + FPRECDET_TIMESTAMP + " TEXT );";

    /**
     * ############################ fRouteDet table Details
     * ################################
     */
    // table
    public static final String TABLE_FORDSTAT = "fOrdStat";
    // table attributes
    public static final String FORDSTAT_ID = "fOrdStat_id";
    public static final String FORDSTAT_ORDREFNO = "OrdRefNo";
    public static final String FORDSTAT_INVREFNO = "InvRefNo";
    public static final String FORDSTAT_OFINREFNO = "OfInRef";

    // create String
    private static final String CREATE_FORDSTAT_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FORDSTAT + " (" + FORDSTAT_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FORDSTAT_ORDREFNO + " TEXT, " + FORDSTAT_INVREFNO + " TEXT, " + FORDSTAT_OFINREFNO + " TEXT); ";

    public static final String TABLE_FGPSLOC = "fGPSLoc";
    // table attributes
    public static final String FGPSLOC_ID = "fGPSLoc_id";
    public static final String FGPSLOC_REPCODE = "RepCode";
    public static final String FGPSLOC_GPSDATE = "GPSDate";
    public static final String FGPSLOC_LONGITUDE = "Longitude";
    public static final String FGPSLOC_LATITUDE = "Latitude";
    public static final String FGPSLOC_BATTPER = "Battper";
    public static final String FGPSLOC_SEQNO = "SeqNo";
    public static final String FGPSLOC_ISSYNCED = "isSync";

    // create String
    private static final String CREATE_FGPSLOC_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FGPSLOC + " (" + FGPSLOC_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FGPSLOC_REPCODE + " TEXT, " + FGPSLOC_GPSDATE + " TEXT, "
            + FGPSLOC_LONGITUDE + " TEXT, " + FGPSLOC_LATITUDE + " TEXT, " + FGPSLOC_BATTPER + " TEXT, "
            + FGPSLOC_SEQNO + " TEXT, " + FGPSLOC_ISSYNCED + " TEXT); ";


    //-------------------------dhnaushika 2018/3/5----------create temp table for items insert------------------------------------

    public static final String TABLE_FPRODUCT = "fProducts";
    public static final String FPRODUCT_ID = "id";
    public static final String FPRODUCT_ITEMCODE = "itemcode";
    public static final String FPRODUCT_ITEMNAME = "itemname";
    public static final String FPRODUCT_PRICE = "price";
    public static final String FPRODUCT_QOH = "qoh";
    public static final String FPRODUCT_NOUCASE = "NOUCase";
    public static final String FPRODUCT_PACK = "Pack";
    public static final String FPRODUCT_QTY = "qty";
    public static final String FPRODUCT_FREESCHEMA = "fSchema";
    public static final String FPRODUCT_supCode = "SupCode";


    private static final String CREATE_FPRODUCT_TABLE = "CREATE  TABLE IF NOT EXISTS " + TABLE_FPRODUCT + " ("
            + FPRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FPRODUCT_ITEMCODE + " TEXT, "
            + FPRODUCT_ITEMNAME + " TEXT, "
            + FPRODUCT_PRICE + " TEXT, "
            + FPRODUCT_NOUCASE + " TEXT, "
            + FPRODUCT_PACK + " TEXT, "
            + FPRODUCT_QOH + " TEXT, "
            + FPRODUCT_QTY + " TEXT, "
            + FPRODUCT_FREESCHEMA + " TEXT, "
            + FPRODUCT_supCode + " TEXT); ";

    private static final String INDEX_PRODUCTS = "CREATE UNIQUE INDEX IF NOT EXISTS ui_product ON " + TABLE_FPRODUCT + " (itemcode);";
    private static final String INDEX_FMDEBTOR= "CREATE UNIQUE INDEX IF NOT EXISTS ui_debtor ON " + TABLE_FMDEBTOR + " (DebCodeM);";
    private static final String INDEX_FMISSUEHED= "CREATE UNIQUE INDEX IF NOT EXISTS ui_issue ON " + TABLE_FMISS_HED + " (RefNo);";
    private static final String INDEX_FMISSUEDET= "CREATE UNIQUE INDEX IF NOT EXISTS ui_issue_det ON " + TABLE_FMISS_DET + " (GItemCode);";


    //NEW CUSTOMER REGISTRATION
    public static final String TABLE_NEW_CUSTOMER = "NewCustomer";
    public static final String TABLE_REC_ID = "recID"; //1
    public static final String CUSTOMER_ID = "customerID"; //2
    public static final String CUSTOMER_OTHER_CODE = "otherCode";//3
    public static final String COMPANY_REG_CODE = "comRegCode"; //4
    public static final String NAME = "Name"; //5
    public static final String NIC = "Nic"; //6
    public static final String ADDRESS1 = "Address1"; //7
    public static final String ADDRESS2 = "Address2"; //8
    public static final String CITY = "City"; //9
    public static final String PHONE = "Phone"; //10
    public static final String MOBILE = "Mobile"; //27
    public static final String FAX = "Fax"; //11
    public static final String E_MAIL = "Email"; //12
    public static final String C_TOWN = "customer_Town";  //13
    public static final String ROUTE_ID = "route_ID"; //14
    public static final String DISTRICT = "District"; //15
    public static final String OLD_CODE = "old_Code"; //16
    public static final String TxnDate = "tnxDate"; //17
    public static final String C_IMAGE = "Image"; //18
    public static final String C_IMAGE1 = "Image1";  //19
    public static final String C_IMAGE2 = "Image2"; //20
    public static final String C_IMAGE3 = "Image3";  //21
    public static final String C_LONGITUDE = "lng";  //22
    public static final String C_LATITUDE = "lat"; //23
    public static final String C_ADD_DATE = "AddDate"; //24
    public static final String C_ADD_MACH = "AddMach"; //25
    public static final String C_IS_SYNCED = "isSynced"; //26

    private static final String CREATE_NEW_CUSTOMER = "CREATE  TABLE IF NOT EXISTS " + TABLE_NEW_CUSTOMER + " ("
            + TABLE_REC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CUSTOMER_ID + " TEXT, "
            + NAME + " TEXT, "
            + NIC + " TEXT, "
            + CUSTOMER_OTHER_CODE + " TEXT, "
            + COMPANY_REG_CODE + " TEXT, "
            + DISTRICT + " TEXT, "
            + C_TOWN + " TEXT, "
            + ROUTE_ID + " TEXT, "
            + ADDRESS1 + " TEXT, "
            + ADDRESS2 + " TEXT, "
            + CITY + " TEXT, "
            + MOBILE + " TEXT, "
            + PHONE + " TEXT, "
            + FAX + " TEXT, "
            + E_MAIL + " TEXT, "
            + OLD_CODE + " TEXT, "
            + C_IMAGE + " TEXT, "
            + C_IMAGE1 + " TEXT, "
            + C_IMAGE2 + " TEXT, "
            + C_IMAGE3 + " TEXT, "
            + C_LONGITUDE + " TEXT, "
            + C_LATITUDE + " TEXT, "
            + C_ADD_DATE + " TEXT, "
            + C_ADD_MACH + " TEXT, "
            + C_IS_SYNCED + " TEXT); ";

    @Override
    public void onCreate(SQLiteDatabase arg0) {

        arg0.execSQL(CREATE_NEW_CUSTOMER);
        arg0.execSQL(CREATE_FMITEMS_DET);
        arg0.execSQL(CREATE_FCOUNTRY_MGR);
        arg0.execSQL(CREATE_TABLE_FMITEMS);
        arg0.execSQL(CREATE_FSALREP_TABLE);
        arg0.execSQL(CREATE_FMAREA_TABLE);
        arg0.execSQL(CREATE_TABLE_FAREA_SUB);
        arg0.execSQL(CREATE_TABLE_FMDEBDET);
        arg0.execSQL(CREATE_TABLE_FEXPENSE);
        arg0.execSQL(CREATE_TABLE_FEXP_GRP);
        arg0.execSQL(CREATE_FITENRHED_TABLE);
        arg0.execSQL(CREATE_FITENRHED_TABLE);
        arg0.execSQL(CREATE_TABLE_FMSALREP);
        arg0.execSQL(CREATE_FMISS_SUBDET);
        arg0.execSQL(CREATE_FMISS_HED);
        arg0.execSQL(CREATE_FMISS_DET);
        //above table were added by sathya
        arg0.execSQL(CREATE_FDISTRICT);
        arg0.execSQL(CREATE_SERVER_DB_TABLE);
        arg0.execSQL(CREATE_FDEBTOR_TABLE);
        arg0.execSQL(CREATE_FCONTROL_TABLE);
        arg0.execSQL(CREATE_FCOMPANYSETTING_TABLE);
        arg0.execSQL(CREATE_FINVDET_TABLE);
        arg0.execSQL(CREATE_FROUTE_TABLE);
        arg0.execSQL(CREATE_FBANK_TABLE);
        arg0.execSQL(CREATE_FREASON_TABLE);
        arg0.execSQL(CREATE_FTOWN_TABLE);
        arg0.execSQL(CREATE_FTRGCAPUL_TABLE);
        arg0.execSQL(CREATE_FTYPE_TABLE);
        arg0.execSQL(CREATE_FSUBBRAND_TABLE);
        arg0.execSQL(CREATE_FGROUP_TABLE);
        arg0.execSQL(CREATE_FSKU_TABLE);
        arg0.execSQL(CREATE_FBRAND_TABLE);
        arg0.execSQL(CREATE_FORDHED_TABLE);
        arg0.execSQL(CREATE_FORDDET_TABLE);
        arg0.execSQL(CREATE_FCOMPANYBRANCH_TABLE);
        arg0.execSQL(CREATE_FSALREP_TABLE);
        arg0.execSQL(CREATE_FDDBNOTE_TABLE);
        arg0.execSQL(CREATE_FFREEDEB_TABLE);
        arg0.execSQL(CREATE_FFREEDET_TABLE);
        arg0.execSQL(CREATE_FFREEHED_TABLE);
        arg0.execSQL(CREATE_FFREESLAB_TABLE);
        arg0.execSQL(CREATE_FFREEITEM_TABLE);
        arg0.execSQL(CREATE_FITEM_TABLE);
        arg0.execSQL(CREATE_FITEMLOC_TABLE);
        arg0.execSQL(CREATE_FITEMPRI_TABLE);
        arg0.execSQL(CREATE_FAREA_TABLE);
        arg0.execSQL(CREATE_FLOCATIONS_TABLE);
        arg0.execSQL(CREATE_FDEALER_TABLE);
        arg0.execSQL(CREATE_FFREEMSLAB_TABLE);
        arg0.execSQL(CREATE_FMERCH_TABLE);
        arg0.execSQL(CREATE_FROUTEDET_TABLE);
        arg0.execSQL(CREATE_FDISCVHED_TABLE);
        arg0.execSQL(CREATE_FDISCVDET_TABLE);
        arg0.execSQL(CREATE_FDISCVDEB_TABLE);
        arg0.execSQL(CREATE_FDISCHED_TABLE);
        arg0.execSQL(CREATE_FDISCDET_TABLE);
        arg0.execSQL(CREATE_FDISCDEB_TABLE);
        arg0.execSQL(CREATE_FDISCSLAB_TABLE);
        arg0.execSQL(CREATE_FITENRHED_TABLE);
        arg0.execSQL(CREATE_FITENRDET_TABLE);
        arg0.execSQL(CREATE_FITEDEBDET_TABLE);
        arg0.execSQL(CREATE_FINVHEDL3_TABLE);
        arg0.execSQL(CREATE_FINVDETL3_TABLE);
        arg0.execSQL(CREATE_FINVHED_TABLE);
        arg0.execSQL(CREATE_FTRANDET_TABLE);
        arg0.execSQL(CREATE_FTRANHED_TABLE);
        arg0.execSQL(CREATE_FTRANISS_TABLE);
        arg0.execSQL(CREATE_TABLE_NONPRDHED);
        arg0.execSQL(CREATE_TABLE_NONPRDDET);
        arg0.execSQL(CREATE_FDAMHED_TABLE);
        arg0.execSQL(CREATE_FDAMDET_TABLE);
        arg0.execSQL(CREATE_FDAYEXPHED_TABLE);
        arg0.execSQL(CREATE_FDAYEXPDET_TABLE);
        arg0.execSQL(CREATE_FADJHED_TABLE);
        arg0.execSQL(CREATE_FADJDET_TABLE);
        arg0.execSQL(CREATE_FORDDISC_TABLE);
        arg0.execSQL(CREATE_FORDFREEISS_TABLE);
        arg0.execSQL(CREATE_FCOST_TABLE);
        arg0.execSQL(TESTITEM);
        arg0.execSQL(TESTITEMLOC);
        arg0.execSQL(TESTITEMPRI);
        arg0.execSQL(TESTINVHEDL3);
        arg0.execSQL(TESTINVDETL3);
        arg0.execSQL(TESTROUTEDET);
        arg0.execSQL(TESTFREEDEB);
//        arg0.execSQL(TESTDEBTOR);
        arg0.execSQL(TESTDDBNOTE);
        arg0.execSQL(TESTBANK);
        arg0.execSQL(IDXCOMSETT);
        arg0.execSQL(IDXFREEHED);
        arg0.execSQL(IDXFREEDET);
        arg0.execSQL(IDXFREEITEM);
        arg0.execSQL(IDXFREESLAB);
        arg0.execSQL(CREATE_FINVRHED_TABLE);
        arg0.execSQL(CREATE_FINVRDET_TABLE);
        arg0.execSQL(CREATE_FREPLOC_TABLE);
//        arg0.execSQL(CREATE_TABLE_TEMP_FDEBTOR);
        arg0.execSQL(CREATE_FPRECHED_TABLE);
        arg0.execSQL(CREATE_FPRECDET_TABLE);
        arg0.execSQL(CREATE_FPRECHEDS_TABLE);
        arg0.execSQL(CREATE_FPRECDETS_TABLE);
        arg0.execSQL(CREATE_FORDSTAT_TABLE);
        arg0.execSQL(CREATE_FGPSLOC_TABLE);
        arg0.execSQL(CREATE_FTOUR_TABLE);
        arg0.execSQL(CREATE_FPRODUCT_PRE_TABLE);
        arg0.execSQL(CREATE_FPRODUCT_TABLE);  //dhanushika create by...for temp table
        arg0.execSQL(INDEX_PRODUCTS);
        arg0.execSQL(INDEX_FMDEBTOR);
        arg0.execSQL(INDEX_FMISSUEHED);
        arg0.execSQL(INDEX_PRODUCTS_PRE);
        arg0.execSQL(CREATE_TABLE_FREPDALO);
        arg0.execSQL(CREATE_TABLE_FSUPPLIER);
        arg0.execSQL(INDEX_FMISSUEDET);
    }

    // --------------------------------------------------------------------------------------------------------------
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {


        try {
            arg0.execSQL(CREATE_NEW_CUSTOMER);
            arg0.execSQL(CREATE_FCOUNTRY_MGR);
            arg0.execSQL(CREATE_FMITEMS_DET);
            arg0.execSQL(CREATE_TABLE_FMITEMS);
            arg0.execSQL(CREATE_FSALREP_TABLE);
            arg0.execSQL(CREATE_FMAREA_TABLE);
            arg0.execSQL(CREATE_TABLE_FAREA_SUB);
            arg0.execSQL(CREATE_FDEBTOR_TABLE);
            arg0.execSQL(CREATE_TABLE_FMDEBDET);
            arg0.execSQL(CREATE_TABLE_FEXPENSE);
            arg0.execSQL(CREATE_TABLE_FEXP_GRP);
            arg0.execSQL(CREATE_FITENRHED_TABLE);
            arg0.execSQL(CREATE_FITENRDET_TABLE);
            arg0.execSQL(CREATE_FITEDEBDET_TABLE);
            arg0.execSQL(CREATE_FITENRHED_TABLE);
            arg0.execSQL(CREATE_TABLE_FMSALREP);
            arg0.execSQL(CREATE_FMISS_SUBDET);
            arg0.execSQL(CREATE_FMISS_HED);
            arg0.execSQL(CREATE_FMISS_DET);
            //above table were added by sathya
            arg0.execSQL(CREATE_FPRODUCT_TABLE);
            arg0.execSQL(INDEX_PRODUCTS);
            arg0.execSQL(INDEX_FMDEBTOR);
            arg0.execSQL(CREATE_TABLE_FREPDALO);
            arg0.execSQL(CREATE_TABLE_FSUPPLIER);


            arg0.execSQL(IDXCOMSETT);
            arg0.execSQL(IDXFREEHED);
            arg0.execSQL(IDXFREEDET);
            arg0.execSQL(IDXFREEITEM);
            arg0.execSQL(IDXFREESLAB);
            arg0.execSQL(CREATE_FORDSTAT_TABLE);
            arg0.execSQL(CREATE_FGPSLOC_TABLE);
            arg0.execSQL(CREATE_FTOUR_TABLE);
            arg0.execSQL(CREATE_FPRODUCT_PRE_TABLE);
            arg0.execSQL(INDEX_PRODUCTS_PRE);
            arg0.execSQL(INDEX_FMISSUEHED);
            arg0.execSQL(INDEX_FMISSUEDET);

        } catch (SQLiteException e) {
            Log.v("SQLite" , e.toString());
        }
//        try {
//            arg0.execSQL("ALTER TABLE " + TABLE_FMDEBTOR + " ADD COLUMN " + FDEBTOR_MROUTE_CODE + " TEXT");
//
//        } catch (SQLiteException e) {
//            Log.v("SQLite - " , e.toString());
//        }
//        try {
//            arg0.execSQL("ALTER TABLE " + TABLE_FMISS_HED + " ADD COLUMN " + FMISS_IS_ISSUE + " TEXT  DEFAULT 0");
//
//        } catch (SQLiteException e) {
//            Log.v("SQLite - ", e.toString());
//        }
        try {
            arg0.execSQL("ALTER TABLE " + TABLE_FMISS_HED + " ADD COLUMN " + FMISS_IS_SYNC + " TEXT  DEFAULT 0");

        } catch (SQLiteException e) {
            Log.v("SQLite - ", e.toString());
        }


	/*	 try{
             arg0.execSQL("ALTER TABLE "+TABLE_FPRODUCT+" ADD COLUMN "+FPRODUCT_NOUCASE+" TEXT");

			 }catch(SQLiteException e){
				 Log.v("SQLiteException - "+TABLE_FPRODUCT, e.toString());
			 }

		 try{
			 arg0.execSQL("ALTER TABLE "+TABLE_FPRODUCT+" ADD COLUMN "+FPRODUCT_PACK+" TEXT");

			 }catch(SQLiteException e){
				 Log.v("SQLiteException - "+TABLE_FPRODUCT, e.toString());
			 }*/
        this.onCreate(arg0);

    }

}