package dev.duongson.clvmonan;

public class MonAn {
    int img;
    String name;
    double price;
    boolean isBuy;

    public MonAn(int img, String name, double price, boolean isBuy) {
        this.img = img;
        this.name = name;
        this.price = price;
        this.isBuy = isBuy;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }
}
