import java.awt.Color;

public class ImageApp {

    public static void main(String[] args) {

        String pictureFile = "lib/beach.jpg";
        Picture origImg = new Picture(pictureFile);
        Pixel[][] origP = origImg.getPixels2D();
        int origWidth = origImg.getWidth();   // 640
        int origHeight = origImg.getHeight(); // 480
        
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
        grayscaleImg.explore();

        // --- Image #4: Rotate 180 using Matrix Multiplication ---
        Picture rot180Img = new Picture(origHeight, origWidth);
        Pixel[][] rot180P = rot180Img.getPixels2D();
        Matrix2by2 mat180 = Matrix2by2.get180FlipMatrix();
        
        // Center points for rotation
        double centerX = (origWidth - 1) / 2.0;
        double centerY = (origHeight - 1) / 2.0;
        
        
        for (int destR = 0; destR < origHeight; destR++) {
            for (int destC = 0; destC < origWidth; destC++) {
                // Convert 
                
                Vector1by2 destCentered = new Vector1by2(destC - centerX, destR - centerY);

                // multiplication

                Vector1by2 srcCentered = Matrix2by2.multiply(destCentered, mat180);
                // Convert back to array indices
                int srcR = (int)Math.round(srcCentered.getY() + centerY);
                int srcC = (int)Math.round(srcCentered.getX() + centerX);
                
                // Bounds check
                if (srcR >= 0 && srcR < origHeight && srcC >= 0 && srcC < origWidth) {
                    rot180P[destR][destC].setColor(origP[srcR][srcC].getColor());
                }
            }
        }
        rot180Img.explore();

        // --- Image #5: Rotate 90 CCW using Matrix Multiplication ---
        Picture rot90CCW = new Picture(origWidth, origHeight);
        Pixel[][] ccwP = rot90CCW.getPixels2D();
        // Matrix2by2 matCCW = Matrix2by2.getInv90CWMatrix(); // Forward: 90 CCW
        Matrix2by2 matCCWInv = Matrix2by2.getInv90CCWMatrix(); // Inverse: 90 CW
        
        int newWidth = origHeight;
        int newHeight = origWidth;
        
        for (int destR = 0; destR < newHeight; destR++) {
            for (int destC = 0; destC < newWidth; destC++) {
                // Convert to centered coords for new image
                Vector1by2 destCentered = new Vector1by2(destC - (newWidth - 1) / 2.0, destR - (newHeight - 1) / 2.0);
                
                // Apply inverse rotation (90 CW) to find source
                Vector1by2 srcCentered = Matrix2by2.multiply(destCentered, matCCWInv);
                // Convert to source array indices
                int srcR = (int)Math.round(srcCentered.getY() + (origHeight - 1) / 2.0);
                int srcC = (int)Math.round(srcCentered.getX() + (origWidth - 1) / 2.0);
                
                if (srcR >= 0 && srcR < origHeight && srcC >= 0 && srcC < origWidth) {
                    ccwP[destR][destC].setColor(origP[srcR][srcC].getColor());
                }
            }
        }
        rot90CCW.explore();

        // --- Image #6: Rotate 90 CW using Matrix Multiplication ---
        Picture rot90CW = new Picture(origWidth, origHeight);
        Pixel[][] cwP = rot90CW.getPixels2D();
        Matrix2by2 matCWInv = Matrix2by2.getInv90CWMatrix(); // Inverse: 90 CCW
        
        for (int destR = 0; destR < newHeight; destR++) {
            for (int destC = 0; destC < newWidth; destC++) {
                // Convert to centered coords
                Vector1by2 destCentered = new Vector1by2(destC - (newWidth - 1) / 2.0, destR - (newHeight - 1) / 2.0);
                // Apply inverse rotation (90 CCW) to find source
                Vector1by2 srcCentered = Matrix2by2.multiply(destCentered, matCWInv);
                // Convert to source array indices
                int srcR = (int)Math.round(srcCentered.getY() + (origHeight - 1) / 2.0);
                int srcC = (int)Math.round(srcCentered.getX() + (origWidth - 1) / 2.0);
                
                if (srcR >= 0 && srcR < origHeight && srcC >= 0 && srcC < origWidth) {
                    cwP[destR][destC].setColor(origP[srcR][srcC].getColor());
                }
            }
            
        }
        rot90CW.explore();
        

    }
    
}