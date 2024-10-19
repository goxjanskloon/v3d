package io.goxjanskloon.v3d;
public class Vector{
    public final double x,y,z;
    public Vector(double x, double y, double z){
        this.x=x;
        this.y=y;
        this.z=z;
    }
    public double get(Dimension d){
        return switch(d){
            case X->
                    x;
            case Y->
                    y;
            case Z->
                    z;
        };
    }
    public double dot(Vector v){
        return x*v.x+y*v.y+z*v.z;
    }
    public Vector cross(Vector v){
        return new Vector(y*v.z-z*v.y,z*v.x-x*v.z,x*v.y-y*v.x);
    }
    public Vector add(Vector v){
        return new Vector(x+v.x,y+v.y,z+v.z);
    }
    public Vector sub(Vector v){
        return new Vector(x-v.x,y-v.y,z-v.z);
    }
    public Vector mul(Vector v){
        return new Vector(x*v.x,y*v.y,z*v.z);
    }
    public Vector mul(double v){
        return new Vector(x*v,y*v,z*v);
    }
    public Vector div(Vector v){
        return new Vector(x/v.x,y/v.y,z/v.z);
    }
    public Vector div(double v){
        return new Vector(x/v,y/v,z/v);
    }
    public Vector neg(){
        return new Vector(-x,-y,-z);
    }
    public double normSq(){
        return dot(this);
    }
    public double norm(){
        return Math.sqrt(normSq());
    }
    public Vector unit(){
        return div(norm());
    }
    public Vector rotate(Ray axis,double angle){
        return this.sub(axis.orig).rotate(axis.dir,angle).add(axis.orig);
    }
    public Vector rotate(Vector axis,double angle){
        final double cos=Math.cos(angle);
        return mul(cos).add(axis.mul(1.0-cos).mul(dot(axis))).add(cross(axis).mul(Math.sin(angle)));
    }
    public static Vector randomUnit(){
        return new Vector(Math.random()-0.5,Math.random()-0.5,Math.random()-0.5).unit();
    }
}