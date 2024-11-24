package io.goxjanskloon.v3d;
import io.goxjanskloon.graphics.*;
import io.goxjanskloon.utils.*;
public interface Hittable{
    final class HitRecord{
        public final Vector point,normal;
        public final Color color;
        public final double dist;
        public final Material material;
        public HitRecord(Vector point,Vector normal,Color color,double dist,Material material){
            this.point=point;
            this.normal=normal;
            this.color=color;
            this.dist=dist;
            this.material=material;
        }
    }
    HitRecord hit(Ray ray,Interval interval);
    Aabb getAabb();
}