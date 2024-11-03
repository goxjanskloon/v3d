package io.goxjanskloon.v3d;
import io.goxjanskloon.graphics.*;
class Material{
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
    public Vector getNormal(Vector normal){
        return roughness==0.0?normal:Vector.randomUnit().mul(roughness).add(normal).unit();
    }
}