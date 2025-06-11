package com.byodian.class_objects;

public class OuterClass {
    private int value = 10;
    String outerField = "Outer field";
    static String staticOuterField = "Static Outer field";

    // As with instance methods and variables, an inner class is associated with an instance of its enclosing class and 
    // has direct access to that object's methods and fields.
    class InnerClass {
        private int value = 100;
        public void show(int value) {
            System.out.println("value= " + value);
            System.out.println("this.value= " + this.value);
            System.out.println("OuterClas.this.value = " + OuterClass.this.value);
        }

        void accessMembers() {
            System.out.println(outerField);
            System.out.println(staticOuterField);
        }
    }

    // As with class methods and variables, a static nested class is associated with its outer class.
    static class StaticNestedClass {
        void accessMembers(OuterClass outer) {
            // Compiler error: Cannot make a static reference to the non-static
            // field outerField
            // System.out.println(outerField);
            System.out.println(outer.outerField);
            System.out.println(staticOuterField);
        }

    }

    public static void main(String[] args) {
        System.out.println("Inner class:");
        System.out.println("-------------------");
        OuterClass outerClass = new OuterClass();
        InnerClass innerClass = outerClass.new InnerClass();
        innerClass.show(1000);
        innerClass.accessMembers();

        System.out.println("\nStatic nested class:");
        System.out.println("-------------------");
        StaticNestedClass staticNestedClass = new StaticNestedClass();
        staticNestedClass.accessMembers(outerClass);

        System.out.println("\nTop-level class:");
        System.out.println("-------------------");

    }

}
