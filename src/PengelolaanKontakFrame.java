/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author ACER
 */
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.util.Set;
import java.util.HashSet;



public class PengelolaanKontakFrame extends javax.swing.JFrame {

    /**
     * Creates new form PengelolaanKontakFrame
     */


    public PengelolaanKontakFrame() {
        initComponents();
        loadKontakList();
        loadTableData(); // memuat table data apabila aplikasi di jalankan
        PengelolaanKontakHelper.createTable();
        
        tblKontak.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) {
        int selectedRow = tblKontak.getSelectedRow();
        if (selectedRow != -1) {
            String nama = tblKontak.getValueAt(selectedRow, 0).toString();
            String telepon = tblKontak.getValueAt(selectedRow, 1).toString();
            String alamat = tblKontak.getValueAt(selectedRow, 2).toString();
            String gender = tblKontak.getValueAt(selectedRow, 3).toString();
            String kategori = tblKontak.getValueAt(selectedRow, 4).toString();

            // Isi data pada input form
            nameInput.setText(nama);
            handphoneInput.setText(telepon);
            alamatInput.setText(alamat);
            genderPilih.setSelectedItem(gender);
            kategoriPilih.setSelectedItem(kategori);
        }
    }
});

        btnClear.addActionListener(new java.awt.event.ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnClearActionPerformed(evt);
    }
});

    kontakList.addListSelectionListener(e -> {
    if (!e.getValueIsAdjusting()) {
        String selectedCategory = kontakList.getSelectedValue();
        System.out.println("Kategori yang dipilih: " + selectedCategory);  // Debug
        if (selectedCategory != null) {
            loadTableDataByCategory(selectedCategory); // Muat data berdasarkan kategori yang dipilih
        } else {
            loadTableData();  // Jika kategori tidak dipilih, tampilkan semua data
        }
    }
});

        
    }
    
    private void clearFields() { // Method Hapus
    nameInput.setText("");
    handphoneInput.setText("");
    alamatInput.setText("");
    genderPilih.setSelectedIndex(0);
    kategoriPilih.setSelectedIndex(0);
    loadTableData(); //Load ulang data setelah method ini dijalankan
    }
    
    private void loadTableData() {
    DefaultTableModel model = (DefaultTableModel) tblKontak.getModel();
    model.setRowCount(0);

    String sql = "SELECT nama, telepon, alamat, gender, kategori FROM kontak";
    try (Connection conn = PengelolaanKontakHelper.connect()) {
        if (conn == null) {
            JOptionPane.showMessageDialog(this, "Koneksi ke database gagal.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Menghentikan eksekusi jika koneksi gagal
        }

        try (java.sql.Statement stmt = conn.createStatement();
             java.sql.ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("nama"),
                    rs.getString("telepon"),
                    rs.getString("alamat"),
                    rs.getString("gender"),
                    rs.getString("kategori")
                });
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal memuat data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void loadTableDataByCategory(String category) {
     DefaultTableModel model = (DefaultTableModel) tblKontak.getModel();
    model.setRowCount(0); // Reset tabel setiap kali memuat data baru

    String sql = "SELECT nama, telepon, alamat, gender, kategori FROM kontak WHERE kategori = ?";
    boolean dataFound = false; // Untuk memeriksa apakah data ditemukan
    try (Connection conn = PengelolaanKontakHelper.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, category); // Set kategori yang dipilih sebagai parameter
        System.out.println("Kategori yang digunakan dalam query: " + category);  // Debugging log
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("nama"),
                    rs.getString("telepon"),
                    rs.getString("alamat"),
                    rs.getString("gender"),
                    rs.getString("kategori")
                });
                dataFound = true; // Menandakan ada data yang ditemukan
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal memuat data berdasarkan kategori: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    // Tampilkan pesan jika tidak ada data ditemukan
    if (!dataFound) {
        JOptionPane.showMessageDialog(this, "Kontak tidak ditemukan untuk kategori tersebut.", "Peringatan", JOptionPane.INFORMATION_MESSAGE);
    }
}

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nameInput = new javax.swing.JTextField();
        handphoneInput = new javax.swing.JTextField();
        alamatInput = new javax.swing.JTextField();
        genderPilih = new javax.swing.JComboBox<>();
        kategoriPilih = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKontak = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        cariInput = new javax.swing.JTextField();
        simpanButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        hapusButton = new javax.swing.JButton();
        tambahButton = new javax.swing.JButton();
        eksporButton = new javax.swing.JButton();
        imporButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        kontakList = new javax.swing.JList<>();
        cariButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btnKeluar = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(153, 51, 255)), "APLIKASI PENGELOLAAN KONTAK", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Nama ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("No. HP ");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Alamat ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Kategori ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Gender ");

        alamatInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alamatInputActionPerformed(evt);
            }
        });

        genderPilih.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-Laki", "Perempuan" }));

        kategoriPilih.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Keluarga", "Teman", "Kerja" }));

        tblKontak.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama", "Telephone", "Alamat", "Gender", "Kategori"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblKontak);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("CARI");

        simpanButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        simpanButton.setText("SIMPAN");
        simpanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanButtonActionPerformed(evt);
            }
        });

        editButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        editButton.setText("EDIT");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        hapusButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        hapusButton.setText("HAPUS");
        hapusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusButtonActionPerformed(evt);
            }
        });

        tambahButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tambahButton.setText("TAMBAH");
        tambahButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahButtonActionPerformed(evt);
            }
        });

        eksporButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        eksporButton.setText("EKSPOR");
        eksporButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eksporButtonActionPerformed(evt);
            }
        });

        imporButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        imporButton.setText("IMPOR");
        imporButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imporButtonActionPerformed(evt);
            }
        });

        kontakList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kontakListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(kontakList);

        cariButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cariButton.setText("CARI");
        cariButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariButtonActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("DAFTAR KONTAK :");

        btnKeluar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnKeluar.setText("KELUAR");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClear.setText("CLEAR");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(tambahButton)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(nameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(handphoneInput, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(alamatInput, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(genderPilih, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(kategoriPilih, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(editButton)
                        .addGap(48, 48, 48)
                        .addComponent(simpanButton)
                        .addGap(50, 50, 50)
                        .addComponent(hapusButton)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cariInput, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cariButton)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(imporButton)
                                .addGap(303, 303, 303))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(eksporButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                                .addComponent(btnKeluar)
                                .addGap(99, 99, 99))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cariInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cariButton)
                    .addComponent(btnClear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(handphoneInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(alamatInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(genderPilih, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(kategoriPilih, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editButton)
                    .addComponent(tambahButton)
                    .addComponent(simpanButton)
                    .addComponent(hapusButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(imporButton)
                        .addGap(124, 124, 124)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eksporButton)
                            .addComponent(btnKeluar)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(77, 77, 77))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void alamatInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alamatInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alamatInputActionPerformed

    private void simpanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanButtonActionPerformed
    String noTelp = handphoneInput.getText().trim();
    int minLength = 10; // Panjang minimal no.telp
    int maxLength = 15; // Panjang maksimal no.telp

    // Cek apabila no.telp sudah benar panjangnya
    if (noTelp.length() < minLength || noTelp.length() > maxLength) {
        JOptionPane.showMessageDialog(
            null,
            "Nomor telpon harus diantara " + minLength + " digit dan " + maxLength + " digit.",
            "Nomor Telepon Tidak Valid.",
            JOptionPane.WARNING_MESSAGE
        );
        return; // Stop proses selanjutnya
    }
    // Validasi agar hanya angka yang bisa dimasukkan
    if (!noTelp.matches("\\d+")) { // Regex untuk memeriksa hanya angka
        JOptionPane.showMessageDialog(
            null,
            "Nomor telepon hanya boleh berisi angka.",
            "Nomor Telepon Tidak Valid.",
            JOptionPane.WARNING_MESSAGE
        );
        return;
    }


    String nama = nameInput.getText();
    String telepon = handphoneInput.getText();
    String alamat = alamatInput.getText();
    String gender = genderPilih.getSelectedItem().toString();
    String kategori = kategoriPilih.getSelectedItem().toString();

    // Jika semua field atau salah satunya ada yang kosong
    if (nama.isEmpty() || telepon.isEmpty() || kategori.equals("(Pilih Kategori Disini)")) {
        JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Memasukkan data kontak yang sudah di input
    String sql = "INSERT INTO kontak (nama, telepon, alamat, gender, kategori) VALUES (?, ?, ?, ?, ?)";
    try (Connection conn = PengelolaanKontakHelper.connect();
         java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, nama);
        pstmt.setString(2, telepon);
        pstmt.setString(3, alamat);
        pstmt.setString(4, gender);
        pstmt.setString(5, kategori);
        pstmt.executeUpdate();

        JOptionPane.showMessageDialog(this, "Kontak berhasil disimpan.");
        clearFields(); // Method untuk mengosongkan input field
        loadKontakList(); // Memuat ulang JList setelah data ditambahkan
        loadTableData();  // Memuat ulang data di table
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan pada saat penyimpanan kontak: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_simpanButtonActionPerformed

    private void tambahButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahButtonActionPerformed
    nameInput.setText("");
    handphoneInput.setText("");
    alamatInput.setText("");
    genderPilih.setSelectedIndex(0); // Default memilih item pertama
    kategoriPilih.setSelectedIndex(0); // Default memilih item pertama
    nameInput.requestFocus(); // Fokus pada input nama
    }//GEN-LAST:event_tambahButtonActionPerformed

    private void eksporButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eksporButtonActionPerformed
    // Set untuk menyimpan data unik
    Set<String> exportedData = new HashSet<>();

    // Periksa apakah ada baris yang dipilih di JTable
    int[] selectedRows = tblKontak.getSelectedRows();

    // Periksa apakah ada elemen yang dipilih di JList
    String selectedNameFromList = kontakList.getSelectedValue();

    if (selectedRows.length == 0 && selectedNameFromList == null) {
        JOptionPane.showMessageDialog(this, "Pilih kontak yang ingin diekspor!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Buka JFileChooser untuk memilih lokasi penyimpanan
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Simpan sebagai CSV");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
    fileChooser.setFileFilter(filter);

    int userSelection = fileChooser.showSaveDialog(this);
    if (userSelection == JFileChooser.APPROVE_OPTION) {
        File fileToSave = fileChooser.getSelectedFile();

        // Jika ekstensi file tidak ditentukan, tambahkan .csv secara otomatis
        if (!fileToSave.getName().endsWith(".csv")) {
            fileToSave = new File(fileToSave.getAbsolutePath() + ".csv");
        }

        try (FileWriter writer = new FileWriter(fileToSave)) {
            // Tulis header kolom
            writer.append("Nama,Telepon,Alamat,Gender,Kategori\n");

            // Jika ada baris yang dipilih di JTable
            if (selectedRows.length > 0) {
                for (int row : selectedRows) {
                    StringBuilder rowData = new StringBuilder();
                    for (int col = 0; col < tblKontak.getColumnCount(); col++) {
                        Object value = tblKontak.getValueAt(row, col);
                        rowData.append(value != null ? value.toString() : "");
                        if (col < tblKontak.getColumnCount() - 1) rowData.append(",");
                    }
                    if (exportedData.add(rowData.toString())) {
                        writer.append(rowData).append("\n");
                    }
                }
            }

            // Jika ada elemen yang dipilih di JList
            if (selectedNameFromList != null) {
                Connection conn = PengelolaanKontakHelper.connect();
                String sql = "SELECT * FROM kontak WHERE nama = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, selectedNameFromList);
                    try (var rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            String rowData = rs.getString("nama") + "," +
                                             rs.getString("telepon") + "," +
                                             rs.getString("alamat") + "," +
                                             rs.getString("gender") + "," +
                                             rs.getString("kategori");
                            if (exportedData.add(rowData)) {
                                writer.append(rowData).append("\n");
                            }
                        }
                    }
                }
            }

            JOptionPane.showMessageDialog(this, "Data berhasil diekspor ke: " + fileToSave.getAbsolutePath());
        } catch (IOException | SQLException e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat mengekspor data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_eksporButtonActionPerformed

    private void hapusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusButtonActionPerformed
    // Periksa apakah ada baris yang dipilih di tabel
    int selectedRow = tblKontak.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Pilih kontak yang ingin dihapus!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Konfirmasi penghapusan
    int response = JOptionPane.showConfirmDialog(this, "Apakah anda yakin ingin menghapus kontak ini?", "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.YES_OPTION) {
        String nama = tblKontak.getValueAt(selectedRow, 0).toString(); // Ambil nama dari tabel

        // Query untuk menghapus data dari database
        String sql = "DELETE FROM kontak WHERE nama = ?";
        try (Connection conn = PengelolaanKontakHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nama);
            pstmt.executeUpdate();

            // Berhasil menghapus
            JOptionPane.showMessageDialog(this, "Kontak berhasil dihapus.");

            // Perbarui JTable
            loadTableData();

            // Perbarui JList
            DefaultListModel<String> listModel = (DefaultListModel<String>) kontakList.getModel();
            listModel.removeElement(nama); // Hapus nama dari JList
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menghapus kontak: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_hapusButtonActionPerformed

    private void kontakListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kontakListMouseClicked
    String selectedName = kontakList.getSelectedValue();  // Mendapatkan nama yang dipilih dari kontakList
    if (selectedName != null) {  // Pastikan ada nama yang dipilih
        try (Connection conn = PengelolaanKontakHelper.connect()) {
            // Query untuk mengambil data berdasarkan nama
            String query = "SELECT * FROM kontak WHERE kategori = ?";
            var ps = conn.prepareStatement(query);
            ps.setString(1, selectedName);  // Set nama yang dipilih sebagai parameter
            var rs = ps.executeQuery();

            if (rs.next()) {
                // Isi data pada input form
                nameInput.setText(rs.getString("nama"));
                handphoneInput.setText(rs.getString("telepon"));
                alamatInput.setText(rs.getString("alamat"));
                genderPilih.setSelectedItem(rs.getString("gender"));
                kategoriPilih.setSelectedItem(rs.getString("kategori"));
            } else {
                JOptionPane.showMessageDialog(this, "Kontak tidak ditemukan.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data kontak: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_kontakListMouseClicked

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
    int selectedRow = tblKontak.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Pilih kontak yang ingin diedit!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String nama = nameInput.getText();
    String telepon = handphoneInput.getText();
    String alamat = alamatInput.getText();
    String gender = genderPilih.getSelectedItem().toString();
    String kategori = kategoriPilih.getSelectedItem().toString();
    String oldName = tblKontak.getValueAt(selectedRow, 0).toString();
    
    // Warning muncul jika semua textfield kosong atau salah satunya
    if (nama.isEmpty() || telepon.isEmpty() || kategori.equals("(Pilih Kategori Disini)")) {
        JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Memulai proses update kontak
    String sql = "UPDATE kontak SET nama = ?, telepon = ?, alamat = ?, gender = ?, kategori = ? WHERE nama = ?";
    try (Connection conn = PengelolaanKontakHelper.connect();
         java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, nama);
        pstmt.setString(2, telepon);
        pstmt.setString(3, alamat);
        pstmt.setString(4, gender);
        pstmt.setString(5, kategori);
        pstmt.setString(6, oldName);
        pstmt.executeUpdate();

        JOptionPane.showMessageDialog(this, "Kontak berhasil diperbarui.");
        clearFields(); //Method hapus
        loadTableData(); // Memuat ulang data setelah di update
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan pada pembaharuan kontak: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_editButtonActionPerformed

    private void cariButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariButtonActionPerformed
    String searchQuery = cariInput.getText().trim(); // Ambil input pencarian

    if (searchQuery.isEmpty()) { // Jika input pencarian kosong
        JOptionPane.showMessageDialog(this, "Masukkan nama atau nomor telepon untuk mencari!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Memulai pencarian berdasarkan nama atau nomor telepon
    DefaultTableModel model = (DefaultTableModel) tblKontak.getModel();
    model.setRowCount(0); // Reset tabel sebelum memasukkan hasil pencarian

    String sql = "SELECT nama, telepon, alamat, gender, kategori FROM kontak WHERE nama LIKE ? OR telepon LIKE ?";
    try (Connection conn = PengelolaanKontakHelper.connect();
         java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, "%" + searchQuery + "%"); // Pencarian berdasarkan nama
        pstmt.setString(2, "%" + searchQuery + "%"); // Pencarian berdasarkan nomor telepon
        java.sql.ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("nama"),
                rs.getString("telepon"),
                rs.getString("alamat"),
                rs.getString("gender"),
                rs.getString("kategori")
            });
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal mencari kontak: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_cariButtonActionPerformed

    private void imporButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imporButtonActionPerformed
    // Membuka JFileChooser untuk memilih file CSV
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Pilih File CSV untuk Diimpor");
    int userSelection = fileChooser.showOpenDialog(this);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
        File fileToImport = fileChooser.getSelectedFile();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileToImport))) {
            String line;
            // Lewati header jika ada
            reader.readLine();

            // Membuat koneksi sekali
            try (Connection conn = PengelolaanKontakHelper.connect();
                 PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO kontak (nama, telepon, alamat, gender, kategori) VALUES (?, ?, ?, ?, ?)")) {

                conn.setAutoCommit(false); // Optimalkan dengan transaksi batch
                int rowCount = 0;

                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length != 5) {
                        JOptionPane.showMessageDialog(this, 
                            "Format data tidak sesuai di baris: " + (rowCount + 2), // Header dianggap baris 1
                            "Error", JOptionPane.ERROR_MESSAGE);
                        continue;
                    }

                    pstmt.setString(1, data[0].trim()); // nama
                    pstmt.setString(2, data[1].trim()); // noHp
                    pstmt.setString(3, data[2].trim()); // alamat
                    pstmt.setString(4, data[3].trim()); // gender
                    pstmt.setString(5, data[4].trim()); // kategori

                    pstmt.addBatch(); // Tambahkan ke batch
                    rowCount++;
                }

                pstmt.executeBatch(); // Eksekusi batch
                conn.commit(); // Commit transaksi

                JOptionPane.showMessageDialog(this, rowCount + " baris data berhasil diimpor!");
                loadTableData(); // Muat ulang JTable
                loadKontakList(); // Muat ulang JList
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Kesalahan membaca file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Kesalahan database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    }//GEN-LAST:event_imporButtonActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
    System.exit(0);
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
    // Hapus teks pada kolom pencarian
    cariInput.setText("");

    // Muat ulang data dari database tanpa filter pencarian
    loadTableData();
    }//GEN-LAST:event_btnClearActionPerformed

    private void loadKontakList() {
    DefaultListModel<String> listModel = new DefaultListModel<>();
    String sql = "SELECT DISTINCT kategori FROM kontak";  // Query untuk mengambil kategori unik
    try (Connection conn = PengelolaanKontakHelper.connect();
         java.sql.Statement stmt = conn.createStatement();
         java.sql.ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            listModel.addElement(rs.getString("kategori"));  // Menambahkan kategori ke dalam model list
        }

        // Menghubungkan DefaultListModel ke kontakList
        kontakList.setModel(listModel);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal memuat data kategori: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PengelolaanKontakFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PengelolaanKontakFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PengelolaanKontakFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PengelolaanKontakFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PengelolaanKontakFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField alamatInput;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton cariButton;
    private javax.swing.JTextField cariInput;
    private javax.swing.JButton editButton;
    private javax.swing.JButton eksporButton;
    private javax.swing.JComboBox<String> genderPilih;
    private javax.swing.JTextField handphoneInput;
    private javax.swing.JButton hapusButton;
    private javax.swing.JButton imporButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> kategoriPilih;
    private javax.swing.JList<String> kontakList;
    private javax.swing.JTextField nameInput;
    private javax.swing.JButton simpanButton;
    private javax.swing.JButton tambahButton;
    private javax.swing.JTable tblKontak;
    // End of variables declaration//GEN-END:variables
}
