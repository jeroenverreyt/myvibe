package ex;

import com.itextpdf.testutils.ITextTest;
import ex.UserBean;
import ex.UserDao;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.util.Date;
import jxl.*;
import jxl.write.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import com.itextpdf.text.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

@WebServlet(value = "/ReportServlet", initParams = {
    @WebInitParam(name = "driver", value = "com.mysql.jdbc.Driver"),
    @WebInitParam(name = "url", value = "jdbc:mysql://db4free.net/myvibe10"),
    @WebInitParam(name = "user", value = "keris"),
    @WebInitParam(name = "password", value = "kerisve"),
    @WebInitParam(name = "page", value = "/report.jsp"),})
public class ReportServlet extends HttpServlet {

    private ReportDao dao;
    private String page;

    public void init() throws ServletException {
        try {
            String driver = getInitParameter("driver");
            String url = getInitParameter("url");
            String user = getInitParameter("user");
            String password = getInitParameter("password");
            page = getInitParameter("page");
            if (driver == null || url == null || user == null || password == null
                    || page == null) {
                throw new ServletException("Init parameter missing");
            }
            dao = new ReportDao();
            dao.setDriver(driver);
            dao.setUser(user);
            dao.setPassword(password);
            dao.setUrl(url);
        } catch (ClassNotFoundException ex) {
            throw new ServletException("Unable to load driver", ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            java.util.List<String> fields = dao.getFields();
            request.setAttribute("fields", fields);
            RequestDispatcher disp = request.getRequestDispatcher(page);
            if (disp != null) {
                disp.forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String[] selectedFields = request.getParameterValues("selectFields");
        java.util.List<String> data = null;

        if (request.getParameter("excel") != null) {

            try {
                WritableWorkbook workbook = Workbook.createWorkbook(new File("Report.xls"));
                WritableSheet sheet = workbook.createSheet("First Sheet", 0);
                WritableFont arial15font = new WritableFont(WritableFont.ARIAL, 15, WritableFont.BOLD);
                WritableCellFormat arial15format = new WritableCellFormat(arial15font);
                int row = 0;
                int column = 0;

                for (String item : selectedFields) {
                    row = 0;
                    String tablename = dao.getTableName(item);
                    System.out.println(item);
                    System.out.println(tablename);
                    data = dao.getData(item, tablename);
                    Label lblTable = new Label(column, row, item, arial15format);
                    sheet.addCell(lblTable);
                    column++;

                    for (String d : data) {
                        row++;
                        System.out.println("DATA " + d);
                        Label lblData = new Label(column - 1, row, d);
                        sheet.addCell(lblData);

                    }
                }

                expandColumn(sheet, 4);

                workbook.write();
                workbook.close();

            } catch (Exception e) {

            }

        } else if (request.getParameter("pdf") != null) {

            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream("Report.pdf"));

                document.open();

                com.itextpdf.text.Font font1 = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER, 15);
                com.itextpdf.text.Font font2 = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER, 18, com.itextpdf.text.Font.BOLD | com.itextpdf.text.Font.UNDERLINE);

                for (String item : selectedFields) {

                    String tablename = dao.getTableName(item);
                    data = dao.getData(item, tablename);
                    document.add(new Phrase(item, font2));

                    for (String d : data) {

                        com.itextpdf.text.List list = new com.itextpdf.text.List(com.itextpdf.text.List.UNORDERED);
                        list.add(new ListItem(d, font1));
                        document.add(list);
                    }
                }

                document.close();
            } catch (Exception e) {

            }

        }
        doGet(request, response);
    }

    private void expandColumn(WritableSheet sheet, int amountOfColumns) {
        int c = amountOfColumns;
        for (int x = 0; x < c; x++) {
            CellView cell = sheet.getColumnView(x);
            cell.setAutosize(true);
            sheet.setColumnView(x, cell);
        }
    }

}
