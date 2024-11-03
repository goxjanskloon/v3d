package io.goxjanskloon.utils;
import java.util.concurrent.*;
public class Randoms{
    public static double nextDouble(){
        return ThreadLocalRandom.current().nextDouble(0.0,1.0);
    }
    public static double nextDouble(double min,double max){
        return min+nextDouble()*(max-min);
    }
    public static double nextDouble(Interval interval){
        return nextDouble(interval.min,interval.max);
    }
}