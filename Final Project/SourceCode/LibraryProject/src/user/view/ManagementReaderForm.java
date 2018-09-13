/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.view;

import bookcopy.dao.MysqlBookCopyDao;
import java.util.Arrays;
import javax.swing.JOptionPane;
import librarian.controller.LibrarianController;
import librarian.model.Librarian;
import login.views.ChooseLoginForm;
import user.User;
import user.controller.UserController;
import user.dao.MysqlAccountDao;
import user.dao.MysqlUserDao;
import view.ManagementForm;

/**
 *
 * @author kienanh2903
 */
public class ManagementReaderForm extends javax.swing.JFrame {

    private final String idLib;
    private final LibrarianController lc;
    private final UserController uc;

    /**
     * Creates new form ManagementReaderForm
     *
     * @param idLib tên tài khoản đăng nhập của thủ thư
     */
    public ManagementReaderForm(String idLib) {

        initComponents();
        uc = new UserController();
        this.idLib = idLib;
        lc = new LibrarianController();
        uc.displayUser(jTable_user, uc.searchUser(""));
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jDialog_changePassLib = new javax.swing.JDialog();
        jPanel_changePass = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPasswordField_PassOld = new javax.swing.JPasswordField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jPasswordField_PassNew = new javax.swing.JPasswordField();
        jPasswordField_PassAgain = new javax.swing.JPasswordField();
        jButton3 = new javax.swing.JButton();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem_updateInforUser = new javax.swing.JMenuItem();
        jDialog_updateInforUser = new javax.swing.JDialog();
        jLabel2 = new javax.swing.JLabel();
        jTextField_username = new javax.swing.JTextField();
        jTextField_name = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField_email = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField_phone = new javax.swing.JTextField();
        jRadioButton_sexMale = new javax.swing.JRadioButton();
        jRadioButton_sexFemale = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        buttonGroup_sex = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTextField_keyword = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_user = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem_changePassLib = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        jDialog_changePassLib.setTitle("Thay đổi mật khẩu");

        jLabel29.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(234, 19, 89));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Bạn vui lòng điền mật khẩu mới vào ô dưới đây");

