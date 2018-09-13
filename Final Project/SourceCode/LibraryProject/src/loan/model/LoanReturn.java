/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loan.model;

import book.model.Book;
import java.sql.Date;
import java.util.List;
import loan.dao.LoanDao;
import loan.dao.MysqlLoanDao;

/**
 * Đối tượng phiếu trả sách
 *
 * @author Linh
 */
public class LoanReturn {

    public LoanReturn(LoanReturnBuilder builder) {
        this.id = builder.id;
        this.idLoan = builder.idLoan;
        this.cardNo = builder.cardNo;
        this.details = builder.details;
        this.money = builder.money;
        this.reDate = builder.reDate;
        this.name = builder.name;
        this.idLib = builder.idLib;

    }
    private int id;

    public int getId() {
        return id;
    }

    public int getIdLoan() {
        return idLoan;
    }

    public String getCardNo() {
        return cardNo;
    }

    public Date getReDate() {
        return reDate;
    }

    public double getMoney() {
        return money;
    }
    private String idLib;

    public String getIdLib() {
        return idLib;
    }

    private int idLoan;
    private String cardNo;
    private String name;
    private List<LoanReturnDetail> details;

    public List<LoanReturnDetail> getDetails() {
        return details;
    }

    public void setDetails(List<LoanReturnDetail> details) {
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private Date reDate;
    private double money;

    public static class LoanReturnBuilder {

        public LoanReturn build() {
            return new LoanReturn(this);
        }
        private int id;
        private int idLoan;

        public LoanReturnBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public LoanReturnBuilder setIdLoan(int idLoan) {
            this.idLoan = idLoan;
            return this;
        }

        public LoanReturnBuilder setCardNo(String cardNo) {
            this.cardNo = cardNo;
            return this;
        }

        public LoanReturnBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public LoanReturnBuilder setReDate(Date reDate) {
            this.reDate = reDate;
            return this;
        }

        public LoanReturnBuilder setMoney(double money) {
            this.money = money;
            return this;
        }

        public LoanReturnBuilder setDetails(List<LoanReturnDetail> details) {
            this.details = details;
            return this;
        }
        private String cardNo;
        private String name;
        private Date reDate;
        private double money;
        private String idLib;

        public LoanReturnBuilder setIdLib(String idLib) {
            this.idLib = idLib;
            return this;
        }
        private List<LoanReturnDetail> details;
    }

    private LoanDao loanDao() {
        return MysqlLoanDao.getInstance();
    }

    public int returnLoan() {
        return loanDao().returnLoan(this);
    }

    public List<Loan> findLoanByKey(String key) {
        return loanDao().findLoanByKey(key);
    }

}
