/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookcopy.controller;

import book.model.Book;
import static bookcopy.dao.MysqlBookCopyDao.RESULT_EXCEPTION_PRICE;
import static bookcopy.dao.MysqlBookCopyDao.RESULT_NULL_POINT;
import bookcopy.model.BookCopy;
import card.controller.CardController;
import category.model.Category;
import com.mysql.cj.util.StringUtils;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import loan.model.Loan;
import loan.model.LoanDetail;
import publisher.model.Publisher;
import registeterm.model.RegisterBorrow;
import registeterm.model.RegisterTermDetail;
import util.Constants;
import static util.Constants.BORROWABLE;
import static util.Constants.BORROW_BIGGER_BOOKCOPYABLE;
import static util.Constants.BORROW_OVER_FIVE_BOOKCOPY;
import static util.Constants.BORROW_OVER_FIVE_BOOKCOPY_BOTH_BORROWED;
import static util.Constants.BORROW_OVER_FIVE_BOOKCOPY_BOTH_REGISTED_BOOK;
import static util.Constants.BORROW_OVER_FIVE_BOOKCOPY_BOTH_REGISTED_BOOK_AND_BORROWED;
import static util.Constants.EXPIRED_CARD;
import static util.Constants.LIST_BOOKCOPY_BORROWBYID_IS_NULL;
import static util.Constants.NO_ACTIVE_CARD;
import static util.Constants.NO_SELECT_ROW_TABLE;
import static util.Constants.OVER_DUE_BOOKCOPY;
import static util.Constants.SUCCESS;
import util.Utils;

/**
 *
 * @author Linh
 */
public class BookCopyController {

    private List<Book> listBook;
    private List<Category> listCat;
    private List<Publisher> listPub;

    public BookCopyController() {
        model = new BookCopy.BookCopyBuilder().build();
        modelCat = new Category();
        modelPub = new Publisher();
        listBook = new ArrayList<>();
        listCat = getListCategorys();
        listPub = getListPublishers();
    }

    private Category modelCat;
    private Publisher modelPub;
    private BookCopy model;

    /**
     * Dùng để thực hiện thêm các bản copy của sách
     *
     * @param id mã sách cần thêm bản copy
     * @param countCopy số lượng bản copy cần thêm
     * @param price giá của bản copy
     * @param type kiểu của bản copy : Có thể mượn hoặc Tham Khảo
     * @return 0 nếu các trường thông tin bị thiếu 1 nếu trường giá của bản copy
     * là không hợp lệ 2 nếu thêm thành công 3 nếu thất bại, lỗi do kết nối csdl
     * hoặc lỗi câu truy vấn
     */
    public int addBookCopies(String id, int countCopy, String price, String type) {
        if (StringUtils.isNullOrEmpty(id) || StringUtils.isNullOrEmpty(price) || StringUtils.isNullOrEmpty(type)) {
            System.out.println(StringUtils.isNullOrEmpty(id) + " " + StringUtils.isNullOrEmpty(price) + " " + StringUtils.isNullOrEmpty(type));
            return RESULT_NULL_POINT;
        }
        if (!Utils.isStringDouble(price)) {
            return RESULT_EXCEPTION_PRICE;
        }
        return model.addBookCopies(id, countCopy, Double.parseDouble(price), type);
    }

    /**
     * Hàm này dùng để sinh id để thêm sách mới
     *
     * @param selectIndex chỉ mục được chọn của combobox thể loại sách
     * @return null nếu chỉ mục không hợp lệ mã sách nếu chỉ mục là hợp lệ
     */
    public String generateIdBook(int selectIndex) {
        if (selectIndex < 0 || selectIndex >= listCat.size()) {
            return null;
        }
        return model.generateIdBook(listCat.get(selectIndex).getId());
    }

