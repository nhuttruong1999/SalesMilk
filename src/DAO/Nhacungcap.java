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
public class Nhacungcap {
    private String mancc;
    private String tenncc;
    private String diachi;
    private String sdt;
    private String email;

    public Nhacungcap(String mancc, String tenncc, String diachi, String sdt, String email) {
        this.mancc = mancc;
        this.tenncc = tenncc;
        this.diachi = diachi;
        this.sdt = sdt;
        this.email = email;
    }

    public String getMancc() {
        return mancc;
    }

    public void setMancc(String mancc) {
        this.mancc = mancc;
    }

    public String getTenncc() {
        return tenncc;
    }

    public void setTenncc(String tenncc) {
        this.tenncc = tenncc;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
