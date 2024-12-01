package io.goxjanskloon.graphics;
import io.goxjanskloon.utils.Interval;
public class Color{
    public static final Interval RANGE=new Interval(0,1);
    public static final Color WHITE=new Color(1,1,1),BLACK=new Color(0,0,0),RED=new Color(1,0,0),GREEN=new Color(0,1,0),BLUE=new Color(0,0,1),YELLOW=new Color(1,1,0),CYAN=new Color(0,1,1);
    public final double red,green,blue;
    public Color(double red,double green,double blue){
        this.red=red;
        this.green=green;
        this.blue=blue;
    }
    public Color mix(Color other){
        return new Color(red+other.red,green+other.green,blue+other.blue);
    }
    public Color scale(Color other){
        return new Color(red*other.red,green*other.green,blue*other.blue);
    }
    public Color scale(double scale){
        return new Color(red*scale,green*scale,blue*scale);
    }
    public Color div(double scale){
        return new Color(red/scale,green/scale,blue/scale);
    }
    public Color gamma(){
        return new Color(red<0?0:Math.sqrt(red),green<0?0:Math.sqrt(green),blue<0?0:Math.sqrt(blue));
    }
    public Rgb toRgb(){
        Color c=gamma();
        c=new Color(RANGE.clamp(c.red),RANGE.clamp(c.green),RANGE.clamp(c.blue));
        c=c.scale(Rgb.MAX);
        return new Rgb((int)c.red,(int)c.green,(int)c.blue);
    }
    public boolean isValid(){
        return Double.isFinite(red)&&Double.isFinite(green)&&Double.isFinite(blue);
    }
}