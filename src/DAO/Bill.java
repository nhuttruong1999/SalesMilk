/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Chu Ai Duc
 */
public class Bill {
    private int madh;
    private String filename;
    private float tongtien;
    private String trangthai;

    public Bill(int madh, String filename, float tongtien, String trangthai) {
        this.madh = madh;
        this.filename = filename;
        this.tongtien = tongtien;
        this.trangthai = trangthai;
    }

    public int getMadh() {
        return madh;
    }

    public void setMadh(int madh) {
        this.madh = madh;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public float getTongtien() {
        return tongtien;
    }

    public void setTongtien(float tongtien) {
        this.tongtien = tongtien;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    
    

    

    
    
}