    /**
     * hàm này dùng để hiện thị danh sách thể loại vào tất cả combobox
     *
     * @param comboBoxs danh sách combobox cần hiển thị
     */
    public void displayListCategory(JComboBox... comboBoxs) {
        if (listCat != null) {
            for (JComboBox comboBox : comboBoxs) {
                if (comboBox != null) {
                    comboBox.removeAllItems();
                    for (int i = 0; i < listCat.size(); i++) {
                        comboBox.addItem(listCat.get(i).getCat());
                    }
                }
            }

        }
    }

    public void displayListCategoryForSearch(JComboBox... comboBoxs) {
        if (listCat != null) {
            for (JComboBox comboBox : comboBoxs) {
                if (comboBox != null) {
                    comboBox.removeAllItems();
                    comboBox.addItem("All");
                    for (int i = 0; i < listCat.size(); i++) {
                        comboBox.addItem(listCat.get(i).getCat());
                    }
                } else {
                    comboBox.addItem("All");
                }
            }

        }
    }

    /**
     * hàm này dùng để hiện thị danh sách nhà xuất bản vào tất cả combobox
     *
     * @param comboBoxs danh sách combobox cần hiển thị
     */
    public void displayListPublisher(JComboBox... comboBoxs) {
        if (listCat != null) {
            for (JComboBox comboBox : comboBoxs) {
                if (comboBox != null) {
                    comboBox.removeAllItems();
                    for (int i = 0; i < listPub.size(); i++) {
                        comboBox.addItem(listPub.get(i).getPublisher());
                    }
                }
            }

        }
    }

    public void displayListPublisherForSearch(JComboBox... comboBoxs) {
        if (listCat != null) {
            for (JComboBox comboBox : comboBoxs) {
                if (comboBox != null) {
                    comboBox.removeAllItems();
                    comboBox.addItem("All");
                    for (int i = 0; i < listPub.size(); i++) {
                        comboBox.addItem(listPub.get(i).getPublisher());
                    }
                } else {
                    comboBox.addItem("All");
                }
            }

        }
    }

    public void displayListBook(DefaultTableModel tableModel, List<Book> listBook) {

        if (tableModel != null) {
            tableModel.setRowCount(0);
            System.out.println("" + listBook.size());
            for (int i = 0; i < listBook.size(); i++) {
                String id = listBook.get(i).getId();
                String title = listBook.get(i).getTitle();
                String author = listBook.get(i).getAuthor();
                Publisher publisher = listBook.get(i).getPublisher();
                Category category = listBook.get(i).getCategory();
                int countCopy = listBook.get(i).getListCopies().size();
                tableModel.insertRow(i, new Object[]{id, title, author, publisher.getPublisher(), category.getCat(), countCopy});
            }

        }
    }

    public void addBookToTable(DefaultTableModel tableModel, Book book) {
        if (book != null && tableModel != null) {
            tableModel.addRow(new Object[]{book.getId(), book.getTitle(), book.getAuthor(), book.getPublisher().getPublisher(), book.getCategory().getCat(), 0});
        }
    }

    /**
     * hàm này truy nhập csdl lấy ra danh sách thể loại sách
     *
     * @return Tập các thể loại sách
     */
    private List<Category> getListCategorys() {
        return modelCat.getAll();
    }

    /**
     * hàm này truy nhập csdl lấy ra danh sách nhà xuất bản
     *
     * @return Tập các nhà xuất bản
     */
    private List<Publisher> getListPublishers() {
        return modelPub.getAll();
    }

    //Lấy thông tin loại sách
    public String getTypeBookCopy(String idCopy) {
        return model.getTypeBookCopy(idCopy);
    }

    public List<Book> getAll() {
        return model.getAll();
    }

    //Lấy thông tin tình trạng hiện tại của sách
    public int getStatusBookCopy(String idCopy) {
        return model.getStatusBookCopy(idCopy);
    }

    public boolean createRegisteBook(int id, String idCopy, String name, Date dateNow) {
        return model.createRegisteBook(id, idCopy, name, dateNow);
    }

