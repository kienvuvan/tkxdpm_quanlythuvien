/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.controller;

import card.controller.CardController;
import card.model.BorrowCard;
import com.mysql.cj.util.StringUtils;
import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import librarian.model.Librarian;
import user.Account;

import user.User;
import static user.dao.MysqlUserDao.RESULT_EMAIL_INCORRECT;
import static user.dao.MysqlUserDao.RESULT_ID_EXITS;
import static user.dao.MysqlUserDao.RESULT_ID_NOT_EXITS;
import static user.dao.MysqlUserDao.RESULT_NO_CHECK_TERM;
import static user.dao.MysqlUserDao.RESULT_NULL_POINT;
import static user.dao.MysqlUserDao.RESULT_PASS_ERROR_CONFIRM;
import static user.dao.MysqlUserDao.RESULT_PASS_SMALL;
import static user.dao.MysqlUserDao.RESULT_PASS_TOO_SMALL;
import static user.dao.MysqlUserDao.RESULT_PHONE_INCORRECT;
import user.view.InforAccount;
import util.Constants;
import util.Utils;

/**
 *
 * @author Linh
 */
public class UserController {

    User user;
    Account acc;
    Librarian lib;

    public UserController() {
        user = new User();
        acc = new Account();
        lib = new Librarian();
    }

    public int insertUser(boolean isReadTerm, String id, String name, String studentId, String sex, String period, String email, String phone, char[] passfield) {
        String pass = Arrays.toString(passfield);
        if (StringUtils.isNullOrEmpty(id) || StringUtils.isNullOrEmpty(name)
                || StringUtils.isNullOrEmpty(studentId)
                || StringUtils.isNullOrEmpty(sex)        
                || StringUtils.isNullOrEmpty(email)
                || StringUtils.isNullOrEmpty(phone)
                || StringUtils.isNullOrEmpty(pass)) {
            return RESULT_NULL_POINT;
        }
        if (!Utils.isAdressEmail(email)) {
            return RESULT_EMAIL_INCORRECT;
        }
        if (!Utils.isPhoneNumber(phone)) {
            return RESULT_PHONE_INCORRECT;
        }
        if (passfield.length < 6) {
            return RESULT_PASS_SMALL;
        }
        if (user.findUserById(id) != null) {
            return RESULT_ID_EXITS;
        }
        if (!isReadTerm) {
            return RESULT_NO_CHECK_TERM;
        }

        User.Builder builder = new User.Builder()
                .setId(id)
                .setStudentId(studentId)
                .setName(name)
                .setSex(sex)
                .setPeriod(period)
                .setEmail(email)
                .setPhone(phone);
        Account account = new Account(id, pass);
        BorrowCard borrowCard = new BorrowCard(id, Constants.DEFAULT_CARD_NO_REGISTER, 0, null);
        builder.setAccount(account)
                .setCard(borrowCard);
        return builder.build().save();
    }

    public User findUserById(String id) {
        return user.findUserById(id);
    }

    public String getNameById(String id) {
        return user.getNameById(id);
    }

    public void displayInforAccount(String id) {
        User user = findUserById(id);
        CardController cc = new CardController();
        BorrowCard borrowCard = cc.getBorrowCardByUsername(id);
        InforAccount inforAccount = new InforAccount();
        inforAccount.setVisible(true);
        String status = "";
        Date dateNow = new Date(Calendar.getInstance().getTimeInMillis());
        String period = "";
        String expried = "";
        String cardNo = "";
        if (user.getPeriod() != null) {
            period = "" + user.getPeriod();
        } else {
            period = "Khác";
        }
        if (borrowCard.getCardNo() == null) {
            status = "Chưa kích hoạt";
        } else {
            cardNo = borrowCard.getCardNo();
            if (borrowCard.getExpiredDate() == null) {
                expried = "";
            } else if (borrowCard.getExpiredDate() != null) {
                expried = Utils.format(borrowCard.getExpiredDate());
                if (borrowCard.getExpiredDate().before(dateNow)) {
                    status = "Hết hạn";
                } else {
                    if (borrowCard.getStatus() == 1) {
                        status = "Đã kích hoạt";
                    }
                }
            }
        }
        inforAccount.setInforAccount(id, user.getName(), user.getStudentId(), user.getSex(), user.getEmail(), user.getPhone(), period,
                cardNo, status, expried);
    }

    public int changePass(Account account, String passNew, String passAgain) {
        return acc.changePass(account, passNew, passAgain);
    }

    public List<User> searchUser(String keyword) {
        return user.searchUser(keyword);
    }

    public void displayUser(JTable jtb, List<User> listUsers) {
        DefaultTableModel dtm = (DefaultTableModel) jtb.getModel();
        dtm.setRowCount(0);
        if (listUsers.isEmpty()) {
            dtm.addRow(new Object[]{"", "", "Không có kết quả nào được tìm thấy", "", ""});
        } else {
            for (int i = 0; i < listUsers.size(); i++) {
                User user = listUsers.get(i);
                dtm.addRow(new Object[]{user.getId(), user.getName(), user.getSex(), user.getEmail(), user.getPhone(), user.getPeriod()});
            }
        }
        jtb.setModel(dtm);
    }

    public int updateInforUser(User userUpdate) {
        if (StringUtils.isNullOrEmpty(userUpdate.getName()) || StringUtils.isNullOrEmpty(userUpdate.getEmail())
                || StringUtils.isNullOrEmpty(userUpdate.getPhone())) {
            return RESULT_NULL_POINT;
        } else if (Utils.isAdressEmail(userUpdate.getEmail()) == false) {
            return RESULT_EMAIL_INCORRECT;
        } else if (Utils.isPhoneNumber(userUpdate.getPhone()) == false) {
            return RESULT_PHONE_INCORRECT;
        } else {
            return user.updateInforUser(userUpdate);
        }
    }

    public int changePassword(String username, char[] newPassField, char[] confirmPassField, String typeLogin) {
        String newPass = Arrays.toString(newPassField);
        String confirmPass = Arrays.toString(confirmPassField);
        if (StringUtils.isNullOrEmpty(username) || StringUtils.isNullOrEmpty(newPass) || StringUtils.isNullOrEmpty(confirmPass) || StringUtils.isNullOrEmpty(typeLogin)||newPassField.length==0||confirmPassField.length==0) {
            return RESULT_NULL_POINT;
        }
        if (newPassField.length < 6) {
            return RESULT_PASS_TOO_SMALL;
        }
        if (!newPass.equals(confirmPass)) {
            return RESULT_PASS_ERROR_CONFIRM;
        }
        if (typeLogin.equals(Constants.LOGIN_WITH_USER)) {
            if (user.findUserById(username) == null) {
                return RESULT_ID_NOT_EXITS;
            }
            return user.changePass(username, newPass);
        } else if (typeLogin.equals(Constants.LOGIN_WITH_ADMIN)) {
            return -1;
        } else {
            if (!lib.checkIdExist(username)) {
                return RESULT_ID_NOT_EXITS;
            }
            return lib.changePass(username, newPass);
        }

    }
}
