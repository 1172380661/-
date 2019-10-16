package com.company.datastructure;

import com.sun.org.apache.bcel.internal.generic.NEW;
import sun.security.jca.GetInstance;

/**
 * @Author: wsw
 * @Date: 2019/10/7 11:50
 */

public class Singleton {
    private static volatile Singleton singleton;
    private Singleton(){

    }
    public static Singleton getInstance(){
        if (singleton == null){
            synchronized (Singleton.class){
                if (singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }



}