    public boolean updateStatusRegistedBookCopy(String idCopy) {
        return model.updateStatusRegistedBookCopy(idCopy);
    }

    public boolean updateStatusUnRegistedBookCopy(String idCopy) {
        return model.updateStatusUnRegistedBookCopy(idCopy);
    }

    public List<BookCopy> getAllBookCopyById(String idBook) {
        return model.getAllBookCopyById(idBook);
    }

    public void loadBookCopyToTable(JTable jtb, List<BookCopy> listBookCopys) {
        DefaultTableModel dtm = (DefaultTableModel) jtb.getModel();
        dtm.setNumRows(0);
        String status = "";
        if (listBookCopys.isEmpty()) {
            dtm.addRow(new Object[]{"", "", "", ""});
        } else {
            for (int i = 0; i < listBookCopys.size(); i++) {
                BookCopy bookCopy = listBookCopys.get(i);
                int status_Int = bookCopy.getStatus();
                switch (status_Int) {
                    case Constants.STATUS_BORROWABLE:
                        status = Constants.STATUS_BORROWABLE1;
                        break;
                    case Constants.STATUS_BORROWED:
                        status = Constants.STATUS_BORROWED_STRING;
                        break;
                    case Constants.STATUS_REGISTED:
                        status = Constants.TYPE_REGISTEBORROW;
                        break;
                    case Constants.STATUS_REGISTED_IN_LIBRARY:
                        status = Constants.STATUS_REGISTED_IN_LIBRARY_STRING;
                }
                dtm.addRow(new Object[]{bookCopy.getCopyNumber(), status, bookCopy.getType(), bookCopy.getPrice()});
            }
        }
        jtb.setModel(dtm);
    }

    public void displayDetailRegistedBookCopy(JTable jtb, List<RegisterBorrow> listRegisteBorrow) {
        DefaultTableModel dtm = (DefaultTableModel) jtb.getModel();
        dtm.setNumRows(0);
        jtb.setModel(dtm);
        if (listRegisteBorrow.isEmpty()) {
            dtm.addRow(new Object[]{"", "", "Bạn chưa mượn sách nào", ""});
        } else {
            System.out.println(listRegisteBorrow.size());
            for (int i = 0; i < listRegisteBorrow.size(); i++) {
                RegisterBorrow rbt = listRegisteBorrow.get(i);
                Date regDate = rbt.getRegDate();
                Date deadlineDate = new Date(regDate.getTime() + 2 * 24 * 3600 * 1000);
                List<RegisterTermDetail> list = rbt.getDetails();
                System.out.println(list.size());
                for (int j = 0; j < list.size(); j++) {
                    dtm.addRow(new Object[]{list.get(j).getIdCopy(), list.get(j).getTitle(), Utils.format(regDate), Utils.format(deadlineDate)});
                }
            }
        }
        jtb.setModel(dtm);
    }

    public void displayDetailLoanBook(JTable jtb, List<Loan> listLoanBook) {
        DefaultTableModel dtm = (DefaultTableModel) jtb.getModel();
        dtm.setNumRows(0);
        jtb.setModel(dtm);
        if (listLoanBook.isEmpty()) {
            dtm.addRow(new Object[]{"", "", "Bạn chưa mượn sách nào", ""});
        } else {
            System.out.println(listLoanBook.size());
            for (int i = 0; i < listLoanBook.size(); i++) {
                Loan loan = listLoanBook.get(i);
                Date startDate = loan.getStartDate();
                Date endDate = loan.getEndDate();
//                Double money = loan.getMoney();
                List<LoanDetail> listLoanDetails = loan.getDetails();
                for (int j = 0; j < listLoanDetails.size(); j++) {
                    dtm.addRow(new Object[]{listLoanDetails.get(j).getIdCopy(), listLoanDetails.get(j).getTitle(), Utils.format(startDate), Utils.format(endDate)});
                }
            }
        }
        jtb.setModel(dtm);
    }

