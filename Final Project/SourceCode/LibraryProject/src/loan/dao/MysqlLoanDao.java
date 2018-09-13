/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loan.dao;

import bookcopy.controller.BorrowBookController;
import card.model.BorrowCard;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import loan.controller.LoanController;
import loan.model.Loan;
import loan.model.LoanDetail;
import loan.model.LoanReturn;
import loan.model.LoanReturnDetail;
import registeterm.model.RegisterTermDetail;
import sqlite.Mysql;
import util.Constants;

/**
 *
 * @author Linh
 */
public class MysqlLoanDao implements LoanDao {

    private static MysqlLoanDao instance;
    private static final String GET_ALL = "SELECT * FROM loanbook";
    private static final String GET_ALL_LOAN_DETAIL_BY_ID_LOAN = "SELECT * FROM loandetail WHERE id = ? and isreturn = false";
    private static final String INSERT_LOAN_BOOK = "INSERT INTO loanbook(id,cardno,idlibrarian,name,startdate,enddate,money) "
            + "SELECT COUNT(*)+1,?,?,?,?,?,? FROM loanbook ";
    private static final String INSERT_LOAN_DETAIL = "INSERT INTO loandetail(id,idcopy,title,isreturn) "
            + "            SELECT COUNT(*),?,?,? FROM loanbook  ";
    private static final String UPDATE_BOOK_COPY_BORROW = "UPDATE bookcopy SET status = 3 WHERE idcopy =?";
    private static final String RETURN_ALL_BOOK_OF_LOAN = "INSERT INTO returninfor(id,idloan,cardno,idlib,name,returndate,money) SELECT COUNT(*)+1,?,?,?,?,?,? FROM returninfor ";
    private static final String INSERT_RETURN_LOAN_DETAIL = "INSERT INTO returndetail(id,idcopy,title)  SELECT COUNT(*),?,? FROM returninfor";
    private static final String UPDATE_BOOK_COPY_RETURN = "UPDATE bookcopy SET status = 0 WHERE idcopy =?";
    private static final String UPDATE_BOOK_STATUS_RETURN = "UPDATE loandetail SET isreturn = true WHERE id = ? and idcopy = ?";
    private static final String GET_LOAN_BY_SEARCH_KEY = "SELECT * FROM loanbook WHERE id like ? or cardno like ? or name like ?";
    public static final int RESULT_NO_SELECTED = 0;
    public static final int RESULT_NULL_POINT = 1;
    public static final int RESULT_OVER_DUE_CARD = 2;
    public static final int RESULT_CARD_NO_ACTIVE = 3;
    public static final int RESULT_OVER_DUE_BORROW = 4;
    public static final int RESULT_SUCCESS = 5;
    public static final int RESULT_SQLITE = 6;

    private MysqlLoanDao() {
    }

    public static MysqlLoanDao getInstance() {
        if (instance == null) {
            instance = new MysqlLoanDao();
        }
        return instance;
    }

    @Override
    public List<Loan> getAll() {
        List<Loan> results = new ArrayList<>();
        try {
            Connection c = Mysql.getInstance().getConnection();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(GET_ALL);
            while (rs.next()) {
                int id = rs.getInt("id");
                Loan loan = new Loan.Builder()
                        .setId(rs.getInt("id"))
                        .setCardNo(rs.getString("cardno"))
                        .setIdLib(rs.getString("idlibrarian"))
                        .setName(rs.getString("name"))
                        .setStartDate(rs.getDate("startdate"))
                        .setEndDate(rs.getDate("enddate"))
                        .setMoney(rs.getDouble("money")).build();
//                PreparedStatement st2 = c.prepareStatement(GET_ALL_LOAN_DETAIL_BY_ID_LOAN);
//                st2.setInt(1, id);
//                ResultSet rs2 = st2.executeQuery();
//                List<LoanDetail> detail = new ArrayList<>();
//                while (rs2.next()) {
//                    LoanDetail loandetail = new LoanDetail.Builder()
//                            .setId(id)
//                            .setIdCopy(rs2.getString("idcopy"))
//                            .setTitle(rs2.getString("title")).build();
//                    detail.add(loandetail);
//
//                }
//                loan.setDetails(detail);
                results.add(loan);

            }

            return results;
        } catch (SQLException e) {
            System.out.println("" + e);
        }
        return null;
    }

