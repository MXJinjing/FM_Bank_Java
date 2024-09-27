package cn.edu.lzu.fmbank.commons.response;

public class ChangeResult extends BasicResult {
    private String primaryKey;
    private String attribute;
    private String changeTo;

    public ChangeResult(){}

    public ChangeResult(String primaryKey, String attribute, String changeTo) {
        this.primaryKey = primaryKey;
        this.attribute = attribute;
        this.changeTo = changeTo;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getChangeTo() {
        return changeTo;
    }

    public void setChangeTo(String changeTo) {
        this.changeTo = changeTo;
    }
}
