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
public class NhacungcapDAO extends DAO<Nhacungcap>{

    @Override
    public int addToDB(Nhacungcap ent) {
        try {
            String sql="insert into nhacungcap values (?,?,?,?,?) ";
            return MySQL.chayUpdate(sql, ent.getMancc(),
                    ent.getTenncc(),
                    ent.getDiachi(),
                    ent.getSdt(),
                    ent.getEmail()
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
            String sql="select * from nhacungcap";
            ResultSet rs=MySQL.chaySelect(sql, (Object) null);
            while(rs.next()){
                Nhacungcap ncc = new Nhacungcap(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                list.add(ncc);
            }
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    @Override
    public int updateToDB(Nhacungcap ent) {
        try {
            String sql="update nhacungcap set tenncc=?, diachi=?, sdt=?, email=? where mancc=?";
            return MySQL.chayUpdate(sql, ent.getTenncc(),
                    ent.getDiachi(),
                    ent.getSdt(),
                    ent.getEmail(),
                    ent.getMancc()
                    );
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int deleteFromDB(Nhacungcap ent) {
        try {
            String sql="delete from nhacungcap where mancc=?";
            return MySQL.chayUpdate(sql, ent.getMancc());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Nhacungcap find(Serializable id) {
        for (int i = 0; i <list.size(); i++) {
            if(list.get(i).getMancc().equals(id))
            {
                return list.get(i);
            }
        }
        return null;
    }
    
}
