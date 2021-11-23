package com.yhy.kafka;

import com.sun.webkit.dom.RGBColorImpl;
import org.w3c.dom.css.RGBColor;

public class SynchronizedDemo2 {

    Object object = new Object();
    public void method1() {
        synchronized (object) {

        }
        method2();
    }

    private static void method2() {

    }
}
