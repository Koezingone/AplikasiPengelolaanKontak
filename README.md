# Aplikasi Pengelolaan Kontak

**Aplikasi Pengelolaan Kontak** adalah aplikasi Java sederhana yang memungkinkan pengguna untuk mengelola daftar kontak mereka. Aplikasi ini mendukung fitur-fitur seperti menambah, mengedit, menghapus, dan melihat data kontak. Data disimpan dalam database MySQL untuk mempermudah pengelolaan.

## Fitur Aplikasi

- Menampilkan daftar kontak yang tersimpan di database
- Menambahkan kontak baru dengan informasi lengkap
- Mengedit informasi kontak yang sudah ada
- Menghapus kontak dari database
- Mencari kontak berdasarkan nama atau kategori
- Antarmuka pengguna yang sederhana dan interaktif

## Komponen Utama

- **JFrame, JPanel, JLabel, JTextField, JTable, JButton** untuk membangun antarmuka pengguna
- **DefaultTableModel** untuk menampilkan data kontak di tabel
- **JDBC** untuk menghubungkan aplikasi dengan database MySQL
- Query SQL untuk operasi CRUD (Create, Read, Update, Delete)
- Penanganan **SQLException** untuk mengelola kesalahan saat berinteraksi dengan database

## Cara Menggunakan

1. Buka aplikasi dan daftar kontak akan ditampilkan di tabel.
2. Klik tombol **TAMBAH** untuk menambahkan kontak baru. Masukkan informasi yang diminta di formulir, lalu klik **SIMPAN**.
3. Pilih baris kontak yang ingin diubah, lalu klik tombol **EDIT**. Perbarui informasi di formulir, lalu klik **SIMPAN**.
4. Pilih baris kontak yang ingin dihapus, lalu klik tombol **HAPUS**.
5. Gunakan kotak pencarian untuk mencari kontak berdasarkan nama atau kategori.
6. Klik tombol **KELUAR** untuk menutup aplikasi.

## Struktur Database

- **Database**: `pbo_db`
- **Tabel**: `kontak`
  - `id` (int, AUTO_INCREMENT, Primary Key, NOT NULL)
  - `nama` (varchar(50), NOT NULL)
  - `noHp` (varchar(15), NOT NULL)
  - `alamat` (varchar(50), NOT NULL)
  - `gender` (varchar(9), NOT NULL)
  - `kategori` (varchar(20), NOT NULL)

## Authors

- **KHAIRUL ILHAM** - 2210010082 - 5C REG BJM

## Screenshots

![App Screenshot](https://github.com/Koezingone/AplikasiPengelolaanKontak/blob/main/img/Screen%20Recording%202024-11-24%20182352.gif)
