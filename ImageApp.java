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

        // --- Image #4: Rotate 180 ---
        Picture rot180Img = new Picture(origHeight, origWidth);
        Pixel[][] rot180P = rot180Img.getPixels2D();
        
        for (int r = 0; r < origHeight; r++) {
            for (int c = 0; c < origWidth; c++) {
                int newR = origHeight - 1 - r;
                int newC = origWidth - 1 - c;
                rot180P[newR][newC].setColor(origP[r][c].getColor());
            }
        }
        System.out.println("\nImage #4: Rotated 180°");
        rot180Img.explore();

        // --- Image #5: Rotate 90 CCW (Counter-Clockwise) ---
        Picture rot90CCW = new Picture(origWidth, origHeight);
        Pixel[][] ccwP = rot90CCW.getPixels2D();
        
        for (int r = 0; r < origHeight; r++) {
            for (int c = 0; c < origWidth; c++) {
                int finalR = origWidth - 1 - c;
                int finalC = r;
                ccwP[finalR][finalC].setColor(origP[r][c].getColor());
            }
        }
        System.out.println("\nImage #5: Rotated 90° Counter-Clockwise");
        rot90CCW.explore();

        // --- Image #6: Rotate 90 CW (Clockwise) ---
        Picture rot90CW = new Picture(origWidth, origHeight);
        Pixel[][] cwP = rot90CW.getPixels2D();

        for (int r = 0; r < origHeight; r++) {
            for (int c = 0; c < origWidth; c++) {
                int finalR = c;
                int finalC = origHeight - 1 - r;
                cwP[finalR][finalC].setColor(origP[r][c].getColor());
            }
        }
        System.out.println("\nImage #6: Rotated 90° Clockwise");
        rot90CW.explore();

        System.out.println("\n✓ All image transformations complete!");


        
    // Final Image: Add a small image to a larger one
    /* 1. create new image object using a dif image from lib 2
       2.  */

    /* to be implemented */
    



    // for testing  2D algorithms
    int[][] test1 = { { 1, 2, 3, 4 },
        { 5, 6, 7, 8 },
        { 9, 10, 11, 12 },
        { 13, 14, 15, 16 } };
    int[][] test2 = new int[4][4];
    }
}