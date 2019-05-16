/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tournament;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import participant.Participant;
import participant.ParticipantListModel;

/**
 *
 * @author Matthias
 */
public class TournamentGUI extends javax.swing.JFrame {

    private TournamentListModel tlm;
    private LinkedList<ParticipantListModel> participantModels = new LinkedList<>();
    
    
    /**
     * Creates new form TournamentGUI
     */
    public TournamentGUI() {
        initComponents();
        
        tlm = new TournamentListModel();
        listTournaments.setModel(tlm);
        try {
            participantModels.addAll(tlm.loadFromDatabase());
        } catch (SQLException ex) {
            Logger.getLogger(TournamentGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        listTournaments.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                listParticipants.setModel(participantModels.get(listTournaments.getSelectedIndex()));
            }
            
        });
        listParticipants.setModel(new DefaultListModel());
        
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
        miEditTournament = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        miAddParticipant = new javax.swing.JMenuItem();
        miRemoveParticipant = new javax.swing.JMenuItem();
        panelWest = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listTournaments = new javax.swing.JList<>();
        panelWestSouth = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btSaveToDatabase = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
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

        miEditTournament.setText("Edit Tournament");
        miEditTournament.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miEditTournamentActionPerformed(evt);
            }
        });
        jPopupMenu1.add(miEditTournament);

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

        panelWestSouth.setLayout(new java.awt.GridLayout(3, 1));
        panelWestSouth.add(jLabel1);

        btSaveToDatabase.setText("Save To Database");
        btSaveToDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveToDatabaseActionPerformed(evt);
            }
        });
        panelWestSouth.add(btSaveToDatabase);
        panelWestSouth.add(jLabel2);

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
            participantModels.add(new ParticipantListModel());
        }
    }//GEN-LAST:event_miAddTournamentActionPerformed

    private void miRemoveTournamentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miRemoveTournamentActionPerformed
        if(listTournaments.getSelectedIndices().length > 0){
            if(listTournaments.getSelectedIndices().length > 1){
                tlm.remove(listTournaments.getSelectedIndices());
            }
            else{
                tlm.remove(listTournaments.getSelectedIndex());
            }
        }
    }//GEN-LAST:event_miRemoveTournamentActionPerformed

    private void miAddParticipantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAddParticipantActionPerformed
        if(listTournaments.getSelectedIndices().length > 0) {
            String s = JOptionPane.showInputDialog(this, "Enter a name for the participant.");
            if (s != null) {
                participantModels.get(listTournaments.getSelectedIndex()).add(new Participant(s));
            }
        }
    }//GEN-LAST:event_miAddParticipantActionPerformed

    private void miRemoveParticipantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miRemoveParticipantActionPerformed
        if(listParticipants.getSelectedIndices().length > 1){
            participantModels.get(listTournaments.getSelectedIndex()).remove(listParticipants.getSelectedIndices());
        }
        else{
            participantModels.get(listTournaments.getSelectedIndex()).remove(listParticipants.getSelectedIndex());
        }
    }//GEN-LAST:event_miRemoveParticipantActionPerformed

    private void miEditTournamentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miEditTournamentActionPerformed
        if(listTournaments.getSelectedIndices().length > 1){
            JOptionPane.showMessageDialog(this,
                    "Please only select one tournament to edit.");
        }
        else{
            int idx = listTournaments.getSelectedIndex();
            TournamentDialog dialog = new TournamentDialog(this, true,
                    (Tournament) tlm.getElementAt(listTournaments.getSelectedIndex()));
            dialog.setVisible(true);
            if(dialog.isOk()){
                tlm.remove(idx);
                tlm.add(idx, dialog.getTournament());
            }
        }
    }//GEN-LAST:event_miEditTournamentActionPerformed

    private void btSaveToDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveToDatabaseActionPerformed
        try {
            tlm.saveToDatabase();
        } catch (SQLException ex) {
            Logger.getLogger(TournamentGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btSaveToDatabaseActionPerformed

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
    private javax.swing.JButton btSaveToDatabase;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listParticipants;
    private javax.swing.JList<String> listTournaments;
    private javax.swing.JMenuItem miAddParticipant;
    private javax.swing.JMenuItem miAddTournament;
    private javax.swing.JMenuItem miEditTournament;
    private javax.swing.JMenuItem miRemoveParticipant;
    private javax.swing.JMenuItem miRemoveTournament;
    private javax.swing.JPanel panelEast;
    private javax.swing.JPanel panelWest;
    private javax.swing.JPanel panelWestSouth;
    // End of variables declaration//GEN-END:variables
}
