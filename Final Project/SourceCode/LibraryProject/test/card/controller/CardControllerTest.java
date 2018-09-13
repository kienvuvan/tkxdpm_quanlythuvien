/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package card.controller;

import card.dao.MysqlBorrowCardDao;
import card.model.BorrowCard;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import javax.swing.table.DefaultTableModel;
import login.controller.LoginController;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Linh
 */
public class CardControllerTest {

    private BorrowCard model;

    public CardControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        model = new BorrowCard();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of generateIdCard method, of class CardController.
     */
    @Test
    public void testGenerateIdCard() {
        System.out.println("generateIdCard");
        CardController instance = new CardController();
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("card/controller/testgenid.txt").getFile());
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                boolean expResult = Boolean.valueOf(data[0]);
                String test = data[1];
                String result = instance.generateIdCard();
                assertEquals(expResult, test.equals(result));
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Test of activateCard method, of class CardController.
     */
    @Test
    public void testActivateCard() {
        System.out.println("activateCard");
        CardController instance = new CardController();
        String idUser = "";
        String cardNo = "";
        Date actDate = null;
        Date expiredDate = null;
        String codeActivate = "";
      
        int expResult = MysqlBorrowCardDao.ACTIVATE_FAIL_NULL_POINT;
        int result = instance.activateCard(idUser, actDate, expiredDate, codeActivate, cardNo);
        assertEquals(expResult, result);

        //Test khi id độc giả NULL
        idUser = null;
        expResult = MysqlBorrowCardDao.ACTIVATE_FAIL_NULL_POINT;
        result = instance.activateCard(idUser, actDate, expiredDate, codeActivate, cardNo);
        assertEquals(expResult, result);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 3, 5);

        // Test khi hạn sử dụng thẻ và ngày đăng ký không hợp lệ n
        expiredDate = calendar.getTime();
        expResult = MysqlBorrowCardDao.ACTIVATE_FAIL_NULL_POINT;
        result = instance.activateCard(idUser, actDate, expiredDate, codeActivate, cardNo);
        assertEquals(expResult, result);
        //Test khi ngày đăng ký khác NULL, hạn sử dụng thẻ không hợp lệ, nhưng mã độc giả mã thẻ , mã kích hoạt đã hợp lệ
        idUser = "linh";
        cardNo = "BCLIBRARY19";
        codeActivate = "222333";
        actDate = new Date();
        expResult = MysqlBorrowCardDao.ACTIVATE_FAIL_EXPERIED;
        result = instance.activateCard(idUser, actDate, expiredDate, codeActivate, cardNo);
        assertEquals(expResult, result);
    }

}
