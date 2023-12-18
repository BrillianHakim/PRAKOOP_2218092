/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package PRAKOOP_2218092;

import javax.swing.table.DefaultTableColumnModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WINDOWS 10
 */
public class MiniSoccerGui extends javax.swing.JFrame {
    private Connection conn;
    
    public MiniSoccerGui() {
        initComponents();
        koneksi();
        displayMiniSoccerData();
    }
    
    public void koneksi() {
    try {
        // Load the JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Setup the connection with the database
        String url = "jdbc:mysql://localhost/prakoop_2218092";
        String user = "root";
        String password = "";

        conn = DriverManager.getConnection(url, user, password);
        System.out.println("Connected to the database");
    } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(FutsalIndoorBesarGui.class.getName()).log(Level.SEVERE, null, ex);
        System.err.println("Error connecting to the database: " + ex.getMessage());
        ex.printStackTrace(); // Print the stack trace for detailed information
        }
    }
    
    public void displayMiniSoccerData() {
    DefaultTableModel dataModel = (DefaultTableModel) tabel.getModel();
    dataModel.setRowCount(0); // Clear existing rows

    try {
        System.out.println("Displaying Mini Soccer Data...");

        // No need to call koneksi() again, as it's called in the constructor

        // Assuming you have a table named 'ms', adjust the table name accordingly
        String query = "SELECT * FROM ms";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String namaPemesan = rs.getString("Nama Pemesan");
                int lamaWaktu = rs.getInt("Lama Menyewa");
                String jenisLapangan = rs.getString("Jenis Lapangan");

                System.out.println("Retrieved: " + namaPemesan + ", " + lamaWaktu + ", " + jenisLapangan);

                List<Object> row = new ArrayList<>();
                row.add(namaPemesan);
                row.add(lamaWaktu);
                row.add(jenisLapangan);

                dataModel.addRow(row.toArray());
            }

            System.out.println("Display completed.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    // Add this method to your class
    private void insertDataToDatabase(String namaPemesan, int lamaWaktu, String jenisLapangan) {
        // Assuming you have a table named 'ms', adjust the table name accordingly
        String insertQuery = "INSERT INTO ms (`Nama Pemesan`, `Lama Menyewa`, `Jenis Lapangan`) VALUES (?, ?, ?)";

        try {
            // Initialize the connection
            koneksi();

            // Create a prepared statement
            try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                pstmt.setString(1, namaPemesan);
                pstmt.setInt(2, lamaWaktu);
                pstmt.setString(3, jenisLapangan);

                // Execute the insert query
                pstmt.executeUpdate();
            }

            // Show a success message
            JOptionPane.showMessageDialog(this, "Data inserted successfully!");

            // Refresh the table
            displayMiniSoccerData();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error inserting data into the database");
        }
    }

    private void updateDataInDatabase(String namaPemesan, int lamaWaktu, String jenisLapangan, String selectedNamaPemesan) {
        // Assuming you have a table named 'ms', adjust the table name accordingly
        String updateQuery = "UPDATE ms SET `Nama Pemesan`=?, `Lama Menyewa`=?, `Jenis Lapangan`=? WHERE `Nama Pemesan`=?";

        try {
            // Initialize the connection
            koneksi();

            // Create a prepared statement
            try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
                pstmt.setString(1, namaPemesan);
                pstmt.setInt(2, lamaWaktu);
                pstmt.setString(3, jenisLapangan);
                pstmt.setString(4, selectedNamaPemesan); // Use the original namaPemesan for the WHERE clause

                // Execute the update query
                int rowsUpdated = pstmt.executeUpdate();

                // Check if any rows were updated
                if (rowsUpdated > 0) {
                    // Show a success message
                    JOptionPane.showMessageDialog(this, "Data updated successfully!");

                    // Refresh the table
                    displayMiniSoccerData();
                } else {
                    JOptionPane.showMessageDialog(this, "No data updated. Please check the entry.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating data in the database");
        }
    }
    
    private void deleteDataInDatabase(String selectedNamaPemesan) {
        // Assuming you have a table named 'ms', adjust the table name accordingly
        String deleteQuery = "DELETE FROM ms WHERE `Nama Pemesan`=?";

        try {
            // Initialize the connection
            koneksi();

            // Create a prepared statement
            try (PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
                pstmt.setString(1, selectedNamaPemesan); // Use the original namaPemesan for the WHERE clause

                // Execute the delete query
                int rowsDeleted = pstmt.executeUpdate();

                // Check if any rows were deleted
                if (rowsDeleted > 0) {
                    // Show a success message
                    JOptionPane.showMessageDialog(this, "Data deleted successfully!");

                    // Refresh the table
                    displayMiniSoccerData();
                    } else {
                    JOptionPane.showMessageDialog(this, "No data deleted. Please check the entry.");
                    }
                }
        }   catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting data from the database");
            }
    }
    
    private void search(String searchQuery) {
        // Assuming you have a table named 'ms', adjust the table name accordingly
        String searchSql = "SELECT * FROM ms WHERE `Nama Pemesan` LIKE ?";
        DefaultTableModel dataModel = (DefaultTableModel) tabel.getModel();
        dataModel.setRowCount(0); // Clear existing rows

        try {
            // Initialize the connection
            koneksi();

            // Create a prepared statement
            try (PreparedStatement pstmt = conn.prepareStatement(searchSql)) {
                pstmt.setString(1, "%" + searchQuery + "%");

                // Execute the search query
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        String namaPemesan = rs.getString("Nama Pemesan");
                        int lamaWaktu = rs.getInt("Lama Menyewa");
                        // Set jenisLapangan to "Lapangan S" by default
                        String jenisLapangan = "Lapangan S";

                        List<Object> row = new ArrayList<>();
                        row.add(namaPemesan);
                        row.add(lamaWaktu);
                        row.add(jenisLapangan);

                        dataModel.addRow(row.toArray());
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error searching data in the database");
        }
    }
    
    public void clear() {
    txt_pemesan.setText("");
    txt_lama.setText("");

}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_tombol = new javax.swing.JButton();
        txt_pemesan = new javax.swing.JTextField();
        txt_lama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nama Pemesan ");

        jLabel2.setText("Lama Menyewa");

        txt_tombol.setText("SUBMIT");
        txt_tombol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tombolActionPerformed(evt);
            }
        });

        txt_pemesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pemesanActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("MINI SOCCER");

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama Pemesan", "Lama Menyewa", "Jenis Lapangan"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel);

        jButton1.setText("HAPUS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("BATAL");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("CLOSE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton6.setText("Search 🔍");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton4.setText("UPDATE");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lapangan A", "Lapangan B" }));

        jLabel4.setText("Jenis Lapangan");

        jButton5.setText("Form Futsal Indoor Besar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1))
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_lama)
                            .addComponent(txt_pemesan)
                            .addComponent(jComboBox1, 0, 134, Short.MAX_VALUE))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton5)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txt_tombol)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton4))))
                        .addGap(31, 31, 31)))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_pemesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_lama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tombol)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_pemesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pemesanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_pemesanActionPerformed

    private void txt_tombolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tombolActionPerformed
        // Get data from the input fields
    String namaPemesan = txt_pemesan.getText();
    int lamaWaktu = Integer.parseInt(txt_lama.getText());
    String jenisLapangan = (String) jComboBox1.getSelectedItem(); // Get selected jenis lapangan

    // Insert data into the database
    insertDataToDatabase(namaPemesan, lamaWaktu, jenisLapangan);

    // Clear the input fields
    clear();
    }//GEN-LAST:event_txt_tombolActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Get the selected row's namaPemesan for deletion
    int selectedRow = tabel.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Select a row to delete.");
        return;
    }

    String selectedNamaPemesan = (String) tabel.getValueAt(selectedRow, 0);

    // Confirm deletion
    int confirmDialogResult = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete the selected data?",
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION);

    if (confirmDialogResult == JOptionPane.YES_OPTION) {
        // Delete data in the database
        deleteDataInDatabase(selectedNamaPemesan);

        // Clear the input fields
        clear();
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String searchQuery = jTextField4.getText().trim();
    if (!searchQuery.isEmpty()) {
        search(searchQuery);
    } else {
        JOptionPane.showMessageDialog(this, "Enter a search query.");
    }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // Get data from the input fields
        String namaPemesan = txt_pemesan.getText();
        int lamaWaktu = Integer.parseInt(txt_lama.getText());
        String jenisLapangan = (String) jComboBox1.getSelectedItem(); // Get selected jenis lapangan

        // Get the selected row's namaPemesan for updating
        int selectedRow = tabel.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to update.");
            return;
        }

        String selectedNamaPemesan = (String) tabel.getValueAt(selectedRow, 0);

        // Update data in the database
        updateDataInDatabase(namaPemesan, lamaWaktu, jenisLapangan, selectedNamaPemesan);

        // Clear the input fields
        clear();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        // Get the selected row index
        int rowIndex = tabel.getSelectedRow();

        // Retrieve data from the selected row
        String namaPemesan = tabel.getValueAt(rowIndex, 0).toString();
        String lamaWaktu = tabel.getValueAt(rowIndex, 1).toString();
        String jenisLapangan = tabel.getValueAt(rowIndex, 2).toString();

        // Set the retrieved data to the text fields
        txt_pemesan.setText(namaPemesan);
        txt_lama.setText(lamaWaktu);

        // Assuming you have a JComboBox named jComboBox1 for jenis lapangan
        // Set the selected item in the JComboBox
        jComboBox1.setSelectedItem(jenisLapangan);
    }//GEN-LAST:event_tabelMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        new FutsalIndoorBesarGui().setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(MiniSoccerGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MiniSoccerGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MiniSoccerGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MiniSoccerGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MiniSoccerGui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTable tabel;
    private javax.swing.JTextField txt_lama;
    private javax.swing.JTextField txt_pemesan;
    private javax.swing.JButton txt_tombol;
    // End of variables declaration//GEN-END:variables
}
