package cn.edu.lzu.fmbank.server.controller;

import cn.edu.lzu.fmbank.commons.request.BasicRequest;
import cn.edu.lzu.fmbank.commons.response.*;
import cn.edu.lzu.fmbank.server.user.services.*;
import com.mysql.cj.QueryResult;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class MyController {

    private static final Logger log = Logger.getLogger(MyController.class);
    private String bid;
    private Connection con;

    private LoginCheckService      loginCheckService;
    private ChangeInfoService      changeInfoService;
    private QueryInfoService       queryInfoService;
    private TransferService        transferService;
    private WithDrawService        withDrawService;
    private DepositService         depositService;
    private DestroyAccountService  destroyAccountService;

    public ServerResponse map(BasicRequest basicRequest) throws SQLException {
        String methodName = basicRequest.getMethodName();
        String[] args = basicRequest.getArgs();

        try {
            if(Objects.equals(bid, "none") && Objects.equals(methodName, "login")){
                ServerResponse<LoginResult> loginResponse = loginCheckService.login( args[0],  args[1]);
                if (loginResponse.getCode().equals(ResponseEnum.SUCCESS)) {
                    setBid(loginResponse.getBody().getUser().getBid());
                }
                return loginResponse;
            }else if(Objects.equals(bid,"none")){
                return new ServerResponse<>(ResponseEnum.ERROR, ResponseEnum.ERROR_ACCESS_DENIED);

            }else if(Objects.equals(methodName, "changeSex")){
                return changeInfoService.changeSex( args[0]);
            }else if(Objects.equals(methodName, "changeTel")){
                return changeInfoService.changeTel( args[0]);
            }else if(Objects.equals(methodName, "changeBirth")){
                return changeInfoService.changeBirth( args[0]);
            }else if(Objects.equals(methodName, "queryUser")){
                return queryInfoService.queryUser();
            }else if(Objects.equals(methodName, "queryBalance")){
                return queryInfoService.queryBalance();
            }else if(Objects.equals(methodName, "querySex")){
                return queryInfoService.querySex();
            }else if(Objects.equals(methodName, "queryId")){
                return queryInfoService.queryId();
            }else if(Objects.equals(methodName, "queryName")){
                return queryInfoService.queryName();
            }else if(Objects.equals(methodName, "queryTel")){
                return queryInfoService.queryTel();
            }else if(Objects.equals(methodName, "queryBirth")){
                return queryInfoService.queryBirth();
            }else if(Objects.equals(methodName, "transfer")){
                return transferService.transfer(args[0],Double.parseDouble(args[1]));
            }else if(Objects.equals(methodName, "withdraw")){
                return withDrawService.withdraw(Double.parseDouble(args[0]));
            }else if(Objects.equals(methodName, "deposit")){
                return depositService.deposit(Double.parseDouble(args[0]));
            }else if(Objects.equals(methodName, "destroyAccount")){
                return destroyAccountService.destroyAccount();
            }else{
                return new ServerResponse<>(ResponseEnum.ERROR,ResponseEnum.ERROR_RESOURCE_NOT_FOUND);
            }
        } catch (IndexOutOfBoundsException e) {
            return new ServerResponse<>(ResponseEnum.ERROR, ResponseEnum.ERROR_ACCESS_DENIED);
        }
    }

    public MyController(String bid, Connection con){
        this.bid = bid;
        this.con = con;
        loginCheckService = new LoginCheckService(bid, con);
        changeInfoService = new ChangeInfoService(bid,con);
        queryInfoService = new QueryInfoService(bid,con);
        transferService = new TransferService(bid,con);
        withDrawService = new WithDrawService(bid,con);
        depositService = new DepositService(bid,con);
        destroyAccountService = new DestroyAccountService(bid,con);
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
        this.loginCheckService.setBid(bid);
        this.loginCheckService.setBid(bid);
        this.changeInfoService.setBid(bid);
        this.queryInfoService .setBid(bid);
        this.transferService.setBid(bid);
        this.withDrawService.setBid(bid);
        this.depositService.setBid(bid);
        this.destroyAccountService.setBid(bid);
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
        this.loginCheckService.setCon(con);
        this.loginCheckService.setCon(con);
        this.changeInfoService.setCon(con);
        this.queryInfoService .setCon(con);
        this.transferService.setCon(con);
        this.withDrawService.setCon(con);
        this.depositService.setCon(con);
        this.destroyAccountService.setCon(con);
    }
}
