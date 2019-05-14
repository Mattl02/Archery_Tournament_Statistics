/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tournament;

/**
 *
 * @author Matthias
 */
public class TournamentGUI extends javax.swing.JFrame {

    private TournamentListModel tlm;
    
    
    /**
     * Creates new form TournamentGUI
     */
    public TournamentGUI() {
        initComponents();
        
        tlm = new TournamentListModel();
        listTournaments.setModel(tlm);
        
        listTournaments.setComponentPopupMenu(jPopupMenu1);
        listParticipants.setComponentPopupMenu(jPopupMenu2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        miAddTournament = new javax.swing.JMenuItem();
        miRemoveTournament = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        miAddParticipant = new javax.swing.JMenuItem();
        miRemoveParticipant = new javax.swing.JMenuItem();
        panelWest = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listTournaments = new javax.swing.JList<>();
        panelWestSouth = new javax.swing.JPanel();
        panelEast = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listParticipants = new javax.swing.JList<>();

        miAddTournament.setText("Add Tournament");
        miAddTournament.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAddTournamentActionPerformed(evt);
            }
        });
        jPopupMenu1.add(miAddTournament);

        miRemoveTournament.setText("Remove selected");
        miRemoveTournament.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miRemoveTournamentActionPerformed(evt);
            }
        });
        jPopupMenu1.add(miRemoveTournament);

        miAddParticipant.setText("Add Participant");
        miAddParticipant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAddParticipantActionPerformed(evt);
            }
        });
        jPopupMenu2.add(miAddParticipant);

        miRemoveParticipant.setText("Remove Selected");
        miRemoveParticipant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miRemoveParticipantActionPerformed(evt);
            }
        });
        jPopupMenu2.add(miRemoveParticipant);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 2));

        panelWest.setLayout(new java.awt.BorderLayout());

        listTournaments.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listTournaments);

        panelWest.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout panelWestSouthLayout = new javax.swing.GroupLayout(panelWestSouth);
        panelWestSouth.setLayout(panelWestSouthLayout);
        panelWestSouthLayout.setHorizontalGroup(
            panelWestSouthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        panelWestSouthLayout.setVerticalGroup(
            panelWestSouthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        panelWest.add(panelWestSouth, java.awt.BorderLayout.SOUTH);

        getContentPane().add(panelWest);

        panelEast.setLayout(new java.awt.GridLayout(1, 1));

        listParticipants.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listParticipants);

        panelEast.add(jScrollPane2);

        getContentPane().add(panelEast);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miAddTournamentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAddTournamentActionPerformed
        TournamentDialog dialog = new TournamentDialog(this, true);
        dialog.setVisible(true);
        if(dialog.isOk()){
            tlm.add(dialog.getTournament());
        }
    }//GEN-LAST:event_miAddTournamentActionPerformed

    private void miRemoveTournamentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miRemoveTournamentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_miRemoveTournamentActionPerformed

    private void miAddParticipantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAddParticipantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_miAddParticipantActionPerformed

    private void miRemoveParticipantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miRemoveParticipantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_miRemoveParticipantActionPerformed

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
            java.util.logging.Logger.getLogger(TournamentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TournamentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TournamentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TournamentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TournamentGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listParticipants;
    private javax.swing.JList<String> listTournaments;
    private javax.swing.JMenuItem miAddParticipant;
    private javax.swing.JMenuItem miAddTournament;
    private javax.swing.JMenuItem miRemoveParticipant;
    private javax.swing.JMenuItem miRemoveTournament;
    private javax.swing.JPanel panelEast;
    private javax.swing.JPanel panelWest;
    private javax.swing.JPanel panelWestSouth;
    // End of variables declaration//GEN-END:variables
}
