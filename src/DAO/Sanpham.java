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
public class Sanpham {
    private String masp;
    private String tensp;
    private float dongia;
    private String hinh;
    private String mancc;
    private int maloai;
    private int soluong;

    public Sanpham(String masp, String tensp, float dongia, String hinh, String mancc, int maloai, int soluong) {
        super();
        this.masp = masp;
        this.tensp = tensp;
        this.dongia = dongia;
        this.hinh = hinh;
        this.mancc = mancc;
        this.maloai = maloai;
        this.soluong = soluong;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public float getDongia() {
        return dongia;
    }

    public void setDongia(float dongia) {
        this.dongia = dongia;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getMancc() {
        return mancc;
    }

    public void setMancc(String mancc) {
        this.mancc = mancc;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    
    
    
    
}
