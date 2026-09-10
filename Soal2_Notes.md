# Catatan Pembelajaran: Soal2_Constants & Konsep OOP Java (dari Sudut Pandang PHP)

## 📌 Prompt Asli (Part 1)

> Bantu aku pelajarin yang Soal2_*
> Termasuk pembelajaran untuk ini simpan aja di Soal2_Notes.md aku perlu tau maksud per keyword. Oh iya include juga prompt ini di notes-nya ya.
> Dari yang aku pahamain terkait PBO seperti di PHP nih, public udah jelas itu terkati visibilitas di mana di php ada 3 level:
>
> - public -> accessible anywhere
> - protected -> accessible by the descendant (class-class yang mewarisi parent-nya)
> - private -> only accessible privately on it's own scope
>
> void atau double dah jelas itu datatype atau return type, kalau final tuh tuh const kan ya. Cuman bedanya di Cons1 dan 2 itu mungkin yang 1 scope dalam method main, sementara satu lagi sebagai attribute class ya?
>
> Dan karena dia jadi attribute, artinya dia accessible by other methods ketika perlu untuk di Cons2?
>
> Nah kalau static.. aku tau di php ini tuh dia jadi accessible pake :: entah apalah konsepnya ini gimana. Cuman yang jelas kalau di php, kalau static pake class::attribute, sementara yang no static bisa pake $this-> oh iya kalau di java gak ada konseep $this juga ya, keliatannya dia directly accessible.
> Kalau gitu, gimana kalau ada const dengan nama yang sama dalam scope attribute dan scope method?
>
> Oh iya kalau gak final, dia kalau attribute berarti bisa diubah juga kan?
>
> Hmm sementara itu yang aku pengen tau, kalau kamu mau tambahin Notes lain yang mungkin menarik buat aku ketahui sertakan di section selanjutnya

---

## 1. Q&A Interogatif Part 1 (Kutipan Pertanyaan & Jawaban Langsung)

### Pertanyaan 1

> *"kalau final tuh tuh const kan ya. Cuman bedanya di Cons1 dan 2 itu mungkin yang 1 scope dalam method main, sementara satu lagi sebagai attribute class ya?"*

**Jawaban: YES.**

