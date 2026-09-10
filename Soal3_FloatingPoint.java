public class Soal3_FloatingPoint {
    public static void main(String[] args) {
        double x = 92.98;
        int nx = (int) Math.round(x);

        System.out.println("x = " + x);
        System.out.println("nx = " + nx);
    }
}

/**
 * Math Class berisi bermacam-macam fungsi matematika seperti pada contoh diatas
 * pada
 * penggunaan round(x), terdapat beberapa pertanyaan yang perlu untuk
 * dijelaskan:
 * 1. Pada kasus berikut jelaskan nilai nx setelah digunakan Math.round(x);
 * 2. Kenapa dibutuhkan cast (int) dalam penggunaan Math.round(x) ?
 * 
 * Jawaban:
 * 1. Nilai nx setelah digunakan Math.round(x) adalah 93. Hal ini karena
 * Math.round(x)
 * melakukan pembulatan bilangan desimal ke bilangan bulat terdekat.
 * 
 * Karena ada round, maka sudah pasti ada 3 method standar lain dalam
 * pembulatan yaitu ceil dan floor.
 * .ceil -> membulatkan ke atas
 * .floor -> membulatkan ke bawah
 * 
 * 2. Karena method round mengembalikan nilai bertipe long, maka diperlukan
 * cast ke int agar dapat disimpan di variabel nx bertipe int.
 * 
 * Bisa dilihat ketika hover terhadap method round tersebut:
 * long java.lang.Math.round(double a) --> return type-nya adalah long, bisa dicast menjadi int
 * round
 * public static long round(double a)
 * Returns the closest long to the argument, with ties rounding to positive
 * infinity.
 * 
 * Special cases:
 * 
 * If the argument is NaN, the result is 0.
 * If the argument is negative infinity or any value less than or equal to the
 * value of Long.MIN_VALUE, the result is equal to the value of Long.MIN_VALUE.
 * If the argument is positive infinity or any value greater than or equal to
 * the value of Long.MAX_VALUE, the result is equal to the value of
 * Long.MAX_VALUE.
 * 
 * 
 */