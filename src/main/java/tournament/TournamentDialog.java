/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tournament;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author Matthias
 */
public class TournamentDialog extends javax.swing.JDialog {

    private Tournament tournament;
    private boolean ok;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public Tournament getTournament() {
        return tournament;
    }

    public boolean isOk() {
        return ok;
    }
    
    /**
     * Creates new form TournamentDialog
     */
    public TournamentDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public TournamentDialog(java.awt.Frame parent, boolean modal, Tournament t){
        super(parent, modal);
        initComponents();
        initExistingTournament(t);
    }
    
    public void initExistingTournament(Tournament t) {
        tfName.setText(t.getName());
        tfStart.setText(t.getStart().format(dtf));
        tfEnd.setText(t.getEnd().format(dtf));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbName = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        lbStart = new javax.swing.JLabel();
        tfStart = new javax.swing.JTextField();
        lbEnd = new javax.swing.JLabel();
        tfEnd = new javax.swing.JTextField();
        btCancel = new javax.swing.JButton();
        btOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(4, 2));

        lbName.setText("Name");
        getContentPane().add(lbName);
        getContentPane().add(tfName);

        lbStart.setText("Start-time (dd.mm.yyyy HH:mm)");
        getContentPane().add(lbStart);
        getContentPane().add(tfStart);

        lbEnd.setText("End-time (dd.mm.yyyy HH:mm)");
        getContentPane().add(lbEnd);
        getContentPane().add(tfEnd);

        btCancel.setText("Cancel");
        btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelActionPerformed(evt);
            }
        });
        getContentPane().add(btCancel);

        btOk.setText("OK");
        btOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOkActionPerformed(evt);
            }
        });
        getContentPane().add(btOk);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelActionPerformed
        this.ok = false;
        this.dispose();
    }//GEN-LAST:event_btCancelActionPerformed

    private void btOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOkActionPerformed
        try{
            LocalDateTime start = LocalDateTime.parse(tfStart.getText(), dtf);
            LocalDateTime end = LocalDateTime.parse(tfEnd.getText(), dtf);
            
            if(end.compareTo(start) <= 0){
                JOptionPane.showMessageDialog(this,
                        "End-date mustn't be earlier or the same as start-date!");
                return;
            }
            
            this.tournament = new Tournament(tfName.getText(), start, end);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        this.ok = true;
        this.dispose();
    }//GEN-LAST:event_btOkActionPerformed

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
            java.util.logging.Logger.getLogger(TournamentDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TournamentDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TournamentDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TournamentDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TournamentDialog dialog = new TournamentDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btOk;
    private javax.swing.JLabel lbEnd;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbStart;
    private javax.swing.JTextField tfEnd;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfStart;
    // End of variables declaration//GEN-END:variables
}
