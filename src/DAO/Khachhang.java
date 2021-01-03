/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author macosx
 */
public class Khachhang {
    private String makh;
    private String hoten;
    private boolean gt;
    private String diachi;
    private String sdt;
    private String ngaydk;

    public Khachhang(String makh, String hoten, boolean gt, String diachi, String sdt, String ngaydk) {
        this.makh = makh;
        this.hoten = hoten;
        this.gt = gt;
        this.diachi = diachi;
        this.sdt = sdt;
        this.ngaydk = ngaydk;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public boolean isGt() {
        return gt;
    }

    public void setGt(boolean gt) {
        this.gt = gt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getNgaydk() {
        return ngaydk;
    }

    public void setNgaydk(String ngaydk) {
        this.ngaydk = ngaydk;
    }

    
    
  
    
    
}
