package io.goxjanskloon.v3d;
import org.junit.jupiter.api.*;
import io.goxjanskloon.utils.*;
import static org.junit.jupiter.api.Assertions.*;
class AabbTest{
    @Test void get(){
        Aabb aabb=new Aabb(new Interval(0.0,1.0),new Interval(2.0,3.0),new Interval(4.0,5.0));
        assertEquals(aabb.get(Dimension.X).min,0.0);
        assertEquals(aabb.get(Dimension.Y).min,2.0);
        assertEquals(aabb.get(Dimension.Z).min,4.0);
    }
    @Test void getLongestAxis(){
        Aabb a=new Aabb(new Interval(0.0,1.0),new Interval(2.0,4.0),new Interval(5.0,8.0)),b=new Aabb(new Interval(0.0,1.0),new Interval(2.0,5.0),new Interval(6.0,8.0)),c=new Aabb(new Interval(0.0,3.0),new Interval(4.0,5.0),new Interval(6.0,8.0));
        assertEquals(a.getLongestAxis(),Dimension.Z);
        assertEquals(b.getLongestAxis(),Dimension.Y);
        assertEquals(c.getLongestAxis(),Dimension.X);
    }
    @Test void hit(){
        Aabb aabb=new Aabb(new Interval(1.0,2.0),new Interval(1.0,2.0),new Interval(1.0,2.0));
        final int MAX=100;
        int hits=0;
        for(int i=0;i<MAX;++i)
            if(aabb.hit(new Ray(new Vector(0.0,0.0,0.0),Vector.randomUnit()),new Interval(0.0,Double.POSITIVE_INFINITY)))
                ++hits;
        assertTrue(0<hits&&hits<MAX);
    }
    @Test void unite(){
        Aabb a=new Aabb(new Interval(0.0,1.0),new Interval(2.0,4.0),new Interval(5.0,8.0)),b=new Aabb(new Interval(0.0,1.0),new Interval(2.0,5.0),new Interval(6.0,8.0)),c=new Aabb(new Interval(0.0,3.0),new Interval(4.0,5.0),new Interval(6.0,8.0));
        Aabb d=a.unite(b).unite(c);
        assertEquals(d.x.min,0.0);
        assertEquals(d.x.max,3.0);
        assertEquals(d.y.min,2.0);
        assertEquals(d.y.max,5.0);
        assertEquals(d.z.min,5.0);
        assertEquals(d.z.max,8.0);
    }
    @Test void compareTo(){
        Aabb a=new Aabb(new Interval(0.0,1.0),new Interval(2.0,4.0),new Interval(5.0,8.0)),b=new Aabb(new Interval(0.0,1.0),new Interval(2.0,5.0),new Interval(6.0,8.0)),c=new Aabb(new Interval(0.0,3.0),new Interval(4.0,5.0),new Interval(6.0,8.0));
        assertEquals(a.compareTo(b,Dimension.X),0);
        assertEquals(a.compareTo(b,Dimension.Y),0);
        assertTrue(a.compareTo(b,Dimension.Z)<0);
        assertTrue(a.compareTo(c,Dimension.Y)<0);
    }
}