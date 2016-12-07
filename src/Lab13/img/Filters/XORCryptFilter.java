package Lab13.img.Filters;

import cslib.images.ImageFilter;
import scala.util.Random;

import java.awt.*;

/**
 * Created by simonpersson on 2016-12-06.
 */
public class XORCryptFilter extends ImageFilter {

    public XORCryptFilter(String var1, int var2) {
        super(var1, var2);
    }

    @Override
    public Color[][] apply(Color[][] colors, double[] doubles) {

        Color[][] out = colors;
        int seed = (int) Math.round(doubles[0]);

        Random rnd = new Random(seed);
        final int rndNbr = Math.abs(rnd.nextInt());

        for(int i = 0 ; i < colors.length ; i++){
            for(int j = 0 ; j < colors[i].length ; j++){
                int red = ((out[i][j].getRed() ^ rndNbr) % 256);
                int green = ((out[i][j].getGreen() ^ rndNbr) % 256);
                int blue = ((out[i][j].getBlue() ^ rndNbr) % 256);
                out[i][j] = new Color(red, green, blue);
            }
        }

        return out;
    }
}
