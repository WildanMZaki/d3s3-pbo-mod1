import java.util.Scanner;

public class Soal5_Strings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String A = scanner.next();
        String B = scanner.next();

        // 1. Sum the lengths of A and B.
        int panjangTotal = A.length() + B.length();
        System.out.println(panjangTotal);

        // 2. Determine if A is lexicographically larger than B (i.e: does B come before A in the dictionary?)
        if (A.compareTo(B) > 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        String A_Kapital = A.substring(0, 1).toUpperCase() + A.substring(1);
        String B_Kapital = B.substring(0, 1).toUpperCase() + B.substring(1);
        System.out.println(A_Kapital + " " + B_Kapital);

        scanner.close();
    }
}

/**
 * Notes:
 * - str.compareTo(str) --> membandingkan String secara leksikografik
 * 
 * if A.compareTo(B) > 0 --> B datang sebelum A dalam urutan kamus
 * if A.compareTo(B) == 0 --> A dan B sama
 * if A.compareTo(B) < 0 --> A datang sebelum B dalam urutan kamus
 * 
 * - str.substring(startindex, endindex) --> memotong string
 * 
 * str.toUpperCase() --> Konversi ke upper case (kapital)
 * 
 * 
 * Terminal Runtime Documentation:
 * java-onboarding on  master [?] via ☕ v21.0.5 took 2s
 * ❯ java Soal5_Strings
 * hello
 * world
 * 10
 * No
 * Hello World
 * 
 * java-onboarding on  master [?] via ☕ v21.0.5 took 23s
 * ❯ java Soal5_Strings
 * minum
 * kopi
 * 9
 * Yes
 * Minum Kopi
 */
