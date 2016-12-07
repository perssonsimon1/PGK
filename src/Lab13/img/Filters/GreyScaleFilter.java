package Lab13.img.Filters;

import cslib.images.ImageFilter;

import java.awt.*;

public class GreyScaleFilter extends ImageFilter {

    public GreyScaleFilter(String var1, int var2) {
        super(var1, var2);
    }

    @Override
    public Color[][] apply(Color[][] colors, double[] doubles) {

        Color[][] out = colors;

        short[][] intensity = computeIntensity(out);

        for(int i = 0 ; i < colors.length ; i++){
            for(int j = 0 ; j < colors[i].length ; j++){
                int itsy = intensity[i][j];
                out[i][j] = new Color(itsy,itsy,itsy);
            }
        }

        return out;
    }
}
