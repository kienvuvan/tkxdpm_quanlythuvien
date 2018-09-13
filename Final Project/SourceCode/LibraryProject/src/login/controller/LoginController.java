/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.controller;

import java.sql.SQLException;
import librarian.model.Librarian;
import user.Account;
import util.Constants;

/**
 *
 * @author Linh
 */
public class LoginController {
    
    /**
     * Kiểm tra login 
     * @param username tài khoản
     * @param password mật khẩu
     * @param type     kiểu đăng nhập  : độc giả, thủ thư, admin
     * @return   0 nếu login thành công với độc giả 
     *           1 nếu login thành công với thủ thư 
     *           2 nếu login thành công với admin
     *           3 lỗi kết nối csdl
     *           4 lỗi sai tài khoản hoặc mật khẩu
     * @throws SQLException lỗi kết nối csdl
     */
    public int checkLogin(String username, String password, String type) throws SQLException {
        if (type.equalsIgnoreCase(Constants.LOGIN_WITH_USER)) {
            Account account = new Account(username, password);
            return account.login();
        } else {
            Librarian bot = new Librarian(username, password);
             return bot.login();
        }

       
    }
}
