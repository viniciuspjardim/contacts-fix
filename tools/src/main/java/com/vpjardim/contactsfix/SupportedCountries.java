/*
 * Copyright 2017 Vinícius Petrocione Jardim
 */

package com.vpjardim.contactsfix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Vinícius Jardim
 * 2017/11/03
 */
public class SupportedCountries {

    // Seems to be impossible import the apk module using gradle. Duplicating code...

    public static final class ARow {
        public String areaCode;
        public String countryCode;
        public String isoCode2;
        public String isoCode3;
        public String countryName;
        public String stateCode;
        public String stateName;

        public ARow(String areaCode, String countryCode, String isoCode2, String isoCode3,
                    String countryName, String stateCode, String stateName) {

            this.areaCode = areaCode;
            this.countryCode = countryCode;
            this.isoCode2 = isoCode2;
            this.isoCode3 = isoCode3;
            this.countryName = countryName;
            this.stateCode = stateCode;
            this.stateName = stateName;
        }

        @Override
        public String toString() {
            return areaCode + " " + countryName;
        }
    }

    public static final HashMap<String, ARow> areaCodes = new HashMap<>();

    static {
        // North American Numbering Plan (NANPA)
        areaCodes.put("1/684", new ARow("684", "1", "AS", "ASM", "American Samoa", "", ""));
        areaCodes.put("1/264", new ARow("264", "1", "AI", "AIA", "Anguilla", "", ""));
        areaCodes.put("1/268", new ARow("268", "1", "AG", "ATG", "Antigua and Barbuda", "", ""));
        areaCodes.put("1/242", new ARow("242", "1", "BS", "BHS", "Bahamas", "", ""));
        areaCodes.put("1/246", new ARow("246", "1", "BB", "BRB", "Barbados", "", ""));
        areaCodes.put("1/441", new ARow("441", "1", "BM", "BMU", "Bermuda", "", ""));
        areaCodes.put("1/284", new ARow("284", "1", "VG", "VGB", "British Virgin Islands", "", ""));
        areaCodes.put("1/403", new ARow("403", "1", "CA", "CAN", "Canada", "AB", "Alberta"));
        areaCodes.put("1/587", new ARow("587", "1", "CA", "CAN", "Canada", "AB", "Alberta"));
        areaCodes.put("1/780", new ARow("780", "1", "CA", "CAN", "Canada", "AB", "Alberta"));
        areaCodes.put("1/825", new ARow("825", "1", "CA", "CAN", "Canada", "AB", "Alberta"));
        areaCodes.put("1/236", new ARow("236", "1", "CA", "CAN", "Canada", "BC", "British Columbia"));
        areaCodes.put("1/250", new ARow("250", "1", "CA", "CAN", "Canada", "BC", "British Columbia"));
        areaCodes.put("1/604", new ARow("604", "1", "CA", "CAN", "Canada", "BC", "British Columbia"));
        areaCodes.put("1/778", new ARow("778", "1", "CA", "CAN", "Canada", "BC", "British Columbia"));
        areaCodes.put("1/204", new ARow("204", "1", "CA", "CAN", "Canada", "MB", "Manitoba"));
        areaCodes.put("1/431", new ARow("431", "1", "CA", "CAN", "Canada", "MB", "Manitoba"));
        areaCodes.put("1/506", new ARow("506", "1", "CA", "CAN", "Canada", "NB", "New Brunswick"));
        areaCodes.put("1/709", new ARow("709", "1", "CA", "CAN", "Canada", "NL", "Newfoundland and Labrador"));
        areaCodes.put("1/867", new ARow("867", "1", "CA", "CAN", "Canada", "NT, NU, YT", "Northwest Territories, Nunavut, Yukon"));
        areaCodes.put("1/902", new ARow("902", "1", "CA", "CAN", "Canada", "NS, PE", "Nova Scotia, Prince Edward Island"));
        areaCodes.put("1/782", new ARow("782", "1", "CA", "CAN", "Canada", "NS, PE", "Nova Scotia, Prince Edward Island"));
        areaCodes.put("1/226", new ARow("226", "1", "CA", "CAN", "Canada", "ON", "Ontario"));
        areaCodes.put("1/249", new ARow("249", "1", "CA", "CAN", "Canada", "ON", "Ontario"));
        areaCodes.put("1/289", new ARow("289", "1", "CA", "CAN", "Canada", "ON", "Ontario"));
        areaCodes.put("1/343", new ARow("343", "1", "CA", "CAN", "Canada", "ON", "Ontario"));
        areaCodes.put("1/365", new ARow("365", "1", "CA", "CAN", "Canada", "ON", "Ontario"));
        areaCodes.put("1/416", new ARow("416", "1", "CA", "CAN", "Canada", "ON", "Ontario"));
        areaCodes.put("1/437", new ARow("437", "1", "CA", "CAN", "Canada", "ON", "Ontario"));
        areaCodes.put("1/519", new ARow("519", "1", "CA", "CAN", "Canada", "ON", "Ontario"));
        areaCodes.put("1/613", new ARow("613", "1", "CA", "CAN", "Canada", "ON", "Ontario"));
        areaCodes.put("1/647", new ARow("647", "1", "CA", "CAN", "Canada", "ON", "Ontario"));
        areaCodes.put("1/705", new ARow("705", "1", "CA", "CAN", "Canada", "ON", "Ontario"));
        areaCodes.put("1/807", new ARow("807", "1", "CA", "CAN", "Canada", "ON", "Ontario"));
        areaCodes.put("1/905", new ARow("905", "1", "CA", "CAN", "Canada", "ON", "Ontario"));
        areaCodes.put("1/418", new ARow("418", "1", "CA", "CAN", "Canada", "QC", "Quebec"));
        areaCodes.put("1/438", new ARow("438", "1", "CA", "CAN", "Canada", "QC", "Quebec"));
        areaCodes.put("1/450", new ARow("450", "1", "CA", "CAN", "Canada", "QC", "Quebec"));
        areaCodes.put("1/514", new ARow("514", "1", "CA", "CAN", "Canada", "QC", "Quebec"));
        areaCodes.put("1/579", new ARow("579", "1", "CA", "CAN", "Canada", "QC", "Quebec"));
        areaCodes.put("1/581", new ARow("581", "1", "CA", "CAN", "Canada", "QC", "Quebec"));
        areaCodes.put("1/819", new ARow("819", "1", "CA", "CAN", "Canada", "QC", "Quebec"));
        areaCodes.put("1/873", new ARow("873", "1", "CA", "CAN", "Canada", "QC", "Quebec"));
        areaCodes.put("1/306", new ARow("306", "1", "CA", "CAN", "Canada", "SK", "Saskatchewan"));
        areaCodes.put("1/639", new ARow("639", "1", "CA", "CAN", "Canada", "SK", "Saskatchewan"));
        areaCodes.put("1/345", new ARow("345", "1", "KY", "CYM", "Cayman Islands", "", ""));
        areaCodes.put("1/767", new ARow("767", "1", "DM", "DMA", "Dominica", "", ""));
        areaCodes.put("1/809", new ARow("809", "1", "DO", "DOM", "Dominican Republic", "", ""));
        areaCodes.put("1/829", new ARow("829", "1", "DO", "DOM", "Dominican Republic", "", ""));
        areaCodes.put("1/849", new ARow("849", "1", "DO", "DOM", "Dominican Republic", "", ""));
        areaCodes.put("1/473", new ARow("473", "1", "GD", "GRD", "Grenada", "", ""));
        areaCodes.put("1/671", new ARow("671", "1", "GU", "GUM", "Guam", "", ""));
        areaCodes.put("1/876", new ARow("876", "1", "JM", "JAM", "Jamaica", "", ""));
        areaCodes.put("1/664", new ARow("664", "1", "MS", "MSR", "Montserrat", "", ""));
        areaCodes.put("1/670", new ARow("670", "1", "MP", "MNP", "Northern Mariana Islands", "", ""));
        areaCodes.put("1/787", new ARow("787", "1", "PR", "PRI", "Puerto Rico", "", ""));
        areaCodes.put("1/939", new ARow("939", "1", "PR", "PRI", "Puerto Rico", "", ""));
        areaCodes.put("1/869", new ARow("869", "1", "KN", "KNA", "Saint Kitts and Nevis", "", ""));
        areaCodes.put("1/758", new ARow("758", "1", "LC", "LCA", "Saint Lucia", "", ""));
        areaCodes.put("1/784", new ARow("784", "1", "VC", "VCT", "Saint Vincent and the Grenadines", "", ""));
        areaCodes.put("1/721", new ARow("721", "1", "SX", "SXM", "Sint Maarten", "", ""));
        areaCodes.put("1/868", new ARow("868", "1", "TT", "TTO", "Trinidad and Tobago", "", ""));
        areaCodes.put("1/649", new ARow("649", "1", "TC", "TCA", "Turks and Caicos Islands", "", ""));
        areaCodes.put("1/205", new ARow("205", "1", "US", "USA", "United States", "AL", "Alabama"));
        areaCodes.put("1/251", new ARow("251", "1", "US", "USA", "United States", "AL", "Alabama"));
        areaCodes.put("1/256", new ARow("256", "1", "US", "USA", "United States", "AL", "Alabama"));
        areaCodes.put("1/334", new ARow("334", "1", "US", "USA", "United States", "AL", "Alabama"));
        areaCodes.put("1/907", new ARow("907", "1", "US", "USA", "United States", "AK", "Alaska"));
        areaCodes.put("1/480", new ARow("480", "1", "US", "USA", "United States", "AZ", "Arizona"));
        areaCodes.put("1/520", new ARow("520", "1", "US", "USA", "United States", "AZ", "Arizona"));
        areaCodes.put("1/602", new ARow("602", "1", "US", "USA", "United States", "AZ", "Arizona"));
        areaCodes.put("1/623", new ARow("623", "1", "US", "USA", "United States", "AZ", "Arizona"));
        areaCodes.put("1/928", new ARow("928", "1", "US", "USA", "United States", "AZ", "Arizona"));
        areaCodes.put("1/479", new ARow("479", "1", "US", "USA", "United States", "AR", "Arkansas"));
        areaCodes.put("1/501", new ARow("501", "1", "US", "USA", "United States", "AR", "Arkansas"));
        areaCodes.put("1/870", new ARow("870", "1", "US", "USA", "United States", "AR", "Arkansas"));
        areaCodes.put("1/209", new ARow("209", "1", "US", "USA", "United States", "CA", "California"));
        areaCodes.put("1/213", new ARow("213", "1", "US", "USA", "United States", "CA", "California"));
        areaCodes.put("1/310", new ARow("310", "1", "US", "USA", "United States", "CA", "California"));
        areaCodes.put("1/323", new ARow("323", "1", "US", "USA", "United States", "CA", "California"));
        areaCodes.put("1/408", new ARow("408", "1", "US", "USA", "United States", "CA", "California"));
        areaCodes.put("1/415", new ARow("415", "1", "US", "USA", "United States", "CA", "California"));
        areaCodes.put("1/510", new ARow("510", "1", "US", "USA", "United States", "CA", "California"));
        areaCodes.put("1/530", new ARow("530", "1", "US", "USA", "United States", "CA", "California"));
        areaCodes.put("1/559", new ARow("559", "1", "US", "USA", "United States", "CA", "California"));
        areaCodes.put("1/562", new ARow("562", "1", "US", "USA", "United States", "CA", "California"));
        areaCodes.put("1/619", new ARow("619", "1", "US", "USA", "United States", "CA", "California"));
        areaCodes.put("1/626", new ARow("626", "1", "US", "USA", "United States", "CA", "California"));
        areaCodes.put("1/650", new ARow("650", "1", "US", "USA", "United States", "CA", "California"));
        areaCodes.put("1/661", new ARow("661", "1", "US", "USA", "United States", "CA", "California"));
        areaCodes.put("1/707", new ARow("707", "1", "US", "USA", "United States", "CA", "California"));
        areaCodes.put("1/714", new ARow("714", "1", "US", "USA", "United States", "CA", "California"));
        areaCodes.put("1/760", new ARow("760", "1", "US", "USA", "United States", "CA", "California"));
        areaCodes.put("1/805", new ARow("805", "1", "US", "USA", "United States", "CA", "California"));
        areaCodes.put("1/818", new ARow("818", "1", "US", "USA", "United States", "CA", "California"));
        areaCodes.put("1/831", new ARow("831", "1", "US", "USA", "United States", "CA", "California"));
        areaCodes.put("1/858", new ARow("858", "1", "US", "USA", "United States", "CA", "California"));
        areaCodes.put("1/909", new ARow("909", "1", "US", "USA", "United States", "CA", "California"));
        areaCodes.put("1/916", new ARow("916", "1", "US", "USA", "United States", "CA", "California"));
        areaCodes.put("1/925", new ARow("925", "1", "US", "USA", "United States", "CA", "California"));
        areaCodes.put("1/949", new ARow("949", "1", "US", "USA", "United States", "CA", "California"));
        areaCodes.put("1/951", new ARow("951", "1", "US", "USA", "United States", "CA", "California"));
        areaCodes.put("1/303", new ARow("303", "1", "US", "USA", "United States", "CO", "Colorado"));
        areaCodes.put("1/719", new ARow("719", "1", "US", "USA", "United States", "CO", "Colorado"));
        areaCodes.put("1/970", new ARow("970", "1", "US", "USA", "United States", "CO", "Colorado"));
        areaCodes.put("1/203", new ARow("203", "1", "US", "USA", "United States", "CT", "Connecticut"));
        areaCodes.put("1/860", new ARow("860", "1", "US", "USA", "United States", "CT", "Connecticut"));
        areaCodes.put("1/302", new ARow("302", "1", "US", "USA", "United States", "DE", "Delaware"));
        areaCodes.put("1/202", new ARow("202", "1", "US", "USA", "United States", "DC", "District of Columbia"));
        areaCodes.put("1/239", new ARow("239", "1", "US", "USA", "United States", "FL", "Florida"));
        areaCodes.put("1/305", new ARow("305", "1", "US", "USA", "United States", "FL", "Florida"));
        areaCodes.put("1/321", new ARow("321", "1", "US", "USA", "United States", "FL", "Florida"));
        areaCodes.put("1/352", new ARow("352", "1", "US", "USA", "United States", "FL", "Florida"));
        areaCodes.put("1/386", new ARow("386", "1", "US", "USA", "United States", "FL", "Florida"));
        areaCodes.put("1/407", new ARow("407", "1", "US", "USA", "United States", "FL", "Florida"));
        areaCodes.put("1/561", new ARow("561", "1", "US", "USA", "United States", "FL", "Florida"));
        areaCodes.put("1/727", new ARow("727", "1", "US", "USA", "United States", "FL", "Florida"));
        areaCodes.put("1/772", new ARow("772", "1", "US", "USA", "United States", "FL", "Florida"));
        areaCodes.put("1/813", new ARow("813", "1", "US", "USA", "United States", "FL", "Florida"));
        areaCodes.put("1/850", new ARow("850", "1", "US", "USA", "United States", "FL", "Florida"));
        areaCodes.put("1/863", new ARow("863", "1", "US", "USA", "United States", "FL", "Florida"));
        areaCodes.put("1/904", new ARow("904", "1", "US", "USA", "United States", "FL", "Florida"));
        areaCodes.put("1/941", new ARow("941", "1", "US", "USA", "United States", "FL", "Florida"));
        areaCodes.put("1/954", new ARow("954", "1", "US", "USA", "United States", "FL", "Florida"));
        areaCodes.put("1/229", new ARow("229", "1", "US", "USA", "United States", "GA", "Georgia"));
        areaCodes.put("1/404", new ARow("404", "1", "US", "USA", "United States", "GA", "Georgia"));
        areaCodes.put("1/478", new ARow("478", "1", "US", "USA", "United States", "GA", "Georgia"));
        areaCodes.put("1/706", new ARow("706", "1", "US", "USA", "United States", "GA", "Georgia"));
        areaCodes.put("1/770", new ARow("770", "1", "US", "USA", "United States", "GA", "Georgia"));
        areaCodes.put("1/912", new ARow("912", "1", "US", "USA", "United States", "GA", "Georgia"));
        areaCodes.put("1/808", new ARow("808", "1", "US", "USA", "United States", "HI", "Hawaii"));
        areaCodes.put("1/208", new ARow("208", "1", "US", "USA", "United States", "ID", "Idaho"));
        areaCodes.put("1/217", new ARow("217", "1", "US", "USA", "United States", "IL", "Illinois"));
        areaCodes.put("1/309", new ARow("309", "1", "US", "USA", "United States", "IL", "Illinois"));
        areaCodes.put("1/312", new ARow("312", "1", "US", "USA", "United States", "IL", "Illinois"));
        areaCodes.put("1/618", new ARow("618", "1", "US", "USA", "United States", "IL", "Illinois"));
        areaCodes.put("1/630", new ARow("630", "1", "US", "USA", "United States", "IL", "Illinois"));
        areaCodes.put("1/708", new ARow("708", "1", "US", "USA", "United States", "IL", "Illinois"));
        areaCodes.put("1/773", new ARow("773", "1", "US", "USA", "United States", "IL", "Illinois"));
        areaCodes.put("1/815", new ARow("815", "1", "US", "USA", "United States", "IL", "Illinois"));
        areaCodes.put("1/847", new ARow("847", "1", "US", "USA", "United States", "IL", "Illinois"));
        areaCodes.put("1/219", new ARow("219", "1", "US", "USA", "United States", "IN", "Indiana"));
        areaCodes.put("1/260", new ARow("260", "1", "US", "USA", "United States", "IN", "Indiana"));
        areaCodes.put("1/317", new ARow("317", "1", "US", "USA", "United States", "IN", "Indiana"));
        areaCodes.put("1/574", new ARow("574", "1", "US", "USA", "United States", "IN", "Indiana"));
        areaCodes.put("1/765", new ARow("765", "1", "US", "USA", "United States", "IN", "Indiana"));
        areaCodes.put("1/812", new ARow("812", "1", "US", "USA", "United States", "IN", "Indiana"));
        areaCodes.put("1/319", new ARow("319", "1", "US", "USA", "United States", "IA", "Iowa"));
        areaCodes.put("1/515", new ARow("515", "1", "US", "USA", "United States", "IA", "Iowa"));
        areaCodes.put("1/563", new ARow("563", "1", "US", "USA", "United States", "IA", "Iowa"));
        areaCodes.put("1/641", new ARow("641", "1", "US", "USA", "United States", "IA", "Iowa"));
        areaCodes.put("1/712", new ARow("712", "1", "US", "USA", "United States", "IA", "Iowa"));
        areaCodes.put("1/316", new ARow("316", "1", "US", "USA", "United States", "KS", "Kansas"));
        areaCodes.put("1/620", new ARow("620", "1", "US", "USA", "United States", "KS", "Kansas"));
        areaCodes.put("1/785", new ARow("785", "1", "US", "USA", "United States", "KS", "Kansas"));
        areaCodes.put("1/913", new ARow("913", "1", "US", "USA", "United States", "KS", "Kansas"));
        areaCodes.put("1/270", new ARow("270", "1", "US", "USA", "United States", "KY", "Kentucky"));
        areaCodes.put("1/502", new ARow("502", "1", "US", "USA", "United States", "KY", "Kentucky"));
        areaCodes.put("1/606", new ARow("606", "1", "US", "USA", "United States", "KY", "Kentucky"));
        areaCodes.put("1/859", new ARow("859", "1", "US", "USA", "United States", "KY", "Kentucky"));
        areaCodes.put("1/225", new ARow("225", "1", "US", "USA", "United States", "LA", "Louisiana"));
        areaCodes.put("1/318", new ARow("318", "1", "US", "USA", "United States", "LA", "Louisiana"));
        areaCodes.put("1/337", new ARow("337", "1", "US", "USA", "United States", "LA", "Louisiana"));
        areaCodes.put("1/504", new ARow("504", "1", "US", "USA", "United States", "LA", "Louisiana"));
        areaCodes.put("1/985", new ARow("985", "1", "US", "USA", "United States", "LA", "Louisiana"));
        areaCodes.put("1/207", new ARow("207", "1", "US", "USA", "United States", "ME", "Maine"));
        areaCodes.put("1/301", new ARow("301", "1", "US", "USA", "United States", "MD", "Maryland"));
        areaCodes.put("1/410", new ARow("410", "1", "US", "USA", "United States", "MD", "Maryland"));
        areaCodes.put("1/413", new ARow("413", "1", "US", "USA", "United States", "MA", "Massachusetts"));
        areaCodes.put("1/508", new ARow("508", "1", "US", "USA", "United States", "MA", "Massachusetts"));
        areaCodes.put("1/617", new ARow("617", "1", "US", "USA", "United States", "MA", "Massachusetts"));
        areaCodes.put("1/781", new ARow("781", "1", "US", "USA", "United States", "MA", "Massachusetts"));
        areaCodes.put("1/978", new ARow("978", "1", "US", "USA", "United States", "MA", "Massachusetts"));
        areaCodes.put("1/231", new ARow("231", "1", "US", "USA", "United States", "MI", "Michigan"));
        areaCodes.put("1/248", new ARow("248", "1", "US", "USA", "United States", "MI", "Michigan"));
        areaCodes.put("1/269", new ARow("269", "1", "US", "USA", "United States", "MI", "Michigan"));
        areaCodes.put("1/313", new ARow("313", "1", "US", "USA", "United States", "MI", "Michigan"));
        areaCodes.put("1/517", new ARow("517", "1", "US", "USA", "United States", "MI", "Michigan"));
        areaCodes.put("1/586", new ARow("586", "1", "US", "USA", "United States", "MI", "Michigan"));
        areaCodes.put("1/616", new ARow("616", "1", "US", "USA", "United States", "MI", "Michigan"));
        areaCodes.put("1/734", new ARow("734", "1", "US", "USA", "United States", "MI", "Michigan"));
        areaCodes.put("1/810", new ARow("810", "1", "US", "USA", "United States", "MI", "Michigan"));
        areaCodes.put("1/906", new ARow("906", "1", "US", "USA", "United States", "MI", "Michigan"));
        areaCodes.put("1/989", new ARow("989", "1", "US", "USA", "United States", "MI", "Michigan"));
        areaCodes.put("1/218", new ARow("218", "1", "US", "USA", "United States", "MN", "Minnesota"));
        areaCodes.put("1/320", new ARow("320", "1", "US", "USA", "United States", "MN", "Minnesota"));
        areaCodes.put("1/507", new ARow("507", "1", "US", "USA", "United States", "MN", "Minnesota"));
        areaCodes.put("1/612", new ARow("612", "1", "US", "USA", "United States", "MN", "Minnesota"));
        areaCodes.put("1/651", new ARow("651", "1", "US", "USA", "United States", "MN", "Minnesota"));
        areaCodes.put("1/763", new ARow("763", "1", "US", "USA", "United States", "MN", "Minnesota"));
        areaCodes.put("1/952", new ARow("952", "1", "US", "USA", "United States", "MN", "Minnesota"));
        areaCodes.put("1/228", new ARow("228", "1", "US", "USA", "United States", "MS", "Mississippi"));
        areaCodes.put("1/601", new ARow("601", "1", "US", "USA", "United States", "MS", "Mississippi"));
        areaCodes.put("1/662", new ARow("662", "1", "US", "USA", "United States", "MS", "Mississippi"));
        areaCodes.put("1/314", new ARow("314", "1", "US", "USA", "United States", "MO", "Missouri"));
        areaCodes.put("1/417", new ARow("417", "1", "US", "USA", "United States", "MO", "Missouri"));
        areaCodes.put("1/573", new ARow("573", "1", "US", "USA", "United States", "MO", "Missouri"));
        areaCodes.put("1/636", new ARow("636", "1", "US", "USA", "United States", "MO", "Missouri"));
        areaCodes.put("1/660", new ARow("660", "1", "US", "USA", "United States", "MO", "Missouri"));
        areaCodes.put("1/816", new ARow("816", "1", "US", "USA", "United States", "MO", "Missouri"));
        areaCodes.put("1/406", new ARow("406", "1", "US", "USA", "United States", "MT", "Montana"));
        areaCodes.put("1/308", new ARow("308", "1", "US", "USA", "United States", "NE", "Nebraska"));
        areaCodes.put("1/402", new ARow("402", "1", "US", "USA", "United States", "NE", "Nebraska"));
        areaCodes.put("1/702", new ARow("702", "1", "US", "USA", "United States", "NV", "Nevada"));
        areaCodes.put("1/775", new ARow("775", "1", "US", "USA", "United States", "NV", "Nevada"));
        areaCodes.put("1/603", new ARow("603", "1", "US", "USA", "United States", "NH", "New Hampshire"));
        areaCodes.put("1/201", new ARow("201", "1", "US", "USA", "United States", "NJ", "New Jersey"));
        areaCodes.put("1/609", new ARow("609", "1", "US", "USA", "United States", "NJ", "New Jersey"));
        areaCodes.put("1/732", new ARow("732", "1", "US", "USA", "United States", "NJ", "New Jersey"));
        areaCodes.put("1/856", new ARow("856", "1", "US", "USA", "United States", "NJ", "New Jersey"));
        areaCodes.put("1/908", new ARow("908", "1", "US", "USA", "United States", "NJ", "New Jersey"));
        areaCodes.put("1/973", new ARow("973", "1", "US", "USA", "United States", "NJ", "New Jersey"));
        areaCodes.put("1/505", new ARow("505", "1", "US", "USA", "United States", "NM", "New Mexico"));
        areaCodes.put("1/575", new ARow("575", "1", "US", "USA", "United States", "NM", "New Mexico"));
        areaCodes.put("1/212", new ARow("212", "1", "US", "USA", "United States", "NY", "New York"));
        areaCodes.put("1/315", new ARow("315", "1", "US", "USA", "United States", "NY", "New York"));
        areaCodes.put("1/516", new ARow("516", "1", "US", "USA", "United States", "NY", "New York"));
        areaCodes.put("1/518", new ARow("518", "1", "US", "USA", "United States", "NY", "New York"));
        areaCodes.put("1/585", new ARow("585", "1", "US", "USA", "United States", "NY", "New York"));
        areaCodes.put("1/607", new ARow("607", "1", "US", "USA", "United States", "NY", "New York"));
        areaCodes.put("1/631", new ARow("631", "1", "US", "USA", "United States", "NY", "New York"));
        areaCodes.put("1/716", new ARow("716", "1", "US", "USA", "United States", "NY", "New York"));
        areaCodes.put("1/718", new ARow("718", "1", "US", "USA", "United States", "NY", "New York"));
        areaCodes.put("1/845", new ARow("845", "1", "US", "USA", "United States", "NY", "New York"));
        areaCodes.put("1/914", new ARow("914", "1", "US", "USA", "United States", "NY", "New York"));
        areaCodes.put("1/252", new ARow("252", "1", "US", "USA", "United States", "NC", "North Carolina"));
        areaCodes.put("1/336", new ARow("336", "1", "US", "USA", "United States", "NC", "North Carolina"));
        areaCodes.put("1/704", new ARow("704", "1", "US", "USA", "United States", "NC", "North Carolina"));
        areaCodes.put("1/828", new ARow("828", "1", "US", "USA", "United States", "NC", "North Carolina"));
        areaCodes.put("1/910", new ARow("910", "1", "US", "USA", "United States", "NC", "North Carolina"));
        areaCodes.put("1/919", new ARow("919", "1", "US", "USA", "United States", "NC", "North Carolina"));
        areaCodes.put("1/701", new ARow("701", "1", "US", "USA", "United States", "ND", "North Dakota"));
        areaCodes.put("1/216", new ARow("216", "1", "US", "USA", "United States", "OH", "Ohio"));
        areaCodes.put("1/330", new ARow("330", "1", "US", "USA", "United States", "OH", "Ohio"));
        areaCodes.put("1/419", new ARow("419", "1", "US", "USA", "United States", "OH", "Ohio"));
        areaCodes.put("1/440", new ARow("440", "1", "US", "USA", "United States", "OH", "Ohio"));
        areaCodes.put("1/513", new ARow("513", "1", "US", "USA", "United States", "OH", "Ohio"));
        areaCodes.put("1/614", new ARow("614", "1", "US", "USA", "United States", "OH", "Ohio"));
        areaCodes.put("1/740", new ARow("740", "1", "US", "USA", "United States", "OH", "Ohio"));
        areaCodes.put("1/937", new ARow("937", "1", "US", "USA", "United States", "OH", "Ohio"));
        areaCodes.put("1/405", new ARow("405", "1", "US", "USA", "United States", "OK", "Oklahoma"));
        areaCodes.put("1/580", new ARow("580", "1", "US", "USA", "United States", "OK", "Oklahoma"));
        areaCodes.put("1/918", new ARow("918", "1", "US", "USA", "United States", "OK", "Oklahoma"));
        areaCodes.put("1/503", new ARow("503", "1", "US", "USA", "United States", "OR", "Oregon"));
        areaCodes.put("1/541", new ARow("541", "1", "US", "USA", "United States", "OR", "Oregon"));
        areaCodes.put("1/215", new ARow("215", "1", "US", "USA", "United States", "PA", "Pennsylvania"));
        areaCodes.put("1/412", new ARow("412", "1", "US", "USA", "United States", "PA", "Pennsylvania"));
        areaCodes.put("1/570", new ARow("570", "1", "US", "USA", "United States", "PA", "Pennsylvania"));
        areaCodes.put("1/610", new ARow("610", "1", "US", "USA", "United States", "PA", "Pennsylvania"));
        areaCodes.put("1/717", new ARow("717", "1", "US", "USA", "United States", "PA", "Pennsylvania"));
        areaCodes.put("1/724", new ARow("724", "1", "US", "USA", "United States", "PA", "Pennsylvania"));
        areaCodes.put("1/814", new ARow("814", "1", "US", "USA", "United States", "PA", "Pennsylvania"));
        areaCodes.put("1/401", new ARow("401", "1", "US", "USA", "United States", "RI", "Rhode Island"));
        areaCodes.put("1/803", new ARow("803", "1", "US", "USA", "United States", "SC", "South Carolina"));
        areaCodes.put("1/843", new ARow("843", "1", "US", "USA", "United States", "SC", "South Carolina"));
        areaCodes.put("1/864", new ARow("864", "1", "US", "USA", "United States", "SC", "South Carolina"));
        areaCodes.put("1/605", new ARow("605", "1", "US", "USA", "United States", "SD", "South Dakota"));
        areaCodes.put("1/423", new ARow("423", "1", "US", "USA", "United States", "TN", "Tennessee"));
        areaCodes.put("1/615", new ARow("615", "1", "US", "USA", "United States", "TN", "Tennessee"));
        areaCodes.put("1/731", new ARow("731", "1", "US", "USA", "United States", "TN", "Tennessee"));
        areaCodes.put("1/865", new ARow("865", "1", "US", "USA", "United States", "TN", "Tennessee"));
        areaCodes.put("1/901", new ARow("901", "1", "US", "USA", "United States", "TN", "Tennessee"));
        areaCodes.put("1/931", new ARow("931", "1", "US", "USA", "United States", "TN", "Tennessee"));
        areaCodes.put("1/210", new ARow("210", "1", "US", "USA", "United States", "TX", "Texas"));
        areaCodes.put("1/214", new ARow("214", "1", "US", "USA", "United States", "TX", "Texas"));
        areaCodes.put("1/254", new ARow("254", "1", "US", "USA", "United States", "TX", "Texas"));
        areaCodes.put("1/281", new ARow("281", "1", "US", "USA", "United States", "TX", "Texas"));
        areaCodes.put("1/325", new ARow("325", "1", "US", "USA", "United States", "TX", "Texas"));
        areaCodes.put("1/361", new ARow("361", "1", "US", "USA", "United States", "TX", "Texas"));
        areaCodes.put("1/409", new ARow("409", "1", "US", "USA", "United States", "TX", "Texas"));
        areaCodes.put("1/432", new ARow("432", "1", "US", "USA", "United States", "TX", "Texas"));
        areaCodes.put("1/512", new ARow("512", "1", "US", "USA", "United States", "TX", "Texas"));
        areaCodes.put("1/713", new ARow("713", "1", "US", "USA", "United States", "TX", "Texas"));
        areaCodes.put("1/806", new ARow("806", "1", "US", "USA", "United States", "TX", "Texas"));
        areaCodes.put("1/817", new ARow("817", "1", "US", "USA", "United States", "TX", "Texas"));
        areaCodes.put("1/830", new ARow("830", "1", "US", "USA", "United States", "TX", "Texas"));
        areaCodes.put("1/903", new ARow("903", "1", "US", "USA", "United States", "TX", "Texas"));
        areaCodes.put("1/915", new ARow("915", "1", "US", "USA", "United States", "TX", "Texas"));
        areaCodes.put("1/936", new ARow("936", "1", "US", "USA", "United States", "TX", "Texas"));
        areaCodes.put("1/940", new ARow("940", "1", "US", "USA", "United States", "TX", "Texas"));
        areaCodes.put("1/956", new ARow("956", "1", "US", "USA", "United States", "TX", "Texas"));
        areaCodes.put("1/972", new ARow("972", "1", "US", "USA", "United States", "TX", "Texas"));
        areaCodes.put("1/979", new ARow("979", "1", "US", "USA", "United States", "TX", "Texas"));
        areaCodes.put("1/435", new ARow("435", "1", "US", "USA", "United States", "UT", "Utah"));
        areaCodes.put("1/801", new ARow("801", "1", "US", "USA", "United States", "UT", "Utah"));
        areaCodes.put("1/802", new ARow("802", "1", "US", "USA", "United States", "VT", "Vermont"));
        areaCodes.put("1/276", new ARow("276", "1", "US", "USA", "United States", "VA", "Virginia"));
        areaCodes.put("1/434", new ARow("434", "1", "US", "USA", "United States", "VA", "Virginia"));
        areaCodes.put("1/540", new ARow("540", "1", "US", "USA", "United States", "VA", "Virginia"));
        areaCodes.put("1/703", new ARow("703", "1", "US", "USA", "United States", "VA", "Virginia"));
        areaCodes.put("1/757", new ARow("757", "1", "US", "USA", "United States", "VA", "Virginia"));
        areaCodes.put("1/804", new ARow("804", "1", "US", "USA", "United States", "VA", "Virginia"));
        areaCodes.put("1/206", new ARow("206", "1", "US", "USA", "United States", "WA", "Washington"));
        areaCodes.put("1/253", new ARow("253", "1", "US", "USA", "United States", "WA", "Washington"));
        areaCodes.put("1/360", new ARow("360", "1", "US", "USA", "United States", "WA", "Washington"));
        areaCodes.put("1/425", new ARow("425", "1", "US", "USA", "United States", "WA", "Washington"));
        areaCodes.put("1/509", new ARow("509", "1", "US", "USA", "United States", "WA", "Washington"));
        areaCodes.put("1/304", new ARow("304", "1", "US", "USA", "United States", "WV", "West Virginia"));
        areaCodes.put("1/262", new ARow("262", "1", "US", "USA", "United States", "WI", "Wisconsin"));
        areaCodes.put("1/414", new ARow("414", "1", "US", "USA", "United States", "WI", "Wisconsin"));
        areaCodes.put("1/608", new ARow("608", "1", "US", "USA", "United States", "WI", "Wisconsin"));
        areaCodes.put("1/715", new ARow("715", "1", "US", "USA", "United States", "WI", "Wisconsin"));
        areaCodes.put("1/920", new ARow("920", "1", "US", "USA", "United States", "WI", "Wisconsin"));
        areaCodes.put("1/307", new ARow("307", "1", "US", "USA", "United States", "WY", "Wyoming"));
        areaCodes.put("1/340", new ARow("340", "1", "VI", "VIR", "United States Virgin Islands", "", ""));

        // Brazil
        areaCodes.put("55/11", new ARow("11", "55", "BR", "BRA", "Brazil", "SP", "São Paulo"));
        areaCodes.put("55/12", new ARow("12", "55", "BR", "BRA", "Brazil", "SP", "São Paulo"));
        areaCodes.put("55/13", new ARow("13", "55", "BR", "BRA", "Brazil", "SP", "São Paulo"));
        areaCodes.put("55/14", new ARow("14", "55", "BR", "BRA", "Brazil", "SP", "São Paulo"));
        areaCodes.put("55/15", new ARow("15", "55", "BR", "BRA", "Brazil", "SP", "São Paulo"));
        areaCodes.put("55/16", new ARow("16", "55", "BR", "BRA", "Brazil", "SP", "São Paulo"));
        areaCodes.put("55/17", new ARow("17", "55", "BR", "BRA", "Brazil", "SP", "São Paulo"));
        areaCodes.put("55/18", new ARow("18", "55", "BR", "BRA", "Brazil", "SP", "São Paulo"));
        areaCodes.put("55/19", new ARow("19", "55", "BR", "BRA", "Brazil", "SP", "São Paulo"));
        areaCodes.put("55/21", new ARow("21", "55", "BR", "BRA", "Brazil", "RJ", "Rio de Janeiro"));
        areaCodes.put("55/22", new ARow("22", "55", "BR", "BRA", "Brazil", "RJ", "Rio de Janeiro"));
        areaCodes.put("55/24", new ARow("24", "55", "BR", "BRA", "Brazil", "RJ", "Rio de Janeiro"));
        areaCodes.put("55/27", new ARow("27", "55", "BR", "BRA", "Brazil", "ES", "Espírito Santo"));
        areaCodes.put("55/28", new ARow("28", "55", "BR", "BRA", "Brazil", "ES", "Espírito Santo"));
        areaCodes.put("55/31", new ARow("31", "55", "BR", "BRA", "Brazil", "MG", "Minas Gerais"));
        areaCodes.put("55/32", new ARow("32", "55", "BR", "BRA", "Brazil", "MG", "Minas Gerais"));
        areaCodes.put("55/33", new ARow("33", "55", "BR", "BRA", "Brazil", "MG", "Minas Gerais"));
        areaCodes.put("55/34", new ARow("34", "55", "BR", "BRA", "Brazil", "MG", "Minas Gerais"));
        areaCodes.put("55/35", new ARow("35", "55", "BR", "BRA", "Brazil", "MG", "Minas Gerais"));
        areaCodes.put("55/37", new ARow("37", "55", "BR", "BRA", "Brazil", "MG", "Minas Gerais"));
        areaCodes.put("55/38", new ARow("38", "55", "BR", "BRA", "Brazil", "MG", "Minas Gerais"));
        areaCodes.put("55/41", new ARow("41", "55", "BR", "BRA", "Brazil", "PR", "Paraná"));
        areaCodes.put("55/42", new ARow("42", "55", "BR", "BRA", "Brazil", "PR", "Paraná"));
        areaCodes.put("55/43", new ARow("43", "55", "BR", "BRA", "Brazil", "PR", "Paraná"));
        areaCodes.put("55/44", new ARow("44", "55", "BR", "BRA", "Brazil", "PR", "Paraná"));
        areaCodes.put("55/45", new ARow("45", "55", "BR", "BRA", "Brazil", "PR", "Paraná"));
        areaCodes.put("55/46", new ARow("46", "55", "BR", "BRA", "Brazil", "PR", "Paraná"));
        areaCodes.put("55/47", new ARow("47", "55", "BR", "BRA", "Brazil", "SC", "Santa Catarina"));
        areaCodes.put("55/48", new ARow("48", "55", "BR", "BRA", "Brazil", "SC", "Santa Catarina"));
        areaCodes.put("55/49", new ARow("49", "55", "BR", "BRA", "Brazil", "SC", "Santa Catarina"));
        areaCodes.put("55/51", new ARow("51", "55", "BR", "BRA", "Brazil", "RS", "Rio Grande do Sul"));
        areaCodes.put("55/53", new ARow("53", "55", "BR", "BRA", "Brazil", "RS", "Rio Grande do Sul"));
        areaCodes.put("55/54", new ARow("54", "55", "BR", "BRA", "Brazil", "RS", "Rio Grande do Sul"));
        areaCodes.put("55/55", new ARow("55", "55", "BR", "BRA", "Brazil", "RS", "Rio Grande do Sul"));
        areaCodes.put("55/61", new ARow("61", "55", "BR", "BRA", "Brazil", "DF", "Distrito Federal"));
        areaCodes.put("55/62", new ARow("62", "55", "BR", "BRA", "Brazil", "GO", "Goiás"));
        areaCodes.put("55/63", new ARow("63", "55", "BR", "BRA", "Brazil", "TO", "Tocantins"));
        areaCodes.put("55/64", new ARow("64", "55", "BR", "BRA", "Brazil", "GO", "Goiás"));
        areaCodes.put("55/65", new ARow("65", "55", "BR", "BRA", "Brazil", "MT", "Mato Grosso"));
        areaCodes.put("55/66", new ARow("66", "55", "BR", "BRA", "Brazil", "MT", "Mato Grosso"));
        areaCodes.put("55/67", new ARow("67", "55", "BR", "BRA", "Brazil", "MS", "Mato Grosso do Sul"));
        areaCodes.put("55/68", new ARow("68", "55", "BR", "BRA", "Brazil", "AC", "Acre"));
        areaCodes.put("55/69", new ARow("69", "55", "BR", "BRA", "Brazil", "RO", "Rondônia"));
        areaCodes.put("55/71", new ARow("71", "55", "BR", "BRA", "Brazil", "BA", "Bahia"));
        areaCodes.put("55/73", new ARow("73", "55", "BR", "BRA", "Brazil", "BA", "Bahia"));
        areaCodes.put("55/74", new ARow("74", "55", "BR", "BRA", "Brazil", "BA", "Bahia"));
        areaCodes.put("55/75", new ARow("75", "55", "BR", "BRA", "Brazil", "BA", "Bahia"));
        areaCodes.put("55/77", new ARow("77", "55", "BR", "BRA", "Brazil", "BA", "Bahia"));
        areaCodes.put("55/79", new ARow("79", "55", "BR", "BRA", "Brazil", "SE", "Sergipe"));
        areaCodes.put("55/81", new ARow("81", "55", "BR", "BRA", "Brazil", "PE", "Pernambuco"));
        areaCodes.put("55/82", new ARow("82", "55", "BR", "BRA", "Brazil", "AL", "Alagoas"));
        areaCodes.put("55/83", new ARow("83", "55", "BR", "BRA", "Brazil", "PB", "Paraíba"));
        areaCodes.put("55/84", new ARow("84", "55", "BR", "BRA", "Brazil", "RN", "Rio Grande do Norte"));
        areaCodes.put("55/85", new ARow("85", "55", "BR", "BRA", "Brazil", "CE", "Ceará"));
        areaCodes.put("55/86", new ARow("86", "55", "BR", "BRA", "Brazil", "PI", "Piauí"));
        areaCodes.put("55/87", new ARow("87", "55", "BR", "BRA", "Brazil", "PE", "Pernambuco"));
        areaCodes.put("55/88", new ARow("88", "55", "BR", "BRA", "Brazil", "CE", "Ceará"));
        areaCodes.put("55/89", new ARow("89", "55", "BR", "BRA", "Brazil", "PI", "Piauí"));
        areaCodes.put("55/91", new ARow("91", "55", "BR", "BRA", "Brazil", "PA", "Pará"));
        areaCodes.put("55/92", new ARow("92", "55", "BR", "BRA", "Brazil", "AM", "Amazonas"));
        areaCodes.put("55/93", new ARow("93", "55", "BR", "BRA", "Brazil", "PA", "Pará"));
        areaCodes.put("55/94", new ARow("94", "55", "BR", "BRA", "Brazil", "PA", "Pará"));
        areaCodes.put("55/95", new ARow("95", "55", "BR", "BRA", "Brazil", "RR", "Roraima"));
        areaCodes.put("55/96", new ARow("96", "55", "BR", "BRA", "Brazil", "AP", "Amapá"));
        areaCodes.put("55/97", new ARow("97", "55", "BR", "BRA", "Brazil", "AM", "Amazonas"));
        areaCodes.put("55/98", new ARow("98", "55", "BR", "BRA", "Brazil", "MA", "Maranhão"));
        areaCodes.put("55/99", new ARow("99", "55", "BR", "BRA", "Brazil", "MA", "Maranhão"));
    }

    public static void main(String[] args) {

        Set<String> linesSet = new HashSet<>();

        for(ARow v : areaCodes.values()) {
            linesSet.add(
                    "| " + String.format("%-32s", v.countryName) +
                    " | " + v.isoCode2 +
                    " | +" + v.countryCode +
                    " | ![" + v.isoCode2 + "](app/src/main/res/drawable/flag_" +
                    v.isoCode2.toLowerCase() + ".png) |");
        }

        ArrayList<String> lines = new ArrayList<>(linesSet);
        Collections.sort(lines);

        for(String line : lines) {
            System.out.println(line);
        }
    }
}
