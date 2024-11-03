package io.goxjanskloon.v3d;
import io.goxjanskloon.utils.*;
public class Sphere implements Hittable{
    public final Vector center;
    public final double radius;
    public final Material material;
    public Sphere(Vector center,double radius,Material material){
        this.center=center;
        this.radius=radius;
        this.material=material;
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
            final Vector normal=point.sub(center).unit();
            final double u=(Math.atan2(-normal.x,normal.z)+Math.PI)/(Math.PI*2.0);
            final double v=Math.acos(-normal.y)/Math.PI;
            return new HitRecord(point,material.getNormal(normal),material.getColor(u,v),t,material.brightness);
        }else
            return null;
    }
    @Override public Aabb getAabb(){
        return new Aabb(new Interval(center.x-radius,center.x+radius),new Interval(center.y-radius,center.y+radius),new Interval(center.z-radius,center.z+radius));
    }
}