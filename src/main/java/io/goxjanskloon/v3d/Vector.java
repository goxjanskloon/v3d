package io.goxjanskloon.v3d;
import java.util.concurrent.*;
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
        return mul(cos).add(axis.mul(1-cos).mul(dot(axis))).add(axis.cross(this).mul(Math.sin(angle)));
    }
    public static Vector random(){
        return new Vector(ThreadLocalRandom.current().nextDouble(-1,1),ThreadLocalRandom.current().nextDouble(-1,1),ThreadLocalRandom.current().nextDouble(-1,1));
    }
    public static Vector randomUnit(){
        while(true){
            Vector v=random();
            final double l=v.normSq();
            if(1e-144<l&&l<=1)
                return v.div(Math.sqrt(l));
        }
    }
    public static Vector randomOnHemisphere(Vector normal){
        Vector v=randomUnit();
        if(normal.dot(v)>0)
            return v;
        return v.neg();
    }
}