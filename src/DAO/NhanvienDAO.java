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
public class NhanvienDAO extends DAO<Nhanvien> {

    public NhanvienDAO() {
    }

    @Override
    public int addToDB(Nhanvien ent) {
        try {
            String sql="Insert into nhanvien values (?,?,?,?,?,?,?,?)";
            return MySQL.chayUpdate(sql, ent.getManv(),
                    ent.getHoten(),
                    ent.getMatkhau(),
                    ent.isGt(),
                    ent.getDiachi(),
                    ent.getSdt(),
                    ent.isVaitro(),
                    ent.getQRCODE());
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
            String sql="select * from nhanvien";
            ResultSet rs = MySQL.chaySelect(sql, (Object) null);
            while(rs.next())
            {
                Nhanvien nv = new Nhanvien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getString(5), rs.getString(6), rs.getBoolean(7));
                list.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int updateToDB(Nhanvien ent) {
        try {
            String sql="update nhanvien set hoten=? ,matkhau=? ,gioitinh=? ,diachi=? ,sdt=? ,vaitro=? where manv=? ";
            return MySQL.chayUpdate(sql,ent.getHoten(),
                    ent.getMatkhau(),
                    ent.isGt(),
                    ent.getDiachi(),
                    ent.getSdt(),
                    ent.isVaitro(),
                    ent.getManv()
                    );
            
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int deleteFromDB(Nhanvien ent) {
        try {
            String sql="delete from nhanvien where manv=? ";
            return MySQL.chayUpdate(sql, ent.getManv());
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Nhanvien find(Serializable id) {
        for (int i = 0; i <list.size(); i++) 
        {
            if(list.get(i).getManv().equals(id))
            {
                return list.get(i);
            }
        }
        return null;
    }
    public Nhanvien getFull(String manv){
        Nhanvien a=null;
        try{
            String sql="Select * from nhanvien where manv=?";
            ResultSet rs=MySQL.chaySelect(sql, manv);
            if(rs.next()){
                a= new Nhanvien(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getString(5), rs.getString(6), rs.getBoolean(7),rs.getString(8));
            }
        }catch(Exception e){
            return null;
        }
        return a;
    }
    
}
