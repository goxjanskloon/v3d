package io.goxjanskloon.v3d;
import io.goxjanskloon.graphics.*;
public class Material{
    public final double roughness,brightness;
    public final Texture texture;
    public Material(double roughness,double brightness,Texture texture){
        this.roughness=roughness;
        this.brightness=brightness;
        this.texture=texture;
    }
    public Color getColor(double u,double v){
        return texture.getColor(u,v);
    }
}