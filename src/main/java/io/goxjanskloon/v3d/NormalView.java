package io.goxjanskloon.v3d;
import io.goxjanskloon.graphics.*;
import io.goxjanskloon.utils.*;
public class NormalView implements Hittable{
    public final Hittable hittable;
    public NormalView(Hittable hittable){
        this.hittable=hittable;
    }
    public Aabb getAabb(){
        return hittable.getAabb();
    }
    public HitRecord hit(Ray ray,Interval interval){
        HitRecord record=hittable.hit(ray,interval);
        if(record==null)
            return null;
        return new HitRecord(record.point,record.normal,new Color((record.normal.x+1)/2,(record.normal.y+1)/2,(record.normal.z+1)/2),1,record.dist,null);
    }
}