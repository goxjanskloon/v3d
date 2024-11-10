package io.goxjanskloon.v3d;
import java.util.*;
import io.goxjanskloon.utils.*;
public class Aabb implements Iterable<Interval>{
    public static final Aabb empty=new Aabb(Interval.EMPTY,Interval.EMPTY,Interval.EMPTY);
    public final Interval x,y,z;
    public Aabb(Interval x,Interval y,Interval z){
        this.x=x;
        this.y=y;
        this.z=z;
    }
    public Interval get(Dimension d){
        return switch(d){
            case X->
                    x;
            case Y->
                    y;
            case Z->
                    z;
        };
    }
    public Dimension getLongestAxis(){
        final double xl=x.length(),yl=y.length(),zl=z.length();
        if(xl>yl&&xl>zl)
            return Dimension.X;
        if(yl>xl&&yl>zl)
            return Dimension.Y;
        return Dimension.Z;
    }
    public boolean hit(Ray ray,Interval interval){
        for(int i=0;i<=2;++i){
            final Dimension d=Dimension.valueOf(i);
            if((interval=interval.intersect(new Interval((get(d).min-ray.orig.get(d))/ray.dir.get(d),(get(d).max-ray.orig.get(d))/ray.dir.get(d)))).isEmpty())
                return false;
        }
        return true;
    }
    public Aabb unite(Aabb other){
        return new Aabb(x.unite(other.x),y.unite(other.y),z.unite(other.z));
    }
    public int compareTo(Aabb other,Dimension d){
        return Double.compare(get(d).min,other.get(d).min);
    }
    @Override public Iterator<Interval> iterator(){
        return new Iterator<Interval>() {
            private Dimension d=Dimension.X;
            @Override public boolean hasNext(){
                return d==Dimension.Z;
            }
            @Override public Interval next(){
                if(!hasNext())
                    throw new IllegalStateException("No next");
                Interval result=get(d);
                d=switch(d){
                    case X->
                            Dimension.Y;
                    case Y->
                            Dimension.Z;
                    default->
                            throw new IllegalStateException("No next");
                };
                return result;
            }
        };
    }
    public Aabb move(Vector motion){
        return new Aabb(x.move(motion.x),y.move(motion.y),z.move(motion.z));
    }
}