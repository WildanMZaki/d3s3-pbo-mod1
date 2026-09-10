public class Soal2_Constants2 {
    public static final double CM_PER_INCH = 2.54;

    public static void main(String[] args) {
        double paperWidth = 8.5;
        double paperHeight = 11;
        System.out.println("Paper size in centimeters: " + paperWidth *
                CM_PER_INCH + " by " + paperHeight * CM_PER_INCH);
    }
}

// Dari 2 contoh block program diatas, jawablah pertanyaan dibawah ini:
// 1. Bagaimana output dari masing masing class Constants dan Constants2?
// 2. Apa perbedaan penggunaan final double dengan public static final double?
// 
// Jawaban:
// 1. Dari hasil uji coba, tidak ada perbedaan signifikan. Keduanya sama-sama
// menghasilkan output: "Paper size in centimeters: 21.59 by 27.94"
// Bukti Terminal Runtime:
// java-onboarding on master via v21.0.5 took 15s
// > javac Soal2_Constants.java
// 
// java-onboarding on master via v21.0.5
// > java Soal2_Constants
// Paper size in centimeters: 21.59 by 27.94
// 
// java-onboarding on master via v21.0.5
// > javac Soal2_Constants2.java
// 
// java-onboarding on master via v21.0.5
// > java Soal2_Constants2
// Paper size in centimeters: 21.59 by 27.94
// 
// 2. Perbedaan keduanya terletak pada scope access dari constanta tersebut.
// Di mana final double yang di-declare di main method itu hanya accessible di dalam
// scope method main itu saja
// Sementara untuk public static final double itu scope access-nya adalah sebagai
// class attribute di mana dia juga accessible di methods lain dalam satu class yang
// sama