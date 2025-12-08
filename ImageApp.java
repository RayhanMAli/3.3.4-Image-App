import java.awt.Color;

public class ImageApp {

    public static void main(String[] args) {

        String pictureFile = "lib/beach.jpg";
        Picture origImg = new Picture(pictureFile);
        Pixel[][] origP = origImg.getPixels2D();
        int origWidth = origImg.getWidth();   // 640
        int origHeight = origImg.getHeight(); // 480
        
        System.out.println("Original pixel color: " + origP[0][0].getColor());
        System.out.println("Original: width=" + origWidth + ", height=" + origHeight);
        origImg.explore();

        // --- Image #1: Recolor ---
        Picture recoloredImg = new Picture(pictureFile);
        Pixel[][] recolorP = recoloredImg.getPixels2D();
        for (int r = 0; r < origHeight; r++) {
            for (int c = 0; c < origWidth; c++) {
                Color col = recolorP[r][c].getColor();
                int newRed = Math.min(255, col.getRed() + 50);
                recolorP[r][c].setColor(new Color(newRed, col.getGreen(), col.getBlue()));
            }
        }
        System.out.println("\nImage #1: Recolored (Red +50)");
        recoloredImg.explore();

        // --- Image #2: Negative ---
        Picture negImg = new Picture(pictureFile);
        Pixel[][] negP = negImg.getPixels2D();
        for (int r = 0; r < origHeight; r++) {
            for (int c = 0; c < origWidth; c++) {
                negP[r][c].setRed(255 - negP[r][c].getRed());
                negP[r][c].setGreen(255 - negP[r][c].getGreen());
                negP[r][c].setBlue(255 - negP[r][c].getBlue());
            }
        }
        System.out.println("\nImage #2: Negative");
        negImg.explore();

        // --- Image #3: Grayscale ---
        Picture grayscaleImg = new Picture(pictureFile);
        Pixel[][] grayP = grayscaleImg.getPixels2D();
        for (int r = 0; r < origHeight; r++) {
            for (int c = 0; c < origWidth; c++) {
                int avg = (grayP[r][c].getRed() + grayP[r][c].getGreen() + grayP[r][c].getBlue()) / 3;
                grayP[r][c].setColor(new Color(avg, avg, avg));
            }
        }
        System.out.println("\nImage #3: Grayscale");
        grayscaleImg.explore();

        // --- Image #4: Rotate 180 using Matrix ---
        Picture rot180Img = new Picture(origHeight, origWidth);
        Pixel[][] rot180P = rot180Img.getPixels2D();
        Matrix2by2 mat180 = Matrix2by2.get180FlipMatrix();
        
        for (int r = 0; r < origHeight; r++) {
            for (int c = 0; c < origWidth; c++) {
                // Create vector for current position
                Vector1by2 v = new Vector1by2(c, r);
                // Apply 180 rotation matrix
                Vector1by2 rotated = Matrix2by2.multiply(v, mat180);
                // Translate to positive coordinates
                int newR = origHeight - 1 + rotated.getYInt();
                int newC = origWidth - 1 + rotated.getXInt();
                rot180P[newR][newC].setColor(origP[r][c].getColor());
            }
        }
        System.out.println("\nImage #4: Rotated 180° (using matrix)");
        rot180Img.explore();

        // --- Image #5: Rotate 90 CCW using Matrix ---
        Picture rot90CCW = new Picture(origWidth, origHeight);
        Pixel[][] ccwP = rot90CCW.getPixels2D();
        Matrix2by2 matCCW = Matrix2by2.getInv90CWMatrix(); // This rotates CCW
        
        for (int r = 0; r < origHeight; r++) {
            for (int c = 0; c < origWidth; c++) {
                // Create vector for current position
                Vector1by2 v = new Vector1by2(c, r);
                // Apply 90 CCW matrix
                Vector1by2 rotated = Matrix2by2.multiply(v, matCCW);
                // Translate: for CCW, x becomes positive, y stays
                int newR = origWidth - 1 + rotated.getXInt();
                int newC = rotated.getYInt();
                ccwP[newR][newC].setColor(origP[r][c].getColor());
            }
        }
        System.out.println("\nImage #5: Rotated 90° Counter-Clockwise (using matrix)");
        rot90CCW.explore();

        // --- Image #6: Rotate 90 CW using Matrix ---
        Picture rot90CW = new Picture(origWidth, origHeight);
        Pixel[][] cwP = rot90CW.getPixels2D();
        Matrix2by2 matCW = Matrix2by2.getInv90CCWMatrix(); // This rotates CW
        
        for (int r = 0; r < origHeight; r++) {
            for (int c = 0; c < origWidth; c++) {
                // Create vector for current position
                Vector1by2 v = new Vector1by2(c, r);
                // Apply 90 CW matrix
                Vector1by2 rotated = Matrix2by2.multiply(v, matCW);
                // Translate: for CW, x stays, y becomes positive
                int newR = rotated.getXInt();
                int newC = origHeight - 1 + rotated.getYInt();
                cwP[newR][newC].setColor(origP[r][c].getColor());
            }
        }
        System.out.println("\nImage #6: Rotated 90° Clockwise (using matrix)");
        rot90CW.explore();

        System.out.println("\n✓ All image transformations complete!");
    }
}