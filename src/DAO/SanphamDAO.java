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
public class SanphamDAO extends DAO<Sanpham>{

    @Override
    public int addToDB(Sanpham ent) {
        try {
            String sql="insert into sanpham values (?,?,?,?,?,?,?)";
            return MySQL.chayUpdate(sql,ent.getMasp(),
                    ent.getTensp(),
                    ent.getDongia(),
                    ent.getHinh(),
                    ent.getMancc(),
                    ent.getMaloai(),
                    ent.getSoluong()
                    );
        } catch (Exception e) 
        {
            System.out.println(e);
            return 0;
        }
    }

    @Override
    public void reLoad() {
        try {
            if(list.size()>0){
                list.clear();
            }
            String sql= "select * from sanpham";
            ResultSet rs=MySQL.chaySelect(sql,(Object) null);
            while(rs.next()){
                Sanpham sp = new Sanpham(rs.getString("masp"), rs.getString("tensp"), rs.getFloat("dongia"), rs.getString("hinhsp"), rs.getString("mancc"), rs.getInt("maloai"), rs.getInt("soluong"));
                list.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int updateToDB(Sanpham ent) {
        try {
            String sql="update sanpham set tensp=? , dongia=? , hinhsp=? , mancc=? , maloai=?, soluong=? where masp=? ";
            return MySQL.chayUpdate(sql, ent.getTensp(),
                    ent.getDongia(),
                    ent.getHinh(),
                    ent.getMancc(),
                    ent.getMaloai(),
                    ent.getSoluong(),
                    ent.getMasp()
                    );          
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int deleteFromDB(Sanpham ent) {
        try {
            String sql="delete from sanpham where masp=?";
            return MySQL.chayUpdate(sql, ent.getMasp());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Sanpham find(Serializable id) {
        reLoad();
        for(int i=0 ;i < list.size(); i++)
        {
           if(list.get(i).getMasp().equals(id))
           {
               return list.get(i);
           }
        }
        return null;
    }
    public ArrayList<Sanpham> getListNew(int ma){
        ArrayList<Sanpham> ketqua=new ArrayList<>();
        try {
            String sql="SELECT * FROM sanpham where MASP not in (Select masp from chitietdonhang where madh=?)";
            ResultSet rs=MySQL.chaySelect(sql, ma);
            while(rs.next()){
                Sanpham newsp=new Sanpham(rs.getString("masp"), rs.getString("tensp"), rs.getFloat("dongia"), rs.getString("hinhsp"), rs.getString("mancc"),rs.getInt("maloai"), rs.getInt("soluong"));
                ketqua.add(newsp);
            }
        } catch (Exception e) {
            return null;
        }
        return ketqua;
    }
    
}
