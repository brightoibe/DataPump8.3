/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import static com.inductivehealth.ndr.client.Client.getXmlDate;
import com.inductivehealth.ndr.schema.CodedSimpleType;
import com.inductivehealth.ndr.schema.RegimenType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.datatype.DatatypeConfigurationException;
import model.datapump.Demographics;
import model.datapump.Obs;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Weeks;
import util.NDRCommonUtills;

/**
 *
 * @author The Bright
 */
public class NDRPharmacyDictionary {

    private Map<String, String> mapRegimenToCodeDictionary = new HashMap<String, String>();
    private Map<String, String> localDrugCodeMapping = new HashMap<String, String>();
    private Map<Integer, String> ndrCodedValues = new HashMap<Integer, String>();
    private List<Integer> arvRegimenList = new ArrayList<Integer>();
    private List<Integer> oiRegimenList=new ArrayList<Integer>();
    public NDRPharmacyDictionary() {
        loadDictionaries();
    }

    private void loadDictionaries() {
        loadNDRCodedValues();
        loadLocalCodingDictionary();
        loadRegimenToCodeDictionary();
        loadARVConcepts();
        loadOIConcepts();
    }

    private void loadOIConcepts() {
        Integer[] oiDrugConcepts = {
            1192, 1193, 1194, 599, 1195,
            1196, 1198, 1199, 1200, 1201, 594,
            1202, 1203, 1594, 1204, 1206, 1207,
            1208, 1209, 1210, 1211, 1212, 37,
            1213, 11, 1214, 1215, 1216, 963,
            1217, 1218, 1239, 1240, 1241, 949,
            950, 10, 7778153, 7778154, 592, 7778155,
            68, 7778156, 58, 590, 7778158, 35, 34, 1197,
            1205, 66, 36, 608, 39, 38, 40
        };
        oiRegimenList.addAll(Arrays.asList(oiDrugConcepts));
    }
    public boolean isOI(Integer valueCoded){
        boolean ans=false;
        if(oiRegimenList.contains(valueCoded)){
            ans=true;
        }
        return ans;
    }
    private void loadARVConcepts() {
        Integer[] arvDrugConcepts = {
            960, 953, 959, 952, 962, 18,
            1187, 961, 957, 1190, 956,
            955, 958, 1221, 1222, 1224,
            1225, 1227, 1228, 1533, 23,
            1189, 1186, 1188, 1184, 17,
            954, 1185, 1235, 1236, 1237,
            7778159, 1219, 1232, 1223,
            1233, 1234, 1229, 1226, 1230,
            1231, 7778592, 7778594, 7778595,
            7778597, 7778598
        };
        arvRegimenList.addAll(Arrays.asList(arvDrugConcepts));
    }

    private void loadNDRCodedValues() {
        //ndrCodedValues.put(102, "1");
        ndrCodedValues.put(46, "1"); //Toxicity/side effects
        ndrCodedValues.put(47, "2");//Pregnancy
        ndrCodedValues.put(650, "3");//Risk of Pregnancy
        ndrCodedValues.put(1722, "4");//Due to new TB
        ndrCodedValues.put(7778306, "5");//New drug available
        ndrCodedValues.put(44, "6");//Drug out of stock
        ndrCodedValues.put(1427, "7");//Clinical treatment failure
        ndrCodedValues.put(1724, "8");//Immunological failure
        ndrCodedValues.put(1723, "9");//Virologic failure
        ndrCodedValues.put(1258, "ART");
        ndrCodedValues.put(1257, "NONART");
        //ndrCodedValues.put(	,CTX);
        ndrCodedValues.put(7778362, "PEP");
        ndrCodedValues.put(7778361, "PEPNONOCC");
        ndrCodedValues.put(1259, "PMTCT");
        //ndrCodedValues.put(	,TB);

    }

