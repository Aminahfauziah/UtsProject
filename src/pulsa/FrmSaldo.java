
package pulsa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author acer
 */
public class FrmSaldo extends javax.swing.JFrame {
Connection con;
Statement stmt;
ResultSet rs;
DefaultTableModel datasource = new DefaultTableModel(
            new String [][]{{null,null, null}},
            new String [] {"No Nota","Tanggal", "Jumlah"}
            );
    /**
     * Creates new form FrmPembelian
     */
    public FrmSaldo() {
        initComponents();
        openConnection();
        opentable();
        saldo();
        lbl_bantuan.setText("simpan");
        lbl_bantuan.setVisible(false);
        lbl_saldo.setVisible(false);
    }
 public void openConnection()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://" +
                    "localhost:3306/Pulsa","root","");
            System.out.println("koneksi berhasil");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch(SQLException sqle){
            sqle.printStackTrace();

        }

    }
 private void refresh(){
     datasource.getDataVector().removeAllElements();
     tbl_laporan.setModel(datasource);
 }
  public void opentable()
{
refresh();

 try{

            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * from tbl_pembelian");

            while(rs.next())
            {
                datasource.addRow(new String[]{rs.getString(1),rs.getString(2),rs.getString(3)});
            }

         }catch(SQLException ex)
        {

            ex.printStackTrace();
        }
        try {
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FrmSaldo.class.getName()).log(Level.SEVERE, null, ex);
        }

}
  public void click()
{
    txt_nota.setEditable(false);
    txt_tgl.setEditable(false);
    txt_saldo.setEditable(false);
    int baris = tbl_laporan.getSelectedRow();
    System.out.print(baris);
    String nota = tbl_laporan.getValueAt(baris,0).toString();
    String tgl = tbl_laporan.getValueAt(baris,1).toString();
    String saldo = tbl_laporan.getValueAt(baris,2).toString();
    
    txt_nota.setText(nota);
    txt_tgl.setText(tgl);
    txt_saldo.setText(saldo);
    btnubah.setEnabled(true);
    
}
  public void ubah(){
      try
      {
  stmt.executeUpdate("update tbl_sisasaldo set sisa_saldo= '"+lbl_saldo.getText()+"' where id='1'");
      } catch (SQLException e)
      {
          System.out.println(e);
      }
  }
  private void simpanPembelian() {
      String beli ,saldo;
      int hasil=0;
      beli = txt_saldo.getText();
      saldo= lbl_saldo.getText();
      int b = Integer.parseInt(beli);
      int s = Integer.parseInt(saldo);
      hasil = b+s;
      String hasill= Integer.toString(hasil);
      lbl_saldo.setText(hasill);
      
              
      
      
        try {
            stmt = (Statement) this.con.createStatement(ResultSet.FETCH_FORWARD,
                    ResultSet.TYPE_SCROLL_SENSITIVE);

            int jml = stmt.executeUpdate("INSERT INTO tbl_pembelian " +
                    "VALUES('" +txt_nota.getText()+"', " +
                  
                    "'"+ txt_tgl.getText()+"', " +
                  
                    "'"+ txt_saldo.getText()+"')"); 
            System.out.println("input data berhasil");
        } catch(SQLException se) {
            se.printStackTrace();
        } //To change body of generated methods, choose Tools | Templates.
    }
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        lblnota = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_nota = new javax.swing.JTextField();
        txt_saldo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_laporan = new javax.swing.JTable();
        btnKmbl = new javax.swing.JButton();
        btnubah = new javax.swing.JButton();
        btntambah = new javax.swing.JButton();
        lbl_bantuan = new javax.swing.JLabel();
        txt_tgl = new javax.swing.JTextField();
        lbl_saldo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblnota.setText("No Nota");

        jLabel5.setText("Tanggal");

        jLabel6.setText("Jumlah");

        txt_saldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_saldoActionPerformed(evt);
            }
        });

        tbl_laporan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "No Nota", "Tanggal", "Jumlah"
            }
        ));
        tbl_laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_laporanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_laporan);

        btnKmbl.setText("Kembali");
        btnKmbl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKmblActionPerformed(evt);
            }
        });

        btnubah.setText("Edit");
        btnubah.setEnabled(false);
        btnubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnubahActionPerformed(evt);
            }
        });

        btntambah.setText("Simpan");
        btntambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btntambahMouseClicked(evt);
            }
        });
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });

        lbl_bantuan.setText("jLabel1");
        lbl_bantuan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                lbl_bantuanFocusLost(evt);
            }
        });

        lbl_saldo.setText("saldo");

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(68, 68, 68))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                    .addComponent(lblnota)
                                    .addGap(69, 69, 69)))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(70, 70, 70)))
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(txt_saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(lbl_saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_nota, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tgl, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(btntambah, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnubah)
                                .addGap(32, 32, 32)
                                .addComponent(btnKmbl)))
                        .addGap(26, 26, 26)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(lbl_bantuan)
                .addGap(36, 36, 36))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblnota)
                    .addComponent(txt_nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_tgl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(22, 22, 22)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_saldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_saldo))
                .addGap(70, 70, 70)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btntambah)
                    .addComponent(btnubah)
                    .addComponent(lbl_bantuan)
                    .addComponent(btnKmbl))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jLayeredPane1.setLayer(lblnota, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txt_nota, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txt_saldo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnKmbl, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnubah, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btntambah, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lbl_bantuan, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txt_tgl, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lbl_saldo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_saldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_saldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_saldoActionPerformed

    private void btnKmblActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKmblActionPerformed
        FrmMenu fm= new FrmMenu();
        fm.setVisible(true);
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnKmblActionPerformed

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
        // TODO add your handling code here:
        btntambah.setEnabled(false);
        if(lbl_bantuan.getText()== "simpan")
        {
            simpanPembelian();
            ubah();
        
        }
        else if(lbl_bantuan.getText()== "ubah")
        {
            
        }

        opentable();
        txt_nota.setText("");
        txt_tgl.setText("");
        txt_saldo.setText("");
        
    }//GEN-LAST:event_btntambahActionPerformed

    private void btnubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnubahActionPerformed
        // TODO add your handling code here:
        txt_nota.setEditable(true);
        txt_tgl.setEditable(true);
        txt_saldo.setEditable(true);
        lbl_bantuan.setText("ubah");
        btnubah.setEnabled(false);
        btntambah.setEnabled(true);
    }//GEN-LAST:event_btnubahActionPerformed

    private void btntambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btntambahMouseClicked
       // TODO add your handling code here:
    }//GEN-LAST:event_btntambahMouseClicked

    private void tbl_laporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_laporanMouseClicked
    click();        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_laporanMouseClicked

    private void lbl_bantuanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lbl_bantuanFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_bantuanFocusLost

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
            java.util.logging.Logger.getLogger(FrmSaldo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmSaldo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmSaldo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmSaldo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmSaldo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKmbl;
    private javax.swing.JButton btntambah;
    private javax.swing.JButton btnubah;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_bantuan;
    private javax.swing.JLabel lbl_saldo;
    private javax.swing.JLabel lblnota;
    private javax.swing.JTable tbl_laporan;
    private javax.swing.JTextField txt_nota;
    private javax.swing.JTextField txt_saldo;
    private javax.swing.JTextField txt_tgl;
    // End of variables declaration//GEN-END:variables

    public void saldo()
{
   
    try{
    stmt = con.createStatement();
      rs = stmt.executeQuery("SELECT sisa_saldo from tbl_sisasaldo ");
    rs.first();
    String saldo= rs.getString("sisa_saldo");
    lbl_saldo.setText(saldo);
    }
    catch(SQLException se)
                {
                      se.printStackTrace();

                }
}
}
