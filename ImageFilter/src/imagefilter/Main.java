package imagefilter;
import median.Median;
import median.Outlier;

/*--------------------------------------------------------------------------------------------------
    4.) src/image/LennaMedian.png
    Reflection 3x3 median filter operation - Image Output Mean = 123.56921376553771

    5.) src/image/LennaOutlier.png
    Reflection 3x3 outlier filter operation with a threshold 50 - Image Output Mean = 123.31023558267347
--------------------------------------------------------------------------------------------------*/
public class Main {

    public static String filename;
    
    public static void main(String[] args) {
        
        ProcessReflectionPadding();
        ProcessMedianFilter4();
        ProcessTreshold50Filter5(); 
        System.out.println("\n");
    }


    /*--------------------------------------------------------------------------------------------
        Perform reflection image padding at the edges
    --------------------------------------------------------------------------------------------*/
    private static void ProcessReflectionPadding() {
        ImageReadWrite image = new ImageReadWrite();
        int[][] gray1 = image.ImageRead("src/image/Lenna.png");
        
        ImageReflectionPadding ref = new ImageReflectionPadding();      
        int[][] gray2 = ref.reflection(gray1, 1);
        int[][] gray3 = ref.makeReflection(gray2, 3);
        ref.writeImage(gray2, "src/image/LennaReflectionPadding.png");
        
        
    }   
    /*--------------------------------------------------------------------------------------------
    Program 4.)
        Perform a 3x3 median filter operation on the green component 
        with reflection image padding at the edges
      --------------------------------------------------------------------------------------------*/
    private static void ProcessMedianFilter4() {
        
        filename = "src/image/LennaMedian.png";        
        Median median = new Median();
        median.process("src/image/LennaReflectionPadding.png", filename);
        
        Statistics stat4 = new Statistics(filename);
        System.out.println("\n4.) " + filename 
                + "\nReflection 3x3 median filter operation - Image Output Mean = " 
                + stat4.getMean());  
        
    }   
    /*--------------------------------------------------------------------------------------------
    Program 5.)
        Perform a 3x3 outlier filter operation on the green component 
        with a threshold value of 50 and with reflection image padding at the edges
     --------------------------------------------------------------------------------------------*/
    
    private static void ProcessTreshold50Filter5() {

        filename = "src/image/LennaOutlier.png";
        Outlier outlier = new Outlier();
        outlier.process("src/image/LennaReflectionPadding.png", filename);
        
        Statistics stat5 = new Statistics(filename);
        System.out.println("\n5.) " + filename 
                + "\nReflection 3x3 outlier filter operation with a threshold 50 - Image Output Mean = " 
                + stat5.getMean());          
        
    }   
    /*--------------------------------------------------------------------------------------------*/
}
