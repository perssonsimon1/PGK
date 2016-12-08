package Lab13.img.Filters;

import cslib.images.ImageFilter;

import java.awt.*;

public class RedFilter extends ImageFilter {

    public RedFilter(String var1, int var2) {
        super(var1, var2);
    }

    @Override
    public Color[][] apply(Color[][] colors, double[] doubles) {

        Color[][] out = colors;

        for(int i = 0 ; i < colors.length ; i++){
            for(int j = 0 ; j < colors[i].length ; j++){
                out[i][j] = new Color(out[i][j].getRed(),0,0);
            }
        }

        return out;
    }
}
