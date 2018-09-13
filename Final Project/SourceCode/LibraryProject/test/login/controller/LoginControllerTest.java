/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.Arrays;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.Constants;

/**
 *
 * @author Linh
 */
public class LoginControllerTest {

    public LoginControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of checkLogin method, of class LoginController.
     */
    @Test
    public void testCheckLogin() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("login/controller/testLoginController.txt").getFile());
        LoginController instance = new LoginController();
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] date = line.split(";");
                String username = date[0];
                String password = date[1];
                String typeLogin = date[2];
                int expResult = Integer.valueOf(date[3]);
                int result = instance.checkLogin(username, password, typeLogin);
                assertEquals(expResult, result);

            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("checkLogin" + results.toString());
//        //Trường hợp mật sai mật khẩu , login với tư cách độc giả
//        String username = "linhluv1";
//        String password = Arrays.toString(new char[]{'l', 'i', 'n', 'h'});
//        System.out.println(password);
//        String type = Constants.LOGIN_WITH_USER;
//        LoginController instance = new LoginController();
//        int expResult = 4;
//        int result = instance.checkLogin(username, password, type);
//        assertEquals(expResult, result);
//        //Trường hợp tài khoản và mật khẩu đều đúng
//        username = "linhluv1";
//        password = Arrays.toString(new char[]{'l', 'i', 'n', 'h', 'l', 'o', 'l', '1', '2', '3'});
//        expResult = 0;
//        result = instance.checkLogin(username, password, type);
//        assertEquals(expResult, result);
//        //Trường hợp đăng nhập với thủ thư ,tài khoản và mật khẩu đều sai
//        type = Constants.LOGIN_WITH_LIBRARIAN;
//        expResult = 4;
//        result = instance.checkLogin(username, password, type);
//        assertEquals(expResult, result);
//        //Trường hợp tài khoản mật khẩu là đúng
//        username = "tkxdpm11";
//        expResult = 1;
//        result = instance.checkLogin(username, password, type);
//        assertEquals(expResult, result);
    }

}
