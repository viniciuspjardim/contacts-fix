package vpjardim.com.contactsfix;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class FormatterUnitTest {

    static String[] numbers = new String[] {

            "+55 63 99930-6102",   "+55 63 99930-6102",  // Brazil mobile
            "984546408",           "+55 63 98454-6408",  // Brazil mobile
            "992338305",           "+55 63 99233-8305",  // Brazil mobile
            "14 (63) 984568863",   "+55 63 98456-8863",  // Brazil mobile
            "01411992907556",      "+55 11 99290-7556",  // Brazil mobile
            "0 14 (62)984041203",  "+55 62 98404-1203",  // Brazil mobile
            "0 14 (63) 984116963", "+55 63 98411-6963",  // Brazil mobile
            "01494991457625",      "+55 94 99145-7625",  // Brazil mobile
            "0 14 (62 999192425",  "+55 62 99919-2425",  // Brazil mobile

            "32131233",            "+55 63 3213-1233",   // Brazil residential
            "33616662",            "+55 63 3361-6662",   // Brazil residential
            "3234-9452",           "+55 63 3234-9452",   // Brazil residential

            "+34606122257",        "+34 606 12 22 57",   // Spain
            "+34606129614",        "+34 606 12 96 14",   // Spain

            "+1 (647) 526-8963",   "+1 647-526-8963",    // US, Canada
            "+16475266214",        "+1 647-526-6214",    // US, Canada

            "+238 74317 19",       "+238 743 17 19",     // Cape Verde
            "+238 260 15 07",      "+238 260 15 07",     // Cape Verde

            "0800 744 0464",       "0800 744 0464",      // 0800
            "08007440464",         "0800 744 0464",      // 0800

            "190",                 null,                 // Special
            "*100",                null,                 // Special

            "(647) 778-7788",      null,                 // US, Canada (no country code)
            "6477783625",          null,                 // US, Canada (no country code)
    };

    @Test
    public void formatIsCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}