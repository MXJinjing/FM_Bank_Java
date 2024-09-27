package cn.edu.lzu.fmbank.server.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import static com.mysql.cj.util.TimeUtil.DATE_FORMATTER;

public class CheckUtil {

    private static final String findUserSql = "select bid from bankusers where bid = ?";

    // 查找当前用户是否存在
    public static boolean checkUserExistence(Connection con, String bid) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(findUserSql);
        pstmt.setString(1,bid);
        ResultSet rs = pstmt.executeQuery();
        return rs.next();
    }

    // 检查性别是否合法
    public static  boolean checkSexValid(String in){
        return (in.equals("男")||in.equals("女"));
    }

    public static boolean checkTelValid(String in){
        for (int i = 0; i < in.length(); i++) {
            if(in.charAt(i)<'0'|| in.charAt(i)>'9'){
                return false;
            }
        }
        return true;
    }

    public static boolean checkDateValid(String in){
        DateTimeFormatter dateTimeFormatter = DATE_FORMATTER.withResolverStyle(ResolverStyle.STRICT);
        try{
            dateTimeFormatter.parse(in);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
}
