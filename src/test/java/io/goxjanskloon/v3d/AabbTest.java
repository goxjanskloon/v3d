package io.goxjanskloon.v3d;
import org.junit.jupiter.api.*;
import io.goxjanskloon.utils.*;
import static org.junit.jupiter.api.Assertions.*;
class AabbTest{
    @Test void get(){
        Aabb aabb=new Aabb(new Interval(0,1),new Interval(2,3),new Interval(4,5));
        assertEquals(0,aabb.get(Dimension.X).min);
        assertEquals(2,aabb.get(Dimension.Y).min);
        assertEquals(4,aabb.get(Dimension.Z).min);
    }
    @Test void getLongestAxis(){
        Aabb a=new Aabb(new Interval(0,1),new Interval(2,4),new Interval(5,8)),b=new Aabb(new Interval(0,1),new Interval(2,5),new Interval(6,8)),c=new Aabb(new Interval(0,3),new Interval(4,5),new Interval(6,8));
        assertEquals(Dimension.Z,a.getLongestAxis());
        assertEquals(Dimension.Y,b.getLongestAxis());
        assertEquals(Dimension.X,c.getLongestAxis());
    }
    @Test void hit(){
        Aabb aabb=new Aabb(new Interval(1,2),new Interval(1,2),new Interval(1,2));
        final int MAX=100;
        int hits=0;
        for(int i=0;i<MAX;++i)
            if(aabb.hit(new Ray(new Vector(0,0,0),Vector.randomUnit()),new Interval(0,Double.POSITIVE_INFINITY)))
                ++hits;
        assertTrue(0<hits&&hits<MAX);
    }
    @Test void unite(){
        Aabb a=new Aabb(new Interval(0,1),new Interval(2,4),new Interval(5,8)),b=new Aabb(new Interval(0,1),new Interval(2,5),new Interval(6,8)),c=new Aabb(new Interval(0,3),new Interval(4,5),new Interval(6,8));
        Aabb d=a.unite(b).unite(c);
        assertEquals(0,d.x.min);
        assertEquals(3,d.x.max);
        assertEquals(2,d.y.min);
        assertEquals(5,d.y.max);
        assertEquals(5,d.z.min);
        assertEquals(8,d.z.max);
    }
    @Test void compareTo(){
        Aabb a=new Aabb(new Interval(0,1),new Interval(2,4),new Interval(5,8)),b=new Aabb(new Interval(0,1),new Interval(2,5),new Interval(6,8)),c=new Aabb(new Interval(0,3),new Interval(4,5),new Interval(6,8));
        assertEquals(0,a.compareTo(b,Dimension.X));
        assertEquals(0,a.compareTo(b,Dimension.Y));
        assertTrue(a.compareTo(b,Dimension.Z)<0);
        assertTrue(a.compareTo(c,Dimension.Y)<0);
    }
}