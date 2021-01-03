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
public class Chitietdonhang {
    private int madh;
    private String masp;
    private float dongia;
    private int soluong;

    public Chitietdonhang(int madh, String masp, float dongia, int soluong) {
        this.madh = madh;
        this.masp = masp;
        this.dongia = dongia;
        this.soluong = soluong;
    }

    public void setMadh(int madh) {
        this.madh = madh;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public void setDongia(float dongia) {
        this.dongia = dongia;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getMadh() {
        return madh;
    }

    public String getMasp() {
        return masp;
    }

    public float getDongia() {
        return dongia;
    }

    public int getSoluong() {
        return soluong;
    }
    

}
