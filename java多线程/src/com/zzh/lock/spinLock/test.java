package com.zzh.lock.spinLock;

import java.util.HashMap;

public class test {
    public static void main(String[] args) {
        spinLock spinLock = new spinLock();
        spinLock.myLock();
        spinLock.myLock();
        System.out.println("ÔËÐÐÖÐ");
        spinLock.unMyLock();
    }
}
