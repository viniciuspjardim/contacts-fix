/*
 * Copyright 2017 Vinícius Petrocione Jardim
 */

package vpjardim.com.contactsfix;

import java.util.HashMap;

/**
 * @author Vinícius Jardim
 * 15/02/2017
 */
public final class Codes {

    public static final class Row {
        public String countryCode;
        public String isoCode2;
        public String isoCode3;
        public String countryName;

        public Row(String countryCode, String isoCode2, String isoCode3, String countryName) {
            this.countryCode = countryCode;
            this.isoCode2 = isoCode2;
            this.isoCode3 = isoCode3;
            this.countryName = countryName;
        }

        @Override
        public String toString() {
            return countryCode + " " + countryName;
        }
    }

    public static final HashMap<String, Row> table = new HashMap<>();

    static {
        table.put("1",       new Row("1",       "US", "USA", "United States"));
        table.put("7",       new Row("7",       "RU", "RUS", "Russia"));
        table.put("20",      new Row("20",      "EG", "EGY", "Egypt"));
        table.put("27",      new Row("27",      "ZA", "ZAF", "South Africa"));
        table.put("30",      new Row("30",      "GR", "GRC", "Greece"));
        table.put("31",      new Row("31",      "NL", "NLD", "Netherlands"));
        table.put("32",      new Row("32",      "BE", "BEL", "Belgium"));
        table.put("33",      new Row("33",      "FR", "FRA", "France"));
        table.put("34",      new Row("34",      "ES", "ESP", "Spain"));
        table.put("36",      new Row("36",      "HU", "HUN", "Hungary"));
        table.put("39",      new Row("39",      "IT", "ITA", "Italy"));
        table.put("40",      new Row("40",      "RO", "ROU", "Romania"));
        table.put("41",      new Row("41",      "CH", "CHE", "Switzerland"));
        table.put("43",      new Row("43",      "AT", "AUT", "Austria"));
        table.put("44",      new Row("44",      "GB", "GBR", "United Kingdom"));
        table.put("45",      new Row("45",      "DK", "DNK", "Denmark"));
        table.put("46",      new Row("46",      "SE", "SWE", "Sweden"));
        table.put("47",      new Row("47",      "NO", "NOR", "Norway"));
        table.put("48",      new Row("48",      "PL", "POL", "Poland"));
        table.put("49",      new Row("49",      "DE", "DEU", "Germany"));
        table.put("51",      new Row("51",      "PE", "PER", "Peru"));
        table.put("52",      new Row("52",      "MX", "MEX", "Mexico"));
        table.put("53",      new Row("53",      "CU", "CUB", "Cuba"));
        table.put("54",      new Row("54",      "AR", "ARG", "Argentina"));
        table.put("55",      new Row("55",      "BR", "BRA", "Brazil"));
        table.put("56",      new Row("56",      "CL", "CHL", "Chile"));
        table.put("57",      new Row("57",      "CO", "COL", "Colombia"));
        table.put("58",      new Row("58",      "VE", "VEN", "Venezuela"));
        table.put("60",      new Row("60",      "MY", "MYS", "Malaysia"));
        table.put("61",      new Row("61",      "CC", "CCK", "Cocos Islands"));
        table.put("62",      new Row("62",      "ID", "IDN", "Indonesia"));
        table.put("63",      new Row("63",      "PH", "PHL", "Philippines"));
        table.put("64",      new Row("64",      "NZ", "NZL", "New Zealand"));
        table.put("65",      new Row("65",      "SG", "SGP", "Singapore"));
        table.put("66",      new Row("66",      "TH", "THA", "Thailand"));
        table.put("81",      new Row("81",      "JP", "JPN", "Japan"));
        table.put("82",      new Row("82",      "KR", "KOR", "South Korea"));
        table.put("84",      new Row("84",      "VN", "VNM", "Vietnam"));
        table.put("86",      new Row("86",      "CN", "CHN", "China"));
        table.put("90",      new Row("90",      "TR", "TUR", "Turkey"));
        table.put("91",      new Row("91",      "IN", "IND", "India"));
        table.put("92",      new Row("92",      "PK", "PAK", "Pakistan"));
        table.put("93",      new Row("93",      "AF", "AFG", "Afghanistan"));
        table.put("94",      new Row("94",      "LK", "LKA", "Sri Lanka"));
        table.put("95",      new Row("95",      "MM", "MMR", "Myanmar"));
        table.put("98",      new Row("98",      "IR", "IRN", "Iran"));
        table.put("211",     new Row("211",     "SS", "SSD", "South Sudan"));
        table.put("212",     new Row("212",     "MA", "MAR", "Morocco"));
        table.put("213",     new Row("213",     "DZ", "DZA", "Algeria"));
        table.put("216",     new Row("216",     "TN", "TUN", "Tunisia"));
        table.put("218",     new Row("218",     "LY", "LBY", "Libya"));
        table.put("220",     new Row("220",     "GM", "GMB", "Gambia"));
        table.put("221",     new Row("221",     "SN", "SEN", "Senegal"));
        table.put("222",     new Row("222",     "MR", "MRT", "Mauritania"));
        table.put("223",     new Row("223",     "ML", "MLI", "Mali"));
        table.put("224",     new Row("224",     "GN", "GIN", "Guinea"));
        table.put("225",     new Row("225",     "CI", "CIV", "Ivory Coast"));
        table.put("226",     new Row("226",     "BF", "BFA", "Burkina Faso"));
        table.put("227",     new Row("227",     "NE", "NER", "Niger"));
        table.put("228",     new Row("228",     "TG", "TGO", "Togo"));
        table.put("229",     new Row("229",     "BJ", "BEN", "Benin"));
        table.put("230",     new Row("230",     "MU", "MUS", "Mauritius"));
        table.put("231",     new Row("231",     "LR", "LBR", "Liberia"));
        table.put("232",     new Row("232",     "SL", "SLE", "Sierra Leone"));
        table.put("233",     new Row("233",     "GH", "GHA", "Ghana"));
        table.put("234",     new Row("234",     "NG", "NGA", "Nigeria"));
        table.put("235",     new Row("235",     "TD", "TCD", "Chad"));
        table.put("236",     new Row("236",     "CF", "CAF", "Central African Republic"));
        table.put("237",     new Row("237",     "CM", "CMR", "Cameroon"));
        table.put("238",     new Row("238",     "CV", "CPV", "Cape Verde"));
        table.put("239",     new Row("239",     "ST", "STP", "Sao Tome and Principe"));
        table.put("240",     new Row("240",     "GQ", "GNQ", "Equatorial Guinea"));
        table.put("241",     new Row("241",     "GA", "GAB", "Gabon"));
        table.put("242",     new Row("242",     "CG", "COG", "Republic of the Congo"));
        table.put("243",     new Row("243",     "CD", "COD", "Democratic Republic of the Congo"));
        table.put("244",     new Row("244",     "AO", "AGO", "Angola"));
        table.put("245",     new Row("245",     "GW", "GNB", "Guinea-Bissau"));
        table.put("246",     new Row("246",     "IO", "IOT", "British Indian Ocean Territory"));
        table.put("248",     new Row("248",     "SC", "SYC", "Seychelles"));
        table.put("249",     new Row("249",     "SD", "SDN", "Sudan"));
        table.put("250",     new Row("250",     "RW", "RWA", "Rwanda"));
        table.put("251",     new Row("251",     "ET", "ETH", "Ethiopia"));
        table.put("252",     new Row("252",     "SO", "SOM", "Somalia"));
        table.put("253",     new Row("253",     "DJ", "DJI", "Djibouti"));
        table.put("254",     new Row("254",     "KE", "KEN", "Kenya"));
        table.put("255",     new Row("255",     "TZ", "TZA", "Tanzania"));
        table.put("256",     new Row("256",     "UG", "UGA", "Uganda"));
        table.put("257",     new Row("257",     "BI", "BDI", "Burundi"));
        table.put("258",     new Row("258",     "MZ", "MOZ", "Mozambique"));
        table.put("260",     new Row("260",     "ZM", "ZMB", "Zambia"));
        table.put("261",     new Row("261",     "MG", "MDG", "Madagascar"));
        table.put("262",     new Row("262",     "RE", "REU", "Reunion"));
        table.put("262",     new Row("262",     "YT", "MYT", "Mayotte"));
        table.put("263",     new Row("263",     "ZW", "ZWE", "Zimbabwe"));
        table.put("264",     new Row("264",     "NA", "NAM", "Namibia"));
        table.put("265",     new Row("265",     "MW", "MWI", "Malawi"));
        table.put("266",     new Row("266",     "LS", "LSO", "Lesotho"));
        table.put("267",     new Row("267",     "BW", "BWA", "Botswana"));
        table.put("268",     new Row("268",     "SZ", "SWZ", "Swaziland"));
        table.put("269",     new Row("269",     "KM", "COM", "Comoros"));
        table.put("290",     new Row("290",     "SH", "SHN", "Saint Helena"));
        table.put("291",     new Row("291",     "ER", "ERI", "Eritrea"));
        table.put("297",     new Row("297",     "AW", "ABW", "Aruba"));
        table.put("298",     new Row("298",     "FO", "FRO", "Faroe Islands"));
        table.put("299",     new Row("299",     "GL", "GRL", "Greenland"));
        table.put("350",     new Row("350",     "GI", "GIB", "Gibraltar"));
        table.put("351",     new Row("351",     "PT", "PRT", "Portugal"));
        table.put("352",     new Row("352",     "LU", "LUX", "Luxembourg"));
        table.put("353",     new Row("353",     "IE", "IRL", "Ireland"));
        table.put("354",     new Row("354",     "IS", "ISL", "Iceland"));
        table.put("355",     new Row("355",     "AL", "ALB", "Albania"));
        table.put("356",     new Row("356",     "MT", "MLT", "Malta"));
        table.put("357",     new Row("357",     "CY", "CYP", "Cyprus"));
        table.put("358",     new Row("358",     "FI", "FIN", "Finland"));
        table.put("359",     new Row("359",     "BG", "BGR", "Bulgaria"));
        table.put("370",     new Row("370",     "LT", "LTU", "Lithuania"));
        table.put("371",     new Row("371",     "LV", "LVA", "Latvia"));
        table.put("372",     new Row("372",     "EE", "EST", "Estonia"));
        table.put("373",     new Row("373",     "MD", "MDA", "Moldova"));
        table.put("374",     new Row("374",     "AM", "ARM", "Armenia"));
        table.put("375",     new Row("375",     "BY", "BLR", "Belarus"));
        table.put("376",     new Row("376",     "AD", "AND", "Andorra"));
        table.put("377",     new Row("377",     "MC", "MCO", "Monaco"));
        table.put("378",     new Row("378",     "SM", "SMR", "San Marino"));
        table.put("379",     new Row("379",     "VA", "VAT", "Vatican"));
        table.put("380",     new Row("380",     "UA", "UKR", "Ukraine"));
        table.put("381",     new Row("381",     "RS", "SRB", "Serbia"));
        table.put("382",     new Row("382",     "ME", "MNE", "Montenegro"));
        table.put("383",     new Row("383",     "XK", "XKX", "Kosovo"));
        table.put("385",     new Row("385",     "HR", "HRV", "Croatia"));
        table.put("386",     new Row("386",     "SI", "SVN", "Slovenia"));
        table.put("387",     new Row("387",     "BA", "BIH", "Bosnia and Herzegovina"));
        table.put("389",     new Row("389",     "MK", "MKD", "Macedonia"));
        table.put("420",     new Row("420",     "CZ", "CZE", "Czech Republic"));
        table.put("421",     new Row("421",     "SK", "SVK", "Slovakia"));
        table.put("423",     new Row("423",     "LI", "LIE", "Liechtenstein"));
        table.put("500",     new Row("500",     "FK", "FLK", "Falkland Islands"));
        table.put("501",     new Row("501",     "BZ", "BLZ", "Belize"));
        table.put("502",     new Row("502",     "GT", "GTM", "Guatemala"));
        table.put("503",     new Row("503",     "SV", "SLV", "El Salvador"));
        table.put("504",     new Row("504",     "HN", "HND", "Honduras"));
        table.put("505",     new Row("505",     "NI", "NIC", "Nicaragua"));
        table.put("506",     new Row("506",     "CR", "CRI", "Costa Rica"));
        table.put("507",     new Row("507",     "PA", "PAN", "Panama"));
        table.put("508",     new Row("508",     "PM", "SPM", "Saint Pierre and Miquelon"));
        table.put("509",     new Row("509",     "HT", "HTI", "Haiti"));
        table.put("590",     new Row("590",     "BL", "BLM", "Saint Barthelemy"));
        table.put("590",     new Row("590",     "MF", "MAF", "Saint Martin"));
        table.put("591",     new Row("591",     "BO", "BOL", "Bolivia"));
        table.put("592",     new Row("592",     "GY", "GUY", "Guyana"));
        table.put("593",     new Row("593",     "EC", "ECU", "Ecuador"));
        table.put("595",     new Row("595",     "PY", "PRY", "Paraguay"));
        table.put("597",     new Row("597",     "SR", "SUR", "Suriname"));
        table.put("598",     new Row("598",     "UY", "URY", "Uruguay"));
        table.put("599",     new Row("599",     "CW", "CUW", "Curacao"));
        table.put("670",     new Row("670",     "TL", "TLS", "East Timor"));
        table.put("672",     new Row("672",     "AQ", "ATA", "Antarctica"));
        table.put("673",     new Row("673",     "BN", "BRN", "Brunei"));
        table.put("674",     new Row("674",     "NR", "NRU", "Nauru"));
        table.put("675",     new Row("675",     "PG", "PNG", "Papua New Guinea"));
        table.put("676",     new Row("676",     "TO", "TON", "Tonga"));
        table.put("677",     new Row("677",     "SB", "SLB", "Solomon Islands"));
        table.put("678",     new Row("678",     "VU", "VUT", "Vanuatu"));
        table.put("679",     new Row("679",     "FJ", "FJI", "Fiji"));
        table.put("680",     new Row("680",     "PW", "PLW", "Palau"));
        table.put("681",     new Row("681",     "WF", "WLF", "Wallis and Futuna"));
        table.put("682",     new Row("682",     "CK", "COK", "Cook Islands"));
        table.put("683",     new Row("683",     "NU", "NIU", "Niue"));
        table.put("685",     new Row("685",     "WS", "WSM", "Samoa"));
        table.put("686",     new Row("686",     "KI", "KIR", "Kiribati"));
        table.put("687",     new Row("687",     "NC", "NCL", "New Caledonia"));
        table.put("688",     new Row("688",     "TV", "TUV", "Tuvalu"));
        table.put("689",     new Row("689",     "PF", "PYF", "French Polynesia"));
        table.put("690",     new Row("690",     "TK", "TKL", "Tokelau"));
        table.put("691",     new Row("691",     "FM", "FSM", "Micronesia"));
        table.put("692",     new Row("692",     "MH", "MHL", "Marshall Islands"));
        table.put("850",     new Row("850",     "KP", "PRK", "North Korea"));
        table.put("852",     new Row("852",     "HK", "HKG", "Hong Kong"));
        table.put("853",     new Row("853",     "MO", "MAC", "Macau"));
        table.put("855",     new Row("855",     "KH", "KHM", "Cambodia"));
        table.put("856",     new Row("856",     "LA", "LAO", "Laos"));
        table.put("880",     new Row("880",     "BD", "BGD", "Bangladesh"));
        table.put("886",     new Row("886",     "TW", "TWN", "Taiwan"));
        table.put("960",     new Row("960",     "MV", "MDV", "Maldives"));
        table.put("961",     new Row("961",     "LB", "LBN", "Lebanon"));
        table.put("962",     new Row("962",     "JO", "JOR", "Jordan"));
        table.put("963",     new Row("963",     "SY", "SYR", "Syria"));
        table.put("964",     new Row("964",     "IQ", "IRQ", "Iraq"));
        table.put("965",     new Row("965",     "KW", "KWT", "Kuwait"));
        table.put("966",     new Row("966",     "SA", "SAU", "Saudi Arabia"));
        table.put("967",     new Row("967",     "YE", "YEM", "Yemen"));
        table.put("968",     new Row("968",     "OM", "OMN", "Oman"));
        table.put("970",     new Row("970",     "PS", "PSE", "Palestine"));
        table.put("971",     new Row("971",     "AE", "ARE", "United Arab Emirates"));
        table.put("972",     new Row("972",     "IL", "ISR", "Israel"));
        table.put("973",     new Row("973",     "BH", "BHR", "Bahrain"));
        table.put("974",     new Row("974",     "QA", "QAT", "Qatar"));
        table.put("975",     new Row("975",     "BT", "BTN", "Bhutan"));
        table.put("976",     new Row("976",     "MN", "MNG", "Mongolia"));
        table.put("977",     new Row("977",     "NP", "NPL", "Nepal"));
        table.put("992",     new Row("992",     "TJ", "TJK", "Tajikistan"));
        table.put("993",     new Row("993",     "TM", "TKM", "Turkmenistan"));
        table.put("994",     new Row("994",     "AZ", "AZE", "Azerbaijan"));
        table.put("995",     new Row("995",     "GE", "GEO", "Georgia"));
        table.put("996",     new Row("996",     "KG", "KGZ", "Kyrgyzstan"));
        table.put("998",     new Row("998",     "UZ", "UZB", "Uzbekistan"));
        table.put("1-242",   new Row("1-242",   "BS", "BHS", "Bahamas"));
        table.put("1-246",   new Row("1-246",   "BB", "BRB", "Barbados"));
        table.put("1-264",   new Row("1-264",   "AI", "AIA", "Anguilla"));
        table.put("1-268",   new Row("1-268",   "AG", "ATG", "Antigua and Barbuda"));
        table.put("1-284",   new Row("1-284",   "VG", "VGB", "British Virgin Islands"));
        table.put("1-340",   new Row("1-340",   "VI", "VIR", "U.S. Virgin Islands"));
        table.put("1-345",   new Row("1-345",   "KY", "CYM", "Cayman Islands"));
        table.put("1-441",   new Row("1-441",   "BM", "BMU", "Bermuda"));
        table.put("1-473",   new Row("1-473",   "GD", "GRD", "Grenada"));
        table.put("1-649",   new Row("1-649",   "TC", "TCA", "Turks and Caicos Islands"));
        table.put("1-664",   new Row("1-664",   "MS", "MSR", "Montserrat"));
        table.put("1-670",   new Row("1-670",   "MP", "MNP", "Northern Mariana Islands"));
        table.put("1-671",   new Row("1-671",   "GU", "GUM", "Guam"));
        table.put("1-684",   new Row("1-684",   "AS", "ASM", "American Samoa"));
        table.put("1-721",   new Row("1-721",   "SX", "SXM", "Sint Maarten"));
        table.put("1-758",   new Row("1-758",   "LC", "LCA", "Saint Lucia"));
        table.put("1-767",   new Row("1-767",   "DM", "DMA", "Dominica"));
        table.put("1-784",   new Row("1-784",   "VC", "VCT", "Saint Vincent and the Grenadines"));
        table.put("1-787",   new Row("1-787",   "PR", "PRI", "Puerto Rico"));
        table.put("1-939",   new Row("1-939",   "PR", "PRI", "Puerto Rico"));
        table.put("1-809",   new Row("1-809",   "DO", "DOM", "Dominican Republic"));
        table.put("1-829",   new Row("1-829",   "DO", "DOM", "Dominican Republic"));
        table.put("1-849",   new Row("1-849",   "DO", "DOM", "Dominican Republic"));
        table.put("1-868",   new Row("1-868",   "TT", "TTO", "Trinidad and Tobago"));
        table.put("1-869",   new Row("1-869",   "KN", "KNA", "Saint Kitts and Nevis"));
        table.put("1-876",   new Row("1-876",   "JM", "JAM", "Jamaica"));
        table.put("44-1481", new Row("44-1481", "GG", "GGY", "Guernsey"));
        table.put("44-1534", new Row("44-1534", "JE", "JEY", "Jersey"));
        table.put("44-1624", new Row("44-1624", "IM", "IMN", "Isle of Man"));

        // Duplicated code countries
        // table.put("1",       new Row("1",       "CA", "CAN", "Canada"));
        // table.put("7",       new Row("7",       "KZ", "KAZ", "Kazakhstan"));
        // table.put("47",      new Row("47",      "SJ", "SJM", "Svalbard and Jan Mayen"));
        // table.put("61",      new Row("61",      "CX", "CXR", "Christmas Island"));
        // table.put("61",      new Row("61",      "AU", "AUS", "Australia"));
        // table.put("64",      new Row("64",      "PN", "PCN", "Pitcairn"));
        // table.put("212",     new Row("212",     "EH", "ESH", "Western Sahara"));
        // table.put("599",     new Row("599",     "AN", "ANT", "Netherlands Antilles"));
    }
}
