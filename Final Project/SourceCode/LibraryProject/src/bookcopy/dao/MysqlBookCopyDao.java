/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookcopy.dao;

import book.model.Book;
import bookcopy.model.BookCopy;
import category.model.Category;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import loan.model.Loan;
import loan.model.LoanDetail;
import publisher.model.Publisher;
import registeterm.model.RegisterBorrow;
import registeterm.model.RegisterTermDetail;
import sqlite.Mysql;
import util.Constants;
import static util.Constants.STATUS_BORROWABLE1;
import static util.Constants.TYPE_REFERENCES;
import util.Utils;

/**
 *
 * @author Linh
 */
public class MysqlBookCopyDao implements BookCopyDao {

    private static MysqlBookCopyDao instance;
    private static final String TABLE_NAME = "bookcopy";
    private static final String NAME_TABLE_BOOK = "book";

    private static final String COLUMN_ID = "idbook";
    private static final String COLUMN_ID_COPY = "idcopy";
    private static final String COLUMN_STATUS = "status";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_PRICE = "price";

    private static final String COLUM_ID_TABLE_BOOK = "id";
    private static final String COLUM_TITLE_TABLE_BOOK = "title";
    private static final String COLUM_AUTHOR_TABLE_BOOK = "author";
    private static final String COLUM_PUB_TABLE_BOOK = "idpub";
    private static final String COLUM_CAT_TABLE_BOOK = "idcat";

    //Columns of table loanbook
    private static final String COLUM_ID_TABLE_LOAN_BOOK = "id";
    private static final String COLUM_STATDATE_TABLE_LOAN_BOOK = "startdate";
    private static final String COLUM_ENDDATE_TABLE_LOAN_BOOK = "enddate";
    private static final String COLUM_MONEY_TABLE_LOAN_BOOK = "money";
    //Columns of table loandetail
    private static final String COLUM_IDCOPY_TABLE_LOAN_DETAIL = "idcopy";
    private static final String COLUM_TITLE_TABLE_LOAN_DETAIL = "title";

    private static final String COLUM_ID_TABLE__REGISTE_BOOK = "id";

    public static final String PREFIX_ID_COPY = "BCP";

    private static final String GENERATE_ID_BOOK = "SELECT count(*) FROM  " + NAME_TABLE_BOOK + " WHERE " + COLUM_CAT_TABLE_BOOK + " = ?";

    private static final String GENNERATE_ID_REGISTE = "SELECT max(id) FROM registebook";

    private static final String GENNERATE_ID_REGISTE_DETAIL = "SELECT id from registedetail";
    private static final String INSERT_COPY = "INSERT INTO bookcopy (idbook,idcopy,type,type,price) VALUES (?,?,?,?,?)";

    //columns of table book
    private static final String COLUMN_ID_BOOK = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_PUBLISH = "idpub";
    private static final String COLUMN_CATEGORY = "idcat";

    //Columns of table registebook
    private static final String COLUMN_REGISTE_DATE = "regdate";
    private static final String COLUMN_REGISTE_BOOK_ID = "registebook.id";

    private static final String SEARCH_BOOK_COPY = "SELECT * FROM book join bookcopy "
            + "where id like ? and title like ? and author like ? and publish like ? and category like ? ;";
    private static final String GET_ALL_BOOK = "SELECT * FROM book,category,publisher WHERE idcat = category.id and idpub = publisher.id";
    private static final String GET_ALL_BOOKCOPY_BY_ID = "SELECT * FROM bookcopy WHERE bookcopy.idbook =?;";
    private static final String GET_TYPE_BOOKCOPY = "SELECT type FROM bookcopy where idcopy like ?";
    private static final String GET_STATUS_BOOKCOPY = "SELECT status FROM bookcopy where idcopy like ?";
    private static final String INSERT_REGISTE_BOOKCOPY = "INSERT INTO registebook value (?,?,?,?)";
    private static final String INSERT_REGISTE_BOOKCOPY_DELTAIL = "INSERT INTO registedetail value (?,?,?)";
    private static final String UN_REGISTE_BOOKCOPY_DETAIL = "DELETE FROM registedetail WHERE idcopy =?";
    private static final String UN_REGISTE_BOOKCOPY = "DELETE FROM registebook WHERE id =?";

