package io.goxjanskloon.v3d;
import io.goxjanskloon.graphics.*;
import io.goxjanskloon.utils.*;
public class NormalView implements Hittable{
    public final Hittable hittable;
    public static final Material material=new Material(0.0,1.0,null);
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
        return new HitRecord(record.point,record.normal,new Color((record.normal.x+1.0)/2.0,(record.normal.y+1.0)/2.0,(record.normal.z+1.0)/2.0),record.dist,material);
    }
}