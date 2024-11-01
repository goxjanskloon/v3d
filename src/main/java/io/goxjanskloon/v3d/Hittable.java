package io.goxjanskloon.v3d;
public interface Hittable{
    final class HitRecord{
        public final Vector point,normal;
        public final Color color;
        public final double dist,brightness;
        public HitRecord(Vector point,Vector normal,Color color,double dist,double brightness){
            this.point=point;
            this.normal=normal;
            this.color=color;
            this.dist=dist;
            this.brightness=brightness;
        }
    }
    HitRecord hit(Ray ray,Interval interval);
    Aabb getAabb();
}