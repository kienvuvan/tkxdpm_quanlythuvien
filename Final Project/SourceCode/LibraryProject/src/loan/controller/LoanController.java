/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loan.controller;

import com.mysql.cj.util.StringUtils;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import loan.dao.MysqlLoanDao;
import loan.model.Loan;
import loan.model.LoanDetail;
import loan.model.LoanReturn;
import loan.model.LoanReturnDetail;
import registeterm.model.RegisterBorrow;
import util.Utils;

/**
 *
 * @author Linh
 */
public class LoanController {

    private Loan model;
    private LoanReturn returnModel;
    private List<Loan> listModel;

    public static final int RETURN_RESULT_NULL_POINT = 0;
    public static final int RETURN_RESULT_NO_COUNT_IN_TABLE = 1;
    public static final int RETURN_RESULT_MONEY_WRONG = 2;
    public static final int RETURN_RESULT_COUNT_SELECT = 3;
    public static final int RETURN_RESULT_NO_BOOK_SELECT = 4;
    public static final int RETURN_RESULT_SUCCESS = 5;
    public static final int RETURN_RESULT_SQLITE = 6;

    public LoanController() {
        model = new Loan.Builder().build();
        returnModel = new LoanReturn.LoanReturnBuilder().build();
        listModel = new ArrayList<>();
        listModel = model.getAllLoan();
    }

    public void displayAllLoan(DefaultTableModel loanModelTable) {
        if (loanModelTable != null) {

            if (listModel != null) {
                for (int i = 0; i < listModel.size(); i++) {
                    Loan loan = listModel.get(i);
                    loanModelTable.insertRow(i, new Object[]{loan.getId(),
                        loan.getCardNo(), loan.getIdLib(), loan.getName(),
                        loan.getStartDate(), loan.getEndDate(), loan.getMoney()});
                }
            }
        }
    }

    public void displayDetailLoan(int rowClick, DefaultTableModel detailModelTable) {
        System.out.println("" + rowClick + " " + listModel.size());
        if (rowClick >= 0 && rowClick < listModel.size() && detailModelTable != null) {
            int idLoan = listModel.get(rowClick).getId();
            List<LoanDetail> details = model.getListLoanDetailById(idLoan);
            detailModelTable.setRowCount(0);
            System.out.println("" + details.size());
            for (int i = 0; i < details.size(); i++) {
                LoanDetail loanDetail = details.get(i);
                detailModelTable.insertRow(i, new Object[]{loanDetail.getId(), loanDetail.getIdCopy(), loanDetail.getTitle(), false});
            }

        }
    }
    /**
     * Dùng để chức năng trả toàn bộ sách trong phiếu mượn
     * @param loanTable bảng chứa thông tin phiếu mượn
     * @param idLib mã thủ thư
     * @param money tiền phạt 
     * @return 0 nếu thông tin nào đó thiếu
     *         2 nếu tiền phạt nhập không hợp lệ
     *         3 thủ thư chưa chọn phiếu cần trả
     *         4 nếu trả thành công
     *         5 nếu trả thất bại do lỗi kết nối csdl
     */
    public int returnLoan(JTable loanTable, String idLib, String money) {
        if (loanTable == null || StringUtils.isNullOrEmpty(money) || StringUtils.isNullOrEmpty(idLib)) {
            return RETURN_RESULT_NULL_POINT;
        }
        if (!Utils.isStringDouble(money)) {
            return RETURN_RESULT_MONEY_WRONG;
        }
        int row = loanTable.getSelectedRow();
        if (row < 0 && row >= listModel.size()) {
            return RETURN_RESULT_COUNT_SELECT;
        }
        returnModel = createLoanReturnFromDataTable(row, idLib, Double.parseDouble(money));

        return returnModel.returnLoan();
    }
      /**
     * Dùng để chức năng trả 1 số sách trong phiếu mượn
     * @param loanTable bảng chứa thông tin phiếu mượn
     * @param detailLoanTable bảng chứa thông tin chi tiết phiếu mượn
     * @param idLib mã thủ thư
     * @param money tiền phạt 
     * @return 0 nếu thông tin nào đó thiếu
     *         1 nếu không có sách nào trong phiếu cần trả
     *         2 nếu tiền phạt nhập không hợp lệ
     *         3 thủ thư chưa chọn phiếu cần trả
     *         4 nếu trả thành công
     *         5 nếu trả thất bại do lỗi kết nối csdl
     */
    public int returnBookOfLoan(JTable loanTable, JTable detailLoanTable, String idLib, String money) {
        if (loanTable == null || detailLoanTable == null || StringUtils.isNullOrEmpty(money) || StringUtils.isNullOrEmpty(idLib)) {
            return RETURN_RESULT_NULL_POINT;
        }
        if (detailLoanTable.getRowCount() == 0) {
            return RETURN_RESULT_NO_COUNT_IN_TABLE;
        }
        if (!Utils.isStringDouble(money)) {
            return RETURN_RESULT_MONEY_WRONG;
        }
        int row = loanTable.getSelectedRow();
        System.out.println("row:" + row + " " + listModel.size());
        if (row < 0 && row >= listModel.size()) {
            return RETURN_RESULT_COUNT_SELECT;
        }
        returnModel = createLoanReturnBoookFromDataTable(row, detailLoanTable, idLib, Double.parseDouble(money));
        if (returnModel.getDetails().isEmpty()) {
            return RETURN_RESULT_NO_BOOK_SELECT;
        }
        System.out.println("Say oh yeah");
        return returnModel.returnLoan();
    }

