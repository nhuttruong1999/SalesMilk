/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Chu Ai Duc
 */

public class BillDAO {

    public BillDAO() {
    }
    
    public int addDB(Bill bill){
        try {
            String sql="insert into qlbill values (?,?,?,?)";
            return MySQL.chayUpdate(sql, bill.getMadh(),bill.getFilename(),bill.getTongtien(),bill.getTrangthai());
        } catch (Exception e) {
            return -1;
        }
    }
    public int deleteDB(int bill){
        try {
            String sql="delete from qlbill where madh=?";
            return MySQL.chayUpdate(sql,bill );
        } catch (Exception e) {
            return -1;
        }
    }
    public ArrayList<Bill> getListBill(int madh){
        ArrayList<Bill> list=new ArrayList<>();
        try {
            String sql="select * from qlbill where madh=?";
            ResultSet rs=MySQL.chaySelect(sql, madh);
            while(rs.next()){
                Bill newbill=new Bill(madh, rs.getString("filename"), rs.getFloat("tongtien"),rs.getString("status"));
                list.add(newbill);
            }
            
        } catch (Exception e) {
            return null;
        }
        return list;
    }
    
}