    public void loadLocalCodingDictionary() {
        localDrugCodeMapping = new HashMap<String, String>();
        localDrugCodeMapping.put("ACTION MEAL PRESCRIBED", "ACM");
        localDrugCodeMapping.put("ACYCLOVIR", "ACY");
        localDrugCodeMapping.put("ALBENDAZOLE", "ALB");
        localDrugCodeMapping.put("AMOXICILLIN", "AMX");
        localDrugCodeMapping.put("AMOXICILLIN AND CLAVULANIC ACID", "AMX");
        localDrugCodeMapping.put("AMITRIPTYLINE", "AMT");
        localDrugCodeMapping.put("ARTEMETER", "ATM");
        localDrugCodeMapping.put("ARTEMETHER-LUMEFANTRINE", "ALU");
        localDrugCodeMapping.put("AZITHROMYCIN", "AZI");
        localDrugCodeMapping.put("PENICILLIN G, BENZATHINE", "BEP");
        localDrugCodeMapping.put("PENICILLIN G BENZATHINE / PENICILLIN G PROCAINE", "BEP");//
        localDrugCodeMapping.put("BENZOIC ACID/SALICYLIC ACID CREAM", "BES");
        localDrugCodeMapping.put("CEFTRIAXONE IV", "CFT");
        localDrugCodeMapping.put("CEFTRIAXONE", "CFT");//
        localDrugCodeMapping.put("CHLORPHENIRAMINE MALEATE", "CHP");
        localDrugCodeMapping.put("CIPROFLOXACIN", "CPF");//
        localDrugCodeMapping.put("CLOTRIMAZOLE VAG. PRESSARY", "CLX");
        localDrugCodeMapping.put("CTX", "CTX960");
        localDrugCodeMapping.put("CTX prophylaxis", "CTX960");
        //localCodeMapping.put("CTX", "CTX240");
        //localCodeMapping.put("Cotrimoxazole (TMP/SMX) Tablet", "CTX960");
        localDrugCodeMapping.put("DAPSONE", "DDS");// DOXYCYCLINE
        localDrugCodeMapping.put("DOXYCYCLINE", "DXY");// 
        localDrugCodeMapping.put("ETHAMBUTOL", "E");// 
        localDrugCodeMapping.put("Erythromycin", "ERN");
        localDrugCodeMapping.put("FANSIDAR", "FAN");
        localDrugCodeMapping.put("FERROUS GLUCONATE", "FEG");
        localDrugCodeMapping.put("FERROUS SULPHATE", "FES");
        localDrugCodeMapping.put("FERROUS SULFATE", "FES");
        localDrugCodeMapping.put("FLUCONAZOLE", "FLUC");//
        localDrugCodeMapping.put("FLUOXETINE", "FLUX");
        localDrugCodeMapping.put("FOLIC ACID", "FOA");
        localDrugCodeMapping.put("HYDROCORTISONE ACETATE", "HYA");
        localDrugCodeMapping.put("HYDROCORTISONE", "HYA");
        localDrugCodeMapping.put("IBUPROFEN", "IBP");
        localDrugCodeMapping.put("INH", "H");
        localDrugCodeMapping.put("INH/B6", "H");//ISONIAZID PROPHYLAXIS
        localDrugCodeMapping.put("ISONIAZID PROPHYLAXIS", "H");//
        localDrugCodeMapping.put("LOPERAMIDE", "LOP");
        //localCodeMapping.put("LORATADINE", "LRS");
        localDrugCodeMapping.put("LORATADINE", "LOR");
        localDrugCodeMapping.put("METOCLOPRAMIDE", "MEP");
        localDrugCodeMapping.put("METRONIDAZOLE", "MET");
        localDrugCodeMapping.put("MULTIVITAMIN", "MUV");
        // localDrugCodeMapping.put("Multivitamin Drops", "MUV");
        //localCodeMapping.put("Multivitamin Syrup", "MUV");
        // localDrugCodeMapping.put("Multivite Tab", "MUV");
        localDrugCodeMapping.put("NYSTATIN SOLUTION", "NYS");
        localDrugCodeMapping.put("NYSTATIN", "NYS");//
        localDrugCodeMapping.put("ORAL REHYDRATION SALT", "ORS");
        localDrugCodeMapping.put("Oral Nystatin", "ORN");//
        localDrugCodeMapping.put("OFLOXACIN", "OFL");//
        localDrugCodeMapping.put("PROMETHAZINE / PSEUDOEPHEDRINE", "PRM");
        localDrugCodeMapping.put("PROMETHAZINE", "PRM");
        //localCodeMapping.put("Pyridoxine (Vitamin B6)", "PYR");
        localDrugCodeMapping.put("PYRIZINAMIDE", "Z");
        localDrugCodeMapping.put("RIFAMPICIN", "R");
        localDrugCodeMapping.put("SULFADOXINE/PYRIMETHAMINE", "SXP");
        localDrugCodeMapping.put("SULFADOXINE AND PYRIMETHAMINE", "SXP");
        localDrugCodeMapping.put("SPECTINOMYCIN", "S");//
        localDrugCodeMapping.put("STREPTOMYCIN INJECTION", "SI");
        localDrugCodeMapping.put("TRAMADOL", "TMD");
        localDrugCodeMapping.put("TRAMADOL HYDROCHLORIDE", "TMD");
        localDrugCodeMapping.put("TINIDAZOLE", "TND");
        localDrugCodeMapping.put("TETRACYCLINE", "TCL");
        localDrugCodeMapping.put("RIFAMPICIN AND ISONIAZID", "HR");
        localDrugCodeMapping.put("RIFAMPICIN ISONIAZID PYRAZINAMIDE AND ETHAMBUTOL ", "HRZE");

    }

