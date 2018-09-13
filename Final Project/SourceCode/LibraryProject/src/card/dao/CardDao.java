/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package card.dao;

import card.model.BorrowCard;
import java.sql.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import user.User;

/**
 *
 * @author Linh
 */
public interface CardDao {

    boolean insert(BorrowCard card);

    int getInforCardRegisted(DefaultTableModel model);

    String generateIdCard();

    int activateCard(BorrowCard card);

    String getCardNoById(String idCard);

    String getNameCardById(String id);

    int getStatusCardById(String id);

    int checkUserOverDueBookCopy(String idCard);

    BorrowCard getBorrowCardByUsername(String id);

    List<String> getAllIdCard();

    User getUserByCardNo(String cardNo);

    int getCountBookBorrowAndRegisted(String cardNo);

    int getStatusCardByCardNo(String cardNo);

    int searchCardUnActive(String key, DefaultTableModel model);

    int getAllCardActive(DefaultTableModel modelTableModel);

    int searchAllCardActiveByKey(String key, DefaultTableModel modelTableModel);

    int deActiveCard(String cardNo);

    public int updateCard(String cardNo, Date expired);

     boolean checkCardOverDueExpired(String cardNo);
}
