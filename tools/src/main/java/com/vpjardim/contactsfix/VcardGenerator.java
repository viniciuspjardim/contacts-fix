package com.vpjardim.contactsfix;

import java.util.ArrayList;

/**
 * @author Vinícius Jardim
 * 2017/05/20
 */
public class VcardGenerator {

    public static void generate(ArrayList<String> names, ArrayList<String> phones) {

        int cont = 0;

        OUTER:
        for(String name : names) {
            System.out.println("BEGIN:VCARD\nVERSION:3.0\nFN:" + name + "\nN:;" + name + ";;;");

            for(; cont < phones.size(); cont++) {

                String phone = phones.get(cont);

                if(phone.equals("")) {
                    System.out.println("END:VCARD");
                    cont++;
                    continue OUTER;
                }
                else
                    System.out.println("TEL;TYPE=CELL:" + phone);
            }
        }
    }

    public static void main(String[] args) {

        ArrayList<String> names  = new ArrayList<>();
        ArrayList<String> phones = new ArrayList<>();

        names.add("Alice");
        phones.add("+55 63 99930-6102");
        phones.add("984546408");
        phones.add("992338305");
        phones.add("");

        names.add("Amanda");
        phones.add("14 (63) 984568863");
        phones.add("01411992907556");
        phones.add("");

        names.add("Ana");
        phones.add("0 14 (62)984041203");
        phones.add("");

        names.add("Arthur");
        phones.add("0 14 (63) 984116963");
        phones.add("");

        names.add("Beatriz");
        phones.add("01494991457625");
        phones.add("");

        names.add("Bernardo");
        phones.add("0 14 (62 999192425");
        phones.add("");

        names.add("Camila");
        phones.add("32131233");
        phones.add("");

        names.add("Davi");
        phones.add("33616662");
        phones.add("3234-9452");
        phones.add("");

        names.add("Eduarda");
        phones.add("+34606122257");
        phones.add("+34606129614");
        phones.add("");

        names.add("Enzo");
        phones.add("+1 (647) 526-8963");
        phones.add("+16475266214");
        phones.add("");

        names.add("Felipe");
        phones.add("+238 74317 19");
        phones.add("+238 260 15 07");
        phones.add("");

        //names.add("Gabriel");
        //phones.add("");
        //phones.add("");
//
        //names.add("Guilherme");
        //phones.add("");
        //phones.add("");
//
        //names.add("Gustavo");
        //phones.add("");
        //phones.add("");
//
        //names.add("Isabela");
        //phones.add("");
        //phones.add("");
//
        //names.add("Joao");
        //phones.add("");
        //phones.add("");
//
        //names.add("Julia");
        //phones.add("");
        //phones.add("");
//
        //names.add("Lara");
        //phones.add("");
        //phones.add("");
//
        //names.add("Larissa");
        //phones.add("");
        //phones.add("");
//
        //names.add("Laura");
        //phones.add("");
        //phones.add("");
//
        //names.add("Leticia");
        //phones.add("");
        //phones.add("");
//
        //names.add("Lucas");
        //phones.add("");
        //phones.add("");
//
        //names.add("Maria");
        //phones.add("");
        //phones.add("");
//
        //names.add("Mariana");
        //phones.add("");
        //phones.add("");
//
        //names.add("Matheus");
        //phones.add("");
        //phones.add("");
//
        //names.add("Miguel");
        //phones.add("");
        //phones.add("");
//
        //names.add("Pedro");
        //phones.add("");
        //phones.add("");
//
        //names.add("Rafael");
        //phones.add("");
        //phones.add("");
//
        //names.add("Sofia");
        //phones.add("");
        //phones.add("");
//
        //names.add("Vinicius");
        //phones.add("");
        //phones.add("");

        //java.util.Collections.sort(names);
//
        //for(String name : names) {
        //    System.out.println("names.add(\"" + name + "\");");
        //    System.out.println("phones.add(\"\");\n" +
        //            "phones.add(\"\");\n");
        //}

        generate(names, phones);
    }
}
