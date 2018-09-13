/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookcopy.controller;

import bookcopy.model.BookCopy;
import com.mysql.cj.util.StringUtils;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
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
import util.Constants;

/**
 *
 * @author Linh
 */
public class BookCopyControllerTest {

    public BookCopyControllerTest() {
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
     * Test of addBookCopies method, of class BookCopyController.
     */
    @Test
    public void testAddBookCopies() {
        System.out.println("addBookCopies");
        BookCopyController instance = new BookCopyController();
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("bookcopy/controller/testaddcopies.txt").getFile());
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                String id = data[0].equals("null") ? null : data[0];
                int countCopy = Integer.valueOf(data[1]);
                String price = data[2];
                String type = data[3];
                int expResult = Integer.valueOf(data[4]);

                int result = instance.addBookCopies(id, countCopy, price, type);
                assertEquals(expResult, result);
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Test of generateIdBook method, of class BookCopyController.
     */
    @Test
    public void testGenerateIdBook() {
        System.out.println("generateIdBook");
        BookCopyController instance = new BookCopyController();
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("bookcopy/controller/testgenidbook.txt").getFile());
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                int selectIndex = Integer.valueOf(data[0]);
                boolean expResult = Boolean.valueOf(data[1]);
                String result = instance.generateIdBook(selectIndex);
                assertEquals(expResult, result != null);
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    /**
     * Test of getTypeBookCopy method, of class BookCopyController.
     */
    @Test
    public void testGetTypeBookCopy() {
        System.out.println("getTypeBookCopy");
        BookCopyController instance = new BookCopyController();
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("bookcopy/controller/testgettypebookcopy.txt").getFile());
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                String idCopy = data[0];
                String expResult = data[1];
                if (expResult.equals("0")) {
                    expResult = Constants.TYPE_BORROWABLE + " ";
                } else if (expResult.equals("1")) {
                    expResult = Constants.TYPE_REFERENCES + " ";
                }
                String result = instance.getTypeBookCopy(idCopy);
                assertEquals(expResult, result + " ");
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getStatusBookCopy method, of class BookCopyController.
     */
    @Test
    public void testGetStatusBookCopy() {
        System.out.println("getStatusBookCopy");
        BookCopyController instance = new BookCopyController();
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("bookcopy/controller/testgetstatuscopy.txt").getFile());
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                String idCopy = data[0];
                int expResult = Integer.valueOf(data[1]);
                int result = instance.getStatusBookCopy(idCopy);
                assertEquals(expResult, result);
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Test of createRegisteBook method, of class BookCopyController.
     */
    @Test
    public void testCreateRegisteBook() {
        System.out.println("createRegisteBook");
        int id = 0;
        String idCopy = "";
        String name = "";
        Date dateNow = null;
        BookCopyController instance = new BookCopyController();
        boolean expResult = false;
        boolean result = instance.createRegisteBook(id, idCopy, name, dateNow);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of updateStatusBookCopy method, of class BookCopyController.
     */
    @Test
    public void testUpdateStatusBookCopy() {
        System.out.println("updateStatusBookCopy");
        String idCopy = "";
        BookCopyController instance = new BookCopyController();
        boolean expResult = false;
        boolean result = instance.updateStatusRegistedBookCopy(idCopy);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getAllBookCopyById method, of class BookCopyController.
     */
    @Test
    public void testGetAllBookCopyById() {
        System.out.println("getAllBookCopyById");
        //Trường hợp idBook trống
        String idBook = "";
        BookCopyController instance = new BookCopyController();
        int expResult = 0;
        List<BookCopy> result = instance.getAllBookCopyById(idBook);
        assertEquals(expResult, result.size());

        // TODO review the generated test code and remove the default call to fail.
    }


    /**
     * Test of getCountBookCopyById method, of class BookCopyController.
     */
    @Test
    public void testGetCountBookCopyById() {
        System.out.println("getCountBookCopyById");
        BookCopyController instance = new BookCopyController();
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("bookcopy/controller/testgetcountbyid.txt").getFile());
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                String idBook = data[0];
                System.out.println("IDbôk" +idBook);
                String expResult = data[1];
               String result = instance.getCountBookCopyById(idBook);
                assertEquals(expResult, result);
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
   
        // TODO review the generated test code and remove the default call to fail.

    }


    /**
     * Test of generateIdRegiste method, of class BookCopyController.
     */
    @Test
    public void testGenerateIdRegiste() {
        System.out.println("generateIdRegiste");
        BookCopyController instance = new BookCopyController();
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("bookcopy/controller/testgenidreg.txt").getFile());
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                int expResult = Integer.valueOf(data[0]);
                int result = instance.generateIdRegiste();
                assertEquals(expResult, result);
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
