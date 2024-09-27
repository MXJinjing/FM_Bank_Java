package cn.edu.lzu.fmbank.commons.response;

public class WithdrawResult extends BasicResult {
    private String bid;
    private String withdrawValue;

    public WithdrawResult(){}

    public WithdrawResult(String bid, String withdrawValue) {
        this.bid = bid;
        this.withdrawValue = withdrawValue;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getWithdrawValue() {
        return withdrawValue;
    }

    public void setWithdrawValue(String withdrawValue) {
        this.withdrawValue = withdrawValue;
    }
}
