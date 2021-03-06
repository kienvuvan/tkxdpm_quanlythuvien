/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import book.views.ManagerBookForm;
import card.views.ActivateCardForm;
import login.views.LoginForm;
import user.view.ManagementReaderForm;
import util.Constants;

/**
 *
 * @author Linh
 */
public class ManagementForm extends javax.swing.JFrame {

    private String idLib;

    /**
     * Creates new form ManagementForm
     */
    public ManagementForm(String idLib) {
        initComponents();
        setLocationRelativeTo(null);
        this.idLib = idLib;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        manageUser = new javax.swing.JButton();
        manageBook = new javax.swing.JButton();
        manageReturn = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        manageCard = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Quản lý thư viện");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 59, 240, 56));

        manageUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/bg_button_user.png"))); // NOI18N
        manageUser.setContentAreaFilled(false);
        manageUser.setFocusPainted(false);
        manageUser.setPreferredSize(new java.awt.Dimension(95, 32));
        manageUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageUserClick(evt);
            }
        });
        getContentPane().add(manageUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 212, 269, 68));

        manageBook.setBackground(new java.awt.Color(51, 255, 204));
        manageBook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/bg_button_book.png"))); // NOI18N
        manageBook.setBorder(null);
        manageBook.setContentAreaFilled(false);
        manageBook.setFocusPainted(false);
        manageBook.setPreferredSize(new java.awt.Dimension(95, 32));
        manageBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageBookClick(evt);
            }
        });
        getContentPane().add(manageBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 126, 269, 68));

        manageReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/bg_button_return.png"))); // NOI18N
        manageReturn.setContentAreaFilled(false);
        manageReturn.setFocusPainted(false);
        manageReturn.setPreferredSize(new java.awt.Dimension(95, 32));
        manageReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageReturnClick(evt);
            }
        });
        getContentPane().add(manageReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 377, 269, 68));

        logoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/bg_button_logout.png"))); // NOI18N
        logoutButton.setContentAreaFilled(false);
        logoutButton.setFocusPainted(false);
        logoutButton.setPreferredSize(new java.awt.Dimension(95, 32));
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonClick(evt);
            }
        });
        getContentPane().add(logoutButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 456, 269, 68));

        manageCard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/bg_button_card.png"))); // NOI18N
        manageCard.setContentAreaFilled(false);
        manageCard.setFocusPainted(false);
        manageCard.setPreferredSize(new java.awt.Dimension(95, 32));
        manageCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageCardClick(evt);
            }
        });
        getContentPane().add(manageCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 298, 269, 68));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/bg_management.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void manageUserClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageUserClick
        // TODO add your handling code here:
        ManagementReaderForm mrf = new ManagementReaderForm(idLib);
        mrf.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_manageUserClick

    private void manageBookClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageBookClick
        // TODO add your handling code here:
        ManagerBookForm managerBook = new ManagerBookForm(idLib);
        managerBook.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_manageBookClick

    private void manageReturnClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageReturnClick
        // TODO add your handling code here:
        ReturnAndBorrowBookForm managerForm = new ReturnAndBorrowBookForm(idLib);
        managerForm.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_manageReturnClick

    private void logoutButtonClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonClick
        // TODO add your handling code here:
        this.setVisible(false);
        new LoginForm(Constants.LOGIN_WITH_LIBRARIAN).setVisible(true);
    }//GEN-LAST:event_logoutButtonClick

    private void manageCardClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageCardClick
        // TODO add your handling code here:
        ActivateCardForm activedCardForm = new ActivateCardForm(idLib);
        activedCardForm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_manageCardClick


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton manageBook;
    private javax.swing.JButton manageCard;
    private javax.swing.JButton manageReturn;
    private javax.swing.JButton manageUser;
    // End of variables declaration//GEN-END:variables
}
