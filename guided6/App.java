package com.rplbo.guided6;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner s = new Scanner(System.in);
        String pil = "";
        do{
            System.out.println("MENU GUIDED");
            System.out.println("1. Soal String");
            System.out.println("2. Soal Java IO");
            System.out.println("3. Soal Collection");
            System.out.println("4. Soal JDBC");
            System.out.println("5. Exit");
            System.out.print("Pilihan anda [1-5]: ");
            pil = s.nextLine();
            if(pil.equalsIgnoreCase("1")){
                //Gunakan StringGuided
                StringGuided sg = new StringGuided("kASur rUSAk");
                if(sg.cekPalindrom()) System.out.println("Palindrom"); else System.out.println("Bukan Palindrom");
                System.out.println(sg.ubahHurufBesarAwal());
            } else if(pil.equalsIgnoreCase("2")){
                //Gunakan JavaIOGuided
                JavaIOGuided jig = new JavaIOGuided();
                jig.cekPythagoras();
                jig.linearSearch();
            } else if (pil.equalsIgnoreCase("3")) {
                //Gunakan Collection
                IntToPosGuided ipg = new IntToPosGuided();
                System.out.println(ipg.infixToPostfix("2 + 3 * 4 - 1"));
            } else if(pil.equalsIgnoreCase("$")){
                //Gunakan JDBC
            }
        }while(!pil.equalsIgnoreCase("5"));
    }
}
