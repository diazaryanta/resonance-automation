# 🚀 Resonance Test Automation

Proyek ini dikerjakan untuk melakukan pengujian pada website **[UI Resonance Dibimbing](https://resonance.dibimbing.id)** dan **[API Resonance Dibimbing](https://resonance.dibimbing.id/docs)**. 
Proyek ini dilakukan untuk memenuhi syarat assignment **mini project dibimbing day 30**. Silahkan klik link berikut untuk **[QA Report](https://docs.google.com/document/d/1kibfIHHLxVoJFqTDHcdJNVpt6RMHYw4n-PTieKEPh5Y/edit?usp=sharing)**.

## 🛠️ Teknologi & *Library* yang Dipakai

* **Bahasa Pemrograman:** Java 21 **(Wajib JDK 17 atau lebih baru)**
* **Build Tool:** Gradle
* **Testing Framework:** TestNG
* **API Testing:** Rest Assured
* **UI Testing:** Selenium WebDriver & WebDriverManager
* **Reporting:** Extent Reports

## 📁 Struktur Proyek

Biar kodenya rapi dan gampang dibaca, proyek ini dibangun pakai konsep **Page Object Model (POM)**.

```text
resonance-automation/
├── build.gradle                 # Konfigurasi library dan dependencies (Rest Assured, Selenium, dll)
├── reports/
│   ├── screenshots/             # Tempat nyimpen bukti screenshot kalau ada test UI yang fail
│   ├── ExtentReport.html        # File HTML buat lihat hasil report lengkap (Pass/Fail)
├── src/
│   ├── main/java/
│   │   ├── API/                 # Tempat nyimpen logic, endpoint, dan payload API
│   │   │   ├── auth/            # Endpoint & Payload khusus Login
│   │   │   ├── dashboard/       # Endpoint & Payload khusus Tiket
│   │   │   └── utils/           # DataUtility (Tempat nyimpen Token & Cookies sementara)
│   │   └── UI/                  # Struktur POM untuk UI Automation
│   │       ├── core/            # Setup WebDriver (DriverManager, BasePage)
│   │       ├── Dashboard/       # Nyimpen elemen/locator UI halaman Tiket
│   │       └── Login/           # Nyimpen elemen/locator UI halaman Login
│   └── test/java/
│       ├── API/                 # File untuk nge-run test skenario API
│       │   ├── base/            # BaseTest (Setup URL Utama API)
│       │   └── tests/           # Skenario test API (contoh: LoginTest, TicketTest)
│       └── UI/                  # File untuk nge-run test skenario UI
│           ├── base/            # Setup logic buat report
│           ├── tests/           # Skenario test UI (contoh: LoginTest & TicketTest)
│           └── utils/           # Konfigurasi ExtentManager buat bikin laporan
└── src/test/resources/suites/   # File XML TestNG buat nge-run banyak test sekaligus (batch)
```

## ⚙️ Persiapan Awal (Prerequisites)

Sebelum mulai nge-run proyek ini, pastikan sudah menginstall *tools* di bawah ini:
1.  **Java Development Kit (JDK):** Versi 17, atau 21.
2.  **IDE:** IntelliJ IDEA (Sangat direkomendasikan).
3.  **Koneksi Internet:** Wajib ada, karena pengetesan langsung ke server *live* Resonance.

## 🚀 Cara Menjalankan Test

Code yang dibuat udah diatur supaya mendukung **Auto-Login (Otentikasi Dinamis)**. Jadi, nggak perlu repot-repot nge-run *script* login terpisah setiap kali mau ngetest fitur lain. Token dan Cookies-nya udah diurus otomatis!

### Opsi 1: Lewat IntelliJ IDEA
1. Buka file skenario test yang mau kamu jalanin. Misalnya: `src/test/java/API/tests/TicketTest.java` atau file di UI.
2. Cari ikon **Play (Segitiga Hijau)** di sebelah kiri tulisan nama *class* atau *method* (contoh: `@Test public void testGetTicketByIdValid()`).
3. Klik ikon tersebut lalu pilih **Run 'TicketTest...'**.
4. Hasil test dan log *response* dari server bakal muncul di tab *Console/Run* di bagian bawah layarmu.

### Opsi 2: Lewat Terminal (Pake Gradle)
Kalau mau nge-run semua test sekaligus lewat file konfigurasi XML:

```bash
# Untuk jalanin Regression Test
./gradlew clean test -Dtestng.suiteXmlFiles=src/test/resources/suites/testng.xml
```

## 🧠 Fitur Utama & Troubleshooting

* **Keamanan API (NextAuth):** Server Resonance itu pakai sistem keamanan *NextAuth* yang lumayan ketat. Nah, test API di proyek ini udah diakalin biar selalu nangkep dan ngirim **Bearer Token** plus **Session Cookies** (`__Secure-next-auth.session-token`) secara otomatis lewat class `DataUtility.java`. Kalau kena *401 Unauthorized*, biasanya masalahnya ada di *Cookies* yang *expired*.
* **API Chaining (Dinamis):** Untuk skenario ngambil tiket pakai ID, agar testnya lebih dinamis. Script bakal otomatis *create* tiket baru dulu buat dapetin ID yang pasti valid (bakal dapet `200 OK`). Nanti tinggal dilanjutin aja dengan ID ngasal kalau mau ngetest skenario `404 Not Found`.
* **Password Leak Detection:** Fitur keamanan anti pop-up, Dengan mengatur `profile.password_manager_leak_detection` ke `false` memerintahkan Chrome untuk tidak mengecek kebocoran password selama sesi automation berlangsung.
* **Auto Generate Token and Cookies:** 

## 📈 Cara Lihat Report
Setelah test selesai dijalankan lewat Terminal, sistem akan otomatis bikin laporan yang rapi.
* **Laporan Bawaan Gradle:** Bisa kamu buka di `build/reports/problems/problems-report.html`.
* **Laporan UI ExtentReports (Lebih Lengkap & Cakep):** Bisa kamu buka file `reports/ExtentReport.html` di browser.