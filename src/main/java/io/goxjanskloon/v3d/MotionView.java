package io.goxjanskloon.v3d;
import io.goxjanskloon.utils.*;
public class MotionView implements Hittable{
    public final Hittable hittable;
    public final Vector motion;
    public MotionView(Hittable hittable,Vector motion){
        this.hittable=hittable;
        this.motion=motion;
    }
    @Override public HitRecord hit(Ray ray,Interval interval){
        return hittable.hit(new Ray(ray.orig,ray.dir.add(motion.mul(Randoms.nextDouble()))),interval);
    }
    @Override
    public Aabb getAabb(){
        Aabb aabb=hittable.getAabb();
        return aabb.unite(aabb.move(motion));
    }
}