    private static final String UN_ALL_REGISTED_BOOKCOPY_DETAIL = "DELETE FROM registedetail WHERE id =?";
    private static final String UN_ALL_REGISTED_BOOKCOPY = "DELETE FROM registebook WHERE idcard =?";
    private static final String UPDATE_STATUS_BOOKCOPY = "UPDATE bookcopy set status =? where idcopy = ?";
    private static final String GET_COUNT_BOOKCOPY_BYID = "SELECT count(idbook) as count FROM bookcopy WHERE idbook = ?";
    private static final String GET_BOOKCOPY_BORROWABLE_BYID = "SELECT * FROM bookcopy where idbook = ? and status = ? and type =?";

    private static final String GET_ID_BOOKCOPY_BY_ID = "SELECT * FROM bookcopy WHERE idbook = ?";
    private static final String GET_CAT_BY_ID = "SELECT idcat FROM book WHERE id = ?";
    private static final String INSERT_BOOK_COPY = "INSERT INTO bookcopy (idbook,idcopy,status,type,price) values(?,?,?,?,?)";

    private static final String GET_REGISTEBORROW_BYID = "SELECT * FROM registebook WHERE idCard like ?";
    private static final String GET_COUNT_BOOKCOPY_UNPAIN_NOOUTDATE = "SELECT COUNT(*) FROM loanbook,loandetail\n"
            + "WHERE loanbook.id = loandetail.id AND loanbook.cardno = ? "
            + "AND loandetail.isreturn = 0 AND loanbook.enddate > CURRENT_DATE";
    private static final String GET_COUNT_REGISTED_BOOKCOPY_BY_CARDNO = "SELECT COUNT(*) FROM registebook, registedetail WHERE registebook.id = registedetail.id AND registebook.idcard =?";

    private static final String GET_ALL_REGISTED_BY_IDCARD = "SELECT * FROM registebook WHERE idcard = ?";
    private static final String GET_ALL_REGISTED_BOOKCOPY_BY_ID = "SELECT  idcopy,title FROM registedetail WHERE id = ?";
    private static final String GET_ID_REGISTED_BY_IDBOOKCOPY = "SELECT id FROM registedetail WHERE idcopy =?";
    private static final String GET_COUNT_BOOKCOPY_SAME_ID_REGISTED = "SELECT COUNT(*) FROM registedetail WHERE id = ?";

    private static final String GET_LIST_LOAN_BOOK_BY_CARDNO = "SELECT * from loanbook WHERE loanbook.cardno = ?";
    private static final String GET_LIST_LOAN_BOOK_DETAIL_BY_CARDNO = "SELECT * from loandetail WHERE id = ?";
    private static final String GET_ALL_ID_BOOK = "SELECT id FROM book";
    private static final String GET_BOOK_BY_ID = "SELECT * FROM book,category,publisher WHERE book.id =? and idcat = category.id and idpub = publisher.id";
    private static final String GET_LIST_COPY_BORROW_BY_COUNT = "SELECT * FROM book,bookcopy WHERE book.id = bookcopy.idbook and book.id = ? and bookcopy.status = 0 and bookcopy.type ='" + Constants.TYPE_BORROWABLE + "'" + " LIMIT ?";
    private static final String UPDATE_INFOR_BOOKCOPY ="UPDATE bookcopy SET type =?, price =? WHERE idcopy =?";
    
