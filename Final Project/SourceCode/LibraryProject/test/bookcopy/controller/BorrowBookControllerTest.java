/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookcopy.controller;

import book.model.Book;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Scanner;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import user.User;

/**
 *
 * @author Linh
 */
public class BorrowBookControllerTest {

    public BorrowBookControllerTest() {
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
     * Test of getBookById method, of class BorrowBookController.
     */
    @Test
    public void testGetBookById() {
        System.out.println("getBookById");
        BorrowBookController instance = new BorrowBookController();
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("bookcopy/controller/testgetbookbyidborrow.txt").getFile());
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                boolean expResult = Boolean.valueOf(data[0]);
                String id = data[1];
                Book result = instance.getBookById(id);
                assertEquals(expResult, result != null);

            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Test of getUserByCardNo method, of class BorrowBookController.
     */
    @Test
    public void testGetUserByCardNo() {

        System.out.println("getUserByCardNo");
        BorrowBookController instance = new BorrowBookController();
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("bookcopy/controller/testgetuserbycardno.txt").getFile());
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                boolean expResult = Boolean.valueOf(data[0]);
                String cardNo = data[1];
                User result = instance.getUserByCardNo(cardNo);
                assertEquals(expResult, result != null);

            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Test of getCountBookCopyBorrowable method, of class BorrowBookController.
     */
    @Test
    public void testGetCountBookCopyBorrowable() {

        System.out.println("getCountBookCopyBorrowable");
        BorrowBookController instance = new BorrowBookController();
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("bookcopy/controller/testgetcountbookcopyborrowable.txt").getFile());
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                String id = data[0];
                System.out.println("Errrow +" +id);
                int expResult = Integer.valueOf(data[1]);
                int result = instance.getCountBookCopyBorrowable(id);
                assertEquals(expResult, result);

            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
