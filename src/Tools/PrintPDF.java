/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import DAO.Bill;
import DAO.BillDAO;
import DAO.Chitietdonhang;
import DAO.ChitietdonhangDAO;
import DAO.DonhangDAO;
import DAO.KhachhangDAO;
import DAO.SanphamDAO;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author Chu Ai Duc
 */
public class PrintPDF {

    public PrintPDF() {
    }

    Locale localeVN = new Locale("vi", "VN");
    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
    BillDAO dao = new BillDAO();
    public static String trangthai="Mua hàng";
    public static String destination="";
    public void Print(int madh) {
        Document document = new Document();
        Calendar car = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss a    dd/MM/yyyy");
        SimpleDateFormat formatter1 = new SimpleDateFormat(" dd-MM-yyyy hh-mm-ss a");
        ChitietdonhangDAO daoct = new ChitietdonhangDAO();
        DonhangDAO daoDH = new DonhangDAO();
        SanphamDAO daoSP = new SanphamDAO();
        KhachhangDAO daoKH = new KhachhangDAO();
        daoDH.reLoad();
        daoKH.reLoad();
        daoSP.reLoad();

//       
        String ngay = formatter1.format(car.getTime());
        String ngay1= formatter.format(car.getTime());
        String filename = "Hoadon" + madh + ngay + ".pdf";
        File directory = new File("Bills");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File path = new File("Font");
        String fontlocation = path.getAbsolutePath();
        File newfilepdf = new File(directory, filename);
        destination = Paths.get(newfilepdf.getAbsolutePath()).toString();

        try {
            BaseFont bf = BaseFont.createFont(fontlocation + "\\TAHOMA.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(bf, 9);
            BaseFont bf3 = BaseFont.createFont(fontlocation + "\\TAHOMABD.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fontred = new Font(bf3, 10);
            fontred.setColor(232, 57, 95);
            BaseFont bf2 = BaseFont.createFont(fontlocation + "\\TAHOMABD.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font2 = new Font(bf2, 10);
            BaseFont bf1 = BaseFont.createFont(fontlocation + "\\TAHOMA.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font1 = new Font(bf1, 18);
            PdfWriter.getInstance(document, new FileOutputStream(new File(destination)));
            document.open();
            //ROW1
            Paragraph p = new Paragraph("\nĐịa chỉ: xxx Nguyễn Thị Minh Khai, P6. Q1, TPHCM\nHOTLINE: 0981.669.267", font);
            p.setAlignment(Element.ALIGN_CENTER);
            Paragraph p1 = new Paragraph("CỬA HÀNG SỮA DT MILK", font1);
            p1.setAlignment(Element.ALIGN_CENTER);
            Image img = Image.getInstance("D:\\logoTM.png");
            img.setAlignment(Element.ALIGN_LEFT);
            PdfPTable table = new PdfPTable(4);

            PdfPCell cell1 = new PdfPCell(img);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setBorder(0);
            table.addCell(cell1);
            PdfPCell cell2 = new PdfPCell(new Phrase("DT MILK", font1));
            cell2.addElement(p1);
            cell2.addElement(p);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell2.setColspan(3);
            cell2.setBorder(0);
            table.addCell(cell2);
            table.completeRow();
            //ROW2
            PdfPCell cellrow2 = new PdfPCell(new Phrase("BIÊN LAI", font1));
            cellrow2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellrow2.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cellrow2.setColspan(4);
            cellrow2.setBorder(0);
            table.addCell(cellrow2);
            table.completeRow();
            //ROW KHACH HANG
            PdfPCell cellrowKH = new PdfPCell(new Phrase("Khách hàng: " + daoKH.find(daoDH.find(madh).getMakh()).getHoten(), font2));
            cellrowKH.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellrowKH.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cellrowKH.setColspan(4);
            cellrowKH.setBorder(0);
            table.addCell(cellrowKH);
            table.completeRow();
            //ROW 3
            PdfPCell cellrow3 = new PdfPCell(new Phrase("------------------------------------------------------------------------------------------------------------------------------", font));
            cellrow3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellrow3.setVerticalAlignment(Element.ALIGN_CENTER);
            cellrow3.setColspan(4);
            cellrow3.setBorder(0);
            table.addCell(cellrow3);
            table.completeRow();
            //ROW 4
            PdfPCell cellrow4 = new PdfPCell(new Phrase("Mã hóa đơn:\nNhân viên:\nThời gian:", font));
            cellrow4.setBorder(0);
            cellrow4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellrow4.setColspan(2);
            table.addCell(cellrow4);
            PdfPCell cellrow42nd = new PdfPCell(new Phrase(madh + "\n" + Tienich.user.getHoten() + "\n" + ngay1, font));
            cellrow42nd.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellrow42nd.setColspan(2);
            cellrow42nd.setBorder(0);
            table.addCell(cellrow42nd);
            table.completeRow();
            //ROW 5
            PdfPCell cellrow5 = new PdfPCell(new Phrase("------------------------------------------------------------------------------------------------------------------------------", font));
            cellrow5.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellrow5.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cellrow5.setColspan(4);
            cellrow5.setBorder(0);
            table.addCell(cellrow5);
            table.completeRow();
            //ROW 6
            PdfPCell cellrow61st = new PdfPCell(new Phrase("Mặt hàng", font2));
            cellrow61st.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellrow61st.setColspan(2);
            cellrow61st.setBorder(0);
            table.addCell(cellrow61st);
            PdfPCell cellrow62nd = new PdfPCell(new Phrase("SL", font2));
            cellrow62nd.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellrow62nd.setBorder(0);
            table.addCell(cellrow62nd);
            PdfPCell cellrow63rd = new PdfPCell(new Phrase("Thành tiền", font2));
            cellrow63rd.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellrow63rd.setBorder(0);
            table.addCell(cellrow63rd);
            table.completeRow();
            //ROW DATA
            for (Chitietdonhang chitiet : daoct.laybyHD(madh)) {
                PdfPCell cellrowdata = new PdfPCell(new Phrase(daoSP.find(chitiet.getMasp()).getTensp() + "\n" + currencyVN.format(chitiet.getDongia()), font));
                cellrowdata.setBorder(0);
                cellrowdata.setColspan(2);
                cellrowdata.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cellrowdata);
                PdfPCell cellrowdata2nd = new PdfPCell(new Phrase(chitiet.getSoluong() + "", font));
                cellrowdata2nd.setBorder(0);
                cellrowdata2nd.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cellrowdata2nd);
                PdfPCell cellrowdata3rd = new PdfPCell(new Phrase(currencyVN.format(chitiet.getDongia() * chitiet.getSoluong()), font));
                cellrowdata3rd.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cellrowdata3rd.setBorder(0);
                table.addCell(cellrowdata3rd);
                table.completeRow();
            }
            //ROW 7
            PdfPCell cellrow7 = new PdfPCell(new Phrase("------------------------------------------------------------------------------------------------------------------------------", font));
            cellrow7.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellrow7.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cellrow7.setColspan(4);
            cellrow7.setBorder(0);
            table.addCell(cellrow7);
            table.completeRow();
            //ROW TONG TIEN
            PdfPCell cellrowtt1st = new PdfPCell(new Phrase("Tổng:", font2));
            cellrowtt1st.setBorder(0);
            cellrowtt1st.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cellrowtt1st);
            PdfPCell cellrowtt2nd = new PdfPCell(new Phrase(daoDH.getTongtien(madh), fontred));
            cellrowtt2nd.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellrowtt2nd.setColspan(3);
            cellrowtt2nd.setBorder(0);
            table.addCell(cellrowtt2nd);
            table.completeRow();
            //ROW WIFI
            PdfPCell cellrowwifi = new PdfPCell(new Phrase("\n\nMọi ý kiến, thái độ về nhân viên, liên hệ 0333.81.2222 Mr Đức\n\nXin cảm ơn quý khách!", font2));
            cellrowwifi.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellrowwifi.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cellrowwifi.setColspan(4);
            cellrowwifi.setBorder(0);
            table.addCell(cellrowwifi);
            table.completeRow();
            document.add(table);
            document.close();
            System.out.println(madh+filename+daoDH.getTongtienFloat(madh)+trangthai);
            dao.addDB(new Bill(madh, filename,daoDH.getTongtienFloat(madh),trangthai));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
