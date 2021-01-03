/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Chu Ai Duc
 */
public class MySQL {
    public static String driver="com.mysql.cj.jdbc.Driver";
    public static String url="jdbc:mysql://localhost:3306/pro1041";
    public static String username="root";
    public static String password="1234";
    public static Connection getcn() {
        Connection cn = null;
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cn;
    }
    public static PreparedStatement PS(String cmd, Object...args){
          PreparedStatement ps=null;
          try{
               Connection cn=getcn();
               if(cmd.trim().startsWith("{")){
               ps=cn.prepareCall(cmd);
               }
               else{
                    ps=cn.prepareStatement(cmd);
               }
               for(int i=0;i<args.length;i++){
                    ps.setObject(i+1, args[i]);

               }
          }
          catch(Exception e){
               
          }
          return ps;
     }
    public static int chayUpdate(String cmd, Object...args){
          try{
               PreparedStatement pst=PS(cmd,args);
               try{
                     return pst.executeUpdate();
               }
               catch(Exception e){
                    return 0;
               }
               finally{
                    pst.getConnection().close();
               }
          }
          catch(Exception e){
               return 0;
          }
     }
     public static ResultSet chaySelect(String cmd, Object...args){
          try{
               PreparedStatement pst=PS(cmd,args);
               return pst.executeQuery();
          }
          catch(Exception e){
               
          }
          return null;
     }
}
