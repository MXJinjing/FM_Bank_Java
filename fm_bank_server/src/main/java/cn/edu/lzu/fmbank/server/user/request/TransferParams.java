package cn.edu.lzu.fmbank.server.user.request;

public class TransferParams extends RequestParams{
    private String outid;
    private String inid;
    private double balance;

    public String getOutid() {
        return outid;
    }

    public void setOutid(String outid) {
        this.outid = outid;
    }

    public String getInid() {
        return inid;
    }

    public void setInid(String inid) {
        this.inid = inid;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
