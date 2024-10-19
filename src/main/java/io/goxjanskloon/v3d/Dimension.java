package io.goxjanskloon.v3d;
public enum Dimension{
    X,Y,Z;
    Dimension(){}
    public static Dimension valueOf(int value){
        return switch(value){
            case 0->
                    X;
            case 1->
                    Y;
            case 2->
                    Z;
            default->
                    throw new IllegalArgumentException();
        };
    }
}