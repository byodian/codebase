import java.util.concurrent.*;

public class ScheduledThreadPoolExample  {
    public static void main(String[] args) {
        // 核心线程数 = 2
        ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(2);

        // 可选：设置线程为守护线程
        scheduler.setThreadFactory(r -> {
            Thread t = new Thread(r);
            t.setDaemon(true); // 守护线程，JVM 退出时自动结束
            t.setName("simple-delay-thread-" + t.getId());
            return t;
        });

        // 可选：取消任务后立即移除，避免内存积压
        scheduler.setRemoveOnCancelPolicy(true);

        // 测试任务：5秒后执行
        scheduler.schedule(() -> {
            System.out.println("Delayed task executed!");
            scheduler.shutdown();
        }, 5, TimeUnit.SECONDS);

        System.out.println("Task scheduled, main thread ends.");
    }
}

