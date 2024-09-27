package cn.edu.lzu.fmbank.commons.response;

public class TransferResult extends BasicResult {
    private String bid;
    private String transferToBid;
    private String amount;

    public TransferResult() {
    }

    public TransferResult(String bid, String transferTO, String amount) {
        this.bid = bid;
        this.transferToBid = transferTO;
        this.amount = amount;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getTransferToBid() {
        return transferToBid;
    }

    public void setTransferToBid(String transferToBid) {
        this.transferToBid = transferToBid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
