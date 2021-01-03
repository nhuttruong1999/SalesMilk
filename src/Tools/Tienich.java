package Tools;


import DAO.Khachhang;
import DAO.Nhanvien;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author macosx
 */
public class Tienich {

    public static Nhanvien user;
    public static Khachhang khachhang;
    public static int SL;
    public static boolean check=false;
    public static int label;
    public static Locale localeVN = new Locale("vi", "VN");
    public static NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
    public static boolean logged() {
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean loggedAdmin() {
        return logged() && user.isVaitro();
    }

    public static boolean saveLogo(File file) {
        File directory = new File("logos");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File newFile = new File(directory, file.getName());
        try {
            Path source = Paths.get(file.getAbsolutePath());
            Path destination = Paths.get(newFile.getAbsolutePath());
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static ImageIcon readLogo(String filename) {
        File path = new File("logos", filename);
        ImageIcon imgicon = new ImageIcon(path.getAbsolutePath());
        Image img = imgicon.getImage();
        Image newimg = img.getScaledInstance(205, 227, Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    public static SimpleDateFormat DATE_FORMATER = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat DATE_FORMATER1 = new SimpleDateFormat("dd-MM-yyyy");
    public static String FixNgayDK(String date){
        try{
            Date newdate=DATE_FORMATER.parse(date);
            return DATE_FORMATER1.format(newdate);
        }catch(Exception e){
            System.out.println(e);
            return "ngayloi";
        }
    }
    
    public static String getNgayDK() {
        try {
            
            return DATE_FORMATER.format(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static ImageIcon readQR(String filename) {
        File path = new File("QRs", filename);
        ImageIcon imgicon = new ImageIcon(path.getAbsolutePath());
        Image img = imgicon.getImage();
        Image newimg = img.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }
    public static void writeQR(String username,String password){
        File directory = new File("QRs");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try {
            File FileQR = new File(directory, username+".png");
            String qrCodeData = username+password;
            String destination = Paths.get(FileQR.getAbsolutePath()).toString();
//            String filePath = "D:\\QRCODE\\hinh.png";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, 350, 350, hintMap);
            MatrixToImageWriter.writeToFile(matrix, destination.substring(destination
                .lastIndexOf('.') + 1), new File(destination));
            System.out.println("QR Code image created successfully!");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    

}