    private LoanReturn createLoanReturnFromDataTable(int rowSelected, String idLib, double money) {

        LoanReturn.LoanReturnBuilder builder = new LoanReturn.LoanReturnBuilder();
        builder.setIdLoan(listModel.get(rowSelected).getId())
                .setCardNo(listModel.get(rowSelected).getCardNo())
                .setIdLib(idLib)
                .setName(listModel.get(rowSelected).getName())
                .setReDate(new Date(System.currentTimeMillis()))
                .setMoney(money);
        List<LoanDetail> details = model.getListLoanDetailById(listModel.get(rowSelected).getId());
        List<LoanReturnDetail> reDetails = new ArrayList<>();
        if (details != null) {
            for (int i = 0; i < details.size(); i++) {
                String idCopy = details.get(i).getIdCopy();
                String title = details.get(i).getTitle();
                reDetails.add(new LoanReturnDetail(idCopy, title));
            }
        }
        builder.setDetails(reDetails);
        return builder.build();
    }

    private LoanReturn createLoanReturnBoookFromDataTable(int rowSelected, JTable loanDetaiLTable, String idLib, double money) {

        LoanReturn.LoanReturnBuilder builder = new LoanReturn.LoanReturnBuilder();
        builder.setIdLoan(listModel.get(rowSelected).getId())
                .setCardNo(listModel.get(rowSelected).getCardNo())
                .setIdLib(idLib)
                .setName(listModel.get(rowSelected).getName())
                .setReDate(new Date(System.currentTimeMillis()))
                .setMoney(money);
        List<LoanReturnDetail> details = getALLLoanDetailSelected(loanDetaiLTable);
        builder.setDetails(details);
        return builder.build();
    }

    private List<LoanReturnDetail> getALLLoanDetailSelected(JTable loanDetaiLTable) {
        List<LoanReturnDetail> results = new ArrayList<>();
        for (int i = 0; i < loanDetaiLTable.getRowCount(); i++) {
            Boolean isChecked = (Boolean) loanDetaiLTable.getValueAt(i, 3);

            if (isChecked) {
                System.out.println("yes");
                //get the values of the columns you need.
                LoanReturnDetail detail = new LoanReturnDetail(loanDetaiLTable.getValueAt(i, 1).toString(), loanDetaiLTable.getValueAt(i, 2).toString());
                results.add(detail);
            }
        }
        return results;
    }

    /**
     * Tìm kiếm phiếu mượn bởi mã phiếu, tên người mượn, mã thẻ mượn..
     * @param key từ khóa tìm kiếm
     * @return tập đối tượng cần tìm kiếm
     *         null nếu không tìm thấy kết quả nào
     */
    public List<Loan> searchLoanByKey(String key) {

        return returnModel.findLoanByKey(key);
    }

    public void disPlayListSearch(String key, DefaultTableModel regModelTable) {
        if (regModelTable != null) {
            regModelTable.setRowCount(0);
            List<Loan> resultSearch = searchLoanByKey(key);
            if (resultSearch != null) {
                listModel.clear();
                listModel.addAll(resultSearch);
                for (int i = 0; i < resultSearch.size(); i++) {
                    regModelTable.insertRow(i, new Object[]{resultSearch.get(i).getId(),
                        resultSearch.get(i).getCardNo(),
                        resultSearch.get(i).getIdLib(),
                        resultSearch.get(i).getName(),
                        resultSearch.get(i).getStartDate(),
                        resultSearch.get(i).getEndDate(),
                        resultSearch.get(i).getMoney()});
                }
            }
        }
    }
    
    public void printVoucher(){
        
    }
}
