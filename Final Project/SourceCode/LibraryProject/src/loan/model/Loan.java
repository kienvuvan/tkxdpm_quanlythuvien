/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loan.model;

import java.sql.Date;
import java.util.List;
import loan.dao.LoanDao;
import loan.dao.MysqlLoanDao;

/**
 * đối tượng phiếu mượn, khi người dùng mượn sách sẽ có phiếu mượn lưu thông tin
 * mượn.
 *
 * @author Linh
 */
public class Loan {

    public Loan(Builder builder) {
        this.id = builder.id;
        this.cardNo = builder.cardNo;
        this.idLib = builder.idLib;
        this.name = builder.name;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.money = builder.money;
        this.details = builder.details;

    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getIdLib() {
        return idLib;
    }

    public void setIdLib(String idLib) {
        this.idLib = idLib;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
    private String cardNo;
    private String idLib;   // mã thủ thư
    private String name;    //tên người mượn 
    private Date startDate; //ngày mượn
    private Date endDate;   //hạn trả 
    private double money;   // tiền cọc
    private List<LoanDetail> details; //danh sách chi tiết của phiếu mượn

    public List<LoanDetail> getDetails() {
        return details;
    }

    public void setDetails(List<LoanDetail> details) {
        this.details = details;
    }

    private LoanDao loanDao() {
        return MysqlLoanDao.getInstance();
    }

    public List<Loan> getAllLoan() {
        return loanDao().getAll();
    }

    public int borrowBook() {
        return loanDao().borrowBook(this);
    }

    public boolean returnLoan() {
        return false;
    }

    public List<LoanDetail> getListLoanDetailById(int idLoan) {
        return loanDao().getListLoanDetailById(idLoan);
    }

    public static class Builder {

        public Loan build() {
            return new Loan(this);
        }

        private int id;
        private String cardNo;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setCardNo(String cardNo) {
            this.cardNo = cardNo;
            return this;
        }

        public Builder setIdLib(String idLib) {
            this.idLib = idLib;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setStartDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder setMoney(double money) {
            this.money = money;
            return this;
        }

        public Builder setDetails(List<LoanDetail> details) {
            this.details = details;
            return this;
        }
        private String idLib;
        private String name;
        private Date startDate;
        private Date endDate;
        private double money;
        private List<LoanDetail> details;
    }
   
}
