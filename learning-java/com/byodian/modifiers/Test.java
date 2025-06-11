package com.byodian.modifiers;

import com.byodian.class_objects.MountainBike;

public class Test {
    public static String publicVar = "Hello world!";

    private static int privateVar = 0;

    public static void setPrivateVar(int value) {
        privateVar = value;
    }

    public static int getPrivateVar() {
        return privateVar;
    }

    public void accessMountainBike() {
        MountainBike mountainBike = new MountainBike();
        mountainBike.speedUp(1);
        // have no access to package-private and protected fileds and methods.
        // mountainBike.protectedMethod();
        // mountainBike.packagePrivateMethod();

    }
}
