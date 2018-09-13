/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loan.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import loan.model.Loan;
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
public class LoanControllerTest {
    
    public LoanControllerTest() {
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
     * Test of searchLoanByKey method, of class LoanController.
     */
    @Test
    public void testSearchLoanByKey() {
        System.out.println("searchLoanByKey");
            LoanController instance = new LoanController();
         ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("loan/controller/testsearchloan.txt").getFile());
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                int expResult  = Integer.valueOf(data[0]);
                String key  = data[1];
                 List<Loan> result = instance.searchLoanByKey(key);
                assertEquals(expResult, result.size());
              
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
