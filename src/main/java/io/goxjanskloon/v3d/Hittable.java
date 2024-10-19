package io.goxjanskloon.v3d;
import java.util.*;
public interface Hittable{
    final class HitRecord{
        public final Vector point,normal;
        public final Color color;
        public final double dist,roughness,brightness;
        public HitRecord(Vector point,Vector normal,Color color,double dist,double roughness,double brightness){
            this.point=point;
            this.normal=normal;
            this.color=color;
            this.dist=dist;
            this.roughness=roughness;
            this.brightness=brightness;
        }
    }
    HitRecord hit(Ray ray,Interval interval);
    Aabb getAabb();
}