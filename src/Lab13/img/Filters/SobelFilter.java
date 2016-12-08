package Lab13.img.Filters;

import cslib.images.ImageFilter;
import scala.util.Random;

import java.awt.*;
import java.util.Arrays;

public class SobelFilter extends ImageFilter {

    public SobelFilter(String var1, int var2) {
        super(var1, var2);
    }

    @Override
    public Color[][] apply(Color[][] colors, double[] doubles) {

        Color[][] outPixels = new Color[colors.length][colors[0].length];

        for (Color[] row: outPixels)
            Arrays.fill(row, new Color(255,255,255));


        short[][] intensity = computeIntensity(colors);

        short[][] x_sobel = {
                {-1,0,1},
                {-2,0,2},
                {-1,0,1}
        };
        short[][] y_sobel = {
                {-1,-2,-1},
                {0,0,0},
                {1,2,1}
        };


        for(int i = 1 ; i < intensity.length - 1 ; i++){
            for(int j = 1 ; j < intensity[i].length - 1 ; j++){
                int x = Math.abs(convolve(intensity,i,j,x_sobel,1));
                int y = Math.abs(convolve(intensity,i,j,y_sobel,1));

                if ((x + y) > doubles[0]){
                    outPixels[i][j] = new Color(0,0,0);
                } else {
                    outPixels[i][j] = new Color(255,255,255);
                }
            }
        }

        return outPixels;
    }
}
