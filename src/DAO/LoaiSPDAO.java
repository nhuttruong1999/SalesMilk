/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.Serializable;
import java.sql.ResultSet;

/**
 *
 * @author Chu Ai Duc
 */
public class LoaiSPDAO extends DAO<LoaiSP>{

    @Override
    public int addToDB(LoaiSP ent) {
        try {
            String sql="insert into loaisp values (?,?) ";
            return MySQL.chayUpdate(sql, ent.getMaloai(),
                    ent.getTenloai()
                    ); 
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public void reLoad() {
        try {
            if(list.size()>0){
                list.clear();
            }
            String sql="select * from loaisp";
            ResultSet rs=MySQL.chaySelect(sql, (Object) null);
            while(rs.next()){
                LoaiSP a=new LoaiSP(rs.getInt("maloai"), rs.getString("tenloai"));
                list.add(a);
            }
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    @Override
    public int updateToDB(LoaiSP ent) {
        try {
            String sql="update loaisp set tensp=? where maloai=?";
            return MySQL.chayUpdate(sql, ent.getTenloai()
                    );
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int deleteFromDB(LoaiSP ent) {
        try {
            String sql="delete from  loaisp where maloai=?";
            return MySQL.chayUpdate(sql, ent.getMaloai()
                    );
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public LoaiSP find(Serializable id) {
        for (int i = 0; i <list.size(); i++) {
            if(list.get(i).getMaloai()==Integer.parseInt(id.toString()))
            {
                return list.get(i);
            }
        }
        return null;
    }
    
}
