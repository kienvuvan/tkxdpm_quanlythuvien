/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loan.model;

/**
 *
 * @author Linh
 */
public class LoanDetail {

    private int id;
    private boolean isReturn;

    public LoanDetail(Builder builder) {
        this.id = builder.id;
        this.idCopy = builder.idCopy;
        this.title = builder.title;
        this.isReturn = builder.isReturn;
    }

    public boolean isIsReturn() {
        return isReturn;
    }

    public void setIsReturn(boolean isReturn) {
        this.isReturn = isReturn;
    }

    public int getId() {
        return id;
    }

    public String getIdCopy() {
        return idCopy;
    }

    public String getTitle() {
        return title;
    }

    private String idCopy;
    private String title;

    public static class Builder {

        public LoanDetail build() {
            return new LoanDetail(this);
        }

        private int id;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setIdCopy(String idCopy) {
            this.idCopy = idCopy;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setIsReturn(boolean isReturn) {
            this.isReturn = isReturn;
            return this;
        }
        private String idCopy;
        private String title;
        private boolean isReturn;
    }
}
