import java.util.Scanner;
public class Soal1_NumberTypes {
    public static void main(String[] args) {
        // Scanner: Mekanisme untuk menerima input di program java
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }
        int t = scanner.nextInt();

        for (int i = 0; i < t; i++) {
            try {
                long x = scanner.nextLong();
                // Catatan: Input di sini diterima dalam bentuk tipe data long agar bisa memenuhi size tipe data yang ada di bawahnya
                System.out.println(x + " can be fitted in:");

                if (x >= Byte.MIN_VALUE && x <= Byte.MAX_VALUE) {
                    System.out.println("* byte");
                }
                if (x >= Short.MIN_VALUE && x <= Short.MAX_VALUE) {
                    System.out.println("* short");
                }
                if (x >= Integer.MIN_VALUE && x <= Integer.MAX_VALUE) {
                    System.out.println("* int");
                }
                if (x >= Long.MIN_VALUE && x <= Long.MAX_VALUE) {
                    System.out.println("* long");
                }
            } catch (Exception e) {
                // ketika angka yang diinputkan lebih besar dari max size of long,
                // maka kode akan melakukan throw exception yang kemmudian masuk ke block catch ini
                System.out.println(scanner.next() + " can't be fitted anywhere.");
            }
        }

        scanner.close();
    }
}
