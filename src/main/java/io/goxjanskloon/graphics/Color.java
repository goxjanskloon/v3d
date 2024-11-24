package io.goxjanskloon.graphics;
import io.goxjanskloon.utils.Interval;
public class Color{
    public static final Interval RANGE=new Interval(0.0,1.0);
    public static final Color WHITE=new Color(1.0,1.0,1.0),BLACK=new Color(0.0,0.0,0.0),RED=new Color(1.0,0.0,0.0),GREEN=new Color(0.0,1.0,0.0),BLUE=new Color(0.0,0.0,1.0),YELLOW=new Color(1.0,1.0,0.0),CYAN=new Color(0.0,1.0,1.0);
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
    public Rgb toRgb(){
        Color c=new Color(RANGE.clamp(red),RANGE.clamp(green),RANGE.clamp(blue));
        c=c.scale(Rgb.MAX);
        return new Rgb((int)c.red,(int)c.green,(int)c.blue);
    }
    public boolean isValid(){
        return Double.isFinite(red)&&Double.isFinite(green)&&Double.isFinite(blue);
    }
}