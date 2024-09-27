package cn.edu.lzu.fmbank.commons.response;

public class DepositResult extends BasicResult {
    private String bid;
    private String depositValue;

    public DepositResult(){}

    public DepositResult(String bid, String depositValue) {
        this.bid = bid;
        this.depositValue = depositValue;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getDepositValue() {
        return depositValue;
    }

    public void setDepositValue(String depositValue) {
        this.depositValue = depositValue;
    }
}
