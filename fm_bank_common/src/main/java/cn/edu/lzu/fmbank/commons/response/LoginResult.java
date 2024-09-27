package cn.edu.lzu.fmbank.commons.response;

import cn.edu.lzu.fmbank.commons.entity.User;

public class LoginResult extends BasicResult {
    private String loginType;
    private User user;

    public LoginResult() {
    }

    public LoginResult(String loginType, User user) {
        this.loginType = loginType;
        this.user = user;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
