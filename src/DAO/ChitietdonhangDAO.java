/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author macosx
 */
public class ChitietdonhangDAO {
    public ArrayList<Chitietdonhang> list=new ArrayList<>();
    public ArrayList<Chitietdonhang> laydanhsach(){
          return list;
     }
    public int addToDB(Chitietdonhang ent) {
        try {
            String sql="insert into chitietdonhang values (?,?,?,?) ";
            return MySQL.chayUpdate(sql, ent.getMadh(),
                        ent.getMasp(),
                        ent.getDongia(),
                        ent.getSoluong()
                        );
        } catch (Exception e) {
            return 0;
        }
    }

    
    public void reLoad() {
        try {
            if(list.size()>0){
                list.clear();
            }
            String sql="select * from chitietdonhang ";
            ResultSet rs = MySQL.chaySelect(sql, (Object) null);
            while(rs.next())
            {
                Chitietdonhang newctdh = new Chitietdonhang(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4));
                list.add(newctdh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public int updateToDB(Chitietdonhang ent) {
        try {
            String sql="update chitietdonhang set soluong=? where madh=? and masp=?";
            return MySQL.chayUpdate(sql, 
                       ent.getSoluong(),
                       ent.getMadh(),
                       ent.getMasp()
                       );
        } catch (Exception e) {
            return 0;
        }
    }

    
    public int deleteFromDB(Chitietdonhang ent) {
        try {
            String sql="delete from chitietdonhang where madh=? and masp=?";
            return MySQL.chayUpdate(sql, ent.getMadh(), ent.getMasp());
        } catch (Exception e) {
            return 0;
        }
    }

    
    public Chitietdonhang find(int madh, String masp) {
        reLoad();
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i).getMadh()==madh && list.get(i).getMasp().equals(masp))
            {
                return list.get(i);
            }
        }
        return null;
    }
    public ArrayList<Chitietdonhang> laybyHD(int x){
        ArrayList<Chitietdonhang> ds=new ArrayList<>();
          try {
            String sql="Select * from chitietdonhang where madh=?";
            ResultSet rs=MySQL.chaySelect(sql, x);
            while(rs.next()){
                Chitietdonhang newct=new Chitietdonhang(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4));
                ds.add(newct);
            }
        } catch (Exception e) {
            return null;
        }
          return ds;
     }
    
}