    public void deleteRowInTable(JTable jtb, int index) {
        DefaultTableModel dtm = (DefaultTableModel) jtb.getModel();
        dtm.removeRow(index);
        jtb.setModel(dtm);
    }

    public void updateInforBookCopy(JTable tableCopy, String id, String title, String author, String publisher) {
        if (tableCopy != null) {
            for (int i = 0; i < tableCopy.getRowCount(); i++) {
                if (tableCopy.getValueAt(i, 0).toString().equals(id)) {
                    tableCopy.setValueAt(title, i, 1);
                    tableCopy.setValueAt(author, i, 2);
                    tableCopy.setValueAt(publisher, i, 3);
                    break;
                }
            }
        }
    }

    public void updateCountBookCopy(JTable tableInfor, String id, int count) {
        if (tableInfor != null) {
            for (int i = 0; i < tableInfor.getRowCount(); i++) {
                if (tableInfor.getValueAt(i, 0).toString().equals(id)) {
                    int countCopy = (Integer) tableInfor.getValueAt(i, 5);
                    tableInfor.setValueAt(count + countCopy, i, 5);
                    break;
                }
            }
        }

    }

    public void deleteAllRowInTable(JTable jtb) {
        DefaultTableModel dtm = (DefaultTableModel) jtb.getModel();
        dtm.setRowCount(0);
        jtb.setModel(dtm);
    }

    public String getCountBookCopyById(String idBook) {
        return model.getCountBookCopyById(idBook);
    }

    public List<BookCopy> getBookCopyListBorrowableById(String idBook) {
        return model.getBookCopyListBorrowableById(idBook);
    }

    public int generateIdRegiste() {
        return model.generateIdRegiste();
    }

    public void addBookCopyToBasket(JTable jtb_book, int index, JTable jtb_basket,
            List<String> listBookCopyInBasket, List<BookCopy> listBookCopy, int countBookBorrow) {
        DefaultTableModel dtm = (DefaultTableModel) jtb_basket.getModel();
        int checkCount = 0;
        for (int i = 0; i < listBookCopy.size(); i++) {
            int check = 0;
            for (int j = 0; j < listBookCopyInBasket.size(); j++) {
                if (listBookCopy.get(i).getCopyNumber().equals(listBookCopyInBasket.get(j))) {
                    break;
                } else {
                    check++;
                }
            }
            if (check == listBookCopyInBasket.size()) {
                updateStatusRegistedBookCopy(listBookCopy.get(i).getCopyNumber());
                dtm.addRow(new Object[]{listBookCopy.get(i).getCopyNumber(), jtb_book.getValueAt(index, 1).toString()});
                checkCount++;
                if (checkCount == countBookBorrow) {
                    break;
                }
            }
        }
        jtb_basket.setModel(dtm);
    }

    public boolean registeBookCopy(int id, String idCopy, String title) {
        return model.registeBookCopy(id, idCopy, title);
    }

    public boolean unRegisteBookCopy(String idCopy) {
        return model.unRegisteBookCopy(idCopy);
    }

    /**
     * Lấy ra tổng số sách đã mượn chưa trả và không quá hạn của độc giả
     *
     * @param idCard mã thẻ mượn
     * @return số lượng cần tìm
     */
    public int getCountBookCopyUnPainAndNoOutDate(String idCard) {
        return model.getCountBookCopyUnPainAndNoOutDate(idCard);
    }

    public List<RegisterBorrow> getRegistedBookCopyLisrByCardNo(String idCard) {
        return model.getRegistedBookCopyLisrByCardNo(idCard);
    }

    public int getIdRegistedByIdBookCopy(String idCopy) {
        return model.getIdRegistedByIdBookCopy(idCopy);
    }

