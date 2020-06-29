package dev.duongson.sqliteonthi;

public class SinhVien {
    private String ma, hoTen;
    private double diemTrungBinh;

    public SinhVien(String ma, String hoTen, double diemTrungBinh) {
        this.ma = ma;
        this.hoTen = hoTen;
        this.diemTrungBinh = diemTrungBinh;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public double getDiemTrungBinh() {
        return diemTrungBinh;
    }

    public void setDiemTrungBinh(double diemTrungBinh) {
        this.diemTrungBinh = diemTrungBinh;
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %.2f", ma, hoTen, diemTrungBinh);
    }
}