        jLabel30.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(239, 34, 127));
        jLabel30.setText("Mật khẩu cũ :");

        jLabel31.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(239, 34, 127));
        jLabel31.setText("Mật khẩu mới :");

        jLabel32.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(239, 34, 127));
        jLabel32.setText("Nhập lại :");

        jButton3.setText("Đổi mật khẩu");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_changePassLayout = new javax.swing.GroupLayout(jPanel_changePass);
        jPanel_changePass.setLayout(jPanel_changePassLayout);
        jPanel_changePassLayout.setHorizontalGroup(
            jPanel_changePassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_changePassLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_changePassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_changePassLayout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_changePassLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel_changePassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_changePassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPasswordField_PassNew)
                            .addComponent(jPasswordField_PassOld)
                            .addComponent(jPasswordField_PassAgain, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
                        .addGap(40, 40, 40))))
            .addGroup(jPanel_changePassLayout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_changePassLayout.setVerticalGroup(
            jPanel_changePassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_changePassLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addGroup(jPanel_changePassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField_PassOld, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_changePassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jPasswordField_PassNew))
                .addGap(18, 18, 18)
                .addGroup(jPanel_changePassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField_PassAgain, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialog_changePassLibLayout = new javax.swing.GroupLayout(jDialog_changePassLib.getContentPane());
        jDialog_changePassLib.getContentPane().setLayout(jDialog_changePassLibLayout);
        jDialog_changePassLibLayout.setHorizontalGroup(
            jDialog_changePassLibLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 466, Short.MAX_VALUE)
            .addGroup(jDialog_changePassLibLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_changePass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialog_changePassLibLayout.setVerticalGroup(
            jDialog_changePassLibLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 368, Short.MAX_VALUE)
            .addGroup(jDialog_changePassLibLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_changePass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuItem_updateInforUser.setText("Cập nhật thông tin");
        jMenuItem_updateInforUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_updateInforUserActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem_updateInforUser);

        jDialog_updateInforUser.setTitle("Cập nhật thông tin độc giả");

        jLabel2.setText("Tên tài khoản : ");

        jTextField_username.setEditable(false);

        jLabel3.setText("Họ tên : ");

        jLabel4.setText("Giới tính : ");

        jLabel5.setText("Email :");

        jLabel6.setText("Số điện thoại : ");

        buttonGroup_sex.add(jRadioButton_sexMale);
        jRadioButton_sexMale.setSelected(true);
        jRadioButton_sexMale.setText("Nam");

        buttonGroup_sex.add(jRadioButton_sexFemale);
        jRadioButton_sexFemale.setText("Nữ");

        jButton2.setText("Cập nhật");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog_updateInforUserLayout = new javax.swing.GroupLayout(jDialog_updateInforUser.getContentPane());
        jDialog_updateInforUser.getContentPane().setLayout(jDialog_updateInforUserLayout);
        jDialog_updateInforUserLayout.setHorizontalGroup(
            jDialog_updateInforUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_updateInforUserLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jDialog_updateInforUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jDialog_updateInforUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialog_updateInforUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog_updateInforUserLayout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog_updateInforUserLayout.createSequentialGroup()
                        .addGroup(jDialog_updateInforUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField_username, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDialog_updateInforUserLayout.createSequentialGroup()
                                .addComponent(jRadioButton_sexMale)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                                .addComponent(jRadioButton_sexFemale))
                            .addComponent(jTextField_email)
                            .addComponent(jTextField_name)
                            .addComponent(jTextField_phone))
                        .addGap(58, 58, 58))))
        );
        jDialog_updateInforUserLayout.setVerticalGroup(
            jDialog_updateInforUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_updateInforUserLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jDialog_updateInforUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_username, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialog_updateInforUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jDialog_updateInforUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton_sexMale, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton_sexFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jDialog_updateInforUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jDialog_updateInforUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel1.setText("Nhập thông tin cần tìm kiếm : ");

        jButton1.setText("Tìm kiếm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField_keyword, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jButton1)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_keyword, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Kết quả tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jTable_user.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên tài khoản", "Tên người dùng", "Giới tính", "Email", "Số điện thoại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jPopupMenu1, org.jdesktop.beansbinding.ObjectProperty.create(), jTable_user, org.jdesktop.beansbinding.BeanProperty.create("componentPopupMenu"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(jTable_user);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/applications_system.png"))); // NOI18N
        jMenu1.setText("Hệ thống");

        jMenuItem_changePassLib.setText("Đổi mật khẩu");
        jMenuItem_changePassLib.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_changePassLibActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem_changePassLib);

        jMenuItem1.setText("Trở về màn hình chức năng");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Đăng xuất");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Thoát");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem_changePassLibActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_changePassLibActionPerformed
        // TODO add your handling code here:
        jDialog_changePassLib.setVisible(true);
        jDialog_changePassLib.setSize(500, 400);
        jDialog_changePassLib.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem_changePassLibActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        new ManagementForm(idLib).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        ChooseLoginForm chooseLoginForm = new ChooseLoginForm();
        chooseLoginForm.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        changePassLib();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        searchUser();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem_updateInforUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_updateInforUserActionPerformed
        // TODO add your handling code here:
        setVisiableDialogUpdateInforReader();
    }//GEN-LAST:event_jMenuItem_updateInforUserActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        updateInforUser();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup_sex;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JDialog jDialog_changePassLib;
    private javax.swing.JDialog jDialog_updateInforUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem_changePassLib;
    private javax.swing.JMenuItem jMenuItem_updateInforUser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel_changePass;
    private javax.swing.JPasswordField jPasswordField_PassAgain;
    private javax.swing.JPasswordField jPasswordField_PassNew;
    private javax.swing.JPasswordField jPasswordField_PassOld;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JRadioButton jRadioButton_sexFemale;
    private javax.swing.JRadioButton jRadioButton_sexMale;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_user;
    private javax.swing.JTextField jTextField_email;
    private javax.swing.JTextField jTextField_keyword;
    private javax.swing.JTextField jTextField_name;
    private javax.swing.JTextField jTextField_phone;
    private javax.swing.JTextField jTextField_username;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    private void changePassLib() {
        String passOld = Arrays.toString(jPasswordField_PassOld.getPassword());
        String passNew = Arrays.toString(jPasswordField_PassNew.getPassword());
        String passAgain = Arrays.toString(jPasswordField_PassAgain.getPassword());
        if (jPasswordField_PassOld.getPassword().length == 0
                || jPasswordField_PassNew.getPassword().length == 0
                || jPasswordField_PassAgain.getPassword().length == 0) {
            JOptionPane.showMessageDialog(jDialog_changePassLib, "Bạn phải điền đầy đủ thông tin", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Librarian acc = new Librarian(idLib, passOld);
            int type = lc.changePassLib(acc, passNew, passAgain);
            switch (type) {
                case MysqlAccountDao.ACCOUNT_FALSE:
                    JOptionPane.showMessageDialog(jDialog_changePassLib, "Mật khẩu không chính xác.Vui lòng thử lại!!");
                    break;
                case MysqlAccountDao.PASSAGAIN_NOT_SAME_PASSNEW:
                    JOptionPane.showMessageDialog(jDialog_changePassLib, "Mật khẩu nhập lại không khớp.Vui lòng nhập lại!!");
                    break;
                case MysqlAccountDao.CHANGE_PASS_SUCCESS:
                    JOptionPane.showMessageDialog(jDialog_changePassLib, "Thay đổi mật khẩu thành công!!");
                    break;
                case MysqlAccountDao.FAILE_SYSTEM:
                    JOptionPane.showMessageDialog(jDialog_changePassLib, "Đã có lỗi xảy ra.Vui lòng thử lại!!");
                    break;

            }
        }
    }

    private void searchUser() {
        String keyword = jTextField_keyword.getText();
        uc.displayUser(jTable_user, uc.searchUser(keyword));
    }

    private void updateInforUser() {
        if (JOptionPane.showConfirmDialog(null, "Bạn có cập nhật thông tin độc giả này không ??", "Thông báo ",
                JOptionPane.YES_OPTION) == JOptionPane.YES_NO_OPTION) {
            String userName = jTextField_username.getText();
            String name = jTextField_name.getText();
            String sex = "";
            if (jRadioButton_sexMale.isSelected()) {
                sex = "Nam";
            } else {
                sex = "Nữ";
            }
            String email = jTextField_email.getText();
            String phone = jTextField_phone.getText();
            User userUpdate = new User.Builder().setId(userName).setName(name).setSex(sex).setEmail(email).setPhone(phone).build();
            int result = uc.updateInforUser(userUpdate);
            switch (result) {
                case MysqlUserDao.RESULT_NULL_POINT:
                    JOptionPane.showMessageDialog(jDialog_updateInforUser, "Các trường dữ liệu không được để trống");
                    break;
                case MysqlUserDao.RESULT_EMAIL_INCORRECT:
                    JOptionPane.showMessageDialog(jDialog_updateInforUser, "Địa chỉ email không đúng chính xác.Vui lòng nhập lại");
                    break;
                case MysqlUserDao.RESULT_PHONE_INCORRECT:
                    JOptionPane.showMessageDialog(jDialog_updateInforUser, "Số điện thoại không đúng chính xác.Vui lòng nhập lại");
                    break;
                case MysqlUserDao.RESULT_SUCCESS:
                    JOptionPane.showMessageDialog(jDialog_updateInforUser, "Cập nhật dữ liệu thành công");
                    jDialog_updateInforUser.setVisible(false);
                    uc.displayUser(jTable_user, uc.searchUser(""));
                    break;
                case MysqlUserDao.RESULT_SQLITE:
                    JOptionPane.showMessageDialog(jDialog_updateInforUser, "Đã có lỗi xảy ra.Vui lòng thử lại sau");
                    break;
            }
        }
    }

    private void setVisiableDialogUpdateInforReader() {
        int index = jTable_user.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn độc giả cần cập nhật.Vui lòng chọn lại");
        } else {
            jDialog_updateInforUser.setVisible(true);
            jDialog_updateInforUser.setSize(450, 380);
            jDialog_updateInforUser.setLocationRelativeTo(null);
            jTextField_username.setText(jTable_user.getValueAt(index, 0).toString());
            jTextField_name.setText(jTable_user.getValueAt(index, 1).toString());
            if (jTable_user.getValueAt(index, 2).toString().equalsIgnoreCase("Nam")) {
                jRadioButton_sexMale.setSelected(true);
            } else {
                jRadioButton_sexFemale.setSelected(true);
            }
            jTextField_email.setText(jTable_user.getValueAt(index, 3).toString());
            jTextField_phone.setText(jTable_user.getValueAt(index, 4).toString());
        }
    }
}