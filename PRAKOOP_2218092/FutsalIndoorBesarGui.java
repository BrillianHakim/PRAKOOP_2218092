package PRAKOOP_2218092;

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

public class FutsalIndoorBesarGui extends javax.swing.JFrame {
    private Connection conn;

        public FutsalIndoorBesarGui() {
        initComponents();
        koneksi();
        displayFutsalData();
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

    
    public void displayFutsalData() {
    DefaultTableModel tableModel = new DefaultTableModel();
    tableModel.addColumn("Nama");
    tableModel.addColumn("Lama Menyewa");
    tableModel.addColumn("Tanggal Sewa");
    tableModel.addColumn("Loket");
    tableModel.addColumn("Jenis Lapangan");

    try {
        String sql = "SELECT * FROM fib";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String nama = resultSet.getString("Nama");
            int lamaWaktu = resultSet.getInt("Lama Waktu");
            String tanggal = resultSet.getString("Tanggal");
            String loket = resultSet.getString("Loket");
            String jenisLap = resultSet.getString("Jenis Lap.");

            tableModel.addRow(new Object[]{nama, lamaWaktu, tanggal, loket, jenisLap});
        }

        tabel_data_futsal.setModel(tableModel);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Failed to retrieve data from the database.");
        e.printStackTrace();
    }
}

    public void insert() {
    // Get data from the input fields
    String namaPenyewa = txt_nama.getText();
    int lamaPenyewa = Integer.parseInt(txt_menyewa.getText()); // Assuming lama penyewa is an integer
    String tanggalPenyewa = txtsewa.getText();
    
    // Default values for Loket and Jenis Lapangan
    String loket = "Loket B";
    String jenisLapangan = "Lapangan Besar";

    try {
        // Initialize the connection
        koneksi();

        // Create a statement
        Statement statement = conn.createStatement();

        // Execute the insert query with default values for Loket and Jenis Lapangan
        statement.executeUpdate("INSERT INTO fib (Nama, `Lama Waktu`, Tanggal, Loket, `Jenis Lap.`)"
                + "VALUES('" + namaPenyewa + "','" + lamaPenyewa + "','" + tanggalPenyewa + "','" + loket + "','" + jenisLapangan + "')");

        // Close the statement
        statement.close();

        // Show a success message
        JOptionPane.showMessageDialog(this, "Data inserted successfully!");

        // Refresh the table
        displayFutsalData();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error inserting data into the database");
    }
}
    
    public void update() {
    // Get data from the input fields
    String namaPenyewa = txt_nama.getText();
    int lamaPenyewa = Integer.parseInt(txt_menyewa.getText()); // Assuming lama penyewa is an integer
    String tanggalPenyewa = txtsewa.getText();

    // Default values for Loket and Jenis Lapangan (or fetch from somewhere based on your logic)
    String loket = "Loket A";
    String jenisLapangan = "Futsal A";

    try {
        // Initialize the connection
        koneksi();

        // Create a statement
        Statement statement = conn.createStatement();

        // Execute the update query
        statement.executeUpdate("UPDATE fib SET `Lama Waktu` = '" + lamaPenyewa + "', Tanggal = '" + tanggalPenyewa + "', Loket = '" + loket + "', `Jenis Lap.` = '" + jenisLapangan + "' WHERE Nama = '" + namaPenyewa + "'");

        // Close the statement
        statement.close();

        // Show a success message
        JOptionPane.showMessageDialog(this, "Data updated successfully!");

        // Refresh the table
        displayFutsalData();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error updating data in the database");
    }
}

    public void delete() {
    // Get the selected row index
    int selectedRowIndex = tabel_data_futsal.getSelectedRow();

    if (selectedRowIndex == -1) {
        JOptionPane.showMessageDialog(this, "Select a row to delete.");
        return;
    }

    // Get the value in the "Nama" column of the selected row
    String namaPenyewa = tabel_data_futsal.getValueAt(selectedRowIndex, 0).toString();

    try {
        // Initialize the connection
        koneksi();

        // Create a statement
        Statement statement = conn.createStatement();

        // Execute the delete query
        statement.executeUpdate("DELETE FROM fib WHERE Nama = '" + namaPenyewa + "'");

        // Close the statement
        statement.close();

        // Show a success message
        JOptionPane.showMessageDialog(this, "Data deleted successfully!");

        // Refresh the table
        displayFutsalData();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error deleting data from the database");
    }
}

