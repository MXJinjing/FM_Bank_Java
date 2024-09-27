package cn.edu.lzu.fmbank.commons.response;

public class ResponseEnum {
    public static String SUCCESS = "success";
    public static String ERROR   = "error";

    public static String ERROR_USER_NOT_EXISTS = "user not exist!";
    public static String ERROR_USER_PW_NOT_MATCH = "user and password not match!";
    public static String ERROR_SEX_NOT_VALID = "sex not valid!";
    public static String ERROR_TEL_NOT_VALID = "phone number not valid!";
    public static String ERROR_DATE_NOT_VALID = "birth date not valid!";
    public static String ERROR_BALANCE_TO_LIMIT = "account balance up to limit";
    public static String ERROR_BALANCE_NOT_ENOUGH = "account balance not enough";
    public static String ERROR_ACCESS_DENIED = "503 access denied, please login first";
    public static String ERROR_RESOURCE_NOT_FOUND = "404 RESOURCES NOT FOUND";
}
