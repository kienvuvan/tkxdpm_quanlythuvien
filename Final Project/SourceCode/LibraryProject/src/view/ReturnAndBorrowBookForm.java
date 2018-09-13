/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import book.model.Book;
import bookcopy.controller.BookCopyController;
import bookcopy.controller.BorrowBookController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import loan.controller.LoanController;
import loan.ireport.ConnectIreport;
import login.views.LoginForm;
import net.sf.jasperreports.engine.JRException;
import registeterm.controller.RegisterTermController;
import registeterm.dao.MysqlRegisterTerrmDao;
import user.User;
import util.Constants;
import util.Utils;

/**
 *
 * @author Linh
 */
public class ReturnAndBorrowBookForm extends javax.swing.JFrame {

    private Date now = new Date(System.currentTimeMillis());
    private String idLib;
    private LoanController controller;
    private RegisterTermController registerTermController;
    private DefaultTableModel loanModelTable, detailLoanModelTable, regModelTable, detailRegModelTable, unregisDetailModelTable;
    private BorrowBookController borrowBookController;
    private BookCopyController bcc;

    /**
     * Creates new form ReturnForm
     */
    public ReturnAndBorrowBookForm(String idLib) {
        initComponents();
        setLocationRelativeTo(null);
        setVisiableRegistedBookPanel();

        this.idLib = idLib;
        initModelTable();
        initController();
        initDialog();
        initValue();
        initAction();
    }

    private void initController() {
        controller = new LoanController();
        bcc = new BookCopyController();
        registerTermController = new RegisterTermController();
        borrowBookController = new BorrowBookController();
        borrowBookController.displayInforBook(idBookFieldUnRegis, idCardFieldUnRegis);
        controller.displayAllLoan(loanModelTable);
        registerTermController.displayAllReg(regModelTable);
        displayUIInforUser(borrowBookController.getUserByCardNo((String) idCardFieldUnRegis.getSelectedItem()));
        displayUIInforBook(borrowBookController.getBookById((String) idBookFieldUnRegis.getSelectedItem()));
    }

