package org.example;

import java.util.ArrayList;
import java.text.Normalizer;
import java.io.IOException;
import java.util.Scanner;

import static javafx.application.Application.launch;

public class game {
    public static int game(ArrayList Country,ArrayList Country2,ArrayList essai
    ) throws IOException {

        // 5 pays capital=1p contient=1p curr=2p language=2 namefr=1p carside=1p  population=2(Higher/lower)
        int points = 0;
        System.out.println(Country.get(0));

        System.out.print("Enter Capital :");
        String capitalessai = normalizeString((String) essai.get(0));
        if (capitalessai.equals(normalizeString((String) Country.get(1)))) {
            points += 1;
            System.out.println("True");
        } else {
            System.out.println("False");
        }


        System.out.print("Enter Continent :");
        String contientessai = normalizeString((String) essai.get(1));
        if (contientessai.equals(normalizeString((String) Country.get(2)))) {
            points += 1;
            System.out.println("True");
        } else {
            System.out.println("False");
        }

        System.out.print("Enter Currency (Code Or Name):");
        String curressai = normalizeString((String) essai.get(2));
        if ((normalizeString((String) Country.get(3)).equals(curressai)) || ((normalizeString((String) Country.get(4)).equals(curressai)))) {
            points += 2;
            System.out.println("True");
        } else {
            System.out.println("False");
        }


        System.out.print("Enter Language (Code Or Name):");
        String langessai = normalizeString((String) essai.get(3));
        if ((normalizeString((String) Country.get(5)).equals(langessai)) || (normalizeString((String) Country.get(6)).equals(langessai))) {
            points += 2;
            System.out.println("True");
        } else {
            System.out.println("False");
        }


        System.out.print("Enter NameInFrench :");
        String frnameessai = normalizeString((String) essai.get(4));
        if (frnameessai.equals(normalizeString((String) Country.get(7)))) {
            points += 1;
            System.out.println("True");
        } else {
            System.out.println("False");
        }


        System.out.print("Enter CarSide :");
        String carsidelessai = normalizeString((String) essai.get(5));
        if (carsidelessai.equals(normalizeString((String) Country.get(8)))) {
            points += 1;
            System.out.println("True");
        } else {
            System.out.println("False");
        }


        System.out.print("Population <> than " + Country2.get(0) + " : ");
        String popessai = normalizeString((String) essai.get(6));
        if ((Integer.parseInt(normalizeString((String) Country.get(9))) >= Integer.parseInt( normalizeString((String) Country2.get(9))) && (popessai.equals("higher")) || ((Integer.parseInt(normalizeString((String) Country.get(9))) <= Integer.parseInt( normalizeString((String) Country2.get(9))) && (popessai.equals("lower")))))) {
            points += 2;
            System.out.println("True");
        } else {
            System.out.println("False");
        }


        System.out.println("Your SCORE : " + points);
        System.out.println("[Country, Capital , Contient , CurrencyCode, CurrencyName, LanguageCode, LanguageName, NameInFR, CarSide, Population]");
        System.out.println(Country);
        return points;
    }

    public static String normalizeString(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase().replace(" ", "").replace("-", "");
    }
}




