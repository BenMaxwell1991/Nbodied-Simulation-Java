package com.maxwell.zBitsAndBobs;

public class ClassWithValues {
    private String name;
    private double number;

    public ClassWithValues(String s, double d) {
        this.name = s;
        this.number = d;
    }

    public String getName() {return name;}
    public double getNumber() {return number;}

    public void setName(String val) {this.name = val;}
    public void setNumber(double val) {this.number = val;}
}
