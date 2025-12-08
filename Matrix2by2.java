/**
 * A 2x2 matrix class used for rotation transformations.
 * Stored in the layout:
 *      | a  b |
 *      | c  d |
 */
public class Matrix2by2 {

    private double a, b, c, d;

    public Matrix2by2(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public double getA() { return a; }
    public double getB() { return b; }
    public double getC() { return c; }
    public double getD() { return d; }

    public static Vector1by2 multiply(Vector1by2 v, Matrix2by2 m) {
        Vector1by2 col1 = new Vector1by2(m.a, m.c);
        Vector1by2 col2 = new Vector1by2(m.b, m.d);
        double newX = Vector1by2.dot(v, col1);
        double newY = Vector1by2.dot(v, col2);
        return new Vector1by2(newX, newY);
    }

    public static Matrix2by2 get180FlipMatrix() {
        return new Matrix2by2(-1, 0, 0, -1);
    }

    public static Matrix2by2 getInv90CCWMatrix() { // 90 CW
        return new Matrix2by2(0, 1, -1, 0);
    }

    public static Matrix2by2 getInv90CWMatrix() { // 90 CCW
        return new Matrix2by2(0, -1, 1, 0);
    }
}
