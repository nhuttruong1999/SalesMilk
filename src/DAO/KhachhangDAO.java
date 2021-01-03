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
 * @author macosx
 */
public class KhachhangDAO extends DAO<Khachhang>{

    @Override
    public int addToDB(Khachhang ent) {
        try {
            String sql="insert into khachhang values (?,?,?,?,?,?)";
            return MySQL.chayUpdate(sql, ent.getMakh(),
                    ent.getHoten(),
                    ent.isGt(),
                    ent.getDiachi(),
                    ent.getSdt(),
                    ent.getNgaydk()
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
            String sql="select * from khachhang";
            ResultSet rs = MySQL.chaySelect(sql, (Object) null);
            while(rs.next())
            {
                Khachhang newkh = new Khachhang(rs.getString("makh"), rs.getString("hoten"),
                        rs.getBoolean("gioitinh"), rs.getString("diachi"), rs.getString("sdt"), rs.getString("ngaydk"));
                list.add(newkh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int updateToDB(Khachhang ent) {
        try {
            String sql="update khachhang set hoten=? ,gioitinh=? ,diachi=? ,sdt=? ,ngaydk=? where makh=? ";
            return MySQL.chayUpdate(sql, ent.getHoten(),
                    ent.isGt(),
                    ent.getDiachi(),
                    ent.getSdt(),
                    ent.getNgaydk(),
                    ent.getMakh()
                    );
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int deleteFromDB(Khachhang ent) {
        try {
            String sql="delete from khachhang where makh=? ";
            return MySQL.chayUpdate(sql, ent.getMakh());
        } catch (Exception e) {
            return 0;
        }
        
        
    }

    @Override
    public Khachhang find(Serializable id) {
        for(int i=0; i<list.size();i++)
        {
            if(list.get(i).getMakh().equals(id))
            {
                return list.get(i);
            }
        }
        return null;
    }
    
}
