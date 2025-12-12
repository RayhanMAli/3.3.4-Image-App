
public class unittest {

    
    private static final Vector1by2 TEST_VECTOR = new Vector1by2(10.0, 5.0);

    // Tests the basic dot product: (2, 3) * (4, 5) = 23
    public static void testBasicDotProduct() {
        Vector1by2 v1 = new Vector1by2(2.0, 3.0);
        Vector1by2 v2 = new Vector1by2(4.0, 5.0);
        double result = Vector1by2.dot(v1, v2); 
        
        if (Math.abs(result - 23.0) < 0.0001) {
            System.out.println("TEST 1: DOT PRODUCT PASSED. Result: " + result);
        } else {
            System.out.println("TEST 1: DOT PRODUCT FAILED. Expected: 23.0, Got: " + result);
        }
    }

 
    // Tests the 180 Degree Rotation: (10, 5) -> (-10, -5)
    public static void test180DegreeRotation() {
        Matrix2by2 mat180 = Matrix2by2.get180FlipMatrix();

        Vector1by2 resultV = Matrix2by2.multiply(TEST_VECTOR, mat180);

        if (Math.abs(resultV.getX() - (-10.0)) < 0.0001 && 
            Math.abs(resultV.getY() - (-5.0)) < 0.0001) {
            System.out.println("TEST 2: 180 DEGREE ROTATION PASSED. Result: " + resultV.toString());
        } else {
            System.out.println("TEST 2: 180 DEGREE ROTATION FAILED. Expected: (-10.0, -5.0), Got: " + resultV.toString());
        }
    }

   
    // Tests the 90 Degree CCW Rotation: (10, 5) -> (-5, 10)
    public static void test90DegreeCCWRotation() {
        
        Matrix2by2 matCCW = Matrix2by2.getInv90CWMatrix(); 

        Vector1by2 resultV = Matrix2by2.multiply(TEST_VECTOR, matCCW);

        if (Math.abs(resultV.getX() - (-5.0)) < 0.0001 && 
            Math.abs(resultV.getY() - 10.0) < 0.0001) {
            System.out.println("TEST 3: 90 CCW ROTATION PASSED. ");
        } else {
            System.out.println("TEST 3: 90 CCW ROTATION FAILED. Expected: (-5.0, 10.0), Got: " + resultV.toString());
        }
    }
    
    // Main method to execute all tests
    public static void main(String[] args) {
        System.out.println("--- Running Matrix Math Verification Tests ---");
        testBasicDotProduct();
        test180DegreeRotation();
        test90DegreeCCWRotation();
        System.out.println("--- Tests Complete ---");
    }
}