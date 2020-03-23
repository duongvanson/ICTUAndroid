package dev.duongson.hocsqlite;

public class Student {

    private String name, code;
    private double mark;

    public Student(String code, String name, double mark) {
        this.name = name;
        this.code = code;
        this.mark = mark;
    }
    @Override
    public String toString() {
        return code + " - " + name + " - "+mark;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }
}
