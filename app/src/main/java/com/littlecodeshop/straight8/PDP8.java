package com.littlecodeshop.straight8;

/**
 * Created by rribier on 26/11/2015.
 */
public class PDP8 {

    public static native void sendChar(char c);
    public static native String getVersion();

    static {
        System.out.println("LOADING PDP8");
        System.loadLibrary("native");
    }
}
