/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookcopy.dao;

import book.model.Book;
import bookcopy.model.BookCopy;
import java.sql.Date;
import java.util.List;
import loan.model.Loan;
import registeterm.model.RegisterBorrow;

/**
 *
 * @author Linh
 */
public interface BookCopyDao {

    String generateIdBook(String category);

    int addBookCopies(String id, int countCopy, double price, String type);

    List<Book> getAll();

    String getTypeBookCopy(String idCopy);

    int getStatusBookCopy(String idCopy);

    boolean createRegisteBook(int id, String idCard, String name, Date dateNow);

    boolean registeBookCopy(int id, String idCopy, String title);

    boolean unRegisteBookCopy(String idCopy);

    boolean unAllRegisteBookCopy(String idCard);

    int getIdRegistedByIdBookCopy(String idCopy);

    int getCountBookCopySameIdRegisted(int idRegisted);

    boolean updateStatusRegistedBookCopy(String idCopy);

    boolean updateStatusUnRegistedBookCopy(String idCopy);

    List<BookCopy> getAllBookCopyById(String id);

    String getCountBookCopyById(String id);

    int getCountRegistedBookCopyByCardNo(String cardNo);

    List<BookCopy> getBookCopyListBorrowableById(String idBook);

    int getMaxCountIdCopyByBookId(String id);

    String getCatCodeById(String id);

    int generateIdRegiste();

//    int generateIdRegisteDetail();
    int getCountBookCopyUnPainAndNoOutDate(String idCard);

    List<RegisterBorrow> getRegistedBookCopyLisrByCardNo(String cardNo);

    List<Integer> getlistIdRegistedBookCopy(String cardNo);

    List<Loan> getListLoanBook(String cardNo);

    List<String> getAllIdBook();

    Book getBookById(String id);

    List<BookCopy> getListCopyBorrowByCount(String id, int count);

    boolean updateStatusRegistedInLibraryBookCopy(int status, String idCopy);
    
    int updateInforBookCopy(String idCopy, String status, String typeOld, String typeNew, double  price);
}
