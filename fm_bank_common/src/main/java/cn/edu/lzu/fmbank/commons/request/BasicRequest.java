package cn.edu.lzu.fmbank.commons.request;

import java.io.Serializable;

public class BasicRequest implements Serializable {
    private String methodName;
    private String[] args;

    public BasicRequest() {
    }

    public BasicRequest(String methodName, String[] args) {
        this.methodName = methodName;
        this.args = args;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }
}
