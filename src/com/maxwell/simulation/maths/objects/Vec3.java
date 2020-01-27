package com.maxwell.simulation.maths.objects;

/** This class is used to hold 3D ArrayList data for a physical object*/
public class Vec3 implements Cloneable {
    private double xValue;
    private double yValue;
    private double zValue;

    public Vec3() {
        this(0,0,0);
    }

    /** Constructor */
    public Vec3(double x, double y, double z) {
        xValue = x;
        yValue = y;
        zValue = z;
    }

    /** Getters, intentionally without the get prefix for ease of use */
    public double x() { return xValue; }
    public double y() { return yValue; }
    public double z() { return zValue; }

    /** Setters use same functions as getters but with an argument passed in */
    public void x(double value) { xValue = value; }
    public void y(double value) { yValue = value; }
    public void z(double value) { zValue = value; }

    /**
     * Subtract Vec3 v from this ArrayList
     */
    public void subtract(Vec3 v) {
        this.xValue -= v.x();
        this.yValue -= v.y();
        this.zValue -= v.z();
    }

    /**
     * Add Vec v to this ArrayList
     */
    public void add(Vec3 v) {
        this.xValue += v.x();
        this.yValue += v.y();
        this.zValue += v.z();
    }

    /**
     * Multiply this ArrayList by (Scalar) n
     */
    public void multiplyScalar(double n){
        this.xValue = this.xValue * n;
        this.yValue = this.xValue * n;
        this.zValue = this.xValue * n;
    }

    /**
     * Divide this ArrayList by (Scalar) n
     */
    public void divideScalar(double n){
        this.xValue = this.xValue / n;
        this.yValue = this.xValue / n;
        this.zValue = this.xValue / n;
    }

    /**
     * Return (Vec3) A-B
     */
    public static Vec3 subtract(Vec3 vecA, Vec3 vecB) {
        return new Vec3(vecA.x() - vecB.x(),
                        vecA.y() - vecB.y(),
                        vecA.z() - vecB.z());
    }

    /**
     * Return (Vec3) A+B
     */
    public static Vec3 add(Vec3 vecA, Vec3 vecB) {
        return new Vec3(vecA.x() + vecB.x(),
                        vecA.y() + vecB.y(),
                        vecA.z() + vecB.z());
    }

    /**
     * Return the ArrayList product of Vec3 A and B.
     */
    public static Vec3 multiplyScalar(Vec3 A, double B){
        return new Vec3(A.x() * B,
                        A.y() * B,
                        A.z() * B);
    }

    /**
     * Return the ArrayList result of Vec3 A / Scalar B.
     */
    public static Vec3 divideScalar(Vec3 A, double B){
        return new Vec3(A.x() / B,
                        A.y() / B,
                        A.z() / B);
    }

    /**
     * Return the square of the distance between VecA and VecB
     */
    public static double distanceSquared(Vec3 A, Vec3 B) {
        return (Math.pow((A.x() - B.x()), 2) +
                Math.pow((A.y() - B.y()), 2) +
                Math.pow((A.z() - B.z()), 2));
    }

    /**
     * Returns the modulus squared of ArrayList V
     */
    public static double modSqr(Vec3 V) {
        return (Math.pow(V.x(), 2) +
                Math.pow(V.y(), 2) +
                Math.pow(V.z(), 2));
    }

    /**
     * Returns the modulus of ArrayList V
     */
    public static double mod(Vec3 V) {
        return Math.sqrt(modSqr(V));
    }

    /**
     * Return the normalised ArrayList V
     */
    public static Vec3 normalise(Vec3 V){
        return Vec3.divideScalar(V, Vec3.mod(V));
    }

    /**
     * Clones this object
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
