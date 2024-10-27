package io.goxjanskloon.v3d;
public class SolidTexture implements Texture{
    public final Color color;
    public SolidTexture(Color color){
        this.color=color;
    }
    public Color getColor(double u,double v){
        return color;
    }
}