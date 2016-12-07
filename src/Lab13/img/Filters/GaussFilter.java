package Lab13.img.Filters;

import cslib.images.ImageFilter;
import scala.util.Random;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by simonpersson on 2016-12-06.
 */
public class GaussFilter extends ImageFilter {

    public GaussFilter(String var1, int var2) {
        super(var1, var2);
    }

    @Override
    public Color[][] apply(Color[][] colors, double[] doubles) {

        Color[][] outPixels = new Color[colors.length][colors[0].length];

        for (Color[] row: outPixels)
            Arrays.fill(row, new Color(255,255,255));

        short[][] k = {{0,1,0},{1,(short) doubles[0],1},{0,1,0}};
        int kSum = 8;

        short[][] red = new short[colors.length][colors[0].length];
        short[][] green = new short[colors.length][colors[0].length];
        short[][] blue = new short[colors.length][colors[0].length];

        for(int i = 0 ; i < colors.length ; i++){
            for(int j = 0 ; j < colors[i].length ; j++){
               red[i][j] = (short) colors[i][j].getRed();
               green[i][j] = (short) colors[i][j].getGreen();
               blue[i][j] = (short) colors[i][j].getBlue();
            }
        }

        for(int i = 1 ; i < colors.length - 1 ; i++){
            for(int j = 1 ; j < colors[i].length - 1 ; j++){
                int r = Math.abs(convolve(red, i,j,k,kSum) % 256);
                int g = Math.abs(convolve(green, i,j,k,kSum) % 256);
                int b = Math.abs(convolve(blue, i,j,k,kSum) % 256);

                outPixels[i][j] = new Color(r, g, b);
            }
        }

        return outPixels;
    }
}
