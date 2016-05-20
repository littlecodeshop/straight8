package com.littlecodeshop.straight8;

/**
 * Created by rribier on 26/11/2015.
 */
public class PDP8 {

    public static native void start();

     public static void teletypeOutput(char c){

         System.out.println("GOT A TTY OUT "+c);
    }

     public static char teletypeInput(){
        return ((char)65);
    }


    static {
        System.out.println("LOADING PDP8");
        System.loadLibrary("native");
        System.out.println("LOADED PDP8");
    }
}
