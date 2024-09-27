package cn.edu.lzu.fmbank.commons.response;

public class ServerResponse<E> {
    String code;
    String errorMessage;

    E body;

    public ServerResponse() {
    }

    public ServerResponse(String code, String errorMessage, E response) {
        this.code = code;
        this.errorMessage = errorMessage;
        this.body = response;
    }

    public ServerResponse(String code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public ServerResponse(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public E getBody() {
        return body;
    }

    public void setBody(E body) {
        this.body = body;
    }
}
