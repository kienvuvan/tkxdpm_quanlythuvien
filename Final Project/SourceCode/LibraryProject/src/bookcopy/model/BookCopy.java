/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookcopy.model;

import book.model.Book;
import bookcopy.dao.BookCopyDao;
import bookcopy.dao.MysqlBookCopyDao;
import java.sql.Date;
import java.util.List;
import loan.model.Loan;
import registeterm.model.RegisterBorrow;

/**
 *
 * @author Linh
 */
public class BookCopy {

    private BookCopy(BookCopyBuilder builder) {
        this.id = builder.id;
        this.copyNumber = builder.copyNumber;
        this.title = builder.title;
        this.author = builder.author;
        this.publisher = builder.publisher;
        this.price = builder.price;
        this.category = builder.category;
        this.status = builder.status;
        this.type = builder.type;
    }
    private String id;

    public String getId() {
        return id;
    }

    public String getCopyNumber() {
        return copyNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public int getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }
    private String copyNumber;
    private String title;
    private String author;
    private String publisher;
    private double price;
    private String category;
    private int status;
    private String type;

    public static class BookCopyBuilder {

        private String id;
        private String copyNumber;

        public BookCopyBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public BookCopyBuilder setCopyNumber(String copyNumber) {
            this.copyNumber = copyNumber;
            return this;
        }

        public BookCopyBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public BookCopyBuilder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public BookCopyBuilder setPublisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public BookCopyBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public BookCopyBuilder setCategory(String category) {
            this.category = category;
            return this;
        }

        public BookCopyBuilder setStatus(int status) {
            this.status = status;
            return this;
        }

        public BookCopyBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public BookCopy build() {
            return new BookCopy(this);
        }
        private String title;
        private String author;
        private String publisher;
        private double price;
        private String category;
        private int status;
        private String type;

    }

    public BookCopyDao bookCopyDao() {
        return MysqlBookCopyDao.getInstance();
    }

    public String generateIdBook(String category) {
        return bookCopyDao().generateIdBook(category);
    }

    public int addBookCopies(String id, int countCopy, double price, String type) {
        return bookCopyDao().addBookCopies(id, countCopy, price, type);
    }

    public List<Book> getAll() {
        return bookCopyDao().getAll();
    }

    public String getTypeBookCopy(String idCopy) {
        return bookCopyDao().getTypeBookCopy(idCopy);
    }

    public int getStatusBookCopy(String idCopy) {
        return bookCopyDao().getStatusBookCopy(idCopy);
    }

    public boolean createRegisteBook(int id, String idCopy, String name, Date dateNow) {
        return bookCopyDao().createRegisteBook(id, idCopy, name, dateNow);
    }

    public boolean updateStatusRegistedBookCopy(String idCopy) {
        return bookCopyDao().updateStatusRegistedBookCopy(idCopy);
    }

    public boolean updateStatusUnRegistedBookCopy(String idCopy) {
        return bookCopyDao().updateStatusUnRegistedBookCopy(idCopy);
    }

    public List<BookCopy> getAllBookCopyById(String idBook) {
        return bookCopyDao().getAllBookCopyById(idBook);
    }

    public String getCountBookCopyById(String idBook) {
        return bookCopyDao().getCountBookCopyById(idBook);
    }

    public List<BookCopy> getBookCopyListBorrowableById(String idBook) {
        return bookCopyDao().getBookCopyListBorrowableById(idBook);
    }

    public int generateIdRegiste() {
        return bookCopyDao().generateIdRegiste();
    }

    public boolean registeBookCopy(int id, String idCopy, String title) {
        return bookCopyDao().registeBookCopy(id, idCopy, title);
    }

    public boolean unRegisteBookCopy(String idCopy) {
        return bookCopyDao().unRegisteBookCopy(idCopy);
    }

    public int getCountBookCopyUnPainAndNoOutDate(String idCard) {
        return bookCopyDao().getCountBookCopyUnPainAndNoOutDate(idCard);
    }

    public List<RegisterBorrow> getRegistedBookCopyLisrByCardNo(String idCard) {
        return bookCopyDao().getRegistedBookCopyLisrByCardNo(idCard);
    }

    public int getIdRegistedByIdBookCopy(String idCopy) {
        return bookCopyDao().getIdRegistedByIdBookCopy(idCopy);
    }

    public int getCountBookCopySameIdRegisted(int idRegisted) {
        return bookCopyDao().getCountBookCopySameIdRegisted(idRegisted);
    }

    public boolean unAllRegisteBookCopy(String idCard) {
        return bookCopyDao().unAllRegisteBookCopy(idCard);
    }

    public List<Loan> getListLoanBook(String cardNo) {
        return bookCopyDao().getListLoanBook(cardNo);
    }

    public List<String> getAllIdBook() {
        return bookCopyDao().getAllIdBook();
    }

    public Book getBookById(String id) {
        return bookCopyDao().getBookById(id);
    }

    public int getCountRegistedBookCopyByCardNo(String cardNo) {
        return bookCopyDao().getCountRegistedBookCopyByCardNo(cardNo);
    }

    public List<BookCopy> getListCopyBorrowByCount(String id, int count) {
        return bookCopyDao().getListCopyBorrowByCount(id, count);
    }

    public boolean updateStatusRegistedInLibraryBookCopy(int status, String idCopy) {
        return bookCopyDao().updateStatusRegistedInLibraryBookCopy(status, idCopy);
    }

    public int updateInforBookCopy(String idCopy, String status, String typeOld, String typeNew, double price) {
        return bookCopyDao().updateInforBookCopy(idCopy, status, typeOld, typeNew, price);
    }
    
}