    public boolean isEquivalent(String openmrsRegimen, String careCardRegimen) {
        boolean ans = false;
        String[] arr1 = null, arr2 = null;
        if (openmrsRegimen != null && careCardRegimen != null) {
            arr1 = openmrsRegimen.toUpperCase().split("-");
            arr2 = careCardRegimen.toUpperCase().split("-");
            Arrays.sort(arr1);
            Arrays.sort(arr2);
        }
        return Arrays.equals(arr1, arr2);
    }

    public String getCareCardRegimen(String openmrsRegimen) {
        String regimen = null;
        Set<String> set = mapRegimenToCodeDictionary.keySet();
        for (String ele : set) {
            if (isEquivalent(openmrsRegimen, ele)) {
                regimen = ele;
                return regimen;
            }
        }
        return regimen;
    }

    public String getRegimenCode(String regimen) {
        String code = null;
        Set<String> set = mapRegimenToCodeDictionary.keySet();
        for (String ele : set) {
            if (isEquivalent(regimen, ele)) {
                code = mapRegimenToCodeDictionary.get(ele);
                return code;
            }
        }
        return code;
    }

    public String getRegimenCode(String regimen, int regimenLineConcept) {
        String code = null;
        int[] firstLineArr = {7778108};
        int[] secondLineArr = {7778109};
        Arrays.sort(firstLineArr);
        Arrays.sort(secondLineArr);

        Set<String> set = mapRegimenToCodeDictionary.keySet();
        for (String ele : set) {
            if (isEquivalent(regimen, ele)) {
                code = mapRegimenToCodeDictionary.get(ele);
                return code;
            }
        }
        if (StringUtils.isEmpty(code) && (Arrays.binarySearch(firstLineArr, regimenLineConcept) >= 0)) {
            code = "1k";
        }
        if (StringUtils.isEmpty(code) && (Arrays.binarySearch(secondLineArr, regimenLineConcept) >= 0)) {
            code = "2g";
        }
        return code;
    }