    public static final int RESULT_NULL_POINT = 0;
    public static final int RESULT_EXCEPTION_PRICE = 1;
    public static final int RESULT_SUCCESS = 2;
    public static final int RESULT_SQLITE = 3;
    public static final int RESULT_EXCEPTION_STATUS =4;

    private MysqlBookCopyDao() {

    }

    public static MysqlBookCopyDao getInstance() {
        if (instance == null) {
            instance = new MysqlBookCopyDao();
        }
        return instance;
    }

    /**
     * Hàm này dùng để sinh ra mã sách cho chức năng thêm sách
     *
     * @param categoryCode mã thể loại sách , dùng để phân loại sách.
     * @return mã sách cho việc thêm.
     * @exception lỗi kết nối csdl hoặc lỗi câu truy vấn
     */
    @Override
    public String generateIdBook(String categoryCode) {
        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement st = c.prepareStatement(GENERATE_ID_BOOK);
            System.out.println("" + GENERATE_ID_BOOK);
            st.setString(1, categoryCode);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                System.out.println("oke");
                int count = rs.getInt(1) + 1;

                return categoryCode + "" + count;

            }

        } catch (SQLException e) {

        }
        return null;
    }

    /**
     * Thêm các bản sách copy
     *
     * @param id id của sách cần thêm bản copy
     * @param countCopy số lượng cần thêm
     * @param price giá của bản copy
     * @param type kiểu sách copy là Có thể mượn hoặc tham khảo
     * @return 2 nếu thêm thành công 3 nếu thêm thất bại
     * @exception Lỗi kết nối csdl hoặc lỗi câu truy vấn
     */
    @Override
    public int addBookCopies(String id, int countCopy, double price, String type) {
        int max = getMaxCountIdCopyByBookId(id);
        if (max == -1) {
            return RESULT_SQLITE;
        }

        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement st = c.prepareStatement(INSERT_BOOK_COPY);

            for (int i = 0; i < countCopy; i++) {
                System.out.println("count" + i);
                int numberId = max + 1 + i;              //số ở cuối cùng của mã sách copy
                String idCopy = id + PREFIX_ID_COPY + numberId; // mã sách copy : = id  + BCP + 1 số.
                st.setString(1, id);
                st.setString(2, idCopy);
                st.setInt(3, 0);
                st.setString(4, type);
                st.setDouble(5, price);
                System.out.println(st);
                st.executeUpdate();

            }

        } catch (SQLException e) {
            return RESULT_SQLITE;   //3 thêm thất bại ,
        }
        return RESULT_SUCCESS;  //2 nghĩa là thêm thành công

    }

    private Book.BookBuilder createBookBuilder(ResultSet rs) throws SQLException {
        Category category = new Category(rs.getString("category.id"), rs.getString("category.category"));
        Publisher publisher = new Publisher(rs.getString("publisher.id"), rs.getString("publisher.pub"));
        return new Book.BookBuilder()
                .setId(rs.getString("book.id"))
                .setTitle(rs.getString(COLUM_TITLE_TABLE_BOOK))
                .setAuthor(rs.getString(COLUM_AUTHOR_TABLE_BOOK))
                .setPublisher(publisher)
                .setCategory(category);

    }

    /**
     * Lấy tất cả sách trong thư viện kèm theo các bản copy của sách đó
     *
     * @return tập hợp sách trong thư viện
     * @exception Lỗi kết nối csdl hoặc lỗi câu truy vấn
     */
    @Override
    public List<Book> getAll() {
        List<Book> results = new ArrayList<>();;
        try {
            System.out.println("getAll" + GET_ALL_BOOK);
            System.out.println("getById" + GET_ALL_BOOKCOPY_BY_ID);
            Connection c = Mysql.getInstance().getConnection();
            Statement st = c.createStatement();
            PreparedStatement st2 = c.prepareStatement(GET_ALL_BOOKCOPY_BY_ID);

            ResultSet rs = st.executeQuery(GET_ALL_BOOK);
            while (rs.next()) {
                String id = rs.getString(COLUM_ID_TABLE_BOOK);
                System.out.println("getId" + id);
                Book.BookBuilder bookBuilder = createBookBuilder(rs);
                List<BookCopy> listCopy = new ArrayList<>();
                st2.setString(1, id);
                System.out.println(st2);
                ResultSet rs2 = st2.executeQuery();
                while (rs2.next()) {
                    System.out.println("Hello" + id);
                    BookCopy copy = new BookCopy.BookCopyBuilder()
                            .setId(id)
                            .setCopyNumber(rs2.getString(COLUMN_ID_COPY))
                            .setStatus(rs2.getInt(COLUMN_STATUS))
                            .setType(rs2.getString(COLUMN_TYPE))
                            .setPrice(rs2.getDouble(COLUMN_PRICE))
                            .build();
                    listCopy.add(copy);

                }
                bookBuilder.setListCopies(listCopy);
                results.add(bookBuilder.build());
            }
            return results;

        } catch (SQLException e) {
            System.out.println("" + e.getMessage());
        }
        return results;
    }

    @Override
    public String getTypeBookCopy(String idCopy) {
        String type = "";
        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement pstmt = c.prepareStatement(GET_TYPE_BOOKCOPY);
            pstmt.setString(1, idCopy);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("pstmt Query"+ pstmt);
            rs.next();
            type = rs.getString(COLUMN_TYPE);
        } catch (SQLException ex) {
            Logger.getLogger(MysqlBookCopyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return type;
    }

    @Override
    public int getStatusBookCopy(String idCopy) {
        int type = 0;
        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement pstmt = c.prepareStatement(GET_STATUS_BOOKCOPY);
            pstmt.setString(1, idCopy);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            type = rs.getInt(COLUMN_STATUS);
        } catch (SQLException ex) {
            Logger.getLogger(MysqlBookCopyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return type;
    }

    @Override
    public boolean createRegisteBook(int id, String idCard, String name, Date dateNow) {
        Connection c = null;
        try {
            c = Mysql.getInstance().getConnection();
            PreparedStatement pstmt = c.prepareStatement(INSERT_REGISTE_BOOKCOPY);
            pstmt.setInt(1, id);
            pstmt.setString(2, idCard);
            pstmt.setString(3, name);
            pstmt.setDate(4, dateNow);
            int check = pstmt.executeUpdate();
            if (check > 0) {
                c.close();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlBookCopyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Hàm này dùng để cập nhật trạng thái đã đăng ký khi sách copy đó có độc
     * giả đăng ký
     *
     * @param idCopy mã sách copy cần cập nhật
     * @return true nếu cập nhật thành công false nếu cập nhật thất bại
     * @exception Lỗi kết nối csdl hoặc lỗi câu truy vấn
     */
    @Override
    public boolean updateStatusRegistedBookCopy(String idCopy) {
        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement pstmt = c.prepareStatement(UPDATE_STATUS_BOOKCOPY);
            pstmt.setInt(1, Constants.STATUS_REGISTED);
            pstmt.setString(2, idCopy);
            int check = pstmt.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlBookCopyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Hàm này dùng để cập nhật trạng thái có thể mượn khi sách copy đó có độc
     * giả hủy đăng ký hoặc độc giả trả
     *
     * @param idCopy mã sách copy cần cập nhật
     * @return true nếu cập nhật thành công false nếu cập nhật thất bại
     * @exception Lỗi kết nối csdl hoặc lỗi câu truy vấn
     */
    @Override
    public boolean updateStatusUnRegistedBookCopy(String idCopy) {
        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement pstmt = c.prepareStatement(UPDATE_STATUS_BOOKCOPY);
            pstmt.setInt(1, Constants.STATUS_BORROWABLE);
            pstmt.setString(2, idCopy);
            int check = pstmt.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlBookCopyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<BookCopy> getAllBookCopyById(String id) {
        List<BookCopy> listBookCopy = new ArrayList<>();
        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement pstmt = c.prepareStatement(GET_ALL_BOOKCOPY_BY_ID);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String idCopy = rs.getString(COLUMN_ID_COPY);
                int status = rs.getInt(COLUMN_STATUS);
                String type = rs.getString(COLUMN_TYPE);
                Double price = rs.getDouble(COLUMN_PRICE);
                BookCopy bookCopy = new BookCopy.BookCopyBuilder()
                        .setId(id)
                        .setCopyNumber(idCopy)
                        .setStatus(status)
                        .setType(type)
                        .setPrice(price)
                        .build();
                listBookCopy.add(bookCopy);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlBookCopyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listBookCopy;
    }

    @Override
    public String getCountBookCopyById(String idBook) {
        String countBookCopy = "0";
        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement pstmt = c.prepareStatement(GET_COUNT_BOOKCOPY_BYID);
            pstmt.setString(1, idBook);
            System.out.println("Query " +pstmt);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                countBookCopy = rs.getString("count");
            }
            System.out.println(countBookCopy);
        } catch (SQLException ex) {
            Logger.getLogger(MysqlBookCopyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return countBookCopy;
    }

    /**
     * Lấy ra tất cả sách copy ở trạng thái có thể mượn theo mã sách
     *
     * @param idBook mã sách
     * @return Tập các bản copy
     * @exception Lỗi kết nối csdl hoặc lỗi câu truy vấn
     */
    @Override
    public List<BookCopy> getBookCopyListBorrowableById(String idBook) {
        List<BookCopy> listBookCopy = new ArrayList<>();
        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement pstmt = c.prepareStatement(GET_BOOKCOPY_BORROWABLE_BYID);
            pstmt.setString(1, idBook);
            pstmt.setInt(2, Constants.STATUS_BORROWABLE);
            pstmt.setString(3, Constants.TYPE_BORROWABLE);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String idCopy = rs.getString(COLUMN_ID_COPY);
                int status = rs.getInt(COLUMN_STATUS);
                String type = rs.getString(COLUMN_TYPE);
                Double price = rs.getDouble(COLUMN_PRICE);
                BookCopy bookCopy = new BookCopy.BookCopyBuilder()
                        .setId(idBook)
                        .setCopyNumber(idCopy)
                        .setStatus(status)
                        .setType(type)
                        .setPrice(price)
                        .build();
                listBookCopy.add(bookCopy);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlBookCopyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listBookCopy;
    }

    /**
     * Lấy ra số lớn nhất chứ trong id copy : giả sử có 3 id Copy là ITBCP1
     * ITBCP2 ITBCP3 thì max sẽ là 3
     *
     * @param id mã sách để tìm các mã copy tương ứng
     * @return 0 nếu không tìm thấy mã sách copy nào hoặc lỗi truy vấn số lớn
     * nhất cần tìm
     */
    @Override
    public int getMaxCountIdCopyByBookId(String id) {
        int max = 0;
        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement st = c.prepareStatement(GET_ID_BOOKCOPY_BY_ID);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String idCopy = rs.getString(COLUMN_ID_COPY);
                System.out.println("String Matcher id" + idCopy);
                Pattern word = Pattern.compile(PREFIX_ID_COPY);
                Matcher match = word.matcher(idCopy);
                while (match.find()) {
                    String numOfId = idCopy.substring(match.end(), idCopy.length());
                    System.out.println("String Matcher" + numOfId);
                    if (Utils.isStringInteger(numOfId)) {
                        int number = Integer.valueOf(numOfId);
                        if (max < number) {
                            max = number;
                        }
                    }
                }
            }
            c.close();
            return max;
        } catch (SQLException e) {
        }
        return -1;
    }

    /**
     * Hàm này dùng để lấy id category từ id sách
     *
     * @param id mã sách cần tìm id category
     * @return null nếu lỗi truy vấn hoặc id không tồn tại trong csdl id
     * category (ví dụ IT) cần tìm
     */
    @Override
    public String getCatCodeById(String id) {

        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement st = c.prepareStatement(GET_CAT_BY_ID);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString(COLUMN_CATEGORY);
            }
            c.close();

        } catch (SQLException e) {
        }
        return null;
    }

    @Override
    public int generateIdRegiste() {
        int count = 0;
        try {
            Connection c = Mysql.getInstance().getConnection();
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery(GENNERATE_ID_REGISTE);
            if (rs.next()) {
                count = rs.getInt(1) + 1;
                System.out.println("" + count);
            }
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(MysqlBookCopyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }

//    @Override
//    public int generateIdRegisteDetail() {
//        try {
//            Connection c = Mysql.getInstance().getConnection();
//            Statement stm = c.createStatement();
//            ResultSet rs = stm.executeQuery(GENNERATE_ID_REGISTE_DETAIL);
//            if (rs.next()) {
//                return rs.getInt(COLUM_ID_TABLE__REGISTE_BOOK) + 1;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(MysqlBookCopyDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return 1;
//    }
    @Override
    public boolean registeBookCopy(int id, String idCopy, String title) {
        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement pstmt = c.prepareStatement(INSERT_REGISTE_BOOKCOPY_DELTAIL);
            pstmt.setInt(1, id);
            pstmt.setString(2, idCopy);
            pstmt.setString(3, title);
            int check = pstmt.executeUpdate();
            if (check > 0) {
                c.close();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlBookCopyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean unRegisteBookCopy(String idCopy) {
        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement pstmt = c.prepareStatement(UN_REGISTE_BOOKCOPY_DETAIL);
            pstmt.setString(1, idCopy);
            int idRegisted = getIdRegistedByIdBookCopy(idCopy);
            int countBookSameIdRegisted = getCountBookCopySameIdRegisted(idRegisted);
            int check = pstmt.executeUpdate();
            if (countBookSameIdRegisted == 1) {
                PreparedStatement pstmt1 = c.prepareStatement(UN_REGISTE_BOOKCOPY);
                pstmt1.setInt(1, idRegisted);
                int check1 = pstmt1.executeUpdate();
                if (check > 0 && check1 > 0) {
                    return true;
                }
            }
            if (check > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlBookCopyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public int getIdRegistedByIdBookCopy(String idCopy) {
        int idRegisted = 0;
        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement pstmt = c.prepareStatement(GET_ID_REGISTED_BY_IDBOOKCOPY);
            pstmt.setString(1, idCopy);
            System.out.println(pstmt);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                idRegisted = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlBookCopyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idRegisted;
    }

    @Override
    public int getCountBookCopySameIdRegisted(int idRegisted) {
        int count = 0;
        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement pstmt = c.prepareStatement(GET_COUNT_BOOKCOPY_SAME_ID_REGISTED);
            pstmt.setInt(1, idRegisted);
            System.out.println(pstmt);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlBookCopyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    @Override
    public int getCountBookCopyUnPainAndNoOutDate(String idCard) {
        int count = 0;
        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement pstmt = c.prepareStatement(GET_COUNT_BOOKCOPY_UNPAIN_NOOUTDATE);
            pstmt.setString(1, idCard);
//            System.out.println(pstmt);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlBookCopyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    @Override
    public List<RegisterBorrow> getRegistedBookCopyLisrByCardNo(String cardNo) {
        List<RegisterBorrow> listRegisterBorrow = new ArrayList<>();

        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement pstmt = c.prepareStatement(GET_ALL_REGISTED_BY_IDCARD);
            pstmt.setString(1, cardNo);
            ResultSet rs = pstmt.executeQuery();
            int id = 0;
            Date regDate = null;
            while (rs.next()) {
                id = rs.getInt(COLUMN_REGISTE_BOOK_ID);
                regDate = rs.getDate(COLUMN_REGISTE_DATE);
                RegisterBorrow rb = new RegisterBorrow();
                rb.setId(id);
                rb.setRegDate(regDate);
                listRegisterBorrow.add(rb);
            }
            for (int i = 0; i < listRegisterBorrow.size(); i++) {
                PreparedStatement pstmt1 = c.prepareStatement(GET_ALL_REGISTED_BOOKCOPY_BY_ID);
                pstmt1.setInt(1, listRegisterBorrow.get(i).getId());
                ResultSet rs1 = pstmt1.executeQuery();
                System.out.println(pstmt1);
                List<RegisterTermDetail> list = new ArrayList<>();
                while (rs1.next()) {
                    String idCopy = rs1.getString(COLUMN_ID_COPY);
                    String title = rs1.getString(COLUMN_TITLE);
                    RegisterTermDetail rdt = new RegisterTermDetail(listRegisterBorrow.get(i).getId(), idCopy, title);
                    list.add(rdt);
                }
                listRegisterBorrow.get(i).setDetails(list);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlBookCopyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listRegisterBorrow;
    }

    @Override

    public List<Loan> getListLoanBook(String cardNo) {
        List<Loan> listLoanBook = new ArrayList<>();
        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement pstmt = c.prepareStatement(GET_LIST_LOAN_BOOK_BY_CARDNO);
            pstmt.setString(1, cardNo);
            ResultSet rs = pstmt.executeQuery();
            System.out.println(pstmt);
            while (rs.next()) {
                int id = rs.getInt(COLUM_ID_TABLE_LOAN_BOOK);
                Date startDate = rs.getDate(COLUM_STATDATE_TABLE_LOAN_BOOK);
                Date endDate = rs.getDate(COLUM_ENDDATE_TABLE_LOAN_BOOK);
                Double money = rs.getDouble(COLUM_MONEY_TABLE_LOAN_BOOK);
                Loan loan = new Loan.Builder()
                        .setId(id)
                        .setStartDate(startDate)
                        .setEndDate(endDate)
                        .setMoney(money).build();
                listLoanBook.add(loan);
            }
            for (int i = 0; i < listLoanBook.size(); i++) {
                PreparedStatement pstmt1 = c.prepareStatement(GET_LIST_LOAN_BOOK_DETAIL_BY_CARDNO);
                pstmt1.setInt(1, listLoanBook.get(i).getId());
                ResultSet rs1 = pstmt1.executeQuery();
                System.out.println(pstmt1);
                List<LoanDetail> list = new ArrayList<>();
                while (rs1.next()) {
                    String idCopy = rs1.getString(COLUMN_ID_COPY);
                    String title = rs1.getString(COLUMN_TITLE);
                    LoanDetail loanDetail = new LoanDetail.Builder().setId(listLoanBook.get(i).getId()).setIdCopy(idCopy).setTitle(title).build();
                    list.add(loanDetail);
                }
                listLoanBook.get(i).setDetails(list);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MysqlBookCopyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listLoanBook;
    }

    @Override
    public boolean unAllRegisteBookCopy(String cardNo) {
        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement pstmt1 = c.prepareStatement(UN_ALL_REGISTED_BOOKCOPY);
//            System.out.println(pstmt1);
            List<Integer> listIdRegisted = getlistIdRegistedBookCopy(cardNo);
            for (int i = 0; i < listIdRegisted.size(); i++) {
                PreparedStatement pstmt = c.prepareStatement(UN_ALL_REGISTED_BOOKCOPY_DETAIL);
                System.out.println(listIdRegisted.get(i));
                pstmt.setInt(1, listIdRegisted.get(i));
                int check = pstmt.executeUpdate();
                if (check < 0) {
                    return false;
                }
            }
            pstmt1.setString(1, cardNo);
            int check1 = pstmt1.executeUpdate();
            if (check1 > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlBookCopyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Integer> getlistIdRegistedBookCopy(String cardNo) {
        List<Integer> listIdRegisted = new ArrayList<>();
        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement pstmt = c.prepareStatement(GET_ALL_REGISTED_BY_IDCARD);
            pstmt.setString(1, cardNo);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                listIdRegisted.add(rs.getInt(COLUM_ID_TABLE__REGISTE_BOOK));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlBookCopyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listIdRegisted;
    }

    @Override
    public List<String> getAllIdBook() {
        List<String> listBookID = new ArrayList<>();
        try {
            Connection c = Mysql.getInstance().getConnection();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(GET_ALL_ID_BOOK);
            while (rs.next()) {

                listBookID.add(rs.getString("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlBookCopyDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return listBookID;
    }

    @Override
    public Book getBookById(String id) {

        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement st = c.prepareCall(GET_BOOK_BY_ID);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Book.BookBuilder builder = createBookBuilder(rs);
                return builder.build();
            }

        } catch (SQLException ex) {
            Logger.getLogger(MysqlBookCopyDao.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    @Override
    public int getCountRegistedBookCopyByCardNo(String cardNo) {
        int count = 0;
        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement pstmt = c.prepareStatement(GET_COUNT_REGISTED_BOOKCOPY_BY_CARDNO);
            pstmt.setString(1, cardNo);
//            System.out.println(pstmt);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlBookCopyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public static void main(String[] args) {
        MysqlBookCopyDao mbcd = new MysqlBookCopyDao();
//        System.out.println(mbcd.getCountRegistedBookCopyByCardNo("BCLIBRARY2"));
        System.out.println(mbcd.generateIdRegiste());
        System.out.println(mbcd.generateIdBook("IT"));
    }

    @Override
    public List<BookCopy> getListCopyBorrowByCount(String id, int count) {
        List<BookCopy> results = new ArrayList<>();
        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement pstmt = c.prepareStatement(GET_LIST_COPY_BORROW_BY_COUNT);
            pstmt.setString(1, id);
            pstmt.setInt(2, count);
            System.out.println(pstmt);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                BookCopy bookCopy = new BookCopy.BookCopyBuilder()
                        .setId(id)
                        .setCopyNumber(rs.getString("idcopy"))
                        .setTitle(rs.getString("title"))
                        .build();
                results.add(bookCopy);

            }

        } catch (SQLException ex) {
            Logger.getLogger(MysqlBookCopyDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return results;

    }

    @Override
    public boolean updateStatusRegistedInLibraryBookCopy(int status, String idCopy) {
        try {
            Connection c = Mysql.getInstance().getConnection();
            PreparedStatement pstmt = c.prepareStatement(UPDATE_STATUS_BOOKCOPY);
            pstmt.setInt(1, status);
            pstmt.setString(2, idCopy);
            int check = pstmt.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MysqlBookCopyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public int updateInforBookCopy(String idCopy, String status, String typeOld, String typeNew, double price) {
        if(status.equalsIgnoreCase(STATUS_BORROWABLE1) == false && typeNew.equalsIgnoreCase(typeOld)==false){
            return RESULT_EXCEPTION_STATUS;
        }else if(price<=0){
            return RESULT_EXCEPTION_PRICE;
        }else{
            try {
                Connection c = Mysql.getInstance().getConnection();
                PreparedStatement pstmt = c.prepareStatement(UPDATE_INFOR_BOOKCOPY);
                pstmt.setString(1, typeNew);
                pstmt.setDouble(2, price);
                pstmt.setString(3, idCopy);
                int check = pstmt.executeUpdate();
                System.out.println(pstmt);
                if(check>0){
                    return RESULT_SUCCESS;
                }
            } catch (SQLException ex) {
                Logger.getLogger(MysqlBookCopyDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return RESULT_SQLITE;
    }
}