    public int getCountBookCopySameIdRegisted(int idRegisted) {
        return model.getCountBookCopySameIdRegisted(idRegisted);
    }

    /**
     * Dùng để hủy đăng ký toàn bộ sách copy trong phiếu mượn của độc giả
     *
     * @param idCard mã thẻ mượn
     * @return true nếu hủy thành công false nếu hủy thất bại do kết nối csdl,
     * câu truy vấn
     */
    public boolean unAllRegisteBookCopy(String idCard) {
        return model.unAllRegisteBookCopy(idCard);
    }

    public List<Loan> getListLoanBook(String cardNo) {
        return model.getListLoanBook(cardNo);
    }

    /**
     * Dùng để lấy số lượng sách độc giả đã đăng ký
     *
     * @param cardNo mã thẻ mượn
     * @return số lượng sách độc giả gửi đăng ký
     */
    public int getCountRegistedBookCopyByCardNo(String cardNo) {
        return model.getCountRegistedBookCopyByCardNo(cardNo);
    }

    /**
     * Dùng để cập nhật thông tin sách copy
     *
     * @param idCopy Mã sách copy
     * @param status Tình trạng của sách
     * @param typeOld Loại sách ban đầu
     * @param typeNew Loại sách cần cập nhật
     * @param price Giá sách cần cập nhật
     * @return Kết quả cập nhật dạng int
     */
    public int updateInforBookCopy(String idCopy, String status, String typeOld, String typeNew, double price) {
        return model.updateInforBookCopy(idCopy, status, typeOld, typeNew, price);
    }

    public int addBookCopy(int index, String idCard, JTable jTable_searchBookCopy, int countBookBorrow, JTable jTable_regisBookCopyBasket) {
        CardController cc = new CardController();
        String cardNo = cc.getCardNoById(idCard);
        String idBook = jTable_searchBookCopy.getValueAt(index, 0).toString();
        List<BookCopy> listBookCopy = getBookCopyListBorrowableById(idBook);
        int countBookCopyUnPainAndNoOutDate = getCountBookCopyUnPainAndNoOutDate(cardNo);
        int countRegistedBookCopy = getCountRegistedBookCopyByCardNo(cardNo);
        int countBookCopyInBasket = jTable_regisBookCopyBasket.getRowCount();
        List<String> listBookCopyInBasket = getListBookCopyInBasket(jTable_regisBookCopyBasket);
        if (countBookCopyUnPainAndNoOutDate == 0) {
            if (countRegistedBookCopy == 0) {
                //Nếu tổng số sách đăng ký trong giỏ với số sách nhập thêm để đăng ký lớn hơn 5
                // thì cho nhập lại
                if (countBookCopyInBasket + countBookBorrow > 5) {
                    return BORROW_OVER_FIVE_BOOKCOPY;
                } else {
                    addBookCopyToBasket(jTable_searchBookCopy, index, jTable_regisBookCopyBasket,
                            listBookCopyInBasket, listBookCopy, countBookBorrow);
                    setCountBookCopyBorrowable(index, getBookCopyListBorrowableById(idBook), jTable_searchBookCopy);
                    return SUCCESS;
                }
            } else {
                if (countRegistedBookCopy + countBookCopyInBasket + countBookBorrow > 5) {
                    JOptionPane.showMessageDialog(null, "Hiện tại bạn đã đăng ký " + countRegistedBookCopy + " quyển sách.Bạn chỉ có thể đănng ký tối đa " + (5 - countRegistedBookCopy) + "quyển.");                                           
                    return BORROW_OVER_FIVE_BOOKCOPY_BOTH_REGISTED_BOOK;
                } else {
                    addBookCopyToBasket(jTable_searchBookCopy, index, jTable_regisBookCopyBasket,
                            listBookCopyInBasket, listBookCopy, countBookBorrow);
                    setCountBookCopyBorrowable(index, getBookCopyListBorrowableById(idBook), jTable_searchBookCopy);
                    return SUCCESS;
                }
            }
        } else {
            if (countRegistedBookCopy == 0) {
                if (countBookCopyUnPainAndNoOutDate + countBookCopyInBasket + countBookBorrow > 5) {
                    JOptionPane.showMessageDialog(null, "Hiện tại bạn đang mượn " + countBookCopyUnPainAndNoOutDate + " quyển chưa trả và đã đăng ký mượn " + countRegistedBookCopy + " quyển.Bạn chỉ có thể đănng ký mượn tối đa " + (5 - countBookCopyUnPainAndNoOutDate - countRegistedBookCopy) + " quyển");                                           
                    return BORROW_OVER_FIVE_BOOKCOPY_BOTH_BORROWED;
                } else {
                    addBookCopyToBasket(jTable_searchBookCopy, index, jTable_regisBookCopyBasket,
                            listBookCopyInBasket, listBookCopy, countBookBorrow);
                    setCountBookCopyBorrowable(index, getBookCopyListBorrowableById(idBook), jTable_searchBookCopy);
                    return SUCCESS;
                }
            } else {
                if (countBookCopyUnPainAndNoOutDate + countRegistedBookCopy + countBookCopyInBasket + countBookBorrow > 5) {
                    JOptionPane.showMessageDialog(null, "Hiện tại bạn đang mượn " + countBookCopyUnPainAndNoOutDate + " quyển chưa trả và đã đăng ký mượn " + countRegistedBookCopy + " quyển.Bạn chỉ có thể đănng ký mượn tối đa " + (5 - countBookCopyUnPainAndNoOutDate - countRegistedBookCopy) + " quyển");                                            
                    return BORROW_OVER_FIVE_BOOKCOPY_BOTH_REGISTED_BOOK_AND_BORROWED;
                } else {
                    addBookCopyToBasket(jTable_searchBookCopy, index, jTable_regisBookCopyBasket,
                            listBookCopyInBasket, listBookCopy, countBookBorrow);
                    setCountBookCopyBorrowable(index, getBookCopyListBorrowableById(idBook), jTable_searchBookCopy);
                    return SUCCESS;
                }
            }
        }
    }

