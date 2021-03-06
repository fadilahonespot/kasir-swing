import Config.Koneksi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author NB01
 */
public class FormLaporanPenjualan extends javax.swing.JFrame {

    /**
     * Creates new form FormHistory
     */
    Date dateFrom;
    Date dateTo;

    public FormLaporanPenjualan() {
        initComponents();

        setHeaderHistoryDetail();
        tampilHistory(dateFrom, dateTo);
    }

    private void tampilHistory(Date dateFrom, Date dateTo) {
        String tanggalDari;
        String tanggalKe;

        LocalDateTime timeNow = LocalDateTime.now();
        LocalDateTime dateFromLocal = timeNow.minusDays(0);
        LocalDateTime dateToLocal = timeNow;

        if (dateFrom != null && dateTo != null) {
            dateFromLocal = dateFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            dateToLocal = dateTo.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }

        DefaultTableModel data = new DefaultTableModel();
        data.addColumn("Order Number");
        data.addColumn("Total");
         data.addColumn("Kasir");
        data.addColumn("Tanggal");

        tableHistoryTrx.setModel(data);
        int omset = 0;
        int banyakTrx = 0;

        tanggalDari = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(dateFromLocal);
        tanggalKe = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(dateToLocal);
        String date1 = tanggalDari + " 00:00:00.000";
        String date2 = tanggalKe + " 23:59:59.000";
        try {
            Statement statement = (Statement) Koneksi.getConnection().createStatement();
            ResultSet res = statement.executeQuery("select order_number, sum(total) as total, tanggal, username from transaksi WHERE tanggal BETWEEN '" + date1 + "' AND '" + date2 + "' group by order_number order by tanggal desc;");
            while (res.next()) {
                data.addRow(new Object[]{
                    res.getString("order_number"),
                    res.getString("total"),
                    res.getString("username"),
                    res.getString("tanggal")
                });

                omset = omset + Integer.parseInt(res.getString("total"));
                banyakTrx = banyakTrx + 1;
                tableHistoryTrx.setModel(data);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Gagal Mengambil Data");
        }

        tOmset.setText(String.valueOf(omset));
        tBanyakTransaksi.setText(String.valueOf(banyakTrx));
    }

    private DefaultTableModel setHeaderHistoryDetail() {
        DefaultTableModel data = new DefaultTableModel();
        data.addColumn("Kode Barang");
        data.addColumn("Nama Barang");
        data.addColumn("Supplier");
        data.addColumn("Harga");
        data.addColumn("Quantity");
        data.addColumn("Total Harga");

        tableDetailHistory.setModel(data);

        return data;
    }

    private void tampilHistoryDetail(String orderNumber) {
        DefaultTableModel data = setHeaderHistoryDetail();
        try {
            Statement statement = (Statement) Koneksi.getConnection().createStatement();
            ResultSet res = statement.executeQuery("select b.kode, b.nama, s.nama as supplier_name, b.harga, t.quantity, t.total from transaksi t join barang b ON t.kode_barang = b.kode join supplier s ON s.kode = b.kode_supplier where order_number = '" + orderNumber + "';");
            while (res.next()) {
                data.addRow(new Object[]{
                    res.getString("kode"),
                    res.getString("nama"),
                    res.getString("supplier_name"),
                    res.getString("harga"),
                    res.getString("quantity"),
                    res.getString("total")
                });

                tableDetailHistory.setModel(data);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(rootPane, "Gagal Mengambil Data");
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bFilter = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tOmset = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDetailHistory = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHistoryTrx = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tBanyakTransaksi = new javax.swing.JLabel();
        dateDari = new com.toedter.calendar.JDateChooser();
        dateKe = new com.toedter.calendar.JDateChooser();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("History Transaksi");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Detail Transaksi");

        bFilter.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        bFilter.setText("Filter");
        bFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFilterActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        jLabel5.setText("LAPORAN PENJUALAN");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel6.setText("Omset : ");

        tOmset.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tOmset.setForeground(new java.awt.Color(0, 204, 0));
        tOmset.setText("jLabel7");

        tableDetailHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tableDetailHistory);

        tableHistoryTrx.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableHistoryTrx.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableHistoryTrxMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableHistoryTrx);

        jLabel7.setText("Dari");

        jLabel8.setText("Ke");

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel3.setText("*Jika tidak menggunakan filter maka transaksi yang di tampilkan adalah transaksi hari ini");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel9.setText("Banyak Transaksi : ");

        tBanyakTransaksi.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tBanyakTransaksi.setForeground(new java.awt.Color(0, 204, 0));
        tBanyakTransaksi.setText("jLabel7");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tOmset)
                .addGap(146, 146, 146)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tBanyakTransaksi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(38, 38, 38))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateDari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel5)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(16, 16, 16))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateDari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tOmset)
                    .addComponent(jLabel9)
                    .addComponent(tBanyakTransaksi))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFilterActionPerformed
        dateFrom = dateDari.getDate();
        dateTo = dateKe.getDate();

        tampilHistory(dateFrom, dateTo);
        setHeaderHistoryDetail();
    }//GEN-LAST:event_bFilterActionPerformed

    private void tableHistoryTrxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHistoryTrxMouseClicked
        int i = tableHistoryTrx.getSelectedRow();
        if (i == -1) {
            return;
        }

        String orderNumber = (String) tableHistoryTrx.getValueAt(i, 0);
        tampilHistoryDetail(orderNumber);
    }//GEN-LAST:event_tableHistoryTrxMouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bFilter;
    private com.toedter.calendar.JDateChooser dateDari;
    private com.toedter.calendar.JDateChooser dateKe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel tBanyakTransaksi;
    private javax.swing.JLabel tOmset;
    private javax.swing.JTable tableDetailHistory;
    private javax.swing.JTable tableHistoryTrx;
    // End of variables declaration//GEN-END:variables
}
