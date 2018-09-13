/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loan.ireport;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import sqlite.Mysql;

/**
 *
 * @author kienanh2903
 */
public class ConnectIreport {

    public void loanBook(String id, String cardno, String ireport) throws JRException, SQLException, IOException {
        //            InputStream is =null;
        try (Connection connec = Mysql.getInstance().getConnection()) {
            String source = "src/loan/ireport/LoanBookIreport.jrxml";
            JasperReport report = JasperCompileManager.compileReport(source);
//                InputStream is = new FileInputStream("src/loan/ireport/LoanBookIreport.jasper");
            Hashtable hash = new Hashtable();
            hash.put("id", id);
            hash.put("cardno", cardno);
            JasperPrint print = JasperFillManager.fillReport(report, hash, connec);
            JasperViewer.viewReport(print, false);
//            is.close();
        }
    }

    public static void main(String[] args) {
        try {
            ConnectIreport connectIreport = new ConnectIreport();
            connectIreport.loanBook("1", "BCLIBRARY1", "LoanBookIreport");
        } catch (JRException ex) {
            Logger.getLogger(ConnectIreport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectIreport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConnectIreport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
