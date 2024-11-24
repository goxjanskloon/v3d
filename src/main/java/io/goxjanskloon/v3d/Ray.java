package io.goxjanskloon.v3d;
public class Ray{
    public final Vector orig,dir;
    public Ray(Vector orig, Vector dir){
        this.orig=orig;
        this.dir=dir;
    }
    public Vector at(double t){
        return orig.add(dir.mul(t));
    }
}