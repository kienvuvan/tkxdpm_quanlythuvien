/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book.controller;

import book.model.Book;
import category.model.Category;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import javax.swing.JTable;
import loan.model.Loan;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import publisher.model.Publisher;

/**
 *
 * @author Linh
 */
public class BookControllerTest {

    Book book;
    private List<Category> catList;
    private List<Publisher> pubList;

    public BookControllerTest() {
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
     * Test of searchBook method, of class BookController.
     */
    @Test
    public void testSearchBook() {
        System.out.println("searchBook");
        BookController instance = new BookController();
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("loan/controller/testseachreg.txt").getFile());
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                String id = data[0];
                String title = data[1];
                String author = data[2];
                String publish = data[3];
                String category = data[4];
                int expResult = Integer.valueOf(data[5]);
                List<Book> result = instance.searchBook_Registe(id, title, author, publish, category);
                assertEquals(expResult, result.size());

            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Test of getAllBook method, of class BookController.
     */
    @Test
    public void testGetAllBook() {
        System.out.println("getAllBook");
        BookController instance = new BookController();
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("loan/controller/testgetall.txt").getFile());
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                int expResult = Integer.valueOf(data[0]);
                List<Book> result = instance.getAllBook();
                assertEquals(expResult, result.size());

            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of addBook method, of class BookController.
     */
    @Test
    public void testAddBook() {
        System.out.println("addBook");
        BookController instance = new BookController();
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("book/controller/testaddbook.txt").getFile());
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                String id = data[0];
                String title = data[1];
                String author = data[2];
                String idPub = data[3];
                String publish = data[4];
                String idcat = data[5];
                String category = data[6];
                int expResult = Integer.valueOf(data[7]);
                 Book book = new Book.BookBuilder().setId(id)
                .setAuthor(author)
                .setPublisher(new Publisher(idPub, publish))
                .setTitle(title).setCategory(new Category(idcat, category)).build();
                int result = instance.addBook(book);
                assertEquals(expResult, result);

            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
  
    }

}
