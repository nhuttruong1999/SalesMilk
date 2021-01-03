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
public class Donhang {
    private int madh;
    private String ngaydh;
    private String trangthai;
    private String makh;
    private String manv;

    public Donhang(int madh, String ngaydh, String trangthai, String makh, String manv) {
        this.madh = madh;
        this.ngaydh = ngaydh;
        this.trangthai = trangthai;
        this.makh = makh;
        this.manv = manv;
    }

    public Donhang(String ngaydh, String trangthai, String makh, String manv) {
        this.ngaydh = ngaydh;
        this.trangthai = trangthai;
        this.makh = makh;
        this.manv = manv;
    }

    public int getMadh() {
        return madh;
    }

    public void setMadh(int madh) {
        this.madh = madh;
    }

    public String getNgaydh() {
        return ngaydh;
    }

    public void setNgaydh(String ngaydh) {
        this.ngaydh = ngaydh;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    
}