- **Di [Soal2_Constants.java](<file:///d:/D3%20JTK/Semester%203/PBO/Tasks/java-onboarding/Soal2_Constants.java>)**: `final double CM_PER_INCH = 2.54;` berstatus sebagai **Local Variable / Local Constant** di dalam method `main`. Variabel ini hanya lahir dan hidup selama method `main` berjalan, dan tidak dikenal oleh method lain.
- **Di [Soal2_Constants2.java](<file:///d:/D3%20JTK/Semester%203/PBO/Tasks/java-onboarding/Soal2_Constants2.java>)**: `public static final double CM_PER_INCH = 2.54;` dideklarasikan langsung di badan class sebagai **Class Attribute / Field**.

---

### Pertanyaan 2

> *"Dan karena dia jadi attribute, artinya dia accessible by other methods ketika perlu untuk di Cons2?"*

**Jawaban: YES.**

- Karena posisinya di level class (terlebih ada keyword `public static`), seluruh method lain di dalam class tersebut bisa langsung memanggil `CM_PER_INCH`.
- Bahkan class lain di luar file ini juga bisa mengaksesnya secara global dengan memanggil `Soal2_Constants2.CM_PER_INCH`.

---

### Pertanyaan 3

> *"oh iya kalau di java gak ada konseep $this juga ya, keliatannya dia directly accessible."*

**Jawaban: NO (Java punya konsep `this`, tapi dilarang di dalam method `static`).**

- Di Java, keyword-nya adalah **`this`** (tanpa tanda dollar `$`, misal `this.lebar`).
- Alasan di kode `Soal2_Constants2` terlihat *directly accessible* (tanpa `this`) adalah karena method `main` bertipe **`static`**.
- **Aturan penting Java**: Di dalam method `static`, keyword `this` **TIDAK BISA DIGUNAKAN** (akan langsung error kompilasi). Karena `static` menempel pada Class, saat dijalankan belum tentu ada objek/instance di memori untuk dirujuk oleh `this`.
- Pada method non-static (instance method), kamu **bisa dan lumrah** menggunakan `this.namaProperty` (persis seperti `$this->` di PHP).

---

### Pertanyaan 4

> *"Kalau gitu, gimana kalau ada const dengan nama yang sama dalam scope attribute dan scope method?"*

**Jawaban: Bisa terjadi, dan dinamakan *Variable Shadowing* (variabel lokal menutupi variabel class).**

- Java tidak akan error jika nama variabelnya sama, tetapi variabel lokal di dalam method akan memprioritaskan nilainya sendiri dan "menyembunyikan" atribut class.

Contoh:

```java
public class ContohShadowing {
    public static final double CM_PER_INCH = 2.54; // Attribute class

    public static void main(String[] args) {
        double CM_PER_INCH = 10.0; // Local variable (menutupi attribute di atas)

        System.out.println(CM_PER_INCH); // Output: 10.0 (mengambil versi lokal)
      
        // Cara tetap mengakses versi attribute class yang tertutup:
        System.out.println(ContohShadowing.CM_PER_INCH); // Output: 2.54
    }
}
```

**Aturan Pemanggilan saat Terjadi Shadowing:**

1. Jika atribut class berstatus `static`: panggil dengan `NamaClass.NAMA_VARIABEL`.
2. Jika atribut class berstatus non-static: panggil dengan `this.namaVariabel`.

---

### Pertanyaan 5

> *"Oh iya kalau gak final, dia kalau attribute berarti bisa diubah juga kan?"*

**Jawaban: YES.**

- Tanpa `final`, variabel berstatus **mutable** (bisa diubah/ditimpa nilainya kapan saja setelah inisialisasi).

```java
public class Soal2_VariabelBiasa {
    public static double CM_PER_INCH = 2.54; // Tanpa final

    public static void main(String[] args) {
        CM_PER_INCH = 3.0; // Valid! Nilai berhasil diubah
        System.out.println(CM_PER_INCH); // Output: 3.0
    }
}
```

---

## 2. Bedah Maksud Per Keyword Secara Rinci

### 1. `public` (Access Modifier)

Di PHP ada 3 level visibilitas. Di Java ada **4 level visibilitas**:

| Modifier                   | Akses di Class Sendiri | Akses di Package yang Sama | Akses Subclass (Turunan) | Akses Dimana Saja (Public) |
| -------------------------- | :--------------------: | :------------------------: | :-----------------------: | :------------------------: |
| `public`                 |           Ya           |             Ya             |            Ya            |             Ya             |
| `protected`              |           Ya           |             Ya             |  Ya (meski beda package)  |           Tidak           |
| *(default / no keyword)* |           Ya           |    Ya (Package-Private)    | Tidak (jika beda package) |           Tidak           |
| `private`                |           Ya           |           Tidak           |           Tidak           |           Tidak           |

*Catatan untuk PHP Developer*: Di Java, jika kamu tidak menuliskan modifier apapun (misal: `double x = 10;`), level aksesnya otomatis menjadi **package-private** (hanya bisa diakses oleh class-class di folder/package yang sama).

### 2. `static` (Class-level Member)

- Menempel pada **Class**, bukan pada objek hasil instansiasi `new`.
- Di PHP: dipanggil dengan `ClassName::attribute` atau `self::$attribute`.
- Di Java: dipanggil dengan `ClassName.attribute` (misal: `Soal2_Constants2.CM_PER_INCH`). Jika dipanggil dari dalam class yang sama, bisa langsung ditulis namanya saja.

### 3. `final` (Konstanta / Immutability)

- Pada variabel: nilainya **tidak dapat diubah** setelah diinisialisasi (sama persis dengan `const` di PHP).
- *Bonus*: Di Java, `final` juga bisa disematkan pada:
  - **Method**: method tidak bisa di-*override* oleh subclass.
  - **Class**: class tidak bisa di-*extend* / diwariskan (mirip `final class` di PHP).

### 4. `void`

- Return type kosong. Menandakan method tersebut tidak mengembalikan nilai apa pun (`return;` tanpa nilai).

### 5. `double`

- Tipe data angka desimal (floating point) presisi ganda berukuran **64-bit**.

---

## 3. Notes Tambahan Menarik (PHP to Java)

### 1. Naming Convention untuk Konstanta

- Variabel biasa / method: `camelCase` (contoh: `paperWidth`, `hitungLuas()`).
- Class: `PascalCase` (contoh: `Soal2_Constants2`).
- Konstanta (`static final`): `UPPER_SNAKE_CASE` (huruf kapital semua dipisah underscore, contoh: `CM_PER_INCH`, `MAX_BUFFER_SIZE`).

### 2. Mengapa method utama selalu `public static void main(String[] args)`?

- `public`: JVM (Java Virtual Machine) harus bisa memanggil method ini dari luar package mana pun untuk memulai program.
- `static`: JVM tidak perlu membuat instance objek dari class tersebut hanya untuk menjalankan aplikasi.
- `void`: Program tidak menghasilkan nilai kembalian saat selesai.
- `String[] args`: Array argumen baris perintah (CLI arguments), identik dengan `$argv` di PHP!

### 3. Best Practice: `public static final` vs `private static final`

- Gunakan `public static final` jika konstanta ditujukan sebagai referensi umum untuk class-class lain (contoh: `Math.PI` di library Java).
- Gunakan `private static final` jika konstanta hanya digunakan sebagai logika internal class itu sendiri (menerapkan prinsip **Encapsulation**).

### 4. Tabel Padanan Sintaks PHP vs Java

| Konsep                | PHP                                           | Java                                               |
| --------------------- | --------------------------------------------- | -------------------------------------------------- |
| Cetak ke konsol       | `echo "Hello\n";`                           | `System.out.println("Hello");`                   |
| Konstanta Class       | `const CM_PER_INCH = 2.54;`                 | `public static final double CM_PER_INCH = 2.54;` |
| Akses Static Member   | `NamaClass::NAMA_CONST` atau `self::$var` | `NamaClass.NAMA_CONST`                           |
| Akses Instance Member | `$this->namaProperty`                       | `this.namaProperty`                              |
| Pewarisan Class       | `class B extends A`                         | `class B extends A`                              |
| Implement Interface   | `class B implements A`                      | `class B implements A`                           |

---

## 📌 Prompt Lanjutan (Part 2)

> Ok masukkin prompt ini di part selanjutnya lagi. Ini konfirmasi lagi doang sih:
> Kalau final class kan tidak bisa diwariskan, tapi artinya masih bisa kan mewarisi class non final, tapi si descendant (turunannya) bersifat final.
>
> Terus.. dosenku pernah bilang katanya kalau static di main dihapus pasti error, itu berarti karena compiler memang perlu static method main itu agar tidak usah membuat instance terlebih dahulu. Di mana kalau gak ada static harus dibuat instance dulu ya? Nah buat instance ini harusnya sama kan kek pemrograman lain di mana dia pake keyword New?
>
> Terus.. Kalau bisa instantiate, artinya harusnya sama juga dia pasti punya constructor kan? Gimana format dan mekanisme constructor di java? Apakah mirip mirip juga sama php tapi beda keyword?
>
> ---
>
> Ok terus ini konfirmasi dari soal yang ada di penugasan, dari output kedua case constant ini keliatannya sama sama aja ya. Berarti bedanya cuman ada di scope level ya. Dan ada pertanyaan di soal:
> Apa perbedaan penggunaan final double dengan public static final double?
>
> Ini berarti jawaban sesuai yang dibahas di sebelumnya yaitu bedanya ada di level akses di mana public static itu accessible di methods lain di class, sementara final double hanya hidup di scope method main berjalan

---

## 4. Q&A Interogatif Part 2 (Konfirmasi & Jawaban Lengkap)

### Pertanyaan 6: Final Class & Inheritance

> *"Kalau final class kan tidak bisa diwariskan, tapi artinya masih bisa kan mewarisi class non final, tapi si descendant (turunannya) bersifat final."*

**Jawaban: YES.**

- `final class` **bisa** mewarisi (`extends`) class lain yang non-final.
- `final class` berperan sebagai **daun terakhir (*leaf node*)** dalam pohon silsilah pewarisan. Dia bisa menjadi *subclass* (anak), namun tidak bisa lagi menjadi *parent* (tidak bisa memiliki turunan).
- Contoh:
  ```java
  class Hewan {
      void bernapas() { }
  }

  // VALID: Kucing mewarisi Hewan, tapi Kucing ditandai final
  final class Kucing extends Hewan {
      void mengeong() { }
  }

  // ERROR KOMPILASI: Anggora tidak diizinkan mewarisi Kucing!
  // class Anggora extends Kucing { }
  ```

  *(Sebagai referensi, class standar Java seperti `java.lang.String`, `Integer`, `Double`, dan `Math` semuanya dideklarasikan sebagai `final class` demi integritas dan keamanan sistem).*

---

### Pertanyaan 7: Kenapa `static` pada `main` Tidak Boleh Dihapus? & Keyword `new`

> *"Terus.. dosenku pernah bilang katanya kalau static di main dihapus pasti error, itu berarti karena compiler memang perlu static method main itu agar tidak usah membuat instance terlebih dahulu. Di mana kalau gak ada static harus dibuat instance dulu ya? Nah buat instance ini harusnya sama kan kek pemrograman lain di mana dia pake keyword New?"*

**Jawaban: YES (Secara konsep pemikiran sangat tepat! Catatan teknis: errornya terjadi di Runtime JVM, bukan di Compiler).**

- **Detail Teknis (Compiler vs JVM/Runtime)**:
  - Jika `static` dihapus: `public void main(String[] args)`, perintah compiler `javac Soal2_Constants.java` **akan tetap sukses** tanpa komplain karena secara sintaks Java itu dianggap method biasa yang sah.
  - Namun ketika kamu menjalankannya dengan `java Soal2_Constants`, **JVM (Java Virtual Machine) akan menolak dan melempar error**:
    `Error: Main method is not static in class Soal2_Constants, please define the main method as: public static void main(String[] args)`
- **Mengapa JVM mewajibkan `static`?**
  - Sebagai gerbang awal (*entry point*) program, JVM harus bisa memanggil method `main` langsung via nama class-nya. Jika non-static, JVM tidak tahu bagaimana cara menginstansiasi class tersebut (misal jika class punya constructor berparameter, JVM tidak tahu nilai apa yang harus diberikan).
- **Keyword `new` untuk Instantiate**:
  - **YES, sama persis!** Java menggunakan keyword `new` untuk membuat instance objek dari class ke dalam memori heap:
    ```java
    Soal2_Constants2 obj = new Soal2_Constants2();
    ```

---

### Pertanyaan 8: Format dan Mekanisme Constructor di Java

> *"Terus.. Kalau bisa instantiate, artinya harusnya sama juga dia pasti punya constructor kan? Gimana format dan mekanisme constructor di java? Apakah mirip mirip juga sama php tapi beda keyword?"*

**Jawaban: YES (pasti punya constructor, formatnya berbeda dari PHP, dan di Java ada fitur Overloading).**

Perbedaan format Constructor PHP vs Java:

1. **Tidak Memiliki Keyword Khusus**:
   - Di PHP: menggunakan method khusus bernama `function __construct(...)`.
   - Di Java: **namanya WAJIB persis sama dengan nama Class** dan **TIDAK ADA tipe kembalian (bahkan tidak memakai `void`)**.
2. **Default Constructor Otomatis**:
   - Jika kamu tidak menulis constructor sama sekali, compiler Java otomatis membuatkan satu *default constructor* kosong tanpa parameter (`public NamaClass() {}`).
3. **Constructor Overloading (Bisa Punya Banyak Constructor)**:
   - Di Java, satu class bisa memiliki lebih dari satu constructor asalkan parameter yang diterima berbeda-beda (*signature overloading*). Di PHP klasik fitur ini tidak ada secara native.

Contoh Format Constructor di Java:

```java
public class Kertas {
    private double lebar;
    private double tinggi;

    // Constructor 1: Default / Tanpa parameter
    public Kertas() {
        this.lebar = 8.5;
        this.tinggi = 11.0;
    }

    // Constructor 2: Dengan parameter (Overloading)
    public Kertas(double lebar, double tinggi) {
        this.lebar = lebar;
        this.tinggi = tinggi;
    }
}

// Pemanggilan saat instantiate:
Kertas k1 = new Kertas();              // memanggil Constructor 1
Kertas k2 = new Kertas(10.0, 15.0);    // memanggil Constructor 2
```

---

### Pertanyaan 9: Konfirmasi Jawaban Pertanyaan Tugas

> *"Ok terus ini konfirmasi dari soal yang ada di penugasan, dari output kedua case constant ini keliatannya sama sama aja ya. Berarti bedanya cuman ada di scope level ya. Dan ada pertanyaan di soal:*
> *Apa perbedaan penggunaan final double dengan public static final double?*
> *Ini berarti jawaban sesuai yang dibahas di sebelumnya yaitu bedanya ada di level akses di mana public static itu accessible di methods lain di class, sementara final double hanya hidup di scope method main berjalan"*

**Jawaban: YES, JAWABANMU 100% BENAR DAN TEPAT!**

Tampilan output kedua program memang identik di layar konsol, namun di balik layar terdapat perbedaan mendasar yang bisa dirangkum untuk jawaban tugasmu:

| Aspek Pembanding               | `final double` ([Soal2_Constants.java](<file:///d:/D3%20JTK/Semester%203/PBO/Tasks/java-onboarding/Soal2_Constants.java>))          | `public static final double` ([Soal2_Constants2.java](<file:///d:/D3%20JTK/Semester%203/PBO/Tasks/java-onboarding/Soal2_Constants2.java>))    |
| ------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------ | ---------------------------------------------------------------------------------------------------------------------------------------------- |
| **Klasifikasi Variabel** | **Local Variable (Local Constant)**                                                                                            | **Class Field (Global/Class Constant)**                                                                                                  |
| **Lokasi Deklarasi**     | Di dalam blok method (`main`).                                                                                                     | Di badan class (di luar semua method).                                                                                                         |
| **Scope Aksesibilitas**  | **Terbatas hanya di dalam method `main`** setelah baris deklarasi. Method lain dalam class tidak dapat melihat variabel ini. | **Dapat diakses oleh semua method** di dalam class tersebut, dan bahkan bisa diakses oleh class lain (`Soal2_Constants2.CM_PER_INCH`). |
| **Masa Hidup di Memori** | Bersifat sementara di memori**Stack** selama method `main` berjalan, dan langsung dibuang saat method selesai.               | Hidup di area memori**Class/Method Area** sejak class pertama kali dimuat oleh JVM hingga seluruh program berhenti.                      |
| **Tujuan Pemakaian**     | Digunakan jika nilai konstanta hanya berupa variabel bantu perhitungan sementara untuk satu method tertentu.                         | Digunakan untuk konstanta referensi bersama (*shared constant*) yang dibutuhkan oleh banyak method atau modul lain.                          |

---

## 📌 Prompt Lanjutan (Part 3)

> boleh masukkin prompt ini juga, kayaknya final buat soal2 ini Dari constructor format ini mirip dart ya.
>
> terus lagi-lagi, karena ini OOP konsep, salah satu konsep unik lain yang aku agak suka dari konsep ini adalah chaining methods, terus kalau ngeliat dia punya this dan self-nya sendiri, artinya udah pasti java juga support chaining methods ini ya. Cuman apakah ada perbedaan khusus?
>
> Sama.. apalagi ya.. paling ulas lagi dong konsep-konsep OOP buat review lagi di mana yang aku inget sekarang ada konsep Encapsulation, ini konsep untuk membuat beberapa data bersifat private, dan untuk mengaksesnya perlu method getter atau setter khusus, nah oh iya di dart seingetku ada mekanisme khusus untuk getter and setter, apakah di java juga?
> Terus ada konsep inheritance di mana ini mirip general to specific yang yah aku pahamlah konsep ini, sisanya ada apa lagi?

---

## 5. Q&A Interogatif Part 3 (Method Chaining, Dart vs Java, & 4 Pilar OOP)

### Pertanyaan 10: Kemiripan Constructor dengan Dart

> *"Dari constructor format ini mirip dart ya."*

**Jawaban: YES, TEPAT SEKALI.**

- Bahasa **Dart** memang banyak mengadopsi sintaks inti dari keluarga bahasa C++/Java. Di Dart, nama constructor juga menggunakan nama Class (`Kertas()`).
- *Perbedaannya*: Dart menambahkan fitur modern yang belum ada secara default di Java, seperti `Kertas(this.lebar, this.tinggi);` (shorthand langsung inisialisasi property) dan *Named Constructors* (`Kertas.custom(...)`). Di Java murni, kamu harus menuliskan penugasan `this.lebar = lebar;` secara manual di dalam badan constructor.

---

### Pertanyaan 11: Method Chaining di Java & Perbedaan Khususnya

> *"salah satu konsep unik lain yang aku agak suka dari konsep ini adalah chaining methods, terus kalau ngeliat dia punya this dan self-nya sendiri, artinya udah pasti java juga support chaining methods ini ya. Cuman apakah ada perbedaan khusus?"*

**Jawaban: YES (Java sangat mendukung Method Chaining, biasanya dikenal sebagai *Fluent Interface / Builder Pattern*).**

Mekanismenya di Java sama: setiap method yang ingin di-chaining harus mengembalikan instance objek itu sendiri via `return this;`.

Contoh di Java:

```java
public class KertasBuilder {
    private double lebar;
    private double tinggi;
    private String warna;

    public KertasBuilder setLebar(double lebar) {
        this.lebar = lebar;
        return this; // Mengembalikan instance objek saat ini
    }

    public KertasBuilder setTinggi(double tinggi) {
        this.tinggi = tinggi;
        return this;
    }

    public KertasBuilder setWarna(String warna) {
        this.warna = warna;
        return this;
    }
}

// Penggunaan Method Chaining:
KertasBuilder kertas = new KertasBuilder()
    .setLebar(8.5)
    .setTinggi(11.0)
    .setWarna("Putih");
```

**Perbedaan Khusus Java vs PHP / Dart pada Chaining:**

1. **Explicit Return Type**: Di Java, tipe kembalian method harus secara tegas dan kaku bertipe nama Class tersebut (`public KertasBuilder setLebar(...)`). Di PHP kamu bisa deklarasi `: self` atau `: static`.
2. **Tantangan saat Inheritance**: Jika class `KertasBuilder` di-`extends` oleh `SubKertasBuilder`, method parent yang mengembalikan tipe `KertasBuilder` akan memutus chain jika ingin memanggil method spesifik milik `SubKertasBuilder`. Di Java, ini diselesaikan dengan pola generic *Self-Referencing Generic* (`Builder<T extends Builder<T>>`).
3. **Penerapan Bawaan Java**: Di library standar Java, method chaining dipakai luas di:
   - `StringBuilder`: `.append("A").append("B").toString()`
   - `Stream API`: `list.stream().filter(...).map(...).toList()`

---

### Pertanyaan 12: Mekanisme Getter & Setter di Java vs Dart

> *"nah oh iya di dart seingetku ada mekanisme khusus untuk getter and setter, apakah di java juga?"*

**Jawaban: NO (Java standar TIDAK memiliki keyword khusus `get` / `set` seperti di Dart).**

- **Di Dart**: Ada keyword bahasa khusus `get` dan `set`:
  ```dart
  int get umur => _umur;
  set umur(int val) => _umur = val;
  // Dipanggil seperti atribut biasa tanpa tanda kurung:
  obj.umur = 20; 
  print(obj.umur);
  ```
- **Di Java Murni**: Getter dan Setter hanyalah **method publik biasa** yang mengikuti aturan standar (*JavaBeans Convention*):
  - Nama getter diawali kata `get` (atau `is` untuk boolean): `getLebar()`, `isAktif()`.
  - Nama setter diawali kata `set`: `setLebar(double lebar)`.
  - Pemanggilan **wajib menggunakan tanda kurung**:
    ```java
    obj.setLebar(10.0);
    double l = obj.getLebar();
    ```
- *Info Tambahan*:
  - Di ekosistem industri Java, developer sering memakai library **Lombok** (`@Getter`, `@Setter`) agar getter-setter digenerate otomatis di background tanpa menulis kodenya.
  - Di Java versi modern (Java 14+), ada fitur **`record`** yang otomatis membungkus data secara *immutable* lengkap dengan method accessor:
    ```java
    public record Kertas(double lebar, double tinggi) {}
    // Otomatis punya accessor bawaan: kertas.lebar(), kertas.tinggi()
    ```

---

### Pertanyaan 13: Review Lengkap 4 Pilar OOP (Object-Oriented Programming)

> *"paling ulas lagi dong konsep-konsep OOP buat review lagi di mana yang aku inget sekarang ada konsep Encapsulation... Terus ada konsep inheritance... sisanya ada apa lagi?"*

Dunia OOP berakar pada **4 Pilar Utama (A-P-I-E)**:

| Pilar                      | Konsep Utama                                                         | Kata Kunci / Wujud di Java                                          |
| -------------------------- | -------------------------------------------------------------------- | ------------------------------------------------------------------- |
| **1. Encapsulation** | Menyembunyikan data internal (*data hiding*) & melindungi akses.   | `private`, `protected`, `public`, `getter()`, `setter()`. |
| **2. Inheritance**   | Menurunkan sifat/metode dari parent ke child (*code reuse*).       | `extends`, `super`, `final`.                                  |
| **3. Polymorphism**  | Kemampuan memiliki banyak bentuk (perilaku berbeda).                 | Overloading (Compile-time) & Overriding (Runtime via`@Override`). |
| **4. Abstraction**   | Menyembunyikan detail kerumitan, menampilkan antarmuka penting saja. | `abstract class`, `interface`, `implements`.                  |

#### Rincian 4 Pilar:

#### 1. Encapsulation (Enkapsulasi)

- Menutup akses langsung ke variabel agar tidak dimanipulasi sembarangan dari luar.
- Validasi data diletakkan di dalam setter. Misalnya:
  ```java
  public void setTinggi(double tinggi) {
      if (tinggi <= 0) {
          throw new IllegalArgumentException("Tinggi harus lebih besar dari 0!");
      }
      this.tinggi = tinggi;
  }
  ```

#### 2. Inheritance (Pewarisan)

- Hubungan *IS-A* (misal: `Mobil` adalah `Kendaraan`).
- Child class mewarisi semua method dan atribut non-private dari parent.
- Keyword `super` digunakan untuk memanggil constructor atau method milik parent class: `super(lebar, tinggi);`.

#### 3. Polymorphism (Polimorfisme - Banyak Bentuk)

Terbagi menjadi 2 di Java:

1. **Method Overloading (Compile-Time / Static Polymorphism)**:
   - Nama method sama dalam 1 class, tetapi jumlah/tipe parameter berbeda.

   ```java
   void gambar() { }
   void gambar(String warna) { }
   void gambar(String warna, int ukuran) { }
   ```
2. **Method Overriding (Runtime / Dynamic Polymorphism)**:
   - Child class menimpa method yang diwariskan dari parent class.

   ```java
   class Hewan {
       void bersuara() { System.out.println("Suara hewan..."); }
   }
   class Kucing extends Hewan {
       @Override
       void bersuara() { System.out.println("Meow!"); }
   }
   ```

#### 4. Abstraction (Abstraksi)

- Memisahkan **APA yang harus dilakukan** dari **BAGAIMANA cara melakukannya**.
- Diwujudkan dalam 2 bentuk:

  1. **`abstract class`**: Blueprint kelas setengah jadi. Tidak bisa dibuat objeknya dengan `new`. Boleh punya method konkret dan method abstract.
  2. **`interface`**: Kontrak murni 100%. Class yang meng-implement interface wajib merealisasikan seluruh method kontrak tersebut.

  - *Kelebihan Interface*: Java tidak mendukung *multiple inheritance* class (`extends A, B` dilarang), namun Java **sangat mendukung multiple implementation** interface:
    ```java
    public class Printer implements CetakDokumen, PerangkatUSB, PerangkatJaringan {
        // Mengimplementasikan seluruh method dari ketiga interface di atas
    }
    ```
