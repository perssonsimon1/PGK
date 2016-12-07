package Lab13.img.Filters;

import cslib.images.ImageFilter;

import java.awt.*;

/**
 * Created by simonpersson on 2016-12-06.
 */
public class InvertFilter extends ImageFilter {

    public InvertFilter(String var1, int var2) {
        super(var1, var2);
    }

    @Override
    public Color[][] apply(Color[][] colors, double[] doubles) {

        Color[][] out = colors;

        for(int i = 0 ; i < colors.length ; i++){
            for(int j = 0 ; j < colors[i].length ; j++){
                out[i][j] = new Color(255 - out[i][j].getRed(),255 - out[i][j].getGreen(),255 - out[i][j].getBlue());
            }
        }

        return out;
    }
}
