/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loan.model;

/**
 * Chi tiết phiếu trả sách,tương ứng với mỗi quyển sách được trả
 *
 * @author Linh
 */
public class LoanReturnDetail {

    public LoanReturnDetail(String idCopy, String title) {
        this.idCopy = idCopy;
        this.title = title;
    }
    private int id; //id của phiếu trả

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdCopy() {
        return idCopy;
    }

    public void setIdCopy(String idCopy) {
        this.idCopy = idCopy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    private String idCopy; //id của sách copy
    private String title;   //tên sách copy
}
