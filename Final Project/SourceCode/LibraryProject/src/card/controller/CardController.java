/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package card.controller;

import card.dao.MysqlBorrowCardDao;
import card.model.BorrowCard;
import com.mysql.cj.util.StringUtils;
import java.util.Date;
import java.util.Random;
import javax.swing.table.DefaultTableModel;
import util.Constants;
import util.Utils;

/**
 *
 * @author Linh
 */
public class CardController {

    public static final int RESULT_NULL_POINT = 0;
    public static final int RESULT_FOUND = 1;
    public static final int RESULT_EXPRIED_FAIL = 1;
    public static final int RESULT_NOT_FOUND = 2;
    public static final int RESULT_SQLITE = 3;
    public static final int RESULT_SUCCESS = 2;

    public CardController() {
        model = new BorrowCard();
    }
    BorrowCard model;

    /**
     * Lấy danh sách thẻ độc giả gửi đăng ký, hiển thị trên bảng
     *
     * @param model kiểu dữ liệu cho bảng.
     */
    public int showInforCardRegisted(DefaultTableModel tableModel) {
        if (tableModel == null) {
            return RESULT_NULL_POINT;
        }
        return model.getInforCardRegisted(tableModel);
    }
    
    /**
     * Hiển thị toàn bộ các thẻ mượn đã kích hoạt
     * @param tableModel bảng hiển thị thông tin thẻ
     * @return 0 nếu không tồn tại bảng này
     *         1 nếu tìm được 1 hoặc 1 số thẻ
     *         2 nếu không tìm thấy thẻ nào
     *         3 lỗi kết nối csdl
     */
    public int showAllCardActive(DefaultTableModel tableModel) {
        if (tableModel == null) {
            return RESULT_NULL_POINT;
        }
        return model.getAllCardActive(tableModel);
    }

    public String generateIdCard() {
        return model.generateIdCard();
    }
    /**
     * Sinh ngẫu nhiên chuỗi số dùng cho việc kích hoạt thẻ thành viên
     * @return chuỗi số kích hoạt giá trị từ 100000 đến 999999
     */
    public String generateCodeActivateCard() {
        Random random = new Random();
        int code = random.nextInt(999999 - 100000 + 1) + 100000;
        return String.valueOf(code);
    }
    
    /**
     * Dùng để kích hoạt thẻ thành viên
     * @param idUser mã độc giả
     * @param actDate ngày kích hoạt
     * @param expiredDate ngày hết hạn
     * @param codeActivate  mã kích hoạt thẻ
     * @param cardNo        mã thẻ được cung cấp bởi hệ thống
     * @return  1 nếu có trường thông tin nào đó thiếu
     *          0 nếu hạn của thẻ là không hợp lệ(hạn thẻ trước thời gian kích hoạt)
     *          2 nếu kích hoạt thành công
     *          3 nếu lỗi kết nối csdl
     */
    public int activateCard(String idUser, Date actDate, Date expiredDate, String codeActivate, String cardNo) {
        if (cardNo.equals(Constants.DEFAULT_CARD_NO_REGISTER_IN_TABLE)) {
            cardNo = generateIdCard();
        }
        java.util.Date now = new java.util.Date();
        if (StringUtils.isNullOrEmpty(cardNo)
                || StringUtils.isNullOrEmpty(codeActivate)
                || StringUtils.isNullOrEmpty(idUser)
                || actDate == null || expiredDate == null) {
            return MysqlBorrowCardDao.ACTIVATE_FAIL_NULL_POINT;
        }
        if (expiredDate.before(now)) {
            return MysqlBorrowCardDao.ACTIVATE_FAIL_EXPERIED;
        }

        model.setId(idUser);
        model.setCardNo(cardNo);
        model.setActDate(Utils.convertDate(actDate));
        model.setExpiredDate(Utils.convertDate(expiredDate));
        return model.activateCard();
    }

    public String getCardNoById(String idCard) {
        return model.getCardNoById(idCard);
    }

    public BorrowCard getBorrowCardById(String idCard) {
        return model.getBorrowCardByUsername(idCard);
    }

    public String getNameCardById(String id) {
        return model.getNameCardById(id);
    }

    public int getStatusCardById(String idCard) {
        return model.getStatusCardById(idCard);
    }
    /**
     * Lấy ra số lượng sách độc giả mượn đã quá hạn theo mã thẻ mượn
     * @param idCard mã thẻ mượn
     * @return số lượng sách quá hạn của độc giả 
     */
    public int checkUseOverDueBookCopy(String idCard) {
        return model.checkUserOverDueBookCopy(idCard);
    }

    public BorrowCard getBorrowCardByUsername(String id) {
        return model.getBorrowCardByUsername(id);
    }

    public int searchCardUnActive(String key, DefaultTableModel modelTable) {
        if (modelTable == null) {
            return RESULT_NULL_POINT;
        }
        return model.searchCardUnActive(key, modelTable);
    }

    public int searchCardActive(String key, DefaultTableModel modelTable) {
        if (modelTable == null || key == null) {
            return RESULT_NULL_POINT;
        }
        return model.searchAllCardActiveByKey(key, modelTable);
    }

    public int deActiveCard(String cardNo) {
        if (StringUtils.isNullOrEmpty(cardNo)) {
            return RESULT_NULL_POINT;
        }
        return model.deActiveCard(cardNo);
    }
     /**
      * Dùng để cập nhật thông tin thẻ mượn
      * @param cardNo mã thẻ mượn
      * @param expired hạn sử dụng thẻ
      * @return 0 nếu thiếu 1 hoặc 1 số trường 
      *         1 nếu hạn sử dụng muốn cập nhật không hợp lệ
      *         2 nếu cập nhật thành công
      *         3 nếu cập nhật thất bại , do lỗi kết nối csdl
      */
    public int updateCard(String cardNo, Date expired) {
        if (expired == null || StringUtils.isNullOrEmpty(cardNo)) {
            return RESULT_NULL_POINT;

        }
        if (expired.before(new Date(System.currentTimeMillis()))) {
            System.out.println("Hello");
            return RESULT_EXPRIED_FAIL;
        }
        return model.updateCard(cardNo, new java.sql.Date(expired.getTime()));
    }
}
