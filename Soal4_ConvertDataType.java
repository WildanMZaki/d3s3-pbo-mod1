public class Soal4_ConvertDataType {
    static short methodOne(long l) {
        System.out.println("long = " + l);
        int i = (int) l;
        System.out.println("int casting = " + i);
        short s = (short) i;
        System.out.println("short casting = " + s);
        return s;
    }

    public static void main(String[] args) {
        double d = 33000.25;
        System.out.println("double = " + d);
        float f = (float) d;
        System.out.println("float casting = " + f);
        byte b = (byte) methodOne((long) f);
        System.out.println("byte casting = " + b);
        System.out.println("hasil casting akhir: " + b);
    }
}

/**
 * 1. Jelaskan output nilai dari variable b.
 * 2. Jelaskan apa yang berubah dari variable d menjadi variable b setelah
 * dilakukan cast ?
 * 
 * Jawaban:
 * 1. Nilai b adalah 10. Hal ini karena ketika melakukan konversi dari
 * bilangan desimal ke bilangan bulat
 * dengan casting secara langsung, maka bilangan desimal tersebut akan
 * dibulatkan
 * ke bawah.
 * 
 * Bukti Terminal Runtime:
 * java-onboarding on  master [?⇡] via ☕ v21.0.5
 * ❯ java Soal4_ConvertDataType
 * 10.25
 * 10
 * 
 * 2. Perubahan nilai d menjadi b dilakukan beberapa tahapan:
 * I. double d -> float f : pada tahapan ini tidak ada perubahan karena angka di
 * belakang koma
 * Masih muat dalam kapasitas penyimpanan float
 * 
 * II. float f -> long l : pada tahap ini terjadi casting desimal -> bil. bulat,
 * dibulatkan ke bawah
 * 
 * III. long l -> int i : terjadi kehilangan informasi pada saat konversi,
 * dimana long menyimpan nilai 32-bit
 * 
 * IV. int i -> short s : terjadi kehilangan informasi pada saat konversi,
 * dimana int menyimpan nilai 16-bit
 * 
 * V. short s -> byte b : terjadi kehilangan informasi pada saat konversi,
 * dimana short menyimpan nilai 8-bit
 * 
 * Tapi pada contoh 10.25 nilai tidak terlihat perubahan yang signifikan karena
 * nominal kecil.
 * 
 * Uji coba case lain:
 * ❯ java Soal4_ConvertDataType
 * double = 33000.25
 * float casting = 33000.25
 * long = 33000
 * int casting = 33000
 * short casting = -32536 --> melebihi nilai max short yaitu: 32767 sehingga sisanya menjadi overflow dan nilai akhir menjadi negatif
 * byte casting = -24 --> case serupa dengan short casting, tapi overflow terjadi berkali-kali sampai nominal akhirnya berada di -24
 * hasil casting akhir: -24
 * 
 */