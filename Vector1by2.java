/**
 * A simple 1x2 vector class used for matrix transformations in image rotation.
 * Stores (x, y) as doubles so rotational math is exact.
 */
public class Vector1by2 {

    private double x;
    private double y;

    public Vector1by2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    // Integer-safe getters for image indexing
    public int getXInt() { return (int) Math.round(x); }
    public int getYInt() { return (int) Math.round(y); }

    public static double dot(Vector1by2 v1, Vector1by2 v2) {
        return v1.x * v2.x + v1.y * v2.y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