    public void loadRegimenToCodeDictionary() {
        //mapRegimenToCodeDictionary = new HashMap<String, String>();
        mapRegimenToCodeDictionary.put("3TC-D4T-EFV", "1k");
        mapRegimenToCodeDictionary.put("TDF-3TC-DTG", "1m");
        mapRegimenToCodeDictionary.put("TDF-FTC-DTG", "1n");
        mapRegimenToCodeDictionary.put("ABC-3TC-DTG", "1o");
        mapRegimenToCodeDictionary.put("ABC-FTC-DTG", "1p");
        mapRegimenToCodeDictionary.put("Other first line", "1k");
        mapRegimenToCodeDictionary.put("AZT-3TC-EFV", "1a");
        mapRegimenToCodeDictionary.put("AZT-3TC-NVP", "1b");
        mapRegimenToCodeDictionary.put("TDF-FTC-EFV", "1c");
        mapRegimenToCodeDictionary.put("TDF-FTC-NVP", "1d");
        mapRegimenToCodeDictionary.put("TDF-3TC-EFV", "1e");
        mapRegimenToCodeDictionary.put("TDF-3TC-NVP", "1f");
        mapRegimenToCodeDictionary.put("AZT-3TC-ABC", "1g");
        mapRegimenToCodeDictionary.put("AZT-3TC-TDF", "1h");
        mapRegimenToCodeDictionary.put("TDF-FTC-LPV/r", "2a");
        mapRegimenToCodeDictionary.put("TDF-3TC-LPV/r", "2b");
        mapRegimenToCodeDictionary.put("TDF-FTC-ATV/r", "2c");
        mapRegimenToCodeDictionary.put("TDF-3TC-ATV/r", "2d");
        mapRegimenToCodeDictionary.put("AZT-3TC-LPV/r", "2e");
        mapRegimenToCodeDictionary.put("AZT-3TC-ATV/r", "2f");
        mapRegimenToCodeDictionary.put("AZT-3TC-EFV", "4a");
        mapRegimenToCodeDictionary.put("AZT-3TC-NVP", "4b");
        mapRegimenToCodeDictionary.put("ABC-3TC-EFV", "4c");
        mapRegimenToCodeDictionary.put("ABC-3TC-NVP", "4d");
        mapRegimenToCodeDictionary.put("AZT-3TC-ABC", "4e");
        mapRegimenToCodeDictionary.put("d4T-3TC-NVP", "4f");
        mapRegimenToCodeDictionary.put("ABC-3TC-LPV/r", "5a");
        mapRegimenToCodeDictionary.put("AZT-3TC-LPV/r", "5b");
        mapRegimenToCodeDictionary.put("d4T-3TC-LPV/r", "5c");
        mapRegimenToCodeDictionary.put("ABC-3TC-ddi", "5e");
        mapRegimenToCodeDictionary.put("AZT", "9a");
        mapRegimenToCodeDictionary.put("3TC", "9b");
        mapRegimenToCodeDictionary.put("NVP", "9c");
        mapRegimenToCodeDictionary.put("AZT-3TC", "9d");
        mapRegimenToCodeDictionary.put("AZT-NVP", "9e");
        mapRegimenToCodeDictionary.put("FTC-TDF", "9f");
        mapRegimenToCodeDictionary.put("3TC-d4T", "9g");
        mapRegimenToCodeDictionary.put("3TC-d4T", "9h");
        mapRegimenToCodeDictionary.put("AZT-FTC-LPV/r-TDF", "2g");
        mapRegimenToCodeDictionary.put("3TC-AZT-LPV/r-TDF", "2g");
        mapRegimenToCodeDictionary.put("IDV/r-AZT-3TC", "2g");
        mapRegimenToCodeDictionary.put("IDV/r-AZT-FTC", "2g");
        mapRegimenToCodeDictionary.put("IDV/r-AZT-3TC", "2g");
        mapRegimenToCodeDictionary.put("IDV/r-3TC-D4T", "2g");
        mapRegimenToCodeDictionary.put("AZT-FTC-ATV/r", "2g");
        mapRegimenToCodeDictionary.put("AZT-FTC-LPV/r", "2g");
        mapRegimenToCodeDictionary.put("ABC-AZT-3TC-LPV/r", "2g");
        mapRegimenToCodeDictionary.put("3TC-ABC-AZT-ATV/r", "2g");
        mapRegimenToCodeDictionary.put("ATV/r-AZT-3TC-TDF", "2g");
        mapRegimenToCodeDictionary.put("ATV/r-AZT-FTC-TDF", "2g");
        mapRegimenToCodeDictionary.put("3TC-ABC-ATV/r", "2g");
        mapRegimenToCodeDictionary.put("3TC-D4T-SQV/r", "2g");
        mapRegimenToCodeDictionary.put("ABC-SQV/r-TDF", "2g");
        mapRegimenToCodeDictionary.put("ABC-DDI-LPV/r", "2g");
        mapRegimenToCodeDictionary.put("TDF-FTC-SQV/r", "2g");
        mapRegimenToCodeDictionary.put("3TC-AZT-SQV/r", "2g");
        mapRegimenToCodeDictionary.put("3TC-IDV/r-TDF", "2g");
        mapRegimenToCodeDictionary.put("FTC-IDV/r-TDF", "2g");
        mapRegimenToCodeDictionary.put("3TC-AZT-SQV/r-TDF", "2g");
        mapRegimenToCodeDictionary.put("3TC-SQV/r-TDF", "2g");
        mapRegimenToCodeDictionary.put("ABC-AZT-LPV/r", "2g");
        mapRegimenToCodeDictionary.put("DDI-IDV/r-TDF", "2g");
        mapRegimenToCodeDictionary.put("DDI-SQV/r-TDF", "2g");
        mapRegimenToCodeDictionary.put("Other second line", "2g");

    }

