package io.goxjanskloon.v3d;
public interface Hittable{
    final class HitRecord{
        public final Vector point,normal;
        public final Color color;
        public final double dist;
        public HitRecord(Vector point,Vector normal,Color color,double dist,Material material){
            this.point=point;
            this.normal=material.getNormal(normal);
            this.color=color;
            this.dist=dist;
        }
    }
    HitRecord hit(Ray ray,Interval interval);
    Aabb getAabb();
}