    private void initModelTable() {
        regModelTable = (DefaultTableModel) tableRegisterBook.getModel();
        detailRegModelTable = (DefaultTableModel) tableDetailRegisterBook.getModel();
        loanModelTable = (DefaultTableModel) tableLoan.getModel();
        detailLoanModelTable = (DefaultTableModel) tableDetailLoan.getModel();
        unregisDetailModelTable = (DefaultTableModel) tablePanelUnregis.getModel();
        tablePanelUnregis.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableRegisterBook.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableDetailRegisterBook.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableDetailLoan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableLoan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void initDialog() {
        dialogCountBorrow.pack();
        dialogCountBorrow.setLocationRelativeTo(null);
        dialogLoan.pack();
        dialogLoan.setLocationRelativeTo(null);
    }

    private void initValue() {
        dateBorrowField.setText(Utils.format(now));
        dateEndField.getEditor().setEditable(false);
        endDateDialog.getEditor().setEditable(false);
    }

    private void initAction() {
        idBookFieldUnRegis.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int positionSelect = idBookFieldUnRegis.getSelectedIndex();
                if (positionSelect >= 0) {
                    displayUIInforBook(borrowBookController.getBookById((String) idBookFieldUnRegis.getSelectedItem()));
                }

            }
        });
        idCardFieldUnRegis.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int positionSelect = idCardFieldUnRegis.getSelectedIndex();
                if (positionSelect >= 0) {
                    displayUIInforUser(borrowBookController.getUserByCardNo((String) idCardFieldUnRegis.getSelectedItem()));
                }

            }
        });
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                borrowBookController.returnStatusBook(tablePanelUnregis);
                System.exit(0);

            }
        });
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {
                borrowBookController.returnStatusBook(tablePanelUnregis);
            }
        });

    }

    public void displayUIInforUser(User user) {

        if (user != null) {
            nameFieldUnRegis.setText(user.getName());
            mailFieldUnRegis.setText(user.getEmail());
            phoneFieldUnRegis.setText(user.getPhone());

        }

    }

    public void displayUIInforBook(Book book) {
        if (book != null) {
            nameBookFieldUnRegis.setText(book.getTitle());
            authorFieldUnRegis.setText(book.getAuthor());
            pubFieldUnRegis.setText(book.getAuthor());
            catFieldUnRegis.setText(book.getCategory().getCat());

        }

    }

    public void setVisiableRegistedBookPanel() {
        registedBookPanel.setVisible(true);
        unRegistedBookPanel.setVisible(false);
        returnBookPanel.setVisible(false);
    }

    public void setVisiableUnRegistedBookPanel() {
        registedBookPanel.setVisible(false);
        unRegistedBookPanel.setVisible(true);
        returnBookPanel.setVisible(false);
    }

    public void setVisiableReturnBookPanel() {
        registedBookPanel.setVisible(false);
        unRegistedBookPanel.setVisible(false);
        returnBookPanel.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem4 = new javax.swing.JMenuItem();
        dialogLoan = new javax.swing.JDialog();
        jTextField15 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        userNameDialog = new javax.swing.JTextField();
        idCardDialog = new javax.swing.JTextField();
        idLibDialog = new javax.swing.JTextField();
        startDateDialog = new javax.swing.JTextField();
        endDateDialog = new org.jdesktop.swingx.JXDatePicker();
        moneyDialog = new javax.swing.JTextField();
        cancelDialog = new javax.swing.JButton();
        doBorrowDialog = new javax.swing.JButton();
        dialogCountBorrow = new javax.swing.JDialog();
        jLabel8 = new javax.swing.JLabel();
        numberAvailable = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        countBorrowFieldDialog = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        deleteMenu = new javax.swing.JMenuItem();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        registedBookPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDetailRegisterBook = new javax.swing.JTable();
        searchRegisterField = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableRegisterBook = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        borrowBook = new javax.swing.JButton();
        unRegistedBookPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablePanelUnregis = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        idBookFieldUnRegis = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        nameBookFieldUnRegis = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        authorFieldUnRegis = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        pubFieldUnRegis = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        catFieldUnRegis = new javax.swing.JTextField();
        addBookToRegis = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        nameFieldUnRegis = new javax.swing.JTextField();
        idCardFieldUnRegis = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        mailFieldUnRegis = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        phoneFieldUnRegis = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        dateBorrowField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        dateEndField = new org.jdesktop.swingx.JXDatePicker();
        moneyField = new javax.swing.JTextField();
        deleteButton = new javax.swing.JButton();
        deleteAll = new javax.swing.JButton();
        returnBookPanel = new javax.swing.JPanel();
        registedBookPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableDetailLoan = new javax.swing.JTable();
        searchLoanField = new javax.swing.JTextField();
        doSearch = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableLoan = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        borrowBook1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        moneyReturn = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        jMenuItem4.setText("jMenuItem4");

        jTextField15.setEditable(false);
        jTextField15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTextField15.setForeground(new java.awt.Color(255, 0, 0));
        jTextField15.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField15.setText("Phiếu mượn");

        jLabel21.setText("Người mượn:");

        jLabel22.setText("Mã thẻ mượn");

        jLabel24.setText("Mã thủ thư");

        jLabel25.setText("Ngày mượn");

        jLabel26.setText("Hạn trả");

        jLabel27.setText("Tiền cọc");

        userNameDialog.setEditable(false);

        idCardDialog.setEditable(false);

        idLibDialog.setEditable(false);

        startDateDialog.setEditable(false);

        cancelDialog.setText("hủy bỏ");
        cancelDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelDialogActionPerformed(evt);
            }
        });

        doBorrowDialog.setText("Xác nhận");
        doBorrowDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doBorrowDialogActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dialogLoanLayout = new javax.swing.GroupLayout(dialogLoan.getContentPane());
        dialogLoan.getContentPane().setLayout(dialogLoanLayout);
        dialogLoanLayout.setHorizontalGroup(
            dialogLoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogLoanLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(dialogLoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(dialogLoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogLoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(idLibDialog)
                        .addComponent(jTextField15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                        .addComponent(userNameDialog, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(idCardDialog, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(startDateDialog, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(moneyDialog, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(endDateDialog, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(111, 111, 111))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogLoanLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelDialog)
                .addGap(41, 41, 41)
                .addComponent(doBorrowDialog)
                .addGap(139, 139, 139))
        );
        dialogLoanLayout.setVerticalGroup(
            dialogLoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogLoanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(dialogLoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(userNameDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dialogLoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(idCardDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dialogLoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(idLibDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dialogLoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(startDateDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(dialogLoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(endDateDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dialogLoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(moneyDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(dialogLoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doBorrowDialog)
                    .addComponent(cancelDialog))
                .addGap(57, 57, 57))
        );

        jLabel8.setText("Số lượng sách khả dụng");

        numberAvailable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Nhập số lượng sách cần mượn");

        jLabel29.setText("Số lượng sách cần mượn");

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBookToCart(evt);
            }
        });

        jButton2.setText("CANCEL");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dialogCountBorrowLayout = new javax.swing.GroupLayout(dialogCountBorrow.getContentPane());
        dialogCountBorrow.getContentPane().setLayout(dialogCountBorrowLayout);
        dialogCountBorrowLayout.setHorizontalGroup(
            dialogCountBorrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogCountBorrowLayout.createSequentialGroup()
                .addGroup(dialogCountBorrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dialogCountBorrowLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dialogCountBorrowLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(dialogCountBorrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel29))
                        .addGap(25, 25, 25)
                        .addGroup(dialogCountBorrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numberAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(countBorrowFieldDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(71, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogCountBorrowLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(56, 56, 56)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
        );

        dialogCountBorrowLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

        dialogCountBorrowLayout.setVerticalGroup(
            dialogCountBorrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogCountBorrowLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(dialogCountBorrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dialogCountBorrowLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel8))
                    .addComponent(numberAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dialogCountBorrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(countBorrowFieldDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(dialogCountBorrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(7, 7, 7))
        );

        deleteMenu.setText("Hủy phiếu đăng ký");
        deleteMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMenuActionPerformed(evt);
            }
        });
        jPopupMenu1.add(deleteMenu);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        tableDetailRegisterBook.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu", "Mã sách Copy", "Tên sách"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableDetailRegisterBook);
        if (tableDetailRegisterBook.getColumnModel().getColumnCount() > 0) {
            tableDetailRegisterBook.getColumnModel().getColumn(0).setResizable(false);
            tableDetailRegisterBook.getColumnModel().getColumn(1).setResizable(false);
            tableDetailRegisterBook.getColumnModel().getColumn(2).setResizable(false);
        }

        jButton3.setText("tìm kiếm");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchRegisBook(evt);
            }
        });

        jLabel1.setText("thông tin tìm kiếm");

        jLabel2.setForeground(new java.awt.Color(244, 19, 19));
        jLabel2.setText("*nhập mã thẻ mượn hoặc mã phiếu mượn");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Phiếu mượn đã đăng ký"));

        jScrollPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane3MouseClicked(evt);
            }
        });

        tableRegisterBook.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu", "Mã thẻ độc giả", "Tên độc giả", "ngày đăng ký", "Trạng thái "
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableRegisterBook.setComponentPopupMenu(jPopupMenu1);
        tableRegisterBook.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableRegisterBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableRegisterBookMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableRegisterBook);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
        );

        jLabel17.setText("Chi tiết phiếu đăng ký");

        borrowBook.setText("Xác nhận mượn");
        borrowBook.setToolTipText("");
        borrowBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrowBookClick(evt);
            }
        });

        javax.swing.GroupLayout registedBookPanelLayout = new javax.swing.GroupLayout(registedBookPanel);
        registedBookPanel.setLayout(registedBookPanelLayout);
        registedBookPanelLayout.setHorizontalGroup(
            registedBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registedBookPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(registedBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registedBookPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(registedBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(registedBookPanelLayout.createSequentialGroup()
                                .addComponent(searchRegisterField, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jButton3)))
                        .addGap(204, 204, 204))
                    .addGroup(registedBookPanelLayout.createSequentialGroup()
                        .addGroup(registedBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(registedBookPanelLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 678, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1))
                        .addContainerGap())))
            .addGroup(registedBookPanelLayout.createSequentialGroup()
                .addGap(344, 344, 344)
                .addComponent(borrowBook)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        registedBookPanelLayout.setVerticalGroup(
            registedBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registedBookPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(registedBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchRegisterField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(33, 33, 33)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(borrowBook)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        tablePanelUnregis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Tên sách", "Mã thẻ độc giả", "Tên độc giả"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablePanelUnregis);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin sách"));

        jLabel3.setText("Mã sách ");

        jLabel4.setText("Tên sách");

        nameBookFieldUnRegis.setEditable(false);

        jLabel5.setText("Tác giả ");

        authorFieldUnRegis.setEditable(false);

        jLabel6.setText("Nhà xuất bản");

        pubFieldUnRegis.setEditable(false);

        jLabel7.setText("Thể loại");

        catFieldUnRegis.setEditable(false);

        addBookToRegis.setText("Thêm vào phiếu");
        addBookToRegis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBookToRegisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pubFieldUnRegis))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(idBookFieldUnRegis, 0, 209, Short.MAX_VALUE)
                            .addComponent(nameBookFieldUnRegis)
                            .addComponent(authorFieldUnRegis))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(catFieldUnRegis)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(addBookToRegis)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel3, jLabel4, jLabel5, jLabel6, jLabel7});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idBookFieldUnRegis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nameBookFieldUnRegis)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(authorFieldUnRegis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(pubFieldUnRegis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(catFieldUnRegis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(addBookToRegis)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin mượn"));

        jLabel9.setText("Mã thẻ mượn");

        jLabel10.setText("Họ tên");

        nameFieldUnRegis.setEditable(false);

        jLabel13.setText("Email");

        mailFieldUnRegis.setEditable(false);

        jLabel15.setText("Số điện thoại");

        phoneFieldUnRegis.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel13)
                    .addComponent(jLabel15))
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nameFieldUnRegis)
                    .addComponent(idCardFieldUnRegis, 0, 214, Short.MAX_VALUE)
                    .addComponent(mailFieldUnRegis)
                    .addComponent(phoneFieldUnRegis))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel13, jLabel9});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(idCardFieldUnRegis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(nameFieldUnRegis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mailFieldUnRegis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(phoneFieldUnRegis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jButton4.setText("Cho mượn");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrowBookButtonClick(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin phiếu"));

        dateBorrowField.setEditable(false);

        jLabel11.setText("Ngày mượn");

        jLabel14.setText("Tổng tiền cọc");

        jLabel12.setText("Ngày trả");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11))
                .addGap(59, 59, 59)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateBorrowField)
                    .addComponent(dateEndField, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                    .addComponent(moneyField))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(dateBorrowField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(dateEndField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(moneyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        deleteButton.setText("Xóa");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        deleteAll.setText("Xóa tất cả");
        deleteAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAllTableUnreg(evt);
            }
        });

        javax.swing.GroupLayout unRegistedBookPanelLayout = new javax.swing.GroupLayout(unRegistedBookPanel);
        unRegistedBookPanel.setLayout(unRegistedBookPanelLayout);
        unRegistedBookPanelLayout.setHorizontalGroup(
            unRegistedBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(unRegistedBookPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(unRegistedBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(unRegistedBookPanelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(unRegistedBookPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addContainerGap(63, Short.MAX_VALUE))
            .addGroup(unRegistedBookPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(deleteAll, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128))
        );

        unRegistedBookPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {deleteAll, deleteButton, jButton4});

        unRegistedBookPanelLayout.setVerticalGroup(
            unRegistedBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, unRegistedBookPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(unRegistedBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(unRegistedBookPanelLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(unRegistedBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton)
                    .addComponent(jButton4)
                    .addComponent(deleteAll))
                .addGap(18, 18, 18))
        );

        tableDetailLoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu", "Mã sách Copy", "Tên sách", "Chọn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDetailLoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDetailLoanMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tableDetailLoan);
        if (tableDetailLoan.getColumnModel().getColumnCount() > 0) {
            tableDetailLoan.getColumnModel().getColumn(0).setResizable(false);
            tableDetailLoan.getColumnModel().getColumn(1).setResizable(false);
            tableDetailLoan.getColumnModel().getColumn(2).setResizable(false);
            tableDetailLoan.getColumnModel().getColumn(3).setResizable(false);
            tableDetailLoan.getColumnModel().getColumn(3).setHeaderValue("Chọn");
        }

        doSearch.setText("tìm kiếm");
        doSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seachLoanToReturnClick(evt);
            }
        });

        jLabel18.setText("thông tin tìm kiếm");

        jLabel19.setForeground(new java.awt.Color(244, 19, 19));
        jLabel19.setText("*nhập mã thẻ mượn hoặc mã phiếu mượn");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Phiếu mượn sách"));

        tableLoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu", "Mã thẻ độc giả", "Mã thủ thư", "Tên độc giả", "ngày mượn", "hạn trả", "cọc"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableLoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableLoanMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tableLoan);
        if (tableLoan.getColumnModel().getColumnCount() > 0) {
            tableLoan.getColumnModel().getColumn(2).setHeaderValue("Mã thủ thư");
            tableLoan.getColumnModel().getColumn(5).setHeaderValue("hạn trả");
            tableLoan.getColumnModel().getColumn(6).setHeaderValue("cọc");
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
        );

        jLabel20.setText("Chi tiết phiếu");

        borrowBook1.setText("Trả sách đã chọn");
        borrowBook1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnBookInLoanClick(evt);
            }
        });

        jButton5.setText("Trả toàn bộ phiếu");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnAllBookClick(evt);
            }
        });

        jLabel16.setText("Tiền phạt(có thể có hoặc không)");

        moneyReturn.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel23.setText("nghìn VNĐ");

        javax.swing.GroupLayout registedBookPanel1Layout = new javax.swing.GroupLayout(registedBookPanel1);
        registedBookPanel1.setLayout(registedBookPanel1Layout);
        registedBookPanel1Layout.setHorizontalGroup(
            registedBookPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registedBookPanel1Layout.createSequentialGroup()
                .addContainerGap(160, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addGroup(registedBookPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addGroup(registedBookPanel1Layout.createSequentialGroup()
                        .addComponent(searchLoanField, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(doSearch)))
                .addGap(204, 204, 204))
            .addGroup(registedBookPanel1Layout.createSequentialGroup()
                .addGap(232, 232, 232)
                .addComponent(borrowBook1)
                .addGap(46, 46, 46)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(registedBookPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(registedBookPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap())
            .addGroup(registedBookPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(moneyReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addGap(44, 44, 44))
        );
        registedBookPanel1Layout.setVerticalGroup(
            registedBookPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registedBookPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(registedBookPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchLoanField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(doSearch)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addGap(33, 33, 33)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(registedBookPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel16)
                    .addComponent(moneyReturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(registedBookPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(borrowBook1)
                    .addComponent(jButton5))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout returnBookPanelLayout = new javax.swing.GroupLayout(returnBookPanel);
        returnBookPanel.setLayout(returnBookPanelLayout);
        returnBookPanelLayout.setHorizontalGroup(
            returnBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 816, Short.MAX_VALUE)
            .addGroup(returnBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(registedBookPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        returnBookPanelLayout.setVerticalGroup(
            returnBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 629, Short.MAX_VALUE)
            .addGroup(returnBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(registedBookPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane1.setLayer(registedBookPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(unRegistedBookPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(returnBookPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(registedBookPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(unRegistedBookPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(returnBookPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(registedBookPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(unRegistedBookPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(returnBookPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/applications_system.png"))); // NOI18N
        jMenu2.setText("Hệ thống");

        jMenuItem1.setText("Trở về");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backMenuClick(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Đăng xuất");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutMenuClick(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Thoát");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuClick(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/utilitie.png"))); // NOI18N
        jMenu1.setText("Tính năng");

        jMenu3.setText("Cho mượn sách");

        jMenuItem6.setText("Độc giả đã đăng ký");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuItem7.setText("Độc giả chưa đăng ký");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenu1.add(jMenu3);

        jMenuItem5.setText("Nhận trả sách");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        setVisiableRegistedBookPanel();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        setVisiableUnRegistedBookPanel();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        setVisiableReturnBookPanel();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void tableRegisterBookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableRegisterBookMouseClicked
        // TODO add your handling code here:

        registerTermController.displayDetailReg(tableRegisterBook.getSelectedRow(), regModelTable, detailRegModelTable);

    }//GEN-LAST:event_tableRegisterBookMouseClicked

    private void borrowBookClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrowBookClick
        // TODO add your handling code here:
        int row = tableRegisterBook.getSelectedRow();
        if (row >= 0) {
            String status = tableRegisterBook.getValueAt(row, 4).toString();
            if (status.equals(Constants.STATUS_REG_OVER_DUE)) {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa không?", "Phiếu đã quá hạn, cần phải xóa", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    deleteRegistedBorrow();
                }

            } else {
                String id = tableRegisterBook.getValueAt(row, 0).toString();
                String idCard = tableRegisterBook.getValueAt(row, 1).toString();
                String name = tableRegisterBook.getValueAt(row, 2).toString();

                userNameDialog.setText(name);
                idCardDialog.setText(idCard);
                idLibDialog.setText(idLib);
                startDateDialog.setText(Utils.format(now));
                dialogLoan.setVisible(true);
            }

        }


    }//GEN-LAST:event_borrowBookClick

    private void tableLoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLoanMouseClicked
        // TODO add your handling code here:

        controller.displayDetailLoan(tableLoan.getSelectedRow(), detailLoanModelTable);

    }//GEN-LAST:event_tableLoanMouseClicked

    private void returnBookInLoanClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnBookInLoanClick
        // TODO add your handling code here:
        int result = controller.returnBookOfLoan(tableLoan, tableDetailLoan, idLib, moneyReturn.getText());
        System.out.println("result " + result);
        switch (result) {
            case LoanController.RETURN_RESULT_NULL_POINT:
                JOptionPane.showMessageDialog(this, "Hãy nhập đầy đủ thông tin");
                break;
            case LoanController.RETURN_RESULT_NO_COUNT_IN_TABLE:
                JOptionPane.showMessageDialog(this, "Phiếu đã trả hết,vui lòng chọn phiếu khác");
                break;
            case LoanController.RETURN_RESULT_MONEY_WRONG:
                JOptionPane.showMessageDialog(this, "Nhập lại tiền phạt,nhập 0 nếu là không phạt");
                break;
            case LoanController.RETURN_RESULT_NO_BOOK_SELECT:
                JOptionPane.showMessageDialog(this, "Hãy chọn sách cần trả");
                break;

            case LoanController.RETURN_RESULT_COUNT_SELECT:
                JOptionPane.showMessageDialog(this, "Hãy chọn phiếu cần trả");
                break;
            case LoanController.RETURN_RESULT_SUCCESS:
                for (int i = 0; i < tableDetailLoan.getRowCount(); i++) {
                    Boolean isChecked = (Boolean) tableDetailLoan.getValueAt(i, 3);

                    if (isChecked) {
                        detailLoanModelTable.removeRow(i);
                    }
                }
                moneyReturn.setText("");
                JOptionPane.showMessageDialog(this, "Trả thành công, trạng thái của sách được cập nhật");
                break;
            case LoanController.RETURN_RESULT_SQLITE:
                JOptionPane.showMessageDialog(this, "Vui lòng  kiểm tra lại kết nối mạng");
                break;

        }

    }//GEN-LAST:event_returnBookInLoanClick

    private void tableDetailLoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDetailLoanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableDetailLoanMouseClicked

    private void backMenuClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backMenuClick
        // TODO add your handling code here:

        this.setVisible(false);
        new ManagementForm(idLib).setVisible(true);
    }//GEN-LAST:event_backMenuClick

    private void logoutMenuClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutMenuClick
        // TODO add your handling code here:
        new LoginForm(Constants.LOGIN_WITH_LIBRARIAN).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_logoutMenuClick

    private void exitMenuClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuClick
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_exitMenuClick

    private void doBorrowDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doBorrowDialogActionPerformed
        // TODO add your handling code here:

        int result = registerTermController.borrowBook(tableRegisterBook, idCardDialog.getText(), idLib, new java.sql.Date(System.currentTimeMillis()), endDateDialog.getDate(), moneyDialog.getText());
        switch (result) {
            case MysqlRegisterTerrmDao.RESULT_NO_SELECTED:
                JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu cần cho mượn");
                break;
            case MysqlRegisterTerrmDao.RESULT_NULL_POINT:
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin");
                break;
            case MysqlRegisterTerrmDao.RESULT_MONEY_WRONG:
                JOptionPane.showMessageDialog(this, "Vui lòng điền tiền cọc hợp lệ");
                break;
            case MysqlRegisterTerrmDao.RESULT_END_DATE:
                JOptionPane.showMessageDialog(this, "Vui lòng chọn lại ngày hẹn trả sách");
                break;
            case MysqlRegisterTerrmDao.RESULT_OVER_DUE_CARD:
                JOptionPane.showMessageDialog(this, "Thẻ đã quá hạn , không thể mượn sách");
                break;
            case MysqlRegisterTerrmDao.RESULT_CARD_NO_ACTIVE:
                JOptionPane.showMessageDialog(this, "Tổng số sách mượn và đăng ký của bạn đang vượt quá 5 ,không thể mượn thêm , không thể mượn sách");
                break;
            case MysqlRegisterTerrmDao.RESULT_MAX_COUNT_BORROW:
                JOptionPane.showMessageDialog(this, "Ban , không thể mượn sách");
                break;

            case MysqlRegisterTerrmDao.RESULT_OVER_DUE_BORROW:
                JOptionPane.showMessageDialog(this, "Độc giả còn sách quá hạn chưa trả");
                break;
            case MysqlRegisterTerrmDao.RESULT_SUCCESS:
                JOptionPane.showMessageDialog(this, "Cho mượn thành công");
                int idLoan = registerTermController.getCountLoan();
                ConnectIreport connectIreport = new ConnectIreport();
                 {
                    try {
                        connectIreport.loanBook(idLoan + "", idCardDialog.getText(), "LoanBookIreport");
                    } catch (JRException | SQLException | IOException ex) {
                        Logger.getLogger(ReturnAndBorrowBookForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                userNameDialog.setText("");
                idCardDialog.setText("");
                idLibDialog.setText("");
                startDateDialog.setText("");
                endDateDialog.getEditor().setText("");
                moneyDialog.setText("");
                detailRegModelTable.setRowCount(0);
                if (tableRegisterBook.getSelectedRow() >= 0) {
                    registerTermController.getListModel().remove(tableRegisterBook.getSelectedRow());
                    regModelTable.removeRow(tableRegisterBook.getSelectedRow());

                }
                break;
            case MysqlRegisterTerrmDao.RESULT_SQLITE:
                JOptionPane.showMessageDialog(this, "Có lỗi trong quá trình mượn");
                break;

        }


    }//GEN-LAST:event_doBorrowDialogActionPerformed

    private void cancelDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelDialogActionPerformed
        // TODO add your handling code here:
        dialogLoan.setVisible(false);
    }//GEN-LAST:event_cancelDialogActionPerformed

    private void borrowBookButtonClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrowBookButtonClick
        // TODO add your handling code here:
        int result = borrowBookController.borrowBook(tablePanelUnregis, now, dateEndField.getDate(), idLib, moneyField.getText(), (String) idCardFieldUnRegis.getSelectedItem(), nameFieldUnRegis.getText());
        switch (result) {

            case BorrowBookController.RESULT_NO_BOOK_BORROW:
                JOptionPane.showMessageDialog(this, "Chưa có sách , hãy chọn thêm sách để có thể mượn");
                break;
            case BorrowBookController.RESULT_NO_SELECTED:
                JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu cần cho mượn");
                break;
            case BorrowBookController.RESULT_NULL_POINT:
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin");
                break;
            case BorrowBookController.RESULT_MONEY_WRONG:
                JOptionPane.showMessageDialog(this, "Vui lòng điền tiền cọc hợp lệ");
                break;
            case BorrowBookController.RESULT_END_DATE:
                JOptionPane.showMessageDialog(this, "Vui lòng chọn lại ngày hẹn trả sách");
                break;
            case BorrowBookController.RESULT_OVER_DUE_CARD:
                JOptionPane.showMessageDialog(this, "Thẻ đã quá hạn , không thể mượn sách");
                break;
            case BorrowBookController.RESULT_CARD_NO_ACTIVE:
                JOptionPane.showMessageDialog(this, "Thẻ chưa kích hoạt , không thể mượn sách");
                break;
            case BorrowBookController.RESULT_OVER_DUE_BORROW:
                JOptionPane.showMessageDialog(this, "Độc giả còn sách quá hạn chưa trả");
                break;
            case BorrowBookController.RESULT_MAX_COUNT_BORROW:
                JOptionPane.showMessageDialog(this, "Tổng số sách hiện mượn và đăng ký không được vượt quá 5");
                break;
            case BorrowBookController.RESULT_SUCCESS:
                JOptionPane.showMessageDialog(this, "Cho mượn thành công");
                int idLoan = registerTermController.getCountLoan();
                ConnectIreport connectIreport = new ConnectIreport();
                 {
                    try {
                        connectIreport.loanBook(idLoan + "", (String) idCardFieldUnRegis.getSelectedItem(), "LoanBookIreport");
                    } catch (JRException | SQLException | IOException ex) {
                        Logger.getLogger(ReturnAndBorrowBookForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                dateEndField.getEditor().setText("");
                moneyField.setText("");
                unregisDetailModelTable.setRowCount(0);
                break;
            case MysqlRegisterTerrmDao.RESULT_SQLITE:
                JOptionPane.showMessageDialog(this, "Có lỗi trong quá trình mượn");
                break;

        }

    }//GEN-LAST:event_borrowBookButtonClick

    private void addBookToRegisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBookToRegisActionPerformed
        // TODO add your handling code here:
        int count = borrowBookController.getCountBookCopyBorrowable((String) idBookFieldUnRegis.getSelectedItem());
        numberAvailable.setText(count + "");
        dialogCountBorrow.setVisible(true);


    }//GEN-LAST:event_addBookToRegisActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_jButton2ActionPerformed

    private void addBookToCart(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBookToCart
        // TODO add your handling code here:
        String inputCount = countBorrowFieldDialog.getText();
        if (!Utils.isStringInteger(inputCount)) {
            JOptionPane.showMessageDialog(this, "Mời nhập lại số lượng", "Thông báo", JOptionPane.ERROR_MESSAGE);
        } else {
            int count = Integer.parseInt(inputCount);
            if (count <= 0) {
                JOptionPane.showMessageDialog(this, "Bạn phải nhập số lượng lớn hơn 0", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } else {
                if (count + tablePanelUnregis.getRowCount() > 5) {
                    JOptionPane.showMessageDialog(this, "Số lượng sách có thể mượn tối đa là 5 quyển", "Thông báo", JOptionPane.ERROR_MESSAGE);
                } else {
                    borrowBookController.addListBookBorrow((String) idBookFieldUnRegis.getSelectedItem(), count, unregisDetailModelTable, (String) idCardFieldUnRegis.getSelectedItem(), nameFieldUnRegis.getText());
                    dialogCountBorrow.setVisible(false);
                }
            }

        }

    }//GEN-LAST:event_addBookToCart

    private void deleteAllTableUnreg(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAllTableUnreg
        // TODO add your handling code here:
        borrowBookController.returnStatusBook(tablePanelUnregis);
        unregisDetailModelTable.setRowCount(0);

    }//GEN-LAST:event_deleteAllTableUnreg

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:\
        int row = tablePanelUnregis.getSelectedRow();
        if (row >= 0) {
            String idCopy = (String) tablePanelUnregis.getValueAt(row, 0);
            borrowBookController.updateStatusRegistedInLibraryBookCopyById(Constants.STATUS_BORROWABLE, idCopy);
            unregisDetailModelTable.removeRow(row);
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void searchRegisBook(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchRegisBook
        // TODO add your handling code here:
        String key = searchRegisterField.getText();
        detailRegModelTable.setRowCount(0);
        registerTermController.disPlayListSearch(key, regModelTable);

    }//GEN-LAST:event_searchRegisBook

    private void returnAllBookClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnAllBookClick
        // TODO add your handling code here:
        int result = controller.returnLoan(tableLoan, idLib, moneyReturn.getText());
        switch (result) {
            case LoanController.RETURN_RESULT_NULL_POINT:
                JOptionPane.showMessageDialog(this, "Có lỗi gì đó , hãy thử lại sau");
                break;
            case LoanController.RETURN_RESULT_MONEY_WRONG:
                JOptionPane.showMessageDialog(this, "Nhập lại tiền phạt,nhập 0 nếu là không phạt");
                break;
            case LoanController.RETURN_RESULT_COUNT_SELECT:
                JOptionPane.showMessageDialog(this, "Hãy chọn phiếu cần trả");
                break;
            case LoanController.RETURN_RESULT_SUCCESS:
                JOptionPane.showMessageDialog(this, "Trả thành công, trạng thái của sách được cập nhật");
                detailLoanModelTable.setRowCount(0);
                break;
            case LoanController.RETURN_RESULT_SQLITE:
                JOptionPane.showMessageDialog(this, "Vui lòng  kiểm tra lại kết nối mạng");
                break;

        }

    }//GEN-LAST:event_returnAllBookClick

    private void seachLoanToReturnClick(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seachLoanToReturnClick
        // TODO add your handling code here:
        controller.disPlayListSearch(searchLoanField.getText(), loanModelTable);
    }//GEN-LAST:event_seachLoanToReturnClick

    private void jScrollPane3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane3MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jScrollPane3MouseClicked

    private void deleteMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMenuActionPerformed
        // TODO add your handling code here:
        deleteRegistedBorrow();

    }//GEN-LAST:event_deleteMenuActionPerformed
    private void deleteRegistedBorrow() {
        int row = tableRegisterBook.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Hãy chọn phiếu mượn cần xóa");
        } else {
            int countRow = tableDetailRegisterBook.getRowCount();
            int checkCount = 0;
            if (bcc.unAllRegisteBookCopy(tableRegisterBook.getValueAt(row, 1).toString())) {
                for (int i = 0; i < countRow; i++) {
                    bcc.updateStatusUnRegistedBookCopy(tableDetailRegisterBook.getValueAt(i, 1).toString());
                    checkCount++;
                }
                if (checkCount == countRow) {
                    JOptionPane.showMessageDialog(this, "Xóa phiếu thành công");
                    detailRegModelTable.setRowCount(0);
                    regModelTable.removeRow(row);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Xóa phiếu thất bại.Vui lòng thử lại sau");
            }

        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBookToRegis;
    private javax.swing.JTextField authorFieldUnRegis;
    private javax.swing.JButton borrowBook;
    private javax.swing.JButton borrowBook1;
    private javax.swing.JButton cancelDialog;
    private javax.swing.JTextField catFieldUnRegis;
    private javax.swing.JTextField countBorrowFieldDialog;
    private javax.swing.JTextField dateBorrowField;
    private org.jdesktop.swingx.JXDatePicker dateEndField;
    private javax.swing.JButton deleteAll;
    private javax.swing.JButton deleteButton;
    private javax.swing.JMenuItem deleteMenu;
    private javax.swing.JDialog dialogCountBorrow;
    private javax.swing.JDialog dialogLoan;
    private javax.swing.JButton doBorrowDialog;
    private javax.swing.JButton doSearch;
    private org.jdesktop.swingx.JXDatePicker endDateDialog;
    private javax.swing.JComboBox<String> idBookFieldUnRegis;
    private javax.swing.JTextField idCardDialog;
    private javax.swing.JComboBox<String> idCardFieldUnRegis;
    private javax.swing.JTextField idLibDialog;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField mailFieldUnRegis;
    private javax.swing.JTextField moneyDialog;
    private javax.swing.JTextField moneyField;
    private javax.swing.JTextField moneyReturn;
    private javax.swing.JTextField nameBookFieldUnRegis;
    private javax.swing.JTextField nameFieldUnRegis;
    private javax.swing.JLabel numberAvailable;
    private javax.swing.JTextField phoneFieldUnRegis;
    private javax.swing.JTextField pubFieldUnRegis;
    private javax.swing.JPanel registedBookPanel;
    private javax.swing.JPanel registedBookPanel1;
    private javax.swing.JPanel returnBookPanel;
    private javax.swing.JTextField searchLoanField;
    private javax.swing.JTextField searchRegisterField;
    private javax.swing.JTextField startDateDialog;
    private javax.swing.JTable tableDetailLoan;
    private javax.swing.JTable tableDetailRegisterBook;
    private javax.swing.JTable tableLoan;
    private javax.swing.JTable tablePanelUnregis;
    private javax.swing.JTable tableRegisterBook;
    private javax.swing.JPanel unRegistedBookPanel;
    private javax.swing.JTextField userNameDialog;
    // End of variables declaration//GEN-END:variables

}
