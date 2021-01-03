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
public class Nhanvien {
    private String manv;
    private String hoten;
    private String matkhau;
    private boolean gt;
    private String sdt;
    private String diachi;
    private boolean vaitro;
    private String QRCODE;

    public Nhanvien(String manv, String hoten, String matkhau, boolean gt, String sdt, String diachi, boolean vaitro) {
        this.manv = manv;
        this.hoten = hoten;
        this.matkhau = matkhau;
        this.gt = gt;
        this.sdt = sdt;
        this.diachi = diachi;
        this.vaitro = vaitro;
    }

    public Nhanvien(String manv, String hoten, String matkhau, boolean gt, String sdt, String diachi, boolean vaitro, String QRCODE) {
        this.manv = manv;
        this.hoten = hoten;
        this.matkhau = matkhau;
        this.gt = gt;
        this.sdt = sdt;
        this.diachi = diachi;
        this.vaitro = vaitro;
        this.QRCODE = QRCODE;
    }

    public String getQRCODE() {
        return QRCODE;
    }

    public void setQRCODE(String QRCODE) {
        this.QRCODE = QRCODE;
    }
    

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public boolean isGt() {
        return gt;
    }

    public void setGt(boolean gt) {
        this.gt = gt;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public boolean isVaitro() {
        return vaitro;
    }

    public void setVaitro(boolean vaitro) {
        this.vaitro = vaitro;
    }
    

    
}
