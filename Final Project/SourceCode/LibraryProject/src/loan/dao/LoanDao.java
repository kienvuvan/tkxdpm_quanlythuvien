/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loan.dao;

import java.util.List;
import loan.model.Loan;
import loan.model.LoanDetail;
import loan.model.LoanReturn;

/**
 *
 * @author Linh
 */
public interface LoanDao {

    List<Loan> getAll();

    int borrowBook(Loan loan);

    int returnLoan(LoanReturn loan);

    List<LoanDetail> getListLoanDetailById(int idLoan);

    List<Loan> findLoanByKey(String key);

}