    public RegimenType createARVRegimenTypeFromObsList(ArrayList<Obs> obsListFromPharmacy, Date visitDate, String pepfarID) {
        RegimenType regimenType = null;
        String prescribedRegimenCode = "";
        Obs obsPin = null;
        int valueCoded = 0;
        CodedSimpleType cst = null;
        String visitID = NDRCommonUtills.formatDateYYYYMMDD(visitDate) + "-" + pepfarID;
        //Get the prescribed regimen line from the list 165708 (Current Regimen Line)
        obsPin = NDRCommonUtills.getObsForConceptForForm(165708, obsListFromPharmacy, visitDate);
        if (obsPin != null) {
            regimenType = new RegimenType();
            valueCoded = obsPin.getValueCoded();
            if (valueCoded == 164506 || valueCoded == 164507) {
                prescribedRegimenCode = "10";
                cst = new CodedSimpleType();
                cst.setCode(prescribedRegimenCode);
                cst.setCodeDescTxt(obsPin.getVariableValue());
            } else if (valueCoded == 164513 || valueCoded == 164514) {
                prescribedRegimenCode = "20";
                cst = new CodedSimpleType();
                cst.setCode(prescribedRegimenCode);
                cst.setCodeDescTxt(obsPin.getVariableValue());
            }

        }

        return regimenType;
    }

    public List<RegimenType> constructRegimenTypeList(Demographics pts, List<Obs> pharmacyObsList) throws DatatypeConfigurationException {
        List<RegimenType> regimenTypeList = new ArrayList<RegimenType>();
        Set<Date> visitSet = NDRCommonUtills.getAllVisitsFromObsList(pharmacyObsList);
        RegimenType regimenType = null;
        List<Obs> obsList = null;
        for (Date vstDate : visitSet) {
            obsList = NDRCommonUtills.getAllObsForDate(vstDate, pharmacyObsList);
            regimenTypeList.add(extractARVRegimens(obsList, pts, vstDate));
            //regimenTypeList.addAll(extractOIRegimens(obsList, vstDate, pts));
        }
        return regimenTypeList;
    }

    public boolean isARV(Integer valueCoded) {
        boolean ans = false;
        if (arvRegimenList.contains(valueCoded)) {
            ans = true;
        }
        return ans;
    }

