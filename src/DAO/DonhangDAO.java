/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.Serializable;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author macosx
 */
public class DonhangDAO extends DAO<Donhang> {

    @Override
    public int addToDB(Donhang ent) {
        try {
            String sql = "insert into donhang values (default,?,?,?,?)";
            return MySQL.chayUpdate(sql,
                    ent.getNgaydh(),
                    ent.getTrangthai(),
                    ent.getMakh(),
                    ent.getManv()
            );
        } catch (Exception e) {
            return 0;
        }

    }

    @Override
    public void reLoad() {
        try {
            list.clear();
            String sql = "select * from donhang";
            ResultSet rs = MySQL.chaySelect(sql, (Object) null);
            while (rs.next()) {
                Donhang newDonhang = new Donhang(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                list.add(newDonhang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int updateToDB(Donhang ent) {
        try {
            String sql = "update donhang set trangthai=? ,makh=? ,manv=? where madh=? ";
            return MySQL.chayUpdate(sql,
                    ent.getTrangthai(),
                    ent.getMakh(),
                    ent.getManv(),
                    ent.getMadh()
            );
        } catch (Exception e) {
            return 0;
        }
    }

    
    public int deleteFromDB(int madh) {
        try {
            String sql = "delete from donhang where madh=? ";
            return MySQL.chayUpdate(sql, madh);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Donhang find(Serializable id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMadh() == Integer.parseInt(id + "")) {
                return list.get(i);
            }
        }
        return null;
    }

    public int getLastMaDH() {
        int kq = 0;
        try {
            String sql = "select max(madh) from donhang";
            ResultSet rs = MySQL.chaySelect(sql, (Object) null);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            return -1;
        }
        return kq;
    }

    public String getTongtien(int madh) {
        String kq = "0";
        try {
            String sql = "select sum(dongia*soluong) from chitietdonhang where madh=? group by madh";
            ResultSet rs = MySQL.chaySelect(sql, madh);
            if (rs.next()) {
                Locale localeVN = new Locale("vi", "VN");
                NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
                return currencyVN.format(rs.getFloat(1));
//                return String.format ("%, d\n",(int)rs.getFloat(1));
            }
        } catch (Exception e) {
            return "-1";
        }
        return kq;
    }
    public float getTongtienFloat(int madh){
        try {
            String sql = "select sum(dongia*soluong) from chitietdonhang where madh=? group by madh";
            ResultSet rs = MySQL.chaySelect(sql, madh);
            if (rs.next()) {
                
                return rs.getFloat(1);
            }else return -1;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int deleteFromDB(Donhang ent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
