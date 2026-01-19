# ☕ WarkopIn - Aplikasi Pemesanan Warung Kopi Modern

[![Android Version](https://img.shields.io/badge/Android-7.0%2B%20(SDK%2024)-green.svg)](https://developer.android.com)
[![Build Status](https://img.shields.io/badge/Build-Success-brightgreen.svg)]()
[![Language](https://img.shields.io/badge/Language-Java-orange.svg)](https://www.java.com/)

**WarkopIn** adalah aplikasi mobile berbasis Android yang dirancang untuk memudahkan pelanggan dalam melakukan pemesanan makanan dan minuman di warung kopi secara digital. Dengan antarmuka yang modern dan intuitif, pengguna dapat menjelajahi menu, mengelola keranjang, hingga memantau status pesanan secara real-time.

---

## 🚀 Fitur Utama

Aplikasi ini dilengkapi dengan berbagai fitur fungsional untuk menunjang pengalaman pengguna:

*   **Sistem Autentikasi Lengkap**: 
    *   Registrasi akun baru & Login.
    *   Verifikasi menggunakan OTP (One-Time Password).
    *   Fitur Lupa Password untuk keamanan akun.
*   **Onboarding Screen**: Pengenalan singkat aplikasi kepada pengguna baru.
*   **Dashboard & Kategori**: Tampilan menu yang tertata rapi berdasarkan kategori tertentu.
*   **Manajemen Pesanan**:
    *   Detail Produk (Melihat deskripsi dan harga).
    *   Sistem Keranjang (Add to Cart).
*   **Riwayat Pesanan**:
    *   **Proses**: Pantau pesanan yang sedang disiapkan.
    *   **Selesai**: Lihat riwayat transaksi yang telah berhasil dilakukan.
*   **Profil Pengguna**: Pengaturan informasi akun pengguna.

---

## 🛠️ Dibangun Dengan (Tech Stack)

Proyek ini menggunakan teknologi terkini dalam pengembangan Android:

*   **Bahasa Pemrograman**: [Java](https://www.java.com/)
*   **Networking**: [Retrofit 2](https://square.github.io/retrofit/) & [Gson](https://github.com/google/gson) (Untuk komunikasi API yang efisien).
*   **Image Loading**: [Picasso](https://square.github.io/picasso/) (Untuk pemuatan gambar yang cepat dan caching).
*   **UI Design**: 
    *   Android Material Design Components.
    *   Constraint Layout untuk responsivitas UI.
*   **Minimum SDK**: API 24 (Android 7.0 Nougat).
*   **Target SDK**: API 34 (Android 14).

---

## ⚙️ Persiapan Pengembangan

Jika Anda ingin menjalankan atau mengembangkan proyek ini di lokal, ikuti langkah berikut:

1.  **Clone Repository**
    ```bash
    git clone https://github.com/rafifahrezi75/warkopin-apps
    ```
2.  **Buka di Android Studio**
    *   Pilih `Open an Existing Project`.
    *   Arahkan ke folder hasil clone.
3.  **Konfigurasi API**
    *   Buka file `ApiClient.java` di folder `retrofit`.
    *   Ubah `BASE_URL` sesuai dengan alamat server backend Anda:
      ```java
      private static final String BASE_URL = "http://ALAMAT_IP_ANDA:8000/api/";
      ```
4.  **Sync Gradle**
    *   Tunggu hingga proses sinkronisasi Gradle selesai.
5.  **Run**
    *   Klik ikon 'Run' atau tekan `Shift + F10`.

---

## 👨‍💻 Developer

*   **Muhammad Rafi Fahrezi** - [GitHub Profile](https://github.com/rafifahrezi75)

---
