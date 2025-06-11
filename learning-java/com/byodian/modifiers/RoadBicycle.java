package com.byodian.modifiers;

import com.byodian.class_objects.Bicycle;

public class RoadBicycle extends Bicycle {
    RoadBicycle() {
        super(0, 0, 0);
    }

    public void testModifier() {
        Bicycle.getClassName();
        // A subclass decalared outside of the base class package have access to the protected method.
        protectedMethodWithinSuper();
    }

    public static void main(String[] args) {
        RoadBicycle roadBicycle = new RoadBicycle();
        roadBicycle.protectedMethodWithinSuper();
    }
} 