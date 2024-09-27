package cn.edu.lzu.fmbank.client.admin;

import cn.edu.lzu.fmbank.server.util.DataBaseLink;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class ImportAccounts {

    public static boolean doit(String path) {
        try {
            File file = new File(path);
            Connection con = new DataBaseLink().conSQL();
            Statement stmt = con.createStatement();
            Workbook wb = Workbook.getWorkbook(file);
            Sheet sheet = wb.getSheet("Sheet1");


            int result = 0;
            for (int i = 0; i < sheet.getRows(); i++) {
                String cell1 = sheet.getCell(0, i).getContents();
                String cell2 = sheet.getCell(1, i).getContents();
                String cell3 = sheet.getCell(2, i).getContents();
                String cell4 = sheet.getCell(3, i).getContents();
                String cell5 = sheet.getCell(4, i).getContents();
                String cell6 = sheet.getCell(5, i).getContents();
                String cell7 = sheet.getCell(6, i).getContents();

                String[] str = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
                StringBuffer id = new StringBuffer();
                for (int j = 0; j < 10; j++) {
                    id.append(str[new Random().nextInt(10)]);
                }

                String sql = ("insert into bankusers (bid,name,password,id,tel,sex,birth,balance) values('" + cell1 + "','" + cell2 + "','" + cell3 + "','" + cell4 + "','" + cell5 + "','" + cell6 + "','" + cell7 + "','"+2000+"')");
                result = stmt.executeUpdate(sql);
                stmt.close();

            }
            wb.close();
            if (result == 1) {
                return true;
            }else
                return false;
        } catch (SQLException | BiffException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