    public RegimenType extractARVRegimens(List<model.datapump.Obs> obsList, Demographics pts, Date visitDate) throws DatatypeConfigurationException {
        //List<RegimenType> regimenTypeList = new ArrayList<RegimenType>();
        String visitID = NDRCommonUtills.formatDateYYYYMMDD(visitDate) + "-" + pts.getPepfarID();
        String prescribedRegimenLineCode = "";
        Obs obsPin = null;
        int valueCoded = 0, obsID = 0, duration = 0, durationCodedUnit = 0;
        CodedSimpleType prescribedRegimenCst = null;
        RegimenType regimenType = null;
        obsPin = NDRCommonUtills.getObsForConceptFromList(7778111, obsList);// RegimenLine
        Calendar cal = Calendar.getInstance();
        String month = "", year = "", day = "", durationUnit = "", regimenCode = "";
        durationUnit = "DAY(S)";
        Date stopDate = null;
        if (obsPin != null) {
            regimenType = new RegimenType();
            valueCoded = obsPin.getValueCoded();
            Obs regimenObs = null;
            if (valueCoded == 7778108) {//First Line
                prescribedRegimenLineCode = "10";
                regimenObs = NDRCommonUtills.getObsForConceptFromList(valueCoded, obsList);

                if (regimenObs != null && getRegimenCode(regimenObs.getVariableValue(), valueCoded) != null) {
                    prescribedRegimenCst = new CodedSimpleType();
                    prescribedRegimenCst.setCode(getRegimenCode(regimenObs.getVariableValue(), valueCoded));
                    prescribedRegimenCst.setCodeDescTxt(regimenObs.getVariableValue());
                }
            } else if (valueCoded == 7778109) {//Second Line
                prescribedRegimenLineCode = "20";
                regimenObs = NDRCommonUtills.getObsForConceptFromList(valueCoded, obsList);
                //regimenCode = getRegimenCode(regimenObs.getVariableValue(), valueCoded);
                if (regimenObs != null && getRegimenCode(regimenObs.getVariableValue(), valueCoded) != null) {
                    prescribedRegimenCst = new CodedSimpleType();
                    regimenObs = NDRCommonUtills.getObsForConceptFromList(valueCoded, obsList);
                    prescribedRegimenCst.setCode(getRegimenCode(regimenObs.getVariableValue(), valueCoded));
                    prescribedRegimenCst.setCodeDescTxt(regimenObs.getVariableValue());
                }
            }
            regimenType.setVisitID(visitID);
            regimenType.setVisitDate(getXmlDate(visitDate));
            regimenType.setPrescribedRegimenLineCode(prescribedRegimenLineCode);
            regimenType.setPrescribedRegimen(prescribedRegimenCst);
            regimenType.setDateRegimenStarted(getXmlDate(visitDate));
            cal.setTime(visitDate);
            month = StringUtils.leftPad(String.valueOf(cal.get(Calendar.MONTH) + 1), 2, "0");
            year = String.valueOf(cal.get(Calendar.YEAR));
            day = StringUtils.leftPad(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)), 2, "0");
            regimenType.setDateRegimenStarted(getXmlDate(visitDate));
            regimenType.setDateRegimenStartedDD(day);
            regimenType.setDateRegimenStartedMM(month);
            regimenType.setDateRegimenStartedYYYY(year);
            regimenType.setPrescribedRegimenDispensedDate(getXmlDate(visitDate));
            obsPin = NDRCommonUtills.getObsForConceptFromList(1725, obsList);// Retrieve Reason for Substitution/Switch from Care Card
            if (obsPin != null) {
                valueCoded = obsPin.getValueCoded();
                regimenType.setReasonForRegimenSwitchSubs(ndrCodedValues.get(valueCoded));
            }
            obsPin = NDRCommonUtills.getObsForConceptFromList(7778363, obsList);// Treatment Type
            if (obsPin != null) {
                valueCoded = obsPin.getValueCoded();
                regimenType.setPrescribedRegimenTypeCode(ndrCodedValues.get(valueCoded));
            }
            obsPin = NDRCommonUtills.getObsForConceptFromList(7778364, obsList);//Drug Name concept

