package io.goxjanskloon.v3d;
import java.util.*;
import io.goxjanskloon.utils.*;
public class BvhTree implements Hittable{
    public final Hittable left,right;
    public final Aabb aabb;
    public BvhTree(List<Hittable> objects){
        if(objects.isEmpty()){
            left=right=null;
            aabb=Aabb.empty;
        }
        else if(objects.size()==1){
            aabb=(left=objects.getFirst()).getAabb();
            right=null;
        }
        else if(objects.size()==2){
            aabb=(left=objects.getFirst()).getAabb().unite((right=objects.getLast()).getAabb());
        }else{
            Aabb tb=Aabb.empty;
            for(Hittable obj: objects)
                tb=tb.unite(obj.getAabb());
            final Dimension d=(aabb=tb).getLongestAxis();
            objects.sort((a,b)->a.getAabb().compareTo(b.getAabb(),d));
            final int mid=objects.size()>>1;
            left=new BvhTree(objects.subList(0,mid));
            right=new BvhTree(objects.subList(mid,objects.size()));
        }
    }
    @Override public Aabb getAabb(){
        return aabb;
    }
    @Override public HitRecord hit(Ray ray,Interval interval){
        HitRecord leftHit=left==null?null:left.hit(ray,interval),rightHit=right==null?null:right.hit(ray,interval);
        if(leftHit==null) return rightHit;
        if(rightHit==null) return leftHit;
        return leftHit.dist<rightHit.dist?leftHit:rightHit;
    }
}