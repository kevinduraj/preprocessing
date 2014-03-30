package percentile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {

    private static final String sInput = "src/image/Lenna.png";
    private static final String sOutput = "src/image/Percentile.png";
    
    /*--------------------------------------------------------------------------------------------
                        Stretch 10th and 90th percentile
    ----------------------------------------------------------------------------------------------*/    
    public static void main(String[] args) throws IOException {

        Percentile percentile = new Percentile();
        int [] mapHist = percentile.readHistogram(sInput);      
        int[] cutoff = percentile.setCutoff(mapHist, 10.0); 
       
        int grn[][] = ImageRead(sInput);
        
        percentile.WriteStretchedImage(grn, percentile.first, percentile.last, new int[0], "src/image/ImgCutOff.png");
        
        int[] stretchedHist = percentile.stretchMap(percentile.first, percentile.last);
        percentile.WriteStretchedImage(grn, percentile.first, percentile.last, stretchedHist
                                       , sOutput);        
        
        /*-------------------- Statistics ------------------------*/
        Statistics stat1 = new Statistics(sOutput);
        System.out.format("Stretch 10th and 90th percentile\n"
                       + "Image Output Mean = %.3f\n\n", 
                       + stat1.getMean() );        
    }
    /*--------------------------------------------------------------------------------------------*/

    public static int[][] ImageRead(String filename) throws IOException {

        File infile = new File(filename);
        BufferedImage bi = ImageIO.read(infile);
        
        int red[][] = new int[bi.getHeight()][bi.getWidth()];
        int grn[][] = new int[bi.getHeight()][bi.getWidth()];
        int blu[][] = new int[bi.getHeight()][bi.getWidth()];

        for (int i = 0; i < red.length; ++i) {
            for (int j = 0; j < red[i].length; ++j) {
                red[i][j] = bi.getRGB(j, i) >> 16 & 0xFF;
                grn[i][j] = bi.getRGB(j, i) >> 8 & 0xFF;
                blu[i][j] = bi.getRGB(j, i) & 0xFF;
            }
        }
        return grn;
    }
    /*--------------------------------------------------------------------------------------------*/

    public static void ImageWrite(int img[][], String filename) throws IOException {

        BufferedImage bi = new BufferedImage(img[0].length, img.length, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < bi.getHeight(); ++i) {
            for (int j = 0; j < bi.getWidth(); ++j) {
                int val = img[i][j];
                int pixel = (val << 16) | (val << 8) | (val);
                bi.setRGB(j, i, pixel);
            }
        }

        File outputfile = new File(filename);
        ImageIO.write(bi, "png", outputfile);
    }
    /*--------------------------------------------------------------------------------------------*/    
}

