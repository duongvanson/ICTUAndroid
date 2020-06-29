package dev.duongson.monanonthi;

import java.io.Serializable;

public class MonAn implements Serializable {
    private int anh, gia;
    private String ten;
    private boolean check;

    public MonAn(int anh, int gia, String ten, boolean check) {
        this.anh = anh;
        this.gia = gia;
        this.ten = ten;
        this.check = check;
    }

    public int getAnh() {
        return anh;
    }

    public void setAnh(int anh) {
        this.anh = anh;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
