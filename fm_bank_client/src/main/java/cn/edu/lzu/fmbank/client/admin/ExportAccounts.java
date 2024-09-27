package cn.edu.lzu.fmbank.client.admin;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import cn.edu.lzu.fmbank.server.util.DataBaseLink;

public class ExportAccounts {
    public static int count;

    public static void doit(File file) throws SQLException, IOException, WriteException {
        Connection con =  new DataBaseLink().conSQL();
        Statement stmt = con.createStatement();
        Statement stm1 = con.createStatement();
        //获取数据库数据
        ResultSet rs = stmt.executeQuery("select *  from bankusers");
        ResultSet rs1 = stm1.executeQuery("select count(*) from bankusers");
        while(rs1.next()){
            count = rs1.getInt(1);
        }
        //把数据放进集合
        ArrayList save = new ArrayList();
        while(rs.next()){
            String Str1 = rs.getString(1);
            String Str2 = rs.getString(2);
            String Str3 = rs.getString(3);
            String Str4 = rs.getString(4);
            String Str5 = rs.getString(5);
            String Str6 = rs.getString(6);
            String Str7 = rs.getString(7);
            String Str8 = rs.getString(8);

            save.add(Str1);
            save.add(Str2);
            save.add(Str3);
            save.add(Str4);
            save.add(Str5);
            save.add(Str6);
            save.add(Str7);
            save.add(Str8);

        }
        stmt.close();
        stm1.close();
        //File file = new File("C://Users//86155//Desktop//users.xls");
        if(!file.exists()){
            file.createNewFile();
        }
        WritableWorkbook wrw = Workbook.createWorkbook(file);
        WritableSheet ws = wrw.createSheet("Sheet1",0);
        ws.addCell(new Label(0,0,"用户Id"));
        ws.addCell(new Label(1,0,"用户名"));
        ws.addCell(new Label(2,0,"密码"));
        ws.addCell(new Label(3,0,"身份ID"));
        ws.addCell(new Label(4,0,"手机号"));
        ws.addCell(new Label(5,0,"性别"));
        ws.addCell(new Label(6,0,"出生日期"));
        ws.addCell(new Label(7,0,"账户余额"));
        //取出数据
        Iterator iterator = save.iterator();//迭代器遍历里面元素
        while(iterator.hasNext()){
            //把数据设置进表格
            for(int i =1;i<=count;i++){
                for(int j = 0;j<8;j++){
                    String data = (String) iterator.next();
                    ws.addCell(new Label(j,i,data));

                }
            }
            wrw.write();
            wrw.close();

        }
}}