            if (obsPin != null) {
                valueCoded = obsPin.getValueCoded();
                if (isARV(valueCoded)) {
                    obsID = obsPin.getObsGroupID();
                    obsPin = NDRCommonUtills.getObsForConceptFromListWithGroupID(159368, obsList, obsID);//Medication Duration Concept 
                    //if (obsPin != null) {
                    //System.out.println(obsPin.getValueNumeric() + " = Duration");
                    //}
                    if (obsPin != null) {
                        //System.out.println("Obs ID group ID is: " + obsPin.getObsID());
                        duration = (int) obsPin.getValueNumeric();
                        //durationUnit = "DAY(S)";
                        //stopDate = NDRCommonUtills.calculateStopDate(visitDate, duration, durationUnit);
                    }
                    obsPin = NDRCommonUtills.getObsForConceptFromListWithGroupID(7778371, obsList, obsID);// Medication Duration unit
                    if (obsPin != null) {
                        valueCoded = obsPin.getValueCoded();
                        durationCodedUnit = valueCoded;
                    }
                    stopDate = NDRCommonUtills.calculateStopDate(visitDate, duration, durationCodedUnit);
                }
            }
            if (stopDate == null) {
                obsPin = NDRCommonUtills.getObsForConceptFromList(7777822, obsList);//Return Visit Date
                if (obsPin != null) {
                    System.out.println("Next Appointment Date value is " + obsPin.getValueDate());
                    stopDate = obsPin.getValueDate();
                    //stopDate = NDRCommonUtills.calculateStopDate(visitDate, duration, durationUnit);
                }

            }
            if (stopDate == null) {
                duration = 30;
                durationUnit = "DAY(S)";
                stopDate = NDRCommonUtills.calculateStopDate(visitDate, duration, durationUnit);
            }
            regimenType.setDateRegimenEnded(getXmlDate(stopDate));
            cal = Calendar.getInstance();
            cal.setTime(stopDate);
            month = StringUtils.leftPad(String.valueOf(cal.get(Calendar.MONTH) + 1), 2, "0");
            year = String.valueOf(cal.get(Calendar.YEAR));
            day = StringUtils.leftPad(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)), 2, "0");
            regimenType.setDateRegimenEndedDD(day);
            regimenType.setDateRegimenEndedMM(month);
            regimenType.setDateRegimenEndedYYYY(year);
            DateTime startDateTime = new DateTime(visitDate);
            DateTime endDateTime = new DateTime(stopDate);
            Days days = Days.daysBetween(startDateTime, endDateTime);
            int daysVal = days.getDays();
            String regimenDuration = String.valueOf(daysVal);
            regimenType.setPrescribedRegimenDuration(regimenDuration);
        }
        return regimenType;
    }

    public RegimenType converterToOIRegimenType(List<Obs> obsListForOI, Date visitDate, Demographics pts, List<Obs> obsListForVisit) throws DatatypeConfigurationException {
        RegimenType regimenType = null;
        Obs obsPin = null;
        int obsID = 0, duration = 0, conceptID = 0, valueCoded = 0;

        Calendar cal = Calendar.getInstance();
        String pepfarID = "", drugName = "", coding = "", description = "", regimenTypeCode = "NONART";
        Date stopDate = null;
        obsPin = NDRCommonUtills.getObsForConceptFromList(165726, obsListForVisit);//OI drugs Concept ID
        if (obsPin != null) {
            obsID = obsPin.getObsID();
            obsPin = NDRCommonUtills.getObsForConceptFromListWithGroupID(165727, obsListForVisit, obsID);// OI drugs
            if (obsPin != null && obsPin.getValueCoded() == 165257) {
                description = obsPin.getVariableValue();
                obsPin = NDRCommonUtills.getObsForConceptFromListWithGroupID(165725, obsListForVisit, obsID);// ARV Drug Strength
                if (obsPin != null) {
                    valueCoded = obsPin.getValueCoded();
                    switch (valueCoded) {
                        case 165062: //960mg 
                            coding = "CTX960";
                            break;
                        case 165060: //480mg
                            coding = "CTX480";
                            break;
                        case 166095: //240mg
                            coding = "CTX240";
                            break;
                        case 165068: //120mg 
                            coding = "CTX120";
                            break;
                        default: // Case anything else
                            coding = "CTX960";
                            break;
                    }

                }
                stopDate = getRegimenStopDate(obsListForVisit, visitDate, obsID);
                regimenType = createRegimenType(pts.getPepfarID(), visitDate, coding, description, stopDate, duration, regimenTypeCode);
                return regimenType;
            }
            obsPin = NDRCommonUtills.getObsForConceptFromList(165726, obsListForVisit);//OI drugs Concept ID
            if (obsPin != null) {
                obsID = obsPin.getObsID();
                obsPin = NDRCommonUtills.getObsForConceptFromListWithGroupID(165727, obsListForVisit, obsID);// OI drugs
                if (obsPin != null && isValidOIDrug(obsPin.getVariableValue())) {
                    drugName = obsPin.getVariableValue();
                    coding = getLocalCodingOtherDrugs(drugName);
                    description = drugName;
                    stopDate = getRegimenStopDate(obsListForOI, visitDate, obsID);
                    regimenType = createRegimenType(pepfarID, visitDate, coding, description, stopDate, duration, regimenTypeCode);
                    return regimenType;
                }
            }

        }
        return regimenType;
    }

    public Date getRegimenStopDate(List<Obs> obsListForVisit, Date visitDate, int obsID) {
        Obs obsPin = null;
        int duration = 0;
        String durationUnit = "DAY(S)";
        Date stopDate = null;
        //if (stopDate == null) {
        obsPin = NDRCommonUtills.getObsForConceptFromListWithGroupID(159368, obsListForVisit, obsID);//Medication duration
        if (obsPin != null) {
            duration = (int) obsPin.getValueNumeric();
            stopDate = NDRCommonUtills.calculateStopDate(visitDate, duration, durationUnit);
        }
        //}
        if (stopDate == null) {
            obsPin = NDRCommonUtills.getObsForConceptFromList(5096, obsListForVisit);// Next Appointment Date
            if (obsPin != null) {
                stopDate = obsPin.getValueDate();
            }
        }
        if (stopDate == null) {
            duration = 30;
            stopDate = NDRCommonUtills.calculateStopDate(visitDate, duration, durationUnit);
        }
        return stopDate;
    }

    public RegimenType createRegimenType(String pepfarID, Date visitDate, String regimenCoding, String description, Date stopDate, int durationDays, String regimenTypeCode) throws DatatypeConfigurationException {
        Calendar cal = Calendar.getInstance();
        String month = "", year = "", day = "";
        RegimenType regimenType = new RegimenType();
        String visitID = NDRCommonUtills.formatDateYYYYMMDD(visitDate) + "-" + pepfarID;
        regimenType.setVisitID(visitID);
        regimenType.setVisitDate(getXmlDate(visitDate));
        regimenType.setPrescribedRegimenDispensedDate(getXmlDate(visitDate));
        regimenType.setPrescribedRegimenTypeCode(regimenTypeCode);
        CodedSimpleType cst = new CodedSimpleType();
        cst.setCode(regimenCoding);
        cst.setCodeDescTxt(description);
        regimenType.setPrescribedRegimen(cst);
        cal.setTime(visitDate);
        month = StringUtils.leftPad(String.valueOf(cal.get(Calendar.MONTH) + 1), 2, "0");
        year = String.valueOf(cal.get(Calendar.YEAR));
        day = StringUtils.leftPad(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)), 2, "0");
        regimenType.setDateRegimenStarted(getXmlDate(visitDate));
        regimenType.setDateRegimenStartedDD(day);
        regimenType.setDateRegimenStartedMM(month);
        regimenType.setDateRegimenStartedYYYY(year);
        regimenType.setDateRegimenEnded(getXmlDate(stopDate));
        cal = Calendar.getInstance();
        cal.setTime(stopDate);
        month = StringUtils.leftPad(String.valueOf(cal.get(Calendar.MONTH) + 1), 2, "0");
        year = String.valueOf(cal.get(Calendar.YEAR));
        day = StringUtils.leftPad(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)), 2, "0");
        regimenType.setDateRegimenEndedDD(day);
        regimenType.setDateRegimenEndedMM(month);
        regimenType.setDateRegimenEndedYYYY(year);
        DateTime startDateTime = new DateTime(visitDate);
        DateTime endDateTime = new DateTime(stopDate);
        Days days = Days.daysBetween(startDateTime, endDateTime);
        int daysVal = days.getDays();
        String regimenDuration = String.valueOf(daysVal);
        regimenType.setPrescribedRegimenDuration(regimenDuration);
        return regimenType;

    }

    public List<RegimenType> extractOIRegimens(List<model.datapump.Obs> obsList, Date visitDate, Demographics pts) throws DatatypeConfigurationException {
        List<RegimenType> regimenTypeList = new ArrayList<RegimenType>();
        RegimenType regimenType = null;
        List<Obs> oiDrugsObsList = null;
        List<Obs> obsListForOIProphylaxis = NDRCommonUtills.getAllObsWithConceptForList(165726, visitDate, obsList);// Retrieve all Group concept obs (Drugs for OI Prophylaxis)
        Set<Integer> obsIDSet = new HashSet<Integer>();
        for (Obs ele : obsListForOIProphylaxis) {
            obsIDSet.add(ele.getObsID());
        }
        for (Integer eleInt : obsIDSet) {
            oiDrugsObsList = NDRCommonUtills.getAllObsForConceptFromListWithGroupID(obsList, eleInt);
            regimenType = converterToOIRegimenType(oiDrugsObsList, visitDate, pts, obsList);
            regimenTypeList.add(regimenType);
        }

        return regimenTypeList;
    }

    public String getLocalCodingOtherDrugs(String drugName) {
        String coding = "";
        if (drugName != null && !drugName.isEmpty()) {
            Set<String> keySet = localDrugCodeMapping.keySet();
            for (String key : keySet) {
                if (drugName.contains(key) && !localDrugCodeMapping.get(key).contains("CTX")) {
                    coding += localDrugCodeMapping.get(key) + "-";
                }

            }
            if (coding.endsWith("-")) {
                coding = coding.substring(0, coding.length() - 1);
            }
            String[] codingArray = coding.split("-");
            Arrays.sort(codingArray);
            coding = StringUtils.join(codingArray, "-");
        }
        return coding;
    }

    public boolean isValidOIDrug(String drugName) {
        boolean ans = false;
        if (localDrugCodeMapping.containsKey(drugName)) {
            ans = true;
        }
        return ans;
    }

}
