/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package category.controller;

import category.model.Category;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import javax.swing.JComboBox;
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
public class CategoryControllerTest {

    public CategoryControllerTest() {
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
     * Test of getAll method, of class CategoryController.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        CategoryController instance = new CategoryController();
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("category/controller/testgetall.txt").getFile());
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                int expResult = Integer.valueOf(data[0]);

                int result = instance.getAll().size();
                assertEquals(expResult, result);
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

      

    }

}
