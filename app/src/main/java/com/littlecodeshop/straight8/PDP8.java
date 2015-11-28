package com.littlecodeshop.straight8;

/**
 * Created by rribier on 26/11/2015.
 */
public class PDP8 {

    public static native void sendChar(char c);
    public static native String getVersion();
    public static native void reset();
    public static native void deposit();
    public static native void examine();
    public static native void loadAddress();
    public static native void step();
    public static native String status();

    static {
        System.out.println("LOADING PDP8");
        System.loadLibrary("native");
    }
}