    @Override
    public int borrowBook(Loan loan) {

        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement st1 = c.prepareStatement(INSERT_LOAN_BOOK);
            st1.setString(1, loan.getCardNo());
            st1.setString(2, loan.getIdLib());
            st1.setString(3, loan.getName());
            st1.setDate(4, loan.getStartDate());
            st1.setDate(5, loan.getEndDate());
            st1.setDouble(6, loan.getMoney());
            if (st1.executeUpdate() > 0) {
                for (int i = 0; i < loan.getDetails().size(); i++) {
                    System.out.println("Size" + loan.getDetails().size());
                    LoanDetail detail = loan.getDetails().get(i);
                    PreparedStatement st2 = c.prepareStatement(INSERT_LOAN_DETAIL);
                    PreparedStatement st3 = c.prepareStatement(UPDATE_BOOK_COPY_BORROW);
                    st2.setString(1, detail.getIdCopy());
                    st2.setString(2, detail.getTitle());
                    st2.setBoolean(3, false);
                    st3.setString(1, detail.getIdCopy());
                    st2.executeUpdate();
                    st3.executeUpdate();
                }
                return BorrowBookController.RESULT_SUCCESS;
            }

        } catch (SQLException e) {
            System.out.println("Exception" + e.getMessage());
        }

        return BorrowBookController.RESULT_SQLITE;
    }

    @Override
    public int returnLoan(LoanReturn loan) {
        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement st1 = c.prepareStatement(RETURN_ALL_BOOK_OF_LOAN);
            st1.setInt(1, loan.getIdLoan());
            st1.setString(2, loan.getCardNo());
            st1.setString(3, loan.getIdLib());
            st1.setString(4, loan.getName());
            st1.setDate(5, loan.getReDate());
            st1.setDouble(6, loan.getMoney());
            System.out.println("Sizes" + loan.getDetails().size());
            if (st1.executeUpdate() > 0) {
                for (int i = 0; i < loan.getDetails().size(); i++) {

                    System.out.println("Size" + loan.getDetails().size());
                    LoanReturnDetail detail = loan.getDetails().get(i);
                    PreparedStatement st2 = c.prepareStatement(INSERT_RETURN_LOAN_DETAIL);

                    st2.setString(1, detail.getIdCopy());
                    st2.setString(2, detail.getTitle());
                    if (st2.executeUpdate() > 0) {
                        System.out.println("st2 Success");
                    }
                    PreparedStatement st3 = c.prepareStatement(UPDATE_BOOK_COPY_RETURN);
                    st3.setString(1, detail.getIdCopy());

                    if (st3.executeUpdate() > 0) {
                        System.out.println("st3 Success");
                    }
                    PreparedStatement st4 = c.prepareStatement(UPDATE_BOOK_STATUS_RETURN);
                    st4.setInt(1, loan.getIdLoan());
                    st4.setString(2, detail.getIdCopy());
                    System.out.println("" + detail.getIdCopy());

                    if (st4.executeUpdate() > 0) {
                        System.out.println("st4 Success");
                    }

                }
                return LoanController.RETURN_RESULT_SUCCESS;
            }

        } catch (SQLException e) {
            System.out.println("Exception" + e.getMessage());
        }

        return LoanController.RETURN_RESULT_SQLITE;
    }

    @Override
    public List<LoanDetail> getListLoanDetailById(int idLoan) {
        List<LoanDetail> results = new ArrayList<>();
        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement st = c.prepareStatement(GET_ALL_LOAN_DETAIL_BY_ID_LOAN);
            st.setInt(1, idLoan);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                LoanDetail loandetail = new LoanDetail.Builder()
                        .setId(idLoan)
                        .setIdCopy(rs.getString("idcopy"))
                        .setTitle(rs.getString("title")).build();
                results.add(loandetail);

            }
            return results;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public List<Loan> findLoanByKey(String key) {
        List<Loan> results = new ArrayList<>();
        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement st = c.prepareStatement(GET_LOAN_BY_SEARCH_KEY);
            st.setString(1, "%" + key + "%");
            st.setString(2, "%" + key + "%");
            st.setString(3, "%" + key + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Loan loan = new Loan.Builder()
                        .setId(rs.getInt("id"))
                        .setCardNo(rs.getString("cardno"))
                        .setIdLib(rs.getString("idlibrarian"))
                        .setName(rs.getString("name"))
                        .setStartDate(rs.getDate("startdate"))
                        .setEndDate(rs.getDate("enddate"))
                        .setMoney(rs.getDouble("money")).build();
                results.add(loan);

            }

            return results;
        } catch (SQLException e) {
            System.out.println("" + e);
        }
        return null;
    }

}
