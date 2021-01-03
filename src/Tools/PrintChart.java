/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;


import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author Chu Ai Duc
 */
public class PrintChart {
    
    public PrintChart() {
    }
    
    public void printChart(JFreeChart jchart, String tenfile) {
        try {
            
            Document document = new Document();
            document.setPageSize(new Rectangle(1200, 600, 0));
            File directory = new File("Charts");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            BufferedImage IMAGE=jchart.createBufferedImage(800, 400,null);
            File newfile=new File(directory,tenfile+".png");
            OutputStream out=new FileOutputStream(newfile);
            Dialog.ThongBao(null, newfile.getAbsolutePath());
            ImageIO.write(IMAGE, "png",out);
            System.out.println("write ok");
            File newfilepdf = new File(directory, tenfile+".pdf");
            String duongdan = Paths.get(newfilepdf.getAbsolutePath()).toString();

            PdfWriter.getInstance(document, new FileOutputStream(new File(duongdan)));
            document.open();
            //ROW1
            Image img = Image.getInstance(newfile.getAbsolutePath());
            img.setAlignment(Element.ALIGN_LEFT);
            PdfPTable table = new PdfPTable(1);

            PdfPCell cell1 = new PdfPCell(img);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setBorder(0);
            
            table.addCell(cell1);

            table.completeRow();
            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
