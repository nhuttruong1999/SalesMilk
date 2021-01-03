/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;


import java.awt.Component;
import javax.swing.JOptionPane;
/**
 *
 * @author macosx
 */
public class Dialog {
    public static void ThongBao(Component parent, String msg){
        
          JOptionPane.showMessageDialog(parent, msg,"DT MILKS",JOptionPane.INFORMATION_MESSAGE);
     }
    
     public static boolean XacNhan(Component parent, String msg){
          int result=JOptionPane.showConfirmDialog(parent, msg,"DT MILKS",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
          return result==JOptionPane.YES_OPTION;
     }
     public static String prompt(Component parent, String msg){
          return JOptionPane.showInputDialog(parent,msg,"Hệ thống QL",JOptionPane.INFORMATION_MESSAGE);
     }
}
