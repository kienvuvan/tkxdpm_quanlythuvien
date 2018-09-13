/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookcopy.controller;

import book.model.Book;
import bookcopy.model.BookCopy;
import card.model.BorrowCard;
import com.mysql.cj.util.StringUtils;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import loan.model.Loan;
import loan.model.LoanDetail;
import user.User;
import util.Constants;
import util.Utils;

/**
 *
 * @author Linh
 */
public class BorrowBookController {

    private BookCopy copyModel;
    private BorrowCard cardModel;
    private List<String> listIdModel;
    private List<String> listIdUserModel;
    public static final int RESULT_NO_BOOK_BORROW = -1;
    public static final int RESULT_NO_SELECTED = 0;
    public static final int RESULT_NULL_POINT = 1;
    public static final int RESULT_MONEY_WRONG = 2;
    public static final int RESULT_END_DATE = 3;
    public static final int RESULT_OVER_DUE_CARD = 4;
    public static final int RESULT_CARD_NO_ACTIVE = 5;
    public static final int RESULT_OVER_DUE_BORROW = 6;
    public static final int RESULT_MAX_COUNT_BORROW = 7;
    public static final int RESULT_SUCCESS = 8;
    public static final int RESULT_SQLITE = 9;

    public BorrowBookController() {
        copyModel = new BookCopy.BookCopyBuilder().build();
        cardModel = new BorrowCard();
        listIdModel = copyModel.getAllIdBook();
        listIdUserModel = cardModel.getAllIdCard();

    }

    public void displayInforBook(JComboBox idBookCb, JComboBox idCardComboBox) {
        if (idBookCb != null && listIdModel != null) {
            idBookCb.removeAllItems();
            for (int i = 0; i < listIdModel.size(); i++) {
                idBookCb.addItem(listIdModel.get(i));
            }
        }
        if (idCardComboBox != null && listIdUserModel != null) {
            idCardComboBox.removeAllItems();
            for (int i = 0; i < listIdUserModel.size(); i++) {
                idCardComboBox.addItem(listIdUserModel.get(i));
            }
        }

    }

    public Book getBookById(String id) {
        return copyModel.getBookById(id);
    }

    public User getUserByCardNo(String cardNo) {
        return cardModel.getUserByCardNo(cardNo);
    }

    public int getCountBookCopyBorrowable(String id) {
        return copyModel.getBookCopyListBorrowableById(id).size();
    }
    
    /**
     * Dùng để thực hiện chức năng cho mượn sách từ phiếu đăng ký mượn offline ngay tại thư viện
     * @param tableDetailLoan bảng chứa thông tin chi tiết của phiếu mượn
     * @param dateBorrow ngày mượn  
     * @param endDate ngày trả
     * @param idLib mã thủ thư 
     * @param money tiền cọc
     * @param cardNo mã thẻ mượn
     * @param name  tên độc giả mượn
     * @return -1 nếu thủ thư chưa chọn phiếu để tiến hành cho mượn
     *          1 nếu các trường thông tin chưa đủ
     *          2 nếu tiền cọc không hợp lệ 
     *          3 nếu hạn trả không hợp lệ
     *          4 nếu thẻ chưa kích hoạt
     *          5 nếu thẻ đã quá hạn
     *          6 nếu độc giả có sách quá hạn chưa trả
     *          7 nếu tổng của sách chuẩn bị mượn và sách độc giả đã mượn lớn hơn 5
     *          8 mượn thành công
     *          9 mượn thất bại do lỗi kết nối csdl
     *          
     */
    public int borrowBook(JTable tableDetailLoan, Date dateBorrow, java.util.Date endDate, String idLib, String money, String cardNo, String name) {
        if (StringUtils.isNullOrEmpty(money)
                || StringUtils.isNullOrEmpty(cardNo) || StringUtils.isNullOrEmpty(name) || StringUtils.isNullOrEmpty(idLib)
                || tableDetailLoan == null || endDate == null || dateBorrow == null) {
            System.out.println(money + " " + cardNo + " " + name + " " + idLib + " " + dateBorrow.toString() + " " + (endDate == null));
            return RESULT_NULL_POINT;
        }
        if (tableDetailLoan.getRowCount() == 0) {
            return RESULT_NO_BOOK_BORROW;
        }
        if (!Utils.isStringDouble(money)) {
            return RESULT_MONEY_WRONG;

        }

        if (endDate.before(dateBorrow)) {
            return RESULT_END_DATE;
        }
        int status = cardModel.getStatusCardByCardNo(cardNo);
        if (status == Constants.STATUS_UNACTIVED) {
            return RESULT_CARD_NO_ACTIVE;
        }
        if(cardModel.checkCardOverDueExpired(cardNo)){
            return RESULT_OVER_DUE_CARD;
        }
        
        if (cardModel.checkUserOverDueBookCopy(cardNo) > 0) {
            return RESULT_OVER_DUE_BORROW;
        }
        int countBorrow = cardModel.getCountBookBorrowAndRegisted(cardNo);
        if (countBorrow + tableDetailLoan.getRowCount() > 5) {
            return RESULT_MAX_COUNT_BORROW;
        }
        List<LoanDetail> detail = createListFromTable(tableDetailLoan);
        Loan loan = new Loan.Builder()
        .setCardNo(cardNo)
        .setName(name)
        .setIdLib(idLib)
        .setStartDate(dateBorrow)
        .setEndDate(new Date(endDate.getTime()))
        .setDetails(detail)
        .setMoney(Double.parseDouble(money)).build();
        return loan.borrowBook();
    }

    private List<LoanDetail> createListFromTable(JTable tableDetailLoan) {
        List<LoanDetail> results = new ArrayList<>();
        for (int i = 0; i < tableDetailLoan.getRowCount(); i++) {
            LoanDetail detail = new LoanDetail.Builder()
                    .setIdCopy((String) tableDetailLoan.getValueAt(i, 0))
                    .setTitle((String) tableDetailLoan.getValueAt(i, 1))
                    .setIsReturn(false).build();
            results.add(detail);
        }
        return results;
    }

    public void addListBookBorrow(String id, int count, DefaultTableModel loanDetailModelTable, String cardNo, String nameUser) {
        if (id != null && loanDetailModelTable != null && count > 0) {
            List<BookCopy> listAddToTable = copyModel.getListCopyBorrowByCount(id, count);
            if (listAddToTable != null) {
                for (int i = 0; i < listAddToTable.size(); i++) {
                    BookCopy item = listAddToTable.get(i);
                    if (item.updateStatusRegistedInLibraryBookCopy(Constants.STATUS_REGISTED_IN_LIBRARY, item.getCopyNumber())) {
                        loanDetailModelTable.insertRow(loanDetailModelTable.getRowCount(), new Object[]{item.getCopyNumber(), item.getTitle(), cardNo, nameUser});
                    }

                }
            }
        }
    }

    public void returnStatusBook(JTable tableDetailLoan) {
        if (tableDetailLoan != null && tableDetailLoan.getRowCount() > 0) {

            for (int i = 0; i < tableDetailLoan.getRowCount(); i++) {
                String idCopy = (String) tableDetailLoan.getValueAt(i, 0);
                updateStatusRegistedInLibraryBookCopyById(Constants.STATUS_BORROWABLE, idCopy);

            }
        }
    }

    public void updateStatusRegistedInLibraryBookCopyById(int status, String idCopy) {
        copyModel.updateStatusRegistedInLibraryBookCopy(status, idCopy);
    }

}
