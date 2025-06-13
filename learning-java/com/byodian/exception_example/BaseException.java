package com.byodian.exception_example;

/**
 * @author BYJ
 * @since 2025/02/26
 **/

class BaseException {
    BaseException() {
    }

    public static  class Example {

        public static void main(String[] args) {
            try {
                // some code that throws an exception
//            throw new RuntimeException("Main exception");
                Class.forName("ClassNotFound");
            } catch (RuntimeException | ClassNotFoundException e) {
                // Catch and throw another exception caused by the first one
                System.out.println(e.getMessage());
                Throwable cause = e.getCause();
                System.out.println(cause);

                IllegalArgumentException newException =  new IllegalArgumentException("Caught exception", e);
                System.out.println(newException.getCause());

                throw newException;
            }
        }
        private static final String NAME = "hello world";

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
        }

        public void sayHello() {
            System.out.println("Hello world!");
        }
    }

    public static class SubExample extends Example {
        @Override
        public void sayHello() {
            sayHello();
        }
    }
}