    public void search(String query) {
    DefaultTableModel tableModel = new DefaultTableModel();
    tableModel.addColumn("Nama");
    tableModel.addColumn("Lama Menyewa");
    tableModel.addColumn("Tanggal Sewa");
    tableModel.addColumn("Loket");
    tableModel.addColumn("Jenis Lapangan");

    try {
        // Initialize the connection
        koneksi();

        // Create a statement
        Statement statement = conn.createStatement();

        // Execute the search query
        String sql = "SELECT * FROM fib WHERE Nama LIKE '%" + query + "%'";
        ResultSet resultSet = statement.executeQuery(sql);

        // Populate the table with search results
        while (resultSet.next()) {
            String nama = resultSet.getString("Nama");
            int lamaWaktu = resultSet.getInt("Lama Waktu");
            String tanggal = resultSet.getString("Tanggal");
            String loket = resultSet.getString("Loket");
            String jenisLap = resultSet.getString("Jenis Lap.");

            tableModel.addRow(new Object[]{nama, lamaWaktu, tanggal, loket, jenisLap});
        }

        // Set the updated model to the table
        tabel_data_futsal.setModel(tableModel);

        // Close the statement
        statement.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error searching data from the database.");
        e.printStackTrace();
    }
}

    // Add itempilih method
    public void itempilih(String nama, String loket, String jenisLapangan) {
        // Your implementation of itempilih() here
        // For example, you can set the values to text fields or perform other actions
        txt_nama.setText(nama);
        txt_menyewa.setText(loket);
        txtsewa.setText(jenisLapangan);
    }
    
    public void clear() {
    txt_nama.setText("");
    txt_menyewa.setText("");
    txtsewa.setText("");

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
        jLabel3 = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        txt_menyewa = new javax.swing.JTextField();
        txt_tombol = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_data_futsal = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtsewa = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("LAPANGAN FUTSAL BESAR");

        jLabel2.setText("Nama Penyewa ");

        jLabel3.setText("Lama Menyewa");

        txt_tombol.setText("SUBMIT");
        txt_tombol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tombolActionPerformed(evt);
            }
        });

        tabel_data_futsal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabel_data_futsal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_data_futsalMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_data_futsal);

        jLabel5.setText("Tanggal Menyewa");

        txtsewa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsewaActionPerformed(evt);
            }
        });

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

        jButton4.setText("UPDATE");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setText("Search 🔍");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton5.setText("Form Mini Soccer");

        jMenu1.setText("Home");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Form");

        jMenuItem1.setText("Futsal Kecil");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Mini Soccer");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtsewa, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_menyewa, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton5)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txt_tombol)
                                    .addGap(27, 27, 27)
                                    .addComponent(jButton1)
                                    .addGap(31, 31, 31)
                                    .addComponent(jButton2)
                                    .addGap(33, 33, 33)
                                    .addComponent(jButton3)
                                    .addGap(30, 30, 30)
                                    .addComponent(jButton4))))))
                .addContainerGap(159, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_menyewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtsewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tombol, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_tombolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tombolActionPerformed
        insert();
    }//GEN-LAST:event_txt_tombolActionPerformed

    private void txtsewaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsewaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsewaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       delete();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        new MiniSoccerGui().setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        new LapanganFutsalGui().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        update();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String searchQuery = jTextField4.getText(); // Assuming you have a text field for the search query
        search(searchQuery);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void tabel_data_futsalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_data_futsalMouseClicked
        int selectedRow = tabel_data_futsal.getSelectedRow();
    if (selectedRow != -1) {
        String nama = tabel_data_futsal.getValueAt(selectedRow, 0).toString();
        String loket = tabel_data_futsal.getValueAt(selectedRow, 1).toString();
        String jenisLapangan = tabel_data_futsal.getValueAt(selectedRow, 2).toString();
        
        // Call itempilih() with the selected values
        itempilih(nama, loket, jenisLapangan);
    }
    }//GEN-LAST:event_tabel_data_futsalMouseClicked

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
            java.util.logging.Logger.getLogger(FutsalIndoorBesarGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FutsalIndoorBesarGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FutsalIndoorBesarGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FutsalIndoorBesarGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FutsalIndoorBesarGui().setVisible(true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTable tabel_data_futsal;
    private javax.swing.JTextField txt_menyewa;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JButton txt_tombol;
    private javax.swing.JTextField txtsewa;
    // End of variables declaration//GEN-END:variables
}