    public int checkUserWhenRegisteBookCopy(int index, String idCard, JTable jTable_searchBookCopy) {
        if (index < 0) {
            return NO_SELECT_ROW_TABLE;
        } else {
            CardController cc = new CardController();
            String cardNo = cc.getCardNoById(idCard);
            if (cardNo.equalsIgnoreCase("null")) {
                return NO_ACTIVE_CARD;
            } else {
                int statusCard = cc.getStatusCardById(idCard);
                if (statusCard == 0) {
                    return EXPIRED_CARD;
                } else {
                    if (cc.checkUseOverDueBookCopy(cardNo) > 0) {
                        return OVER_DUE_BOOKCOPY;
                    } else {
                        return BORROWABLE;
                    }
                }
            }
        }
    }

    public List<String> getListBookCopyInBasket(JTable jTable_regisBookCopyBasket) {
        List<String> listIdBookCopy = new ArrayList<>();
        int countRow = jTable_regisBookCopyBasket.getRowCount();
        for (int i = 0; i < countRow; i++) {
            listIdBookCopy.add(jTable_regisBookCopyBasket.getValueAt(i, 0).toString());
        }
        return listIdBookCopy;
    }

    public void setCountBookCopyBorrowable(int index, List<BookCopy> listBookCopy, JTable jTable_searchBookCopy) {
        DefaultTableModel dtm = (DefaultTableModel) jTable_searchBookCopy.getModel();
        dtm.setValueAt(listBookCopy.size(), index, 5);
        jTable_searchBookCopy.setModel(dtm);
    }

    public int checkCountBookCopyBorrowable(String idBook) {
        List<BookCopy> listBookCopys = getBookCopyListBorrowableById(idBook);
        return listBookCopys.size();
    }
}
