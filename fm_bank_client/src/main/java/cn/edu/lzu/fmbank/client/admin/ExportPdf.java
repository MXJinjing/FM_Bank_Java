package cn.edu.lzu.fmbank.client.admin;


import cn.edu.lzu.fmbank.server.util.DataBaseLink;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExportPdf {
    public static void doit(String file) throws SQLException, FileNotFoundException, DocumentException {
        Connection con = new DataBaseLink().conSQL();
        Statement stmt = con.createStatement();
        Statement stm1 = con.createStatement();
        int count = 0;
        //获取数据库数据
        int total = 0;
        ResultSet rs = stmt.executeQuery("select count(*) from bankusers where balance = 2000");
        while(rs.next()){
            total = rs.getInt(1);
        }
        ResultSet rs1 = stm1.executeQuery("select count(*) from bankusers");
        while(rs1.next()){
            count = rs1.getInt(1);
        }
        double balance = 0.0;
        ResultSet rs2 = stm1.executeQuery("select sum(balance) from bankusers");
        while(rs2.next()){
            balance = rs2.getDouble(1);
        }
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        document.add(new Paragraph("Report:"));
        document.add(new Paragraph(""));
        document.add(new Paragraph("Total number of current accounts："+count));
        document.add(new Paragraph("Number of new accounts opened: "+total));
        document.add(new Paragraph("Current total balance: "+balance));
        document.close();
    }
}
