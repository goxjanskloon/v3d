package io.goxjanskloon.v3d;
public class Sphere implements Hittable{
    public final Vector center;
    public final double radius,roughness,brightness;
    public final Color color;
    public Sphere(Vector center,double radius,Color color,double roughness,double brightness){
        this.center=center;
        this.radius=radius;
        this.color=color;
        this.roughness=roughness;
        this.brightness=brightness;
    }
    @Override public HitRecord hit(Ray ray,Interval interval){
        final Vector co=ray.orig.sub(center);
        final double b=ray.dir.dot(co),d=b*b-co.normSq()+radius*radius;
        if(d<0)
            return null;
        final double sd=Math.sqrt(d);
        double t=-b-sd;
        if(!interval.contains(t))
            t+=sd*2;
        if(interval.contains(t)){
            final Vector point=ray.at(t);
            return new HitRecord(point,point.sub(center).unit(),color,t,roughness,brightness);
        }else
            return null;
    }
    @Override public Aabb getAabb(){
        return new Aabb(new Interval(center.x-radius,center.x+radius),new Interval(center.y-radius,center.y+radius),new Interval(center.z-radius,center.z+radius));
    }
}