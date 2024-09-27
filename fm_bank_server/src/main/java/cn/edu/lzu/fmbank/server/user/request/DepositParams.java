package cn.edu.lzu.fmbank.server.user.request;

public class DepositParams extends RequestParams{
    private String bid;
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }



    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }
}
