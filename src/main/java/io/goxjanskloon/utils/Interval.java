package io.goxjanskloon.utils;
public class Interval{
    public static final Interval EMPTY=new Interval(Double.POSITIVE_INFINITY,Double.NEGATIVE_INFINITY),UNIVERSE=new Interval(Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY),UNIT_RANGE=new Interval(-0.5,0.5);
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
    public double random(){
        return Randoms.nextDouble(this);
    }
    public Interval move(double x){
        return new Interval(min+x,max+x);
    }
}