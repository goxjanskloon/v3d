package io.goxjanskloon.v3d;
public class Interval{
    public static final Interval empty=new Interval(Double.POSITIVE_INFINITY,Double.NEGATIVE_INFINITY),universe=new Interval(Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY);
    public final double min,max;
    public Interval(double min,double max){
        this.min=min;
        this.max=max;
    }
    public double length(){
        return max-min;
    }
    public boolean isEmpty(){
        return max<=min;
    }
    public boolean contains(double x){
        return x>=min&&x<=max;
    }
    public double clamp(double x){
        return x<min?min:Math.min(x,max);
    }
    public Interval intersect(Interval other){
        return new Interval(Math.max(min,other.min),Math.min(max,other.max));
    }
    public Interval unite(Interval other){
        return new Interval(Math.min(min,other.min),Math.max(max,other.max));
    }
}