public class JavaThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new TestThread();
        t.start();
        Thread.sleep(1); // sleep 1 ms
        t.interrupt(); // stop t thread
        t.join(); // wait t until stop

        Thread barT = new BarThread();
        barT.start();
        Thread.sleep(1);
        BarThread.running = false;
    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            int n = 0;
            while (!isInterrupted()) {
                n++;
                System.out.println("Count: " + n);
            }
            System.out.println("Task finish");
        }
    }

    public static class TestThread extends Thread {
        public void run() {
            Thread hello = new HelloThread();
            hello.start();
            try {
                hello.join(); // wait unti hello thread stop, if interrupt TestThread then throws
                              // InterruptedException
            } catch (InterruptedException e) {
                System.out.println("hello join method throws interruptted exception in TestThread");
            }
            hello.interrupt();
        }
    }

    public static class HelloThread extends Thread {
        @Override
        public void run() {
            int n = 0;
            while (!isInterrupted()) {
                n++;
                System.out.println("Count: " + n);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    public static class BarThread extends Thread {
        public static volatile boolean running = true;

        @Override
        public void run() {
            int n = 0;
            while (running) {
                n++;
                System.out.println("Count: " + n);
            }
        }
    }
}
